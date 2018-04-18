package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSAdultOffTheStocksVo extends Request{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8676341686165797770L;

	private String reqFlag;//请求标识
	private String offStartDate;//完成开始时间
	private String offEndDate;//完成结束时间
	private String auditPersonCode;//审核人编码
	private String operatorCode;//操作人编码
	private String operatorIP;//操作人IP
	private int pageNum;//当前页数
	private int pageSize;//分页条数
	
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	public String getReqFlag() {
		return reqFlag;
	}
	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}
	public String getOffStartDate() {
		return offStartDate;
	}
	public void setOffStartDate(String offStartDate) {
		this.offStartDate = offStartDate;
	}
	public String getOffEndDate() {
		return offEndDate;
	}
	public void setOffEndDate(String offEndDate) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFieldSort() {
		return fieldSort;
	}
	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	public int getRulesSort() {
		return rulesSort;
	}
	public void setRulesSort(int rulesSort) {
		this.rulesSort = rulesSort;
	}
	
}
