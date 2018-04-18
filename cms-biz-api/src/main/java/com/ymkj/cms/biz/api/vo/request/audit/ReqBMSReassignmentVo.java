package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSReassignmentVo extends Request{

	/**
	 * 对象序列化
	 */
	private static final long serialVersionUID = 3659964207944500738L;
	private int pageNum;//页数
	private int pageSize;//一页显示条数
	private String Flag;//请求标识
	private String loanNo;//借款单号
	private String customerName;//客户名称
	private String customerIDNO;//客户身份证
	private String branchManagerName;//客户经理名称
	private String productCd;//产品编码
	private String owningBranchId;//营业部ID
	private String fpStatus;//分派状态
	private String caseType;//案件标识
	
	private String ifPri;//是否加急
	private String ifSuspectCheat;//是否欺诈
	private String appInputFlag;//是否app进件
	private String xsStartDate;//提交信审开始时间
	private String xsEndDate;//提交信审结束时间
	private String handleCode;//处理人编码
	private String loginPersonCode;//登录人编码
	private String[] accLmt;   //下限金额
	private String returnFile;//退回件
	
	private List<String> caseIdentifyList; //案件标识 1加急，2APP进件，3反欺诈，4退回件，5优惠标识
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
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
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDNO() {
		return customerIDNO;
	}
	public void setCustomerIDNO(String customerIDNO) {
		this.customerIDNO = customerIDNO;
	}
	
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getFpStatus() {
		return fpStatus;
	}
	public void setFpStatus(String fpStatus) {
		this.fpStatus = fpStatus;
	}
	
	public String getXsStartDate() {
		return xsStartDate;
	}
	public void setXsStartDate(String xsStartDate) {
		this.xsStartDate = xsStartDate;
	}
	public String getXsEndDate() {
		return xsEndDate;
	}
	public void setXsEndDate(String xsEndDate) {
		this.xsEndDate = xsEndDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHandleCode() {
		return handleCode;
	}
	public void setHandleCode(String handleCode) {
		this.handleCode = handleCode;
	}
	public String getLoginPersonCode() {
		return loginPersonCode;
	}
	public void setLoginPersonCode(String loginPersonCode) {
		this.loginPersonCode = loginPersonCode;
	}
	
	public String[] getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String[] accLmt) {
		this.accLmt = accLmt;
	}
	public String getReturnFile() {
		return returnFile;
	}
	public void setReturnFile(String returnFile) {
		this.returnFile = returnFile;
	}
	public String getFieldSort() {
		return fieldSort;
	}
	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	public int getRulesSort() {
		return rulesSort;
	}
	public void setRulesSort(int rulesSort) {
		this.rulesSort = rulesSort;
	}

	public List<String> getCaseIdentifyList() {
		return caseIdentifyList;
	}

	public void setCaseIdentifyList(List<String> caseIdentifyList) {
		this.caseIdentifyList = caseIdentifyList;
	}

	
}
