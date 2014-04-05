package com.sfv.entitybean.system;

import java.util.Date;

/**
 * Photos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Photos implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String description;
	private Date createTime;
	private String imgUrl;
	private String imgUrlS;
	private String extendName;
	private String userid;
	private Integer albumid;

	// Constructors

	/** default constructor */
	public Photos() {
	}

	/** full constructor */
	public Photos(String name, String description, Date createTime,
			String imgUrl, String imgUrlS, String extendName, String userid, Integer albumid) {
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.imgUrl = imgUrl;
		this.imgUrlS = imgUrlS;
		this.extendName = extendName;
		this.userid = userid;
		this.albumid = albumid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getAlbumid() {
		return this.albumid;
	}

	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
	}

	public String getImgUrlS() {
		return imgUrlS;
	}

	public void setImgUrlS(String imgUrlS) {
		this.imgUrlS = imgUrlS;
	}

	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

}