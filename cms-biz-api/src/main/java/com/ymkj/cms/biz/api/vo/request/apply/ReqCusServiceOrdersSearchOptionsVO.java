package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 客服接单员工信息查询接口--入参
 * @author YM10152-CYB
 *
 */
public class ReqCusServiceOrdersSearchOptionsVO extends Request{

	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 1L;
	
	private String manageBranchCode;	//管理营业部			
	private String staffCode;			//员工编号			
	private String userName;			//人员姓名		
	
	private String serviceCode;			//操作人工号
	private String serviceName;			//操作人姓名
	private String ip;					//操作ip

	private String ifAccept;   			//是否接单	Y:允许接单 N:禁止接单
	
	
	public String getManageBranchCode() {
		return manageBranchCode;
	}
	public void setManageBranchCode(String manageBranchCode) {
		this.manageBranchCode = manageBranchCode;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIfAccept() {
		return ifAccept;
	}
	public void setIfAccept(String ifAccept) {
		this.ifAccept = ifAccept;
	}
	
}
