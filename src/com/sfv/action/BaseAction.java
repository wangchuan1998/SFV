package com.sfv.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import org.apache.struts2.interceptor.ServletResponseAware; 

import org.apache.struts2.interceptor.SessionAware;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements 
	ServletRequestAware,ServletResponseAware, SessionAware{
	
	static Logger log = Logger.getLogger("BaseAction");
	
	public HttpServletRequest request;
	
	public HttpServletResponse response;
	
	public Map session;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;     
	}   
	
	public void setServletResponse(HttpServletResponse response) {   
		this.response = response;     
	}   
	
	public void setSession(Map map) {   
		this.session = map;     
	} 
}