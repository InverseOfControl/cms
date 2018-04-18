package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqCsUpdVO extends Request{

	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = -705253753804344853L;

	private String loanNo;//借款编号
	private String status;//借款状态
	private String rtfNodeStatus;//流程节点状态
	private String checkNodeStatus;//复核节点状态
	private String accLmt;//审批额度
	private String accTime;//审批期限
	private String accDate;//审批日期
	private String cSPersonCode;//初审人员code
	private String cSPersonName;//初审人员名称
	private String complexPersonCode;//复核人员code
	private String firstLevelReasons;//一级原因
	private String twoLevelReasons;//二级原因
	private String firstLevelReasonCode;//一级原因Code
	private String twoLevelReasonCode;//二级原因Code
	private String operatorCode;//操作人工号
	private String allotDate;//初审分配时间
	private String blackList;//黑名单Id
	private int version;//版本号
	private String operatorIP;//操作人IP
	private String remark;//备注
	private String firstSubZsDate;//初审提交终审首次时间
	private String ifNewLoanNo;//是否新生件 0：不是；1：是
	private String ifCreditRecode;//有无信用记录
	private String amoutIncome;//收入证明金额
	private String productCd;//产品编号
	
	private Long flag;//操作
	
	//华征报告相关字段
	private String name;
	private String idNo;
	private String cellphone;
	private String cellPhoneSec;
	private String longOnlineId;
	private String realNameAuthId;
	
	public String getcSPersonName() {
		return cSPersonName;
	}
	public void setcSPersonName(String cSPersonName) {
		this.cSPersonName = cSPersonName;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public String getIfCreditRecode() {
		return ifCreditRecode;
	}
	public void setIfCreditRecode(String ifCreditRecode) {
		this.ifCreditRecode = ifCreditRecode;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getCheckNodeStatus() {
		return checkNodeStatus;
	}
	public void setCheckNodeStatus(String checkNodeStatus) {
		this.checkNodeStatus = checkNodeStatus;
	}
	public String getComplexPersonCode() {
		return complexPersonCode;
	}
	public void setComplexPersonCode(String complexPersonCode) {
		this.complexPersonCode = complexPersonCode;
	}
	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}
	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}
	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}
	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
	}
	public String getFirstLevelReasonCode() {
		return firstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReasonCode() {
		return twoLevelReasonCode;
	}
	public void setTwoLevelReasonCode(String twoLevelReasonCode) {
		this.twoLevelReasonCode = twoLevelReasonCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getAllotDate() {
		return allotDate;
	}
	public void setAllotDate(String allotDate) {
		this.allotDate = allotDate;
	}
	public String getBlackList() {
		return blackList;
	}
	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFirstSubZsDate() {
		return firstSubZsDate;
	}
	public void setFirstSubZsDate(String firstSubZsDate) {
		this.firstSubZsDate = firstSubZsDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getAccTime() {
		return accTime;
	}
	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getcSPersonCode() {
		return cSPersonCode;
	}
	public void setcSPersonCode(String cSPersonCode) {
		this.cSPersonCode = cSPersonCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
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
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getCellPhoneSec() {
		return cellPhoneSec;
	}
	public void setCellPhoneSec(String cellPhoneSec) {
		this.cellPhoneSec = cellPhoneSec;
	}
	public String getLongOnlineId() {
		return longOnlineId;
	}
	public void setLongOnlineId(String longOnlineId) {
		this.longOnlineId = longOnlineId;
	}
	public String getRealNameAuthId() {
		return realNameAuthId;
	}
	public void setRealNameAuthId(String realNameAuthId) {
		this.realNameAuthId = realNameAuthId;
	}
}
