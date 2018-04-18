package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSDebtRadio extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5896751008129929949L;
	
	private Integer id;
	
	private String customerTypeCode;   //客户类型code
	
	private String customerTypeName;  //客户类型名称
	
	private BigDecimal totalDebtRadio;  //总负债率
	
	private BigDecimal internalDebtRadio;  //内部负债率
	
    private Integer version;
    
    private String modifier; 
    
    private Date modifierDate;
    
    private String modifierId;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerTypeCode() {
		return customerTypeCode;
	}

	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public BigDecimal getTotalDebtRadio() {
		return totalDebtRadio;
	}

	public void setTotalDebtRadio(BigDecimal toalDebtRadio) {
		this.totalDebtRadio = toalDebtRadio;
	}

	public BigDecimal getInternalDebtRadio() {
		return internalDebtRadio;
	}

	public void setInternalDebtRadio(BigDecimal internalDebtRadio) {
		this.internalDebtRadio = internalDebtRadio;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	
	

}
