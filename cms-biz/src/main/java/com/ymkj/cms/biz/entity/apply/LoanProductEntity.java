package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class LoanProductEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; // id
	private Long loanBaseId;  
	private String appNo;  //申请件编号
	private String loanNo;//借款编号

	private String productCd; //申请产品

	private String productName;//产品名称
	@NotNull(message = "000001,申请额度不能为空")
	private BigDecimal applyLmt; //申请额度
	@NotNull(message = "000001,申请期限 不能为空")
	private Integer applyTerm; //申请期限 
	private Integer ifPri; //是否加急
	
	private String ifOldOrNewLogo;  //是否新老客户标识
	private Integer repayDate;  //还款日
	private String ifGrey;  //是否在灰名单中
	private Integer bankPhone;  //银行预留手机号
	private String applyBankCardNo;  //银行卡号	 
	private String applyBankBranch;  //开户行
	private String applyBankName;  //所属银行
	
	private String contractNum;//合同编号
	private String contractSource; //合同来源
	@NotNull(message = "000001,申请产品不能为空")
	private String initProductCd; //原产品
	@NotNull(message = "000001,产品名称不能为空")
	private String initProductName;//原产品名称
	
	private BigDecimal initApplyLmt;//原产品申请额度
	private Integer initApplyTerm;//原产品申请期限

	private BigDecimal contractLmt; //合同金额
	private Integer contractTrem; //合同期限
	private String ifEnd; //是否处理完成件
	private Integer ifSuspectCheat; //是否疑似欺诈
	private String ifLoanAgain; //是否结清再贷
	private String ifRefuse; //是否拒绝
	private Double applyRate; //费率
	private String ifPatchBolt; //是否补件
	private Integer ifUrgent; //加急等级
		 
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;

	
	
	public BigDecimal getInitApplyLmt() {
		return initApplyLmt;
	}

	public void setInitApplyLmt(BigDecimal initApplyLmt) {
		this.initApplyLmt = initApplyLmt;
	}

	public Integer getInitApplyTerm() {
		return initApplyTerm;
	}

	public void setInitApplyTerm(Integer initApplyTerm) {
		this.initApplyTerm = initApplyTerm;
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

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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

	public Integer getIfPri() {
		return ifPri;
	}

	public void setIfPri(Integer ifPri) {
		this.ifPri = ifPri;
	}

	public String getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}

	public void setIfOldOrNewLogo(String ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}

	public Integer getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
	}

	public String getIfGrey() {
		return ifGrey;
	}

	public void setIfGrey(String ifGrey) {
		this.ifGrey = ifGrey;
	}

	public Integer getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(Integer bankPhone) {
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

	public String getApplyBankName() {
		return applyBankName;
	}

	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
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

	public BigDecimal getContractLmt() {
		return contractLmt;
	}

	public void setContractLmt(BigDecimal contractLmt) {
		this.contractLmt = contractLmt;
	}

	public Integer getContractTrem() {
		return contractTrem;
	}

	public void setContractTrem(Integer contractTrem) {
		this.contractTrem = contractTrem;
	}

	public String getIfEnd() {
		return ifEnd;
	}

	public void setIfEnd(String ifEnd) {
		this.ifEnd = ifEnd;
	}

	public Integer getIfSuspectCheat() {
		return ifSuspectCheat;
	}

	public void setIfSuspectCheat(Integer ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}

	public String getIfLoanAgain() {
		return ifLoanAgain;
	}

	public void setIfLoanAgain(String ifLoanAgain) {
		this.ifLoanAgain = ifLoanAgain;
	}

	public String getIfRefuse() {
		return ifRefuse;
	}

	public void setIfRefuse(String ifRefuse) {
		this.ifRefuse = ifRefuse;
	}

	public Double getApplyRate() {
		return applyRate;
	}

	public void setApplyRate(Double applyRate) {
		this.applyRate = applyRate;
	}

	public String getIfPatchBolt() {
		return ifPatchBolt;
	}

	public void setIfPatchBolt(String ifPatchBolt) {
		this.ifPatchBolt = ifPatchBolt;
	}

	public Integer getIfUrgent() {
		return ifUrgent;
	}

	public void setIfUrgent(Integer ifUrgent) {
		this.ifUrgent = ifUrgent;
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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getInitProductName() {
		return initProductName;
	}

	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}

}
