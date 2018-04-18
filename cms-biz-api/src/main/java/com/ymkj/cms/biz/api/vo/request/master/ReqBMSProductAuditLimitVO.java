package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 1.demo请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 2.其中必须包含 对应实体类 {Entity}中的 所有字段, 业务不同实体不同  
 * 3.如有需要可 自行扩展
 * @author 
 *
 */
public class ReqBMSProductAuditLimitVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 8656170812642602261L;
	/**
	 * 审批期限ID
	 */
	private Long auditLimitId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 产品代码
	 */
	private String productCode;
	/**
	 * 审批期限
	 */
	private Long auditLimit;
	/**
	 * 阀值上限
	 */
	private BigDecimal upperLimit;
	/**
	 * 阀值下限
	 */
	private BigDecimal floorLimit; 
	/**
	 * 是否可用
	 */
	private Long isDisabled;
	
	/**
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	
	
	
	private int pageNum;     // 当前页数
	private int pageSize;  
	// 每页记录数
	private int rows;// 行数
	private int page;// 页数
	
	/**
	 * 用户code
	 */
	private String userCode;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	/**
	 * 门店ID
	 */
	private Long orgId;
	
	/**
	 * 配置冲突
	 * @return
	 */
	private String configureConflict;
	
	private String productIds;
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
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
	
	public ReqBMSProductAuditLimitVO(){
		
	}
	public ReqBMSProductAuditLimitVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public Long getAuditLimitId() {
		return auditLimitId;
	}
	public void setAuditLimitId(Long auditLimitId) {
		this.auditLimitId = auditLimitId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Long getAuditLimit() {
		return auditLimit;
	}
	public void setAuditLimit(Long auditLimit) {
		this.auditLimit = auditLimit;
	}
	
	public Long getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
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
	public void setPageSize(int numPerPage) {
		this.pageSize = numPerPage;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 用于存储excel中产品名称值(用于导入excel,生成sql)
	 */
	private String productNameExcel;
	public String getProductNameExcel() {
		return productNameExcel;
	}
	public void setProductNameExcel(String productNameExcel) {
		this.productNameExcel = productNameExcel;
	}
	/**
	 * 错误的导入产品名称值(用于导入excel,生成sql)
	 */
	private String MistakeProductName;
	public String getMistakeProductName() {
		return MistakeProductName;
	}
	public void setMistakeProductName(String mistakeProductName) {
		MistakeProductName = mistakeProductName;
	}
	/**
	 * 错误的导入期限值(用于导入excel,生成sql)
	 */
	private String MistakeAuditLimit;
	/**
	 * 错误的导入阀值上限值(用于导入excel,生成sql)
	 */
	private String MistakeUpperLimit;
	/**
	 * 错误的导入阀值下限值(用于导入excel,生成sql)
	 */
	private String MistakeFloorLimit;
/*	*//**
	 * 错误的导入调整基数值(用于导入excel,生成sql)
	 *//*
	private String MistakeAdjustBase;
	*//**
	 * 调整基数
	 *//*
	private BigDecimal adjustBase;*/
	public String getMistakeAuditLimit() {
		return MistakeAuditLimit;
	}
	public void setMistakeAuditLimit(String mistakeAuditLimit) {
		MistakeAuditLimit = mistakeAuditLimit;
	}
	public String getMistakeUpperLimit() {
		return MistakeUpperLimit;
	}
	public void setMistakeUpperLimit(String mistakeUpperLimit) {
		MistakeUpperLimit = mistakeUpperLimit;
	}
	public String getMistakeFloorLimit() {
		return MistakeFloorLimit;
	}
	public void setMistakeFloorLimit(String mistakeFloorLimit) {
		MistakeFloorLimit = mistakeFloorLimit;
	}
	/*public String getMistakeAdjustBase() {
		return MistakeAdjustBase;
	}
	public void setMistakeAdjustBase(String mistakeAdjustBase) {
		MistakeAdjustBase = mistakeAdjustBase;
	}
	public BigDecimal getAdjustBase() {
		return adjustBase;
	}
	public void setAdjustBase(BigDecimal adjustBase) {
		this.adjustBase = adjustBase;
	}*/
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

	/**
	 * 导出结果(用于导入excel,生成sql)
	 */
	private String derivedResult;
	/**
	 * 失败原因(用于导入excel,生成sql)
	 */
	private String failureCause;
	public String getConfigureConflict() {
		return configureConflict;
	}
	public void setConfigureConflict(String configureConflict) {
		this.configureConflict = configureConflict;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	
	
	
	
	
}
