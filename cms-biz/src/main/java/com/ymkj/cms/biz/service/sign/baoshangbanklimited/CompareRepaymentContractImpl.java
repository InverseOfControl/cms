package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.sign.IContractLoanDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.dao.sign.ILoanContractDao;
import com.ymkj.cms.biz.dao.sign.IRepaymentDetailDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.*;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.base.CreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.impl.LoanContractGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompareRepaymentContractImpl
 * @Description: 生成合同
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月16日 上午11:11:02
 */
@Service
public class CompareRepaymentContractImpl extends CreateLoanContractImpl {

    @Autowired
    private ICoreHttpService coreHttpService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private LoanContractGenerator loanContractGenerator;
    @Autowired
    private IBMSSysLogService ibmsSysLogService;
    @Autowired
    private ILoanSignDataOprateService loanSignDataOprateService;
    @Autowired
    private IBMSLoanExtEntityService loanExtService;
    @Autowired
    private ILoanContractDao loanContractDao;
    @Autowired
    private IContractLoanDao contractLoanDao;
    @Autowired
    private ILoanBaseEntityDao loanBaseEntityDao;
    @Autowired
    private IRepaymentDetailDao repaymentDetailDao;
    @Autowired
    private ILoanBaseEntityService loanBaseEntityService;
    @Autowired
    private LoanProductService loanProductService;

    private static final Log log = LogFactory.getLog(CompareRepaymentContractImpl.class);
    @Value("#{env['prodSubNo']?:''}")
    public String prodSubNo;

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        log.info("********************生成合同开始**********************loanNo:"+reqLoanContractSignVo.getLoanNo());
        String contractTypeCode=loanProductService.getContractType(reqLoanContractSignVo.getLoanNo());
        if (contractTypeCode == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "contractTypeCode" }), res);
            return false;
        }
        String loanNo = reqLoanContractSignVo.getLoanNo();
        Long id = reqLoanContractSignVo.getId();
        String org = "0000000"; //OrganizationContextHolder.getCurrentOrg(); // 机构号
        Map<String, Object> hxMap = new HashMap<String, Object>();
        Map<String, Object> retMap = new HashMap<String, Object>();
        hxMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt ext = loanExtService.findLoanExtByLoanNo(hxMap);
        //获取核心接口
        HttpResponse hx = coreHttpService.createBMSLoan(hxMap);
        //获取核心返回数据
        HttpContractReturnEntity contractReturnEntity = JsonUtils.decode(hx.getContent(), HttpContractReturnEntity.class);
        contractReturnEntity.getLoan().setLoanNo(loanNo);
        if (!"000000".equals(contractReturnEntity.getCode())) {//是否请求成功
            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息"), res);
            return false;
        }
        BMSLoanContract bmsLoanContract = contractReturnEntity.getContract();//借款合同信息
        BMSContractLoan bmsContractLoan = contractReturnEntity.getLoan();//合同借款信息

        //调用包商银行的生成还款计划接口
        JSONObject dataJson = new JSONObject();
        dataJson.put("prodSubNo", prodSubNo);// 产品小类编号
        dataJson.put("transAmt", new BigDecimal(String.format("%.2f", new BigDecimal(bmsContractLoan.getPactMoney()))));// 借款金额
        dataJson.put("totalCnt", bmsContractLoan.getTime());// 总期数
        String startRDateStr = bmsLoanContract.getStartRDate().substring(0, 10);
        startRDateStr = startRDateStr.replaceAll("-", "");
        dataJson.put("repayMethod", "03"); //还款方式--03等额本息
        dataJson.put("firstPayDate", startRDateStr);// 首期还款日
        dataJson.put("txncd", "BYXY0001"); // 接口编号

        List<BMSRepaymentDetail> repaylist = contractReturnEntity.getRepaymentDetail();//还款计划信息
        //将合同金额、审批期限、利率、计算方式（等额本息）字段信息推送至包商银行，由包商银行生成还款计划,将结果回退到借款系统
        HttpResponse bs = baoShangHttpService.baoShangUrl("bsb100001", dataJson);
        //获取包银信息
        JSONObject json = new JSONObject(bs.getContent());
        JSONArray listMap = new JSONArray();
        //校验网关返回的值
        JSONObject infos = json.getJSONObject("infos");
        if (infos.has("data")) {
            JSONObject jo = infos.getJSONObject("data");
            if (!jo.has("respcd") || !"0000".equals(jo.get("respcd"))) {
                setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "放款试算接口返回失败"), res);
                return false;
            } else {
                listMap = jo.getJSONArray("payPlanList");
            }
        } else {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, "接口调用网关返回data为null"), res);
            return false;
        }
        //比较核心与包商还款计划
        for (int i = 0; i < repaylist.size(); i++) {
            BMSRepaymentDetail bd = repaylist.get(i);
            for (int j = 0; j < listMap.length(); j++) {
                JSONObject jon = listMap.getJSONObject(j);
                if (bd.getCurrentTerm().equals(jon.getLong("period"))) {
                    BigDecimal schedulePrincipal = jon.getBigDecimal("schedulePrincipal");
                    BigDecimal scheduleInterest = jon.getBigDecimal("scheduleInterest");
                    if (bd.getReturneterm().compareTo(schedulePrincipal.add(scheduleInterest)) != 0) {
                        setError(new BizException(CoreErrorCode.NO_RULE_EXECUTE_ERROR, "第" + jon.getLong("period") + "期试算不一致"), res);
                        return false;
                    }
                }
            }
        }
        Long loanId = null;
        //保存合同信息
        if (bmsLoanContract != null) {
            if (loanContractDao.selectConutByLoanBaseId(id) > 0) {
                loanContractDao.deleteByLoanBaseId(id);
            }
            bmsLoanContract.setLoanBaseId(id);
            loanContractDao.insert(bmsLoanContract);
            loanId = bmsLoanContract.getLoanId();
        }
        //保存债权ID
        BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity();
        loanBaseEntity.setLoanId(loanId);
        loanBaseEntity.setId(id);
        loanBaseEntity.setVersion(reqLoanContractSignVo.getVersion());
        long upCount = loanBaseEntityDao.update(loanBaseEntity);
        if (upCount != 1) {
            setError(new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新债权ID失败，请检查参数"), res);
            return false;

        }
        //保存合同借款信息
        if (bmsContractLoan != null) {
            if (contractLoanDao.selectConutByLoanBaseId(id) > 0) {
                contractLoanDao.deleteByLoanBaseId(id);
            }
            bmsContractLoan.setLoanBaseId(id);
            bmsContractLoan.setLoanNo(loanNo);
            contractLoanDao.insert(contractReturnEntity.getLoan());
        }
        //保存还款计划
        if (repaylist != null && repaylist.size() > 0) {
            if (repaymentDetailDao.selectConutByLoanBaseId(id) > 0) {
                repaymentDetailDao.deleteByLoanBaseId(id);
            }
            for (BMSRepaymentDetail bmsRepaymentDetail : repaylist) {
                bmsRepaymentDetail.setLoanBaseId(id);
            }
            repaymentDetailDao.batchInsert(repaylist);
        }
        retMap.put("code", contractReturnEntity.getCode());
        retMap.put("message", contractReturnEntity.getMessage());
        //封装生成合同数据
        Map<String, Object> generatorMap = null;
        try {
            generatorMap = loanSignDataOprateService.setValueToContract(contractReturnEntity);
        } catch (Exception e) {
            log.info("*************封装合同数据失败***********" ,e);
            setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "封装合同数据失败"), res);
            return false;
        }
        //将业务申请流水号和贷款用途添加到合同文档上
        generatorMap.put("busNumber", ext.getBusNumber());
        /**贷款用途推送给包银时需要转化-04 装修、06 婚庆，01 家电、03 旅游******/
        //查询借款主表信息
        BMSLoanBaseEntity appMain = loanBaseEntityService.getLoanInfoByLoanNo(reqLoanContractSignVo.getLoanNo());
        BigDecimal contractLmt = appMain.getContractLmt();
        //04装修、06婚庆，01家电、03旅游
        if (contractLmt.compareTo(new BigDecimal(30000)) == 1) {
            java.util.Random random = new java.util.Random();//定义随机类
            int r = random.nextInt(2);
            String[] loanUse = {"装修", "婚庆"};
            generatorMap.put("loanUse", loanUse[r]);//贷款用途
        } else {
            java.util.Random random = new java.util.Random();//定义随机类
            int r = random.nextInt(2);
            String[] loanUse = {"家电", "旅游"};
            generatorMap.put("loanUse", loanUse[r]); //贷款用途
        }

        generatorMap.put("contractTypeCode", contractTypeCode);
        generatorMap.put("channelCd", reqLoanContractSignVo.getChannelCd());
        generatorMap.put("serviceName", reqLoanContractSignVo.getServiceName());
        generatorMap.put("createDate", DateUtil.defaultFormatDay(new Date()));
        generatorMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());

        loanSignDataOprateService.setRepayArrayToMap(generatorMap, repaylist);
        //生成合同
        loanContractGenerator.getContractTemplate(org, reqLoanContractSignVo.getChannelCd(), generatorMap, loanNo);
        //系统日志
        ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|生成合同|create", "ILoanContractSignExecuter", "createLoanContract");
        retMap.put("isSuceess", true);
        log.info("*******************生成合同完成*********************");
        return true;
    }
}
