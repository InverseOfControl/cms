package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;

public class ResBMSLoanProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3766404906583120059L;
	
	private String loanNo;
	private String appNo;
    public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	private String repayDate;
    private String ifGrey;
    private String ifOldOrNewLogo;
    private String bankPhone;
    private String applyBankCardNo;
    private String applyBankBranch;
    private String applyBankBranchId;
    private String applyBankName;
    private String applyBankNameId;
    private String contractNum;
    private String contractSource;
    private String initProductCd;
    private String productCd;
    private String applyLmt;
    private String applyTerm;
    private String contractLmt;
    private String contractTrem;
    private String ifEnd;
    private String ifSuspectCheat;
    private String ifLoanAgain;
    private String fRefuse;
    private String applyRate;
    private String ifPatchBolt;
    private String ifPri;
    private String ifUrgent;
    private int id;
    private String loanBaseId;
    // <!-- 原产品金额 -->
    private String initApplyLmt;
   // <!-- 原产品期限 -->
    private String initApplyTerm;
   // <!-- 借款银行 -->
    private String loanBankIdBorrow;
    //<!-- 还款用户 -->
    private String loanBankIdStill;
   // <!-- 合同类型 -->
    private String contractType;
    private String creator;
    private String createdTime;
    private String creatorId;
    private String modifier;
    private String modifiedTime;
    private String modifierId;
    private String version;
    private String isDelete;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getIfGrey() {
		return ifGrey;
	}
	public void setIfGrey(String ifGrey) {
		this.ifGrey = ifGrey;
	}
	public String getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}
	public void setIfOldOrNewLogo(String ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}
	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}
	public String getApplyBankBranch() {
		return applyBankBranch;
	}
	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}
	public String getApplyBankBranchId() {
		return applyBankBranchId;
	}
	public void setApplyBankBranchId(String applyBankBranchId) {
		this.applyBankBranchId = applyBankBranchId;
	}
	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}
	public String getApplyBankNameId() {
		return applyBankNameId;
	}
	public void setApplyBankNameId(String applyBankNameId) {
		this.applyBankNameId = applyBankNameId;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getContractSource() {
		return contractSource;
	}
	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}
	public String getInitProductCd() {
		return initProductCd;
	}
	public void setInitProductCd(String initProductCd) {
		this.initProductCd = initProductCd;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(String contractLmt) {
		this.contractLmt = contractLmt;
	}
	public String getContractTrem() {
		return contractTrem;
	}
	public void setContractTrem(String contractTrem) {
		this.contractTrem = contractTrem;
	}
	public String getIfEnd() {
		return ifEnd;
	}
	public void setIfEnd(String ifEnd) {
		this.ifEnd = ifEnd;
	}
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getIfLoanAgain() {
		return ifLoanAgain;
	}
	public void setIfLoanAgain(String ifLoanAgain) {
		this.ifLoanAgain = ifLoanAgain;
	}
	public String getfRefuse() {
		return fRefuse;
	}
	public void setfRefuse(String fRefuse) {
		this.fRefuse = fRefuse;
	}
	public String getApplyRate() {
		return applyRate;
	}
	public void setApplyRate(String applyRate) {
		this.applyRate = applyRate;
	}
	public String getIfPatchBolt() {
		return ifPatchBolt;
	}
	public void setIfPatchBolt(String ifPatchBolt) {
		this.ifPatchBolt = ifPatchBolt;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getIfUrgent() {
		return ifUrgent;
	}
	public void setIfUrgent(String ifUrgent) {
		this.ifUrgent = ifUrgent;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getInitApplyLmt() {
		return initApplyLmt;
	}
	public void setInitApplyLmt(String initApplyLmt) {
		this.initApplyLmt = initApplyLmt;
	}
	public String getInitApplyTerm() {
		return initApplyTerm;
	}
	public void setInitApplyTerm(String initApplyTerm) {
		this.initApplyTerm = initApplyTerm;
	}
	public String getLoanBankIdBorrow() {
		return loanBankIdBorrow;
	}
	public void setLoanBankIdBorrow(String loanBankIdBorrow) {
		this.loanBankIdBorrow = loanBankIdBorrow;
	}
	public String getLoanBankIdStill() {
		return loanBankIdStill;
	}
	public void setLoanBankIdStill(String loanBankIdStill) {
		this.loanBankIdStill = loanBankIdStill;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	

}
