package com.ymkj.cms.biz.entity.master;

public class BMSContractChange extends BMSProductBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7765231153875293185L;
	
	/**
	 * LOAN_BASE_ID
	 */
	private Integer id;

	/**
	 * 借款编号
	 */
	private String loanNo;
	
	/**
	 * 流程状态
	 */
	private String rtfState;
	
	/**
	 * 流程状态，节点子
	 */
	private String rtfNodeState;
	
	/**
	 * 客户姓名
	 */
	private String personName;
	
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道code
	 */
	private String channelCode;
	
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品code
	 */
	private String productCode;
	
	/**
	 * 录单网点
	 */
	private String owningBranch;
	/**
	 * 录单网点Id
	 */
	private Long owningBranchId;
	
	/**
	 * 签约网点Id
	 */
	private String contractBranchId;
	
	/**
	 * 签约网点
	 */
	private String contractBranch;
	
	/**
	 * 签约客服Code
	 */
	private String signCode;
	
	/**
	 * 签约客服
	 */
	private String signName;
	
	/**
	 * 网点渠道产品是否可用标识
	 */
	private Integer isDisabled;
	/**
	 * 是否加急   0不加急   1加急
	 */
	private String ifPri;
	/**
	 *  APP进件标识     app_input:进件
	 */
	private String appInputFlag;
	/**
	 * 是否疑似欺诈  Y 是  N否
	 */
	private String ifSuspectCheat;
	/**
	 * 标的锁定
	 */
	private String lockTarget;
	/**
	 * 是否优惠费率用户  Y 是  N 否
	 */
	private String ifPreferentialUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getRtfNodeState() {
		return rtfNodeState;
	}

	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}

	public String getIfPri() {
		return ifPri;
	}

	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
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

	public String getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}


	
}
