package com.ymkj.cms.biz.api.vo.response.audit;

import java.util.List;


import com.ymkj.base.core.biz.api.message.Response;
/**
 * 申请件VO
 * @author YM10161
 *
 */
public class ResBMSQualityInspectionVO extends Response<ResBMSQualityInspectionVO> {

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = 1401065917404587746L;

	private List<ResBMSQualityInspectionAttrVO> list;

	public List<ResBMSQualityInspectionAttrVO> getList() {
		return list;
	}

	public void setList(List<ResBMSQualityInspectionAttrVO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
