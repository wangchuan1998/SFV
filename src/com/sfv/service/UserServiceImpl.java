package com.sfv.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.sfv.dao.UserDao;
import com.sfv.entitybean.system.SystemUser;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<SystemUser> queryByCondiction(Map values) {
		return userDao.queryByCondiction(values);
	}

	public List<SystemUser> queryNearby(Map<String, String> values) {
		return userDao.queryNearby(values);
	}
	
	public List<SystemUser> queryFriendByUserId(String userid) {
		return userDao.queryFriendByUserId(userid);
	}

	
	public void saveUser(SystemUser form) {
		
		try{
			userDao.saveUser(form);
		}catch(DataAccessException e){
			throw new RuntimeException(e);
		}
		
	}

	public SystemUser login(String email, String password) {
		return userDao.login(email, password);
	}

	public SystemUser getUserById(String id) {
		
		return userDao.getUserById(id);
	}

	public void addFriend(String userid, String refid) {
		userDao.addFriend(userid, refid);
	}

	public boolean isFriend(String userid, String refid) {
		
		return userDao.isFriend(userid, refid);
	}
}
