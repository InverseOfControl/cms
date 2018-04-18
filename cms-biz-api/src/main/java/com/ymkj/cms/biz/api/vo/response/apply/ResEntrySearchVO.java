package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResEntrySearchVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	private Long 	id;	//借款ID
	private String  loanNo;//借款编号
	private String  name;//客户姓名
	private String  idNo;//证件号
	private String  status;//状态
	
	private String  productName;//申请产品
	private String  applyLmt;//申请金额
	private String  applyType;//申请类型
	 
	private String  appInputFlag;//进件类型标识 app进件
	 
	private Date  startTime;
	 
	private Date  endTime;
	private String ifPri; //是否加急
	private Long logoFlag;//颜色标识0，无色，1黄色色（#F2FC96）
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private String initProductName;
	private String initProductCd;
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
	
	public String getIfPri() {
		return ifPri;
	}

	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyLmt() {
		return applyLmt;
	}

	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getAppInputFlag() {
		return appInputFlag;
	}

	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getLogoFlag() {
		return logoFlag;
	}

	public void setLogoFlag(Long logoFlag) {
		this.logoFlag = logoFlag;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	public String getInitProductCd() {
		return initProductCd;
	}
	public void setInitProductCd(String initProductCd) {
		this.initProductCd = initProductCd;
	}
	
	


}
