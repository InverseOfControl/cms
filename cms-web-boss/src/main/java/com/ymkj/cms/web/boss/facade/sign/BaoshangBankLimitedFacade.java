package com.ymkj.cms.web.boss.facade.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.JsonResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.IBaoShangLoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.web.boss.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）----消费端
 * Created by YM10140 on 2017/6/27.
 */
@Component
public class BaoshangBankLimitedFacade extends BaseFacade {

    @Autowired
    private IBaoShangLoanContractSignExecuter iBaoShangLoanContractSignExecuter;

    /**
     *包银授信结果通知
     * @param busNumber
     * @param respcd
     * @param resptx
     * @param patchType
     * @return
     */
    public JsonResult<String> riskManagementNotice(String busNumber, String respcd, String resptx, String patchType){
        JsonResult<String> result = new JsonResult<>();
        ReqLoanContractSignVo reqLoanContractSignVo=new ReqLoanContractSignVo(EnumConstants.BMS_SYSTEM_CODE);
        reqLoanContractSignVo.setBusNumber(busNumber);
        reqLoanContractSignVo.setRespcd(respcd);
        reqLoanContractSignVo.setResptx(resptx);
        reqLoanContractSignVo.setPatchType(patchType);
        Response<ResLoanContractSignVo> response = iBaoShangLoanContractSignExecuter.riskManagementNotice(reqLoanContractSignVo);
        if (response.isSuccess()) {
            result.setResMsg(response.getRepMsg());
            result.setCode(response.getRepCode());
            return result;
        } else {
            throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
        }
    }

    /**
     * 单据撤销通知
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    public JsonResult<String> revokeNotice(String busNumber, String respcd, String resptx) {
        JsonResult<String> result = new JsonResult<>();
        ReqLoanContractSignVo reqLoanContractSignVo=new ReqLoanContractSignVo(EnumConstants.BMS_SYSTEM_CODE);
        reqLoanContractSignVo.setBusNumber(busNumber);
        reqLoanContractSignVo.setRespcd(respcd);
        reqLoanContractSignVo.setResptx(resptx);
        Response<ResLoanContractSignVo> response = iBaoShangLoanContractSignExecuter.revokeNotice(reqLoanContractSignVo);
        if (response.isSuccess()) {
            result.setCode(response.getRepCode());
            result.setResMsg(response.getRepMsg());
            return result;
        } else {
            throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
        }
    }

    /**
     * 授信成功后超时拒绝结果通知
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    public JsonResult<String> timeOutRefuse(String busNumber, String respcd, String resptx) {
        JsonResult<String> result = new JsonResult<>();
        ReqLoanContractSignVo reqLoanContractSignVo=new ReqLoanContractSignVo(EnumConstants.BMS_SYSTEM_CODE);
        reqLoanContractSignVo.setBusNumber(busNumber);
        reqLoanContractSignVo.setRespcd(respcd);
        reqLoanContractSignVo.setResptx(resptx);
        Response<ResLoanContractSignVo> response = iBaoShangLoanContractSignExecuter.timeOutRefuse(reqLoanContractSignVo);
        if (response.isSuccess()) {
            result.setCode(response.getRepCode());
            result.setResMsg(response.getRepMsg());
            return result;
        } else {
            throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
        }
    }
}
