package com.ymkj.cms.biz.service.audit.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansAndVersionsVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansChackVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentBatchVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsPlupdateStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefusePlupdateStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefuseUpdStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqcsBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.dao.master.IBMSProductDao;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.ApplyDataManipulationService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.audit.BMSFirstAuditService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.http.impl.CreditHttpServiceImpl;
import com.ymkj.cms.biz.service.master.IBMSLoanAuditEntityService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.pms.biz.api.enums.OrganizationEnum;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResGroupVO;

/**
 * 初审接口实现类
 * 
 * @author YM10143
 *
 */
@Service
public class BMSFirstAuditServiceImpl extends BaseServiceImpl<BMSFirstAuditEntity> implements BMSFirstAuditService {
	public Logger logger = LoggerFactory.getLogger(BMSFirstAuditServiceImpl.class);
	
	
	@Autowired
	private BMSFirstAuditDao firstAuditDao;
	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	@Autowired
	private IBMSProductDao productDao;
	@Autowired
	private LoanBaseService loanBaseService;
	@Autowired
	private IBlackListExecuter blackListExecuter;
	@Autowired
	private BMSQualityInspectionService bMSQualityInspectionService;
	@Autowired
	private PMSClientService pmsClientService;
	@Autowired
	private IBMSLoanAuditEntityService iLoanAuditService;
	@Autowired
	private CreditHttpServiceImpl creditHttpServiceImpl;
	
	@Autowired
	private ApplyDataManipulationService applyDataManipulationService;
	
	@Value("#{env['queryHZEducationInfo']?:''}")
	private String queryHZEducationInfo;
	@Value("#{env['queryHZMobileOnlineInfo']?:''}")
	private String queryHZMobileOnlineInfo;
	@Value("#{env['queryHZIdCardCheckInfo']?:''}")
	private String queryHZIdCardCheckInfo;
	
	@Override
	protected BaseDao<BMSFirstAuditEntity> getDao() {
		return firstAuditDao;
	}

	@Override
	public List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstAuditDao.automaticDispatchList(map);
	}
	
	
	@Override
	public List<BMSAutomaticDispatchEntity> csAutomaticDispatchList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstAuditDao.csAutomaticDispatchList(map);
	}

	/**
	 * CS 插入借款日志表； 插入审核表 并且更新申请主表流程状态，流程节点状态； 三个一起组成一个事务
	 */
	@Override
	public boolean updateCsLoanNoState(ReqCsUpdVO reqCsUpdVO) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		Integer result4 = null;
		// 更新申请主表状态
		if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSREJECT.getValue())){
			map.put("status", EnumConstants.LoanStatus.REFUSE.getValue());
		}else{
			map.put("logoFlag",0);//退回录入,标识去除，借款前台标识,1(黄色)
			map.put("status", EnumConstants.LoanStatus.APPLY.getValue());
		}
		if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSASSIGN.getValue())){
			map.put("rtfStatus", EnumConstants.RtfState.XSCS.getValue());
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSCS.getValue());	
		}else{
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSCS.getValue());
			map.put("rtfStatus", EnumConstants.RtfState.XSCS.getValue());
		}
		map.put("loanNo", reqCsUpdVO.getLoanNo());
		map.put("rtfNodeState", reqCsUpdVO.getRtfNodeStatus());
		map.put("version", reqCsUpdVO.getVersion());
		map.put("ifNewLoanNo", reqCsUpdVO.getIfNewLoanNo());
		int result = firstAuditDao.updLoanBase(map);
		// 插入借款日志表
		bmsSysLoanLog.setLoanNo(reqCsUpdVO.getLoanNo());
		if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSREJECT.getValue())){
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
			map.put("refustDate",DateUtil.defaultFormatDate(new Date()));
		}else{
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		}
		bmsSysLoanLog.setRtfNodeState(reqCsUpdVO.getRtfNodeStatus());
		bmsSysLoanLog.setCheckNodeState(reqCsUpdVO.getCheckNodeStatus());
		bmsSysLoanLog.setFirstLevleReasons(reqCsUpdVO.getFirstLevelReasons());
		bmsSysLoanLog.setTwoLevleReasons(reqCsUpdVO.getTwoLevelReasons());
		bmsSysLoanLog.setOperatorCode(reqCsUpdVO.getOperatorCode());
		ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(reqCsUpdVO.getOperatorCode());
		ResEmployeeVO  resVo=pmsClientService.findEmployeeByCode(reqEmployeeVO);
		if(resVo!=null){
			bmsSysLoanLog.setOperator(resVo.getName());
		}else{
			bmsSysLoanLog.setOperator(reqCsUpdVO.getOperatorCode());
		}
		bmsSysLoanLog.setFirstLevleReasonsCode(reqCsUpdVO.getFirstLevelReasonCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(reqCsUpdVO.getTwoLevelReasonCode());
		bmsSysLoanLog.setCheckPersonCode(reqCsUpdVO.getcSPersonCode());
		bmsSysLoanLog.setCheckPerson(reqCsUpdVO.getcSPersonName());
		bmsSysLoanLog.setComplexPersonCode(reqCsUpdVO.getComplexPersonCode());
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		bmsSysLoanLog.setRemark(reqCsUpdVO.getRemark());
		ReqParamVO csParamVO=new ReqParamVO();
		csParamVO.setLoginUser(reqCsUpdVO.getOperatorCode());
		csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
		ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
		if(groupVo!=null){
			bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
			bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
			bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
		}
		//初审提交时查询审批额度和审批期限到日志表
		BMSLoanAudit loanAudit=iLoanAuditService.findBmsLoanAuditByLoanNo(reqCsUpdVO.getLoanNo());
		if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSREJECT.getValue())){//拒绝按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		}else if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSRETURN.getValue())){//退回按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		}else if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSHANGUP.getValue())){//挂起按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.HANHUP.getValue());
			
		}else if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSPASS.getValue())){//通过按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
			bmsSysLoanLog.setApplyLmt(loanAudit.getAccLmt());
			bmsSysLoanLog.setApplyTerm(loanAudit.getAccTerm());
		}else if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSASSIGN.getValue())){//初审自动派单
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.CS_AUTOMATIC_DISPATCH.getValue());
		}else if(reqCsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.HIGHPASS.getValue())){
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());
			bmsSysLoanLog.setApplyLmt(loanAudit.getAccLmt());
			bmsSysLoanLog.setApplyTerm(loanAudit.getAccTerm());
		}else{
			if(reqCsUpdVO.getCheckNodeStatus().equals(EnumConstants.ChcekNodeState.CHECKPASS.getValue())){//复核确认
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REVIEW_PASS.getValue());//同意
			}else{
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REVIEW_NO_PASS.getValue());//不同意
			}
		}
		Long result2 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
		// 更新审批表信息
		map.put("loanNo", reqCsUpdVO.getLoanNo());
		map.put("checkNodeStatus", reqCsUpdVO.getCheckNodeStatus());
		map.put("checkPerson", reqCsUpdVO.getcSPersonName());
		map.put("checkPersonCode", reqCsUpdVO.getcSPersonCode());
		map.put("firstSubZsDate", reqCsUpdVO.getFirstSubZsDate());
		map.put("accLmt", reqCsUpdVO.getAccLmt());
		map.put("accTime", reqCsUpdVO.getAccTime());
		map.put("accDate", reqCsUpdVO.getAccDate());
		map.put("allotDate", new Date());
		map.put("complexPersonCode", reqCsUpdVO.getComplexPersonCode());
		
		//map.put("loanNoTopClass", reqCsUpdVO.getLoanTopClass());
		int result3 = firstAuditDao.updAuditNo(map);
		// 系统日志
		int result5 = ibmsSysLogService.recordSysLog(reqCsUpdVO, "审核系统|初审更新|update|更新",
				"接口名称:updateCsLoanNoState", "初审更新");
		if (reqCsUpdVO.getFirstLevelReasons() != null || reqCsUpdVO.getTwoLevelReasons() != null) {
			// 更新借款扩展表
			map.put("firstLevelReasons", reqCsUpdVO.getFirstLevelReasons());
			map.put("twoLevelReasons", reqCsUpdVO.getTwoLevelReasons());
			map.put("firstLevelReasonCode", reqCsUpdVO.getFirstLevelReasonCode());
			map.put("twoLevelReasonCode", reqCsUpdVO.getTwoLevelReasonCode());
			
			
			/** 会黑名单校验 **/
			BlackGreyVO blackGreyVO = new BlackGreyVO();
			blackGreyVO.setLoanNo(reqCsUpdVO.getLoanNo());
			blackGreyVO.setFirstLevelReasonsCode(reqCsUpdVO.getFirstLevelReasonCode());
			blackGreyVO.setFirstLevelReasons(reqCsUpdVO.getFirstLevelReasons());
			blackGreyVO.setTwoLevelReasonsCode(reqCsUpdVO.getTwoLevelReasonCode());
			blackGreyVO.setTwoLevelReasons(reqCsUpdVO.getTwoLevelReasons());
			blackGreyVO.setOperatorCode(reqCsUpdVO.getOperatorCode());
			Map<String,Object> maps=new HashMap<String,Object>();
			maps.put("loanNo", reqCsUpdVO.getLoanNo());
			InformationEntity infomation=loanBaseService.queryInformationEntity(maps);
			if(infomation.getCheckNodeState().equals("NO_CHECK")){	
				ResReassignmentUpdVO ifSaveBlackGrey = bMSQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
				if(ifSaveBlackGrey.isIfSuccessful()){
					//保存灰名单
					map.put("blackList", ifSaveBlackGrey.getMsg());
				}else{
					logger.info("["+reqCsUpdVO.getLoanNo()+"]将黑名单存入行为库错误：原因："+ifSaveBlackGrey.getMsg());
				}
			}			
			result4 = firstAuditDao.updLoanAdExt(map);
			if (result > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		} else {
			if (result > 0 && result2 > 0 && result3 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}
		return flag;
	}
	
	
	
	
	
	
	

	/**
	 * ZS 插入借款日志表； 插入审核表 并且更新申请主表流程状态，流程节点状态； 三个一起组成一个事务
	 */
	@Override
	public boolean updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer result4 = null;
		// 更新申请主表状态
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())){
			map.put("status", EnumConstants.LoanStatus.REFUSE.getValue());
		}else{
			map.put("status", EnumConstants.LoanStatus.APPLY.getValue());
		}
		map.put("rtfStatus", EnumConstants.RtfState.XSZS.getValue());
		map.put("loanNo", reqZsUpdVO.getLoanNo());
		map.put("rtfNodeState", reqZsUpdVO.getRtfNodeStatus());
		map.put("version", reqZsUpdVO.getVersion());
		map.put("zSIfNewLoanNo", reqZsUpdVO.getzSIfNewLoanNo());
		int result = firstAuditDao.updLoanBase(map);
		// 插入借款日志表
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setLoanNo(reqZsUpdVO.getLoanNo());
		/**
		 * 如果是拒绝按钮修改流程节点状态的同时借款状态也要修改成拒绝
		 */
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())){
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
			map.put("refustDate",DateUtil.defaultFormatDate(new Date()));
		}else{
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
		}
		bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSZS.getValue());
		bmsSysLoanLog.setRtfNodeState(reqZsUpdVO.getRtfNodeStatus());
		bmsSysLoanLog.setFirstLevleReasons(reqZsUpdVO.getFirstLevelReasons());
		bmsSysLoanLog.setTwoLevleReasons(reqZsUpdVO.getTwoLevelReasons());
		bmsSysLoanLog.setOperatorCode(reqZsUpdVO.getOperatorCode());
		bmsSysLoanLog.setOperator(reqZsUpdVO.getOperatorCode());
		bmsSysLoanLog.setFirstLevleReasonsCode(reqZsUpdVO.getFirstLevelReasonCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(reqZsUpdVO.getTwoLevelReasonCode());
		bmsSysLoanLog.setFinalPersonCode(reqZsUpdVO.getzSPersonCode());
		bmsSysLoanLog.setApppovalPersonCode(reqZsUpdVO.getApppovalPersonCode());
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		bmsSysLoanLog.setRemark(reqZsUpdVO.getRemark());
		ReqParamVO csParamVO=new ReqParamVO();
		csParamVO.setLoginUser(reqZsUpdVO.getOperatorCode());
		csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
		ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
		if(groupVo!=null){
			bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
			bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
			bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
		}
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())){//拒绝按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())
				||reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())){//退回按钮或退回初审
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSHANGUP.getValue())){//挂起按钮
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.HANHUP.getValue());
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSASSIGN.getValue())){//终审自动派单
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_AUTOMATIC_DISPATCH.getValue());
		}else{
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.PASS.getValue());
		}
		Long result2 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
		// 更新审批表信息
		map.put("loanNo", reqZsUpdVO.getLoanNo());
		map.put("finalPersonCode", reqZsUpdVO.getzSPersonCode());
		map.put("apppovalPersonCode", reqZsUpdVO.getApppovalPersonCode());
		map.put("accLmt", reqZsUpdVO.getAccLmt());
		map.put("accTime", reqZsUpdVO.getAccTime());
		map.put("accDate", reqZsUpdVO.getAccDate());
		map.put("finalAllotDate",  new Date());
		//map.put("loanNoTopClass", reqZsUpdVO.getLoanNoTopClass());
		int result3 = firstAuditDao.updAuditNo(map);
		// 系统日志
		int result5 = ibmsSysLogService.recordSysLog(reqZsUpdVO, "审核系统|终审更新|update|更新",
			"接口名称:updateZsLoanNoState", "终审更新");
		if (reqZsUpdVO.getFirstLevelReasons() != null || reqZsUpdVO.getTwoLevelReasons() != null) {
			map.put("firstLevelReasons", reqZsUpdVO.getFirstLevelReasons());
			map.put("twoLevelReasons", reqZsUpdVO.getTwoLevelReasons());
			map.put("firstLevelReasonCode", reqZsUpdVO.getFirstLevelReasonCode());
			map.put("twoLevelReasonCode", reqZsUpdVO.getTwoLevelReasonCode());
			map.put("blackList", reqZsUpdVO.getBlackList());
			result4 = firstAuditDao.updLoanAdExt(map);
			if (result > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		} else {
			if (result > 0 && result2 > 0 && result3 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}
		return flag;
	}
	
	
	@Override
	public List<ResReassignmentUpdVO> updateReassignmentNew(ReqBMSReassignmentBatchVo reqBMSReassignmentUpdVo) {
		List<ResReassignmentUpdVO> listRes = new ArrayList<ResReassignmentUpdVO>();
		
		for (ReqBMSLoansChackVO reqBMSLoansAndVersionsVO : reqBMSReassignmentUpdVo.getList()) {
			// 系统日志
			ibmsSysLogService.recordSysLog(reqBMSReassignmentUpdVo, "审核系统|信审改派|update|更新","接口名称:reassignmentUpd", "批量改派更新");
			
			
			ResReassignmentUpdVO res = new ResReassignmentUpdVO();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanNo", reqBMSLoansAndVersionsVO.getLoanNo());
			LoanBaseEntity by = loanBaseService.getBy(map);
			if(by == null){
				res.setIfSuccessful(false);
				res.setMsg("根据借款编号["+reqBMSLoansAndVersionsVO.getLoanNo()+"]，查询出数据为null，请联系管理员");
				res.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
				listRes.add(res);
				continue;
			}
			if(!by.getVerson().equals(reqBMSLoansAndVersionsVO.getVersion())){
				res.setIfSuccessful(false);
				res.setMsg("根据借款编号["+reqBMSLoansAndVersionsVO.getLoanNo()+"]，当前版本已更新，非最新数据");
				res.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
				listRes.add(res);
				continue;
			}
			
			
			map.put("version", reqBMSLoansAndVersionsVO.getVersion());
			map.put("rtfStatus", EnumConstants.RtfState.XSCS.getValue());
			map.put("rtfNodeState", EnumConstants.RtfNodeState.XSCSASSIGN.getValue());
			firstAuditDao.updLoanBase(map);
			
			
			map.put("checkPerson", reqBMSReassignmentUpdVo.getAuditPersonName());
			map.put("checkPersonCode", reqBMSReassignmentUpdVo.getAuditPersonCode());
			map.put("checkNodeStatus", EnumConstants.ChcekNodeState.NOCHECK.getValue());
			firstAuditDao.updAuditNo(map);
			
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSCS.getValue());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
			bmsSysLoanLog.setCheckPersonCode(reqBMSReassignmentUpdVo.getAuditPersonCode());
			if(StringUtils.isEmpty(reqBMSLoansAndVersionsVO.getOldAuditPersonName())){
				//bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原初审人员:null改派为:"+reqBMSReassignmentUpdVo.getAuditPersonName());
				bmsSysLoanLog.setRemark("由null改派至"+reqBMSReassignmentUpdVo.getAuditPersonName());
			}else{
				ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
				reqEmployeeVO.setUsercode(reqBMSLoansAndVersionsVO.getOldAuditPersonName());
				ResEmployeeVO  resVo=pmsClientService.findEmployeeByCode(reqEmployeeVO);
				//bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原初审人员:"+resVo.getName()+"改派为:"+reqBMSReassignmentUpdVo.getAuditPersonName());
				bmsSysLoanLog.setRemark("由"+resVo.getName()+"改派至"+reqBMSReassignmentUpdVo.getAuditPersonName());
			}
			bmsSysLoanLog.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
			bmsSysLoanLog.setRtfNodeState(by.getRtfNodeState());
			bmsSysLoanLog.setOperatorCode(reqBMSReassignmentUpdVo.getOperatorCode());
			bmsSysLoanLog.setOperator(reqBMSReassignmentUpdVo.getOperatorName());
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
			ReqParamVO csParamVO=new ReqParamVO();
			csParamVO.setLoginUser(reqBMSReassignmentUpdVo.getOperatorCode());
			csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
			ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
			if(groupVo!=null){
				bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
				bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
				bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
			}
			bmsSysLoanLogDao.insert(bmsSysLoanLog);
			
			res.setIfSuccessful(true);
			res.setMsg("成功");
			res.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
			listRes.add(res);
		}
		return listRes;
	}

	/**
	 * 更新审核表审核人员code和申请主表版本号
	 */
	@Override
	public Map<String, Object> updateReassignment(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> saveLoans = new HashMap<String, Object>();
		List<Boolean> checkList = new ArrayList<Boolean>();
		List<ReqBMSLoansAndVersionsVO> loans = reqBMSReassignmentUpdVo.getList();
		int result = 0;
		int result2 = 0;
		int result3 = 0;
		long result4 = 0;
		for (ReqBMSLoansAndVersionsVO reqBMSLoansAndVersionsVO : loans) {
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			map.put("loanNo", reqBMSLoansAndVersionsVO.getLoanNo());
			map.put("version", reqBMSLoansAndVersionsVO.getVersion());
			if (reqBMSReassignmentUpdVo.getReqFlag().equals(EnumConstants.ReqFlag.CS.getValue())) {
				map.put("checkPersonCode", reqBMSReassignmentUpdVo.getAuditPersonCode());
				bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSCS.getValue());
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
				bmsSysLoanLog.setCheckPersonCode(reqBMSReassignmentUpdVo.getAuditPersonCode());
				bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原初审人员:"+reqBMSLoansAndVersionsVO.getOldAuditPersonCode()
				+"改派为:"+reqBMSReassignmentUpdVo.getAuditPersonCode());
			} else {
				map.put("finalPersonCode", reqBMSReassignmentUpdVo.getAuditPersonCode());
				bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSZS.getValue());
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
				bmsSysLoanLog.setFinalPersonCode(reqBMSReassignmentUpdVo.getAuditPersonCode());
				bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原终审人员:"+reqBMSLoansAndVersionsVO.getOldAuditPersonCode()
				+"改派为:"+reqBMSReassignmentUpdVo.getAuditPersonCode());
			}
			//插入申请主表
			if(reqBMSLoansAndVersionsVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue())){
				map.put("rtfStatus", EnumConstants.RtfState.XSCS.getValue());
				map.put("rtfNodeState", EnumConstants.RtfNodeState.XSCSASSIGN.getValue());
			}
			if(reqBMSLoansAndVersionsVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSCSPASS.getValue())
					||reqBMSLoansAndVersionsVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.HIGHPASS.getValue())){
				map.put("rtfStatus", EnumConstants.RtfState.XSZS.getValue());
				map.put("rtfNodeState", EnumConstants.RtfNodeState.XSZSASSIGN.getValue());
			}
			// 插入借款日志表
			bmsSysLoanLog.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
			bmsSysLoanLog.setRtfNodeState(reqBMSLoansAndVersionsVO.getRtfNodeStatus());
			
			bmsSysLoanLog.setOperatorCode(reqBMSReassignmentUpdVo.getOperatorCode());
			bmsSysLoanLog.setOperator(reqBMSReassignmentUpdVo.getOperatorCode());
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
	
			result4 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
			result = firstAuditDao.updAuditNo(map);
			result2 = firstAuditDao.updLoanBase(map);
			// 系统日志
			result3 = ibmsSysLogService.recordSysLog(reqBMSReassignmentUpdVo, "审核系统|信审改派|update|更新",
					"接口名称:reassignmentUpd", "批量改派更新");
			if (result > 0 && result2 > 0 && result3>0 && result4>0) {
				checkList.add(true);
			} else {
				checkList.add(false);
			}
		}
		saveLoans.put("checkList", checkList);
		return saveLoans;
	}

	/**
	 * 信审产品更新
	 */
	@Override
	public boolean updateProductInfo(ReqBMProductUpdVo reqBMProductUpdVo) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", reqBMProductUpdVo.getLoanNo());
		map.put("version", reqBMProductUpdVo.getVersion());
		int result = firstAuditDao.updLoanBase(map);
		map.put("isDeleted", EnumConstants.ifNewLoanNo.NOLOANNO.getValue());
		map.put("code", reqBMProductUpdVo.getProductCd());
		BMSProduct product = productDao.getByVo(map);
		map.put("name", product.getName());
		int result2 = firstAuditDao.updBmsLoanProduct(map);
		map.put("ifCreditRecode", reqBMProductUpdVo.getIfCreditRecode());
		map.put("amoutIncome", reqBMProductUpdVo.getAmoutIncome());
		map.put("accLmt", reqBMProductUpdVo.getAccLmt());
		map.put("accTime", reqBMProductUpdVo.getAccTerm());
		map.put("accDate", reqBMProductUpdVo.getAccDate());
		int result4 = firstAuditDao.updAuditNo(map);
		// 系统日志
		int result3 = ibmsSysLogService.recordSysLog(reqBMProductUpdVo, "审核系统|信审|update|更新", "接口名称:xsProductUpd",
				"借款产品信息更新"+reqBMProductUpdVo.getRemark());
		if (result > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
			flag = true;
		} else {
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		return flag;
	}
	
	/**
	 * 初审借款产品更新
	 */
	@Override
	public boolean updateCSProductInfo(ReqcsBMProductUpdVo reqBMProductUpdVo) {
		
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", reqBMProductUpdVo.getLoanNo());
		map.put("version", reqBMProductUpdVo.getVersion());
		int result = firstAuditDao.updLoanBaseTwo(map);
		if(result==0){
			throw new BizException(BizErrorCode.VERSION_EOERR);
		}
		map.put("isDeleted", EnumConstants.ifNewLoanNo.NOLOANNO.getValue());
		map.put("code", reqBMProductUpdVo.getProductCd());
		BMSProduct product = productDao.getByVo(map);
		map.put("name", product.getName());
		int result2 = firstAuditDao.updBmsLoanProduct(map);
		map.put("ifCreditRecode", reqBMProductUpdVo.getIfCreditRecode());
		map.put("amoutIncome", reqBMProductUpdVo.getAmoutInconme());
		map.put("accLmt", reqBMProductUpdVo.getAccLmt());
		map.put("accTime", reqBMProductUpdVo.getAccTerm());
		map.put("accDate", reqBMProductUpdVo.getAccDate());
		
		updateAuditSheet(reqBMProductUpdVo);
		int result4 = firstAuditDao.updAuditNo(map);
		// 系统日志
		int result3 = ibmsSysLogService.recordSysLog(reqBMProductUpdVo, "审核系统|信审|update|更新", "接口名称:xsProductUpd",
				"借款产品信息更新");
		if (result > 0 && result2 > 0&& result3 > 0 && result4 > 0) {
			flag = true;
		} else {
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		return flag;
		
	}
	
	/**
	 * 修改审核意见表
	 * @param reqBMProductUpdVo
	 */
	public void updateAuditSheet(ReqcsBMProductUpdVo reqBMProductUpdVo){
		if(reqBMProductUpdVo.getOnlineAWithin3MonthsAddress() != null || 
				reqBMProductUpdVo.getOnlineAWithin6MonthsAddress() != null || 
						reqBMProductUpdVo.getOnlineAWithin12MonthsAddress() != null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanNo", reqBMProductUpdVo.getLoanNo());
			map.put("onlineAWithin3MonthsAddress", reqBMProductUpdVo.getOnlineAWithin3MonthsAddress());
			map.put("onlineAWithin6MonthsAddress", reqBMProductUpdVo.getOnlineAWithin6MonthsAddress());
			map.put("onlineAWithin12MonthsAddress", reqBMProductUpdVo.getOnlineAWithin12MonthsAddress());
			firstAuditDao.updateOnlineAWithinMonthsAddress(map);
		}
		
		if(!StringUtils.isEmpty(reqBMProductUpdVo.getPolicyCheckIsVerify())){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanNo", reqBMProductUpdVo.getLoanNo());
			map.put("policyCheckIsVerify", reqBMProductUpdVo.getPolicyCheckIsVerify());
			firstAuditDao.updatePolicyCheckIsVerify(map);
		}
		
		if(!StringUtils.isEmpty(reqBMProductUpdVo.getCarCheckIsVerify())){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanNo", reqBMProductUpdVo.getLoanNo());
			map.put("carCheckIsVerify", reqBMProductUpdVo.getCarCheckIsVerify());
			firstAuditDao.updateCarCheckIsVerify(map);
		}
	}
	
	

	@Override
	public List<ResReassignmentUpdVO> ultimatePlUpdStatusNew(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO) {
		List<ResReassignmentUpdVO> resList = new ArrayList<ResReassignmentUpdVO>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//原因数据
		paramMap.put("code", reqCsRefusePlupdateStatusVO.getFirstLevelReasonCode());
		BMSTMReasonEntity firstLevel = iBMSTMReasonService.getBy(paramMap);
		BMSTMReasonEntity twoLevel = null;
		if(reqCsRefusePlupdateStatusVO.getTwoLevelReasonCode() != null){
			paramMap.put("code", reqCsRefusePlupdateStatusVO.getTwoLevelReasonCode());
			twoLevel = iBMSTMReasonService.getBy(paramMap);
		}
		
		
		for (ReqCsRefuseUpdStatusVO vo : reqCsRefusePlupdateStatusVO.getList()) {
			paramMap = new HashMap<String, Object>();
			ibmsSysLogService.recordSysLog(reqCsRefusePlupdateStatusVO, "审核系统|信审初审|update|更新", "接口名称:reassignmentPlReturnUpdStatus","初审批量更新退回状态");
			
			
			ResReassignmentUpdVO re = new ResReassignmentUpdVO();
			paramMap.put("loanNo", vo.getLoanNo());
			LoanBaseEntity by = loanBaseService.getBy(paramMap);
			if(by == null){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("根据借款编号select 为 NULL");
				resList.add(re);
				continue;
			}
			
			if(!by.getVerson().equals(vo.getVersion())){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("当前的version非最新的版本！");
				resList.add(re);
				continue;
			}
			
			
			if(by.getRtfNodeState() != null && !by.getRtfNodeState().equals(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue())
					&&!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())
					&& !by.getCheckNodeState().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("当前的流程节点状态非[初审分派办理]或者[终审分配退回初审]或者信审初审复核不同意，无法操作！");
				resList.add(re);
				continue;
			}
			
			paramMap.put("logoFlag",0);//退回录入,标识去除，借款前台标识,1(黄色)

			paramMap.put("version", vo.getVersion());
			paramMap.put("rtfNodeState",EnumConstants.RtfNodeState.CSFPCANCEL.getValue());
			firstAuditDao.updLoanBase(paramMap);
			
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			bmsSysLoanLog.setLoanBaseId(by.getId());
			bmsSysLoanLog.setLoanNo(vo.getLoanNo());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_ASSIGN_TRTURN.getValue());
			bmsSysLoanLog.setRtfState(by.getRtfState());
			bmsSysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.CSFPCANCEL.getValue());
			bmsSysLoanLog.setFirstLevleReasons(firstLevel.getReason());
			bmsSysLoanLog.setTwoLevleReasons(twoLevel==null?null:twoLevel.getReason());
			bmsSysLoanLog.setFirstLevleReasonsCode(firstLevel.getCode());
			bmsSysLoanLog.setTwoLevleReasonsCode(twoLevel==null?null:twoLevel.getCode());
			bmsSysLoanLog.setOperatorCode(reqCsRefusePlupdateStatusVO.getOperatorCode());
			bmsSysLoanLog.setOperator(reqCsRefusePlupdateStatusVO.getOperatorName());
			bmsSysLoanLog.setRemark(reqCsRefusePlupdateStatusVO.getRemark());
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
			ReqParamVO csParamVO=new ReqParamVO();
			csParamVO.setLoginUser(reqCsRefusePlupdateStatusVO.getOperatorCode());
			csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
			ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
			if(groupVo!=null){
				bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
				bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
				bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
			}
			bmsSysLoanLogDao.insert(bmsSysLoanLog);
			
			paramMap.put("firstLevelReasons", firstLevel.getReason());
			paramMap.put("twoLevelReasons", twoLevel==null?null:twoLevel.getReason());
			paramMap.put("firstLevelReasonCode", firstLevel.getCode());
			paramMap.put("twoLevelReasonCode", twoLevel==null?null:twoLevel.getCode());
			firstAuditDao.updLoanAdExt(paramMap);
			
			re.setIfSuccessful(true);
			re.setLoanNo(vo.getLoanNo());
			re.setMsg("成功！");
			resList.add(re);
			applyDataManipulationService.auditDifferences(vo.getLoanNo(), EnumConstants.differences.audit_back.getValue());	
		}
		return resList;
	}
	
	
	
	
	/**
	 * 批量更新初审退回或拒绝
	 */
	@Override
	public Map<String, Object> reassignmentPlUpdStatus(ReqCsPlupdateStatusVo reqCsPlupdateStatusVo,ReqCsUpdStatusVo reqCsUpdStatusVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> saveLoans = new HashMap<String, Object>();
		int result = 0;
		long result2 = 0;
		int result3 = 0;
		int result4=0;
		int result5=0;
		//for (ReqCsUpdStatusVo reqCsUpdStatusVo : loans) {
			map.put("loanNo", reqCsUpdStatusVo.getLoanNo());
			if(reqCsPlupdateStatusVo.getOperatorFlag().equals(EnumConstants.RtfNodeState.CSFPREJECT.getValue())){
				map.put("status",EnumConstants.LoanStatus.REFUSE.getValue());
				map.put("refustDate",DateUtil.defaultFormatDate(new Date()));
				result5=firstAuditDao.updAuditNo(map);
			}else{
				map.put("status",EnumConstants.LoanStatus.APPLY.getValue());
			}
			map.put("rtfNodeState",reqCsPlupdateStatusVo.getOperatorFlag());
			map.put("version",reqCsUpdStatusVo.getVersion());
			result = firstAuditDao.updLoanBase(map);
			// 插入借款日志表
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			bmsSysLoanLog.setLoanNo(reqCsUpdStatusVo.getLoanNo());
			if(reqCsUpdStatusVo.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.CSFPREJECT.getValue())){
				bmsSysLoanLog.setStatus(reqCsUpdStatusVo.getRtfNodeStatus());
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
			}else{
				bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
			}
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSZS.getValue());
			bmsSysLoanLog.setRtfNodeState(reqCsUpdStatusVo.getRtfNodeStatus());
			bmsSysLoanLog.setFirstLevleReasons(reqCsPlupdateStatusVo.getFirstLevelReasons());
			bmsSysLoanLog.setTwoLevleReasons(reqCsPlupdateStatusVo.getTwoLevelReasons());
			bmsSysLoanLog.setOperatorCode(reqCsPlupdateStatusVo.getOperatorCode());
			bmsSysLoanLog.setOperator(reqCsPlupdateStatusVo.getOperatorCode());
			bmsSysLoanLog.setFirstLevleReasonsCode(reqCsPlupdateStatusVo.getFirstLevelReasonCode());
			bmsSysLoanLog.setTwoLevleReasonsCode(reqCsPlupdateStatusVo.getTwoLevelReasonCode());
			bmsSysLoanLog.setRemark(reqCsPlupdateStatusVo.getRemark());
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
			ReqParamVO csParamVO=new ReqParamVO();
			csParamVO.setLoginUser(reqCsPlupdateStatusVo.getOperatorCode());
			csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
			ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
			if(groupVo!=null){
				bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
				bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
				bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
			}
			result2 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
			// 系统日志
			result3 = ibmsSysLogService.recordSysLog(reqCsPlupdateStatusVo, "审核系统|信审初审|update|更新", "接口名称:reassignmentPlUpdStatus",
					"初审批量更新拒绝或退回状态");
			if(reqCsPlupdateStatusVo.getOperatorFlag().equals(EnumConstants.RtfNodeState.CSFPREJECT.getValue())
					&& !reqCsUpdStatusVo.getBlackCode().equals("")){
				//保存灰名单
				map.put("blackList", reqCsUpdStatusVo.getBlackCode());
				result4=firstAuditDao.updLoanAdExt(map);
				if(result>0 && result2>0&& result3>0 && result4>0 && result5>0){
					saveLoans.put("sus",reqCsUpdStatusVo.getLoanNo());
				}else{
					saveLoans.put("fail",reqCsUpdStatusVo.getLoanNo());
				}
			}else{
				if(result>0 && result2>0&& result3>0){
					saveLoans.put("sus",reqCsUpdStatusVo.getLoanNo());
				}else{
					saveLoans.put("fail",reqCsUpdStatusVo.getLoanNo());
				}
			}
		//}
		return saveLoans;
	}
	
	
	
	/**
	 * 批量拒绝操作
	 */
	public List<ResReassignmentUpdVO> reassignmentPlUpdStatusNew(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO){
		boolean ifBlackGrey = false;
		List<ResReassignmentUpdVO> resList = new ArrayList<ResReassignmentUpdVO>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//原因数据
		paramMap.put("code", reqCsRefusePlupdateStatusVO.getFirstLevelReasonCode());
		BMSTMReasonEntity firstLevel = iBMSTMReasonService.getBy(paramMap);
		BMSTMReasonEntity twoLevel = null;
		if(reqCsRefusePlupdateStatusVO.getTwoLevelReasonCode() != null){
			paramMap.put("code", reqCsRefusePlupdateStatusVO.getTwoLevelReasonCode());
			twoLevel = iBMSTMReasonService.getBy(paramMap);
		}
		
		for (ReqCsRefuseUpdStatusVO vo : reqCsRefusePlupdateStatusVO.getList()) {
			paramMap = new HashMap<String, Object>();
			
			ibmsSysLogService.recordSysLog(reqCsRefusePlupdateStatusVO, "审核系统|信审初审|update|更新", "接口名称:refusePlRejectUpdStatus","初审批量更新拒绝状态");
			
			
			ResReassignmentUpdVO re = new ResReassignmentUpdVO();
			paramMap.put("loanNo", vo.getLoanNo());
			LoanBaseEntity by = loanBaseService.getBy(paramMap);
			if(by == null){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("根据借款编号select 为 NULL");
				resList.add(re);
				continue;
			}
			
			if(!by.getVerson().equals(vo.getVersion())){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("当前的version非最新的版本！");
				resList.add(re);
				continue;
			}
			
			if(!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.CSFPSUBMIT.getValue())
					&&!by.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())
					&& !by.getCheckNodeState().equals(EnumConstants.ChcekNodeState.CHECKNOPASS.getValue())){
				re.setIfSuccessful(false);
				re.setLoanNo(vo.getLoanNo());
				re.setMsg("当前的流程节点状态非[初审分派办理]或者[终审分配退回初审]或者信审初审复核不同意，无法操作！");
				resList.add(re);
				continue;
			}
			
			paramMap.put("status",EnumConstants.LoanStatus.REFUSE.getValue());
			paramMap.put("refustDate",DateUtil.defaultFormatDate(new Date()));
			firstAuditDao.updAuditNo(paramMap);
			
			
			paramMap.put("version", vo.getVersion());
			paramMap.put("rtfNodeState",EnumConstants.RtfNodeState.CSFPREJECT.getValue());
			firstAuditDao.updLoanBase(paramMap);
			
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			bmsSysLoanLog.setLoanBaseId(by.getId());
			bmsSysLoanLog.setLoanNo(vo.getLoanNo());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
			bmsSysLoanLog.setRtfState(by.getRtfState());
			bmsSysLoanLog.setRtfNodeState(EnumConstants.RtfNodeState.CSFPREJECT.getValue());
			bmsSysLoanLog.setFirstLevleReasons(firstLevel.getReason());
			bmsSysLoanLog.setTwoLevleReasons(twoLevel==null?null:twoLevel.getReason());
			bmsSysLoanLog.setFirstLevleReasonsCode(firstLevel.getCode());
			bmsSysLoanLog.setTwoLevleReasonsCode(twoLevel==null?null:twoLevel.getCode());
			bmsSysLoanLog.setOperatorCode(reqCsRefusePlupdateStatusVO.getOperatorCode());
			ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
			reqEmployeeVO.setUsercode(reqCsRefusePlupdateStatusVO.getOperatorCode());
			ResEmployeeVO  resVo=pmsClientService.findEmployeeByCode(reqEmployeeVO);
			if(resVo!=null){
				bmsSysLoanLog.setOperator(resVo.getName());
			}else{
			bmsSysLoanLog.setOperator(reqCsRefusePlupdateStatusVO.getOperatorName());
			}
			bmsSysLoanLog.setRemark(reqCsRefusePlupdateStatusVO.getRemark());
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
			ReqParamVO csParamVO=new ReqParamVO();
			csParamVO.setLoginUser(reqCsRefusePlupdateStatusVO.getOperatorCode());
			csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.CHECK.getCode());
			ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
			if(groupVo!=null){
				bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
				bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
				bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
			}
			bmsSysLoanLogDao.insert(bmsSysLoanLog);
			
			
			//是否需要校验黑名单
//			if(ifBlackGrey){
//				BlackGreyReqVO black = new BlackGreyReqVO();
//				black.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
//				black.setListSrc(Constants.SourceStatus._00002.getCode());
//				black.setIdNo(by.getIdNo());
//				black.setName(by.getName());
//				black.setBlackReason(firstLevel.getReason());
//				black.setGreyReason(twoLevel==null?null:twoLevel.getReason());
//				black.setCreateUser(reqCsRefusePlupdateStatusVO.getOperatorCode());
//				Response<Object> xingWeiResponse = blackListExecuter.saveBlackGreyList(black);
				
			BlackGreyVO blackGreyVO = new BlackGreyVO();
			blackGreyVO.setLoanNo(vo.getLoanNo());
			blackGreyVO.setFirstLevelReasonsCode(firstLevel.getCode());
			blackGreyVO.setFirstLevelReasons(firstLevel.getReason());
			blackGreyVO.setTwoLevelReasonsCode(twoLevel==null?null:twoLevel.getCode());
			blackGreyVO.setTwoLevelReasons(twoLevel==null?null:twoLevel.getReason());
			blackGreyVO.setOperatorCode(reqCsRefusePlupdateStatusVO.getOperatorCode());
			
			ResReassignmentUpdVO ifSaveBlackGrey = bMSQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
			if(ifSaveBlackGrey.isIfSuccessful()){
				//保存灰名单
				paramMap.put("blackList", ifSaveBlackGrey.getMsg());
			}else{
				logger.info("["+vo.getLoanNo()+"]将黑名单存入行为库错误：原因："+ifSaveBlackGrey.getMsg());
			}	
			
//			}
			
			
			paramMap.put("firstLevelReasons", firstLevel.getReason());
			paramMap.put("twoLevelReasons", twoLevel==null?null:twoLevel.getReason());
			paramMap.put("firstLevelReasonCode", firstLevel.getCode());
			paramMap.put("twoLevelReasonCode", twoLevel==null?null:twoLevel.getCode());
			firstAuditDao.updLoanAdExt(paramMap);
			
			re.setIfSuccessful(true);
			re.setLoanNo(vo.getLoanNo());
			re.setMsg("成功！");
			resList.add(re);
		}
		return resList;
	}

	@Override
	public boolean checkIsHositoryLoanNo(Map<String, Object> map) {
		boolean flag = false;
		int result = firstAuditDao.checkIsHositoryLoanNo(map);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<BMSFirstAuditEntity> queryBmsLogByLoan(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstAuditDao.queryBmsLogByLoan(map);
	}

	@Override
	public boolean checkRtfNodeStatus(Map<String, Object> map) {
		boolean flag=true;
		int count=firstAuditDao.checkRtfNodeStatus(map);
		if(count>0){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}

	@Override
	public String byLoanNoQueryXieShengCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstAuditDao.byLoanNoQueryXieShengCode(map);
	}

	@Override
	public int byPersonCodeQueryJuse(String serviceCode) {
		// TODO Auto-generated method stub
		return firstAuditDao.byPersonCodeQueryJuse(serviceCode);
	}

	@Override
	public List<String> byAppRovalPersonCodeQueryLoanNo(String serviceCode) {
		// TODO Auto-generated method stub
		return firstAuditDao.byAppRovalPersonCodeQueryLoanNo(serviceCode);
	}

	@Override
	public Integer findTrialByStatus(String code) {
		return firstAuditDao.findTrialByStatus(code);
	}

	@Override
	public List<BMSFirstAuditEntity> queryFirstOperationTime(
			Map<String, Object> map) {
		
		return firstAuditDao.queryFirstOperationTime(map);
	}

	@Override
	public List<BMSFirstAuditEntity> querycSFirstOperationTime(
			Map<String, Object> map) {
		
		return firstAuditDao.querycSFirstOperationTime(map);
	}
	
	@Override
	public boolean getHZReportInfo(ReqCsUpdVO reqCsUpdVO) {
		String name = ObjectUtils.toString(reqCsUpdVO.getName());
		String idNo = ObjectUtils.toString(reqCsUpdVO.getIdNo());
		String cellphone = ObjectUtils.toString(reqCsUpdVO.getCellphone());
		String cellphoneSec = ObjectUtils.toString(reqCsUpdVO.getCellPhoneSec());
		StringBuffer sb = new StringBuffer();
		String flag = "";
		
		sb.append(name).append("|");
		sb.append(idNo).append("|");
		sb.append(cellphone).append("|");
		sb.append(cellphoneSec);
		
		logger.info("绑定【华征报告】，信审入参："+sb.toString());
				
		Map<String,Object> personPhoneMap = firstAuditDao.getPersonPhone(reqCsUpdVO.getLoanNo());
		String loanBaseId = ObjectUtils.toString(personPhoneMap.get("id"));
		String hzReportChangeInfo = ObjectUtils.toString(personPhoneMap.get("hz_report_change_info"));
		logger.info("绑定【华征报告】，对比数据："+hzReportChangeInfo);
		
		//绑定华征前置条件
		Map<String,Object> preMap = firstAuditDao.hzReportIdPreCondition(reqCsUpdVO.getLoanNo());
		if(null == preMap || preMap.isEmpty()){
			logger.info("绑定【华征报告】前置条件未通过，前置条件查询为空！");
			return false;
		}else{
			if(StringUtils.isEmpty(hzReportChangeInfo)){ //信审第一次提交，把变动的信息存到数据库，用于后面数据对比
				flag = "1";
			}else{ //之后的提交
				if(hzReportChangeInfo.indexOf(name) == -1 || hzReportChangeInfo.indexOf(idNo) == -1){ //姓名身份有变化，重新查询所有华征信
					logger.info("绑定【华征报告】信息对比，姓名和身份证有变动，需要重新获取华征。");
					flag = "1";
				}else{ //姓名身份没有变化
					if(!StringUtils.isEmpty(cellphone) && !StringUtils.isEmpty(cellphoneSec)){ //两个手机都存在
						if(hzReportChangeInfo.indexOf(cellphone) == -1 && hzReportChangeInfo.indexOf(cellphoneSec) == -1){ //两个手机都有变化
							logger.info("绑定【华征报告】信息对比，两手机号，两个手机号有变动，需要重新获取华征。");
							flag = "1";
						}
						if(hzReportChangeInfo.indexOf(cellphone) == -1 && hzReportChangeInfo.indexOf(cellphoneSec) != -1){ //手机1有变化，手机2没有变化
							logger.info("绑定【华征报告】信息对比，两手机号，手机1有变动，手机2没有变动，需要重新获取华征。");
							flag = "2";
						}
						if(hzReportChangeInfo.indexOf(cellphone) != -1 && hzReportChangeInfo.indexOf(cellphoneSec) == -1){ //手机1没有变化，手机2有变化
							logger.info("绑定【华征报告】信息对比，两手机号，手机1没有变动，手机2有变动，需要重新获取华征。");
							flag = "3";
						}
					}else{ //一个手机号
						if(!StringUtils.isEmpty(cellphone) && hzReportChangeInfo.indexOf(cellphone) == -1){
							logger.info("绑定【华征报告】信息对比，一手机号，手机1不为空，手机1有变动，需要重新获取华征。");
							flag = "2";
						}
						if(StringUtils.isEmpty(cellphone) && hzReportChangeInfo.indexOf(cellphoneSec) == -1){
							logger.info("绑定【华征报告】信息对比，一手机号，手机1不空，手机2有变动，需要重新获取华征。");
							flag = "3";
						}
					}
				}
			}
		}
		
		//每次提交都需要更新对比的最新信息
		Map<String,Object> tempMap1 = new HashMap<>();
		tempMap1.put("id", loanBaseId);
		tempMap1.put("hzReportChangeInfo", sb.toString());
		firstAuditDao.updateHZReportChangeInfo(tempMap1);
		
		if(flag.equals("1") || flag.equals("2") || flag.equals("3")){ //信息有变动，更新最新变动信息到数据库
			logger.info("绑定【华征报告】信息对比，信息改变了，需要重新绑定");
		}else{
			logger.info("绑定【华征报告】信息对比，信息未改变，不需要重新绑定！");
		}
		
		Map<String,String> tempMap = new HashMap<>();
		tempMap.put("timestamp", ObjectUtils.toString(new Date().getTime()));
		tempMap.put("idCard", idNo);
		tempMap.put("name", name);
		tempMap.put("appNo", reqCsUpdVO.getLoanNo());
		tempMap.put("creatorId", reqCsUpdVO.getOperatorCode());
		tempMap.put("isCheck", "true"); 
		tempMap.put("sources", "bms");
		
		JSONObject mobileOnlineIDObj = new JSONObject();
		JSONObject idCardCheckIDObj = new JSONObject();
		
		JSONObject mobileOnlineIDObjTmp = new JSONObject();
		JSONObject idCardCheckIDObjTmp = new JSONObject();
		String longOnlineId = ObjectUtils.toString(personPhoneMap.get("long_online_id"));
		String realNameAuthId = ObjectUtils.toString(personPhoneMap.get("real_name_auth_id"));
		
		if(!StringUtils.isEmpty(longOnlineId) && !flag.equals("1")){
			mobileOnlineIDObjTmp = new JSONObject(longOnlineId);
			if(!StringUtils.isEmpty(cellphone) && longOnlineId.indexOf(cellphone) != -1){
				mobileOnlineIDObj.put(cellphone, mobileOnlineIDObjTmp.get(cellphone));
			}
			if(!StringUtils.isEmpty(cellphoneSec) && longOnlineId.indexOf(cellphoneSec) != -1){
				mobileOnlineIDObj.put(cellphoneSec, mobileOnlineIDObjTmp.get(cellphoneSec));
			}
		}
		if(!StringUtils.isEmpty(realNameAuthId) && !flag.equals("1")){
			idCardCheckIDObjTmp = new JSONObject(realNameAuthId);
			if(!StringUtils.isEmpty(cellphone) && realNameAuthId.indexOf(cellphone) != -1){
				idCardCheckIDObj.put(cellphone, idCardCheckIDObjTmp.get(cellphone));
			}
			if(!StringUtils.isEmpty(cellphoneSec) && realNameAuthId.indexOf(cellphoneSec) != -1){
				idCardCheckIDObj.put(cellphoneSec, idCardCheckIDObjTmp.get(cellphoneSec));
			}
		}
				
		if(flag.equals("1")){ //查询两个手机号的华征信息
			String hzMobileOnlineID = getMobileOnlineCellphone(tempMap,cellphone);
			if(!StringUtils.isEmpty(hzMobileOnlineID)){
				mobileOnlineIDObj.put(cellphone, hzMobileOnlineID);
			}
			if(!StringUtils.isEmpty(cellphoneSec)){
				String hzMobileOnlineSecID = getMobileOnlineCellphone(tempMap,cellphoneSec);
				if(!StringUtils.isEmpty(hzMobileOnlineSecID)){
					mobileOnlineIDObj.put(cellphoneSec, hzMobileOnlineSecID);
				}
			}
			
			String hzIdCardCheckID = getIdCardCheckCellphone(tempMap,cellphone);
			if(!StringUtils.isEmpty(hzIdCardCheckID)){
				idCardCheckIDObj.put(cellphone, hzIdCardCheckID);
			}
			if(!StringUtils.isEmpty(cellphoneSec)){
				String hzIdCardCheckSECID = getIdCardCheckCellphone(tempMap,cellphoneSec);
				if(!StringUtils.isEmpty(hzIdCardCheckSECID)){
					idCardCheckIDObj.put(cellphoneSec, hzIdCardCheckSECID);
				}
			}
		}
		
		if(flag.equals("2")){ //查询手机号1
			String hzMobileOnlineID = getMobileOnlineCellphone(tempMap,cellphone);
			if(!StringUtils.isEmpty(hzMobileOnlineID)){
				mobileOnlineIDObj.put(cellphone, hzMobileOnlineID);
			}
			String hzIdCardCheckID = getIdCardCheckCellphone(tempMap,cellphone);
			if(!StringUtils.isEmpty(hzIdCardCheckID)){
				idCardCheckIDObj.put(cellphone, hzIdCardCheckID);
			}
		}
		
		if(flag.equals("3")){ //查询手机号2
			String hzMobileOnlineSecID = getMobileOnlineCellphone(tempMap,cellphoneSec);
			if(!StringUtils.isEmpty(hzMobileOnlineSecID)){
				mobileOnlineIDObj.put(cellphoneSec, hzMobileOnlineSecID);
			}
			String hzIdCardCheckSECID = getIdCardCheckCellphone(tempMap,cellphoneSec);
			if(!StringUtils.isEmpty(hzIdCardCheckSECID)){
				idCardCheckIDObj.put(cellphoneSec, hzIdCardCheckSECID);
			}
		}
		
		//把华征报告ID更新到借款
		Map<String,Object> paramsMap = new HashMap<>();
		if(mobileOnlineIDObj.length() > 0){
			paramsMap.put("mobileOnlineId", mobileOnlineIDObj.toString());
		}
		if(idCardCheckIDObj.length() > 0){
			paramsMap.put("idCardCheckId", idCardCheckIDObj.toString());
		}
		
		paramsMap.put("id", loanBaseId);
		firstAuditDao.updateHZReportId(paramsMap);
		
		return true;
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
	 * 获取华征学历报告ID
	 */
	public String queryHZEducationInfo(Map<String,String> paramsMap){
		String reportId = "";
		try {
			logger.info("【华征学历报告】请求入参：" + paramsMap);
			JSONObject obj = creditHttpServiceImpl.postBaseMethod(queryHZEducationInfo, paramsMap);
			if(null == obj){
				logger.info("【华征学历报告】返回为null");
			}else{
				if("000000".equals(obj.getString("code"))){
					
				}
				logger.info("【华征学历报告】响应结果：" + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【华征学历报告】异常：" + e);
		}
		return reportId;
	}
	
	/**
	 * 获取华征手机在网时长报告ID
	 */
	public String queryHZMobileOnlineInfo(Map<String,String> paramsMap){
		String reportId = "";
		try {
			logger.info("【华征手机在网时长】请求入参：" + paramsMap);
			JSONObject obj = creditHttpServiceImpl.postBaseMethod(queryHZMobileOnlineInfo, paramsMap);
			if(null == obj){
				logger.info("【华征手机在网时长】返回为null");
			}else{
				if("000000".equals(obj.getString("code"))){
					reportId = ObjectUtils.toString(obj.getInt("id"));
				}
				logger.info("【华征手机在网时长】响应结果：" + obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【华征手机在网时长】异常：" + e);
		}
		return reportId;
	}
	
	/**
	 * 获取华征实名认证报告ID
	 */
	public String queryHZIdCardCheckInfo(Map<String,String> paramsMap){
		String reportId = "";
		try {
			logger.info("【华征实名认证】请求入参：" + paramsMap);
			JSONObject obj = creditHttpServiceImpl.postBaseMethod(queryHZIdCardCheckInfo, paramsMap);
			if(null == obj){
				logger.info("【华征实名认证】返回为null");
			}else{
				if("000000".equals(obj.getString("code"))){
					reportId = ObjectUtils.toString(obj.getInt("id"));
				}

				logger.info("【华征实名认证】响应结果：" +  obj.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【华征实名认证】异常：" + e);
		}
		return reportId;
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
}
