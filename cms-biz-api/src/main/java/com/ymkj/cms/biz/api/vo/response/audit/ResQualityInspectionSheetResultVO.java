package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.util.Date;

public class ResQualityInspectionSheetResultVO implements Serializable{
	//借款编号
	private String loanNo;
	//抽取时间
	private Date extractionTime;
	//申请状态
	private String status;
	//客户姓名
	private String name;
	//身份证
	private String idNO;
	//进件门店ID
	private Integer owningBranchId;
	//进件门店
	private String owningBranch;
	//申请日期
	private Date applyDate;
	//申请类型
	private String applyType;
	//流程节点状态
	private String rtfNodeState;
	//初审工号
	private String checkPersonCode;
	//初审人员
	private String checkPerson;
	//终审工号
	private String finalPersonCode;
	//终审人员 
	private String finalPerson;
	//审批日期
	private Date accDate;
	//借款产品
	private String productCd;
	//一级原因
	private String firstLevleReasons;
	//二级原因
	private String twoLevleReasons;
	//一级原因CODE
	private String firstLevleReasonsCode;
	//二级原因CODE
	private String twoLevleReasonsCode;
	//拒绝件走过终审通过并且被申请件信息维护拒绝总数
	private Integer countZsJj;
	
	//判断此单子是不是通过件是的话值大于=1
	private String passFile;
	//判断次单子是不是拒绝件是的话值大于=1
	private String rejectFile;
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Date getExtractionTime() {
		return extractionTime;
	}
	public void setExtractionTime(Date extractionTime) {
		this.extractionTime = extractionTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNO() {
		return idNO;
	}
	public void setIdNO(String idNO) {
		this.idNO = idNO;
	}
	public Integer getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Integer owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	
	
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}
	public String getFinalPerson() {
		return finalPerson;
	}
	public void setFinalPerson(String finalPerson) {
		this.finalPerson = finalPerson;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
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
	public String getFirstLevleReasonsCode() {
		return firstLevleReasonsCode;
	}
	public void setFirstLevleReasonsCode(String firstLevleReasonsCode) {
		this.firstLevleReasonsCode = firstLevleReasonsCode;
	}
	public String getTwoLevleReasonsCode() {
		return twoLevleReasonsCode;
	}
	public void setTwoLevleReasonsCode(String twoLevleReasonsCode) {
		this.twoLevleReasonsCode = twoLevleReasonsCode;
	}
	public Integer getCountZsJj() {
		return countZsJj;
	}
	public void setCountZsJj(Integer countZsJj) {
		this.countZsJj = countZsJj;
	}
	public String getPassFile() {
		return passFile;
	}
	public void setPassFile(String passFile) {
		this.passFile = passFile;
	}
	public String getRejectFile() {
		return rejectFile;
	}
	public void setRejectFile(String rejectFile) {
		this.rejectFile = rejectFile;
	}
	
}
