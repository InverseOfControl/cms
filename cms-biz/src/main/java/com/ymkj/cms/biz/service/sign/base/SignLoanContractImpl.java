package com.ymkj.cms.biz.service.sign.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

/**
 * 签约 签订
 * 
 * @author YM10138
 *
 */
@Service
public class SignLoanContractImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance() .validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "loanNo" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getId() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "id" }), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getServiceCode())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" }), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "channelCd" }), res);
			return false;
		}
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String .valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_HTQD.equals(task.getTaskName())) {
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		
		String confirmUserCode = null;// 合同确认办理CODE
		String confirmUserName = null;// 合同确认办理NAME
		ResEmployeeVO branchManager = null; // 经理
		ResEmployeeVO branchAssistantManager = null; // 副理
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if (bmsLoanBaseEntity == null) {
			setError(new BizException(BizErrorCode.DB_RESULT_ISNULL,"更新失败，请刷新页面"), res);
			return false;
		}
		if (!StringUtils.isBlank(bmsLoanBaseEntity.getManagerCode())) {// 如果确认经理不为空，则为退回重新办理,确认经理为原经理
			confirmUserCode = bmsLoanBaseEntity.getManagerCode();
			confirmUserName = bmsLoanBaseEntity.getManagerName();
		} else {// 如果确认经理为空，则不退回重新办理,从员工上级副理，经理取
				// 根据签约客服得到副理
			branchAssistantManager = loanSignDataOprateService.getSignManager(
					reqLoanContractSignVo.getServiceCode(),
					EnumConstants.BRANCH_ASSISTANT_MANAGER);
			// 根据签约客服得到经理
			branchManager = loanSignDataOprateService.getSignManager(
					reqLoanContractSignVo.getServiceCode(),
					EnumConstants.BRANCH_MANAGER);

			if (branchManager == null && branchAssistantManager == null) {// 未找到经理副理
				setError(new BizException(BizErrorCode.MANAGER_NOTFOUND), res);
				return false;
			}

			// 如果副理为空 则为分配经理
			if (branchAssistantManager == null) {
				confirmUserCode = branchManager.getUsercode();
				confirmUserName = branchManager.getName();
			} else {
				confirmUserCode = branchAssistantManager.getUsercode();
				confirmUserName = branchAssistantManager.getName();
			}
		}
		reqLoanContractSignVo.setManagerCode(confirmUserCode);
		reqLoanContractSignVo.setManagerName(confirmUserName);
			
		//更新借款系统借款状态
		//构造 loanBaseId 和 借款状态RtfNodeState
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQR.getValue(),EnumConstants.RtfNodeState.HTQYSUBMIT.getValue(),reqLoanContractSignVo.getVersion());
		//合同确认经理
		loanBaseEntity.setManagerCode(reqLoanContractSignVo.getManagerCode());
		loanBaseEntity.setManagerName(reqLoanContractSignVo.getManagerName());
		loanBaseEntity.setSignEndDate(DateUtil.defaultFormatDate(new Date()));//签约结束时间
		//是否退回 录单RtfNodeState传过来是HTQYASSIGN非退回再签订
		if(EnumConstants.RtfNodeState.HTQYASSIGN.getValue().equals(reqLoanContractSignVo.getRtfNodeState())){
			loanBaseEntity.setIsReturn(EnumConstants.NO);
		}
		long count=  loanBaseEntityDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
		}
		//调用核心接口更新合同
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_SIGN);
		updateCoreLoanState(reqLoanContractSignVo,res);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		loanLog.setOperator(reqLoanContractSignVo.getServiceName());
		loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.SIGN.getValue());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|合同签订|sign","ILoanContractSignExecuter","signLoanContract");
			
		
		// 查询版本号 并传给前端
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		// 完成当前任务 并添加 合同确认环节 经理 ，副理流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		if (branchManager != null) {
			variables.put(EnumConstants.BRANCH_MANAGER,
					branchManager.getUsercode());
		}
		variables.put(EnumConstants.BRANCH_ASSISTANT_MANAGER, confirmUserCode);
		try {
			TaskOpinion option = new TaskOpinion("签订");
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WF_HTQD, variables, option);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
		}
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		
		return true;
	}
	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanContractSignVo reqLoanContractSignVo,Response<ResLoanContractSignVo> res){
		Map<String,Object> loanDataMap  = new HashMap<String,Object>();
		
		//构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBaseEntity bmsLoanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getLoanNo(),
				reqLoanContractSignVo.getServiceCode(),reqLoanContractSignVo.getState());
		
		loanDataMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());
		loanDataMap.put("userCode",bmsLoanBaseEntity.getUserCode());
		loanDataMap.put("stateCode",bmsLoanBaseEntity.getState());
		//调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);
		
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");	
		}else{
			return true;
		}
		
	}
}
