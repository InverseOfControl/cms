package com.ymkj.cms.biz.service.sign.base;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 放款确认  退回
 * 
 * @author YM10138
 *
 */
@Service
public class BackLoanImpl extends BaseSign<ResLoanContractSignVo> {

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

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if(reqLoanContractSignVo.getId() == null){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
			return false;
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
			return false;
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasons())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"}), res);
			return false;
		}
		
		if(StringUtils.isBlank(reqLoanContractSignVo.getFirstLevleReasonsCode())){
			setError( new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"}), res);
			return false;
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
			return false;
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(task == null || (!EnumConstants.WF_FKQR.equals(task.getTaskName()) && !EnumConstants.WF_FKQR_HX.equals(task.getTaskName()))){
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		try {
			//调用
			//更新借款系统借款状态
			BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanContractSignVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
					EnumConstants.RtfNodeState.FKQRRETURN.getValue());
			boolean updatFlag=	updateBMSLoanState(loanBaseEntity, res);
			if(updatFlag){
				//调用接口更新合同
				reqLoanContractSignVo.setState(EnumConstants.FINANCE_LOAN_BACK);
				boolean flag = updateCoreLoanState(reqLoanContractSignVo, res);
				if(!flag){
					return false;
				}
			}else{
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
			}	
			//记一二级原因 后面修改为前台传
			Map paramMap =new HashMap();
			paramMap.put("id",reqLoanContractSignVo.getFirstLevleReasonsCode());
			List<BMSTMReasonEntity> list = ibmstmReasonService.listBy(paramMap);
			String firstLevleReasonsCode =list.get(0).getCode();
			String twoLevleReasonsCode ="";
			if(!StringUtils.isBlank(reqLoanContractSignVo.getTwoLevleReasonsCode())){
				paramMap.put("id",reqLoanContractSignVo.getTwoLevleReasonsCode());
				List<BMSTMReasonEntity> by = ibmstmReasonService.listBy(paramMap);
				twoLevleReasonsCode =by.get(0).getCode();
			}
			LoanExtEntity loanExtEntity = new LoanExtEntity();
			loanExtEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			loanExtEntity.setPrimaryReason(reqLoanContractSignVo.getFirstLevleReasons());
			loanExtEntity.setSecodeReason(reqLoanContractSignVo.getTwoLevleReasons());
			loanExtEntity.setFirstLevleReasonsCode(firstLevleReasonsCode);
			loanExtEntity.setTwoLevleReasonsCode(twoLevleReasonsCode);
//			loanExtEntity.setModifier(reqLoanVo.getServiceName());
			loanExtEntity.setModifier(reqLoanContractSignVo.getServiceCode());
			loanExtEntity.setModifiedTime(new Date());
			loanExtDao.update(loanExtEntity);
			//借款日志
			BMSSysLoanLog loanLog= new BMSSysLoanLog();
			loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
			loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
			loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
			loanLog.setOperationTime(new Date());
			loanLog.setOperator(reqLoanContractSignVo.getServiceName());
			loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
			loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog.setRtfState(EnumConstants.RtfState.FKQR.getValue());
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKQRRETURN.getValue());
			loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
			loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
			loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
			loanLog.setFirstLevleReasonsCode(firstLevleReasonsCode);
			loanLog.setTwoLevleReasonsCode(twoLevleReasonsCode);
			loanLog.setRemark(reqLoanContractSignVo.getRemark());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "财务管理|放款确认|退回|back","ILoanExecuter|backLoan","放款确认退回");
		
			
		}  catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
		} 
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//完成当前任务
		TaskOpinion option =new TaskOpinion("退回");
		taskService.compUsersTaskByLoanBaseId(reqLoanContractSignVo.getId(),reqLoanContractSignVo.getServiceCode(),EnumConstants.WFPH_TH,option);
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
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息"), res);
			return false;
		}else{
			return true;
		}
		
	}
	//更新借款系统借款状态
	private boolean  updateBMSLoanState(BMSLoanBase loanBaseEntity, Response<ResLoanContractSignVo> res){
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
		}
		return true;
	}
}
