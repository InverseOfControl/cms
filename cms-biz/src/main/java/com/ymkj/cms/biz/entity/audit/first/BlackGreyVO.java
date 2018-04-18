package com.ymkj.cms.biz.entity.audit.first;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;

public class BlackGreyVO extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loanNo;//借款单号  获取姓名和身份证
	private String firstLevelReasons;//一级原因
	private String firstLevelReasonsCode;//一级原因code
	private String twoLevelReasons;//二级原因
	private String twoLevelReasonsCode;//二级原因code
	private String operatorCode;//操作人code
	
	
	private String type;	//1 门店上报


	public BlackGreyVO(LoanExtEntity loanExtEntity) {
		this.loanNo =loanExtEntity.getLoanNo();
		this.firstLevelReasonsCode=	loanExtEntity.getFirstLevleReasonsCode();
		this.firstLevelReasons=loanExtEntity.getPrimaryReason();
		this.twoLevelReasons =loanExtEntity.getSecodeReason();
		this.twoLevelReasonsCode=loanExtEntity.getTwoLevleReasonsCode();
	}

	
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public BlackGreyVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getLoanNo() {
		return loanNo;
	}


	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}


	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}


	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}


	public String getFirstLevelReasonsCode() {
		return firstLevelReasonsCode;
	}


	public void setFirstLevelReasonsCode(String firstLevelReasonsCode) {
		this.firstLevelReasonsCode = firstLevelReasonsCode;
	}


	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}


	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
	}


	public String getTwoLevelReasonsCode() {
		return twoLevelReasonsCode;
	}


	public void setTwoLevelReasonsCode(String twoLevelReasonsCode) {
		this.twoLevelReasonsCode = twoLevelReasonsCode;
	}


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
}
