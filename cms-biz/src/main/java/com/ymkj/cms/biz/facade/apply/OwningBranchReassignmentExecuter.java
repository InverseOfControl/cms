package com.ymkj.cms.biz.facade.apply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IOwningBranchReassignmentExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyPhoneVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentListVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentVO;
import com.ymkj.cms.biz.api.vo.request.apply.ResOwningBranchReassignmentSearchVO;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPContactInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;


@Service
public class OwningBranchReassignmentExecuter implements IOwningBranchReassignmentExecuter{
	
	@Autowired
	private LoanBaseService loanBaseService;
	@Autowired
	private APPContactInfoService appContactInfoService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;
	@Autowired
	private APPPersonInfoService appPersonInfoService;
	
	@Override
	public PageResponse<ResOwningBranchReassignmentSearchVO> search(
			ReqOwningBranchReassignmentSearchVO reqOwningBranchReassignmentSearchVO) {
		
		//系统日志
		ibmsSysLogService.recordSysLog(reqOwningBranchReassignmentSearchVO, 
												"入单系统|改派模块|门店改派|查询",
												"IOwningBranchReassignmentExecuter/search",
												"查询可改派基本信息");
		
		PageResponse<ResOwningBranchReassignmentSearchVO> response = new PageResponse<ResOwningBranchReassignmentSearchVO>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(reqOwningBranchReassignmentSearchVO.getPageNum() == 0 ){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageNum"});
		}
		
		if(reqOwningBranchReassignmentSearchVO.getPageSize() == 0 ){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageSize"});
		}
		
		if(reqOwningBranchReassignmentSearchVO.getOwningBranchIds() == null || reqOwningBranchReassignmentSearchVO.getOwningBranchIds().size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"owningBranchIds"});
		}
		
	
		if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentSearchVO.getStatus())){
			//允许传空   系统过滤掉
			reqOwningBranchReassignmentSearchVO.setStatus("1111");
		}
		
		List<String> rtfNodeState = new ArrayList<String>();
		
		if(reqOwningBranchReassignmentSearchVO.getStatus().equals("1")){
//			1录入修改
			rtfNodeState.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());//申请录入
			rtfNodeState.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());//录入复核退回
			rtfNodeState.add(EnumConstants.RtfNodeState.CSFPCANCEL.getValue());//初审分派办理退回
			rtfNodeState.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入
			rtfNodeState.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());//终审退回录入
		}else if(reqOwningBranchReassignmentSearchVO.getStatus().equals("2")){
//			2合同签约
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQYASSIGN.getValue());//合同签约
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQRRETURN.getValue());//合同确认退回
			rtfNodeState.add(EnumConstants.RtfNodeState.FKSHRETURN.getValue());//放款审核退回
			rtfNodeState.add(EnumConstants.RtfNodeState.FKQRRETURN.getValue());//放款确认退回
		}else if(reqOwningBranchReassignmentSearchVO.getStatus().equals("3")){
//			3合同确认	
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());//合同确认
		}else if(reqOwningBranchReassignmentSearchVO.getStatus().equals("4")){
//			3录入复核	
			rtfNodeState.add(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());//录入复核
		}else{
//			1录入修改
			rtfNodeState.add(EnumConstants.RtfNodeState.SQLR_SAVE.getValue());//申请录入
			rtfNodeState.add(EnumConstants.RtfNodeState.LRFH_RETURN.getValue());//录入复核退回
			rtfNodeState.add(EnumConstants.RtfNodeState.CSFPCANCEL.getValue());//初审分派办理退回
			rtfNodeState.add(EnumConstants.RtfNodeState.XSCSRETURN.getValue());//初审退回录入
			rtfNodeState.add(EnumConstants.RtfNodeState.XSZSRETURN.getValue());//终审退回录入
//			2合同签约
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQYASSIGN.getValue());//合同签约
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQRRETURN.getValue());//合同确认退回
			rtfNodeState.add(EnumConstants.RtfNodeState.FKSHRETURN.getValue());//放款审核退回
			rtfNodeState.add(EnumConstants.RtfNodeState.FKQRRETURN.getValue());//放款确认退回
//			3合同确认	
			rtfNodeState.add(EnumConstants.RtfNodeState.HTQYSUBMIT.getValue());//合同确认
//			4录入复核	
			//rtfNodeState.add(EnumConstants.RtfNodeState.SQLR_SUBMIT.getValue());//录入复核
		}
		
		
		
		paramMap.put("rtfNodeState", rtfNodeState);
		
		
		PageParam pageParam = new PageParam(reqOwningBranchReassignmentSearchVO.getPageNum(), reqOwningBranchReassignmentSearchVO.getPageSize());
		paramMap.put("name", reqOwningBranchReassignmentSearchVO.getName());
		paramMap.put("loanNo", reqOwningBranchReassignmentSearchVO.getLoanNo());
		paramMap.put("idNo", reqOwningBranchReassignmentSearchVO.getIdNo());
		paramMap.put("owningBranchIds", reqOwningBranchReassignmentSearchVO.getOwningBranchIds());
		paramMap.put("operatorCode", reqOwningBranchReassignmentSearchVO.getOperatorCode());//当前处理人Code
		paramMap.put("owningBranchIds", reqOwningBranchReassignmentSearchVO.getOwningBranchIds());
		paramMap.put("minAccLmt", reqOwningBranchReassignmentSearchVO.getMinAccLmt());
		paramMap.put("maxAccLmt", reqOwningBranchReassignmentSearchVO.getMaxAccLmt());
		paramMap.put("accTerms", reqOwningBranchReassignmentSearchVO.getAccTerms());
		
		PageBean<LoanBaseEntity> pageDate = loanBaseService.getReassignment(pageParam, paramMap);
		
		if(pageDate != null && pageDate.getRecords().size() != 0){
			List<ResOwningBranchReassignmentSearchVO> ResSearchVO = new ArrayList<ResOwningBranchReassignmentSearchVO>();
			List<LoanBaseEntity> listres = pageDate.getRecords();
			for (int i = 0; i < listres.size(); i++) {
				ResOwningBranchReassignmentSearchVO vo = new ResOwningBranchReassignmentSearchVO();
				vo.setLoanNo(listres.get(i).getLoanNo());// 	借款编号
				vo.setPersonName(listres.get(i).getName());//	客户姓名
				vo.setIdNo(listres.get(i).getIdNo());// 	证件号码
				vo.setContractBranch(listres.get(i).getContractBranch());//	营业部	
				vo.setProductName(listres.get(i).getProductName());//	借款产品
				vo.setInitProductName(listres.get(i).getInitProductName());
				vo.setOperatorCode(listres.get(i).getServiceCode());
				vo.setOperator(listres.get(i).getServiceName());//	处理人
				vo.setStatus(listres.get(i).getRtfState());
				
//				if(listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.SQLR_SAVE.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.LRFH_RETURN.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.CSFPCANCEL.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSRETURN.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())){
//					//申请录入,录入复核退回,初审分派办理退回,初审退回录入,终审退回录入  节点状态的都考虑是录入改派环节
//					vo.setStatus(EnumConstants.RtfState.SQLR.getValue());
//					vo.setOperatorCode(listres.get(i).getServiceCode()==null?"":listres.get(i).getServiceCode());
//					vo.setOperator(listres.get(i).getServiceName()==null?"":listres.get(i).getServiceName());//	处理人
//				}else if(listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.HTQYASSIGN.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.HTQRRETURN.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.FKSHRETURN.getValue())
//						|| listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.FKQRRETURN.getValue())){
//					//合同签约,合同确认退回,放款审核退回,放款确认退回
//					vo.setStatus(EnumConstants.RtfState.HTQY.getValue());
//					vo.setOperatorCode(listres.get(i).getSignCode()==null?"":listres.get(i).getSignCode());
//					vo.setOperator(listres.get(i).getSignName()==null?"":listres.get(i).getSignName());//	处理人
//				}else if(listres.get(i).getRtfNodeState().equals(EnumConstants.RtfNodeState.HTQYSUBMIT.getValue())){
//					//合同确认
//					vo.setStatus(EnumConstants.RtfState.HTQR.getValue());
//					vo.setOperatorCode(listres.get(i).getManagerCode()==null?"":listres.get(i).getManagerCode());
//					vo.setOperator(listres.get(i).getManagerName()==null?"":listres.get(i).getManagerName());//	处理人
//				}
				vo.setContractBranchId(listres.get(i).getContractBranchId());//签约营业部ID	
				vo.setApplyInputFlag(listres.get(i).getApplyInputFlag());//申请件渠道标识 	applyInput 普通进件 directApplyInput 直通车进件
				vo.setOwningBranchId(listres.get(i).getOwningBranchId());//进件门店ID
				vo.setOwningBranch(listres.get(i).getOwningBranch());//进件门店
				vo.setPactMoney(listres.get(i).getPactMoney());//进件门店
				vo.setSqlrUserCode(listres.get(i).getSqlrUserCode());
				vo.setVersion(listres.get(i).getVerson()+"");//版本号
				ResSearchVO.add(vo);
			}
			BeanUtils.copyProperties(pageDate, response, "records");
			response.setRecords(ResSearchVO);
		}
		return response;
	}

	@Override
	public Response<Object> reassignment(ReqOwningBranchReassignmentListVO reqOwningBranchReassignmentListVO) {
		
		//系统日志
		ibmsSysLogService.recordSysLog(reqOwningBranchReassignmentListVO, 
														"入单系统|改派模块|门店改派|开始改派",
														"IOwningBranchReassignmentExecuter/reassignment",
														"改派基本信息");
		
		if(reqOwningBranchReassignmentListVO.getListReqOwningBranchReassignmentVO() == null 
				|| reqOwningBranchReassignmentListVO.getListReqOwningBranchReassignmentVO().size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"listReqOwningBranchReassignmentVO"});
		}
		
		String msg = "";
		for (ReqOwningBranchReassignmentVO reqOwningBranchReassignmentVO :reqOwningBranchReassignmentListVO.getListReqOwningBranchReassignmentVO()) {
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getLoanNo())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
			}
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getCode())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"code"});
			}
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getName())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
			}
			
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getStatus())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"status"});
			}
			
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getIsThroughTrain())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"isThroughTrain"});
			}
			
			if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getVersion())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
			}
			
			if(reqOwningBranchReassignmentVO.getIsThroughTrain().equals("1")){
				if(org.apache.commons.lang.StringUtils.isBlank(reqOwningBranchReassignmentVO.getBranchId())){
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"branchId"});
				}
			}
		}
		
		for (ReqOwningBranchReassignmentVO reqOwningBranchReassignmentVO :reqOwningBranchReassignmentListVO.getListReqOwningBranchReassignmentVO()) {
			Map<String, Object> versionMap = new HashMap<String, Object>();
			versionMap.put("loanNo",  reqOwningBranchReassignmentVO.getLoanNo());
			versionMap.put("version", reqOwningBranchReassignmentVO.getVersion());
			long updateVersion = loanBaseService.updateVersion(versionMap);
			if(updateVersion <= 0){
				msg += reqOwningBranchReassignmentVO.getLoanNo()+"操作失败  原因，该笔借款已更新！无法进行操作！   ";
				continue;
			}
			
			List<LoanBaseEntity> byMap = loanBaseService.getByMap(versionMap);
			if(byMap == null || byMap.size() != 1){
				msg += reqOwningBranchReassignmentVO.getLoanNo()+"操作失败  原因，该笔借款select错误！   ";
				continue;
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.SQLR.getValue())){//SXLR 录入修改
//				1录入修改
				paramMap.put("LRXG", reqOwningBranchReassignmentVO.getStatus());
				paramMap.put("LRXG_code", reqOwningBranchReassignmentVO.getCode());
				paramMap.put("LRXG_name", reqOwningBranchReassignmentVO.getName());
				if(reqOwningBranchReassignmentVO.getIsThroughTrain().equals("0")){
					//非直通车  签约客服也需要更改
					paramMap.put("LRXG_sign_code", reqOwningBranchReassignmentVO.getCode());
					paramMap.put("LRXG_sign_name", reqOwningBranchReassignmentVO.getName());
				}
			}else if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.LRFH.getValue())){//SXLR 录入复核
//				1录入复核
				paramMap.put("LRFH", reqOwningBranchReassignmentVO.getStatus());
				paramMap.put("LRFH_code", reqOwningBranchReassignmentVO.getCode());
				paramMap.put("LRFH_name", reqOwningBranchReassignmentVO.getName());
				/*if(reqOwningBranchReassignmentVO.getIsThroughTrain().equals("0")){
					//非直通车  签约客服也需要更改
					paramMap.put("LRXG_sign_code", reqOwningBranchReassignmentVO.getCode());
					paramMap.put("LRXG_sign_name", reqOwningBranchReassignmentVO.getName());
				}*/
			}else if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQY.getValue())){//HTQY 合同签约
//				2合同签约  如果是直通车进件 需要根据营业部ID，重新查询员工
				if(reqOwningBranchReassignmentVO.getIsThroughTrain().equals("0")){
					//非直通车
					paramMap.put("HTQY", reqOwningBranchReassignmentVO.getStatus());
					paramMap.put("HTQY_code", reqOwningBranchReassignmentVO.getCode());
					paramMap.put("HTQY_name", reqOwningBranchReassignmentVO.getName());
				}else if(reqOwningBranchReassignmentVO.getIsThroughTrain().equals("1")){
					//直通车
					//直通车进件 	查询签约营业部下面的客服
					List<String> roleCodes = new ArrayList<String>();
					roleCodes.add("customerService");
					ResEmployeeVO effectiveEmployeeVo = loanBaseService.getEffectiveEmployeeVo(new Long(reqOwningBranchReassignmentVO.getBranchId()), roleCodes);
					if(effectiveEmployeeVo == null){
						msg += reqOwningBranchReassignmentVO.getLoanNo()+"查询可改派员工失败！";
						continue;
					}else{
						paramMap.put("HTQY", reqOwningBranchReassignmentVO.getStatus());
						paramMap.put("HTQY_code", effectiveEmployeeVo.getUsercode());
						paramMap.put("HTQY_name", effectiveEmployeeVo.getName());
					}
				}else{
					msg += reqOwningBranchReassignmentVO.getLoanNo()+"操作失败，原因 isThroughTrain 为空   ";
					continue;
				}
				
			}else if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQR.getValue())){//HTQR 合同确认
//				3合同确认	
				paramMap.put("HTQR", reqOwningBranchReassignmentVO.getStatus());
				paramMap.put("HTQR_code", reqOwningBranchReassignmentVO.getCode());
				paramMap.put("HTQR_name", reqOwningBranchReassignmentVO.getName());
			}else{
				msg += reqOwningBranchReassignmentVO.getLoanNo()+"操作失败  原因，status 应该为[SQLR录入修改,HTQY合同签约,HTQR合同确认]！   ";
				continue;
			}
			paramMap.put("loanNo", reqOwningBranchReassignmentVO.getLoanNo());
			boolean bol = loanBaseService.updateOperatingStaff(paramMap);
			if(!bol){
				msg += reqOwningBranchReassignmentVO.getLoanNo()+"数据库操作失败   ";
			}else{
				//记录借款日志
				BMSSysLoanLog sysLoanLog = new BMSSysLoanLog();
				sysLoanLog.setLoanNo(reqOwningBranchReassignmentVO.getLoanNo());
				sysLoanLog.setLoanBaseId(byMap.get(0).getId());
				if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQR.getValue())||reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQY.getValue())){
				sysLoanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
				}else{
				sysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
				}
				sysLoanLog.setRtfState(byMap.get(0).getRtfState());
				sysLoanLog.setRtfNodeState(byMap.get(0).getRtfNodeState());
				sysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
				sysLoanLog.setOperator(reqOwningBranchReassignmentVO.getServiceName());
				sysLoanLog.setOperatorCode(reqOwningBranchReassignmentVO.getServiceCode());
				sysLoanLog.setOperationTime(new Date());
				sysLoanLog.setRemark("门店改派环节，改派给:"+reqOwningBranchReassignmentVO.getName());
				sysLoanLogService.saveOrUpdate(sysLoanLog);
				
				
				//改派成功，合同确认环节需要更新流程
				if(reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQR.getValue())||reqOwningBranchReassignmentVO.getStatus().equals(EnumConstants.RtfState.HTQY.getValue())){
					Task task=taskService.findTaskByLoanBaseId(String.valueOf(byMap.get(0).getId()));
					if(task !=null){
						taskService.changeTaskAssigneeByLoanBaseId(byMap.get(0).getId()+"", reqOwningBranchReassignmentVO.getCode());
					}
					
				}
			}
		}
		
		Response<Object> res = new Response<Object>();
		res.setRepCode(msg.equals("")?"000000":"100001");
		res.setRepMsg(msg.equals("")?"成功":msg);
		return res;
	}

	@Override
	public Response<List<ContactInfoVO>> queryContactInfo(ReqContactInfoVO contactInfoVO) {
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanBaseId())
				&& org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanBaseId | loanNo"});
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanBaseId())){
			paramMap.put("loanBaseId", contactInfoVO.getLoanBaseId());
		}
		if(!org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanNo())){
			paramMap.put("loanNo", contactInfoVO.getLoanNo());
		}
		
		List<APPContactInfoEntity> ContactInfoEntityList = appContactInfoService.listBy(paramMap);
		if(ContactInfoEntityList == null || ContactInfoEntityList.size() == 0){
			throw new BizException(CoreErrorCode.DB_LIST_IS_NULL);
		}
		List<ContactInfoVO> listVo = new ArrayList<ContactInfoVO>();
		for (APPContactInfoEntity ContactInfoEntity : ContactInfoEntityList) {
			ContactInfoVO co = new ContactInfoVO();
			BeanUtils.copyProperties(ContactInfoEntity, co);
			listVo.add(co);
		}
		
		Response<List<ContactInfoVO>> response = new Response<List<ContactInfoVO>>();
		response.setData(listVo);
		return response;
	}

	@Override
	public Response<Object> updateContactInfo(ReqContactInfoVO contactInfoVO) {
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanBaseId())
				&& org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanBaseId | loanNo"});
		}
		
		if(contactInfoVO.getSequenceNum() == null || contactInfoVO.getSequenceNum() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"sequenceNum"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getType())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"type"});
		}
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getVersion())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
		}
		
		Map<String, Object> versionMap = new HashMap<String, Object>();
		versionMap.put("loanNo",  contactInfoVO.getLoanNo());
		versionMap.put("version", contactInfoVO.getVersion());
		long updateVersion = loanBaseService.updateVersion(versionMap);
		if(updateVersion <= 0){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanBaseId())){
			paramMap.put("loanBaseId", contactInfoVO.getLoanBaseId());
		}
		if(!org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanNo())){
			paramMap.put("loanNo", contactInfoVO.getLoanNo());
		}
		paramMap.put("sequenceNum", contactInfoVO.getSequenceNum());
		
		List<APPContactInfoEntity> ContactInfoEntityList = appContactInfoService.listBy(paramMap);
		if(ContactInfoEntityList == null 
				|| ContactInfoEntityList.size() == 0
				|| ContactInfoEntityList.size() > 1){
			throw new BizException(CoreErrorCode.DB_LIST_IS_NULL);
		}
		APPContactInfoEntity appContactInfoEntity = new APPContactInfoEntity();
		
		if(!StringUtils.isBlank(contactInfoVO.getContactName())){
			appContactInfoEntity.setContactName(contactInfoVO.getContactName());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactRelation())){
			appContactInfoEntity.setContactRelation(contactInfoVO.getContactRelation());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContacGender())){
			appContactInfoEntity.setContacGender(contactInfoVO.getContacGender());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactCellPhone())){
			appContactInfoEntity.setContactCellPhone(contactInfoVO.getContactCellPhone());
		}
		
		if(!StringUtils.isBlank(contactInfoVO.getIfKnowLoan())){
			appContactInfoEntity.setIfKnowLoan(contactInfoVO.getIfKnowLoan());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactEmpName())){
			appContactInfoEntity.setContactEmpName(contactInfoVO.getContactEmpName());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactCorpPost())){
			appContactInfoEntity.setContactCorpPost(contactInfoVO.getContactCorpPost());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactCorpPhone())){
			appContactInfoEntity.setContactCorpPhone(contactInfoVO.getContactCorpPhone());
		}
		
		if(!StringUtils.isBlank(contactInfoVO.getContactCellPhone_1())){
			appContactInfoEntity.setContactCellPhone_1(contactInfoVO.getContactCellPhone_1());
		}
		if(!StringUtils.isBlank(contactInfoVO.getContactCorpPhone_1())){
			appContactInfoEntity.setContactCorpPhone_1(contactInfoVO.getContactCorpPhone_1());
		}
		
		appContactInfoEntity.setModifier(contactInfoVO.getServiceCode()==null?"updateContactInfo":contactInfoVO.getServiceCode());
		appContactInfoEntity.setModifierId(new Long("1"));
		appContactInfoEntity.setModifiedTime(new Date());
		appContactInfoEntity.setId(ContactInfoEntityList.get(0).getId());
		if(contactInfoVO.getType().equals("0")){
			appContactInfoService.updateContactInfoByAudit(appContactInfoEntity);
		}else if(contactInfoVO.getType().equals("1")){
			appContactInfoService.deleteContactInfoByAudit(appContactInfoEntity);
		}else{
			throw new BizException(CoreErrorCode.VALIDATE_ILLEGAL_MULTIVALUE, new Object[]{"type","0修改|1删除"});
		}
		Response<Object> response = new Response<Object>();
		return response;
	}

	@Override
	public Response<Object> insertContactInfo(ReqContactInfoVO contactInfoVO) {
		Response<Object> response = new Response<Object>();
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if(org.apache.commons.lang.StringUtils.isBlank(contactInfoVO.getVersion())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"version"});
		}
		
		Map<String, Object> versionMap = new HashMap<String, Object>();
		versionMap.put("loanNo",  contactInfoVO.getLoanNo());
		versionMap.put("version", contactInfoVO.getVersion());
		long updateVersion = loanBaseService.updateVersion(versionMap);
		if(updateVersion <= 0){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		
		Integer sequenceNum = 0;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", contactInfoVO.getLoanNo());
		List<APPContactInfoEntity> ContactInfoEntityList = appContactInfoService.listBy(paramMap);
		if(ContactInfoEntityList == null || ContactInfoEntityList.size() == 0){
			sequenceNum = 1;
		}else{
			for (int i = 0; i < ContactInfoEntityList.size(); i++) {
				if(ContactInfoEntityList.get(i).getSequenceNum() > sequenceNum){
					sequenceNum = ContactInfoEntityList.get(i).getSequenceNum();
				}
			}
		}
		contactInfoVO.setSequenceNum(sequenceNum+1);
		APPContactInfoEntity contactInfoEntity = new APPContactInfoEntity();
		BeanUtils.copyProperties(contactInfoVO, contactInfoEntity);
		
		//联系人
		Response<Object> responseContactInfoEntityList = Validate.getInstance().validate(contactInfoEntity);
		if (!responseContactInfoEntityList.isSuccess()) {
			return responseContactInfoEntityList;
		}
		
		
		Map<String, Object> paramQueryMap = new HashMap<String, Object>();
		paramQueryMap.put("contactName", contactInfoEntity.getContactName());
		paramQueryMap.put("contactRelation", contactInfoEntity.getContactRelation());
		paramQueryMap.put("loanNo", contactInfoVO.getLoanNo());
		List<APPContactInfoEntity> listBy = appContactInfoService.listBy(paramQueryMap);
		if(listBy == null || listBy.size() == 0){
			contactInfoEntity.setOrg(EnumConstants.BMS_Org); 
			contactInfoEntity.setCreatedTime(new Date());
			contactInfoEntity.setCreator(contactInfoVO.getServiceCode()); 
			contactInfoEntity.setCreatorId(new Long("0")); 
			contactInfoEntity.setLoanBaseId(ContactInfoEntityList.get(0).getLoanBaseId());
			contactInfoEntity.setPersonId(ContactInfoEntityList.get(0).getPersonId());
			appContactInfoService.saveOrUpdate(contactInfoEntity);
			response.setData(contactInfoVO.getSequenceNum());
		}else{
			if(listBy.size() > 1){
				response.setRepCode("999999");
				response.setRepMsg("根据此名称，和关系，查出个数为【"+listBy.size()+"】");
				return response;
			}
			
			if(listBy.get(0).getContactCellPhone() != null && listBy.get(0).getContactCellPhone_1()!=null){
				response.setRepCode("999999");
				response.setRepMsg("已拥有该用户，并且两个手机号码都不为空");
				return response;
			}
			
			if(listBy.get(0).getContactCorpPhone() != null && listBy.get(0).getContactCorpPhone_1()!=null){
				response.setRepCode("999999");
				response.setRepMsg("已拥有该用户，并且两个公司号码都不为空");
				return response;
			}
			
			APPContactInfoEntity updateInfoEntity = new APPContactInfoEntity();
			updateInfoEntity.setContactCellPhone_1(contactInfoVO.getContactCellPhone());
			updateInfoEntity.setContactCorpPhone_1(contactInfoVO.getContactCorpPhone());
			updateInfoEntity.setId(listBy.get(0).getId());
			appContactInfoService.saveOrUpdate(updateInfoEntity);
		}
		return response;
	}

	@Override
	public Response<Object> updateUserPhone(ReqApplyPhoneVO reqApplyPhoneVO) {
		Response<Object> response = new Response<Object>();
		if(StringUtils.isEmpty(reqApplyPhoneVO.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if(StringUtils.isEmpty(reqApplyPhoneVO.getCellphone())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"cellphone"});
		}
		
		if(StringUtils.isEmpty(reqApplyPhoneVO.getCorpPhone())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"corpPhone"});
		}
		
		if(StringUtils.isEmpty(reqApplyPhoneVO.getCellphoneSec())){
			reqApplyPhoneVO.setCellphoneSec(null);
		}
		
		if(StringUtils.isEmpty(reqApplyPhoneVO.getCorpPhoneSec())){
			reqApplyPhoneVO.setCorpPhoneSec(null);
		}
		
		APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
		BeanUtils.copyProperties(reqApplyPhoneVO, appPersonInfoEntity);
		
		Long i = appPersonInfoService.updateUserPhone(appPersonInfoEntity);
		if(i == null || i == 0){
			response.setRepCode("999999");
			response.setRepMsg("更新失败");
			return response;
		}
		return response;
	}
	
	
}
