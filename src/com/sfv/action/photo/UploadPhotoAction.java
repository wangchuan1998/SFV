package com.sfv.action.photo;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.sfv.action.BaseAction;
import com.sfv.common.Constants;
import com.sfv.entitybean.system.SystemUser;
import com.sfv.form.UploadFileForm;

public class UploadPhotoAction extends BaseAction {

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
	

	public void uploadPhoto() throws Exception {
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
			String fileName = String.valueOf(new Date().getTime());
			fileNameAtServer = fileName + "." + extendFileType;
			out = new BufferedOutputStream(new FileOutputStream(path + "/"+ fileNameAtServer), 5120);
            byte[] buf = new byte[5120];
            while (in.read(buf) > 0) {
                out.write(buf);
            }
            
            //上传后压缩图片
            String srcFilePath = path + "/"+ fileNameAtServer;
            String descFilePath = path + "/"+ fileName + "_small" + "." + extendFileType;
            compressPic(srcFilePath, descFilePath);
            UploadFileForm photo = new UploadFileForm();
            photo.setFileNameAtServer(fileNameAtServer);
            photo.setExtendFileType(extendFileType);
            photo.setFileRealName(fileInputFileName);
            photo.setFilePath(Constants.UPLOAD_FILE_PATH + "photos/" + user.getId() + "/"+fileNameAtServer);
            JSONObject jo = JSONObject.fromObject(photo);
            PrintWriter pw = response.getWriter();
            pw.write(jo.toString());
            pw.flush();
            pw.close();
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
	}
	
	/**
	 * 功能：压缩图片
	 * @param srcFilePath:源图片路径
	 * @param descFilePath：压缩后图片路径
	 * @return
	 */
	public static boolean compressPic(String srcFilePath, String descFilePath) {   
		File file = null;   
		BufferedImage src = null;   
		FileOutputStream out = null;   
		ImageWriter imgWrier;   
		ImageWriteParam imgWriteParams;   
		
		// 指定写图片的方式为 jpg   
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();   
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);   
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT   
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);   
		// 这里指定压缩的程度，参数qality是取值0~1范围内，   
		imgWriteParams.setCompressionQuality((float)0.05);   
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);   
		ColorModel colorModel = ColorModel.getRGBdefault();   
		// 指定压缩时使用的色彩模式   
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,    
				colorModel.createCompatibleSampleModel(16, 16)));  
		
		try  {   
			if(StringUtils.isBlank(srcFilePath)) {   
				return false;   
			}   
			else {   
				file = new File(srcFilePath);   
				src = ImageIO.read(file);   
				out = new FileOutputStream(descFilePath);   
				
				imgWrier.reset();   
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造   
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));   
				// 调用write方法，就可以向输入流写图片   
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);   
				out.flush();   
				out.close();   
			}   
		}	catch(Exception e)   
		{   
			e.printStackTrace();   
			return false;   
		}   
		return true;   
	}  

}
