package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqApplicationPartVO extends Request {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4823295431905329022L;
	private String flag;//请求标识 1:通过件；2:拒绝件
	private String customerName;//客户名称
	private String cdNo;//身份证ID
	private String loanNo;//借款编号
	private List<String> rtfNodeStatusList;//流程节点状态
	private int  pageNum;//当前页数
	private int  pageSize;//分页条数
	
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCdNo() {
		return cdNo;
	}
	public void setCdNo(String cdNo) {
		this.cdNo = cdNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public List<String> getRtfNodeStatusList() {
		return rtfNodeStatusList;
	}
	public void setRtfNodeStatusList(List<String> rtfNodeStatusList) {
		this.rtfNodeStatusList = rtfNodeStatusList;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
