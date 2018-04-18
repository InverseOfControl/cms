package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:AppMerchantLoanInfo</p>
 * <p>Description:淘宝商户信息</p>
 * @uthor YM10159
 * @date 2017年5月3日 上午9:52:17
 */
public class AppMerchantLoanInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private Long personId; //客户主表主键
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String org; //机构号
	private String merchantLoanId; //淘宝商户贷信息流水号
	
	private String setupShopDate; //开店时间
	private String sellerCreditLevel; //卖家信用等级
	private String sellerCreditType; //卖家信用类型
	private String regardedNum; //好评数
	private String biullAmt1; //第一个月账单金额
	private String biullAmt2; //第二个月账单金额
	private String biullAmt3; //第三个月账单金额
	private String biullAmt4; //第四个月账单金额
	private String biullAmt5; //第五个月账单金额
	private String biullAmt6; //第六个月账单金额
	
	private String creator;
	private String creatorId;
	
	public AppMerchantLoanInfo(Long loanBaseId, Long personId, String loanNo, String org, String creator, String creatorId){
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
	public String getMerchantLoanId() {
		return merchantLoanId;
	}
	public void setMerchantLoanId(String merchantLoanId) {
		this.merchantLoanId = merchantLoanId;
	}
	public String getSetupShopDate() {
		return setupShopDate;
	}
	public void setSetupShopDate(String setupShopDate) {
		this.setupShopDate = setupShopDate;
	}
	public String getSellerCreditLevel() {
		return sellerCreditLevel;
	}
	public void setSellerCreditLevel(String sellerCreditLevel) {
		this.sellerCreditLevel = sellerCreditLevel;
	}
	public String getSellerCreditType() {
		return sellerCreditType;
	}
	public void setSellerCreditType(String sellerCreditType) {
		this.sellerCreditType = sellerCreditType;
	}
	public String getRegardedNum() {
		return regardedNum;
	}
	public void setRegardedNum(String regardedNum) {
		this.regardedNum = regardedNum;
	}
	public String getBiullAmt1() {
		return biullAmt1;
	}
	public void setBiullAmt1(String biullAmt1) {
		this.biullAmt1 = biullAmt1;
	}
	public String getBiullAmt2() {
		return biullAmt2;
	}
	public void setBiullAmt2(String biullAmt2) {
		this.biullAmt2 = biullAmt2;
	}
	public String getBiullAmt3() {
		return biullAmt3;
	}
	public void setBiullAmt3(String biullAmt3) {
		this.biullAmt3 = biullAmt3;
	}
	public String getBiullAmt4() {
		return biullAmt4;
	}
	public void setBiullAmt4(String biullAmt4) {
		this.biullAmt4 = biullAmt4;
	}
	public String getBiullAmt5() {
		return biullAmt5;
	}
	public void setBiullAmt5(String biullAmt5) {
		this.biullAmt5 = biullAmt5;
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

	public String getBiullAmt6() {
		return biullAmt6;
	}

	public void setBiullAmt6(String biullAmt6) {
		this.biullAmt6 = biullAmt6;
	}
}
