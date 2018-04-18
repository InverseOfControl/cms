package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSSysLoanLogVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	
	 
    
private Long loanBaseId; 
private String operationModule; // 操作环节code
private String operationType; // 操作类型code
private String operator; // 操作人
private String operatorCode; // 操作人工号
private String firstLevleReasons; // 一级原因code
private String twoLevleReasons; // 二级原因code
private Date operationTime; //操作时间

private String remark; //备注





public Long getLoanBaseId() {
return loanBaseId;
}

public void setLoanBaseId(Long loanBaseId) {
this.loanBaseId = loanBaseId;
}

public String getOperationModule() {
return operationModule;
}

public void setOperationModule(String operationModule) {
this.operationModule = operationModule;
}

public String getOperationType() {
return operationType;
}

public void setOperationType(String operationType) {
this.operationType = operationType;
}

public String getOperator() {
return operator;
}

public void setOperator(String operator) {
this.operator = operator;
}

public String getOperatorCode() {
return operatorCode;
}

public void setOperatorCode(String operatorCode) {
this.operatorCode = operatorCode;
}

public String getFirstLevleReasons() {
return firstLevleReasons;
}

public void setFirstLevleReasons(String firstLevleReasons) {
this.firstLevleReasons = firstLevleReasons;
}

public String getTwoLevleReasons() {
return twoLevleReasons;
}

public void setTwoLevleReasons(String twoLevleReasons) {
this.twoLevleReasons = twoLevleReasons;
}

public Date getOperationTime() {
return operationTime;
}

public void setOperationTime(Date operationTime) {
this.operationTime = operationTime;
}

public String getRemark() {
return remark;
}

public void setRemark(String remark) {
this.remark = remark;
}

}
