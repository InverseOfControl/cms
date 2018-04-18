package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPSalaryLoanInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;

	private String conditionType;//条件类型

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

 

}
