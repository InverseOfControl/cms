package com.ymkj.cms.biz.entity.apply.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class ApplyInfoVOEntity {
	
	private Long loanBaseId	; //申请信息Id
	private Long loanProductId	; //申请产品Id
	private Long loanExtId	; //借款扩展表Id
	private String loanNo;	 //借款编号
	

	private String productCd	; //申请产品
	private String productName; //产品名称'
	@NotNull(message = "000001,申请产品名称不能为空")
	private String initProductName;
	@NotNull(message = "000001,申请产品不能为空")
	private String initProductCd;
	@NotNull(message = "000001,申请金额不能为空")
	private BigDecimal applyLmt	; //申请金额
	@NotNull(message = "000001,申请期限不能为空")
	private Integer applyTerm	; //申请期限
	@NotNull(message = "000001,姓名不能为空")
	private String name;	 //姓名
	@NotNull(message = "000001,身份证不能为空")
	private String idNo	; //身份证
	@NotNull(message = "000001,是否加急不能为空")
	private Integer ifPri; 	//是否加急	0不加急 	1加急
	private String customerSource; //客户来源
	@NotNull(message = "000001,借款用途不能为空")
	private String creditApplication;	 //	贷款用途
	@NotNull(message = "000001,客户经理Code不能为空")
	private String branchManagerCode;	 //客户经理Code
	@NotNull(message = "000001,客户经理Name不能为空")
	private String branchManagerName;	 //客户经理Name
	private String remark;	 //备注
	@NotNull(message = "000001,申请件渠道标识不能为空")
	private String applyInputFlag;	//申请件渠道标识 	applyInput 普通进件 directApplyInput 直通车进件
	
	private String appInputFlag;	//APP进件标识	app_input 代表app进件
	private	String	contractBranch;	//签约营业部
	private Long contractBranchId;	//签约营业部ID	
	private String applyType;//申请类型	 NEW 新申请    TOPUP 追加贷款	RELOAN 结清再贷
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public Integer getIfPri() {
		return ifPri;
	}
	public void setIfPri(Integer ifPri) {
		this.ifPri = ifPri;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getApplyInputFlag() {
		return applyInputFlag;
	}
	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public Long getLoanProductId() {
		return loanProductId;
	}
	public void setLoanProductId(Long loanProductId) {
		this.loanProductId = loanProductId;
	}
	public Long getLoanExtId() {
		return loanExtId;
	}
	public void setLoanExtId(Long loanExtId) {
		this.loanExtId = loanExtId;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
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
	public String getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	 
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getBranchManagerCode() {
		return branchManagerCode;
	}
	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

			
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public Long getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(Long contractBranchId) {
		this.contractBranchId = contractBranchId;
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
