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

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.sfv.action.BaseAction;
import com.sfv.common.Constants;
import com.sfv.entitybean.system.SystemUser;

public class UploadHeadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private File fileInput;
	private String fileInputFileName;
	
	private String fileNameAtServer;

	public String getFileNameAtServer() {
		return fileNameAtServer;
	}

	public void setFileNameAtServer(String fileNameAtServer) {
		this.fileNameAtServer = fileNameAtServer;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	// @SuppressWarnings("deprecation")
	public String uploadPhoto() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try{
			//保存附件
			SystemUser user = (SystemUser)request.getSession().getAttribute("USERINFO");	
			String root = ServletActionContext.getServletContext().getRealPath(Constants.UPLOAD_FILE_PATH );
			String path = root + "/photos/" + user.getId() + "/";
			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdirs();
			}
			String extendFileType = fileInputFileName.substring(fileInputFileName.lastIndexOf('.') + 1);
			in = new BufferedInputStream(new FileInputStream(fileInput), 5120);
			//保存在服务器上的文件名称，防止重名，采用时间戳命名
			fileNameAtServer = new Date().getTime() + "." + extendFileType;
			out = new BufferedOutputStream(new FileOutputStream(path + "/"+ fileNameAtServer), 5120);
            byte[] buf = new byte[5120];
            while (in.read(buf) > 0) {
                out.write(buf);
            }
            //上传成功，回写页面
			String imgPath = Constants.UPLOAD_FILE_PATH + "photos/" + user.getId() + "/" + fileNameAtServer;
            String msg = imgPath;
			response.getWriter().print(msg);
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print("上传文件<b>" + fileInputFileName + "<b>时失败");
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
		
		return null; 
	}

}
