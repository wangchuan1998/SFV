package com.sfv.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sfv.entitybean.system.Sign;

public class SignDaoImpl extends BaseDao implements SignDao{

	
	public List<Sign> query() {
		List<Sign> list = new ArrayList<Sign>();
		try{
			list = (List<Sign>)this.getHibernateTemplate().find("from Sign order by creatTime desc");
		}catch(DataAccessException e){
			log.error("query::has exception when query table[sign]" + e);
		}
		return list;
	}

	public void save(Sign pojo) {
		try{
			this.getHibernateTemplate().save(pojo);
		}catch(DataAccessException e){
			log.error("query::has exception when save user sign" + e);
			e.printStackTrace();
		}
	}

	static Logger log = Logger.getLogger("SignDaoImpl");
}
