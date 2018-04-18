package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ApplyInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	private Long loanBaseId	; //申请信息Id
	private Long loanProductId	; //申请产品Id
	private Long loanExtId	; //借款扩展表Id
	
	private String loanNo;	 //借款编号
	private String productCd	; //申请产品
	private String productName; //产品名称
	private BigDecimal applyLmt	; //申请金额
	private Integer applyTerm	; //	申请期限
	private String name;	 //	姓名
	private String idNo	; //	身份证
	private	String	contractBranch;	//签约营业部
	private Long contractBranchId;	//签约营业部ID	
	private Integer ifPri; 			//是否加急	0不加急 	1加急
	private String customerSource; 	//客户来源
	
	private String creditApplication;	 //	贷款用途
	
	private String branchManagerCode;	 //客户经理
	private String branchManagerName;	 //客户经理
	
	private String remark;	 //备注
	
	private String applyType;//申请类型	 NEW 新申请    TOPUP 追加贷款	RELOAN 结清再贷
	private String applyInputFlag;	//申请件渠道标识 	applyInput 普通进件 directApplyInput 直通车进件
	private String appInputFlag;	//APP进件标识	app_input 代表app进件
	
	private Date appinputDate; //app录入时间
	
	
	//为对接益博瑞接口新增字段开始  2017-06-27 hcr 新增
		private Long owningBranchId;//录单门店ID
		private Long enterBranchId;//进件门店（复核保存）
		private String enterBranch;//进件门店（复核保存）
		
		private String nameAndidNoIsBlack;//姓名身份证是否匹配黑名单
		
		private String enterBranchCity;//进件营业部所属城市
		private String enterBranchSubsection;//进件营业部所属分部
		private String enterBranchArea;//进件营业部所属区域
		
		private Date commitDate;//提交时间
		private float amoutIncome;//收入证明
		private String ifCreditRecord;//有无信用记录
		
		private String antiFraudScore;//  反欺诈评分
		private String antiFraudWarning;//   反欺诈预警
		private String antiRiskRate;//  欺诈风险评估
		private String lastTimeStatus;//最近一次借款申请状态
		private String rtfNodeState;//三级节点状态 这个现在没用到了换成下面的流程状态了
		private String rtfState ; //流程状态
		
		private String initProductCd;
		
		private String initProductName;
		//为对接益博瑞接口新增字段结束
	
	public Date getAppinputDate() {
		return appinputDate;
	}
	public void setAppinputDate(Date appinputDate) {
		this.appinputDate = appinputDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public Integer getIfPri() {
		return ifPri;
	}
	public void setIfPri(Integer ifPri) {
		this.ifPri = ifPri;
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
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public Long getLoanProductId() {
		return loanProductId;
	}
	public void setLoanProductId(Long loanProductId) {
		this.loanProductId = loanProductId;
	}
	public Long getLoanExtId() {
		return loanExtId;
	}
	public void setLoanExtId(Long loanExtId) {
		this.loanExtId = loanExtId;
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
	public String getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	 
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getBranchManagerCode() {
		return branchManagerCode;
	}
	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

			
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public Long getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(Long contractBranchId) {
		this.contractBranchId = contractBranchId;
	}	

	
	
	public ApplyInfoVO(){
		
	}
	public ApplyInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public Long getEnterBranchId() {
		return enterBranchId;
	}
	public void setEnterBranchId(Long enterBranchId) {
		this.enterBranchId = enterBranchId;
	}
	public String getEnterBranch() {
		return enterBranch;
	}
	public void setEnterBranch(String enterBranch) {
		this.enterBranch = enterBranch;
	}
	public String getNameAndidNoIsBlack() {
		return nameAndidNoIsBlack;
	}
	public void setNameAndidNoIsBlack(String nameAndidNoIsBlack) {
		this.nameAndidNoIsBlack = nameAndidNoIsBlack;
	}
	public String getEnterBranchCity() {
		return enterBranchCity;
	}
	public void setEnterBranchCity(String enterBranchCity) {
		this.enterBranchCity = enterBranchCity;
	}
	public String getEnterBranchSubsection() {
		return enterBranchSubsection;
	}
	public void setEnterBranchSubsection(String enterBranchSubsection) {
		this.enterBranchSubsection = enterBranchSubsection;
	}
	public String getEnterBranchArea() {
		return enterBranchArea;
	}
	public void setEnterBranchArea(String enterBranchArea) {
		this.enterBranchArea = enterBranchArea;
	}
	public Date getCommitDate() {
		return commitDate;
	}
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}
	public float getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(float amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getAntiFraudScore() {
		return antiFraudScore;
	}
	public void setAntiFraudScore(String antiFraudScore) {
		this.antiFraudScore = antiFraudScore;
	}
	public String getAntiFraudWarning() {
		return antiFraudWarning;
	}
	public void setAntiFraudWarning(String antiFraudWarning) {
		this.antiFraudWarning = antiFraudWarning;
	}
	public String getAntiRiskRate() {
		return antiRiskRate;
	}
	public void setAntiRiskRate(String antiRiskRate) {
		this.antiRiskRate = antiRiskRate;
	}
	public String getLastTimeStatus() {
		return lastTimeStatus;
	}
	public void setLastTimeStatus(String lastTimeStatus) {
		this.lastTimeStatus = lastTimeStatus;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getInitProductCd() {
		return initProductCd;
	}
	public void setInitProductCd(String initProductCd) {
		this.initProductCd = initProductCd;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	
	 
}
