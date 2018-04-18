package com.ymkj.cms.biz.service.sign.aite.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
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
import com.ymkj.cms.biz.service.sign.base.CreateLoanContractImpl;

/**
 * 生成合同
 * 
 * @author YM10138
 *
 */
@Service
public class AiTeCreateLoanContractImpl extends CreateLoanContractImpl {

	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	
	private static final Logger logger = LoggerFactory.getLogger(AiTeCreateLoanContractImpl.class);

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		if(super.before(reqLoanContractSignVo, res)){
			//校验之前是否被陆金所拒绝过，若发生过拒绝（审核不通过），则提示“渠道审核未通过，请改签其他渠道！”
			//推送数据判断  标识已被陆金所拒绝过渠道
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
			paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
			
			if(lockTargetEntity != null && "N".equals(lockTargetEntity.getLockTarget())){
				setError(new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, "渠道审核未通过，请改签其他渠道！"), res);
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		boolean notifyFalg = false;
		
		// 央行征信报告上传到爱特ftp服务器
		notifyFalg = aiTeLoanContractService.creditInvestigationUploadFile(reqLoanContractSignVo);
		if (!notifyFalg) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
		}

		// 上海资信报告上传到爱特ftp服务器
		notifyFalg = aiTeLoanContractService.creditReportUploadFile(reqLoanContractSignVo);
		if (!notifyFalg) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
		}

		// 人身份证信息上传到爱特ftp服务器
		notifyFalg = aiTeLoanContractService.IDCardUploadFile(reqLoanContractSignVo);
		if (!notifyFalg) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用人身份证信息上传返回失败消息");
		}
		
		//生成合同在，推送数据
		if(super.execute(reqLoanContractSignVo, res)){
			
//			若返回【上一步】，渠道仍为“捞财宝”，重新点击“生成合同”   ①　调用【标的降额】接口
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
			paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
			// 推送判断,推送后标的锁定
			if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget())) {
				//调用捞财宝的【标的降额】接口
				Map<String, String> requestMap = new HashMap<String, String>();

				requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
				
				HttpResponse httpResponse = aiTeHttpService.targetDerating(requestMap);
				
				if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
					String content = httpResponse.getContent();
					Map contentMap = JsonUtils.decode(content, Map.class);
					if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
						//降额后，这笔借款标的我们这边就认为无效了，取消锁定
						notifyFalg = aiTeLoanContractService.discardLockTarget(reqLoanContractSignVo);

					} else {
						throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法降额，请联系爱特");
					}
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
				}
			}
			
			// 标的推送部分
			notifyFalg = aiTeLoanContractService.targetPushedToAiTe(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "推送失败");
			}
			
			//标的锁定
			notifyFalg = aiTeLoanContractService.lockTarget(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(BizErrorCode.DB_RESULT_ERROR, "标的锁定失败");
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}

}
