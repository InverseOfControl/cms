package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class APPCurrentApplyEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ifBlacklist;		//当前申请   是否匹配黑名单  Y匹配  N不匹配
	private String applyType;		//当前申请   申请类型
	private Date applyDate;			//当前申请   申请创建时间
	private String appApplyInput;		//当前申请   是否APP进件   Y是  N否
	private String owningBranchCity;//当前申请  进件营业部城市
	private String owningBranchDivision;	//当前申请  进件营业部分部
	private String owningBranchArea;	//当前申请  进件营业部区域
	private Date firstInModifyDate;//当前申请  首次进入录入修改时间
	private Date appServiceClaimDate;	//APP客服认领时间
	
	private String rtfState;//当前借款处于业务环节
	
	
	
	public Date getAppServiceClaimDate() {
		return appServiceClaimDate;
	}
	public void setAppServiceClaimDate(Date appServiceClaimDate) {
		this.appServiceClaimDate = appServiceClaimDate;
	}
	public Date getFirstInModifyDate() {
		return firstInModifyDate;
	}
	public void setFirstInModifyDate(Date firstInModifyDate) {
		this.firstInModifyDate = firstInModifyDate;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getIfBlacklist() {
		return ifBlacklist;
	}
	public void setIfBlacklist(String ifBlacklist) {
		this.ifBlacklist = ifBlacklist;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getAppApplyInput() {
		return appApplyInput;
	}
	public void setAppApplyInput(String appApplyInput) {
		this.appApplyInput = appApplyInput;
	}
	public String getOwningBranchCity() {
		return owningBranchCity;
	}
	public void setOwningBranchCity(String owningBranchCity) {
		this.owningBranchCity = owningBranchCity;
	}
	public String getOwningBranchDivision() {
		return owningBranchDivision;
	}
	public void setOwningBranchDivision(String owningBranchDivision) {
		this.owningBranchDivision = owningBranchDivision;
	}
	public String getOwningBranchArea() {
		return owningBranchArea;
	}
	public void setOwningBranchArea(String owningBranchArea) {
		this.owningBranchArea = owningBranchArea;
	}
	
	
	
}
