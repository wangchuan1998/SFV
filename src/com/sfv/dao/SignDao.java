package com.sfv.dao;

import java.util.List;

import com.sfv.entitybean.system.Sign;
import com.sfv.entitybean.system.SystemUser;

public interface SignDao {
	public List<Sign> query();
	
	public void save(Sign pojo); 
}
