package com.ymkj.cms.biz.service.sign.byapp;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.sign.PICUtils;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.master.*;
import com.ymkj.cms.biz.entity.sign.BMSContractLoan;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSDebtRadioService;
import com.ymkj.cms.biz.service.master.IBMSLoanAuditEntityService;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactInfoService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 风控审核预授信-包银渠道改造APP接口
 * Created by YM10140 on 2017/8/7.
 */
@Service
public class ByAppAirControlCheckImpl extends BaseSign<ResLoanContractSignVo> {
    //包银渠道产品小类编号
    @Value("#{env['prodSubNo']?:''}")
    private String prodSubNo;
    //包银渠道回调地址
    @Value("#{env['byCallback']?:''}")
    private String byCallback;
    //pic接口地址
    @Value("#{env['patchBoltUrl']?:''}")
    private String patchBoltUrl;
    //pic包银资料上传网关地址
    @Value("#{env['byPicGateWayUrl']?:''}")
    private String byPicGateWayUrl;
    //系统名称
    @Value("#{env['sysName']?:''}")
    private String sysName;
    //业务环节
    @Value("#{env['nodeKey']?:''}")
    private String nodeKey;
    //包银渠道aes加密秘钥
    @Value("#{env['byAesKey']?:''}")
    private String byAesKey;
    //银行卡鉴权提示语
    @Value("${bankNoAuditMsg}")
    private String bankNoAuditMsg;
    //风控审核提示语
    @Value("${byAirControlMsg}")
    private String byAirControlMsg;
    //审核系统获取最后一次审批意见表接口
    @Value("#{env['amsWebApiUrl']?:''}")
    private String amsWebApiUrl;

    @Autowired
    private ITaskService taskService;
    @Autowired
    private IBMSAppPersonInfoService ibmsAppPersonInfoService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private ILoanBaseEntityService loanBaseEntityService;
    @Autowired
    private IBMSTmAppContactInfoService ibmsTmAppContactInfoService;
    @Autowired
    private IBMSLoanAuditEntityService ibmsLoanAuditEntityService;
    @Autowired
    private ICoreHttpService coreHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private IAiTeHttpService aiTeHttpService;
    @Autowired
    private APPPersonInfoDao appPersonInfoDao;
    @Autowired
    private IBMSDebtRadioService debtRadioService;

    private static final Log log = LogFactory.getLog(ByAppAirControlCheckImpl.class);

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {

        // 参数校验
        Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
        if (!validateResponse.isSuccess()) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
            return false;
        }
        if (reqLoanContractSignVo.getId() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getLoanNo() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getChannelCd() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
            return false;
        }

        // 流程校验
        Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService
                .findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        if (task == null) {
            if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
                setError(new BizException(BizErrorCode.OPRATEUSER_EOERR, "当前签约客服异常，请刷新"), res);
                return false;
            }
        } else if (EnumConstants.WF_FKSHJY.equals(task.getTaskName())) {
            if (!reqLoanContractSignVo.getServiceCode().equals(task.getAssignee())) {
                setError(new BizException(BizErrorCode.OPRATEUSER_EOERR, "当前签约客服异常，请刷新"), res);
                return false;
            }
        } else {
            setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        log.info("*************************包银渠道风控审核预授信开始*************************");
        //查询条件
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());

        //预授信开始之前需要校验app是否完成提交处于第二次人工审核中
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = loanExtByLoanNo.getAuditingState();
        if (!StringUtils.isEmpty(state)) {
            if (EnumConstants.BaoyinAuditingState._002.getCode().equals(state)) {
                //拒绝了不能再签包银
                res.setRepMsg(byAirControlMsg + "（" + loanExtByLoanNo.getWindControlResult() + "）");
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                return true;
            } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
                //主动查询风控App提交后的结果
                JSONObject dataJson = new JSONObject();
                dataJson.put("busNumber", loanExtByLoanNo.getBusNumber());// 业务申请流水号
                dataJson.put("txncd", "BYXY0004"); // 接口编号
                HttpResponse response = baoShangHttpService.baoShangUrl("bsb100004", dataJson);
                JSONObject jsonRes = new JSONObject(response.getContent());
                JSONObject infos = jsonRes.getJSONObject("infos");
                //保存包银返回的状态值
                Map<String, Object> paramExt = new HashMap<>();
                paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
                if (infos.has("data")) {
                    JSONObject jo = infos.getJSONObject("data");
                    String respcd = jo.get("respcd").toString();
                    String resptx = jo.get("resptx").toString();
                    if ("0000".equals(respcd)) {
                        //预授信通过或者拒绝
                        if (resptx.contains("0002")) {
                            state = EnumConstants.BaoyinAuditingState._001.getCode();
                        } else if (resptx.contains("0105")) {
                            res.setRepMsg(byAirControlMsg + "（" + resptx + "）");
                            res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                            return true;
                        } else {
                            state = EnumConstants.BaoyinAuditingState._004.getCode();
                        }
                    } else if ("0100".equals(respcd)) {
                        //拒绝
                        res.setRepMsg(byAirControlMsg + "（" + resptx + "）");
                        res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                        return true;
                    } else if ("0500".equals(respcd)) {
                        state = EnumConstants.BaoyinAuditingState._000.getCode();
                    }
                } else {
                    res.setRepCode("999999");
                    res.setRepMsg("网关返回data为空，请稍后再试！");
                    return false;
                }
            }
            //根据状态值判断需不需要调用撤销接口 0 4
            if (EnumConstants.BaoyinAuditingState._000.getCode().equals(state) || EnumConstants.BaoyinAuditingState._004.getCode().equals(state)) {
                if (loanExtByLoanNo.getBusNumber() != null) {
                    //调撤销接口
                    log.info("************************调用包银撤销接口，busNumber：" + loanExtByLoanNo.getBusNumber());
                    if (!baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) {
                        return false;
                    }
                }
            }
        }

        //保存包银返回的状态
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //查询借款主表信息
        BMSLoanBaseEntity appMain = loanBaseEntityService.getLoanInfoByLoanNo(reqLoanContractSignVo.getLoanNo());
        if (appMain == null) {
            log.debug("*************************借款主表信息为空****");
            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "借款主表信息为空"), res);
            return false;
        }
        //获取客户信息
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(paramMap);
        if (appPersonInfo == null) {
            log.debug("*************************客户信息为空****");
            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "客户信息为空"), res);
            return false;
        }
        //查询出借款审核信息
        BMSLoanAudit bmsLoanAudit = ibmsLoanAuditEntityService.findBmsLoanAuditByLoanNo(reqLoanContractSignVo.getLoanNo());
        if (bmsLoanAudit == null) {
            log.debug("*************************借款审核信息为空****");
            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "借款审核信息为空"), res);
            return false;
        }

        /*****************基础报文参数配置ok************************************/
        JSONObject dataJson = new JSONObject();
        dataJson.put("prodSubNo", prodSubNo);
        dataJson.put("busNumber", "");// 业务流水号
        dataJson.put("mercNo", appMain.getOwningBranchId()); // 商户编号
        dataJson.put("mercName", appMain.getOwningBranch()); // 商户名称
        dataJson.put("custName", appPersonInfo.getName());// 客户姓名
        dataJson.put("idType", "01");// 证件类型
        dataJson.put("idNo", appPersonInfo.getIdNo()); // 证件号码
        dataJson.put("mobNo", appPersonInfo.getCellphone()); //手机号码
        dataJson.put("callbackUrl", byCallback); // 回调地址
        dataJson.put("gender", "男".equals(appPersonInfo.getGender()) || "M".equals(appPersonInfo.getGender()) ? "M" : "F"); // 性别 M-男 F-女
        dataJson.put("email", appPersonInfo.getEmail());// 用户邮箱
        dataJson.put("qqNum", appPersonInfo.getQqNum());// QQ号
        dataJson.put("isPreApproval", "01");//是否预授信 01，表示预授信，02表示真正授信

        /**************婚姻状态参数配置--证大渠道必传ok*******************/
        String marriage = appPersonInfo.getMaritalStatus();
        Map<String, Object> paramInfo = new HashMap<>();
        //查询出借款人的联系人列表
        paramInfo.put("loanNo", reqLoanContractSignVo.getLoanNo());
        List<BMSTmAppContactInfo> tmAppContactInfos = ibmsTmAppContactInfoService.listByLoanNo(paramInfo);
        BMSTmAppContactInfo spouseContactInfo = null;//配偶信息实体
        if (!StringUtils.isEmpty(marriage)) {
            if (!CollectionUtils.isEmpty(tmAppContactInfos)) {
                if (EnumConstants.MaritalStatus.MARRIED.getCode().equals(marriage) //已婚
                        ||EnumConstants.MaritalStatus.RESUME_MARRIAGE.getCode().equals(marriage)//复婚
                        ||EnumConstants.MaritalStatus.REMARRIAGE.getCode().equals(marriage)) {//再婚
                    for (BMSTmAppContactInfo appContactInfo : tmAppContactInfos) {
                        //找出借款人的配偶信息
                        if (EnumConstants.Relationship._00013.getCode().equals(appContactInfo.getContactRelation())) {
                            spouseContactInfo = appContactInfo;
                            break;
                        }
                    }
                    dataJson.put("marrStatus", "10"); // 婚姻状态 已婚
                    JSONObject spouseInfoJson = new JSONObject(); // 配偶信息json对象
                    if (null != spouseContactInfo) {
                        spouseInfoJson.put("spouseName",
                                spouseContactInfo.getContactName());// 配偶姓名
                        spouseInfoJson.put("idType", "01");// 配偶证件类型
                        spouseInfoJson.put("idNo",
                                spouseContactInfo.getContactIdNo());// 配偶证件号码
                        spouseInfoJson.put("mobNo",
                                spouseContactInfo.getContactCellphone());// 移动电话
                    } else {
                        spouseInfoJson.put("spouseName", "");// 配偶姓名
                        spouseInfoJson.put("idType", "01");// 配偶证件类型
                        spouseInfoJson.put("idNo", "");// 配偶证件号码
                        spouseInfoJson.put("mobNo", "");// 移动电话
                    }
                    dataJson.put("spouseInfo", spouseInfoJson); // 配偶信息
                } else if (EnumConstants.MaritalStatus.UN_MARRIED.getCode().equals(marriage)
                        || EnumConstants.MaritalStatus.WIDOWED.getCode().equals(marriage)) {//丧偶
                    dataJson.put("marrStatus", "20"); // 婚姻状态 未婚
                } else if (EnumConstants.MaritalStatus.DIVORCE.getCode().equals(marriage)) {
                    dataJson.put("marrStatus", "30"); // 婚姻状态 离异
                }else{
                    dataJson.put("marrStatus", "20");
                }
            }
        }

        /*****************最高学历参数配置--证大渠道必传ok***********************/
        String educationLevel = appPersonInfo.getQualification();
        if (null != educationLevel && !"".equals(educationLevel)) {
            if ("00001".equals(educationLevel)) {
                dataJson.put("eduDegree", "硕士及以上");// 最高学历
            } else if ("00002".equals(educationLevel)) {
                dataJson.put("eduDegree", "本科");// 最高学历
            } else if ("00003".equals(educationLevel)) {
                dataJson.put("eduDegree", "专科(高职)");// 最高学历
            } else if ("00004".equals(educationLevel)) {
                dataJson.put("eduDegree", "第二专科");// 最高学历
            } else {
                dataJson.put("eduDegree", "不详");// 最高学历
            }
        } else {
            dataJson.put("eduDegree", "不详");// 最高学历
        }
        dataJson.put("addrDetail", appPersonInfo.getHomeState() + appPersonInfo.getHomeCity() + appPersonInfo.getHomeZone() + appPersonInfo.getHomeAddress());// 居住地址-详细地址
        dataJson.put("cenRegiAdds", appPersonInfo.getIssuerState() + appPersonInfo.getIssuerCity() + appPersonInfo.getIssuerZone() + appPersonInfo.getIdIssuerAddress()); // 户籍地址
        /******************借款信息参数配置--证大渠道必传ok*********************/
        JSONObject loanInfoJson = new JSONObject(); // 借款信息
        //调用核心系统生成合同信息 生成合同 得到 合同金额 利率 期限 数据
        HttpResponse httpResponseLoan = coreHttpService.createBMSLoan(paramMap);
        HttpContractReturnEntity contractReturnEntity = JsonUtils.decode(httpResponseLoan.getContent(), HttpContractReturnEntity.class);
        if (!"000000".equals(contractReturnEntity.getCode())) {//是否请求成功
            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用核心生成合同接口失败"), res);
            return false;
        }

        BMSLoanContract bmsLoanContract = contractReturnEntity.getContract();//借款合同信息
        BMSContractLoan bmsContractLoan = contractReturnEntity.getLoan();//合同借款信息
        //查询借款合同信息
        loanInfoJson.put("repayInterval", "01"); // 还款间隔-证大按月 01：按月
        loanInfoJson.put("interRateModel", "01"); // 利率模式 01为固定，02：浮动-证大的为固定
        if (bmsContractLoan != null) {
            if (bmsContractLoan.getPactMoney() != null) {
                loanInfoJson.put("transAmt", new BigDecimal(String.format("%.2f", new BigDecimal(bmsContractLoan.getPactMoney())))); // 借款金额 Number(20,2)
            } else {
                loanInfoJson.put("transAmt", 0.00);
            }
            loanInfoJson.put("totalCnt", appMain.getContractTrem()); // 总期数
            loanInfoJson.put("repayMethod", "03"); // 还款方式--03等额本息
            String startRDateStr = bmsLoanContract.getStartRDate().substring(0, 10);
            startRDateStr = startRDateStr.replaceAll("-", "");
            loanInfoJson.put("firstPayDate", Integer.parseInt(startRDateStr)); // 首个还款日期
            if (bmsContractLoan.getRateEM() != null) {
                loanInfoJson.put("interestRate", new BigDecimal(String.format("%.2f", new BigDecimal(bmsContractLoan.getRateEM())))); //利率 Number (20,6)
            } else {
                loanInfoJson.put("interestRate", "0.00");// 利率（月）
            }
            /**贷款用途推送给包银时需要转化-04 装修、06 婚庆，01 家电、03 旅游******/
            BigDecimal contractLmt = appMain.getContractLmt();
            //04装修、06婚庆，01家电、03旅游
            if (contractLmt.compareTo(new BigDecimal(30000)) == 1) {
                java.util.Random random = new java.util.Random();//定义随机类
                int r = random.nextInt(2);
                String[] loanUse = {"04", "06"};
                loanInfoJson.put("loanUse", loanUse[r]); //贷款用途
            } else {
                java.util.Random random = new java.util.Random();//定义随机类
                int r = random.nextInt(2);
                String[] loanUse = {"01", "03"};
                loanInfoJson.put("loanUse", loanUse[r]); //贷款用途
            }
        }
        dataJson.put("loanInfo", loanInfoJson);
        dataJson.put("transAmt", new BigDecimal(String.format("%.2f", new BigDecimal(bmsContractLoan.getPactMoney()))));//贷款金额
        dataJson.put("totalCnt", appMain.getContractTrem());
        /******************单位信息参数配置--证大渠道必传ok*********************/
        JSONArray companyInfoJson = new JSONArray(); // 单位信息
        JSONObject companyJsonMyself = new JSONObject();// 本人工作信息
        companyJsonMyself.put("relation", "01"); // 关系
        companyJsonMyself.put("comyName", appPersonInfo.getCorpName());// 现单位名称
        companyJsonMyself.put("monthIncome", appPersonInfo.getMonthSalary());// 月均收入
        String comyNature = appPersonInfo.getCorpStructure();
        String byConynature = "";
        if (comyNature.equals("00001")) {
            byConynature = "01";
        } else if (comyNature.equals("00002")) {
            byConynature = "02";
        } else if (comyNature.equals("00003")) {
            byConynature = "03";
        } else if (comyNature.equals("00004")) {
            byConynature = "04";
        } else if (comyNature.equals("00005")) {
            byConynature = "05";
        } else if (comyNature.equals("00006")) {
            byConynature = "06";
        } else {
            byConynature = "07";
        }
        companyJsonMyself.put("comyNature", byConynature);// 现单位性质
        companyJsonMyself.put("comyAdd", appPersonInfo.getCorpProvince() + appPersonInfo.getCorpCity() + appPersonInfo.getCorpZone() + appPersonInfo.getCorpAddress());// 详细地址
        companyJsonMyself.put("comyZipCode", appPersonInfo.getCorpPostcode());// 现单位邮编
        companyInfoJson.put(companyJsonMyself);
        if (EnumConstants.MaritalStatus.MARRIED.getCode().equals(marriage) //已婚
                ||EnumConstants.MaritalStatus.RESUME_MARRIAGE.getCode().equals(marriage)//复婚
                ||EnumConstants.MaritalStatus.REMARRIAGE.getCode().equals(marriage)) {
            JSONObject companyJsonSpouse = new JSONObject();// 配偶工作信息
            companyJsonSpouse.put("relation", "02"); // 关系
            String contactEmpName = spouseContactInfo.getContactEmpName();
            companyJsonSpouse.put("comyName", contactEmpName == null ? "无" : contactEmpName);// 现单位名称
            companyJsonSpouse.put("monthIncome", new BigDecimal(String.format("%.2f", new BigDecimal("10"))));// 月均收入
            companyJsonSpouse.put("comyNature", "01");// 现单位性质
            companyJsonSpouse.put("comyAdd", "上海");// 详细地址
            companyJsonSpouse.put("comyZipCode", "010000");// 现单位邮编
            companyInfoJson.put(companyJsonSpouse);
        }
        dataJson.put("companyInfo", companyInfoJson);
        /******************用户联系人信息参数配置（非配偶）--证大渠道必传ok*********************/
        JSONArray userRelationInfoJson = new JSONArray(); // 用户联系人信息（非配偶）
        if (!CollectionUtils.isEmpty(tmAppContactInfos)) {
            for (BMSTmAppContactInfo appContactInfo : tmAppContactInfos) {
                JSONObject jsonContact = new JSONObject();
                jsonContact.put("contactsName", appContactInfo.getContactName());// 联系人名称
                String contactsRelations = "";
                String cr = appContactInfo.getContactRelation();
                if (cr.equals("00013")) {
                    continue;
                } else if (cr.equals("00001") || cr.equals("00002") || cr.equals("00003") || cr.equals("00004") || cr.equals("00005") || cr.equals("00006") || cr.equals("000010")) {
                    contactsRelations = "1";
                } else if (cr.equals("00007")) {
                    contactsRelations = "2";
                } else {
                    contactsRelations = "3";
                }
                jsonContact.put("contactsRelations", contactsRelations);// 用户联系人关系
                jsonContact.put("contactsMobile", appContactInfo.getContactCellphone());// 联系人电话
                userRelationInfoJson.put(jsonContact);
            }
        }
        dataJson.put("userRelationInfo", userRelationInfoJson);
        /******************银行卡认证信息参数配置--证大渠道必传ok*********************/
        JSONObject agentCustBankcardAuthInfo = new JSONObject();// 银行卡信息
        agentCustBankcardAuthInfo.put("bankCardNo", appMain.getApplyBankCardNo());
        agentCustBankcardAuthInfo.put("bankCardType", "1");
        agentCustBankcardAuthInfo.put("custName", appPersonInfo.getName());
        agentCustBankcardAuthInfo.put("idType", "01");
        agentCustBankcardAuthInfo.put("idNo", appPersonInfo.getIdNo());
        agentCustBankcardAuthInfo.put("mobileNo", appMain.getBankPhone());
        dataJson.put("agentCustBankcardAuthInfo", agentCustBankcardAuthInfo);
        /******************渠道风控信息参数配置--证大渠道必传ok*********************/

        JSONArray chanlRiskinfoJsonArray = new JSONArray();// 渠道风控信息

        JSONObject riskInfoJson1 = new JSONObject();// 系统评分
        riskInfoJson1.put("riskCode", "zd_risk_sys_score");
        riskInfoJson1.put("riskCodeValue", bmsLoanAudit.getPointResult() != null ? bmsLoanAudit.getPointResult() : "");
        chanlRiskinfoJsonArray.put(riskInfoJson1);

        JSONObject riskInfoJson2 = new JSONObject();// 系统判定核实收入
        riskInfoJson2.put("riskCode", "zd_risk_sys_income");
        riskInfoJson2.put("riskCodeValue", bmsLoanAudit.getSysCheckLmt() != null ? bmsLoanAudit.getSysCheckLmt() : "");
        chanlRiskinfoJsonArray.put(riskInfoJson2);

        JSONObject riskInfoJson3 = new JSONObject();// 客户建议审批额度
        riskInfoJson3.put("riskCode", "zd_risk_sys_credit_amt");
        riskInfoJson3.put("riskCodeValue", bmsLoanAudit.getSysAccLmt() != null ? bmsLoanAudit.getSysAccLmt() : "");
        chanlRiskinfoJsonArray.put(riskInfoJson3);

        JSONObject riskInfoJson4 = new JSONObject();// 最小可审批金额
        riskInfoJson4.put("riskCode", "zd_risk_sys_credit_min");
        riskInfoJson4.put("riskCodeValue", bmsLoanAudit.getMinApprovalAmt() != null ? new BigDecimal(String.format("%.2f", new BigDecimal(bmsLoanAudit.getMinApprovalAmt()))) : "");
        chanlRiskinfoJsonArray.put(riskInfoJson4);

        JSONObject riskInfoJson5 = new JSONObject();// 最大可审批金额
        riskInfoJson5.put("riskCode", "zd_risk_sys_credit_max");
        riskInfoJson5.put("riskCodeValue", bmsLoanAudit.getMaxApprovalAmt() != null ? new BigDecimal(String.format("%.2f", new BigDecimal(bmsLoanAudit.getMaxApprovalAmt()))) : "");
        chanlRiskinfoJsonArray.put(riskInfoJson5);

        JSONObject riskInfoJson6 = new JSONObject();// 系统建议审批期限
        riskInfoJson6.put("riskCode", "zd_risk_sys_credit_term");
        riskInfoJson6.put("riskCodeValue", bmsLoanAudit.getSysAccTrem() != null ? bmsLoanAudit.getSysAccTrem() : "");
        chanlRiskinfoJsonArray.put(riskInfoJson6);

        JSONObject riskInfoJson7 = new JSONObject();// 申请金额
        riskInfoJson7.put("riskCode", "zd_risk_app_credit");
        riskInfoJson7.put("riskCodeValue", bmsLoanAudit.getAccLmt() != null ? new BigDecimal(String.format("%.2f", new BigDecimal(bmsLoanAudit.getAccLmt()))) : "");
        chanlRiskinfoJsonArray.put(riskInfoJson7);

        JSONObject riskInfoJson8 = new JSONObject();// 申请期限
        riskInfoJson8.put("riskCode", "zd_risk_app_term");
        riskInfoJson8.put("riskCodeValue", bmsLoanAudit.getAccTerm() == null ? "" : bmsLoanAudit.getAccTerm());
        chanlRiskinfoJsonArray.put(riskInfoJson8);

        JSONObject riskInfoJson10 = new JSONObject();// 贷款类型
        riskInfoJson10.put("riskCode", "zd_risk_loan_type");
        riskInfoJson10.put("riskCodeValue", appMain.getProductCd() == null ? "" : appMain.getProductCd());
        chanlRiskinfoJsonArray.put(riskInfoJson10);
        /***********************************************************/
        JSONObject riskInfoJson11 = new JSONObject();// 初审审批额度
        riskInfoJson11.put("riskCode", "zd_risk_jstaff_credit");
        riskInfoJson11.put("riskCodeValue", appMain.getAccLmt() == null ? "" : appMain.getAccLmt());
        chanlRiskinfoJsonArray.put(riskInfoJson11);

        JSONObject riskInfoJson12 = new JSONObject();// 终审审批额度
        riskInfoJson12.put("riskCode", "zd_risk_sstaff_credit");
        riskInfoJson12.put("riskCodeValue", appMain.getAccLmt() == null ? "" : appMain.getAccLmt());
        chanlRiskinfoJsonArray.put(riskInfoJson12);

        JSONObject riskInfoJson9 = new JSONObject(); // 核实收入
        riskInfoJson9.put("riskCode", "zd_risk_confirmed_income");
        riskInfoJson9.put("riskCodeValue", bmsLoanAudit.getSysCheckLmt() == null ? "" : bmsLoanAudit.getSysCheckLmt());
        chanlRiskinfoJsonArray.put(riskInfoJson9);

        JSONObject riskInfoJson13 = new JSONObject();// 审批期限
        riskInfoJson13.put("riskCode", "zd_risk_approval_term");
        riskInfoJson13.put("riskCodeValue", appMain.getAccTerm() == null ? "" : appMain.getAccTerm());
        chanlRiskinfoJsonArray.put(riskInfoJson13);

        JSONObject riskInfoJson14 = new JSONObject();// 月还款额
        riskInfoJson14.put("riskCode", "zd_risk_monthly_repayment");
        riskInfoJson14.put("riskCodeValue", bmsContractLoan == null ? "" : bmsContractLoan.getMonthlyRepayment());
        chanlRiskinfoJsonArray.put(riskInfoJson14);

        //查询出借款系统的内部负债率
        /*******************内部负债率与总负债率与产品有关************************************/
        BMSDebtRadio debtRadio = new BMSDebtRadio();
        debtRadio.setCustomerTypeCode(appMain.getApplyType());
        BMSDebtRadio bmsDebtRadio = debtRadioService.findDebtRadioById(debtRadio);

        JSONObject riskInfoJson15 = new JSONObject();// 内部负债率
        riskInfoJson15.put("riskCode", "zd_risk_internal_debt_ratio");
        riskInfoJson15.put("riskCodeValue", bmsDebtRadio == null ? 0.00 : bmsDebtRadio.getInternalDebtRadio());
        chanlRiskinfoJsonArray.put(riskInfoJson15);

        JSONObject riskInfoJson16 = new JSONObject();//总负债率
        riskInfoJson16.put("riskCode", "zd_risk_total_debt_ratio");
        riskInfoJson16.put("riskCodeValue", bmsDebtRadio == null ? 0.00 : bmsDebtRadio.getTotalDebtRadio());
        chanlRiskinfoJsonArray.put(riskInfoJson16);

        //调用信审系统查询最近一次的审批意见表
        log.error("调用信审系统查询最近一次的审批意见表url:" + amsWebApiUrl);
        Map<String, String> strMap = new HashMap<>();
        strMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        strMap.put("rtfNodeState", "XSZS-PASS");
        strMap.put("sysCode", EnumConstants.BMS_SYSTEM_CODE);//系统编号
        strMap.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
        strMap.put("userCode",EnumConstants.BMS_SYSTEM_CODE);//登录用户
        String urlParam = CommonUtil.map2UrlParams(strMap);
        log.error("调用信审系统查询最近一次的审批意见表参数:" + urlParam);
        HttpResponse amsResponse = HttpUtil.post(amsWebApiUrl, urlParam, false);
        log.error("调用信审系统查询最近一次的审批意见表返回结果:" + JsonUtils.encode(amsResponse));
        String approvalMemo = null;//备注
        if (amsResponse.getCode() == 200) {
            if (!StringUtils.isEmpty(amsResponse.getContent())) {
                JSONObject json = new JSONObject(amsResponse.getContent());
                String repCode = json.getString("repCode");
                if (repCode.equals("000000")) {
                    JSONObject data = json.getJSONObject("data");
                    approvalMemo = data.getString("approvalMemo");
                }
            }
        }else{
            log.error("************************调用信审系统查询最近一次的审批意见表失败**************loanNo="+reqLoanContractSignVo.getLoanNo()+"amsWebUrl="+amsWebApiUrl);
            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用信审系统查询最近一次的审批意见表失败！"), res);
            return false;
        }
        if (approvalMemo == null) {
            log.error("*************************审批意见表备注信息为空**************loanNo="+reqLoanContractSignVo.getLoanNo());
            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "审批意见表备注信息为空！"), res);
            return false;
        }

        JSONObject riskInfoJson17 = new JSONObject();// 备注
        riskInfoJson17.put("riskCode", "zd_risk_comments");
        riskInfoJson17.put("riskCodeValue", approvalMemo == null ? "" : approvalMemo);
        chanlRiskinfoJsonArray.put(riskInfoJson17);

        JSONObject riskInfoJson18 = new JSONObject();// 现住房性质
        riskInfoJson18.put("riskCode", "zd_risk_house_property");
        if (appPersonInfo != null) {
            String ht = appPersonInfo.getHouseType() == null ? "" : appPersonInfo.getHouseType();
            if ("00002".equals(ht)) { //单位房
                riskInfoJson18.put("riskCodeValue", "05");//单位房
            } else if ("00003".equals(ht)) { //亲属房
                riskInfoJson18.put("riskCodeValue", "04"); //亲属房
            } else if ("00004".equals(ht)) { //租房
                riskInfoJson18.put("riskCodeValue", "06"); //租赁
            } else if ("00001".equals(ht)) { //自有住房  进行分类
                if ("ING".equals(ht)) { //还款中
                    riskInfoJson18.put("riskCodeValue", "01"); //有按揭房
                } else if ("ALL".equals(ht) || "END".equals(ht)) { // ALL-全款购 ，END-已结清
                    riskInfoJson18.put("riskCodeValue", "02");//无按揭房
                } else {
                    riskInfoJson18.put("riskCodeValue", "03"); //其他类型为  自建房
                }
            } else {
                riskInfoJson18.put("riskCodeValue", "");
            }
        } else {
            riskInfoJson18.put("riskCodeValue", "");
        }
        chanlRiskinfoJsonArray.put(riskInfoJson18);

        JSONObject riskInfoJson19 = new JSONObject();// 户口性质
        riskInfoJson19.put("riskCode", "zd_risk_huji_iflocal");
        riskInfoJson19.put("riskCodeValue", "");
        chanlRiskinfoJsonArray.put(riskInfoJson19);
        /*********************上海资信查询结果 ok *****************************/
        // 查询申请人信息
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanBaseId", reqLoanContractSignVo.getId());
        APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);
        //上海资信查询的结果map
        Map<String, String> reslutMap = new HashMap<String, String>();
        if (!StringUtils.isEmpty(tmAppPersonInfo.getNfcsId())) {
            Map<String, String> rquestMap = new HashMap<String, String>();
            rquestMap.put("id", tmAppPersonInfo.getNfcsId().toString());
            // 调用审核接口获取上海资信信息
            HttpResponse resObject = aiTeHttpService.getCreditStandingAndRespectabilitySH(rquestMap);
            String content = resObject.getContent();
            if (EnumConstants.HTTP_CODE_SUCCESS == resObject.getCode() && !StringUtils.isEmpty(content) && "000000".equals(new JSONObject(content).getString("code"))) {// 请求成功
                JSONObject jsonRes = new JSONObject(resObject.getContent());
                JSONObject resultJosn = jsonRes.getJSONObject("responseJson");
                PICUtils.decodeJSONObject(resultJosn, reslutMap);
            }
        }

        JSONObject riskInfoJson20 = new JSONObject();// 姓名
        riskInfoJson20.put("riskCode", "zd_shzx_name");
        riskInfoJson20.put("riskCodeValue", reslutMap.get("姓名") == null ? "" : reslutMap.get("姓名"));
        chanlRiskinfoJsonArray.put(riskInfoJson20);

        JSONObject riskInfoJson21 = new JSONObject();// 证件号码
        riskInfoJson21.put("riskCode", "zd_shzx_id");
        riskInfoJson21.put("riskCodeValue", reslutMap.get("证件号码") == null ? "" : reslutMap.get("证件号码"));
        chanlRiskinfoJsonArray.put(riskInfoJson21);

        JSONObject riskInfoJson22 = new JSONObject();// 性别
        riskInfoJson22.put("riskCode", "zd_shzx_gender");
        riskInfoJson22.put("riskCodeValue", reslutMap.get("性别") == null ? "" : reslutMap.get("性别"));
        chanlRiskinfoJsonArray.put(riskInfoJson22);

        JSONObject riskInfoJson23 = new JSONObject();// 出生日期
        riskInfoJson23.put("riskCode", "zd_shzx_birthday");
        riskInfoJson23.put("riskCodeValue", reslutMap.get("出生日期") == null ? "" : reslutMap.get("出生日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson23);

        JSONObject riskInfoJson24 = new JSONObject();// 婚姻状况
        riskInfoJson24.put("riskCode", "zd_shzx_maritalstatus");
        riskInfoJson24.put("riskCodeValue", reslutMap.get("婚姻明细") == null ? "" : reslutMap.get("婚姻明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson24);

        JSONObject riskInfoJson25 = new JSONObject();// 最高学历
        riskInfoJson25.put("riskCode", "zd_shzx_education");
        riskInfoJson25.put("riskCodeValue", reslutMap.get("学历明细") == null ? "" : reslutMap.get("学历明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson25);

        JSONObject riskInfoJson26 = new JSONObject();// 职称
        riskInfoJson26.put("riskCode", "zd_shzx_title");
        riskInfoJson26.put("riskCodeValue", reslutMap.get("职称明细") == null ? "" : reslutMap.get("职称明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson26);

        JSONObject riskInfoJson27 = new JSONObject();// 住宅电话
        riskInfoJson27.put("riskCode", "zd_shzx_homephone");
        riskInfoJson27.put("riskCodeValue", reslutMap.get("住宅电话明细") == null ? "" : reslutMap.get("住宅电话明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson27);

        JSONObject riskInfoJson28 = new JSONObject();// 手机号码
        riskInfoJson28.put("riskCode", "zd_shzx_cellphone");
        riskInfoJson28.put("riskCodeValue", reslutMap.get("手机号码明细") == null ? "" : reslutMap.get("手机号码明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson28);

        JSONObject riskInfoJson29 = new JSONObject();// 电子邮箱
        riskInfoJson29.put("riskCode", "zd_shzx_email");
        riskInfoJson29.put("riskCodeValue", reslutMap.get("电子邮箱明细") == null ? "" : reslutMap.get("电子邮箱明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson29);

        JSONObject riskInfoJson30 = new JSONObject();// 住址
        riskInfoJson30.put("riskCode", "zd_shzx_putress");
        riskInfoJson30.put("riskCodeValue", reslutMap.get("地址明细") == null ? "" : reslutMap.get("地址明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson30);

        JSONObject riskInfoJson31 = new JSONObject();// 工作单位
        riskInfoJson31.put("riskCode", "zd_shzx_company");
        riskInfoJson31.put("riskCodeValue", reslutMap.get("工作明细") == null ? "" : reslutMap.get("工作明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson31);

        JSONObject riskInfoJson32 = new JSONObject();// 职业
        riskInfoJson32.put("riskCode", "zd_shzx_profession");
        riskInfoJson32.put("riskCodeValue", reslutMap.get("职业") == null ? "" : reslutMap.get("职业"));
        chanlRiskinfoJsonArray.put(riskInfoJson32);

        JSONObject riskInfoJson33 = new JSONObject();// 配偶姓名
        riskInfoJson33.put("riskCode", "zd_shzx_spouse_name");
        riskInfoJson33.put("riskCodeValue", reslutMap.get("配偶姓名") == null ? "" : reslutMap.get("配偶姓名"));
        chanlRiskinfoJsonArray.put(riskInfoJson33);

        JSONObject riskInfoJson34 = new JSONObject();// 配偶证件号码
        riskInfoJson34.put("riskCode", "zd_shzx_spouse_id");
        riskInfoJson34.put("riskCodeValue", reslutMap.get("配偶证件号码") == null ? "" : reslutMap.get("配偶证件号码"));
        chanlRiskinfoJsonArray.put(riskInfoJson34);

        JSONObject riskInfoJson35 = new JSONObject();// 配偶性别
        riskInfoJson35.put("riskCode", "zd_shzx_spouse_gender");
        riskInfoJson35.put("riskCodeValue", reslutMap.get("配偶性别") == null ? "" : reslutMap.get("配偶性别"));
        chanlRiskinfoJsonArray.put(riskInfoJson35);

        JSONObject riskInfoJson36 = new JSONObject();// 配偶出生日期
        riskInfoJson36.put("riskCode", "zd_shzx_spouse_birthday");
        riskInfoJson36.put("riskCodeValue", reslutMap.get("配偶出生日期") == null ? "" : reslutMap.get("配偶出生日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson36);

        JSONObject riskInfoJson37 = new JSONObject();// 配偶联系电话
        riskInfoJson37.put("riskCode", "zd_shzx_spouse_tel");
        riskInfoJson37.put("riskCodeValue", reslutMap.get("配偶联系电话明细") == null ? "" : reslutMap.get("配偶联系电话明细"));
        chanlRiskinfoJsonArray.put(riskInfoJson37);

        JSONObject riskInfoJson38 = new JSONObject();// 第一联系人
        riskInfoJson38.put("riskCode", "zd_shzx_contact_name");
        riskInfoJson38.put("riskCodeValue", reslutMap.get("联系人姓名") == null ? "" : reslutMap.get("联系人姓名"));
        chanlRiskinfoJsonArray.put(riskInfoJson38);

        JSONObject riskInfoJson39 = new JSONObject();// 联系人关系
        riskInfoJson39.put("riskCode", "zd_shzx_contact_rel");
        riskInfoJson39.put("riskCodeValue", reslutMap.get("联系人关系") == null ? "" : reslutMap.get("联系人关系"));
        chanlRiskinfoJsonArray.put(riskInfoJson39);

        JSONObject riskInfoJson40 = new JSONObject();// 联系电话
        riskInfoJson40.put("riskCode", "zd_shzx_contact_tel");
        riskInfoJson40.put("riskCodeValue", reslutMap.get("联系电话") == null ? "" : reslutMap.get("联系电话"));
        chanlRiskinfoJsonArray.put(riskInfoJson40);

        JSONObject riskInfoJson41 = new JSONObject();// 信息获取时间
        riskInfoJson41.put("riskCode", "zd_shzx_personalinfo_date");
        riskInfoJson41.put("riskCodeValue", reslutMap.get("信息获取日期") == null ? "" : reslutMap.get("信息获取日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson41);

        JSONObject riskInfoJson42 = new JSONObject();// 申请机构
        riskInfoJson42.put("riskCode", "zd_shzx_app_insti");
        riskInfoJson42.put("riskCodeValue", reslutMap.get("申请机构") == null ? "" : reslutMap.get("申请机构"));
        chanlRiskinfoJsonArray.put(riskInfoJson42);

        JSONObject riskInfoJson43 = new JSONObject();// 申请时间
        riskInfoJson43.put("riskCode", "zd_shzx_app_date");
        riskInfoJson43.put("riskCodeValue", reslutMap.get("申请时间") == null ? "" : reslutMap.get("申请时间"));
        chanlRiskinfoJsonArray.put(riskInfoJson43);

        JSONObject riskInfoJson44 = new JSONObject();// 申请金额
        riskInfoJson44.put("riskCode", "zd_shzx_app_credit");
        riskInfoJson44.put("riskCodeValue", reslutMap.get("申请金额") == null ? "" : new BigDecimal(String.format("%.2f", new BigDecimal(reslutMap.get("申请金额").toString()))));
        chanlRiskinfoJsonArray.put(riskInfoJson44);

        JSONObject riskInfoJson45 = new JSONObject();// 申请月数
        riskInfoJson45.put("riskCode", "zd_shzx_app_month");
        riskInfoJson45.put("riskCodeValue", reslutMap.get("申请月数") == null ? "" : reslutMap.get("申请月数"));
        chanlRiskinfoJsonArray.put(riskInfoJson45);

        JSONObject riskInfoJson46 = new JSONObject();// 申请类型
        riskInfoJson46.put("riskCode", "zd_shzx_app_type");
        riskInfoJson46.put("riskCodeValue", reslutMap.get("申请类型") == null ? "" : reslutMap.get("申请类型"));
        chanlRiskinfoJsonArray.put(riskInfoJson46);

        JSONObject riskInfoJson47 = new JSONObject();// 申请状态
        riskInfoJson47.put("riskCode", "zd_shzx_app_stat");
        riskInfoJson47.put("riskCodeValue", reslutMap.get("申请状态") == null ? "" : reslutMap.get("申请状态"));
        chanlRiskinfoJsonArray.put(riskInfoJson47);

        JSONObject riskInfoJson48 = new JSONObject();// 信息获取时间
        riskInfoJson48.put("riskCode", "zd_shzx_app_date");
        riskInfoJson48.put("riskCodeValue", reslutMap.get("信息获取日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson48);

        JSONObject riskInfoJson49 = new JSONObject();// 贷款笔数
        riskInfoJson49.put("riskCode", "zd_shzx_loan_bill");
        riskInfoJson49.put("riskCodeValue", reslutMap.get("贷款笔数") == null ? "" : reslutMap.get("贷款笔数"));
        chanlRiskinfoJsonArray.put(riskInfoJson49);

        JSONObject riskInfoJson50 = new JSONObject();// 首贷日
        riskInfoJson50.put("riskCode", "zd_shzx_loan_firstcredit");
        riskInfoJson50.put("riskCodeValue", reslutMap.get("首贷日") == null ? "" : reslutMap.get("首贷日"));
        chanlRiskinfoJsonArray.put(riskInfoJson50);

        JSONObject riskInfoJson51 = new JSONObject();// 最大授信额度
        riskInfoJson51.put("riskCode", "zd_shzx_credit_max");
        riskInfoJson51.put("riskCodeValue", reslutMap.get("最大授信额度") == null ? "" : reslutMap.get("最大授信额度"));
        chanlRiskinfoJsonArray.put(riskInfoJson51);

        JSONObject riskInfoJson52 = new JSONObject();// 贷款总额
        riskInfoJson52.put("riskCode", "zd_shzx_loan_amt");
        riskInfoJson52.put("riskCodeValue", reslutMap.get("贷款总额") == null ? "" : reslutMap.get("贷款总额"));
        chanlRiskinfoJsonArray.put(riskInfoJson52);

        JSONObject riskInfoJson53 = new JSONObject();// 贷款余额
        riskInfoJson53.put("riskCode", "zd_shzx_loan_bal");
        riskInfoJson53.put("riskCodeValue", reslutMap.get("贷款余额") == null ? "" : reslutMap.get("贷款余额"));
        chanlRiskinfoJsonArray.put(riskInfoJson53);

        JSONObject riskInfoJson54 = new JSONObject();// 协定月还款
        riskInfoJson54.put("riskCode", "zd_shzx_loan_monthly_repayment");
        riskInfoJson54.put("riskCodeValue", reslutMap.get("协定月还款") == null ? "" : reslutMap.get("协定月还款"));
        chanlRiskinfoJsonArray.put(riskInfoJson54);

        JSONObject riskInfoJson55 = new JSONObject();// 当前逾期总额
        riskInfoJson55.put("riskCode", "zd_shzx_loan_pdamt");
        riskInfoJson55.put("riskCodeValue", reslutMap.get("当前逾期总额") == null ? "" : reslutMap.get("当前逾期总额"));
        chanlRiskinfoJsonArray.put(riskInfoJson55);

        JSONObject riskInfoJson56 = new JSONObject();// 最高逾期金额
        riskInfoJson56.put("riskCode", "zd_shzx_loan_pdamt_max");
        riskInfoJson56.put("riskCodeValue", reslutMap.get("最高逾期金额") == null ? "" : new BigDecimal(String.format("%.2f", new BigDecimal(reslutMap.get("最高逾期金额").toString()))));
        chanlRiskinfoJsonArray.put(riskInfoJson56);

        JSONObject riskInfoJson57 = new JSONObject();// 最高逾期期数
        riskInfoJson57.put("riskCode", "zd_shzx_loan_pdterm_max");
        riskInfoJson57.put("riskCodeValue", reslutMap.get("最高逾期期数") == null ? "" : new BigDecimal(reslutMap.get("最高逾期期数").toString()));
        chanlRiskinfoJsonArray.put(riskInfoJson57);

        JSONObject riskInfoJson58 = new JSONObject();// 信息获取日期
        riskInfoJson58.put("riskCode", "zd_shzx_loan_date");
        riskInfoJson58.put("riskCodeValue", reslutMap.get("信息获取日期") == null ? "" : reslutMap.get("信息获取日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson58);

        JSONObject riskInfoJson59 = new JSONObject();// 担保项目
        riskInfoJson59.put("riskCode", "zd_shzx_guarantee");
        riskInfoJson59.put("riskCodeValue", reslutMap.get("担保项目") == null ? "" : reslutMap.get("担保项目"));
        chanlRiskinfoJsonArray.put(riskInfoJson59);

        JSONObject riskInfoJson60 = new JSONObject();// 担保日期
        riskInfoJson60.put("riskCode", "zd_shzx_guarantee_date");
        riskInfoJson60.put("riskCodeValue", reslutMap.get("担保日期") == null ? "" : reslutMap.get("担保日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson60);

        JSONObject riskInfoJson61 = new JSONObject();// 担保金额
        riskInfoJson61.put("riskCode", "zd_shzx_guarantee_amt");
        riskInfoJson61.put("riskCodeValue", reslutMap.get("担保金额") == null ? "" : new BigDecimal(String.format("%.2f", new BigDecimal(reslutMap.get("担保金额").toString()))));
        chanlRiskinfoJsonArray.put(riskInfoJson61);

        JSONObject riskInfoJson62 = new JSONObject();// 担保关系
        riskInfoJson62.put("riskCode", "zd_shzx_guarantee_rel");
        riskInfoJson62.put("riskCodeValue", reslutMap.get("担保关系") == null ? "" : reslutMap.get("担保关系"));
        chanlRiskinfoJsonArray.put(riskInfoJson62);

        JSONObject riskInfoJson63 = new JSONObject();// 信息获取日期
        riskInfoJson63.put("riskCode", "zd_shzx_guarantee_date");
        riskInfoJson63.put("riskCodeValue", reslutMap.get("信息获取日期") == null ? "" : reslutMap.get("信息获取日期"));
        chanlRiskinfoJsonArray.put(riskInfoJson63);

        //获取签约营业部所在的城市
        String cityName = baoShangHttpService.getContractBranchCityNameById(Long.valueOf(appMain.getContractBranchId()));
        JSONObject riskInfoJson64 = new JSONObject();// 信息获取日期
        riskInfoJson64.put("riskCode", "zd_risk_appcity");
        riskInfoJson64.put("riskCodeValue", cityName == null ? "" : cityName);
        chanlRiskinfoJsonArray.put(riskInfoJson64);

        dataJson.put("chanlRiskinfo", chanlRiskinfoJsonArray);

        /***********************调用包银风控审核接口*********************************/
        HttpResponse response = baoShangHttpService.baoShangUrl("bsb100003", dataJson);
        JSONObject jsonRes = new JSONObject(response.getContent());
        JSONObject infos = jsonRes.getJSONObject("infos");
        if (infos.has("data")) {
            JSONObject jo = infos.getJSONObject("data");
            if (jo.has("respcd") && "0000".equals(jo.get("respcd"))) {
                //预授信通过
                String busNumber = jo.getString("busNumber");//业务申请流水号
                paramExt.put("windControlResult", jo.get("resptx"));
                paramExt.put("windControlDate", new Date());
                paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._001.getCode());//预授信通过
                paramExt.put("busNumber", busNumber);
                paramExt.put("byState", EnumConstants.BaoYinSatus._005.getCode());//预授信通过
                res.setRepCode("000000");
                res.setRepMsg(jo.get("resptx").toString());
                //更新借款系统包银返回的状态值
                long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (updateBySatus != 1) {
                    log.debug("*******************更新包银返回的状态值失败*******************");
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                }
                /*******************同时将业务流水号推送给核心************************/
                Map<String, Object> paramCore = new HashMap<String, Object>();
                paramCore.put("loanNo", reqLoanContractSignVo.getLoanNo());
                paramCore.put("busNumber", busNumber);
                // 调用核心系统推送业务流水号
                HttpResponse httpResponse = coreHttpService.saveOrUpdateBsyhBusNo(paramCore);
                HttpContractReturnEntity respon = JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
                if (!"000000".equals(respon.getCode())) {
                    //抛异常保证事务回滚
                    log.debug("*******************包银返回的业务申请流水号推送给核心失败：" + respon.getMessage());
                    throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "包银返回的业务申请流水号推送给核心失败");
                }
            } else if (jo.has("respcd") && "0100".equals(jo.get("respcd"))) {
                //预授信拒绝---黑名单检验失败
                paramExt.put("windControlResult", jo.get("resptx"));
                paramExt.put("windControlDate", new Date());
                paramExt.put("byState", EnumConstants.BaoYinSatus._000.getCode());
                paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());//拒绝
                res.setRepMsg(byAirControlMsg +jo.get("resptx") );
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                //更新借款系统包银返回的状态值
                long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (updateBySatus != 1) {
                    log.debug("*******************更新包银返回的状态值失败*******************");
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                }
            } else if (jo.has("respcd") && "3500".equals(jo.get("respcd"))) {
                //预授信拒绝---银行卡鉴权失败,可以再次发起鉴权
                paramExt.put("windControlDate", new Date());
                paramExt.put("windControlResult", jo.get("resptx"));
                paramExt.put("byState", EnumConstants.BaoYinSatus._002.getCode());
                res.setRepCode(jo.get("respcd").toString());
                res.setRepMsg(bankNoAuditMsg + jo.get("resptx"));
                //更新借款系统包银返回的状态值
                long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (updateBySatus != 1) {
                    log.debug("*******************更新包银返回的状态值失败*******************");
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                }
            } else {
                res.setRepCode("999999");
                res.setRepMsg(jo.get("resptx").toString());
            }
        } else {
            //接口调用异常
            res.setRepCode(jsonRes.getString("resCode"));
            String respDesc = jsonRes.getString("respDesc");
            if (null != respDesc) {
                res.setRepMsg(respDesc);
            } else {
                res.setRepMsg("网关返回data为空，请稍后再试！");
            }
        }
        log.debug("*************************风控审核推送信息完成*************************");
        return true;
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 查询版本号 并传给前端
        BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
        resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
        resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
        res.setData(resLoanSignVo);
        try {
            //风控通过则流转到生成合同环节
            if ("000000".equals(res.getRepCode())) {
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_TG);
            } else if (BizErrorCode.BAOYIN_REFUSE.getErrorCode().equals(res.getRepCode())) {
                //包银预授信拒绝，结束工作流
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_JJQX);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
        }
        return true;
    }
}
