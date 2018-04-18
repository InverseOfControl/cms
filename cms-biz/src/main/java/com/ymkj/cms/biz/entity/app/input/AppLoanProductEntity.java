package com.ymkj.cms.biz.entity.app.input;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppLoanProductEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id; //主键
	private Long loanBaseId; //借款主表ID  
	private String loanNo; //借款编号
	private String productCd; //申请产品
	private String productName;//产品名称
	private BigDecimal applyLmt; //申请额度
	private Integer applyTerm; //申请期限 
	private Integer ifPri; //是否加急
	private Integer ifOldOrNewLogo; //是否新老客户标识
	private Integer repayDate; //还款日
	private Integer ifGrey; //是否在灰名单中
	private Integer bankPhone; //银行预留手机号
	private String applyBankCardNo; //银行卡号	 
	private String applyBankBranch; //开户行
	private String applyBankName; //所属银行
	private Integer ifEnd; //是否处理完成件
	private Integer ifSuspectCheat; //是否疑似欺诈
	private Integer ifLoanAgain; //是否结清再贷
	private Integer ifRefuse; //是否拒绝
	private Double applyRate; //费率
	private Integer ifPatchBolt; //是否补件
	private Integer ifUrgent; //加急等级
	private Long creatorId; //创建人ID
	private String creator; //创建人
	private Date createdTime; //创建时间
	private Integer isDelete; //是否删除 
	private Integer verson ; //版本
	
	public AppLoanProductEntity(Long loanBaseId,String loanNo,Long creatorId, String creator){
		this.loanBaseId = loanBaseId;
		this.loanNo = loanNo;
		this.creatorId = creatorId;
		this.creator = creator;
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Integer getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}
	public void setIfOldOrNewLogo(Integer ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}
	public Integer getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
	}
	public Integer getIfGrey() {
		return ifGrey;
	}
	public void setIfGrey(Integer ifGrey) {
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
	public Integer getIfEnd() {
		return ifEnd;
	}
	public void setIfEnd(Integer ifEnd) {
		this.ifEnd = ifEnd;
	}
	public Integer getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(Integer ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public Integer getIfLoanAgain() {
		return ifLoanAgain;
	}
	public void setIfLoanAgain(Integer ifLoanAgain) {
		this.ifLoanAgain = ifLoanAgain;
	}
	public Integer getIfRefuse() {
		return ifRefuse;
	}
	public void setIfRefuse(Integer ifRefuse) {
		this.ifRefuse = ifRefuse;
	}
	public Double getApplyRate() {
		return applyRate;
	}
	public void setApplyRate(Double applyRate) {
		this.applyRate = applyRate;
	}
	public Integer getIfPatchBolt() {
		return ifPatchBolt;
	}
	public void setIfPatchBolt(Integer ifPatchBolt) {
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
