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
public class WorkInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private String corpName; // 单位名称
	private String cusWorkType; // 客户工作类型		
	private Long corpProvinceId; // 公司所在省ID
	private Long corpCityId; // 公司所在市ID
	private Long corpZoneId; // 	公司所在区/县ID
	
	private String corpProvince; // 公司所在省
	private String corpCity; // 公司所在市
	private String corpZone; // 	公司所在区/县
	
	private String corpAddress; //公司地址
	private String corpPostcode; //公司邮编
	private String businessNetWork; //工商网信息
	private String corpStructure; //公司性质
	private String corpType ; //	公司行业类别
	
	private String corpDepapment; // 	任职部门
	private String corpPost; // 	职务	 
	private String occupation; // 职业
	private String corpPhone; // 单电
	private String corpPhoneSec; // 单电2
	private Date corpStandFrom; // 入职时间
	private String corpPayWay; // 发薪方式
	private BigDecimal monthSalary; // 	单位月收入/元
	private BigDecimal otherIncome; // 其他月收入
	private BigDecimal totalMonthSalary; // 	月总收入/元	

	
	
	public String getCorpProvince() {
		return corpProvince;
	}
	public void setCorpProvince(String corpProvince) {
		this.corpProvince = corpProvince;
	}
	public String getCorpCity() {
		return corpCity;
	}
	public void setCorpCity(String corpCity) {
		this.corpCity = corpCity;
	}
	public String getCorpZone() {
		return corpZone;
	}
	public void setCorpZone(String corpZone) {
		this.corpZone = corpZone;
	}
	public enum ii{
		Y,n
	}
	
	
	public String getCorpAddress() {
		return corpAddress;
	}
	public void setCorpAddress(String corpAddress) {
		this.corpAddress = corpAddress;
	}
	public String getCorpPostcode() {
		return corpPostcode;
	}
	public void setCorpPostcode(String corpPostcode) {
		this.corpPostcode = corpPostcode;
	}
	public String getBusinessNetWork() {
		return businessNetWork;
	}
	public void setBusinessNetWork(String businessNetWork) {
		this.businessNetWork = businessNetWork;
	}
	public String getCorpStructure() {
		return corpStructure;
	}
	public void setCorpStructure(String corpStructure) {
		this.corpStructure = corpStructure;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCusWorkType() {
		return cusWorkType;
	}
	public void setCusWorkType(String cusWorkType) {
		this.cusWorkType = cusWorkType;
	}
	public Long getCorpProvinceId() {
		return corpProvinceId;
	}
	public void setCorpProvinceId(Long corpProvinceId) {
		this.corpProvinceId = corpProvinceId;
	}
	public Long getCorpCityId() {
		return corpCityId;
	}
	public void setCorpCityId(Long corpCityId) {
		this.corpCityId = corpCityId;
	}
	public Long getCorpZoneId() {
		return corpZoneId;
	}
	public void setCorpZoneId(Long corpZoneId) {
		this.corpZoneId = corpZoneId;
	}
	public String getCorpDepapment() {
		return corpDepapment;
	}
	public void setCorpDepapment(String corpDepapment) {
		this.corpDepapment = corpDepapment;
	}
	public String getCorpPost() {
		return corpPost;
	}
	public void setCorpPost(String corpPost) {
		this.corpPost = corpPost;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCorpPhone() {
		return corpPhone;
	}
	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}
	public String getCorpPhoneSec() {
		return corpPhoneSec;
	}
	public void setCorpPhoneSec(String corpPhoneSec) {
		this.corpPhoneSec = corpPhoneSec;
	}
	public Date getCorpStandFrom() {
		return corpStandFrom;
	}
	public void setCorpStandFrom(Date corpStandFrom) {
		this.corpStandFrom = corpStandFrom;
	}
	public String getCorpPayWay() {
		return corpPayWay;
	}
	public void setCorpPayWay(String corpPayWay) {
		this.corpPayWay = corpPayWay;
	}
	
	public BigDecimal getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(BigDecimal monthSalary) {
		this.monthSalary = monthSalary;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public BigDecimal getTotalMonthSalary() {
		return totalMonthSalary;
	}
	public void setTotalMonthSalary(BigDecimal totalMonthSalary) {
		this.totalMonthSalary = totalMonthSalary;
	}
	public WorkInfoVO(){	
	}
	public WorkInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
