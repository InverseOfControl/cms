package com.ymkj.cms.biz.service.audit.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.bds.biz.api.enums.Constants;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.service.IGreyListExecuter;
import com.ymkj.bds.biz.api.vo.request.BlackGreyReqVO;
import com.ymkj.bds.biz.api.vo.request.RemoveReqVo;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.dao.audit.BMSQualityInspectionDao;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;
import com.ymkj.cms.biz.entity.audit.BMSApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.BMSQualityInspectionEntity;
import com.ymkj.cms.biz.entity.audit.first.ApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPContactInfoService;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;
import com.ymkj.cms.biz.service.apply.ILoanReviewService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class BMSQualityInspectionServiceImpl extends BaseServiceImpl<BMSApplicationPartEntity>
		implements BMSQualityInspectionService {
	
	private Gson gson = new Gson();
	
	public Logger logger = LoggerFactory.getLogger(BMSQualityInspectionServiceImpl.class);
	
	@Autowired
	private BMSQualityInspectionDao bmsQualityInspectionDao;
	@Autowired
	private BMSFirstAuditDao firstAuditDao;
	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private LoanBaseService loanBaseService;
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	@Autowired
	private LoanExtService loanExtService;
	@Autowired
	private IGreyListExecuter greyListExecuter;
	@Autowired
	private IBlackListExecuter blackListExecuter;
	@Autowired
	private APPPersonInfoService appPersonInfoService;
	@Autowired
	private APPContactInfoService appContactInfoService;
	@Autowired
	private IBMSOrgLimitChannelService orgLimitChannelService;
	@Autowired
	private IBlackListExecuter iBlackListExecuter;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private ILoanReviewService loanReviewService;
	
	@Override
	protected BaseDao<BMSApplicationPartEntity> getDao() {
		// TODO Auto-generated method stub
		return bmsQualityInspectionDao;
	}

	@Override
	public List<BMSQualityInspectionEntity> queryQualityInsInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bmsQualityInspectionDao.queryQualityInsInfo(map);
	}
	
	
	public PageBean<BMSApplicationPartEntity> listPageEntrySearch(PageParam pageParam,Map<String, Object> paramMap){
		return bmsQualityInspectionDao.listPageEntrySearch(pageParam, paramMap);
	}

	@Override
	public boolean checkLoanIsNowReject(Map<String, Object> map) {
		boolean flag = true;
		int result = bmsQualityInspectionDao.checkLoanIsNowReject(map);
		if (result > 0) {
			return flag;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateApplicationPartInfo(ReqApplicationPartUpdVO reqApplicationPartUpdVO) {
		boolean flag = true;
		BMSSysLoanLog bmsSysLoanLog = null;
		Map<String, Object> map = new HashMap<String, Object>();
		// 更新申请主表状态
		map.put("loanNo", reqApplicationPartUpdVO.getLoanNo());
		map.put("version", reqApplicationPartUpdVO.getVersion());
		int result4 = firstAuditDao.updLoanBase(map);
		// 插入借款日志表
		/*bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		bmsSysLoanLog.setLoanNo(reqApplicationPartUpdVO.getLoanNo());
		bmsSysLoanLog.setStatus(reqApplicationPartUpdVO.getStatus());
		bmsSysLoanLog.setRtfState(reqApplicationPartUpdVO.getRtfState());
		bmsSysLoanLog.setRtfNodeState(reqApplicationPartUpdVO.getRtfNodeState());
		bmsSysLoanLog.setOperatorCode(reqApplicationPartUpdVO.getOperatorCode());
		bmsSysLoanLog.setFirstLevleReasons(reqApplicationPartUpdVO.getFirstLevelReasons());
		bmsSysLoanLog.setTwoLevleReasons(reqApplicationPartUpdVO.getTwoLevelReasons());
		bmsSysLoanLog.setFirstLevleReasonsCode(reqApplicationPartUpdVO.getFirstLevelReasonsCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(reqApplicationPartUpdVO.getTwoLevelReasonsCode());
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		Long result = bmsSysLoanLogDao.insert(bmsSysLoanLog);*/
		//系统日志
		int result=ibmsSysLogService.recordSysLog(reqApplicationPartUpdVO, "审核系统|申请件维护|update|null","接口名称:updateApplicationInfo","申请件维护更新"+reqApplicationPartUpdVO.getRemark());
		if (reqApplicationPartUpdVO.getFlag().equals(EnumConstants.applicationUpdFlag.PASS_UPD.getValue())) {// 通过件修改
			// 更新审核信息表
			map.put("accLmt", reqApplicationPartUpdVO.getAccLmt());
			map.put("accTime", reqApplicationPartUpdVO.getAccTerm());
			int result2 = firstAuditDao.updAuditNo(map);
			if(result>0 && result2>0 && result4>0){
				 flag=true;
			}else{
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}
		if(reqApplicationPartUpdVO.getFlag().equals(EnumConstants.applicationUpdFlag.PASS_REJECT.getValue())||
			reqApplicationPartUpdVO.getFlag().equals(EnumConstants.applicationUpdFlag.REJECT_UPD.getValue())){//拒绝件修改
			//更新借款扩展表
			map.put("firstLevelReasons", reqApplicationPartUpdVO.getFirstLevelReasons());
			map.put("twoLevelReasons", reqApplicationPartUpdVO.getTwoLevelReasons());
			map.put("firstLevelReasonCode", reqApplicationPartUpdVO.getFirstLevelReasonsCode());
			map.put("twoLevelReasonCode", reqApplicationPartUpdVO.getTwoLevelReasonsCode());
			int result3 = firstAuditDao.updLoanAdExt(map);
			if(result>0 && result3>0 && result4>0){
				 flag=true;
			}else{
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}
		return flag;
	}

	@Override
	public void updateApplicationPartInfoNew(ApplicationPartEntity applicationPartEntity) {
		Integer resUp = 0;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", applicationPartEntity.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(paramMap);
		if(by == null){
			throw new BizException(CoreErrorCode.DB_SELECTONE_IS_NULL);
		}
		
		if(!by.getVerson().equals(applicationPartEntity.getVersion()) ){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		
//		1.终审通过件拒绝  2,通过件修改数据 3 拒绝件修改拒绝原因
		if(applicationPartEntity.getFlag() == 1){
			if(!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSPASS.getValue())
					&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.HTQYASSIGN.getValue())){
				throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
			}
			
			paramMap.put("rtfNodeState",EnumConstants.RtfNodeState.SQJWH_REJECT.getValue());
			paramMap.put("status",EnumConstants.LoanStatus.REFUSE.getValue());
			paramMap.put("refustDate",DateUtil.defaultFormatDate(new Date()));
			resUp = firstAuditDao.updAuditNo(paramMap);
			if(resUp == 0){
				throw new BizException(BizErrorCode.EOERR, new Object[]{"更新 Audit 表异常"} );
			}
		}else if(applicationPartEntity.getFlag() == 2){
			paramMap.put("accLmt", applicationPartEntity.getAccLmt());
			paramMap.put("accTime", applicationPartEntity.getAccTerm());
			resUp = firstAuditDao.updAuditNo(paramMap);
			if(resUp == 0){
				throw new BizException(BizErrorCode.EOERR, new Object[]{"更新 Audit 表异常"} );
			}
		}else{
			if(!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.CSFPREJECT.getValue())
					&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSCSREJECT.getValue())
					&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())
					&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.SQJWH_REJECT.getValue())
					&& !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.HTQYREJECT.getValue())){
				throw new BizException(BizErrorCode.RTF_NOT_STATUS_EOERR);
			}
			//删除灰名单
			removeBlackListId(applicationPartEntity.getLoanNo());
			
		}
		paramMap.put("version", applicationPartEntity.getVersion());
		resUp = firstAuditDao.updLoanBase(paramMap);
		if(resUp == 0){
			throw new BizException(BizErrorCode.EOERR, new Object[]{"更新 loanBase 表异常"} );
		}
		
		if(applicationPartEntity.getFlag() != 2){
			//更新借款扩展表
			paramMap.put("firstLevelReasons", applicationPartEntity.getFirstLevelReasons());
			paramMap.put("twoLevelReasons", applicationPartEntity.getTwoLevelReasons());
			paramMap.put("firstLevelReasonCode", applicationPartEntity.getFirstLevelReasonsCode());
			paramMap.put("twoLevelReasonCode", applicationPartEntity.getTwoLevelReasonsCode());
			
			if(applicationPartEntity.getFlag() == 1){
				paramMap.put("rejectPersonName", applicationPartEntity.getOperatorName());
				paramMap.put("rejectPersonCode", applicationPartEntity.getOperatorCode());
			}
			
			//原因是否添加黑名单
			BlackGreyVO blackGreyVO = new BlackGreyVO();
			BeanUtils.copyProperties(applicationPartEntity, blackGreyVO);
			
			ResReassignmentUpdVO ifSaveBlackGrey = ifSaveBlackGrey(blackGreyVO);
			if(ifSaveBlackGrey.isIfSuccessful()){
				paramMap.put("blackList", ifSaveBlackGrey.getMsg());
			}
			
			resUp = firstAuditDao.updLoanAdExtReject(paramMap);
			if(resUp == 0){
				throw new BizException(BizErrorCode.EOERR, new Object[]{"更新 loanExt 表异常"} );
			}
			LoanReviewEntity loanreview = loanReviewService.selectByLoanNo(applicationPartEntity.getLoanNo());
			if(loanreview!=null){
				loanReviewService.updateReasonByNo(paramMap);
			}
		}
		
		if(applicationPartEntity.getFlag() != 2){
			insertBmsLoanLog(applicationPartEntity, by);
		}
		
	}
	
	public void removeBlackListId(String loanNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		LoanExtEntity loanExt = loanExtService.getBy(paramMap);
		if(!StringUtils.isEmpty(loanExt.getBlackListId())){
			logger.info("-------扩展表存在黑名单ID["+loanExt.getBlackListId()+"]，调用行为库删除----------");
			RemoveReqVo bdsReq = new RemoveReqVo();
			bdsReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			bdsReq.setRemoveId(loanExt.getBlackListId());
			Response<Object> bdsResponse = iBlackListExecuter.removeBlackGreyList(bdsReq);
			if (bdsResponse.isSuccess()) {
				logger.info("-------删除行为库灰名单成功----------");
			} else {
				logger.info("-------["+loanNo+"]删除行为库灰名单失败----------"+bdsResponse.getRepMsg());
			}
			
			//不管成功不成功都删除先
			firstAuditDao.updLoanAdExtIsBlackNull(paramMap);
		}
	}
	
	public void insertBmsLoanLog(ApplicationPartEntity applicationPartEntity,LoanBaseEntity by){
		logger.info("这边进来了，申请件维护"+gson.toJson(by));
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setLoanBaseId(by.getId());
		bmsSysLoanLog.setLoanNo(by.getLoanNo());
		bmsSysLoanLog.setOperatorCode(applicationPartEntity.getOperatorCode());
		bmsSysLoanLog.setOperator(applicationPartEntity.getOperatorName());
		bmsSysLoanLog.setFirstLevleReasons(applicationPartEntity.getFirstLevelReasons());
		bmsSysLoanLog.setTwoLevleReasons(applicationPartEntity.getTwoLevelReasons());
		bmsSysLoanLog.setFirstLevleReasonsCode(applicationPartEntity.getFirstLevelReasonsCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(applicationPartEntity.getTwoLevelReasonsCode());
		bmsSysLoanLog.setOperationTime(new Date());
		bmsSysLoanLog.setOperationModule(EnumConstants.OptionModule.MAINTAIN_APPLY_MODIFY.getValue());
		bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_XSZS_MAINTAIN.getValue());
		
//		1.终审通过件拒绝  2,通过件修改数据 3 拒绝件修改拒绝原因
		if(applicationPartEntity.getFlag() == 1){
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.SQJXXWH.getValue());
			bmsSysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.SQJWH_REJECT.getValue());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
			bmsSysLoanLog.setRemark("申请件维护拒绝");
			logger.info("这边走的是第一个了"+gson.toJson(bmsSysLoanLog));
		}else if(applicationPartEntity.getFlag() == 2){
			bmsSysLoanLog.setStatus(by.getState());
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.SQJXXWH.getValue());
			bmsSysLoanLog.setRtfNodeState(by.getRtfNodeState());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_XSZS_MAINTAIN.getValue());
			bmsSysLoanLog.setRemark("修改申请金额:["+applicationPartEntity.getAccLmt()+"]和期限:["+applicationPartEntity.getAccTerm()+"]");
			logger.info("这边走的是第二个了"+gson.toJson(bmsSysLoanLog));
		}else{
			//bmsSysLoanLog.setStatus(by.getState());
			bmsSysLoanLog.setStatus(by.getStatus());
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.SQJXXWH.getValue());
			bmsSysLoanLog.setRtfNodeState(by.getRtfNodeState());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_XSZS_MAINTAIN.getValue());
			bmsSysLoanLog.setRemark("修改拒绝原因!");
			logger.info("这边走的是第三个了"+gson.toJson(bmsSysLoanLog));
		}
		bmsSysLoanLogDao.insert(bmsSysLoanLog);
	}

	@Override
	public ResReassignmentUpdVO ifSaveBlackGrey(BlackGreyVO blackGreyVO) {
		ResReassignmentUpdVO res = new ResReassignmentUpdVO();
		res.setLoanNo(blackGreyVO.getLoanNo());
		
		boolean ifBlackGrey = false;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", blackGreyVO.getLoanNo());
		LoanBaseEntity by = loanBaseService.getBy(paramMap);
		if(by == null){
			res.setIfSuccessful(false);
			res.setMsg("根据LoanNo["+blackGreyVO.getLoanNo()+"]查询数据为null");
			return res;
		}
		
		//原因数据
		BMSTMReasonEntity firstLevel = null;
		BMSTMReasonEntity twoLevel = null;
		if(blackGreyVO.getFirstLevelReasonsCode() != null){
			paramMap.put("code", blackGreyVO.getFirstLevelReasonsCode());
			firstLevel = iBMSTMReasonService.getBy(paramMap);
			if(firstLevel==null){
				res.setIfSuccessful(false);
				res.setMsg("根据FirstLevelReasonsCode查询数据为null");
				return res;
			}
		}
		if(!StringUtils.isEmpty(blackGreyVO.getTwoLevelReasonsCode())){
			paramMap.put("code", blackGreyVO.getTwoLevelReasonsCode());
			twoLevel = iBMSTMReasonService.getBy(paramMap);
		}
		if(twoLevel != null && twoLevel.getConditionType() != null && twoLevel.getConditionType().indexOf("isBlackList") != -1){
			ifBlackGrey = true;
		}
		
		if(twoLevel == null && firstLevel != null && firstLevel.getConditionType() != null && firstLevel.getConditionType().indexOf("isBlackList") != -1){
			ifBlackGrey = true;
		}
		
		
		if(ifBlackGrey){
			BlackGreyReqVO black = new BlackGreyReqVO();
			black.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			black.setListSrc((blackGreyVO.getType()!=null&&blackGreyVO.getType().equals("1"))?Constants.SourceStatus._00001.getCode():Constants.SourceStatus._00002.getCode());
			black.setIdNo(by.getIdNo());
			black.setName(by.getName());
			black.setBlackReason(firstLevel.getReason()+""+(twoLevel==null?"":"-"+twoLevel.getReason()));
			black.setGreyReason(twoLevel==null?"":twoLevel.getReason());
			if(black.getListSrc().equals(Constants.SourceStatus._00001.getCode())){
				ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
				reqEmployeeVO.setSysCode("BMS");
				reqEmployeeVO.setUsercode(blackGreyVO.getOperatorCode());
				Response<List<ResOrganizationVO>> response=	iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);
				List<ResOrganizationVO> resOrganizationVOList=	response.getData();
				if(CollectionUtils.isEmpty(resOrganizationVOList)){
					logger.info("queryMessageCount：【查询组织机构信息为空！】");
				}else{
					ResOrganizationVO resOrganizationVO = resOrganizationVOList.get(0);
					black.setCreateUser(resOrganizationVO.getOrgCode());
				}
			}else{
				black.setCreateUser(blackGreyVO.getOperatorCode());	
			}

			black.setCellphone(getCellphone(blackGreyVO.getLoanNo()));

			logger.info("["+ blackGreyVO.getLoanNo()+"]往行为库添加会黑名单："+gson.toJson(black));
			Response<Object> xingWeiResponse = blackListExecuter.saveBlackGreyList(black);
			logger.info("["+ blackGreyVO.getLoanNo()+"]往行为库添加会黑名单返回值："+gson.toJson(xingWeiResponse));
			if(xingWeiResponse.isSuccess()){
				//保存灰名单
				paramMap.put("blackList", xingWeiResponse.getData().toString());
				res.setMsg(xingWeiResponse.getData().toString());
				res.setIfSuccessful(ifBlackGrey);
				return res;
			}else{
				logger.info("["+blackGreyVO.getLoanNo()+"]将黑名单存入行为库错误：原因："+xingWeiResponse.getRepMsg());
				res.setIfSuccessful(false);
				res.setMsg("["+blackGreyVO.getLoanNo()+"]将黑名单存入行为库错误：原因："+xingWeiResponse.getRepMsg());
				return res;
			}
		}
		return res;
	}
	
	/**
	 * 获取所有手机号码
	 * @param loanNo
	 * @return
	 */
	public String getCellphone(String loanNo){
		String res = "";
		
		List<String> list = new ArrayList<String>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		/** 获取个人信息手机号码 **/
		APPPersonInfoEntity by = appPersonInfoService.getBy(paramMap);
		if(by != null){
			if(!StringUtils.isEmpty(by.getCellphone())){
				list.add(by.getCellphone());
			}
			if(!StringUtils.isEmpty(by.getCellphoneSec())){
				list.add(by.getCellphoneSec());
			}
		}
		
//		/** 获取联系人手机号码 **/
//		List<APPContactInfoEntity> listBy = appContactInfoService.listBy(paramMap);
//		if(listBy != null && listBy.size() != 0){
//			for(APPContactInfoEntity tactInfo : listBy){
//				if(!StringUtils.isEmpty(tactInfo.getContactCellPhone())){
//					list.add(tactInfo.getContactCellPhone());
//				}
//				if(!StringUtils.isEmpty(tactInfo.getContactCellPhone_1())){
//					list.add(tactInfo.getContactCellPhone_1());
//				}
//			}
//		}
		if(list.size() != 0){
			for (String phone : list) {
				res += ","+phone;
			}
			res = res.substring(1);
		}
		return res;
	}

	/**
	 * 审核产品期限校验
	 */
	public boolean auditCheckProductSales(String loanNo) {
		boolean resBoolean = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", loanNo);
		EntrySearchEntity loanBase = (EntrySearchEntity) loanBaseService.getBy(map, "queryAuditProduct");
		if(loanBase == null){
			logger.info("根据借款编号未查询到借款信息");
			return resBoolean;
		}
		
		// 网点，产品，产品期限可用校验
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("orgId", loanBase.getOwningBranchId());
		paras.put("productCode", loanBase.getProductCd());//产品
		paras.put("applyLmt", loanBase.getAccLmt());//金额
		paras.put("auditLimit", loanBase.getAccTerm());//期限
		List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelService.listOrgProductLimitByOrgProApp(paras);
		if (orgLimitChannelList == null || orgLimitChannelList.isEmpty()) {
			logger.info("根据借款编号未查询到配置信息");
			return resBoolean;
		}else{
			resBoolean = true;
			return resBoolean;
		}
	}

}
