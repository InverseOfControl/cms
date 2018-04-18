package com.ymkj.cms.biz.entity.apply;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class ThirdPartyInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> ifThirdOrgReturn;//第三方接口是否返回结果
	private List<Date> thirdOrgReturnDate;//第三方接口是否返回结果时间
	
	public List<String> getIfThirdOrgReturn() {
		return ifThirdOrgReturn;
	}
	public void setIfThirdOrgReturn(List<String> ifThirdOrgReturn) {
		this.ifThirdOrgReturn = ifThirdOrgReturn;
	}
	public List<Date> getThirdOrgReturnDate() {
		return thirdOrgReturnDate;
	}
	public void setThirdOrgReturnDate(List<Date> thirdOrgReturnDate) {
		this.thirdOrgReturnDate = thirdOrgReturnDate;
	}
}
