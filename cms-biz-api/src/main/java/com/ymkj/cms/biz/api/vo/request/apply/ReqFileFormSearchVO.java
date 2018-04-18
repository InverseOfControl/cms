package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqFileFormSearchVO extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4189086643971414265L;
	
	private Integer Id;  //ID
	private String name;  //姓名
	private String idNo; //身份证号码
	private String mobilePhone; //手机号码
	private String branchManagerCode; //客户经理
	private Date lendingTimeStart; //放款开始时间
	private Date lendingTimeEnd;  //放款结束时间
	private List<String>owningBranchId;  //营业部ID
	private String serviceCode;  //操作客服工号
	private String serviceName;  //操作客服姓名
	private String ip;  //操作IP
	private int pageNum;     // 当前页数
	private int pageSize;  
	// 每页记录数
	private int rows;// 行数
	private int page;// 页数
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
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getBranchManagerCode() {
		return branchManagerCode;
	}
	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}
	public Date getLendingTimeStart() {
		return lendingTimeStart;
	}
	public void setLendingTimeStart(Date lendingTimeStart) {
		this.lendingTimeStart = lendingTimeStart;
	}
	public Date getLendingTimeEnd() {
		return lendingTimeEnd;
	}
	public void setLendingTimeEnd(Date lendingTimeEnd) {
		this.lendingTimeEnd = lendingTimeEnd;
	}
	public List<String> getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(List<String> owningBranchId) {
		this.owningBranchId = owningBranchId;
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
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
			return page;
	}
	public void setPage(int page) {
			this.page = page;
		this.setPageNum(page);
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
	public void setPageSize(int numPerPage) {
		this.pageSize = numPerPage;
	}
	

}
