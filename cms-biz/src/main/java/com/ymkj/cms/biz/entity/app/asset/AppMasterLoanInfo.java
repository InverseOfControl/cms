package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:AppMasterLoanInfo</p>
 * <p>Description:网购达人信息</p>
 * @uthor YM10159
 * @date 2017年5月3日 上午9:32:06
 */
public class AppMasterLoanInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private Long personId; //客户主表主键
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String org; //机构号
	private String masterLoadId; //淘宝达人贷信息流水号
	
	private String jiDongUserLevel; //京东用户等级
	private String whiteCreditValue; //小白信用分
	private String pastYearShoppingAmount; //近一年消费总量
	
	
	private String acctRegisterDate; //账户注册时间
	private String buyerCreditLevel; //买家信用等级
	private String buyerCreditType; //买家信用类型
	private String goodRate; //好评率
	private String lastYearPayAmt; //上一年度支出额
	private String payAmt1; //第一个月支出总额
	private String payAmt2; //第二个月支出总额
	private String payAmt3; //第三个月支出总额
	private String sesameCreditValue; //芝麻信用分
	
	private String creator;
	private String creatorId;
	
	
	public AppMasterLoanInfo(Long loanBaseId, Long personId, String loanNo, String org, String creator, String creatorId){
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
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
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
	public String getMasterLoadId() {
		return masterLoadId;
	}
	public void setMasterLoadId(String masterLoadId) {
		this.masterLoadId = masterLoadId;
	}
	public String getJiDongUserLevel() {
		return jiDongUserLevel;
	}
	public void setJiDongUserLevel(String jiDongUserLevel) {
		this.jiDongUserLevel = jiDongUserLevel;
	}
	public String getWhiteCreditValue() {
		return whiteCreditValue;
	}
	public void setWhiteCreditValue(String whiteCreditValue) {
		this.whiteCreditValue = whiteCreditValue;
	}
	public String getPastYearShoppingAmount() {
		return pastYearShoppingAmount;
	}
	public void setPastYearShoppingAmount(String pastYearShoppingAmount) {
		this.pastYearShoppingAmount = pastYearShoppingAmount;
	}
	public String getAcctRegisterDate() {
		return acctRegisterDate;
	}
	public void setAcctRegisterDate(String acctRegisterDate) {
		this.acctRegisterDate = acctRegisterDate;
	}
	public String getBuyerCreditLevel() {
		return buyerCreditLevel;
	}
	public void setBuyerCreditLevel(String buyerCreditLevel) {
		this.buyerCreditLevel = buyerCreditLevel;
	}
	public String getBuyerCreditType() {
		return buyerCreditType;
	}
	public void setBuyerCreditType(String buyerCreditType) {
		this.buyerCreditType = buyerCreditType;
	}
	public String getGoodRate() {
		return goodRate;
	}
	public void setGoodRate(String goodRate) {
		this.goodRate = goodRate;
	}
	public String getLastYearPayAmt() {
		return lastYearPayAmt;
	}
	public void setLastYearPayAmt(String lastYearPayAmt) {
		this.lastYearPayAmt = lastYearPayAmt;
	}
	public String getPayAmt1() {
		return payAmt1;
	}
	public void setPayAmt1(String payAmt1) {
		this.payAmt1 = payAmt1;
	}
	public String getPayAmt2() {
		return payAmt2;
	}
	public void setPayAmt2(String payAmt2) {
		this.payAmt2 = payAmt2;
	}
	public String getPayAmt3() {
		return payAmt3;
	}
	public void setPayAmt3(String payAmt3) {
		this.payAmt3 = payAmt3;
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

	public String getSesameCreditValue() {
		return sesameCreditValue;
	}

	public void setSesameCreditValue(String sesameCreditValue) {
		this.sesameCreditValue = sesameCreditValue;
	}

}
