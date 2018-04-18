package com.ymkj.cms.biz.entity.channel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:线下代付 报盘批次信息
 */
public class LinePaymentOfferBatch extends BaseEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5957498823543742507L;

	/**
	 * 主键id
	 */
	private long id;

	/**
	 * 交易标志
	 */
	private String tradeMark;

	/**
	 * 商户号
	 */
	private String merchantId;

	/**
	 * 提交日期
	 */
	private Date commitTime;

	/**
	 * 总记录数
	 */
	private int recordsTotal;

	/**
	 * 总金额
	 */
	private BigDecimal amountTotal;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 当日批次号
	 */
	private String dayBatchNumber;

	/**
	 * 是否导出文件
	 */
	private String exportFile;

	/**
	 * 提交日期（YYYYMMDD）
	 */
	private String submitDate;

	private String creatorId;

	private String creator;

	private Date createdTime;

	private String modifierId;

	private String modifier;

	private Date modifiedTime;

	private String version;

	private Integer isDelete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDayBatchNumber() {
		return dayBatchNumber;
	}

	public void setDayBatchNumber(String dayBatchNumber) {
		this.dayBatchNumber = dayBatchNumber;
	}

	public String getExportFile() {
		return exportFile;
	}

	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	

}
