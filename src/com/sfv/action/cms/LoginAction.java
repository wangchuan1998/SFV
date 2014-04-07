package com.sfv.action.cms;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sfv.action.BaseAction;
import com.sfv.common.AjaxReponse;
import com.sfv.common.Constants;
import com.sfv.common.ErrorCode;
import com.sfv.common.MD5;
import com.sfv.common.VertityUtil;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.service.UserService;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257721497548400395L;
	
	private UserService userService;
	
	//登录
	public String adminLogin()throws Exception {  
		SystemUser user = userService.login(this.getEmail(), this.getPassword());
		if(user == null)
		{
			return LOGIN;
		}
		HttpSession session = request.getSession();
		session.setAttribute("USERINFO", user);
		return SUCCESS;
	}
	//退出登录
	public String logout()throws Exception {  
		SystemUser sourceUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		ServletContext application = request.getSession().getServletContext(); 
        Map<String, Object> connections = (Map<String, Object>)application.getAttribute("cometConnections");
        if(!VertityUtil.isEmptyMap(connections)){
        	connections.remove(sourceUser.getId());
        }
		request.getSession().removeAttribute("USERINFO");
		return SUCCESS;
	}
	
	

	static Logger log = Logger.getLogger("LoginAction");
	
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String headUrl;
	
	private char sex;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
}
