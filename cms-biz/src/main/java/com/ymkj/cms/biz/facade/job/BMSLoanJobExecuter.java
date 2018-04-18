package com.ymkj.cms.biz.facade.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyValidateExecuter;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.service.job.IBMSLoanJobExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryCancelVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqValidateVo;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.job.ReqLoanJobVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.apply.LoanBaseRelaService;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseEntityService;
import com.ymkj.cms.biz.service.master.IBMSLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.service.ICalendarExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqCalendarVO;
import com.ymkj.rule.biz.api.message.MapResponse;
import com.ymkj.rule.biz.api.message.RuleEngineRequest;
import com.ymkj.rule.biz.api.service.IRuleEngineExecuter;
import com.ymkj.rule.biz.api.vo.ApplyRuleBatchExecVo;

@Service
public class BMSLoanJobExecuter implements IBMSLoanJobExecuter {

	private Gson gson = new Gson();
	private static final Logger logger = LoggerFactory.getLogger(BMSLoanJobExecuter.class);

	@Autowired
	private IBMSLoanBaseEntityService bmsLoanBaseEntityService;
	@Autowired
	private IEntryAuditExecuter entryAuditExecuter;
	@Autowired
	private ICalendarExecuter calendarExecuter;
	@Autowired
	private ILoanContractSignExecuter loanContractSignExecuter;
	@Autowired
	private IBMSLoanLogService bmsLoanLogService;
	@Autowired
	private ApplyDataManipulationService ApplyDataManipulationServiceImpl;
	@Autowired
	private  LoanBaseRelaService  loanBaseRelaService;
	@Autowired
	private APPPersonInfoService appPersonInfoService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ILoanService LoanService;
	@Autowired
	private IApplyValidateExecuter applyValidateExecuter;
	@Autowired
	private IRuleEngineExecuter ruleEngineExecuter;
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;
	
	@Override
	public Response<Object> recordTimeOutRefuseAPP(ReqLoanJobVO reqLoanJobVO) {
		Response<Object> response = new Response<Object>();
		
		List<Map<String,Object>> resultList = new ArrayList<>();

		try {
			// 参数校验
			if (reqLoanJobVO.getSysCode() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
			}
			
			List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();
			
			//调用取消接口Vo
			ReqEntryCancelVO reqEntryCancelVO = new ReqEntryCancelVO();
			reqEntryCancelVO.setServiceCode(EnumConstants.BMS_SYSTEM_CODE);
			reqEntryCancelVO.setServiceName(EnumConstants.DEFAULT_SYSTEM_NAME);
			reqEntryCancelVO.setServiceId(0L);
			reqEntryCancelVO.setIp(RpcContext.getContext().getRemoteHost());
			reqEntryCancelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			
			//封装益博睿数据
			 List<ApplyRuleBatchExecVo> validateList = convertToRequestYBRData(loanBaseList,resultList);
			
			//调用“益百利”接口
			RuleEngineRequest ruleRequest = new RuleEngineRequest();
			ruleRequest.setBizType("loanApplyBatch");
			ruleRequest.setSysCode(EnumConstants.BMS_SYSCODE);
			ruleRequest.setData(validateList);
			
			if(validateList.isEmpty()){
				response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
				response.setRepMsg("Success");
				response.setData(resultList);
				return response;
			}
			
			logger.info("录单APP进件时效超时自动拒绝定时任务【益博睿入参】:"+gson.toJson(ruleRequest));
			
			com.ymkj.rule.biz.api.message.Response resRule =  ruleEngineExecuter.executeRuleEngine(ruleRequest);
			if(resRule!=null && EnumConstants.RES_CODE_SUCCESS.equals(resRule.getRepCode())){
				MapResponse response_ = (MapResponse)resRule;
				List<Map<String, Object>> ruleMapList = response_.getMapList();
				for (Map<String, Object> map : ruleMapList) {
					logger.info("录单APP进件时效超时自动拒绝定时任务【益博睿出参】:"+gson.toJson(ruleRequest));

					if(map.get("action") !=null && EnumConstants.OperationType.REJECT.getValue().equals(map.get("action"))){
						refuseJobDispose(loanBaseList, resultList, map, reqEntryCancelVO);						
					} else if(map.get("action") !=null && EnumConstants.OperationType.CANCEL.getValue().equals(map.get("action"))) {
						cancelJobDispose(loanBaseList, resultList, map, reqEntryCancelVO);					
					}
				}
				
			} else {
				if(resRule != null){
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, resRule.getRepMsg());
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "规则引擎返回结果为null");
				}
				// 抛出自定义异常
			}
			
			response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
			response.setRepMsg("Success");
			response.setData(resultList);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			e.printStackTrace();
			
			// 抛出自定义异常
			throw new BizException(BizErrorCode.SYSTEM_ERROR, e.getMessage());
		}
		return response;
	}

	

	


	@Override
	public Response<Object> supplementTimeOutCancel(ReqLoanJobVO reqLoanJobVO) {
		Response<Object> response = new Response<Object>();
		
		List<Map<String,Object>> resultList = new ArrayList<>();

		try {
			// 参数校验
			if (reqLoanJobVO.getSysCode() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
			}
			List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();

			
			//调用取消接口Vo
			ReqEntryCancelVO reqEntryCancelVO = new ReqEntryCancelVO();
			reqEntryCancelVO.setServiceCode(EnumConstants.BMS_SYSTEM_CODE);
			reqEntryCancelVO.setServiceName(EnumConstants.DEFAULT_SYSTEM_NAME);
			reqEntryCancelVO.setServiceId(0L);
			reqEntryCancelVO.setIp(RpcContext.getContext().getRemoteHost());
			reqEntryCancelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			
			//调用平台接口Vo
			ReqCalendarVO reqCalendarVO = new ReqCalendarVO();
			reqCalendarVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			reqCalendarVO.setBizType(EnumConstants.BMS_SYSTEM_CODE);
			
			//查询日志数据map
			Map<String, Object> loanLogMap = new HashMap<String, Object>();
			
			//从借款日志表获取 -XSCS-RETURN/XSZS-RETURN的最新时间
			List<String> rtfNodeStateListLogList = new ArrayList<String>();//联合状态   status+rtfState+rtfNodeState
			rtfNodeStateListLogList.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入
			rtfNodeStateListLogList.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());//终审退回录入
			loanLogMap.put("rtfNodeStateList", rtfNodeStateListLogList);
			
			
			Date endDate = new Date();
			//单笔数据校验
			for (ResBMSLoanBaseVO loanBase : loanBaseList) {
				loanLogMap.put("loanNo", loanBase.getLoanNo());
				BMSLoanLog loanLog = bmsLoanLogService.findLastLog(loanLogMap);
				if(loanLog == null || loanLog.getOperationTime() == null){
					continue;
				}
				Date startDate = loanLog.getOperationTime();
				
				//调用平台接口，获取有效工作日
				reqCalendarVO.setQueryBegin(startDate);
				reqCalendarVO.setQueryEnd(endDate);
				
				Response<Integer> res = calendarExecuter.getWorkDayNum(reqCalendarVO);
				//调用判断
				if(!EnumConstants.RES_CODE_SUCCESS.equals(res.getRepCode())){
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "false");
					resultMap.put("message", res.getRepMsg());
					resultList.add(resultMap);
					continue;
				}

				Integer workDayNum = res.getData();
				//	当前时间-信审回退门店时间＞5个工作日
				if(workDayNum > 5){
					
					//调用取消接口 	借款日志中对应的取消原因只有一级原因：超补件时效自动取消
					reqEntryCancelVO.setLoanBaseId(Long.valueOf(loanBase.getId()));
					reqEntryCancelVO.setLoanNo(loanBase.getLoanNo());
					reqEntryCancelVO.setFirstLevelReason("超补件时效自动取消");
					reqEntryCancelVO.setFirstLevelReasonCode(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue());
					reqEntryCancelVO.setTwoLevelReason("0");
					reqEntryCancelVO.setTwoLevelReasonCode("0");
					reqEntryCancelVO.setOptionModule(loanBase.getRtfState());
					reqEntryCancelVO.setRemark("");
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
					
					if(EnumConstants.RtfNodeState.SQLR_SAVE.getValue().equals(loanBase.getRtfNodeState())
							|| EnumConstants.RtfNodeState.XSCSRETURN.getValue().equals(loanBase.getRtfNodeState())
							|| EnumConstants.RtfNodeState.XSZSRETURN.getValue().equals(loanBase.getRtfNodeState())
							||EnumConstants.RtfNodeState.LRFH_RETURN.getValue().equals(loanBase.getRtfNodeState())){
						reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());

					}else if(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue().equals(loanBase.getRtfNodeState())){
						reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
						
					}
					
					Response resCancel = entryAuditExecuter.cancel(reqEntryCancelVO);
					if(!EnumConstants.RES_CODE_SUCCESS.equals(resCancel.getRepCode())){
						Map<String,Object> resultMap = new HashMap<String,Object>();
						resultMap.put("loanNo", loanBase.getLoanNo());
						resultMap.put("isSuccess", "false");
						resultMap.put("message", resCancel.getRepMsg());
						resultList.add(resultMap);
						continue;
					}
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "true");
					resultList.add(resultMap);
				}
			}
			response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
			response.setRepMsg("Success");
			response.setData(resultList);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(BizErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}



	@Override
	public Response<Object> signTimeOutCancel(ReqLoanJobVO reqLoanJobVO) {
		Response<Object> response = new Response<Object>();
		
		List<Map<String,Object>> resultList = new ArrayList<>();

		
		// 参数校验
		if (reqLoanJobVO.getSysCode() == null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
		}
		
		List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();
		
		//调用取消接口Vo
		ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
		reqLoanContractSignVo.setServiceCode(EnumConstants.BMS_SYSTEM_CODE);
		reqLoanContractSignVo.setServiceName(EnumConstants.DEFAULT_SYSTEM_NAME);
		reqLoanContractSignVo.setIp(RpcContext.getContext().getRemoteHost());
		reqLoanContractSignVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		//调用平台接口Vo
		ReqCalendarVO reqCalendarVO = new ReqCalendarVO();
		reqCalendarVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqCalendarVO.setBizType(EnumConstants.BMS_SYSTEM_CODE);
		
		//查询日志数据map
		Map<String, Object> loanLogMap = new HashMap<String, Object>();
		
		//从借款日志表获取 XSZS-PASS的最新时间
		List<String> rtfNodeStateListLogList = new ArrayList<String>();//状态   rtfNodeState
		rtfNodeStateListLogList.add(EnumConstants.RtfNodeState.XSZSPASS.getValue());//信审终审通过
		loanLogMap.put("rtfNodeStateList", rtfNodeStateListLogList);
			
			
		Date endDate = new Date();
			//单笔数据校验
		for (ResBMSLoanBaseVO loanBase : loanBaseList) {
			try {
				loanLogMap.put("loanNo", loanBase.getLoanNo());
				BMSLoanLog loanLog = bmsLoanLogService.findLastLog(loanLogMap);
				if(loanLog == null || loanLog.getOperationTime() == null){
					continue;
				}
				Date startDate = loanLog.getOperationTime();
				
				//调用平台接口，获取有效工作日
				reqCalendarVO.setQueryBegin(startDate);
				reqCalendarVO.setQueryEnd(endDate);
				
				Integer workDayNum = getIntervalDays(startDate, endDate);
				//	当前时间-信审通过时间＞15个自然日
				if(workDayNum > 15){
					
					//调用取消接口 	借款日志中对应的取消原因只有一级原因：超签约时效自动取消
					reqLoanContractSignVo.setId(Long.valueOf(loanBase.getId()));
					reqLoanContractSignVo.setLoanNo(loanBase.getLoanNo());
					reqLoanContractSignVo.setChannelCd(loanBase.getChannelCd());
					reqLoanContractSignVo.setFirstLevleReasons("超签约时效自动取消");
					reqLoanContractSignVo.setFirstLevleReasonsCode(EnumConstants.cancelFirstLevleReasonCode.超签约时效自动取消.getValue());
					reqLoanContractSignVo.setTwoLevleReasons("0");
					reqLoanContractSignVo.setTwoLevleReasonsCode("0");
					reqLoanContractSignVo.setRemark("");
					
					
					Response resCancel = loanContractSignExecuter.cancelLoan(reqLoanContractSignVo);
					if(!EnumConstants.RES_CODE_SUCCESS.equals(resCancel.getRepCode())){
						Map<String,Object> resultMap = new HashMap<String,Object>();
						resultMap.put("loanNo", loanBase.getLoanNo());
						resultMap.put("isSuccess", "false");
						resultMap.put("message", resCancel.getRepMsg());
						resultList.add(resultMap);
						continue;
					}
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "true");
					resultList.add(resultMap);
				}
			} catch (BizException e) {
				response.setRepCode(e.getRealCode());
				String message = "";
				if (e.getArguments() != null && e.getArguments().length > 0) {
					for (int i = 0; i < e.getArguments().length; i++) {
						message += e.getArguments()[i];
					}
				}
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", e.getErrorMsg() +":"+ message);
				resultList.add(resultMap);
			} catch (Exception e) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", e.getMessage());
				resultList.add(resultMap);
			}
		}
		response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
		response.setRepMsg("Success");
		response.setData(resultList);

		return response;
	}

	@Override
	public Response<Object> bindCreditReport(ReqLoanJobVO reqLoanJobVO) {
		Response<Object> response = new Response<Object>();
		List<Map<String,Object>> resultList = new ArrayList<>();

		try {
			// 参数校验
			if (reqLoanJobVO.getSysCode() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
			}

			List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();
			
			
			
			//单笔数据校验
			for (ResBMSLoanBaseVO loanBaseEntity : loanBaseList) {
				//人行征信报告绑定
				PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
				personHistoryLoanVO.setSysCode(EnumConstants.BMS_SYSCODE);
				personHistoryLoanVO.setName(loanBaseEntity.getName());
				personHistoryLoanVO.setIdNo(loanBaseEntity.getIdNo());
				personHistoryLoanVO.setLoanNo(loanBaseEntity.getLoanNo());
				String fixedCreditReport = ApplyDataManipulationServiceImpl.fixedCreditReport(personHistoryLoanVO);

				if(fixedCreditReport != null){
					LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(Long.valueOf(loanBaseEntity.getId()));
					APPPersonInfoEntity reportEntity = new APPPersonInfoEntity();
					reportEntity.setId(LoanBaseRela.getBmsAppPersonInfoId());
					reportEntity.setReportId(new Long(fixedCreditReport));
					reportEntity.setModifiedTime(new Date());
					reportEntity.setModifier(EnumConstants.BMS_SYSTEM_CODE); 
					reportEntity.setModifierId(0L); 
					appPersonInfoService.saveOrUpdate(reportEntity);
				} else {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBaseEntity.getLoanNo());
					resultMap.put("isSuccess", "false");
					resultMap.put("message", "央行报告获取失败");
					resultList.add(resultMap);
				}
				
				
			}
			
			response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
			response.setRepMsg("Success");
			response.setData(resultList);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出自定义异常
			throw new BizException(BizErrorCode.SYSTEM_ERROR, e.getMessage());
		}
		return response;
	}

	@Override
	public Response<Object> grantLoanUpdateByCore(ReqLoanJobVO reqLoanJobVO) {
		List<Map<String,Object>> resultList = new ArrayList<>();

		Response<Object> response = new Response<Object>();

		try {
			// 参数校验
			if (reqLoanJobVO.getSysCode() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
			}
			
			List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();
			
			//借款编号拼接
			String loanNos = "";
			for (ResBMSLoanBaseVO entity : loanBaseList) {
				loanNos += entity.getLoanNo() + ",";
			}
			boolean isEmpty = false;
			if(loanNos.length()>1){
				loanNos = loanNos.substring(0, loanNos.length()-1);
				isEmpty = true;
			}
			if(isEmpty){
				Map<String, Object> httpParamMap  = new HashMap<String, Object>();
				httpParamMap.put("loanNos", loanNos);
				HttpResponse httpResponse =coreHttpService.queryLoanState(httpParamMap);
				if (httpResponse != null) {
					if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){ // 查询成功
						// 再次封装
						String content = httpResponse.getContent();
						Map contentMap = JsonUtils.decode(content, Map.class);
						if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("code"))){// 查询成功
							if(contentMap.get("loans") == null){
								throw new BizException(BizErrorCode.REQUEST_PARAM_ISNULL, "loans属性不存在");
							}
							//依据核心状态更新数据
							List<Map> loans = (List<Map>) contentMap.get("loans");
							if(!loans.isEmpty()){
								List<String> loanNoList = new ArrayList<String>();
								for (Map map : loans) {
									if(map.get("loanNo") != null){
										if(map.get("loanState") != null 
												&& EnumConstants.CoreLoanState.ZC.getValue().equals(map.get("loanState"))){
											loanNoList.add(map.get("loanNo").toString());
											
										}
									}
								}
								if(!loanNoList.isEmpty()){
									ReqLoanVo reqLoanVo = new ReqLoanVo();
									reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
									reqLoanVo.setLoanNos(loanNoList);
									reqLoanVo.setServiceCode(EnumConstants.BMS_SYSCODE);
									reqLoanVo.setServiceName(EnumConstants.DEFAULT_SYSTEM_NAME);
									Map<String, Object> retMap = LoanService.grantLoanCore(reqLoanVo);
									resultList = (List<Map<String, Object>>) retMap.get("msg");
								}
							}
						} else {
							throw new BizException(BizErrorCode.DB_RESULT_ISNULL, contentMap.get("message").toString());
						}
					} else {
						throw new BizException(BizErrorCode.DB_RESULT_ISNULL, httpResponse.getMessage());
						
					}
				}
				
			}
			response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
			response.setRepMsg("Success");
			response.setData(resultList);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出自定义异常
			throw new BizException(BizErrorCode.SYSTEM_ERROR, e.getMessage());
		}
		return response;
	}
	
	public static int getIntervalDays(Date startDate, Date endDate) {

	       Calendar aCalendar = Calendar.getInstance();

	       aCalendar.setTime(startDate);

	       int startDay = aCalendar.get(Calendar.DAY_OF_YEAR);

	       aCalendar.setTime(endDate);

	       int endday = aCalendar.get(Calendar.DAY_OF_YEAR);

	       return endday - startDay;

	    }



	@Override
	public Response<Object> recordLogoProcessing(ReqLoanJobVO reqLoanJobVO) {
		Response<Object> response = new Response<Object>();
		
		List<Map<String,Object>> resultList = new ArrayList<>();

		try {
			// 参数校验
			if (reqLoanJobVO.getSysCode() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "sysCode" });
			}
			
			List<ResBMSLoanBaseVO> loanBaseList = reqLoanJobVO.getDisposeList();
			
			//封装益博睿数据
			 List<ApplyRuleBatchExecVo> validateList = convertToRequestYBRData(loanBaseList,resultList);
			
			//调用“益百利”接口
			RuleEngineRequest ruleRequest = new RuleEngineRequest();
			ruleRequest.setBizType("loanApplyBatch");
			ruleRequest.setSysCode(EnumConstants.BMS_SYSCODE);
			ruleRequest.setData(validateList);
			logger.info("------益博睿标识验证参数------" + gson.toJson(ruleRequest));
			//益博睿会区分app，或非app
			com.ymkj.rule.biz.api.message.Response resRule =  ruleEngineExecuter.executeRuleEngine(ruleRequest);
			logger.info("------益博睿标识返回结果------" + gson.toJson(resRule));

			if(resRule!=null && EnumConstants.RES_CODE_SUCCESS.equals(resRule.getRepCode())){
				MapResponse response_ = (MapResponse)resRule;
				List<Map<String, Object>> ruleMapList = response_.getMapList();
				for (Map<String, Object> map : ruleMapList) { 
					if(map.get("action") !=null && EnumConstants.OperationType.LOGO.getValue().equals(map.get("action"))){
						logoJobDispose(loanBaseList,resultList,map);
						
					}
				}
				
			} else {
				if(resRule != null){
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, resRule.getRepMsg());
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "规则引擎返回结果为null");
				}
				// 抛出自定义异常
			}
			
			response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
			response.setRepMsg("Success");
			response.setData(resultList);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			e.printStackTrace();
			
			// 抛出自定义异常
			throw new BizException(BizErrorCode.SYSTEM_ERROR, e.getMessage());
		}
		return response;
	}
	
	private void logoJobDispose(List<ResBMSLoanBaseVO> loanBaseList, List<Map<String, Object>> resultList,
			Map<String, Object> map) {
		ResBMSLoanBaseVO loanBase = null;
		
		for (ResBMSLoanBaseVO entity : loanBaseList) {
			if(map.get("loanNo") != null && map.get("loanNo").equals(entity.getLoanNo())){
				loanBase = entity;
				break;
			}
		}
		if(loanBase == null){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("loanNo", map.get("loanNo"));
			resultMap.put("isSuccess", "false");
			resultMap.put("message", "返回信息loanNo不正确");
			resultList.add(resultMap);
			return;
		}
		BMSLoanBaseEntity entity = new BMSLoanBaseEntity();
		entity.setId(Long.valueOf(loanBase.getId()));
		entity.setLogoFlag(EnumConstants.LogoFlagType.HS.getCode());
		//加盖标识
		long resCancel = loanBaseDao.update(entity);

		if(resCancel<1){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("loanNo", loanBase.getLoanNo());
			resultMap.put("isSuccess", "false");
			resultMap.put("message", "更新失败");
			resultList.add(resultMap);
			return;
		}
				
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("loanNo", map.get("LoanNo"));
		resultMap.put("isSuccess", "true");
		resultList.add(resultMap);
		
	}






	private void refuseJobDispose(List<ResBMSLoanBaseVO> loanBaseList, List<Map<String, Object>> resultList,
		Map<String, Object> map, ReqEntryCancelVO reqEntryCancelVO) {
		ResBMSLoanBaseVO loanBase = null;
		if(map.get("actionCode") != null){
			for (ResBMSLoanBaseVO entity : loanBaseList) {
				if(map.get("loanNo") != null && map.get("loanNo").equals(entity.getLoanNo())){
					loanBase = entity;
					break;
				}
			}
			if(loanBase == null){
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", map.get("loanNo"));
				resultMap.put("isSuccess", "false");
				resultMap.put("message", "返回信息loanNo不正确");
				resultList.add(resultMap);
				return;
			}
			
			//判断是一级原因，还是二级原因
			Map<String, Object> reasonParamMap = new HashMap<String, Object>();
			reasonParamMap.put("code", map.get("actionCode"));
			
			BMSTMReasonEntity reason = iBMSTMReasonService.getByParam(reasonParamMap);
			if(reason != null){
				String firstLevelReason = "0";
				String firstLevelReasonCode = "0";
				String twoLevelReason = "0";
				String twoLevelReasonCode = "0";
				if(EnumConstants.ReasonType.FirstLevleReason.getValue().equals(reason.getType())){//一级原因
					
				} else if(EnumConstants.ReasonType.TwoLevleReason.getValue().equals(reason.getType())){
					twoLevelReason = reason.getReason();
					twoLevelReasonCode = reason.getCode();
					
					reasonParamMap.clear();
					reasonParamMap.put("id", reason.getParentId());

					reason = iBMSTMReasonService.getByParam(reasonParamMap);
				}

				firstLevelReasonCode = reason.getCode();
				firstLevelReason = reason.getReason();
				
				
				//调用取消接口,	借款日志中对应的取消一二级原因由益百利返回
				reqEntryCancelVO.setLoanBaseId(Long.valueOf(loanBase.getId()));
				reqEntryCancelVO.setLoanNo(loanBase.getLoanNo());
				reqEntryCancelVO.setFirstLevelReason(firstLevelReason);
				reqEntryCancelVO.setFirstLevelReasonCode(firstLevelReasonCode);
				reqEntryCancelVO.setTwoLevelReason(twoLevelReason);
				reqEntryCancelVO.setTwoLevelReasonCode(twoLevelReasonCode);
				reqEntryCancelVO.setRemark("");
				if(EnumConstants.RtfNodeState.SQLR_SAVE.getValue().equals(loanBase.getRtfNodeState())){
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_ENTRY.getValue());
				} else if(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue().equals(loanBase.getRtfNodeState())){
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
				} else if(EnumConstants.RtfNodeState.LRFH_RETURN.getValue().equals(loanBase.getRtfNodeState())){
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
				}
				
				//录入超时拒绝	LRCS-REJECT
				Response resCancel = entryAuditExecuter.timeOutRefuse(reqEntryCancelVO);
				if(!EnumConstants.RES_CODE_SUCCESS.equals(resCancel.getRepCode())){
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "false");
					resultMap.put("message", resCancel.getRepMsg());
					resultList.add(resultMap);
					return;
				}
				
				
			} else {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", "原因code不存在");
				resultList.add(resultMap);
				return;
			}
		} else {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("loanNo", map.get("loanNo"));
			resultMap.put("isSuccess", "false");
			resultMap.put("message", "返回原因code空");
			resultList.add(resultMap);
			return;
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("loanNo", map.get("loanNo"));
		resultMap.put("isSuccess", "true");
		resultList.add(resultMap);
		
	}



	private List<ApplyRuleBatchExecVo> convertToRequestYBRData(List<ResBMSLoanBaseVO> loanBaseList,
			List<Map<String, Object>> resultList) {
		List<ApplyRuleBatchExecVo> validateList = new ArrayList<ApplyRuleBatchExecVo>();
		//单笔数据校验
		for (ResBMSLoanBaseVO loanBase : loanBaseList) {
			
			//提取数据
			ValidateNameIdNoVO validateVo = new ValidateNameIdNoVO();
			validateVo.setSysCode(EnumConstants.BMS_SYSCODE);
			
			validateVo.setName(loanBase.getName());
			validateVo.setIdNo(loanBase.getIdNo());
			validateVo.setLoanNo(loanBase.getLoanNo());
			validateVo.setOwningBranchId(Long.valueOf(loanBase.getOwningBranchId()));
			validateVo.setProductCode(loanBase.getProductCode());
			
			try{
				//获取需要传给“益百利”数据
				Response<ReqValidateVo> resVo= applyValidateExecuter.validateNameIdNo(validateVo);
				
				if(resVo.isSuccess()){
					ReqValidateVo demoEntity = resVo.getData();
					ApplyRuleBatchExecVo ruleVO = new ApplyRuleBatchExecVo();
					BeanUtils.copyProperties(demoEntity, ruleVO);
					
					ruleVO.setExecuteType("LDPC009");
					validateList.add(ruleVO);
				} else {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "false");
					resultMap.put("message", resVo.getRepMsg());
					resultList.add(resultMap);
					continue;
				}
			}catch (BizException e) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", e.getMessage());
				resultList.add(resultMap);
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", e.getMessage());
				resultList.add(resultMap);
				continue;
			}
		}
		return validateList;
	}

	private void cancelJobDispose(List<ResBMSLoanBaseVO> loanBaseList, List<Map<String, Object>> resultList,
			Map<String, Object> map, ReqEntryCancelVO reqEntryCancelVO) {
		ResBMSLoanBaseVO loanBase = null;
		if(map.get("actionCode") !=null){
			for (ResBMSLoanBaseVO entity : loanBaseList) {
				if(map.get("loanNo") != null && map.get("loanNo").equals(entity.getLoanNo())){
					loanBase = entity;
					break;
				}
			}
			if(loanBase == null){
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", map.get("loanNo"));
				resultMap.put("isSuccess", "false");
				resultMap.put("message", "返回信息loanNo不正确");
				resultList.add(resultMap);
				return;
			}
			//判断是一级原因，还是二级原因
			Map<String, Object> reasonParamMap = new HashMap<String, Object>();
			reasonParamMap.put("code", map.get("actionCode"));
			
			BMSTMReasonEntity reason = iBMSTMReasonService.getByParam(reasonParamMap);
			if(reason != null){
				String firstLevelReason = "0";
				String firstLevelReasonCode = "0";
				String twoLevelReason = "0";
				String twoLevelReasonCode = "0";
				if(EnumConstants.ReasonType.FirstLevleReason.getValue().equals(reason.getType())){//一级原因
					
				} else if(EnumConstants.ReasonType.TwoLevleReason.getValue().equals(reason.getType())){
					twoLevelReason = reason.getReason();
					twoLevelReasonCode = reason.getCode();
					
					reasonParamMap.clear();
					reasonParamMap.put("id", reason.getParentId());

					reason = iBMSTMReasonService.getByParam(reasonParamMap);
				}

				firstLevelReasonCode = reason.getCode();
				firstLevelReason = reason.getReason();
	
				//调用取消接口 	借款日志中对应的取消原因只有一级原因：超补件时效自动取消
				reqEntryCancelVO.setLoanBaseId(Long.valueOf(loanBase.getId()));
				reqEntryCancelVO.setLoanNo(loanBase.getLoanNo());
				reqEntryCancelVO.setFirstLevelReason(firstLevelReason);
				reqEntryCancelVO.setFirstLevelReasonCode(firstLevelReasonCode);
				reqEntryCancelVO.setTwoLevelReason(twoLevelReason);
				reqEntryCancelVO.setTwoLevelReasonCode(twoLevelReasonCode);
				reqEntryCancelVO.setOptionModule(loanBase.getRtfState());
				reqEntryCancelVO.setRemark("");
				reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
	
				if(EnumConstants.RtfNodeState.SQLR_SAVE.getValue().equals(loanBase.getRtfNodeState())
						|| EnumConstants.RtfNodeState.XSCSRETURN.getValue().equals(loanBase.getRtfNodeState())
						|| EnumConstants.RtfNodeState.XSZSRETURN.getValue().equals(loanBase.getRtfNodeState())
						||EnumConstants.RtfNodeState.LRFH_RETURN.getValue().equals(loanBase.getRtfNodeState())){
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());

				}else if(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue().equals(loanBase.getRtfNodeState())){
					reqEntryCancelVO.setOptionModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
					
				}
	
				Response resCancel = entryAuditExecuter.cancel(reqEntryCancelVO);
				if(!EnumConstants.RES_CODE_SUCCESS.equals(resCancel.getRepCode())){
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanNo", loanBase.getLoanNo());
					resultMap.put("isSuccess", "false");
					resultMap.put("message", resCancel.getRepMsg());
					resultList.add(resultMap);
					return;
				}
			} else {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("loanNo", loanBase.getLoanNo());
				resultMap.put("isSuccess", "false");
				resultMap.put("message", "原因code不存在");
				resultList.add(resultMap);
				return;
			}
		} else {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("loanNo", map.get("loanNo"));
			resultMap.put("isSuccess", "false");
			resultMap.put("message", "返回原因code空");
			resultList.add(resultMap);
			return;
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("loanNo", loanBase.getLoanNo());
		resultMap.put("isSuccess", "true");
		resultMap.put("message", "成功");
		resultList.add(resultMap);
		
	}

}
