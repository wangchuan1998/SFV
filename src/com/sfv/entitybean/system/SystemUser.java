package com.sfv.entitybean.system;

/**
 * SystemUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SystemUser implements java.io.Serializable {

	// Fields

	private String id;
	private String realname;
	private String headUrl;
	private String email;
	private String password;
	private Integer grade;
	private String sex;
	private String birthDay;
	private String registerTime;
	private Integer loginTimes;
	private String region;
	private String regionDetail;
	private String degree;
	private Integer height;

	// Constructors

	/** default constructor */
	public SystemUser() {
	}

	/** minimal constructor */
	public SystemUser(String password) {
		this.password = password;
	}

	/** full constructor */
	public SystemUser(String realname, String headUrl, String email,
			String password, Integer grade, String sex, String birthDay,
			String registerTime, Integer loginTimes, String region,
			String regionDetail, String degree, Integer height) {
		this.realname = realname;
		this.headUrl = headUrl;
		this.email = email;
		this.password = password;
		this.grade = grade;
		this.sex = sex;
		this.birthDay = birthDay;
		this.registerTime = registerTime;
		this.loginTimes = loginTimes;
		this.region = region;
		this.regionDetail = regionDetail;
		this.degree = degree;
		this.height = height;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getLoginTimes() {
		return this.loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionDetail() {
		return this.regionDetail;
	}

	public void setRegionDetail(String regionDetail) {
		this.regionDetail = regionDetail;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}