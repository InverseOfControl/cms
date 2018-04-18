package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.CancelLoanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 包银渠道取消-包银渠道改造APP接口
 */
@Service
public class ByAppCancelLoanImpl extends CancelLoanImpl {
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    private static final Log log = LogFactory.getLog(ByAppCancelLoanImpl.class);

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //获取当前任务节点
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = bmsLoanExt.getAuditingState();
        if (EnumConstants.BaoyinAuditingState._004.getCode().equals(state) || EnumConstants.BaoyinAuditingState._000.getCode().equals(state)) {
            //风控审核中或者风控通过上一步需要调用撤销接口
            if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) {
                //撤销成功
                return super.execute(reqLoanContractSignVo, res);
            } else {
                log.info("调用包银撤销接口失败，loanNo：" + reqLoanContractSignVo.getLoanNo());
                //撤销失败
                return false;
            }
        } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
            //主动查询风控App提交后的结果
            JSONObject dataJson = new JSONObject();
            dataJson.put("busNumber", bmsLoanExt.getBusNumber());// 业务申请流水号
            dataJson.put("txncd", "BYXY0004"); // 接口编号
            HttpResponse response = baoShangHttpService.baoShangUrl("bsb100004", dataJson);
            JSONObject jsonRes = new JSONObject(response.getContent());
            JSONObject infos = jsonRes.getJSONObject("infos");
            //保存包银返回的状态值
            Map<String, Object> paramExt = new HashMap<>();
            paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
            if (infos.has("data")) {
                JSONObject jo = infos.getJSONObject("data");
                String resptx = jo.get("resptx").toString();
                String respcd = jo.get("respcd").toString();
                if ("0000".equals(respcd) || "0500".equals(respcd)) {
                    if(resptx.contains("0105")|| resptx.contains("0002")){//预授信阶段不调用撤销接口
                        return super.execute(reqLoanContractSignVo, res);
                    }else{
                        if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) {
                            //撤销成功
                            return super.execute(reqLoanContractSignVo, res);
                        } else {
                            //撤销失败
                            log.info("调用包银撤销接口失败了，loanNo：" + reqLoanContractSignVo.getLoanNo());
                            return false;
                        }
                    }
                }
            } else {
                String respDesc = jsonRes.getString("respDesc");
                res.setRepMsg(respDesc);
                res.setRepCode("999999");
                return false;
            }
        }
        return super.execute(reqLoanContractSignVo, res);
    }
}
