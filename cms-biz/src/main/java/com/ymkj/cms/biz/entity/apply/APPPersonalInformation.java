package com.ymkj.cms.biz.entity.apply;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class APPPersonalInformation  extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;	 //借款编号
	
	private String corpName; //单位名称
	@NotNull(message = "000001,公司所在省不能为空")
	private Long corpProvinceId; //公司所在省
	@NotNull(message = "000001,公司所在市不能为空")
	private Long corpCityId; //公司所在市
	@NotNull(message = "000001,公司所在区/县不能为空")
	private Long corpZoneId; //公司所在区/县
	@NotNull(message = "000001,公司地址不能为空")
	private String corpAddress; //公司地址
	@NotNull(message = "000001,家庭所在省不能为空")
	private Long homeStateId; //	家庭所在省
	@NotNull(message = "000001,家庭所在市不能为空")
	private Long homeCityId; //	家庭所在市
	@NotNull(message = "000001,家庭所在区县不能为空")
	private Long homeZoneId; //家庭所在区县
	@NotNull(message = "000001,家庭地址不能为空")
	private String homeAddress; //家庭地址
	@NotNull(message = "000001,操作人工号不能为空")
	private String serviceCode;			//操作人工号
	@NotNull(message = "000001,操作人姓名不能为空")
	private String serviceName;			//操作人姓名
	@NotNull(message = "000001,操作ip不能为空")
	private String ip;					//操作ip
	
	private String TheThirdPartyNote;//第三方联系人备注
	private String TheThirdPartyNoteDetails;//第三方联系人备注详情
	@NotNull(message = "000001,当前版本号不能为空")
	private String version;//当前版本号
	
//	@NotNull(message = "000001,公司所在省名称不能为空")
	private String corpProvince; //公司所在省
//	@NotNull(message = "000001,公司所在市名称不能为空")
	private String corpCity; //公司所在市
//	@NotNull(message = "000001,公司所在区/县名称不能为空")
	private String corpZone; //公司所在区/县
	
//	@NotNull(message = "000001,家庭所在省名称不能为空")
	private String homeState; //	家庭所在省
//	@NotNull(message = "000001,家庭所在市名称不能为空")
	private String homeCity; //	家庭所在市
//	@NotNull(message = "000001,家庭所在区县名称不能为空")
	private String homeZone; //家庭所在区县
	
	
	
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getCorpAddress() {
		return corpAddress;
	}
	public void setCorpAddress(String corpAddress) {
		this.corpAddress = corpAddress;
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
