package com.sfv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sfv.dao.SignDao;
import com.sfv.entitybean.system.Sign;
import com.sfv.form.SignForm;

public class SignServiceImpl implements SignService {
	private SignDao signDao;
	
	public List<SignForm> query() {
		List<Sign> list = null;
		List<SignForm> signs = new ArrayList<SignForm>();
		try{
			list = signDao.query();
			for(Sign sign : list){
				SignForm fm = new SignForm(sign);
				signs.add(fm);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		
		return signs;
	}

	public void save(Sign pojo) {
		signDao.save(pojo);
	}

	public SignDao getSignDao() {
		return signDao;
	}

	public void setSignDao(SignDao signDao) {
		this.signDao = signDao;
	}

	static Logger log = Logger.getLogger("SignServiceImpl");
}
