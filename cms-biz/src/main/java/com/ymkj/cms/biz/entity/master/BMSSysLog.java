package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;

/**
 * 银行表对应的实体类
 * @author YM10115
 *
 */
public class BMSSysLog extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369946015268396394L;
	
	private Long id;
	private String postCode; //  岗位;
	private String operatorCode; // 操作人工号
	private String operator; // 操作人
	private String firstLevelDir; // 一级目录code
	private String twoLevelDir; // 二级目录code
	private String operationType; //操作类型code

	private String operationTime; //操作时间
	private String ip; //ip 
	private String remark; //备注
	
	public BMSSysLog(ReqLoanContractSignVo reqLoanContractSignVo) {
		this.setPostCode(reqLoanContractSignVo.getPostCode());
		this.setIp(reqLoanContractSignVo.getIp());
		this.setOperationTime(DateUtil.defaultFormatDate(new Date()));
		this.setOperator(reqLoanContractSignVo.getServiceName());//当前登录用户
		this.setOperatorCode(reqLoanContractSignVo.getServiceCode());//当前登录用户	
		this.setRemark(reqLoanContractSignVo.getRemark());//当前登录用户
	}

	
	
	public BMSSysLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getFirstLevelDir() {
		return firstLevelDir;
	}
	public void setFirstLevelDir(String firstLevelDir) {
		this.firstLevelDir = firstLevelDir;
	}
	public String getTwoLevelDir() {
		return twoLevelDir;
	}
	public void setTwoLevelDir(String twoLevelDir) {
		this.twoLevelDir = twoLevelDir;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	 
	
	            
}
