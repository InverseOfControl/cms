package com.ymkj.cms.biz.api.enums;

public enum EnumAppStateConstants {
/*	//待签约 已结清 拒绝 逾期 取消 申请中 正常 待放款
*/	 
	 WZ("0","未知"),
	 YQ("1","已结清"), 
	 JQ("2","逾期"), 
	 ZC("3","正常"), 
	 SQZ("4","申请中"), 
	 DQY("5","待签约"), 
	 DFK("6","待放款"), 
	 QX("7","取消"),
	 JJ("8","拒绝"),
	 XSTH("9","信审退回");
	
	private String value;
	
	private String code;

	private EnumAppStateConstants(String value) {
		this.value = value;
	}
	
	private EnumAppStateConstants(String code, String value) {
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
		for(EnumAppStateConstants enumAppStateConstants:values()){
			if(enumAppStateConstants.getCode().equals(code))
				return enumAppStateConstants.getValue();
		}
		return null;
	}
	
	public static String getAppStateCodeByValue(String value) {
		for(EnumAppStateConstants enumAppStateConstants:values()){
			if(enumAppStateConstants.getValue().equals(value))
				return enumAppStateConstants.getCode();
		}
		return null;
	}
}
