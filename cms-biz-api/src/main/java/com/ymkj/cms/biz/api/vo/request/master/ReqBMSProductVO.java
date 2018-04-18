package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 1.demo请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 2.其中必须包含 对应实体类 {Entity}中的 所有字段, 业务不同实体不同  
 * 3.如有需要可 自行扩展
 * @author 
 *
 */
public class ReqBMSProductVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 8656170812642602261L;
	public ReqBMSProductVO(){
		
	}
	public ReqBMSProductVO(String sysCode){
		super.setSysCode(sysCode);
	}
	/**
	 * 渠道ID集合
	 */
	private List<Long> channelIds;
	/**
	 * 产品期限集合
	 */
	private List<Long> auditLimitIds;
	/**
	 * 渠道产品期限集合
	 */
	private List<String> channelAuditLimitList;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	/**
	 * 客服code
	 */
	private String userCode;
	/**
	 * 门店ID
	 */
	private Long orgId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 产品CODE
	 */
	private String code;
	/**
	 * 产品描述
	 */
	private String depict;
	/**
	 * 产品额度上限
	 */
	private BigDecimal floorLimit;
	/**
	 * 产品额度下限
	 */
	private  BigDecimal upperLimit;
	/**
	 * 费率
	 */
	private BigDecimal rate;
	
	/**
	 * 优惠费率
	 */
	private BigDecimal preferentialRate;
	
	/**
	 * 调整基数
	 */
	private BigDecimal adjustBase;
	
	/**
	 * 
	 */
	private BigDecimal debtRadio;
	/**
	 * 错误的产品额度下限值(用于导入excel,生成sql)
	 */
	private String MistakeFloorLimit;
	/**
	 * 错误的产品额度上限值(用于导入excel,生成sql)
	 */
	private String MistakeUpperLimit;
	/**
	 * 错误的调整基数值(用于导入excel,生成sql)
	 */
	private String MistakeAdjustBase;
	/**
	 * 是否是优惠配置，0是 1否
	 */
	private String isCanPreferential;
	
	private List<Long> orgIdList; //机构id集合
	
	private String productIds;//产口id列表
	
	private String channels;
	
	
	public BigDecimal getPreferentialRate() {
		return preferentialRate;
	}
	public void setPreferentialRate(BigDecimal preferentialRate) {
		this.preferentialRate = preferentialRate;
	}
	public String getMistakeFloorLimit() {
		return MistakeFloorLimit;
	}
	public void setMistakeFloorLimit(String mistakeFloorLimit) {
		MistakeFloorLimit = mistakeFloorLimit;
	}
	public String getMistakeUpperLimit() {
		return MistakeUpperLimit;
	}
	public void setMistakeUpperLimit(String mistakeUpperLimit) {
		MistakeUpperLimit = mistakeUpperLimit;
	}
	public String getMistakeAdjustBase() {
		return MistakeAdjustBase;
	}
	public void setMistakeAdjustBase(String mistakeAdjustBase) {
		MistakeAdjustBase = mistakeAdjustBase;
	}
	/**
	 * 导出结果(用于导入excel,生成sql)
	 */
	private String derivedResult;
	/**
	 * 失败原因(用于导入excel,生成sql)
	 */
	private String failureCause;
	
	public String getDerivedResult() {
		return derivedResult;
	}
	public void setDerivedResult(String derivedResult) {
		this.derivedResult = derivedResult;
	}
	public String getFailureCause() {
		return failureCause;
	}
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}
	public BigDecimal getAdjustBase() {
		return adjustBase;
	}
	public void setAdjustBase(BigDecimal adjustBase) {
		this.adjustBase = adjustBase;
	}
	
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	
	
	
	// 每页记录数
	
	public List<Long> getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(List<Long> channelIds) {
		this.channelIds = channelIds;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public BigDecimal getFloorLimit() {
		return floorLimit;
	}
	public void setFloorLimit(BigDecimal floorLimit) {
		this.floorLimit = floorLimit;
	}
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
	
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public BigDecimal getDebtRadio() {
		return debtRadio;
	}
	public void setDebtRadio(BigDecimal debtRadio) {
		this.debtRadio = debtRadio;
	}
	public List<Long> getAuditLimitIds() {
		return auditLimitIds;
	}
	public void setAuditLimitIds(List<Long> auditLimitIds) {
		this.auditLimitIds = auditLimitIds;
	}
	public List<String> getChannelAuditLimitList() {
		return channelAuditLimitList;
	}
	public void setChannelAuditLimitList(List<String> channelAuditLimitList) {
		this.channelAuditLimitList = channelAuditLimitList;
	}
	public String getIsCanPreferential() {
		return isCanPreferential;
	}
	public void setIsCanPreferential(String isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}
	public List<Long> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<Long> orgIdList) {
		this.orgIdList = orgIdList;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	
	
}
