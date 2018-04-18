package com.ymkj.cms.biz.entity.master;

public class BMSEnumCode  extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -453203369877652571L;
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
	
	
	
}
