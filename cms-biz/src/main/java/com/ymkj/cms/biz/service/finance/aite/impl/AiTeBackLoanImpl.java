package com.ymkj.cms.biz.service.finance.aite.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackLoan2Impl;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;


@Service
public class AiTeBackLoanImpl extends BackLoan2Impl{
	
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private IBMSLoanExtEntityDao loanExtEntityDao;
	
	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.before(reqLoanVo, res);
	}
	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanVo.getId());
		paramMap.put("channelCode", reqLoanVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		// 推送判断,推送后标的锁定
		if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget())) {
			// 调用终止借款接口，待联调
			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanVo.getLoanNo());
			requestMap.put("reason", "放款确认退回:"+reqLoanVo.getFirstLevleReasons());
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 处理成功 // 同意终止，通知核心
					ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
					reqLoanContractSignVo.setId(reqLoanVo.getId());
					reqLoanContractSignVo.setLoanNo(reqLoanVo.getLoanNo());
					reqLoanContractSignVo.setChannelCd(reqLoanVo.getChannelCd());
					reqLoanContractSignVo.setServiceCode(reqLoanVo.getServiceCode());
					reqLoanContractSignVo.setServiceName(reqLoanVo.getServiceName());
					reqLoanContractSignVo.setSysCode(reqLoanVo.getSysCode());
					
					//标的解锁锁定
					boolean notifyFalg = aiTeLoanContractService.discardLockTarget(reqLoanContractSignVo);
					if (!notifyFalg) {
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
					}
					//推送值加一
					// 签约信息查询 历史数据
					BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
					Map<String,Object> extUpdateParam = new HashMap<String,Object>();
					extUpdateParam.put("loanNo", loanInfo.getLoanNo());
					extUpdateParam.put("channelPushFrequency", loanInfo.getChannelPushFrequency()+1);
					loanExtEntityDao.updateBySatus(extUpdateParam);
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法取消，请联系爱特确认是否已放款");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
			}
		}
		return super.execute(reqLoanVo, res);
	}
	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		//完成任务
		TaskOpinion option =new TaskOpinion(EnumConstants.WFPH_LBTZ_TH);
		taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_LBTZ_TH,option);
		
		//捞财宝放款审核退回，流转到第一步保存
		taskService.completeTaskByLoanBaseId(reqLoanVo.getId(),EnumConstants.WFPH_SYB);
		return true;
	}
	
}
