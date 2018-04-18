package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPPersonInfoVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
 
	private String org; //机构号	
	 
	private Date birthday; //生日	
 
	private String gender; //性别
 
	private Integer age; //年龄		 
	private Date idLastDate; //证件到期日
 
	private String idIssuerAddress; //户籍地址
 
	private Long issuerStateId; //户籍所在省 
 
	private Long issuerCityId; //户籍所在市 	 
 
	private Long issuerZoneId; //户籍所在区/县	
 
	private String issuerPostcode; //户籍邮编
	
	
 
	 
	private String maritalStatus; //婚姻状况
	 
	private Integer childrenNum; //子女数		
	 
	private String qualification; //教育状况
	 
	private Date graduationDate; //毕业时间
		 
	private String houseOwnership; //房屋持有类型
	 
	private String houseType; //住宅类型
	 
	private  BigDecimal	houseRent; //租金/元
	
	private String liquidAsset; //个人资产类型
 
	private String cellphone; //手机1
	private String cellphoneSec; //手机2
	
	private String qqNum; //qq号
	private String wechatNum; //	微信号	
	private String email; //	电子邮箱
	
 
	private BigDecimal familyMonthPay; //每月家庭支出
	private Long homeStateId; //	家庭所在省
	private Long homeCityId; //	家庭所在市
	private Long homeZoneId; //家庭所在区县
	 
	private String homeAddress; //家庭地址
	private String homePhone; //住宅电话1
	 
	private BigDecimal monthMaxRepay; //可接受的月最高还款
	 
	private String cusWorkType; //客户工作类型
	 
	private String corpName; //单位名称
	 
	private Long corpProvinceId; //公司所在省
	private Long corpCityId; //公司所在市
	private Long corpZoneId; //公司所在区/县
	 
	private String corpPhone; //公司电话1
	private String corpPhoneSec; //公司电话2
	 
	private String corpPost; //职务
 
	private Date corpStandFrom; //	入职时间
	 
	private String corpPayWay; //发薪方式
 
	private String corpDepapment; //	任职部门	
	 
	private String occupation; //职业	
 
	private BigDecimal monthSalary; //单位月收入/元
	private BigDecimal otherIncome ; //其他月收入
	 
	private BigDecimal totalMonthSalary; //月总收入/元	
	 	 
	private Date setupDate ; //成立时间
	 
	private Double sharesScale ; //占股比例/%
	 
	private BigDecimal registerFunds; //注册资本/万元
	 
	private String priEnterpriseType ; //私营企业类型
	  
	private String businessPlace; //经营场所
	private Integer monthRent ; //月租金/元
	 
	private Integer employeeNum; //员工人数/人
	 
	private Double enterpriseRate ; //企业净利润率/%		 
	private String sharesName; //股东姓名(除客户外最大股东)
	private String sharesIdNo ; //	股东身份证
	 
	private BigDecimal monthAmt; //每月净利率额/万元

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getIdLastDate() {
		return idLastDate;
	}

	public void setIdLastDate(Date idLastDate) {
		this.idLastDate = idLastDate;
	}

	public String getIdIssuerAddress() {
		return idIssuerAddress;
	}

	public void setIdIssuerAddress(String idIssuerAddress) {
		this.idIssuerAddress = idIssuerAddress;
	}

	public Long getIssuerStateId() {
		return issuerStateId;
	}

	public void setIssuerStateId(Long issuerStateId) {
		this.issuerStateId = issuerStateId;
	}

	public Long getIssuerCityId() {
		return issuerCityId;
	}

	public void setIssuerCityId(Long issuerCityId) {
		this.issuerCityId = issuerCityId;
	}

	public Long getIssuerZoneId() {
		return issuerZoneId;
	}

	public void setIssuerZoneId(Long issuerZoneId) {
		this.issuerZoneId = issuerZoneId;
	}

	public String getIssuerPostcode() {
		return issuerPostcode;
	}

	public void setIssuerPostcode(String issuerPostcode) {
		this.issuerPostcode = issuerPostcode;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Integer childrenNum) {
		this.childrenNum = childrenNum;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public BigDecimal getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(BigDecimal houseRent) {
		this.houseRent = houseRent;
	}

	public String getLiquidAsset() {
		return liquidAsset;
	}

	public void setLiquidAsset(String liquidAsset) {
		this.liquidAsset = liquidAsset;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCellphoneSec() {
		return cellphoneSec;
	}

	public void setCellphoneSec(String cellphoneSec) {
		this.cellphoneSec = cellphoneSec;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getFamilyMonthPay() {
		return familyMonthPay;
	}

	public void setFamilyMonthPay(BigDecimal familyMonthPay) {
		this.familyMonthPay = familyMonthPay;
	}

	public Long getHomeStateId() {
		return homeStateId;
	}

	public void setHomeStateId(Long homeStateId) {
		this.homeStateId = homeStateId;
	}

	public Long getHomeCityId() {
		return homeCityId;
	}

	public void setHomeCityId(Long homeCityId) {
		this.homeCityId = homeCityId;
	}

	public Long getHomeZoneId() {
		return homeZoneId;
	}

	public void setHomeZoneId(Long homeZoneId) {
		this.homeZoneId = homeZoneId;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public BigDecimal getMonthMaxRepay() {
		return monthMaxRepay;
	}

	public void setMonthMaxRepay(BigDecimal monthMaxRepay) {
		this.monthMaxRepay = monthMaxRepay;
	}

	public String getCusWorkType() {
		return cusWorkType;
	}

	public void setCusWorkType(String cusWorkType) {
		this.cusWorkType = cusWorkType;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
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

	public String getCorpPost() {
		return corpPost;
	}

	public void setCorpPost(String corpPost) {
		this.corpPost = corpPost;
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

	public String getCorpDepapment() {
		return corpDepapment;
	}

	public void setCorpDepapment(String corpDepapment) {
		this.corpDepapment = corpDepapment;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public Integer getMonthRent() {
		return monthRent;
	}

	public void setMonthRent(Integer monthRent) {
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
