package com.ymkj.cms.biz.api.vo.request.sign;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;

import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 合同签约请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 * @author haowp
 *
 */
public class ReqLoanContractSignVo extends Request {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2331072370994591104L;

	private Long id; // LOAN_BASE_ID
	private String appNo; // 申请件编号
	private String loanNo;// 借款编号
	private String loanId;// 债权编号
	private String name;
	private BigDecimal applyLmt; // 申请额度
	private String productCd; // 申请产品
	private Integer applyTerm; // 申请期限

	private BigDecimal accLmt; // 申请额度
	private Integer accTerm; // 申请期限

	private Integer ifOldOrNewLogo; // 是否新老客户标识
	private Integer repayDate; // 还款日
	private Integer ifGrey; // 是否在灰名单中

	private String bankPhone; // 银行预留手机号

	private String applyBankCardNo; // 银行卡号

	private String applyBankBranch; // 支行
	private String applyBankName; // 所属银行

	private Integer applyBankBranchId; // 支行行id

	private Integer applyBankNameId; // 所属银行id

	private String contractNum;// 合同编号
	private String channelCd; // 渠道Code
	private Long channelId; // 渠道id
	private Long initProductCd; // 原产品

	private BigDecimal contractLmt; // 合同金额
	private Integer contractTrem; // 合同期限

	private String signCode;
	private String status; // 申请件状态
	private String rtfState; // 流程状态

	private String rtfNodeState; // xiao流程状态

	// private String userCode;
	private String oprateLevel;

	private String owningBranchId;

	private String postCode;

	private String state; // 核心状态

	private String managerCode;

	private String managerName;

	private String taskName;

	private Long version;

	private Long pversion;

	// 工作流相关
	private Long taskId; // 当前任务ID

	private String signEndDate;

	private String signEndDate2;
	
	private Integer ifNeedPatchBolt;


	private int pageNum; // 当前页数
	private int pageSize;
	private int rows;// 行数
	private int page;// 页数
	@NotEmpty(message = "操作人工号不能为空")
	private String serviceCode; // 操作客服工号
	@NotEmpty(message = "操作人姓名不能为空")
	private String serviceName; // 操作客服姓名
	@NotEmpty(message = "操作ip不能为空")
	private String ip; // 操作ip

	// 借款日志
	private String firstLevleReasons; // 一级原因code
	private String twoLevleReasons; // 二级原因code
	private String firstLevleReasonsCode;// 一级原因
	private String twoLevleReasonsCode; // 二级原因
	private String remark;

	private Long orgId;
	
	private BigDecimal transAmt; // 借款金额
	private Integer totalCnt;// 总期数
	private String  repayMethod;// 还款方式
	/**
	 * 合同类型code
	 */
	private String contractTypeCode;

	//包银
	private String verifyCode;// 验证码
	private String busNumber;//业务申请流水号
	private String idType;//证件类型
	private String idNo;//证件号码
	private String custName;//客户姓名
	private String operator;//操作人姓名
	private String jobNumber;//工号
	private String respcd;//包银响应码
	private String resptx;//包银响应结果
	private String patchType;//包银补件类型
	
	private List<ReqLoanVo> loanList;//业务申请流水号,或放款推送时参数

	private Date idLastDate;//证件到期日（陆金所）
	private String lujsName;//陆金所注册用户名
	private String lujsLoanReqId;//陆金所id
	

	
	private String productId; //申请产品
	private String contractBranchId; //签约网点ID
	private String contractBranch;
	
	private String confirmDate; //合同确认时间
	private String confirmDate2; //合同确认时间2
	private String financeAuditDate; //放款审核时间
	private String financeAuditDate2;//放款审核时间2
	private String loanDate; //放款审核时间
	private String loanDate2;//放款审核时间2
	
	
	private String auditor;//放款审批人
	
	
	private String batchNum; //批次号
	
	private String taskPath;//任务线路
	
	
	private String userCode;
	
	
	private String applyInputFlag;
	
	/**
	 * //批量通过时参数 
	 */
	private List<String> loanNos;
	private List<String> ids;
	private List<String> owningBranchIds;
	private List<String> channelCds;
	private List<String> productCds;
	private List<Integer> contractTrems;
	private List<BigDecimal> contractLmts;
	
    //日志
	private String operationType;
	private String operationModule;
	
	//陆金所人工审核
	private ReqLufax800001Vo reqLufax800001Vo;
	private String applyType; //01-新案件；02-补件
	
	/*
	 * 渠道接口版本号
	 */
	private String channelCdVersion;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private String isCanPreferential; //是否配置  0 是  1 否
	
	public String getRespcd() {
		return respcd;
	}

	public void setRespcd(String respcd) {
		this.respcd = respcd;
	}

	public String getResptx() {
		return resptx;
	}

	public void setResptx(String resptx) {
		this.resptx = resptx;
	}

	public String getPatchType() {
		return patchType;
	}

	public void setPatchType(String patchType) {
		this.patchType = patchType;
	}

	public ReqLoanContractSignVo() {
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}

	public ReqLoanContractSignVo(String sysCode) {
		super.setSysCode(sysCode);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRtfNodeState() {
		return rtfNodeState;
	}

	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public BigDecimal getApplyLmt() {
		return applyLmt;
	}

	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public Integer getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}

	public void setIfOldOrNewLogo(Integer ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}

	public Integer getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
	}

	public Integer getIfGrey() {
		return ifGrey;
	}

	public void setIfGrey(Integer ifGrey) {
		this.ifGrey = ifGrey;
	}

	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}

	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}

	public String getApplyBankBranch() {
		return applyBankBranch;
	}

	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}

	public String getApplyBankName() {
		return applyBankName;
	}

	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
	}

	public Long getInitProductCd() {
		return initProductCd;
	}

	public void setInitProductCd(Long initProductCd) {
		this.initProductCd = initProductCd;
	}

	public BigDecimal getContractLmt() {
		return contractLmt;
	}

	public void setContractLmt(BigDecimal contractLmt) {
		this.contractLmt = contractLmt;
	}

	public Integer getContractTrem() {
		return contractTrem;
	}

	public void setContractTrem(Integer contractTrem) {
		this.contractTrem = contractTrem;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getApplyBankBranchId() {
		return applyBankBranchId;
	}

	public void setApplyBankBranchId(Integer applyBankBranchId) {
		this.applyBankBranchId = applyBankBranchId;
	}

	public Integer getApplyBankNameId() {
		return applyBankNameId;
	}

	public void setApplyBankNameId(Integer applyBankNameId) {
		this.applyBankNameId = applyBankNameId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	/*
	 * public String getUserCode() { return userCode; }
	 * 
	 * 
	 * public void setUserCode(String userCode) { this.userCode = userCode; }
	 */

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignEndDate() {
		return signEndDate;
	}

	public void setSignEndDate(String signEndDate) {
		this.signEndDate = signEndDate;
	}

	public String getSignEndDate2() {
		return signEndDate2;
	}

	public void setSignEndDate2(String signEndDate2) {
		this.signEndDate2 = signEndDate2;
	}

	public Long getPversion() {
		return pversion;
	}

	public void setPversion(Long pversion) {
		this.pversion = pversion;
	}

	public String getManagerCode() {
		return managerCode;
	}
	

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public BigDecimal getAccLmt() {
		return accLmt;
	}

	public void setAccLmt(BigDecimal accLmt) {
		this.accLmt = accLmt;
	}

	public Integer getAccTerm() {
		return accTerm;
	}

	public void setAccTerm(Integer accTerm) {
		this.accTerm = accTerm;
	}

	public String getOprateLevel() {
		return oprateLevel;
	}

	public void setOprateLevel(String oprateLevel) {
		this.oprateLevel = oprateLevel;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getContractTypeCode() {
		return contractTypeCode;
	}

	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	public List<ReqLoanVo> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<ReqLoanVo> loanList) {
		this.loanList = loanList;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Date getIdLastDate() {
		return idLastDate;
	}

	public void setIdLastDate(Date idLastDate) {
		this.idLastDate = idLastDate;
	}

	public String getLujsName() {
		return lujsName;
	}

	public void setLujsName(String lujsName) {
		this.lujsName = lujsName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getConfirmDate2() {
		return confirmDate2;
	}

	public void setConfirmDate2(String confirmDate2) {
		this.confirmDate2 = confirmDate2;
	}

	public String getFinanceAuditDate() {
		return financeAuditDate;
	}

	public void setFinanceAuditDate(String financeAuditDate) {
		this.financeAuditDate = financeAuditDate;
	}

	public String getFinanceAuditDate2() {
		return financeAuditDate2;
	}

	public void setFinanceAuditDate2(String financeAuditDate2) {
		this.financeAuditDate2 = financeAuditDate2;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getLoanDate2() {
		return loanDate2;
	}

	public void setLoanDate2(String loanDate2) {
		this.loanDate2 = loanDate2;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getTaskPath() {
		return taskPath;
	}

	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getApplyInputFlag() {
		return applyInputFlag;
	}

	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}

	public List<String> getLoanNos() {
		return loanNos;
	}

	public void setLoanNos(List<String> loanNos) {
		this.loanNos = loanNos;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getOwningBranchIds() {
		return owningBranchIds;
	}

	public void setOwningBranchIds(List<String> owningBranchIds) {
		this.owningBranchIds = owningBranchIds;
	}

	public List<String> getChannelCds() {
		return channelCds;
	}

	public void setChannelCds(List<String> channelCds) {
		this.channelCds = channelCds;
	}

	public List<String> getProductCds() {
		return productCds;
	}

	public void setProductCds(List<String> productCds) {
		this.productCds = productCds;
	}

	public List<Integer> getContractTrems() {
		return contractTrems;
	}

	public void setContractTrems(List<Integer> contractTrems) {
		this.contractTrems = contractTrems;
	}

	public List<BigDecimal> getContractLmts() {
		return contractLmts;
	}

	public void setContractLmts(List<BigDecimal> contractLmts) {
		this.contractLmts = contractLmts;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public String getLujsLoanReqId() {
		return lujsLoanReqId;
	}

	public void setLujsLoanReqId(String lujsLoanReqId) {
		this.lujsLoanReqId = lujsLoanReqId;
	}

	public ReqLufax800001Vo getReqLufax800001Vo() {
		return reqLufax800001Vo;
	}

	public void setReqLufax800001Vo(ReqLufax800001Vo reqLufax800001Vo) {
		this.reqLufax800001Vo = reqLufax800001Vo;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getChannelCdVersion() {
		return channelCdVersion;
	}

	public void setChannelCdVersion(String channelCdVersion) {
		this.channelCdVersion = channelCdVersion;
	}

	public Integer getIfNeedPatchBolt() {
		return ifNeedPatchBolt;
	}

	public void setIfNeedPatchBolt(Integer ifNeedPatchBolt) {
		this.ifNeedPatchBolt = ifNeedPatchBolt;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public String getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(String isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}


	
	
}
