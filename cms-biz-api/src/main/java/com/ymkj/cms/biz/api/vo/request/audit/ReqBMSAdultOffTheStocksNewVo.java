package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSAdultOffTheStocksNewVo extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date offStartDate;//完成开始时间
	private Date offEndDate;//完成结束时间
	private String auditPersonCode;//审核人编码
	
	
	private String operatorCode;//操作人编码
	private String operatorIP;//操作人IP
	private int pageNum;//当前页数
	private int pageSize;//分页条数
	
	public Date getOffStartDate() {
		return offStartDate;
	}
	public void setOffStartDate(Date offStartDate) {
		this.offStartDate = offStartDate;
	}
	public Date getOffEndDate() {
		return offEndDate;
	}
	public void setOffEndDate(Date offEndDate) {
		this.offEndDate = offEndDate;
	}
	public String getAuditPersonCode() {
		return auditPersonCode;
	}
	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
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
