package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqApplicationPartUpVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long flag;//请求标识 1.通过件拒绝  2,通过件修改 3 拒绝件修改
	private String loanNo;//借款单号
	
	private Long accLmt;//审批金额
	private Long accTerm;//审批期限
	
	private String firstLevelReasons;//一级原因
	private String firstLevelReasonsCode;//一级原因code
	private String twoLevelReasons;//二级原因
	private String twoLevelReasonsCode;//二级原因code
	
	private String operatorCode;//操作人code
	private String operatorName;//操作人Nmae
	private String operatorIP;//操作人code
	private Long version;//版本号
	private String remark;//备注
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Long getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Long accLmt) {
		this.accLmt = accLmt;
	}
	public Long getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(Long accTerm) {
		this.accTerm = accTerm;
	}
	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}
	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}
	public String getFirstLevelReasonsCode() {
		return firstLevelReasonsCode;
	}
	public void setFirstLevelReasonsCode(String firstLevelReasonsCode) {
		this.firstLevelReasonsCode = firstLevelReasonsCode;
	}
	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}
	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
	}
	public String getTwoLevelReasonsCode() {
		return twoLevelReasonsCode;
	}
	public void setTwoLevelReasonsCode(String twoLevelReasonsCode) {
		this.twoLevelReasonsCode = twoLevelReasonsCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
