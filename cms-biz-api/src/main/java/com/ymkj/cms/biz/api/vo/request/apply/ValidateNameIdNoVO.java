package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ValidateNameIdNoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	 
	 
	private String name; //申请客户姓名
	private String idNo; //申请客户身份证
	private Long owningBranchId;//录入门店ID
	private String productCode;  //商品code
	private String loanNo;//借款编号
	private String appStatus;//app侧状态
	
	
	private String owningBranch;//录入门店
	private String ifThroughTrain;//是否是直通车  0 不是  1 是
	private String orgId;//组织id
	private String auditLimitId;//期限ID
	
	
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getAuditLimitId() {
		return auditLimitId;
	}
	public void setAuditLimitId(String auditLimitId) {
		this.auditLimitId = auditLimitId;
	}
	public String getIfThroughTrain() {
		return ifThroughTrain;
	}
	public void setIfThroughTrain(String ifThroughTrain) {
		this.ifThroughTrain = ifThroughTrain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public ValidateNameIdNoVO(){	
	}
	public ValidateNameIdNoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
