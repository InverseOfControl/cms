package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSLoanBaseRelaVO extends Request{

	private static final long serialVersionUID = 2233876694806878537L;
	
	 private Integer id; //

		
	 private Integer personId; //

	
	 private Integer loanBaseId; //

	
	 private Integer bmsAppPersonInfoId; //主卡申请人信息表主键

	
	 private Integer bmsTmAppContactInfoId; //联系人表主键

	
	 private Integer bmsTmAppEstateInfoId; //房产信息表主键

	
	 private Integer bmsTmAppProvidentInfoId; //公积金信息表主键

	
	 private Integer bmsTmAppPolicyInfoId; //保单信息表主键

	
	 private Integer bmsTmAppMasterLoanInfoId; //淘宝达人贷信息表主键

	
	 private Integer bmsTmAppMerchantLoanInfoId; //淘宝商户贷信息表主键

	
	 private Integer bmsTmAppCarInfoId; //车辆信息表主键

	
	 private Integer bmsTmAppSalaryLoanInfoId; //随薪贷信息表主键

	
	 private Integer bmsTmAppCardLoanInfoId; //卡友信息表主键

	
	 private Long creatorId; //创建用户ID

	
	 private String creator; //创建用户

	
	 private Date createdTime; //创建时间

	
	 private Long modifierId; //修改用户ID

	
	 private String modifier; //修改用户

	
	 private Date modifiedTime; //修改用户时间

	
	 private Integer version; //默认是1

	
	 private Integer isDelete; //默认值是0 删除是1
	 
	 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Integer loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public Integer getBmsAppPersonInfoId() {
		return bmsAppPersonInfoId;
	}
	public void setBmsAppPersonInfoId(Integer bmsAppPersonInfoId) {
		this.bmsAppPersonInfoId = bmsAppPersonInfoId;
	}
	public Integer getBmsTmAppContactInfoId() {
		return bmsTmAppContactInfoId;
	}
	public void setBmsTmAppContactInfoId(Integer bmsTmAppContactInfoId) {
		this.bmsTmAppContactInfoId = bmsTmAppContactInfoId;
	}
	public Integer getBmsTmAppEstateInfoId() {
		return bmsTmAppEstateInfoId;
	}
	public void setBmsTmAppEstateInfoId(Integer bmsTmAppEstateInfoId) {
		this.bmsTmAppEstateInfoId = bmsTmAppEstateInfoId;
	}
	public Integer getBmsTmAppProvidentInfoId() {
		return bmsTmAppProvidentInfoId;
	}
	public void setBmsTmAppProvidentInfoId(Integer bmsTmAppProvidentInfoId) {
		this.bmsTmAppProvidentInfoId = bmsTmAppProvidentInfoId;
	}
	public Integer getBmsTmAppPolicyInfoId() {
		return bmsTmAppPolicyInfoId;
	}
	public void setBmsTmAppPolicyInfoId(Integer bmsTmAppPolicyInfoId) {
		this.bmsTmAppPolicyInfoId = bmsTmAppPolicyInfoId;
	}
	public Integer getBmsTmAppMasterLoanInfoId() {
		return bmsTmAppMasterLoanInfoId;
	}
	public void setBmsTmAppMasterLoanInfoId(Integer bmsTmAppMasterLoanInfoId) {
		this.bmsTmAppMasterLoanInfoId = bmsTmAppMasterLoanInfoId;
	}
	public Integer getBmsTmAppMerchantLoanInfoId() {
		return bmsTmAppMerchantLoanInfoId;
	}
	public void setBmsTmAppMerchantLoanInfoId(Integer bmsTmAppMerchantLoanInfoId) {
		this.bmsTmAppMerchantLoanInfoId = bmsTmAppMerchantLoanInfoId;
	}
	public Integer getBmsTmAppCarInfoId() {
		return bmsTmAppCarInfoId;
	}
	public void setBmsTmAppCarInfoId(Integer bmsTmAppCarInfoId) {
		this.bmsTmAppCarInfoId = bmsTmAppCarInfoId;
	}
	public Integer getBmsTmAppSalaryLoanInfoId() {
		return bmsTmAppSalaryLoanInfoId;
	}
	public void setBmsTmAppSalaryLoanInfoId(Integer bmsTmAppSalaryLoanInfoId) {
		this.bmsTmAppSalaryLoanInfoId = bmsTmAppSalaryLoanInfoId;
	}
	public Integer getBmsTmAppCardLoanInfoId() {
		return bmsTmAppCardLoanInfoId;
	}
	public void setBmsTmAppCardLoanInfoId(Integer bmsTmAppCardLoanInfoId) {
		this.bmsTmAppCardLoanInfoId = bmsTmAppCardLoanInfoId;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Long getModifierId() {
		return modifierId;
	}
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	private int pageNum; // 当前页数
	private int pageSize;
	private int rows;// 行数
	private int page;// 页数

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
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
