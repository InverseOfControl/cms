package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSCreditRatingLimitVO extends Request{
	private Long id;
	//等级
	private String creditRating;
	
	private Integer isDeleted;
	
	
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	
	
	private String addCreditRating;
	private String addCustomerType;
	private String addProductCode;
	private String addProductText;
	private String addRemark;
	private String addCreaditType;
	
	private Long creatorId;
	private String creator;
	
	
	private String updateCreditRating;
	private String updateCustomerType;
	private String updateProductCode;
	private String updateProductText;
	private String updateRemark;
	private String updateCreaditType;
	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getAddCreditRating() {
		return addCreditRating;
	}

	public void setAddCreditRating(String addCreditRating) {
		this.addCreditRating = addCreditRating;
	}

	public String getAddCustomerType() {
		return addCustomerType;
	}

	public void setAddCustomerType(String addCustomerType) {
		this.addCustomerType = addCustomerType;
	}

	public String getAddProductCode() {
		return addProductCode;
	}

	public void setAddProductCode(String addProductCode) {
		this.addProductCode = addProductCode;
	}

	public String getAddProductText() {
		return addProductText;
	}

	public void setAddProductText(String addProductText) {
		this.addProductText = addProductText;
	}

	public String getAddRemark() {
		return addRemark;
	}

	public void setAddRemark(String addRemark) {
		this.addRemark = addRemark;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpdateCreditRating() {
		return updateCreditRating;
	}

	public void setUpdateCreditRating(String updateCreditRating) {
		this.updateCreditRating = updateCreditRating;
	}

	public String getUpdateCustomerType() {
		return updateCustomerType;
	}

	public void setUpdateCustomerType(String updateCustomerType) {
		this.updateCustomerType = updateCustomerType;
	}

	public String getUpdateProductCode() {
		return updateProductCode;
	}

	public void setUpdateProductCode(String updateProductCode) {
		this.updateProductCode = updateProductCode;
	}

	public String getUpdateProductText() {
		return updateProductText;
	}

	public void setUpdateProductText(String updateProductText) {
		this.updateProductText = updateProductText;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}

	public String getAddCreaditType() {
		return addCreaditType;
	}

	public void setAddCreaditType(String addCreaditType) {
		this.addCreaditType = addCreaditType;
	}

	public String getUpdateCreaditType() {
		return updateCreaditType;
	}

	public void setUpdateCreaditType(String updateCreaditType) {
		this.updateCreaditType = updateCreaditType;
	}
	
	
}
