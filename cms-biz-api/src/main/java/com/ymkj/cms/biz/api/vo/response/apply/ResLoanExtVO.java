package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * 实体类 对应 表 demo
 */
public class ResLoanExtVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	private Long id; 
	private String creditApplication;//贷款用途

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCreditApplication() {
		return creditApplication;
	}


	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	

	
 

}
