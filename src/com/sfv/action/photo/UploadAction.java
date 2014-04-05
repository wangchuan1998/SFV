package com.sfv.action.photo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sfv.action.BaseAction;
import com.sfv.common.Constants;
import com.sfv.entitybean.system.SystemUser;

public class UploadAction extends BaseAction{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8177215048628880680L;
	
	private File uploadFile;
	
	private String uploadFileFileName;
	
	private String uploadFileContentType;
	
	private String name;
	
	private String description;
	
	private String fileNameAtServer;
	
	private Map<String, Object> dataMap;

	public String execute(){ 
		System.out.println("-------------------------------------");
		InputStream in = null;
		OutputStream out = null;
		try{
			dataMap = new HashMap<String, Object>();
			//保存附件
			SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");	
			String root = ServletActionContext.getServletContext().getRealPath(Constants.UPLOAD_FILE_PATH );
			String path = root + "/photos/" + user.getId() + "/";
			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdirs();
			}
			String extendFileType = uploadFileFileName.substring(uploadFileFileName.lastIndexOf('.') + 1);
			in = new BufferedInputStream(new FileInputStream(uploadFile), 5120);
			//保存在服务器上的文件名称，防止重名，采用时间戳命名
			fileNameAtServer = new Date().getTime() + "." + extendFileType;
			out = new BufferedOutputStream(new FileOutputStream(path + "/"+ fileNameAtServer), 5120);
            byte[] buf = new byte[5120];
            while (in.read(buf) > 0) {
                out.write(buf);
            }
            dataMap.put("uploadFileFileName", uploadFileFileName);
            dataMap.put("fileNameAtServer", fileNameAtServer);
            dataMap.put("path", Constants.UPLOAD_FILE_PATH + "/photos/" + user.getId() + "/");
            dataMap.put("retCode", "success");
			dataMap.put("retInfo", "照片上传成功");
		}catch(Exception e){
			e.printStackTrace();
			log.error("execute:" + e);
			dataMap.put("retCode", "failure");
			dataMap.put("retInfo", "照片上传失败");
		}finally{
			if(null != in){
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return SUCCESS;
	}
	
	static Logger log = Logger.getLogger("UploadAction");

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
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

	public String getFileNameAtServer() {
		return fileNameAtServer;
	}

	public void setFileNameAtServer(String fileNameAtServer) {
		this.fileNameAtServer = fileNameAtServer;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}


	
	
}