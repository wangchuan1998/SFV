package com.sfv.entitybean.system;

/**
 * PhotoComment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PhotoComment implements java.io.Serializable {

	// Fields

	private String id;
	private String photoid;
	private String userid;
	private String username;
	private String comment;
	private Long commentTime;

	// Constructors

	/** default constructor */
	public PhotoComment() {
	}

	/** full constructor */
	public PhotoComment(String photoid, String userid, String username,
			String comment, Long commentTime) {
		this.photoid = photoid;
		this.userid = userid;
		this.username = username;
		this.comment = comment;
		this.commentTime = commentTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhotoid() {
		return this.photoid;
	}

	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Long commentTime) {
		this.commentTime = commentTime;
	}

}