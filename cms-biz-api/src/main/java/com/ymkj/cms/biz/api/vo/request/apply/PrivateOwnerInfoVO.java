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
public class PrivateOwnerInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	
	private Date setupDate; // 成立时间	
	private Double sharesScale; // 占股比例/%
	private BigDecimal registerFunds; //注册资本/万元
	private String priEnterpriseType; //私营企业类型
	private String businessPlace; // 经营场所
	
	private BigDecimal monthRent; //	月租金/元
	private Integer employeeNum; //员工人数/人	
	private Double enterpriseRate; // 企业净利润率/%
		
	private String sharesName; //	股东姓名（除客户外最大股东）
	private String sharesIdNO; //股东身份证
	private BigDecimal monthAmt; // 每月净利润率/万元
	 
	public Date getSetupDate() {
		return setupDate;
	}
	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}
	public Double getSharesScale() {
		return sharesScale;
	}
	public void setSharesScale(Double sharesScale) {
		this.sharesScale = sharesScale;
	}
	public BigDecimal getRegisterFunds() {
		return registerFunds;
	}
	public void setRegisterFunds(BigDecimal registerFunds) {
		this.registerFunds = registerFunds;
	}
	public String getPriEnterpriseType() {
		return priEnterpriseType;
	}
	public void setPriEnterpriseType(String priEnterpriseType) {
		this.priEnterpriseType = priEnterpriseType;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public BigDecimal getMonthRent() {
		return monthRent;
	}
	public void setMonthRent(BigDecimal monthRent) {
		this.monthRent = monthRent;
	}
	public Integer getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}
	public Double getEnterpriseRate() {
		return enterpriseRate;
	}
	public void setEnterpriseRate(Double enterpriseRate) {
		this.enterpriseRate = enterpriseRate;
	}
	public String getSharesName() {
		return sharesName;
	}
	public void setSharesName(String sharesName) {
		this.sharesName = sharesName;
	}
	public String getSharesIdNO() {
		return sharesIdNO;
	}
	public void setSharesIdNO(String sharesIdNO) {
		this.sharesIdNO = sharesIdNO;
	}
	public BigDecimal getMonthAmt() {
		return monthAmt;
	}
	public void setMonthAmt(BigDecimal monthAmt) {
		this.monthAmt = monthAmt;
	}
				
			
			
			
		
		
			
			
			
 
}
