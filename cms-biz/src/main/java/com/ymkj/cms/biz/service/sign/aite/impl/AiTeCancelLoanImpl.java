package com.ymkj.cms.biz.service.sign.aite.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.base.CancelLoanImpl;

/**
 * 签约环节     取消
 * 
 * @author YM10138
 *
 */
@Service
public class AiTeCancelLoanImpl extends CancelLoanImpl {

	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		boolean notifyFalg = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		// 推送判断,推送后标的锁定
		if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget())) {
			// 调用终止借款接口，待联调
			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
			requestMap.put("reason", reqLoanContractSignVo.getFirstLevleReasons());
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 处理成功 // 同意终止，通知核心
					//标的解锁锁定
					notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
					if (!notifyFalg) {
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
					}
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法取消，请联系爱特");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
			}
		}
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	

}
