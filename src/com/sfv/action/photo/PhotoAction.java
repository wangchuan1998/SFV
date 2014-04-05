package com.sfv.action.photo;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.sfv.action.BaseAction;
import com.sfv.common.Constants;
import com.sfv.common.ErrorCode;
import com.sfv.common.VertityUtil;
import com.sfv.entitybean.system.PhotoComment;
import com.sfv.entitybean.system.Photos;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.form.photo.PhotoAlbumsForm;
import com.sfv.form.photo.PhotoForm;
import com.sfv.service.UserService;
import com.sfv.service.photo.PhotoService;

public class PhotoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3511181400971319732L;

	private List<PhotoAlbumsForm> albums;
	//个人相册
	public String queryAlbums() {  
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
	    albums = photoService.queryAlbums(user.getId());
		return SUCCESS;
	}
	//根据相册查询照片
	public String queryPhotos() { 
		if(!VertityUtil.isNum(ablumid))
		{
			return ERROR;
		}
	    photos = photoService.queryPhotos(Integer.valueOf(ablumid));
		return SUCCESS;
	}
	//查看其他人相册
	public String viewAlbums() {  
		user = userService.getUserById(userid);
	    albums = photoService.queryAlbums(userid);
		return SUCCESS;
	}
	public String queryPhoto(){
		photoForm = photoService.queryPhoto(imgId);
		return SUCCESS;
	}
	public String viewPhoto(){
		photoForm = photoService.queryPhoto(imgId);
		return SUCCESS;
	}
	public String saveAlbum() {  
		dataMap = new HashMap<String, Object>();
		String description = request.getParameter("description");
		String name = request.getParameter("name");
		SystemUser userForm = (SystemUser)request.getSession().getAttribute("USERINFO");
		try{
			photoService.saveAlbum(new PhotoAlbumsForm(name, new Date(),userForm.getId(), description));
			dataMap.put("retCode", "success");
			return SUCCESS;
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		return ERROR;
	}
	public String queryComment(){
		dataMap = new HashMap<String, Object>();
		try{
			dataMap.put("data", photoService.queryComment(imgId));
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		return SUCCESS;
	}
	
	public String saveComment(){
		dataMap = new HashMap<String, Object>();
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
		try{
			PhotoComment pc = new PhotoComment(imgId, user.getId(), user.getRealname(),
					comment, System.currentTimeMillis());
			photoService.saveComment(pc);
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		
		return SUCCESS;
	}
	public String selectPhoto(){
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
		this.albums = photoService.queryAllAlbums(user.getId());
		return SUCCESS;
	}
	public String savePhoto() {  
		dataMap = new HashMap<String, Object>();
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
		try{
			if(null != fileNameAtServer)
			{
				for(int i=0; i<fileNameAtServer.size(); i++)
				{
					String fullName = fileNameAtServer.get(i);
					String fileName = fullName.split("\\.")[0];
					String smallName = fullName.split("\\.")[0] + "_small";
					String extendName = fullName.split("\\.")[1];
					String path = "/photos/" + user.getId() + "/";
					PhotoForm photo = new PhotoForm(fileRealName.get(i), null, new Date(), 
							path + fileName, path + smallName, extendName, user.getId(), Integer.parseInt(ablumid));
					photoService.savePhoto(photo);
				}
			}
			dataMap.put("retCode", "success");
		}catch(Exception e){
			log.error("savePhoto-!",e);
			dataMap.put("retCode", "failure");
		}
		return SUCCESS;
	}
	
	public String deleteSelected(){
		dataMap = new HashMap<String, Object>();
		String root = ServletActionContext.getServletContext().getRealPath(Constants.UPLOAD_FILE_PATH );
		SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");
		String path = root + "/photos/" + user.getId() + "/" + fileNameAtServer.get(0);
		try{
			this.response.setContentType("text/html;charset=utf-8");
			log.info(path);
			File file = new File(path);
			if(!file.isFile()){
				log.error("this is not a file!");
	            dataMap.put("retCode", ErrorCode.IS_NOT_FILE);
			}else if(!file.exists()){
				log.error("the file does't exist!");
	            dataMap.put(ErrorCode.FILE_NOT_EXSIT, ErrorCode.IS_NOT_FILE);
			}else{
				file.delete();
	            dataMap.put("retCode", "success");
			}
		}catch(Exception e){
			log.error("delete file failed", e);
            dataMap.put("retCode", "failure");
		}
		
		return SUCCESS;
	}
	
	private PhotoService photoService;
	
	private UserService userService;
	
	private SystemUser user;
	
	private String userid;
	
	private String name;
	
	private String description;
	
	private String ablumid;
	
	private List<String> fileNameAtServer;
	
	private List<String> descriptions;
	
	private List<String> fileRealName;
	
	private PhotoForm photoForm;
	
	private String imgId;
	
	private String comment;
	
	private List<Photos> photos;
	
	private Map<String, Object> dataMap;
	
	static Logger log = Logger.getLogger("PhotoAction");
	
	@JSON(serialize=false)
	public PhotoService getPhotoService() {
		return photoService;
	}

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	public List<PhotoAlbumsForm> getAlbums() {
		return albums;
	}
	public void setAlbums(List<PhotoAlbumsForm> albums) {
		this.albums = albums;
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
	public List<String> getFileNameAtServer() {
		return fileNameAtServer;
	}
	public void setFileNameAtServer(List<String> fileNameAtServer) {
		this.fileNameAtServer = fileNameAtServer;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getAblumid() {
		return ablumid;
	}
	public void setAblumid(String ablumid) {
		this.ablumid = ablumid;
	}
	
	
	public static void main(String[] args){
		File f = new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\sfv\\userfiles\\photos\\8\\11.txt");
		f.delete();
	}
	public PhotoForm getPhotoForm() {
		return photoForm;
	}
	public void setPhotoForm(PhotoForm photoForm) {
		this.photoForm = photoForm;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
	public List<String> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	public List<String> getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(List<String> fileRealName) {
		this.fileRealName = fileRealName;
	}
}
