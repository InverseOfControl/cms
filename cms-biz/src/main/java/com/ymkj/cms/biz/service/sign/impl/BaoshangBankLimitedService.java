package com.ymkj.cms.biz.service.sign.impl;

import com.bstek.uflo.model.task.Task;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IBaoshangBankLimitedService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）
 * Created by YM10140 on 2017/6/28.
 */
@Service
public class BaoshangBankLimitedService implements IBaoshangBankLimitedService {

    private static Log logger = LogFactory.getLog(BaoshangBankLimitedService.class);
    @Autowired
    private IBMSLoanExtEntityService loanExtService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    /**
     * 包商银行-授信通知接口
     *
     * @param busNumber
     * @param respcd
     * @param resptx
     * @param patchType
     * @return
     */
    @Override
    public Map<String, String> confirmRiskManagementNotice(String busNumber, String respcd, String resptx, String patchType) {
        Map<String, String> resMap = new HashMap<String, String>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("busNumber", busNumber);
        //根据申请单号查询
        BMSLoanExt ext = loanExtService.findLoanExtByLoanNo(param);
        param.put("loanNo", ext.getLoanNo());
        Task task = taskService.findTaskByLoanBaseId(ext.getLoanBaseId());
        logger.info("===包银授信结果通知接口传入参数=== busNumber：" + busNumber + "，respcd：" + respcd + "，resptx："+resptx+ "，patchType："+patchType+",loanNo:"+ext.getLoanNo());
        if (null != busNumber) {
            /**0审核中（风控和合同签订） 1图像资料待上传（风控通过）  2拒绝（风控和合同签订） 3补件（合同签订）  4通过（合同签订）**/
            if (null != respcd && "0000".equals(respcd)) {
                //合同签订通过
                param.put("auditingState", EnumConstants.BaoyinAuditingState._004.getCode());//合同签订通过
                param.put("windControlResult", resptx);
                param.put("orgAuditState",EnumConstants.OrgAuditState.TG.getCode());//机构审核状态
            } else if ("0100".equals(respcd)) {
                param.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());//拒绝（风控和合同签订
                param.put("windControlResult", resptx);
               //风控审核返回拒绝当前交易结束
                if(task!=null && task.getTaskName().equals(EnumConstants.WF_FKSHJY)){
                    taskService.completeTaskByLoanBaseId(Long.valueOf(ext.getLoanBaseId()), EnumConstants.WFPH_JJQX);
                }else{
                    param.put("orgAuditState",EnumConstants.OrgAuditState.BTG.getCode());//机构审核状态
                }
            } else if ("3800".equals(respcd)) {
                //[3802]动态返回补件信息
                if (resptx.contains("3802")) {
                    param.put("auditingState", EnumConstants.BaoyinAuditingState._003.getCode());//补件（合同签订）
                    if(patchType!=null){
                        String[] split = patchType.split("\\|");
                        StringBuffer sb=new StringBuffer();
                        for (String str : split) {
                            /***
                             * 01身份证正面照片，
                             * 02身份证反面照片，
                             * 03手持身份证照片
                             * 04个人消费贷款合同扫描版原件（PDF格式，
                             * 05借款人手持个人消费贷款合同正面照片。
                             * 06个人征信查询授权协议扫描版原件照片,
                             * 07还款代扣授权书扫描版原件照片,
                             * 08个人信息使用和第三方机构数据查询授权书扫描版原件照片
                             * 14身份证复印件
                             * 15 申请表
                             * ********************/
                            if("01".equals(str)){
                                sb.append("身份证正面照片，");
                            }else if("02".equals(str)){
                                sb.append("身份证反面照片，");
                            }else if("03".equals(str)){
                                sb.append("手持身份证照片，");
                            }else if("04".equals(str)){
                                sb.append("个人消费贷款合同扫描版原件（PDF格式），");
                            }else if("05".equals(str)){
                                sb.append("借款人手持个人消费贷款合同正面照片，");
                            }else if("06".equals(str)){
                                sb.append("个人征信查询授权协议扫描版原件照片，");
                            }else if("07".equals(str)){
                                sb.append("还款代扣授权书扫描版原件照片，");
                            }else if("08".equals(str)){
                                sb.append("个人信息使用和第三方机构数据查询授权书扫描版原件照片，");
                            }else if("14".equals(str)){
                                sb.append("身份证复印件，");
                            }else if("15".equals(str)){
                                sb.append("机构申请表，");
                            }
                        }
                        param.put("byRefusalResult",patchType);//补件编号信息保存
                        param.put("windControlResult", resptx); //包银拒绝并需补齐的资料信息
                    }
                    param.put("orgAuditState",EnumConstants.OrgAuditState.BCCL.getCode());//机构审核状态
                } else {
                    //风控审核通过 [3801]影像资料待上传
                    param.put("auditingState", EnumConstants.BaoyinAuditingState._001.getCode());//图像资料待上传（风控通过）
                    param.put("windControlResult", resptx);
                    //完成风控审核节点任务  如果授信结果通知返回通过时则完成完成当前任务
                    if(task!=null && task.getTaskName().equals(EnumConstants.WF_FKSHJY)){
                        taskService.completeTaskByLoanBaseId(Long.valueOf(ext.getLoanBaseId()), EnumConstants.WFPH_TG);
                    }
                }
            }
            try {
                long satus = ibmsLoanExtEntityDao.updateBySatus(param);
                if (satus != 1) {
                    resMap.put("code", "999999");
                    resMap.put("msg", URLEncoder.encode("通知失败", "UTF-8"));
                } else {
                    resMap.put("code", "000000");
                    resMap.put("msg", URLEncoder.encode("通知成功", "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                logger.debug(e.getMessage());
            }
        } else {
            resMap.put("code", "999999");
            try {
                resMap.put("msg", URLEncoder.encode("通知失败", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.info(e.getMessage());
            }
        }
        logger.info("===包银授信结果通知接口返回参数===："+ resMap.toString());
        return resMap;
    }

    /**
     * 单据撤销通知
     *
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    @Override
    public Map<String, String> confirmRevokeNotice(String busNumber, String respcd, String resptx) {
        Map<String, String> resMap = new HashMap<String, String>();
        logger.debug("===包银单据撤销通知接口传入参数=== busNumber：" + busNumber + "，respcd：" + respcd + "，resptx："+resptx);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("busNumber", busNumber);
        param.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());
        param.put("windControlResult", resptx);
        try {
            long satus = ibmsLoanExtEntityDao.updateBySatus(param);
            if (satus == 1) {
                resMap.put("code", "000000");
                resMap.put("msg", URLEncoder.encode("通知成功", "UTF-8"));
            } else {
                resMap.put("msg", URLEncoder.encode("通知失败", "UTF-8"));
                resMap.put("code", "999999");
            }
        } catch (UnsupportedEncodingException e) {
            logger.debug(e.getMessage());
        }
        logger.debug("===包银单据撤销通知接口返回参数===："+ resMap.toString());
        return resMap;
    }

    /**
     * 授信成功后超时拒绝结果通知
     *
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    @Override
    public Map<String, String> confirmTimeOutRefuse(String busNumber, String respcd, String resptx) {
        Map<String, String> resMap = new HashMap<String, String>();
        logger.debug("===包银授信成功后超时拒绝结果通知接口传入参数=== busNumber：" + busNumber + "，respcd：" + respcd + "，resptx："+resptx);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("busNumber", busNumber);
        param.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());
        param.put("windControlResult", resptx);
        try {
            long satus = ibmsLoanExtEntityDao.updateBySatus(param);
            if (satus == 1) {
                resMap.put("msg", URLEncoder.encode("通知成功", "UTF-8"));
                resMap.put("code", "000000");
            } else {
                resMap.put("code", "999999");
                resMap.put("msg", URLEncoder.encode("通知失败", "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            logger.debug(e.getMessage());
        }
        logger.debug("===包银授信成功后超时拒绝结果通知接口返回参数===："+ resMap.toString());
        return resMap;
    }

    @Override
    public Map<String, String> loanRepeal(String idType, String idNo, String custName, String busNumber){
        Map<String, String> map = baoShangHttpService.loanRepeal(idType, idNo, custName, busNumber);
        return map;
    }

}
