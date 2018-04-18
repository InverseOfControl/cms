package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 1.demo请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 2.其中必须包含 对应实体类 {Entity}中的 所有字段, 业务不同实体不同  
 * 3.如有需要可 自行扩展
 * @author 
 *
 */
public class ReqBMSLoanAuditVO extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = 996070232633674994L;
	public ReqBMSLoanAuditVO(){
		
	}
	public ReqBMSLoanAuditVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
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

	private String accLmt;
    private String accTerm;
    private String accDate;
    private String pointResult;
    private String proNum;
    private String proName;
    private String checkNodeState;
    private String refuseCode;
    private String checkPersonCode;
    private String checkPerson;
    private String checkAllotDate;
    private String finalPersonCode;
    private String finalPerson;
    private String finalRole;
    private String finalAllotDate;
    private String approvalPerson;
    private String amoutIncome;
    private String sysCheckLmt;
    private String sysAccTrem;
    private String sysAccLmt;
    private String isRollback;
    private String minApprovalAmt;
    private String ifCreditRecord;
    private String maxApprovalAmt;
    private String startDate;
    private String endDate;
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
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(String accTerm) {
		this.accTerm = accTerm;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getPointResult() {
		return pointResult;
	}
	public void setPointResult(String pointResult) {
		this.pointResult = pointResult;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCheckNodeState() {
		return checkNodeState;
	}
	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}
	public String getRefuseCode() {
		return refuseCode;
	}
	public void setRefuseCode(String refuseCode) {
		this.refuseCode = refuseCode;
	}
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getCheckAllotDate() {
		return checkAllotDate;
	}
	public void setCheckAllotDate(String checkAllotDate) {
		this.checkAllotDate = checkAllotDate;
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
	public String getFinalRole() {
		return finalRole;
	}
	public void setFinalRole(String finalRole) {
		this.finalRole = finalRole;
	}
	public String getFinalAllotDate() {
		return finalAllotDate;
	}
	public void setFinalAllotDate(String finalAllotDate) {
		this.finalAllotDate = finalAllotDate;
	}
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getSysCheckLmt() {
		return sysCheckLmt;
	}
	public void setSysCheckLmt(String sysCheckLmt) {
		this.sysCheckLmt = sysCheckLmt;
	}
	public String getSysAccTrem() {
		return sysAccTrem;
	}
	public void setSysAccTrem(String sysAccTrem) {
		this.sysAccTrem = sysAccTrem;
	}
	public String getSysAccLmt() {
		return sysAccLmt;
	}
	public void setSysAccLmt(String sysAccLmt) {
		this.sysAccLmt = sysAccLmt;
	}
	public String getIsRollback() {
		return isRollback;
	}
	public void setIsRollback(String isRollback) {
		this.isRollback = isRollback;
	}
	public String getMinApprovalAmt() {
		return minApprovalAmt;
	}
	public void setMinApprovalAmt(String minApprovalAmt) {
		this.minApprovalAmt = minApprovalAmt;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getMaxApprovalAmt() {
		return maxApprovalAmt;
	}
	public void setMaxApprovalAmt(String maxApprovalAmt) {
		this.maxApprovalAmt = maxApprovalAmt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
