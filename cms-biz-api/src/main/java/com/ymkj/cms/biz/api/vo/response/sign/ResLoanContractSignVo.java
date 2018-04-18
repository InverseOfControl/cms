package com.ymkj.cms.biz.api.vo.response.sign;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;


public class ResLoanContractSignVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 359363705040032652L;

    private Long id; // LOAN_BASE_ID
    private Long taskId; //当前任务ID
    private Long loanId;
    private String loanNo;//借款编号
    private String name;
    private String idNo;
    private String channelId;
    private String productId;
    private String channelCd;
    private String productCd;
    private String channelName;
    private String productName;
    private BigDecimal accLmt;
    private Integer accTerm;
    private String reportId;
    private BigDecimal contractLmt;       // '合同金额',
    private Integer contractTrem;        // '合同期限',
    private String owningBranch;
    private String owningBranchId;
    private String contractBranch;
    private String contractBranchId;
    private String signName;
    private String signDate;
    private String signEndDate;
    private String signEndDate2;
    private String confirmDate;
    private String confirmEndDate;
    private String bankPhone;  //银行预留手机号
    private String applyBankCardNo;  //银行卡号
    private String applyBankBranch;  //开户行
    private String applyBankName;  //所属银行
    private String applyBankBranchId;  //开户行
    private String applyBankNameId;  //所属银行
    private Long version;
    private String taskName;
    private Long pversion;
    private String oprateFlag;//是否可以操作标识
    private String appInputFlag;//是否app进件
    private String applyInputFlag;
    private String signOprateType; //签约处理类型
    private String confirmOprateType; //签约处理类型
    private Integer ifEnd; //是否处理完成件
    private Integer ifSuspectCheat; //是否疑似欺诈
    private Integer ifLoanAgain; //是否结清再贷
    private Integer ifRefuse; //是否拒绝
    private Double applyRate; //费率
    private Integer ifPatchBolt; //是否补件
    private Integer ifPri; //是否加急
    private Integer ifUrgent; //加急等级
    private String state;
    private String userCode;
    private String serviceCode;
    private String rtfNodeState; //xiao流程状态
    private String managerName; //合同确认经理姓名
    private String cellphone; //手机1
    private String cellphoneSec; //手机2
    private BigDecimal adjustBase;
    private Date  patchBoltTime;
    
    //陆金所专用字段
    //包银的状态值
    private String busNumber;//包银业务流水申请号
    private String byState;//包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功
    private String byRefusalResult;//包银黑名单拒绝原因
    private String windControlDate;//授信信息推送时间
    private String windControlResult;//风控结果
    private String auditingState;//'人工审核状态 0审核中 1通过  2拒绝 3是图像资料待上传 4是补件';
    
    
	private BigDecimal applyLmt  ; // 申请额度
	private String	applyType;		  //申请类型
	private String oprateType;
	
	private String lujsName;//陆金所注册用户名
	private String lujsLoanReqId;//陆金所id
	private String lujsAntiFraud;//没有调用是空；陆金所反欺诈调用状态：0反欺诈拒绝，1等待结果返回，2反欺诈通过
	private Date idLastDate;//证件到期日
	private String lujsApplyNo;
	
	private Date auditSubmitTime; //信审提交时间
	/**
	 * 锁定Y，退回N
	 */
	private String lockTarget;

	private String orgAuditState;
	
	/**
	 * 核心状态集合
	 */
	private List<Map<String,Object>> loanCoreStateList;
	
	/**
	 * 出错信息：目前预留着，备用
	 */
	private String errorMessage;
	
	private String pactMoney;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
    
    

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getByState() {
        return byState;
    }

    public void setByState(String byState) {
        this.byState = byState;
    }

    public String getByRefusalResult() {
        return byRefusalResult;
    }

    public void setByRefusalResult(String byRefusalResult) {
        this.byRefusalResult = byRefusalResult;
    }

    public String getWindControlDate() {
        return windControlDate;
    }

    public void setWindControlDate(String windControlDate) {
        this.windControlDate = windControlDate;
    }

    public String getWindControlResult() {
        return windControlResult;
    }

    public void setWindControlResult(String windControlResult) {
        this.windControlResult = windControlResult;
    }

    public String getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(String auditingState) {
        this.auditingState = auditingState;
    }

    /**
     * 合同类型code
     */
    private String contractTypeCode;
    /**
     * 合同类型code
     */
    private String contractTypeName;

    private List<ResLoanVo> failList;
    private List<ResLoanVo> successList;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }


    public String getApplyBankBranchId() {
        return applyBankBranchId;
    }

    public void setApplyBankBranchId(String applyBankBranchId) {
        this.applyBankBranchId = applyBankBranchId;
    }

    public String getApplyBankNameId() {
        return applyBankNameId;
    }

    public void setApplyBankNameId(String applyBankNameId) {
        this.applyBankNameId = applyBankNameId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRtfNodeState() {
        return rtfNodeState;
    }

    public void setRtfNodeState(String rtfNodeState) {
        this.rtfNodeState = rtfNodeState;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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

    public String getOwningBranch() {
        return owningBranch;
    }

    public void setOwningBranch(String owningBranch) {
        this.owningBranch = owningBranch;
    }

    public String getContractBranch() {
        return contractBranch;
    }

    public void setContractBranch(String contractBranch) {
        this.contractBranch = contractBranch;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(String signEndDate) {
        this.signEndDate = signEndDate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getConfirmEndDate() {
        return confirmEndDate;
    }

    public void setConfirmEndDate(String confirmEndDate) {
        this.confirmEndDate = confirmEndDate;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getOprateFlag() {
        return oprateFlag;
    }

    public void setOprateFlag(String oprateFlag) {
        this.oprateFlag = oprateFlag;
    }

    public String getSignOprateType() {
        return signOprateType;
    }

    public void setSignOprateType(String signOprateType) {
        this.signOprateType = signOprateType;
    }

    public String getConfirmOprateType() {
        return confirmOprateType;
    }

    public void setConfirmOprateType(String confirmOprateType) {
        this.confirmOprateType = confirmOprateType;
    }

    public Integer getIfEnd() {
        return ifEnd;
    }

    public void setIfEnd(Integer ifEnd) {
        this.ifEnd = ifEnd;
    }

    public Integer getIfSuspectCheat() {
        return ifSuspectCheat;
    }

    public void setIfSuspectCheat(Integer ifSuspectCheat) {
        this.ifSuspectCheat = ifSuspectCheat;
    }

    public Integer getIfLoanAgain() {
        return ifLoanAgain;
    }

    public void setIfLoanAgain(Integer ifLoanAgain) {
        this.ifLoanAgain = ifLoanAgain;
    }

    public Integer getIfRefuse() {
        return ifRefuse;
    }

    public void setIfRefuse(Integer ifRefuse) {
        this.ifRefuse = ifRefuse;
    }

    public Double getApplyRate() {
        return applyRate;
    }

    public void setApplyRate(Double applyRate) {
        this.applyRate = applyRate;
    }

    public Integer getIfPatchBolt() {
        return ifPatchBolt;
    }

    public void setIfPatchBolt(Integer ifPatchBolt) {
        this.ifPatchBolt = ifPatchBolt;
    }

    public Integer getIfUrgent() {
        return ifUrgent;
    }

    public void setIfUrgent(Integer ifUrgent) {
        this.ifUrgent = ifUrgent;
    }

    public Integer getIfPri() {
        return ifPri;
    }

    public void setIfPri(Integer ifPri) {
        this.ifPri = ifPri;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getOwningBranchId() {
        return owningBranchId;
    }

    public void setOwningBranchId(String owningBranchId) {
        this.owningBranchId = owningBranchId;
    }

    public String getContractBranchId() {
        return contractBranchId;
    }

    public void setContractBranchId(String contractBranchId) {
        this.contractBranchId = contractBranchId;
    }

    public String getAppInputFlag() {
        return appInputFlag;
    }

    public void setAppInputFlag(String appInputFlag) {
        this.appInputFlag = appInputFlag;
    }

    public String getApplyInputFlag() {
        return applyInputFlag;
    }

    public void setApplyInputFlag(String applyInputFlag) {
        this.applyInputFlag = applyInputFlag;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCellphoneSec() {
        return cellphoneSec;
    }

    public void setCellphoneSec(String cellphoneSec) {
        this.cellphoneSec = cellphoneSec;
    }

    public BigDecimal getAdjustBase() {
        return adjustBase;
    }

    public void setAdjustBase(BigDecimal adjustBase) {
        this.adjustBase = adjustBase;
    }

    public String getContractTypeCode() {
        return contractTypeCode;
    }

    public void setContractTypeCode(String contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
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

	public BigDecimal getApplyLmt() {
		return applyLmt;
	}

	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getOprateType() {
		return oprateType;
	}

	public void setOprateType(String oprateType) {
		this.oprateType = oprateType;
	}

	public List<Map<String, Object>> getLoanCoreStateList() {
		return loanCoreStateList;
	}

	public void setLoanCoreStateList(List<Map<String, Object>> loanCoreStateList) {
		this.loanCoreStateList = loanCoreStateList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}

	public String getLujsName() {
		return lujsName;
	}

	public void setLujsName(String lujsName) {
		this.lujsName = lujsName;
	}

	public String getLujsLoanReqId() {
		return lujsLoanReqId;
	}

	public void setLujsLoanReqId(String lujsLoanReqId) {
		this.lujsLoanReqId = lujsLoanReqId;
	}

	public Date getIdLastDate() {
		return idLastDate;
	}

	public void setIdLastDate(Date idLastDate) {
		this.idLastDate = idLastDate;
	}

	public String getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}

	public String getLujsAntiFraud() {
		return lujsAntiFraud;
	}

	public void setLujsAntiFraud(String lujsAntiFraud) {
		this.lujsAntiFraud = lujsAntiFraud;
	}

	public Date getAuditSubmitTime() {
		return auditSubmitTime;
	}

	public void setAuditSubmitTime(Date auditSubmitTime) {
		this.auditSubmitTime = auditSubmitTime;
	}

	public String getLujsApplyNo() {
		return lujsApplyNo;
	}

	public void setLujsApplyNo(String lujsApplyNo) {
		this.lujsApplyNo = lujsApplyNo;
	}


	public Date getPatchBoltTime() {
		return patchBoltTime;
	}

	public void setPatchBoltTime(Date patchBoltTime) {
		this.patchBoltTime = patchBoltTime;
	}
	public String getOrgAuditState() {
		return orgAuditState;
	}

	public void setOrgAuditState(String orgAuditState) {
		this.orgAuditState = orgAuditState;
	}
	
}
