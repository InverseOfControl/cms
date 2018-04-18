package com.ymkj.cms.biz.facade.finance;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.BMSLoanConfirmationQueryVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.loan.LoanFactory;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanConfirmationQuery;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;


@Service
public class LoanExecuter  implements ILoanExecuter{
	
	@Autowired
	private ILoanService loanService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSOrgLimitChannelService ibmsOrgLimitChannelService;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private ICoreHttpService coreHttpService;

	
	//财务审核通过
	
	public Response<ResLoanVo> passAuditLoan(ReqLoanVo reqLoanVo){
		if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
			if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
				return LoanFactory.execute(reqLoanVo, reqLoanVo.getChannelCd(), EnumConstants.Sign.LOAN_TRIAL.getValue());
			}
		}
		Response<ResLoanVo> res = new Response<ResLoanVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		if(reqLoanVo.getId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
		}
		if(StringUtils.isBlank(reqLoanVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
		if(task == null || !EnumConstants.WF_FKSH.equals(task.getTaskName())){
			res.setRepCode(BizErrorCode.UFLOTASK_EOERR.getErrorCode());
			res.setRepMsg(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
			return res;
		}
		try {
			boolean passFlag = loanService.passAuditLoan(reqLoanVo);
			if(!passFlag){
				throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
			}
			//完成竞争任务
			TaskOpinion option =new TaskOpinion("通过");
			taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(), reqLoanVo.getServiceCode(), EnumConstants.WFPH_TG,option);
		} catch (BizException e) {
			BmsLogger.error("放款审核异常："+e.getMessage()+e.getArguments()[0]);
			res.setRepCode(BizErrorCode.EOERR.getErrorCode());
			res.setRepMsg(e.getMessage()+e.getArguments()[0]);
		} 
		return res;
	}
	


	@Override
	public Response<ResLoanVo> bacthPassAudit(ReqLoanVo reqLoanVo) {
		
		List<ResLoanVo>	successLoanList =new ArrayList<ResLoanVo>();
		List<ResLoanVo>	failLoanList =new ArrayList<ResLoanVo>();
		ResLoanVo resLoanVo =null;
		Response<ResLoanVo> res = new Response<ResLoanVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		if(reqLoanVo.getLoanList() == null || reqLoanVo.getLoanList().size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"});
		}
		List<ReqLoanVo> loanList=	reqLoanVo.getLoanList();
		
		for (ReqLoanVo reqLoanVo2 : loanList) {
			resLoanVo =new ResLoanVo();
			resLoanVo.setId(reqLoanVo2.getId());
			resLoanVo.setLoanNo(reqLoanVo2.getLoanNo());
			reqLoanVo2.setServiceCode(reqLoanVo.getServiceCode());
			reqLoanVo2.setServiceName(reqLoanVo.getServiceName());
			reqLoanVo2.setIp(reqLoanVo.getIp());
			reqLoanVo2.setChannelCd(reqLoanVo2.getChannelCd());
			reqLoanVo2.setSysCode(reqLoanVo.getSysCode());
			Response<ResLoanVo> inRes=passAuditLoan(reqLoanVo2);
			if(inRes.isSuccess()){
				successLoanList.add(resLoanVo);
			}else{
				resLoanVo.setErrorMessage(inRes.getRepMsg());
				failLoanList.add(resLoanVo);
			}
		}
		//封装返回结果
		ResLoanVo resultLoanVo = new ResLoanVo();
		resultLoanVo.setFailList(failLoanList);//失败的借款集合
		resultLoanVo.setSuccessList(successLoanList);//成功的借款集合
		res.setData(resultLoanVo);
		return res;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Response<ResLoanVo> backAudit(ReqLoanVo reqLoanVo) {
		if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
			if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
				return LoanFactory.execute(reqLoanVo, reqLoanVo.getChannelCd(), EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue());
			}
		}
		Response<ResLoanVo> res = new Response<ResLoanVo>();
		// 参数校验	
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		if(reqLoanVo.getLoanNo()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		if(reqLoanVo.getId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
		}
		if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasons())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"});
		}
		
		if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasonsCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"});
		}
		
		if(StringUtils.isBlank(reqLoanVo.getChannelCd())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"});
		}
		//渠道分流
		if(EnumConstants.channelCode.AITE.getValue().equals(reqLoanVo.getChannelCd())){
			//流程校验
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
			if(task == null || !EnumConstants.WF_FKSH.equals(task.getTaskName())){
				res.setRepCode(BizErrorCode.UFLOTASK_EOERR.getErrorCode());
				res.setRepMsg(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
				return res;
			}
			try {
				// 捞财宝渠道推送后，需要通知
				// 存在验证
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanNo", reqLoanVo.getLoanNo());
				BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
				if (loanBaseEntity == null) {
					throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, new Object[] { "loanNo" });
				}
				// 推送判断,推送后标的锁定
				if ("Y".equals(loanBaseEntity.getLockTarget())) {
					// 调用终止借款接口，待联调
					Map<String, String> requestMap = new HashMap<String, String>();
					requestMap.put("borrowNo", reqLoanVo.getLoanNo());
					requestMap.put("reason", "放款审核退回");
					HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
					if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
						String content = httpResponse.getContent();
						Map contentMap = JsonUtils.decode(content, Map.class);
						if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
							// 处理成功 // 同意终止，通知核心
							ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
							reqLoanContractSignVo.setId(reqLoanVo.getId());
							reqLoanContractSignVo.setServiceCode(reqLoanVo.getServiceCode());
							reqLoanContractSignVo.setServiceName(reqLoanVo.getServiceName());
							reqLoanContractSignVo.setSysCode(reqLoanVo.getSysCode());
							
							//标的解锁锁定
							boolean notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
							if (!notifyFalg) {
								throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
							}
						} else {
							throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,contentMap.get("repMsg"));
						}
					} else {
						throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
					}
				}
				boolean	 backFlag = loanService.backAudit(reqLoanVo);
				if(!backFlag){
					throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
				}
				//完成任务
				TaskOpinion option =new TaskOpinion("退回");
				taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_LBTZ_TH,option);
			} catch (BizException e) {
				res.setRepCode(e.getRealCode());
				String message = "";
				if (e.getArguments() != null && e.getArguments().length > 0) {
					for (int i = 0; i < e.getArguments().length; i++) {
						message += e.getArguments()[i];
					}
				}
				res.setRepMsg(e.getErrorMsg() +":"+ message);
			} catch (Exception e) {
				res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
				res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
			}
		} else {
			//流程校验
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
			if(task == null || !EnumConstants.WF_FKSH.equals(task.getTaskName())){
				res.setRepCode(BizErrorCode.UFLOTASK_EOERR.getErrorCode());
				res.setRepMsg(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
				return res;
			}
			try {
				boolean	 backFlag = loanService.backAudit(reqLoanVo);
				if(!backFlag){
					throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
				}
				//完成任务
				TaskOpinion option =new TaskOpinion("退回");
				taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_TH,option);
			}  catch (BizException e) {
				BmsLogger.error("放款确认退回异常："+e.getMessage()+e.getArguments()[0]);
				res.setRepCode(BizErrorCode.EOERR.getErrorCode());
				res.setRepMsg(e.getMessage()+e.getArguments()[0]);
			} 
			
		}
		
		return res;
	}
	
	
		//财务放款通过 
		@SuppressWarnings("unchecked")
		@Override
		public Response<ResLoanVo> grantLoan(ReqLoanVo reqLoanVo) {
			Map<String,Object> retMap = null;
			List<ResLoanVo>	successLoanList =null;
			List<ResLoanVo>	failLoanList =new ArrayList<ResLoanVo>();
			Response<ResLoanVo> res = new Response<ResLoanVo>();
			// 参数校验	
			Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
			}
			if(reqLoanVo.getLoanList() == null || reqLoanVo.getLoanList().size()==0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"});
			}
			
			//流程校验
			List<ReqLoanVo> loanList=	reqLoanVo.getLoanList();
			for (ReqLoanVo reqLoanVo2 : loanList) {
				Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo2.getId()));
				if(task == null || !EnumConstants.WF_FKQR.equals(task.getTaskName())){
					ResLoanVo viTaskFailLoanVO = new ResLoanVo();
					viTaskFailLoanVO.setLoanNo(reqLoanVo2.getLoanNo());
					viTaskFailLoanVO.setErrorMessage(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
					failLoanList.add(viTaskFailLoanVO);
					loanList.remove(reqLoanVo2);
				}
			}
		
			try {
				//放款确认
				retMap = loanService.grantLoan(reqLoanVo);
				successLoanList =(List<ResLoanVo>) retMap.get("successLoanList");
				failLoanList.addAll((List<ResLoanVo>) retMap.get("failLoanList"));
				//以下为新增内容，因为需要返回失败的以及成功的记录
				ResLoanVo resLoanVo = new ResLoanVo();
				resLoanVo.setFailList(failLoanList);//失败的借款集合
				resLoanVo.setSuccessList(successLoanList);//成功的借款集合
				res.setData(resLoanVo);
				//放款成功批量完成任务
				if(successLoanList != null){
					for (ResLoanVo successResLoanVo : successLoanList) {
						TaskOpinion option =new TaskOpinion("通过");
						taskService.compUsersTaskByLoanBaseId(successResLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_JS,option);
					}
				}
			} catch (BizException e) {
				BmsLogger.error("放款确认异常："+e.getMessage()+e.getArguments()[0]);
				res.setRepCode(BizErrorCode.EOERR.getErrorCode());
				res.setRepMsg(e.getMessage()+e.getArguments()[0]);
			} 
	
			return res;
		}
		
		//退回
		public Response<ResLoanVo> backLoan(ReqLoanVo reqLoanVo){
			if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
				if(LoanFactory.containsChannelCd(reqLoanVo.getChannelCd())){
					return LoanFactory.execute(reqLoanVo, reqLoanVo.getChannelCd(), EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue());
				}
			}
			Response<ResLoanVo> res = new Response<ResLoanVo>();
			// 参数校验
			@SuppressWarnings("unchecked")
			Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
			}
			if(reqLoanVo.getId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
			}
			if(StringUtils.isBlank(reqLoanVo.getLoanNo())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
			}
			if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasons())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"});
			}
			
			if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasonsCode())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"});
			}
			if(StringUtils.isBlank(reqLoanVo.getChannelCd())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"});
			}
			//渠道分流
			if(EnumConstants.channelCode.AITE.getValue().equals(reqLoanVo.getChannelCd())){
				//流程校验
				Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
				if(task == null || !EnumConstants.WF_FKQR.equals(task.getTaskName())||!EnumConstants.WF_FKQR_HX.equals(task.getTaskName())){
					res.setRepCode(BizErrorCode.UFLOTASK_EOERR.getErrorCode());
					res.setRepMsg(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
					return res;
				}
				try {
					// 捞财宝渠道推送后，需要通知
					if (EnumConstants.channelCode.AITE.getValue().equals(reqLoanVo.getChannelCd())) {
						// 存在验证
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("loanNo", reqLoanVo.getLoanNo());
						BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
						if (loanBaseEntity == null) {
							throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, new Object[] { "loanNo" });
						}
						// 推送判断,推送后标的锁定
						if ("Y".equals(loanBaseEntity.getLockTarget())) {
							// 调用终止借款接口，待联调
							Map<String, String> requestMap = new HashMap<String, String>();
							requestMap.put("borrowNo", reqLoanVo.getLoanNo());
							requestMap.put("reason", "放款审核退回");
							HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
							if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
								String content = httpResponse.getContent();
								Map contentMap = JsonUtils.decode(content, Map.class);
								if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
									// 处理成功 // 同意终止，通知核心
									ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
									reqLoanContractSignVo.setId(reqLoanVo.getId());
									reqLoanContractSignVo.setServiceCode(reqLoanVo.getServiceCode());
									reqLoanContractSignVo.setServiceName(reqLoanVo.getServiceName());
									reqLoanContractSignVo.setSysCode(reqLoanVo.getSysCode());
									
									//标的解锁锁定
									boolean notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
									if (!notifyFalg) {
										throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
									}
								} else {
									throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,"捞财宝拒绝终止借款");
								}
							}
						}
					}
					
					boolean	 backFlag = aiTeLoanContractService.backLoan(reqLoanVo);
					if(!backFlag){
						throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
					}
					//完成任务
					TaskOpinion option =new TaskOpinion("退回");
					taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_LBTZ_TH,option);
				}  catch (BizException e) {
					throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
				} 
			} else {
				
				//流程校验
				Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
				if(task == null || (!EnumConstants.WF_FKQR.equals(task.getTaskName()) && !EnumConstants.WF_FKQR_HX.equals(task.getTaskName()))){
					res.setRepCode(BizErrorCode.UFLOTASK_EOERR.getErrorCode());
					res.setRepMsg(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
					return res;
				}
				try {
					//调用
					boolean backFlag=loanService.backLoan(reqLoanVo);
					if(!backFlag){
						throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
					}
					//完成当前任务
					TaskOpinion option =new TaskOpinion("退回");
					taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_TH,option);
				}  catch (BizException e) {
					BmsLogger.error("放款确认退回异常："+e.getMessage()+e.getArguments()[0]);
					res.setRepCode(BizErrorCode.EOERR.getErrorCode());
					res.setRepMsg(e.getMessage()+e.getArguments()[0]);
				}
			}
			return res;
		}
		
		@Override
		public Response<ResLoanVo> bacthBackLoanAudit(ReqLoanVo reqLoanVo) {
			List<ResLoanVo>	successLoanList =new ArrayList<ResLoanVo>();
			List<ResLoanVo>	failLoanList =new ArrayList<ResLoanVo>();
			ResLoanVo resLoanVo =null;
			Response<ResLoanVo> res = new Response<ResLoanVo>();
			// 参数校验
			@SuppressWarnings("unchecked")
			Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
			}
			if(reqLoanVo.getLoanList() == null || reqLoanVo.getLoanList().size() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"});
			}
			List<ReqLoanVo> loanList=	reqLoanVo.getLoanList();
			
			for (ReqLoanVo reqLoanVo2 : loanList) {
				resLoanVo =new ResLoanVo();
				resLoanVo.setLoanNo(reqLoanVo2.getLoanNo());
				reqLoanVo2.setServiceCode(reqLoanVo.getServiceCode());
				reqLoanVo2.setServiceName(reqLoanVo.getServiceName());
				reqLoanVo2.setIp(reqLoanVo.getIp());
				reqLoanVo2.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
				reqLoanVo2.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
				reqLoanVo2.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
				reqLoanVo2.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
				reqLoanVo2.setRemark(reqLoanVo.getRemark());
				reqLoanVo2.setChannelCd(reqLoanVo2.getChannelCd());
				reqLoanVo2.setSysCode(reqLoanVo.getSysCode());
				Response<ResLoanVo> inRes=backAudit(reqLoanVo2);
				if(inRes.isSuccess()){
					successLoanList.add(resLoanVo);
				}else{
					resLoanVo.setErrorMessage(inRes.getRepMsg());
					failLoanList.add(resLoanVo);
				}
			}
			//封装返回结果
			ResLoanVo resultLoanVo = new ResLoanVo();
			resultLoanVo.setFailList(failLoanList);//失败的借款集合
			resultLoanVo.setSuccessList(successLoanList);//成功的借款集合
			res.setData(resultLoanVo);
			return res;
		}
		
		
		
		@Override
		public Response<ResLoanVo> bacthBackLoanConfirm(ReqLoanVo reqLoanVo) {
			List<ResLoanVo>	successLoanList =new ArrayList<ResLoanVo>();
			List<ResLoanVo>	failLoanList =new ArrayList<ResLoanVo>();
			ResLoanVo resLoanVo =null;
			Response<ResLoanVo> res = new Response<ResLoanVo>();
			// 参数校验
			@SuppressWarnings("unchecked")
			Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
			}
			if(reqLoanVo.getLoanList() == null || reqLoanVo.getLoanList().size() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"});
			}
			List<ReqLoanVo> loanList=	reqLoanVo.getLoanList();
			
			for (ReqLoanVo reqLoanVo2 : loanList) {
				resLoanVo =new ResLoanVo();
				resLoanVo.setLoanNo(reqLoanVo2.getLoanNo());
				reqLoanVo2.setServiceCode(reqLoanVo.getServiceCode());
				reqLoanVo2.setServiceName(reqLoanVo.getServiceName());
				reqLoanVo2.setIp(reqLoanVo.getIp());
				reqLoanVo2.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
				reqLoanVo2.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
				reqLoanVo2.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
				reqLoanVo2.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
				reqLoanVo2.setRemark(reqLoanVo.getRemark());
				reqLoanVo2.setChannelCd(reqLoanVo2.getChannelCd());
				reqLoanVo2.setSysCode(reqLoanVo.getSysCode());
				Response<ResLoanVo> inRes=backLoan(reqLoanVo2);
				if(inRes.isSuccess()){
					successLoanList.add(resLoanVo);
				}else{
					resLoanVo.setErrorMessage(inRes.getRepMsg());
					failLoanList.add(resLoanVo);
				}
			}
			//封装返回结果
			ResLoanVo resultLoanVo = new ResLoanVo();
			resultLoanVo.setFailList(failLoanList);//失败的借款集合
			resultLoanVo.setSuccessList(successLoanList);//成功的借款集合
			res.setData(resultLoanVo);
			return res;
		}


	/*@Override
	public Response<ResLoanVo> updateLoanState(ReqLoanVo reqLoanVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		Response<ResLoanVo> res = new Response<ResLoanVo>();
		try {
		retMap = loanService.updateLoanState(loanBaseEntity);
		}catch (CoreException e) {
			retMap.put("code", e.getRealCode());	
			retMap.put("message", e.getErrorMsg());	
		} catch (Exception e) {
		//e.printStackTrace();
			retMap.put("code", CoreErrorCode.UNKNOWN_ERROR.getErrorCode());	
			retMap.put("message",CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());	
		}
		res.setRepMsg((String)retMap.get("message"));
		res.setRepCode((String)retMap.get("code"));	
		return res;
	}*/

	
	@Override
	public PageResponse<ResLoanVo> listPage(ReqLoanVo reqLoanVo) {

		PageResponse<ResLoanVo> response = new PageResponse<ResLoanVo>();
		// 参数校验
		if(EnumConstants.WF_FKQR.equals(reqLoanVo.getTaskName())&&StringUtils.isBlank(reqLoanVo.getChannelCd())){
			reqLoanVo.setChannelCd(EnumConstants.channelCode.ZDP2P.getValue());
		}
		if (reqLoanVo.getPageNum() == 0 || reqLoanVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "pageNum | pageSize" });
		}
		if (StringUtils.isBlank(reqLoanVo.getServiceCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "serviceCode" });
		}
		try {

			// 构造请求参数
			PageParam pageParam = new PageParam(reqLoanVo.getPageNum(), reqLoanVo.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqLoanVo);
			if("".equals(paramMap.get("loanNos"))){
				paramMap.put("loanNos", null);
			}
			// 调用业务逻辑
			PageBean<BMSLoanBase> pageBean = loanService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResLoanVo> records = new ArrayList<ResLoanVo>();
			List<BMSLoanBase> demoEntitys = pageBean.getRecords();
			
			for (BMSLoanBase demoEntity : demoEntitys) {
				ResLoanVo resDemoVO = new ResLoanVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());	
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());	
		}
		return response;
	}

	
	
	@Override
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo) {
		ResListVO<ResLoanExportInfoVo> response = new ResListVO<ResLoanExportInfoVo>();
		// 参数校验
		if(EnumConstants.WF_FKQR.equals(reqLoanVo.getTaskName())&&StringUtils.isBlank(reqLoanVo.getChannelCd())){
			reqLoanVo.setChannelCd(EnumConstants.channelCode.ZDP2P.getValue());
		}
		try {

			// 构造请求参数;
			Map<String, Object> paramMap = BeanKit.bean2map(reqLoanVo);
			if("".equals(paramMap.get("loanNos"))){
				paramMap.put("loanNos", null);
			}
			// 调用业务逻辑
			List<BMSLoanBase>  demoEntitys= loanService.listBy(paramMap);

			// 构造响应参数
			
			List<ResLoanExportInfoVo> records = new ArrayList<ResLoanExportInfoVo>();
			
			for (BMSLoanBase demoEntity : demoEntitys) {
				ResLoanExportInfoVo resDemoVO = new ResLoanExportInfoVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			response.setParamList(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());	
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());	
		}
		return response;
	}

	
	@Override
	public PageResponse<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo) {

		PageResponse<ResLoanVo> response = new PageResponse<ResLoanVo>();

		// 参数校验
		if(EnumConstants.WF_FKQR.equals(reqLoanVo.getTaskName())&&StringUtils.isBlank(reqLoanVo.getChannelCd())){
			reqLoanVo.setChannelCd(EnumConstants.channelCode.ZDP2P.getValue());
		}
		
		// 参数校验
		if (reqLoanVo.getPageNum() == 0 || reqLoanVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 调用业务逻辑
			PageBean<BMSLoanBase> pageBean = loanService.doneListBy(reqLoanVo);

			// 构造响应参数
			List<ResLoanVo> records = new ArrayList<ResLoanVo>();
			List<BMSLoanBase> demoEntitys = pageBean.getRecords();
			
			for (BMSLoanBase demoEntity : demoEntitys) {
				ResLoanVo resDemoVO = new ResLoanVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());	
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()+e.getLocalizedMessage());	
		}
		return response;
	}
	
	public Response<String> valiProductIsDisabled( ReqLoanVo reqLoanVo){
		Response<String> res= new Response<String>();
		if(StringUtils.isBlank(reqLoanVo.getOwningBranchId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		if(StringUtils.isBlank(reqLoanVo.getChannelCd())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelCd"});
		}
		if(StringUtils.isBlank(reqLoanVo.getProductCd())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCd"});
		}
		if(reqLoanVo.getContractTrem() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"contractTrem"});
		}
		if(reqLoanVo.getContractLmt() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"contractLmt"});
		}
		Map<String, Object> params =new  HashMap<String, Object>();
		params.put("orgId", reqLoanVo.getOwningBranchId());
		params.put("channelCd", reqLoanVo.getChannelCd());
		params.put("productCd", reqLoanVo.getProductCd());
		params.put("contractTrem", reqLoanVo.getContractTrem());
		params.put("contractLmt",reqLoanVo.getContractLmt());
		 boolean isDisabled=  ibmsOrgLimitChannelService.isDisabledInSign(params);
		 if(isDisabled){
			 res.setData(EnumConstants.YES);;//被禁
		 }else{
			 res.setData(EnumConstants.NO);//未 被禁
		 }
		return res;
	}



	@Override
	public Response<BMSLoanConfirmationQueryVO> auditCommit(ReqBMSWmxtVO vo) {
		Response<BMSLoanConfirmationQuery> respons=loanService.auditCommit(vo);
		
		if(null!=respons.getData()){
			BMSLoanConfirmationQueryVO resBMSLoanBaseVO=new BMSLoanConfirmationQueryVO();
			BeanUtils.copyProperties(respons.getData(), resBMSLoanBaseVO);
			Response<BMSLoanConfirmationQueryVO> resBMSLoanBaseVOResponse=new Response<BMSLoanConfirmationQueryVO>();
			BeanUtils.copyProperties(respons, resBMSLoanBaseVOResponse);
			resBMSLoanBaseVOResponse.setData(resBMSLoanBaseVO);
			return resBMSLoanBaseVOResponse;
		}else{
			Response<BMSLoanConfirmationQueryVO> resBMSLoanBaseVOResponse=new Response<BMSLoanConfirmationQueryVO>();
			BeanUtils.copyProperties(respons, resBMSLoanBaseVOResponse);
			resBMSLoanBaseVOResponse.setData(null);
			return resBMSLoanBaseVOResponse;
		}
		
		
	}



	@Override
	public Response<Integer> findLoanIdbyFeeInfo(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		return loanService.findLoanIdbyFeeInfo(reqBMSLoanBaseVO);
	}
	
	@Override
	public Response<ResBMSLoanImportVO> queryUserLoanInfo(ReqImportExcelVO importExcelVO) {
		// TODO Auto-generated method stub
		return loanService.queryUserLoanInfo(importExcelVO);
	}
	
	




	@Override
	public Response<ResLoanVo> queryLoanCoreState(ReqLoanVo reqLoanVo) {
		if(SignFactory.containsChannelCd(reqLoanVo.getChannelCd())){
			
		}
		Response<ResLoanVo> res = new Response<ResLoanVo>();
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		if(reqLoanVo.getId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
		}
		if(StringUtils.isBlank(reqLoanVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
			
		try {
			Map<String, Object> httpParamMap  = new HashMap<String, Object>();
			httpParamMap.put("loanNos", reqLoanVo.getLoanNo());
			HttpResponse httpResponse =coreHttpService.queryLoanState(httpParamMap);
			if (httpResponse != null) {
				if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){ // 查询成功
					// 再次封装
					String content = httpResponse.getContent();
					Map contentMap = JsonUtils.decode(content, Map.class);
					if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("code"))){// 查询成功
						if(contentMap.get("loans") == null){
							throw new BizException(CoreErrorCode.ERROR_CODE_MESSAGE_FORMAT_BAD, "loans属性不存在");
						}
					}
					//依据核心状态更新数据
					List<Map> loans = (List<Map>) contentMap.get("loans");
					if(!loans.isEmpty()){
						List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
						for (Map map : loans) {
							if(map.get("loanNo") != null){
								resultList.add(map);
							}
						}
						ResLoanVo resLoanVo = new ResLoanVo();
						resLoanVo.setLoanCoreStateList(resultList);
						res.setData(resLoanVo);
						res.setRepCode(res.SUCCESS_RESPONSE_CODE);
					} else {
						throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, contentMap.get("message").toString());
					}
				} else {
					throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, httpResponse.getMessage());
				}
			}
		}  catch (BizException e) {
			BmsLogger.error("放款确认退回异常："+e.getMessage()+e.getArguments()[0]);
			res.setRepCode(BizErrorCode.EOERR.getErrorCode());
			res.setRepMsg(e.getMessage()+e.getArguments()[0]);
		}
		
		return res;
	}

}
