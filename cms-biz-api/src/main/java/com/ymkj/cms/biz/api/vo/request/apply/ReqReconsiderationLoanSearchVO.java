package com.ymkj.cms.biz.api.vo.request.apply;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqReconsiderationLoanSearchVO</p>
 * <p>Description:复议查询接口请求对象</p>
 * @uthor YM10159
 * @date 2017年3月3日 上午10:23:23
 */
public class ReqReconsiderationLoanSearchVO extends Request{
	
	private static final long serialVersionUID = 2616430633532416594L;
	
	public ReqReconsiderationLoanSearchVO(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 代办任务和已完成任务标识
	 */
	@NotEmpty(message="代办任务和已完成任务标识不能为空")
	private String agencyOrComplete;	
	/**
	 * 已提交任务和未提交任务标识
	 */
	private String submittedOrUnsubmitted;
	/**
	 * 操作客服工号
	 */
	@NotEmpty(message = "操作客服工号不能为空")
	private String serviceCode;
	/**
	 * 操作客服姓名
	 */
	@NotEmpty(message="操作客服姓名不能为空")
	private String serviceName;
	/**
	 * 操作ip
	 */
	@NotEmpty(message="操作ip不能为空")
	private String ip;
	/**
	 * 当前页数
	 */
	@Min(1)
	private int pageNum;
	/**
	 * 分页条数
	 */
	@Min(1)
	private int pageSize;

	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getAgencyOrComplete() {
		return agencyOrComplete;
	}
	public void setAgencyOrComplete(String agencyOrComplete) {
		this.agencyOrComplete = agencyOrComplete;
	}
	public String getSubmittedOrUnsubmitted() {
		return submittedOrUnsubmitted;
	}
	public void setSubmittedOrUnsubmitted(String submittedOrUnsubmitted) {
		this.submittedOrUnsubmitted = submittedOrUnsubmitted;
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
}
