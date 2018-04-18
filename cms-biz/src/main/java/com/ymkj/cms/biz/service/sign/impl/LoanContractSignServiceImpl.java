package com.ymkj.cms.biz.service.sign.impl;
import com.bstek.uflo.service.StartProcessInfo;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.*;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.*;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.http.IPictureService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.IProcessService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanContractSignServiceImpl  implements ILoanContractSignService{
	@Autowired
	private ILoanContractSignDao loanContractSignDao;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private ILoanContractDao loanContractDao;
	@Autowired
	private IContractLoanDao contractLoanDao;
	@Autowired
	private IRepaymentDetailDao repaymentDetailDao;
	@Autowired
	private IProcessService processService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private LoanContractGenerator loanContractGenerator;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private LoanExtDao loanExtDao;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private BMSQualityInspectionService bmsQualityInspectionService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Value(value = "#{env['needDelPicTypeCode']?:''}")
	private String needDelPicTypeCode;   
	@Autowired
	private  IPictureService pictureService;
	
	
	@Override
	public PageBean<BMSLoanBaseEntity> undoneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
					// 构造请求参数
					PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
					Map<String, Object> paramMap= null;
					try {
						paramMap = BeanKit.bean2map(reqLoanContractSignVo);
						paramMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYASSIGN.getValue());
						paramMap.put("rtfState", EnumConstants.RtfState.HTQY.getValue());
					} catch (Exception e) {
						throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
					}
					// 调用业务逻辑
					PageBean<BMSLoanBaseEntity> pageBean = loanBaseEntityDao.listPage(pageParam, paramMap);		
		return pageBean;
	}
	
	@Override
	public long queryContractSignToDoCount(Map<String, Object> paramsMap) {
		return loanBaseEntityDao.queryContractSignToDoCount(paramsMap);
	}

	@Override
	public PageBean<BMSLoanBaseEntity> doneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
		
		// 构造请求参数
		PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
		Map<String, Object> paramMap= null;
		try {
			paramMap = BeanKit.bean2map(reqLoanContractSignVo);
			paramMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYASSIGN.getValue());
			paramMap.put("rtfState", EnumConstants.RtfState.HTQY.getValue());
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		// 调用业务逻辑
		PageBean<BMSLoanBaseEntity> pageBean = loanBaseEntityDao.donelistPage(pageParam, paramMap);
		return pageBean;
	}
	
	@Override
	public Map<String,Object> saveLoanContractSign(ReqLoanContractSignVo reqLoanContractSignVo) {
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		long count;
		BMSLoanProduct bmsLoanProduct =new BMSLoanProduct(reqLoanContractSignVo);
		bmsLoanProduct.setVersion(reqLoanContractSignVo.getPversion());
		Long loanBaseId= reqLoanContractSignVo.getId();
		Map<String,Object> param =new HashMap<String,Object>();
		param.put("loanBaseId",loanBaseId);
		// 签约信息查询 历史数据
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		//流程的渠道
		String channelCode =processService.getChannelCdById(String.valueOf(loanBaseId));
		//渠道分流
		if(EnumConstants.channelCode.AITE.getValue().equals(reqLoanContractSignVo.getChannelCd())){//当前保存渠道为爱特
			//推送数据判断  标识已签过捞财宝渠道
			if("N".equals(loanInfo.getLockTarget())){
				throw new BizException(BizErrorCode.UFLOTASK_EOERR,"该渠道流程节点，数据不可保存，请修改渠道");
			}
		}
		
		//在标的推送成功后，在第一个保存页面，修改渠道名称，点击“保存”
		//调用捞财宝【终止借款】接口，且不可再签约捞财宝
		
		boolean notifyFalg = false;
		// 推送判断,推送后标的锁定   渠道变更
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", EnumConstants.channelCode.AITE.getValue());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget()) && !reqLoanContractSignVo.getChannelCd().equals(loanInfo.getChannelCd())) {
			// 调用终止借款接口，待联调
			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
			requestMap.put("reason", "渠道变更");
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 处理成功 // 同意终止，通知核心
					//标的解锁锁定
					ReqLoanContractSignVo lockTargetSignVo = new ReqLoanContractSignVo();
					BeanUtils.copyProperties(reqLoanContractSignVo, lockTargetSignVo);
					lockTargetSignVo.setChannelCd(EnumConstants.channelCode.AITE.getValue());
					notifyFalg = aiTeLoanContractService.unLockTarget(lockTargetSignVo);
					if (!notifyFalg) {
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
					}
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法取消，请联系爱特");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
			}
		}
		
		//保存借款签约信息
		count = loanContractSignDao.update(bmsLoanProduct);
		if(count != 1){
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}else{	
			//推送过的借款为保证与核心数据同步需推送核心同步数据
			if(!StringUtils.isBlank(loanInfo.getApplyBankNameId())){
				reqLoanContractSignVo.setAccLmt(loanInfo.getContractLmt());
				reqLoanContractSignVo.setAccTerm(loanInfo.getContractTrem());
				reqLoanContractSignVo.setApplyBankNameId(Integer.valueOf(loanInfo.getApplyBankNameId()));
				reqLoanContractSignVo.setApplyBankBranch(loanInfo.getApplyBankBranch());
				reqLoanContractSignVo.setBankPhone(loanInfo.getBankPhone());
				reqLoanContractSignVo.setApplyBankCardNo(loanInfo.getApplyBankCardNo());
				reqLoanContractSignVo.setApplyBankName(loanInfo.getApplyBankName());
				//调用核心接口
				pushCore(reqLoanContractSignVo);
			}
			StartProcessInfo startProcessInfo = new StartProcessInfo();
			startProcessInfo.setBusinessId(String.valueOf(loanBaseId));
			startProcessInfo.setCompleteStartTask(true);
			startProcessInfo.setPromoter(reqLoanContractSignVo.getServiceCode());//流程发起人
			//当重新选择渠道时
			if(!StringUtils.isEmpty(channelCode)&& !channelCode.equals(reqLoanContractSignVo.getChannelCd())){
				//删除流程实例和任务
				processService.deleteProcessInstanceByLoanBaseId(String.valueOf(loanBaseId));
				//开启新的流程实例
				processService.startProcessByName(reqLoanContractSignVo.getChannelCd(), startProcessInfo);
			}else if(StringUtils.isEmpty(channelCode)){
				//开启工作流程
				processService.startProcessByName(reqLoanContractSignVo.getChannelCd(), startProcessInfo);
			}else{
				taskService.completeTaskByLoanBaseId(loanBaseId,"保存");
			}
		}
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存1|save","ILoanContractSignExecuter","saveLoanContractSign");
		return retMap;
	}

	@Override
	public Map<String,Object> saveLoanContractBankAcc(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		long count;
		//保存借款银行账号信息组装参数
		BMSLoanProduct bmsLoanProduct =new BMSLoanProduct(reqLoanContractSignVo);
		bmsLoanProduct.setVersion(reqLoanContractSignVo.getPversion());
		//更新银行表
	/*	BMSLoanBank loanBank =null;
		loanBank=loanSignDataOprateService.handleBanks(reqLoanContractSignVo);
		loanBankService.saveOrUpdate(loanBank);*/
			//保存借款银行信息
			count =loanContractSignDao.update(bmsLoanProduct);
			if(count != 1){
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}else{
				//调用推送核心接口
				pushCore(reqLoanContractSignVo);
			}
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存2|save","ILoanContractSignExecuter","保存银行账号信息推送债权");
		return retMap;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String,Object> createLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		Map paramMap = new HashMap();
		Map<String, Object>  retMap=new HashMap<String, Object>();
		String loanNo =reqLoanContractSignVo.getLoanNo();
		Long id =reqLoanContractSignVo.getId();
		paramMap.put("loanNo",loanNo);
		String org = "0000000"; //OrganizationContextHolder.getCurrentOrg(); // 机构号
		String contractNum=""; 
		HttpResponse httpResponse =  coreHttpService.createBMSLoan(paramMap);
		
			String retStr = httpResponse.getContent();
			HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(retStr, HttpContractReturnEntity.class);
			
			if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");	
			}
				BMSLoanContract bmsLoanContract  = contractReturnEntity.getContract();//合同信息
				BMSContractLoan  bmsContractLoan = contractReturnEntity.getLoan();//合同借款信息
				List<BMSRepaymentDetail> repaylist = contractReturnEntity.getRepaymentDetail();//还款计划信息
				Long loanId= null;
				//保存合同信息	
				if(bmsLoanContract != null){
					if(loanContractDao.selectConutByLoanBaseId(id)>0){
						loanContractDao.deleteByLoanBaseId(id);
					}
					bmsLoanContract.setLoanBaseId(id);
					loanContractDao.insert(bmsLoanContract);
					loanId = bmsLoanContract.getLoanId();
					contractNum = bmsLoanContract.getContractNum();
				}
				
				//保存借款银行账号信息组装参数
				BMSLoanProduct bmsLoanProduct =new BMSLoanProduct();
				bmsLoanProduct.setContractNum(contractNum);
				bmsLoanProduct.setLoanBaseId(reqLoanContractSignVo.getId());
				long upProCount=loanContractSignDao.update(bmsLoanProduct);
				if(upProCount !=1){
					throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新合同编号失败，请检查参数");
				}
				//保存债权ID
				BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity();
				loanBaseEntity.setLoanId(loanId);
				loanBaseEntity.setId(id);
				loanBaseEntity.setVersion(reqLoanContractSignVo.getVersion());
				long upCount= loanBaseEntityDao.update(loanBaseEntity);
				if(upCount !=1){
					throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新债权ID失败，请检查参数");
				}
				//保存合同借款信息	
				if(bmsContractLoan != null){
					if(contractLoanDao.selectConutByLoanBaseId(id)>0){
						contractLoanDao.deleteByLoanBaseId(id);
					}
					bmsContractLoan.setLoanId(loanId);
					bmsContractLoan.setLoanNo(loanNo);
					bmsContractLoan.setLoanBaseId(id);
					contractLoanDao.insert(contractReturnEntity.getLoan());
				}
				//保存还款计划	
				if(repaylist != null && repaylist.size()>0){
					if(repaymentDetailDao.selectConutByLoanBaseId(id)>0){
						repaymentDetailDao.deleteByLoanBaseId(id);
					}
					for (BMSRepaymentDetail bmsRepaymentDetail : repaylist) {
						bmsRepaymentDetail.setLoanBaseId(id);
					}
					repaymentDetailDao.batchInsert(repaylist);
				}
				retMap.put("contractReturnEntity", contractReturnEntity);
				//系统日志
				ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|生成合同|create","ILoanContractSignExecuter","createLoanContract");
				retMap.put("isSuceess",true);
				return retMap;
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
	public boolean signLoanContract(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean signFlag=false;
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
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		//调用核心接口更新合同
		reqLoanContractSignVo.setState(EnumConstants.CONTRACT_SIGN);
		signFlag=updateCoreLoanState(reqLoanContractSignVo);
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
		return signFlag;

	}
	

	/**
	 * 上一步
	 * 
	 */
	@Override
	public boolean returnLastStep(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean returnFlag=false;
		try {
			//完成当前任务
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),"上一步");
		} catch (Exception e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
		}
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|上一步|return","ILoanContractSignExecuter","returnLastStep");
		return returnFlag;
	}

	@Override
	public boolean cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean cancelFlag=false;
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQY.getValue(),EnumConstants.RtfNodeState.HTQYCANCEL.getValue(),reqLoanContractSignVo.getVersion());
		loanBaseEntity.setSignEndDate(DateUtil.defaultFormatDate(new Date()));
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		//签约超时取消判断
		if(EnumConstants.cancelFirstLevleReasonCode.超签约时效自动取消.getValue().equals(reqLoanContractSignVo.getFirstLevleReasons())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.QYCSCANCEL.getValue());
		} else {
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.HTQYCANCEL.getValue());
		}
		long count=  loanBaseEntityDao.update(loanBaseEntity);//签约结束时间
		if(count !=1){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		//调用接口更新合同
		if(reqLoanContractSignVo.getApplyBankNameId() != null){//推送成功调用
			reqLoanContractSignVo.setState(EnumConstants.CONTRACT_CANCEL);
			cancelFlag=updateCoreLoanState(reqLoanContractSignVo);
		} else {
			cancelFlag = true;
		}
		//记一二级原因
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanBaseId(reqLoanContractSignVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanContractSignVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanContractSignVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanExtEntity.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
//		loanExtEntity.setModifier(reqLoanContractSignVo.getServiceName());
		loanExtEntity.setModifier(reqLoanContractSignVo.getServiceCode());
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanContractSignVo.getServiceName());
		loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		//签约超时取消判断
		if(EnumConstants.cancelFirstLevleReasonCode.超签约时效自动取消.getValue().equals(reqLoanContractSignVo.getFirstLevleReasons())){
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.QYCSCANCEL.getValue());
		} else {
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQYCANCEL.getValue());
		}
		
		loanLog.setOperationType(EnumConstants.OptionType.CANCEL.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
		loanLog.setRemark(reqLoanContractSignVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|取消|cancel","ILoanContractSignExecuter","cancelLoan");
		return cancelFlag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo) {	
		boolean refuseFlag=false;	
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQY.getValue(),EnumConstants.RtfNodeState.HTQYREJECT.getValue(),reqLoanContractSignVo.getVersion());
		loanBaseEntity.setSignEndDate(DateUtil.defaultFormatDate(new Date()));//签约结束时间
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());//借款状态
		long count=  loanBaseEntityDao.update(loanBaseEntity);
		if(count !=1){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		//调用接口更新合同
		if(reqLoanContractSignVo.getApplyBankNameId() != null){//推送成功调用
			reqLoanContractSignVo.setState(EnumConstants.CONTRACT_REFUSE);
			refuseFlag=updateCoreLoanState(reqLoanContractSignVo);
		} else {
			refuseFlag = true;
		}
		//记一二级原因(录单传参为原因id 转为Code)
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanExtEntity.setLoanBaseId(reqLoanContractSignVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanContractSignVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanContractSignVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanExtEntity.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
		//loanExtEntity.setModifier(reqLoanContractSignVo.getServiceName());
		loanExtEntity.setModifier(reqLoanContractSignVo.getServiceCode());
		loanExtEntity.setModifiedTime(new Date());
		//原因是否添加黑名单
		BlackGreyVO blackGreyVO = new BlackGreyVO(loanExtEntity);
		blackGreyVO.setOperatorCode(reqLoanContractSignVo.getServiceCode());
		blackGreyVO.setType("1");
		ResReassignmentUpdVO ifSaveBlackGrey = bmsQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
		if(ifSaveBlackGrey.isIfSuccessful()){
			loanExtEntity.setBlackListId(ifSaveBlackGrey.getMsg());
		}
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setOperationModule(EnumConstants.OptionModule.CONTRACT_SIGN.getValue());
		loanLog.setLoanBaseId(reqLoanContractSignVo.getId());
		loanLog.setLoanNo(reqLoanContractSignVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanContractSignVo.getServiceName());
		loanLog.setOperatorCode(reqLoanContractSignVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.HTQY.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.HTQYREJECT.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		loanLog.setFirstLevleReasons(reqLoanContractSignVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanContractSignVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanContractSignVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanContractSignVo.getTwoLevleReasonsCode());
		loanLog.setRemark(reqLoanContractSignVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|拒绝|refuse","ILoanContractSignExecuter","refuseLoan");
		return refuseFlag;
	}
	

	


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BMSLoanBaseEntity findSignInfo(ReqLoanContractSignVo reqLoanContractSignVo){
		Map paramMap =new HashMap();	
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityDao.findSignInfo(paramMap);	
		return bmsLoanBaseEntity;	
	}
	


	@Override
	public List<BMSContractTemplate> getContractUrlList(ReqLoanContractSignVo reqLoanContractSignVo) {
		//根据借款编号查询该借款为电子或纸质合同
		Map paramMap =new HashMap();	
		paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityDao.findSignInfo(paramMap);
		List<BMSContractTemplate> tempList=	loanContractGenerator.getContractUrlList(reqLoanContractSignVo.getChannelCd(), reqLoanContractSignVo.getLoanNo(),bmsLoanBaseEntity.getContractType());
		return tempList;
	}



	@Override
	public boolean returnBoxChoiceTask(ReqLoanContractSignVo reqLoanContractSignVo) {
		boolean submitFlag=false;
		String taskPath=null;
		//流转到指定签约流程环节
		Map<String,String> varMap=new HashMap<String,String>();
		varMap.put("isReturnSubmit", EnumConstants.YES);
		//完成环节节点任务
			if(EnumConstants.WF_BCXX.equals(reqLoanContractSignVo.getTaskName())){
				taskPath = EnumConstants.WFPH_BCXX;
			}else if(EnumConstants.WF_SCHT.equals(reqLoanContractSignVo.getTaskName())){
				taskPath = EnumConstants.WFPH_SCHT;
			}else if(EnumConstants.WF_HTQD.equals(reqLoanContractSignVo.getTaskName())){
				taskPath = EnumConstants.WFPH_HTQD;
			}
		//完成相应任务
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),taskPath);
		} catch (BizException e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR,e);
		}
		submitFlag=true;
		
		return submitFlag;
	}



	@Override
	public BMSLoanContract findByLoanNo(String loanNo) {
		
		return loanContractDao.findByLoanNo(loanNo);
	}
	
	
	@Override
	public BMSLoanContract findLoanContractInfo(ReqLoanContractSignVo reqLoanContractSignVo) {
		Map paramMap =new HashMap();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		List<BMSLoanContract> contractList= loanContractDao.listBy(paramMap);
		BMSLoanContract bmsLoanContract=contractList.get(0);
		return bmsLoanContract;
	}
	

	private boolean pushCore(ReqLoanContractSignVo reqLoanContractSignVo) {
		//债权推送信息组装参数
		Map<String,Object> param =new HashMap<String,Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());
		String jsonStr=loanSignDataOprateService.fetchAllApplyData(reqLoanContractSignVo);
		Map<String,Object> httpParam =new HashMap<String,Object>();
		httpParam.put("userCode", reqLoanContractSignVo.getServiceCode());
		httpParam.put("jsonStr", jsonStr);
		//调用
		HttpResponse httpResponse=	coreHttpService.pushBMSLoan(httpParam);
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(),HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode()) ){
			throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL,contractReturnEntity.getMessage());	
		}else{
			return true;
		}		
	}



	@Override
	public List<BMSRepaymentDetail> getPayPlanList(ReqLoanContractSignVo reqLoanContractSignVo) {
		// 构造请求参数
				
				Map<String, Object> paramMap= new HashMap<String, Object>();
				paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
				List<BMSRepaymentDetail> repaymentDetails = repaymentDetailDao.listBy(paramMap);
				return repaymentDetails;
	}


	@Override
	public long update(BMSLoanProduct bmsLoanProduct) {
		return loanContractSignDao.update(bmsLoanProduct);
	}

	/**
	 * 查询借款产品信息
	 *
	 * @param loanNo
	 * @return
	 */
	@Override
	public BMSLoanProduct findBMSLoanProductByLoanNo(String loanNo) {
		Map<String,Object> param=new HashMap<>();
		param.put("loanNo",loanNo);
		return loanContractSignDao.findBMSLoanProductByLoanNo(param);
	}
	
	@Override
	public PageBean<BMSLoanBaseEntity> unclaimedContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo) {
		// 构造请求参数
		PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
		Map<String, Object> paramMap= null;
		try {
			paramMap = BeanKit.bean2map(reqLoanContractSignVo);
			
			paramMap.put("rtfNodeState", EnumConstants.RtfNodeState.HTQYASSIGN.getValue());
			paramMap.put("rtfState", EnumConstants.RtfState.HTQY.getValue());
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		// 调用业务逻辑
		PageBean<BMSLoanBaseEntity> pageBean = loanBaseEntityDao.unclaimedContractSignListBy(pageParam, paramMap);		
		return pageBean;
	}


	@Override
	public boolean claimedContractSign(ReqLoanContractSignVo reqLoanContractSignVo ) {
		//更新借款系统借款状态
		BMSLoanBaseEntity  loanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getId(),
				EnumConstants.RtfState.HTQY.getValue(),EnumConstants.RtfNodeState.HTQYASSIGN.getValue(),reqLoanContractSignVo.getVersion());
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.PASS.getValue());//借款状态
		loanBaseEntity.setSignCode(reqLoanContractSignVo.getServiceCode());
		loanBaseEntity.setSignName(reqLoanContractSignVo.getServiceName());
		long count=  loanBaseEntityDao.update(loanBaseEntity);
		
		if(count !=1){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}else{
			return true;
		}	
	}
	
	@Override
	public void checkChangeAndDelFile(ReqLoanContractSignVo reqLoanContractSignVo,Response<ResLoanContractSignVo> res) {
		if (!"".equals(needDelPicTypeCode.trim())) {
			Map<String, String> requestDel = new HashMap<String, String>();
			requestDel.put("appNo", reqLoanContractSignVo.getLoanNo());
			requestDel.put("sorts", needDelPicTypeCode.trim());
			requestDel.put("operator", reqLoanContractSignVo.getServiceCode());
			requestDel.put("jobNumber", reqLoanContractSignVo.getServiceName());
			HttpResponse response=  pictureService.batchDelPicFile(requestDel);	 
			if(response ==null ||response.getCode() !=200){
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用PIC系统失败");
			}else{
				Map retMap =JsonUtils.decode(response.getContent(), Map.class);
				if(retMap ==null ||!"000000".equals(retMap.get("errorcode"))){
					BmsLogger.info(response.getContent());
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用PIC系统返回失败消息");
				}
			}  

		}
	}
}
