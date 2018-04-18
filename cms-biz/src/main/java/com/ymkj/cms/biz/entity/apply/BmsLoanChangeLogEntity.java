package com.ymkj.cms.biz.entity.apply;

import com.ymkj.base.core.biz.entity.BaseEntity;
import java.util.Date;


public class BmsLoanChangeLogEntity extends BaseEntity {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; //

	 private String loanNo; //接口编号

	 private Integer loanBaseId; //

	 private String operationModule; //操作模块Code

	 private String operator; //操作人

	 private String operatorCode; //操作人工号

	 private Date operationStart; //操作时间

	 private String content; //变更内容

	 private Date createdTime; //创建时间

	 private Integer version; //默认值是1

	 private Integer isDelete; //默认是0,删除是1


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}

	public String getLoanNo(){
		return this.loanNo;
	}

	public void setLoanBaseId(Integer loanBaseId){
		this.loanBaseId = loanBaseId;
	}

	public Integer getLoanBaseId(){
		return this.loanBaseId;
	}

	public void setOperationModule(String operationModule){
		this.operationModule = operationModule;
	}

	public String getOperationModule(){
		return this.operationModule;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return this.operator;
	}

	public void setOperatorCode(String operatorCode){
		this.operatorCode = operatorCode;
	}

	public String getOperatorCode(){
		return this.operatorCode;
	}

	public void setOperationStart(Date operationStart){
		this.operationStart = operationStart;
	}

	public Date getOperationStart(){
		return this.operationStart;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public Date getCreatedTime(){
		return this.createdTime;
	}

	public void setVersion(Integer version){
		this.version = version;
	}

	public Integer getVersion(){
		return this.version;
	}

	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}

}
