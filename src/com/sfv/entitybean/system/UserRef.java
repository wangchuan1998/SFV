package com.sfv.entitybean.system;

/**
 * UserRef entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserRef implements java.io.Serializable {

	// Fields

	private String userid;
	private String refuserid;

	// Constructors

	/** default constructor */
	public UserRef() {
	}

	/** minimal constructor */
	public UserRef(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public UserRef(String userid, String refuserid) {
		this.userid = userid;
		this.refuserid = refuserid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRefuserid() {
		return this.refuserid;
	}

	public void setRefuserid(String refuserid) {
		this.refuserid = refuserid;
	}

}