package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqQueryLoanLogVO</p>
 * <p>Description:查询借款日志</p>
 * @uthor YM10159
 * @date 2017年3月28日 下午2:30:56
 */
public class ReqQueryLoanLogVO extends Request{

	private static final long serialVersionUID = -2188450519756557865L;

	public ReqQueryLoanLogVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqQueryLoanLogVO(){}
	
	/**
	 * 借款编号
	 */
	@NotEmpty(message="借款编号不能为空")
	private String loanNo;
	
	/**
	 * 操作人工号
	 */
	@NotEmpty(message="操作人工号不能为空")
	private String serviceCode;
	/**
	 * 操作人姓名
	 */
	@NotEmpty(message="操作人姓名不能为空")
	private String serviceName;
	/**
	 * 操作ip
	 */
	@NotEmpty(message="操作ip不能为空")
	private String ip;

	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	
}
