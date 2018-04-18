package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPMerchantLoanInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long merchantLoanId;//淘宝商户贷信息流水号	
	
	@NotNull(message = "000001,开店时间不能为空")
	private Date setupShopDate;//开店时间
	@NotNull(message = "000001,卖家信用等级不能为空")
	private String sellerCreditLevel;//卖家信用等级
	@NotNull(message = "000001,卖家信用类型不能为空")
	private String sellerCreditType;//卖家信用类型
	@NotNull(message = "000001,近半年好评数不能为空")
	private Integer regardedNum;//近半年好评数
	@NotNull(message = "000001,近1个月账单金额不能为空")
	private BigDecimal billAmt1;//近1个月账单金额
	@NotNull(message = "000001,近2个月账单金额不能为空")
	private BigDecimal billAmt2;//近2个月账单金额
	@NotNull(message = "000001,近3个月账单金额不能为空")
	private BigDecimal billAmt3;//近3个月账单金额
	@NotNull(message = "000001,近4个月账单金额不能为空")
	private BigDecimal billAmt4;//近4个月账单金额
	@NotNull(message = "000001,近5个月账单金额不能为空")
	private BigDecimal billAmt5;//近5个月账单金额
	@NotNull(message = "000001,近6个月账单金额不能为空")
	private BigDecimal billAmt6;//近6个月账单金额	
	
	private BigDecimal payMonthAmt;//月均账单金额
	 
		 
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号

	
	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 

	public Long getMerchantLoanId() {
		return merchantLoanId;
	}

	public void setMerchantLoanId(Long merchantLoanId) {
		this.merchantLoanId = merchantLoanId;
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

	 

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	} 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getSetupShopDate() {
		return setupShopDate;
	}

	public void setSetupShopDate(Date setupShopDate) {
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

	public Integer getRegardedNum() {
		return regardedNum;
	}

	public void setRegardedNum(Integer regardedNum) {
		this.regardedNum = regardedNum;
	}

	 
	public BigDecimal getBillAmt1() {
		return billAmt1;
	}

	public void setBillAmt1(BigDecimal billAmt1) {
		this.billAmt1 = billAmt1;
	}

	public BigDecimal getBillAmt2() {
		return billAmt2;
	}

	public void setBillAmt2(BigDecimal billAmt2) {
		this.billAmt2 = billAmt2;
	}

	public BigDecimal getBillAmt3() {
		return billAmt3;
	}

	public void setBillAmt3(BigDecimal billAmt3) {
		this.billAmt3 = billAmt3;
	}

	public BigDecimal getBillAmt4() {
		return billAmt4;
	}

	public void setBillAmt4(BigDecimal billAmt4) {
		this.billAmt4 = billAmt4;
	}

	public BigDecimal getBillAmt5() {
		return billAmt5;
	}

	public void setBillAmt5(BigDecimal billAmt5) {
		this.billAmt5 = billAmt5;
	}

	public BigDecimal getBillAmt6() {
		return billAmt6;
	}

	public void setBillAmt6(BigDecimal billAmt6) {
		this.billAmt6 = billAmt6;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVerson() {
		return verson;
	}

	public void setVerson(Integer verson) {
		this.verson = verson;
	}

 

}
