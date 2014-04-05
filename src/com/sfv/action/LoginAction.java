package com.sfv.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sfv.common.AjaxReponse;
import com.sfv.common.Constants;
import com.sfv.common.ErrorCode;
import com.sfv.common.MD5;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.service.UserService;
import com.sfv.common.VertityUtil;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257721497548400395L;
	
	private UserService userService;
	
	//登录
	public String login()throws Exception {  
		SystemUser user = userService.login(this.getEmail(), this.getPassword());
		if(user == null)
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain"); 
			AjaxReponse rep = new AjaxReponse(ErrorCode.PASSWORD_ERROR, "");
			JSONObject json = JSONObject.fromObject(rep);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.close();
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("USERINFO", user);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain"); 
			JSONObject json = JSONObject.fromObject(user);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.close();
		}
		
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
	
	//用户注册
	public void saveUser()throws Exception {  
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthDay = sdf.format(new Date());
		String sex = request.getParameter("sex");
		SystemUser user = new SystemUser();
		user.setEmail(email);
		user.setRealname(username);
		user.setPassword(MD5.MD5Encode(password));
		user.setSex(sex);
		user.setBirthDay(birthDay);
		user.setGrade(Constants.USER_GRADE_ONE);
		user.setRegisterTime(String.valueOf(System.currentTimeMillis()));
		userService.saveUser(user);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain"); 
		AjaxReponse rep = new AjaxReponse("true", "成功");
		JSONObject json = JSONObject.fromObject(rep);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
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
