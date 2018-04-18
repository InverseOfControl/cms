package com.ymkj.cms.biz.service.finance.base;

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
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.loan.BaseLoan;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;

/**
 * 放款审核  退回
 * 
 * @author YM10138
 *
 */
@Service
public class BackAudit2Impl extends BaseLoan<ReqLoanVo,ResLoanVo> {

	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private LoanExtDao loanExtDao;
	@Autowired
	private IBMSTMReasonService ibmstmReasonService;
	@Autowired
	private IBMSLoanBaseDao loanBaseDao;
	@Autowired
	private ILoanBaseEntityDao  loanBaseEntityDao;

	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		// 参数校验	
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if(reqLoanVo.getLoanNo()==null){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
			return false;
		}
		if(reqLoanVo.getId()==null){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
			return false;
		}
		if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasons())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"}), res);
			return false;
		}
		
		if(StringUtils.isBlank(reqLoanVo.getFirstLevleReasonsCode())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"}), res);
			return false;
		}
		
		if(StringUtils.isBlank(reqLoanVo.getChannelCd())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
			return false;
		}
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo.getId()));
		if(task == null || !EnumConstants.WF_FKSH.equals(task.getTaskName())){
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		boolean executeFlag =true;
		try {
			//更新借款系统借款状态
			//构造 loanBaseId 和 借款状态RtfNodeState
			BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
					EnumConstants.RtfNodeState.FKSHRETURN.getValue());
			loanBaseEntity.setFinanceAuditTime(new Date());
			
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_AUDIT_BACK);
			boolean flag = updateCoreLoanState(reqLoanVo, res);
			if(!flag){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "退回失败，请联系开发");
			}
			
			boolean updatFlag =	updateBMSLoanState(loanBaseEntity, res);
			if(!updatFlag){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "退回失败，请联系开发");
			}	

			//记一二级原因
			LoanExtEntity loanExtEntity = new LoanExtEntity();
			loanExtEntity.setLoanBaseId(reqLoanVo.getId());
			loanExtEntity.setPrimaryReason(reqLoanVo.getFirstLevleReasons());
			loanExtEntity.setSecodeReason(reqLoanVo.getTwoLevleReasons());
			loanExtEntity.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
			loanExtEntity.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
			loanExtEntity.setModifier(reqLoanVo.getServiceName());
			loanExtEntity.setModifier(reqLoanVo.getServiceCode());
			loanExtEntity.setModifiedTime(new Date());
			loanExtDao.update(loanExtEntity);
			//借款日志
			BMSSysLoanLog loanLog= new BMSSysLoanLog();
			loanLog.setLoanBaseId(reqLoanVo.getId());
			loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_AUDIT.getValue());
			loanLog.setLoanNo(reqLoanVo.getLoanNo());
			loanLog.setOperationTime(new Date());
			loanLog.setOperator(reqLoanVo.getServiceName());
			loanLog.setOperatorCode(reqLoanVo.getServiceCode());
			loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog.setRtfState(EnumConstants.RtfState.FKSH.getValue());
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKSHRETURN.getValue());
			loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
			loanLog.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
			loanLog.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
			loanLog.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
			loanLog.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
			loanLog.setRemark(reqLoanVo.getRemark());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款审核|退回|back","ILoanExecuter","放款审核退回");					
		} catch (BizException e) {
			executeFlag=false;
			setError(e,  res);
		}catch (Exception e) {
			executeFlag=false;
			setError(new BizException(CoreErrorCode.UNKNOWN_ERROR, e.getLocalizedMessage()),  res);
		}
		return executeFlag;
	}

	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		//完成任务
		TaskOpinion option =new TaskOpinion("退回");
		taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_TH,option);
		return true;
	}

	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanVo reqLoanVo, Response<ResLoanVo> res){
		Map<String,Object> loanDataMap  = new HashMap<String,Object>();
		
		//构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBaseEntity bmsLoanBaseEntity = new BMSLoanBaseEntity(reqLoanVo.getLoanNo(),
				reqLoanVo.getServiceCode(),reqLoanVo.getState());
		
		loanDataMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());
		loanDataMap.put("userCode",bmsLoanBaseEntity.getUserCode());
		loanDataMap.put("stateCode",bmsLoanBaseEntity.getState());
		//调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);
		
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息"), res);
			return false;
		}else{
			return true;
		}
		
	}
	//更新借款系统借款状态
	private boolean  updateBMSLoanState(BMSLoanBase loanBaseEntity, Response<ResLoanVo> res){
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			setError(new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败"), res);
			return false;
		}
		return true;
	}
}
