package com.ymkj.cms.biz.api.constant;

public class BmsConstant {

	//导出excele文件的后缀
	public static final String EXCEL_SUFFIX = ".xls";
	//债权审核
	public static final String ZQSH = "债权审核";
	//债权
	public static final String ZQ = "债权";
	//批次号汉字
	public static final String PCH = "批次号";
	//成功
	public static final String SUCCESS="成功";
	//失败
	public static final String FAILED="失败";
	//龙信小贷导入excel文件后缀
	public static final String LXXD_EXCEL_SUFFIX = ".xlsx";
	// 龙信小贷导入excel追加一栏的title
	public static final String FEED_BACK = "反馈结果";
	//龙信小贷导入出错信息
	public static final String[] ERRORM_MSG={"借款信息不存在","金额不匹配，请重新核对","放款时间小于申请时间",
		                                     "结果状态为失败不做导入处理","已经放款成功，无法再次放款","导出成功",
		                                     "接口异常，放款确认更新失败"};
	//文件格式不合法
	public static final String FILE_TYPE_ILLEGAL = "文件格式不合法";
	//龙信小贷导入放款文件第一行title
	public static final String ROW_ONE_TITLE="“龙证贷”贷款发放确认表";
	//龙信小贷导入放款文件第二行title
	public static final String ROW_TWO_TITLE="编制单位：龙信小贷";
	//放款状态code
	public static final String CODE = "code";
	//放款状态desc
	public static final String DESC = "desc";
	//UTF-8编码
	public static final String UTF_8 = "UTF-8";
	//ISO-8859-1编码
	public static final String ISO_8859_1 = "ISO-8859-1";
	
}
