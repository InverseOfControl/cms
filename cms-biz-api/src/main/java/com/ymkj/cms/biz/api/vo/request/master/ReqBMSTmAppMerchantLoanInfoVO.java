package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSTmAppMerchantLoanInfoVO extends Request{

	private static final long serialVersionUID = 3876153303456650193L;
	
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long merchantLoanId;//淘宝商户贷信息流水号	
	
	
	private Date setupShopDate;//开店时间

	private String sellerCreditLevel;//卖家信用等级

	private String sellerCreditType;//卖家信用类型
	
	private Integer regardedNum;//近半年好评数
	
	private BigDecimal billAmt1;//近1个月账单金额
	
	private BigDecimal billAmt2;//近2个月账单金额
	
	private BigDecimal billAmt3;//近3个月账单金额
	
	private BigDecimal billAmt4;//近4个月账单金额
	
	private BigDecimal billAmt5;//近5个月账单金额
	
	private BigDecimal billAmt6;//近6个月账单金额	
	
	private BigDecimal payMonthAmt;//月均账单金额
	 
		 
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer version ;

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

	public Long getMerchantLoanId() {
		return merchantLoanId;
	}

	public void setMerchantLoanId(Long merchantLoanId) {
		this.merchantLoanId = merchantLoanId;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	private int pageNum; // 当前页数
	private int pageSize;
	private int rows;// 行数
	private int page;// 页数

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	

}
