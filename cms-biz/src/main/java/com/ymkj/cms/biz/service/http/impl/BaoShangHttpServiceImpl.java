package com.ymkj.cms.biz.service.http.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class BaoShangHttpServiceImpl implements BaoShangHttpService {

    //包银请求网关地址
    @Value("#{env['requestByUrl']?:''}")
    private String requestByUrl;

    //包银渠道秘钥
    @Value("#{env['requestByKey']?:''}")
    private String requestByKey;

    //pic业务环节
    @Value("#{env['nodeKey']?:''}")
    private String nodeKey;

    //pic接口地址
    @Value("#{env['patchBoltPicUrl']?:''}")
    private String patchBoltPicUrl;

    //获取pic目录中文件
    @Value("#{env['getIDCardPicUrl']?:''}")
    private String getIDCardPicUrl;

    //文件上传接口pic
    @Value("#{env['uploadPicUrl']?:''}")
    private String uploadPicUrl;

    @Value("#{env['prodSubNo']?:''}")
    public String prodSubNo;
    @Autowired
    private IBMSLoanExtEntityService ibmsLoanExtEntityService;
    @Autowired
    private IBMSAppPersonInfoService ibmsAppPersonInfoService;
    @Autowired
    private IOrganizationExecuter iOrganizationExecuter;


    @Override
    public HttpResponse baoShangUrl(String interfaceCode, JSONObject dataJson) {

        //参数格式化
        JSONObject json = dataFormat(interfaceCode, dataJson);
        try {
            //调用放款推送接口，得到返回的信息
            BmsLogger.info("调用包商银行接口,传送核心url：[{}]" + requestByUrl);
            BmsLogger.info("调用包商银行接口,传送核心数据：[{}]" + json.toString());
            //HttpResponse  httpResponse = HttpUtil.post("http://172.16.230.21:8086/creditGateway-web/preRequest", json.toString(), false);
            HttpResponse httpResponse = HttpUtil.post(requestByUrl, json.toString(), false);
            //解码
            httpResponse.setContent(URLDecoder.decode(httpResponse.getContent(), "UTF-8"));
            BmsLogger.info("调用包商银行接口,核心返回json：[{}]" + JsonUtils.encode(httpResponse));
            return httpResponse;
        } catch (Exception e) {
            BmsLogger.info(e.getMessage());
            throw new BizException(CoreErrorCode.FACADE_ERROR, "调用包商银行接口时异常");
        }
    }

    @Override
    public HttpResponse baoShangPic(Map<String, String> map) {
        //转换成url参数格式
        String urlParam = CommonUtil.map2UrlParams(map);
        try {
            //调用接口
            long currentTime = System.currentTimeMillis();
            BmsLogger.info("调用pic目录接口前的时间：[{}]" + currentTime);
            BmsLogger.info("调用pic目录接口URL及参数：" + patchBoltPicUrl + urlParam);
            HttpResponse httpResponse = HttpUtil.post(patchBoltPicUrl, urlParam, false);
            BmsLogger.info("调用pic目录接口后的时间：[{}]" + System.currentTimeMillis());
            BmsLogger.info("调用pic目录接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
            BmsLogger.info("调用pic目录接口，核心返回json信息：[{}]" + JsonUtils.encode(httpResponse));
            return httpResponse;
        } catch (Exception e) {
            BmsLogger.info(e.getMessage());
            throw new BizException(CoreErrorCode.FACADE_ERROR, "调用pic目录接口发生异常");
        }
    }


    @Override
    public HttpResponse baoShangPicFile(Map<String, String> map) {
        //转换成url参数格式
        String urlParam = CommonUtil.map2UrlParams(map);
        try {
            //调用接口
            long currentTime = System.currentTimeMillis();
            BmsLogger.info("调用pic目录下文件接口前的时间：[{}]" + currentTime);
            BmsLogger.info("调用pic目录下文件接口的URL及参数：" + getIDCardPicUrl + urlParam);
            HttpResponse httpResponse = HttpUtil.post(getIDCardPicUrl, urlParam, false);
            BmsLogger.info("调用pic目录下文件接口后的时间：[{}]" + System.currentTimeMillis());
            BmsLogger.info("调用pic目录下文件接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
            BmsLogger.info("调用pic目录下文件接口，核心返回json信息：[{}]" + JsonUtils.encode(httpResponse));
            return httpResponse;
        } catch (Exception e) {
            BmsLogger.info(e.getMessage());
            throw new BizException(CoreErrorCode.FACADE_ERROR, "调用pic目录下文件接口发生异常");
        }
    }

    @Override
    public Map<String, String> loanRepeal(String idType, String idNo, String custName, String busNumber) {
        Map<String, String> resMap = new HashMap<String, String>();
        JSONObject dataJson = new JSONObject();
        try {
            dataJson.put("prodSubNo", prodSubNo);
            dataJson.put("idType", idType);//证件类型
            dataJson.put("idNo", idNo);//证件号码
            dataJson.put("custName", custName);//客户姓名
            dataJson.put("busNumber", busNumber);//业务流水号
            dataJson.put("txncd", "BYXY0020"); // 接口编号
            HttpResponse bs = baoShangUrl("bsb100020", dataJson);
            String msg = "调用接口失败";
            String code = CoreErrorCode.NO_RULE_EXECUTE_ERROR.getErrorCode();
            if (null != bs) {
                JSONObject infos = (JSONObject) new JSONObject(bs.getContent()).get("infos");
                JSONObject jo = null;
                if (infos.has("data") && (jo = infos.getJSONObject("data")) != null) {
                    msg = (String) jo.get("resptx");
                    String byCode = (String) jo.get("respcd");
                    resMap.put("originCode", byCode);
                    if ("0000".equals(byCode) || "0001".equals(byCode) || "3400".equals(byCode)) {
                        code = "000000";
                    }
                }
            }
            resMap.put("code", code);
            resMap.put("msg", msg);
        } catch (JSONException e) {
            BmsLogger.info(e.getMessage());
            throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, e.getMessage());
        }
        return resMap;
    }

    @Override
    public boolean BaoShangLoanRepeal(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        Map<String, Object> param = new HashMap<>();
        param.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityService.findLoanExtByLoanNo(param);
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(param);
        String busNumber = loanExtByLoanNo.getBusNumber();
        //黑名单校验通过后有业务申请流水号返回
        if (busNumber != null && !EnumConstants.BaoyinAuditingState._002.getCode().equals(loanExtByLoanNo.getAuditingState())) {
            Map<String, String> map = this.loanRepeal("01", appPersonInfo.getIdNo(), appPersonInfo.getName(), busNumber);
            if (null != map && !map.isEmpty()) {
                //如果调用失败则不更新借款的状态
                if ("000000".equals(map.get("code"))) {
                    //包银撤销成功后置空包银状态值
                    BmsLogger.info("***********************包银撤销成功后置空包银状态值***********************loanNo:" + reqLoanContractSignVo.getLoanNo());
                    long byStatusNull = ibmsLoanExtEntityService.updateByStatusNull(reqLoanContractSignVo.getLoanNo());
                    if (byStatusNull != 1) {
                        BmsLogger.info("***********************包银撤销成功后置空包银状态值数据库操作异常***********************");
                        throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "包银撤销成功后置空包银状态值数据库操作异常！");
                    }
                } else {
                    res.setRepMsg(map.get("msg"));
                    res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode());
                    return false;
                }
            } else {
                res.setRepMsg("撤销接口调用失败");
                res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode());
                return false;
            }
        }
        return true;
    }

    /**
     * 调用pic上传文档接口
     *
     * @param map
     * @return
     */
    @Override
    public HttpResponse picFileUpload(Map<String, String> map) {
        //转换成url参数格式
        String urlParam = CommonUtil.map2UrlParams(map);
        try {
            //调用接口
            long currentTime = System.currentTimeMillis();
            BmsLogger.info("调用pic文件上传接口前的时间：[{}]" + currentTime);
            BmsLogger.info("调用pic文件上传接口URL及参数：" + uploadPicUrl + urlParam);
            HttpResponse httpResponse = HttpUtil.post(uploadPicUrl, urlParam, false);
            BmsLogger.info("调用pic文件上传接口后的时间：[{}]" + System.currentTimeMillis());
            BmsLogger.info("调用pic文件上传接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
            BmsLogger.info("调用pic文件上传接口，核心返回json信息：[{}]" + JsonUtils.encode(httpResponse));
            return httpResponse;
        } catch (Exception e) {
            BmsLogger.info(e.getMessage());
            throw new BizException(CoreErrorCode.FACADE_ERROR, "调用pic文件上传接口发生异常");
        }
    }

    /**
     * 根据签约营业部ID获取营业部所在城市
     *
     * @param id
     * @return
     */
    @Override
    public String getContractBranchCityNameById(Long id) {
        BmsLogger.info("调用平台系统查询营业部所在城市，营业部id：" + id);
        // 进件营业部详情
        ReqOrganizationVO organizationVO = new ReqOrganizationVO();
        organizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
        organizationVO.setId(id);
        Response<ResOrganizationInfo> orgInfoByID = iOrganizationExecuter.getOrgInfoByID(organizationVO);
        if (orgInfoByID.isSuccess()) {
            return orgInfoByID.getData().getCityName();
        } else {
            //接口调用失败
            BmsLogger.info("调用平台系统查询营业部所在城市，营业部id：" + id + "，接口调用失败");
            throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
        }
    }

    /**
     * 数据封装格式化
     *
     * @param dataJson
     * @param interfaceCode
     * @return
     */
    private JSONObject dataFormat(String interfaceCode, JSONObject dataJson) {
        JSONObject json = new JSONObject();
        long dateKey = System.currentTimeMillis();
        try {
            JSONObject params = new JSONObject();
            params.put("requestId", "aps" + dateKey);
            params.put("projectNo", "aps");
            params.put("reqParam", dataJson);
            json.put("funcId", interfaceCode);
            json.put("params", params);
            json.put("key", dateKey);
            String encryptionString = interfaceCode + params.toString() + dateKey + requestByKey;
            String sign = DigestUtils.md5Hex(encryptionString);
            json.put("sign", sign);
        } catch (JSONException e) {
            BmsLogger.info(e.getMessage());
        } catch (Exception e) {
            BmsLogger.info(e.getMessage());
        }
        return json;
    }
}
