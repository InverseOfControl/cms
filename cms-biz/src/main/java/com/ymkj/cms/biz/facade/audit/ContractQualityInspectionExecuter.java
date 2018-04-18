package com.ymkj.cms.biz.facade.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.IQualityInspectionExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionTwoVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSApplicationPartVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSQualityInspectionAttrVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSQualityInspectionVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.BMSQualityInspectionEntity;
import com.ymkj.cms.biz.entity.audit.first.ApplicationPartEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

/**
 * 申请件接口服务提供者 申请件接口服务API
 * 
 * @author YM10161
 *
 */
@Service
public class ContractQualityInspectionExecuter implements IQualityInspectionExecuter {
	public Logger logger = LoggerFactory.getLogger(ContractQualityInspectionExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private BMSQualityInspectionService bMSQualityInspectionService;
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private PMSClientService pmsClientService;
	@Autowired
	private LoanBaseService loanBaseService;
	/**
	 * 申请件接口1具体业务实现方法 具体业务逻辑的实现
	 */
	@Override
	public ResBMSQualityInspectionVO queryQualityInsInfo(ReqQualityInspectionVO reqQualityInspectionVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqQualityInspectionVO));
		ResBMSQualityInspectionVO resBMSQualityInspectionVO = new ResBMSQualityInspectionVO();
		ResBMSQualityInspectionAttrVO ResBMSQISearchListVO = null;
		// 验证参数
		if (reqQualityInspectionVO != null) {
			if (StringUtils.isEmpty(reqQualityInspectionVO.getStatus())) {
				return resTheSignInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"status" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
			}else{
				if(!ValidataUtil.isExtisFlag(reqQualityInspectionVO.getStatus())){
					return resTheSignInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"status" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
				}
			}
			if (StringUtils.isEmpty(reqQualityInspectionVO.getStartDate())) {
				return resTheSignInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"startDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
			}else{
				if(!ValidataUtil.validataDate(reqQualityInspectionVO.getStartDate())){
					return resTheSignInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"startDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
				}
			}
			if (StringUtils.isEmpty(reqQualityInspectionVO.getEndDate())) {
				return resTheSignInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"endDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
			}else{
				if(!ValidataUtil.validataDate(reqQualityInspectionVO.getStartDate())){
					return resTheSignInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"endDate" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
				}
			}
			if (reqQualityInspectionVO.getList() == null) {
				return resTheSignInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"list" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
			}
			try {
				// 正常流程
				List<ResBMSQualityInspectionAttrVO> dataList = new ArrayList<ResBMSQualityInspectionAttrVO>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", reqQualityInspectionVO.getStatus());
				map.put("department", reqQualityInspectionVO.getDepartment());
				map.put("productType", reqQualityInspectionVO.getProductType());
				map.put("refuseReasonLevelOneCd", reqQualityInspectionVO.getRefuseReasonLevelOneCd());
				map.put("refuseReasonLevelTwoCd", reqQualityInspectionVO.getRefuseReasonLevelTwoCd());
				map.put("auditIdList", reqQualityInspectionVO.getList());
				// 切割时间
				/*
				 * List<String> auditDateList = DateUtil.cutDate("D",
				 * reqQualityInspectionVO.getStartDate(),
				 * reqQualityInspectionVO.getEndDate());
				 * auditDateList.add(reqQualityInspectionVO.getStartDate());
				 */
				List<BMSQualityInspectionEntity> qualityInsList = bMSQualityInspectionService.queryQualityInsInfo(map);
				for (BMSQualityInspectionEntity bmsQualityInspectionEntity : qualityInsList) {
					ResBMSQISearchListVO = new ResBMSQualityInspectionAttrVO();
					BeanUtils.copyProperties(bmsQualityInspectionEntity, ResBMSQISearchListVO);
				}
				dataList.add(ResBMSQISearchListVO);
				/*
				 * for (int i = 0; i < auditDateList.size(); i++) {
				 * map.put("startDate", auditDateList.get(i) + " 00:00:00");
				 * map.put("endDate", auditDateList.get(i) + " 23:59:59");
				 * List<BMSQualityInspectionEntity> qualityInsList =
				 * bMSQualityInspectionService .queryQualityInsInfo(map); for
				 * (BMSQualityInspectionEntity bmsQualityInspectionEntity :
				 * qualityInsList) { ResBMSQISearchListVO = new
				 * ResBMSQualityInspectionAttrVO();
				 * BeanUtils.copyProperties(bmsQualityInspectionEntity,
				 * ResBMSQISearchListVO); } dataList.add(ResBMSQISearchListVO);
				 */
				/*
				 * switch (reqQualityInspectionVO.getStatus()) { case "1"://通过件
				 * for (BMSQualityInspectionEntity bmsQualityInspectionEntity :
				 * qualityInsList) { map.put("loanNo",
				 * bmsQualityInspectionEntity.getLoanNo()); map.put("rtfState",
				 * EnumConstants.RtfState.XSCS.getValue());
				 * map.put("rtfNodeState",
				 * EnumConstants.RtfNodeState.XSCSREJECT.getValue()); boolean
				 * flag=bMSQualityInspectionService.checkLoanIsNowReject(map);
				 * if(flag){//如果存在就删除这条记录
				 * qualityInsList.remove(bmsQualityInspectionEntity); continue;
				 * } ResBMSQISearchListVO = new ResBMSQualityInspectionAttrVO();
				 * BeanUtils.copyProperties(bmsQualityInspectionEntity,
				 * ResBMSQISearchListVO); } dataList.add(ResBMSQISearchListVO);
				 * break; case "2"://初审拒绝件 for (BMSQualityInspectionEntity
				 * bmsQualityInspectionEntity : qualityInsList) {
				 * ResBMSQISearchListVO = new ResBMSQualityInspectionAttrVO();
				 * BeanUtils.copyProperties(bmsQualityInspectionEntity,
				 * ResBMSQISearchListVO); } dataList.add(ResBMSQISearchListVO);
				 * break; case "3"://终审通过且当日拒绝件 for (BMSQualityInspectionEntity
				 * bmsQualityInspectionEntity : qualityInsList) {
				 * map.put("loanNo", bmsQualityInspectionEntity.getLoanNo());
				 * boolean
				 * flag=bMSQualityInspectionService.checkLoanIsNowReject(map);
				 * if(flag){//如果存在就删除这条记录
				 * qualityInsList.remove(bmsQualityInspectionEntity); continue;
				 * } ResBMSQISearchListVO = new ResBMSQualityInspectionAttrVO();
				 * BeanUtils.copyProperties(bmsQualityInspectionEntity,
				 * ResBMSQISearchListVO); } dataList.add(ResBMSQISearchListVO);
				 * break; default:
				 * logger.info("status没有匹配到----------------------"+
				 * reqQualityInspectionVO.getStatus()); break; } }
				 */
				resBMSQualityInspectionVO.setList(dataList);
				logger.info("----------------请求结束------------");
			} catch (Exception e) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		} else {
			return resTheSignInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
					"reqQualityInspectionVO" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resBMSQualityInspectionVO);
		}
		return resBMSQualityInspectionVO;
	}

	/**
	 * 申请件接口2具体业务实现方法
	 */
	@Override
	public ResBMSQualityInspectionVO queryQualityInsInfoTwo(ReqQualityInspectionTwoVO reqQualityInspectionTwoVO) {
		ResBMSQualityInspectionVO resBMSQualityInspectionVO = null;
		ResBMSQualityInspectionAttrVO ResBMSQISearchListVO = null;
		if (reqQualityInspectionTwoVO != null) {
			if (reqQualityInspectionTwoVO.getLoanList() == null) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "loanList" });
			} else {
				try {
					List<ResBMSQualityInspectionAttrVO> dataList = new ArrayList<ResBMSQualityInspectionAttrVO>();
					resBMSQualityInspectionVO = new ResBMSQualityInspectionVO();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("personName", reqQualityInspectionTwoVO.getCustName());
					map.put("iDNo", reqQualityInspectionTwoVO.getiDNo());
					map.put("loanNoList", reqQualityInspectionTwoVO.getLoanList());
					map.put("rtfNodeState", reqQualityInspectionTwoVO.getRtfNodeState());
					map.put("status", "4");
					List<BMSQualityInspectionEntity> qualityInsList = bMSQualityInspectionService
							.queryQualityInsInfo(map);
					for (BMSQualityInspectionEntity bmsQualityInspectionEntity : qualityInsList) {
						ResBMSQISearchListVO = new ResBMSQualityInspectionAttrVO();
						BeanUtils.copyProperties(bmsQualityInspectionEntity, ResBMSQISearchListVO);
					}
					dataList.add(ResBMSQISearchListVO);
					resBMSQualityInspectionVO.setList(dataList);
				} catch (Exception e) {
					throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
				}
			}
		} else {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqQualityInspectionTwoVO" });
		}
		return resBMSQualityInspectionVO;
	}
	/**
	 * 根据前台传过来的数据，信息排序整理
	 * @param reqIntegratedSearchVO
	 * @return
	 */
	public static String getSortValue(ReqApplicationPartVO reqApplicationPartVO){
		if(StringUtils.isEmpty(reqApplicationPartVO.getFieldSort())){
			return "bla.CREATED_TIME desc";
		}
		String sort = " desc";
		if(reqApplicationPartVO.getRulesSort() == 1){
			sort = " asc";
		}
		
		String field = "bla.CREATED_TIME";
		if("loanNo".equals(reqApplicationPartVO.getFieldSort())){
			field = "blb.loan_no";
		}else if("customerName".equals(reqApplicationPartVO.getFieldSort())){
			field = "CONVERT(blb.NAME USING gbk)";
		}else if("owningBanchName".equals(reqApplicationPartVO.getFieldSort())){
			field = "CONVERT(blb.ENTER_BRANCH USING gbk)";
		}else if("auditEndTime".equals(reqApplicationPartVO.getFieldSort())){
			field = "bla.CREATED_TIME";
		}else if("cdNo".equals(reqApplicationPartVO.getFieldSort())){
			field = "blb.ID_NO";
		}else if("productName".equals(reqApplicationPartVO.getFieldSort())){
			field = "CONVERT(blp.PRODUCT_NAME USING gbk)";
		}else if("accLmt".equals(reqApplicationPartVO.getFieldSort())){
			field = "bla.ACC_LMT";
		}else if("accTerm".equals(reqApplicationPartVO.getFieldSort())){
			field = "bla.ACC_TERM";
		}else if("accDate".equals(reqApplicationPartVO.getFieldSort())){
			field = "bla.ACC_DATE";
		}else if("primaryReason".equals(reqApplicationPartVO.getFieldSort())){
			field="ble.PRIMARY_REASON";
		}else if("secodeReason".equals(reqApplicationPartVO.getFieldSort())){
			field="ble.SECODE_REASON";
		}else{
			field = "bla.CREATED_TIME";
		}
		return field + sort;
	}

	/**
	 * 通过借款编号,客户ID,身份证ID以及流程节点状态 获取申请件信息接口getApplicationInfo HTQY-SUBMIT
	 */
	@Override
	public PageResponse<ResBMSApplicationPartVO> getApplicationInfo(ReqApplicationPartVO reqApplicationPartVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数--------" + gson.toJson(reqApplicationPartVO));
		PageResponse<ResBMSApplicationPartVO> response = new PageResponse<ResBMSApplicationPartVO>();
		
		if (StringUtils.isEmpty(reqApplicationPartVO.getFlag())) {
			return ContractFirstAdultExecuter.resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(), 
					"flag"+BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
		}
		
		List<String> rtfNodeStatusList = new ArrayList<String>();
		List<String> checkNodeStatusList = null;
		if(reqApplicationPartVO.getFlag().equals("1")){
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.XSZSPASS.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.HTQYASSIGN.getValue());
		}else if(reqApplicationPartVO.getFlag().equals("2")){
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.CSFPREJECT.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.XSCSREJECT.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.ZSFPREJECT.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.XSZSREJECT.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.SQJWH_REJECT.getValue());
			rtfNodeStatusList.add(EnumConstants.RtfNodeState.HTQYREJECT.getValue());
			
			//AMS-331
			//只需要展示    不需要复核，复核通过，的单子
			checkNodeStatusList = new ArrayList<String>();
			checkNodeStatusList.add(EnumConstants.ChcekNodeState.NOCHECK.getValue());
			checkNodeStatusList.add(EnumConstants.ChcekNodeState.CHECKPASS.getValue());
			
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"Flag"});
		}
		
		try {
			PageParam pageParam = new PageParam(reqApplicationPartVO.getPageNum(),reqApplicationPartVO.getPageSize());
			Map<String, Object> parmMap = new HashMap<String, Object>();
			parmMap.put("loanNo", reqApplicationPartVO.getLoanNo());
			parmMap.put("customerName", reqApplicationPartVO.getCustomerName());
			parmMap.put("cdNo", reqApplicationPartVO.getCdNo());
			parmMap.put("rtfNodeStatusList", rtfNodeStatusList);
			parmMap.put("checkNodeStatusList", checkNodeStatusList);
			parmMap.put("flag", reqApplicationPartVO.getFlag());
			parmMap.put("startDate", reqApplicationPartVO.getStartDate());
			parmMap.put("endDate", reqApplicationPartVO.getEndDate());
			parmMap.put("sortValue", getSortValue(reqApplicationPartVO));
			PageBean<BMSApplicationPartEntity> pageBean = bMSQualityInspectionService.listPage(pageParam, parmMap);
			List<BMSApplicationPartEntity> bmsApplicationPartEntitys = pageBean.getRecords();
			
			// 构造响应参数
			//  Collections.sort(list,new Comparator<User>(){  
           // public int compare(User arg0, User arg1) {  
            //    return arg0.getPrice().compareTo(arg1.getPrice());  
            //}  
            if(reqApplicationPartVO.getFlag().equals("2") && StringUtils.isEmpty(reqApplicationPartVO.getFieldSort())){
            	 Collections.sort(bmsApplicationPartEntitys,new Comparator<BMSApplicationPartEntity>(){  
                     public int compare(BMSApplicationPartEntity arg0, BMSApplicationPartEntity arg1) {  
                         return arg1.getRejectPersonDate().compareTo(arg0.getRejectPersonDate());  
                     }  
                 });  
            } 
			List<ResBMSApplicationPartVO> records = new ArrayList<ResBMSApplicationPartVO>();
			for (BMSApplicationPartEntity ApplicationPartEntity : bmsApplicationPartEntitys) {
				ResBMSApplicationPartVO ResBMSApplicationPartVO = new ResBMSApplicationPartVO();
				BeanUtils.copyProperties(ApplicationPartEntity, ResBMSApplicationPartVO);
				String idNo = ResBMSApplicationPartVO.getCdNo();
				if(idNo != null && idNo.length() > 4){
					ResBMSApplicationPartVO.setCdNo("*"+idNo.substring(idNo.length() - 4, idNo.length()));
				}
				if(ApplicationPartEntity.getCheckPersonCode()!=null){
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(ApplicationPartEntity.getCheckPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);
					if(resEmps != null){
						ResBMSApplicationPartVO.setCheckPersonName(resEmps.getName());	
					}
					
				}
				if(ApplicationPartEntity.getFinalPersonCode()!=null){
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(ApplicationPartEntity.getFinalPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);	
					if(resEmps != null){
						ResBMSApplicationPartVO.setFinalPersonName(resEmps.getName());	
					}
					
				}
				if(ApplicationPartEntity.getApprovalPersonCode()!=null){
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(ApplicationPartEntity.getApprovalPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);	
					if(resEmps != null){
						ResBMSApplicationPartVO.setFinalPersonName(resEmps.getName());	
					}
					
				}
				
				
				records.add(ResBMSApplicationPartVO);
			}
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
			logger.info("返回结果------" + gson.toJson(records));
		} catch (Exception e) {
			logger.info("请求参数--------" + gson.toJson(reqApplicationPartVO));
			logger.info("错误信息--------" + e.getLocalizedMessage());
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	/**
	 * 审核系统申请件信息维护更新
	 */
	@Override
	public ResBMSAudiUpdVo updateApplicationInfo(ReqApplicationPartUpdVO reqApplicationPartUpdVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数--------" + gson.toJson(reqApplicationPartUpdVO));
		ResBMSAudiUpdVo resAudiUpdVo = new ResBMSAudiUpdVo();
		if (reqApplicationPartUpdVO != null) {
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getSysCode())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqApplicationPartUpdVO.getSysCode())) {
					return ContractFirstAdultExecuter.resAudiInfo(
							BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getFlag())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"flag" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			} else {
				if (!ValidataUtil.isExtisCaseType(reqApplicationPartUpdVO.getFlag())) {
					return ContractFirstAdultExecuter.resAudiInfo(
							BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"flag" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resAudiUpdVo);
				}
			}
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getLoanNo())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getStatus())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"status" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			}else{
				if(!ValidataUtil.checkLoanNoStatus(reqApplicationPartUpdVO.getStatus())){
					return ContractFirstAdultExecuter.resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"借款单子状态status"
									+ BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resAudiUpdVo);
				}
			}		
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getRtfState())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"rtfStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getRtfNodeState())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"rtfNodeStatus" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			}	
			if (!ValidataUtil.isExtisRtfNodeStatus(reqApplicationPartUpdVO.getRtfState(),
					reqApplicationPartUpdVO.getRtfNodeState())) {
				return ContractFirstAdultExecuter.resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"rtfStatus or rtfNodeStatus"
								+ BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resAudiUpdVo);
			}
			if (StringUtils.isEmpty(reqApplicationPartUpdVO.getOperatorIP())) {
				return ContractFirstAdultExecuter.resAudiInfo(
						BizErrorCode.BIZ_EOERR.getErrorCode(),
						"operatorIP" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
			} else {
				if (!ValidataUtil.isIP(reqApplicationPartUpdVO.getOperatorIP())) {
					return ContractFirstAdultExecuter.resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"operatorIP" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(),resAudiUpdVo);
				}
			}
		} else {
			logger.info("申请件信息维护reqApplicationPartUpdVO请求对象为空" + gson.toJson(reqApplicationPartUpdVO));
			return ContractFirstAdultExecuter.resAudiInfo(
					BizErrorCode.BIZ_EOERR.getErrorCode(),
					"reqApplicationPartUpdVO" + BizErrorCode.BIZ_EOERR.getDefaultMessage(),resAudiUpdVo);
		}
		try {
			boolean flag = bMSQualityInspectionService.updateApplicationPartInfo(reqApplicationPartUpdVO);
			if(flag){
				logger.info("----------------请求结束------------");
				return resAudiUpdVo;
			}
		} catch (Exception e) {
			logger.info("数据库操作失败回滚" + gson.toJson(reqApplicationPartUpdVO));
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return null;
	}
	
	/**
	 * 审核维护响应修改对象 ResBMSQualityInspectionVO
	 * 
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static ResBMSQualityInspectionVO resTheSignInfo(String errCode, String errMsg,ResBMSQualityInspectionVO resBMSQualityInspectionVO) {
		resBMSQualityInspectionVO.setRepCode(errCode);
		resBMSQualityInspectionVO.setRepMsg(errMsg);
		return resBMSQualityInspectionVO;
	}

	@Override
	public Response<Object> updateApplicationInfoNew(ReqApplicationPartUpVO reqApplicationPartUpVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数--------" + gson.toJson(reqApplicationPartUpVO));
		Response<Object> res = new Response<Object>();
		
		ibmsSysLogService.recordSysLog(reqApplicationPartUpVO, "审核系统|申请件维护|update|null","接口名称:updateApplicationInfo","申请件维护更新"+reqApplicationPartUpVO.getRemark());
		
		ApplicationPartEntity applicationPartEntity = new ApplicationPartEntity();
		// 校验必填项
		BeanUtils.copyProperties(reqApplicationPartUpVO, applicationPartEntity);

		Response<Object> validate = Validate.getInstance().validate(applicationPartEntity);
		if (!validate.isSuccess()) {
			return validate;
		}
		applicationPartEntity.chack();
		
		bMSQualityInspectionService.updateApplicationPartInfoNew(applicationPartEntity);
		
		return res;
	}

	@Override
	public Response<Object> updateIfNewLoanNo(PersonHistoryLoanVO personHistoryLoanVO) {
		Response<Object> res = new Response<Object>();
		
		//借款编号
		if(StringUtils.isEmpty(personHistoryLoanVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"借款编号不能为空"});
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", personHistoryLoanVO.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(map);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		long resResult = loanBaseService.updateIfNewLoanNo(map);
		if(resResult == 0){
			res.setRepCode("000001");
			res.setRepMsg("更新失败!");
		}
		return res;
	}
}
