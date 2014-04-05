package com.sfv.form.photo;

import java.util.Date;

import com.sfv.entitybean.system.Photos;

public class PhotoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2892402379993493109L;
	private String id;
	private String name;
	private String description;
	private Date createTime;
	private String imgUrl;
	private String imgUrlS;
	private String extendName;
	private String userid;
	private Integer albumid;
	private String filename;
	
	// Constructors

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/** default constructor */
	public PhotoForm() {
	}
	
	/** full constructor */
	public PhotoForm(String name, String description, Date createTime, String imgUrl, 
			String imgUrlS, String extendName, String userid,
			Integer albumid) {
		this.name = name;
		this.createTime = createTime;
		this.userid = userid;
		this.description = description;
		this.imgUrl = imgUrl;
		this.imgUrlS = imgUrlS;
		this.extendName = extendName;
		this.albumid = albumid;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getAlbumid() {
		return albumid;
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
	
	public PhotoForm copyFiled(Photos photo) {
		this.id = photo.getId();
		this.name = photo.getName();
		this.createTime = photo.getCreateTime();
		this.userid = photo.getUserid();
		this.description = photo.getDescription();
		this.imgUrl = photo.getImgUrl();
		this.imgUrlS = photo.getImgUrlS();
		this.extendName = photo.getExtendName();
		this.albumid = photo.getAlbumid();
		
		return this;
	}
}
