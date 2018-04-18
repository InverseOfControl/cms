package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPPersonVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	private Long id;
	
	 
	private String name; // 名称
	
	private String perosnNo;
	
	 
	  
	private String idNo;
	
	private String idType ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerosnNo() {
		return perosnNo;
	}

	public void setPerosnNo(String perosnNo) {
		this.perosnNo = perosnNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	} 
	 
}
