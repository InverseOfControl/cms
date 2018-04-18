package com.ymkj.cms.biz.service.audit.last.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSLoansAndVersionsVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSMessageVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.dao.audit.last.IBMSFinalAuditDao;
import com.ymkj.cms.biz.dao.master.IBMSProductDao;
import com.ymkj.cms.biz.dao.master.IBMSReasonManagementDao;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionService;
import com.ymkj.cms.biz.service.audit.last.IBMSFinalAuditService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.cms.biz.service.master.IBMSLoanAuditEntityService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.pms.biz.api.enums.OrganizationEnum;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResGroupVO;
/**
 * 终审接口实现类
 * @author YM10172
 *
 */
@Service
public class BMSFinalAuditServiceImpl  extends BaseServiceImpl<BMSFirstAuditEntity> implements IBMSFinalAuditService {
	@Autowired
	private BMSFirstAuditDao firstAuditDao;
	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private IBMSProductDao productDao;
	@Autowired
	private IBMSFinalAuditDao finalAuditDao;
	@Autowired
	private IBMSReasonManagementDao reasonManageDao;
	@Autowired
	private BMSQualityInspectionService bmsQualityInspectionService;
	@Autowired
	private LoanBaseService iloanBaseService;
	
	@Autowired
	private PMSClientService pmsClientService;
	@Autowired
	private IBMSLoanAuditEntityService iLoanAuditService;
	/**
	 * ZS 插入借款日志表； 插入审核表 并且更新申请主表流程状态，流程节点状态； 三个一起组成一个事务
	 */
	@Override
	public boolean updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer result4 = null;
		BlackGreyVO blackGreyVO=new BlackGreyVO();
		// 更新申请主表状态
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())){
			map.put("status",    EnumConstants.LoanStatus.REFUSE.getValue());
		}else{
			map.put("logoFlag",0);//退回录入,标识去除，借款前台标识,1(黄色)
			map.put("status",    EnumConstants.LoanStatus.APPLY.getValue());
		}
		map.put("rtfStatus",     EnumConstants.RtfState.XSZS.getValue());
		map.put("loanNo",        reqZsUpdVO.getLoanNo());
		map.put("rtfNodeState",  reqZsUpdVO.getRtfNodeStatus());
		map.put("version",       reqZsUpdVO.getVersion());
		map.put("ifNewLoanNo",   reqZsUpdVO.getIfNewLoanNo());
		map.put("zSIfNewLoanNo", reqZsUpdVO.getzSIfNewLoanNo());
		int result = finalAuditDao.updLoanBase(map);
		if(result==0){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		// 插入借款日志表
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setLoanNo(reqZsUpdVO.getLoanNo());
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
		ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(reqZsUpdVO.getOperatorCode());
		ResEmployeeVO resVO=pmsClientService.findEmployeeByCode(reqEmployeeVO);
		if(resVO!=null){
			bmsSysLoanLog.setOperator(resVO.getName());	
		}
		bmsSysLoanLog.setFinalPersonCode(reqZsUpdVO.getzSPersonCode());
		bmsSysLoanLog.setApppovalPersonCode(reqZsUpdVO.getApppovalPersonCode());
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		bmsSysLoanLog.setRemark(reqZsUpdVO.getRemark());
		bmsSysLoanLog.setLoanBaseId(finalAuditDao.findByLoanNo(reqZsUpdVO.getLoanNo()));
		ReqParamVO csParamVO=new ReqParamVO();
		csParamVO.setLoginUser(reqZsUpdVO.getOperatorCode());
		csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.FINAL_CHECK.getCode());
		ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
		if(groupVo!=null){
			bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
			bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
			bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
		}
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSREJECT.getValue())){   //终审拒绝
			 if(EnumConstants.RtfState.ZSFP.getValue().equals(reqZsUpdVO.getRtfStatus())){
				 bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_ZSFP_REJECT.getValue()); 
			 }else{
				 bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
			 }
			BMSLoanAudit loanAudit=iLoanAuditService.findBmsLoanAuditByLoanNo(reqZsUpdVO.getLoanNo());
			bmsSysLoanLog.setApplyLmt(loanAudit.getAccLmt());
			bmsSysLoanLog.setApplyTerm(loanAudit.getAccTerm());
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())){  //终审退回录入
			map.put("loanNo", reqZsUpdVO.getLoanNo()); 
			BMSFirstAuditEntity firstAuditEntity=finalAuditDao.findByCheckCode(map);
			if(firstAuditEntity!=null){
				bmsSysLoanLog.setCheckPersonCode(firstAuditEntity.getCheckPersonCode());
				bmsSysLoanLog.setApplyLmt(firstAuditEntity.getApplyLmt());
				bmsSysLoanLog.setApplyTerm(firstAuditEntity.getApplyTerm());
			}
			 if(EnumConstants.RtfState.ZSFP.getValue().equals(reqZsUpdVO.getRtfStatus())){
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_ZSFP_RETURN.getValue()); 
			}else{
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_XSZS_RETURN.getValue());
			}
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())){  //终审退回初审
			map.put("loanNo", reqZsUpdVO.getLoanNo()); 
			BMSFirstAuditEntity firstAuditEntity=finalAuditDao.findByCheckCode(map);
			if(firstAuditEntity!=null){
				bmsSysLoanLog.setCheckPersonCode(firstAuditEntity.getCheckPersonCode());
				bmsSysLoanLog.setApplyLmt(firstAuditEntity.getApplyLmt());
				bmsSysLoanLog.setApplyTerm(firstAuditEntity.getApplyTerm());
			}
			 if(EnumConstants.RtfState.ZSFP.getValue().equals(reqZsUpdVO.getRtfStatus())){
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_ZSFP_RTNCS.getValue()); 
			}else{
				bmsSysLoanLog.setOperationType(EnumConstants.OptionType.ZS_XSZS_RTNCS.getValue());
			}
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSHANGUP.getValue())){  //终审挂起
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.HANHUP.getValue());
		}else{
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.SUBMIT.getValue());  //终审提交
			//终审提交时查询审批额度和审批期限到日志表
			BMSLoanAudit loanAudit=iLoanAuditService.findBmsLoanAuditByLoanNo(reqZsUpdVO.getLoanNo());
			bmsSysLoanLog.setApplyLmt(loanAudit.getAccLmt());
			bmsSysLoanLog.setApplyTerm(loanAudit.getAccTerm());
		}
		if(reqZsUpdVO.getFirstLevelReasonCode()!=null){
				map.put("firstLevelReasons", reqZsUpdVO.getFirstLevelReasons());
				map.put("firstLevelReasonCode",reqZsUpdVO.getFirstLevelReasonCode());	
				blackGreyVO.setFirstLevelReasonsCode(reqZsUpdVO.getFirstLevelReasonCode());
				blackGreyVO.setFirstLevelReasons(reqZsUpdVO.getFirstLevelReasons());
				bmsSysLoanLog.setFirstLevleReasonsCode(reqZsUpdVO.getFirstLevelReasonCode());
				bmsSysLoanLog.setFirstLevleReasons(reqZsUpdVO.getFirstLevelReasons());
			}
		if(reqZsUpdVO.getTwoLevelReasonCode()!=null){
				map.put("twoLevelReasons", reqZsUpdVO.getTwoLevelReasons());
				map.put("twoLevelReasonCode",reqZsUpdVO.getTwoLevelReasonCode());
				blackGreyVO.setTwoLevelReasonsCode(reqZsUpdVO.getTwoLevelReasonCode());
				blackGreyVO.setTwoLevelReasons(reqZsUpdVO.getTwoLevelReasons());
				bmsSysLoanLog.setTwoLevleReasonsCode(reqZsUpdVO.getTwoLevelReasonCode());
				bmsSysLoanLog.setTwoLevleReasons(reqZsUpdVO.getTwoLevelReasons());
		}
		Long result2 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
		// 更新审批表信息
		map.put("loanNo", reqZsUpdVO.getLoanNo());  
	/*	map.put("finalPersonCode", reqZsUpdVO.getzSPersonCode());
		map.put("apppovalPersonCode", reqZsUpdVO.getApppovalPersonCode());*/
		map.put("accLmt", reqZsUpdVO.getAccLmt());
		map.put("accTime", reqZsUpdVO.getAccTime());
		map.put("accDate", reqZsUpdVO.getAccDate());
		map.put("allotDate", reqZsUpdVO.getAllotDate());
		/*map.put("loanNoTopClass", reqZsUpdVO.getLoanNoTopClass());*/
		//设置是终审退回初审还是门店
		if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRETURN.getValue())){
			map.put("ifLastCheck", "2");
		}else if(reqZsUpdVO.getRtfNodeStatus().equals(EnumConstants.RtfNodeState.XSZSRTNCS.getValue())){
			map.put("ifLastCheck", "1");
		}
		int result3 = finalAuditDao.updAuditNo(map);
		// 系统日志
		int result5 = ibmsSysLogService.recordSysLog(reqZsUpdVO, "审核系统|终审更新|update|更新",
				"接口名称:updateZsLoanNoState", "终审更新");
		map.put("blackList", reqZsUpdVO.getBlackList());
		//判断一级原因是否为空，更新扩展表
		if(reqZsUpdVO.getFirstLevelReasonCode()!=null){
			//验证是否是需要加入黑名单的原因，true是
			if((reqZsUpdVO.getRtfNodeStatus()).equals("XSZS-REJECT")){
				blackGreyVO.setOperatorCode(reqZsUpdVO.getOperatorCode());
				blackGreyVO.setLoanNo( reqZsUpdVO.getLoanNo());  //借款编号
				ResReassignmentUpdVO vo=bmsQualityInspectionService.ifSaveBlackGrey(blackGreyVO);
				if(vo.isIfSuccessful()==true){
					map.put("blackList", vo.getMsg());
				}
			}
			result4 = firstAuditDao.updLoanAdExt(map);
			if (result > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}else{
			if (result > 0 && result2 > 0 && result3 > 0 && result5>0) {
				flag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
		}
		return flag;
	}
	/**
	 * 信审产品更新
	 */
	@Override
	public boolean updateZSProductInfo(ReqBMProductUpdVo reqBMProductUpdVo) {
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", reqBMProductUpdVo.getLoanNo());
		map.put("version", reqBMProductUpdVo.getVersion());
		int result = finalAuditDao.updLoanBaseTwo(map);
		map.put("isDeleted", EnumConstants.ifNewLoanNo.NOLOANNO.getValue());
		map.put("code", reqBMProductUpdVo.getProductCd());
		BMSProduct product = productDao.getByVo(map);
		map.put("name", product.getName());
		int result2 = finalAuditDao.updBmsLoanProduct(map);
		map.put("sysCheckLmt", reqBMProductUpdVo.getSysCheckLmt());
		map.put("accLmt", reqBMProductUpdVo.getAccLmt());
		map.put("accTime", reqBMProductUpdVo.getAccTerm());
		map.put("accDate", reqBMProductUpdVo.getAccDate());
		int result4 = finalAuditDao.updAuditNo(map);
		// 系统日志
		int result3 = ibmsSysLogService.recordSysLog(reqBMProductUpdVo, "审核系统|信审终审|update|更新", "接口名称:xsProductUpd",
				"借款产品信息更新"+reqBMProductUpdVo.getRemark());
		if (result > 0 && result2 > 0&& result3 > 0 && result4 > 0) {
			flag = true;
		} else {
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		return flag;
	}
	@Override
	protected BaseDao<BMSFirstAuditEntity> getDao() {

		return finalAuditDao;
	}

	@Override
	public boolean checkFinalRtfNodeStatus(Map<String, Object> map) {
		int count=finalAuditDao.checkFinalRtfNodeStatus(map);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<BMSAutomaticDispatchEntity> zSautomaticDispatchList(Map<String, Object> map) {
		return finalAuditDao.zSautomaticDispatchList(map);
	}
	@Override
	public Map<String, Object> updateZsReassignment(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> saveLoans = new HashMap<String, Object>();
		List<ResBMSMessageVO> resList=new ArrayList<ResBMSMessageVO>();
		List<ReqBMSLoansAndVersionsVO> loans = reqBMSReassignmentUpdVo.getList();
		int result = 0;
		int result2 = 0;
		int result3 = 0;
		long result4 = 0;
		for (ReqBMSLoansAndVersionsVO reqBMSLoansAndVersionsVO : loans) {
			BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
			Map<String,Object> hMap=new HashMap<String,Object>();
			hMap.put("loanNo", reqBMSLoansAndVersionsVO.getLoanNo());
			InformationEntity inforMation = iloanBaseService.queryInformationEntity(hMap);
			if(inforMation!=null){
				if(inforMation.getApprovalPersonCode()!=null || inforMation.getRtfNodeState().equals(EnumConstants.RtfNodeState.XSZSSUBMITAPPROVAL.getValue()) ){
					map.put("apppovalPersonCode", reqBMSReassignmentUpdVo.getAuditPersonCode());	
					bmsSysLoanLog.setApppovalPersonCode(reqBMSReassignmentUpdVo.getAuditPersonCode());
				}else{
					map.put("finalPersonCode", reqBMSReassignmentUpdVo.getAuditPersonCode());
					bmsSysLoanLog.setFinalPersonCode(reqBMSReassignmentUpdVo.getAuditPersonCode());
				}
			}
			map.put("loanNo", reqBMSLoansAndVersionsVO.getLoanNo());
			map.put("version", reqBMSLoansAndVersionsVO.getVersion());
			map.put("rtfNodeState", reqBMSLoansAndVersionsVO.getRtfNodeStatus());
			map.put("rtfStatus", EnumConstants.RtfState.XSZS.getValue());
			bmsSysLoanLog.setRtfState(EnumConstants.RtfState.XSZS.getValue());
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REASSIGNMENT.getValue());
			ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
			reqEmployeeVO.setUsercode(reqBMSReassignmentUpdVo.getOperatorCode());
			ResEmployeeVO resVO=pmsClientService.findEmployeeByCode(reqEmployeeVO);
			if(resVO!=null){
				bmsSysLoanLog.setOperator(resVO.getName());	
			}
			ReqEmployeeVO reqEmployee=new ReqEmployeeVO();
			reqEmployee.setUsercode(reqBMSReassignmentUpdVo.getAuditPersonCode());
			ResEmployeeVO resEmp=pmsClientService.findEmployeeByCode(reqEmployee);
			if(resEmp!=null){
				//String oldAuditPersonCode=reqBMSLoansAndVersionsVO.getOldAuditPersonCode()==null?null:reqBMSLoansAndVersionsVO.getOldAuditPersonCode();
				if(reqBMSLoansAndVersionsVO.getOldAuditPersonCode()==null){
					//bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原终审人员null改派为:"+resEmp.getName());	
					bmsSysLoanLog.setRemark("由null改派至"+resEmp.getName());
				}else{
					ReqEmployeeVO reqEmployees=new ReqEmployeeVO();
					reqEmployees.setUsercode(reqBMSLoansAndVersionsVO.getOldAuditPersonCode());
					ResEmployeeVO resEmps=pmsClientService.findEmployeeByCode(reqEmployees);	
					//bmsSysLoanLog.setRemark("借款单号:"+reqBMSLoansAndVersionsVO.getLoanNo()+"由原终审人员:"+resEmps.getName()+"改派为:"+resEmp.getName());
					bmsSysLoanLog.setRemark("由"+resEmps.getName()+"改派至"+resEmp.getName());
				}
				
			}
			
			// 插入借款日志表
			
			bmsSysLoanLog.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
			bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.APPLY.getValue());
			bmsSysLoanLog.setRtfNodeState(reqBMSLoansAndVersionsVO.getRtfNodeStatus());
			bmsSysLoanLog.setLoanBaseId(finalAuditDao.findByLoanNo(reqBMSLoansAndVersionsVO.getLoanNo()));
			bmsSysLoanLog.setOperatorCode(reqBMSReassignmentUpdVo.getOperatorCode());
			
			bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
			ReqParamVO csParamVO=new ReqParamVO();
			csParamVO.setLoginUser(reqBMSReassignmentUpdVo.getOperatorCode());
			csParamVO.setOrgTypeCode(OrganizationEnum.OrgCode.FINAL_CHECK.getCode());
			ResGroupVO groupVo=pmsClientService.findOrgGroupInfo(csParamVO);
			if(groupVo!=null){
				bmsSysLoanLog.setSmallGroupId(groupVo.getGroupId());
				bmsSysLoanLog.setLargeGroupId(groupVo.getBigGroupId());
				bmsSysLoanLog.setHandleGroupId(groupVo.getGroupCode());
			}
			result = finalAuditDao.updAuditNo(map);
			result2 = finalAuditDao.updLoanBase(map);
			if(result2==0){
				throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
			}
			result4 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
			// 系统日志
			result3 = ibmsSysLogService.recordSysLog(reqBMSReassignmentUpdVo, "审核系统|信审改派|update|更新",
					"接口名称:updateZsReassignment", "批量改派更新");
			if (result > 0 && result2 > 0 && result3>0 && result4>0) {
				ResBMSMessageVO vo=new ResBMSMessageVO();
				vo.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
				vo.setStatus(true);
				resList.add(vo);
			} else {
				ResBMSMessageVO vo=new ResBMSMessageVO();
				vo.setLoanNo(reqBMSLoansAndVersionsVO.getLoanNo());
				vo.setStatus(false);
				vo.setRepMsg(CoreErrorCode.DB_UPDATE_RESULT_0.getDefaultMessage());
				resList.add(vo);
			}
		}
		saveLoans.put("resList", resList);
		return saveLoans;
	}
	@Override
	public Integer findLastByStatus(String code) {
		return finalAuditDao.findLastByStatus(code);
	}
	@Override
	public List<BMSFirstAuditEntity> queryzSBmsLogByLoan(Map<String, Object> map) {
		
		return finalAuditDao.queryzSBmsLogByLoan(map);
	}
	@Override
	public boolean updateXsSystemReject(ReqZsUpdVO reqZsUpdVO) {
		//更新借款主表信息

		Map<String, Object> map = new HashMap<String, Object>();

		// 更新申请主表状态
		map.put("status",    EnumConstants.LoanStatus.REFUSE.getValue());
		map.put("rtfStatus", reqZsUpdVO.getRtfStatus());
		map.put("rtfNodeState",  reqZsUpdVO.getRtfNodeStatus());
		map.put("loanNo",     reqZsUpdVO.getLoanNo());
		map.put("version",       reqZsUpdVO.getVersion());
		int result = finalAuditDao.updLoanBase(map);
		if(result==0){
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		// 插入借款日志表
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setLoanNo(reqZsUpdVO.getLoanNo());
		bmsSysLoanLog.setLoanBaseId(finalAuditDao.findByLoanNo(reqZsUpdVO.getLoanNo()));
		bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		bmsSysLoanLog.setRtfState(reqZsUpdVO.getRtfStatus());
		bmsSysLoanLog.setRtfNodeState(reqZsUpdVO.getRtfNodeStatus());
		bmsSysLoanLog.setFirstLevleReasons(reqZsUpdVO.getFirstLevelReasons());
		bmsSysLoanLog.setTwoLevleReasons(reqZsUpdVO.getTwoLevelReasons());
		bmsSysLoanLog.setFirstLevleReasonsCode(reqZsUpdVO.getFirstLevelReasonCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(reqZsUpdVO.getTwoLevelReasonCode());
		bmsSysLoanLog.setOperatorCode(reqZsUpdVO.getOperatorCode());
		bmsSysLoanLog.setOperator(reqZsUpdVO.getOperatorCode());
		bmsSysLoanLog.setRemark(reqZsUpdVO.getRemark());
		bmsSysLoanLog.setOperationType(EnumConstants.OptionType.REJECT.getValue());
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		Long result2 = bmsSysLoanLogDao.insert(bmsSysLoanLog);
		map.put("firstLevelReasons",   reqZsUpdVO.getFirstLevelReasons());
		map.put("firstLevelReasonsCode", reqZsUpdVO.getFirstLevelReasonCode());
		map.put("twoLevelReasons",  reqZsUpdVO.getTwoLevelReasons());
		map.put("twoLevelReasonsCode",   reqZsUpdVO.getTwoLevelReasonCode());
		int result3 = firstAuditDao.updLoanAdExt(map);
		if(result>0 && result2>0 && result3>0){
			return true;
		}else{
			return false;
		}

	}
	@Override
	public BMSFirstAuditEntity findLogByReturn(Map<String, Object> map) {
		
		return finalAuditDao.findLogByReturn(map);
	}
	@Override
	public BMSFirstAuditEntity findXsSnapVersionInfo(Map<String, Object> map) {
		return finalAuditDao.findXsSnapVersionInfo(map);
	}


}
