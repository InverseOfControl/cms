package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResLinePaymentOfferBatch implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5059388281164088747L;

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

	
	
	

}
