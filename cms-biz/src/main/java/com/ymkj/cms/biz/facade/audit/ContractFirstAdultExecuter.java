package com.ymkj.cms.biz.facade.audit;

import java.util.ArrayList;
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
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.bds.biz.api.enums.Constants;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.service.IGreyListExecuter;
import com.ymkj.bds.biz.api.service.IInternalMatchingExecuter;
import com.ymkj.bds.biz.api.vo.request.BlackGreyReqVO;
import com.ymkj.bds.biz.api.vo.request.IdentifyingAntiFraudReqVO;
import com.ymkj.bds.biz.api.vo.request.RemoveReqVo;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.IFirstAdultExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansAndVersionsVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqChcekVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsPlupdateStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchAttrVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSCheckVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.common.util.SortClassUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSCheckEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.audit.BMSFirstAuditService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.enums.OrganizationEnum;
import com.ymkj.pms.biz.api.enums.RoleEnum;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmpOrgVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrgNameAreaVO;


/**
 * 审核接口服务提供者 初审接口API实现
 * 
 * @author YM10161
 *
 */
@Service
public class ContractFirstAdultExecuter implements IFirstAdultExecuter {
	public Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private BMSFirstAuditService bMSResFirstAuditService;
	@Autowired
	private IBMSAppPersonInfoService BMSAppPersonInfoService;
	@Autowired
	private IEmployeeExecuter employeeExecuter;
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	@Autowired
	private IBlackListExecuter blackListExecuter;
	@Autowired
	private IGreyListExecuter greyListExecuter;
	@Autowired
	private IInternalMatchingExecuter iInternalMatchingExecuter;
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;
	
	@Autowired
	private PMSClientService pmsClientService;
	private final String QUERY_SQL = "queryCheckInfoByCode";
	private final String COUNT_SQL = "queryCheckInfoCount";
	public final String AUDIT_SQL_FLAG = "1";// 审核台查询SQL使用SQL标识
	
	
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
		}else if("accLmt".equals(reqFirstAuditVo.getFieldSort())){
			field="bla.ACC_LMT";
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
			field="CONVERT(blb.ENTER_BRANCH USING gbk)";
		}else if("submitAuditDate".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field = "bla.CREATED_TIME";
		}else if("applyType".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.APPLY_TYPE";
		}else if("loanNo".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.LOAN_NO";
		}else if("refNodeStatus".equals(reqBMSAdultOffTheStocksVo.getFieldSort())){
			field="blb.RTF_NODE_STATE";
		}/*else{
			field="bla.CREATED_TIME";
		}*/
		return field + sort;
	}
	/**
	 * 根据员工工号查询审核界面的Vo
	 */
	@Override
	public PageResponse<ResBMSAuditVo> listPage(ReqAuditVo reqFirstAuditVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqFirstAuditVo));
		PageResponse<ResBMSAuditVo> response = new PageResponse<ResBMSAuditVo>();
		// 验证参数
		if (reqFirstAuditVo != null) {
			if (StringUtils.isEmpty(reqFirstAuditVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqFirstAuditVo.getSysCode())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
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
				paramMap.put("flag2", AUDIT_SQL_FLAG);	
			    paramMap.put("checkPersonCd", reqFirstAuditVo.getServiceCode());
			    paramMap.put("sortValue", getSortValue(reqFirstAuditVo));
					// 调用业务逻辑
					pageBean = bMSResFirstAuditService.listPage(pageParam, paramMap);
					/*List<Long> orgIdList = new ArrayList<Long>();*/
					if (pageBean != null) {
						for (int i = 0; i < pageBean.getRecords().size(); i++) {
							BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(i);
							/*if (bmsFirstAuditEntity.getOwningBranceId() != null) {
								orgIdList.add(Long.parseLong(bmsFirstAuditEntity.getOwningBranceId()));
							}*/
							// 调行为库,获取是否疑似欺诈
							Map<String, Object>map =new HashMap<String,Object>();
							map.put("loanNo", bmsFirstAuditEntity.getLoanNo());
							BMSAppPersonInfo appPersonInfo = BMSAppPersonInfoService.byLoanNoQueryInfo(map);
							if(appPersonInfo!=null){
								bmsFirstAuditEntity.setIfSuspectCheat(ifSuspectCheat(appPersonInfo).toString());
							}
							if(bmsFirstAuditEntity.getAppInputFlag()!=null){
								bmsFirstAuditEntity.setAppInputFlag("1");
							}else{
								bmsFirstAuditEntity.setAppInputFlag("0");
							}
						}
					}
			/*		// 调权限系统api,根据营业部ID查询名称和地区
					ReqParamVO reqParamVO = new ReqParamVO();
					reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
					reqParamVO.setOrgIds(orgIdList);
					logger.info("调用权限API,根据营业部ID查询名称和地区入参:"+gson.toJson(reqParamVO));
					Response<List<ResOrgNameAreaVO>> byOrgIdResponse = OrganizationExecuter.findNameByIds(reqParamVO);
					// 塞数据将调接口返回的机构名称塞进去
					logger.info("调用权限API,根据营业部ID查询名称和地区返回值:"+byOrgIdResponse.isSuccess());
					if (byOrgIdResponse.isSuccess()) {
						logger.info("调用权限API,根据营业部ID查询名称和地区返回数据:"+gson.toJson(byOrgIdResponse.getData()));
						for (int i = 0; i <byOrgIdResponse.getData().size(); i++) {
							ResOrgNameAreaVO resOrgNameAreaVO = byOrgIdResponse.getData().get(i);
							for (int j = 0; j < pageBean.getRecords().size(); j++) {
								BMSFirstAuditEntity bmsFirstAuditEntity = pageBean.getRecords().get(j);
								if (bmsFirstAuditEntity.getOwningBranceId() != null) {
									if (resOrgNameAreaVO.getOrgId() == Long.parseLong(bmsFirstAuditEntity.getOwningBranceId())) {
										bmsFirstAuditEntity.setOwningBrance(resOrgNameAreaVO.getOrgName());
										bmsFirstAuditEntity.setOwningBranceAttribute(resOrgNameAreaVO.getDepLevel());// 营业部属性
									}
								}
							}
						}
					}*/

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
				logger.info("请求异常，请求参数:" + CoreErrorCode.SYSTEM_ERROR.getErrorCode()+"|"+e.getLocalizedMessage());
				response.setRepCode(CoreErrorCode.SYSTEM_ERROR.getErrorCode());
				response.setRepMsg(e.getLocalizedMessage());
			}
		} else {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqFirstAuditVo" });
		}
		return response;
	}

	/**
	 * 根据状态查询派单信息
	 */
	@Override
	public ResBMSAutomaticDispatchVO automaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqAutomaticDispatchVO));
		List<ResBMSAutomaticDispatchAttrVO> resAutomaticList = null;
		ResBMSAutomaticDispatchVO responseAutomaticDis = new ResBMSAutomaticDispatchVO();
		List<Long> orgIdList = new ArrayList<Long>();

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
			if (StringUtils.isEmpty(reqAutomaticDispatchVO.getFlag())) {
				responseAutomaticDis.setRepCode(BizErrorCode.BIZ_EOERR.getErrorCode());
				responseAutomaticDis.setRepMsg("flag" + BizErrorCode.BIZ_EOERR.getDefaultMessage());
				return responseAutomaticDis;
			}
			if (!ValidataUtil.isExtisFlag(reqAutomaticDispatchVO.getFlag())) {
				responseAutomaticDis.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
				responseAutomaticDis.setRepMsg("flag" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
				return responseAutomaticDis;
			}
			try {
				resAutomaticList = new ArrayList<ResBMSAutomaticDispatchAttrVO>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("flag", reqAutomaticDispatchVO.getFlag());
				// 查询所有入单记录
				List<BMSAutomaticDispatchEntity> automaticDList = bMSResFirstAuditService.automaticDispatchList(map);
				// 数据转换
				for (int i = 0; i < automaticDList.size(); i++) {
					ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchSearchListVO = new ResBMSAutomaticDispatchAttrVO();
					BMSAutomaticDispatchEntity bMSAutomaticDispatchEntity = automaticDList.get(i);
					if (bMSAutomaticDispatchEntity.getOwningBranchId() != null) {
						orgIdList.add(Long.parseLong(bMSAutomaticDispatchEntity.getOwningBranchId()));
					}
					BeanUtils.copyProperties(bMSAutomaticDispatchEntity, resBMSAutomaticDispatchSearchListVO);
					resAutomaticList.add(resBMSAutomaticDispatchSearchListVO);
				}
				// 调权限系统api,根据营业部ID查询名称和地区
				ReqParamVO reqParamVO = new ReqParamVO();
				reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
				reqParamVO.setOrgIds(orgIdList);
				Response<List<ResOrgNameAreaVO>> response = OrganizationExecuter.findNameByIds(reqParamVO);
				if (response.isSuccess()) {
					// 塞数据将调接口返回的机构名称和地区code塞进去
					for (int i = 0; i < resAutomaticList.size(); i++) {
						ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchAttrVO = resAutomaticList.get(i);
						for (int j = 0; j < response.getData().size(); j++) {
							ResOrgNameAreaVO resOrgNameAreaVO = response.getData().get(j);
							if (resBMSAutomaticDispatchAttrVO.getOwningBranchId() != null) {
								/*if (resOrgNameAreaVO.getOrgId() == Long
										.parseLong(resBMSAutomaticDispatchAttrVO.getOwningBranchId())) {
									resBMSAutomaticDispatchAttrVO.setSpecialOrgCode(resOrgNameAreaVO.getOrgCode());
								}*/
							}
						}
					}
				} else {
					logger.info("调权限系统失败或返回响应信息为空|请求参数------" + gson.toJson(reqParamVO));
				}
				responseAutomaticDis.setList(resAutomaticList);
			} catch (Exception e) {
				// 异常抛出堆栈信息
				logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqAutomaticDispatchVO" });
		}
		return responseAutomaticDis;
	}

	/**
	 * 初审查询派单信息
	 */
	@Override
	public ResBMSAutomaticDispatchVO csAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqAutomaticDispatchVO));
		List<ResBMSAutomaticDispatchAttrVO> resAutomaticList = null;
		ResBMSAutomaticDispatchVO responseAutomaticDis = new ResBMSAutomaticDispatchVO();
		List<Long> orgIdList = new ArrayList<Long>();
		//校验sysCode合法性
		if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqAutomaticDispatchVO.getSysCode())) {
			responseAutomaticDis.setRepCode(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode());
			responseAutomaticDis.setRepMsg("sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage());
			return responseAutomaticDis;
		}
		
		try {
			resAutomaticList = new ArrayList<ResBMSAutomaticDispatchAttrVO>();
			// 查询所有入单记录
			List<BMSAutomaticDispatchEntity> automaticDList = bMSResFirstAuditService.csAutomaticDispatchList(new HashMap<String, Object>());
			// 数据转换
			for (int i = 0; i < automaticDList.size(); i++) {
				ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchSearchListVO = new ResBMSAutomaticDispatchAttrVO();
				BMSAutomaticDispatchEntity bMSAutomaticDispatchEntity = automaticDList.get(i);
				if (bMSAutomaticDispatchEntity.getOwningBranchId() != null) {
					orgIdList.add(Long.parseLong(bMSAutomaticDispatchEntity.getOwningBranchId()));
				}
				BeanUtils.copyProperties(bMSAutomaticDispatchEntity, resBMSAutomaticDispatchSearchListVO);
				resAutomaticList.add(resBMSAutomaticDispatchSearchListVO);
			}
			// 调权限系统api,根据营业部ID查询名称和地区
			ReqParamVO reqParamVO = new ReqParamVO();
			reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
			reqParamVO.setOrgIds(orgIdList);

			Response<List<ResOrgNameAreaVO>> response = OrganizationExecuter.findNameByIds(reqParamVO);
			if (response.isSuccess() && response != null) {
				// 塞数据将调接口返回的机构名称和地区code塞进去
				for (int i = 0; i < resAutomaticList.size(); i++) {
					ResBMSAutomaticDispatchAttrVO resBMSAutomaticDispatchAttrVO = resAutomaticList.get(i);
					for (int j = 0; j < response.getData().size(); j++) {
						ResOrgNameAreaVO resOrgNameAreaVO = response.getData().get(j);
						if (resBMSAutomaticDispatchAttrVO.getOwningBranchId() != null) {
							/*if (resOrgNameAreaVO.getOrgId() == Long
									.parseLong(resBMSAutomaticDispatchAttrVO.getOwningBranchId())) {
								resBMSAutomaticDispatchAttrVO.setSpecialOrgCode(resOrgNameAreaVO.getOrgCode());
							}*/
						}
					}
				}
			} else {
				logger.info("调权限系统失败或返回响应信息为空|请求参数------" + gson.toJson(reqParamVO));
			}
			responseAutomaticDis.setList(resAutomaticList);
		} catch (Exception e) {
			// 异常抛出堆栈信息
			logger.info("请求异常，请求参数:" + gson.toJson(reqAutomaticDispatchVO));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return responseAutomaticDis;
	}
	
	
	/**
	 * 更新初审申请状态
	 */
	@Override
	public ResBMSAudiUpdVo updateCsLoanNoState(ReqCsUpdVO reqCsUpdVO) {
		logger.info("----------------初审更新开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqCsUpdVO));
		ResBMSAudiUpdVo resAudiUpdVo = new ResBMSAudiUpdVo();
		// 参数校验
		if (reqCsUpdVO != null) {
			if (StringUtils.isEmpty(reqCsUpdVO.getSysCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqCsUpdVO.getSysCode())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqCsUpdVO.getLoanNo())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqCsUpdVO.getOperatorCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqCsUpdVO.getOperatorIP())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			} else {
				if (!ValidataUtil.isIP(reqCsUpdVO.getOperatorIP())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqCsUpdVO.getRtfNodeStatus())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"rtfNodeStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}

			if (!ValidataUtil.isExtisRtfNodeStatus(EnumConstants.RtfState.XSCS.getValue(),
					reqCsUpdVO.getRtfNodeStatus())) {
				return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"rtfStatus or rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (!StringUtils.isEmpty(reqCsUpdVO.getCheckNodeStatus())) {
				if (!ValidataUtil.isCheckNodeStatus(reqCsUpdVO.getCheckNodeStatus())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"checkNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
				if (reqCsUpdVO.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())
						|| reqCsUpdVO.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKPASS.getValue())) {
					if (StringUtils.isEmpty(reqCsUpdVO.getComplexPersonCode())) {
						return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
								"complexPersonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
					}
				}
			}
		 if(reqCsUpdVO.getCheckNodeStatus().
					equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())||reqCsUpdVO.getRtfNodeStatus().
					equals(EnumConstants.RtfNodeState.XSCSREJECT.getValue())
					||reqCsUpdVO.getRtfNodeStatus().
					equals(EnumConstants.RtfNodeState.XSCSRETURN.getValue())){
				if (StringUtils.isEmpty(reqCsUpdVO.getFirstLevelReasonCode())) {
					return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"firstLevelReasonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
				if(StringUtils.isEmpty(reqCsUpdVO.getFirstLevelReasons())){
					return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"firstLevelReasons" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			 
		 }
			if (!StringUtils.isEmpty(reqCsUpdVO.getAccDate())) {
				if (!ValidataUtil.validataDate(reqCsUpdVO.getAccDate())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqCsUpdVO.getAllotDate())) {
				if (!ValidataUtil.validataDate(reqCsUpdVO.getAllotDate())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"allotDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}

			if (!StringUtils.isEmpty(reqCsUpdVO.getFirstSubZsDate())) {
				if (!ValidataUtil.validataDate(reqCsUpdVO.getFirstSubZsDate())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"firstSubZsDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqCsUpdVO.getAccLmt())) {
				if (!ValidataUtil.isNumeric(reqCsUpdVO.getAccLmt())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accLmt" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqCsUpdVO.getAccTime())) {
				if (!ValidataUtil.isNumeric(reqCsUpdVO.getAccTime())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accTime" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			try {
				// 调用更新的服务
				boolean flag = bMSResFirstAuditService.updateCsLoanNoState(reqCsUpdVO);
				if (flag) {
					if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSPASS.getValue())){
						applyDataManipulationService.auditDifferences(reqCsUpdVO.getLoanNo(), "2");
					}
					logger.info("----------------初审更新请求结束------------");
					return resAudiUpdVo;
				}
			} catch (Exception e) {
				logger.info("数据库操作失败回滚" + gson.toJson(reqCsUpdVO));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("初审reqAudiUpdVO请求对象为空" + gson.toJson(reqCsUpdVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqCsUpdVO" });
		}
		return resAudiUpdVo;
	}

	/**
	 * 更新终审申请状态
	 */
	@Override
	public ResBMSAudiUpdVo updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO) {
		logger.info("----------------终审更新开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqZsUpdVO));
		ResBMSAudiUpdVo resAudiUpdVo = new ResBMSAudiUpdVo();
		// 参数校验
		if (reqZsUpdVO != null) {
			if (StringUtils.isEmpty(reqZsUpdVO.getSysCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqZsUpdVO.getSysCode())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqZsUpdVO.getLoanNo())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqZsUpdVO.getOperatorCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqZsUpdVO.getOperatorIP())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			} else {
				if (!ValidataUtil.isIP(reqZsUpdVO.getOperatorIP())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqZsUpdVO.getRtfNodeStatus())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"rtfNodeStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
			}

			if (StringUtils.isEmpty(reqZsUpdVO.getApppovalPersonCode())) {
				if (StringUtils.isEmpty(reqZsUpdVO.getzSPersonCode())) {
					return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"zSPersonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!ValidataUtil.isExtisRtfNodeStatus(EnumConstants.RtfState.XSZS.getValue(),
					reqZsUpdVO.getRtfNodeStatus())) {
				return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"rtfStatus or rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
			}
			if (reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())) {
				if (StringUtils.isEmpty(reqZsUpdVO.getFirstLevelReasonCode())) {
					return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"firstLevelReasonCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
				if (StringUtils.isEmpty(reqZsUpdVO.getFirstLevelReasons())) {
					return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"firstLevelReasons" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqZsUpdVO.getAccDate())) {
				if (!ValidataUtil.validataDate(reqZsUpdVO.getAccDate())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqZsUpdVO.getAllotDate())) {
				if (!ValidataUtil.validataDate(reqZsUpdVO.getAllotDate())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"allotDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqZsUpdVO.getAccLmt())) {
				if (!ValidataUtil.isNumeric(reqZsUpdVO.getAccLmt())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accLmt" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (!StringUtils.isEmpty(reqZsUpdVO.getAccTime())) {
				if (!ValidataUtil.isNumeric(reqZsUpdVO.getAccTime())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accTime" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resAudiUpdVo);
				}
			}
			if (reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())) {
				reqZsUpdVO.setIfNewLoanNo(EnumConstants.ifNewLoanNo.NOLOANNO.getValue());
			}
			try {
				// 调用更新的服务
				boolean flag = bMSResFirstAuditService.updateZsLoanNoState(reqZsUpdVO);
				logger.info("----------------终审更新请求结束------------");
				if (flag) {
					logger.info("返回结果:" + gson.toJson(flag));
				}
			} catch (Exception e) {
				logger.info("数据库操作失败回滚" + gson.toJson(reqZsUpdVO));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("终审reqZsUpdVO请求对象为空" + gson.toJson(reqZsUpdVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqZsUpdVO" });
		}
		return resAudiUpdVo;
	}
	
	/***
	 * 根据前台传过来的数据，信息排序整理
	 */
	public static String getSortValue1(ReqChcekVO reqChcekVO){
		if(StringUtils.isEmpty(reqChcekVO.getFieldSort())){
			return " bla.CREATED_TIME asc";
		}
		String sort = " desc";
		if(reqChcekVO.getRulesSort() == 1){
			sort = " asc";
		}
		String field = " bla.CREATED_TIME";
		if("personName".equals(reqChcekVO.getFieldSort())){
			field = "CONVERT(blb.NAME USING gbk)";
		}else if("productName".equals(reqChcekVO.getFieldSort())){
			field="CONVERT(blp.PRODUCT_NAME USING gbk)";
		}else if("applyType".equals(reqChcekVO.getFieldSort())){
			field="blb.APPLY_TYPE";
		}else if("rtfNodeStatus".equals(reqChcekVO.getFieldSort())){
			field="blb.RTF_NODE_STATE";
		}else if("accDate".equals(reqChcekVO.getFieldSort())){
			field = "bla.FIRST_SUB_ZS_DATE";
		}else if("primaryReason".equals(reqChcekVO.getFieldSort())){
			field="CONVERT(ble.PRIMARY_REASON USING gbk)";
		}else{
			field="bla.CREATED_TIME";
		}
		return field + sort;
	}
	
	
	/**
	 * 查询复核件信息
	 */
	@Override
	public PageResponse<ResBMSCheckVO> queryCheckInfoByCode(ReqChcekVO reqChcekVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqChcekVO));
		PageResponse<ResBMSCheckVO> response = new PageResponse<ResBMSCheckVO>();
		// 参数校验
		if (reqChcekVO != null) {
			if (StringUtils.isEmpty(reqChcekVO.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqChcekVO.getSysCode())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (StringUtils.isEmpty(reqChcekVO.getLoginUserCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loginUserCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (reqChcekVO.getPageNum() == 0 || reqChcekVO.getPageSize() == 0) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"pageNum|pageSize" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			try {
				List<String> checkPersonCds = new ArrayList<String>();
				List<BMSCheckEntity> proxyGroups = new ArrayList<BMSCheckEntity>();
				PageParam pageParam = new PageParam(reqChcekVO.getPageNum(), reqChcekVO.getPageSize());
				Map<String, Object> parmMap = new HashMap<String, Object>();
				/** 调权限系统获取员工身份 **/
				ReqParamVO reqParamVO = new ReqParamVO();
				reqParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqParamVO.setLoginUser(reqChcekVO.getLoginUserCode());
				reqParamVO.setRoleCode(RoleEnum.SUPER_ADMIN.getCode());
				Response<Boolean> isAdmin = employeeExecuter.hasRole(reqParamVO);
				if (isAdmin.getData()) {// 如果是初审组经理或admin
					parmMap.put("flag", EnumConstants.MANAGER);
				} else {
					checkPersonCds.add(reqChcekVO.getLoginUserCode());
					parmMap.put("checkPersonCdList", checkPersonCds);
					
					//查看当前用户是否是初审经理，只允许组长查看
					reqParamVO.setRoleCode(RoleEnum.CHECK_GROUP_LEADER.getCode());
					Response<Boolean> resFlag = employeeExecuter.hasRole(reqParamVO);
					if(!resFlag.getData()){
						return response;
					}
				}
				
				// 请求平台接口根据code查询下面的组员
				ReqParamVO vo = new ReqParamVO();
				vo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				vo.setLoginUser(reqChcekVO.getLoginUserCode());
				vo.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
				logger.info("调权限系统接口" + gson.toJson(reqChcekVO.getLoginUserCode()));
				Response<List<ResEmpOrgVO>> resPlatForm = employeeExecuter.findLowerByAccount(vo);
				logger.info("调权限系统接口结束" + gson.toJson(resPlatForm));
				if (resPlatForm.isSuccess()) {
					List<ResEmpOrgVO> list = resPlatForm.getData();
					for (int i = 0; i < list.size(); i++) {
						BMSCheckEntity checkEntity = new BMSCheckEntity();
						checkPersonCds.add(list.get(i).getUsercode());
						checkEntity.setProxyGroup(list.get(i).getOrgName());
						checkEntity.setComplexPersonCode(list.get(i).getUsercode());
						proxyGroups.add(checkEntity);
					}
				}
				parmMap.put("sortValue", getSortValue1(reqChcekVO));
				if (checkPersonCds == null || checkPersonCds.size() == 0) {
					return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"复核人员checkPersonCds集合" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
				}
				@SuppressWarnings("rawtypes")
				PageBean pageBean = bMSResFirstAuditService.listPage(pageParam, parmMap, QUERY_SQL, COUNT_SQL);
				@SuppressWarnings("unchecked")
				List<BMSCheckEntity> CheckList = pageBean.getRecords();
				// 组装数据将代理小组名称塞进去
				for (int i = 0; i < CheckList.size(); i++) {
					BMSCheckEntity checkEntity = CheckList.get(i);
					for (int j = 0; j < proxyGroups.size(); j++) {
						BMSCheckEntity proxyGroupCheck = proxyGroups.get(j);
						if (checkEntity.getComplexPersonCode() != null) {
							if (checkEntity.getComplexPersonCode().equals(proxyGroupCheck.getComplexPersonCode())) {
								checkEntity.setProxyGroup(proxyGroupCheck.getProxyGroup());
							}
						}
					}
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(checkEntity.getCheckPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);
					if(resEmps!=null){
						checkEntity.setCheckPersonName(resEmps.getName());
					}	
					checkEntity.setPrimaryReason(checkEntity.getPrimaryReason());
					checkEntity.setSecodeReason(checkEntity.getSecodeReason());
				}
				// 遍历响应集合，组装小组的名称
				List<ResBMSCheckVO> records = new ArrayList<ResBMSCheckVO>();
				for (BMSCheckEntity checkEntity : CheckList) {
					ResBMSCheckVO resBMSCheckVO = new ResBMSCheckVO();
					BeanUtils.copyProperties(checkEntity, resBMSCheckVO);
					records.add(resBMSCheckVO);
				}
				response = new PageResponse<ResBMSCheckVO>();
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);
				logger.info("----------------请求结束------------");
			} catch (Exception e) {
				logger.info("数据库操作失败" + gson.toJson(reqChcekVO));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqChcekVO请求对象为空" + gson.toJson(reqChcekVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqChcekVO" });
		}
		return response;
	}

	

	/**
	 * 批量初、终审改派更新接口
	 */
	@SuppressWarnings("unchecked")
	public Response<ResBMSPlReassignMentUpdVo> reassignmentUpd(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSReassignmentUpdVo));
		Response<ResBMSPlReassignMentUpdVo> response = new Response<ResBMSPlReassignMentUpdVo>();
		ResBMSPlReassignMentUpdVo resBMSPlReassignMentUpdVo = null;
		if (reqBMSReassignmentUpdVo != null) {
			if (StringUtils.isEmpty(reqBMSReassignmentUpdVo.getReqFlag())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"reqFlag" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!ValidataUtil.isExtisFlag(reqBMSReassignmentUpdVo.getReqFlag())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"reqFlag" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}

			if (StringUtils.isEmpty(reqBMSReassignmentUpdVo.getSysCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqBMSReassignmentUpdVo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (StringUtils.isEmpty(reqBMSReassignmentUpdVo.getAuditPersonCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"auditPersonCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			}
			if (reqBMSReassignmentUpdVo.getList() == null || reqBMSReassignmentUpdVo.getList().size() == 0) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"LoanNos" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			for (int i = 0; i < reqBMSReassignmentUpdVo.getList().size(); i++) {
				ReqBMSLoansAndVersionsVO reqBMSLoansAndVersionsVO = reqBMSReassignmentUpdVo.getList().get(i);
				if (StringUtils.isEmpty(reqBMSLoansAndVersionsVO.getRtfNodeStatus())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
				if (reqBMSReassignmentUpdVo.getReqFlag().equals(EnumConstants.ReqFlag.CS.getValue())) {
					if (!ValidataUtil.isExtisCsRtfNodeStatus(reqBMSLoansAndVersionsVO.getRtfNodeStatus())) {
						return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
								"rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
					}
				} else {
					if (!ValidataUtil.isExtisZsRtfNodeStatus(reqBMSLoansAndVersionsVO.getRtfNodeStatus())
							&& !ValidataUtil.isExtisCsRtfNodeStatus(reqBMSLoansAndVersionsVO.getRtfNodeStatus())) {
						return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
								"rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
					}
				}
			}
			if (StringUtils.isEmpty(reqBMSReassignmentUpdVo.getOperatorCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqBMSReassignmentUpdVo.getOperatorIP())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!ValidataUtil.isIP(reqBMSReassignmentUpdVo.getOperatorIP())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			try {
				Map<String, Object> resMap = bMSResFirstAuditService.updateReassignment(reqBMSReassignmentUpdVo);
				logger.info("响应成功！");
				resBMSPlReassignMentUpdVo = new ResBMSPlReassignMentUpdVo();
				resBMSPlReassignMentUpdVo.setCheckList((List<Boolean>) resMap.get("checkList"));
				response.setData(resBMSPlReassignMentUpdVo);
			} catch (Exception e) {
				logger.info("------请求更新审核改派异常，请求参数-------" + gson.toJson(reqBMSReassignmentUpdVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqBMSReassignmentVo请求对象为空" + gson.toJson(reqBMSReassignmentUpdVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSReassignmentUpdVo" });
		}
		return response;
	}

	/**
	 * 更新借款产品信息
	 */
	@Override
	public Response<Object> xsProductUpd(ReqBMProductUpdVo reqBMProductUpdVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMProductUpdVo));
		Response<Object> response = new Response<Object>();
		if (reqBMProductUpdVo != null) {
			if (StringUtils.isEmpty(reqBMProductUpdVo.getSysCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqBMProductUpdVo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}

			if (StringUtils.isEmpty(reqBMProductUpdVo.getReqFlag())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"reqFlag" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!ValidataUtil.isExtisFlag(reqBMProductUpdVo.getReqFlag())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"reqFlag" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (StringUtils.isEmpty(reqBMProductUpdVo.getLoanNo())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqBMProductUpdVo.getProductCd())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"productCd" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqBMProductUpdVo.getOperatorCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqBMProductUpdVo.getOperatorIP())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!ValidataUtil.isIP(reqBMProductUpdVo.getOperatorIP())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (reqBMProductUpdVo.getReqFlag().equals(EnumConstants.ReqFlag.CS.getValue())) {
				if (StringUtils.isEmpty(reqBMProductUpdVo.getIfCreditRecode())) {
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"ifCreditRecode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
				}
			}

			if (StringUtils.isEmpty(reqBMProductUpdVo.getAccLmt())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"accLmt" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (!ValidataUtil.isNumeric(reqBMProductUpdVo.getAccLmt())) {
				return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"accLmt" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			}
			if(reqBMProductUpdVo.getAccLmt().length()>11){
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"accLmt" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			try {
				/**
				 * 调用更新借款产品信息接口
				 */
				boolean flag = bMSResFirstAuditService.updateProductInfo(reqBMProductUpdVo);
				if (flag) {
					logger.info("响应成功！");
				}
			} catch (Exception e) {
				logger.info("------请求信审更新借款产品信息异常，请求参数-------" + gson.toJson(reqBMProductUpdVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqBMProductUpdVo请求对象为空" + gson.toJson(reqBMProductUpdVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMProductUpdVo" });
		}
		return response;
	}

	/**
	 * 审核已完成任务查询接口
	 */
	@Override
	public PageResponse<ResOffTheStocksAuditVO> adultOffTheStocks(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqBMSAdultOffTheStocksVo));
		PageResponse<ResOffTheStocksAuditVO> pageResponse = new PageResponse<ResOffTheStocksAuditVO>();
		if (reqBMSAdultOffTheStocksVo != null) {
			if (StringUtils.isBlank(reqBMSAdultOffTheStocksVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqBMSAdultOffTheStocksVo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), pageResponse);
			}
			if (StringUtils.isEmpty(reqBMSAdultOffTheStocksVo.getReqFlag())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"reqFlag" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), pageResponse);
			}
			if (!ValidataUtil.isExtisFlag(reqBMSAdultOffTheStocksVo.getReqFlag())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"reqFlag" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), pageResponse);
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
				if (reqBMSAdultOffTheStocksVo.getReqFlag().equals(EnumConstants.ReqFlag.CS.getValue())) {
					/** -------------初审已完成任务------------- **/
					// 先去权限系统查询改员工的身份
					PageParam pageParam = new PageParam(reqBMSAdultOffTheStocksVo.getPageNum(),reqBMSAdultOffTheStocksVo.getPageSize());
					Map<String, Object> paramMap = BeanKit.bean2map(reqBMSAdultOffTheStocksVo);
					paramMap.put("checkPersonCd", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
					paramMap.put("flag", reqBMSAdultOffTheStocksVo.getReqFlag());
					List<BMSFirstAuditEntity> loanNos = bMSResFirstAuditService.queryBmsLogByLoan(paramMap);
					PageBean<BMSFirstAuditEntity> pageBean = null;
					List<ResOffTheStocksAuditVO> records = new ArrayList<ResOffTheStocksAuditVO>();
					if (loanNos != null && loanNos.size() > 0) {
						paramMap.put("loans", loanNos);
						paramMap.put("sortValue", getSortValues(reqBMSAdultOffTheStocksVo));
						pageBean = bMSResFirstAuditService.listPage(pageParam, paramMap);
					} else {	
						return resAuditQueryInfo("000000","查询出来的数据集合为空", pageResponse);
					}
					List<BMSFirstAuditEntity> loanList = pageBean.getRecords();
					List<BMSFirstAuditEntity> saveIsHostioryLoans = new ArrayList<BMSFirstAuditEntity>();
				
					for (BMSFirstAuditEntity bmsFirstAuditEntity : loanList) {
							for (BMSFirstAuditEntity firstAuditEntity : loanNos) {
								if (bmsFirstAuditEntity.getLoanNo().equals(firstAuditEntity.getLoanNo())) {	
									Map<String, Object> maps = new HashMap<String, Object>();
									maps.put("checkPersonCd", reqBMSAdultOffTheStocksVo.getAuditPersonCode());
									maps.put("loanNo", firstAuditEntity.getLoanNo());
									List<BMSFirstAuditEntity> firstlAudit=bMSResFirstAuditService.querycSFirstOperationTime(maps);
									if(firstlAudit.size()>0){
										bmsFirstAuditEntity.setCsStartDate(firstlAudit.get(0).getOperationTime());	
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
				}
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
	 * 初审改派批量更新流程节点状态
	 */
	@Override
	public Response<ResBMSPlReassignMentUpdVo> reassignmentPlUpdStatus(ReqCsPlupdateStatusVo reqCsPlupdateStatusVo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqCsPlupdateStatusVo));
		Response<ResBMSPlReassignMentUpdVo> response = new Response<ResBMSPlReassignMentUpdVo>();
		ResBMSPlReassignMentUpdVo resBMSPlReassignMentUpdVo = null;
		if (reqCsPlupdateStatusVo != null) {
			if (StringUtils.isEmpty(reqCsPlupdateStatusVo.getSysCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (!reqCsPlupdateStatusVo.getSysCode().equals(EnumConstants.AMS_SYSTEM_CODE)) {
				return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			}
			if (reqCsPlupdateStatusVo.getList().size() == 0) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"list" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			for (ReqCsUpdStatusVo reqCsUpdStatusVo : reqCsPlupdateStatusVo.getList()) {
				if (!ValidataUtil.isExtisCsRtfNodeStatus(reqCsUpdStatusVo.getRtfNodeStatus())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"rtfNodeStatus" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			if (StringUtils.isEmpty(reqCsPlupdateStatusVo.getOperatorIP())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			if (!ValidataUtil.isIP(reqCsPlupdateStatusVo.getOperatorIP())) {
				return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
			}
			if (StringUtils.isEmpty(reqCsPlupdateStatusVo.getOperatorCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			List<ReqCsUpdStatusVo> saveXingWeiFailLoans = new ArrayList<ReqCsUpdStatusVo>();
			try {
					List<ReqCsUpdStatusVo> loans = reqCsPlupdateStatusVo.getList();
					List<ReqCsUpdStatusVo> saveXingWeiSusLoans = new ArrayList<ReqCsUpdStatusVo>();
					List<String> fails = new ArrayList<String>();
					List<String> sus = new ArrayList<String>();
					for (ReqCsUpdStatusVo reqCsUpdStatusVo : loans) {
						Map<String, Object> paramMap = BeanKit.bean2map(reqCsUpdStatusVo);
						BMSAppPersonInfo appPersonInfo = BMSAppPersonInfoService.byLoanNoQueryInfo(paramMap);
						ReqCsUpdStatusVo reqVo = new ReqCsUpdStatusVo();
						BlackGreyReqVO black = new BlackGreyReqVO();
						black.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
						black.setListSrc(Constants.SourceStatus._00002.getCode());
						black.setIdNo(appPersonInfo.getIdNo());
						black.setName(appPersonInfo.getName());
						paramMap.put("code", reqCsPlupdateStatusVo.getFirstLevelReasonCode());
						List<BMSTMReasonEntity> oneReason = iBMSTMReasonService.listBy(paramMap);
						paramMap.put("code", reqCsPlupdateStatusVo.getTwoLevelReasonCode());
						List<BMSTMReasonEntity> twoReason = iBMSTMReasonService.listBy(paramMap);
						BMSTMReasonEntity oneRejectReason = new BMSTMReasonEntity();
						BMSTMReasonEntity twoRejectReason = new BMSTMReasonEntity();
						if (oneReason != null && oneReason.size() > 0) {
							oneRejectReason = oneReason.get(0);
						}
						if (twoReason != null && twoReason.size() > 0) {
							twoRejectReason = twoReason.get(0);
						}
					if(reqCsPlupdateStatusVo.getOperatorFlag()
							.equals(EnumConstants.RtfNodeState.CSFPREJECT.getValue())){
						if (oneRejectReason.getIsBlackList() != 0) {
							if (twoRejectReason != null && twoRejectReason.getIsBlackList() != 1) {
								// TODO 单位电话 手机号
								String first = oneRejectReason.getReason();
								String second = twoRejectReason.getReason();
								black.setBlackReason(first != null ? first + "," : null + second != null ? second : null);
								black.setGreyReason(second != null ? second : null);
								black.setCreateUser(reqCsPlupdateStatusVo.getOperatorCode());
								Response<Object> xingWeiResponse = blackListExecuter.saveBlackGreyList(black);
								if (xingWeiResponse.isSuccess()) {
									reqCsUpdStatusVo.setBlackCode(xingWeiResponse.getData().toString());
									BeanUtils.copyProperties(reqCsUpdStatusVo, reqVo);
									saveXingWeiSusLoans.add(reqVo);
								} else {
									reqCsUpdStatusVo.setBlackCode(xingWeiResponse.getData().toString());
									saveXingWeiFailLoans.add(reqCsUpdStatusVo);
								}
								reqCsPlupdateStatusVo.setList(saveXingWeiSusLoans);
								Map<String, Object> resMap = bMSResFirstAuditService.reassignmentPlUpdStatus(reqCsPlupdateStatusVo,reqCsUpdStatusVo);
								resBMSPlReassignMentUpdVo = new ResBMSPlReassignMentUpdVo();
				
								for (ReqCsUpdStatusVo reqCsUpdStatusVo2 : saveXingWeiFailLoans) {
									fails.add(reqCsUpdStatusVo2.getLoanNo());
									RemoveReqVo bdsReq = new RemoveReqVo();
									bdsReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
									bdsReq.setRemoveId(reqCsUpdStatusVo2.getBlackCode());
									Response<Object> bdsResponse = greyListExecuter.removeGreyList(bdsReq);
									if (bdsResponse.isSuccess()) {
										logger.info("-------删除行为库灰名单成功----------");
									} else {
										logger.info("-------删除行为库灰名单失败----------");
										throw new BizException(CoreErrorCode.SYSTEM_ERROR);
									}
								}
								fails.add((String) resMap.get("fail"));
								sus.add((String) resMap.get("sus"));
								
							}
						}else{
							Map<String, Object> resMap = bMSResFirstAuditService.reassignmentPlUpdStatus(reqCsPlupdateStatusVo,reqCsUpdStatusVo);
							fails.add((String) resMap.get("fail"));
							sus.add((String) resMap.get("sus"));
						}
					}else{
							Map<String, Object> resMap = bMSResFirstAuditService.reassignmentPlUpdStatus(reqCsPlupdateStatusVo,reqCsUpdStatusVo);
							fails.add((String) resMap.get("fail"));
							sus.add((String) resMap.get("sus"));
						}
						
					}
					resBMSPlReassignMentUpdVo = new ResBMSPlReassignMentUpdVo();
					resBMSPlReassignMentUpdVo.setFailList(fails);
					resBMSPlReassignMentUpdVo.setSuccessList(sus);
					response.setData(resBMSPlReassignMentUpdVo);
			} catch (Exception e) {

				logger.info("------初审批量更新状态接口异常，请求参数-------" + gson.toJson(reqCsPlupdateStatusVo));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			logger.info("reqCsPlupdateStatusVo请求对象为空" + gson.toJson(reqCsPlupdateStatusVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqCsPlupdateStatusVo" });
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
	 * 审核公共响应修改对象 ResBMSAudiUpdVo
	 * 
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static ResBMSAudiUpdVo resAudiInfo(String errCode, String errMsg, ResBMSAudiUpdVo resBMSAudiUpdVo) {
		resBMSAudiUpdVo.setRepCode(errCode);
		resBMSAudiUpdVo.setRepMsg(errMsg);
		return resBMSAudiUpdVo;
	}

	/**
	 * 审核公共响应修改对象 ResBMSAudiUpdVo
	 * 
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static <T> Response<T> resAudiInfo2(String errCode, String errMsg, Response<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}

	/**
	 * 查询当前用户是否疑似欺诈
	 * 
	 * @return
	 */
	public Integer ifSuspectCheat(BMSAppPersonInfo personInfo) {
		Response<String> identifyingAntiFraud = new Response<String>();

		try {
			String cellPhone = personInfo.getCellphone();
			if (personInfo.getCellphoneSec() != null && !personInfo.getCellphoneSec().equals("")) {
				cellPhone += "," + personInfo.getCellphoneSec();
			}

			String corpPhone = personInfo.getCorpPhone();
			if (personInfo.getCorpPhoneSec() != null && !personInfo.getCorpPhoneSec().equals("")) {
				corpPhone += "," + personInfo.getCorpPhoneSec();
			}
			IdentifyingAntiFraudReqVO identifyingAntiFraudReq = new IdentifyingAntiFraudReqVO();
			identifyingAntiFraudReq.setName(personInfo.getName());// 姓名
			identifyingAntiFraudReq.setIdNo(personInfo.getIdNo());// 证件号
			identifyingAntiFraudReq.setCellPhone(cellPhone);// 手机号码
			identifyingAntiFraudReq.setCorpName(personInfo.getCorpName());// 公司名称
			identifyingAntiFraudReq.setCorpPhone(corpPhone);// 公司电话
			identifyingAntiFraudReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			BmsLogger.info("调用行为库入参：" + gson.toJson(identifyingAntiFraudReq));
			identifyingAntiFraud = iInternalMatchingExecuter.identifyingAntiFraud(identifyingAntiFraudReq);
			BmsLogger.info("调用行为库出参：" + gson.toJson(identifyingAntiFraud));
			if (identifyingAntiFraud.getData().equals("1")) {
				BmsLogger.info("返回结果，用户[" +personInfo.getName() + "][" + personInfo.getIdNo() + "]疑似欺诈");
				return ParametersType.ifSuspectCheat._1;
			}
		} catch (Exception e) {
			BmsLogger.info("[" + personInfo.getName() + "][" + personInfo.getIdNo() + "]查询联欺诈信息失败，失败原因：" + e.getMessage() + ",接口返回原因:" + identifyingAntiFraud.getRepMsg());
			return ParametersType.ifSuspectCheat._0;
		}
		return ParametersType.ifSuspectCheat._0;
	}
}
