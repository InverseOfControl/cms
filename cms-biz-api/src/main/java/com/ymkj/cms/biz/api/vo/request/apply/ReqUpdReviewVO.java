package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;




import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqUpdReviewVO extends Request { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "000001,借款编号不能为空")
    private String loanNo;  //借款编号
	
	private String version; //版本号
	
	@NotNull(message = "000001,Flag不能为空")
	private Integer flag;  //状态标识 1.复议同意 2.复议退回 3.复议拒绝
	
	
	private String newRejectFirstReason;  //改后的拒绝原因
	
	
	private String newRejectFirstReasonCode;  //一级原因CODE
	
	@NotNull(message = "000001,复议结果不能为空")
	private Integer reviewResult;  //复议结果 0:通过 1:拒绝 3:退回
	
	@NotNull(message = "000001,修改人ID不能为空")
	private String modifierId;
	
    private String modifier;  //修改人
	
	private Date modifierDate;
	
	private String remark;  //备注
	
	private String newRejectTwoReasonCode;
	
	private String newRejectTwoReason;
	
	private int status; //状态 0:未办理 1:办理过但未提交复议 2:已提交复议申请 3:退回的复议申请 4:取消
	
	

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReviewResult() {
		return reviewResult;
	}

	public void setReviewResult(Integer reviewResult) {
		this.reviewResult = reviewResult;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getNewRejectFirstReason() {
		return newRejectFirstReason;
	}

	public void setNewRejectFirstReason(String newRejectFirstReason) {
		this.newRejectFirstReason = newRejectFirstReason;
	}

	public String getNewRejectFirstReasonCode() {
		return newRejectFirstReasonCode;
	}

	public void setNewRejectFirstReasonCode(String newRejectFirstReasonCode) {
		this.newRejectFirstReasonCode = newRejectFirstReasonCode;
	}

	public String getNewRejectTwoReasonCode() {
		return newRejectTwoReasonCode;
	}

	public void setNewRejectTwoReasonCode(String newRejectTwoReasonCode) {
		this.newRejectTwoReasonCode = newRejectTwoReasonCode;
	}

	public String getNewRejectTwoReason() {
		return newRejectTwoReason;
	}

	public void setNewRejectTwoReason(String newRejectTwoReason) {
		this.newRejectTwoReason = newRejectTwoReason;
	}
	
	

}
