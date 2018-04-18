package com.ymkj.cms.web.boss.service.sign.impl;

import com.alibaba.fastjson.JSONObject;
import com.ymkj.base.core.web.result.JsonResult;
import com.ymkj.cms.web.boss.facade.sign.BaoshangBankLimitedFacade;
import com.ymkj.cms.web.boss.service.sign.IBaoshangBankLimitedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）
 * Created by YM10140 on 2017/6/27.
 */
@Service
public class BaoshangBankLimitedServiceImpl implements IBaoshangBankLimitedService {

    @Autowired
    private BaoshangBankLimitedFacade baoshangBankLimitedFacade;

    /**
     * 包银授信结果通知
     * @param receivePost
     * @return
     */
    @Override
    public JsonResult<String> riskManagementNotice(String receivePost) {
        JSONObject parse = JSONObject.parseObject(receivePost);
        JsonResult<String> jsonResult = baoshangBankLimitedFacade.riskManagementNotice
                (parse.getString("busNumber"), parse.getString("respcd"), parse.getString("resptx"), parse.getString("patchType"));
        return jsonResult;
    }

    /**
     * 单据撤销通知
     * @param receivePost
     * @return
     */
    @Override
    public JsonResult<String> revokeNotice(String receivePost) {
        JSONObject parse = JSONObject.parseObject(receivePost);
        JsonResult<String> jsonResult = baoshangBankLimitedFacade.revokeNotice
                (parse.getString("busNumber"), parse.getString("respcd"), parse.getString("resptx"));
        return jsonResult;
    }

    /**
     * 授信成功后超时拒绝结果通知
     * @param receivePost
     * @return
     */
    @Override
    public JsonResult<String> timeOutRefuse(String receivePost) {
        JSONObject parse = JSONObject.parseObject(receivePost);
        JsonResult<String> jsonResult = baoshangBankLimitedFacade.timeOutRefuse
                (parse.getString("busNumber"), parse.getString("respcd"), parse.getString("resptx"));
        return jsonResult;
    }

}
