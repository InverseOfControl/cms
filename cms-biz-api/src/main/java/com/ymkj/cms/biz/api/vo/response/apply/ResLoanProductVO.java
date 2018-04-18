package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体类 对应 表 demo
 */
public class ResLoanProductVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id; // id

	private String productCd; //申请产品
 
	private BigDecimal applyLmt; //申请额度
	private Integer applyTerm; //申请期限 
	private Integer ifPri; //是否加急
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public BigDecimal getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(BigDecimal applyLmt) {
		this.applyLmt = applyLmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public Integer getIfPri() {
		return ifPri;
	}
	public void setIfPri(Integer ifPri) {
		this.ifPri = ifPri;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
