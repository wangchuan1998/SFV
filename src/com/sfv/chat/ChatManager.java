package com.sfv.chat;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContextFactory;

import com.sfv.entitybean.system.SystemUser;

public class ChatManager {
	
	/**
	 * 发送消息
	 * @param receiverid：接收者ID
	 * @param msg;消息内容
	 */
	public void send(String receiverid,String msg){
		//得到用户session
		HttpSession session = WebContextFactory.get().getSession();
		//发送者
		SystemUser user = (SystemUser)session.getAttribute("USERINFO");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		final String sid = user.getId() ;
		final String sname = user.getRealname();
		final String rid = receiverid ;
		final String autoMessage = "<font color='#888888'>" + user.getRealname() + " " + sdf.format(new Date()) + "<br></font><span>" + msg + "</span>";
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				if (session.getAttribute(ChatConstants.LG_USER_ID) == null){
					return false;
				}else{
					return (session.getAttribute(ChatConstants.LG_USER_ID)).equals(rid);
				}
			}
		}, new Runnable(){
			private ScriptBuffer script = new ScriptBuffer();
			public void run() {
				//回调JS函数
				script.appendCall("showMessage", autoMessage, sid, sname);
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});
	}
}
