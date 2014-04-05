package com.sfv.entitybean.system;

import java.util.Date;

/**
 * PhotoAlbums entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PhotoAlbums implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Date createTime;
	private String userid;
	private String description;

	// Constructors

	/** default constructor */
	public PhotoAlbums() {
	}

	/** full constructor */
	public PhotoAlbums(String name, Date createTime, String userid,
			String description) {
		this.name = name;
		this.createTime = createTime;
		this.userid = userid;
		this.description = description;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}