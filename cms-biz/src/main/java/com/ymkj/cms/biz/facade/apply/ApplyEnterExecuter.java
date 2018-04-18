package com.ymkj.cms.biz.facade.apply;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.cms.biz.api.enums.EnumCHConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.*;
import com.ymkj.cms.biz.api.vo.response.apply.ResCheckNewDataVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResLoanBaseVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.common.util.RedisUtil;
import com.ymkj.cms.biz.dao.apply.LoanBaseDao;
import com.ymkj.cms.biz.entity.apply.*;
import com.ymkj.cms.biz.entity.apply.input.ApplyInfoVOEntity;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
import com.ymkj.cms.biz.entity.master.*;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.*;
import com.ymkj.cms.biz.service.audit.BMSAPPHistoryService;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;
import com.ymkj.cms.biz.service.http.CreditHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.*;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 服务提供者:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4.  
 *  
 *		
 */
@Service
public class ApplyEnterExecuter implements IApplyEnterExecuter  {
	public Date defaultDate;
	private static final Logger logger = LoggerFactory.getLogger(ApplyEnterExecuter.class);
	private Gson gson = new Gson();
	
	@Autowired
	private APPCardLoanInfoService appCardLoanInfoService;
	@Autowired
	private APPCarInfoService appCarInfoService;
	@Autowired
	private APPContactInfoService appContactInfoService;
	@Autowired
	private APPEstateInfoService appEstateInfoService;
	@Autowired
	private APPMasterLoanInfoService appMasterLoanInfoService;
	@Autowired
	private APPMerchantLoanInfoService appMerchantLoanInfoService;
	@Autowired
	private APPPersonInfoService appPersonInfoService;
	@Autowired
	private APPPersonService appPersonService;
	@Autowired
	private APPPolicyInfoService appPolicyInfoService;
	@Autowired
	private APPProvidentInfoService appProvidentInfoService;
	@Autowired
	private APPSalaryInfoService appSalaryInfoService;
	@Autowired
	private LoanBaseService loanBaseService;
	@Autowired
	private LoanExtService loanExtService;
	@Autowired
	private LoanProductService loanProductService;
	@Autowired
	private  IBMSParameterService  parameterService;
	@Autowired
	private  BMSAPPHistoryService  aPPHistoryService;
	@Autowired
	private  IBMSRejectReasonService  rejectReasonService;
	@Autowired
	private  LoanBaseRelaService  loanBaseRelaService;
	@Autowired
	private IBMSEnumCodeService demoService;
	@Autowired
	private ILoanAuditService iLoanAuditService;
	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private LoanChangeLogService loanChangeLogService;
	
	@Autowired
	private IBMSOrgLimitChannelService ibmsOrgLimitChannelService;
	
	@Autowired
	private APPContactHeadService aPPContactHeadService;
	
	@Autowired
	private CreditHttpService creditHttpService;
	
	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;
	
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	
	@Autowired
	private IBMSOrgLimitChannelService orgLimitChannelService;
	
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private IBlackListExecuter blackListExecuter;
	
//	@Autowired
//	private IApplyValidateExecuter iApplyValidateExecuter;
	
	
	@Autowired
	private LoanBaseDao loanBaseDao;
	
//	@Value("${checkUrl}")
//	private String checkUrl;
	
	@Override
	public Response<ReqApplyEntryVO> saveOrUpdate(ApplyEntryVO applyEntryVO) {
		
		logger.info("111111111111：saveOrUpdate开始");

		//申请信息
		if(applyEntryVO.getApplyInfoVO() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"applyInfoVO"});
		}
		
		
		ApplyInfoVOEntity applyInfoVOEntity = new ApplyInfoVOEntity();
		BeanUtils.copyProperties(applyEntryVO.getApplyInfoVO(), applyInfoVOEntity);
		
		Response<ReqApplyEntryVO> applyInfoInfo = Validate.getInstance().validate(applyInfoVOEntity);
		if (!applyInfoInfo.isSuccess()) {
			logger.info("ApplyInfoVO 参数 规则验证: 校验不通过,原因："+applyInfoInfo.getRepMsg());
			return applyInfoInfo;
		}
		
		//直通车进件   校验签约营业部
		if(applyEntryVO.getApplyInfoVO().getApplyInputFlag().equals("directApplyInput")){
			//签约营业部
			if(org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getApplyInfoVO().getContractBranch())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ApplyInfoVO.contractBranch"});
			}
			//签约营业部Id
			if(applyEntryVO.getApplyInfoVO().getContractBranchId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ApplyInfoVO.contractBranchId"});
			}
		}
		
		//APP进件，app申请时间比传
		if(applyEntryVO.getApplyInfoVO().getAppInputFlag() != null && 
				applyEntryVO.getApplyInfoVO().getAppInputFlag().equals("app_input")){
			if(applyEntryVO.getApplyInfoVO().getAppinputDate() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ApplyInfoVO.appinputDate"});
			}
		}
		
		
		//操作模块
		if(org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getOptionModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"OptionModule"});
		}
		//操作类型
		if(org.apache.commons.lang.StringUtils.isBlank(applyEntryVO.getOptionType())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"OptionType"});
		}
		
		
		//记录日志
		this.insetSysLog(applyEntryVO);
		
		logger.info("111111111111：applyDataManipulationService.save(applyEntryVO);");

		/**
		 * 判断新增还是修改
		 */
		if(applyEntryVO.getApplyInfoVO().getLoanBaseId() == null 
				|| applyEntryVO.getApplyInfoVO().getLoanBaseId() == 0){
			logger.info("loanBaseId is null ,leave for sava");
			return applyDataManipulationService.save(applyEntryVO);
		}else{
			Response<ReqApplyEntryVO> res = new Response<ReqApplyEntryVO>();
			
			//判断redis缓存中是否有这个操作
			if(redisUtil.exists("saveOrUpdate_"+applyEntryVO.getApplyInfoVO().getLoanBaseId())){
				res.setRepMsg("目前这笔借款还未全部执行完成，无法重复操作！");
				res.setRepCode("000001");
				return res;
			}
			
			try {
				//将此操作添加到缓存里面  30s超时 防止重复提交
				boolean redisSet = redisUtil.set("saveOrUpdate_"+applyEntryVO.getApplyInfoVO().getLoanBaseId(), 
						applyEntryVO.getApplyInfoVO().getLoanBaseId(), 
						30L);
			
				logger.info("将数据缓存到redis["+redisSet+"]");
				logger.info("开始进行修改数据。。。。。。。。。。。。。。");
				logger.info("111111111111：applyDataManipulationService.update(applyEntryVO);");

				return applyDataManipulationService.update(applyEntryVO);
			} catch (Exception e) {
				logger.info("录单环节报错，错误信息是："+e.getLocalizedMessage());
				res.setRepCode("000001");
				res.setRepMsg(e.getMessage());
				return res;
			} finally {
				logger.info(applyEntryVO.getApplyInfoVO().getLoanBaseId() + "操作完成，删除redis里面的操作!!");
				redisUtil.remove("saveOrUpdate_"+applyEntryVO.getApplyInfoVO().getLoanBaseId());
			}
			
		}
	}
	
	public void insetSysLog(ApplyEntryVO applyEntryVO){
		String OptionModule = "";
		if(applyEntryVO.getOptionModule().equals("1")){
			OptionModule = "申请录入";
		}else if(applyEntryVO.getOptionModule().equals("2")){
			OptionModule = "录入修改 ";
		}else if(applyEntryVO.getOptionModule().equals("3")){
			OptionModule = "录入复核";
		}else if(applyEntryVO.getOptionModule().equals("10")){
			OptionModule = "退件箱办理";
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"optionModule","1,2,3,10"});
		}
		
		String OptionType = "";
		if(applyEntryVO.getOptionType().equals("101")){
			OptionType= "提交";
		}else if(applyEntryVO.getOptionType().equals("102")){
			OptionType="保存";
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"optionType","101,102"});
		}
		
		//系统日志
		ibmsSysLogService.recordSysLog(applyEntryVO, 
										"录单系统|"+OptionModule+"|"+OptionModule+"|"+OptionType+"",
										"IApplyEnterExecuter/saveOrUpdate",
										"saveOrUpadate借款信息");
	}
 
	
	@SuppressWarnings("unchecked")
	public Response<Object> validateNameIdNo(ValidateNameIdNoVO validateNameIdNoVO){
		 Response<Object> response = new Response<Object>();
		//1 验证参数
	 
		ValidateNameIdNoEntity validateNameIdNoEntity=new ValidateNameIdNoEntity();
		BeanUtils.copyProperties(validateNameIdNoVO, validateNameIdNoEntity);		
		Response<Object> responseValidateNameIdNoEntity = Validate.getInstance().validate(validateNameIdNoEntity);			
		if (!responseValidateNameIdNoEntity.isSuccess()) {
			return responseValidateNameIdNoEntity;
		}
		 //2 根据name和idNo判断是否新用户
		 Map<String, Object>  paramMap=new HashMap<String, Object>();
		 paramMap.put("name", validateNameIdNoEntity.getName());
		 paramMap.put("IdNo", validateNameIdNoEntity.getIdNo());
		 APPPersonEntity  person= (APPPersonEntity) appPersonService.getBy(paramMap);
		 if(person==null){
				return response;
		 }
		 //3 不是新用户  查出上一笔拒绝 取消借款 或者 未结清的状态
		 Map<String, Object>  loanMap=new HashMap<String, Object>();
		 paramMap.put("personId", person.getId());
		 List<String> statusList=new ArrayList<String>();
		 statusList.add(EnumConstants.LoanStatus.CANCEL.getValue());
		 statusList.add(EnumConstants.LoanStatus.REFUSE.getValue());
		 paramMap.put("statusList", statusList );
		 List<String> notStatusList=new ArrayList<String>();
		 notStatusList.add(EnumConstants.LoanStatus.NORMAL.getValue());
		 paramMap.put("notStatusList", notStatusList );
		 LoanBaseEntity  loanBaseEntity= (LoanBaseEntity) loanBaseService.getBy(loanMap,"getLatelyLoanByPersonIdAndStatus");
		 //4 有未结清的借款 提示：
		 if(!(loanBaseEntity.getStatus().equals(EnumConstants.LoanStatus.CANCEL.getValue())|| loanBaseEntity.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue()))){
				throw new BizException(BizErrorCode.EXISTING_APPLY_LOAN, new Object[]{}
						);
		 }
		 //5 上次拒绝取消借款 ,未满拒贷 取消再次提交天数,同门店再次申请 提示:---------  ,+前次取消原因
		if( validateNameIdNoEntity.getOwningBranchId().equals(loanBaseEntity.getOwningBranchId())){
			// 参数表获取拒绝 取消再次提交天数
			 Map<String, Object> paramterMap=new HashMap<String, Object>();
			 paramterMap.put("code", EnumConstants.LOAN_RESTRICT_DAY);
			 BMSParameter	bmsParameter = parameterService.getBy(paramterMap);
			 Integer loanRestrictDay=Integer.valueOf(bmsParameter.getParameterValue()) ;
			 //获取上次借款取消 拒绝的时间
			 Map<String, Object> historyMap=new HashMap<String, Object>();
			 historyMap.put("loanNo",  loanBaseEntity.getLoanNo());
			 List<String>  hisStatusList=new ArrayList<String>();
			// hisStatusList.add(EnumConstants.RTFLoanStatus.拒绝.getValue());
//			 hisStatusList.add(EnumConstants.RTFLoanStatus.取消.getValue());
			 paramMap.put("statusList", hisStatusList );
			 BMSAPPHistoryEntity appHistoryEntity= aPPHistoryService.getBy(paramMap);
			 Long dayNum=DateUtil.getDiffDay( appHistoryEntity.getCreatedTime(),new Date())	;	
			 
			 if(loanRestrictDay>= dayNum.intValue()){
				 Map<String, Object> resonMap=new HashMap<String, Object>();
				 resonMap.put("code",EnumConstants.RejectReasonCode.信用度不够.getValue()  );
				 BMSRejectReason  rejectReason =rejectReasonService.getBy(paramMap);
				 String txt="在申请限制天数"+loanRestrictDay+"天前次 原因为"+rejectReason.getReason();
				 throw new BizException(BizErrorCode.DISSATISFACTION_RESTRICT_DAYS, new Object[]{txt});
			 }
			
		}else{
			// 参数表获取保护期天数
			 Map<String, Object> paramterMap=new HashMap<String, Object>();
			 paramterMap.put("code", EnumConstants.LOAN_PROTECT_DAY);
			 BMSParameter	bmsParameter = parameterService.getBy(paramterMap);
			 Integer loanProtectDay=Integer.valueOf(bmsParameter.getParameterValue()) ;
			 List<String>  hisStatusList=new ArrayList<String>();
			// hisStatusList.add(EnumConstants.RTFLoanStatus.拒绝.getValue());
			// hisStatusList.add(EnumConstants.RTFLoanStatus.取消.getValue());
			 paramMap.put("statusList", hisStatusList );
			 paramMap.put("loanNo", loanBaseEntity.getLoanNo() );
			 BMSAPPHistoryEntity appHistoryEntity= aPPHistoryService.getBy(paramMap);
			 Long dayNum=DateUtil.getDiffDay( appHistoryEntity.getCreatedTime(),new Date())	;	
			 if(loanProtectDay>= dayNum.intValue()){
				 String txt="客户在保护期内，不可提交，限制天数为"+loanProtectDay+"天";
				throw new BizException(BizErrorCode.DISSATISFACTION_PROTECT_DAYS, new Object[]{txt});
			    //throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"contactInfoVOList"});
			 }else{
				 Map<String, Object> resMap=new HashMap<String, Object>();
				 resMap.put("code", EnumConstants.LOAN_RESTRICT_DAY);
				 BMSParameter	bmsParameterDay = parameterService.getBy(resMap);
				 Integer loanRestrictDay=Integer.valueOf(bmsParameterDay.getParameterValue()) ;
				 if(loanRestrictDay>=dayNum){ 
					 Map<String, Object> resonMap=new HashMap<String, Object>();
					 resonMap.put("code",EnumConstants.RejectReasonCode.信用度不够.getValue()  );
					 BMSRejectReason  rejectReason =rejectReasonService.getBy(paramMap);
					 String txt="再申请限制天数XX天，前次一级拒绝原因"+ rejectReason.getReason();
					 throw new BizException(BizErrorCode.DIFFERENT_STORES_DISSATISFACTION_RESTRICT_DAYS, new Object[]{txt});
					 
				 }else{
					 Map<String, Object> resonMap=new HashMap<String, Object>();
					 resonMap.put("code",EnumConstants.RejectReasonCode.信用度不够.getValue()  );
					 BMSRejectReason  rejectReason =rejectReasonService.getBy(paramMap);
					 String txt="前次一级拒绝原因"+rejectReason.getReason();
					 response.setData(txt);
				 }
				 
			 }
		}
		
		return response;
	}
	
	

	@Override
	public Response<ApplyEntryVO> queryApplyEntry(PersonHistoryLoanVO personHistoryLoanVO) {
		return  applyDataManipulationService.queryApplyEntry(personHistoryLoanVO);
	}
	
	
	
//	/**
//	 * 私营企业类型
//	 * @author YM10152
//	 */
//	public enum PriEnterpriseType{
//		_00001("个体户"), 
//		_00002("独资"), 
//		_00003("合伙制"),
//		_00004("股份制"), 
//		_00005("其他");
//		private String value;
//		
//		PriEnterpriseType(String value) {
//			this.value = value;
//		}
//		public String getValue(){
//			return value;
//		}
//	}
	
	
	@Override
	public Response<Object> test(PersonHistoryLoanVO personHistoryLoanVO) {
		
		applyDataManipulationService.ifReloanGreaterThan12MonthsUser(personHistoryLoanVO.getName(),personHistoryLoanVO.getIdNo());
		
//		Date date = new Date();
//		
//		loanBaseDao.createLoanNo(date);
		
//		applyDataManipulationService.query(personHistoryLoanVO.getName(), personHistoryLoanVO.getIdNo(), personHistoryLoanVO.getLoanNo());
		
//		getSalesman(personHistoryLoanVO);
		
//		personValidate(personHistoryLoanVO);
		
//		List<BMSEnumCode> listBy = demoService.listBy(new HashMap<String, Object>());
//		Map<String, List<BMSEnumCode>> map = new HashMap<String, List<BMSEnumCode>>();
//		
//		for (int i = 0; i < listBy.size(); i++) {
//			BMSEnumCode vo = listBy.get(i);
//			if(map.get(vo.getCodeType()) != null){
//				List<BMSEnumCode> list = map.get(vo.getCodeType());
//				list.add(vo);
//				map.put(vo.getCodeType(), list);
//			}else {
//				List<BMSEnumCode> list = new ArrayList<BMSEnumCode>();
//				list.add(vo);
//				map.put(vo.getCodeType(), list);
//			}
//		}
//		
//		
//		for (Map.Entry<String, List<BMSEnumCode>> entry : map.entrySet()) {  
////			/**
//			System.out.println("/**");
////			 * 私营企业类型
//			System.out.println(" *");
////			 * @author YM10152
//			System.out.println("* @author YM10152");
////			 */
//			System.out.println("*/");
////			public enum PriEnterpriseType{
//			System.out.println("public enum "+entry.getKey()+"{");
//			
////				_00001("个体户"), 
////				_00002("独资"), 
////				_00003("合伙制"),
////				_00004("股份制"), 
////				_00005("其他");
//			List<BMSEnumCode> list = entry.getValue();
//			for (BMSEnumCode vo :list) {
//				System.out.println("_"+vo.getCode()+"(\""+vo.getNameCN()+"\"),");
//			}
//			
////				private String value;
//			System.out.println("private String value;");
////				
//			System.out.println();
////				PriEnterpriseType(String value) {
//			System.out.println(entry.getKey()+"(String value) {");
////					this.value = value;
//			System.out.println("this.value = value;");
////				}
//			System.out.println("}");
////				public String getValue(){
//			System.out.println("public String getValue(){");
////					return value;
//			System.out.println("return value;");
////				}
//			System.out.println("}");
////			}
//			System.out.println("}");
//			
////		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//		}  
		return null;
	}

	/**
	 * 快照版本
	 * @param loanNo
	 * @param version
	 * @return
	 */
	public Response<Object> rapidReplicationVersion(String loanNo,Long version) {
		
		//更新员工姓名
		if(org.apache.commons.lang.StringUtils.isBlank(loanNo)){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		
		List<LoanBaseEntity> listLoanVo = loanBaseService.getByMap(paramMap);
		if(listLoanVo == null || listLoanVo.size() == 0){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		LoanBaseEntity loanBaseEntity = listLoanVo.get(0);
		Long loanBaseId = loanBaseEntity.getId();
		
		//获取配置总表
		LoanBaseRelaEntity LoanBaseRela = loanBaseRelaService.getByBaseId(loanBaseId);
		if(LoanBaseRela == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		//卡友贷  OK
		APPCardLoanInfoEntity appCardLoanInfoEntity = appCardLoanInfoService.getById(LoanBaseRela.getTmAppCardLoanInfoId());
		appCardLoanInfoEntity.setId(null);
		appCardLoanInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appCardLoanInfoService.saveOrUpdate(appCardLoanInfoEntity);
		
		//车辆贷 OK
		APPCarInfoEntity appCarInfoEntity = appCarInfoService.getById(LoanBaseRela.getTmAppCarInfoId());
		appCarInfoEntity.setId(null);
		appCarInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appCarInfoService.saveOrUpdate(appCarInfoEntity);
		
		//房产贷 OK
		APPEstateInfoEntity appEstateInfoEntity = appEstateInfoService.getById(LoanBaseRela.getTmAppEstateInfoId());
		appEstateInfoEntity.setId(null);
		appEstateInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appEstateInfoService.saveOrUpdate(appEstateInfoEntity);
		
		//淘宝达人贷 OK
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = appMasterLoanInfoService.getById(LoanBaseRela.getTmAppMasterLoanInfoId());
		appMasterLoanInfoEntity.setId(null);
		appMasterLoanInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appMasterLoanInfoService.saveOrUpdate(appMasterLoanInfoEntity);
		
		//淘宝商户贷 OK
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = appMerchantLoanInfoService.getById(LoanBaseRela.getTmAppMerchanLoanInfoId());
		appMerchantLoanInfoEntity.setId(null);
		appMerchantLoanInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appMerchantLoanInfoService.saveOrUpdate(appMerchantLoanInfoEntity);
		
		//保单贷 OK
		APPPolicyInfoEntity appPolicyInfoEntity = appPolicyInfoService.getById(LoanBaseRela.getTmAppPolicyInfoId());
		appPolicyInfoEntity.setId(null);
		appPolicyInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appPolicyInfoService.saveOrUpdate(appPolicyInfoEntity);
		
		//公积金贷 OK
		APPProvidentInfoEntity appProvidentInfoEntity = appProvidentInfoService.getById(LoanBaseRela.getTmAppProvidentInfoId());
		appProvidentInfoEntity.setId(null);
		appProvidentInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appProvidentInfoService.saveOrUpdate(appProvidentInfoEntity);
		
//		//随薪贷 OK
//		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = appSalaryInfoService.getById(LoanBaseRela.getTmAppSalaryLoanInfoId());
//		appSalaryLoanInfoEntity.setId(null);
//		appSalaryLoanInfoEntity.setSnapVersion(new Integer(version.intValue()));
//		appSalaryInfoService.saveOrUpdate(appSalaryLoanInfoEntity);
		
		//用户信息详情 OK
		APPPersonInfoEntity appPersonInfoEntity = appPersonInfoService.getById(LoanBaseRela.getBmsAppPersonInfoId());
		appPersonInfoEntity.setId(null);
		appPersonInfoEntity.setSnapVersion(new Integer(version.intValue()));
		appPersonInfoService.saveOrUpdate(appPersonInfoEntity);
		
		//联系人主表 OK
		AppContactHeadEntity appContactHeadEntity = aPPContactHeadService.getById(LoanBaseRela.getTmAppContatcInfoId());
		appContactHeadEntity.setId(null);
		appContactHeadEntity.setSnapVersion(new Long(version.intValue()));
		Long headId = aPPContactHeadService.saveOrUpdate(appContactHeadEntity);
		
		//联系人信息
		List<APPContactInfoEntity> appContactInfoEntityList = appContactInfoService.listBy(paramMap);
		for (APPContactInfoEntity appContactInfoEntity :appContactInfoEntityList) {
			appContactInfoEntity.setId(null);
			appContactInfoEntity.setHeadId(headId);
			appContactInfoEntity.setVerson(new Integer(version.intValue()));
		}
		appContactInfoService.saveList(appContactInfoEntityList);
		
		
		return null;
	}
	
	/**
	 * 查看当前用户的贷款状态
	 * 申请类型 	NEW 新申请    TOPUP 追加贷款	RELOAN 结清再贷
	 * @param idNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getApplyType(String idNo){
		String ApplyType = "NEW";
		//查看是否
		Map<String,Object> httpParamMap = new HashMap<String,Object>();
		httpParamMap.put("idnum", idNo);
		
		HttpResponse queryLoan = coreHttpService.loanStatus(httpParamMap);
		
		Map<String,Object> contentMap = JsonUtils.decode(queryLoan.getContent(), Map.class);
		if(contentMap.get("code").equals("000000")){
			if(contentMap.get("msg").equals("该用户不在系统中")){
				return ApplyType;
			}
			
			List<ResLoanBaseVo> resQueryLoanVo = (List<ResLoanBaseVo>) contentMap.get("loan");
			if(resQueryLoanVo != null && resQueryLoanVo.size() > 0){
				for (ResLoanBaseVo loanBaseVo : resQueryLoanVo) {
					if("正常".equals(loanBaseVo.getLoanState()) || "逾期".equals(loanBaseVo.getLoanState()) || "预结清".equals(loanBaseVo.getLoanState())){
						ApplyType = "TOPUP";
						return ApplyType;
					}else if("结清".equals(loanBaseVo.getLoanState())){
						ApplyType = "RELOAN";
					}
				}
			}
		}
		return ApplyType;
	}

	@Override
	public Response<ReqApplyEntryVO> auditUpdate(AuditAmendEntryVO applyEntryVO) {
		//系统日志
		ibmsSysLogService.recordSysLog(applyEntryVO, 
										"审核系统|电核模块|基本信息修改|修改",
										"IApplyEnterExecuter/auditUpdate",
										"修改基本信息");
		logger.info("-------------------调用auditUpdate方法--------------------------"+gson.toJson(applyEntryVO));
		return applyDataManipulationService.auditUpdate(applyEntryVO);
	}

	@Override
	public Response<Object> fixedCreditReport(PersonHistoryLoanVO personHistoryLoanVO) {
		Response<Object> respon = new Response<Object>();
		
		Map<String,Object> appDataMap = new HashMap<String,Object>();
		
		appDataMap.put("customerIdCard",personHistoryLoanVO.getIdNo());//身份证
		appDataMap.put("customerName", personHistoryLoanVO.getName());//用户名
		appDataMap.put("appNo", personHistoryLoanVO.getLoanNo());//申请件编号
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		appDataMap.put("queryDate", df.format(new Date()));//首次录入复核时间
		
		JSONObject resObject = creditHttpService.queryBMSReport(appDataMap);
		if(resObject.has("code") && "000000".equals(resObject.getString("code"))){ // 查询成功，并且有报告
			logger.info(" --------------- 查询央行报成功，将返回的央行征信报告id["+resObject.get("reportId")+"]");
			respon.setData(resObject.get("reportId"));
			return respon;
		}else{
			logger.info("申请件号为【"+personHistoryLoanVO.getLoanNo()+"查询央行报告失败，失败原因: "+resObject.get("messages"));
		}
		return null;
	}

	/**
	 * 校验重复入单
	 */
	public Response<Object> validateRepeatSingle(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<Object> response = new Response<Object>();
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getIdNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"idNo"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getIfThroughTrain())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ifThroughTrain"});
		}
		Map<String, Object>  paramMap=new HashMap<String, Object>();
		paramMap.put("name", validateNameIdNoVO.getName());
		paramMap.put("idNo", validateNameIdNoVO.getIdNo());
		
		List<String> statusList=new ArrayList<String>();
		statusList.add(EnumConstants.LoanStatus.APPLY.getValue());
		statusList.add(EnumConstants.LoanStatus.PASS.getValue());
		if(validateNameIdNoVO.getIfThroughTrain().equals("0")){
			//非直通车进件  放完款的申请单一并查出
			statusList.add(EnumConstants.LoanStatus.NORMAL.getValue());
		}else if(validateNameIdNoVO.getIfThroughTrain().equals("1")){
			//直通车  进件需要过滤
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"ifThroughTrain","0,1"});
		}
		paramMap.put("statusList", statusList );
		
		LoanBaseEntity  loanBaseEntity= (LoanBaseEntity) loanBaseService.getBy(paramMap,"getLatelyLoanByPersonId");
		if(loanBaseEntity != null){
			response.setRepCode("000027");
			response.setRepMsg("重复入单，当前申请件已进入["+loanBaseEntity.getStatus()+"]流程");
		}
		return response;
	}

	/**
	 * 上个是否被拒
	 */
	public Response<Object> validateLastIfBeingRejected(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<Object> response = new Response<Object>();
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getIdNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"idNo"});
		}
		Map<String, Object>  paramMap=new HashMap<String, Object>();
		paramMap.put("name", validateNameIdNoVO.getName());
		paramMap.put("idNo", validateNameIdNoVO.getIdNo());
		
		LoanBaseEntity  loanBaseEntity= (LoanBaseEntity) loanBaseService.getBy(paramMap,"getLatelyLoanByPersonId");
		if(loanBaseEntity != null){
			if(loanBaseEntity.getState().equals(EnumConstants.LoanStatus.REFUSE.getValue())){
				paramMap.put("loanBaseId", loanBaseEntity.getId());
				LoanExtEntity loanExtEntity = loanExtService.getBy(paramMap);
				if(loanExtEntity != null){
					response.setRepCode("000028");
					response.setRepMsg("上一申请单被拒绝,拒绝原因[一级原因：{"+loanExtEntity.getPrimaryReason()+"}，二级原因：{"+loanExtEntity.getSecodeReason()+"}]");
				}else{
					throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
				}
			}
		}
		return response;
	}

	@Override
	public Response<Object> validateWhetherProtection(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<Object> response = new Response<Object>();
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(validateNameIdNoVO.getIdNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"idNo"});
		}
		if(validateNameIdNoVO.getOwningBranchId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		
		
		Map<String, Object>  paramMap=new HashMap<String, Object>();
		paramMap.put("name", validateNameIdNoVO.getName());
		paramMap.put("idNo", validateNameIdNoVO.getIdNo());
		LoanBaseEntity  loanBaseEntity= (LoanBaseEntity) loanBaseService.getBy(paramMap,"getLatelyLoanByPersonId");
		if(loanBaseEntity == null){
			return response;
		}else{
			if((!loanBaseEntity.getOwningBranchId().equals(validateNameIdNoVO.getOwningBranchId()))&&
					loanBaseEntity.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue())){
				paramMap.put("rtfState", EnumConstants.RtfState.QYFP.getValue());
				paramMap.put("loanBaseId", loanBaseEntity.getId());
				//查看日志表，判断用户是否审核通过
				BMSSysLoanLog by = sysLoanLogService.getBy(paramMap);
				if(by != null){
					//时间间隔
					Long dayNum=DateUtil.getDiffDay( by.getCreatorDate(),new Date());
					
					Integer loanRestrictDay=Integer.valueOf(EnumConstants.LOAN_PROTECT_DAY_VALUE) ;
					if(loanRestrictDay > dayNum){
						LoanExtEntity loanExtEntity = loanExtService.getBy(paramMap);
						
						response.setRepCode("000029");
						response.setRepMsg("在保护期30天内，不能重复入单，还差["+(loanRestrictDay - dayNum)+"]天"
								+ "，取消原因[一级原因：{"+loanExtEntity.getPrimaryReason()+"}，二级原因：{"+loanExtEntity.getSecodeReason()+"}]");
					}
				}
			}
		}
		return response;
	}

	@Override
	public Response<Object> productIfShelves(ValidateNameIdNoVO validateNameIdNoVO) {
		Response<Object> response = new Response<Object>();
		if(validateNameIdNoVO.getOrgId() ==null||validateNameIdNoVO.getProductCode()==null
				||validateNameIdNoVO.getAuditLimitId()==null){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "orgId|aroductCode|auditLimit" });
		}
		BMSOrgLimitChannel orgLimitChannel = new BMSOrgLimitChannel();
		BeanUtils.copyProperties(validateNameIdNoVO, orgLimitChannel);
		Boolean flag = ibmsOrgLimitChannelService.isDisabled(orgLimitChannel);
		if(flag){
			response.setRepCode("000030");
			response.setRepMsg("产品已经下架");
		}
		return response;
	}
	

	@Override
	public Response<ApplyEntryVO> queryApplyValueEntry(PersonHistoryLoanVO personHistoryLoanVO) {
		return applyDataManipulationService.queryApplyValueEntry(personHistoryLoanVO);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(EnumCHConstants.Indicator.valueOf("_a"));
		} catch (IllegalArgumentException e) {
			System.out.println("111111"+e.getMessage());
		}catch (Exception e) {
			System.out.println("222");
		}
	}

	@Override
	public Response<AuditApplyEntryVO> queryAuditDifferences(ReqAuditDifferencesVO reqAuditDifferencesVO) {
		Response<AuditApplyEntryVO> reSponse = new Response<AuditApplyEntryVO>();
		
		if(org.apache.commons.lang.StringUtils.isBlank(reqAuditDifferencesVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(reqAuditDifferencesVO.getVersion())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
		}
		
		PersonHistoryLoanVO queryVo = new PersonHistoryLoanVO();
		queryVo.setLoanNo(reqAuditDifferencesVO.getLoanNo());
		
		
		Response<ApplyEntryVO> queryApplyEntry = null;
		if(reqAuditDifferencesVO.getVersion().equals("1")){
			queryApplyEntry = queryApplyEntry(queryVo);
		}else if(reqAuditDifferencesVO.getVersion().equals("2")){
			queryApplyEntry = queryApplyValueEntry(queryVo);
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"version","1初审对比   2 终审对比"});
		}
		
		AuditEntryVO auditEntryVO = new AuditEntryVO();
		BeanUtils.copyProperties(queryApplyEntry.getData(),auditEntryVO);
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanNo", reqAuditDifferencesVO.getLoanNo());
		LoanExtEntity loanExtEntitySelect = loanExtService.getBy(obj);
		
		AuditEntryVO submitAuditApplyEntryVO = null;
		AuditEntryVO backAuditApplyEntryVO = null;
		if(reqAuditDifferencesVO.getVersion().equals("1")){
			if(loanExtEntitySelect.getPeviewSnapVersion() != null){
				submitAuditApplyEntryVO = gson.fromJson(loanExtEntitySelect.getPeviewSnapVersion(), AuditEntryVO.class);
			}
			if(loanExtEntitySelect.getAuditBackSnapVersion() != null){
				backAuditApplyEntryVO = gson.fromJson(loanExtEntitySelect.getAuditBackSnapVersion(), AuditEntryVO.class);
			}
		}else if(reqAuditDifferencesVO.getVersion().equals("2")){
			if(loanExtEntitySelect.getAuditSnapVersion() != null){
				submitAuditApplyEntryVO = gson.fromJson(loanExtEntitySelect.getAuditSnapVersion(), AuditEntryVO.class);
			}
			if(loanExtEntitySelect.getFinalAuditBackSnapVersion() != null){
				backAuditApplyEntryVO = gson.fromJson(loanExtEntitySelect.getFinalAuditBackSnapVersion(), AuditEntryVO.class);
			}
		}
		
		List<LoanBaseEntity> by =loanBaseService.getByMap(obj);
		auditEntryVO.setVersion(by.get(0).getVerson());
		AuditApplyEntryVO auditApplyEntryVO = new AuditApplyEntryVO();
		auditApplyEntryVO.setAuditApplyEntryVO(auditEntryVO);
		auditApplyEntryVO.setSubmitAuditApplyEntryVO(submitAuditApplyEntryVO);
		auditApplyEntryVO.setBackAuditApplyEntryVO(backAuditApplyEntryVO);
		reSponse.setData(auditApplyEntryVO);
		return reSponse;
	}

	@Override
	public Response<Long> deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO) {
		Response<Long> reSponse = new Response<Long>();
		
		if(org.apache.commons.lang.StringUtils.isBlank(reqAuditDifferencesVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(reqAuditDifferencesVO.getSequenceNum())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"sequenceNum"});
		}
		Long successInt=appContactInfoService.deleteApplyContactInfo(reqAuditDifferencesVO);
		reSponse.setData(successInt);
		return reSponse;
	}

	@SuppressWarnings("unchecked")
	public Response<Object> checkProductCdMandatoryModel(AuditAmendEntryVO applyVO) {
		Response<Object> reSponse = new Response<Object>();
		
		if(StringUtils.isEmpty(applyVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		PersonHistoryLoanVO queryVo = new PersonHistoryLoanVO();
		queryVo.setLoanNo(applyVO.getLoanNo());
		
		Response<ApplyEntryVO> queryApplyEntry = queryApplyEntry(queryVo);
		if(!queryApplyEntry.isSuccess()){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		ApplyEntryVO applyEntryVO = queryApplyEntry.getData();
		if(applyEntryVO == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		// 构造实体类
		APPCardLoanInfoEntity appCardLoanInfoEntity = new APPCardLoanInfoEntity();
		APPCarInfoEntity appCarInfoEntity = new APPCarInfoEntity();
		List<APPContactInfoEntity> appContactInfoEntityList = new ArrayList<APPContactInfoEntity>();
		APPEstateInfoEntity appEstateInfoEntity = new APPEstateInfoEntity();
		APPMasterLoanInfoEntity appMasterLoanInfoEntity = new APPMasterLoanInfoEntity();
		APPMerchantLoanInfoEntity appMerchantLoanInfoEntity = new APPMerchantLoanInfoEntity();
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		APPPolicyInfoEntity appPolicyInfoEntity = new APPPolicyInfoEntity();
		APPProvidentInfoEntity appProvidentInfoEntity = new APPProvidentInfoEntity();
		APPSalaryLoanInfoEntity appSalaryLoanInfoEntity = new APPSalaryLoanInfoEntity();
		// 获取参数值
		BasicInfoVO basicInfoVO = applyEntryVO.getBasicInfoVO() != null ? applyEntryVO.getBasicInfoVO() : new BasicInfoVO();
		AssetsInfoVO assetsInfoVO = applyEntryVO.getAssetsInfoVO() != null ? applyEntryVO.getAssetsInfoVO() : new AssetsInfoVO();
		List<ContactInfoVO> contactInfoVOList = applyEntryVO.getContactInfoVOList() != null ? applyEntryVO.getContactInfoVOList() : new ArrayList<ContactInfoVO>();
		// 资产信息
		EstateInfoVO estateInfoVO = assetsInfoVO.getEstateInfoVO() != null ? assetsInfoVO.getEstateInfoVO() : new EstateInfoVO();
		CarInfoVO carInfoVO = assetsInfoVO.getCarInfoVO() != null ? assetsInfoVO.getCarInfoVO() : new CarInfoVO();
		PolicyInfoVO policyInfoVO = assetsInfoVO.getPolicyInfoVO() != null ? assetsInfoVO.getPolicyInfoVO() : new PolicyInfoVO();
		ProvidentInfoVO providentInfoVO = assetsInfoVO.getProvidentInfoVO() != null ? assetsInfoVO.getProvidentInfoVO() : new ProvidentInfoVO();
		CardLoanInfoVO cardLoanInfoVO = assetsInfoVO.getCardLoanInfoVO() != null ? assetsInfoVO.getCardLoanInfoVO() : new CardLoanInfoVO();
		SalaryLoanInfoVO salaryLoanInfoVO = assetsInfoVO.getSalaryLoanInfoVO() != null ? assetsInfoVO.getSalaryLoanInfoVO() : new SalaryLoanInfoVO();
		MasterLoanInfoVO masterLoanInfoVO = assetsInfoVO.getMasterLoanInfoVO() != null ? assetsInfoVO.getMasterLoanInfoVO() : new MasterLoanInfoVO();
		MerchantLoanInfoVO merchantLoanInfoVO = assetsInfoVO.getMerchantLoanInfoVO() != null ? assetsInfoVO.getMerchantLoanInfoVO() : new MerchantLoanInfoVO();
		// 基本信息
		PersonInfoVO personInfoVO = basicInfoVO.getPersonInfoVO() != null ? basicInfoVO.getPersonInfoVO() : new PersonInfoVO();
		PrivateOwnerInfoVO privateOwnerInfoVO = basicInfoVO.getPrivateOwnerInfoVO() != null ? basicInfoVO.getPrivateOwnerInfoVO() : new PrivateOwnerInfoVO();
		WorkInfoVO workInfoVO = basicInfoVO.getWorkInfoVO() != null ? basicInfoVO.getWorkInfoVO() : new WorkInfoVO();

		// 实体类赋值
		// //借款人信息 BMS_APP_PERSON
		// BeanUtils.copyProperties(personInfoVO, appPersonEntity);
		// 主办申请人 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(personInfoVO, appPersonInfoEntity);
		// 房产信息 TM_APP_ESTATE_INFO
		BeanUtils.copyProperties(estateInfoVO, appEstateInfoEntity);
		// 车辆信息 TM_APP_CAR_INFO
		BeanUtils.copyProperties(carInfoVO, appCarInfoEntity);
		// 保单信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(policyInfoVO, appPolicyInfoEntity);
		// 公积金信息 TM_APP_POLICY_INFO
		BeanUtils.copyProperties(providentInfoVO, appProvidentInfoEntity);
		// 卡友贷信息 TM_APP_CARD_LOAN_INFO
		BeanUtils.copyProperties(cardLoanInfoVO, appCardLoanInfoEntity);
		// 随薪贷信息 TM_APP_SALARY_LOAN_INFO
		BeanUtils.copyProperties(salaryLoanInfoVO, appSalaryLoanInfoEntity);
		// 网购达人贷 TM_APP_MASTER_LOAN_INFO
		BeanUtils.copyProperties(masterLoanInfoVO, appMasterLoanInfoEntity);
		// 淘宝商户贷信息 TM_APP_MERCHANT_LOAN_INFO
		BeanUtils.copyProperties(merchantLoanInfoVO, appMerchantLoanInfoEntity);
		// 工作信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(workInfoVO, appPersonInfoEntity);
		// 私营业主信息 BMS_APP_PERSON_INFO
		BeanUtils.copyProperties(privateOwnerInfoVO, appPersonInfoEntity);
		// 联系人信息 TM_APP_CONTACT_INFO
		for (int i = 0; i < contactInfoVOList.size(); i++) {
			APPContactInfoEntity contactInfoEntity = new APPContactInfoEntity();
			BeanUtils.copyProperties(contactInfoVOList.get(i), contactInfoEntity);
			appContactInfoEntityList.add(contactInfoEntity);
		}

		// 个人基本信息 工作信息
		Response<Object> responseAppPersonInfo = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appPersonInfoEntity);
		if (!responseAppPersonInfo.isSuccess()) {
			logger.info("appPersonInfoEntity 参数 规则验证: 校验不通过");
			String msg = "个人基本信息模块      "+responseAppPersonInfo.getRepMsg();
			responseAppPersonInfo.setRepMsg(msg);
			return responseAppPersonInfo;
		}else{
			if(appPersonInfoEntity.getHouseType().equals(ParametersType.HouseType._00004)){
				//住宅类型选择“租房”时必填  并且大于0
				if(appPersonInfoEntity.getHouseRent() == null || appPersonInfoEntity.getHouseRent().intValue() < 0){
					responseAppPersonInfo.setRepMsg("当前住宅类型是租房，租金必须大于0");
					return responseAppPersonInfo;
				}
			}
			//QQ、微信、电子邮箱至少填写一项
			if(appPersonInfoEntity.getQqNum() ==null && appPersonInfoEntity.getEmail() == null && appPersonInfoEntity.getWeChatNum() == null){
				responseAppPersonInfo.setRepMsg("QQ、微信、电子邮箱至少填写一项");
				return responseAppPersonInfo;
			}
			
		}

		// 私营业主信息 客户工作类型选“私营业主”时必填，选择“自雇人士”时选填
		if (!StringUtils.isEmpty(appPersonInfoEntity.getCusWorkType()) && appPersonInfoEntity.getCusWorkType().equals(ParametersType.JobType._00001)) {

			PrivateOwnerInfoEntity privateOwnerInfoEntity = new PrivateOwnerInfoEntity();
			BeanUtils.copyProperties(appPersonInfoEntity, privateOwnerInfoEntity);
			
			Response<Object> responPrivateOwnerInfo = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(privateOwnerInfoEntity);
			if (!responPrivateOwnerInfo.isSuccess()) {
				logger.info("responPrivateOwnerInfo 参数 规则验证: 校验不通过");
				String msg = "私营业主信息模块      "+responPrivateOwnerInfo.getRepMsg();
				responPrivateOwnerInfo.setRepMsg(msg);
				return responPrivateOwnerInfo;
			}
		}
		if(appPersonInfoEntity.getCusWorkType()==null || appPersonInfoEntity.getOccupation()==null || appPersonInfoEntity.getCorpStructure()==null){
			String msg = "职业字段不符合条件，请重新选择！";
			responseAppPersonInfo.setRepMsg(msg);
			responseAppPersonInfo.setRepCode("000001");
			return responseAppPersonInfo;
		}
		//客户工作类型,单位性质，职业校验
		if(!StringUtil.isEmpty(appPersonInfoEntity.getCusWorkType()) && appPersonInfoEntity.getCusWorkType().equals(ParametersType.JobType._00002)){
			//如果是个体,只能选择工薪
			if(ParametersType.CorpStructure._00009.equals(appPersonInfoEntity.getCorpStructure()) && !ParametersType.professionType._00001.equals(appPersonInfoEntity.getOccupation())){
				String msg = "职业字段不符合条件，请重新选择！";
				responseAppPersonInfo.setRepMsg(msg);
				responseAppPersonInfo.setRepCode("000001");
				return responseAppPersonInfo;
			}
			//如果是政府机构、事业单位、国企、外资、民营、私营、其他、合资, 选择 公务员、白领、工薪
			if(!ParametersType.CorpStructure._00009.equals(appPersonInfoEntity.getCorpStructure()) &&(ParametersType.professionType._A.equals(appPersonInfoEntity.getOccupation()) || ParametersType.professionType._B.equals(appPersonInfoEntity.getOccupation()))){
				String msg = "职业字段不符合条件，请重新选择！";
				responseAppPersonInfo.setRepCode("000001");
				responseAppPersonInfo.setRepMsg(msg);
				return responseAppPersonInfo;
			}  
		} 
		if(!StringUtil.isEmpty(appPersonInfoEntity.getCusWorkType()) && (appPersonInfoEntity.getCusWorkType().equals(ParametersType.JobType._00001) || appPersonInfoEntity.getCusWorkType().equals(ParametersType.JobType._00003))){
			if(ParametersType.CorpStructure._00001.equals(appPersonInfoEntity.getCorpStructure()) || ParametersType.CorpStructure._00002.equals(appPersonInfoEntity.getCorpStructure())){
				String msg = "单位性质和职业字段不符合条件，请重新选择！";
				responseAppPersonInfo.setRepCode("000001");
				responseAppPersonInfo.setRepMsg(msg);
				return responseAppPersonInfo;
			}
			if(!ParametersType.professionType._A.equals(appPersonInfoEntity.getOccupation()) && !ParametersType.professionType._B.equals(appPersonInfoEntity.getOccupation())){
				String msg = "职业字段不符合条件，请重新选择！";
				responseAppPersonInfo.setRepCode("000001");
				responseAppPersonInfo.setRepMsg(msg);
				return responseAppPersonInfo;
			}
		}
     
		// 联系人
		if (appContactInfoEntityList.size() < 5) {
			logger.info("responseContactInfoEntityList.size<5 参数 规则验证: 校验不通过");
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "contactInfoVOList个数不满足" });
		}
		for (APPContactInfoEntity appContactInfoEntity : appContactInfoEntityList) {
			Response<Object> responseContactInfoEntityList = Validate.getInstance().validate(appContactInfoEntity);
			if (!responseContactInfoEntityList.isSuccess()) {
				String msg = "联系人信息模块      "+responseContactInfoEntityList.getRepMsg();
				responseContactInfoEntityList.setRepMsg(msg);
				return responseContactInfoEntityList;
			}
			//联系人新增配置选择 
			if(appContactInfoEntity.getContactRelation() != null && appContactInfoEntity.getContactRelation().equals(ParametersType.Relationship._00013) ){
				// 如果婚姻状况是  “已婚”、“再婚”、“复婚”  需要校验配偶身份证
				if(appPersonInfoEntity.getMaritalStatus() != null && (
						appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0002) ||
						appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0006) ||
						appPersonInfoEntity.getMaritalStatus().equals(ParametersType.MaritalStatus._0007)
						)){
					if(StringUtils.isEmpty(appContactInfoEntity.getContactIdNo())){
						String msg = "联系人信息模块      联系人为配偶，身份证号不为空!";
						responseContactInfoEntityList.setRepMsg(msg);
						return responseContactInfoEntityList;
					}
				}
			}
		}

	

		/**
		 * 获取商品资产配置表，
		 */
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("productCode", new Long(applyEntryVO.getApplyInfoVO().getProductCd()));
		List<BMSEnumCode> ecnumCodeList = demoService.getProducAssetsInfo(paraMap);
		if (ecnumCodeList != null && ecnumCodeList.size() > 0) {
			String model;
			for (int i = 0; i < ecnumCodeList.size(); i++) {
				model = ecnumCodeList.get(i).getCode();
//				if (model.equals(EnumConstants.productModule.SALARYLOAN.getValue())) {// 随薪贷
//					// 随薪贷
//					Response<Object> responseSalaryLoanInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appSalaryLoanInfoEntity);
//					if (!responseSalaryLoanInfoEntity.isSuccess()) {
//						logger.info("appSalaryLoanInfoEntity 参数 规则验证: 校验不通过");
//						String msg = "随薪贷信息模块      "+responseSalaryLoanInfoEntity.getRepMsg();
//						responseSalaryLoanInfoEntity.setRepMsg(msg);
//						return responseSalaryLoanInfoEntity;
//					}
//				} else 
				if (model.equals(EnumConstants.productModule.ESTATE.getValue())) {// 房产贷
					// 房产信息
					Response<Object> responseEstateInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appEstateInfoEntity);
					if (!responseEstateInfoEntity.isSuccess()) {
						logger.info("appEstateInfoEntity 参数 规则验证: 校验不通过");
						String msg = "房产信息模块      "+responseEstateInfoEntity.getRepMsg();
						responseEstateInfoEntity.setRepMsg(msg);
						return responseEstateInfoEntity;
					}
					// 当 房贷情况 还款中时 房贷金额，月供，已还期数 不能为空
					if (appEstateInfoEntity.getEstateLoan().equals("ING")) {
//						if (appEstateInfoEntity.getEstateLoanAmt() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块    房贷金额" });
//						}
						if (appEstateInfoEntity.getMonthPaymentAmt() == null) {
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块     月供" });
						}
						if(appEstateInfoEntity.getEstateLoanIssueDate() == null){
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块   房贷发放年月" });
						}
//						if (appEstateInfoEntity.getHasRepaymentNum() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块     已还期数" });
//						}
					}else{
						if(appEstateInfoEntity.getIfMe() == null){
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "房产信息模块   单据户名为本人" });
						}
					}

				} else if (model.equals(EnumConstants.productModule.CAR.getValue())) {// 车辆贷
					// 车辆信息
					Response<Object> responseCarInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appCarInfoEntity);
					if (!responseCarInfoEntity.isSuccess()) {
						logger.info("appCarInfoEntity 参数 规则验证: 校验不通过");
						String msg = "车辆信息模块      "+responseCarInfoEntity.getRepMsg();
						responseCarInfoEntity.setRepMsg(msg);
						return responseCarInfoEntity;
					}
					// 是否有车贷
					if (appCarInfoEntity.getCarLoan().equals(ParametersType.Indicator._Y)) {
						if (appCarInfoEntity.getMonthPaymentAmt() == null) {
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息模块     月供" });
						}
//						if (appCarInfoEntity.getCarLoanTerm() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息模块     贷款剩余期数" });
//						}
						if(appCarInfoEntity.getCarLoanIssueDate() == null){
							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息模块  车贷发放年月贷" });
						}
					}

//					// 一手车 购车价和裸车价 都必填 二手车 二者必填一
//					if (appCarInfoEntity.getCarType().equals(ParametersType.CarType._ONE)) {
//						if (appCarInfoEntity.getNakedCarPrice() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息模块     裸车价" });
//						}
//						if (appCarInfoEntity.getCarBuyPrice() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息     购买价" });
//						}
//					} else {
//						if (appCarInfoEntity.getNakedCarPrice() == null && appCarInfoEntity.getCarBuyPrice() == null) {
//							throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "车辆信息     二手车  裸车价和购买价二者必填一" });
//						}
//					}

				} else if (model.equals(EnumConstants.productModule.PROVIDENT.getValue())) {// 公积金贷
					// 公积金信息
					Response<Object> responseProvidentInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appProvidentInfoEntity);
					if (!responseProvidentInfoEntity.isSuccess()) {
						logger.info("appProvidentInfoEntity 参数 规则验证: 校验不通过");
						String msg = "公积金信息模块      "+responseProvidentInfoEntity.getRepMsg();
						responseProvidentInfoEntity.setRepMsg(msg);
						return responseProvidentInfoEntity;
					}
				} else if (model.equals(EnumConstants.productModule.POLICY.getValue())) {// 保单贷
					// 保单信息
					Response<Object> responsePolicyInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appPolicyInfoEntity);
					if (!responsePolicyInfoEntity.isSuccess()) {
						logger.info("appPolicyInfoEntity 参数 规则验证: 校验不通过");
						String msg = "保单信息模块      "+responsePolicyInfoEntity.getRepMsg();
						responsePolicyInfoEntity.setRepMsg(msg);
						return responsePolicyInfoEntity;
					}
				} else if (model.equals(EnumConstants.productModule.MASTERLOAN_A.getValue())) {// 网购达人贷A
					APPMasterLoanInfoAEntity appMasterLoanInfoAEntity = new APPMasterLoanInfoAEntity();
					BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoAEntity);

					// 网购达人贷A
					Response<Object> responseMasterLoanInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appMasterLoanInfoAEntity);
					if (!responseMasterLoanInfoEntity.isSuccess()) {
						logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
						String msg = "网购达人贷A模块      "+responseMasterLoanInfoEntity.getRepMsg();
						responseMasterLoanInfoEntity.setRepMsg(msg);
						return responseMasterLoanInfoEntity;
					}
				} else if (model.equals(EnumConstants.productModule.MASTERLOAN_B.getValue())) {// 网购达人贷B
					APPMasterLoanInfoBEntity appMasterLoanInfoBEntity = new APPMasterLoanInfoBEntity();
					BeanUtils.copyProperties(appMasterLoanInfoEntity, appMasterLoanInfoBEntity);

					// 网购达人贷B
					Response<Object> responseMasterLoanInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appMasterLoanInfoBEntity);
					if (!responseMasterLoanInfoEntity.isSuccess()) {
						logger.info("appMasterLoanInfoEntity 参数 规则验证: 校验不通过");
						String msg = "网购达人贷B模块      "+responseMasterLoanInfoEntity.getRepMsg();
						responseMasterLoanInfoEntity.setRepMsg(msg);
						return responseMasterLoanInfoEntity;
					}
				} else if (model.equals(EnumConstants.productModule.MERCHANTLOAN.getValue())) {// 淘宝商户贷
					// 淘宝商户贷信息
					Response<Object> responseMerchantLoanInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appMerchantLoanInfoEntity);
					if (!responseMerchantLoanInfoEntity.isSuccess()) {
						logger.info("appMerchantLoanInfoEntity 参数 规则验证: 校验不通过");
						String msg = "淘宝商户贷信息模块      "+responseMerchantLoanInfoEntity.getRepMsg();
						responseMerchantLoanInfoEntity.setRepMsg(msg);
						return responseMerchantLoanInfoEntity;
					}
				} else if (model.equals(EnumConstants.productModule.CARDLOAN.getValue())) {// 卡友贷
					// 卡友信息
					Response<Object> responseCardLoanInfoEntity = com.ymkj.cms.biz.common.util.Validate.getInstance().validate(appCardLoanInfoEntity);
					if (!responseCardLoanInfoEntity.isSuccess()) {
						logger.info("appCardLoanInfoEntity 参数 规则验证: 校验不通过");
						String msg = "卡友信息模块      "+responseCardLoanInfoEntity.getRepMsg();
						responseCardLoanInfoEntity.setRepMsg(msg);
						return responseCardLoanInfoEntity;
					}
				}
			}
		}
		return reSponse;
	}

	@Override
	public Response<ResCheckNewDataVO> queryCheckNewData(PersonHistoryLoanVO personHistoryLoanVO) {
		logger.info("----queryCheckNewData------------开始请求---------------");
		logger.info("请求参数:" + gson.toJson(personHistoryLoanVO));
		if(null==personHistoryLoanVO){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"personHistoryLoanVO"});
		}
		if(null==personHistoryLoanVO.getName()||personHistoryLoanVO.getName().length()==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(null==personHistoryLoanVO.getIdNo()||personHistoryLoanVO.getIdNo().length()==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"idNo"});
		}
		logger.info("---    开始调用接口查询--------");
		Response<ResCheckNewDataVO> response=new Response<ResCheckNewDataVO>();
		ResCheckNewDataVO vo=loanExtService.queryCheckNewData(personHistoryLoanVO);
		logger.info("---   调用接口查询--------"+gson.toJson(vo));
		logger.info("---    结束调用接口查询--------");
		
		
		Map<String,Object> loanStatusMap=new HashMap<String,Object>();
    	loanStatusMap.put("idnum", personHistoryLoanVO.getIdNo());
    	logger.info("掉核心接口-------------------开始"+System.currentTimeMillis());
    	HttpResponse responseHttp=coreHttpService.loanStatus(loanStatusMap);
    	logger.info("掉核心接口-------------------开始"+System.currentTimeMillis());
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
				} catch (Exception e) {
					logger.info("-------------RELOAN状态的申请单装换集合实体失败----------------"+contentMap.get("loan").toString());
					e.printStackTrace();
				} 
				String isSettle="N";
				if(null!=listReadValues&&listReadValues.size()>0){
					for(int i=listReadValues.size();i>0;i--){
						if(listReadValues.get(listReadValues.size()-1).getLoanState().equals("结清")||listReadValues.get(listReadValues.size()-1).getLoanState().equals("预结清")){
							isSettle="Y";
							vo.setReturnMoneyStatu(isSettle);
							break;
						}else{
							isSettle="N";
							vo.setReturnMoneyStatu(isSettle);
							break;
						}
					}
				}
				
				
    		}
		}else{
			logger.info("---------------------" +contentMap.get("code")+"||"+contentMap.get("msg"));
			throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);	
		}
    	
		response.setData(vo);
		
		return response;
	}

	
	
	
	
	
}
