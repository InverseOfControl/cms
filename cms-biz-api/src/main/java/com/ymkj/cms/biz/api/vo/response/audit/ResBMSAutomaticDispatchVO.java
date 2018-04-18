package com.ymkj.cms.biz.api.vo.response.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;

/**
 * 自动派单接口响应VO
 * @author YM10161
 *
 */
public class ResBMSAutomaticDispatchVO extends Response<ResBMSAutomaticDispatchVO> {

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = -6303120676004780370L;

	private List<ResBMSAutomaticDispatchAttrVO> list;

	public List<ResBMSAutomaticDispatchAttrVO> getList() {
		return list;
	}

	public void setList(List<ResBMSAutomaticDispatchAttrVO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
