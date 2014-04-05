package com.sfv.entitybean.system;

import java.util.Date;

/**
 * Sign entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sign implements java.io.Serializable {

	// Fields

	private Integer id;
	private String info;
	private SystemUser user;
	private Date creatTime;

	// Constructors

	/** default constructor */
	public Sign() {
	}

	/** full constructor */
	public Sign(String info, SystemUser user, Date creatTime) {
		this.info = info;
		this.user = user;
		this.creatTime = creatTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

}