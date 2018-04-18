package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class QueryMainEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String repCode;// 响应吗 Y ‘000000’成功；其余为更新失败

	private String ifHaveValidate; // 是否有借款 Y有 N无
	private String previousStatus; // 上笔借款状态
	private String previousRtfState; // 上笔借款环节
	private Long previousOwningBranchId; // 上笔借款录单营业部
	private String previousIfRefuse; // 上笔借款是否被拒 Y是 N否
	private Date previousRefuseDate; // 最近操作的一笔借款被拒时间
	private Date previousCancelDate;// 最近操作的一笔借款被取消时间
	private Integer limitDays; // 限制天数
	private Integer protectDays;// 保护天数
	private String applyType; // 申请类型
	private String productCode; // 借款产品
	private String applyDate; // 申请创建时间
	private String appApplyInput; // 是否APP进件 ‘Y是 N否
	private String owningBranchId; // 进件营业部
	private String appNo;//借款编号

	
	
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	public String getIfHaveValidate() {
		return ifHaveValidate;
	}

	public void setIfHaveValidate(String ifHaveValidate) {
		this.ifHaveValidate = ifHaveValidate;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public String getPreviousRtfState() {
		return previousRtfState;
	}

	public void setPreviousRtfState(String previousRtfState) {
		this.previousRtfState = previousRtfState;
	}

	public Long getPreviousOwningBranchId() {
		return previousOwningBranchId;
	}

	public void setPreviousOwningBranchId(Long previousOwningBranchId) {
		this.previousOwningBranchId = previousOwningBranchId;
	}

	public String getPreviousIfRefuse() {
		return previousIfRefuse;
	}

	public void setPreviousIfRefuse(String previousIfRefuse) {
		this.previousIfRefuse = previousIfRefuse;
	}

	public Date getPreviousRefuseDate() {
		return previousRefuseDate;
	}

	public void setPreviousRefuseDate(Date previousRefuseDate) {
		this.previousRefuseDate = previousRefuseDate;
	}

	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	public Integer getProtectDays() {
		return protectDays;
	}

	public void setProtectDays(Integer protectDays) {
		this.protectDays = protectDays;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAppApplyInput() {
		return appApplyInput;
	}

	public void setAppApplyInput(String appApplyInput) {
		this.appApplyInput = appApplyInput;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public Date getPreviousCancelDate() {
		return previousCancelDate;
	}

	public void setPreviousCancelDate(Date previousCancelDate) {
		this.previousCancelDate = previousCancelDate;
	}

	
}
