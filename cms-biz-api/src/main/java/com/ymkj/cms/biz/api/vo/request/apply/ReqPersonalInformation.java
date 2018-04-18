package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqPersonalInformation  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanNo;	 //借款编号
	
	private String corpName; //单位名称
	private Long corpProvinceId; //公司所在省
	private Long corpCityId; //公司所在市
	private Long corpZoneId; //公司所在区/县
	
	private String corpProvince; //公司所在省
	private String corpCity; //公司所在市
	private String corpZone; //公司所在区/县
	
	private String corpAddress; //公司地址
	private Long homeStateId; //	家庭所在省
	private Long homeCityId; //	家庭所在市
	private Long homeZoneId; //家庭所在区县
	
	private String homeState; //	家庭所在省
	private String homeCity; //	家庭所在市
	private String homeZone; //家庭所在区县
	
	private String homeAddress; //家庭地址
	private String TheThirdPartyNote;//第三方联系人备注
	private String TheThirdPartyNoteDetails;//第三方联系人备注详情
	
	private String serviceCode;			//操作人工号
	private String serviceName;			//操作人姓名
	private String ip;					//操作ip
	
	private String version;//当前版本号
	
	
	
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
