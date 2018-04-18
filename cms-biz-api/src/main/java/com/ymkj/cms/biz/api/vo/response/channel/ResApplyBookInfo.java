package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YM10189
 * @date 2017年6月1日
 * @Description:申请书
 */
public class ResApplyBookInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3298431324992775864L;

	private long id;

	private String fileName;

	private String batchNum;

	private BigDecimal grantMoney;

	private int loanTimes;

	private BigDecimal diffMoney;

	private BigDecimal applyMoney;

	private String remark;

	private String mark1;

	private String mark2;

	private String creatorId;

	private String creator;

	private Date createdTime;

	private String modifierId;

	private String modifier;

	private Date modifiedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public BigDecimal getGrantMoney() {
		return grantMoney;
	}

	public void setGrantMoney(BigDecimal grantMoney) {
		this.grantMoney = grantMoney;
	}

	public int getLoanTimes() {
		return loanTimes;
	}

	public void setLoanTimes(int loanTimes) {
		this.loanTimes = loanTimes;
	}

	public BigDecimal getDiffMoney() {
		return diffMoney;
	}

	public void setDiffMoney(BigDecimal diffMoney) {
		this.diffMoney = diffMoney;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMark1() {
		return mark1;
	}

	public void setMark1(String mark1) {
		this.mark1 = mark1;
	}

	public String getMark2() {
		return mark2;
	}

	public void setMark2(String mark2) {
		this.mark2 = mark2;
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

}
