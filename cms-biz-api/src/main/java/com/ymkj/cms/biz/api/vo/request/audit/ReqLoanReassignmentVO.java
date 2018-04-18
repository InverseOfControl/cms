package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqLoanReassignmentVO extends Request{
	/**	
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	
	private String loanNo;//借款编号
	
	private String operator;//操作人
	private Long operatorId;//操作人id
	private String operatorCode;//操作人code
	
	private String reassignmentOperator;//被改派人
	private String reassignmentOperatorCode;//被改派人code
	
	private String ip;
	
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	 
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getReassignmentOperator() {
		return reassignmentOperator;
	}
	public void setReassignmentOperator(String reassignmentOperator) {
		this.reassignmentOperator = reassignmentOperator;
	}
	public String getReassignmentOperatorCode() {
		return reassignmentOperatorCode;
	}
	public void setReassignmentOperatorCode(String reassignmentOperatorCode) {
		this.reassignmentOperatorCode = reassignmentOperatorCode;
	}

	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public ReqLoanReassignmentVO(){	
	}
	public ReqLoanReassignmentVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
