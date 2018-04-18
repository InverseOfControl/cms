package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 
 * @author YM10161
 *
 */
public class ReqBMSLoanNoLogVO extends Request{

	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = 3263714802912667790L;
	
	private String loanNo;//借款单号
	private String operatorStartTime;//操作开始时间
	private String operatorEndTime;//操作开始时间
	private int pageNum;     // 当前页数
	private int pageSize;
	
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getOperatorStartTime() {
		return operatorStartTime;
	}
	public void setOperatorStartTime(String operatorStartTime) {
		this.operatorStartTime = operatorStartTime;
	}
	public String getOperatorEndTime() {
		return operatorEndTime;
	}
	public void setOperatorEndTime(String operatorEndTime) {
		this.operatorEndTime = operatorEndTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
