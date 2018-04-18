package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 包银风控审核结果查询
 * Created by YM10140 on 2017/6/13.
 */
@Service
public class BaoShangAirControlCheckResultImpl extends BaseSign<ResLoanContractSignVo> {
    //包银渠道产品小类编号
    @Value("#{env['prodSubNo']?:''}")
    private String prodSubNo;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;


    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        JSONObject dataJson = new JSONObject();
        //查询出业务申请流水号
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        try {
            dataJson.put("busNumber", loanExtByLoanNo==null?"":loanExtByLoanNo.getBusNumber());// 业务申请流水号
            dataJson.put("txncd", "BYXY0004"); // 接口编号
        } catch (JSONException e1) {
            e1.printStackTrace();
            throw new BizException(BizErrorCode.EOERR, "接口参数设置异常");
        }
        //保存包银返回的状态值
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo",reqLoanContractSignVo.getLoanNo());
        try {
            HttpResponse response = baoShangHttpService.baoShangUrl("bsb100004", dataJson);
            JSONObject jsonRes = new JSONObject(response.getContent());
            //0审核中 1通过  2拒绝 3等待影像资料上传;
            JSONObject infos = jsonRes.getJSONObject("infos");
            if (infos.has("data")) {
                JSONObject jo = infos.getJSONObject("data");
                String respcd = jo.get("respcd").toString();
                String resptx = jo.get("resptx").toString();
                if ("0000".equals(respcd)) {
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._001.getCode());//通过
                    paramExt.put("windControlResult",resptx);
                    res.setRepMsg(resptx);
                    res.setRepCode("000000");
                }else if ("3800".equals(respcd)) {
                    if (resptx.contains("3802")) {
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._004.getCode());//包银拒绝并需补齐的资料信息
                        paramExt.put("windControlResult",resptx);
                        res.setRepMsg(resptx);
                        res.setRepCode("999994");
                    } else {
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._003.getCode());//影像资料待上传
                        paramExt.put("windControlResult",resptx);
                        res.setRepMsg(resptx);
                        res.setRepCode("999993");
                    }
                }else if("0100".equals(respcd)) {
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());//拒绝
                    paramExt.put("windControlResult",resptx);
                    res.setRepMsg(resptx);
                    res.setRepCode("999992");
                }else {
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._000.getCode());//风控审核中
                    paramExt.put("windControlResult",resptx);
                    res.setRepMsg(resptx);
                    res.setRepCode("999990");
                }
            } else {
                res.setRepCode("999999");
                res.setRepMsg("网关返回data为null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setRepCode("999999");
            res.setRepMsg("接口调用异常");
            throw new BizException(BizErrorCode.EOERR, "接口调用异常");
        }
        //更新包银返回的状态值
        long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
        if(updateBySatus!=1){
            throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"更新包银返回状态值失败！");
        }
        return true;
    }
}
