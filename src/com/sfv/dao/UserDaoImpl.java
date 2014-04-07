package com.sfv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sfv.common.MD5;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.entitybean.system.UserRef;

public class UserDaoImpl extends BaseDao implements UserDao{
	
	public List<SystemUser> queryByCondiction(Map values) {
		return this.getHibernateTemplate().find("from SystemUser where email!='admin'");
	}
	
	public List<SystemUser> queryNearby(Map<String, String> values) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("from SystemUser su");
		hqlBuffer.append(" where 1=1 ");
		if(values.containsKey("curuserid"))
		{
			hqlBuffer.append(" and su.id not in(select refuserid from UserRef ur where ur.userid=")
			.append("'").append(values.get("curuserid")).append("')")
			.append(" and su.id !='").append(values.get("curuserid")).append("'");
		}
		
		return this.getHibernateTemplate().find(hqlBuffer.toString());
	}

	public SystemUser getUserById(String id) {
		try{
			SystemUser user = (SystemUser)this.getHibernateTemplate().get(SystemUser.class, id);
			return user;
		}catch(DataAccessException e){
			log.error("getUserById:" + e);
		}catch(ClassCastException e){
			log.error("getUserById:can't format Object to SystemUser" +e);
		}
		
		return null;
	}

	public void saveUser(SystemUser form) {
		try{
			this.getHibernateTemplate().saveOrUpdate(form);
			
		}catch(DataAccessException e){
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
		
	}

	public SystemUser login(String email, String password) {
		String[] condition = {email, MD5.MD5Encode(password)};
		List<SystemUser> user = new ArrayList<SystemUser>();
		try{
			user = this.getHibernateTemplate().find("from SystemUser where email=? and password=?", condition);
		}catch(DataAccessException e){
			log.error(e.getMessage());
		}
		
		if(user.size() > 0){
			SystemUser su = user.get(0);
			su.setLoginTimes((su.getLoginTimes()==null ? 0 : su.getLoginTimes())+1);
			try{
				this.getHibernateTemplate().saveOrUpdate(su);
			}catch(Exception e){
				log.error("update user information error:" + e);
			}
			return su;
		}
		return null;
	}

	/**
	 * 添加好友
	 * userid：当前用户
	 * refid：好友ID
	 */
	public void addFriend(String userid, String refid) {
		try{
			UserRef userRef = this.getUserRef(userid);
			if(null != userRef){
				String refUserId = userRef.getRefuserid();
				userRef.setRefuserid(refUserId.concat(",").concat(refid));
			}else{
				userRef = new UserRef(userid, refid);
			}
			this.getHibernateTemplate().saveOrUpdate(userRef);
		}catch(Exception e){
			log.error("update user ref error:" + e);
		}
	}
	
	/**
	 * 获取用户好友关系
	 * @param userid
	 * @return
	 */
	public UserRef getUserRef(String userid){
		try{
			List<UserRef> ref = new ArrayList<UserRef>();
			ref = getHibernateTemplate().find("from UserRef where id.userid=?", userid);
			if(ref.size() > 0){
				return ref.get(0);
			}
		}catch(Exception e){
			log.error("getUserRef error:" + e);
		}
		return null;
	}

	/**
	 * 判断是否是好友关系 
	 */
	public boolean isFriend(String userid, String refid) {
		try{
			List<UserRef> ref = new ArrayList<UserRef>();
			List<String> refids = new ArrayList<String>();
			ref = getHibernateTemplate().find("from UserRef where id.userid=?", userid);
			
			if(ref.size() > 0){
				if(ref.get(0).getRefuserid().contains(refid)){
					return true;
				}
				
			}
		}catch(Exception e){
			log.error("getUserRef error:" + e);
		}
		return false;
	}

	static Logger log = Logger.getLogger("UserDaoImpl");

	public List<SystemUser> queryFriendByUserId(String userid) {
		List<SystemUser> friends = new ArrayList<SystemUser>();
		friends = getHibernateTemplate().find("from SystemUser su where su.id in(select refuserid from UserRef ur where ur.userid=?)", userid);
		return friends;
	}
}
