package com.sfv.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sfv.entitybean.system.Sign;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.service.SignService;

public class SignAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7242043856113270102L;

	/**
	 * 查询用户签名
	 * @return
	 */
	public String query(){
		dataMap = new HashMap<String, Object>();
		try{
			dataMap.put("data", signService.query());
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		return SUCCESS;
	}
	
	/**
	 * 保存用户签名
	 * @return
	 */
	public String save(){
		dataMap = new HashMap<String, Object>();
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
		try{
			Sign sign = new Sign();
			sign.setUser(user);
			sign.setInfo(info);
			sign.setCreatTime(new Date());
			signService.save(sign);
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		
		return SUCCESS;
	}
	
	private Map<String, Object> dataMap;
	
	private SignService signService;
	
	private String info;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public SignService getSignService() {
		return signService;
	}
	public void setSignService(SignService signService) {
		this.signService = signService;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
