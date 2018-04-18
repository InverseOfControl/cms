package com.ymkj.cms.biz.facade.audit.last;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.last.IFinalAdultExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.AuditEntryVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchAttrVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSMessageVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;
import com.ymkj.cms.biz.api.vo.response.audit.last.ResBMSFinalAduitUpdVO;
import com.ymkj.cms.biz.common.util.SortClassUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.entity.audit.BMSReassigMentEntity;
import com.ymkj.cms.biz.entity.audit.last.ALastBMSProductUpEntity;
import com.ymkj.cms.biz.entity.audit.last.ALastReassignmentUpdEntity;
import com.ymkj.cms.biz.entity.audit.last.AXsSystemRejectEntity;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.ContractFirstAdultExecuter;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.audit.BMSFirstAuditService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.audit.last.IBMSFinalAuditService;
import com.ymkj.cms.biz.service.client.BDSClientService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
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
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;


@Service
public class FinalAuditExecuter implements IFinalAdultExecuter{
	public static Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);
	// JSON 工具类
	private static Gson gson = new Gson();
	@Autowired
	private BMSFirstAuditService bMSResFirstAuditService;
	@Autowired
	private IBMSFinalAuditService bMSResFinalAuditService;
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	@Autowired
	private IEmployeeExecuter employeeExecuter;
	@Autowired
	private BMSQualityInspectionService bmsQualityService;
	@Autowired
	private BDSClientService bDSClientService;
	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;
	@Autowired
	private IBMSAppPersonInfoService BMSAppPersonInfoService;
	@Autowired
	private PMSClientService pmsClientService;
   //终审改派查询
	private final String QUERY_ZS_REASSIGNMENT_SQL = "queryZsReassignMentInfo";
	private final String COUNT_ZS_REASSIGNMENT_SQL = "reassignMentZsCount";
	//终审待办任务查询
	private final String QUERY_ZS_ZSWAITFORTHESTOCKS_SQL="queryZSWaitForTheStocks";
	private final String COUNT_ZS_ZSWAITFORTHESTOCKS_SQL="zSWaitForTheStocksCount";
	//终审已完成任务查询
	private final String QUERY_ZS_ADULTOFFTHESTOCKS_SQL="queryZsadultOffTheStocks";
	private final String COUNT_ZS_ADULTOFFTHESTOCKS_SQL="zSadultOffTheStocksCount";
	/**
	 * 终审更新接口(拒绝)
	 */
	@Override
	public ResBMSFinalAduitUpdVO zSRejectUpd(ReqZsUpdVO reqFinalVo) {
		logger.info("------------请求终审拒绝更新接口开始--------------");
		ResBMSFinalAduitUpdVO resFinalVo=new ResBMSFinalAduitUpdVO();
		if(null!=ChenckIsReturn(reqFinalVo)){
			return ChenckIsReturn(reqFinalVo);
		}
		reqFinalVo.setRtfNodeStatus(EnumConstants.RtfNodeState.XSZSREJECT.getValue());
		if(StringUtils.isEmpty(reqFinalVo.getFirstLevelReasonCode())){
			return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(), 
					"FirstLevelReasonCode"+BizErrorCode.BIZ_EOERR.getDefaultMessage(), resFinalVo);
		}
		try{
			boolean result=bMSResFinalAuditService.updateZsLoanNoState(reqFinalVo);
			if(result){
				logger.info("终审挂起更新接口调用完成");
			}
		}catch(Exception e){
			logger.info("数据库操作失败回滚|zSRejectUpd" + gson.toJson(reqFinalVo));
			throw new BizException(CoreErrorCode.DB_ERROR, e);
		}

		return resFinalVo;
	}
	/**
	 * 终审挂起接口
	 */
	@Override
	public ResBMSFinalAduitUpdVO zSHangupUpd(ReqZsUpdVO reqFinalVo) {
		logger.info("------------请求终审挂起更新接口开始--------------");
		ResBMSFinalAduitUpdVO resFinalVo=new ResBMSFinalAduitUpdVO();
		if(null!=ChenckIsReturn(reqFinalVo)){
			return ChenckIsReturn(reqFinalVo);
		}
		reqFinalVo.setRtfNodeStatus(EnumConstants.RtfNodeState.XSZSHANGUP.getValue());
		if(StringUtils.isEmpty(reqFinalVo.getFirstLevelReasonCode())){
			return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(), 
					"FirstLevelReasonCode"+BizErrorCode.BIZ_EOERR.getDefaultMessage(), resFinalVo);
		}
		try{
			boolean result=bMSResFinalAuditService.updateZsLoanNoState(reqFinalVo);
			if(result){
				logger.info("终审挂起更新接口调用完成");
			}
		}catch(Exception e){
			logger.info("数据库操作失败回滚|zSHangupUpd" + gson.toJson(reqFinalVo));
			throw new BizException(CoreErrorCode.DB_ERROR, e);
		}
		return resFinalVo;
	}
	/**
	 * 终审提交接口
	 */
	public ResBMSFinalAduitUpdVO zSSubmitUpd(ReqZsUpdVO reqFinalVo) {
		logger.info("------------请求终审提交更新接口开始--------------");
		ResBMSFinalAduitUpdVO resFinalVo=new ResBMSFinalAduitUpdVO();
		if(null!=ChenckIsReturn(reqFinalVo)){
			return ChenckIsReturn(reqFinalVo);
		}
		if (StringUtils.isEmpty(reqFinalVo.getRtfNodeStatus())) {
			return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
					"rtfNodeStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resFinalVo);
		}
		try{
			//查询产品相关信息
			boolean flag=bmsQualityService.auditCheckProductSales(reqFinalVo.getLoanNo());
			if(!flag){
				resFinalVo.setRepCode("000031");
				resFinalVo.setRepMsg("该产品已经下架");
				return resFinalVo;
			}
			boolean result=bMSResFinalAuditService.updateZsLoanNoState(reqFinalVo);
			if(result){
				logger.info("终审提交更新接口调用完成");
			}
		}catch(Exception e){
			logger.info("数据库操作失败回滚|zSSubmitUpd" + gson.toJson(reqFinalVo));
			throw new BizException(CoreErrorCode.DB_ERROR, e);
		}
		return resFinalVo;
	}
	/**
	 * 终审退回接口
	 */
	public ResBMSFinalAduitUpdVO zSReturnUpd(ReqZsUpdVO reqFinalVo) {
		logger.info("------------请求终审退回更新接口开始--------------");
		ResBMSFinalAduitUpdVO resFinalVo=new ResBMSFinalAduitUpdVO();
		if(null!=ChenckIsReturn(reqFinalVo)){
			return ChenckIsReturn(reqFinalVo);
		}
		if(StringUtils.isEmpty(reqFinalVo.getFirstLevelReasonCode())){
			return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(), 
					"FirstLevelReasonCode"+BizErrorCode.BIZ_EOERR.getDefaultMessage(), resFinalVo);
		}
		if (StringUtils.isEmpty(reqFinalVo.getRtfNodeStatus())) {
			return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
					"rtfNodeStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resFinalVo);
		}
		try{
			boolean result=bMSResFinalAuditService.updateZsLoanNoState(reqFinalVo);
			if(result){
				//记录快照
			    applyDataManipulationService.auditDifferences(reqFinalVo.getLoanNo(), EnumConstants.differences.final_audit_back.getValue());
				logger.info("终审退回更新接口调用完成");
				
				//如果退回门店，更新初审退回快照
				if(reqFinalVo.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())){
					applyDataManipulationService.auditDifferences(reqFinalVo.getLoanNo(), EnumConstants.differences.audit_back.getValue());
				}
			}
		}catch(Exception e){
			logger.info("数据库操作失败回滚|zSReturnUpd" + gson.toJson(reqFinalVo));
			throw new BizException(CoreErrorCode.DB_ERROR, e);
		}

		return resFinalVo;
	}
	/**
	 * 借款产品更新接口
	 */
	@Override
	public Response<Object> zSProductUpd(ReqBMProductUpdVo reqBMProductUpdVo) {
		logger.info("----------------终审借款产品更新接口开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMProductUpdVo));
		Response<Object> response = new Response<Object>();
		ALastBMSProductUpEntity lastBmsProductEntity=new ALastBMSProductUpEntity();
		// 校验必填项
		BeanUtils.copyProperties(reqBMProductUpdVo, lastBmsProductEntity);
		Response<Object> validate = Validate.getInstance().validate(lastBmsProductEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		lastBmsProductEntity.checkValue();
		try {

			boolean flag = bMSResFinalAuditService.updateZSProductInfo(reqBMProductUpdVo);
			if (flag) {
				logger.info("响应成功！");
			}
		} catch (Exception e) {
			logger.info("------请求信审更新借款产品信息异常，请求参数-------" + gson.toJson(reqBMProductUpdVo));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSPlReassignMentUpdVo> zSreassignmentUpd(
			ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo) {
		logger.info("----------------批量终审改派开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSReassignmentUpdVo));
		Response<ResBMSPlReassignMentUpdVo> response = new Response<ResBMSPlReassignMentUpdVo>();
		ResBMSPlReassignMentUpdVo resBMSPlReassignMentUpdVo = new ResBMSPlReassignMentUpdVo();;
		ALastReassignmentUpdEntity reassignmentUpdEntity = new ALastReassignmentUpdEntity();
		BeanUtils.copyProperties(reqBMSReassignmentUpdVo, reassignmentUpdEntity);
		Response<ResBMSPlReassignMentUpdVo> validate = Validate.getInstance().validate(reassignmentUpdEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		reassignmentUpdEntity.check();
			try {
				Map<String, Object> resMap = bMSResFinalAuditService.updateZsReassignment(reqBMSReassignmentUpdVo);
				logger.info("响应成功！"); 
				resBMSPlReassignMentUpdVo.setResList((List<ResBMSMessageVO>) resMap.get("resList"));
				response.setData(resBMSPlReassignMentUpdVo);
			} catch (Exception e) {
				logger.info("------请求更新审核改派异常，请求参数-------" + gson.toJson(reqBMSReassignmentUpdVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		return response;
	}
	@Override
	public ResBMSAutomaticDispatchVO zSAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO) {
		logger.info("----------------终审自动派单查询开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqAutomaticDispatchVO));
		List<ResBMSAutomaticDispatchAttrVO> resAutomaticList = null;
		ResBMSAutomaticDispatchVO responseAutomaticDis = new ResBMSAutomaticDispatchVO();
		if (reqAutomaticDispatchVO != null) {// 如果请求参数为空
			if (StringUtils.isEmpty(reqAutomaticDispatchVO.getSysCode())) {
				responseAutomaticDis.setRepCode(BizErrorCode.BIZ_EOERR.getErrorCode());
				responseAutomaticDis.setRepMsg("sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage());
				return responseAutomaticDis;
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqAutomaticDispatchVO.getSysCode())) {
					responseAutomaticDis.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
					responseAutomaticDis.setRepMsg("sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
					return responseAutomaticDis;
				}
			}
			try {
				resAutomaticList = new ArrayList<ResBMSAutomaticDispatchAttrVO>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("flag", reqAutomaticDispatchVO.getFlag());
				// 查询所有入单记录
				List<BMSAutomaticDispatchEntity> automaticDList = bMSResFinalAuditService.zSautomaticDispatchList(map);
				// 数据转换
				for (int i = 0; i < automaticDList.size(); i++) {
					ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchSearchListVO = new ResBMSAutomaticDispatchAttrVO();
					BMSAutomaticDispatchEntity bMSAutomaticDispatchEntity = automaticDList.get(i);
					BeanUtils.copyProperties(bMSAutomaticDispatchEntity, resBMSAutomaticDispatchSearchListVO);
					resAutomaticList.add(resBMSAutomaticDispatchSearchListVO);
				}
				responseAutomaticDis.setList(resAutomaticList);
			}
			catch (Exception e) {
				// 异常抛出堆栈信息
				logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
				throw new BizException(CoreErrorCode.DB_ERROR, e);
			}
		} else {
			logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqAutomaticDispatchVO" });
		}
		return responseAutomaticDis;
	}
	/***
	 * 根据前台传过来的数据，信息排序整理
	 */
	public static String getSortValues1(ReqBMSReassignmentVo reqBMSAdultOffTheStocksVo){
		if(StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getFieldSort())){
			return " bla.CREATED_TIME asc";
		}
		String sort = " desc";
		if(reqBMSAdultOffTheStocksVo.getRulesSort() == 1){
			sort = " asc";
		}
		String field = " bla.CREATED_TIME asc";
		if("customerName".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field = "CONVERT(blb.NAME USING gbk)";
		}else if("customerIDNO".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){	
			field = "blb.ID_NO";
		}else if("productName".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="CONVERT(blp.PRODUCT_NAME USING gbk)";
		}else if("accLmt".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="bla.ACC_LMT";
		}else if("owningBrance".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="CONVERT(blb.ENTER_BRANCH USING gbk)";
		}else if("xsSubDate".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field = "bla.CREATED_TIME";
		}else if("applyType".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.APPLY_TYPE";
		}else if("loanNo".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){  
			field="blb.LOAN_NO";
		}else if("refNodeStatus".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.RTF_NODE_STATE";
		}else if("accLmt".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="bla.ACC_LMT";
		}else{
			field=" CREATED_TIME";
		}
		return field + sort;
	}
	@Override
	public PageResponse<ResBMSReassignmentVo> zSlistPage(ReqBMSReassignmentVo reqBMSReassignmentVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSReassignmentVo));
		PageResponse<ResBMSReassignmentVo> response = new PageResponse<ResBMSReassignmentVo>();
		// 验参
		if (reqBMSReassignmentVo != null) {
			if (StringUtils.isEmpty(reqBMSReassignmentVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
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
				/*************************** 终审改派 **********************************/
				/**
				 * 构造数据响应
				 */
				List<String> saveZsPersonCodes = new ArrayList<String>();
				List<BMSReassigMentEntity> saveProxyGroupNames = new ArrayList<BMSReassigMentEntity>();
				PageParam pageParam = new PageParam(reqBMSReassignmentVo.getPageNum(),reqBMSReassignmentVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSReassignmentVo);
				if (StringUtils.isEmpty(reqBMSReassignmentVo.getHandleCode())) {// 如果处理人为空查询登录人下面的组员
					/** 调权限系统获取员工身份 **/
					ReqParamVO reqParamVO = new ReqParamVO();
					reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					reqParamVO.setLoginUser(reqBMSReassignmentVo.getLoginPersonCode());
					logger.info("调用权限API,查询当前登录人是否是经理及经理以上角色入参："+gson.toJson(reqParamVO));
					Response<Boolean> isAdmin=employeeExecuter.isManagerAbove(reqParamVO);
					if(isAdmin.getData()){
						logger.info("调用权限API,查询当前登录人是否是经理及经理以上角色返回值："+isAdmin.getData());
						paramMap.put("flag2", EnumConstants.MANAGER);
					}else{
						logger.info("调用权限API,查询当前登录人是否是经理及经理以上角色返回值："+isAdmin.getData());
						paramMap.put("flag2", EnumConstants.NOT_MANAGER);
					}
					ReqParamVO reqParamVOZ = new ReqParamVO();
					reqParamVOZ.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					reqParamVOZ.setLoginUser(reqBMSReassignmentVo.getLoginPersonCode());
					reqParamVOZ.setOrgTypeCode(OrganizationEnum.OrgCode.FINAL_CHECK.getCode());
					logger.info("调用权限API,查询当前当前登录人的所有下级员工CODE入参："+gson.toJson(reqParamVOZ));
					Response<List<ResEmpOrgVO>> powerResponse = employeeExecuter.findLowerByAccount(reqParamVOZ);
					logger.info("调用权限API,查询当前当前登录人的所有下级员工CODE返回值："+powerResponse.isSuccess());
					if (powerResponse.isSuccess()) {
						List<ResEmpOrgVO> empOrglist = powerResponse.getData();
						logger.info("调用权限API,查询当前当前登录人的所有下级员工CODE返回数据："+gson.toJson(empOrglist));
						for (ResEmpOrgVO resEmpOrgVO : empOrglist) {
							BMSReassigMentEntity reassigMentEntity = new BMSReassigMentEntity();
							saveZsPersonCodes.add(resEmpOrgVO.getUsercode());
							saveProxyGroupNames.add(reassigMentEntity);

						}
					}
				} else {
					List<String> handleCodes = new ArrayList<String>();
					if (reqBMSReassignmentVo.getHandleCode().indexOf(",") != -1) {
						String arr[] = reqBMSReassignmentVo.getHandleCode().split(",");
						for (String value : arr) {
							handleCodes.add(value);
						}
					} else {
						handleCodes.add(reqBMSReassignmentVo.getHandleCode());
					}
					
					paramMap.put("flag2", EnumConstants.NOT_MANAGER);
					paramMap.put("handleCodes", handleCodes);
				}
				saveZsPersonCodes.add(reqBMSReassignmentVo.getLoginPersonCode());
				paramMap.put("saveZsPersonCodes", saveZsPersonCodes);
				if(reqBMSReassignmentVo.getFpStatus()==null){
					paramMap.put("fpStatus", null);
				}
				if(reqBMSReassignmentVo.getCaseType()!=null){
					paramMap.put("caseType", dataAsc(reqBMSReassignmentVo.getCaseType()));
				}
				if(reqBMSReassignmentVo.getCaseIdentifyList()!=null && !reqBMSReassignmentVo.getCaseIdentifyList().isEmpty()){
					paramMap.put("caseIdentifyList", reqBMSReassignmentVo.getCaseIdentifyList());
				} else {
					paramMap.put("caseIdentifyList", null);
				}
				paramMap.put("sortValue", getSortValues1(reqBMSReassignmentVo));
				@SuppressWarnings({ "rawtypes" })
				PageBean pageBean = bMSResFinalAuditService.listPage(pageParam, paramMap, QUERY_ZS_REASSIGNMENT_SQL,COUNT_ZS_REASSIGNMENT_SQL);
				@SuppressWarnings("unchecked")
				List<BMSReassigMentEntity> reassignMents = pageBean.getRecords();
				List<ResBMSReassignmentVo> records = new ArrayList<ResBMSReassignmentVo>();
				// 调权限系统api,根据营业部ID查询名称和地区
				List<Long> orgIdList = new ArrayList<Long>();
				for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
					if (reassigMentEntity.getOwningBranchId() != null) {
						orgIdList.add(Long.parseLong(reassigMentEntity.getOwningBranchId()));
					}
				}
				ReqParamVO reqParamVO = new ReqParamVO();
				reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqParamVO.setOrgIds(orgIdList);
				logger.info("调用权限API,根据机构ID列表获取结构名和区域名入参:"+gson.toJson(reqParamVO));
				Response<List<ResOrgNameAreaVO>> byOrgIdResponse = OrganizationExecuter.findNameByIds(reqParamVO);
				logger.info("调用权限API,根据机构ID列表获取结构名和区域名返回值:"+byOrgIdResponse.isSuccess());
				if (byOrgIdResponse.isSuccess()) {
					List<ResOrgNameAreaVO> orgs = byOrgIdResponse.getData();
					logger.info("调用权限API,根据机构ID列表获取结构名和区域名返回数据:"+gson.toJson(orgs));
					for (ResOrgNameAreaVO resOrgNameAreaVO : orgs) {
						for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
							if (reassigMentEntity.getOwningBranchId() != null) {
								if (Long.parseLong(reassigMentEntity.getOwningBranchId()) == resOrgNameAreaVO.getOrgId()) {
									reassigMentEntity.setOwningBranchName(resOrgNameAreaVO.getOrgName());
									reassigMentEntity.setOwningBranchAttr(resOrgNameAreaVO.getDepLevel());
								}

							}

						}
					}
				}
				
				for (BMSReassigMentEntity reassigMentEntity : reassignMents) {
					//如果有协审取协审,否则取终审人员
					String values=EnumConstants.noAssignment.get(reassigMentEntity.getRtfNodeStatus());
					if(values==null){
						if(reassigMentEntity.getApprovalPersonCode()!=null){
							ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
							reqEmployees.setUsercode(reassigMentEntity.getApprovalPersonCode());
							ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);
							if(resEmps!=null){
								reassigMentEntity.setHandleCode(reassigMentEntity.getApprovalPersonCode());
								reassigMentEntity.setHandleName(resEmps.getName());
							}	
						}else{
							ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
							reqEmployees.setUsercode(reassigMentEntity.getFinalPersonCode());
							ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);
							if(resEmps!=null){
								reassigMentEntity.setHandleCode(reassigMentEntity.getFinalPersonCode());
								reassigMentEntity.setHandleName(resEmps.getName());
							}	
						}

					}else{
						reassigMentEntity.setHandleCode(null);
					}
					if(reassigMentEntity.getAppInputFlag()!=null){
						reassigMentEntity.setAppInputFlag("1");
					}else{
						reassigMentEntity.setAppInputFlag("0");
					}
					/**
					 * 获取初审人员所在组
					 */
					// 调权限系统api,根据员工ID查询所在小组
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(reassigMentEntity.getCheckPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);	
					ReqParamVO cSreqCsParamVO = new ReqParamVO();
					cSreqCsParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					cSreqCsParamVO.setUsercode(reassigMentEntity.getCheckPersonCode());
					logger.info("调权限API,查询初审人员所在小组入参:" + gson.toJson(reassigMentEntity.getCheckPersonCode()));
					Response<List<ResOrganizationVO>> orgResponse = OrganizationExecuter.findFuncByAccount(cSreqCsParamVO);
					logger.info("调权限API,查询初审人员所在小组返回值:" + orgResponse.isSuccess());
					if (orgResponse.isSuccess()) {
						logger.info("调权限API,查询初审人员所在小组返回数据:" +gson.toJson(orgResponse.getData()));
						for (ResOrganizationVO resEmpOrgVO : orgResponse.getData()) {
							if(resEmpOrgVO.getGroupCode().equals("check")){
								reassigMentEntity.setcSProxyGroupName(resEmpOrgVO.getName());
							}
						}
					}
					if(resEmps!=null){
						reassigMentEntity.setCheckPersonCode(resEmps.getName());
					}
					//String value=EnumConstants.noAssignment.get(reassigMentEntity.getRtfNodeStatus());
					if(values==null){
					//调用权限系统api，根据员工编号查询所在管理组
					ReqParamVO zSreqCsParamVO = new ReqParamVO();
					zSreqCsParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					if(reassigMentEntity.getApprovalPersonCode()!=null){
						zSreqCsParamVO.setLoginUser(reassigMentEntity.getApprovalPersonCode());	
					}else{
						zSreqCsParamVO.setLoginUser(reassigMentEntity.getFinalPersonCode());
					}
					zSreqCsParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.FINAL_CHECK.getCode());
					logger.info("调权限API,查询员工所在管理组入参:" + gson.toJson(reassigMentEntity.getFinalPersonCode()));
					Response<ResGroupVO> orgResponses = OrganizationExecuter.findGroupInfoByAccount(zSreqCsParamVO);
					logger.info("调权限API,查询员工所在管理组返回值:" +orgResponses.isSuccess());
					if (orgResponses.isSuccess()) {
						logger.info("调权限API,查询员工所在管理组返回结果:" +gson.toJson(orgResponses.getData()));
						if(orgResponses.getData().getGroupName()!=null && orgResponses.getData().getBigGroupName()!=null){
							reassigMentEntity.setProxyGroupName(orgResponses.getData().getBigGroupName()+orgResponses.getData().getGroupName()); 
						}else if(orgResponses.getData().getBigGroupName()!=null && orgResponses.getData().getGroupName()!=null){
							reassigMentEntity.setProxyGroupName(orgResponses.getData().getFuncName()+orgResponses.getData().getBigGroupName()); 
						}
					}
					}else{
						reassigMentEntity.setProxyGroupName(null);

					}
				}
				for (BMSReassigMentEntity bmsReassigMentEntity : reassignMents) {
					ResBMSReassignmentVo resBMSReassignmentVo = new ResBMSReassignmentVo();
					BeanUtils.copyProperties(bmsReassigMentEntity, resBMSReassignmentVo);
					records.add(resBMSReassignmentVo);
				}
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);


			} catch (Exception e) {
				logger.info("------请求审核改派异常，请求参数-------" + gson.toJson(reqBMSReassignmentVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqBMSReassignmentVo请求对象为空" + gson.toJson(reqBMSReassignmentVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSReassignmentVo" });
		}
		return response;

	} 

	/**
	 * 校验必传字段
	 * @param reqBMSAdultOffTheStocksVo
	 * @return
	 */
	public static  ResBMSFinalAduitUpdVO ChenckIsReturn(ReqZsUpdVO reqFinalVo){
		ResBMSFinalAduitUpdVO resBMSFinalVo=new ResBMSFinalAduitUpdVO();
		if (null!=reqFinalVo) {
			//系统编码
			if (StringUtils.isBlank(reqFinalVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resBMSFinalVo);
			}
			if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqFinalVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resBMSFinalVo);
			}
			//借款编号
			if (StringUtils.isEmpty(reqFinalVo.getLoanNo())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resBMSFinalVo);
			}
			//操作人员工号
			if (StringUtils.isEmpty(reqFinalVo.getOperatorCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resBMSFinalVo);
			}
			//操作人IP
			if (StringUtils.isEmpty(reqFinalVo.getOperatorIP())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resBMSFinalVo);
			} 
			if (!ValidataUtil.isIP(reqFinalVo.getOperatorIP())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resBMSFinalVo);
			}
		}else{
			logger.info("终审更新接口request请求对象为空" + gson.toJson(reqFinalVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqFinalVo" });
		}

		return null;
	}
	/***
	 * 根据前台传过来的数据，信息排序整理
	 */
	public static String getSortValues(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo){
		/*if(StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getFieldSort())){
			return " bla.CREATED_TIME ASC";
		}*/
		String sort = " desc";
		if(reqBMSAdultOffTheStocksVo.getRulesSort() == 1){
			sort = " asc";
		}
		String field = " bla.CREATED_TIME";
		if("personName".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field = "CONVERT(blb.NAME USING gbk)";
		}else if("productName".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="CONVERT(blp.PRODUCT_NAME USING gbk)";
		}else if("accLmt".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="bla.ACC_LMT";
		}else if("owningBrance".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.ENTER_BRANCH";
		}else if("submitAuditDate".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field = "bla.CREATED_TIME";
		}else if("applyType".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.APPLY_TYPE";
		}else if("loanNo".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.LOAN_NO";
		}else if("refNodeStatus".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.RTF_NODE_STATE";
		}
		return field + sort;
	}
	@Override
	public PageResponse<ResOffTheStocksAuditVO> zSAdultOffTheStocks(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo) {
		logger.info("----------------终审已完成任务开始请求------------");
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
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSAdultOffTheStocksVo);
				PageParam pageParam = new PageParam(reqBMSAdultOffTheStocksVo.getPageNum(), reqBMSAdultOffTheStocksVo.getPageSize());
				paramMap.put("over", 4);
				paramMap.put("personCode", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
				paramMap.put("flag", reqBMSAdultOffTheStocksVo.getReqFlag());
				List<BMSFirstAuditEntity> loanNos = bMSResFinalAuditService.queryzSBmsLogByLoan(paramMap);
				List<ResOffTheStocksAuditVO> records = new ArrayList<ResOffTheStocksAuditVO>();
				PageBean<BMSFirstAuditEntity> pageBean = null;
				if (loanNos != null && loanNos.size() > 0) {
					paramMap.put("loans", loanNos);
					paramMap.put("sortValue", getSortValues(reqBMSAdultOffTheStocksVo));
					pageBean = bMSResFinalAuditService.listPage(pageParam, paramMap,QUERY_ZS_ADULTOFFTHESTOCKS_SQL,COUNT_ZS_ADULTOFFTHESTOCKS_SQL);
				} else {
					return resAuditQueryInfo("000000","查询已完成数据列表为空", pageResponse);
				}
				List<BMSFirstAuditEntity> loanList = pageBean.getRecords();
				List<BMSFirstAuditEntity> saveIsHostioryLoans = new ArrayList<BMSFirstAuditEntity>();
				for (BMSFirstAuditEntity bmsFirstAuditEntity : loanList) {
					for (BMSFirstAuditEntity firstAuditEntity : loanNos) {
						if (bmsFirstAuditEntity.getLoanNo().equals(firstAuditEntity.getLoanNo())) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("personCode", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
							map.put("flag", reqBMSAdultOffTheStocksVo.getReqFlag());
							map.put("loanNo", firstAuditEntity.getLoanNo());
							List<BMSFirstAuditEntity> fianlAudit=bMSResFirstAuditService.queryFirstOperationTime(map);
							if(fianlAudit.size()>0){
								bmsFirstAuditEntity.setZsStartDate(fianlAudit.get(0).getOperationTime());	
							}
							if(bmsFirstAuditEntity.getAppInputFlag()!=null){
								bmsFirstAuditEntity.setAppInputFlag("1");
							}else{
								bmsFirstAuditEntity.setAppInputFlag("0");
							}
							bmsFirstAuditEntity.setHistorNodeStatus(firstAuditEntity.getRefNodeStatus());
							bmsFirstAuditEntity.setOperationTime(firstAuditEntity.getOperationTime());
							continue;
						}

					}
					saveIsHostioryLoans.add(bmsFirstAuditEntity);
				}
				if(StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getFieldSort())){
					SortClassUtil sort = new SortClassUtil();  
					Collections.sort(saveIsHostioryLoans,sort);  	
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
	
	/***
	 * 根据前台传过来的数据，信息排序整理
	 */
	public static String getSortValue(ReqAuditVo reqFirstAuditVo){
		if(StringUtils.isEmpty(reqFirstAuditVo.getFieldSort())){
			return " bla.CREATED_TIME ASC";
		}
		String sort = " desc";
		if(reqFirstAuditVo.getRulesSort() == 1){
			sort = " asc";
		}
		String field = " bla.CREATED_TIME";
		if("personName".equals(reqFirstAuditVo.getFieldSort())){
			field = "CONVERT(blb.NAME USING gbk)";
		}else if("productName".equals(reqFirstAuditVo.getFieldSort())){
			field="CONVERT(blp.PRODUCT_NAME USING gbk)";
		}else if("applyLmt".equals(reqFirstAuditVo.getFieldSort())){
			field="blp.APPLY_LMT";
		}else if("owningBrance".equals(reqFirstAuditVo.getFieldSort())){
			field="CONVERT(blb.ENTER_BRANCH USING gbk)";
		}else if("submitAuditDate".equals(reqFirstAuditVo.getFieldSort())){
			field = "bla.CREATED_TIME";
		}else if("applyType".equals(reqFirstAuditVo.getFieldSort())){
			field="blb.APPLY_TYPE";
		}else{
			field="bla.CREATED_TIME";
		}
		return field + sort;
	}
	@Override
	public PageResponse<ResBMSAuditVo> zSWaitForTheStocks(ReqAuditVo reqFirstAuditVo) {
		logger.info("----------------终审待办任务开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqFirstAuditVo));
		PageResponse<ResBMSAuditVo> response = new PageResponse<ResBMSAuditVo>();
		// 验证参数
		if (reqFirstAuditVo != null) {
			if (StringUtils.isEmpty(reqFirstAuditVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
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
				PageParam pageParam = new PageParam(reqFirstAuditVo.getPageNum(), reqFirstAuditVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqFirstAuditVo);
				paramMap.put("loginCode", reqFirstAuditVo.getServiceCode());	
				paramMap.put("sortValue", getSortValue(reqFirstAuditVo));
				//查询数据
				PageBean<BMSFirstAuditEntity> pageBean = bMSResFinalAuditService.listPage(pageParam, paramMap,QUERY_ZS_ZSWAITFORTHESTOCKS_SQL,COUNT_ZS_ZSWAITFORTHESTOCKS_SQL);
				List<Long> orgIdList = new ArrayList<Long>();
				if(pageBean!=null){
					//循环查询权限系统 工作小组
					for (int i = 0; i < pageBean.getRecords().size(); i++) {
						BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(i);
						if (bmsFirstAuditEntity.getOwningBranceId() != null) {
							orgIdList.add(Long.parseLong(bmsFirstAuditEntity.getOwningBranceId()));
						}
						String proxyGroupName = "";
						// 调权限系统api,根据员工ID查询所在小组
						ReqParamVO reqParamVO = new ReqParamVO();
						reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
						reqParamVO.setUsercode(bmsFirstAuditEntity.getCheckPersonCode());
						logger.info("调权限API,查询所在小组入参:" + gson.toJson(bmsFirstAuditEntity.getCheckPersonCode()));
						Response<List<ResOrganizationVO>> orgResponse = OrganizationExecuter.findFuncByAccount(reqParamVO);
						logger.info("调权限API,查询所在小组返回值:" +orgResponse.isSuccess());
						// 遍历响应集合，组装小组的名称
						if (orgResponse.isSuccess()) {
							logger.info("调权限API,查询所在小组返回数据:" +gson.toJson(orgResponse.getData()));
							for (int j = 0; j < orgResponse.getData().size(); j++) {
								if (orgResponse.getData().get(j).getName() != null) {
									proxyGroupName += orgResponse.getData().get(j).getName() + ",";
								}
							}
							if (proxyGroupName.indexOf(",") != -1) {
								proxyGroupName = proxyGroupName.substring(0, proxyGroupName.length() - 1);
								bmsFirstAuditEntity.setProxyGroupName(proxyGroupName);
							}
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
						ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
						reqEmployees.setUsercode(bmsFirstAuditEntity.getCheckPersonCode());
						ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);
						if(resEmps!=null){
							bmsFirstAuditEntity.setCheckPersonCode(resEmps.getName());	
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

		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqFirstAuditVo" });
		}
		return response;
	}
	/**
	 * 审核台查询响应对象
	 * 
	 * @param <T>
	 */
	public static ResBMSFinalAduitUpdVO resAuditQueryInfo(String errCode, String errMsg, ResBMSFinalAduitUpdVO response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}
	public static <T> Response<T> resAudiInfo2(String errCode, String errMsg, Response<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
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
	@Override
	public Response<Integer> findLastByStatus(ReqZsUpdVO reqFinalVo) {
		logger.info("接口开始----------------------------------"+reqFinalVo);
		Response<Integer> response=new Response<Integer>();
		if(null==reqFinalVo){
			response.setRepCode(BizErrorCode.BIZ_EOERR.getErrorCode());
			response.setRepMsg("ReqZsUpdVO"+BizErrorCode.BIZ_EOERR.getDefaultMessage());
			return response;
		}
		if(null==reqFinalVo.getzSPersonCode()||reqFinalVo.getzSPersonCode().length()==0){
			response.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
			response.setRepMsg("code"+BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
			return response;
		}
		Integer res=bMSResFinalAuditService.findLastByStatus(reqFinalVo.getzSPersonCode());
		response.setData(res);
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
		}else{
			req.setRoleCode(RoleEnum.FINAL_CHECK_MANAGER.getCode());
			Response<Boolean> resFlag2 = employeeExecuter.hasRole(req);
			if(resFlag2.getData()){//如果是admin
				return EnumConstants.YES;
			}	
		}
		return  EnumConstants.NO;
	}
	public String dataAsc(String DataType){
		if(DataType!=null){
			StringBuffer sb=new StringBuffer();
			String[] str=DataType.split(",");
			Arrays.sort(str);
			for(int i=0;i<str.length;i++){
				sb.append(str[i]); 
			}
			return sb.toString();
		}else{
			return null;	
		}
	}
	@Override
	public  Response<Object> xsSystemReject(ReqZsUpdVO reqZsUpdVO) {
		logger.info("----------------信审分派拒绝------------");
		logger.info("请求参数:" + gson.toJson(reqZsUpdVO));
		Response<Object> response = new Response<Object>();
		AXsSystemRejectEntity lastBmsProductEntity=new AXsSystemRejectEntity();
		// 校验必填项
		BeanUtils.copyProperties(reqZsUpdVO, lastBmsProductEntity);
		Response<Object> validate =  Validate.getInstance().validate(lastBmsProductEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		lastBmsProductEntity.checkValue();
		try {

			boolean flag = bMSResFinalAuditService.updateXsSystemReject(reqZsUpdVO);
			if (flag) {
				logger.info("响应成功！");
			}
		} catch (Exception e) {
			logger.info("------请求信审更新借款产品信息异常，请求参数-------" + gson.toJson(reqZsUpdVO));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	/**
	 * 查询该单子有没有做过初审退回和终审退回门店操作,取最新的一次,查询扩展表终审退回和初审退回快照信息
	 */
	@Override
	public Response<Object> xSInfoChangeCheck(ReqAuditVo reqAuditVo) {
		Response<Object> response=new Response<Object>();
		if(reqAuditVo.getIdNo()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"idNo"});	
		}
		if(reqAuditVo.getName()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(reqAuditVo.getLoanNo()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqAuditVo);
			BMSFirstAuditEntity firstEntity=bMSResFinalAuditService.findLogByReturn(paramMap);
			if(firstEntity!=null){
				AuditEntryVO backAuditApplyEntryVO = null;
				if(firstEntity.getRtfState().equals("XSCS")||firstEntity.getRtfState().equals("CSFP")){
					backAuditApplyEntryVO = gson.fromJson(firstEntity.getAuditBackSnapVersion(), AuditEntryVO.class);
				}else{
					backAuditApplyEntryVO=gson.fromJson(firstEntity.getFinalAuditBackSnapVersion(),AuditEntryVO.class);
				}
				String oldName=backAuditApplyEntryVO.getApplyInfoVO().getName();
				String oldIdNo=backAuditApplyEntryVO.getApplyInfoVO().getIdNo();
				if((!oldName.equals(reqAuditVo.getName()) || !oldIdNo.equals(reqAuditVo.getIdNo()))){
					response.setData(false);
				}else{
					response.setData(true);
				}
			}else {
				response.setData(true);
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage()+"------请求信审查询信息是否一致，请求参数-------" + gson.toJson(reqAuditVo));
		}
		return response;
	}
	
	/**
	 * 查询该单子有没有做过初审退回和终审退回门店操作,取最新的一次,查询扩展表终审退回和初审快照信息
	 */
	@Override
	public Response<String> findXsSnapVersionInfo(ReqAuditVo reqAuditVo) {
		Response<String> response=new Response<String>();
		if(StringUtils.isEmpty(reqAuditVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}	
		if(StringUtils.isEmpty(reqAuditVo.getFlag())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"flag"});
		}	
		String xsSnapVersionStr = null;
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqAuditVo);
			BMSFirstAuditEntity firstEntity=bMSResFinalAuditService.findXsSnapVersionInfo(paramMap);
			if("1".equals(reqAuditVo.getFlag())){
				if(firstEntity.getRefNodeStatus().equals(EnumConstants.RtfNodeState.XSCSPASS.getValue())|| firstEntity.getRefNodeStatus().equals(EnumConstants.RtfNodeState.HIGHPASS.getValue())){
					xsSnapVersionStr=firstEntity.getAuditSnapVersion();	
				}else{
					xsSnapVersionStr =firstEntity.getAuditBackSnapVersion();	
				}
			}else{
				xsSnapVersionStr =firstEntity.getFinalAuditBackSnapVersion();
			}
			response.setData(xsSnapVersionStr);
		} catch (Exception e) {
			logger.info("------请求信审查询信息是否一致，请求参数-------"+e.getLocalizedMessage()+ gson.toJson(reqAuditVo));
		}
		return response;
	}
}
