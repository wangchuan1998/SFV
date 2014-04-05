package com.sfv.service;

import java.util.List;

import com.sfv.entitybean.system.Sign;
import com.sfv.form.SignForm;

public interface SignService {
	public List<SignForm> query();
	public void save(Sign pojo); 
}