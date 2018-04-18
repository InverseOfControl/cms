package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqLoanTrialVO</p>
 * <p>Description:贷前试算请求对象</p>
 * @uthor YM10159
 * @date 2017年3月7日 下午5:22:18
 */
public class ReqTrialBeforeCreditVO extends Request{

	private static final long serialVersionUID = 7116962623972817901L;
	
	public ReqTrialBeforeCreditVO(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	/**
	 * 申请产品code
	 */
	@NotEmpty(message="申请产品code不能为空")
	private String productCode;
	/**
	 * 申请金额
	 */
	private BigDecimal applyLmt;
	/**
	 * 申请期限
	 */
	private int applyTerm;
	/**
	 * 预计首次还款日
	 */
	@NotEmpty(message="预计首次还款日不能为空")
	private String firstRepaymentDate;
	/**
	 * 渠道code
	 */
	@NotEmpty(message="渠道code不能为空")
	private String channelCode;
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
	
	
	@NotEmpty(message="是否优惠费率用户不能为空")
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否

	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}
	public int getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(int applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getFirstRepaymentDate() {
		return firstRepaymentDate;
	}
	public void setFirstRepaymentDate(String firstRepaymentDate) {
		this.firstRepaymentDate = firstRepaymentDate;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
