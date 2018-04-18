package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPPersonInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long loanBaseId;
	private String appNo;
	private String loanNo; //借款编号
	private Long applyPersonId;//申请人信息流水
	private Long personId;
	private String org; //机构号	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthday; //生日	
	@NotNull(message = "000001,性别不能为空")
	private String gender; //性别
	@NotNull(message = "000001,年龄不能为空")
	private Integer age; //年龄
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date idLastDate; //证件到期日
	@NotNull(message = "000001,户籍地址不能为空")
	private String idIssuerAddress; //户籍地址
	@NotNull(message = "000001,户籍所在省不能为空")
	private Long issuerStateId; //户籍所在省 
//	@NotNull(message = "000001,户籍所在市不能为空")
	private Long issuerCityId; //户籍所在市 	 
//	@NotNull(message = "000001,户籍所在区/县不能为空")
	private Long issuerZoneId; //户籍所在区/县	
//	@NotNull(message = "000001,户籍邮编不能为空")
	private String issuerPostcode; //户籍邮编
	
	
 
	@NotNull(message = "000001,婚姻状况不能为空") 
	private String maritalStatus; //婚姻状况
//	@NotNull(message = "000001,子女数不能为空") 
	private Integer childrenNum; //子女数		
	@NotNull(message = "000001,最高学历不能为空") 
	private String qualification; //教育状况
//	@NotNull(message = "000001,毕业时间不能为空") 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date graduationDate; //毕业时间
		 
	private String houseOwnership; //房屋持有类型
	@NotNull(message = "000001,住宅类型不能为空") 
	private String houseType; //住宅类型
//	@NotNull(message = "000001,租金/元不能为空")  
	private  BigDecimal	houseRent; //租金/元
	
	private String liquidAsset; //个人资产类型
	@NotNull(message = "000001,手机1不能为空")  
	private String cellphone; //手机1
	private String cellphoneSec; //手机2
	
	private String cellPhoneStatus;//手机1详单获取状态
	private String cellPhoneSecStatus;//手机2详单获取状态
	
	private String cellPhoneTime;//手机1详单获取时间
	private String cellPhoneSecTime;//手机2详单获取时间
	
	private String qqNum; //qq号
	private String weChatNum; //	微信号	
	private String email; //	电子邮箱
	
	@NotNull(message = "000001,每月家庭支出不能为空")  
	private BigDecimal familyMonthPay; //每月家庭支出
	private Long homeStateId; //	家庭所在省
	private Long homeCityId; //	家庭所在市
	private Long homeZoneId; //家庭所在区县
	@NotNull(message = "000001,家庭地址不能为空")  		 
	private String homeAddress; //家庭地址
	private String homePhone1; //住宅电话1
	@NotNull(message = "000001,可接受的月最高还款不能为空")  	
	private BigDecimal monthMaxRepay; //可接受的月最高还款
	@NotNull(message = "000001,客户工作类型不能为空")  		 
	private String cusWorkType; //客户工作类型
	@NotNull(message = "000001,单位名称不能为空")  	 
	private String corpName; //单位名称
	@NotNull(message = "000001,公司所在省不能为空")  	 
	private Long corpProvinceId; //公司所在省
	@NotNull(message = "000001,公司所在市不能为空")  	 
	private Long corpCityId; //公司所在市
	@NotNull(message = "000001,公司所在区/县不能为空")  	 
	private Long corpZoneId; //公司所在区/县
	@NotNull(message = "000001,单电1不能为空")  	 
	private String corpPhone; //公司电话1
	private String corpPhoneSec; //公司电话2
	@NotNull(message = "000001,职务不能为空")  	 
	private String corpPost; //职务
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "000001,入职时间不能为空")  	 
	private Date corpStandFrom; //	入职时间
	@NotNull(message = "000001,发薪方式不能为空")  	
	private String corpPayWay; //发薪方式
	@NotNull(message = "000001,任职部门不能为空")  
	private String corpDepapment; //	任职部门	
//	@NotNull(message = "000001,职业不能为空")  
	private String occupation; //职业	
	@NotNull(message = "000001,单位月收入/元不能为空") 
	private BigDecimal monthSalary; //单位月收入/元
	private BigDecimal otherIncome ; //其他月收入
	private BigDecimal totalMonthSalary; //月总收入/元	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	
	
	private Date setupDate ; //成立时间
	private Double sharesScale ; //占股比例/%
	private BigDecimal registerFunds; //注册资本/元
	private String priEnterpriseType ; //私营企业类型
	private String businessPlace; //经营场所
	private BigDecimal monthRent ; //月租金/元
	private Integer employeeNum; //员工人数/人
	private Double enterpriseRate ; //企业净利润率/%		 
	private String sharesName; //股东姓名(除客户外最大股东)
	private String sharesIdNO ; //	股东身份证
	private BigDecimal monthAmt; //每月净利率额/万元
	
	private Long reportId ; //	人行征信id
	private Long nfcsId; //  上海资信ID
	private Long hzzxId;//华征征信
	
	private String reportMessage; //绑定记录信息
	private Integer familyMember; //家庭人口
	private BigDecimal familyAvgeVenue;//家庭人均年收入
	private String homeAddrCtryCd; //家庭国家代码
	private Integer nationality; //国籍
	private String residencyCountryCd; //永久居住国家代码
	private String homePostcode; //家庭住宅邮编
	private String homePhone2; //住宅电话2
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date homeStandFrom; //现住址居住起始年月
	private Integer prOfCountry; //是否永久居住
	private String corpAddrCtryCd; //公司国家代码
	private String corpStructure; //公司性质
	private String corpAddress; //公司地址
	private String corpPostcode; //公司邮编
	private String corpFax; //公司传真
	private String businessNetWork; //工商网信息
	private Integer corpmemFlag ; //	是否公司员工
	private String corpmemNo; //本公司员工号
	private String corpType ; //	公司行业类别
	private Integer corpWorkyears; //本单位工作年限
	private Integer corpStability ; //	工作稳定性
	private Integer empStatus ; //	是否在职
	private Integer corpPayday ; //发薪日

	
	private String homeState;
	
	private String homeCity;
	
	private String homeZone;
	
	private String issuerState;
	private String issuerCity;
	private String issuerZone; 

	
	private String corpProvince;
	private String corpCity;
	private String corpZone;
	//private String corpAddress;
	  	

	private String TheThirdPartyNote;//第三方联系人备注
	private String TheThirdPartyNoteDetails;//第三方联系人备注详情

	private String idNo;
	
	private Long creatorId ; 
	
	private String creator ; 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private String name ;//申请人姓名
	
	private Integer snapVersion=3;//快照版本号

	private Integer homeSameRegistered;        //家庭住址是否同户籍地址
	
	private Date auditEndTime;        //审核结束时间（首次录入复核时间）
	
	private String ifReportId;//是否重绑央行id
	
	private String flag;
	
	
	private String longOnlineId;
	private String realNameAuthId;
	public String getIfReportId() {
		return ifReportId;
	}

	public void setIfReportId(String ifReportId) {
		this.ifReportId = ifReportId;
	}

	public Integer getHomeSameRegistered() {
		return homeSameRegistered;
	}

	public void setHomeSameRegistered(Integer homeSameRegistered) {
		this.homeSameRegistered = homeSameRegistered;
	}

	public String getTheThirdPartyNote() {
		return TheThirdPartyNote;
	}

	public void setTheThirdPartyNote(String theThirdPartyNote) {
		TheThirdPartyNote = theThirdPartyNote;
	}

	public String getTheThirdPartyNoteDetails() {
		return TheThirdPartyNoteDetails;
	}

	public void setTheThirdPartyNoteDetails(String theThirdPartyNoteDetails) {
		TheThirdPartyNoteDetails = theThirdPartyNoteDetails;
	}

	public Long getHzzxId() {
		return hzzxId;
	}

	public void setHzzxId(Long hzzxId) {
		this.hzzxId = hzzxId;
	}

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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

	 
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Long getApplyPersonId() {
		return applyPersonId;
	}

	public void setApplyPersonId(Long applyPersonId) {
		this.applyPersonId = applyPersonId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	
	public Long getNfcsId() {
		return nfcsId;
	}

	public void setNfcsId(Long nfcsId) {
		this.nfcsId = nfcsId;
	}

	public String getIssuerPostcode() {
		return issuerPostcode;
	}

	public void setIssuerPostcode(String issuerPostcode) {
		this.issuerPostcode = issuerPostcode;
	}
 

	public Integer getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Integer childrenNum) {
		this.childrenNum = childrenNum;
	}

 

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
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

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getWeChatNum() {
		return weChatNum;
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

	public void setWeChatNum(String weChatNum) {
		this.weChatNum = weChatNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getHomePhone1() {
		return homePhone1;
	}

	public void setHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
	}
 

	 
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public String getLiquidAsset() {
		return liquidAsset;
	}

	public void setLiquidAsset(String liquidAsset) {
		this.liquidAsset = liquidAsset;
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

	 

	public BigDecimal getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}

	 
	public BigDecimal getFamilyMonthPay() {
		return familyMonthPay;
	}

	public void setFamilyMonthPay(BigDecimal familyMonthPay) {
		this.familyMonthPay = familyMonthPay;
	}

	public BigDecimal getMonthMaxRepay() {
		return monthMaxRepay;
	}

	public void setMonthMaxRepay(BigDecimal monthMaxRepay) {
		this.monthMaxRepay = monthMaxRepay;
	}

	public BigDecimal getMonthSalary() {
		return monthSalary;
	}

	public void setMonthSalary(BigDecimal monthSalary) {
		this.monthSalary = monthSalary;
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

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReportMessage() {
		return reportMessage;
	}

	public void setReportMessage(String reportMessage) {
		this.reportMessage = reportMessage;
	}

	public Integer getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(Integer familyMember) {
		this.familyMember = familyMember;
	}

	public BigDecimal getFamilyAvgeVenue() {
		return familyAvgeVenue;
	}

	public void setFamilyAvgeVenue(BigDecimal familyAvgeVenue) {
		this.familyAvgeVenue = familyAvgeVenue;
	}

	public String getHomeAddrCtryCd() {
		return homeAddrCtryCd;
	}

	public void setHomeAddrCtryCd(String homeAddrCtryCd) {
		this.homeAddrCtryCd = homeAddrCtryCd;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public String getResidencyCountryCd() {
		return residencyCountryCd;
	}

	public void setResidencyCountryCd(String residencyCountryCd) {
		this.residencyCountryCd = residencyCountryCd;
	}

	public String getHomePostcode() {
		return homePostcode;
	}

	public void setHomePostcode(String homePostcode) {
		this.homePostcode = homePostcode;
	}

	public String getHomePhone2() {
		return homePhone2;
	}

	public void setHomePhone2(String homePhone2) {
		this.homePhone2 = homePhone2;
	}

	public Date getHomeStandFrom() {
		return homeStandFrom;
	}

	public void setHomeStandFrom(Date homeStandFrom) {
		this.homeStandFrom = homeStandFrom;
	}

	public Integer getPrOfCountry() {
		return prOfCountry;
	}

	public void setPrOfCountry(Integer prOfCountry) {
		this.prOfCountry = prOfCountry;
	}

	public String getCorpAddrCtryCd() {
		return corpAddrCtryCd;
	}

	public void setCorpAddrCtryCd(String corpAddrCtryCd) {
		this.corpAddrCtryCd = corpAddrCtryCd;
	}

	public String getCorpStructure() {
		return corpStructure;
	}

	public void setCorpStructure(String corpStructure) {
		this.corpStructure = corpStructure;
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

	public String getCorpFax() {
		return corpFax;
	}

	public void setCorpFax(String corpFax) {
		this.corpFax = corpFax;
	}

	public String getBusinessNetWork() {
		return businessNetWork;
	}

	public void setBusinessNetWork(String businessNetWork) {
		this.businessNetWork = businessNetWork;
	}

	public Integer getCorpmemFlag() {
		return corpmemFlag;
	}

	public void setCorpmemFlag(Integer corpmemFlag) {
		this.corpmemFlag = corpmemFlag;
	}

	public String getCorpmemNo() {
		return corpmemNo;
	}

	public void setCorpmemNo(String corpmemNo) {
		this.corpmemNo = corpmemNo;
	}

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public Integer getCorpWorkyears() {
		return corpWorkyears;
	}

	public void setCorpWorkyears(Integer corpWorkyears) {
		this.corpWorkyears = corpWorkyears;
	}

	public Integer getCorpStability() {
		return corpStability;
	}

	public void setCorpStability(Integer corpStability) {
		this.corpStability = corpStability;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public Integer getCorpPayday() {
		return corpPayday;
	}

	public void setCorpPayday(Integer corpPayday) {
		this.corpPayday = corpPayday;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVerson() {
		return verson;
	}

	public void setVerson(Integer verson) {
		this.verson = verson;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

	public String getCellPhoneStatus() {
		return cellPhoneStatus;
	}

	public void setCellPhoneStatus(String cellPhoneStatus) {
		this.cellPhoneStatus = cellPhoneStatus;
	}

	public String getCellPhoneSecStatus() {
		return cellPhoneSecStatus;
	}

	public void setCellPhoneSecStatus(String cellPhoneSecStatus) {
		this.cellPhoneSecStatus = cellPhoneSecStatus;
	}

	public String getCellPhoneTime() {
		return cellPhoneTime;
	}

	public void setCellPhoneTime(String cellPhoneTime) {
		this.cellPhoneTime = cellPhoneTime;
	}

	public String getCellPhoneSecTime() {
		return cellPhoneSecTime;
	}

	public void setCellPhoneSecTime(String cellPhoneSecTime) {
		this.cellPhoneSecTime = cellPhoneSecTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLongOnlineId() {
		return longOnlineId;
	}

	public void setLongOnlineId(String longOnlineId) {
		this.longOnlineId = longOnlineId;
	}

	public String getRealNameAuthId() {
		return realNameAuthId;
	}

	public void setRealNameAuthId(String realNameAuthId) {
		this.realNameAuthId = realNameAuthId;
	}
	
 
}
