package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSProductCodeModuleVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 913656038295839195L;
	
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 枚举（模块）ID
	 */
	private Long codeId;
	
	/**
	 * 是否删除标识 
	 */
	private Long isDeleted;
	
	/**
	 * 版本号
	 */
	private Long version;
	
	
	/**
	 * 产品当前的模块Ids
	 */

	private String[] productModuleIds;


	/**
	 * 产品当前的模块CodeIds
	 */
	
	private String[] productCodeIds;
	

	private String[] initProducts;
	
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
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCodeId() {
		return codeId;
	}
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
	

	public String[] getProductCodeIds() {
		return productCodeIds;
	}
	public void setProductCodeIds(String[] productCodeIds) {
		this.productCodeIds = productCodeIds;
	}
	
	public String[] getProductModuleIds() {
		return productModuleIds;
	}
	public void setProductModuleIds(String[] productModuleIds) {
		this.productModuleIds = productModuleIds;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String[] getInitProducts() {
		return initProducts;
	}
	
	public void setInitProducts(String[] initProducts) {
		this.initProducts = initProducts;
	}
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/**
	 * 导出结果(用于导入excel,生成sql)
	 */
	private String derivedResult;
	/**
	 * 失败原因(用于导入excel,生成sql)
	 */
	private String failureCause;
	/**
	 * 产品代码(用于导入excel,生成sql)
	 */
	private String productCodeExcel;
	
	/**
	 * 产品名称(用于导入excel,生成sql)
	 */
	private String productNameExcel;
	
	/**
	 * 模块名称(用于导入excel,生成sql)
	 */
	private String codeNameExcel;
	
	/**
	 * 错误的产品代码(用于导入excel,生成sql)
	 */
	private String MistakeProductCodeExcel;
	
	/**
	 *错误的产品名称(用于导入excel,生成sql)
	 */
	private String MistakeProductNameExcel;
	/**
	 * 排序字段(用于导入excel,生成sql)
	 */
	private String sort;
	

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getMistakeProductCodeExcel() {
		return MistakeProductCodeExcel;
	}
	public void setMistakeProductCodeExcel(String mistakeProductCodeExcel) {
		MistakeProductCodeExcel = mistakeProductCodeExcel;
	}
	public String getMistakeProductNameExcel() {
		return MistakeProductNameExcel;
	}
	public void setMistakeProductNameExcel(String mistakeProductNameExcel) {
		MistakeProductNameExcel = mistakeProductNameExcel;
	}
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
	public String getProductCodeExcel() {
		return productCodeExcel;
	}
	public void setProductCodeExcel(String productCodeExcel) {
		this.productCodeExcel = productCodeExcel;
	}
	public String getProductNameExcel() {
		return productNameExcel;
	}
	public void setProductNameExcel(String productNameExcel) {
		this.productNameExcel = productNameExcel;
	}
	public String getCodeNameExcel() {
		return codeNameExcel;
	}
	public void setCodeNameExcel(String codeNameExcel) {
		this.codeNameExcel = codeNameExcel;
	}

	

}
