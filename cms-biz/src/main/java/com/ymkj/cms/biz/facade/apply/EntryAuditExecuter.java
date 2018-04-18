package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumCHConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryAuditOperationVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryCancelVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntrySearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqInformationVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqPersonalInformation;
import com.ymkj.cms.biz.api.vo.response.apply.ResEntrySearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResWaitToDealCount;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonalInformation;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
@Service
public class EntryAuditExecuter implements IEntryAuditExecuter {
	
	 @Autowired
	 private LoanBaseService loanBaseService;
	 @Autowired
	 private IBMSSysLoanLogService sysLoanLogService;
	 @Autowired
	 private APPPersonInfoService appPersonInfoService;
	 
	 @Autowired
	 private LoanExtService loanExtService;
	 
	@Override
	public PageResponse<ResEntrySearchVO> listPage(
			ReqEntrySearchVO reqEntrySearchVO) {
		
		//参数校验
		if(reqEntrySearchVO.getPageNum()==0 || reqEntrySearchVO.getPageSize()==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getOptionModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"optionModule"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getAgencyOrComplete())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"agencyOrComplete"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getOwningBranchId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		
		// 构造请求参数
		PageParam pageParam = new PageParam(reqEntrySearchVO.getPageNum(), reqEntrySearchVO.getPageSize());
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanKit.bean2map(reqEntrySearchVO);
			
			//前台只传年月日   在时间末尾添加起始  00:00:00     完结23:59:59
			if (reqEntrySearchVO.getCompletionTimeStart() != null) {
				String startTime = DateUtil.defaultFormatDay(reqEntrySearchVO.getCompletionTimeStart()) + " 00:00:00";
				reqEntrySearchVO.setCompletionTimeStart(DateUtil.strToDateTime(startTime));
			}
			if (reqEntrySearchVO.getCompletionTimeEnd() != null) {
				String endTime = DateUtil.defaultFormatDay(reqEntrySearchVO.getCompletionTimeEnd()) + " 23:59:59";
				reqEntrySearchVO.setCompletionTimeEnd(DateUtil.strToDateTime(endTime));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果名字不为空
		if(!org.apache.commons.lang.StringUtils.isBlank(reqEntrySearchVO.getPersonName())){
			paramMap.put("name", reqEntrySearchVO.getPersonName());
		}
		Date newDate = new Date();
		List<String> notStatus = new ArrayList<String>();
		//判断录入修改 ，录入复核 ，根据status查询，判断代办任务 完成任务，左连接日志表关联查询，体现出修改次数历史
		if(reqEntrySearchVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_MODIFY.getValue())){
			//录入环节查询的是自己本人录入的借款单
			paramMap.put("inServiceCode", reqEntrySearchVO.getServiceCode());
			
			if(reqEntrySearchVO.getAgencyOrComplete().equals(EnumConstants.agencyOrComplete.agency.getValue())){
				// 录入修改  代办
				paramMap.put("inStatus", EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
				paramMap.put("DBRB", "acc");//待办任务标识  用于处理排序问题
			}else{
				// 录入修改  完成
				notStatus.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
				//以下进入退件箱，不在已完成列表展示
				notStatus.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());
				notStatus.add(EnumConstants.RtfNodeState.CSFPCANCEL.getValue());
				notStatus.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());
				notStatus.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());
				paramMap.put("notStatus", notStatus);
				
				//如果时间段传空
				if(reqEntrySearchVO.getCompletionTimeStart() == null && reqEntrySearchVO.getCompletionTimeEnd() == null){
					paramMap.put("applyStartTime", DateUtil.getDateBefore(newDate, 30));
					paramMap.put("applyEndTime", newDate);
				}else if(reqEntrySearchVO.getCompletionTimeStart() != null && reqEntrySearchVO.getCompletionTimeEnd() == null){
					paramMap.put("applyStartTime", reqEntrySearchVO.getCompletionTimeStart());
					paramMap.put("applyEndTime", newDate);
				}else if(reqEntrySearchVO.getCompletionTimeStart() == null && reqEntrySearchVO.getCompletionTimeEnd() != null){
					paramMap.put("applyEndTime", reqEntrySearchVO.getCompletionTimeEnd());
				}else{
					paramMap.put("applyStartTime", reqEntrySearchVO.getCompletionTimeStart());
					paramMap.put("applyEndTime", reqEntrySearchVO.getCompletionTimeEnd());
				}
				
				paramMap.put("LRXG_complete", "acc");
			}	
		}else if(reqEntrySearchVO.getOptionModule().equals(EnumConstants.OptionModule.APPLY_CHECK.getValue())){
			//审核环节查询的是非本人录入的单子
			paramMap.put("notInServiceCode", reqEntrySearchVO.getServiceCode());
			
			if(reqEntrySearchVO.getAgencyOrComplete().equals(EnumConstants.agencyOrComplete.agency.getValue())){
				//录入复核  代办
				//paramMap.put("reviewCode", reqEntrySearchVO.getServiceCode());
				paramMap.put("inStatus", EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
				paramMap.put("DBRB", "acc");//待办任务标识  用于处理排序问题
			}else{
				//录入复核  完成
				notStatus.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());//申请录入保存
				notStatus.add(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());//申请录入取消
				notStatus.add(EnumConstants.RtfNodeState.SQLR_REJECT.getValue());//申请录入拒绝
				notStatus.add(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());//录入超时拒绝
				notStatus.add(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());//录入超时取消
				
				notStatus.add(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());//录入复核办理
//				notStatus.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());// CFS-105 bug要求改
//				notStatus.add(EnumConstants.RtfNodeState.LRFH_CANCEL.getValue());// CFS-197
				
				
				
				paramMap.put("notStatus", notStatus);
				
				//如果时间段传空
				if(reqEntrySearchVO.getCompletionTimeStart() == null && reqEntrySearchVO.getCompletionTimeEnd() == null){
					paramMap.put("auditStartTime", DateUtil.getDateBefore(newDate, 30));
					paramMap.put("auditEndTime", newDate);
				}else if(reqEntrySearchVO.getCompletionTimeStart() != null && reqEntrySearchVO.getCompletionTimeEnd() == null){
					paramMap.put("auditStartTime", reqEntrySearchVO.getCompletionTimeStart());
					paramMap.put("auditEndTime", newDate);
				}else if(reqEntrySearchVO.getCompletionTimeStart() == null && reqEntrySearchVO.getCompletionTimeEnd() != null){
					paramMap.put("auditEndTime", reqEntrySearchVO.getCompletionTimeEnd());
				}else{
					paramMap.put("auditStartTime", reqEntrySearchVO.getCompletionTimeStart());
					paramMap.put("auditEndTime", reqEntrySearchVO.getCompletionTimeEnd());
				}
				paramMap.put("LRFH_complete", "acc");
				
				paramMap.put("reviewCode", reqEntrySearchVO.getServiceCode());
			}	
		}
		
		//是APP进件
		if(reqEntrySearchVO.getAppFlag() != null && reqEntrySearchVO.getAppFlag().equals("1")){
			paramMap.put("appInputFlag", "app_input");
		}else if(reqEntrySearchVO.getAppFlag() != null && reqEntrySearchVO.getAppFlag().equals("0")){
			paramMap.put("noAppInputFlag", "no_app_input");
		}
		
		// 调用业务逻辑  listPageEntrySearch
		PageBean<EntrySearchEntity> pageBean = loanBaseService.listPageEntrySearch(pageParam, paramMap);
		
		// 构造响应参数
		List<ResEntrySearchVO> records = new ArrayList<ResEntrySearchVO>();
		List<EntrySearchEntity> entrySearchEntitys = pageBean.getRecords();
		for (EntrySearchEntity entrySearchEntity : entrySearchEntitys) {
			if(entrySearchEntity == null ){
				entrySearchEntity = new EntrySearchEntity();
			}
			ResEntrySearchVO resEntrySearchVO = new ResEntrySearchVO();
			BeanUtils.copyProperties(entrySearchEntity, resEntrySearchVO);
			if(entrySearchEntity.getStatus().equals(EnumConstants.LoanStatus.CANCEL.getValue())){
				resEntrySearchVO.setStatus("取消");
			}else if(entrySearchEntity.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue())){
				resEntrySearchVO.setStatus("拒绝");
			}else{
				if(entrySearchEntity.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.LRFH_RETURN.getValue())){
					resEntrySearchVO.setStatus("退回");
				}else{
					resEntrySearchVO.setStatus("提交");
				}
			}
			
			if(entrySearchEntity.getAppInputFlag()!=null && entrySearchEntity.getAppInputFlag().equals("app_input")){
				resEntrySearchVO.setAppInputFlag("APP进件");
			}else{
				resEntrySearchVO.setAppInputFlag("PC进件");
			}
			
			records.add(resEntrySearchVO);
		}
		// 忽略 复制的字段
		PageResponse<ResEntrySearchVO> response = new PageResponse<ResEntrySearchVO>();
		BeanUtils.copyProperties(pageBean, response, "records");
		response.setRecords(records);
		return response;
	}
	
	@Override
	public Response<Object> queryInputModifyCount(ReqEntrySearchVO reqEntrySearchVO) {
		Response<Object> response = new Response<Object>();
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getOwningBranchId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inServiceCode", reqEntrySearchVO.getServiceCode());
		//paramMap.put("reviewCode", reqEntrySearchVO.getServiceCode());
		paramMap.put("inStatus", EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		paramMap.put("owningBranchId",reqEntrySearchVO.getOwningBranchId());
		
		Long count = loanBaseService.queryWaitToDealCount(paramMap);
		
		response.setData(count);
		return response;
	}

	@Override
	public Response cancel(ReqEntryCancelVO reqEntryCancelVO) {
		Response  response = new Response();
		//参数校验
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())
				&& reqEntryCancelVO.getLoanBaseId() == null){
			if(reqEntryCancelVO.getLoanBaseId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo,loanBaseId"});
			}
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getIp())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ip"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getOptionModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"optionModule"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getFirstLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"firstLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getTwoLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"twoLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceName"});
		}
		if(reqEntryCancelVO.getServiceId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceId"});
		}
		
		if(reqEntryCancelVO.getTwoLevelReason().equals("0")){
			reqEntryCancelVO.setTwoLevelReason(null);
		}
		if(reqEntryCancelVO.getTwoLevelReasonCode().equals("0")){
			reqEntryCancelVO.setTwoLevelReasonCode(null);
		}
		
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		if(!org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())){
			map.put("loanNo", reqEntryCancelVO.getLoanNo());
		}else{
			map.put("id", reqEntryCancelVO.getLoanBaseId());
		}
		
		List<LoanBaseEntity> listLoanBase= loanBaseService.getByMap(map);
		if(listLoanBase == null || listLoanBase.size() == 0){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		
		/**
		 * 拦截
		 */
		LoanBaseEntity	loanBaseEntitySelect = listLoanBase.get(0);
		if(loanBaseEntitySelect.getStatus().equals(EnumConstants.LoanStatus.CANCEL.getValue())){
			throw new BizException(BizErrorCode.EXISTING_APPLY_LOAN_CANCEL);
		}
		
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", loanBaseEntitySelect.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(obj);
		if(loanList == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		
		LoanBaseEntity loanBaseEntity=new LoanBaseEntity();
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRFH_CANCEL.getValue());
			//超补件时效自动取消
			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())
					|| EnumConstants.cancelFirstLevleReasonCode.超过提交时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())){
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_CANCEL.getValue());
			}
			loanExtEntity.setAuditEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());
			//超补件时效自动取消
			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReason())
					|| EnumConstants.cancelFirstLevleReasonCode.超过提交时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())){
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
			}
			loanExtEntity.setApplyEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_ENTRY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());
			//超补件时效自动取消
			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReason())
					|| EnumConstants.cancelFirstLevleReasonCode.超过提交时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())){
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
			}
			loanExtEntity.setApplyEndTime(new Date());
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"OptionModule","1,2,3"});
		}
		
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		loanBaseEntity.setModifierId(reqEntryCancelVO.getServiceId());
//		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceName());
		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceCode());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setId(loanBaseEntitySelect.getId());
		loanBaseEntity.setReviewCode(reqEntryCancelVO.getServiceCode());//复核人Code
		loanBaseEntity.setReviewName(reqEntryCancelVO.getServiceName());//复核人名称
		loanBaseService.saveOrUpdate(loanBaseEntity);
		
		
		loanExtEntity.setPrimaryReason(reqEntryCancelVO.getFirstLevelReason());//一级原因
		loanExtEntity.setSecodeReason(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());//二级原因
		loanExtEntity.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode());
		loanExtEntity.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		loanExtEntity.setId(loanList.get(0).getId());
		loanExtService.saveOrUpdate(loanExtEntity);
		
		
		//记借款日志
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		sysLoanLog.setLoanBaseId(loanBaseEntitySelect.getId());
		sysLoanLog.setLoanNo(loanBaseEntitySelect.getLoanNo());
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.LRFH.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.LRFH_CANCEL.getValue());
			//超补件时效自动取消
			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReason())
					|| EnumConstants.cancelFirstLevleReasonCode.超过提交时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())){
				
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_CANCEL.getValue());
			}
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_CANCEL.getValue());
			//超补件时效自动取消
			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReason())
					|| EnumConstants.cancelFirstLevleReasonCode.超过提交时效自动取消.getValue().equals(reqEntryCancelVO.getFirstLevelReasonCode())){
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
			}
		}
		sysLoanLog.setStatus(EnumConstants.LoanStatus.CANCEL.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.CANCEL.getValue());
		sysLoanLog.setOperator(reqEntryCancelVO.getServiceName()); 
		sysLoanLog.setOperatorCode(reqEntryCancelVO.getServiceCode()); 
		sysLoanLog.setOperationTime(new Date()); 
		sysLoanLog.setRemark(reqEntryCancelVO.getRemark()); 
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_TASK.getValue());
		sysLoanLog.setFirstLevleReasons(reqEntryCancelVO.getFirstLevelReason()); 
		sysLoanLog.setTwoLevleReasons(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());
		sysLoanLog.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode()); 
		sysLoanLog.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		sysLoanLogService.saveOrUpdate(sysLoanLog);
		return response;
	}

	@Override
	public Response<ReqInformationVO> queryInformationVO(ReqInformationVO reqInformationVO) {
		if(org.apache.commons.lang.StringUtils.isBlank(	reqInformationVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		
		ReqInformationVO reqInformation = new ReqInformationVO();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", 	reqInformationVO.getLoanNo());
		//getInformationVO
		InformationEntity informationEntity = loanBaseService.queryInformationEntity(paramMap);
		if(informationEntity == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}else{
			BeanUtils.copyProperties(informationEntity, reqInformation);
		}
		
		if (!StringUtils.isEmpty(reqInformation.getCreditApplication())) {// 借款用途
			reqInformation.setCreditApplication(EnumCHConstants.CreditApplication.valueOf("_" + reqInformation.getCreditApplication()).getValue());
		}
		
		Response<ReqInformationVO> response = new Response<ReqInformationVO>();
		response.setData(reqInformation);
		return response;
	}

	@Override
	public Response<String> reviewReturn(ReqEntryAuditOperationVO reqEntryAuditOperationVO) {
		Response<String> response = new Response<String>();
		
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryAuditOperationVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryAuditOperationVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryAuditOperationVO.getServiceName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceName"});
		}
		
		
		if(reqEntryAuditOperationVO.getTwoLevelReason().equals("0")){
			reqEntryAuditOperationVO.setTwoLevelReason(null);
		}
		if(reqEntryAuditOperationVO.getTwoLevelReasonCode().equals("0")){
			reqEntryAuditOperationVO.setTwoLevelReasonCode(null);
		}
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("loanNo", reqEntryAuditOperationVO.getLoanNo());
		
		List<LoanBaseEntity> listLoanBase= loanBaseService.getByMap(map);
		if(listLoanBase == null || listLoanBase.size() == 0){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		/**
		 * 拦截
		 */
		LoanBaseEntity	loanBaseEntitySelect = listLoanBase.get(0);
		if(loanBaseEntitySelect.getStatus().equals(EnumConstants.LoanStatus.CANCEL.getValue())){
			throw new BizException(BizErrorCode.APPLY_LOAN_CANCEL);
		}
		
		if(!loanBaseEntitySelect.getRtfNodeState().equals(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue())){
			throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
		}
		
		LoanBaseEntity updateEntity = new LoanBaseEntity();
		updateEntity.setId(loanBaseEntitySelect.getId());
		updateEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());
		updateEntity.setHandleType("10");
		updateEntity.setModifiedTime(new Date());
//		updateEntity.setModifier(reqEntryAuditOperationVO.getServiceName()==null?"":reqEntryAuditOperationVO.getServiceName());
		updateEntity.setModifier(reqEntryAuditOperationVO.getServiceCode()==null?"":reqEntryAuditOperationVO.getServiceCode());
		updateEntity.setReviewCode(reqEntryAuditOperationVO.getServiceCode());//复核人Code
		updateEntity.setReviewName(reqEntryAuditOperationVO.getServiceName());//复核人名称
		loanBaseService.saveOrUpdate(updateEntity);
		
		//更新改变字段比对 		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", loanBaseEntitySelect.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(obj);
		if(loanList == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		LoanExtEntity update = new LoanExtEntity();
		update.setId(loanList.get(0).getId());
		update.setPrimaryReason(reqEntryAuditOperationVO.getFirstLevelReason());//一级原因
		update.setFirstLevleReasonsCode(reqEntryAuditOperationVO.getFirstLevelReasonCode());
		update.setSecodeReason(reqEntryAuditOperationVO.getTwoLevelReason()==null?"":reqEntryAuditOperationVO.getTwoLevelReason());//二级原因
		update.setTwoLevleReasonsCode(reqEntryAuditOperationVO.getTwoLevelReasonCode()==null?"":reqEntryAuditOperationVO.getTwoLevelReasonCode());
		update.setAuditEndTime(new Date());//复核结束时间
		loanExtService.saveOrUpdate(update);
		
		
		
		//记借款日志
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		sysLoanLog.setLoanBaseId(loanBaseEntitySelect.getId());
		sysLoanLog.setLoanNo(loanBaseEntitySelect.getLoanNo());
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
		sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		sysLoanLog.setRtfState(EnumConstants.RtfState.LRFH.getValue());
		sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		sysLoanLog.setOperator(reqEntryAuditOperationVO.getServiceName()); 
		sysLoanLog.setOperatorCode(reqEntryAuditOperationVO.getServiceCode());
		sysLoanLog.setOperationTime(new Date()); 
		sysLoanLog.setRemark(reqEntryAuditOperationVO.getRemark()); 
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
		sysLoanLog.setFirstLevleReasons(reqEntryAuditOperationVO.getFirstLevelReason()); 
		sysLoanLog.setTwoLevleReasons(reqEntryAuditOperationVO.getTwoLevelReason()==null?"":reqEntryAuditOperationVO.getTwoLevelReason());
		sysLoanLog.setFirstLevleReasonsCode(reqEntryAuditOperationVO.getFirstLevelReasonCode()); 
		sysLoanLog.setTwoLevleReasonsCode(reqEntryAuditOperationVO.getTwoLevelReasonCode()==null?"":reqEntryAuditOperationVO.getTwoLevelReasonCode());
		sysLoanLogService.saveOrUpdate(sysLoanLog);				
		return response;
	}
	
	@Override
	public Response<ResWaitToDealCount> queryWaitToDealCount(ReqEntrySearchVO reqEntrySearchVO) {
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getOwningBranchId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		
		// 构造请求参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("notInServiceCode", reqEntrySearchVO.getServiceCode());
		paramMap.put("inStatus", EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());
		paramMap.put("owningBranchId",reqEntrySearchVO.getOwningBranchId());
//		paramMap.put("serviceCode",	reqEntrySearchVO.getServiceCode());
		
		Long count = loanBaseService.queryWaitToDealCount(paramMap);
		ResWaitToDealCount resWaitToDealCount = new ResWaitToDealCount();
		resWaitToDealCount.setCount(count);;
		
		PageResponse<ResWaitToDealCount> response = new PageResponse<ResWaitToDealCount>();
		response.setData(resWaitToDealCount);
		return response;
	}

	@Override
	public Response<String> updatePersonalInformation(ReqPersonalInformation reqPersonalInformation) {
		
		Response<String> response = new Response<String>();
		
		APPPersonalInformation appPersonalInformation = new APPPersonalInformation();
		BeanUtils.copyProperties(reqPersonalInformation, appPersonalInformation);
		
		
		Response<String> responseEstateInfoEntity = Validate.getInstance().validate(appPersonalInformation);
		if (!responseEstateInfoEntity.isSuccess()) {
			return responseEstateInfoEntity;
		}
		
		Map<String, Object> versionMap = new HashMap<String, Object>();
		versionMap.put("loanNo",  reqPersonalInformation.getLoanNo());
		versionMap.put("version", reqPersonalInformation.getVersion());
		long updateVersion = loanBaseService.updateVersion(versionMap);
		if(updateVersion <= 0){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", reqPersonalInformation.getLoanNo());
		APPPersonInfoEntity by = appPersonInfoService.getBy(paramMap);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		BeanUtils.copyProperties(appPersonalInformation, appPersonInfoEntity);
		appPersonInfoEntity.setId(by.getId());
		appPersonInfoEntity.setModifiedTime(new Date());
//		appPersonInfoEntity.setModifier(reqPersonalInformation.getServiceName());
		appPersonInfoEntity.setModifier(reqPersonalInformation.getServiceCode());
		appPersonInfoEntity.setModifierId(new Long("00"));
		appPersonInfoService.saveOrUpdate(appPersonInfoEntity);
		return response;
	}

	@Override
	public Response timeOutRefuse(ReqEntryCancelVO reqEntryCancelVO) {
		Response  response = new Response();
		//参数校验
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())
				&& reqEntryCancelVO.getLoanBaseId() == null){
			if(reqEntryCancelVO.getLoanBaseId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo,loanBaseId"});
			}
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getIp())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ip"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getOptionModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"optionModule"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getFirstLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"firstLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getTwoLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"twoLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceName"});
		}
		if(reqEntryCancelVO.getServiceId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceId"});
		}
		
		if(reqEntryCancelVO.getTwoLevelReason().equals("0")){
			reqEntryCancelVO.setTwoLevelReason(null);
		}
		if(reqEntryCancelVO.getTwoLevelReasonCode().equals("0")){
			reqEntryCancelVO.setTwoLevelReasonCode(null);
		}
		
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		if(!org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())){
			map.put("loanNo", reqEntryCancelVO.getLoanNo());
		}else{
			map.put("id", reqEntryCancelVO.getLoanBaseId());
		}
		
		List<LoanBaseEntity> listLoanBase= loanBaseService.getByMap(map);
		if(listLoanBase == null || listLoanBase.size() == 0){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		
		/**
		 * 拦截
		 */
		LoanBaseEntity	loanBaseEntitySelect = listLoanBase.get(0);
		if(loanBaseEntitySelect.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue())){
			throw new BizException(BizErrorCode.EXISTING_APPLY_LOAN_REFUSE);
		}
		
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", loanBaseEntitySelect.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(obj);
		if(loanList == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		
		LoanBaseEntity loanBaseEntity=new LoanBaseEntity();
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_REJECT.getValue());
			loanExtEntity.setAuditEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
			loanExtEntity.setApplyEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_ENTRY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
			loanExtEntity.setApplyEndTime(new Date());
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"OptionModule","1,2,3"});
		}
		
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		loanBaseEntity.setModifierId(reqEntryCancelVO.getServiceId());
//		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceName());
		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceCode());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setId(loanBaseEntitySelect.getId());
		loanBaseEntity.setReviewCode(reqEntryCancelVO.getServiceCode());//复核人Code
		loanBaseEntity.setReviewName(reqEntryCancelVO.getServiceName());//复核人名称
		loanBaseService.saveOrUpdate(loanBaseEntity);
		
		
		loanExtEntity.setPrimaryReason(reqEntryCancelVO.getFirstLevelReason());//一级原因
		loanExtEntity.setSecodeReason(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());//二级原因
		loanExtEntity.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode());
		loanExtEntity.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		loanExtEntity.setId(loanList.get(0).getId());
		loanExtService.saveOrUpdate(loanExtEntity);
		
		
		//记借款日志
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		sysLoanLog.setLoanBaseId(loanBaseEntitySelect.getId());
		sysLoanLog.setLoanNo(loanBaseEntitySelect.getLoanNo());
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.LRFH.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_REJECT.getValue());
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
		} else if(EnumConstants.OptionModule.APPLY_ENTRY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_REJECT.getValue());
		}
		sysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		sysLoanLog.setOperator(reqEntryCancelVO.getServiceName()); 
		sysLoanLog.setOperatorCode(reqEntryCancelVO.getServiceCode()); 
		sysLoanLog.setOperationTime(new Date()); 
		sysLoanLog.setRemark(reqEntryCancelVO.getRemark()); 
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_TASK.getValue());
		sysLoanLog.setFirstLevleReasons(reqEntryCancelVO.getFirstLevelReason()); 
		sysLoanLog.setTwoLevleReasons(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());
		sysLoanLog.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode()); 
		sysLoanLog.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		sysLoanLogService.saveOrUpdate(sysLoanLog);
		return response;
	}
	
	
	@Override
	public Response<ResWaitToDealCount> queryWaitToUpdateCount(ReqEntrySearchVO reqEntrySearchVO) {
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntrySearchVO.getOwningBranchId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchId"});
		}
		
		// 构造请求参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inStatus", EnumConstants.RtfNodeState.SQLR_SAVE.getValue());
		paramMap.put("owningBranchId",reqEntrySearchVO.getOwningBranchId());
		paramMap.put("serviceCode",	reqEntrySearchVO.getServiceCode());
		
		Long count = loanBaseService.queryWaitToDealCount(paramMap);
		ResWaitToDealCount resWaitToDealCount = new ResWaitToDealCount();
		resWaitToDealCount.setCount(count);;
		
		PageResponse<ResWaitToDealCount> response = new PageResponse<ResWaitToDealCount>();
		response.setData(resWaitToDealCount);
		return response;
	}

	@Override
	public Response invalid(ReqEntryCancelVO reqEntryCancelVO) {
		Response  response = new Response();
		//参数校验
		if(org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())
				&& reqEntryCancelVO.getLoanBaseId() == null){
			if(reqEntryCancelVO.getLoanBaseId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo,loanBaseId"});
			}
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getIp())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"ip"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getOptionModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"optionModule"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getFirstLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"firstLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getTwoLevelReason())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"twoLevelReason"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceCode"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(	reqEntryCancelVO.getServiceName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceName"});
		}
		if(reqEntryCancelVO.getServiceId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"serviceId"});
		}
		
		if(reqEntryCancelVO.getTwoLevelReason().equals("0")){
			reqEntryCancelVO.setTwoLevelReason(null);
		}
		if(reqEntryCancelVO.getTwoLevelReasonCode().equals("0")){
			reqEntryCancelVO.setTwoLevelReasonCode(null);
		}
		
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		if(!org.apache.commons.lang.StringUtils.isBlank(reqEntryCancelVO.getLoanNo())){
			map.put("loanNo", reqEntryCancelVO.getLoanNo());
		}else{
			map.put("id", reqEntryCancelVO.getLoanBaseId());
		}
		
		List<LoanBaseEntity> listLoanBase= loanBaseService.getByMap(map);
		if(listLoanBase == null || listLoanBase.size() == 0){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		
		/**
		 * 拦截
		 */
		LoanBaseEntity	loanBaseEntitySelect = listLoanBase.get(0);
		if(loanBaseEntitySelect.getStatus().equals(EnumConstants.LoanStatus.REFUSE.getValue())){
			throw new BizException(BizErrorCode.EXISTING_APPLY_LOAN_REFUSE);
		}
		
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("loanBaseId", loanBaseEntitySelect.getId());
		List<LoanExtEntity> loanList = loanExtService.listBy(obj);
		if(loanList == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		
		LoanBaseEntity loanBaseEntity=new LoanBaseEntity();
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRFH_REJECT.getValue());
			loanExtEntity.setAuditEndTime(new Date());
			//超补件时效自动取消
//			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消_SQLR.getValue().equals(reqEntryCancelVO.getFirstLevelReason())){
//				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_REJECT.getValue());
//			}
//			loanExtEntity.setAuditEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_REJECT.getValue());
			loanExtEntity.setApplyEndTime(new Date());
			//超补件时效自动取消
//			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消_SQLR.getValue().equals(reqEntryCancelVO.getFirstLevelReason())){
//				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
//			}
//			loanExtEntity.setApplyEndTime(new Date());
		}else if(EnumConstants.OptionModule.APPLY_ENTRY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_REJECT.getValue());
			loanExtEntity.setApplyEndTime(new Date());
			//超补件时效自动取消
//			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消_SQLR.getValue().equals(reqEntryCancelVO.getFirstLevelReason())){
//				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
//			}
//			loanExtEntity.setApplyEndTime(new Date());
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"OptionModule","1,2,3"});
		}
		
		loanBaseEntity.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		loanBaseEntity.setModifierId(reqEntryCancelVO.getServiceId());
//		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceName());
		loanBaseEntity.setModifier(reqEntryCancelVO.getServiceCode());
		loanBaseEntity.setModifiedTime(new Date());
		loanBaseEntity.setId(loanBaseEntitySelect.getId());
		loanBaseEntity.setReviewCode(reqEntryCancelVO.getServiceCode());//复核人Code
		loanBaseEntity.setReviewName(reqEntryCancelVO.getServiceName());//复核人名称
		loanBaseService.saveOrUpdate(loanBaseEntity);
		
		
		loanExtEntity.setPrimaryReason(reqEntryCancelVO.getFirstLevelReason());//一级原因
		loanExtEntity.setSecodeReason(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());//二级原因
		loanExtEntity.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode());
		loanExtEntity.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		loanExtEntity.setId(loanList.get(0).getId());
		loanExtService.saveOrUpdate(loanExtEntity);
		
		
		//记借款日志
		BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
		sysLoanLog.setLoanBaseId(loanBaseEntitySelect.getId());
		sysLoanLog.setLoanNo(loanBaseEntitySelect.getLoanNo());
		if(EnumConstants.OptionModule.APPLY_CHECK.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_CHECK.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.LRFH.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.LRFH_REJECT.getValue());
			//超补件时效自动取消
//			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消_SQLR.getValue().equals(reqEntryCancelVO.getFirstLevelReason())){
//				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FHCS_CANCEL.getValue());
//			}
		}else if(EnumConstants.OptionModule.APPLY_MODIFY.getValue().equals(reqEntryCancelVO.getOptionModule())){
			sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_MODIFY.getValue());
			sysLoanLog.setRtfState(EnumConstants.RtfState.SQLR.getValue());
			sysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQLR_REJECT.getValue());
			//超补件时效自动取消
//			if(EnumConstants.cancelFirstLevleReasonCode.超补件时效自动取消_SQLR.getValue().equals(reqEntryCancelVO.getFirstLevelReason())){
//				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.LRCS_CANCEL.getValue());
//			}
		}
		sysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		sysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		sysLoanLog.setOperator(reqEntryCancelVO.getServiceName()); 
		sysLoanLog.setOperatorCode(reqEntryCancelVO.getServiceCode()); 
		sysLoanLog.setOperationTime(new Date()); 
		sysLoanLog.setRemark(reqEntryCancelVO.getRemark()); 
		sysLoanLog.setOperationModule(EnumConstants.OptionModule.APPLY_TASK.getValue());
		sysLoanLog.setFirstLevleReasons(reqEntryCancelVO.getFirstLevelReason()); 
		sysLoanLog.setTwoLevleReasons(reqEntryCancelVO.getTwoLevelReason()==null?"":reqEntryCancelVO.getTwoLevelReason());
		sysLoanLog.setFirstLevleReasonsCode(reqEntryCancelVO.getFirstLevelReasonCode()); 
		sysLoanLog.setTwoLevleReasonsCode(reqEntryCancelVO.getTwoLevelReasonCode()==null?"":reqEntryCancelVO.getTwoLevelReasonCode());
		sysLoanLogService.saveOrUpdate(sysLoanLog);
		return response;
	}

	 
}
