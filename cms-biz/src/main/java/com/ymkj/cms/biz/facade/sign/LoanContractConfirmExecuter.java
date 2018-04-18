package com.ymkj.cms.biz.facade.sign;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.ILoanContractConfirmExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanContractConfirmExecuter implements ILoanContractConfirmExecuter {
	@Autowired
	private ILoanContractConfirmService loanContractConfirmService;
	
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	
	
	@Override
	public PageResponse<ResLoanContractSignVo> undoneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();
		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0 || reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		if(reqLoanContractSignVo.getServiceCode()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		try {
			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractConfirmService.undoneContractConfirmListBy(reqLoanContractSignVo);
			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();
			
			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		}catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage());
		}	
		return response;
	}
	

	
	@Override
	public PageResponse<ResLoanContractSignVo> doneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();

		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0 || reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		if(reqLoanContractSignVo.getServiceCode()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		try {

			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractConfirmService.doneContractConfirmListBy(reqLoanContractSignVo);

			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();
			
			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage());
		}	
		return response;
		
	}



	@Override
	public Response<ResLoanContractSignVo> confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		try {
			//合同确认推送影像资料
			if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
				return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
						EnumConstants.Sign.CONTRACT_CONFIRM.getValue());
			}
			boolean	confirmFlag =false;
			// 参数校验
			@SuppressWarnings("unchecked")
			Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
			if (!validateResponse.isSuccess()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
			}
	 		if(StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())){
	 			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
	 		}
	 		if(reqLoanContractSignVo.getId()==null){
	 			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});	
	 		}	
	 		if(StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())){
	 			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"});	
	 		}	
	 		//流程校验
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
			if(task == null ||!EnumConstants.WF_HTQR.equals(task.getTaskName())){
				throw new BizException(BizErrorCode.UFLOTASK_EOERR);
			}
			if(!reqLoanContractSignVo.getServiceCode().equals(task.getAssignee())){
				throw new BizException(BizErrorCode.OPRATEUSER_EOERR,"当前确认经理异常，请刷新");
			}
			//渠道特殊处理
			if(EnumConstants.channelCode.AITE.getValue().equals(reqLoanContractSignVo.getChannelCd())){
				boolean notifyFalg = false;
				// 央行征信报告上传到爱特ftp服务器
				notifyFalg = aiTeLoanContractService.creditInvestigationUploadFile(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
				}

				// 上海资信报告上传到爱特ftp服务器
				notifyFalg = aiTeLoanContractService.creditReportUploadFile(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
				}

				// 人身份证信息上传到爱特ftp服务器
				notifyFalg = aiTeLoanContractService.IDCardUploadFile(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用人身份证信息上传返回失败消息");
				}
				
				// 标的推送部分
				notifyFalg = aiTeLoanContractService.targetPushedToAiTe(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "推送失败");
				}
				
				// 调用合同确认通知捞财宝
				notifyFalg = aiTeLoanContractService.contractConfirmationToAiTe(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "推送失败");
				}
				
				
				//标的锁定
				notifyFalg = aiTeLoanContractService.lockTarget(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(CoreErrorCode.DB_RESULT_ERROR, "操作失败");
				}

			}
		 	try {
		 		confirmFlag = loanContractConfirmService.confirmLoanContract(reqLoanContractSignVo);
				if(!confirmFlag){
					throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
				}
				//完成任务
				Map<String,String> variables = new HashMap<String,String>();
				variables.put(EnumConstants.CONFIRM_OPRATE_TYPE, "确认");
				TaskOpinion option =new TaskOpinion("确认");
				try {
					taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_HTQR,option);
				} catch (Exception e) {
					throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,e.getMessage());
				}
			} catch (BizException e) {
				throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
			} 
	 	
			//查询版本号 并传给前端
		 	BMSLoanBaseEntity  bmsLoanBaseEntity =loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
			ResLoanContractSignVo resLoanSignVo=new ResLoanContractSignVo();
			resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
			resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
			res.setData(resLoanSignVo);
			res.setRepMsg("success");
			res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
			
	
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg((e.getErrorMsg()==null?"":e.getErrorMsg()+":") +e.getLocalizedMessage());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return res;
	}



	@Override
	public Response<ResLoanContractSignVo> backConfirm(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		if(SignFactory.containsChannelCd(reqLoanContractSignVo.getChannelCd())){
			return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
					EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue());
		}
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
 		if(StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())){
 			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
 		}
 		if(reqLoanContractSignVo.getId()==null){
 			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});	
 		}
 		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasons())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"});
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasonsCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"});
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(task == null || !EnumConstants.WF_HTQR.equals(task.getTaskName())){
			throw new BizException(BizErrorCode.UFLOTASK_EOERR);
		}
		if(!reqLoanContractSignVo.getServiceCode().equals(task.getAssignee())){
			throw new BizException(BizErrorCode.OPRATEUSER_EOERR,"当前确认经理异常，请刷新");
		}
		try {
		boolean backFlag=loanContractConfirmService.backConfirm(reqLoanContractSignVo);
		if(!backFlag){
			throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
		 }
		try {
			//完成当前任务
			TaskOpinion option =new TaskOpinion("退回");
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_TH,option);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		}  catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
		} 
		
			return res;
	}

	@Override
	public Response<Object> queryContractConfirmToDoCount(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<Object> response = new Response<Object>();
		
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());
		paramsMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
		paramsMap.put("taskName", EnumConstants.WF_HTQR);
		
		long count = loanContractConfirmService.queryContractConfirmToDoCount(paramsMap);
		response.setData(count);
		return response;
	}
	
	
	
}
