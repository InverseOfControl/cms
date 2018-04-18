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
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

/**
 * 合同确认 退回
 * 
 * @author YM10138
 *
 */
@Service
public class BackConfirmImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private LoanExtDao loanExtDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
 		if(StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())){
 			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
			return false;
 		}
 		if(reqLoanContractSignVo.getId()==null){
 			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
			return false;
 		}
 		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasons())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"}), res);
			return false;
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasonsCode())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"}), res);
			return false;
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(task == null || !EnumConstants.WF_HTQR.equals(task.getTaskName())){
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		if(!reqLoanContractSignVo.getServiceCode().equals(task.getAssignee())){
			setError(new BizException(BizErrorCode.OPRATEUSER_EOERR,"当前确认经理异常，请刷新页面"), res);
			return false;
		}	

		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		try {
			//更新借款系统借款状态
			BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),EnumConstants.RtfNodeState.HTQRRETURN.getValue(),
																	reqLoanContractSignVo.getVersion());
			loanBaseEntity.setRtfState(EnumConstants.RtfState.HTQY.getValue());
			loanBaseEntity.setConfirmEndDate(DateUtil.defaultFormatDate(new Date()));//确认结束时间
			
			reqLoanContractSignVo.setState(EnumConstants.CONTRACT_BACK);
			
			//构造 LoanNo 和 借款状态useCode 和 state
			boolean flag = updateCoreLoanState(reqLoanContractSignVo, res);
			if(!flag){
				return false;
			}		
			
			//调用接口更新合同
			long count=  loanBaseDao.update(loanBaseEntity);
			if(count !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
			}
			//记一二级原因
			LoanExtEntity loanExtEntity = new LoanExtEntity();
			loanExtEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			loanExtEntity.setPrimaryReason(reqLoanContractSignVo.getFirstLevleReasons());
			loanExtEntity.setSecodeReason(reqLoanContractSignVo.getTwoLevleReasons());
			loanExtEntity.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
			loanExtEntity.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
			loanExtEntity.setModifier(reqLoanContractSignVo.getServiceCode());
			loanExtEntity.setModifiedTime(new Date());
			loanExtDao.update(loanExtEntity);
			//借款日志
			BMSSysLoanLog loanLog= new BMSSysLoanLog();
			loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
			loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_CONFIRM.getValue());
			loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
			loanLog.setOperationTime(new Date());
			loanLog.setOperator(reqLoanContractSignVo.getServiceName());
			loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
			loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog.setRtfState(EnumConstants.RtfState.HTQR.getValue());
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQRRETURN.getValue());
			loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
			loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
			loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
			loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
			loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
			loanLog.setRemark(reqLoanContractSignVo.getRemark());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认|退回|back","ILoanContractConfirmExecuter","backConfirm");			
		} catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getMessage() + e.getArguments()[0]);
		}
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		try {
			//完成当前任务
			TaskOpinion option =new TaskOpinion("退回");
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_TH,option);
		} catch (Exception e) {
			setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常"), res);
			return false;
		}
		return true;
	}

	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res){
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
