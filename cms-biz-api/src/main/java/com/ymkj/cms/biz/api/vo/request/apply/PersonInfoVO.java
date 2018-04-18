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
public class PersonInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	private Long  appPeronId; //申请人信息ID
	private Long  appPersonInfoId;//主卡人信息ID
	private String name;         //姓名		
	private String idNo;        //身份证号码
	private String gender;      //性别
	private Date birthday; //生日
	private Integer age;        //年龄
	private String maritalStatus;        //婚姻状况
	private Integer childrenNum;        //子女数
	private String qualification;        //最高学历
	private Date graduationDate;        //毕业时间
	private Long issuerStateId;        //户籍所在省ID
	private Long issuerCityId;        //户籍所在市ID
	private Long issuerZoneId;        //户籍所在区ID
	private String issuerState;	//户籍所在省
	private String issuerCity;	//户籍所在市
	private String issuerZone;	//户籍所在区
	
	private String idIssuerAddress;        //户籍地址	
	private String issuerPostcode;        //户籍邮编
	private Integer homeSameRegistered;        //家庭住址是否同户籍地址    0 是  1否
	private Long homeStateId;        //家庭所在省ID
	private Long homeCityId;        //家庭所在市ID
	private Long homeZoneId;        //家庭所在区ID
	private String homeState;		//家庭所在省名称
	private String homeCity;		//家庭所在市ID
	private String homeZone;		//家庭所在区ID
	private String homeAddress;        //	家庭地址
	private String homePostcode; //家庭住址邮编
	private String houseType;        //住宅类型
	private BigDecimal houseRent;     //	租金/元	
	private BigDecimal familyMonthPay; //每月家庭支出
	private BigDecimal monthMaxRepay;  //可接受的月最高还款
	private String cellphone;        //手机1
	private String cellphoneSec;  //手机2
	private String homePhone1;        //宅电
	private String qqNum;  //QQ号
	private String weChatNum;        //微信号
	private String email;        //电子邮箱
	
	private Long reportId ; //	人行征信id
	private Long nfcsId; //  上海资信ID
	
	private String longOnlineId;
	private String realNameAuthId;
	 
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
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public Long getNfcsId() {
		return nfcsId;
	}
	public void setNfcsId(Long nfcsId) {
		this.nfcsId = nfcsId;
	}
	public String getHomePostcode() {
		return homePostcode;
	}
	public void setHomePostcode(String homePostcode) {
		this.homePostcode = homePostcode;
	}
	public Long getAppPeronId() {
		return appPeronId;
	}
	public void setAppPeronId(Long appPeronId) {
		this.appPeronId = appPeronId;
	}
	public Long getAppPersonInfoId() {
		return appPersonInfoId;
	}
	public void setAppPersonInfoId(Long appPersonInfoId) {
		this.appPersonInfoId = appPersonInfoId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public Integer getHomeSameRegistered() {
		return homeSameRegistered;
	}
	public void setHomeSameRegistered(Integer homeSameRegistered) {
		this.homeSameRegistered = homeSameRegistered;
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
	public String getWeChatNum() {
		return weChatNum;
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
	public PersonInfoVO(){
		
	}
	public PersonInfoVO(String sysCode){
		super.setSysCode(sysCode);
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
