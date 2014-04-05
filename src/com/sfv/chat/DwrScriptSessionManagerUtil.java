package com.sfv.chat;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.Browser;
import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.impl.DefaultScriptSession;
import org.directwebremoting.impl.DefaultScriptSessionManager;

import com.sfv.common.VertityUtil;
import com.sfv.entitybean.system.SystemUser;

public class DwrScriptSessionManagerUtil extends DefaultScriptSessionManager{
	
	private static final long serialVersionUID = -7504612622407420071L;
	
	public static final String SS_ID="DWR_ScriptSession_Id";
	
	static Logger log = Logger.getLogger("DwrScriptSessionManagerUtil");
	
	/**
	 * 初始化scriptsession，并对旧session销毁
	 */
	public DwrScriptSessionManagerUtil(){
		addScriptSessionListener(new ScriptSessionListener(){

			public void sessionDestroyed(ScriptSessionEvent event) {
				
				
			}

			public void sessionCreated(ScriptSessionEvent event) {
				try{
					//创建新的scriptsession
					ScriptSession scriptSession = event.getSession();
					//得到用户session
					HttpSession session = WebContextFactory.get().getSession();
					SystemUser user = (SystemUser)session.getAttribute("USERINFO");
					String userId = user.getId();
					//如果用户已退出，销毁scriptsession
					if(null == user){
						scriptSession.invalidate();   
						session.invalidate();  
				        log.info("user is logout"); 
						return;
					}
					
					//查找SS_ID
					String ssId = (String) session.getAttribute(SS_ID);
					//scriptsession已经存在
					if(!VertityUtil.isEmpty(ssId)){
						//注销旧的scriptsession
						DefaultScriptSession oldScriptSession = sessionMap.get(ssId);
						if(null != oldScriptSession)
						{
							invalidate(oldScriptSession);
					        log.info("destroy old scriptsession");
						}
					}
					session.setAttribute(SS_ID, scriptSession.getId());
					//绑定用户ID到ScriptSession上
					scriptSession.setAttribute(ChatConstants.LG_USER_ID, user.getId());
			        log.info("a ScriptSession is created!");
				}catch(Exception e){
			        log.info("a ScriptSession is distroyed!");
				}
			}
			
		});
	}
	
	public static void invalidate(String ssId){
		Browser.withSession(ssId, new Runnable() {
			   
			public void run() {
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for(ScriptSession session : sessions)
				{
					session.invalidate();
				}
			}
		}); 
	}
}
