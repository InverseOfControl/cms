package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

public class ResBMSChannelBankVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;

	/**
	 * 渠道id
	 */
	private Long channelId;
	/**
	 * 银行id
	 */
	private Long bankId;
	/**
	 * 渠道代码
	 */
	private String channeCode;
	/**
	 * 渠道名称
	 */
	private String channeName;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	private Long isDelete;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	
	private Long creatorId ; 

	private String creator ; 

	private Date creatorDate ; 

	private Long modifiedId ; 

	private String modified ; 

	private Date modifiedDate ; 

	private Long version ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getChanneCode() {
		return channeCode;
	}

	public void setChanneCode(String channeCode) {
		this.channeCode = channeCode;
	}

	public String getChanneName() {
		return channeName;
	}

	public void setChanneName(String channeName) {
		this.channeName = channeName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
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

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	
	
}
