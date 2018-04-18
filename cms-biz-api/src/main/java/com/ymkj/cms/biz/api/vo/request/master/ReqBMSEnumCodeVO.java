package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSEnumCodeVO  extends Request{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4107156351656074766L;
	/**
	 * 产品ID
	 */
	private Long productId;
	
	/**
	 * 产品Code
	 */
	private String productCode;

	/**
	 * id
	 */
	private Long codeId;
	/**
	 * 枚举CODE
	 */
	private String code;
	/**
	 * 枚举名称
	 */
	private String name;
	/**
	 * 枚举中文名称
	 */
	private String nameCN;
	/**
	 * 枚举code类型
	 */
	private String codeType;
	/**
	 * 索引
	 */
	private Long codeIndex;
	
	private String org;
	
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
	
	private int rows;// 行数
	private int page;// 页数
	
	//上一级 客户工作类型CODE
	private String parentCode;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public ReqBMSEnumCodeVO() {
	}
	
	public ReqBMSEnumCodeVO(String sysCode) {
		super.setSysCode(sysCode);
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public Long getCodeIndex() {
		return codeIndex;
	}
	public void setCodeIndex(Long codeIndex) {
		this.codeIndex = codeIndex;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
