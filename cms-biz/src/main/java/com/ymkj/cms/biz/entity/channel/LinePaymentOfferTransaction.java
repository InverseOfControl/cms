package com.ymkj.cms.biz.entity.channel;

import java.io.Serializable;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class LinePaymentOfferTransaction extends BaseEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5735474233672169685L;

	/** 主键Id **/
	private long id;

	/** 备注 **/
	private String remark;

	/** 反馈码 **/
	private String feedbackCode;

	/** 原因 **/
	private String reason;

	/** 报盘Id **/
	private long offerId;

	/** 流水号 **/
	private String flowNumber;

	/** 报盘批次Id **/
	private long batchId;

	/** 记录序号 **/
	private String recordNumber;

	/** 报盘状态（未报盘 已报盘 扣款成功 扣款失败） **/
	private String state;

	/** 回盘时间 **/
	private Date returnTime;

	/** 发送时间 **/
	private Date sendTime;

	private String creatorId;

	private String creator;

	private Date createdTime;

	private String modifierId;

	private String modifier;

	private Date modifiedTime;

	private Integer version;

	private Integer isDelete;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getFeedbackCode() {
		return feedbackCode;
	}

	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode == null ? null : feedbackCode.trim();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}


	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getFlowNumber() {
		return flowNumber;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber == null ? null : flowNumber.trim();
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber == null ? null : recordNumber.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}