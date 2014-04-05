package com.sfv.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sfv.entitybean.system.Sign;

public class SignForm extends Sign{
	public SignForm(Sign sign){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm E");
		this.setId(sign.getId());
		this.setUser(sign.getUser());
		this.setInfo(sign.getInfo());
		this.setTime(format.format(sign.getCreatTime()));
		this.actionDesc = "更新了一条签名";
	}
	private String time;
	
	private String actionDesc;
	
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
