package com.ymkj.cms.biz.service.sign.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;

@Service
public class LoanContractConfirmService implements ILoanContractConfirmService{
	
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private LoanExtDao loanExtDao;
	@Autowired
	private IBMSTMReasonService ibmstmReasonService;
	

	
	@Override
	public PageBean<BMSLoanBaseEntity> undoneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo){
		// 构造请求参数
		PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
		Map<String, Object> paramMap= null;
		try {
			paramMap = BeanKit.bean2map(reqLoanContractSignVo);
			paramMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());
			paramMap.put("rtfState", EnumConstants.RtfState.HTQR.getValue());
			paramMap.put("taskName", EnumConstants.WF_HTQR);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		// 调用业务逻辑
		PageBean<BMSLoanBaseEntity> pageBean = loanBaseDao.undoneContractConfirmListPage(pageParam, paramMap);	
		return pageBean;
	}

	
	@Override
	public PageBean<BMSLoanBaseEntity> doneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
		// 构造请求参数
		PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
		Map<String, Object> paramMap= null;
		try {
			paramMap = BeanKit.bean2map(reqLoanContractSignVo);
			paramMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());
			paramMap.put("rtfState", EnumConstants.RtfState.HTQR.getValue());
			paramMap.put("taskName", EnumConstants.WF_HTQR);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		// 调用业务逻辑
		PageBean<BMSLoanBaseEntity> pageBean = loanBaseDao.doneContractConfirmListPage(pageParam, paramMap);
	
		return pageBean;
	}
	
	@Override
	public boolean confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean confirmFlag=false;
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),EnumConstants.RtfState.FKSH.getValue(),
											EnumConstants.RtfNodeState.HTQRSUBMIT.getValue(),reqLoanContractSignVo.getVersion());
		loanBaseEntity.setConfirmEndDate(DateUtil.defaultFormatDate(new Date()));//确认结束时间
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		//构造 LoanNo 和 借款状态useCode 和 state
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CONFIRM);
		reqLoanContractSignVo.setServiceCode(reqLoanContractSignVo.getServiceCode());
		//调用核心更新借款状态
		confirmFlag =updateCoreLoanState(reqLoanContractSignVo);

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
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.CONFIRM.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认|确认|confirm","ILoanContractConfirmExecuter","confirmLoanContract");
		return confirmFlag;
	}

	
	@Override
	public boolean backConfirm(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),EnumConstants.RtfNodeState.HTQRRETURN.getValue(),
																reqLoanContractSignVo.getVersion());
		loanBaseEntity.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		loanBaseEntity.setConfirmEndDate(DateUtil.defaultFormatDate(new Date()));//确认结束时间
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
			//调用接口更新合同
			reqLoanContractSignVo.setState(EnumConstants.CONTRACT_BACK);
			//构造 LoanNo 和 借款状态useCode 和 state
			backFlag =updateCoreLoanState(reqLoanContractSignVo);
			
			//记一二级原因
			LoanExtEntity loanExtEntity = new LoanExtEntity();
			loanExtEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			loanExtEntity.setPrimaryReason(reqLoanContractSignVo.getFirstLevleReasons());
			loanExtEntity.setSecodeReason(reqLoanContractSignVo.getTwoLevleReasons());
			loanExtEntity.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
			loanExtEntity.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
//			loanExtEntity.setModifier(reqLoanContractSignVo.getServiceName());
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
		return backFlag;	
	}
	

		//更新核心系统借款状态
		private boolean  updateCoreLoanState(ReqLoanContractSignVo reqLoanContractSignVo){
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
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");	
			}else{
				return true;
			}
		}

		@Override
		public long queryContractConfirmToDoCount(Map<String,Object> paramsMap) {
			return loanBaseDao.queryContractConfirmToDoCount(paramsMap);
		}
}