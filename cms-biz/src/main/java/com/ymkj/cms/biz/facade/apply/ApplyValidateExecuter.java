package com.ymkj.cms.biz.facade.apply;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mchange.v1.lang.GentleThread;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.vo.request.BlackListQueryReqVO;
import com.ymkj.bds.biz.api.vo.response.BlackListResVO;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyValidateExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApprovalOpinionsVO;
import com.ymkj.cms.biz.api.vo.request.apply.AuditRulesVO;
import com.ymkj.cms.biz.api.vo.request.apply.ContractSignInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.FindHisVO;
import com.ymkj.cms.biz.api.vo.request.apply.FindHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReasonVO;
import com.ymkj.cms.biz.api.vo.request.apply.RecordVO;
import com.ymkj.cms.biz.api.vo.request.apply.RepayListVo;
import com.ymkj.cms.biz.api.vo.request.apply.RepayVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCreditCheckVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqValidateVo;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateRecordVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResContractSignVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResYBRReturnVO;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.MoneyUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSCreditRatingLimitDao;
import com.ymkj.cms.biz.entity.APPPersonVauleAddresEntity;
import com.ymkj.cms.biz.entity.app.BadCreditInfoEntity;
import com.ymkj.cms.biz.entity.app.BadCreditInfoValueEntity;
import com.ymkj.cms.biz.entity.app.ResBadCreditInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPCurrentApplyEntity;
import com.ymkj.cms.biz.entity.apply.BmsRuleLogEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanMapperToEntity;
import com.ymkj.cms.biz.entity.apply.QueryMainEntity;
import com.ymkj.cms.biz.entity.apply.ThirdPartyInfoDateEntity;
import com.ymkj.cms.biz.entity.apply.ThirdPartyInfoEntity;
import com.ymkj.cms.biz.entity.apply.ThirdPartyInfoListDateEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.master.BMSCreditRatingLimit;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPCarInfoService;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.apply.IBaseRuleLogService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;
import com.ymkj.cms.biz.service.http.CreditHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.http.impl.CreditHttpServiceImpl;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;
import com.ymkj.pms.biz.api.service.ICalendarExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqCalendarVO;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResCalendarVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationInfo;


public class ApplyValidateExecuter implements IApplyValidateExecuter {
	public Date defaultDate;

	private Gson gson = new Gson();
	private static final Logger logger = LoggerFactory.getLogger(ApplyValidateExecuter.class);

	@Autowired
	private LoanBaseService loanBaseService;
	
	@Autowired
	private APPCarInfoService appCarInfoService;

	@Autowired
	private IBMSTmParameterService tmParameterService;

	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;

	@Autowired
	private LoanProductService loanProductService;

	@Autowired
	private IBaseRuleLogService iBaseRuleLogService;

	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;

	@Autowired
	private IBlackListExecuter blackListExecuter;
	
	@Autowired
	private ILoanAuditService iLoanAuditService;

	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;

	@Autowired
	private ICoreHttpService coreHttpService;

	@Autowired
	private ICalendarExecuter iCalendarExecuter;

	@Autowired
	private CreditHttpService creditHttpService;

	@Autowired
	private IBMSTmParameterService ibmsTmParameterService;

	@Autowired
	private IBMSCreditRatingLimitDao iBMSCreditRatingLimitDao;

	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;
	
	@Value("#{env['checkUrl']?:''}")
	private String checkUrl;
	
	@Autowired
	private CreditHttpServiceImpl creditHttpServiceImpl;
	
	@Value("#{env['queryHZMobileOnlineInfo']?:''}")
	private String queryHZMobileOnlineInfo;
	
	@Value("#{env['queryHZIdCardCheckInfo']?:''}")
	private String queryHZIdCardCheckInfo;
	
	
	
	@Override
	public Response<ReqValidateVo> validateNameIdNo(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<ReqValidateVo> res = new Response<ReqValidateVo>();

		ReqValidateVo reqValidateVo = new ReqValidateVo();
		// 姓名
		if (StringUtils.isEmpty(validateNameIdNoVO.getName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "name" });
		}
		// 身份证
		if (StringUtils.isEmpty(validateNameIdNoVO.getIdNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "idNo" });
		}
		try {
			reqValidateVo.initDate();// 将所有时间默认赋值

			BeanUtils.copyProperties(validateNameIdNoVO, reqValidateVo);
			if(null!=validateNameIdNoVO.getOwningBranchId()){
				reqValidateVo.setOwningBranchId(validateNameIdNoVO.getOwningBranchId()+"");
			}
			
			List<String> holidays = new ArrayList<String>();
			List<String> queryHolidays = queryHolidays();
			holidays.add(queryHolidays.size() + "");
			holidays.addAll(queryHolidays);

			/**
			 * 营业部所在的地区信息
			 */
			APPCurrentApplyEntity quertyOrgInfoByID = quertyOrgInfoByID(validateNameIdNoVO.getOwningBranchId());
			BeanUtils.copyProperties(quertyOrgInfoByID, reqValidateVo);

			// 当年的非工作日
			reqValidateVo.setHolidays(holidays);
			// 调用核心获取数据
			FindHisVO findHistoryLoan = findHistoryLoan(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo());
			logger.info("调用核心获取历史借款数据，结果" + gson.toJson(findHistoryLoan));
			BeanUtils.copyProperties(findHistoryLoan, reqValidateVo);

			ThirdPartyInfoEntity thirdPartyInfo = getThirdPartyInfo(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo());
			logger.info("调用征信获取第三方返回结果" + gson.toJson(thirdPartyInfo));
			
			if (thirdPartyInfo != null) {
				SimpleDateFormat thirdPartyInfoSdf=new SimpleDateFormat("yyyy-MM-dd");
				for(int z=0;z<thirdPartyInfo.getThirdOrgReturnDate().size();z++){
					thirdPartyInfo.getThirdOrgReturnDate().set(z, thirdPartyInfoSdf.parse(thirdPartyInfoSdf.format(thirdPartyInfo.getThirdOrgReturnDate().get(z))));
				}
				reqValidateVo.setIfThirdOrgReturn(thirdPartyInfo.getIfThirdOrgReturn());
				reqValidateVo.setThirdOrgReturnDate(thirdPartyInfo.getThirdOrgReturnDate());
			} else {
				reqValidateVo.setIfThirdOrgReturn(getIfThirdOrgReturn());
				reqValidateVo.setThirdOrgReturnDate(getThirdOrgReturnDate());
			}
			
			boolean ifQuery = true;
			
			QueryMainEntity queryApsAll = queryMainByIdNo(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo());
			logger.info("获取老征审借款结果 ：" + gson.toJson(queryApsAll));
			if(queryApsAll != null){
				if (!StringUtils.isEmpty(queryApsAll.getIfHaveValidate()) && queryApsAll.getIfHaveValidate().equals("Y")) {
					logger.info("获取老征审借款的借款状态是：" + gson.toJson(queryApsAll.getPreviousStatus()));
					
					// 查询借款编号在新新系统是否存在
					LoanBaseEntity queryInfoByAppNo = queryInfoByAppNo(queryApsAll.getAppNo());
					logger.info("根据老征审返回借款编号【"+queryApsAll.getAppNo()+"】，去借款库查询数据结果为["+(queryInfoByAppNo != null)+"]");
					
					
					//这边要在去查询一下新系统最新一笔，看下新系统是否存在正在申请的数据，如果新系统有就直接去查新系统数据，queryInfoByAppNo设置成不等于null,不走老系统
					//这边代码可以避免老系统A一笔已经拒绝或者取消的数据，在新系统A有一笔正在申请的单子，然后又在新系统用A在录入一笔，可以录进去的问题
					if(null==queryInfoByAppNo){
						logger.info("判断老系统借款编号在新系统不存在-----------");
						Map<String, Object> newMap = new HashMap<String, Object>();
						newMap.put("IdNo", validateNameIdNoVO.getIdNo());
						newMap.put("loanNo", validateNameIdNoVO.getLoanNo());
						LoanBaseEntity newLoanBase = (LoanBaseEntity) loanBaseService.getBy(newMap, "getLoanBaseByNameIdNo");
						logger.info("拿身份证借款编号去查询新系统是否有在申请中的借款"+gson.toJson(newLoanBase));
						if(null !=newLoanBase){
							logger.info("拿姓名借款编号去查询新系统是否有在申请中的借款  存在------");
							SimpleDateFormat sss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							if(!queryApsAll.getApplyDate().contains("9999")){
								if(newLoanBase.getCreatedTime().compareTo(sss.parse(queryApsAll.getApplyDate()))==1){
									logger.info("新系统最近一笔创建时间大于老系统的最近一笔创建时间---");
									queryInfoByAppNo=new LoanBaseEntity();
								}
							}
							
						}
					}
					
					
					
					
					//获取逻辑，  1，这笔借款在老征审  正在走流程      或 	这笔借款在新系统不存在  的情况下，将这笔借款返回益博睿
					if((queryApsAll.getPreviousStatus().equals("申请中") || 
							queryApsAll.getPreviousStatus().equals("待签约") ||
							queryApsAll.getPreviousStatus().equals("待放款 ")) || queryInfoByAppNo == null){
						logger.info("进来了老政审，------------------------");
						reqValidateVo.setIfHaveValidate(queryApsAll.getIfHaveValidate());
						reqValidateVo.setPreviousStatus(EnumConstants.OldStatus.get(queryApsAll.getPreviousStatus()));// 上笔借款状态
						
						reqValidateVo.setPreviousRtfState("SQLR");// 上笔借款环节
						//转换老政审和新系统的状态
						if(queryApsAll.getPreviousRtfState().equals("K21")){
							reqValidateVo.setPreviousRtfState("FKQR");// 上笔借款环节
						}
						
						// 这边是给拒绝用的
						if(queryApsAll.getPreviousRtfState().equals("F06")){
							reqValidateVo.setPreviousRtfState("XSCS");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("A25")){
							reqValidateVo.setPreviousRtfState("SQLR");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("K25")){
							reqValidateVo.setPreviousRtfState("HTQY");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("K11")){
							reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("K15")){
							reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("M10")){
							reqValidateVo.setPreviousRtfState("HTQY");// 上笔借款环节
						}
						if(queryApsAll.getPreviousRtfState().equals("B08")){
							reqValidateVo.setPreviousRtfState("LRFH");// 上笔借款环节
						}
						// 这边是给拒绝用的
						if(queryApsAll.getPreviousRtfState().equals("H15")){
							reqValidateVo.setPreviousRtfState("CSFP");// 上笔借款环节
						}
						
						
						if(queryApsAll.getPreviousRtfState().equals("K10")){
							reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
						}
						
						
						reqValidateVo.setPreviousOwningBranchId(queryApsAll.getPreviousOwningBranchId() + "");// 上笔借款录单营业部
						
//						if(queryApsAll.getPreviousStatus().equals("取消")){
//							reqValidateVo.setPreviousCancelDate(queryApsAll.getPreviousRefuseDate() == null ? getDefaultDate() : queryApsAll.getPreviousRefuseDate());// 上笔取消时间
//							reqValidateVo.setPreviousRefuseDate(strToDate("99991231", "yyyyMMdd"));// 上笔被拒时间
//						}else if(queryApsAll.getPreviousStatus().equals("拒绝")){
//							reqValidateVo.setPreviousRefuseDate(queryApsAll.getPreviousRefuseDate() == null ? getDefaultDate() : queryApsAll.getPreviousRefuseDate());// 上笔被拒时间
//							reqValidateVo.setPreviousCancelDate(strToDate("99991231", "yyyyMMdd"));
//						}else{
//							
//						}
						
						
						
						
						List<Map<String,Object>> listMapRefuse=loanBaseService.findDataByIdNoRefuse(validateNameIdNoVO.getIdNo());//查询拒绝
						logger.info("查询新系统最近一次拒绝的时间"+gson.toJson(listMapRefuse));
						List<Map<String,Object>> listMapCancel=loanBaseService.findDataByIdNoCancel(validateNameIdNoVO.getIdNo());//查询取消
						logger.info("查询新系统最近一次取消的时间"+gson.toJson(listMapCancel));
						SimpleDateFormat seq=new SimpleDateFormat("yyyy-MM-dd");
						
						if(null != queryApsAll&&!queryApsAll.getPreviousRefuseDate().toString().contains("9999")){//判断拒绝
							logger.info("判断拒绝老政审有值的进来了-----------------------");
							if(null!=listMapRefuse&&listMapRefuse.size()>0){
								logger.info("判断拒绝新系统有值的进来了-----------------------");
								Date refuseOld=queryApsAll.getPreviousRefuseDate();
								Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
								if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
									logger.info("老政审拒绝时间大于或者等于新系统-----------------------");
									reqValidateVo.setPreviousRefuseDate(refuseOld);
									reqValidateVo.setLimitDays(queryApsAll.getLimitDays()); 
								}else{
									logger.info("老政审拒绝时间小于新系统-----------------------");
									reqValidateVo.setPreviousRefuseDate(refuseNew);
									LoanBaseEntity l=new LoanBaseEntity();
									l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
									Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
									
									logger.info("查询拒绝对应的日志表和原因表获取限制天数------"+gson.toJson(queryLimitDays));
									if(queryLimitDays != null){
										reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
									} else {
										//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
										reqValidateVo.setLimitDays(0);
										
									}
								}
								
							}else{
								logger.info("判断拒绝新系统没值的进来了-----------------------");
								reqValidateVo.setPreviousRefuseDate(queryApsAll.getPreviousRefuseDate());
								reqValidateVo.setLimitDays(queryApsAll.getLimitDays());
							}
							
							
						}else{
							logger.info("判断拒绝老政审没值的进来了=================");
							if(null!=listMapRefuse&&listMapRefuse.size()>0){
								logger.info("判断拒绝老政审没值,新系统有值的进来了==============");
								Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
								reqValidateVo.setPreviousRefuseDate(refuseNew);
								
								LoanBaseEntity l=new LoanBaseEntity();
								l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
								Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
								
								logger.info("判断拒绝老政审没值,新系统有值   拒绝对应的日志表和原因表获取限制天数  =============="+gson.toJson(queryLimitDays));
								if(queryLimitDays != null){
									reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
								} else {
									//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
									reqValidateVo.setLimitDays(0);
									
								}
							}else{
								logger.info("判断拒绝新系统没值老系统也没值的进来了=================");
								reqValidateVo.setPreviousRefuseDate(strToDate("99991231", "yyyyMMdd"));
								reqValidateVo.setLimitDays(0);
							}
							
						}
						
						
						
						
						if(null != queryApsAll&&!queryApsAll.getPreviousCancelDate().toString().contains("9999")){//判断取消
							logger.info("判断取消老政审有值的进来了-----------------------");
							if(null!=listMapCancel&&listMapCancel.size()>0){
								logger.info("判断取消新系统有值的进来了-----------------------");
								Date refuseOld=queryApsAll.getPreviousCancelDate();
								Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
								if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
									logger.info("老政审取消时间大于或者等于新系统-----------------------");
									reqValidateVo.setPreviousCancelDate(refuseOld);
								}else{
									logger.info("老政审取消时间小于新系统-----------------------");
									reqValidateVo.setPreviousCancelDate(refuseNew);
									
								}
								
							}else{
								logger.info("判断取消新系统没值的进来了-----------------------");
								
								reqValidateVo.setPreviousCancelDate(queryApsAll.getPreviousCancelDate());
								
							}
							
							
						}else{
							logger.info("判断取消老政审没值的进来了=================");
							if(null!=listMapCancel&&listMapCancel.size()>0){
								logger.info("判断取消老政审没值,新系统有值的进来了==============");
								Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
								reqValidateVo.setPreviousCancelDate(refuseNew);
								
							}else{
								logger.info("判断取消新系统没值老系统也没值的进来了=================");
								reqValidateVo.setPreviousCancelDate(strToDate("99991231", "yyyyMMdd"));
							}
							
						}
						
						
						
						
						
						
						
						//reqValidateVo.setPreviousRefuseDate(queryApsAll.getPreviousRefuseDate() == null ? getDefaultDate() : queryApsAll.getPreviousRefuseDate());// 上笔被拒时间
						reqValidateVo.setProtectDays(queryApsAll.getProtectDays());// 查询保护天数
						reqValidateVo.setApplyType(queryApsAll.getApplyType()); // 申请类型
						reqValidateVo.setProductCode(queryApsAll.getProductCode()); // 申请产品
						reqValidateVo.setApplyDate(queryApsAll.getApplyDate() == null ? getDefaultDate() : DateUtil.strToDateTimeDay(queryApsAll.getApplyDate())); // 申请创建时间
						reqValidateVo.setAppApplyInput(queryApsAll.getAppApplyInput());
						ifQuery = false;
					}
				}
			}
			
			
			
			if(ifQuery){
				logger.info("老政审没有对应数据查询借款本地__________________");
				// 1 根据name和idNo查询上笔借款记录
				Map<String, Object> paramMap = new HashMap<String, Object>();
				//paramMap.put("name", validateNameIdNoVO.getName());
				paramMap.put("IdNo", validateNameIdNoVO.getIdNo());
				paramMap.put("loanNo", validateNameIdNoVO.getLoanNo());
				LoanBaseEntity loanBase = (LoanBaseEntity) loanBaseService.getBy(paramMap, "getLoanBaseByNameIdNo");
				logger.info("获取借款新系统上一笔数据：" + gson.toJson(loanBase));
				if (loanBase != null) {
					reqValidateVo.setIfHaveValidate("Y");
					reqValidateVo.setPreviousStatus("REFUSE".equals(loanBase.getStatus())?"REJECT":loanBase.getStatus());// 上笔借款状态
					reqValidateVo.setPreviousRtfState(loanBase.getRtfState());// 上笔借款环节
					
					//查询上一笔单子对应的借款审批表的创建时间赋值给上一笔提交信审时间
					logger.info("查询上一笔单子对应的借款审批表的创建时间赋值给上一笔提交信审时间");
					Map<String, Object> paramMapAudit = new HashMap<String, Object>();
					paramMapAudit.put("loanNo", loanBase.getLoanNo());
					LoanAuditEntity loanAuditSetPrivodeFirstCommitDate = iLoanAuditService.getBy(paramMapAudit);
					logger.info("查询上一笔单子对应的借款审批表"+gson.toJson(loanAuditSetPrivodeFirstCommitDate));
					if(null!=loanAuditSetPrivodeFirstCommitDate){
						SimpleDateFormat sssCretime=new SimpleDateFormat("yyyy-MM-dd");
						reqValidateVo.setPreviousFirstCommitDate(sssCretime.parse(sssCretime.format(loanAuditSetPrivodeFirstCommitDate.getCreated_time())));
					}
					
					
					if("NORMAL".equals(loanBase.getStatus())){
						//改成吊核心去获取上一笔单子状态  (loanBase查询出来的loan_no就是上一笔的)
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("loanNo", loanBase.getLoanNo());
						logger.info("上一笔数据状态正常并且存在查询核心判断该笔借款的状态");
						HttpResponse queryLoan = coreHttpService.queryLoan(map);	
						logger.info("上一笔数据状态正常并且存在查询核心判断该笔借款的状态"+gson.toJson(queryLoan));
						Map<String, Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
						logger.info("上一笔数据状态正常并且存在查询核心判断该笔借款的状态"+gson.toJson(contentMap));
						if (contentMap.get("code").equals("000000")) {
							Map<String, String> resQueryLoanVo = (Map<String, String>) contentMap.get("loan");
							if (resQueryLoanVo != null) {
								reqValidateVo.setPreviousStatus(EnumConstants.OldStatus.get(resQueryLoanVo.get("loanState")));// 上笔借款状态
							}
						}
					}
					
					
					
					
					reqValidateVo.setPreviousOwningBranchId(loanBase.getOwningBranchId() + "");// 上笔借款录单营业部
					reqValidateVo.setProtectDays(queryProtectDays());// 查询保护天数
					
					
					// 判断上笔借款是否被拒
//					if (loanBase.getStatus() != null && loanBase.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue())) {
//						Map<String, String> queryLimitDays = queryLimitRefuseDays(loanBase);
//						logger.info("查询对应的日志表和原因表获取限制天数和上一笔拒绝时间"+gson.toJson(queryLimitDays));
//						reqValidateVo.setPreviousCancelDate(getDefaultDate());
//						if(queryLimitDays != null){
//							reqValidateVo.setPreviousRefuseDate(strToDate(queryLimitDays.get("previousRefuseDate"), DateUtil.default_pattern_day));
//							reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
//						} else {
//							reqValidateVo.setPreviousRefuseDate(getDefaultDate());
//							reqValidateVo.setLimitDays(0);
//							
//						}
//					} else if (loanBase.getStatus() != null && loanBase.getStatus().equals(EnumConstants.LoanStatus.CANCEL.getValue())) {
//						Map<String, String> queryLimitDays = queryLimitCancelDays(loanBase);
//						reqValidateVo.setPreviousRefuseDate(getDefaultDate());
//						reqValidateVo.setPreviousCancelDate(strToDate(queryLimitDays.get("previousRefuseDate"), DateUtil.default_pattern_day));
//						reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
//					}
					//改成获取最近一次拒绝和取消时间还有限制天数
					Map<String,Object> map=queryOldRefuseAndCancelAndLimitDaysData(validateNameIdNoVO.getIdNo());//查询老政审拒绝和取消和限制天数
					
					List<Map<String,Object>> listMapRefuse=loanBaseService.findDataByIdNoRefuse(validateNameIdNoVO.getIdNo());//查询拒绝
					logger.info("查询新系统最近一次拒绝的时间"+gson.toJson(listMapRefuse));
					List<Map<String,Object>> listMapCancel=loanBaseService.findDataByIdNoCancel(validateNameIdNoVO.getIdNo());//查询取消
					logger.info("查询新系统最近一次取消的时间"+gson.toJson(listMapCancel));
					SimpleDateFormat seq=new SimpleDateFormat("yyyy-MM-dd");
					
					if(null != map&&!map.get("previousRefuseDate").equals("")){//判断拒绝
						logger.info("判断拒绝老政审有值的进来了-----------------------");
						if(null!=listMapRefuse&&listMapRefuse.size()>0){
							logger.info("判断拒绝新系统有值的进来了-----------------------");
							Date refuseOld=seq.parse(map.get("previousRefuseDate").toString());
							Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
							if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
								logger.info("老政审拒绝时间大于或者等于新系统-----------------------");
								reqValidateVo.setPreviousRefuseDate(refuseOld);
								String limitDays=(String) map.get("limitDays");
								reqValidateVo.setLimitDays(Integer.parseInt(limitDays));
							}else{
								logger.info("老政审拒绝时间小于新系统-----------------------");
								reqValidateVo.setPreviousRefuseDate(refuseNew);
								LoanBaseEntity l=new LoanBaseEntity();
								l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
								Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
								
								logger.info("查询拒绝对应的日志表和原因表获取限制天数------"+gson.toJson(queryLimitDays));
								if(queryLimitDays != null){
									reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
								} else {
									//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
									reqValidateVo.setLimitDays(0);
									
								}
							}
							
						}else{
							logger.info("判断拒绝新系统没值的进来了-----------------------");
							Date refuseOld=seq.parse(map.get("previousRefuseDate").toString());
							reqValidateVo.setPreviousRefuseDate(refuseOld);
							String limitDays=(String) map.get("limitDays");
							reqValidateVo.setLimitDays(Integer.parseInt(limitDays));
						}
						
						
					}else{
						logger.info("判断拒绝老政审没值的进来了=================");
						if(null!=listMapRefuse&&listMapRefuse.size()>0){
							logger.info("判断拒绝老政审没值,新系统有值的进来了==============");
							Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
							reqValidateVo.setPreviousRefuseDate(refuseNew);
							
							LoanBaseEntity l=new LoanBaseEntity();
							l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
							Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
							
							logger.info("判断拒绝老政审没值,新系统有值   拒绝对应的日志表和原因表获取限制天数  =============="+gson.toJson(queryLimitDays));
							if(queryLimitDays != null){
								reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
							} else {
								//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
								reqValidateVo.setLimitDays(0);
								
							}
						}else{
							logger.info("判断拒绝新系统没值老系统也没值的进来了=================");
							reqValidateVo.setPreviousRefuseDate(strToDate("99991231", "yyyyMMdd"));
							reqValidateVo.setLimitDays(0);
						}
						
					}
					
					
					
					
					if(null != map&&!map.get("previousCancelDate").equals("")){//判断取消
						logger.info("判断取消老政审有值的进来了-----------------------");
						if(null!=listMapCancel&&listMapCancel.size()>0){
							logger.info("判断取消新系统有值的进来了-----------------------");
							Date refuseOld=seq.parse(map.get("previousCancelDate").toString());
							Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
							if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
								logger.info("老政审取消时间大于或者等于新系统-----------------------");
								reqValidateVo.setPreviousCancelDate(refuseOld);
							}else{
								logger.info("老政审取消时间小于新系统-----------------------");
								reqValidateVo.setPreviousCancelDate(refuseNew);
								
							}
							
						}else{
							logger.info("判断取消新系统没值的进来了-----------------------");
							Date refuseOld=seq.parse(map.get("previousCancelDate").toString());
							reqValidateVo.setPreviousCancelDate(refuseOld);
							
						}
						
						
					}else{
						logger.info("判断取消老政审没值的进来了=================");
						if(null!=listMapCancel&&listMapCancel.size()>0){
							logger.info("判断取消老政审没值,新系统有值的进来了==============");
							Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
							reqValidateVo.setPreviousCancelDate(refuseNew);
							
						}else{
							logger.info("判断取消新系统没值老系统也没值的进来了=================");
							reqValidateVo.setPreviousCancelDate(strToDate("99991231", "yyyyMMdd"));
						}
						
					}
					
					
					
				} else {
					// 自己数据为空，去老征审查询数据
					QueryMainEntity queryMainByIdNo = queryMainByIdNo(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo());
					logger.info("获取老征审借款结果：" + gson.toJson(queryMainByIdNo));
					if (queryMainByIdNo != null) {
						if (!StringUtils.isEmpty(queryMainByIdNo.getIfHaveValidate()) && queryMainByIdNo.getIfHaveValidate().equals("Y")) {
							reqValidateVo.setIfHaveValidate(queryMainByIdNo.getIfHaveValidate());
							reqValidateVo.setPreviousStatus(EnumConstants.OldStatus.get(queryMainByIdNo.getPreviousStatus()));// 上笔借款状态
							
							reqValidateVo.setPreviousRtfState("SQLR");// 上笔借款环节
							
							//转换老政审和新系统的状态
							if(queryApsAll.getPreviousRtfState().equals("K21")){
								reqValidateVo.setPreviousRtfState("FKQR");// 上笔借款环节
							}
							//这边是给拒绝的
							if(queryApsAll.getPreviousRtfState().equals("F06")){
								reqValidateVo.setPreviousRtfState("XSCS");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("A25")){
								reqValidateVo.setPreviousRtfState("SQLR");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("K25")){
								reqValidateVo.setPreviousRtfState("HTQY");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("K11")){
								reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("K15")){
								reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("M10")){
								reqValidateVo.setPreviousRtfState("HTQY");// 上笔借款环节
							}
							if(queryApsAll.getPreviousRtfState().equals("B08")){
								reqValidateVo.setPreviousRtfState("LRFH");// 上笔借款环节
							}
							//这边是给拒绝的
							if(queryApsAll.getPreviousRtfState().equals("H15")){
								reqValidateVo.setPreviousRtfState("CSFP");// 上笔借款环节
							}
							
							
							if(queryApsAll.getPreviousRtfState().equals("K10")){
								reqValidateVo.setPreviousRtfState("XSZS");// 上笔借款环节
							}
							
							reqValidateVo.setPreviousOwningBranchId(queryMainByIdNo.getPreviousOwningBranchId() + "");// 上笔借款录单营业部
							
//							if(queryApsAll.getPreviousStatus().equals("取消")){
//								reqValidateVo.setPreviousCancelDate(queryApsAll.getPreviousRefuseDate() == null ? getDefaultDate() : queryApsAll.getPreviousRefuseDate());// 上笔取消时间
//								reqValidateVo.setPreviousRefuseDate(strToDate("99991231", "yyyyMMdd"));// 上笔被拒时间
//							}else if(queryApsAll.getPreviousStatus().equals("拒绝")){
//								reqValidateVo.setPreviousRefuseDate(queryApsAll.getPreviousRefuseDate() == null ? getDefaultDate() : queryApsAll.getPreviousRefuseDate());// 上笔被拒时间
//								reqValidateVo.setPreviousCancelDate(strToDate("99991231", "yyyyMMdd"));
//							}else{
//								
//							}
							
							
							List<Map<String,Object>> listMapRefuse=loanBaseService.findDataByIdNoRefuse(validateNameIdNoVO.getIdNo());//查询拒绝
							logger.info("查询新系统最近一次拒绝的时间"+gson.toJson(listMapRefuse));
							List<Map<String,Object>> listMapCancel=loanBaseService.findDataByIdNoCancel(validateNameIdNoVO.getIdNo());//查询取消
							logger.info("查询新系统最近一次取消的时间"+gson.toJson(listMapCancel));
							SimpleDateFormat seq=new SimpleDateFormat("yyyy-MM-dd");
							
							if(null != queryApsAll&&!queryApsAll.getPreviousRefuseDate().toString().contains("9999")){//判断拒绝
								logger.info("判断拒绝老政审有值的进来了-----------------------");
								if(null!=listMapRefuse&&listMapRefuse.size()>0){
									logger.info("判断拒绝新系统有值的进来了-----------------------");
									Date refuseOld=queryApsAll.getPreviousRefuseDate();
									Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
									if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
										logger.info("老政审拒绝时间大于或者等于新系统-----------------------");
										reqValidateVo.setPreviousRefuseDate(refuseOld);
										reqValidateVo.setLimitDays(queryApsAll.getLimitDays()); 
									}else{
										logger.info("老政审拒绝时间小于新系统-----------------------");
										reqValidateVo.setPreviousRefuseDate(refuseNew);
										LoanBaseEntity l=new LoanBaseEntity();
										l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
										Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
										
										logger.info("查询拒绝对应的日志表和原因表获取限制天数------"+gson.toJson(queryLimitDays));
										if(queryLimitDays != null){
											reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
										} else {
											//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
											reqValidateVo.setLimitDays(0);
											
										}
									}
									
								}else{
									logger.info("判断拒绝新系统没值的进来了-----------------------");
									reqValidateVo.setPreviousRefuseDate(queryApsAll.getPreviousRefuseDate());
									reqValidateVo.setLimitDays(queryApsAll.getLimitDays());
								}
								
								
							}else{
								logger.info("判断拒绝老政审没值的进来了=================");
								if(null!=listMapRefuse&&listMapRefuse.size()>0){
									logger.info("判断拒绝老政审没值,新系统有值的进来了==============");
									Date refuseNew=seq.parse(listMapRefuse.get(0).get("OPERATION_TIME").toString());
									reqValidateVo.setPreviousRefuseDate(refuseNew);
									
									LoanBaseEntity l=new LoanBaseEntity();
									l.setLoanNo(listMapRefuse.get(0).get("loan_no").toString());
									Map<String, String> queryLimitDays=queryLimitRefuseDays(l);
									
									logger.info("判断拒绝老政审没值,新系统有值   拒绝对应的日志表和原因表获取限制天数  =============="+gson.toJson(queryLimitDays));
									if(queryLimitDays != null){
										reqValidateVo.setLimitDays(queryLimitDays.get("evel") == null ? null : Integer.parseInt(queryLimitDays.get("evel")));
									} else {
										//reqValidateVo.setPreviousRefuseDate(getDefaultDate());
										reqValidateVo.setLimitDays(0);
										
									}
								}else{
									logger.info("判断拒绝新系统没值老系统也没值的进来了=================");
									reqValidateVo.setPreviousRefuseDate(strToDate("99991231", "yyyyMMdd"));
									reqValidateVo.setLimitDays(0);
								}
								
							}
							
							
							
							
							if(null != queryApsAll&&!queryApsAll.getPreviousCancelDate().toString().contains("9999")){//判断取消
								logger.info("判断取消老政审有值的进来了-----------------------");
								if(null!=listMapCancel&&listMapCancel.size()>0){
									logger.info("判断取消新系统有值的进来了-----------------------");
									Date refuseOld=queryApsAll.getPreviousCancelDate();
									Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
									if(refuseOld.compareTo(refuseNew)==1||refuseOld.compareTo(refuseNew)==0){
										logger.info("老政审取消时间大于或者等于新系统-----------------------");
										reqValidateVo.setPreviousCancelDate(refuseOld);
									}else{
										logger.info("老政审取消时间小于新系统-----------------------");
										reqValidateVo.setPreviousCancelDate(refuseNew);
										
									}
									
								}else{
									logger.info("判断取消新系统没值的进来了-----------------------");
									
									reqValidateVo.setPreviousCancelDate(queryApsAll.getPreviousCancelDate());
									
								}
								
								
							}else{
								logger.info("判断取消老政审没值的进来了=================");
								if(null!=listMapCancel&&listMapCancel.size()>0){
									logger.info("判断取消老政审没值,新系统有值的进来了==============");
									Date refuseNew=seq.parse(listMapCancel.get(0).get("OPERATION_TIME").toString());
									reqValidateVo.setPreviousCancelDate(refuseNew);
									
								}else{
									logger.info("判断取消新系统没值老系统也没值的进来了=================");
									reqValidateVo.setPreviousCancelDate(strToDate("99991231", "yyyyMMdd"));
								}
								
							}
							
							
							
							
							
							//reqValidateVo.setPreviousRefuseDate(queryMainByIdNo.getPreviousRefuseDate() == null ? getDefaultDate() : queryMainByIdNo.getPreviousRefuseDate());// 上笔被拒时间
							reqValidateVo.setProtectDays(queryMainByIdNo.getProtectDays());// 查询保护天数
							reqValidateVo.setApplyType(queryMainByIdNo.getApplyType()); // 申请类型
							reqValidateVo.setProductCode(queryMainByIdNo.getProductCode()); // 申请产品
							reqValidateVo.setApplyDate(queryMainByIdNo.getApplyDate() == null ? getDefaultDate() : DateUtil.strToDateTimeDay(queryMainByIdNo.getApplyDate())); // 申请创建时间
							reqValidateVo.setAppApplyInput(queryMainByIdNo.getAppApplyInput());
						}
					}
				}
			}
			
			//返回这里借款省市区信息
				APPPersonVauleAddresEntity queryPersonValue = queryPersonValue(validateNameIdNoVO.getLoanNo());
				BeanUtils.copyProperties(queryPersonValue, reqValidateVo);
			
			
			if (!StringUtils.isEmpty(validateNameIdNoVO.getLoanNo())) {
				SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
				APPCurrentApplyEntity queryLoanBaseValue = queryLoanBaseValue(validateNameIdNoVO.getLoanNo());
				if (queryLoanBaseValue != null) {
					if(null!=queryLoanBaseValue.getApplyDate()){
						queryLoanBaseValue.setApplyDate(sdf.parse(sdf.format(queryLoanBaseValue.getApplyDate())));
					}
					if(null!=queryLoanBaseValue.getFirstInModifyDate()){
						queryLoanBaseValue.setFirstInModifyDate(sdf.parse(sdf.format(queryLoanBaseValue.getFirstInModifyDate())));
					}
					BeanUtils.copyProperties(queryLoanBaseValue, reqValidateVo);
				}
				
				// 查看审批表
				Map<String, Object> paramMap1 = new HashMap<String, Object>();
				paramMap1.put("loanNo", validateNameIdNoVO.getLoanNo());
				LoanAuditEntity loanAuditEntitySelect = iLoanAuditService.getBy(paramMap1);
				if(loanAuditEntitySelect != null){
					reqValidateVo.setFirstCommitDate(sdf.parse(sdf.format(loanAuditEntitySelect.getCreated_time())));
				}
				
			} 
			if(null==reqValidateVo.getFirstInModifyDate()){
				reqValidateVo.setFirstInModifyDate(strToDate("99991231", "yyyyMMdd"));
			}
			if(null==reqValidateVo.getAppServiceClaimDate()){
				reqValidateVo.setAppServiceClaimDate(strToDate("99991231", "yyyyMMdd"));
			}

			// 申请类型
			reqValidateVo.setApplyType(getApplyTypeYBR(validateNameIdNoVO.getIdNo()));
			// 查询灰黑名单
			reqValidateVo.setIfBlacklist(getBlackList(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo()));
			
			
			//是否是直通车
//			if(!StringUtils.isEmpty(validateNameIdNoVO.getIfThroughTrain())
//					&& validateNameIdNoVO.getIfThroughTrain().equals("1")){
				//是否在已分派名单
				reqValidateVo.setIfDispatchList(getSalesman2(validateNameIdNoVO.getName(), validateNameIdNoVO.getIdNo()));
				
				//是否是优质客户名单
				reqValidateVo.setIfHighQualityCustomers(personValidate(validateNameIdNoVO.getIdNo()));
			//}

			
			//如果有传APPstatus过来最后默认返回rtfstatus的值是APPstatus
		     if (!StringUtils.isEmpty(validateNameIdNoVO.getAppStatus())) {
				reqValidateVo.setRtfState(validateNameIdNoVO.getAppStatus());
			 }

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取数据异常：" + e.getMessage());

			res.setData(reqValidateVo);
			return res;
		}
		res.setData(reqValidateVo);
		return res;
	}
	
	/**
	 * 查询借款编号是否在借款库存在
	 * @param appNo
	 */
	public LoanBaseEntity queryInfoByAppNo(String appNo){
		if(StringUtils.isEmpty(appNo)){
			return null;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", appNo);

		return loanBaseService.getBy(paramMap);
	}
	
	public APPPersonVauleAddresEntity queryPersonValue(String loanNo){
		if(!StringUtils.isEmpty(loanNo)){
			// 查看审批表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", loanNo);
			APPPersonVauleAddresEntity loan = appCarInfoService.queryPersonVauleAddresEntity(paramMap);
			if(loan != null){
				if(StringUtils.isEmpty(loan.getPlateNum())){
					loan.setPlateNum("");
				}
				return loan;
			}
		}
		APPPersonVauleAddresEntity loan = new APPPersonVauleAddresEntity();
		loan.setPlateNum("");
		return loan;
	}

	/**
	 * 初始化第三方结果
	 * 
	 * @return
	 */
	public List<String> getIfThirdOrgReturn() {
		List<String> listOrgReturn = new ArrayList<String>();
		listOrgReturn.add("N");
		listOrgReturn.add("N");
		listOrgReturn.add("N");
		listOrgReturn.add("N");
		listOrgReturn.add("N");
		return listOrgReturn;
	}

	/**
	 * 初始化第三方结果时间
	 * 
	 * @return
	 */
	public List<Date> getThirdOrgReturnDate() {
		List<Date> listReturnDate = new ArrayList<Date>();
		listReturnDate.add(getDefaultDate());
		listReturnDate.add(getDefaultDate());
		listReturnDate.add(getDefaultDate());
		listReturnDate.add(getDefaultDate());
		listReturnDate.add(getDefaultDate());
		return listReturnDate;
	}

	/**
	 * 查询保护天数
	 * 
	 * @return
	 */
	public Integer queryProtectDays() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", EnumConstants.LOAN_PROTECT_DAY);
		BMSTmParameter queryByCode = (BMSTmParameter) tmParameterService.getBy(paramMap, "queryByCode");
		if (queryByCode == null) {
			return Integer.parseInt(EnumConstants.LOAN_PROTECT_DAY_VALUE);
		} else {
			return Integer.parseInt(queryByCode.getParameterValue());
		}
	}

	/**
	 * 查询拒绝限制天数
	 * 
	 * @param loanBase
	 * @return
	 */
	public Map<String, String> queryLimitRefuseDays(LoanBaseEntity loanBase) {
		Map<String, String> res = new HashMap<String, String>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanBase.getLoanNo());
		paramMap.put("status", EnumConstants.LoanStatus.REFUSE.getValue());
		paramMap.put("orderBy", "orderBy");
		BMSSysLoanLog by = sysLoanLogService.getBy(paramMap);
		if (by != null) {
			res.put("previousRefuseDate", DateUtil.defaultFormatDate(by.getOperationTime()));

			if (!StringUtils.isEmpty(by.getTwoLevleReasonsCode())) {
				paramMap.put("code", by.getTwoLevleReasonsCode());
				BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
				if (evel != null) {
					res.put("evel", evel.getCanRequestDays() + "");
				}
			} else {
				if (!StringUtils.isEmpty(by.getFirstLevleReasonsCode())) {
					paramMap.put("code", by.getFirstLevleReasonsCode());
					
					
					//加了一个这个
//					if(by.getOperatorCode().equals("bms")||by.getOperatorCode().contains("系统")){
//						paramMap.put("reasonType", "2");
//					}
				
					
					
					
					BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
					if (evel != null) {
						res.put("evel", evel.getCanRequestDays() + "");
					}
				}
			}
			return res;
		}
		return null;
	}

	/**
	 * 查询取消限制天数
	 * 
	 * @param loanBase
	 * @return
	 */
	public Map<String, String> queryLimitCancelDays(LoanBaseEntity loanBase) {
		Map<String, String> res = new HashMap<String, String>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanBase.getLoanNo());
		paramMap.put("status", EnumConstants.LoanStatus.CANCEL.getValue());
		BMSSysLoanLog by = sysLoanLogService.getBy(paramMap);
		if (by != null) {
			res.put("previousRefuseDate", DateUtil.defaultFormatDate(by.getOperationTime()));

			if (!StringUtils.isEmpty(by.getTwoLevleReasonsCode())) {
				paramMap.put("code", by.getTwoLevleReasonsCode());
				BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
				if (evel != null) {
					res.put("evel", evel.getCanRequestDays() + "");
				}
			} else {
				if (!StringUtils.isEmpty(by.getFirstLevleReasonsCode())) {
					paramMap.put("code", by.getFirstLevleReasonsCode());
					BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
					if (evel != null) {
						res.put("evel", evel.getCanRequestDays() + "");
					}
				}
			}
			return res;
		}
		return null;
	}

	public String getBlackList(String name, String idNo) {
		try {
			// 灰黑名单
			BlackListQueryReqVO queryReqVo = new BlackListQueryReqVO();
			queryReqVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			queryReqVo.setIdNo(idNo);
			//queryReqVo.setName(name);
			PageResponse<BlackListResVO> blackList = blackListExecuter.getBlackList(queryReqVo);
			if (blackList.isSuccess()) {
				// 当前申请 是否匹配黑名单 Y匹配 N不匹配
				if (blackList.getRecords() != null && blackList.getRecords().size() > 0) {
					return "Y";
				} else {
					return "N";
				}
			} 
		} catch (Exception e) {
			logger.info("获取灰黑名单异常:"+e.getMessage());
			return "N";
		}
		return "N";
	}

	
	/**
	 * 查询借款信息
	 * 
	 * @param loanNo
	 * @throws ParseException 
	 */
	public APPCurrentApplyEntity queryLoanBaseValue(String loanNo) throws ParseException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		//LoanBaseEntity by = loanBaseService.getBy(paramMap);
		LoanBaseEntity by = (LoanBaseEntity) loanBaseService.getBy(paramMap,"listByLoanBase");
		logger.info("根据LOAN_NO查询借款主表信息用于判断FirstInModifyDate值:" + gson.toJson(by));
		if (by == null) {
			return null;
		} else {
			APPCurrentApplyEntity appCurrent = new APPCurrentApplyEntity();
			appCurrent.setApplyType(by.getApplyType());// 当前申请 申请类型
			appCurrent.setApplyDate(by.getApplyDate());// 当前申请 申请创建时间
			appCurrent.setAppApplyInput(by.getAppInputFlag() != null ? "Y" : "N");// 当前申请
																					// 是否APP进件
			appCurrent.setAppServiceClaimDate(by.getAppApplyDate());//客服认领时间
			appCurrent.setRtfState(by.getRtfState());// 业务环节
			//这边的取值，如果是APP客户经理提交取的是是APP客户经理提交的时间.需查日志表，如果是PC端客服取得是APPLY_DATE
			BMSSysLoanLog sys=(BMSSysLoanLog) sysLoanLogService.getBy(paramMap, "queryCommitOperationTime");
			logger.info("根据LOAN_NO查询借款日志表查询APP客户经理提交时间" + gson.toJson(sys));
			if(null==by.getAppInputFlag()||by.getAppInputFlag().equals("")){
				appCurrent.setFirstInModifyDate(by.getApplyDate());
			}else{
				if(null!=sys){
					appCurrent.setFirstInModifyDate(sys.getOperationTime());
				}else{
					appCurrent.setFirstInModifyDate(strToDate("99991231", "yyyyMMdd"));
				}
			}
				
			
			// 进件营业部详情
			ReqOrganizationVO organizationVO = new ReqOrganizationVO();
			organizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			organizationVO.setId(by.getOwningBranchId());
			Response<ResOrganizationInfo> orgInfoByID = iOrganizationExecuter.getOrgInfoByID(organizationVO);
			if (orgInfoByID.isSuccess()) {
				ResOrganizationInfo res = orgInfoByID.getData();
				appCurrent.setOwningBranchCity(res.getCityId() + "");// 当前申请
																		// 进件营业部城市
				appCurrent.setOwningBranchDivision(res.getDivisionId() + "");// 当前申请
																				// 进件营业部分部
				appCurrent.setOwningBranchArea(res.getAreaId() + "");// 当前申请
																		// 进件营业部区域
			}
			return appCurrent;
		}
	}

	public APPCurrentApplyEntity quertyOrgInfoByID(Long owningBranchId) {
		APPCurrentApplyEntity appCurrent = new APPCurrentApplyEntity();
		// 进件营业部详情
		ReqOrganizationVO organizationVO = new ReqOrganizationVO();
		organizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		organizationVO.setId(owningBranchId);
		Response<ResOrganizationInfo> orgInfoByID = iOrganizationExecuter.getOrgInfoByID(organizationVO);
		if (orgInfoByID.isSuccess()) {
			ResOrganizationInfo res = orgInfoByID.getData();
			appCurrent.setOwningBranchCity(res.getCityId() + "");// 当前申请
																	// 进件营业部城市
			appCurrent.setOwningBranchDivision(res.getDivisionId() + "");// 当前申请
																			// 进件营业部分部
			appCurrent.setOwningBranchArea(res.getAreaId() + "");// 当前申请
																	// 进件营业部区域
		}

		appCurrent.setAppApplyInput("N");
		appCurrent.setRtfState("SQLR");
		return appCurrent;
	}

	/**
	 * 益博睿规则命中记录
	 */
	@Override
	public Response<String> saveValidateRecord(ValidateRecordVO validateRecordVO) {
		Response<String> res = new Response<String>();

		if (validateRecordVO.getRecordvo() == null || validateRecordVO.getRecordvo().size() == 0) {
			return res;
		}
		List<BmsRuleLogEntity> listRuleVo = new ArrayList<BmsRuleLogEntity>();

		Date date = new Date();
		for (RecordVO recordvo : validateRecordVO.getRecordvo()) {
			BmsRuleLogEntity bmsVo = new BmsRuleLogEntity();
			BeanUtils.copyProperties(recordvo, bmsVo);
			bmsVo.setOperationTime(date);
			listRuleVo.add(bmsVo);
		}
		iBaseRuleLogService.insetBaseRuleLogList(listRuleVo);
		return res;
	}

	/**
	 * 查询用户的历史借款信息
	 * 
	 * @param name
	 * @param idNo
	 */
	public FindHisVO findHistoryLoan(String name, String idNo) {
		FindHisVO resFindHis = new FindHisVO();

		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idNo)) {
			return resFindHis;
		}

		Map<String, Object> httpParamMap = new HashMap<String, Object>();
		httpParamMap.put("name", name);
		httpParamMap.put("idnum", idNo);
		HttpResponse findHistoryLoan;
		try {
			findHistoryLoan = coreHttpService.findHistoryLoan(httpParamMap);
			logger.info("" + gson.toJson(findHistoryLoan));
			Map<String, Object> contentMap = JsonUtils.decode(findHistoryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("message").equals("该用户不在系统中")) {
					logger.info("查询历史数据返回  该用户不在系统中");
					List<Integer> repaymentTerm = new ArrayList<Integer>();
					repaymentTerm.add(0);
					repaymentTerm.add(0);
					repaymentTerm.add(0);
					resFindHis.setRepaymentTerm(repaymentTerm);// 借款期数
					return resFindHis;
				} else {
					FindHistoryLoanVO decode = JsonUtils.decode(gson.toJson(contentMap.get("infos")), FindHistoryLoanVO.class);
					logger.info("查询历史数据返回 :" + gson.toJson(decode));
					resFindHis.setIfHaveHistoryApply("Y");// 是否有借款数据
					resFindHis.setIfHaveBreaks(decode.getIfHaveBreaks());// 上笔借款是否减免
					resFindHis.setRepaymentTerm(Arrays.asList(decode.getBorrowingTerm()));// 借款期数

					List<Date> realityRepaymentDate = new ArrayList<Date>();// 实际还款日期
					List<Date> shouldRepaymentDate = new ArrayList<Date>();// 应还款日期
					List<Integer> repaymentStatue = new ArrayList<Integer>();// 还款状态
					if (decode.getLoanList() != null && decode.getLoanList().size() > 0) {
						for (RepayListVo repayListVo : decode.getLoanList()) {
							if (repayListVo.getRepayList() != null && repayListVo.getRepayList().size() > 0) {
								for (RepayVo repayVo : repayListVo.getRepayList()) {
									realityRepaymentDate.add(repayVo.getRealityRepaymentDate() == null ? getDefaultDate() : strToDate(repayVo.getRealityRepaymentDate().replace("-", ""), DateUtil.pattern_day));
									shouldRepaymentDate.add(repayVo.getShouldRepaymentDate() == null ? getDefaultDate() : strToDate(repayVo.getShouldRepaymentDate().replace("-", ""), DateUtil.pattern_day));
									repaymentStatue.add(repayVo.getRepaymentStatue() == null ? null : repayVo.getRepaymentStatue());
								}
							}
						}
					}
					resFindHis.setRealityRepaymentDate(realityRepaymentDate);
					resFindHis.setShouldRepaymentDate(shouldRepaymentDate);
					resFindHis.setRepaymentStatue(repaymentStatue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用核心查询历史数据异常，异常信息" + e.getMessage());
			List<Integer> repaymentTerm = new ArrayList<Integer>();
			repaymentTerm.add(0);
			repaymentTerm.add(0);
			repaymentTerm.add(0);
			resFindHis.setRepaymentTerm(repaymentTerm);// 借款期数
			return resFindHis;
		}
		return resFindHis;
	}

	/**
	 * 从权限获取当前的非工作日信息
	 * 
	 * @return
	 */
	public List<String> queryHolidays() {
		List<String> reqList = new ArrayList<String>();
		try {
			ReqCalendarVO reqVo = new ReqCalendarVO();
			reqVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			reqVo.setYear(Calendar.getInstance().get(Calendar.YEAR) + "");
			reqVo.setType("1");
			logger.info("从权限获取当前的非工作日信息,入参:" + gson.toJson(reqVo));
			Response<List<ResCalendarVO>> listAllByParam = iCalendarExecuter.listAllByParam(reqVo);
			logger.info("从权限获取当前的非工作日信息,返回:" + gson.toJson(listAllByParam));
			if (listAllByParam.isSuccess()) {
				List<ResCalendarVO> resList = listAllByParam.getData();
				if (resList != null && resList.size() > 0) {
					for (ResCalendarVO reVo : resList) {
						if (reVo.getSomeDay() == null)
							continue;

						reqList.add(DateUtil.getDate(DateUtil.strToDateTimeDay(reVo.getSomeDay()), DateUtil.pattern_day));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("从权限获取当前的非工作日信息 失败：" + e.getMessage());
			return reqList;
		}
		return reqList;
	}

	public Date getDefaultDate() {
		if (defaultDate == null) {
			logger.info("初始化默认时间");
			initDefaultDate();
		}
		return defaultDate;
	}

	public void initDefaultDate() {
		try {
			defaultDate = strToDate("99991231", DateUtil.pattern_day);
		} catch (Exception e) {
			logger.info("初始化时间失败：" + e.getMessage());
			defaultDate = new Date();
		}
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 * @return formatStr
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String formatStr) throws ParseException {
		if (formatStr == null) {
			formatStr = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}

	@Override
	public Response<ReasonVO> queryReason(ReasonVO reasonVO) {
		Response<ReasonVO> resReason = new Response<ReasonVO>();
		ReasonVO reason = new ReasonVO();
		// 入参
		if (StringUtils.isEmpty(reasonVO.getQueryReasonCode())) {
			resReason.setData(reason);
			return resReason;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 原因数据
		BMSTMReasonEntity firstLevel = null;
		BMSTMReasonEntity twoLevel = null;
		if (reasonVO.getQueryReasonCode() != null) {
			paramMap.put("code", reasonVO.getQueryReasonCode());
			//原因类别特殊
			/*paramMap.put("reasonType", "2");*/
			firstLevel = iBMSTMReasonService.getBy(paramMap);
			if (firstLevel == null) {
				resReason.setData(reason);
				return resReason;
			} else {
				// 判断是否二级原因
				if (firstLevel.getType() != null && firstLevel.getType().equals("2")) {
					twoLevel = firstLevel;
					paramMap.put("code", null);
					/*paramMap.put("reasonType", "2");*/
					paramMap.put("id", twoLevel.getParentId());
					firstLevel = iBMSTMReasonService.getBy(paramMap);
				}
			}
		}

		reason.setFirstLevelReason(firstLevel == null ? null : firstLevel.getReason());
		reason.setFirstLevelReasonCode(firstLevel == null ? null : firstLevel.getCode());
		reason.setTwoLevelReason(twoLevel == null ? null : twoLevel.getReason());
		reason.setTwoLevelReasonCode(twoLevel == null ? null : twoLevel.getCode());
		resReason.setData(reason);
		return resReason;
	}

	/**
	 * 查看当前用户的贷款状态 申请类型 NEW 新申请 TOPUP 追加贷款 RELOAN 结清再贷
	 * 
	 * @param idNo
	 *            取消 结清 逾期 正常
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getApplyType(String idNo) {
		String ApplyType = "";
		try {
			ApplyType = "NEW";
			// 查看是否
			Map<String, Object> httpParamMap = new HashMap<String, Object>();
			httpParamMap.put("idnum", idNo);
			System.out.println("===========>调用核心查询数据该用户是否有借款信息:" + gson.toJson(httpParamMap));
			HttpResponse queryLoan = coreHttpService.loanStatus(httpParamMap);
			Map<String, Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("msg").equals("该用户不在系统中")) {
					return ApplyType;
				}

				List<Map<String, String>> resQueryLoanVo = (List<Map<String, String>>) contentMap.get("loan");
				if (resQueryLoanVo != null && resQueryLoanVo.size() > 0) {
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("正常".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "TOPUP";
							return ApplyType;
						} else if ("结清".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "RELOAN";
						}
					}
				}
			}
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			return ApplyType;
		}
		return ApplyType;
	}

	
	/**
	 * 查看当前用户的贷款状态 申请类型 NEW 新申请 TOPUP 追加贷款 RELOAN 结清再贷
	 * 
	 * @param idNo
	 *            取消 结清 逾期 正常
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getApplyTypeYBR(String idNo) {
		String ApplyType = "";
		try {
			//ApplyType = "NEW";
			// 查看是否
			Map<String, Object> httpParamMap = new HashMap<String, Object>();
			httpParamMap.put("idnum", idNo);
			System.out.println("===========>调用核心查询数据该用户是否有借款信息:" + gson.toJson(httpParamMap));
			HttpResponse queryLoan = coreHttpService.loanStatus(httpParamMap);
			Map<String, Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("msg").equals("该用户不在系统中")) {
					ApplyType = "NEW";
					return ApplyType;
				}

				List<Map<String, String>> resQueryLoanVo = (List<Map<String, String>>) contentMap.get("loan");
				if (resQueryLoanVo != null && resQueryLoanVo.size() > 0) {
					//新加一行，如果前两种都不属于，就直接给新客户 2017-10-30
					ApplyType = "NEW";
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("正常".equals(loanBaseVo.get("loanState"))||"逾期".equals(loanBaseVo.get("loanState"))||"预结清".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "TOPUP";
							return ApplyType;
						} 
					}
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("结清".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "RELOAN";
							return ApplyType;
						}
					}
					for (Map<String, String> loanBaseVo : resQueryLoanVo) {
						if ("申请".equals(loanBaseVo.get("loanState"))||"通过".equals(loanBaseVo.get("loanState"))) {
							ApplyType = "NEW";
							return ApplyType;
						} 
					}
					
				}
			}
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			return ApplyType;
		}
		return ApplyType;
	}
	
	/**
	 * 获取第三方接口数据
	 * 
	 * @param name
	 * @param idNo
	 * @return
	 */
	public ThirdPartyInfoEntity getThirdPartyInfo(String name, String idNo) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ThirdPartyInfoEntity res = new ThirdPartyInfoEntity();
		try {
			String msg = "";
			Map<String, Object> appDataMap = new HashMap<String, Object>();
			appDataMap.put("idCard", idNo);// 身份证
			appDataMap.put("name", name);// 用户名
			appDataMap.put("creatorId", "system");// 员工
			JSONObject thirdPartyInfo = creditHttpService.getThirdPartyInfo(appDataMap);
			if (thirdPartyInfo.has("code") && "000000".equals(thirdPartyInfo.getString("code"))) { // 查询成功，并且有报告
				ThirdPartyInfoListDateEntity decode = JsonUtils.decode(gson.toJson(thirdPartyInfo.get("data")), ThirdPartyInfoListDateEntity.class);
				List<String> ifThirdOrgReturn = new ArrayList<String>();
				List<Date> thirdOrgReturnDate = new ArrayList<Date>();
				if (decode != null && decode.getMyArrayList() != null && decode.getMyArrayList().size() > 0) {
					for (int i = 0; i < decode.getMyArrayList().size(); i++) {
						ThirdPartyInfoDateEntity thirdPartyInfoDateEntity = decode.getMyArrayList().get(i).get("map");
						if (thirdPartyInfoDateEntity.getCode() != null && thirdPartyInfoDateEntity.getCode().equals("000000")) {
							ifThirdOrgReturn.add("Y");
							thirdOrgReturnDate.add(new Date(thirdPartyInfoDateEntity.getDate()));
						} else {
							ifThirdOrgReturn.add("N");
							thirdOrgReturnDate.add(getDefaultDate());
						}
					}
					res.setIfThirdOrgReturn(ifThirdOrgReturn);
					res.setThirdOrgReturnDate(thirdOrgReturnDate);
				}
			} else {
				msg = "---------------获取第三方接口数据异常，失败原因: " + thirdPartyInfo.get("messages");
				logger.info(msg);
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------获取第三方接口数据异常：" + e.getMessage());
			return null;
		}
		return res;
	}

	/**
	 * 获取老政审数据
	 * 
	 * @param name
	 * @param idNo
	 */
	public QueryMainEntity queryMainByIdNo(String name, String idNo) {
		QueryMainEntity contentMap = null;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", "LOAN_MAIN_VALUE");
		BMSTmParameter loanBase = (BMSTmParameter) ibmsTmParameterService.getBy(paramMap, "queryByCode");
		// 判断调用益博睿开关 0开 1关
		logger.info("调用老征审开关：0开 1关--" + gson.toJson(loanBase));
		if (loanBase != null && loanBase.getParameterValue() != null && loanBase.getParameterValue().equals("1")) {
			return contentMap;
		}

		Map<String, Object> cMap = null;
		try {
			Map<String, Object> appDataMap = new HashMap<String, Object>();
			appDataMap.put("idNo", idNo);// 身份证
			appDataMap.put("name", name);// 用户名
			HttpResponse thirdPartyInfo = creditHttpService.queryMainByIdNo(appDataMap);
			logger.info("获取老政审数据：" + gson.toJson(thirdPartyInfo));
			if (thirdPartyInfo != null && thirdPartyInfo.getCode() == 200) {
				contentMap = new QueryMainEntity();

				cMap = JsonUtils.decode(thirdPartyInfo.getContent(), Map.class);

				contentMap.setIfHaveValidate(ifNull(cMap.get("ifHaveValidate")) ? "N" : cMap.get("ifHaveValidate").toString());
				; // 是否有借款 Y有 N无
				contentMap.setPreviousStatus(ifNull(cMap.get("previousStatus")) ? null : cMap.get("previousStatus").toString());
				; // 上笔借款状态 previousStatus
				contentMap.setPreviousRtfState(ifNull(cMap.get("previousRtfState")) ? null : cMap.get("previousRtfState").toString());
				; // 上笔借款环节 previousRtfState
				contentMap.setPreviousOwningBranchId(ifNull(cMap.get("previousOwningBranchId")) ? null : Long.parseLong(cMap.get("previousOwningBranchId").toString()));
				; // 上笔借款录单营业部 previousOwningBranchId
				contentMap.setPreviousIfRefuse(ifNull(cMap.get("previousIfRefuse")) ? "N" : cMap.get("previousIfRefuse").toString()); // 上笔借款是否被拒
																																		// Y是
																																		// N否
																																		// previousIfRefuse
				contentMap.setPreviousRefuseDate(ifNull(cMap.get("previousRefuseDate")) ? getDefaultDate() : DateUtil.strToDateTime(cMap.get("previousRefuseDate").toString(),"yyyy-MM-dd"));
				; // 上笔借款被拒时间 previousRefuseDate
				contentMap.setPreviousCancelDate(ifNull(cMap.get("previousCancelDate")) ? getDefaultDate() : DateUtil.strToDateTime(cMap.get("previousCancelDate").toString(),"yyyy-MM-dd"));
				; // 上笔借款被取消时间 previousCancelDate
				contentMap.setLimitDays(ifNull(cMap.get("limitDays")) ? null : Integer.parseInt(cMap.get("limitDays").toString()));
				; // 限制天数 limitDays
				contentMap.setProtectDays(ifNull(cMap.get("protectDays")) ? null : Integer.parseInt(cMap.get("protectDays").toString()));
				;// 保护天数 protectDays
				contentMap.setApplyType(ifNull(cMap.get("applyType")) ? null : cMap.get("applyType").toString());
				; // 申请类型 applyType
				contentMap.setProductCode(ifNull(cMap.get("productCode")) ? null : cMap.get("productCode").toString());
				; // 借款产品 productCode
				contentMap.setApplyDate(ifNull(cMap.get("applyDate")) ? getDefaultDate().toString() : cMap.get("applyDate").toString()); // 申请创建时间
																																			// applyDate
				contentMap.setAppApplyInput(ifNull(cMap.get("appApplyInput")) ? "N" : cMap.get("appApplyInput").toString());
				; // 是否APP进件 ‘Y是 N否 appApplyInput
				contentMap.setOwningBranchId(ifNull(cMap.get("owningBranchId")) ? null : cMap.get("owningBranchId").toString());
				; // 进件营业部 owningBranchId
				
				contentMap.setAppNo(ifNull(cMap.get("appNo")) ? null : cMap.get("appNo").toString());
				;// 借款编号
			}
		} catch (Exception e) {
			logger.info("获取老政审数据异常：" + e.getMessage());
			return contentMap;
		}
		return contentMap;
	}

	public static boolean ifNull(Object obj) {
		//return ((obj == null) || (obj.equals("null") || "".equals(obj)));
		return (obj == null) || (obj.equals("null")) || ("".equals(obj));
	}

	/**
	 * 获取央行征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String fixedCreditReportNotAppNo(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();

		String msg = "";
		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			appDataMap.put("queryDate", df.format(new Date()));// 首次录入复核时间
			JSONObject resObject = creditHttpService.queryBMSReportNotAppNo(appDataMap);
			if (resObject.has("code") && "000000".equals(resObject.getString("code"))) { // 查询成功，并且有报告
				msg = " ---------------查询央行报告成功，将返回的央行征信报告id[" + resObject.get("reportId") + "]";
				logger.info(msg);
				return resObject.get("reportId").toString();
			} else {
				msg = "---------------查询央行报告失败，失败原因: " + resObject.get("messages");
				logger.info(msg);
				return null;
			}
		} catch (Exception e) {
			logger.info("---------------查询央行报告成功异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 解读征信报告
	 * 
	 * @param personHistoryLoanVO
	 * @return
	 */
	public ResBadCreditInfoEntity getBadCreditInfo(PersonHistoryLoanVO personHistoryLoanVO) {
		ResBadCreditInfoEntity res = new ResBadCreditInfoEntity();

		Map<String, Object> appDataMap = new HashMap<String, Object>();

		try {
			appDataMap.put("customerIdCard", personHistoryLoanVO.getIdNo());// 身份证
			appDataMap.put("customerName", personHistoryLoanVO.getName());// 用户名
			appDataMap.put("reportId", personHistoryLoanVO.getReportId());// 央行报告id
			logger.info("央行报告解析结果--入参：" + gson.toJson(appDataMap));
			JSONObject resObject = creditHttpService.getBadCreditInfo(appDataMap);
			logger.info("央行报告解析结果--出参：" + gson.toJson(resObject));

			if (resObject.has("code") && "000000".equals(resObject.getString("code"))) {
				// 000000为有记录
				logger.info("央行报告解析结果--有记录：" + resObject.getString("code"));
				logger.info("央行报告解析结果--(type是否符合无综合信用条件(YES是/NO否))符合无综合信用判断：" + resObject.getString("type"));
				if (resObject.has("type") && "YES".equals(resObject.getString("type"))) {
					// YES 符合无综合信用判断
					res.setIfCreditInnocence(true);
					return res;
				} else {
					logger.info("央行报告解析结果--(status是否信用良好(YES良好/NO不良))信用：" + resObject.getString("status"));
					if (resObject.has("status") && "NO".equals(resObject.getString("status"))) {
						// 信用不良
						if (resObject.has("data") && resObject.getJSONObject("data") != null) {
							// 返回数据有反斜杠 去掉
							BadCreditInfoEntity data = JsonUtils.decode(resObject.getJSONObject("data").toString(), BadCreditInfoEntity.class);

							logger.info("央行报告解析结果--返回数据解析为：" + gson.toJson(data));
							if (data != null) {
								BeanUtils.copyProperties(data, res);

								/** 信用卡 **/
								List<BadCreditInfoValueEntity> card = data.getCard();
								if (!card.isEmpty()) {
									logger.info("央行报告解析结果--信用卡逾期信息：" + gson.toJson(card));
									for (int i = 0; i < card.size(); i++) {
										BadCreditInfoValueEntity cardVo = card.get(i);
										if (cardVo == null || cardVo.getOverdueType() == null)
											continue;
										// 当前逾期0, 非当前逾期1
										if (cardVo.getOverdueType() == 0) {
											res.setCredit_ifCurrentOverdue(true);
										} else if (cardVo.getOverdueType() == 1) {
											res.setCredit_ifNoCurrentOverdue(true);
										}
									}
								}

								/** 购房贷款 **/
								List<BadCreditInfoValueEntity> houseLoan = data.getHouseLoan();
								if (!houseLoan.isEmpty()) {
									logger.info("央行报告解析结果--购房贷款逾期信息：" + gson.toJson(houseLoan));
									for (int i = 0; i < houseLoan.size(); i++) {
										BadCreditInfoValueEntity houseLoanVo = houseLoan.get(i);
										if (houseLoanVo == null || houseLoanVo.getOverdueType() == null)
											continue;
										// 当前逾期0, 非当前逾期1
										if (houseLoanVo.getOverdueType() == 0) {
											res.setLoan_ifCurrentOverdue(true);
										} else if (houseLoanVo.getOverdueType() == 1) {
											res.setLoan_ifNoCurrentOverdue(true);
										}
									}
								}
							}
						}
					}
				}
			}

			// 如果触发某条 说明要做提示
			if (res.isIfCreditInnocence() || res.isCredit_ifCurrentOverdue() || res.isCredit_ifNoCurrentOverdue() || res.isLoan_ifCurrentOverdue() || res.isLoan_ifNoCurrentOverdue()) {
				return res;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.info("---------------央行报告解析结果--异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 征信白户校验
	 */
	public Response<ReqCreditCheckVO> checkCreditUser(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<ReqCreditCheckVO> resResponseVO = new Response<ReqCreditCheckVO>();

		ReqCreditCheckVO reqCkVO = new ReqCreditCheckVO();

		String msg = "";

		// 姓名
		if (StringUtils.isEmpty(validateNameIdNoVO.getName())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "name" });
		}
		// 身份证
		if (StringUtils.isEmpty(validateNameIdNoVO.getIdNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "idNo" });
		}
		// 申请的产品
		if (StringUtils.isEmpty(validateNameIdNoVO.getProductCode())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "productCode" });
		}

		try {
			// 录入复核提交绑定 人行征信报告
			PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
			personHistoryLoanVO.setSysCode(EnumConstants.BMS_SYSCODE);
			personHistoryLoanVO.setName(validateNameIdNoVO.getName());
			personHistoryLoanVO.setIdNo(validateNameIdNoVO.getIdNo());
			String fixedCreditReport = fixedCreditReportNotAppNo(personHistoryLoanVO);
			logger.info("获取征信报告id:" + fixedCreditReport);
			// 无征信报告
			if (fixedCreditReport == null) {
				resResponseVO.setData(reqCkVO);
				return resResponseVO;
			}
			personHistoryLoanVO.setReportId(fixedCreditReport);
			reqCkVO.setReportId(fixedCreditReport);
			ResBadCreditInfoEntity badCreditInfo = getBadCreditInfo(personHistoryLoanVO);
			logger.info("解读征信报告信息:" + gson.toJson(badCreditInfo));
			// 是否触发规则
			if (badCreditInfo != null) {

				reqCkVO.setIfTrigger(true);

				// 是否触发征信白户
				if (badCreditInfo.isIfCreditInnocence()) {
					// 是征信白户
					reqCkVO.setType(2);// 征信白户

					msg += getCreditInnocenceMsg(validateNameIdNoVO);
				} else {
					// 逾期造成的信用不良
					reqCkVO.setType(3);// 信用不良

					msg += getCreditBadMsg(validateNameIdNoVO, badCreditInfo);
				}
				
				logger.info("------------拼装描述内容为["+msg+"]");
				//如果没有描述，默认不触发任何规则
				if(StringUtils.isEmpty(msg)){
					reqCkVO.setIfTrigger(false);
				}
			}
		} catch (Exception e) {
			logger.info("征信白户校验方法异常：" + e.getMessage());
			return resResponseVO;
		}
		reqCkVO.setMsg(msg);
		resResponseVO.setData(reqCkVO);
		return resResponseVO;
	}

	/**
	 * 征信白户整合msg
	 * 
	 * @return
	 */
	public String getCreditInnocenceMsg(ValidateNameIdNoVO validateNameIdNoVO) {
		String msg = "";
		// 用户的申请类型
		String applyType = getApplyType(validateNameIdNoVO.getIdNo());

		Map<String, Object> repMap = new HashMap<String, Object>();
		repMap.put("grade", "1");// 征信等级
		repMap.put("customerType", applyType);// 客户类型

		BMSCreditRatingLimit ratingV = iBMSCreditRatingLimitDao.getBy(repMap);
		logger.info("征信白户配置查询结果：" + gson.toJson(ratingV));
		logger.info("匹配的产品是：" + validateNameIdNoVO.getProductCode());
		// 有配置，并且配置的商品不为空
		if (ratingV != null && ratingV.getProductType() != null) {
			// 交易该商品是否有配置该申请商品
			if (ratingV.getProductType().indexOf(validateNameIdNoVO.getProductCode()) == -1) {
				msg += ratingV.getRemark();
			}
		}
		return msg;
	}

	/**
	 * 当前逾期信息
	 * 
	 * @return
	 */
	public String getCurrentMsg(BMSCreditRatingLimit config, BadCreditInfoValueEntity value) {
		String msg = "";
		if (config == null || value == null || config.getCreditType() == null) {
			return msg;
		}
		// 类型 1 贷款 2 信用卡
		if (config.getCreditType().equals("1")) {
			msg += " " + value.getNo() + "、" + value.getContent() + " " + config.getRemark() + " ";
		} else if (config.getCreditType().equals("2")) {
			// 需要动态替换数据
			msg += " " + value.getNo() + "、" + value.getContent() + " " + MoneyUtil.TransformationMsg(value.getContent(), config.getRemark()) + " ";
		}
		return msg;
	}

	/**
	 * 非当前逾期信息
	 * 
	 * @return
	 */
	public String getNoCurrentMsg(BMSCreditRatingLimit config, BadCreditInfoValueEntity value) {
		String msg = "";
		if (config == null || value == null) {
			return msg;
		}
		msg += " " + value.getNo() + "、  " + config.getRemark() + " ";
		return msg;
	}

	/**
	 * 整合逾期造成信用不良msg
	 * 
	 * @return
	 */
	public String getCreditBadMsg(ValidateNameIdNoVO validateNameIdNoVO, ResBadCreditInfoEntity badCreditInfo) {
		String msg = "";

		Map<String, Object> repMap = new HashMap<String, Object>();
		repMap.put("noGrade", "1");
		// 征信等级 2 当前逾期 3 非当前逾期
		// 类型 1 贷款 2 信用卡
		List<BMSCreditRatingLimit> listBy = iBMSCreditRatingLimitDao.listBy(repMap);
		if (!listBy.isEmpty()) {
			Map<String, BMSCreditRatingLimit> map = new HashMap<String, BMSCreditRatingLimit>();
			for (int i = 0; i < listBy.size(); i++) {
				String key = (listBy.get(i).getGrade()) + "_" + (StringUtils.isEmpty(listBy.get(i).getCreditType()) ? "0" : listBy.get(i).getCreditType());
				map.put(key, listBy.get(i));
			}

			/** 信用卡 **/
			List<BadCreditInfoValueEntity> cardList = badCreditInfo.getCard();
			if (!cardList.isEmpty()) {
				for (BadCreditInfoValueEntity card : cardList) {
					if (card.getOverdueType() == null)
						continue;
					// 当前逾期0, 非当前逾期1
					if (card.getOverdueType() == 0) {
						msg += getCurrentMsg(map.get("2_2"), card);
					} else if (card.getOverdueType() == 1) {
						msg += getNoCurrentMsg(map.get("3_0"), card);
					}
				}
			}

			/** 购房贷款 **/
			List<BadCreditInfoValueEntity> houseLoanList = badCreditInfo.getHouseLoan();
			if (!houseLoanList.isEmpty()) {
				for (BadCreditInfoValueEntity houseLoan : houseLoanList) {
					if (houseLoan.getOverdueType() == null)
						continue;
					// 当前逾期0, 非当前逾期1
					if (houseLoan.getOverdueType() == 0) {
						msg += getCurrentMsg(map.get("2_1"), houseLoan);
					} else if (houseLoan.getOverdueType() == 1) {
						msg += getNoCurrentMsg(map.get("3_0"), houseLoan);
					}
				}
			}

		}

		return msg;
	}

	public ApprovalOpinionsVO httpToTheLetter(PersonHistoryLoanVO personHistoryLoanVO){
		ApprovalOpinionsVO approvalOpinionsVO = new ApprovalOpinionsVO();
		
		logger.info("调用审核接口HTTP开始--------------------");
		//HTTP请求审核接口
		 DefaultHttpClient client = new DefaultHttpClient();
		 logger.info("打印URL路径"+checkUrl+personHistoryLoanVO.getLoanNo()+"/"+"bms"+"/"+System.currentTimeMillis());
        // 创建httpget.    
        HttpGet httpget = new HttpGet(checkUrl+personHistoryLoanVO.getLoanNo()+"/"+"bms"+"/"+System.currentTimeMillis());  
        // 执行get请求.    
        CloseableHttpResponse httpResponse = null;
		try {
			httpResponse =  client.execute(httpget);
		}catch (Exception e) {
			logger.info("调用审核接口HTTP开始报错--------------------"+e.getMessage());
			return approvalOpinionsVO;
		}  
        // 获取响应实体    
        HttpEntity responseEntity = httpResponse.getEntity(); 
        String strResult = "";
        try {
			 strResult = EntityUtils.toString(responseEntity,"UTF-8");
			 logger.info("查看返回的JSON的值--------------------"+strResult);
		} catch (Exception e) {
			logger.info("获取HttpEntity里面的返回参数报错--------------------"+e.getMessage());
			return approvalOpinionsVO;
		} 
        ObjectMapper mapper = new ObjectMapper(); 
       
        try {
        	JsonNode node = mapper.readTree(strResult);  
        	JsonNode nodeString=node.get("data");
        	approvalOpinionsVO = mapper.readValue(nodeString.toString(), ApprovalOpinionsVO.class);
        	
        	if(approvalOpinionsVO == null){
        		return new ApprovalOpinionsVO();
        	}
		} catch (Exception e) {
			logger.info("JSON字符串转实体报错--------------------"+e.getMessage());
			return approvalOpinionsVO;
		}
        return approvalOpinionsVO;
	}
	
	
	
	@Override
	public Response<ResYBRReturnVO> queryApplyDataIsYBR(PersonHistoryLoanVO personHistoryLoanVO) {
		long start=System.currentTimeMillis();
		logger.info("开始计时-------------------"+start);
		if(null==personHistoryLoanVO){
			logger.info("吊用益博瑞接口开始--------------------personHistoryLoanVO传入参数为null");
		}
		if(null==personHistoryLoanVO.getLoanNo()||personHistoryLoanVO.getLoanNo().equals("")){
			logger.info("吊用益博瑞接口开始--------------------personHistoryLoanVO的loan_no参数为 NULL");
		}
		logger.info("查询很多数据开始--------------------"+System.currentTimeMillis());
		Response<ApplyEntryVO> response=applyDataManipulationService.queryApplyEntry(personHistoryLoanVO);
		logger.info("查询很多数据结束--------------------"+System.currentTimeMillis());
		ApplyEntryVO applyEntryVO=response.getData();
		
		ApplyInfoVO applyInfoVO=applyEntryVO.getApplyInfoVO();
		
		PersonInfoVO personInfoVO=applyEntryVO.getBasicInfoVO().getPersonInfoVO();
		logger.info("查询黑名单开始计时-------------------"+System.currentTimeMillis());
		String black=getBlackList(personInfoVO.getName(),personInfoVO.getIdNo());
		logger.info(black+"查询黑名单结束计时-------------------"+System.currentTimeMillis());
		applyInfoVO.setNameAndidNoIsBlack(black);//设置是否黑名单
		APPCurrentApplyEntity aPPCurrentApplyEntity=quertyOrgInfoByID(applyInfoVO.getOwningBranchId());
		applyInfoVO.setEnterBranchCity(aPPCurrentApplyEntity.getOwningBranchCity());//进件营业部所属城市
		applyInfoVO.setEnterBranchSubsection(aPPCurrentApplyEntity.getOwningBranchDivision());//进件营业部所属分部
		applyInfoVO.setEnterBranchArea(aPPCurrentApplyEntity.getOwningBranchArea());//进件营业部所属区域
		logger.info("查询进件营业部信息--------------------"+gson.toJson(aPPCurrentApplyEntity));
		logger.info("查询借款审批表实体开始--------------------");
		LoanAuditEntity loanAuditEntity=applyDataManipulationService.findByAudit(personHistoryLoanVO);
		logger.info("查询借款审批表实体结束--------------------"+gson.toJson(loanAuditEntity));
		applyInfoVO.setCommitDate(loanAuditEntity.getCreated_time());//设置提交时间
		applyInfoVO.setAmoutIncome(loanAuditEntity.getAmout_income());//设置收入证明
		applyInfoVO.setIfCreditRecord(loanAuditEntity.getIf_credit_record());//设置是否信用记录
		logger.info("吊审核接口开始计时-------------------"+System.currentTimeMillis());
		//掉审核接口
        applyEntryVO.setApprovalOpinionsVO(httpToTheLetter(personHistoryLoanVO));
        logger.info("吊审核接口结束计时-------------------"+System.currentTimeMillis());
        logger.info("掉核心接口1-------------------开始"+System.currentTimeMillis());
        //FindHisVO resFindHis=findHistoryLoanTwo("张姚敏33","882542199101186079");
        FindHisVO resFindHis=findHistoryLoanTwo(personInfoVO.getName(),personInfoVO.getIdNo());//掉核心接口查询HTTP
        logger.info("掉核心接口1-------------------结束"+System.currentTimeMillis());
        applyEntryVO.setFindHisVO(resFindHis);
        
        personHistoryLoanVO.setName(personInfoVO.getName());
        personHistoryLoanVO.setIdNo(personInfoVO.getIdNo());
//        List<LoanBaseEntity> loanBaseEntity=applyDataManipulationService.findLoanNoByNotBelongTo(personHistoryLoanVO);
//        logger.info("查询借款最近一笔实体结束--------------------"+gson.toJson(loanBaseEntity));
//        if(null==loanBaseEntity||loanBaseEntity.size()==0){
//        	applyInfoVO.setLastTimeStatus(null);
//        }else{
//        	applyInfoVO.setLastTimeStatus(loanBaseEntity.get(0).getStatus());
//        }
        //获取创建时间最近的一笔借款状态，新老系统校验（修改上面注释掉的取值）
        QueryMainEntity queryApsAll = queryMainByIdNo(personHistoryLoanVO.getName(), personHistoryLoanVO.getIdNo());
		logger.info("获取老征审借款结果 -----------：" + gson.toJson(queryApsAll));
		
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("IdNo", personInfoVO.getIdNo());
		newMap.put("loanNo",personHistoryLoanVO.getLoanNo());
		LoanBaseEntity newLoanBase = (LoanBaseEntity) loanBaseService.getBy(newMap, "getLoanBaseByNameIdNo");
		logger.info("拿身份证借款编号去查询新系统是否有的借款"+gson.toJson(newLoanBase));
		if(queryApsAll != null && newLoanBase!=null){
			logger.info("新老系统存在数据---------------");
				
				SimpleDateFormat sss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!queryApsAll.getApplyDate().contains("9999")){
					try {
						if(newLoanBase.getCreatedTime().compareTo(sss.parse(queryApsAll.getApplyDate()))==1){
							logger.info("新系统最近一笔创建时间大于老系统的最近一笔创建时间---");
							applyInfoVO.setLastTimeStatus(newLoanBase.getStatus());
						}else{
							logger.info("老系统最近一笔创建时间大于新系统的最近一笔创建时间---");
							applyInfoVO.setLastTimeStatus(EnumConstants.OldStatus.get(queryApsAll.getPreviousStatus()));
						}
					} catch (ParseException e) {
						logger.info("转换日期出错-----------------------------"+e.getMessage());
					}
				}else{
					applyInfoVO.setLastTimeStatus(newLoanBase.getStatus());
				}
				
		}else{
			logger.info("老系统存在数据---------------");
			if(queryApsAll!=null&&!queryApsAll.getApplyDate().contains("9999")){
				applyInfoVO.setLastTimeStatus(EnumConstants.OldStatus.get(queryApsAll.getPreviousStatus()));
			}else if(newLoanBase!=null){
				applyInfoVO.setLastTimeStatus(newLoanBase.getStatus());
			}else{
				applyInfoVO.setLastTimeStatus(null);
			}
		}
		
		
		
        
        //创建新的对象封装返回实体
        Response<ResYBRReturnVO> responseYBR=new Response<ResYBRReturnVO>();
        ResYBRReturnVO vo=new ResYBRReturnVO();
        logger.info("创建返回的实体对象--------------------");
        BeanUtils.copyProperties(applyEntryVO.getApplyInfoVO(), vo);
        vo.setRtfNodeState(applyEntryVO.getApplyInfoVO().getRtfState());
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getEstateInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getCarInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getPolicyInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getProvidentInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getBasicInfoVO().getPersonInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getBasicInfoVO().getWorkInfoVO(), vo);
        BeanUtils.copyProperties(applyEntryVO.getBasicInfoVO().getPrivateOwnerInfoVO(), vo);
        
        BeanUtils.copyProperties(applyEntryVO.getApprovalOpinionsVO(), vo);
        
        BeanUtils.copyProperties(applyEntryVO.getFindHisVO(), vo);
        if(null!=applyEntryVO.getFindHisVO().getGrantMoney()){
        	List<Double> listDouble=new ArrayList<Double>(); 
        	for(BigDecimal b:applyEntryVO.getFindHisVO().getGrantMoney()){
            	Double returnDouble=b.doubleValue();
            	listDouble.add(returnDouble);
            }
        	vo.setGrantMoney(listDouble);
        }else{
        	List<Double> listDoubleNull=new ArrayList<Double>(); 
        	for(int i=0;i<3;i++){
        		listDoubleNull.add(0d);
        	}
        	vo.setGrantMoney(listDoubleNull);
        }
        logger.info("BeanUtils.copyPropertiesBIGDEMAIC赋值给DOUBLE不行赋值这边手动赋值--------------------");
        //BeanUtils.copyPropertiesBIGDEMAIC赋值给DOUBLE不行赋值这边手动赋值
        vo.setApplyLmt(applyEntryVO.getApplyInfoVO().getApplyLmt()==null?null:applyEntryVO.getApplyInfoVO().getApplyLmt().doubleValue());
        vo.setMonthPaymentAmt(applyEntryVO.getAssetsInfoVO().getEstateInfoVO().getMonthPaymentAmt()==null?null:applyEntryVO.getAssetsInfoVO().getEstateInfoVO().getMonthPaymentAmt().doubleValue());
        vo.setCarBuyPrice(applyEntryVO.getAssetsInfoVO().getCarInfoVO().getCarBuyPrice()==null?null:applyEntryVO.getAssetsInfoVO().getCarInfoVO().getCarBuyPrice().doubleValue());
        vo.setYearPaymentAmt(applyEntryVO.getAssetsInfoVO().getPolicyInfoVO().getYearPaymentAmt()==null?null:applyEntryVO.getAssetsInfoVO().getPolicyInfoVO().getYearPaymentAmt().doubleValue());
        vo.setMonthDepositAmt(applyEntryVO.getAssetsInfoVO().getProvidentInfoVO().getMonthDepositAmt()==null?null:applyEntryVO.getAssetsInfoVO().getProvidentInfoVO().getMonthDepositAmt().doubleValue());
        vo.setDepositBase(applyEntryVO.getAssetsInfoVO().getProvidentInfoVO().getDepositBase()==null?null:applyEntryVO.getAssetsInfoVO().getProvidentInfoVO().getDepositBase().doubleValue());
        vo.setCreditLimit(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getCreditLimit()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getCreditLimit().doubleValue());
        vo.setGoodRate(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getGoodRate()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getGoodRate().doubleValue());
        vo.setLastYearPayAmt(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getLastYearPayAmt()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getLastYearPayAmt().doubleValue());
        vo.setPayAmt3(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt3()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt3().doubleValue());
        vo.setPayAmt2(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt2()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt2().doubleValue());
        vo.setPayAmt1(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt1()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayAmt1().doubleValue());
        vo.setPastYearShoppingAmount(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPastYearShoppingAmount()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPastYearShoppingAmount().doubleValue());
        vo.setFamilyMonthPay(applyEntryVO.getBasicInfoVO().getPersonInfoVO().getFamilyMonthPay()==null?null:applyEntryVO.getBasicInfoVO().getPersonInfoVO().getFamilyMonthPay().doubleValue());
        vo.setMonthMaxRepay(applyEntryVO.getBasicInfoVO().getPersonInfoVO().getMonthMaxRepay()==null?null:applyEntryVO.getBasicInfoVO().getPersonInfoVO().getMonthMaxRepay().doubleValue());
        vo.setMonthSalary(applyEntryVO.getBasicInfoVO().getWorkInfoVO().getMonthSalary()==null?null:applyEntryVO.getBasicInfoVO().getWorkInfoVO().getMonthSalary().doubleValue());
        vo.setOtherIncome(applyEntryVO.getBasicInfoVO().getWorkInfoVO().getOtherIncome()==null?null:applyEntryVO.getBasicInfoVO().getWorkInfoVO().getOtherIncome().doubleValue());
        vo.setTotalMonthSalary(applyEntryVO.getBasicInfoVO().getWorkInfoVO().getTotalMonthSalary()==null?null:applyEntryVO.getBasicInfoVO().getWorkInfoVO().getTotalMonthSalary().doubleValue());
        vo.setRegisterFunds(applyEntryVO.getBasicInfoVO().getPrivateOwnerInfoVO().getRegisterFunds()==null?null:applyEntryVO.getBasicInfoVO().getPrivateOwnerInfoVO().getRegisterFunds().doubleValue());
        
        
        
        
        vo.setCarbillAmt1(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt1()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt1().doubleValue());
        vo.setCarbillAmt2(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt2()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt2().doubleValue());
        vo.setCarbillAmt3(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt3()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt3().doubleValue());
        vo.setCarbillAmt4(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt4()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getBillAmt4().doubleValue());
        vo.setCarpayMonthAmt(applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getPayMonthAmt()==null?null:applyEntryVO.getAssetsInfoVO().getCardLoanInfoVO().getPayMonthAmt().doubleValue());
        
        vo.setMasterpayMonthAmt(applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayMonthAmt()==null?null:applyEntryVO.getAssetsInfoVO().getMasterLoanInfoVO().getPayMonthAmt().doubleValue());
        
        vo.setTaobillAmt1(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt1()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt1().doubleValue());
        vo.setTaobillAmt2(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt2()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt2().doubleValue());
        vo.setTaobillAmt3(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt3()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt3().doubleValue());
        vo.setTaobillAmt4(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt4()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt4().doubleValue());
        vo.setTaobillAmt5(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt5()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt5().doubleValue());
        vo.setTaobillAmt6(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt6()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getBillAmt6().doubleValue());
        vo.setTaopayMonthAmt(applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getPayMonthAmt()==null?null:applyEntryVO.getAssetsInfoVO().getMerchantLoanInfoVO().getPayMonthAmt().doubleValue());
        
        //新加的内部负债率和总负债率 2017-10-18
        vo.setInternalDebtRatio(loanAuditEntity.getInternalDebtRatio()==null?null:loanAuditEntity.getInternalDebtRatio().doubleValue());
        vo.setTotalDebtRatio(loanAuditEntity.getTotalDebtRatio()==null?null:loanAuditEntity.getTotalDebtRatio().doubleValue());
        
        //查询对应产品总负债率
        BMSProduct product=applyDataManipulationService.findProductData(vo.getProductCd());
        logger.info("查询对应产品负债率--------------------"+gson.toJson(product));
        if(null!=product){
        	//vo.setProductRate(product.getRate()==null?null:product.getRate().doubleValue());产品费率取值逻辑改变放在下面几行代码从新取值
            vo.setProductLetterRate(product.getDebtRadio()==null?null:product.getDebtRadio().doubleValue());
        }
        //查询对应的产品费率
        Map<String,Object> loanMap=new HashMap<String,Object>();
        loanMap.put("loanNo", personHistoryLoanVO.getLoanNo());
        List<LoanBaseEntity> listLoanBase= loanBaseService.getByMap(loanMap);
        logger.info("查询对应产品费率--------------------"+gson.toJson(listLoanBase));
        if(null!=listLoanBase&&listLoanBase.size()>0){
        	String ifUser=listLoanBase.get(0).getIfPreferentialUser();
        	if(ifUser.equals("Y")){//优惠费率客户
        		String code=vo.getProductCd();
        		BMSProduct productRate=applyDataManipulationService.findProductData(code);
        		if(null!=productRate){
        			vo.setProductRate(product.getPreferentialRate()==null?null:product.getPreferentialRate().doubleValue());
        		}
        	}else{//不是优惠费率客户
        		String code=vo.getProductCd();
        		BMSProduct productRate=applyDataManipulationService.findProductData(code);
        		if(null!=productRate){
        			vo.setProductRate(product.getRate()==null?null:product.getRate().doubleValue());
        		}
        	}
        }
        
        
        
        
        
        logger.info("查看返回对象vo值-------------------"+gson.toJson(vo));
        //查看该笔申请单的申请类型是哪一种（NEW ,TOPUP ,RELOAD）
        if(vo.getApplyType().equals("NEW")||vo.getApplyType().equals("TOPUP")){
        	Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType(vo.getApplyType());
        	if(null!=returnMap){
        		vo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
            	vo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
        	}
        }else{//RELOAN
        	Map<String,Object> loanStatusMap=new HashMap<String,Object>();
        	loanStatusMap.put("idnum", vo.getIdNo());
        	//loanStatusMap.put("loanNo",applyInfoVO.getLoanNo());
        	logger.info("掉核心接口2-------------------开始"+System.currentTimeMillis());
        	HttpResponse responseHttp=coreHttpService.loanStatus(loanStatusMap);
        	logger.info("掉核心接口2-------------------开始"+System.currentTimeMillis());
        	Map<String,Object> contentMap= JsonUtils.decode(responseHttp.getContent(),Map.class);
        	logger.info("-------------申请类型为reloan返回MAP开始----------------"+contentMap.get("code"));
        	logger.info("-------------申请类型为reloan返回MAP开始----------------"+contentMap.get("msg"));
        	if(contentMap.get("code").equals("000000")){
        		if(!contentMap.get("msg").equals("该用户不在系统中")){
    				ObjectMapper mapperLoan = new ObjectMapper(); 
    				List<LoanMapperToEntity> listReadValues=null;
    				try {
    					JsonNode node = mapperLoan.readTree(responseHttp.getContent());  
        	        	JsonNode nodeString=node.get("loan");
    					JavaType javaType=mapperLoan.getTypeFactory().constructParametricType(ArrayList.class, LoanMapperToEntity.class);   
						listReadValues = mapperLoan.readValue(nodeString.toString(), javaType);
						logger.info("调用核心返回JSON转成实体listReadValues--------------------"+gson.toJson(listReadValues));
    					//System.out.println(listReadValues.get(0).getLoanNo());
    				} catch (Exception e) {
    					logger.info("-------------RELOAN状态的申请单装换集合实体失败----------------"+contentMap.get("loan").toString());
    					e.printStackTrace();
    				} 
    				
    				if(null!=listReadValues&&listReadValues.size()>0){
    					for(LoanMapperToEntity loanMapperToEntity:listReadValues){
    						if(loanMapperToEntity.getLoanState().equals("结清")){
    							int mouth=mouthDiff(new Date(),loanMapperToEntity.getCreateTime());
    		    				if(mouth<=3){//RELOAN_LT
    		    					Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType("RELOAN_LT");
    		    		        	vo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
    		    		        	vo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
    		    				}else{//RELOAN_GT
    		    					Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType("RELOAN_GT");
    		    		        	vo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
    		    		        	vo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
    		    				}
    							break;
    						}
    					}
    				}
    				
    				
        		}
			}else{
				logger.info("---------------------" +contentMap.get("code")+"||"+contentMap.get("msg"));
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);	
			}
        }
        
        
        vo.setOnUnclearedRate(getRateLoanUrl(personHistoryLoanVO.getName(), personHistoryLoanVO.getIdNo()));
       
        //设置CC等级  默认100
        if(null!=loanAuditEntity.getCcRuleSet()&&loanAuditEntity.getCcRuleSet().length()>0){
			String ruleSet=loanAuditEntity.getCcRuleSet();
			String[] releSets=ruleSet.split(",");
			List<String> ruleList=Arrays.asList(releSets);
			List<String> oldRuleList=new ArrayList<String>(ruleList);
			for(int y=oldRuleList.size();y<100;y++){
				oldRuleList.add("");
			}
			vo.setCcRuleSet(oldRuleList);
		}else{
			List<String> list = new ArrayList<String>();
			for(int i=0;i<100;i++){
				list.add("");
			}
			vo.setCcRuleSet(list);
		}
    	//设置系统建议核实收入
        if(null!=loanAuditEntity.getAdviceVerifyIncome()){
        	vo.setAnticipationIncome(Double.parseDouble(loanAuditEntity.getAdviceVerifyIncome().toString()));
        }
        
        //目前这边我们没有这个字段所以给一个默认日期
        vo.setFaimalyGrantDate(getDefaultDate());
        
        //对日期统一进行处理，没有的默认给99991232有的格式化成yyyyMMdd格式的日期
        SimpleDateFormat sdfConvert=new SimpleDateFormat("yyyyMMdd");
        try {
        vo.setEstateBuyDate(vo.getEstateBuyDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getEstateBuyDate())));
        vo.setCarBuyDate(vo.getCarBuyDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getCarBuyDate())));
        vo.setIssuerDate(vo.getIssuerDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getIssuerDate())));
        vo.setSetupShopDate(vo.getSetupShopDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getSetupShopDate())));
        vo.setGraduationDate(vo.getGraduationDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getGraduationDate())));
        vo.setSetupDate(vo.getSetupDate()==null?getDefaultDate():sdfConvert.parse(sdfConvert.format(vo.getSetupDate())));
        
        
        } catch (Exception e) {
        	logger.info("-------------转换日期成yyyyMMdd格式失败----------------"+e.getMessage());
		}
        
        //华征手机在网时长和实名认证 因为有华征需要的ID，其他的参数默认随便传就行了
        String longOnlineId=personInfoVO.getLongOnlineId();
        String realNameAuthId=personInfoVO.getRealNameAuthId();
        
        List<String> huazhengNetPlayLongList=new ArrayList<String>();
        List<String> huazhengNameLookList=new ArrayList<String>();
        
        if(null!=longOnlineId && !longOnlineId.equals("") && null!=realNameAuthId && !realNameAuthId.equals("")){
        	longOnlineId=longOnlineId.replace("{", "");
        	longOnlineId=longOnlineId.replace("}", "");
        	realNameAuthId=realNameAuthId.replace("{", "");
        	realNameAuthId=realNameAuthId.replace("}", "");
        	String[] longOnlineIdSplit=longOnlineId.split(",");
        	String[] phones1=longOnlineIdSplit[0].split(":");
        	String[] phones2=longOnlineIdSplit[1].split(":");
        	
        	String p1=phones1[0].replace("\"", "");
        	
        	String p2=phones2[0].replace("\"", "");
        	
        	
        	Map<String,String> tempMap = new HashMap<>();
     		tempMap.put("timestamp", ObjectUtils.toString(new Date().getTime()));
     		tempMap.put("idCard", personInfoVO.getIdNo());
     		tempMap.put("name", personInfoVO.getName());
     		tempMap.put("appNo", personHistoryLoanVO.getLoanNo());
     		tempMap.put("creatorId", "bms");
     		tempMap.put("isCheck", "true"); 
     		tempMap.put("sources", "bms");
     		tempMap.put("id", phones1[1].replaceAll("\"",""));
     		String times1=getMobileOnlineCellphone(tempMap,p1);
     		
     		
     		Map<String,String> tempMap1 = new HashMap<>();
     		tempMap1.put("timestamp", ObjectUtils.toString(new Date().getTime()));
     		tempMap1.put("idCard", personInfoVO.getIdNo());
     		tempMap1.put("name", personInfoVO.getName());
     		tempMap1.put("appNo", personHistoryLoanVO.getLoanNo());
     		tempMap1.put("creatorId", "bms");
     		tempMap1.put("isCheck", "true"); 
     		tempMap1.put("sources", "bms");
     		tempMap1.put("id", phones2[1].replaceAll("\"",""));
     		String times2=getMobileOnlineCellphone(tempMap1,p2);
     		
     		huazhengNetPlayLongList.add(times1);
     		huazhengNetPlayLongList.add(times2);
     		//------华征手机在网时长结束
     		
     		
     		
     		String[] realNameAuthIdSplit=realNameAuthId.split(",");
        	String[] AuthId1=realNameAuthIdSplit[0].split(":");
        	String[] AuthId2=realNameAuthIdSplit[1].split(":");
        	
        	String a1=AuthId1[0].replace("\"", "");
        	
        	String a2=AuthId2[0].replace("\"", "");
        	
        	
        	Map<String,String> auth1 = new HashMap<>();
        	auth1.put("timestamp", ObjectUtils.toString(new Date().getTime()));
        	auth1.put("idCard", personInfoVO.getIdNo());
        	auth1.put("name", personInfoVO.getName());
        	auth1.put("appNo", personHistoryLoanVO.getLoanNo());
        	auth1.put("creatorId", "bms");
        	auth1.put("isCheck", "true"); 
        	auth1.put("sources", "bms");
        	auth1.put("id", AuthId1[1].replaceAll("\"",""));
     		String auths1=getIdCardCheckCellphone(auth1,a1);
     		
     		
     		Map<String,String> auth2 = new HashMap<>();
     		auth2.put("timestamp", ObjectUtils.toString(new Date().getTime()));
     		auth2.put("idCard", personInfoVO.getIdNo());
     		auth2.put("name", personInfoVO.getName());
     		auth2.put("appNo", personHistoryLoanVO.getLoanNo());
     		auth2.put("creatorId", "bms");
     		auth2.put("isCheck", "true"); 
     		auth2.put("sources", "bms");
     		auth2.put("id", AuthId2[1].replaceAll("\"",""));
     		String auths2=getIdCardCheckCellphone(auth2,a2);
     		
     		huazhengNameLookList.add(auths1);
     		huazhengNameLookList.add(auths2);
     		//------华征手机实名认证结束
        }
		vo.setHuazhengNetPlayLong(huazhengNetPlayLongList);
		vo.setHuazhengNameLook(huazhengNameLookList);
        
        
        
        
        long end=System.currentTimeMillis();
        logger.info("结束计时--------------"+end);
        responseYBR.setData(vo);
		return responseYBR;
	}
	
	
	/**
	 * 获取在网时长
	 */
	public String getMobileOnlineCellphone(Map<String,String> tempMap,String cellphone){
		Map<String,String> mobileOnlineIDMap = new HashMap<>();
		tempMap.put("mobile", cellphone);
		tempMap.put("mobileService", getMobileOperators(cellphone));
		mobileOnlineIDMap.put("param", new JSONObject(tempMap).toString());
		String hzMobileOnlineID = queryHZMobileOnlineInfo(mobileOnlineIDMap);
		return hzMobileOnlineID;
	}
	
	/**
	 * 获取实名认证
	 */
	public String getIdCardCheckCellphone(Map<String,String> tempMap,String cellphone){
		Map<String,String> idCardCheckIDMap = new HashMap<>();
		tempMap.put("mobile", cellphone);
		tempMap.put("mobileService", getMobileOperators(cellphone));
		idCardCheckIDMap.put("param", new JSONObject(tempMap).toString());
		String hzIdCardCheckID = queryHZIdCardCheckInfo(idCardCheckIDMap);
		return hzIdCardCheckID;
	}
	
	
	/**
	 * 获取手机运营商
	 * (手机运营商:1：移动，2：联通，3：电信)
	 * @param phone
	 */
	public String getMobileOperators(String phone){
		String result = "";
		//移动
		String chinaMobile = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
		//联通
		String chinaUnicom = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";
		//电信
		String chinaTelcom = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";
		
		if(phone.matches(chinaMobile)){
			result = "1";
		}
		if(phone.matches(chinaUnicom)){
			result = "2";
		}
		if(phone.matches(chinaTelcom)){
			result = "3";
		}
		return result;
	}
	
	
	
	/**
	 * 获取华征手机在网时长报告
	 * 
	 */
	public String queryHZMobileOnlineInfo(Map<String,String> paramsMap){
		String times = "";
		try {
			logger.info("【华征手机在网时长】请求入参：" + paramsMap);
			JSONObject obj = creditHttpServiceImpl.postBaseMethod(queryHZMobileOnlineInfo, paramsMap);
			if(null == obj){
				logger.info("【华征手机在网时长】返回为null");
			}else{
				logger.info("【华征手机在网时长】响应结果：" + obj.toString());
				if("000000".equals(obj.getString("code"))){
					JSONArray result = obj.getJSONArray("result");
					JSONArray data=result.getJSONObject(0).getJSONArray("data");
					times=data.getJSONObject(0).getString("times");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【华征手机在网时长】异常：" + e);
		}
		return times;
	}
	
	
	
	/**
	 * 获取华征实名认证报告
	 */
	public String queryHZIdCardCheckInfo(Map<String,String> paramsMap){
		String report = "";
		try {
			logger.info("【华征实名认证】请求入参：" + paramsMap);
			JSONObject obj = creditHttpServiceImpl.postBaseMethod(queryHZIdCardCheckInfo, paramsMap);
			if(null == obj){
				logger.info("【华征实名认证】返回为null");
			}else{
				logger.info("【华征实名认证】响应结果：" +  obj.toString());
				if("000000".equals(obj.getString("code"))){
					JSONArray result = obj.getJSONArray("result");
					JSONArray data=result.getJSONObject(0).getJSONArray("data");
					report=data.getJSONObject(0).getString("userName");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【华征实名认证】异常：" + e);
		}
		return report;
	}
	
	
	
	/**
	 * 	//根据姓名身份证掉核心借口查询费率
	 * @return
	 */
	public Double getRateLoanUrl(String name,String idNo){
		 try {
			//根据姓名身份证掉核心借口查询费率
			Map<String, Object> map = new HashMap<String, Object>();
			//map.put("name", name);
			map.put("idnum", idNo);
			HttpResponse rateRepsonse = coreHttpService.getRateLoanUrl(map);
			Map<String, Object> contentMap = JsonUtils.decode(rateRepsonse.getContent(), Map.class);
			logger.info("-------------吊用核心根据姓名身份证查询费率借口----------------" + contentMap.get("code"));
			logger.info("-------------吊用核心根据姓名身份证查询费率借口----------------" + contentMap.get("message"));
			if (contentMap.get("code").equals("000000")) {
				List<String> loanStates=(List<String>) contentMap.get("loanStates");
				List<Double> rates=(List<Double>) contentMap.get("rates");
				if(null!=loanStates&&loanStates.size()>0){
					for(int i=0;i<loanStates.size();i++){
						if(loanStates.get(i).equals("正常")||loanStates.get(i).equals("逾期")){
							return rates.get(i);
						}
					}
				}else{
					return new Double("0");
				}
//				Double rate = (Double) contentMap.get("rate");
//				return rate;
			} 
		} catch (Exception e) {
			logger.info("调用核心异常"+e.getMessage());
			return new Double("0");
		}
		 return new Double("0");
		
	}
	
	
	

	public Integer mouthDiff(Date str1,String str2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(str1);
		try {
			aft.setTime(sdf.parse(str2));
		} catch (java.text.ParseException e) {
			logger.info("-----------转换两个日期是否小于或者大于三个月出问题----------"+str2);
			e.printStackTrace();
		}
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		Integer mouth=(Math.abs(month + result));
		return mouth;
	}
	
	
	

	
	/**
	 * 查询用户的历史借款信息
	 * 
	 * @param name
	 * @param idNo
	 */
	public FindHisVO findHistoryLoanTwo(String name, String idNo) {
		FindHisVO resFindHis = new FindHisVO();

		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idNo)) {
			return resFindHis;
		}

		Map<String, Object> httpParamMap = new HashMap<String, Object>();
		httpParamMap.put("name", name);
		httpParamMap.put("idnum", idNo);
		HttpResponse findHistoryLoan;
		try {
			findHistoryLoan = coreHttpService.findHistoryLoan(httpParamMap);
			logger.info("" + gson.toJson(findHistoryLoan));
			Map<String, Object> contentMap = JsonUtils.decode(findHistoryLoan.getContent(), Map.class);
			if (contentMap.get("code").equals("000000")) {
				if (contentMap.get("message").equals("该用户不在系统中")) {
					logger.info("查询历史数据返回  该用户不在系统中");
					List<Integer> repaymentTerm = new ArrayList<Integer>();
					repaymentTerm.add(0);
					repaymentTerm.add(0);
					repaymentTerm.add(0);
					resFindHis.setRepaymentTerm(repaymentTerm);// 借款期数
					return resFindHis;
				} else {
					FindHistoryLoanVO decode = JsonUtils.decode(gson.toJson(contentMap.get("infos")), FindHistoryLoanVO.class);
					logger.info("查询历史数据返回 :" + gson.toJson(decode));
					resFindHis.setIfHaveHistoryApply("Y");// 是否有借款数据
					resFindHis.setIfHaveBreaks(decode.getIfHaveBreaks());// 上笔借款是否减免
					resFindHis.setRepaymentTerm(Arrays.asList(decode.getBorrowingTerm()));// 借款期数
					resFindHis.setGrantMoney(Arrays.asList(decode.getGrantMoney()));//放款金额
					List<Date> realityRepaymentDate = new ArrayList<Date>();// 实际还款日期
					List<Date> shouldRepaymentDate = new ArrayList<Date>();// 应还款日期
					List<Integer> repaymentStatue = new ArrayList<Integer>();// 还款状态
					if (decode.getLoanList() != null && decode.getLoanList().size() > 0) {
						for (RepayListVo repayListVo : decode.getLoanList()) {
							if (repayListVo.getRepayList() != null && repayListVo.getRepayList().size() > 0) {
								for (RepayVo repayVo : repayListVo.getRepayList()) {
									realityRepaymentDate.add(repayVo.getRealityRepaymentDate() == null ? getDefaultDate() : strToDate(repayVo.getRealityRepaymentDate().replace("-", ""), DateUtil.pattern_day));
									shouldRepaymentDate.add(repayVo.getShouldRepaymentDate() == null ? getDefaultDate() : strToDate(repayVo.getShouldRepaymentDate().replace("-", ""), DateUtil.pattern_day));
									repaymentStatue.add(repayVo.getRepaymentStatue() == null ? null : repayVo.getRepaymentStatue());
								}
							}
						}
					}
					resFindHis.setRealityRepaymentDate(realityRepaymentDate);
					resFindHis.setShouldRepaymentDate(shouldRepaymentDate);
					resFindHis.setRepaymentStatue(repaymentStatue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用核心查询历史数据异常，异常信息" + e.getMessage());
			List<Integer> repaymentTerm = new ArrayList<Integer>();
			repaymentTerm.add(0);
			repaymentTerm.add(0);
			repaymentTerm.add(0);
			resFindHis.setRepaymentTerm(repaymentTerm);// 借款期数
			return resFindHis;
		}
		return resFindHis;
	}


	

	@Override
	public Response<Object> auditUpdaetRulesData(AuditRulesVO auditRulesVO) {
		if(StringUtils.isBlank(auditRulesVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		Response<Object> res = new Response<Object>();
		LoanAuditEntity arEntity = new LoanAuditEntity();
		BeanUtils.copyProperties(auditRulesVO, arEntity);
		
		if(auditRulesVO.getAdviceAuditLines() == null
				&& auditRulesVO.getAdviceVerifyIncome() == null
				&& auditRulesVO.getInternalDebtRatio() == null
				&& auditRulesVO.getTotalDebtRatio() == null
				&& auditRulesVO.getScorecardScore() == null
				&& auditRulesVO.getCcRuleSet() == null
				&& auditRulesVO.getIsAntifraud() == null){
			res.setRepCode("000001");
			res.setRepMsg("不能全部传空!");
			return res;
		}
		
		
		Integer updaeAudittRulesData = iLoanAuditService.updaeAudittRulesData(arEntity);
		if(updaeAudittRulesData != 1){
			res.setRepCode("000001");
			res.setRepMsg("更新失败!");
			return res;
		}
		return res;
	}

	@Override
	public Response<AuditRulesVO> queryAuditRulesData(AuditRulesVO auditRulesVO) {
		if(StringUtils.isBlank(auditRulesVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		Response<AuditRulesVO> res = new Response<AuditRulesVO>();
		AuditRulesVO resEntity = new AuditRulesVO();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", auditRulesVO.getLoanNo());
		LoanAuditEntity by = iLoanAuditService.getBy(paramMap);
		if(by != null){
			BeanUtils.copyProperties(by, resEntity);
		}
		
		res.setData(resEntity);
		return res;
	}
	
	
	/**
	 * 获取电销是否在已分派名单(电销)
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String getSalesman(String name,String idNum) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();
		String msg = "N";
		try {
//			线上暂时不上
			
			
			appDataMap.put("idNum", idNum);// 身份证
			appDataMap.put("name", name);// 用户名
			logger.info("获取电销是否在已分派名单 入参:"+gson.toJson(appDataMap));
			HttpResponse resObject = creditHttpService.getSalesman(appDataMap);
			logger.info("获取电销是否在已分派名单 出参:"+gson.toJson(resObject));
			if(resObject.getCode() == 200){
				Map<String, Object> contentMap = JsonUtils.decode(resObject.getContent(), Map.class);
				if(contentMap.get("flag") != null){
					msg = contentMap.get("flag").toString();
				}
			}
		} catch (Exception e) {
			logger.info("---------------获取电销是否在已分派名单异常：" + e.getMessage());
			return "N";
		}
		return msg;
	}
	
	
	/**
	 * 获取电销是否在已分派名单(电销)
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String getSalesman2(String name,String idNum) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();
		String msg = "N";
		try {
//			线上暂时不上
			
			
			appDataMap.put("idNum", idNum);// 身份证
			appDataMap.put("name", name);// 用户名
			logger.info("获取电销是否在已分派名单 入参:"+gson.toJson(appDataMap));
			HttpResponse resObject = creditHttpService.getSalesman2(appDataMap);
			logger.info("获取电销是否在已分派名单 出参:"+gson.toJson(resObject));
			if(resObject.getCode() == 200){
				Map<String, Object> contentMap = JsonUtils.decode(resObject.getContent(), Map.class);
				if(contentMap.get("flag") != null){
					msg = contentMap.get("flag").toString();
				}
			}
		} catch (Exception e) {
			logger.info("---------------获取电销是否在已分派名单异常：" + e.getMessage());
			return "N";
		}
		return msg;
	}
	
	/**
	 * 获取是否是优质客户(电销)
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String personValidate(String idNum) {
		Map<String, Object> appDataMap = new HashMap<String, Object>();
		String msg = "N";
		try {
			//线上暂时不上
			
			
			
			appDataMap.put("idNum", idNum);// 身份证
			logger.info("获取是否是优质客户(电销) 入参:"+gson.toJson(appDataMap));
			HttpResponse resObject = creditHttpService.personValidate(appDataMap);
			logger.info("获取是否是优质客户(电销) 出参:"+gson.toJson(resObject));
			if(resObject.getCode() == 200){
				Map<String, Object> contentMap = JsonUtils.decode(resObject.getContent(), Map.class);
				if(contentMap.get("flag") != null){
					Boolean bol = (Boolean) contentMap.get("flag");
					if(bol){
						msg = "Y";
					}
				}
			}
		} catch (Exception e) {
			logger.info("---------------获取是否是优质客户(电销)异常：" + e.getMessage());
			return "N";
		}
		return msg;
	}
	
	//老政审转新系统RTF_STATE
	public String OldConvertNewNtfState(String oldState){
		String old="A05,A10,A15,A20,B05";
		String old1="B08,B09,B10,B15";
		return "";
	}
	
	
	/**
	 * 获取老政审最近一次取消和拒绝的时间和拒绝的限制天数
	 * 
	 * @param name
	 * @param idNo
	 */
	public Map<String,Object> queryOldRefuseAndCancelAndLimitDaysData(String idNo) {

	
		logger.info("获取老政审最近一次取消和拒绝的时间和拒绝的限制天数:::::::::::;:" );

		Map<String, Object> cMap = null;
		try {
			Map<String, Object> appDataMap = new HashMap<String, Object>();
			appDataMap.put("idNo", idNo);// 身份证
			HttpResponse thirdPartyInfo = creditHttpService.queryOldRefuseAndCancelAndLimitDaysData(appDataMap);
			logger.info("获取老政审最近一次取消和拒绝的时间和拒绝的限制天数：" + gson.toJson(thirdPartyInfo));
			if (thirdPartyInfo != null && thirdPartyInfo.getCode() == 200) {

				cMap = JsonUtils.decode(thirdPartyInfo.getContent(), Map.class);
				logger.info("获取老政审最近一次取消和拒绝的时间和拒绝的限制天数MAP：" + gson.toJson(cMap));
			}
		} catch (Exception e) {
			logger.info("获取老政审最近一次取消和拒绝的时间和拒绝的限制天数：" + e.getMessage());
			return cMap;
		}
		return cMap;
	}


//	@Override
//	public Response<ResContractSignVO> queryContractAwardRetuenYBR(ContractSignInfoVO contractSignInfoVO) {
//		Response<ResContractSignVO> res = new Response<ResContractSignVO>();
//
//		ResContractSignVO signVo = new ResContractSignVO();
//		// 借款编号
//		if (StringUtils.isEmpty(contractSignInfoVO.getLoanNo())) {
//			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "loanNo" });
//		}
//		// 身份证
//		if (StringUtils.isEmpty(contractSignInfoVO.getIdNo())) {
//			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "idNo" });
//		}
//		// 姓名
//		if (StringUtils.isEmpty(contractSignInfoVO.getName())) {
//			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "name" });
//		}
//		
//		Map<String,Object> allUserfulInfoData=loanBaseService.queryContractInfo(contractSignInfoVO.getLoanNo());
//		logger.info("获取返回签约规则查询数据库表数据--------------:" + gson.toJson(allUserfulInfoData));
//		signVo.setAppApplyInput(allUserfulInfoData.get("appInputFlag")!= null ? "Y" : "N");
//		signVo.setOwningBranchId((String) allUserfulInfoData.get("enterBranchId"));
//		APPCurrentApplyEntity quertyOrgInfoByID = quertyOrgInfoByID(Long.parseLong(allUserfulInfoData.get("enterBranchId").toString()));
//		logger.info("获取返回签约规则查询进件营业部城市，区域，分部--------------:" + gson.toJson(quertyOrgInfoByID));
//		signVo.setOwningBranchCity(quertyOrgInfoByID.getOwningBranchCity());
//		signVo.setOwningBranchDivision(quertyOrgInfoByID.getOwningBranchDivision());
//		signVo.setOwningBranchArea(quertyOrgInfoByID.getOwningBranchArea());
//		signVo.setApplyType((String) allUserfulInfoData.get("applyType"));
//		
//		
//		//查看该笔申请单的申请类型是哪一种（NEW ,TOPUP ,RELOAD）
//        if(signVo.getApplyType().equals("NEW")||signVo.getApplyType().equals("TOPUP")){
//        	Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType(signVo.getApplyType());
//        	if(null!=returnMap){
//        		signVo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
//        		signVo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
//        	}
//        }else{//RELOAN
//        	Map<String,Object> loanStatusMap=new HashMap<String,Object>();
//        	loanStatusMap.put("idnum", contractSignInfoVO.getIdNo());
//        	logger.info("掉核心接口-------------------开始"+System.currentTimeMillis());
//        	HttpResponse responseHttp=coreHttpService.loanStatus(loanStatusMap);
//        	logger.info("掉核心接口-------------------开始"+System.currentTimeMillis());
//        	Map<String,Object> contentMap= JsonUtils.decode(responseHttp.getContent(),Map.class);
//        	logger.info("-------------申请类型为reloan返回MAP开始----------------"+contentMap.get("code"));
//        	logger.info("-------------申请类型为reloan返回MAP开始----------------"+contentMap.get("msg"));
//        	if(contentMap.get("code").equals("000000")){
//        		if(!contentMap.get("msg").equals("该用户不在系统中")){
//    				ObjectMapper mapperLoan = new ObjectMapper(); 
//    				List<LoanMapperToEntity> listReadValues=null;
//    				try {
//    					JsonNode node = mapperLoan.readTree(responseHttp.getContent());  
//        	        	JsonNode nodeString=node.get("loan");
//    					JavaType javaType=mapperLoan.getTypeFactory().constructParametricType(ArrayList.class, LoanMapperToEntity.class);   
//						listReadValues = mapperLoan.readValue(nodeString.toString(), javaType);
//						logger.info("调用核心返回JSON转成实体listReadValues--------------------"+gson.toJson(listReadValues));
//    				} catch (Exception e) {
//    					logger.info("-------------RELOAN状态的申请单装换集合实体失败----------------"+contentMap.get("loan").toString());
//    					e.printStackTrace();
//    				} 
//    				
//    				if(null!=listReadValues&&listReadValues.size()>0){
//    					for(LoanMapperToEntity loanMapperToEntity:listReadValues){
//    						if(loanMapperToEntity.getLoanState().equals("结清")){
//    							int mouth=mouthDiff(new Date(),loanMapperToEntity.getCreateTime());
//    		    				if(mouth<=3){//RELOAN_LT
//    		    					Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType("RELOAN_LT");
//    		    					signVo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
//    		    					signVo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
//    		    				}else{//RELOAN_GT
//    		    					Map<String,Object> returnMap=applyDataManipulationService.findRadioByApplyType("RELOAN_GT");
//    		    					signVo.setApplyTypeLetterRate(((BigDecimal)returnMap.get("totalDebtRadio")).doubleValue());//总
//    		    					signVo.setApplyTypeInsideRate(((BigDecimal)returnMap.get("internalDebtRadio")).doubleValue());//内部
//    		    				}
//    							break;
//    						}
//    					}
//    				}
//    				
//    				
//        		}
//			}else{
//				logger.info("---------------------" +contentMap.get("code")+"||"+contentMap.get("msg"));
//				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);	
//			}
//        }
//        
//        signVo.setIdNo((String) allUserfulInfoData.get("idNo"));
//        String black=getBlackList(contractSignInfoVO.getName(),contractSignInfoVO.getIdNo());
//        logger.info("----------查询黑名单--------------"+gson.toJson(black));
//        signVo.setNameAndidNoIsBlack(black);
//		signVo.setApprovalProductCd((String) allUserfulInfoData.get("productCd"));
//        
//		
//		//查询对应产品总负债率
//        BMSProduct product=applyDataManipulationService.findProductData(signVo.getApprovalProductCd());
//        logger.info("查询对应产品负债率--------------------"+gson.toJson(product));
//        if(null!=product){
//        	signVo.setApprovalProductTotalDebtRatio(product.getDebtRadio()==null?null:product.getDebtRadio().doubleValue());
//        	signVo.setApprovalProductRate(product.getRate()==null?null:product.getRate().doubleValue());
//        	signVo.setProductPreferredRate(product.getPreferentialRate()==null?null:product.getPreferentialRate().doubleValue());
//        }
//       
//        
//        //取信审系统接口返回的字段
//        logger.info("吊审核接口开始计时-------------------"+System.currentTimeMillis());
//        PersonHistoryLoanVO personHistoryLoanVO=new PersonHistoryLoanVO();
//        personHistoryLoanVO.setLoanNo(contractSignInfoVO.getLoanNo());
//        ApprovalOpinionsVO returnVo=httpToTheLetter(personHistoryLoanVO);
//        logger.info("吊审核接口结束计时-------------------"+System.currentTimeMillis());
//        signVo.setAnticipationIncome(returnVo.getApprovalCheckIncome());
//        signVo.setTotalCreditLiabilities(returnVo.getCreditLoanDebt());
//        
//        // 查看审批表
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//		Map<String, Object> paramMap1 = new HashMap<String, Object>();
//		paramMap1.put("loanNo",contractSignInfoVO.getLoanNo());
//		LoanAuditEntity loanAuditEntitySelect = iLoanAuditService.getBy(paramMap1);
//		if(loanAuditEntitySelect != null){
//			try {
//				signVo.setCommitDate(sdf.parse(sdf.format(loanAuditEntitySelect.getCreated_time())));
//			} catch (ParseException e) {
//				logger.info("获取提交时间转换日期出错-------------------");
//			}
//		}
//		
//		signVo.setApprovalLimit((Integer) allUserfulInfoData.get("accLmt"));
//		signVo.setClientManager((String) allUserfulInfoData.get("branchManagerCode"));
//		signVo.setIsPreferredRate((String) allUserfulInfoData.get("ifPerferentialUser"));
//		//首次进入合同签约环节日期
//		BMSSysLoanLog sysLog=sysLoanLogService.findFirstOldPassTime(contractSignInfoVO.getLoanNo());
//		try {
//			signVo.setFirstContractSignDate(sdf.parse(sdf.format(sysLog.getOperationTime())));
//		} catch (ParseException e) {
//			logger.info("获取操作时间转换日期出错-------------------");
//		}
//		
//		logger.info("~~~~-------------------------------------------");
//		
//		signVo.setSignBranchId((String) allUserfulInfoData.get("contractBranchId"));
//		APPCurrentApplyEntity quertyOrgContractInfo = quertyOrgInfoByID(Long.parseLong(allUserfulInfoData.get("contractBranchId").toString()));
//		logger.info("获取返回签约规则查询签约营业部城市，区域，分部--------------:" + gson.toJson(quertyOrgContractInfo));
//		signVo.setSignBranchCity(quertyOrgContractInfo.getOwningBranchCity());
//		signVo.setSignBranchDivision(quertyOrgContractInfo.getOwningBranchDivision());
//		signVo.setSignBranchArea(quertyOrgContractInfo.getOwningBranchArea());
//		
//		//系统配置可选渠道    该字段和胡文峰商讨之后，直接他们页面自己直接取值
//        
//		//取核心计算的  合同金额  页面所选渠道剩余合同金额  所有渠道剩余合同金额（暂时还没弄）
//		
//		
//		//银行卡号是否与他人一致
//		String bankNo=contractSignInfoVO.getBankNo();
//		
//		//吊核心返回信息
//		FindHisVO findHistoryLoan = findHistoryLoan(contractSignInfoVO.getName(), contractSignInfoVO.getIdNo());
//		logger.info("调用核心获取历史借款数据，结果" + gson.toJson(findHistoryLoan));
//		signVo.setIfHaveBreaks(findHistoryLoan.getIfHaveBreaks());
//		signVo.setRepaymentStatue(findHistoryLoan.getRepaymentStatue());
//		signVo.setRepaymentTerm(findHistoryLoan.getRepaymentTerm());
//		signVo.setRealityRepaymentDate(findHistoryLoan.getRealityRepaymentDate());
//		signVo.setShouldRepaymentDate(findHistoryLoan.getShouldRepaymentDate());
//		
//		
//		//非工作日日期 
//		List<String> holidays = new ArrayList<String>();
//		List<String> queryHolidays = queryHolidays();
//		holidays.add(queryHolidays.size() + "");
//		holidays.addAll(queryHolidays);
//        signVo.setHolidays(holidays);
//        
//        
//        res.setData(signVo);
//		return res;
//	}

}