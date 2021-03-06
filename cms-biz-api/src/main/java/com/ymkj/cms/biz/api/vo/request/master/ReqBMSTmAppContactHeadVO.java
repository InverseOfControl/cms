package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSTmAppContactHeadVO extends Request{

	private static final long serialVersionUID = -8871607811129981683L;
	
 private Integer id; //id

	 
	 private String loanNo; //借款编号

	 
	 private String appNo; //申请件编号

	
	 private Integer loanBaseId; //LOAN_BASE_ID

	 
	 private Integer personId; //客户主表主键

	 
	 private String creator; //创建用户

	
	 private Date createdTime; //创建时间

	
	 private Long creatorId; //创建用户ID

	 
	 private Integer flowState; //

	
	 private Integer version; //默认值是1

	 
	 private Integer snapVersion; //快照版本

	 
	 private Integer isDelete; //默认是0，删除是1


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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


	public Integer getLoanBaseId() {
		return loanBaseId;
	}


	public void setLoanBaseId(Integer loanBaseId) {
		this.loanBaseId = loanBaseId;
	}


	public Integer getPersonId() {
		return personId;
	}


	public void setPersonId(Integer personId) {
		this.personId = personId;
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


	public Long getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}


	public Integer getFlowState() {
		return flowState;
	}


	public void setFlowState(Integer flowState) {
		this.flowState = flowState;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getSnapVersion() {
		return snapVersion;
	}


	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
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
