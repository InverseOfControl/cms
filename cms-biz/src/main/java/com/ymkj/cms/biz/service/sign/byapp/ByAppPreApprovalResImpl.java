package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
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
 * 包银预授信结果查询-包银渠道改造APP接口
 * Created by YM10140 on 2017/6/13.
 */
@Service
public class ByAppPreApprovalResImpl extends BaseSign<ResLoanContractSignVo> {
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
            dataJson.put("txncd", "BYXY0021"); // 接口编号
        } catch (JSONException e1) {
            e1.printStackTrace();
            throw new BizException(BizErrorCode.EOERR, "接口参数设置异常");
        }
        //保存包银返回的状态值
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo",reqLoanContractSignVo.getLoanNo());
        try {
            HttpResponse response = baoShangHttpService.baoShangUrl("bsb100021", dataJson);
            JSONObject jsonRes = new JSONObject(response.getContent());
            //0审核中 1通过  2拒绝
            JSONObject infos = jsonRes.getJSONObject("infos");
            if (infos.has("data")) {
                JSONObject jo = infos.getJSONObject("data");
                String respcd = jo.get("respcd").toString();
                String resptx = jo.get("resptx").toString();
                if ("0000".equals(respcd)) {
                    res.setRepMsg(resptx);
                    res.setRepCode("000000");
                }else {
                    res.setRepMsg(resptx);
                    res.setRepCode(respcd);
                }
            } else {
                res.setRepCode("999999");
                res.setRepMsg("网关返回data为空，请稍后再试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setRepCode("999999");
            res.setRepMsg("接口调用异常");
            throw new BizException(BizErrorCode.EOERR, "接口调用异常");
        }
        return true;
    }
}
