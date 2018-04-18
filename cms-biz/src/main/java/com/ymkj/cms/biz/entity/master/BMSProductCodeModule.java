package com.ymkj.cms.biz.entity.master;

public class BMSProductCodeModule extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4902324736020558206L;
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
	 * 枚举名称(前台展示用)
	 */
	private String name;
	
	/**
	 * 枚举中文名称(前台展示用)
	 */
	private String nameCN;
	
	

	
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

	
	

}
