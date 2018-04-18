package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 档案类
 * @author YM10172
 *
 */
public class ReqArchivesVO extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1500885367492020072L;
	
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
    private Long version; //版本号
    
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
	public Integer getPropertyProveNum() {
		return propertyProveNum;
	}
	public void setPropertyProveNum(Integer propertyProveNum) {
		this.propertyProveNum = propertyProveNum;
	}
	
    
    
}
