package com.ymkj.cms.biz.common.util;

public interface ParametersType {

	/**
	 * 私营企业类型
	 * @author YM10152
	 */
	public static interface PriEnterpriseType {
		public final static String _00001 = "00001"; // 个体户
		public final static String _00002 = "00002"; // 独资
		public final static String _00003 = "00003"; // 合伙制
		public final static String _00004 = "00004"; // 股份制
		public final static String _00005 = "00005"; // 其他
	}

	/**
	 *
	 * 买家/卖家 信用等级
	 * @author YM10152
	 */
	public static interface SellerCreditLevel {
		public final static String _A = "A"; // 1
		public final static String _B = "B"; // 2
		public final static String _C = "C"; // 3
		public final static String _D = "D"; // 4
		public final static String _E = "E"; // 5
	}

	/**
	 * 公司行业类别
	 * @author YM10152
	 */
	public static interface EmpType {
		public final static String _00001 = "00001"; // 农、林、牧、渔业，能源、采矿业
		public final static String _00002 = "00002"; // 食品、药品、工业原料、服装、日用品等制造业
		public final static String _00003 = "00003"; // 电力、热力、燃气及水生产和供应业
		public final static String _00004 = "00004"; // 建筑业
		public final static String _00005 = "00005"; // 批发和零售业
		public final static String _00006 = "00006"; // 交通运输、仓储和邮政业
		public final static String _00007 = "00007"; // 住宿、旅游、餐饮业
		public final static String _00008 = "00008"; // 信息传输、软件和信息技术服务业
		public final static String _00009 = "00009"; // 金融业
		public final static String _00010 = "00010"; // 房地产业
		public final static String _00011 = "00011"; // 租赁和商务服务业
		public final static String _00012 = "00012"; // 科学研究和技术服务业
		public final static String _00013 = "00013"; // 水利、环境和公共设施管理业
		public final static String _00014 = "00014"; // 居民服务、修理和其他服务业
		public final static String _00015 = "00015"; // 教育、培训
		public final static String _00016 = "00016"; // 卫生、医疗、社会保障、社会福利
		public final static String _00017 = "00017"; // 文化、体育和娱乐业
		public final static String _00018 = "00018"; // 政府、非赢利机构和社会组织
		public final static String _00019 = "00019"; // 警察、消防、军人
		public final static String _00020 = "00020"; // 其他
	}

	/**
	 * 工商网信息
	 * @author YM10152
	 */
	public static interface BusinessNetWork {
		public final static String _A = "A"; // 在营
		public final static String _C = "C"; // 注销/吊销/过期
		public final static String _N = "N"; // 查无
	}

	/**
	 * 房产所有权
	 * @author YM10152
	 */
	public static interface HouseOwnerType {
		public final static String _Y = "Y"; // 客户独有
		public final static String _O = "O"; // 客户共有
		public final static String _N = "N"; // 非本人名下
	}

	/**
	 *
	 *
	 * @author YM10152
	 */
	public static interface CusWorkType {
		public final static String _Y = "Y"; // 私营业主
		public final static String _N = "N"; // 受薪人士
		public final static String _F = "F"; // 自雇人士
	}

	/**
	 * 职务
	 * @author YM10152
	 */
	public static interface NoGovInstitution {
		public final static String _legal = "00001"; // 法人代表
		public final static String _GM = "00002"; // 总经理
		public final static String _agm = "00003"; // 副总经理
		public final static String _dm = "00004"; // 部门经理
		public final static String _supervisor = "00005"; // 主管
		public final static String _职员 = "00006"; // 职员
		
		//优化 添加
		public final static String _A = "A"; // 高层管理人员
		public final static String _B = "B"; // 中层管理人员
		public final static String _C = "C"; // 基层管理人员
		public final static String _D = "D"; // 一般员工
		public final static String _E = "E"; // 内勤
		public final static String _F = "F"; // 后勤
		public final static String _G = "G"; // 工人
		public final static String _H = "H"; // 销售/中介/业务代表
		public final static String _I = "I"; // 营业员/服务员
		public final static String _J = "J"; // 正部级
		public final static String _K = "K"; // 副部级
		public final static String _L = "L"; // 正厅级
		public final static String _M = "M"; // 副厅级
		public final static String _N = "N"; // 正处级
		public final static String _O = "O"; // 副处级
		public final static String _P = "P"; // 正科级
		public final static String _Q = "Q"; // 副科级
		public final static String _R = "R"; // 正股级
		public final static String _S = "S"; // 副股级
		public final static String _Z = "Z"; // 其他
	}

	/**
	 * 与被保险人关系
	 * @author YM10152
	 */
	public static interface PolicyRelation {
		public final static String _00001 = "00001"; // 本人
		public final static String _00002 = "00002"; // 夫妻
		public final static String _00003 = "00003"; // 父母
		public final static String _00004 = "00004"; // 子女
		public final static String _00005 = "00005"; // 其他
	}

	/**
	 *
	 * 基本贷款信息
	 * @author YM10152
	 */
	public static interface ProductCd {
		public final static String _00001 = "00001"; // 随薪贷
		public final static String _00002 = "00002"; // 随意贷
		public final static String _00003 = "00003"; // 随意贷A
		public final static String _00004 = "00004"; // 随意贷B
		public final static String _00005 = "00005"; // 随意贷C
		public final static String _00006 = "00006"; // 随房贷
		public final static String _00007 = "00007"; // 助学贷
		public final static String _00008 = "00008"; // 车贷
		public final static String _00009 = "00009"; // 薪生贷
		public final static String _00010 = "00010"; // 随车贷
		public final static String _00011 = "00011"; // 随房贷A
		public final static String _00012 = "00012"; // 随房贷B
		public final static String _00013 = "00013"; // 公积金贷
		public final static String _00014 = "00014"; // 保单贷
		public final static String _00015 = "00015"; // 网购达人贷A
		public final static String _00016 = "00016"; // 淘宝商户贷
		public final static String _00017 = "00017"; // 学历贷
		public final static String _00018 = "00018"; // 卡友贷
		public final static String _00020 = "00020"; // 网购达人贷B
	}

	/**
	 * 职业
	 * @author YM10152
	 */
	public static interface OccupationType {
		public final static String _Payroll = "00001"; // 工薪
		public final static String _WhiteCollar = "00002"; // 白领
		public final static String _Dealer = "00003"; // 自营
		public final static String _Student = "00004"; // 学生
	}

	/**
	 * 经营场所
	 * @author YM10152
	 */
	public static interface BusinessPlace {
		public final static String _A = "00001"; // 自有
		public final static String _B = "00002"; // 租用
	}

	/**
	 * 京东用户等级
	 * @author YM10152
	 */
	public static interface JiDongUserLevel {
		public final static String _A = "A"; // 注册会员
		public final static String _B = "B"; // 铜牌会员
		public final static String _C = "C"; // 银牌会员
		public final static String _D = "D"; // 金牌会员
		public final static String _E = "E"; // 钻石会员
		public final static String _F = "F"; // 企业会员
	}

	/**
	 * 婚姻状况
	 * @author YM10152
	 */
	public static interface MaritalStatus {
		public final static String _0002 = "00002"; // 已婚
		public final static String _0001 = "00001"; // 未婚
		public final static String _0004 = "00004"; // 其他
		public final static String _0003 = "00003"; // 离异
		
		/* 后续添加优化 */
		public final static String _0005 = "00005"; // 丧偶
		public final static String _0006 = "00006"; // 再婚
		public final static String _0007 = "00007"; // 复婚
	}

	/**
	 * 客户来源
	 * @author YM10152
	 */
	public static interface CustomerSource {
		public final static String _00001 = "00001"; // 主动拜访
		public final static String _00002 = "00002"; // 宣传单
		public final static String _00003 = "00003"; // 电销
		public final static String _00004 = "00004"; // 短信
		public final static String _00005 = "00005"; // 网络
		public final static String _00006 = "00006"; // 报纸
		public final static String _00007 = "00007"; // 市场推广
		public final static String _00008 = "00008"; // 转介绍
		public final static String _00009 = "00009"; // 朋友介绍
		public final static String _00010 = "00010"; // 其他
	}

	/**
	 *
	 *
	 * @author YM10152
	 */
	public static interface ContractTemPrintType {
		public final static String _1 = "1"; // 套打
		public final static String _2 = "2"; // 非套打
	}

	/**
	 *
	 *
	 * @author YM10152
	 */
	public static interface LimitCd {
		public final static String _00001 = "00001"; // 12
		public final static String _00002 = "00002"; // 18
		public final static String _00003 = "00003"; // 24
		public final static String _00004 = "00004"; // 36
	}

	/**
	 * 条件类型
	 * @author YM10152
	 */
	public static interface ConditionType {
		public final static String _00001 = "00001"; // 申请人名下或直系亲属名下在进件地有房产
		public final static String _00002 = "00002"; // 本地户籍
		public final static String _00003 = "00003"; // 满足信用条件
	}

	/**
	 * 公积金材料
	 * @author YM10152
	 */
	public static interface ProvidentInfo {
		public final static String _A = "A"; // 官方网站
		public final static String _B = "B"; // 网银账户
		public final static String _C = "C"; // 中心证明
		public final static String _D = "D"; // 人行记录
	}

	/**
	 * 是否需要填写信息
	 * @author YM10152
	 */
	public static interface Indicator {
		public final static String _Y = "Y"; // 是
		public final static String _N = "N"; // 否
	}

	/**
	 * 住宅类型
	 * @author YM10152
	 */
	public static interface HouseType {
		public final static String _00001 = "00001"; // 自有住房
		public final static String _00002 = "00002"; // 单位住房
		public final static String _00003 = "00003"; // 亲属住房
		public final static String _00004 = "00004"; // 租房
	}

	/**
	 * 房贷情况
	 * @author YM10152
	 */
	public static interface EstateType {
		public final static String _ING = "ING"; // 还款中
		public final static String _ALL = "ALL"; // 全款购
		public final static String _END = "END"; // 已结清
	}

	/**
	 * 职务
	 * @author YM10152
	 */
	public static interface EmpPositionAttrType {
		public final static String _A = "A"; // 高层管理人员
		public final static String _B = "B"; // 中层管理人员
		public final static String _C = "C"; // 基层管理人员
		public final static String _D = "D"; // 一般员工
		public final static String _E = "E"; // 内勤
		public final static String _F = "F"; // 后勤
		public final static String _G = "G"; // 工人
		public final static String _H = "H"; // 销售/中介/业务代表
		public final static String _I = "I"; // 营业员/服务员
		public final static String _J = "J"; // 正部级
		public final static String _K = "K"; // 副部级
		public final static String _L = "L"; // 正厅级
		public final static String _M = "M"; // 副厅级
		public final static String _N = "N"; // 正处级
		public final static String _O = "O"; // 副处级
		public final static String _P = "P"; // 正科级
		public final static String _Q = "Q"; // 副科级
		public final static String _R = "R"; // 正股级
		public final static String _S = "S"; // 副股级
		public final static String _Z = "Z"; // 其他
	}

	/**
	 * 房产类型
	 * @author YM10152
	 */
	public static interface FangType {
		public final static String _00001 = "00001"; // 商品房
		public final static String _00002 = "00002"; // 经济适用房/动迁房/房改房
		public final static String _00003 = "00003"; // 自建房
	}

	/**
	 * 
	 * @author YM10152
	 */
	public static interface ProductConfigurationModuleInfo {
		public final static String _SALARYLOAN = "SALARYLOAN"; // 随薪贷信息
		public final static String _ESTATE = "ESTATE"; // 房产信息
		public final static String _CAR = "CAR"; // 车辆信息
		public final static String _PROVIDENT = "PROVIDENT"; // 公积金信息
		public final static String _POLICY = "POLICY"; // 保单信息
		public final static String _MASTERLOAN_A = "MASTERLOAN_A"; // 网购达人贷信息A
		public final static String _MERCHANTLOAN = "MERCHANTLOAN"; // 淘宝商户贷信息
		public final static String _CARDLOAN = "CARDLOAN"; // 卡友贷信息
		public final static String _MASTERLOAN_B = "MASTERLOAN_B"; // 网购达人贷信息B
	}

	/**
	 * 公司性质
	 * @author YM10152
	 */
	public static interface CorpStructure {
		public final static String _00001 = "00001"; // 政府机构
		public final static String _00002 = "00002"; // 事业单位
		public final static String _00003 = "00003"; // 国企
		public final static String _00004 = "00004"; // 外资
		public final static String _00005 = "00005"; // 民营
		public final static String _00006 = "00006"; // 私营
		public final static String _00007 = "00007"; // 其它
		public final static String _00008 = "00008"; // 合资
		public final static String _00009 = "00009"; // 个体
	}

	/**
	 * 缴费方式
	 * @author YM10152
	 */
	public static interface PaymentMethod {
		public final static String _Y = "Y"; // 年
		public final static String _M = "M"; // 月
	}

	/**
	 * 发薪方式
	 * @author YM10152
	 */
	public static interface CorpPayWay {
		public final static String _A = "00001"; // 网银
		public final static String _B = "00002"; // 现金
		public final static String _C = "00003"; // 网银+现金
	}

	/**
	 * 工作类型
	 * @author YM10152
	 */
	public static interface JobType {
		public final static String _00001 = "00001"; // 私营业主
		public final static String _00002 = "00002"; // 受薪人士
		public final static String _00003 = "00003"; // 自雇人士
	}

	/**
	 * 保单真伪核实方式
	 * @author YM10152
	 */
	public static interface PolicyCheck {
		public final static String _A = "A"; // 客服热线
		public final static String _B = "B"; // 网站
	}

	/**
	 * 买家/卖家 信用类型
	 * @author YM10152
	 */
	public static interface SellerCreditType {
		public final static String _A = "A"; // 红心
		public final static String _B = "B"; // 黄钻
		public final static String _C = "C"; // 红冠
		public final static String _D = "D"; // 紫冠
	}

	/**
	 * 最高学历
	 * @author YM10152
	 */
	public static interface EducationType {
		public final static String _0001 = "00001"; // 硕士及以上
		public final static String _0002 = "00002"; // 本科
		public final static String _0003 = "00003"; // 大专
		public final static String _0004 = "00004"; // 中专
		public final static String _F = "00005"; // 高中
		public final static String _G = "00006"; // 初中及以下
	}

	/**
	 * 贷款用途
	 * @author YM10152
	 */
	public static interface CreditApplication {
		public final static String _00001 = "00001"; // 资金周转
		public final static String _00002 = "00002"; // 扩大经营
		public final static String _00003 = "00003"; // 购生活品
		public final static String _00004 = "00004"; // 购原材料
		public final static String _00005 = "00005"; // 购设备
		public final static String _00006 = "00006"; // 教育支出
		public final static String _decoration = "00007"; // 装修家居
		public final static String _Medical = "00008"; // 医疗
		public final static String _Tourism = "00009"; // 旅游
		public final static String _Buy = "00010"; // 购买
		public final static String _00011 = "00011"; // 其他
	}

	/**
	 * 性别
	 * @author YM10152
	 */
	public static interface Gender {
		public final static String _M = "M"; // 男
		public final static String _F = "F"; // 女
	}

	/**
	 * 与申请人关系
	 * @author YM10152
	 */
	public static interface Relationship {
		public final static String _00001 = "00001"; // 父母
		public final static String _00002 = "00002"; // 子女
		public final static String _00003 = "00003"; // 兄弟
		public final static String _00004 = "00004"; // 姐妹
		public final static String _00005 = "00005"; // 兄妹
		public final static String _00006 = "00006"; // 姐弟
		public final static String _00007 = "00007"; // 朋友
		public final static String _00008 = "00008"; // 同事
		public final static String _00009 = "00009"; // 房东
		public final static String _00010 = "00010"; // 亲属
		public final static String _00011 = "00011"; // 同学
		public final static String _00012 = "00012"; // 其它
		public final static String _00013 = "00013"; // 配偶
	}

	/**
	 * 车辆类型
	 * @author YM10152
	 */
	public static interface CarType {
		public final static String _ONE = "00001"; // 一手车
		public final static String _TWO = "00002"; // 二手车
	}
	
	
	/**
	 * 操作类型
	 * @author YM10152
	 *
	 */
	public static interface optionModule{
		public final static String _1 = "1"; // 申请录入
		public final static String _2 = "2"; // 录入修改 
		public final static String _3 = "3"; // 录入复核
		public final static String _10 = "10"; // 退件箱
	}
	
	/**
	 * 操作类型
	 * @author YM10152
	 *
	 */
	public static interface optionType{
		public final static String _101 = "101"; // 提交
		public final static String _102 = "102"; // 保存
	} 
	
	/**
	 * 是否加急
	 * @author YM10152
	 * 
	 */
	public static interface IfPri{
		public final static String _0 = "0"; // 不加急
		public final static String _1 = "1"; // 加急
	}
	
	/**
	 * 是否疑似欺诈
	 * @author YM10152
	 *
	 */
	public static interface ifSuspectCheat{
		public final static Integer _0 = 0; // 非疑似欺诈
		public final static Integer _1 = 1; // 疑似欺诈
	}
			
	/**
	 * 有无信息用记录
	 * @author YM10152
	 *
	 */
	public static interface IfCreditRecode{
		public final static String _0 = "0"; // 无
		public final static String _1 = "1"; // 有
	}
	
	/**
	 * 初审同意，不同意
	 * @author YM10152
	 *
	 */
	public static interface ReviewAgreeOrDisagreeCheckNodeStatus{
		public final static String _CHECK_PASS = "CHECK_PASS"; // 同意
		public final static String _CHECK_NO_PASS = "CHECK_NO_PASS"; // 不同意
	}
	/**
	 * 职业
	 */
	public static interface professionType{
		public final static String _00001 = "00001";  //工薪
		public final static String _00002 = "00002";   //白领
		public final static String _00005 = "00005";   //公务员
		public final static String _A = "A";   //个体业主
		public final static String _B = "B";    //企业主
		
	}
}
