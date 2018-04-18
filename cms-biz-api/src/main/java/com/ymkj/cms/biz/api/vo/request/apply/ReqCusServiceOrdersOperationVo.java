package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 客服接单开启关闭接单接口--入参
 * @author YM10152-CYB
 *
 */
public class ReqCusServiceOrdersOperationVo extends Request{

	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> userIdList;	//选中的UserId集合
	private String ifAccept;			//是否接单	Y:是 N:否
	private String serviceCode;			//操作人工号
	private String serviceName;			//操作人姓名
	private String ip;					//操作ip
	
	
	
	public List<String> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	public String getIfAccept() {
		return ifAccept;
	}
	public void setIfAccept(String ifAccept) {
		this.ifAccept = ifAccept;
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
	
}
