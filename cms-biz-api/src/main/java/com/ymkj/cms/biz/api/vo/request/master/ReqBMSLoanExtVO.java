package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSLoanExtVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619167128737931543L;
	
	private String loanNo;
	private String appNo;
	    public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
		private String specialOrg;
	    private String regularState;
	    private String specialPlan;
	    private String pushDate;
	    private String reasonShuxin;
	    private String creditApplication;
	    private String sugLmt;
	    private String appOrgName;
	    private String priority;
	    private String thirdId;
	    private String appLoanPlan;
	    private String ensureAmtAmount;
	    private String clientType;
	  /*  private String primaryReason;
	    private String secodeReason;*/
	    private int id;
	    private String loanBaseId;
	    private String creator;
	    private String createdTime;
	    private String creatorId;
	    private String modifier;
	    private String modifiedTime;
	    private String modifierId;
	    private String version;
	    private String isDelete;
	    
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLoanBaseId() {
			return loanBaseId;
		}
		public void setLoanBaseId(String loanBaseId) {
			this.loanBaseId = loanBaseId;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(String creatorId) {
			this.creatorId = creatorId;
		}
		public String getModifier() {
			return modifier;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		public String getModifiedTime() {
			return modifiedTime;
		}
		public void setModifiedTime(String modifiedTime) {
			this.modifiedTime = modifiedTime;
		}
		public String getModifierId() {
			return modifierId;
		}
		public void setModifierId(String modifierId) {
			this.modifierId = modifierId;
		}
		public String getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(String isDelete) {
			this.isDelete = isDelete;
		}
		private int pageNum;     // 当前页数
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
		public String getSpecialOrg() {
			return specialOrg;
		}
		public void setSpecialOrg(String specialOrg) {
			this.specialOrg = specialOrg;
		}
		public String getRegularState() {
			return regularState;
		}
		public void setRegularState(String regularState) {
			this.regularState = regularState;
		}
		public String getSpecialPlan() {
			return specialPlan;
		}
		public void setSpecialPlan(String specialPlan) {
			this.specialPlan = specialPlan;
		}
		public String getPushDate() {
			return pushDate;
		}
		public void setPushDate(String pushDate) {
			this.pushDate = pushDate;
		}
		public String getReasonShuxin() {
			return reasonShuxin;
		}
		public void setReasonShuxin(String reasonShuxin) {
			this.reasonShuxin = reasonShuxin;
		}
		public String getCreditApplication() {
			return creditApplication;
		}
		public void setCreditApplication(String creditApplication) {
			this.creditApplication = creditApplication;
		}
		public String getSugLmt() {
			return sugLmt;
		}
		public void setSugLmt(String sugLmt) {
			this.sugLmt = sugLmt;
		}
		public String getAppOrgName() {
			return appOrgName;
		}
		public void setAppOrgName(String appOrgName) {
			this.appOrgName = appOrgName;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getThirdId() {
			return thirdId;
		}
		public void setThirdId(String thirdId) {
			this.thirdId = thirdId;
		}
		public String getAppLoanPlan() {
			return appLoanPlan;
		}
		public void setAppLoanPlan(String appLoanPlan) {
			this.appLoanPlan = appLoanPlan;
		}
		public String getEnsureAmtAmount() {
			return ensureAmtAmount;
		}
		public void setEnsureAmtAmount(String ensureAmtAmount) {
			this.ensureAmtAmount = ensureAmtAmount;
		}
		public String getClientType() {
			return clientType;
		}
		public void setClientType(String clientType) {
			this.clientType = clientType;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

}
