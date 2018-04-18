package com.ymkj.cms.biz.api.vo.request.audit;
import com.ymkj.base.core.biz.api.message.Request;

public class ReqApplicationPartUpdVO extends Request{

	/**
	 * 序列化对象
	 */
	private static final long serialVersionUID = -558299710879885216L;
	
	private String flag;//请求标识 1.通过件_修改or拒绝按钮 2.拒绝件_修改按钮
	private String loanNo;//借款单号
	private String status;//借款单状态
	private String rtfState;//借款环节状态
	private String rtfNodeState;//借款环节节点状态
	private String accLmt;//审批金额
	private String accTerm;//审批期限
	private String firstLevelReasons;//一级原因
	private String firstLevelReasonsCode;//一级原因code
	private String twoLevelReasons;//二级原因
	private String twoLevelReasonsCode;//二级原因code
	private String operatorCode;//操作人code
	private String operatorIP;//操作人code
	private int version;//版本号
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(String accTerm) {
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
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
