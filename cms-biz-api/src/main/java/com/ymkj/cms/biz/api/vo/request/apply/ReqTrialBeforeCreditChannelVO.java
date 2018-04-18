package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqTrialBeforeCreditChannelVO</p>
 * <p>Description:贷前试算-获取渠道请求实体</p>
 * @uthor YM10159
 * @date 2017年3月16日 上午9:36:45
 */
public class ReqTrialBeforeCreditChannelVO extends Request{

	private static final long serialVersionUID = 3280415208395537802L;

	public ReqTrialBeforeCreditChannelVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqTrialBeforeCreditChannelVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 申请产品code
	 */
	@NotEmpty(message="申请产品code不能为空")
	private String productCode;
	/**
	 * 申请期限
	 */
	private int applyTerm;
	/**
	 * 申请额度
	 */
	private BigDecimal applyLmt;
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
	
	private String isCanPreferential; //是否配置  0 是  1 否

	private Long orgId; //机构id
	
	private List<Long> orgIdList; //机构id集合
	

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
	public int getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(int applyTerm) {
		this.applyTerm = applyTerm;
	}
	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
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

	public String getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(String isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<Long> getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(List<Long> orgIdList) {
		this.orgIdList = orgIdList;
	}

	
}
