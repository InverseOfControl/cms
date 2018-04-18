package com.ymkj.cms.biz.entity.app.input;

import org.apache.commons.lang.ObjectUtils;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.cms.biz.api.enums.EnumConstants;

public class AppAppPersonInfoEntity extends BaseEntity{
	private static final long serialVersionUID = 179926471785725311L;

	//公共字段
	private Long  id; //主键
	private Long  loanBaseId; //借款主表ID
	private String loanNo; //借款编号
	private String name; //姓名		
	private String idNo; //身份证号码
	private Long personId; //客户主表主键
	private String org;
	
	//个人信息字段
	private String maritalStatus; //婚姻状况code
	private String maritalStatus1; //婚姻状况
	private Integer childrenNum; //子女数
	private String qualification; //最高学历code
	private String qualification1; //最高学历
	private String graduationDate; //毕业时间
	private String issZoneLinkage; //户籍所在     app传输字段：issZone-Linkage
	private String idIssuerAddress; //户籍地址
	private String issuerPostcode; //户籍邮编
	private String homeZoneLinkage; //家庭所在   app传输字段：homeZone-Linkage
	private String homeAddress; //家庭地址
	private String homePostcode; //家庭住宅邮编
	private String houseType; //住宅类型
	private String houseRent; //租金/元
	private String familyMonthPay; //每月家庭支出
	private String monthMaxRepay; //可接受的月最高还款额
	private String cellphone; //手机1
	private String cellphone_status;
	private String cellphoneSec; //手机2
	private String homePhone1; //宅电
	private String qqNum; //QQ号
	private String wechatNum; //微信号
	private String email; //邮箱
	
	//省市区转换字段
	private String issuerStateId; //户籍所在省ID
	private String issuerCityId; //户籍所在市ID
	private String issuerZoneId; //户籍所在区ID
	private String issuerState;	//户籍所在省
	private String issuerCity;	//户籍所在市
	private String issuerZone;	//户籍所在区
	private String homeStateId; //家庭所在省ID
	private String homeCityId; //家庭所在市ID
	private String homeZoneId; //家庭所在区ID
	private String homeState; //家庭所在省名称
	private String homeCity; //家庭所在市
	private String homeZone; //家庭所在区
	
	//工作信息
	private String corpName; //公司名称
	private String privateOwnersFlag; //是否私营业主code   Y/N
	private String privateOwnersFlag1; //是否私营业主   是/否
	private String corpZoneLinkage; //公司所在   app传输原字段  corpZone-Linkage
	private String corpAddress; //公司地址
	private String corpPostcode; //公司邮编
	private String businessNetWork; //工商网信息
	private String corpStructure; //公司性质
	private String corpType; //公司行业类别
	private String corpDepapment; //任职部门
	private String corpPost; //职务
	private String occupation; //职业
	private String corpPhone; //单位电话1
	private String corpPhoneSec; //单位电话2
	private String corpStandFrom; //入职时间
	private String corpPayWay; //发薪方式
	private String monthSalary; //单位月收入/元
	private String otherIncome; //其它月收入/元
	private String cusWorkType; //客户工作类型
	private String homeSameIdIssuerAddress; //家庭地址是否同户籍地址
	
	//转换字段
	private String corpProvinceId; //省ID
	private String corpCityId; //市ID
	private String corpZoneId; //区ID
	private String corpProvince; //省名称
	private String corpCity; //市名称
	private String corpZone; //区名称
	
	private String gender; //性别
	private String birthday; //生日
	private String age; //年龄
	
	private String creatorId; //创建人ID
	private String creator; //创建人
	
	/**私营业主信息*/
	private String setupDate; //成立时间
	private String sharesScale; //占股比例
	private String registerFunds; //注册资本
	private String priEnterpriseType; //私营企业类型
	private String monthAmt; //每月净利润额
	
	public AppAppPersonInfoEntity(Long loanBaseId,Long personId,String loanNo,Long creatorId,String creator){
		this.loanBaseId = loanBaseId;
		this.personId = personId;
		this.loanNo = loanNo;
		this.creatorId = ObjectUtils.toString(creatorId);
		this.creator = creator;
		this.org = EnumConstants.BMS_Org;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMaritalStatus1() {
		return maritalStatus1;
	}
	public void setMaritalStatus1(String maritalStatus1) {
		this.maritalStatus1 = maritalStatus1;
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
	public String getQualification1() {
		return qualification1;
	}
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getIssZoneLinkage() {
		return issZoneLinkage;
	}
	public void setIssZoneLinkage(String issZoneLinkage) {
		this.issZoneLinkage = issZoneLinkage;
	}
	public String getIdIssuerAddress() {
		return idIssuerAddress;
	}
	public void setIdIssuerAddress(String idIssuerAddress) {
		this.idIssuerAddress = idIssuerAddress;
	}
	public String getIssuerPostcode() {
		return issuerPostcode;
	}
	public void setIssuerPostcode(String issuerPostcode) {
		this.issuerPostcode = issuerPostcode;
	}
	public String getHomeZoneLinkage() {
		return homeZoneLinkage;
	}
	public void setHomeZoneLinkage(String homeZoneLinkage) {
		this.homeZoneLinkage = homeZoneLinkage;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getHomePostcode() {
		return homePostcode;
	}
	public void setHomePostcode(String homePostcode) {
		this.homePostcode = homePostcode;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getHouseRent() {
		return houseRent;
	}
	public void setHouseRent(String houseRent) {
		this.houseRent = houseRent;
	}
	public String getFamilyMonthPay() {
		return familyMonthPay;
	}
	public void setFamilyMonthPay(String familyMonthPay) {
		this.familyMonthPay = familyMonthPay;
	}
	public String getMonthMaxRepay() {
		return monthMaxRepay;
	}
	public void setMonthMaxRepay(String monthMaxRepay) {
		this.monthMaxRepay = monthMaxRepay;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getCellphone_status() {
		return cellphone_status;
	}
	public void setCellphone_status(String cellphone_status) {
		this.cellphone_status = cellphone_status;
	}
	public String getCellphoneSec() {
		return cellphoneSec;
	}
	public void setCellphoneSec(String cellphoneSec) {
		this.cellphoneSec = cellphoneSec;
	}
	public String getHomePhone1() {
		return homePhone1;
	}
	public void setHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
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
	public String getIssuerStateId() {
		return issuerStateId;
	}
	public void setIssuerStateId(String issuerStateId) {
		this.issuerStateId = issuerStateId;
	}
	public String getIssuerCityId() {
		return issuerCityId;
	}
	public void setIssuerCityId(String issuerCityId) {
		this.issuerCityId = issuerCityId;
	}
	public String getIssuerZoneId() {
		return issuerZoneId;
	}
	public void setIssuerZoneId(String issuerZoneId) {
		this.issuerZoneId = issuerZoneId;
	}
	public String getIssuerState() {
		return issuerState;
	}
	public void setIssuerState(String issuerState) {
		this.issuerState = issuerState;
	}
	public String getIssuerCity() {
		return issuerCity;
	}
	public void setIssuerCity(String issuerCity) {
		this.issuerCity = issuerCity;
	}
	public String getIssuerZone() {
		return issuerZone;
	}
	public void setIssuerZone(String issuerZone) {
		this.issuerZone = issuerZone;
	}
	public String getHomeStateId() {
		return homeStateId;
	}
	public void setHomeStateId(String homeStateId) {
		this.homeStateId = homeStateId;
	}
	public String getHomeCityId() {
		return homeCityId;
	}
	public void setHomeCityId(String homeCityId) {
		this.homeCityId = homeCityId;
	}
	public String getHomeZoneId() {
		return homeZoneId;
	}
	public void setHomeZoneId(String homeZoneId) {
		this.homeZoneId = homeZoneId;
	}
	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public String getHomeZone() {
		return homeZone;
	}
	public void setHomeZone(String homeZone) {
		this.homeZone = homeZone;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getPrivateOwnersFlag() {
		return privateOwnersFlag;
	}
	public void setPrivateOwnersFlag(String privateOwnersFlag) {
		this.privateOwnersFlag = privateOwnersFlag;
	}
	public String getPrivateOwnersFlag1() {
		return privateOwnersFlag1;
	}
	public void setPrivateOwnersFlag1(String privateOwnersFlag1) {
		this.privateOwnersFlag1 = privateOwnersFlag1;
	}
	public String getCorpZoneLinkage() {
		return corpZoneLinkage;
	}
	public void setCorpZoneLinkage(String corpZoneLinkage) {
		this.corpZoneLinkage = corpZoneLinkage;
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
	public String getCorpStandFrom() {
		return corpStandFrom;
	}
	public void setCorpStandFrom(String corpStandFrom) {
		this.corpStandFrom = corpStandFrom;
	}
	public String getCorpPayWay() {
		return corpPayWay;
	}
	public void setCorpPayWay(String corpPayWay) {
		this.corpPayWay = corpPayWay;
	}
	public String getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(String monthSalary) {
		this.monthSalary = monthSalary;
	}
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getCorpProvinceId() {
		return corpProvinceId;
	}
	public void setCorpProvinceId(String corpProvinceId) {
		this.corpProvinceId = corpProvinceId;
	}
	public String getCorpCityId() {
		return corpCityId;
	}
	public void setCorpCityId(String corpCityId) {
		this.corpCityId = corpCityId;
	}
	public String getCorpZoneId() {
		return corpZoneId;
	}
	public void setCorpZoneId(String corpZoneId) {
		this.corpZoneId = corpZoneId;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}

	public String getSharesScale() {
		return sharesScale;
	}

	public void setSharesScale(String sharesScale) {
		this.sharesScale = sharesScale;
	}

	public String getRegisterFunds() {
		return registerFunds;
	}

	public void setRegisterFunds(String registerFunds) {
		this.registerFunds = registerFunds;
	}

	public String getPriEnterpriseType() {
		return priEnterpriseType;
	}

	public void setPriEnterpriseType(String priEnterpriseType) {
		this.priEnterpriseType = priEnterpriseType;
	}

	public String getMonthAmt() {
		return monthAmt;
	}

	public void setMonthAmt(String monthAmt) {
		this.monthAmt = monthAmt;
	}

	public String getCusWorkType() {
		return cusWorkType;
	}

	public void setCusWorkType(String cusWorkType) {
		this.cusWorkType = cusWorkType;
	}

	public String getHomeSameIdIssuerAddress() {
		return homeSameIdIssuerAddress;
	}

	public void setHomeSameIdIssuerAddress(String homeSameIdIssuerAddress) {
		this.homeSameIdIssuerAddress = homeSameIdIssuerAddress;
	}
}
