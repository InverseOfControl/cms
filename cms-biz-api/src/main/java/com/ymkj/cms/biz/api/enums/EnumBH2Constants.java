package com.ymkj.cms.biz.api.enums;

import java.util.HashMap;
import java.util.Map;

import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;

/**
 * @author YM10189
 * @date 2017年5月26日
 * @Description:渤海二枚举
 */
public enum EnumBH2Constants {

	/** 字段code **/
	放款本金("FKBJ"), 手续费("SXF"), CNY("CNY"),

	/**上传下载**/
	downLoad("01"), upload("02"),

	/** 报盘状态 **/
	未报盘("0"), 已报盘("1"), 扣款成功("2"), 扣款失败("3"),

	/** 项目简码 ***/
	渤海项目简码("ZDCF02", "渤海2"),

	/** 渠道枚举 **/
	证大P2P("00001", "证大P2P"), 证大爱特("00003", "证大爱特"), 证大爱特2("00006", "证大爱特2"), 积木盒子("00007", "积木盒子"), 向上360("00002", "向上360P"), 华澳信托("00005", "华澳信托"), 国民信托("00004", "国民信托"), 挖财2("00008", "挖财2"), 海门小贷("00011", "海门小贷"), 渤海信托("00012", "渤海信托"), 龙信小贷("00013", "龙信小贷"), 外贸信托("00014", "外贸信托"), 渤海2("00015", "渤海2"), 捞财宝("00016", "捞财宝"), 外贸2("00017", "外贸2"), 包商银行("00018", "包商银行"), 华瑞渤海("00020", "华瑞渤海"),

	/**合同协议**/
	渤海2_电子签章_借款协议("01","00141-01"),渤海2_电子签章_个人贷款咨询服务协议("02","00142-02"),渤海2_套打_贷款合同("00","00139"),渤海2_套打_借款咨询服务协议("00","00138"),
	
	华瑞渤海_电子签章_个人借款咨询服务协议("02","00006-02"),华瑞渤海_电子签章_贷款合同("01","00005-01"),华瑞渤海_套打_个人借款咨询服务协议("00","00001"),华瑞渤海_套打_贷款合同("00","00002"),
	
	合同配置(new String[][]{{"00015","1","01","00141-01","S1"},{"00015","1","02","00142-02","S8"},{"00015","1","04","00140-04","S2"},{"00015","0","00","00139",""},{"00015","0","00","00138",""},{"00015","0","00","00140",""},{"00020","1","02","00006-02","S8"},{"00020","1","01","00005-01","S1"},{"00020","1","04","00003-04","S2"},{"00020","0","00","00001",""},{"00020","0","00","00002",""},{"00020","0","00","00003",""},{"00021","1","03","00002-03","S11"},{"00021","1","02","00003-02","S8"},{"00021","1","04","00004-04","S2"},{"00021","0","00","00002",""},{"00021","0","00","00003",""},{"00021","0","00","00004",""},{"00016","1","02","00004-02","S8"},
			{"00016","1","03","00002-03","S11"},{"00016","1","05","00008-05","S15"},{"00016","1","04","00006-04","S2"},
			//包商银行电子版 个人借款咨询服务风险基金协议0001851  个人借款咨询服务协议0001861  还款提示函0001821
			{"00018","1","03","000185-03","S11"},{"00018","1","02","000186-02","S8"},{"00018","1","04","000182-04","S2"},
			//白银纸质版
			{"00018","0","00","000181",""},{"00018","0","00","000182",""},/*{"00018","0","00","000183",""},*/{"00018","0","00","000184",""},{"00018","0","00","000185",""},{"00018","0","00","000186",""}}),
	
	/** 申请书导出文件 **/
	划拨申请书pdf("01", "_apply_", ".pdf"), 划拨申请书xls("02", "_apply_", ".xls"), 放款申请书txt("03", "_loanapply_", ".txt"), 放款申请书xls("04", "_loanapply_", ".xls"), 还款计划xls("05", "_payplan_", ".xls"), 放款明细xls("06", "_loandetail_", ".xls"), 放款明细txt("07", "_loandetail_", ".txt"), 还款计划导出txt("08", "_RetuPlan", ".txt"), 债权导出供理财txt("08", "_LoanApply", ".txt"), 回款确认书("09", "_paylogsum_", ".xls"), 分账明细表("10", "_paylog_", ".xls"), 实分账明细表("11", "_paylogdetail_", ".xls"),

	/** 还款计划导出 **/
	REPAYMENT_PLAN_EXP(new String[] { "projectcode", "loanNo", "loanNo", "currentTerm", "returnDate", "currentPrincipal", "currentAccrual", "counterFee", "assureFee", "serviceFee", "fee1", "fee2", "fee3", "principalBalance", "repaymentAll" }, new String[] { "信托项目简码", "借款编号", "还款类型", "期次", "应还款日期", "应还本金", "应还利息", "应还手续费", "应还担保费", "应还服务费", "应还其他费1", "应还其他费2", "应还其他费3", "剩余本金", "一次性结清应还金额" }, ResRepaymentExpVo.class),

	/** 放款申请明细导出 **/
	LOAN_APP_DETAIL_EXP(new String[] { "creditCode", "contractNum", "requestPlace", "requestNo", "channel", "idType", "idnum", "name", "contactPhone", "mphone", "postcode", "address", "purpose", "pactMoney", "money", "currency", "time", "accountType", "backAccount", "repaymentMethod", "repaymentDayType", "repaymentDayCategory", "promiseReturnDate", "married", "edLevel", "hrAddress", "totalMonthlyIncome", "familyAddress", "familyPostcode", "familyPhone", "handleOrg", "payMethod", "loanType", "customerType", "productCode", "productName", "counterFee", "counterRate", "rate", "defaultRate", "penaltyRate", "assureDays", "serviceFee", "serviceFeeRate", "assureFee", "assureFeeRate", "bankCode", "openAccountCity", "fee1", "fee2", "fee3", "fee4", "fee5", "profession", "company", "industryType", "cAddress", "cCode", "startYear", "officialRank", "staff", "assureMethod", "assureName", "assureIdType", "assureIdnum", "assureAmount", "assureRelation", "lenderAcountType", "lenderBankCode", "lenderAcountName", "lenderAcount", "lenderBranchBank", "lenderAcountProvince", "lenderAcountCity", "applyName", "loanDate", "endrdate", "rateType", "valueDate" }, new String[] { "信托项目简码", "合同号/借款编号", "申请地点", "申请号", "渠道来源", "证件类型", "证件号码", "姓名", "联系电话", "移动电话", "邮政编码", "通讯地址", "申请用途", "合同金额", "实付金额", "申请币种", "申请期限(月)", "还款帐户类型", "还款帐号", "还款方式", "扣款日类型", "扣款日类别", "扣款日期", "婚姻状况", "学历", "户籍", "个人月收入", "家庭住址", "家庭邮编", "住宅电话", "经办机构", "缴费方式", "贷款类型", "借款人类型", "产品编号", "产品名称", "手续费", "手续费率", "利率(月)", "提前还款违约金比率", "罚息率(月)", "履行担保天数", "服务费", "服务费率", "担保费", "担保费率", "银行代码", "开户省市", "费用一", "费用二", "费用三", "费用四", "费用五", "职业", "单位名称", "单位所属行业", "单位地址", "单位邮政编码", "本单位工作起始年份", "本人职务", "本人职称", "担保方式", "担保人姓名", "担保人证件类型", "担保人证件号码", "担保金额", "担保关系", "放款账户类型(*)", "放款银行代码(*)", "放款账户名称(*)", "放款账户号码(*)", "放款账户开户支行", "放款账户开户所在省", "放款账户开户所在市", "划拨申请书文件名称", "放款日期", "到期日期", "利率类型", "起息日期" }, LoanApplyDetailVo.class),

	/** 债权审核导出 **/
	LOAN_CHECK_EXP(new String[] { "productName", "pactMoney", "time", "loanDate", "startRdate", "currentPrincipal", "dueDate", "creditApplication", "name", "age", "gender", "idNo", "qualification", "homeAddress", "maritalStatus", "corpStructure", "corpStandFrom", "corpPost", "carTypr", "estateAmt", "estateType", "builtupArea", "priEnterpriseType", "setupDate", "sharesScale" }, new String[] { "产品类型", "借款金额（万元）","借款期限（期）", "借款发放日期", "还款日期", "月还款额", "借款到期日期", "借款用途", "姓名", "年龄", "性别", "身份证号", "学历", "现居住地址", "婚姻状况", "单位性质", "本工作开始日期", "职位", "车型", "购买价格", "房产类型", "建筑面积", "经营主体类型", "成立年限", "融资人持股比例" }, ResLoanCheckExpVo.class),

	/** 债权导出 **/
	LOAN_EXP(new String[] { "loanNo", "contractId", "productName", "creditAppliction", "pactMonney", "loanLmt", "payLmt", "loanDate", "name", "sex", "idNo", "projectBacthNum", "bankName", "bankFullName", "account", "grantMonney", "serviceMonney", "signDate", "dueDate", "returneterm", "loanBranck" }, new String[] { "借款ID", "合同编号", "产品类型", "借款用途", "合同金额", "借款期限", "已还期数", "首还款日期", "真实姓名", "性别", "身份证号", "产品批次号", "放款银行卡开户行", "支行", "卡号", "放款金额", "服务费金额", "债权签约日期", "到期日", "每月还款金额", "放款营业部" }, ResLoanExpVo.class);
	private String value;

	private String code;

	private String type;

	private String name;

	private String[] excCode;

	private String[] excName;

	private Class<?> classObj;
	
	private String channelCd;
	
	private String[][]contractConf;

	/**
	 * 字段code,报盘状态
	 * 
	 * @param value
	 */
	private EnumBH2Constants(String value) {
		this.value = value;
	}

	/**
	 * 项目简码,渠道枚举
	 * 
	 * @param code
	 * @param value
	 */
	private EnumBH2Constants(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 申请书文件名
	 * 
	 * @param code
	 * @param name
	 * @param type
	 */
	private EnumBH2Constants(String code, String name, String type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	/**
	 * 报表导出
	 * 
	 * @param excCode
	 * @param excName
	 * @param classObj
	 */
	private EnumBH2Constants(String[] excCode, String[] excName, Class<?> classObj) {
		this.excCode = excCode;
		this.excName = excName;
		this.classObj = classObj;
	}
	
	/**
	 * 合同模板配置
	 * @param channelCd
	 * @param contractConf
	 */
	private EnumBH2Constants(String[][]contractConf){
		this.contractConf=contractConf;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getExcCode() {
		return excCode;
	}

	public void setExcCode(String[] excCode) {
		this.excCode = excCode;
	}

	public String[] getExcName() {
		return excName;
	}

	public void setExcName(String[] excName) {
		this.excName = excName;
	}

	public Class<?> getClassObj() {
		return classObj;
	}

	public void setClassObj(Class<?> classObj) {
		this.classObj = classObj;
	}
	
	
	public String getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
	}

	public String[][] getContractConf() {
		return contractConf;
	}

	public void setContractConf(String[][] contractConf) {
		this.contractConf = contractConf;
	}
	
	public static Map<String,String> getTmpprotocol(String channelCd,String contractType,String tempCode){
		Map<String,String> dataMap=new HashMap<String,String>();
		String[][]arys=EnumBH2Constants.合同配置.getContractConf();
		for (int i = 0; i < arys.length; i++) {
			String[] obj=arys[i];
			if(channelCd.equals(obj[0])&&contractType.equals(obj[1])&&tempCode.equals(obj[3])){
				String protocol=obj[2];
				dataMap.put("protocol", protocol);
				dataMap.put("tempCode", tempCode);
				dataMap.put("foldName", obj[4]);
				break;
			}
		}
		return dataMap;
	}

}
