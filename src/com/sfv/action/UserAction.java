package com.sfv.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sfv.entitybean.system.SystemUser;
import com.sfv.service.UserService;

public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257721497548400395L;
	
	//首页
	public String index()throws Exception {  
		return SUCCESS;
	}
	
	//查看其他人首页
	public String viewIndex()throws Exception {  
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		user = userService.getUserById(userid);
		friend = userService.isFriend(currentUser.getId(), userid);
		return SUCCESS;
	}
	
	//个人资料
	public String userinfo()throws Exception {
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		user = userService.getUserById(currentUser.getId());
		return SUCCESS;
	}
	
	//所有用户列表 
	public String userlist()throws Exception {
		friends = userService.queryByCondiction(null);
		return SUCCESS;
	}
	
	//查看其他人个人资料
	public String viewUser()throws Exception {
		user = userService.getUserById(userid);
		return SUCCESS;
	}
	
	//互加好友
	public String addFriend()throws Exception {
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		userService.addFriend(currentUser.getId(), refUserId);
		return SUCCESS;
	}
	
	//保存个人资料
	public String save()throws Exception {
		try{
			userService.saveUser(user);
			user = userService.getUserById(user.getId());
			//更新session
			HttpSession session = request.getSession();
			session.setAttribute("USERINFO", user);
		}catch(Exception e){
			log.error("save or update person information error:" + e);
		}
		
		return SUCCESS;
	}
	
	//查询好友关系
	public String queryFriend(){
		dataMap = new HashMap<String, Object>();
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		List<SystemUser> users = userService.queryFriendByUserId(currentUser.getId());
		try{
			dataMap.put("data", users);
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("querynearbyuser:",e);
			dataMap.put("retCode", "failure");
		}
		
		return SUCCESS;
	}
	
	//查询附近的人
	public String nearby(){
		dataMap = new HashMap<String, Object>();
		SystemUser currentUser = (SystemUser)request.getSession().getAttribute("USERINFO");
		Map<String, String> condistions = new HashMap<String, String>();
		condistions.put("curuserid", currentUser.getId());
		List<SystemUser> users = userService.queryNearby(condistions);
		try{
			dataMap.put("data", users);
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("querynearbyuser:",e);
			dataMap.put("retCode", "failure");
		}
		
		return SUCCESS;
	}
	

	static Logger log = Logger.getLogger("UserAction");
	
	private UserService userService;
	
	private Map<String, Object> dataMap;
	
	private List<SystemUser> friends;
	
	private SystemUser user;
	
	private String userid;
	
	private String refUserId;
	
	private String realname;
	
	private String password;
	
	private String email;
	
	private String headUrl;
	
	private char sex;
	
	private String birthDay;
	
	private String region;
	
	private String degree;
	
	private Integer height;
	
	private boolean friend;

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
		this.user.setDegree(degree);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
		this.user.setRealname(realname);
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
		if(null != headUrl && !headUrl.equals("")){
			this.user.setHeadUrl(headUrl);
		}
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
		this.user.setBirthDay(birthDay);
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
		this.user.setRegion(region);
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
		this.user.setHeight(height);
	}

	public String getRefUserId() {
		return refUserId;
	}

	public void setRefUserId(String refUserId) {
		this.refUserId = refUserId;
	}

	public boolean isFriend() {
		return friend;
	}

	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	public List<SystemUser> getFriends() {
		return friends;
	}

	public void setFriends(List<SystemUser> friends) {
		this.friends = friends;
	}
}
