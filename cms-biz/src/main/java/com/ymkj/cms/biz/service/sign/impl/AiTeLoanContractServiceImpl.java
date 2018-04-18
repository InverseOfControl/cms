package com.ymkj.cms.biz.service.sign.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.FTPUtils;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.APPCarInfoDao;
import com.ymkj.cms.biz.dao.apply.APPEstateInfoDao;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.audit.ILoanAuditDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.IContractLoanDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.dao.sign.ILoanContractDao;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.finance.GrantInfo;
import com.ymkj.cms.biz.entity.finance.GrantLoanEntity;
import com.ymkj.cms.biz.entity.finance.HttpLoanReturnEntity;
import com.ymkj.cms.biz.entity.finance.ResLoanFailEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSContractLoan;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.ftp.IAiTeFtpService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class AiTeLoanContractServiceImpl implements IAiTeLoanContractService {

	@Autowired
	private ILoanContractDao loanContractDao;
	@Autowired
	private IContractLoanDao contractLoanDao;
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	@Autowired
	private APPCarInfoDao appCarInfoDao;
	@Autowired
	private APPEstateInfoDao appEstateInfoDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private IAiTeFtpService aiTeFtpService;
	@Autowired
	private FTPUtils ftpUtils;
	@Autowired
	private IBMSLoanBaseDao loanBaseDao;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private LoanExtDao loanExtDao;
	@Autowired
	private ILoanAuditDao loanAuditDao;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	private static final Logger logger = LoggerFactory.getLogger(AiTeLoanContractServiceImpl.class);

	
	@Override
	public boolean targetPushedToAiTe(ReqLoanContractSignVo reqLoanContractSignVo) {
		logger.info("标的推送部分  开始");
		// 请求参数集合
		Map<String, String> requestMap = null;
		// 签约信息查询
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		// 合约信息不存在
		if (loanInfo == null) {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, new Object[] { "id" });
		}
		// 非法请求,传入的合同编号不属于捞财宝合同来源
		if (!EnumConstants.channelCode.AITE.getValue().equals(loanInfo.getChannelCd())) {
			throw new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "非法请求,传入的合同编号不属于捞财宝合同来源");
		}
		reqLoanContractSignVo.setVersion(loanInfo.getVersion());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());

		// 审核基本信息
		LoanAuditEntity loanAuditEntity = loanAuditDao.getBy(param);
		// 合同签约
		BMSContractLoan contractLoan = contractLoanDao.getBy(param);
		// 签约合同
		BMSLoanContract loanContract = loanContractDao.getBy(param);
		// 申请人信息
		APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);
		// 车辆信息表
		APPCarInfoEntity tmAppCarInfo = appCarInfoDao.getBy(param);
		// 房产信息表
		APPEstateInfoEntity tmAppEstateInfo = appEstateInfoDao.getBy(param);
		// 数据封装
		requestMap = loanSignDataOprateService.convertToRequestMap(loanInfo, contractLoan, loanContract,
				tmAppPersonInfo, tmAppPersonInfo, tmAppCarInfo, tmAppEstateInfo, loanAuditEntity);

		HttpResponse httpResponse = aiTeHttpService.targetPushed(requestMap);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){//请求成功
			Map resMap = JsonUtils.decode(httpResponse.getContent(), Map.class);
			if(!EnumConstants.RES_CODE_SUCCESS.equals(resMap.get("repCode"))){
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, resMap.get("repMsg"));
			}
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认完成|标的推送|confirm","AiTeLoanContractServiceImpl//targetPushedToAiTe","合同确认（标的推送）");
			
			logger.info("标的推送部分  结束");
			return true;
		} else {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "推送标的，http请求失败");
		}
	}

	@Override
	public boolean cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean cancelFlag=false;
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQY.getValue(),EnumConstants.RtfNodeState.HTQYCANCEL.getValue(),reqLoanContractSignVo.getVersion());
		loanBaseEntity.setSignEndDate(DateUtil.defaultFormatDate(new Date()));
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		long count=  loanBaseEntityDao.update(loanBaseEntity);//签约结束时间
		if(count !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		//调用接口更新合同
		if(!EnumConstants.WF_KS.equals(reqLoanContractSignVo.getTaskName())
				&&!EnumConstants.WF_BCXX.equals(reqLoanContractSignVo.getTaskName())){//推送成功调用
			reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CANCEL);
			cancelFlag=updateCoreLoanState(reqLoanContractSignVo);
		}
		// 调用接口更新合同
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CANCEL);
		cancelFlag = updateCoreLoanState(reqLoanContractSignVo);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanContractSignVo.getServiceName());
		loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.HTQR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.SIGN.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setRemark(reqLoanContractSignVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|取消|cancel", "ILoanContractSignExecuter",
				"cancelLoan");
		return cancelFlag;

	}

	@Override
	public boolean refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean cancelFlag = false;
		// 更新借款系统借款状态
		BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQY.getValue(), EnumConstants.RtfNodeState.HTQYCANCEL.getValue(),
				reqLoanContractSignVo.getVersion());
		loanBaseEntity.setSignEndDate(DateUtil.defaultFormatDate(new Date()));
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		long count = loanBaseEntityDao.update(loanBaseEntity);// 签约结束时间
		if (count != 1) {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		// 调用接口更新合同
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CANCEL);
		cancelFlag = updateCoreLoanState(reqLoanContractSignVo);
		// 借款日志
		BMSSysLoanLog loanLog = new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setRtfState(EnumConstants.RtfState.HTQR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.SIGN.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setRemark(reqLoanContractSignVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|取消|cancel", "ILoanContractSignExecuter",
				"cancelLoan");
		return cancelFlag;
	}

	@Override
	public boolean confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean confirmFlag = false;
		// 更新借款系统借款状态
		BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.FKSH.getValue(), EnumConstants.RtfNodeState.FKSHSUBMIT.getValue(),
				reqLoanContractSignVo.getVersion());
		long count = loanBaseEntityDao.update(loanBaseEntity);
		if (count != 1) {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		// 构造 LoanNo 和 借款状态useCode 和 state
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CONFIRM);
		// 当前用户
		confirmFlag = updateCoreLoanState(reqLoanContractSignVo);

		// 借款日志
		/*
		 * reqLoanContractSignVo.setOperationModule(EnumConstants.OptionModule.
		 * CONTRACT_CONFIRM.getValue()); BMSSysLoanLog loanLog = new
		 * BMSSysLoanLog(reqLoanContractSignVo);
		 * ibmsSysLoanLogService.saveOrUpdate(loanLog);
		 */
		return confirmFlag;
	}

	// 更新核心系统借款状态
	private boolean updateCoreLoanState(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map<String, Object> loanDataMap = new HashMap<String, Object>();

		// 构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBaseEntity bmsLoanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getLoanNo(),
				reqLoanContractSignVo.getServiceCode(), reqLoanContractSignVo.getState());

		loanDataMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());
		loanDataMap.put("userCode", bmsLoanBaseEntity.getUserCode());
		loanDataMap.put("stateCode", bmsLoanBaseEntity.getState());
		// 调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);

		HttpContractReturnEntity contractReturnEntity = JsonUtils.decode(httpResponse.getContent(),
				HttpContractReturnEntity.class);
		if (!"000000".equals(contractReturnEntity.getCode())) {// 是否请求成功
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, contractReturnEntity.getMessage());
		} else {
			return true;
		}
	}

	@Override
	public boolean passAuditLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean passFlag = false;
		// 更新借款系统借款状态
		// 构造 loanBaseId 和 借款状态RtfNodeState
		BMSLoanBase loanBaseEntity = new BMSLoanBase(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.FKQR.getValue(), EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
		loanBaseEntity.setFinanceAuditTime(new Date());
		boolean updatFlag = updateBMSLoanState(loanBaseEntity);
		if (updatFlag) {
			// 调用接口更新合同
			reqLoanContractSignVo.setState(EnumConstants.FINANCE_AUDIT_PASS);
//			passFlag = true;
			passFlag = updateCoreLoanState(reqLoanContractSignVo);
		} else {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		if(passFlag){
			// 借款日志
			BMSSysLoanLog loanLog= new BMSSysLoanLog();
			loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
			loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_AUDIT.getValue());
			loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
			loanLog.setOperationTime(new Date());
			loanLog.setOperator(reqLoanContractSignVo.getServiceName());
			loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
			loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog.setRtfState(EnumConstants.RtfState.FKSH.getValue());
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
			loanLog.setOperationType(EnumConstants.OptionType.PASS.getValue());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			// 系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "财务管理|财务审核|满标通知|update",
					"AiTeLoanContractServiceImpl\\passAuditLoan", "满标通知-审核通过");
			
			try {
				//完成竞争任务
				TaskOpinion option =new TaskOpinion("满标通知");
				// 完成当前任务
				taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(), reqLoanContractSignVo.getServiceCode(), EnumConstants.WFPH_MBTZ,option);
			} catch (Exception e) {
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
			}
		}
		return passFlag;

	}

	// 更新借款系统借款状态
	private boolean updateBMSLoanState(BMSLoanBase loanBaseEntity) {
		long count = loanBaseDao.update(loanBaseEntity);
		if (count != 1) {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		return true;
	}

	@Override
	public boolean backConfirm(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),EnumConstants.RtfNodeState.HTQRRETURN.getValue(),
																reqLoanContractSignVo.getVersion());
		loanBaseEntity.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		loanBaseEntity.setConfirmEndDate(DateUtil.defaultFormatDate(new Date()));//确认结束时间
		long count=  loanBaseEntityDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
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
			loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
			loanLog.setRemark(reqLoanContractSignVo.getRemark());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认|退回|back","ILoanContractConfirmExecuter","backConfirm");
		return backFlag;
	}

	@Override
	public boolean backAudit(ReqLoanVo reqLoanVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		//构造 loanBaseId 和 借款状态RtfNodeState
		BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
				EnumConstants.RtfNodeState.FKSHRETURN.getValue());
		//loanBaseEntity.setStatus(EnumConstants.LoanStatus.getValue());
		boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
	
		if(updatFlag){
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_AUDIT_BACK);
			backFlag=updateCoreLoanState(reqLoanVo);
		}

		//记一二级原因
		String firstLevleReasonsCode =reqLoanVo.getFirstLevleReasonsCode();
		String twoLevleReasonsCode = reqLoanVo.getTwoLevleReasonsCode();
		
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanBaseId(reqLoanVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(firstLevleReasonsCode);
		loanExtEntity.setTwoLevleReasonsCode(twoLevleReasonsCode);
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
		loanLog.setFirstLevleReasonsCode(firstLevleReasonsCode);
		loanLog.setTwoLevleReasonsCode(twoLevleReasonsCode);
		loanLog.setRemark(reqLoanVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款审核|退回|back","ILoanExecuter","放款审核退回");
		return backFlag;
	}

	@Override
	public boolean creditInvestigationUploadFile(ReqLoanContractSignVo reqLoanContractSignVo) {
		logger.info("央行征信报告上传到爱特ftp服务器  开始");
		boolean notifyFlag = false;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());
		// 申请人信息
		APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);
		if (tmAppPersonInfo == null) {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, "借款主表ID对应的人员信息不存在");
		} else if (tmAppPersonInfo.getReportId() == null){
//			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, "该申请件绑定央行征信报告失败，没有对应的报告上传到爱特ftp服务器");
			//央行征信报告空，不进行上传
			return true;
		}
		// 央行征信报告获取
		String creditInvestigationStream = aiTeFtpService.getPeopleBankCredit(tmAppPersonInfo);
		if(creditInvestigationStream == null || "".equals(creditInvestigationStream)){
			logger.info("央行征信报告上传到爱特ftp服务器   央行征信报告获取空  结束");
			return true;
		}
		// 发送央行征信报告
		notifyFlag = aiTeFtpService.creditInvestigationUploadFile(creditInvestigationStream, tmAppPersonInfo);
		if (!notifyFlag) {
			throw new BizException(BizErrorCode.DB_RESULT_ERROR, "FTP上传错误");
		}
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认完成|央行征信报告上传到爱特ftp服务器|confirm","AiTeLoanContractServiceImpl//creditInvestigationUploadFile","合同确认（央行征信报告上传到爱特ftp服务器）");
		
		logger.info("央行征信报告上传到爱特ftp服务器  结束");
		return notifyFlag;
	}

	@Override
	public boolean creditReportUploadFile(ReqLoanContractSignVo reqLoanContractSignVo) {
		logger.info("上海资信报告上传到爱特ftp服务器 开始");
		boolean notifyFlag = false;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());
		// 申请人信息
		APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);

		String nfcsId = tmAppPersonInfo.getNfcsId()==null?"":tmAppPersonInfo.getNfcsId().toString();
		String returnHtml = "";
		if ("".equals(nfcsId)) {
			//上海资信报告  没有不进行上传
			logger.info("上海资信报告上传到爱特ftp服务器  没有不进行上传  结束");
			return true;
//			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, "该申请件绑定上海资信报告失败，没有对应的报告上传到爱特ftp服务器");
			// logger.info("该申请件appNo:"+appNo+",绑定上海征信报告失败，没有对应的报告上传到爱特ftp服务器");
		} else {

			Map<String, String> rquestMap = new HashMap<String, String>();

			rquestMap.put("id", nfcsId);
			rquestMap.put("sources", EnumConstants.BMS_SYSTEM_CODE);
			// 上海资信获取
			HttpResponse httpResponse = aiTeHttpService.getCreditStandingAndRespectabilitySH(rquestMap);
			if (httpResponse != null) {
				if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){ // 查询成功
					// 再次封装
					String content = httpResponse.getContent();
					Map contentMap = JsonUtils.decode(content, Map.class);
					if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("code"))){// 查询成功
						returnHtml = JsonUtils.encode(contentMap.get("responseJson"));
					} else {
						if(contentMap.get("message") != null){
							logger.info("上海资信报告上传到爱特ftp服务器  没有不进行上传  结束" + contentMap.get("message").toString());
						} else {
							logger.info("上海资信报告上传到爱特ftp服务器  没有不进行上传  结束");
						}
						return true;
//						throw new BizException(BizErrorCode.DB_RESULT_ISNULL, contentMap.get("message").toString());
//						returnHtml = contentMap.get("message").toString();
					}
				} else {
					return true;
//					throw new BizException(BizErrorCode.DB_RESULT_ISNULL, httpResponse.getMessage());
//					returnHtml = httpResponse.getMessage();

				}
			}

			// 发送上海资信报告
			notifyFlag = aiTeFtpService.creditReportUploadFile(returnHtml, tmAppPersonInfo);
			if(notifyFlag){
				ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认完成|上海资信报告上传到爱特ftp服务器|confirm","AiTeLoanContractServiceImpl//creditReportUploadFile","合同确认（上海资信报告上传到爱特ftp服务器）");

			}
				

		}
		logger.info("上海资信报告上传到爱特ftp服务器 结束");
		return notifyFlag;
	}

	@Override
	public boolean IDCardUploadFile(ReqLoanContractSignVo reqLoanContractSignVo) {
		logger.info("人身份证信息上传到爱特ftp服务器 开始");
		boolean notifyFlag = false;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());
		// 申请人信息
		APPPersonInfoEntity tmAppPersonInfo = appPersonInfoDao.findPersonByLoanNo(param);

		Map<String, String> rquestMap = new HashMap<String, String>();

		rquestMap.put("appNo", tmAppPersonInfo.getLoanNo());
		rquestMap.put("subclass_sort", EnumConstants.PicPaperstype.IDENTIFICATION_PAPER.getValue());
		rquestMap.put("operator", EnumConstants.AITE_USER_NAME);
		rquestMap.put("jobNumber", EnumConstants.AITE_USER_CODE);
		// 身份证照片获取获取
		HttpResponse httpResponse = aiTeHttpService.getIDCard(rquestMap);
		// 请求响应成功验证
		if (httpResponse != null && httpResponse.getContent() != null && EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()) {

			notifyFlag = aiTeFtpService.IDCardUploadFile(httpResponse.getContent(), tmAppPersonInfo);
			if(notifyFlag){
				ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认完成|人身份证信息上传到爱特ftp服务器|confirm","AiTeLoanContractServiceImpl//IDCardUploadFile","合同确认（人身份证信息上传到爱特ftp服务器）");

			}
		} else {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, "该申请件身份证明获取失败，没有对应的身份证明上传到爱特ftp服务器");

		}
		logger.info("人身份证信息上传到爱特ftp服务器 结束");
		return notifyFlag;
	}

	@Override
	public boolean terminationLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean notifyFlag = false;

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
		requestMap.put("reason", "取消");
		// 调终止借款接口
		HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
			String content = httpResponse.getContent();
			Map contentMap = JsonUtils.decode(content, Map.class);
			if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
				// 同意终止，进入取消，通知核心
				try {
					// 完成当前任务
					taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_TY);
					notifyFlag = true;
				} catch (Exception e) {
					throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
				}
			} else {
				taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_BTY);
				notifyFlag = false;
			}
		} else{
			notifyFlag = false;
		}
		
		return notifyFlag;
	}

	@Override
	public boolean cancelLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean cancelFlag = false;
		// 存在验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
		BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
		
		if (loanBaseEntity == null) {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, new Object[] { "loanNo" });
		}
		// 非法请求,传入的合同编号不属于捞财宝合同来源
		if (!"00016".equals(loanBaseEntity.getChannelCd())) {
			throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "非法请求,传入的合同编号不属于捞财宝合同来源");
		}
		String taskDefKey = loanBaseEntity.getRtfNodeState();
		// 财务已放款，不能执行此操作
		if (null == taskDefKey || "".equals(taskDefKey)) {
			throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "财务已放款，不能执行此操作");
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(task == null){
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		reqLoanContractSignVo.setTaskName(task.getTaskName());

		try {
			// 完成当前任务
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_JJ_QX_QYGP);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		reqLoanContractSignVo.setVersion(loanBaseEntity.getVersion());
		//流程校验
		task =	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		// 推送后会锁定标的，所以依据锁定字段判断是否，通知爱特
		if ("Y".equals(loanBaseEntity.getLockTarget()) && task != null && EnumConstants.WF_ZZJK.equals(task.getTaskName())) {// 锁定
			// 进入终止借款处理,含有流程
			cancelFlag = this.terminationLoanHasUflo(reqLoanContractSignVo);
//			cancelFlag = true;
			// 同意终止借款判断
			if (cancelFlag) {
				cancelFlag = loanContractSignService.cancelLoan(reqLoanContractSignVo);
//				this.cancelLoan(reqLoanContractSignVo);
				//标的解锁锁定
				boolean notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(BizErrorCode.DB_ERROR, "标的解锁失败");
				}
			}
		} else if ("Y".equals(loanBaseEntity.getLockTarget())) {// 退件箱
			// 进入终止借款处理
			boolean notifyFlag = false;

			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
			requestMap.put("reason", "取消");
			// 调终止借款接口
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 同意终止，进入取消，通知核心
					notifyFlag = true;
				} else {
					notifyFlag = false;
				}
			} else{
				notifyFlag = false;
			}
			// 同意终止借款判断
			if (notifyFlag) {
				cancelFlag = loanContractSignService.cancelLoan(reqLoanContractSignVo);
//				this.cancelLoan(reqLoanContractSignVo);
				//标的解锁锁定
				notifyFlag = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
				if (!notifyFlag) {
					throw new BizException(BizErrorCode.DB_ERROR, "标的解锁失败");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "捞财宝不同意终止借款");
			}
		} else {
			cancelFlag = loanContractSignService.cancelLoan(reqLoanContractSignVo);

//			this.cancelLoan(reqLoanContractSignVo);
		}

		return cancelFlag;
	}

	@Override
	public boolean refuseLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean refuseFlag = false;
		
		// 存在验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
		BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
		if (loanBaseEntity == null) {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, new Object[] { "loanNo" });
		}
		// 非法请求,传入的合同编号不属于捞财宝合同来源
		if (!"00016".equals(loanBaseEntity.getChannelCd())) {
			throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "非法请求,传入的合同编号不属于捞财宝合同来源");
		}
		String taskDefKey = loanBaseEntity.getRtfNodeState();
		// 财务已放款，不能执行此操作
		if (null == taskDefKey || "".equals(taskDefKey)) {
			throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "财务已放款，不能执行此操作");
		}
		//流程校验
		Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(task == null){
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		reqLoanContractSignVo.setTaskName(task.getTaskName());

		try {
			// 完成当前任务
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_JJ_QX_QYGP);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		reqLoanContractSignVo.setVersion(loanBaseEntity.getVersion());
		//流程校验
		task =	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		// 推送后会锁定标的，所以依据锁定字段判断是否，通知爱特
		if ("Y".equals(loanBaseEntity.getLockTarget()) && task != null && EnumConstants.WF_ZZJK.equals(task.getTaskName())) {// 锁定
			// 进入终止借款处理
			refuseFlag = this.terminationLoanHasUflo(reqLoanContractSignVo);
			// 同意终止借款判断
			if (refuseFlag) {
				 loanContractSignService.refuseLoan(reqLoanContractSignVo);
//				this.refuseLoan(reqLoanContractSignVo);
				//标的解锁锁定
				boolean notifyFalg = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
				if (!notifyFalg) {
					throw new BizException(BizErrorCode.DB_ERROR, "标的解锁失败");
				}
			}
		} else if ("Y".equals(loanBaseEntity.getLockTarget()) && task != null && EnumConstants.WF_TJX.equals(task.getTaskName())) {// 退件箱
			// 进入终止借款处理
			boolean notifyFlag = false;

			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
			requestMap.put("reason", "取消");
			// 调终止借款接口
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 同意终止，进入取消，通知核心
					notifyFlag = true;
				} else {
					notifyFlag = false;
				}
			} else{
				notifyFlag = false;
			}
			// 同意终止借款判断
			if (notifyFlag) {
				refuseFlag = loanContractSignService.refuseLoan(reqLoanContractSignVo);
//				this.cancelLoan(reqLoanContractSignVo);
				//标的解锁锁定
				notifyFlag = aiTeLoanContractService.unLockTarget(reqLoanContractSignVo);
				if (!notifyFlag) {
					throw new BizException(BizErrorCode.DB_ERROR, "标的解锁失败");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "捞财宝不同意终止借款");
			}
		} else {
			refuseFlag =  loanContractSignService.refuseLoan(reqLoanContractSignVo);
//			this.refuseLoan(reqLoanContractSignVo);
		}
		return refuseFlag;
	}

	@Override
	public boolean signLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean signFlag = false;
		String confirmUserCode = null;//合同确认办理CODE
		String confirmUserName = null;//合同确认办理NAME
		ResEmployeeVO	branchManager =null;  //经理
		ResEmployeeVO	branchAssistantManager =null; //副理
		BMSLoanBaseEntity  bmsLoanBaseEntity =loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(bmsLoanBaseEntity==null){
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL);
		}
		if(!StringUtils.isBlank(bmsLoanBaseEntity.getManagerCode())){//如果确认经理不为空，则为退回重新办理,确认经理为原经理
			confirmUserCode = bmsLoanBaseEntity.getManagerCode();
			confirmUserName = bmsLoanBaseEntity.getManagerName();
		}else{//如果确认经理为空，则不退回重新办理,从员工上级副理，经理取
			//根据签约客服得到副理
			branchAssistantManager =getSignManager(bmsLoanBaseEntity.getSignCode(),EnumConstants.BRANCH_ASSISTANT_MANAGER);
			//根据签约客服得到经理
			branchManager =getSignManager(bmsLoanBaseEntity.getSignCode(),EnumConstants.BRANCH_MANAGER);
			
			if(branchManager==null && branchAssistantManager==null){//未找到经理副理
				throw new BizException(BizErrorCode.MANAGER_NOTFOUND);
			}
			
			//如果副理为空 则为分配经理
			if(branchAssistantManager == null){
				confirmUserCode=branchManager.getUsercode();
				confirmUserName=branchManager.getName();
			}else{
				confirmUserCode=branchAssistantManager.getUsercode();
				confirmUserName=branchAssistantManager.getName();
			}
		}
		reqLoanContractSignVo.setManagerCode(confirmUserCode);
		reqLoanContractSignVo.setManagerName(confirmUserName);
		try {
			//合同签订处理
			signFlag = loanContractSignService.signLoanContract(reqLoanContractSignVo);
		
		 }  catch (BizException e) {
			throw new BizException(BizErrorCode.EOERR,e.getMessage()+e.getArguments()[0]);
		}
		//查询版本号 并传给前端
		reqLoanContractSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		reqLoanContractSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		//完成当前任务 并添加 合同确认环节 经理 ，副理流程变量
		Map<String,Object> variables = new HashMap<String,Object>();
		if(branchManager !=null){
			variables.put(EnumConstants.BRANCH_MANAGER,branchManager.getUsercode());
		}
		variables.put(EnumConstants.BRANCH_ASSISTANT_MANAGER,confirmUserCode);
		TaskOpinion option =new TaskOpinion("签订");
		try{
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WF_HTQD, variables, option);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,e.getMessage());
		}
		return signFlag;
	}

	@Override
	public boolean lockTarget(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		String lockTarget = "Y";
		if(lockTargetEntity != null){
			lockTargetEntity.setLockTarget(lockTarget);
			lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
			}
		} else {
			//锁定数据
			BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
			saveEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			saveEntity.setLoanNo(reqLoanContractSignVo.getLoanNo());
			saveEntity.setChannelCode(reqLoanContractSignVo.getChannelCd());
			saveEntity.setLockTarget(lockTarget);
			saveEntity.setCreator(reqLoanContractSignVo.getServiceCode());
			saveEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "插入错误");
			}
			
		}
		
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "渠道处理|标的锁定|标的锁定|confirm","AiTeLoanContractServiceImpl//lockTarget","标的锁定");
		return true;

	}

	@Override
	public boolean unLockTarget(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		String lockTarget = "N";
		if(lockTargetEntity != null){
			lockTargetEntity.setLockTarget(lockTarget);
			lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
			}
		} else {
			//锁定数据
			BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
			saveEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			saveEntity.setLoanNo(reqLoanContractSignVo.getLoanNo());
			saveEntity.setChannelCode(reqLoanContractSignVo.getChannelCd());
			saveEntity.setLockTarget(lockTarget);
			saveEntity.setCreator(reqLoanContractSignVo.getServiceCode());
			saveEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "插入错误");
			}
			
		}
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "渠道处理|标的解锁|标的解锁|confirm","AiTeLoanContractServiceImpl//unLockTarget","标的解锁");

		return true;
	}

	@Override
	public boolean backSign(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean backFlag = false;
		// 更新借款系统借款状态
		BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfNodeState.HTQRRETURN.getValue(), reqLoanContractSignVo.getVersion());
		// loanBaseEntity.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		loanBaseEntity.setConfirmEndDate(DateUtil.defaultFormatDate(new Date()));// 确认结束时间
		long count = loanBaseEntityDao.update(loanBaseEntity);
		if (count != 1) {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		// 调用接口更新合同
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_BACK);
		// 构造 LoanNo 和 借款状态useCode 和 state
		backFlag = updateCoreLoanState(reqLoanContractSignVo);
		Map<String, String> variables = new HashMap<String, String>();
		variables.put(EnumConstants.CONFIRM_OPRATE_TYPE, "退回");
		try {
			// 完成当前任务
			TaskOpinion option = new TaskOpinion("退回");
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), "退回", option);
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}

		// 借款日志
		BMSSysLoanLog loanLog = new BMSSysLoanLog();
		loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_CONFIRM.getValue());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setRtfState(EnumConstants.RtfState.HTQR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setRemark(reqLoanContractSignVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认|退回|back", "ILoanContractConfirmExecuter",
				"backConfirm");
		return backFlag;
	}

	@Override
	public Map<String, Object> grantLoan(ReqLoanVo reqLoanVo) {
		GrantLoanEntity grantLoanEntity= new GrantLoanEntity();	
		Map<String,Object> retMap  = new HashMap<String,Object>();
		List<GrantInfo> infos  = new ArrayList<GrantInfo>();
		List<ReqLoanVo> loanList = reqLoanVo.getLoanList();
		
		List<BMSLoanBase>  loanBaseEntitys = new ArrayList<BMSLoanBase>();
		
		//将请求的reLoanVo 转换成loanBaseEntity
		for (ReqLoanVo reLoanVo2 : loanList) {
			BMSLoanBase loanBaseEntity = new BMSLoanBase(reLoanVo2);
			loanBaseEntity.setContractBranch(reLoanVo2.getContractBranch());
			loanBaseEntity.setContractBranchId(reLoanVo2.getContractBranchId());
				loanBaseEntitys.add(loanBaseEntity);
				GrantInfo info = new GrantInfo();
				info.setLoanNo(reLoanVo2.getLoanNo());
				if(reLoanVo2.getBatchNum()==null){
					info.setBatchNum("");
				}else{
					info.setBatchNum(reLoanVo2.getBatchNum());
				}
				infos.add(info);
		}
			grantLoanEntity.setInfos(infos);
			grantLoanEntity.setUserCode(reqLoanVo.getServiceCode());

				//调用推送放款款接口
			HttpResponse 	httpResponse = coreHttpService.grantLoanURL(grantLoanEntity);
				HttpLoanReturnEntity httpLoanReturnEntity= JsonUtils.decode(httpResponse.getContent(),HttpLoanReturnEntity.class);
				//判断调用推送放款款接口是否成功
				if (httpLoanReturnEntity !=null &&!"000000".equals(httpLoanReturnEntity.getCode())){
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");
				}
				StringBuffer logSb =new StringBuffer();//日志
				StringBuffer msgSb =new StringBuffer();//前台展示
				List<ResLoanFailEntity> failList = httpLoanReturnEntity.getCreateFaiVOs();
				if(failList !=null && failList.size()>0){
					for (ResLoanFailEntity resLoanFailEntity : failList) {
						logSb.append(resLoanFailEntity.getLoanNo());
						logSb.append(":");
						logSb.append(resLoanFailEntity.getErrorMessage());
						msgSb.append(resLoanFailEntity.getLoanNo());
						msgSb.append(":");
						msgSb.append("放款失败");
					}
				}
				
				for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
					boolean sucessFlag =true;
						for (ResLoanFailEntity resLoanFailEntity : failList) {
							if(resLoanFailEntity.getLoanNo().equals(loanBaseEntity.getLoanNo())){//失败
								sucessFlag=false;
								break;
							}
						}
						if(sucessFlag){//成功
							msgSb.append(loanBaseEntity.getLoanNo());
							msgSb.append(":");
							msgSb.append("放款确认成功;");
							logSb.append(loanBaseEntity.getLoanNo());
							logSb.append(":");
							logSb.append("放款确认成功;");
							//更新借款状态
							loanBaseEntity.setStatus(EnumConstants.LoanStatus.NORMAL.getValue());
							loanBaseEntity.setRtfState(EnumConstants.RtfState.DHHK.getValue());
							loanBaseEntity.setLoanDate(new Date());
							loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
							long updatFlag=	loanBaseDao.update(loanBaseEntity);
							if(updatFlag !=1){
								throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
							}
							BMSSysLoanLog loanLog= new BMSSysLoanLog();
							// 借款日志
							loanLog.setLoanBaseId(loanBaseEntity.getId());
							loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
							loanLog.setLoanNo(loanBaseEntity.getLoanNo());
							loanLog.setOperationTime(new Date());
							loanLog.setOperator(reqLoanVo.getServiceName());
							loanLog.setOperatorCode(reqLoanVo.getServiceCode());
							loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
							loanLog.setRtfState(EnumConstants.RtfState.FKQR.getValue());
							loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
							loanLog.setOperationType(EnumConstants.OptionType.PASS.getValue());
							ibmsSysLoanLogService.saveOrUpdate(loanLog);
							if(updatFlag>0){
								try {
									// 完成当前任务
									TaskOpinion option =new TaskOpinion("通过");
									taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(),reqLoanVo.getServiceCode(),EnumConstants.WFPH_BDFKTZ,option);
								} catch (Exception e) {
									throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
								}
							}
						}
				}	
				retMap.put("msg", msgSb.toString());
				String grantTypeStr=loanList.size()>1?"批量通过":"通过";
				String grantType=loanList.size()>1?"batchPass":"pass";
				ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|"+grantTypeStr+"|"+grantType,"AiTeLoanContractServiceImpl|grantLoan",logSb.toString());
		return retMap;
	}

	@Override
	public Map<String,Object> queryRepaymentDetail(RequestVo requestVo) {
		//常规校验
		// 存在验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", requestVo.getBorrowNo());
		BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
		if (loanBaseEntity == null) {
			throw new BizException(BizErrorCode.DB_RESULT_ISNULL, new Object[] { "borrowNo" });
		}
		// 非法请求,传入的合同编号不属于捞财宝合同来源
		if (!EnumConstants.channelCode.AITE.getValue().equals(loanBaseEntity.getChannelCd())) {
			throw new BizException(BizErrorCode.NO_RULE_EXECUTE_ERROR, "非法请求,传入的合同编号不属于捞财宝合同来源");
		}
		
		Map<String,Object> returnMap = null; 
		try {
			List<BMSRepaymentDetail> detailVOList = new ArrayList<BMSRepaymentDetail>();
			 //传输数据map
			 Map<String, String> map = new HashMap<String, String>();
			 String currentUser = requestVo.getServiceCode();
			 String loanNo = loanBaseEntity.getLoanNo().toString();
			 map.put("userCode", currentUser);
			 map.put("loanNo", loanNo);
			 map.put("offset", "0");
			 map.put("max", "99999");
			 HttpResponse httpResponse = aiTeHttpService.queryRepaymentDetail(map);
			 if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){//请求成功
				Map<String, Object> resMap = JsonUtils.decode(httpResponse.getContent(), Map.class);
				if(!EnumConstants.RES_CODE_SUCCESS.equals(resMap.get("code"))){
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, resMap.get("message"));
				}
				returnMap = resMap;
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "推送标的，http请求失败,请联系开发");
			}
			 return returnMap;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用失败,请联系开发!");
		}
	}

	@Override
	public boolean contractConfirmationToAiTe(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean notifyFlag = false;

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
		// 调终止借款接口
		HttpResponse httpResponse = aiTeHttpService.contractConfirmationToAiTe(requestMap);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
			String content = httpResponse.getContent();
			Map contentMap = JsonUtils.decode(content, Map.class);
			if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
				notifyFlag = true;
				// 通知成功
				ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同确认|合同确认完成|合同确认通知捞财宝|confirm","AiTeLoanContractServiceImpl//contractConfirmationToAiTe","合同确认（合同确认通知捞财宝）");

			} else if(EnumConstants.RES_CODE_FAILED.equals(contentMap.get("repCode"))){
				notifyFlag = false;
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "合同确认接口响应失败，请联系开发！");
			}
		} else{
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "合同确认，http请求失败，请联系开发");

		}
		
		return notifyFlag;
	}
	/**
	 * 得到经理副理
	 * @param serviceCode
	 * @param managerType
	 * @return
	 */
	private ResEmployeeVO 	getSignManager(String serviceCode,String managerType){
		ResEmployeeVO branchAssManager=null;
		ReqEmployeeVO	reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setSysCode("bms");
		reqEmployeeVO.setUsercode(serviceCode);
		Response<List<ResOrganizationVO>> res=	iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
		List<ResOrganizationVO> resOrganizationVOs=	res.getData();
		if(resOrganizationVOs==null){
			throw new BizException(BizErrorCode.ORG_NOTFOUND,"未找到该客服所在营业部");
		}
		ResOrganizationVO	org	=resOrganizationVOs.get(0);
		Long orgId=org.getId();
		ReqParamVO	reqParamVO2=new ReqParamVO();
		reqParamVO2.setSysCode("bms");
		reqParamVO2.setOrgId(orgId);
		reqParamVO2.setStatus(0);// 可用
		reqParamVO2.setInActive("t");
		List <String> roleCodes =new ArrayList<String>();
		roleCodes.add(managerType);
		reqParamVO2.setRoleCodes(roleCodes);
		Response<List<ResEmployeeVO>> res2=iEmployeeExecuter.findByDeptAndRole(reqParamVO2);
		 List<ResEmployeeVO> list=	res2.getData();
		if(list != null && list.size() > 0){
			branchAssManager = list.get(0);
		}
		return branchAssManager;
	}

	@Override
	public boolean backLoan(ReqLoanVo reqLoanVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
				EnumConstants.RtfNodeState.FKQRRETURN.getValue());
		boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
		if(updatFlag){
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_LOAN_BACK);
			backFlag=updateCoreLoanState(reqLoanVo);
		}else{
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}	
		//记一二级原因 后面修改为前台传
		String firstLevleReasonsCode =reqLoanVo.getFirstLevleReasonsCode();
		String twoLevleReasonsCode = reqLoanVo.getTwoLevleReasonsCode();
		
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanBaseId(reqLoanVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(firstLevleReasonsCode);
		loanExtEntity.setTwoLevleReasonsCode(twoLevleReasonsCode);
		loanExtEntity.setModifier(reqLoanVo.getServiceCode());
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setLoanBaseId(reqLoanVo.getId());
		loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
		loanLog.setLoanNo(reqLoanVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanVo.getServiceName());
		loanLog.setOperatorCode(reqLoanVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKQRRETURN.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		loanLog.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(firstLevleReasonsCode);
		loanLog.setTwoLevleReasonsCode(twoLevleReasonsCode);
		loanLog.setRemark(reqLoanVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|退回|back","ILoanExecuter|backLoan","放款确认退回");

		return backFlag;
	}
	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanVo reqLoanVo){
		Map<String,Object> loanDataMap  = new HashMap<String,Object>();
		//构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBase  loanBaseEntity  = new BMSLoanBase(reqLoanVo.getLoanNo(),
				reqLoanVo.getServiceCode(),reqLoanVo.getState());
		
		loanDataMap.put("loanNo", loanBaseEntity.getLoanNo());
		loanDataMap.put("userCode",loanBaseEntity.getUserCode());
		loanDataMap.put("stateCode",loanBaseEntity.getState());
		//调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);
		
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, contractReturnEntity.getMessage());	
		}else{
			return true;
		}
	}

	@Override
	public boolean discardLockTarget(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		String lockTarget = "D";
		if(lockTargetEntity != null){
			lockTargetEntity.setLockTarget(lockTarget);
			lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面");
			}
		} else {
			//锁定数据
			BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
			saveEntity.setLoanBaseId(reqLoanContractSignVo.getId());
			saveEntity.setLoanNo(reqLoanContractSignVo.getLoanNo());
			saveEntity.setChannelCode(reqLoanContractSignVo.getChannelCd());
			saveEntity.setLockTarget(lockTarget);
			saveEntity.setCreator(reqLoanContractSignVo.getServiceCode());
			saveEntity.setModified(reqLoanContractSignVo.getServiceCode());
			long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "插入错误");
			}
		}
		
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "渠道处理|标的锁定清除|标的锁定清除|confirm","AiTeLoanContractServiceImpl//lockTarget","标的锁定");
		return true;
	}
}
