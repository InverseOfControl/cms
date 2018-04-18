package com.ymkj.cms.biz.api.enums;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘枚举
 */
public enum EnumLinePayment {

	/** 报盘划扣放款本金 **/
	放款本金("FKBJ"),
	/** 报盘划扣手续费 **/
	手续费("SXF"),
	/**币种**/
	CNY("CNY"),
	
	证大P2P("00001","证大P2P"), 
	证大爱特("00003","证大爱特"), 
	证大爱特2("00006","证大爱特2"), 
	积木盒子("00007","积木盒子"), 
	向上360("00002","向上360P"), 
	华澳信托("00005","华澳信托"), 
	国民信托("00004","国民信托"),
	挖财2("00008","挖财2"),
	海门小贷("00011","海门小贷"),
	渤海信托("00012","渤海信托"),
	龙信小贷("00013","龙信小贷"),
	外贸信托("00014","外贸信托"),
	渤海2("00015","渤海2"),
	捞财宝("00016","捞财宝"),
	外贸2("00017","外贸2"),
	包商银行("00018","包商银行"),
	张三测试("00088","渤海二测试"),

	/** 报盘文件生成初始状态 **/
	未报盘("0"),
	/** 已发送tpp还未回盘 */
	已报盘("1"),
	/** 回盘成功 */
	扣款成功("2"),
	/** 回盘失败 */
	扣款失败("3");

	private String value;
	
	private String code;

	private EnumLinePayment(String value) {
		this.value = value;
	}
	
	private EnumLinePayment(String code, String value) {
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
	
	

}
