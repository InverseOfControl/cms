package com.ymkj.cms.biz.service.sign.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractNoticeService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

@Service
public class AiTeLoanContractNoticeServiceImpl implements IAiTeLoanContractNoticeService {
	
	@Autowired
	ILoanContractConfirmService loanContractConfirmService;
	@Autowired
	ILoanService loanService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	
	@Override
	public Map<String, Object> bidSuccessNotice(BMSLoanBaseEntity loanBaseEntity) {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
		reqLoanContractSignVo.setId(loanBaseEntity.getId());
		reqLoanContractSignVo.setLoanNo(loanBaseEntity.getLoanNo());
		reqLoanContractSignVo.setVersion(loanBaseEntity.getVersion());
		reqLoanContractSignVo.setServiceCode(EnumConstants.AITE_USER_CODE);
		reqLoanContractSignVo.setServiceName(EnumConstants.AITE_USER_NAME);
		reqLoanContractSignVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
		
		//满标通知结束，流到放款审核
		boolean confirmFlag = aiTeLoanContractService.confirmLoanContract(reqLoanContractSignVo);
		if(confirmFlag){
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "success");
		} else {
			throw new BizException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
		}
		
		return retMap;
	}

	@Override
	public Map<String, Object> targetLoan(BMSLoanBaseEntity loanBaseEntity) {
		Map<String, Object> retMap = new HashMap<String, Object>();
			
			RequestVo requestVo = new RequestVo();

			//放款确认通过
			List<ReqLoanVo> voList = new ArrayList<ReqLoanVo>();
			ReqLoanVo reqLoanVo = new ReqLoanVo();
			
			reqLoanVo.setRtfNodeState(loanBaseEntity.getRtfNodeState());
			reqLoanVo.setRtfState(loanBaseEntity.getRtfState());
			reqLoanVo.setStatus(loanBaseEntity.getStatus());
			reqLoanVo.setLoanNo(loanBaseEntity.getLoanNo());
			reqLoanVo.setId(loanBaseEntity.getId());
			reqLoanVo.setTaskId(loanBaseEntity.getTaskId());
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			
			
			voList.add(reqLoanVo);
			
			ReqLoanVo reqLoanVoBase = new ReqLoanVo();
			reqLoanVoBase.setServiceCode(EnumConstants.AITE_USER_CODE);
			reqLoanVoBase.setServiceName(EnumConstants.AITE_USER_NAME);
			reqLoanVoBase.setLoanList(voList);
			reqLoanVoBase.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			
			retMap = aiTeLoanContractService.grantLoan(reqLoanVoBase);
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", retMap.get("msg"));
		return retMap;
	}

	@Override
	public Map<String, Object> bidFailureNotice(BMSLoanBaseEntity loanBaseEntity) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String taskDefKey = loanBaseEntity.getRtfState();
		
		
		boolean noticeFlag = false;
		if(EnumConstants.RtfState.FKSH.getValue().equals(taskDefKey)){ //放款审核（放款审核代办）流标
			
			ReqLoanVo reqLoanVo = new ReqLoanVo();
			reqLoanVo.setId(loanBaseEntity.getId());
			reqLoanVo.setLoanNo(loanBaseEntity.getLoanNo());
			reqLoanVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			reqLoanVo.setServiceName(EnumConstants.AITE_USER_NAME);
			reqLoanVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			reqLoanVo.setFirstLevleReasons("其他");
			reqLoanVo.setFirstLevleReasonsCode(EnumConstants.ReturnFirstLevleReasonCode.其他_FKSH.getValue());
			reqLoanVo.setTwoLevleReasons("其他");
			reqLoanVo.setTwoLevleReasonsCode(EnumConstants.ReturnTwoLevleReasonCode.其他_FKSH.getValue());
			reqLoanVo.setRemark("其他");
			noticeFlag = aiTeLoanContractService.backAudit(reqLoanVo);
			
			
		} else if(EnumConstants.RtfState.FKQR.getValue().equals(taskDefKey)){//放款确认（放款确认代办）流标
			
			ReqLoanVo reqLoanVo = new ReqLoanVo();
			reqLoanVo.setId(loanBaseEntity.getId());
			reqLoanVo.setLoanNo(loanBaseEntity.getLoanNo());
			reqLoanVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			reqLoanVo.setServiceName(EnumConstants.AITE_USER_NAME);
			reqLoanVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			reqLoanVo.setFirstLevleReasons("其他");
			reqLoanVo.setFirstLevleReasonsCode(EnumConstants.ReturnFirstLevleReasonCode.其他_FKQR.getValue());
			reqLoanVo.setTwoLevleReasons("其他");
			reqLoanVo.setTwoLevleReasonsCode(EnumConstants.ReturnTwoLevleReasonCode.其他_FKQR.getValue());
			reqLoanVo.setRemark("其他");
			
			noticeFlag = aiTeLoanContractService.backLoan(reqLoanVo);
			
//			noticeFlag = loanService.backAudit(reqLoanVo);
//			noticeFlag = aiTeLoanContractService.backAudit(reqLoanContractSignVo);
		} else {
			noticeFlag = true;
		}
		
		if(noticeFlag){
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "success");
		} else {
			throw new BizException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
		}
		
		return null;
	}
	
}
