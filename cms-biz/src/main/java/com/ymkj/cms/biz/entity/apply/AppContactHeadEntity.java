package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppContactHeadEntity extends BaseEntity {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; //id

	 private String loanNo; //

	 private String appNo; //申请件编号

	 private Long loanBaseId; //LOAN_BASE_ID

	 private Long personId; //客户主表主键

	 private String creator; //创建用户

	 private Date createdTime; //创建时间

	 private Long creatorId; //创建用户ID

	 private Long flowState; //

	 private Long version; //默认值是1

	 private Long snapVersion = 3L; //

	 private Long isDelete; //默认是0，删除是1


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}

	public String getLoanNo(){
		return this.loanNo;
	}

	public void setAppNo(String appNo){
		this.appNo = appNo;
	}

	public String getAppNo(){
		return this.appNo;
	}

	public void setLoanBaseId(Long loanBaseId){
		this.loanBaseId = loanBaseId;
	}

	public Long getLoanBaseId(){
		return this.loanBaseId;
	}

	public void setPersonId(Long personId){
		this.personId = personId;
	}

	public Long getPersonId(){
		return this.personId;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getCreator(){
		return this.creator;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public Date getCreatedTime(){
		return this.createdTime;
	}

	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}

	public Long getCreatorId(){
		return this.creatorId;
	}

	public void setFlowState(Long flowState){
		this.flowState = flowState;
	}

	public Long getFlowState(){
		return this.flowState;
	}

	public void setVersion(Long version){
		this.version = version;
	}

	public Long getVersion(){
		return this.version;
	}

	public void setSnapVersion(Long snapVersion){
		this.snapVersion = snapVersion;
	}

	public Long getSnapVersion(){
		return this.snapVersion;
	}

	public void setIsDelete(Long isDelete){
		this.isDelete = isDelete;
	}

	public Long getIsDelete(){
		return this.isDelete;
	}

}
