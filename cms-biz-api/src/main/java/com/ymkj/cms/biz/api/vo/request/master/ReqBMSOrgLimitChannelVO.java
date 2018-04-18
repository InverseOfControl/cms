package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSOrgLimitChannelVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621948014430792290L;
	/**
	 * 门店ID字符串，以逗号分隔
	 */
	private String orgIdStr;
	/**
	 * 渠道id和产品期限id,用下划线进行连接
	 */
	private String channelLimitStr;
	
	/**
	 * 渠道的产品期限id集合
	 */
	private String channelLimitIds;
	
	/**
	 * id
	 */
	private Long id;
	/**
	 * 审核期限ID
	 */
	private Long auditLimitId;
	/**
	 *门店ID
	 */
	private Long orgId;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	/**
	 * 产品配置类型
	 */
	private String productDeployCode;
	
	/**
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 产品期限
	 */
	private Integer auditLimit;
	/**
	 * 是否可用
	 */
	private Integer isDisabled;
	
	/**
	 * 审批下限
	 */
	private  BigDecimal floorLimit;
	/**
	 * 审批上限
	 */
	private BigDecimal upperLimit;
	
	/**
	 * 产品code集合
	 */
	private List<String> productCodeList;
	/**
	 * 门店ID集合
	 */
	private List<Long> orgIdList;
	
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 操作人员code
	 */
	private String serviceCode;
	/**
	 * 操作人员姓名
	 */
	private String serviceName;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 申请金额
	 */
	private BigDecimal applyLmt;
	/**
	 * 配置冲突
	 */
	private String configure;
	/**
	 * 渠道产品 是否可用
	 */
	private String blcIsDisabled;
	
	private int page;     // 当前页数
	private int rows; //每页显示条数 
	
	private List<Long>orgIds;//门店id集合
	
	private List<String>channelIds;//渠道分期id集体
	
	private String standard;//基准0为门店1为渠道分期
	
	private String channs;
	private String prods;
	private String limits;
	private String orgs;
	
	
	public String getOrgIdStr() {
		return orgIdStr;
	}
	public void setOrgIdStr(String orgIdStr) {
		this.orgIdStr = orgIdStr;
	}
	public String getChannelLimitStr() {
		return channelLimitStr;
	}
	public void setChannelLimitStr(String channelLimitStr) {
		this.channelLimitStr = channelLimitStr;
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
	public String getChannelLimitIds() {
		return channelLimitIds;
	}
	public void setChannelLimitIds(String channelLimitIds) {
		this.channelLimitIds = channelLimitIds;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getAuditLimit() {
		return auditLimit;
	}
	public void setAuditLimit(Integer auditLimit) {
		this.auditLimit = auditLimit;
	}
	
	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAuditLimitId() {
		return auditLimitId;
	}
	public void setAuditLimitId(Long auditLimitId) {
		this.auditLimitId = auditLimitId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getProductDeployCode() {
		return productDeployCode;
	}
	public void setProductDeployCode(String productDeployCode) {
		this.productDeployCode = productDeployCode;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public ReqBMSOrgLimitChannelVO(){
		
	}
	public ReqBMSOrgLimitChannelVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
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
	
	/**
	 * 网点名称(用于导入excel,生成sql)
	 */
	private String orgName;
	/**
	 * 渠道代码(用于导入excel,生成sql)
	 */
	private String channelCode;
	/**
	 * 渠道名称(用于导入excel,生成sql)
	 */
	private String channelName;
	/**
	 * 产品代码(用于导入excel,生成sql)
	 */
	private String productCode;
	/**
	 * 产品名称(用于导入excel,生成sql)
	 */
	private String productName;
	/**
	 * 产品期限(用于导入excel,生成sql)
	 */
	private String productAuditLimit;


	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductAuditLimit() {
		return productAuditLimit;
	}
	public void setProductAuditLimit(String productAuditLimit) {
		this.productAuditLimit = productAuditLimit;
	}
	
	/**
	 * 错误的产品额度下限值(用于导入excel,生成sql)
	 */
	private String mistakeFloorLimit;
	/**
	 * 错误的产品额度上限值(用于导入excel,生成sql)
	 */
	private String mistakeUpperLimit;


	public String getMistakeFloorLimit() {
		return mistakeFloorLimit;
	}
	public void setMistakeFloorLimit(String mistakeFloorLimit) {
		this.mistakeFloorLimit = mistakeFloorLimit;
	}
	public String getMistakeUpperLimit() {
		return mistakeUpperLimit;
	}
	public void setMistakeUpperLimit(String mistakeUpperLimit) {
		this.mistakeUpperLimit = mistakeUpperLimit;
	}
	public List<String> getProductCodeList() {
		return productCodeList;
	}
	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public List<Long> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<Long> orgIdList) {
		this.orgIdList = orgIdList;
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
	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	private int pageNum;     // 当前页数
	private int pageSize;


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
	public String getConfigure() {
		return configure;
	}
	public void setConfigure(String configure) {
		this.configure = configure;
	}
	public String getBlcIsDisabled() {
		return blcIsDisabled;
	}
	public void setBlcIsDisabled(String blcIsDisabled) {
		this.blcIsDisabled = blcIsDisabled;
	}
	public List<Long> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<Long> orgIds) {
		this.orgIds = orgIds;
	}
	public List<String> getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getChanns() {
		return channs;
	}
	public void setChanns(String channs) {
		this.channs = channs;
	}
	public String getProds() {
		return prods;
	}
	public void setProds(String prods) {
		this.prods = prods;
	}
	public String getLimits() {
		return limits;
	}
	public void setLimits(String limits) {
		this.limits = limits;
	}
	public String getOrgs() {
		return orgs;
	}
	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
