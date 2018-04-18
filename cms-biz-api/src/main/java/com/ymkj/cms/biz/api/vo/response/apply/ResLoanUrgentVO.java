package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

public class ResLoanUrgentVO implements Serializable{
	//是否可加急（true 可以加急,false 不可以加急）
	private Boolean isUrgent;
	
	//剩余加急个数
	private Integer overUrgentSize;

	public Boolean getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Integer getOverUrgentSize() {
		return overUrgentSize;
	}

	public void setOverUrgentSize(Integer overUrgentSize) {
		this.overUrgentSize = overUrgentSize;
	}
	
}
