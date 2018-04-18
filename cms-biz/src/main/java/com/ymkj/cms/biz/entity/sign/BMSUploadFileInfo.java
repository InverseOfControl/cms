package com.ymkj.cms.biz.entity.sign;


import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;


public class BMSUploadFileInfo extends BMSProductBaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7104705846715723919L;
	
	private Long id;     //id
	private Long fileId;     //文件id
	private String fileName;     //文件名称，含有格式，aaa.jpg
	private String url;     //文件地址
	private String owerCode;     //文件所有者
    private String loanNo;//申请主表id
    private String sysCode;     //系统编号
    private String nodeKey;     //业务环节code
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwerCode() {
		return owerCode;
	}
	public void setOwerCode(String owerCode) {
		this.owerCode = owerCode;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getNodeKey() {
		return nodeKey;
	}
	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
    

	
}
