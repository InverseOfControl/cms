package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSLoanFeeOffer extends BaseEntity{
	private Long id;
	//债券编号
	private Long loanId;
	//收费主表编号
	private Long feeId;
	//合同编号
	private String contractNum;
	//姓名
	private String name;
	//身份证号号
	private String idNum;
	//手机号
	private String mobile;
	//报盘应收金额
	private Double amoout;
	//报盘已收金额
	private Double receiveAmount;
	//报盘未收金额
	private Double unpaidAmount;
	//银行账号
	private String bankAcct;
	//银行名称
	private String bankName;
	//银行代码
	private String bankCode;
	//货币类型
	private String currencyType;
	//划扣类型
	private String type;
	//报盘日期
	private Date offerDate;
	//信息类别编码
	private String infoCategoryCode;
	//业务系统编码
	private String bizSysNo;
	//第三方通道编码
	private String paySysNo;
	//网点号
	private String organ;
	//报盘状态
	private String state;
	//预留备注信息
	private String memo;
	
	private String creator;
	private Date createTime;
	private String updator;
	private Date updateTime;
	//最终划扣通道编码
	private String paySysNoReal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Long getFeeId() {
		return feeId;
	}
	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getAmoout() {
		return amoout;
	}
	public void setAmoout(Double amoout) {
		this.amoout = amoout;
	}
	public Double getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	public Double getUnpaidAmount() {
		return unpaidAmount;
	}
	public void setUnpaidAmount(Double unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}
	public String getBankAcct() {
		return bankAcct;
	}
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public String getInfoCategoryCode() {
		return infoCategoryCode;
	}
	public void setInfoCategoryCode(String infoCategoryCode) {
		this.infoCategoryCode = infoCategoryCode;
	}
	public String getBizSysNo() {
		return bizSysNo;
	}
	public void setBizSysNo(String bizSysNo) {
		this.bizSysNo = bizSysNo;
	}
	public String getPaySysNo() {
		return paySysNo;
	}
	public void setPaySysNo(String paySysNo) {
		this.paySysNo = paySysNo;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPaySysNoReal() {
		return paySysNoReal;
	}
	public void setPaySysNoReal(String paySysNoReal) {
		this.paySysNoReal = paySysNoReal;
	}
	
	
	
}
