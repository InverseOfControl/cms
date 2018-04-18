package com.ymkj.cms.biz.api.vo.response.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class ResLoanVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7320896438673439399L;
	
		private String loanNo; //借款编号
		private Long id; //借款编号
		private String channelName;		// 渠道名称
		private String	productName;// 产品名称
		private String channelCd;		// 渠道名称
		private String	productCd;// 产品名称
		private BigDecimal applyLmt  ; // 申请额度
		private BigDecimal contractLmt; // 签约期限
		private Integer contractTrem; // 签约期限
		private String	name;// 客户姓名
		private String	idNo;// 身份证号码
		private String applyBankName;//银行名称
		private String applyBankBranch ; // 开户银行
		private String applyBankCardNo ; //银行卡号
		private String bankPhone;  	// 手机号码
		private String	contractBranchId;	 //  签约网点ID
		private String owningBranchId; //进件营业部ID
		private String	applyType;		  //申请类型
		private String	applyInputFlag;	   //     交件类型*/
		private String contractBranch; //  签约网点
		private Long taskId;//任务TASKid
		private String ifPri;//是否加急
		private String oprateType;
		private Long version;
		
		/**
		 *  APP进件标识     app_input:进件
		 */
		private String appInputFlag;
		/**
		 * 是否疑似欺诈  Y 是  N否
		 */
		private String ifSuspectCheat;
		
		
		private List<ResLoanVo> failList;
		private List<ResLoanVo> successList;
		/**
		 * 核心状态集合
		 */
		private List<Map<String,Object>> loanCoreStateList;
		
		/**
		 * 出错信息：目前预留着，备用
		 */
		private String errorMessage;
		
		private String pactMoney;
		
		//是否优惠费率用户  Y 是  N 否
	    private String ifPreferentialUser;
	
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public String getIfPri() {
			return ifPri;
		}
		public void setIfPri(String ifPri) {
			this.ifPri = ifPri;
		}
		public List<ResLoanVo> getFailList() {
			return failList;
		}
		public void setFailList(List<ResLoanVo> failList) {
			this.failList = failList;
		}
		public List<ResLoanVo> getSuccessList() {
			return successList;
		}
		public void setSuccessList(List<ResLoanVo> successList) {
			this.successList = successList;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		public String getContractBranch() {
			return contractBranch;
		}
		public void setContractBranch(String contractBranch) {
			this.contractBranch = contractBranch;
		}
		public String getLoanNo() {
			return loanNo;
		}
		public void setLoanNo(String loanNo) {
			this.loanNo = loanNo;
		}
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public BigDecimal getApplyLmt() {
			return applyLmt;
		}
		public void setApplyLmt(BigDecimal applyLmt) {
			this.applyLmt = applyLmt;
		}
		public Integer getContractTrem() {
			return contractTrem;
		}
		public void setContractTrem(Integer contractTrem) {
			this.contractTrem = contractTrem;
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
		public String getApplyBankName() {
			return applyBankName;
		}
		public void setApplyBankName(String applyBankName) {
			this.applyBankName = applyBankName;
		}
		public String getApplyBankBranch() {
			return applyBankBranch;
		}
		public void setApplyBankBranch(String applyBankBranch) {
			this.applyBankBranch = applyBankBranch;
		}
		public String getApplyBankCardNo() {
			return applyBankCardNo;
		}
		public void setApplyBankCardNo(String applyBankCardNo) {
			this.applyBankCardNo = applyBankCardNo;
		}
		public String getBankPhone() {
			return bankPhone;
		}
		public void setBankPhone(String bankPhone) {
			this.bankPhone = bankPhone;
		}
		public String getContractBranchId() {
			return contractBranchId;
		}
		public void setContractBranchId(String contractBranchId) {
			this.contractBranchId = contractBranchId;
		}
		public String getApplyType() {
			return applyType;
		}
		public void setApplyType(String applyType) {
			this.applyType = applyType;
		}
		public String getApplyInputFlag() {
			return applyInputFlag;
		}
		public void setApplyInputFlag(String applyInputFlag) {
			this.applyInputFlag = applyInputFlag;
		}
		public BigDecimal getContractLmt() {
			return contractLmt;
		}
		public void setContractLmt(BigDecimal contractLmt) {
			this.contractLmt = contractLmt;
		}
		public String getOprateType() {
			return oprateType;
		}
		public void setOprateType(String oprateType) {
			this.oprateType = oprateType;
		}
		public String getAppInputFlag() {
			return appInputFlag;
		}
		public void setAppInputFlag(String appInputFlag) {
			this.appInputFlag = appInputFlag;
		}
		public String getIfSuspectCheat() {
			return ifSuspectCheat;
		}
		public void setIfSuspectCheat(String ifSuspectCheat) {
			this.ifSuspectCheat = ifSuspectCheat;
		}
		public String getChannelCd() {
			return channelCd;
		}
		public void setChannelCd(String channelCd) {
			this.channelCd = channelCd;
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
		public List<Map<String, Object>> getLoanCoreStateList() {
			return loanCoreStateList;
		}
		public void setLoanCoreStateList(List<Map<String, Object>> loanCoreStateList) {
			this.loanCoreStateList = loanCoreStateList;
		}
		public String getPactMoney() {
			return pactMoney;
		}
		public void setPactMoney(String pactMoney) {
			this.pactMoney = pactMoney;
		}
		public Long getVersion() {
			return version;
		}
		public void setVersion(Long version) {
			this.version = version;
		}
		public String getIfPreferentialUser() {
			return ifPreferentialUser;
		}
		public void setIfPreferentialUser(String ifPreferentialUser) {
			this.ifPreferentialUser = ifPreferentialUser;
		}
	
	

}
