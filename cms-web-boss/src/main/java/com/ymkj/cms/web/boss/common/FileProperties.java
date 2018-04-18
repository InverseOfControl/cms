package com.ymkj.cms.web.boss.common;

public class FileProperties {

	// 上传文件的目录
	private String uploadPath;
	// 下载文件的临时目录
	private String downloadPath;
	/**
	 * 合同模板上传路径
	 */
	private String uploadContractTemPath;
	// PIC附件文件路径
	private String  picfiledataUrl;

	public String getUploadContractTemPath() {
		return uploadContractTemPath;
	}

	public void setUploadContractTemPath(String uploadContractTemPath) {
		this.uploadContractTemPath = uploadContractTemPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getPicfiledataUrl() {
		return picfiledataUrl;
	}

	public void setPicfiledataUrl(String picfiledataUrl) {
		this.picfiledataUrl = picfiledataUrl;
	}
	
	
}
