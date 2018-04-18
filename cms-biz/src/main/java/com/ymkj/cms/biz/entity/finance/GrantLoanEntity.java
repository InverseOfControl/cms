package com.ymkj.cms.biz.entity.finance;

import java.util.List;

public class GrantLoanEntity {
	private String userCode;
	
	private List<GrantInfo> infos;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List<GrantInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<GrantInfo> infos) {
		this.infos = infos;
	}

	


}