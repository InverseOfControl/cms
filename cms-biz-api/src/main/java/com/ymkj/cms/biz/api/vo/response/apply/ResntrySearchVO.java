package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

public class ResntrySearchVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7362664530640818299L;
	
	private Integer Id;
	private String appNo;  //老系统借款编号
	private String loanNo; //申请件编号
	private String fileNo;
	private String fileStatus;
	private String approval; //审批单
	private Integer approvalNum; //审批单页数
	private String loanForm; //借款申请表
	private Integer loanFormNum; //借款申请页数
	private String bankCardCopy; //银行卡复印件
	private Integer bankCardCopyNum; //银行卡复印件页数
	private String loanSignRecordTable; //借款人签约记录表
	private Integer loanSignRecordTableNum;//借款人签约记录表页数
	private String personalCreditReport;//个人信用报告
    private Integer personalCreditReportNum;//个人信用报告页数
    private String identityProve; //身份证明
    private Integer identityProveNum;//身份证明页数
    private String incomeProve; //收入证明
    private Integer incomeProveNum; //收入证明页数
    private String jobProve; //工作证明
    private Integer jobProveNum; //工作证明页数
    private String addressProve; //住址证明
    private Integer addressProveNum; //住址证明页数
    private String bussinessProve; //经营证明
    private Integer bussinessProveNum; //经营证明页数
    private String propertyProve; //资产证明
    private Integer propertyProveNum; //资产证明页数
    private String othersProve;//其他证明
    private Integer othersProveNum;  //其他证明页数
    private String remark;  //备注
    private Integer remarkNum; //备注页数
    private String loanBranch; //放款营业部
    private String loanBranchId; //放款营业部ID
	private Long version; //版本号
	private Long isDeleted; //是否删除
	private String creator;  //创建人
	private Long creatorId; //创建人ID
	private Date creatorDate; //创建时间
	private String modified; //修改人
	private Long modifiedId; //修改人ID
	private Date modifiedDate; //修改日期
	private String productName; //申请产品
	private String name;  //客户姓名
	private Date loanDate;  //放款日期 
	private String businessProve; //经营证明
	private Integer businessProveNum; //经营证明页数
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public Integer getApprovalNum() {
		return approvalNum;
	}
	public void setApprovalNum(Integer approvalNum) {
		this.approvalNum = approvalNum;
	}
	public String getLoanForm() {
		return loanForm;
	}
	public void setLoanForm(String loanForm) {
		this.loanForm = loanForm;
	}
	public Integer getLoanFormNum() {
		return loanFormNum;
	}
	public void setLoanFormNum(Integer loanFormNum) {
		this.loanFormNum = loanFormNum;
	}
	public String getBankCardCopy() {
		return bankCardCopy;
	}
	public void setBankCardCopy(String bankCardCopy) {
		this.bankCardCopy = bankCardCopy;
	}
	public Integer getBankCardCopyNum() {
		return bankCardCopyNum;
	}
	public void setBankCardCopyNum(Integer bankCardCopyNum) {
		this.bankCardCopyNum = bankCardCopyNum;
	}
	public String getLoanSignRecordTable() {
		return loanSignRecordTable;
	}
	public void setLoanSignRecordTable(String loanSignRecordTable) {
		this.loanSignRecordTable = loanSignRecordTable;
	}
	public Integer getLoanSignRecordTableNum() {
		return loanSignRecordTableNum;
	}
	public void setLoanSignRecordTableNum(Integer loanSignRecordTableNum) {
		this.loanSignRecordTableNum = loanSignRecordTableNum;
	}
	public String getPersonalCreditReport() {
		return personalCreditReport;
	}
	public void setPersonalCreditReport(String personalCreditReport) {
		this.personalCreditReport = personalCreditReport;
	}
	public Integer getPersonalCreditReportNum() {
		return personalCreditReportNum;
	}
	public void setPersonalCreditReportNum(Integer personalCreditReportNum) {
		this.personalCreditReportNum = personalCreditReportNum;
	}
	public String getIdentityProve() {
		return identityProve;
	}
	public void setIdentityProve(String identityProve) {
		this.identityProve = identityProve;
	}
	public Integer getIdentityProveNum() {
		return identityProveNum;
	}
	public void setIdentityProveNum(Integer identityProveNum) {
		this.identityProveNum = identityProveNum;
	}
	public String getIncomeProve() {
		return incomeProve;
	}
	public void setIncomeProve(String incomeProve) {
		this.incomeProve = incomeProve;
	}
	public Integer getIncomeProveNum() {
		return incomeProveNum;
	}
	public void setIncomeProveNum(Integer incomeProveNum) {
		this.incomeProveNum = incomeProveNum;
	}
	public String getJobProve() {
		return jobProve;
	}
	public void setJobProve(String jobProve) {
		this.jobProve = jobProve;
	}
	public Integer getJobProveNum() {
		return jobProveNum;
	}
	public void setJobProveNum(Integer jobProveNum) {
		this.jobProveNum = jobProveNum;
	}
	public String getAddressProve() {
		return addressProve;
	}
	public void setAddressProve(String addressProve) {
		this.addressProve = addressProve;
	}
	public Integer getAddressProveNum() {
		return addressProveNum;
	}
	public void setAddressProveNum(Integer addressProveNum) {
		this.addressProveNum = addressProveNum;
	}
	public String getBussinessProve() {
		return bussinessProve;
	}
	public void setBussinessProve(String bussinessProve) {
		this.bussinessProve = bussinessProve;
	}
	public Integer getBussinessProveNum() {
		return bussinessProveNum;
	}
	public void setBussinessProveNum(Integer bussinessProveNum) {
		this.bussinessProveNum = bussinessProveNum;
	}
	public String getPropertyProve() {
		return propertyProve;
	}
	public void setPropertyProve(String propertyProve) {
		this.propertyProve = propertyProve;
	}
	
	public Integer getPropertyProveNum() {
		return propertyProveNum;
	}
	public void setPropertyProveNum(Integer propertyProveNum) {
		this.propertyProveNum = propertyProveNum;
	}
	public String getOthersProve() {
		return othersProve;
	}
	public void setOthersProve(String othersProve) {
		this.othersProve = othersProve;
	}
	public Integer getOthersProveNum() {
		return othersProveNum;
	}
	public void setOthersProveNum(Integer othersProveNum) {
		this.othersProveNum = othersProveNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRemarkNum() {
		return remarkNum;
	}
	public void setRemarkNum(Integer remarkNum) {
		this.remarkNum = remarkNum;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Long getModifiedId() {
		return modifiedId;
	}
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getLoanBranch() {
		return loanBranch;
	}
	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}
	public String getLoanBranchId() {
		return loanBranchId;
	}
	public void setLoanBranchId(String loanBranchId) {
		this.loanBranchId = loanBranchId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getBusinessProve() {
		return businessProve;
	}
	public void setBusinessProve(String businessProve) {
		this.businessProve = businessProve;
	}
	public Integer getBusinessProveNum() {
		return businessProveNum;
	}
	public void setBusinessProveNum(Integer businessProveNum) {
		this.businessProveNum = businessProveNum;
	}
	
	
    

}
