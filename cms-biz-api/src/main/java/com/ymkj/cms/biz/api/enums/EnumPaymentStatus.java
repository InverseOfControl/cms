package com.ymkj.cms.biz.api.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ymkj.cms.biz.api.constant.BmsConstant;

public enum EnumPaymentStatus {
	

	/** 报盘文件生成初始状态 **/
	未报盘(EnumLinePayment.未报盘.getValue(),"未报盘"),
	/** 已发送tpp还未回盘 */
	已报盘(EnumLinePayment.已报盘.getValue(),"已报盘"),
	/** 回盘成功 */
	扣款成功(EnumLinePayment.扣款成功.getValue(),"扣款成功"),
	/** 回盘失败 */
	扣款失败(EnumLinePayment.扣款失败.getValue(),"扣款失败");
	
	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private EnumPaymentStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static List<Map<String, String>> queryAllPaymetStatus() {
		List<Map<String, String>> paymentList = new ArrayList<Map<String,String>>();
		for (EnumPaymentStatus status : values()) {
			Map<String, String> paymentMap = new HashMap<String, String>();
			paymentMap.put(BmsConstant.CODE, status.getCode());
			paymentMap.put(BmsConstant.DESC, status.getDesc());
			paymentList.add(paymentMap);
		}
		return paymentList;
	}

}
