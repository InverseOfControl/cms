package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqOwningBranchReassignmentSearchVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loanNo;//	借款编号		
	private String name;//	客户姓名			
	private String idNo;//	证件号码			
	private String operatorCode;//	处理人工号			
	private String status;//	申请状态
	private List<String> owningBranchIds;//进件门店id
	
	private String serviceCode;//	操作人工号	
	private String serviceName;//	操作人姓名
	
	private String ip;//	操作ip
	private int pageNum;//	当前页数
	private int pageSize;//	分页条数
	
	private BigDecimal minAccLmt; //审批额度最小额度
	private BigDecimal maxAccLmt; //审批额度最大额度
	private List<Integer> accTerms;//审批期限集合
	
	
	
	
	public BigDecimal getMinAccLmt() {
		return minAccLmt;
	}
	public void setMinAccLmt(BigDecimal minAccLmt) {
		this.minAccLmt = minAccLmt;
	}
	public BigDecimal getMaxAccLmt() {
		return maxAccLmt;
	}
	public void setMaxAccLmt(BigDecimal maxAccLmt) {
		this.maxAccLmt = maxAccLmt;
	}
	public List<Integer> getAccTerms() {
		return accTerms;
	}
	public void setAccTerms(List<Integer> accTerms) {
		this.accTerms = accTerms;
	}
	public List<String> getOwningBranchIds() {
		return owningBranchIds;
	}
	public void setOwningBranchIds(List<String> owningBranchIds) {
		this.owningBranchIds = owningBranchIds;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
