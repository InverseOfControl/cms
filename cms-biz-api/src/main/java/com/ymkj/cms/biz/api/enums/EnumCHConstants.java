package com.ymkj.cms.biz.api.enums;

public class EnumCHConstants{
	/**
	 * 借款环节
	 */
	public enum RtfState {
		APPSQLR("APP申请录入"), 
		SQLR("申请录入"), 
		LRFH("录入复核"), 
		CSFP("初审分派"), 
		XSCS("信审初审"), 
		ZSFP("终审分派"), 
		XSZS("信审终审"), 
		QYFP("签约分派"), 
		HTQY("合同签约"), 
		HTQR("合同确认"), 
		FKSH("放款审核"), 
		FKQR("放款确认"),
		DHHK("贷后还款"),
	    SQJXXWH("申请件信息维护");
		private String value;
		RtfState(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 操作类型
	 */
	public enum OptionType {
		
		OPTION_101("提交"),
		OPTION_102("保存"),
		OPTION_103("取消"),
		OPTION_104("改派"),
		OPTION_105("同步复议数据"),
		OPTION_106("签订"),
		OPTION_107("确认"),
		OPTION_108("通过"),
		OPTION_1080("提交（待确认）"),
		OPTION_109("退回"),
		OPTION_1090("退回（待确认）"),
		OPTION_110("放款"),
		OPTION_111("挂起"),
		OPTION_112("复核同意"),
		OPTION_113("复核不同意"),
		OPTION_114("初审自动派单"),
		OPTION_115("拒绝"),
		OPTION_1150("拒绝（待确认）"),
		OPTION_116("终审自动派单"),
		OPTION_117("提交高审"),
		OPTION_118("提交协审"),
		OPTION_119("回到终审"),
		OPTION_120("退回初审"),
		OPTION_121("修改"),
		OPTION_122("签约分派"),
		OPTION_123("退回门店"),
		OPTION_124("签约改派"),
		OPTION_125("复议拒绝"),
		OPTION_126("复议通过"),
		OPTION_127("复议退回"),
		OPTION_128("初审分派退回"),
		OPTION_129("终审分派退回录入"),
		OPTION_130("终审分派退回初审"),
		OPTION_131("终审分派拒绝");
		private String value;

		OptionType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 私营企业类型
	 * @author YM10152
	 */
	public enum PriEnterpriseType {
		_00001("个体户"),
		_00002("独资"), 
		_00003("合伙制"),
		_00004("股份制"),
		_00005("其他");
		private String value;

		PriEnterpriseType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 买家/卖家 信用等级
	 * @author YM10152
	 */
	public enum SellerCreditLevel {
		_A("1"),
		_B("2"),
		_C("3"), 
		_D("4"),
		_E("5");
		private String value;

		SellerCreditLevel(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 公司行业类别
	 * @author YM10152
	 */
	public enum EmpType {
		_00001("农、林、牧、渔业，能源、采矿业"),
		_00002("食品、药品、工业原料、服装、日用品等制造业"),
		_00003("电力、热力、燃气及水生产和供应业"),
		_00004("建筑业"), _00005(
				"批发和零售业"),
		_00006("交通运输、仓储和邮政业"),
		_00007("住宿、旅游、餐饮业"),
		_00008("信息传输、软件和信息技术服务业"),
		_00009("金融业"), 
		_00010("房地产业"),
		_00011("租赁和商务服务业"),
		_00012("科学研究和技术服务业"),
		_00013("水利、环境和公共设施管理业"),
		_00014("居民服务、修理和其他服务业"), 
		_00015("教育、培训"), 
		_00016("卫生、医疗、社会保障、社会福利"),
		_00017("文化、体育和娱乐业"), 
		_00018("政府、非赢利机构和社会组织"),
		_00019("警察、消防、军人"), 
		_00020("其他");
		private String value;

		EmpType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 工商网信息
	 * @author YM10152
	 */
	public enum BusinessNetWork {
		_A("在营"), 
		_C("注销/吊销/过期"), 
		_N("查无");
		private String value;

		BusinessNetWork(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 房产所有权
	 * @author YM10152
	 */
	public enum HouseOwnerType {
		_Y("客户独有"),
		_O("客户共有"),
		_N("非本人名下");
		private String value;

		HouseOwnerType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum CusWorkType {
		_Y("私营业主"), 
		_N("受薪人士"), 
		_F("自雇人士");
		private String value;

		CusWorkType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 职务
	 * @author YM10152
	 */
	public enum NoGovInstitution {
		_00001("法人代表"),
		_00002("总经理"), 
		_00003("副总经理"),
		_00004("部门经理"), 
		_00005("主管"), 
		_00006("职员"),
		
		//优化添加的
		_A("高层管理人员"),
		_B("中层管理人员"),
		_C("基层管理人员"),
		_D("一般员工"), 
		_E("内勤"),
		_F("后勤"),
		_G("工人"),
		_H("销售/中介/业务代表"), 
		_I("营业员/服务员"),
		_J("正部级"),
		_K("副部级"), 
		_L("正厅级"), 
		_M("副厅级"),
		_N("正处级"),
		_O("副处级"),
		_P("正科级"), 
		_Q("副科级"), 
		_R("正股级"), 
		_S("副股级"),
		_Z("其他");
		private String value;

		NoGovInstitution(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 与被保险人关系
	 * @author YM10152
	 */
	public enum PolicyRelation {
		_00001("本人"), 
		_00002("夫妻"), 
		_00003("父母"), 
		_00004("子女"), 
		_00005("其他");
		private String value;

		PolicyRelation(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 基本贷款信息
	 * @author YM10152
	 */
	public enum ProductCd {
		_00001("随薪贷"),
		_00002("随意贷"),
		_00003("随意贷A"),
		_00004("随意贷B"),
		_00005("随意贷C"), 
		_00006("随房贷"), 
		_00007("助学贷"),
		_00008("车贷"),
		_00009("薪生贷"),
		_00010("随车贷"),
		_00011("随房贷A"), 
		_00012("随房贷B"),
		_00013("公积金贷"),
		_00014("保单贷"),
		_00015("网购达人贷A"),
		_00016("淘宝商户贷"), 
		_00017("学历贷"),
		_00018("卡友贷"), 
		_00020("网购达人贷B");
		private String value;

		ProductCd(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 职业
	 * @author YM10152
	 */
	public enum OccupationType {
		_00001("工薪"), 
		_00002("白领"),
		_00003("自营"),
		_00004("学生");
		private String value;

		OccupationType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 经营场所
	 * @author YM10152
	 */
	public enum BusinessPlace {
		_00001("自有"), 
		_00002("租用");
		private String value;

		BusinessPlace(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 京东用户等级
	 * @author YM10152
	 */
	public enum JiDongUserLevel {
		_A("注册会员"), 
		_B("铜牌会员"), 
		_C("银牌会员"),
		_D("金牌会员"), 
		_E("钻石会员"),
		_F("企业会员");
		private String value;

		JiDongUserLevel(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 婚姻状况
	 * @author YM10152
	 */
	public enum MaritalStatus {
		_00002("已婚"),
		_00001("未婚"), 
		_00004("其他"), 
		_00003("离异"),
		
		_00005("丧偶"),
		_00006("再婚"),
		_00007("复婚");
		private String value;

		MaritalStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 客户来源
	 * @author YM10152
	 */
	public enum CustomerSource {
		_00001("主动拜访"),
		_00002("宣传单"), 
		_00003("电销"), 
		_00004("短信"), 
		_00005("网络"), 
		_00006("报纸"), 
		_00007("市场推广"),
		_00008("转介绍"),
		_00009("朋友介绍"), 
		_00010("其他");
		private String value;

		CustomerSource(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum ContractTemPrintType {
		_1("套打"), 
		_2("非套打");
		private String value;

		ContractTemPrintType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum LimitCd {
		_00001("12"),
		_00002("18"), 
		_00003("24"), 
		_00004("36");
		private String value;

		LimitCd(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum ConditionType {
		_00001("申请人名下或直系亲属名下在进件地有房产"), 
		_00002("本地户籍"), 
		_00003("满足信用条件");
		private String value;

		ConditionType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 公积金材料
	 * @author YM10152
	 */
	public enum ProvidentInfo {
		_A("官方网站"),
		_B("网银账户"), 
		_C("中心证明"), 
		_D("人行记录");
		private String value;

		ProvidentInfo(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 是否需要填写信息
	 * @author YM10152
	 */
	public enum Indicator {
		_Y("是"),
		_N("否");
		private String value;

		Indicator(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum HouseType {
		_00001("自有住房"),
		_00002("单位住房"),
		_00003("亲属住房 "),
		_00004("租房");
		private String value;

		HouseType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum EstateType {
		_ING("还款中"), 
		_ALL("全款购"), 
		_END("已结清");
		private String value;

		EstateType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum EmpPositionAttrType {
		_A("高层管理人员"),
		_B("中层管理人员"),
		_C("基层管理人员"),
		_D("一般员工"), 
		_E("内勤"),
		_F("后勤"),
		_G("工人"),
		_H("销售/中介/业务代表"), 
		_I("营业员/服务员"),
		_J("正部级"),
		_K("副部级"), 
		_L("正厅级"), 
		_M("副厅级"),
		_N("正处级"),
		_O("副处级"),
		_P("正科级"), 
		_Q("副科级"), 
		_R("正股级"), 
		_S("副股级"),
		_Z("其他");
		private String value;

		EmpPositionAttrType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum FangType {
		_00001("商品房"),
		_00002("经济适用房/动迁房/房改房"), 
		_00003("自建房");
		private String value;

		FangType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *
	 * @author YM10152
	 */
	public enum ProductConfigurationModuleInfo {
		_SALARYLOAN("随薪贷信息"), 
		_ESTATE("房产信息"), 
		_CAR("车辆信息"), 
		_PROVIDENT("公积金信息"), 
		_POLICY("保单信息"), 
		_MASTERLOAN_A("网购达人贷信息A"), 
		_MERCHANTLOAN("淘宝商户贷信息"),
		_CARDLOAN("卡友贷信息"),
		_MASTERLOAN_B("网购达人贷信息B");
		private String value;

		ProductConfigurationModuleInfo(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 公司性质
	 * @author YM10152
	 */
	public enum CorpStructure {
		_00001("政府机构"),
		_00002("事业单位"), 
		_00003("国企"), 
		_00004("外资"), 
		_00005("民营"), 
		_00006("私营"),
		_00007("其它"),
		_00008("合资"),
		_00009("个体");
		private String value;

		CorpStructure(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 缴费方式
	 * @author YM10152
	 */
	public enum PaymentMethod {
		_Y("年"),
		_M("月");
		private String value;

		PaymentMethod(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 发薪方式
	 * @author YM10152
	 */
	public enum CorpPayWay {
		_00001("网银"),
		_00002("现金"), 
		_00003("网银+现金");
		private String value;

		CorpPayWay(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 工作类型
	 * @author YM10152
	 */
	public enum JobType {
		_00001("私营业主"),
		_00002("受薪人士"),
		_00003("自雇人士");
		private String value;

		JobType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 保单真伪核实方式
	 * @author YM10152
	 */
	public enum PolicyCheck {
		_A("客服热线"), 
		_B("网站");
		private String value;

		PolicyCheck(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 买家/卖家 信用类型
	 * @author YM10152
	 */
	public enum SellerCreditType {
		_A("红心"),
		_B("黄钻"),
		_C("红冠"), 
		_D("紫冠");
		private String value;

		SellerCreditType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 最高学历
	 * @author YM10152
	 */
	public enum EducationType {
		_00001("硕士及以上"), 
		_00002("本科"), 
		_00003("大专"),
		_00004("中专"),
		_00005("高中"), 
		_00006("初中及以下");
		private String value;

		EducationType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 贷款用途
	 * @author YM10152
	 */
	public enum CreditApplication {
		_00001("资金周转"), 
		_00002("扩大经营"), 
		_00003("购生活品"), 
		_00004("购原材料"), 
		_00005("购设备"), 
		_00006("教育支出"),
		_00007("装修家居"),
		_00008("医疗"), 
		_00009("旅游"), 
		_00010("购买"), 
		_00011("其他");
		private String value;

		CreditApplication(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 性别
	 * @author YM10152
	 */
	public enum Gender {
		_M("男"),
		_F("女");
		private String value;

		Gender(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 与申请人关系
	 * @author YM10152
	 */
	public enum Relationship {
		_00001("父母"), 
		_00002("子女"), 
		_00003("兄弟"), 
		_00004("姐妹"),
		_00005("兄妹"), 
		_00006("姐弟"), 
		_00007("朋友"), 
		_00008("同事"),
		_00009("房东"), 
		_00010("亲属"), 
		_00011("同学"), 
		_00012("其它"), 
		_00013("配偶");
		private String value;

		Relationship(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 车辆类型
	 * @author YM10152
	 */
	public enum CarType {
		_00001("一手车"),
		_00002("二手车");
		private String value;

		CarType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	/**
	 * 业务环节
	 * @author 李璇
	 * @date 2017年4月28日 下午3:15:44
	 */
	public enum BusinessSegment {
		LDHJ("录单环节"),
		XSHJ("信审环节"),
		QYHJ("签约环节");
		private String value;

		BusinessSegment(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * <p>Description:借款日志查询角色</p>
	 * @uthor YM10159
	 * @date 2017年6月7日 上午10:59:05
	 */
	public enum LoanLogRole {
		/**信审详细日志角色*/
		XSXXRZ("amsDetailLog"), 
		/**录单详细原因角色*/
		LDXXYY("cfsDetailReason"), 
		/**信审详细原因角色*/
		XSXXYY("amsDetailReason");
		
		private String value;
		LoanLogRole(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	
	public enum EnumApplyInputFlagConstants {
		/*	//待签约 已结清 拒绝 逾期 取消 申请中 正常 待放款
		*/	 
			 ZTC("directApplyInput","直通车"),
			 YYB("applyInput","营业部");
			
			private String value;
			
			private String code;

			private EnumApplyInputFlagConstants(String value) {
				this.value = value;
			}
			
			private EnumApplyInputFlagConstants(String code, String value) {
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
			
			public static String getApplyInputFlagValueByCode(String code) {
				for(EnumApplyInputFlagConstants enumAppStateConstants:values()){
					if(enumAppStateConstants.getCode().equals(code))
						return enumAppStateConstants.getValue();
				}
				return null;
			}
			
			public static String getApplyInputFlagCodeByValue(String value) {
				for(EnumApplyInputFlagConstants enumAppStateConstants:values()){
					if(enumAppStateConstants.getValue().equals(value))
						return enumAppStateConstants.getCode();
				}
				return null;
			}
		}
}
