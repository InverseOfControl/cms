package com.ymkj.cms.biz.facade.sign.aite;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants.LoanStatus;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractNoticeExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractFileService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractNoticeService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

@Service
public class AiTeLoanContractNoticeExecuter implements IAiTeLoanContractNoticeExecuter {

	@Autowired
	private IAiTeLoanContractNoticeService aiTeloanContractNoticeService;
	
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private IAiTeLoanContractFileService aiTeLoanContractFileService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IBMSLoanExtEntityDao loanExtEntityDao;
	@Autowired
	private ILoanContractSignService loanContractSignService;

	// 借款密钥
	@Value("#{env['picSecretKey']?:''}")
	private String picSecretKey;
	
	@Override
	public Response<RequestVo> bidSuccessNotice(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<RequestVo> res = new Response<RequestVo>();
		
		boolean notifyFalg = false;
		// 参数校验
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			//编号解析
			requestVo.setBorrowNo(ValidationUtils.analysisAitePushLoanNo(requestVo.getBorrowNo()));
			//常规校验，含有标的锁定
			BMSLoanBaseEntity loanBaseEntity = loanSignDataOprateService.aiTeConventionCheck(requestVo);
			//通知处理开始	
			ReqLoanContractSignVo reqLoanContractSignVo= new ReqLoanContractSignVo();
			reqLoanContractSignVo.setId(loanBaseEntity.getId());
			reqLoanContractSignVo.setVersion(loanBaseEntity.getVersion());
			reqLoanContractSignVo.setLoanNo(loanBaseEntity.getLoanNo());
			reqLoanContractSignVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			reqLoanContractSignVo.setServiceName(EnumConstants.AITE_USER_NAME);
			reqLoanContractSignVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			//流程校验
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
			if(task !=null && !EnumConstants.WF_FKSH.equals(task.getTaskName())){
					throw new BizException(BizErrorCode.UFLOTASK_EOERR);
			}
			//放款审核通过
			boolean noticeFlag = aiTeLoanContractService.passAuditLoan(reqLoanContractSignVo);
			
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "success");
			
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			retMap.put("message", e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(requestVo);
		return res;

	}
	
	@Override
	public Response<RequestVo> targetLoan(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<RequestVo> res = new Response<RequestVo>();
		// 参数校验
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			//编号解析
			requestVo.setBorrowNo(ValidationUtils.analysisAitePushLoanNo(requestVo.getBorrowNo()));
			//常规校验
			BMSLoanBaseEntity loanBaseEntity = loanSignDataOprateService.aiTeConventionCheck(requestVo);
			requestVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			requestVo.setServiceName(EnumConstants.AITE_USER_NAME);
			requestVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			
			String taskDefKey = loanBaseEntity.getRtfNodeState();
			
			
			
			// 此笔标的不在放款确认环节，不能执行此操作
			if(null !=taskDefKey && !EnumConstants.RtfNodeState.FKSHSUBMIT.getValue().equals(taskDefKey))
			{
				throw new BizException(CoreErrorCode.NO_RULE_EXECUTE_ERROR, "此笔标的不在放款确认环节，不能执行此操作");
			}
			
			//通知处理开始
			retMap = aiTeloanContractNoticeService.targetLoan(loanBaseEntity);
			
			ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
			reqLoanContractSignVo.setId(loanBaseEntity.getId());
			reqLoanContractSignVo.setVersion(loanBaseEntity.getVersion());
			//标的解锁锁定
			boolean notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.DB_RESULT_ERROR, "操作失败");
			}
			
			
			ibmsSysLogService.recordSysLog(requestVo, "合同签约|签约代办|标的放款|confirm","AiTeLoanContractNoticeExecuter//targetLoan","标的放款");
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			retMap.put("message", e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(requestVo);
		return res;
	}


	@Override
	public Response<RequestVo> bidFailureNotice(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<RequestVo> res = new Response<RequestVo>();
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(BizErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			requestVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			requestVo.setServiceName(EnumConstants.AITE_USER_NAME);
			requestVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			//编号解析
			requestVo.setBorrowNo(ValidationUtils.analysisAitePushLoanNo(requestVo.getBorrowNo()));
			//常规校验
			BMSLoanBaseEntity loanBaseEntity = loanSignDataOprateService.aiTeConventionCheck(requestVo);
			//财务已放款，不能执行流标操作
			if(LoanStatus.NORMAL.getValue().equals(loanBaseEntity.getRtfState())){
				throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "财务已放款，不能执行流标操作");
			}
			
			
			//已流标，无需重新发起此操作
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", requestVo.getBorrowNo());
			paramMap.put("channelCode", EnumConstants.channelCode.AITE.getValue());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
			
			if(lockTargetEntity !=null && "D".equals(lockTargetEntity.getLockTarget())) {
				throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "已流标，无需重新发起此操作");
			}
			//流程校验，
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(loanBaseEntity.getId()));
			if(task !=null && 
					(EnumConstants.WF_FKSH.equals(task.getTaskName()) || EnumConstants.WF_FKQR.equals(task.getTaskName()))){
				//通知处理开始
				retMap = aiTeloanContractNoticeService.bidFailureNotice(loanBaseEntity);
				//流标同时，发起附件作作废操作
				//----------接口待定
				retMap = aiTeLoanContractFileService.deleteFile(requestVo);
				//标的解锁锁定
				ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
				reqLoanContractSignVo.setId(loanBaseEntity.getId());
				reqLoanContractSignVo.setLoanNo(loanBaseEntity.getLoanNo());
				reqLoanContractSignVo.setChannelCd(loanBaseEntity.getChannelCd());
				reqLoanContractSignVo.setServiceCode(EnumConstants.AITE_USER_CODE);
				reqLoanContractSignVo.setServiceName(EnumConstants.AITE_USER_NAME);
				reqLoanContractSignVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
				boolean notifyFalg = aiTeLoanContractService.discardLockTarget(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(BizErrorCode.DB_ERROR, "标的解锁失败");
				}
				//推送值加一
				Map<String,Object> extUpdateParam = new HashMap<String,Object>();
				extUpdateParam.put("loanNo", loanBaseEntity.getLoanNo());
				extUpdateParam.put("channelPushFrequency", loanBaseEntity.getChannelPushFrequency()+1);
				loanExtEntityDao.updateBySatus(extUpdateParam);
				
				ibmsSysLogService.recordSysLog(requestVo, "合同签约|捞财宝|流标通知|confirm","AiTeLoanContractNoticeExecuter//bidFailureNotice","流标通知");

				try {
					//完成当前任务,进入退件箱环节
					//完成当前任务
					TaskOpinion option =new TaskOpinion("流标通知");
					taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(),requestVo.getServiceCode(),EnumConstants.WFPH_LBTZ_TH,option);
					
					//捞财宝放款审核退回，流转到第一步保存
					reqLoanContractSignVo = new ReqLoanContractSignVo();
					reqLoanContractSignVo.setId(loanBaseEntity.getId());
					reqLoanContractSignVo.setTaskName(EnumConstants.WF_BCXX);
					loanContractSignService.returnBoxChoiceTask(reqLoanContractSignVo);
				} catch (Exception e) {
					throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
				}
			} else {
				throw new BizException(BizErrorCode.UFLOTASK_EOERR);
			}
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			retMap.put("message", e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(requestVo);
		return res;
	}


	

}
