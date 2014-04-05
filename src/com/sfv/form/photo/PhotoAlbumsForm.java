package com.sfv.form.photo;

import java.util.Date;
import java.util.List;

import com.sfv.entitybean.system.Photos;


public class PhotoAlbumsForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date createTime;
	private String userid;
	private String description;
	private List<Photos> photos;

	// Constructors

	/** default constructor */
	public PhotoAlbumsForm() {
	}

	/** full constructor */
	public PhotoAlbumsForm(String name, Date createTime, String userid,
			String description) {
		this.name = name;
		this.createTime = createTime;
		this.userid = userid;
		this.description = description;
	}
	public PhotoAlbumsForm(Integer id, String name, Date createTime, String userid,
			String description) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.userid = userid;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

}
