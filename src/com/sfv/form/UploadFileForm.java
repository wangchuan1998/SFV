package com.sfv.form;

public class UploadFileForm implements java.io.Serializable {
	
	private static final long serialVersionUID = -3101430770926206037L;

	//文件在服务器上的名称（一般采用时间戳定义）
	private String fileNameAtServer;
	
	//文件后缀名
	private String extendFileType;
	
	//文件路径
	private String filePath;
	
	//文件真实名称
	private String fileRealName;

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNameAtServer() {
		return fileNameAtServer;
	}

	public void setFileNameAtServer(String fileNameAtServer) {
		this.fileNameAtServer = fileNameAtServer;
	}

	public String getExtendFileType() {
		return extendFileType;
	}

	public void setExtendFileType(String extendFileType) {
		this.extendFileType = extendFileType;
	}
}
