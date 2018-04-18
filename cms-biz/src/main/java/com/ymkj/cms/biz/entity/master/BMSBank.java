package com.ymkj.cms.biz.entity.master;

/**
 * 银行表对应的实体类
 * @author YM10115
 *
 */
public class BMSBank extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369946015268396394L;
	
	/**
	 * 银行ID
	 */
	private Long Id;
	
	/**
	 * 银行名称
	 */
	private String name;
	
	/**
	 * 银行编码
	 */
	private String code;



	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
	
	/**
	 * 是否删除
	 */
	private Long isDeleted;

	private Long  isDisabled;

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	

}
