package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 客服接单员工信息查询接口--出参
 * @author YM10152-CYB
 *
 */
public class ResCusServiceOrdersSearchVO extends Request{

	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 1L;
	
	private String userCode;		//人员Id
	private String userName;	//人员姓名
	private String orgName;		//组织名称
	private String ifAccept;	//是否接单	Y:允许接单 N:禁止接单
	
	
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getIfAccept() {
		return ifAccept;
	}
	public void setIfAccept(String ifAccept) {
		this.ifAccept = ifAccept;
	}
}
