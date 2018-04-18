package com.ymkj.cms.biz.entity;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class APPPersonVauleAddresEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long issuerStateId; // 户籍所在省
	private Long issuerCityId; // 户籍所在市
	private Long issuerZoneId; // 户籍所在区/县
	private Long homeStateId; // 家庭所在省
	private Long homeCityId; // 家庭所在市
	private Long homeZoneId; // 家庭所在区县
	private Long corpProvinceId; // 公司所在省
	private Long corpCityId; // 公司所在市
	private Long corpZoneId; // 公司所在区/县
	private String plateNum; // 车牌号
	
	
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
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	
	
}
