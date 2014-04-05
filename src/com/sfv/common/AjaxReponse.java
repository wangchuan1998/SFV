package com.sfv.common;

public class AjaxReponse {
	private String retCode;
	private String retInfo;
	
	public AjaxReponse(String retCode, String retInfo){
		this.retCode = retCode;
		this.retInfo = retInfo;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
}
