package com.ymkj.cms.biz.api.enums;

import java.util.concurrent.TimeUnit;

/**
 * Demo 枚举类, 对应 实体中 状态值得定义
 * 
 * @author haowp
 *
 */
public class EnumConstants {
  /**
   * 默认系统操作名
   */
  public static final String DEFAULT_SYSTEM_NAME = "系统";
  /**
   * 系统编码
   */
  public static final String BMS_SYSTEM_CODE = "bms";
  public static final String AMS_SYSTEM_CODE = "ams";
  public static final String AITE_SYSTEM_CODE = "aite";
  public static final String CMS_SYSTEM_CODE = "cms";
  public static final String APP_SYSTEM_CODE = "app";
  /**
   * 捞财宝（爱特）,在核心code
   */
  public static final String AITE_USER_CODE = "aite";
  public static final String AITE_USER_NAME = "爱特";
  /**
   * 借款限制天数
   */
  public static final String LOAN_RESTRICT_DAY = "LOAN_RESTRICT_DAY";

  /**
   * 借款保护期
   */
  public static final String LOAN_PROTECT_DAY = "LOAN_PROTECT_DAY";

  /**
   * 借款保护期天数
   */
  public static final String LOAN_PROTECT_DAY_VALUE = "30";
  /**
   * 产品模块
   */
  public static final String PRODUCT_CONFIGURATION_MODULE = "ProductConfigurationModuleInfo";

  /**
   * 借款系统请求参数
   */
  public static final String BMS_RESPONSE_SYSCODE = "1111111";

  /**
   * 借款系统 sysCode
   */
  public static final String BMS_SYSCODE = "bms";

  /**
   * 系统默认机构号
   */
  public static final String BMS_Org = "000000";

  /**
   * 調用核心借款状态状态码
   */
  public static final String CONTRACT_CONFIRM = "0010";// :合同确认
  public static final String CONTRACT_BACK = "0011";// :合同退回
  public static final String FINANCE_AUDIT_PASS = "0020";// :财务审核通过
  public static final String FINANCE_AUDIT_BACK = "0021";// :财务审核退回
  public static final String FINANCE_LOAN_BACK = "0022";// :财务放款退回
  public static final String CONTRACT_REFUSE = "0030";// :拒绝
  public static final String CONTRACT_CANCEL = "0031";// :取消
  public static final String CONTRACT_SIGN = "0040";// :合同签订

  public static final String YES = "1"; // 是
  public static final String NO = "0";// 否

  public static final String MANAGER = "2";// 经理角色代号
  public static final String NOT_MANAGER = "0";// 非经理角色代号
  public static final String ASSISTANT = "3";// 协审角色代号

  /**
   * 响应结果成功code
   */
  public static final String RES_CODE_SUCCESS = "000000";
  /**
   * 响应结果成功code
   */
  public static final String RES_CODE_SUCCESS_SHORT = "0000";
  /**
   * http响应成功code
   */
  public static final int HTTP_CODE_SUCCESS = 200;
  /**
   * 响应处理失败code
   */
  public static final String RES_CODE_FAILED = "111111";

  /**
   * 数据来源
   * 
   * @author user
   *
   */
  public enum dataSources {
    PC("1"), APP("2");

    private String value;

    dataSources(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 工作类型 私营业主 受薪人士 自雇人士
   * 
   * @author user
   *
   */
  public enum WorkType {
    PrivateOwners(1), WageEarners(2), SelfEmployed(3);

    private Integer value;

    WorkType(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }
  }

  /**
   * 借款状态
   * 
   * @author user
   *
   */
  public enum LoanStatus {
    /** 申请 */
    APPLY("APPLY"),
    /** 通过 */
    PASS("PASS"),
    /** 取消 */
    CANCEL("CANCEL"),
    /** 拒绝 */
    REFUSE("REFUSE"),
    /** 正常 */
    NORMAL("NORMAL");

    private String value;

    LoanStatus(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 借款环节
   * 
   * @author user
   *
   */
  public enum RtfState {
    /** APP申请录入 */
    APPSQLR("APPSQLR"),
    /** 申请录入 */
    SQLR("SQLR"),
    /** 录入复核 */
    LRFH("LRFH"),
    /** 初审分派 */
    CSFP("CSFP"),
    /** 信审初审 */
    XSCS("XSCS"),
    /** 终审分派 */
    ZSFP("ZSFP"),
    /** 信审终审 */
    XSZS("XSZS"),
    /** 签约分派 */
    QYFP("QYFP"),
    /** 合同签约 */
    HTQY("HTQY"),
    /** 合同确认 */
    HTQR("HTQR"),
    /** 放款审核 */
    FKSH("FKSH"),
    /** 放款确认 */
    FKQR("FKQR"),
    /** 贷后还款 */
    DHHK("DHHK"),
    /** 申请件维护 */
    SQJXXWH("SQJXXWH");
    private String value;

    RtfState(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 借款审核环节节点状态
   * 
   * @author user
   *
   */
  public enum RtfNodeState {
    /** "申请录入保存" */
    SQLR_SAVE("SQLR-SAVE"),
    /** "申请录入取消" */
    SQLR_CANCEL("SQLR-CANCEL"),
    /** "申请录入拒绝" */
    SQLR_REJECT("SQLR-REJECT"),
    /** "录入超时取消" */
    LRCS_CANCEL("LRCS-CANCEL"),
    /** "录入超时拒绝" */
    LRCS_REJECT("LRCS-REJECT"),

    /** "录入复核办理" */
    SQLR_SUBMIT("SQLR-SUBMIT"),
    /** "录入复核退回" */
    LRFH_RETURN("LRFH-RETURN"),
    /** "录入复核取消" */
    LRFH_CANCEL("LRFH-CANCEL"),
    /** "录入复核拒绝" */
    LRFH_REJECT("LRFH-REJECT"),
    /** "复核超时取消" */
    FHCS_CANCEL("FHCS-CANCEL"),
    /** "复核超时拒绝" */
    FHCS_REJECT("FHCS-REJECT"),

    /** 初审分派办理 */
    CSFPSUBMIT("CSFP-SUBMIT"),
    /** 初审分派办理退回 */
    CSFPCANCEL("CSFP-RETURN"),
    /** 初审分派办理拒绝 */
    CSFPREJECT("CSFP-REJECT"),

    /** 信审初审办理 */
    XSCSASSIGN("XSCS-ASSIGN"),
    /** 信审初审挂起 */
    XSCSHANGUP("XSCS-HANGUP"),
    /** 信审初审通过 */
    XSCSPASS("XSCS-PASS"),
    /** 信审初审拒绝 */
    XSCSREJECT("XSCS-REJECT"),
    /** 初审退回录入 */
    XSCSRETURN("XSCS-RETURN"),
    /** 初审提交高审 */
    HIGHPASS("HIGH-PASS"),

    /** 终审分派办理 */
    XSCSSUBMIT("XSCS-SUBMIT"),

    /** 信审终审办理 */
    XSZSASSIGN("XSZS-ASSIGN"),
    /** 信审终审挂起 */
    XSZSHANGUP("XSZS-HANGUP"),
    /** 信审终审通过 */
    XSZSPASS("XSZS-PASS"),
    /** 信审终审拒绝 */
    XSZSREJECT("XSZS-REJECT"),
    /** 终审退回录入 */
    XSZSRETURN("XSZS-RETURN"),
    /** 终审分配退回初审 */
    XSZSRTNCS("XSZS-RTNCS"),
    /** 终审提交高审 */
    XSZSSUBMITHIGH("XSZS-SUBMIT-HIGH"),
    /** 终审回到终审 */
    XSZSSUBMITBACK("XSZS-SUBMIT-BACK"),
    /** 终审提交协审 */
    XSZSSUBMITAPPROVAL("XSZS-SUBMIT-APPROVAL"),

    /** 合同签约分派办理 */
    XSZSSUBMIT("XSZS-SUBMIT"),

    /** 申请件维护拒绝 */
    SQJWH_REJECT("SQJWH-REJECT"),

    /** 合同签超时取消 */
    QYCSCANCEL("QYCS-CANCEL"),
    /** 合同签约办理 */
    HTQYASSIGN("HTQY-ASSIGN"),
    /** 合同签约取消 */
    HTQYCANCEL("HTQY-CANCEL"),
    /** 合同签约拒绝 */
    HTQYREJECT("HTQY-REJECT"),

    /** 合同确认办理 */
    HTQYSUBMIT("HTQY-SUBMIT"),
    /** 合同确认退回 */
    HTQRRETURN("HTQR-RETURN"),

    /** 放款审核退回 */
    FKSHRETURN("FKSH-RETURN"),
    /** 放款审核办理 */
    HTQRSUBMIT("HTQR-SUBMIT"),

    /** 放款确认退回 */
    FKQRRETURN("FKQR-RETURN"),
    /** 借款放款办理 */
    FKSHSUBMIT("FKSH-SUBMIT"),
    /** 借款放款完成 */
    FKQRSUBMIT("FKQR-SUBMIT"),
    /** 终审分派拒绝 **/
    ZSFPREJECT("ZSFP-REJECT");
    private String value;

    RtfNodeState(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 借款审核环节节点复核节点状态
   * 
   * @author user 默认状态为NO_CHECK不需要复核
   */
  public enum ChcekNodeState {
    /** 信审初审不需要复核 */
    NOCHECK("NO_CHECK"),
    /** 信审初审需要复核 */
    CHECK("CHECK"),
    /** 信审初审复核同意 */
    CHECKPASS("CHECK_PASS"),
    /** 信审初审复核不同意 */
    CHECKNOPASS("CHECK_NO_PASS");
    private String value;

    ChcekNodeState(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 拒绝原因code
   * 
   * @author user
   *
   */
  public enum RejectReasonCode {
    信用度不够("1");

    private String value;

    RejectReasonCode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 退回一级原因code
   * 
   * @author user
   *
   */
  public enum ReturnFirstLevleReasonCode {
    其他_FKQR("RT00047"), // 放款确认环节，其他
    其他_FKSH("RT00048");// 放款审核环节，其他

    private String value;

    ReturnFirstLevleReasonCode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 退回二级原因code
   * 
   * @author user
   *
   */
  public enum ReturnTwoLevleReasonCode {
    其他_FKQR("RT0004700066"), // 放款确认环节，其他
    其他_FKSH("RT0004800067");// 放款审核环节，其他

    private String value;

    ReturnTwoLevleReasonCode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 标红环节 1 复核提交 2 初审退回 3 初审提交 4 终审退回
   * 
   * @author YM10152
   *
   */
  public enum differences {
    /** 复核提交 */
    peview_submit("1"),
    /** 初审退回 */
    audit_back("2"),
    /** 初审提交 */
    audit_submit("3"),
    /** 终审退回 */
    final_audit_back("4");
    private String value;

    differences(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 取消一级原因code
   * 
   * @author user
   *
   */
  public enum cancelFirstLevleReasonCode {
    超过提交时效自动取消("CA0014"), // 超过提交时效自动取消
    超补件时效自动取消("CA0012"), // 超补件时效自动取消
    超签约时效自动取消("CA0013");// 超签约时效自动取消

    private String value;

    cancelFirstLevleReasonCode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 取消一级原因code
   * 
   * @author user
   *
   */
  public enum ReasonType {
    FirstLevleReason("1"), // 申请录入环节，超补件时效自动取消
    TwoLevleReason("2");// 合同签约环节，超补件时效自动取消

    private String value;

    ReasonType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum agencyOrComplete {
    /** 代办任务 */
    agency("1"),
    /** 完成任务 */
    complete("2");
    private String value;

    agencyOrComplete(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 初审接口枚举
   * 
   * @author YM10143
   *
   */
  public enum auditType {
    /** 门店重提 */
    STORERETURN("001"),
    /** 复合退回 */
    REEXAMINERETURN("002"),
    /** 终审退回 */
    FINALJUDGMENTRETURN("003");
    private String value;

    public String getValue() {
      return value;
    }

    auditType(String value) {
      this.value = value;
    }
  }

  public enum OptionModule {

    /** APP申请录入 */
    APP_APPLY_ENTRY("13"),
    /** 申请作业 */
    APPLY_TASK("0"),
    /** 申请录入 */
    APPLY_ENTRY("1"),
    /** 录入修改 */
    APPLY_MODIFY("2"),
    /** 录入复核 */
    APPLY_CHECK("3"),
    /** 合同签约 */
    CONTRACT_SIGN("4"),
    /** 复议 */
    REVIEW("6"),
    /** 合同确认 */
    CONTRACT_CONFIRM("7"),
    /** 放款审核 */
    FINANCE_AUDIT("8"),
    /** 放款确认 */
    FINANCE_CONFIRM("9"),
    /** 退件箱办理 */
    BACK_APPLY_MODIFY("10"),
    /** 审核，申请件信息维护 */
    MAINTAIN_APPLY_MODIFY("11"),
    /** 签约改派 */
    SIGN_CHANGE("12");

    private String value;

    OptionModule(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum OptionType {
    /** 提交 */
    SUBMIT("101"),
    /** 保存 */
    SAVE("102"),
    /** 取消 */
    CANCEL("103"),
    /** 改派 */
    REASSIGNMENT("104"),
    /** 同步复议数据 */
    SYN_REVIEW_DATA("105"),
    /** 签订 */
    SIGN("106"),
    /** 确认 */
    CONFIRM("107"),
    /** 通过 */
    PASS("108"),
    /** 通过待确认 */
    PASS_WAIT("1080"),
    /** 退回 */
    RETRUN("109"),
    /** 退回待确认 */
    RETRUN_WAIT("1090"),
    /** 放款 */
    LOAN("110"),
    /** 挂起 */
    HANHUP("111"),
    /** 复核同意 */
    REVIEW_PASS("112"),
    /** 复核不同意 */
    REVIEW_NO_PASS("113"),
    /** 初审自动派单 */
    CS_AUTOMATIC_DISPATCH("114"),
    /** 拒绝 */
    REJECT("115"),
    /** 拒绝待确认 */
    REJECT_WAIT("1150"),
    /** 终审自动派单 */
    ZS_AUTOMATIC_DISPATCH("116"),
    /** 终审提交高审 */
    ZS_XSZS_SUBMIT_HIGH("117"),
    /** 终审回到终审 */
    ZS_XSZS_SUBMIT_BACK("119"),
    /** 终审提交协审 */
    ZS_XSZS_SUBMIT_APPROVAL("118"),
    /** 终审回到初审 */
    ZS_XSZS_RTNCS("120"),
    /** 申请件维护修改 */
    ZS_XSZS_MAINTAIN("121"),
    /** 签约分派 */
    SIGN_ASSIGNMENT("122"),
    /** 终审退回录入 */
    ZS_XSZS_RETURN("123"),
    /** 签约改派 */
    SIGN_ASSIGNMENT_CHANGE("124"),
    /** 复议拒绝 **/
    XS_RECONS_REJECT("125"),
    /** 复议通过 **/
    XS_RECONS_PASS("126"),
    /** 复议退回 **/
    XS_RECONS_TRTURN("127"),
    /** 分派退回 **/
    XS_ASSIGN_TRTURN("128"),
    /** 终审分派退回录入 **/
    XS_ZSFP_RETURN("129"),
    /** 终审分派退回初审 **/
    XS_ZSFP_RTNCS("130"),
    /** 终审分派拒绝 **/
    XS_ZSFP_REJECT("131");
    private String value;

    OptionType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 请求标识
   * 
   * @author YM10161
   *
   */
  public enum ReqFlag {
    /** 初审 */
    CS("1"),
    /** 终审 */
    ZS("2");
    private String value;

    ReqFlag(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 商品模块枚举
   * 
   * @author YM10152
   *
   */
  public enum productModule {
    /** 随薪贷信息 */
    SALARYLOAN("SALARYLOAN"),
    /** 房产信息 */
    ESTATE("ESTATE"),
    /** 车辆信息 */
    CAR("CAR"),
    /** 公积金信息 */
    PROVIDENT("PROVIDENT"),
    /** 保单信息 */
    POLICY("POLICY"),
    /** 网购达人贷信息A */
    MASTERLOAN_A("MASTERLOAN_A"),
    /** 淘宝商户贷信息 */
    MERCHANTLOAN("MERCHANTLOAN"),
    /** 卡友贷信息 */
    CARDLOAN("CARDLOAN"),
    /** 网购达人贷信息B */
    MASTERLOAN_B("MASTERLOAN_B");
    private String value;

    productModule(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 是否是新生件标识
   * 
   * @author YM10161
   *
   */
  public enum ifNewLoanNo {
    /** 非新生件 */
    NOLOANNO("0"),
    /** 新生件 */
    NEWLOANNO("1");
    private String value;

    ifNewLoanNo(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 申请件维护信息更新标识
   * 
   * @author YM10161
   *
   */
  public enum applicationUpdFlag {
    /** 通过件修改 */
    PASS_UPD("1"),
    /** 通过件拒绝 */
    PASS_REJECT("2"),
    /** 拒绝修改 */
    REJECT_UPD("3");
    private String value;

    applicationUpdFlag(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

  }

  /**
   * 是否加急
   * 
   * @author YM10152
   *
   */
  public enum ifPri {
    /** 不加急 */
    NO_URGERT("0"),
    /** 加急 */
    URGERT("1");

    private String value;

    ifPri(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:sysType
   * </p>
   * <p>
   * Description:系统类型
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月27日 上午10:41:13
   */
  public enum sysType {
    SYS_APP("APP"), SYS_BMS("借款系统"), SYS_CMS("运营平台系统"), SYS_AMS("审核系统"), SYS_CFS("录单系统"), SYS_AITE("爱特系统");

    private String value;

    sysType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * reviewStatus
   * </p>
   * <p>
   * Description:复议-代办任务和已完成任务
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 上午9:52:47
   */
  public enum reviewAgencyComplete {
    /** 代办任务 */
    AGENCY("0"),
    /** 已完成任务 */
    COMPLETE("1");

    private String value;

    reviewAgencyComplete(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:reviewAgencyComplete
   * </p>
   * <p>
   * Description:复议-未提交队列和已提交队列
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 上午10:06:10
   */
  public enum reviewSubmitUnsubmit {
    /** 未提交队列 */
    UNSUBMIT("0"),
    /** 已提交队列 */
    SUBMIT("1");

    private String value;

    reviewSubmitUnsubmit(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:reviewStatus
   * </p>
   * <p>
   * Description:复议-状态
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 上午10:12:57
   */
  public enum reviewStatus {
    /** 未办理 */
    STATUS_0("0"),
    /** 办理过但未提交复议 */
    STATUS_1("1"),
    /** 已提交复议申请 */
    STATUS_2("2"),
    /** 退回的复议申请 */
    STATUS_3("3"),
    /** 取消 */
    STATUS_4("4");

    private String value;

    reviewStatus(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:reviewResultStatus
   * </p>
   * <p>
   * Description:复议-复议结果
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 上午10:23:48
   */
  public enum reviewResultStatus {
    /** 通过 */
    STATUS_0("0"),
    /** 拒绝 */
    STATUS_1("1");

    private String value;

    reviewResultStatus(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:returnType
   * </p>
   * <p>
   * Description:退回环节
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 上午11:08:22
   */
  public enum returnType {
    /** 录入复核退回 */
    HANDLETYPE_10("10"),
    /** 信审退回 */
    HANDLETYPE_20("20"),
    /** 合同确认退回 */
    HANDLETYPE_30("30"),
    /** 财务放款退回 */
    HANDLETYPE_40("40");

    private String value;

    returnType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * <p>
   * Title:returnType
   * </p>
   * <p>
   * Description:综合查询-案件标示
   * </p>
   * 
   * @uthor YM10159
   * @date 2017年3月28日 下午5:30:33
   */
  public enum caseIdentify {
    /** app进件 */
    caseIdentify_0("0"),
    /** 加急 */
    caseIdentify_1("1"),
    /** 欺诈 */
    caseIdentify_2("2");

    private String value;

    caseIdentify(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /*
   * @company:上海郁敏网络科技有限公司 <p>Title:channelCode</p> <p>Description:渠道code</p>
   * 
   * @author 李璇
   * 
   * @date 2017年3月28日 下午5:58:18
   */
  public enum channelCode {
    /** 证大P2P */
    ZDP2P("00001"), // CODE
    /** 渤海2 */
    BH2("00015"), // CODE
    /** 华瑞渤海 */
    HRBH("00020"), // CODE
    /** 包商银行 */
    BSYH("00018"), // CODE
    /** 外贸信托 */
    WMXT("00014"), // CODE
    /** 海门小贷 */
    HMXD("00011"), // CODE
    /** 龙信小贷 */
    LXXD("00013"), // CODE
    /** 渤海信托 */
    BHXT("00012"), // CODE
    /** 国民信托 */
    GMXT("00004"), // CODE
    /** 爱特（捞财宝） */
    AITE("00016"), // CODE
    /** 包商银行 */
    BAOSHANG("00018"),
    /** 外贸3 */
    WAIMAOT("00019"),
    /** 陆金所 */
    LUJS("00021");

    private String value;

    channelCode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 调用PIC，业务环节code
   */
  public enum BusinessSegment {
    CONTRACT_AWARD("contractAward");// 合同签约

    private String value;

    BusinessSegment(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 调用PIC，证件类型code
   */
  public enum PicPaperstype {
    IDENTIFICATION_PAPER("B");// 身份证明

    private String value;

    PicPaperstype(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 借款业务环节
   * 
   * @author 李璇
   * @date 2017年4月28日 下午3:15:44
   */
  public enum BusinessSegmentLoan {
    LDHJ("LDHJ"), XSHJ("XSHJ"), QYHJ("QYHJ");
    private String value;

    BusinessSegmentLoan(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 枚举类型
   * 
   * @author 李璇
   * @date 2017年4月28日 下午3:15:44
   */
  public enum EnumCodeType {
    BusinessSegmentLoan("BusinessSegmentLoan"), ContractType("contractType");// 合同类型
    private String value;

    EnumCodeType(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 核心流程状态
   * 
   * @author 李璇
   * @date 2017年4月28日 下午3:15:44
   */
  public enum CoreLoanState {
    SQZ("申请中"), FKZ("放款中"), ZC("正常");
    private String value;

    CoreLoanState(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 报文状态
   * 
   * @author YM10152 <option value="APPLY">申请</option> <option
   *         value="PASS">通过</option> <option value="CANCEL">取消</option> <option
   *         value="NORMAL">正常</option> <option value="REFUSE">拒绝</option>
   */
  public enum Status {
    /** 申请录入 */
    APPLY("APPLY", "申请"),
    /** 录入复核 */
    PASS("PASS", "通过"),
    /** 初审分派 */
    CANCEL("CANCEL", "取消"),
    /** 初审分派 */
    NORMAL("NORMAL", "正常"),
    /** 信审初审 */
    REFUSE("REFUSE", "拒绝");

    private final String code;
    private final String desc;

    private Status(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static Status get(String code) {
      for (Status status : Status.values()) {
        if (status.getCode().equals(code))
          return status;
      }
      throw new IllegalArgumentException("status is not exist : " + code);
    }
  }

  /**
   * 报文状态
   * 
   * @author YM10152
   *
   */
  public enum RtfStatus {

    /** 申请录入 */
    SQLR("SQLR", "申请录入"),
    /** 录入复核 */
    LRFH("LRFH", "录入复核"),
    /** 初审分派 */
    CSFP("CSFP", "初审分派"),
    /** 信审初审 */
    XSCS("XSCS", "信审初审"),
    /** 终审分派 */
    ZSFP("ZSFP", "终审分派"),
    /** 信审终审 */
    XSZS("XSZS", "信审终审"),
    /** 签约分派 */
    QYFP("QYFP", "签约分派"),
    /** 合同签约 */
    HTQY("HTQY", "合同签约"),
    /** 合同确认 */
    HTQR("HTQR", "合同确认"),
    /** 放款审核 */
    FKSH("FKSH", "放款审核"),
    /** 放款确认 */
    FKQR("FKQR", "放款确认"),
    /** 贷后还款 */
    DHHK("DHHK", "贷后还款"),
    /** 终审退回门店 */
    ZSRTNLR("ZSRTNLR", "终审退回门店"),
    /** 终审退回初审 */
    ZSRTNCS("ZSRTNCS", "终审退回初审"),
    /** 申请件维护 */
    SQJXXWH("SQJXXWH", "申请件信息维护");
    private final String code;
    private final String desc;

    private RtfStatus(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static RtfStatus get(String code) {
      for (RtfStatus status : RtfStatus.values()) {
        if (status.getCode().equals(code))
          return status;
      }
      throw new IllegalArgumentException("RtfStatus is not exist : " + code);
    }
  }

  /**
   * 申请件维护模块 操作枚举值
   * 
   * @author YM10152
   *
   */
  public enum applyMaintainFlag {
    /** 通过件拒绝 */
    _1(1L, "通过件修改成拒绝件"),
    /** 通过件修改 */
    _2(2L, "通过件修改金额期限"),
    /** 拒绝件修改 */
    _3(3L, "拒绝件修改拒绝原因");
    private final Long code;
    private final String desc;

    private applyMaintainFlag(Long code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public Long getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static applyMaintainFlag get(String code) {
      for (applyMaintainFlag flag : applyMaintainFlag.values()) {
        if (flag.getCode().equals(code))
          return flag;
      }
      throw new IllegalArgumentException("applyMaintainFlag is not exist : " + code);
    }
  }

  /**
   * 与申请人关系
   * 
   * @author YM10152
   *
   */
  public enum Relationship {
    _00001("00001", "父母"), _00002("00002", "子女"), _00003("00003", "兄弟"), _00004("00004", "姐妹"), _00005("00005", "兄妹"), _00006("00006", "姐弟"), _00007("00007", "朋友"), _00008("00008", "同事"), _00009(
        "00009", "房东"), _00010("00010", "亲属"), _00011("00011", "同学"), _00012("00012", "其它"), _00013("00013", "配偶");

    private final String code;
    private final String desc;

    private Relationship(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static Relationship get(String code) {
      for (Relationship flag : Relationship.values()) {
        if (flag.getCode().equals(code))
          return flag;
      }
      throw new IllegalArgumentException("Relationship is not exist : " + code);
    }

  }

  /**
   * 婚姻状况
   *
   * @author YM10152
   *
   */
  public enum MaritalStatus {
    RESUME_MARRIAGE("00007", "复婚"), REMARRIAGE("00006", "再婚"), WIDOWED("00005", "丧偶"), DIVORCE("00003", "离异"), MARRIED("00002", "已婚"), UN_MARRIED("00001", "未婚");

    private final String code;
    private final String desc;

    private MaritalStatus(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static MaritalStatus get(String code) {
      for (MaritalStatus flag : MaritalStatus.values()) {
        if (flag.getCode().equals(code))
          return flag;
      }
      throw new IllegalArgumentException("MaritalStatus is not exist : " + code);
    }

  }

  /**
   * 包银状态值
   *
   * @author YM10152
   *
   */
  public enum BaoYinSatus {
    _000("0", "黑名单拒绝"), _001("1", "黑名单通过"), _002("2", "银行卡鉴权拒绝"), _003("3", "银行卡鉴权通过"), _004("4", "风控信息推送失败"), _005("5", "风控信息推送成功"), _006("6", "影像资料推送失败"), _007("7", "影像资料推送成功"), ;

    private final String code;
    private final String desc;

    private BaoYinSatus(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static MaritalStatus get(String code) {
      for (MaritalStatus flag : MaritalStatus.values()) {
        if (flag.getCode().equals(code))
          return flag;
      }
      throw new IllegalArgumentException("BaoYinSatus is not exist : " + code);
    }
  }

  /**
   * 包银状人工审核状态值
   *
   * @author YM10152 人工审核状态 0审核中（风控和合同签订） 1图像资料待上传（风控通过） 2拒绝（风控和合同签订） 3补件（合同签订）
   *         4通过（合同签订）
   */
  public enum BaoyinAuditingState {
    _000("0", "审核中（风控和合同签订）"), _001("1", "图像资料待上传（风控通过 "), _002("2", "拒绝（风控和合同签订）"), _003("3", "补件（合同签订）"), _004("4", "通过（合同签订）");

    private final String code;
    private final String desc;

    private BaoyinAuditingState(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static MaritalStatus get(String code) {
      for (MaritalStatus flag : MaritalStatus.values()) {
        if (flag.getCode().equals(code))
          return flag;
      }
      throw new IllegalArgumentException("BaoyinAuditingState is not exist : " + code);
    }
  }

  /**
   * 操作类型
   */
  public enum OperationType {
    LOGO("logo", "标识"), CANCEL("cancel", "取消"), REJECT("reject", "拒绝"), RETURN("return", "退回"), HANG("hang", "挂起");

    private final String code;
    private final String value;

    private OperationType(String code, String value) {
      this.code = code;
      this.value = value;
    }

    public String getCode() {
      return code;
    }

    public String getValue() {
      return value;
    }

    public static String getOperationTypeVlue(String code) {
      for (OperationType otype : OperationType.values()) {
        if (otype.getCode().equals(code)) {
          return otype.getValue();
        }
      }
      return null;
    }
  }

  /**
   * 缓存类型-redis
   * 
   * @author ym10195
   */
  public enum CacheType {
    SYSTEM("system", 24, TimeUnit.HOURS); // 系统参数-终审层级

    private final String prefix; // 缓存前缀
    private final long expire; // 缓存时间
    private TimeUnit timeUnit; // 缓存时间单位

    private CacheType(String key, long expire, TimeUnit timeUnit) {
      this.expire = expire;
      prefix = key;
      this.timeUnit = timeUnit;
    }

    public String getPrefix() {
      return prefix;
    }

    public long getExpire() {
      return expire;
    }

    public TimeUnit getTimeUnit() {
      return timeUnit;
    }
  }

  /**
   * uflo流程枚举
   */
  public static final String BRANCH_ASSISTANT_MANAGER = "branchAssistantManager";// 客服副理
  public static final String BRANCH_MANAGER = "branchManager";// 客服经理

  public static final String SIGN_OPRATE_TYPE = "signOprateType";// 签约处理方式
  public static final String CONFIRM_OPRATE_TYPE = "confirmOprateType";// 确认处理方式

  /**
   * 流程节点
   */
  public static final String WF_KS = "开始";
  public static final String WF_BCXX = "保存信息";
  public static final String WF_PLATFROM_BCXX = "保存外部平台用户信息";
  public static final String WF_HMDJY = "黑名单校验";
  public static final String WF_YHKJQ = "银行卡鉴权";
  public static final String WF_FKSHJY = "风控审核";
  public static final String WF_SCHT = "生成合同";
  public static final String WF_HTQD = "合同签订";
  public static final String WF_HTQYDB = "合同签约待办";
  public static final String WF_ZZJK = "终止借款";
  public static final String WF_HTQR = "合同确认";
  public static final String WF_TJX = "退件箱";
  public static final String WF_FKSH = "放款审核";
  public static final String WF_FKQR = "放款确认";
  public static final String WF_FKQR_HX = "放款确认(核心)";
  public static final String WF_JS = "结束";
  public static final String WF_RGSH_LUJS = "陆金所人工审核";

  /**
   * 流程节点PATH
   */
  public static final String WFPH_BC = "保存";

  public static final String WFPH_JJQX = "拒绝/取消";
  public static final String WFPH_JJ_QX_QYGP = "拒绝/取消/签约改派";
  public static final String WFPH_TY = "同意";
  public static final String WFPH_BTY = "不同意";
  public static final String WFPH_TG = "通过";
  public static final String WFPH_TH = "退回";
  public static final String WFPH_BJ = "补件";

  public static final String WFPH_BCXX = "保存信息";
  public static final String WFPH_SCHT = "生成合同";
  public static final String WFPH_HTQD = "合同签订";
  public static final String WFPH_HTQR = "合同确认";
  public static final String WFPH_JS = "结束";

  public static final String WFPH_TSBD = "推送标的";
  public static final String WFPH_MBTZ = "满标通知";
  public static final String WFPH_BDFKTZ = "标的放款通知";
  public static final String WFPH_LBTZ = "流标通知";
  public static final String WFPH_LBTZ_TH = "流标通知/退回";

  public static final String WFPH_SCPC = "生成批次";
  public static final String WFPH_RGSH_LUJS = "陆金所人工审核";
  public static final String WFPH_RGSH_LUJS_RETURN1 = "返回一";
  public static final String WFPH_RGSH_LUJS_RETURN2 = "返回二";
  public static final String WFPH_SYB ="上一步";

  /**
   * 加急个数限制
   */
  public static final Integer LOAN_URGENT_CONFIG_VALUE = 100;
  public static final String PMS_SYSTEM_CODE = "pms";
  public static final String CFS_SYSTEM_CODE = "cfs";

  /**
   * 签约公共流程节点
   * 
   * @author YM10138
   *
   */
  public enum Sign {
    /**
     * 保存合同签约信息
     */
    SAVE("SAVE"),
    /**
     * 保存合同银行信息
     */
    SAVE_BANK_ACC("SAVE_BANK_ACC"),
    /**
     * 保存外部平台用户信息
     */
    SAVE_PLATFROM_ACC("SAVE_PLATFROM_ACC"),
    /**
     * 生成合同
     */
    CREATE_CONTRACT("CREATE_CONTRACT"),
    /**
     * 合同签订
     */
    SIGN_CONTRACT("SIGN_CONTRACT"),
    /**
     * 合同确认
     */
    CONTRACT_CONFIRM("CONTRACT_CONFIRM"),

    /**
     * 合同确认 退回
     */
    CONTRACT_CONFIRM_RETURN("CONTRACT_CONFIRM_RETURN"),

    /**
     * 放款审核
     */
    LOAN_TRIAL("LOAN_TRIAL"),

    /**
     * 放款审核 退回
     */
    LOAN_TRIAL_RETURN("LOAN_TRIAL_RETURN"),
    /**
     * 放款确认
     */
    LOAN_CONFIRM("LOAN_CONFIRM"),
    /**
     * 放款确认 退回
     */
    LOAN_CONFIRM_RETURN("LOAN_CONFIRM_RETURN"),

    /**
     * 银行卡鉴权、风控审核、生成合同、合同签订 拒绝,
     */
    CONTRACT_REFUSE_LOAN("CONTRACT_REFUSE_LOAN"),
    /**
     * 银行卡鉴权、风控审核、生成合同、合同签订 取消
     */
    CONTRACT_CANCEL_LOAN("CONTRACT_CANCEL_LOAN"),
    /**
     * 上一步
     */
    RETURN_LAST_STEP("RETURN_LAST_STEP"),
    /**
     * 取消
     */
    CANCEL("CANCEL"),
    /**
     * 拒绝
     */
    REFUSE("REFUSE"),
    /**
     * 人工审核（陆金所）
     */
    MANUAL_AUDIT("MANUAL_AUDIT");

    private String value;

    Sign(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 报文状态
   *
   * @author YM10152
   *
   */
  public enum ContractType {
    /** 终审退回初审 */
    ZZB("0", "纸质版"), DZB("1", "电子版");
    private final String code;
    private final String desc;

    private ContractType(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static ContractType get(String code) {
      for (ContractType contractType : ContractType.values()) {
        if (contractType.getCode().equals(code))
          return contractType;
      }
      throw new IllegalArgumentException("ContractType is not exist : " + code);
    }
  }

  /**
   * 标识颜色
   * 
   * @author YM10152
   *
   */
  public enum LogoFlagType {
    /** 终审退回初审 */
    ZC(0L, "正常"), HS(1L, "黄色");
    private final Long code;
    private final String desc;

    private LogoFlagType(Long code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public Long getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static LogoFlagType get(String code) {
      for (LogoFlagType logoFlagType : LogoFlagType.values()) {
        if (logoFlagType.getCode().equals(code))
          return logoFlagType;
      }
      throw new IllegalArgumentException("LogoFlagType is not exist : " + code);
    }
  }

  /**
   * 老政审的状态对应
   * 
   * @author YM10152
   *
   */
  public enum OldStatus {

    /** 申请录入 */
    正常("正常", "NORMAL"),
    /** 录入复核 */
    逾期("逾期", "OVERDUE"),
    /** 初审分派 */
    已结清("已结清", "COMPLETE"), 结清("结清", "COMPLETE"), 预结清("预结清", "PRECOMPLETE"),
    /** 信审初审 */
    拒绝("拒绝", "REJECT"),
    /** 终审分派 */
    取消("取消", "CANCEL"),
    /** 信审终审 */
    申请中("申请中", "APPLY"),
    /** 签约分派 */
    待签约("待签约", "PASS"),
    /** 合同签约 */
    待放款("待放款 ", "PASS");
    private final String code;
    private final String desc;

    private OldStatus(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static String get(String code) {
      for (OldStatus status : OldStatus.values()) {
        if (status.getCode().equals(code))
          return status.getDesc();
      }
      return "NORMAL";
    }
  }

  /** 未分派状态 */
  public enum noAssignment {
    初审通过("XSCS-PASS", "XSCS-PASS"), 终审提交协审("XSZS-SUBMIT-APPROVAL", "XSZS-SUBMIT-APPROVAL"), 终审提交高审("XSZS-SUBMIT-HIGH", "XSZS-SUBMIT-HIGH"), 终审退回("XSZS-SUBMIT-BACK", "XSZS-SUBMIT-BACK"), 提交高审(
        "HIGH-PASS", "HIGH-PASS");
    private final String code;
    private final String desc;

    private noAssignment(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static String get(String code) {
      for (noAssignment status : noAssignment.values()) {
        if (status.getCode().equals(code))
          return status.getDesc();
      }
      return null;
    }
  }

  /**
   * 原因解释[1.常规原因,2.特殊原因]
   *
   */
  public enum ReasonTexplain {

    /** 常规原因 */
    CGYY("1", "常规原因"),
    /** 特殊原因 */
    TSYY("2", "特殊原因");
    private final String code;
    private final String desc;

    private ReasonTexplain(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static LogoFlagType get(String code) {
      for (LogoFlagType logoFlagType : LogoFlagType.values()) {
        if (logoFlagType.getCode().equals(code))
          return logoFlagType;
      }
      throw new IllegalArgumentException("ReasonTexplain is not exist : " + code);
    }
  }

  /**
   * 陆金所通知类型
   *
   */
  public enum LuJSnotifyType {

    /** 反欺诈 */
    FQZ("1", "反欺诈");
    private final String code;
    private final String desc;

    private LuJSnotifyType(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static LuJSnotifyType get(String code) {
      for (LuJSnotifyType luJSnotifyType : LuJSnotifyType.values()) {
        if (luJSnotifyType.getCode().equals(code))
          return luJSnotifyType;
      }
      throw new IllegalArgumentException("LuJSnotifyType is not exist : " + code);
    }
  }

  /**
   * 陆金所通知结果code
   *
   */
  public enum LuJSnotifyCode {
    /** 信审通知 */
    XSTG("005", "信审通过"), XSBTG("00401", "信审不通过"), XSQCL("013", "信审缺乏材料"), XSSQEDBG("00201", "申请进件额度不够时的人工取消"),

    /** 反欺诈 */
    FQZTG("004", "反欺诈通过"), FQZJJ("00301", "反欺诈拒绝"),
    /** 确认合同人工取消 */
    QREDQX("00601", "确认合同额度不够时的人工取消");
    private final String code;
    private final String desc;

    private LuJSnotifyCode(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    public String getCode() {
      return code;
    }

    public String getDesc() {
      return desc;
    }

    public static LuJSnotifyCode get(String code) {
      for (LuJSnotifyCode luJSnotifyCode : LuJSnotifyCode.values()) {
        if (luJSnotifyCode.getCode().equals(code))
          return luJSnotifyCode;
      }
      throw new IllegalArgumentException("LuJSnotifyCode is not exist : " + code);
    }
  }

  /**
   * 机构审核状态
   *
   */
  public enum OrgAuditState {
    // 审核中 通过 不通过 补充材料 人工取消
    SHZ("1", "审核中"), TG("2", "通过"), BTG("3", " 不通过"), BCCL("4", "补充材料"), RGQX("5", "人工取消");
    private final String code;
    private final String value;

    private OrgAuditState(String code, String value) {
      this.code = code;
      this.value = value;
    }

    public String getCode() {
      return code;
    }

    public String getValue() {
      return value;
    }

    public static OrgAuditState get(String code) {
      for (OrgAuditState orgAuditState : OrgAuditState.values()) {
        if (orgAuditState.getCode().equals(code))
          return orgAuditState;
      }
      throw new IllegalArgumentException("OrgAuditStateCode is not exist : " + code);
    }
  }
}
