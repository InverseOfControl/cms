package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResBMSDebtRadioVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4159940270779555547L;

	private Integer id;
	
	private String customerTypeCode;   //客户类型code
	
	private String customerTypeName;  //客户类型名称
	
	private BigDecimal totalDebtRadio;  //总负债率
	
	private BigDecimal internalDebtRadio;  //内部负债率
	
    private Integer version;

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

	public void setTotalDebtRadio(BigDecimal totalDebtRadio) {
		this.totalDebtRadio = totalDebtRadio;
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
    
    
}
