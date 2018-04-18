package com.ymkj.cms.biz.api.enums;

public enum EnumLuJSConstants {
/*	//待签约 已结清 拒绝 逾期 取消 申请中 正常 待放款
*/	 
	
	TG	("005" ,"通过"),	
	NTG	("00401", "不通过"),	
	BCZL("013", "补充材料"),	
	SJCG("008", "上架成功"),	
	TZCG("009", "投资成功"),	
	FKCG("010", "放款成功	"),
	SQEDQX("00201", "申请进件额度不够时的人工取消"),
	QREDQX("00601", "确认合同额度不够时的人工取消");
	
	private String value;
	
	private String code;

	private EnumLuJSConstants(String value) {
		this.value = value;
	}
	
	private EnumLuJSConstants(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static String getAppStateValueByCode(String code) {
		for(EnumLuJSConstants enumAppStateConstants:values()){
			if(enumAppStateConstants.getCode().equals(code))
				return enumAppStateConstants.getValue();
		}
		return null;
	}
	
	public static String getAppStateCodeByValue(String value) {
		for(EnumLuJSConstants enumAppStateConstants:values()){
			if(enumAppStateConstants.getValue().equals(value))
				return enumAppStateConstants.getCode();
		}
		return null;
	}
}
