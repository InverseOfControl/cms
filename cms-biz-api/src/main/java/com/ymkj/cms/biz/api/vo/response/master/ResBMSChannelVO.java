package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSChannelVO implements Serializable {

	private static final long serialVersionUID = 2125950758423809989L;

	private int id;

	private String channelId;

	private String name;

	private String code;

	private String calculateUrl;

	private String startSalesTime;

	private String stopSalesTime;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	
    private Long creatorId ; 
	
	private String creator ; 
	
	private  Date creatorDate ; 
	
	private Long modifiedId ; 
	
	private String modified ; 
	
	private Date modifiedDate ; 
	
	private Long isDeleted; 
	
	private Long version ;
	/**
	 * 电子合同是否禁用（0：启用，1禁用）默认值1
	 */
	private Long econtractFlag ;
	/**
	 * 纸质合同是否禁用（0：启用，1禁用）默认值1
	 */
	private Long pcontractFlag ;
	
	/**
	 * 是否可优惠
	 */
	private Long isCanPreferential;

	
	
	public Long getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(Long isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCalculateUrl() {
		return calculateUrl;
	}

	public void setCalculateUrl(String calculateUrl) {
		this.calculateUrl = calculateUrl;
	}

	public String getStartSalesTime() {
		return startSalesTime;
	}

	public void setStartSalesTime(String startSalesTime) {
		this.startSalesTime = startSalesTime;
	}

	public String getStopSalesTime() {
		return stopSalesTime;
	}

	public void setStopSalesTime(String stopSalesTime) {
		this.stopSalesTime = stopSalesTime;
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



	public Date getCreatorDate() {
		return creatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

	public Long getModifiedId() {
		return modifiedId;
	}

	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getEcontractFlag() {
		return econtractFlag;
	}

	public void setEcontractFlag(Long econtractFlag) {
		this.econtractFlag = econtractFlag;
	}

	public Long getPcontractFlag() {
		return pcontractFlag;
	}

	public void setPcontractFlag(Long pcontractFlag) {
		this.pcontractFlag = pcontractFlag;
	}


}
