package com.ymkj.cms.biz.api.vo.request.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqLoanVo extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = -493158853165640685L;

	private Long id; // LOAN_BASE_ID
	
	private String productCd; //申请产品
	private String productId; //申请产品
	private String loanNo; //借款编号
	private String appNo; //借款编号
	private String idNo;//身份证号
	private String name; //客户姓名
	private String contractBranchId; //签约网点ID
	private String contractBranch;
	private String owningBranchId; //进件营业部ID
	private BigDecimal contractLmt; // 签约期限
	private Integer contractTrem; // 签约期限
	private String channelId; //渠道Id
	private String channelCd; //渠道Code
	private String loanId;
	
	private String confirmDate; //合同确认时间
	private String confirmDate2; //合同确认时间2
	private String financeAuditDate; //放款审核时间
	private String financeAuditDate2;//放款审核时间2
	private String loanDate; //放款审核时间
	private String loanDate2;//放款审核时间2
	
	private String status ; //申请件状态
	private String rtfState ; //流程状态
	
	private String auditor;//放款审批人
	
	private String state ; //核心借款状态
	
	private String taskName; //节点名称
	
	private String batchNum; //批次号
	
	private String taskPath;//任务线路
	
	private String version; //版本号
	
	private String rtfNodeState; //xiao流程状态
	
	private String userCode;
	
	private Long taskId;//当前任务ID
	
	private String applyInputFlag;
	
	private String firstLevleReasons; // 一级原因code
	private String twoLevleReasons; // 二级原因code
	private String firstLevleReasonsCode;//一级原因code
	private String twoLevleReasonsCode; // 二级原因code
	
	private String remark;
	
	
	private String queryCount;
	
	
	private String orgAuditState;


	/**
	 * 更新借款状态
	 * 必填 ：LOAN_BASE_ID(id) 和 任务TASKID(taskId)和 债权id LOAN_NO(loanNo)
	 * 
	 */
	private List<ReqLoanVo> loanList ; //放款推送时参数
	
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
	
	
	
	@NotEmpty(message="操作人工号不能为空")
    private  String serviceCode;	//操作客服工号
	@NotEmpty(message="操作人姓名不能为空")
    private  String serviceName;	//操作客服姓名
	@NotEmpty(message="操作ip不能为空")
    private  String ip	;           //操作ip
	private int pageNum;	//当前页数
	private int pageSize;	//分页条数

	private int rows;// 行数
	private int page;// 页数

	
   public ReqLoanVo() {
	   super();
   	}
   
   
   
   public String getState() {
	return state;
}



public String getAppNo() {
	return appNo;
}



public void setAppNo(String appNo) {
	this.appNo = appNo;
}



public String getUserCode() {
	return userCode;
}



public void setUserCode(String userCode) {
	this.userCode = userCode;
}



public String getBatchNum() {
	return batchNum;
}



public void setBatchNum(String batchNum) {
	this.batchNum = batchNum;
}



public String getRtfNodeState() {
	return rtfNodeState;
}



public void setRtfNodeState(String rtfNodeState) {
	this.rtfNodeState = rtfNodeState;
}



public void setState(String state) {
	this.state = state;
}



public String getTaskPath() {
	return taskPath;
}



public void setTaskPath(String taskPath) {
	this.taskPath = taskPath;
}



public ReqLoanVo(String sysCode) {
		super.setSysCode(sysCode);
	  }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
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

	public String getStatus() {
		return status;
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



	
	


	public List<ReqLoanVo> getLoanList() {
		return loanList;
	}



	public void setLoanList(List<ReqLoanVo> loanList) {
		this.loanList = loanList;
	}



	public String getAuditor() {
		return auditor;
	}



	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}







	public String getLoanId() {
		return loanId;
	}



	public void reqLoanVo2(String loanId) {
		this.loanId = loanId;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}
	

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}






	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getChannelId() {
		return channelId;
	}



	public void setChannelId(String channelId) {
		this.channelId = channelId;
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






	public String getTaskName() {
		return taskName;
	}



	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public String getOperationType() {
		return operationType;
	}



	public void setOperationType(String operationType) {
		this.operationType = operationType;
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



	public String getOperationModule() {
		return operationModule;
	}



	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}



	public String getChannelCd() {
		return channelCd;
	}



	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
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

	
	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getOwningBranchId() {
		return owningBranchId;
	}



	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
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



	public String getApplyInputFlag() {
		return applyInputFlag;
	}



	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}



	public String getQueryCount() {
		return queryCount;
	}



	public void setQueryCount(String queryCount) {
		this.queryCount = queryCount;
	}



	public String getOrgAuditState() {
		return orgAuditState;
	}



	public void setOrgAuditState(String orgAuditState) {
		this.orgAuditState = orgAuditState;
	}






	
	
}
