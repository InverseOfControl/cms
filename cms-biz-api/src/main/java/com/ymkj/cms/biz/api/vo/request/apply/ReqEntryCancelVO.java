package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqEntryCancelVO extends Request{
	/**	
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long loanBaseId; //借款ID
	private String loanNo;//借款编号
	private String firstLevelReason;//一级原因
	private String firstLevelReasonCode;//一级原因
	private String twoLevelReason;//二级原因
	private String twoLevelReasonCode;//二级原因
	private String optionModule;	//操作模块 2 录入修改 3录入复核
	private String remark;//备注
	
	private String serviceCode;//操作人工号;
	private String serviceName;//操作人姓名;
	private Long serviceId;//操作人id;

	private String ip;
	
	
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
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getFirstLevelReason() {
		return firstLevelReason;
	}
	public void setFirstLevelReason(String firstLevelReason) {
		this.firstLevelReason = firstLevelReason;
	}
	public String getTwoLevelReason() {
		return twoLevelReason;
	}
	public void setTwoLevelReason(String twoLevelReason) {
		this.twoLevelReason = twoLevelReason;
	}
	 
	public String getOptionModule() {
		return optionModule;
	}
	public void setOptionModule(String optionModule) {
		this.optionModule = optionModule;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ReqEntryCancelVO(){	
	}
	public ReqEntryCancelVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
