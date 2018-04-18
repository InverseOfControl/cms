package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:AppProvidentInfo</p>
 * <p>Description:公积金信息表</p>
 * @uthor YM10159
 * @date 2017年5月2日 下午7:24:44
 */
public class AppProvidentInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long personId; //客户主表主键
	private Long loanBaseId; //借款主表ID
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String org; //机构号
	private String providentId; //公积金信息流水号
	
	private String openAccountDate; //开户时间
	private String depositRate; //缴存比例
	private String monthDepositAmt; //月缴存额
	private String depositBase; //缴存基数
	private String providentInfo; //公积金材料Code
	private String providentInfo1; //公积金材料
	private String paymentUnit; //缴纳单位同申请单位
	private String paymentMonthNum; //申请单位已缴月数
	
	private String creator;
	private String creatorId;
	
	public AppProvidentInfo(Long loanBaseId, Long personId, String loanNo, String org, String creator, String creatorId){
		this.loanBaseId = loanBaseId;
		this.personId = personId;
		this.loanNo = loanNo;
		this.org = org;
		this.creator = creator;
		this.creatorId = creatorId;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getProvidentId() {
		return providentId;
	}
	public void setProvidentId(String providentId) {
		this.providentId = providentId;
	}
	public String getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(String openAccountDate) {
		this.openAccountDate = openAccountDate;
	}
	public String getDepositRate() {
		return depositRate;
	}
	public void setDepositRate(String depositRate) {
		this.depositRate = depositRate;
	}
	public String getMonthDepositAmt() {
		return monthDepositAmt;
	}
	public void setMonthDepositAmt(String monthDepositAmt) {
		this.monthDepositAmt = monthDepositAmt;
	}
	public String getDepositBase() {
		return depositBase;
	}
	public void setDepositBase(String depositBase) {
		this.depositBase = depositBase;
	}
	public String getProvidentInfo() {
		return providentInfo;
	}
	public void setProvidentInfo(String providentInfo) {
		this.providentInfo = providentInfo;
	}
	public String getProvidentInfo1() {
		return providentInfo1;
	}
	public void setProvidentInfo1(String providentInfo1) {
		this.providentInfo1 = providentInfo1;
	}
	public String getPaymentUnit() {
		return paymentUnit;
	}
	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}
	public String getPaymentMonthNum() {
		return paymentMonthNum;
	}
	public void setPaymentMonthNum(String paymentMonthNum) {
		this.paymentMonthNum = paymentMonthNum;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	

}
