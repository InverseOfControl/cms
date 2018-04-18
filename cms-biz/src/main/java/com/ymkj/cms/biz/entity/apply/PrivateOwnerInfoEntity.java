package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class PrivateOwnerInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	@NotNull(message = "000001,成立时间不能为空")  	 
	private Date setupDate ; //成立时间
	@NotNull(message = "000001,占股比例/%不能为空")  	 
	private Double sharesScale ; //占股比例/%
	@NotNull(message = "000001,注册资本/元不能为空")  	 
	private BigDecimal registerFunds; //注册资本/元
	@NotNull(message = "000001,私营企业类型%不能为空")  	 
	private String priEnterpriseType ; //私营企业类型
//	@NotNull(message = "000001,经营场所不能为空")  	 
	private String businessPlace; //经营场所
	private BigDecimal monthRent ; //月租金/元
//	@NotNull(message = "000001,员工人数/人不能为空")  	 
	private Integer employeeNum; //员工人数/人
//	@NotNull(message = "000001,企业净利润率/%不能为空")  	 
	private Double enterpriseRate ; //企业净利润率/%		 
	private String sharesName; //股东姓名(除客户外最大股东)
	private String sharesIdNo ; //	股东身份证
	@NotNull(message = "000001,每月净利率额/万元不能为空")  	 
	private BigDecimal monthAmt; //每月净利率额/万元
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
	public String getSharesIdNo() {
		return sharesIdNo;
	}
	public void setSharesIdNo(String sharesIdNo) {
		this.sharesIdNo = sharesIdNo;
	}
	public BigDecimal getMonthAmt() {
		return monthAmt;
	}
	public void setMonthAmt(BigDecimal monthAmt) {
		this.monthAmt = monthAmt;
	}
	 

}
