package com.ymkj.cms.biz.exception;

/**
 * demo 异常枚举, 可自行定义(非核心包中的错误类型), 须遵守规范 ( CoreErrorCode 提供常见的校验类型)
 */
public enum BizErrorCode {
	
	// 参数校验错误可自行定义, 0 开头
	BANK_CARD_NO_VAULE_EOERR("000070","银行卡格式不符合规范！"),
	BAOYIN_IMAGE_MISS("000080","包银渠道附件缺失"),
	REQUEST_PARAM_ISNULL("000001","{0} 对象为空"),
	// 系统错误可自行定义, 1 开头
	VERSION_EOERR("100001","当前version(版本)非最新版本，无法进行操作"),
	UFLOWORKFLOW_EOERR("100020","UFLO工作流运行异常"),
	UFLOTASK_EOERR("100020","借款不在当前任务节点，请刷新"),
	FILEPATH_NOT_FOUND("100021","输出文件路径不存在"),
	SYSTEM_ERROR("100001", "系统错误"),
	// 业务错误，2开头
	RTF_NOT_STATUS_EOERR("200001","当前操作与当前借款状态不符，无法操作！"),
	PRODUCT_ISDISABLED("200020","产品不可用"),
	ORG_NOTFOUND("200030","该客服未找到相应营业部"),
	MANAGER_NOTFOUND("200040","该客服未找到上级经理副理"),
	OPRATEUSER_EOERR("200050","处理人异常请刷新页面"),
	SERVICE_NOT_REGISERED("200060","签约渠道服务节点未注册"),
	BAOYIN_REFUSE("200070","包银渠道交易拒绝"),
	BAOYIN_UNSUBMIT("200080","包银App未完成提交"),
	BAOYIN_FILENAME_ERROR("200090","贷款合同文件命名不符合规范"),
	CLAIM_EOERR("200100","认领失败"),
	BAOYIN_DZQZ_UNDONE("200200","电子签章未签订"),

	// db数据库业务操作错误, 3开头
	DB_RESULT_ISNULL("300006", "数据查询结果为空"),
	DB_RESULT_ERROR("100003", "数据操作失败"),
	DB_ERROR("300000", "数据库操作错误"), 
	DB_UPDATE_RESULT_0("300002","数据库操作,update返回0"),
	EXISTING_APPLY_LOAN("000020", "该客户已存在申请中状态的贷款，不允许再申请"), 
	DISSATISFACTION_RESTRICT_DAYS("000021", "未满拒贷、取消再提交限制天数"),
	DISSATISFACTION_PROTECT_DAYS("000022", "客户在保护期内"),
	DIFFERENT_STORES_DISSATISFACTION_RESTRICT_DAYS("000023", "跨门店未满拒贷、取消再提交限制天数"),
	
	EXISTING_APPLY_LOAN_CANCEL("000024", "该申请单状态已取消，无法重复取消"), 
	EXISTING_APPLY_LOAN_REFUSE("000029", "该申请单状态已拒绝，无法重复拒绝"), 
	APPLY_LOAN_CANCEL("000025", "该申请单状态已取消"), 

	CREATEFILE_EOERR("100022","生成文件异常"),
	BIZ_EOERR("000025","对象为空或空字符串"),
	BIZ_PAGE_EOERR("000026","当前页或一页显示的条数对象为零"),
	BIZ_VALUE_EOERR("000026","值不符合要求"),
	
	APPLY_IN_EOERR("000027","重复入单，当前申请件已进入[{0}]流程"),
	APPLY_BEING_REJECTED_EOERR("000028","前一个借款单被拒绝"),
	APPLY_VALUE_EOERR("009999","字段  {0} 未按要求传值！"),
	LENGTH_EOERR("000030","字段  {0},超过长度{1} 未按要求传值！"),
	LOAN_NO_OR_STATUS_NOT_EXITS("000030","借款单号或流程节点状态未按要求传值"),
	
	EOERR("99999","错误，错误:[{0}]"),
	
	// 不确定类型的错误
	NO_RULE_EXECUTE_ERROR("999999","条件不匹配任一规则"),
	
	
	IF_FRI_EOERR("000034","已经没有加急件可用！"),
	OFFER_OPERATE("000088", "报盘操作异常"), 
	FACADE_RESPONSE_FAIL("400002", "接口调用返回失败消息"),
	REQUEST_PARAM_ERROR("000003","{0} 参数不合法"),
	ENUM_EOERR("000032","枚举值异常，当前枚举值不符合规范，[{0}]");
	
	
	
	
	private String code;

	private String defaultMessage;

	BizErrorCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getErrorCode() {
		return this.code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

}
