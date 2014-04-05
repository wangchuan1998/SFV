package com.sfv.service;

import java.util.List;
import java.util.Map;

import com.sfv.entitybean.system.SystemUser;


public interface UserService {
	
	public List<SystemUser> queryByCondiction(Map values);
	
	public List<SystemUser> queryNearby(Map<String, String> values);
	
	public void saveUser(SystemUser form); 
	
	public SystemUser login(String email, String password);
	
	//根据ID查询指定用户
	public SystemUser getUserById(String id);
	
	//加好友
	public void addFriend(String userid, String refid);
	
	//判断两个用户是否是好友关系
	public boolean isFriend(String userid, String refid);
	
	public List<SystemUser> queryFriendByUserId(String userid);
}
