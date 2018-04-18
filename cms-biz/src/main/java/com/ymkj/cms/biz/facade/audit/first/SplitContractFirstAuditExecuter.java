package com.ymkj.cms.biz.facade.audit.first;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.service.IGreyListExecuter;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.first.ISplitContractFirstAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentBatchVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefusePlupdateStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqcsBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchAttrVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.entity.audit.BMSReassigMentEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstAgreeOrDisagreeEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstCsRefusePlupdateEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstReassignmentUpdEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstTrialBackUpEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstTrialHangEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstTrialHangUpEntity;
import com.ymkj.cms.biz.entity.audit.first.AFirstTrialSubmitEntity;
import com.ymkj.cms.biz.entity.audit.first.AfirstBMProductUpdEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.ContractFirstAdultExecuter;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.audit.BMSFirstAuditService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;
import com.ymkj.cms.biz.service.client.BDSClientService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.enums.OrganizationEnum;
import com.ymkj.pms.biz.api.enums.RoleEnum;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmpOrgVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResGroupVO;
import com.ymkj.pms.biz.api.vo.response.ResOrgNameAreaVO;


/**
 * 初审服务实现类
 * 
 * @author YM10161
 *
 */
public class SplitContractFirstAuditExecuter implements ISplitContractFirstAuditExecuter {
	public Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);
	//改派查询
	private final String QUERY_CS_REASSIGNMENT_SQL_UPDATE = "queryCsReassignMentInfoUpdate";
	private final String COUNT_CS_REASSIGNMENT_SQL_UPDATE = "reassignMentCsCountUpdate";
	//待办任务查询
	private final String QUERY_CS_WAITFORTHESTOCKS_SQL="queryCsWaitForTheStocks";
	private final String COUNT_CS_WAITFORTHESTOCKS_SQL="cSWaitForTheStocksCount";
	//初审已完成任务查询
	private final String QUERY_CS_AUDITOFFTHESTOCKS_SQL="queryCsAduditOffTheStocks";
	private final String COUNT_CS_AUDITOFFTHESTOCKS_SQL="cSAduditOffTheStocksCount";

	// JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private BMSFirstAuditService bMSResFirstAuditService;

	@Autowired
	private IOrganizationExecuter OrganizationExecuter;

	@Autowired
	private IBMSOrgLimitChannelService ibmsOrgLimitChannelService;

	@Autowired
	private IBMSAppPersonInfoService BMSAppPersonInfoService;

	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;

	@Autowired
	private BMSFirstAuditDao firstAuditDao;

	@Autowired
	private IGreyListExecuter greyListExecuter;

	@Autowired
	private IBlackListExecuter blackListExecuter;

	@Autowired
	private LoanBaseService loanBaseService;

	@Autowired
	private ILoanAuditService iLoanAuditService;

	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;

	@Autowired
	private BMSQualityInspectionService bMSQualityInspectionService;
	@Autowired
	private IEmployeeExecuter employeeExecuter;

	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;

	@Autowired
	private BDSClientService bDSClientService;

	@Autowired
	private PMSClientService pmsClientService;
	
	@Autowired
	private IBMSLoanLogService logService;
	/**
	 * 初审挂起更新接口
	 */
	@Override
	public Response<ResBMSAudiUpdVo> cSHangupUpd(ReqCsUpdVO request) {
		logger.info("------------请求初审挂起更新接口开始--------------");
		logger.info("请求参数列表:" + gson.toJson(request));

		Response<ResBMSAudiUpdVo> response = new Response<ResBMSAudiUpdVo>();

		AFirstTrialHangEntity aFirstTrialHangEntity = new AFirstTrialHangEntity();
		// 校验必填项
		BeanUtils.copyProperties(request, aFirstTrialHangEntity);

		Response<ResBMSAudiUpdVo> validate = Validate.getInstance().validate(aFirstTrialHangEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		aFirstTrialHangEntity.checkValue();

		chackStatusOnVersion(request);

		request.setRtfNodeStatus(EnumConstants.RtfNodeState.XSCSHANGUP.getValue());
		bMSResFirstAuditService.updateCsLoanNoState(request);
		return response;
	}

	/**
	 * 初审拒绝更新接口
	 */
	@Override
	public Response<ResBMSAudiUpdVo> cSRejectUpd(ReqCsUpdVO request) {
		logger.info("------------请求初审拒绝更新接口开始--------------");
		logger.info("请求参数列表:" + gson.toJson(request));

		Response<ResBMSAudiUpdVo> response = new Response<ResBMSAudiUpdVo>();

		// 校验必填项
		AFirstTrialHangUpEntity aFirstTrialHangUpEntity = new AFirstTrialHangUpEntity();
		BeanUtils.copyProperties(request, aFirstTrialHangUpEntity);

		Response<ResBMSAudiUpdVo> validate = Validate.getInstance().validate(aFirstTrialHangUpEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		// 参数值校验
		aFirstTrialHangUpEntity.checkValue();

		chackStatusOnVersion(request);

		request.setRtfNodeStatus(EnumConstants.RtfNodeState.XSCSREJECT.getValue());
		bMSResFirstAuditService.updateCsLoanNoState(request);
		return response;
	}

	/**
	 * 初审提交（终审，高审）更新接口(拆分)
	 */
	@Override
	public Response<ResBMSAudiUpdVo> cSSubmitUpd(ReqCsUpdVO request) {

		bMSQualityInspectionService.auditCheckProductSales(request.getLoanNo());

		logger.info("------------请求初审提交更新接口开始--------------");
		logger.info("请求参数列表:" + gson.toJson(request));
		Response<ResBMSAudiUpdVo> response = new Response<ResBMSAudiUpdVo>();

		AFirstTrialSubmitEntity aFirstTrialSubmitEntity = new AFirstTrialSubmitEntity();
		// 校验必填项
		BeanUtils.copyProperties(request, aFirstTrialSubmitEntity);

		Response<ResBMSAudiUpdVo> validate = Validate.getInstance().validate(aFirstTrialSubmitEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		aFirstTrialSubmitEntity.checkValue();

		chackStatusOnVersion(request);

		//审核提交产品校验
		boolean auditCheckProductSales = bMSQualityInspectionService.auditCheckProductSales(request.getLoanNo());
		if(!auditCheckProductSales){
			response.setRepCode("000030");
			response.setRepMsg("产品已经下架");
			return response;
		}

		//1 终审 2高审
		if(request.getFlag() == 1){
			request.setRtfNodeStatus(EnumConstants.RtfNodeState.XSCSPASS.getValue());
		}else if(request.getFlag() == 2){
			request.setRtfNodeStatus(EnumConstants.RtfNodeState.HIGHPASS.getValue());
		}else{
			throw new BizException(BizErrorCode.ENUM_EOERR, new Object[]{"Flag"});
		}

		//申请件层级以校验为主
		//request.setLoanTopClass(aFirstTrialSubmitEntity.getLoanTopClass());
		boolean boo = bMSResFirstAuditService.updateCsLoanNoState(request);
		if(boo){
			//添加快照版本
			applyDataManipulationService.auditDifferences(request.getLoanNo(), EnumConstants.differences.audit_submit.getValue());

			//绑定华证报告信息 add by gaohx 2017-08-23
			try{
				boolean bool = bMSResFirstAuditService.getHZReportInfo(request);
				if(bool){
					logger.info("非复核确认华征报告绑定成功！");
				}else{
					logger.info("非复核确认华征报告绑定失败！");
				}
			}catch(Exception e){
				logger.info("华征报告绑定异常！"+e);
			}
			
		}
		return response;
	}

	/**
	 * 初审退回更新接口
	 */
	@Override
	public Response<ResBMSAudiUpdVo> cSReturnUpd(ReqCsUpdVO request) {
		logger.info("------------请求初审退回更新接口开始--------------");
		logger.info("请求参数列表:" + gson.toJson(request));
		Response<ResBMSAudiUpdVo> response = new Response<ResBMSAudiUpdVo>();

		AFirstTrialBackUpEntity aFirstTrialBackUpEntity = new AFirstTrialBackUpEntity();
		// 校验必填项
		BeanUtils.copyProperties(request, aFirstTrialBackUpEntity);

		Response<ResBMSAudiUpdVo> validate = Validate.getInstance().validate(aFirstTrialBackUpEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		aFirstTrialBackUpEntity.checkValue();

		chackStatusOnVersion(request);

		request.setRtfNodeStatus(EnumConstants.RtfNodeState.XSCSRETURN.getValue());
		bMSResFirstAuditService.updateCsLoanNoState(request);

		//记录快照
		applyDataManipulationService.auditDifferences(request.getLoanNo(), EnumConstants.differences.audit_back.getValue());
		return response;
	}

	/**
	 * 初审自动派单
	 */
	@Override
	public Response<ResBMSAutomaticDispatchVO> csAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO) {
		long startTime = System.currentTimeMillis();
		Response<ResBMSAutomaticDispatchVO> res = new Response<ResBMSAutomaticDispatchVO>();
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqAutomaticDispatchVO));
		List<ResBMSAutomaticDispatchAttrVO> resAutomaticList = null;
		// 校验sysCode合法性
		if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqAutomaticDispatchVO.getSysCode())) {
			res.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
			res.setRepMsg("sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
			return res;
		}

		try {
			resAutomaticList = new ArrayList<ResBMSAutomaticDispatchAttrVO>();
			// 查询所有入单记录
			List<BMSAutomaticDispatchEntity> automaticDList = bMSResFirstAuditService.csAutomaticDispatchList(new HashMap<String, Object>());
			System.err.println("接口响应时间=========================："+(System.currentTimeMillis()-startTime));
			// 数据转换
			for (int i = 0; i < automaticDList.size(); i++) {
				ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchSearchListVO = new ResBMSAutomaticDispatchAttrVO();
				BMSAutomaticDispatchEntity bMSAutomaticDispatchEntity = automaticDList.get(i);
				BeanUtils.copyProperties(bMSAutomaticDispatchEntity, resBMSAutomaticDispatchSearchListVO);
				resAutomaticList.add(resBMSAutomaticDispatchSearchListVO);
			}

			ResBMSAutomaticDispatchVO ResBMSAutomaticDispatchVO = new ResBMSAutomaticDispatchVO();
			ResBMSAutomaticDispatchVO.setList(resAutomaticList);
			res.setData(ResBMSAutomaticDispatchVO);
			System.err.println("最终响应时间响应时间=========================："+(System.currentTimeMillis()-startTime));			
		} catch (Exception e) {
			// 异常抛出堆栈信息
			logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return res;
	}

	/**
	 * 初审申请产品更新(拆分)
	 */
	@Override
	public Response<Object> csProductUpd(ReqcsBMProductUpdVo reqcsBMProductUpdVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqcsBMProductUpdVo));
		Response<Object> response = new Response<Object>();

		AfirstBMProductUpdEntity afirstBMProductUpdEntity = new AfirstBMProductUpdEntity();
		// 校验必填项
		BeanUtils.copyProperties(reqcsBMProductUpdVo, afirstBMProductUpdEntity);

		Response<Object> validate = Validate.getInstance().validate(afirstBMProductUpdEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		afirstBMProductUpdEntity.checkValue();

		LoanBaseEntity chackVersion = chackVersion(reqcsBMProductUpdVo);

		//校验产品是否下架
		Map<String, Object> params =new  HashMap<String, Object>();
		params.put("orgId", chackVersion.getOwningBranchId());
		params.put("productCd", reqcsBMProductUpdVo.getProductCd());
		params.put("contractTrem",reqcsBMProductUpdVo.getAccTerm());
		boolean disabledInSign = ibmsOrgLimitChannelService.isDisabledInSign(params);
		if(disabledInSign){
			response.setRepCode("000030");
			response.setRepMsg("产品已经下架");
			return response;
		}

		//初审新增更新字段
		if(reqcsBMProductUpdVo.getOnlineAWithin3MonthsAddress() != null || 
				reqcsBMProductUpdVo.getOnlineAWithin6MonthsAddress() != null || 
				reqcsBMProductUpdVo.getOnlineAWithin12MonthsAddress() != null){

			if(StringUtils.isEmpty(reqcsBMProductUpdVo.getOnlineAWithin3MonthsAddress()) || 
					StringUtils.isEmpty(reqcsBMProductUpdVo.getOnlineAWithin3MonthsAddress()) ||
					StringUtils.isEmpty(reqcsBMProductUpdVo.getOnlineAWithin12MonthsAddress())){

				response.setRepCode("000001");
				response.setRepMsg("网购达人贷 3 , 6 , 12个月内收货地址类型不能为空！");
				return response;
			}
		}
		/**
		 * 调用更新借款产品信息接口
		 */
		bMSResFirstAuditService.updateCSProductInfo(reqcsBMProductUpdVo);
		return response;
	}


	/**
	 * 初审复核确认(同意)(不同意)(拆分)
	 * @param request
	 * @return
	 */
	@Override
	public Response<ResBMSAudiUpdVo> cSConfirmationReviewAgreeOrDisagreeUpd(ReqCsUpdVO request) {
		Response<ResBMSAudiUpdVo> res = new Response<ResBMSAudiUpdVo>();

		AFirstAgreeOrDisagreeEntity AFirstAgreeOrDisagreeEntity = new AFirstAgreeOrDisagreeEntity();
		// 校验必填项
		BeanUtils.copyProperties(request, AFirstAgreeOrDisagreeEntity);
		Response<ResBMSAudiUpdVo> validate = Validate.getInstance().validate(AFirstAgreeOrDisagreeEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		AFirstAgreeOrDisagreeEntity.chack();
		cSConfirmationReviewAgreeOrDisagreeUpdChack(request);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", request.getLoanNo());
		map.put("checkNodeStatus", request.getCheckNodeStatus());
		map.put("auditReviewTime", new Date());
		map.put("checkPersonCode",request.getcSPersonCode());
		map.put("complexPersonCode", request.getComplexPersonCode());

		//复核不同意标识
		if(request.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())){
			map.put("ifCheckReturn", "1");
		}

		firstAuditDao.updAuditNo(map);


		map.put("version", request.getVersion());
		if(request.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())){
			//TODO 是否新生件标识
			map.put("ifNewLoanNo", EnumConstants.ifNewLoanNo.NOLOANNO.getValue());

			//复核不同意  回退到 申请状态
			map.put("status", EnumConstants.LoanStatus.APPLY.getValue());

			/*//如果有黑名单，删除黑名单
			bMSQualityInspectionService.removeBlackListId(request.getLoanNo());*/
		}
		if(request.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKPASS.getValue())){
			Map<String,Object> maps=new HashMap<String,Object>();
			maps.put("loanNo",  request.getLoanNo());
			InformationEntity infomation=loanBaseService.queryInformationEntity(maps);
			if(infomation.getRtfNodeState().equals("XSCS-REJECT")){
				BlackGreyVO blackGreyVO = new BlackGreyVO();
				blackGreyVO.setLoanNo(request.getLoanNo());
				blackGreyVO.setFirstLevelReasonsCode(infomation.getFirstLevelReasonCode());
				blackGreyVO.setFirstLevelReasons(infomation.getPrimaryReason());
				blackGreyVO.setTwoLevelReasonsCode(infomation.getTwoLevelReasonCode());
				blackGreyVO.setTwoLevelReasons(infomation.getSecodeReason());
				blackGreyVO.setOperatorCode(request.getOperatorCode());
				ResReassignmentUpdVO ifSaveBlackGrey = bMSQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
				Map<String,Object>hMaps=new HashMap<String, Object>();
				if(ifSaveBlackGrey.isIfSuccessful()){
					//保存灰名单
					hMaps.put("blackList", ifSaveBlackGrey.getMsg());
					hMaps.put("loanNo", request.getLoanNo());
					firstAuditDao.updLoanAdExt(hMaps);
				}else{
					logger.info("["+request.getLoanNo()+"]将黑名单存入行为库错误：原因："+ifSaveBlackGrey.getMsg());
				}

			}			
		}

		firstAuditDao.updLoanBase(map);
		
		
		//绑定华证报告信息 add by gaohx 2017-08-23
		try{
			boolean bool = bMSResFirstAuditService.getHZReportInfo(request);
			if(bool){
				logger.info("复核确认华征报告绑定成功！");
			}else{
				logger.info("复核确认华征报告绑定失败！");
			}
		}catch(Exception e){
			logger.info("复核确认华征报告绑定异常！"+e);
		}



		/**
		 * 记录系统日志
		 */
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		mapQuery.put("loanNo", request.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(mapQuery);
		// 插入借款日志表
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		BMSLoanLog loanLog=new BMSLoanLog();
		loanLog.setLoanNo(request.getLoanNo());
		BMSLoanLog logs=logService.findLogReasonByCode(loanLog);
		if(logs!=null){
			bmsSysLoanLog.setFirstLevleReasonsCode(logs.getFirstLevleReasonsCode());
			bmsSysLoanLog.setFirstLevleReasons(logs.getFirstLevleReasons());
			bmsSysLoanLog.setTwoLevleReasonsCode(logs.getTwoLevleReasonsCode());
			bmsSysLoanLog.setTwoLevleReasons(logs.getTwoLevleReasons());
		}
		bmsSysLoanLog.setLoanNo(request.getLoanNo());
		if(request.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKPASS.getValue())){
			bmsSysLoanLog.setStatus(by.getStatus());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REVIEW_PASS.getValue());
			if(logs!=null){
				bmsSysLoanLog.setRemark(logs.getRemark());
			}
		}else if(request.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())){
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REVIEW_NO_PASS.getValue());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
			bmsSysLoanLog.setRemark(request.getRemark());
		}
		bmsSysLoanLog.setRtfState(by.getRtfState());
		bmsSysLoanLog.setRtfNodeState(by.getRtfNodeState());
		bmsSysLoanLog.setCheckNodeState(request.getCheckNodeStatus());
		bmsSysLoanLog.setOperatorCode(request.getOperatorCode());
		ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(request.getOperatorCode());
		ResEmployeeVO  resVo=pmsClientService.findEmployeeByCode(reqEmployeeVO);
		if(resVo!=null){
			bmsSysLoanLog.setOperator(resVo.getName());
		}

		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		bmsSysLoanLog.setCheckPersonCode(request.getcSPersonCode());
		bmsSysLoanLog.setComplexPersonCode(request.getComplexPersonCode());
		ReqParamVO csParamVO=new ReqParamVO();
		csParamVO.setLoginUser(request.getOperatorCode());
		csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
		ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
		if(groupVo!=null){
			bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
			bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
			bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
		}
		bmsSysLoanLogDao.insert(bmsSysLoanLog);		
		return res;
	}

	/**
	 * 初审批量改派
	 */
	@Override
	public Response<List<ResReassignmentUpdVO>> plCsReassignmentUpd(ReqBMSReassignmentBatchVo reqBMSReassignmentUpdVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSReassignmentUpdVo));
		Response<List<ResReassignmentUpdVO>> response = new Response<List<ResReassignmentUpdVO>>();

		AFirstReassignmentUpdEntity reassignmentUpdEntity = new AFirstReassignmentUpdEntity();
		BeanUtils.copyProperties(reqBMSReassignmentUpdVo, reassignmentUpdEntity);
		Response<List<ResReassignmentUpdVO>> validate = Validate.getInstance().validate(reassignmentUpdEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		reassignmentUpdEntity.chack();

		try {
			List<ResReassignmentUpdVO> updateReassignmentNew = bMSResFirstAuditService.updateReassignmentNew(reqBMSReassignmentUpdVo);
			response.setData(updateReassignmentNew);
		} catch (Exception e) {
			logger.info("------请求更新审核改派异常，请求参数-------" + gson.toJson(reqBMSReassignmentUpdVo));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	/**
	 * 初审批量拒绝
	 */
	@Override
	public Response<List<ResReassignmentUpdVO>> refusePlRejectUpdStatus(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqCsRefusePlupdateStatusVO));
		Response<List<ResReassignmentUpdVO>> res = new Response<List<ResReassignmentUpdVO>>();


		AFirstCsRefusePlupdateEntity csRefusePlupdateEntity = new AFirstCsRefusePlupdateEntity();
		BeanUtils.copyProperties(reqCsRefusePlupdateStatusVO, csRefusePlupdateEntity);
		Response<List<ResReassignmentUpdVO>> validate = Validate.getInstance().validate(csRefusePlupdateEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		csRefusePlupdateEntity.chack();


		List<ResReassignmentUpdVO> reassignmentPlUpdStatusNew = bMSResFirstAuditService.reassignmentPlUpdStatusNew(reqCsRefusePlupdateStatusVO);

		res.setData(reassignmentPlUpdStatusNew);
		return res;
	}

	@Override
	public Response<List<ResReassignmentUpdVO>> reassignmentPlReturnUpdStatus(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqCsRefusePlupdateStatusVO));
		Response<List<ResReassignmentUpdVO>> res = new Response<List<ResReassignmentUpdVO>>();


		AFirstCsRefusePlupdateEntity csRefusePlupdateEntity = new AFirstCsRefusePlupdateEntity();
		BeanUtils.copyProperties(reqCsRefusePlupdateStatusVO, csRefusePlupdateEntity);
		Response<List<ResReassignmentUpdVO>> validate = Validate.getInstance().validate(csRefusePlupdateEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		csRefusePlupdateEntity.chack();



		List<ResReassignmentUpdVO> ultimatePlUpdStatusNew = bMSResFirstAuditService.ultimatePlUpdStatusNew(reqCsRefusePlupdateStatusVO);
		res.setData(ultimatePlUpdStatusNew);
		return res;
	}


	/**
	 * 校验
	 * @param request
	 */
	private void chackStatusOnVersion(ReqCsUpdVO request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", request.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(map);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		//办理和挂起  都能够操作该件
		if(!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSASSIGN.getValue())
				&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSHANGUP.getValue())){
			throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
		}

		if(by.getVerson() != request.getVersion()){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
	}

	/**
	 * 更新产品校验version
	 * 校验
	 * @param request
	 */
	private LoanBaseEntity chackVersion(ReqcsBMProductUpdVo request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", request.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(map);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		if(!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSASSIGN.getValue())
				&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSHANGUP.getValue())){
			throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
		}
		if(by.getVerson() != request.getVersion()){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		return by;
	}

	public void cSConfirmationReviewAgreeOrDisagreeUpdChack(ReqCsUpdVO request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", request.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(map);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}

		if(by.getVerson() != request.getVersion()){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}

		LoanAuditEntity by2 = iLoanAuditService.getBy(map);
		if(by2 == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		if(!by2.getCheck_node_state().equals(EnumConstants.ChcekNodeState.CHECK.getValue())){
			logger.info("当前的复核节点状态已更新，无法再次更新~"+request.getLoanNo());
			throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
		}
	}

	//@SuppressWarnings("unused")
	@Override
	public Response<Integer> findTrialByStatus(ReqCsUpdVO request) {
		logger.info("接口开始----------------------------------"+request);
		Response<Integer> response=new Response<Integer>();
		if(null==request){
			response.setRepCode(BizErrorCode.BIZ_EOERR.getErrorCode());
			response.setRepMsg("ReqCsUpdVO"+BizErrorCode.BIZ_EOERR.getDefaultMessage());
			return response;
		}
		if(null==request.getcSPersonCode()||request.getcSPersonCode().length()==0){
			response.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
			response.setRepMsg("code"+BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
			return response;
		}
		Integer res=bMSResFirstAuditService.findTrialByStatus(request.getcSPersonCode());
		response.setData(res);
		return response;
	}
	/***
	 * 根据前台传过来的数据，信息排序整理
	 */
	public static String getSortValue(ReqBMSReassignmentVo reqBMSReassignmentVo){
		if(StringUtils.isEmpty(reqBMSReassignmentVo.getFieldSort())){
			return "a.CREATED_TIME asc";
		}
		String sort = " desc";
		if(reqBMSReassignmentVo.getRulesSort() == 1){
			sort = " asc";
		}
		String field = " a.CREATED_TIME";
		if("loanNo".equals(reqBMSReassignmentVo.getFieldSort())){
			field="a.LOAN_NO";
		}else if("xsSubDate".equals(reqBMSReassignmentVo.getFieldSort())){
			field = "a.CREATED_TIME";
		}else if("customerName".equals(reqBMSReassignmentVo.getFieldSort())){
			field="CONVERT(a.NAME USING gbk)";
		}else if("customerIDNO".equals(reqBMSReassignmentVo.getFieldSort())){
			field="a.ID_NO";
		}else if("productName".equals(reqBMSReassignmentVo.getFieldSort())){
			field="CONVERT(a.PRODUCT_NAME USING gbk)";
		}else if("checkPerson".equals(reqBMSReassignmentVo.getFieldSort())){
			field = "CONVERT(a.CHECK_PERSON USING gbk)";
		}else if("owningBranchId".equals(reqBMSReassignmentVo.getFieldSort())){
			field="a.OWNING_BRANCH_ID";
		}else if("ifNewLoanNo".equals(reqBMSReassignmentVo.getFieldSort())){
			field="a.IF_NEW_LOAN_NO";
		}else{
			field="a.CREATED_TIME";
		}
		return field + sort;
	}
	@Override
	public PageResponse<ResBMSReassignmentVo> firstTrialReassignmentQuery(ReqBMSReassignmentVo reqBMSReassignmentVo) {
		logger.info("----------------开始请求计时------------"+System.currentTimeMillis());
		logger.info("请求参数:" + gson.toJson(reqBMSReassignmentVo));
		PageResponse<ResBMSReassignmentVo> response = new PageResponse<ResBMSReassignmentVo>();
		// 验参
		if (reqBMSReassignmentVo != null) {
			if (StringUtils.isEmpty(reqBMSReassignmentVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!reqBMSReassignmentVo.getSysCode().equals(EnumConstants.AMS_SYSTEM_CODE)) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (StringUtils.isEmpty(reqBMSReassignmentVo.getHandleCode())) {
				if (StringUtils.isEmpty(reqBMSReassignmentVo.getLoginPersonCode())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"loginPersonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
				}
			}
			if (!StringUtils.isEmpty(reqBMSReassignmentVo.getFpStatus())) {
				if (!ValidataUtil.isExtisNewOrOldStatus(reqBMSReassignmentVo.getFpStatus())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"fpStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			//			if (reqBMSReassignmentVo.getFlag().equals(EnumConstants.ReqFlag.CS.getValue())) {
			//				if (!StringUtils.isEmpty(reqBMSReassignmentVo.getCaseType())) {
			//					if (!ValidataUtil.isExtisCaseType(reqBMSReassignmentVo.getCaseType())) {
			//						return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
			//								"caseType" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			//					}
			//				}
			//			}
			if (!StringUtils.isEmpty(reqBMSReassignmentVo.getXsStartDate())) {
				if (!ValidataUtil.validataDate(reqBMSReassignmentVo.getXsStartDate())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"xsStartDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (!StringUtils.isEmpty(reqBMSReassignmentVo.getXsEndDate())) {
				if (!ValidataUtil.validataDate(reqBMSReassignmentVo.getXsEndDate())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"xsEndDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (reqBMSReassignmentVo.getPageNum() == 0 || reqBMSReassignmentVo.getPageSize() == 0) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"pageNum|pageSize" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			try {
				/*************************** 初审改派 **********************************/
				/**
				 * 构造数据响应
				 */
				List<String> saveCsPersonCodes = new ArrayList<String>();
				List<BMSReassigMentEntity> saveProxyGroupNames = null;
				PageParam pageParam = new PageParam(reqBMSReassignmentVo.getPageNum(),
						reqBMSReassignmentVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSReassignmentVo);

				if (StringUtils.isEmpty(reqBMSReassignmentVo.getHandleCode())) {// 如果处理人为空查询登录人下面的组员
					/** 调权限系统获取员工身份 **/
					ReqParamVO reqParamVO = new ReqParamVO();
					reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					reqParamVO.setLoginUser(reqBMSReassignmentVo.getLoginPersonCode());
					//						reqParamVO.setRoleCode(RoleEnum.CHECK_MANAGER.getCode());
					//						Response<Boolean> resFlag = employeeExecuter.hasRole(reqParamVO);
					//						reqParamVO.setRoleCode(RoleEnum.SUPER_ADMIN.getCode());
					Response<Boolean> isAdmin = employeeExecuter.isManagerAbove(reqParamVO);
					if(null==isAdmin){
						logger.info("————————掉平台获取当前登入人信息返回为{null}—————————————");
					}
					if(!isAdmin.isSuccess()){
						logger.info("————————掉平台获取当前登入人信息返回失败,接口返回非[000000]—————————————");
					}
					logger.info("——————————掉平台接口返回的数据结果是否是经理级别———————————"+isAdmin.getData());
					if (StringUtils.isEmpty(reqBMSReassignmentVo.getHandleCode())) {
						if (isAdmin.getData()) {// 如果是初审组经理或admin
							paramMap.put("flag2", EnumConstants.MANAGER);
						} else {
							paramMap.put("flag2", EnumConstants.NOT_MANAGER);
						}
					} else {
						paramMap.put("handleCode", reqBMSReassignmentVo.getHandleCode());
					}
					ReqParamVO reqParamVO2 = new ReqParamVO();
					reqParamVO2.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					reqParamVO2.setLoginUser(reqBMSReassignmentVo.getLoginPersonCode());
					reqParamVO2.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
					logger.info("---------------根据工号和角色获取下级员工开始-----------");
					Response<List<ResEmpOrgVO>> powerResponse = employeeExecuter.findLowerByAccount(reqParamVO2);
					logger.info("---------------根据工号和角色获取下级员工结束-----------"+gson.toJson(powerResponse));
					saveProxyGroupNames = new ArrayList<BMSReassigMentEntity>();
					if (powerResponse.isSuccess()) {
						List<ResEmpOrgVO> empOrglist = powerResponse.getData();
						for (ResEmpOrgVO resEmpOrgVO : empOrglist) {
							BMSReassigMentEntity reassigMentEntity = new BMSReassigMentEntity();
							saveCsPersonCodes.add(resEmpOrgVO.getUsercode());
							reassigMentEntity.setProxyGroupName(resEmpOrgVO.getName());
							reassigMentEntity.setCheckPersonCode(resEmpOrgVO.getUsercode());
							saveProxyGroupNames.add(reassigMentEntity);
						}
					}

				}else{
					String arrayHangle=reqBMSReassignmentVo.getHandleCode();
					arrayHangle=arrayHangle.replaceAll("'", "");
					String[] arrays=arrayHangle.split(",");
					Boolean countIsAdmin=false;
					for(int i=0;i<arrays.length;i++){
						ReqParamVO reqParamVO = new ReqParamVO();
						reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
						reqParamVO.setLoginUser(arrays[i]);
						//							reqParamVO.setRoleCode(RoleEnum.CHECK_MANAGER.getCode());
						//							Response<Boolean> resFlag = employeeExecuter.hasRole(reqParamVO);
						//							reqParamVO.setRoleCode(RoleEnum.SUPER_ADMIN.getCode());
						Response<Boolean> isAdmin = employeeExecuter.isManagerAbove(reqParamVO);
						if(null==isAdmin){
							logger.info(i+"————————掉平台获取当前登入人信息返回为{null}—————————————");
						}
						if(!isAdmin.isSuccess()){
							logger.info(i+"————————掉平台获取当前登入人信息返回失败,接口返回非[000000]—————————————");
						}
						logger.info(i+"——————————掉平台接口返回的数据结果是否是经理级别———————————"+isAdmin.getData());
						if (isAdmin.getData()) {// 如果是初审组经理或admin
							countIsAdmin=true;
						} 
					}



					if (countIsAdmin) {
						paramMap.put("flag2", EnumConstants.MANAGER);
					} else{
						paramMap.put("flag2", EnumConstants.NOT_MANAGER);
						paramMap.put("handleCode", reqBMSReassignmentVo.getHandleCode());
					}


				}
				if (StringUtils.isEmpty(reqBMSReassignmentVo.getHandleCode())) {
					saveCsPersonCodes.add(reqBMSReassignmentVo.getLoginPersonCode());
					paramMap.put("saveCsPersonCodes", saveCsPersonCodes);
				}
				if (reqBMSReassignmentVo.getCaseIdentifyList() != null && !reqBMSReassignmentVo.getCaseIdentifyList().isEmpty()) {
					paramMap.put("caseIdentifyList", reqBMSReassignmentVo.getCaseIdentifyList());
				} else {
					paramMap.put("caseIdentifyList", null);
				}
				paramMap.put("sortValue", getSortValue(reqBMSReassignmentVo));
				logger.info("---------------查询数据开始-----------");
				@SuppressWarnings({ "rawtypes" })

				PageBean pageBean = bMSResFirstAuditService.listPage(pageParam, paramMap, QUERY_CS_REASSIGNMENT_SQL_UPDATE,
						COUNT_CS_REASSIGNMENT_SQL_UPDATE);
				@SuppressWarnings("unchecked")
				List<BMSReassigMentEntity> reassignMents = pageBean.getRecords();
				logger.info("---------------查询数据结束-----------");
				List<ResBMSReassignmentVo> records = new ArrayList<ResBMSReassignmentVo>();
				// 调权限系统api,根据营业部ID查询名称和地区
				List<Long> orgIdList = new ArrayList<Long>();
				for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
					if (reassigMentEntity.getOwningBranchId() != null) {
						orgIdList.add(Long.parseLong(reassigMentEntity.getOwningBranchId()));
					}
					ReqParamVO zSreqCsParamVO = new ReqParamVO();
					zSreqCsParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					zSreqCsParamVO.setLoginUser(reassigMentEntity.getCheckPersonCode());
					zSreqCsParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
					logger.info("调权限系统接口" + gson.toJson(reassigMentEntity.getCheckPersonCode()));
					Response<ResGroupVO> orgResponses = null;
					if(null!=zSreqCsParamVO.getLoginUser()){
						orgResponses = OrganizationExecuter.findGroupInfoByAccount(zSreqCsParamVO);
					}
					if(null==orgResponses){
						logger.info("————————chenckpersonCodee为NULL 不去调用平台————————————");
					}else{
						logger.info("————————chenckpersonCodee不为NULL 去调用平台————————————");
					}
					if(null!=orgResponses){
						if (orgResponses.isSuccess()) {
							if(orgResponses.getData().getGroupName()!=null && orgResponses.getData().getBigGroupName()!=null){
								reassigMentEntity.setcSProxyGroupName(orgResponses.getData().getBigGroupName()+orgResponses.getData().getGroupName()); 
							}else if(orgResponses.getData().getBigGroupName()!=null && orgResponses.getData().getGroupName()!=null){
								reassigMentEntity.setcSProxyGroupName(orgResponses.getData().getFuncName()+orgResponses.getData().getBigGroupName()); 
							}
						}
					}

					//判断是哪一组添加组
					//				            ReqParamVO cSreqCsParamVO=new ReqParamVO();
					//				            cSreqCsParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					//				            cSreqCsParamVO.setUsercode(reassigMentEntity.getCheckPersonCode());
					//				            Response<List<ResOrganizationVO>> orgResponse=null;
					//				            logger.info("————————userCode值—————————————"+cSreqCsParamVO.getUsercode());
					//				            if(null!=cSreqCsParamVO.getUsercode()){
					//				            	orgResponse = OrganizationExecuter.findGroupByAccount(cSreqCsParamVO);
					//				            }
					//				            
					//				            if(null==orgResponse){
					//								logger.info("————————userCode为null不去调用平台—————————————");
					//							}else{
					//								logger.info("————————userCode不为null去调用平台—————————————");
					//								if(orgResponse.getData()!=null&&orgResponse.getData().size()>0){
					//					            	reassigMentEntity.setProxyGroupName(orgResponse.getData().get(0).getName());
					//					            }
					//							}


				}
				ReqParamVO reqParamVO3 = new ReqParamVO();
				reqParamVO3.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqParamVO3.setOrgIds(orgIdList);
				Response<List<ResOrgNameAreaVO>> byOrgIdResponse = OrganizationExecuter.findNameByIds(reqParamVO3);
				if (byOrgIdResponse.isSuccess()) {
					List<ResOrgNameAreaVO> orgs = byOrgIdResponse.getData();
					for (ResOrgNameAreaVO resOrgNameAreaVO : orgs) {
						for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
							if (reassigMentEntity.getOwningBranchId().equals(resOrgNameAreaVO.getOrgId() == null ? 0
									: resOrgNameAreaVO.getOrgId().toString())) {
								reassigMentEntity.setOwningBranchName(resOrgNameAreaVO.getOrgName());
								reassigMentEntity.setOwningBranchAttr(resOrgNameAreaVO.getDepLevel());
							}
						}
					}
				}
				//					if(saveProxyGroupNames!=null){
				//						for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
				//							for (BMSReassigMentEntity reassigMentEntityTwo : saveProxyGroupNames) {
				//								if (reassigMentEntity.getCheckPersonCode() != null
				//										&& reassigMentEntityTwo.getCheckPersonCode() != null) {
				//									if (reassigMentEntity.getCheckPersonCode()
				//											.equals(reassigMentEntityTwo.getCheckPersonCode())) {
				//										reassigMentEntity.setProxyGroupName(reassigMentEntityTwo.getProxyGroupName());
				//									}
				//								}
				//
				//							}
				//						}
				//					}
				for (BMSReassigMentEntity bmsReassigMentEntity : reassignMents) {
					ResBMSReassignmentVo resBMSReassignmentVo = new ResBMSReassignmentVo();
					BeanUtils.copyProperties(bmsReassigMentEntity, resBMSReassignmentVo);
					if(resBMSReassignmentVo.getAppInputFlag()==null){
						resBMSReassignmentVo.setAppInputFlag("0");
					}else{
						resBMSReassignmentVo.setAppInputFlag("1");
					}
					records.add(resBMSReassignmentVo);
				}
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);


			} catch (Exception e) {
				e.printStackTrace();
				logger.info("------请求审核改派异常，请求参数-------" + gson.toJson(reqBMSReassignmentVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqBMSReassignmentVo请求对象为空" + gson.toJson(reqBMSReassignmentVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSReassignmentVo" });
		}
		logger.info("请求接口结束计时---------------"+System.currentTimeMillis());
		return response;
	}

	/**
	 * 初审已完成任务查询
	 */
	@Override
	public PageResponse<ResOffTheStocksAuditVO> cSAduditOffTheStocks(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSAdultOffTheStocksVo));
		PageResponse<ResOffTheStocksAuditVO> pageResponse = new PageResponse<ResOffTheStocksAuditVO>();
		if (reqBMSAdultOffTheStocksVo != null) {
			if (StringUtils.isBlank(reqBMSAdultOffTheStocksVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if(StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getOffStartDate())){
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"offStartDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}

			if(!ValidataUtil.validataDate(reqBMSAdultOffTheStocksVo.getOffStartDate())){
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"offStartDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), pageResponse);
			}

			if (StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getAuditPersonCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"auditPersonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if (StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getOperatorCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if (StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getOperatorIP())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if (!ValidataUtil.isIP(reqBMSAdultOffTheStocksVo.getOperatorIP())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), pageResponse);
			}
			if (reqBMSAdultOffTheStocksVo.getPageNum() == 0 || reqBMSAdultOffTheStocksVo.getPageSize() == 0) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"pageNum|pageSize" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			try {
				// 先去权限系统查询改员工的身份
				PageParam pageParam = new PageParam(reqBMSAdultOffTheStocksVo.getPageNum(),reqBMSAdultOffTheStocksVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSAdultOffTheStocksVo);
				String flag2=iSSuperAdmin(reqBMSAdultOffTheStocksVo.getAuditPersonCode());
				if(flag2.equals("1")){
					paramMap.put("flag2", EnumConstants.MANAGER);
				}
				// 获取所在组
				paramMap.put("checkPersonCd", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
				List<BMSFirstAuditEntity> loanNos = bMSResFirstAuditService.queryBmsLogByLoan(paramMap);
				PageBean<BMSFirstAuditEntity> pageBean = null;
				List<ResOffTheStocksAuditVO> records = new ArrayList<ResOffTheStocksAuditVO>();
				if (loanNos != null && loanNos.size() > 0) {
					paramMap.put("loans", loanNos);
					pageBean = bMSResFirstAuditService.listPage(pageParam, paramMap,QUERY_CS_AUDITOFFTHESTOCKS_SQL,COUNT_CS_AUDITOFFTHESTOCKS_SQL);
				} else {	
					return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"查询出来的loans集合" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
				}
				List<BMSFirstAuditEntity> loanList = pageBean.getRecords();
				List<BMSFirstAuditEntity> saveIsHostioryLoans = new ArrayList<BMSFirstAuditEntity>();
				List<Long> orgIds= new ArrayList<Long>();
				for (BMSFirstAuditEntity bmsFirstAuditEntity : loanList) {
					for (BMSFirstAuditEntity firstAuditEntity : loanNos) {
						if (bmsFirstAuditEntity.getLoanNo().equals(firstAuditEntity.getLoanNo())) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("checkPersonCd", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
							map.put("loanNo", firstAuditEntity.getLoanNo());
							List<BMSFirstAuditEntity> firstlAudit=bMSResFirstAuditService.querycSFirstOperationTime(map);
							if(firstlAudit.size()>0){
								bmsFirstAuditEntity.setCsStartDate(firstlAudit.get(0).getOperationTime());	
							}
							bmsFirstAuditEntity.setHistorNodeStatus(firstAuditEntity.getRefNodeStatus());
							bmsFirstAuditEntity.setOperationTime(firstAuditEntity.getOperationTime());
							continue;
						}										
					}
					saveIsHostioryLoans.add(bmsFirstAuditEntity);
				}
				for (int i = 0; i < pageBean.getRecords().size(); i++) {
					if( pageBean.getRecords().get(i).getOwningBranceId()!=null){
						orgIds.add(Long.parseLong( pageBean.getRecords().get(i).getOwningBranceId()));
					}
				}
				// 调权限系统api,根据营业部ID查询名称和地区
				ReqParamVO reqOrgDetail = new ReqParamVO();
				reqOrgDetail.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqOrgDetail.setOrgIds(orgIds);
				Response<List<ResOrgNameAreaVO>> byOrgIdResponse = OrganizationExecuter.findNameByIds(reqOrgDetail);

				// 塞数据将调接口返回的机构名称塞进去
				if (byOrgIdResponse.isSuccess() && byOrgIdResponse != null) {
					for (int i = 0; i < byOrgIdResponse.getData().size(); i++) {
						ResOrgNameAreaVO resOrgNameAreaVO = byOrgIdResponse.getData().get(i);
						for (int j = 0; j < pageBean.getRecords().size(); j++) {
							BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(j);
							if (bmsFirstAuditEntity.getOwningBranceId() != null) {
								if (resOrgNameAreaVO.getOrgId() == Long
										.parseLong(bmsFirstAuditEntity.getOwningBranceId())) {
									bmsFirstAuditEntity.setOwningBrance(resOrgNameAreaVO.getOrgName());
									bmsFirstAuditEntity.setOwningBranceAttribute(resOrgNameAreaVO.getDepLevel());// 营业部属性
								}
							}
							if(bmsFirstAuditEntity.getAppInputFlag()!=null){
								bmsFirstAuditEntity.setAppInputFlag("1");
							}else{
								bmsFirstAuditEntity.setAppInputFlag("0");
							}
						}
					}
				}
				for (BMSFirstAuditEntity bmsFirstAuditEntity : saveIsHostioryLoans) {
					ResOffTheStocksAuditVO resOffTheStocksAuditVO = new ResOffTheStocksAuditVO();
					BeanUtils.copyProperties(bmsFirstAuditEntity, resOffTheStocksAuditVO);
					records.add(resOffTheStocksAuditVO);
				}
				BeanUtils.copyProperties(pageBean, pageResponse, "records");
				pageResponse.setRecords(records);

			} catch (Exception e) {
				logger.info("------查询审核已完成任务接口异常，请求参数-------" + gson.toJson(reqBMSAdultOffTheStocksVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqBMSAdultOffTheStocksVo请求对象为空" + gson.toJson(reqBMSAdultOffTheStocksVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSAdultOffTheStocksVo" });
		}
		return pageResponse;

	}
	/**
	 * 初审待办任务查询
	 */
	@Override
	public PageResponse<ResBMSAuditVo> cSWaitForTheStocks( ReqAuditVo reqFirstAuditVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqFirstAuditVo));
		PageResponse<ResBMSAuditVo> response = new PageResponse<ResBMSAuditVo>();
		// 验证参数
		if (reqFirstAuditVo != null) {
			if (StringUtils.isEmpty(reqFirstAuditVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqFirstAuditVo.getCaseType())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"caseType" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (!ValidataUtil.isExtisCaseType(reqFirstAuditVo.getCaseType())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"caseType" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			}
			if (reqFirstAuditVo.getPageNum() == 0 || reqFirstAuditVo.getPageSize() == 0) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"pageNum | pageSize" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			try {
				PageBean<BMSFirstAuditEntity> pageBean = null;
				// 构造请求参数
				PageParam pageParam = new PageParam(reqFirstAuditVo.getPageNum(), reqFirstAuditVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqFirstAuditVo);
				String flag=iSSuperAdmin(reqFirstAuditVo.getServiceCode());
				if(flag.equals(EnumConstants.YES)){
					paramMap.put("isAdmin", "1");
				}
				paramMap.put("checkPersonCd", reqFirstAuditVo.getServiceCode());					
				// 调用业务逻辑
				pageBean = bMSResFirstAuditService.listPage(pageParam, paramMap,QUERY_CS_WAITFORTHESTOCKS_SQL,COUNT_CS_WAITFORTHESTOCKS_SQL);
				List<Long> orgIdList = new ArrayList<Long>();
				if (pageBean != null) {
					for (int i = 0; i < pageBean.getRecords().size(); i++) {
						BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(i);
						if (bmsFirstAuditEntity.getOwningBranceId() != null) {
							orgIdList.add(Long.parseLong(bmsFirstAuditEntity.getOwningBranceId()));
						}
						// 调行为库,获取是否疑似欺诈
						Map<String, Object>map =new HashMap<String,Object>();
						map.put("loanNo", bmsFirstAuditEntity.getLoanNo());
						BMSAppPersonInfo appPersonInfo = BMSAppPersonInfoService.byLoanNoQueryInfo(map);
						if(appPersonInfo!=null){
							bmsFirstAuditEntity.setIfSuspectCheat(bDSClientService.ifSuspectCheat(appPersonInfo).toString());
						}
						if(bmsFirstAuditEntity.getAppInputFlag()!=null){
							bmsFirstAuditEntity.setAppInputFlag("1");
						}else{
							bmsFirstAuditEntity.setAppInputFlag("0");
						}
					}

				}
				// 调权限系统api,根据营业部ID查询名称和地区
				ReqParamVO reqParamVO = new ReqParamVO();
				reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqParamVO.setOrgIds(orgIdList);
				Response<List<ResOrgNameAreaVO>> byOrgIdResponse = OrganizationExecuter.findNameByIds(reqParamVO);
				// 塞数据将调接口返回的机构名称塞进去
				if (byOrgIdResponse.isSuccess() && byOrgIdResponse != null) {
					for (int i = 0; i < byOrgIdResponse.getData().size(); i++) {
						ResOrgNameAreaVO resOrgNameAreaVO = byOrgIdResponse.getData().get(i);
						for (int j = 0; j < pageBean.getRecords().size(); j++) {
							BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(j);
							if (bmsFirstAuditEntity.getOwningBranceId() != null) {
								if (resOrgNameAreaVO.getOrgId() == Long
										.parseLong(bmsFirstAuditEntity.getOwningBranceId())) {
									bmsFirstAuditEntity.setOwningBrance(resOrgNameAreaVO.getOrgName());
									bmsFirstAuditEntity.setOwningBranceAttribute(resOrgNameAreaVO.getDepLevel());// 营业部属性
								}
							}
						}
					}
				}

				// 构造响应参数
				List<ResBMSAuditVo> records = new ArrayList<ResBMSAuditVo>();
				List<BMSFirstAuditEntity> firstAuditEntitys = pageBean.getRecords();
				for (BMSFirstAuditEntity firstAuditEntity : firstAuditEntitys) {
					ResBMSAuditVo resFirstAuditVo = new ResBMSAuditVo();
					BeanUtils.copyProperties(firstAuditEntity, resFirstAuditVo);
					records.add(resFirstAuditVo);
				}
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);

			} catch (Exception e) {
				logger.info("请求异常，请求参数:" + gson.toJson(reqFirstAuditVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqFirstAuditVo" });
		}
		return response;
	}
	/**
	 * 审核台查询响应对象
	 * 
	 * @param <T>
	 */
	public static <T> PageResponse<T> resAuditQueryInfo(String errCode, String errMsg, PageResponse<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}
	/**
	 * 根据员工号判断该员工是否是经理
	 * */
	public String iSSuperAdmin(String serviceCode){
		ReqParamVO req = new ReqParamVO();
		req.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		req.setLoginUser(serviceCode);
		req.setRoleCode(RoleEnum.SUPER_ADMIN.getCode());		
		Response<Boolean> resFlag = employeeExecuter.hasRole(req);
		if(resFlag.getData()){//如果是admin
			return EnumConstants.YES;
		}
		return  EnumConstants.NO;
	}

	@Override
	public Response<Object> getHZReportIDInfo(ReqCsUpdVO reqCsUpdVO) {
		Response<Object> response = new Response<Object>();
		Map<String,Object> resultMap = new HashMap<>();
		
		Map<String,Object> map = firstAuditDao.getPersonPhone(reqCsUpdVO.getLoanNo());
		if(map != null && !map.isEmpty()){
			resultMap.put("mobileOnlineID", map.get("long_online_id"));
			resultMap.put("idCardCheckID", map.get("real_name_auth_id"));
			response.setData(resultMap);
		}
		return response;
	}

	@Override
	public Response<Object> syncHZReportID(ReqCsUpdVO reqCsUpdVO) {
		Response<Object> response = new Response<Object>();
		String longOnlineId = ObjectUtils.toString(reqCsUpdVO.getLongOnlineId());
		String realNameAuthId = ObjectUtils.toString(reqCsUpdVO.getRealNameAuthId());
		String loanNo = ObjectUtils.toString(reqCsUpdVO.getLoanNo());
		
		if(org.apache.commons.lang.StringUtils.isBlank(longOnlineId)){
			logger.info("信审同步华征在网时长信息为空！");
		}
		if(org.apache.commons.lang.StringUtils.isBlank(realNameAuthId)){
			logger.info("信审同步华征实名认证信息为空！");
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(longOnlineId) && org.apache.commons.lang.StringUtils.isBlank(realNameAuthId)){
			response.setRepMsg("同步到借款的华征信息为空!");
			return response;
		}
		
		String loanBaseId = ObjectUtils.toString(firstAuditDao.getPersonPhone(loanNo).get("id"));
		
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("id", loanBaseId);
		paramsMap.put("mobileOnlineId", longOnlineId);
		paramsMap.put("idCardCheckId", realNameAuthId);
		
		int count = firstAuditDao.updateHZReportId(paramsMap);
		if(count > 0){
			response.setRepMsg("同步成功");
		}else{
			response.setRepMsg("同步失败");
		}
		return response;
	}

}
