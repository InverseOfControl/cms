package com.ymkj.cms.biz.api.enums;

/**
 * @author YM10193
 * 渠道code以及渠道简称
 */
public enum EnumChannelCode {
	
	国民信托("00004","GMXT"),
	海门小贷("00011","HMXD"),
	龙信小贷("00013","LXXD"),
	渤海2("00015","BH2"),
	外贸信托("00014","WMXT");
	private String channelCode;
	private String channelShort;

	
	
	public String getChannelCode() {
		return channelCode;
	}



	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}



	public String getChannelShort() {
		return channelShort;
	}



	public void setChannelShort(String channelShort) {
		this.channelShort = channelShort;
	}



	private EnumChannelCode(String channelCode, String channelShort) {
		this.channelCode = channelCode;
		this.channelShort = channelShort;
	}



	public static String getChannelShort(String code) {
		for(EnumChannelCode channelCode:values()){
			if(channelCode.getChannelCode().equals(code))
				return channelCode.getChannelShort();
		}
		return null;
	}

	
	
}
