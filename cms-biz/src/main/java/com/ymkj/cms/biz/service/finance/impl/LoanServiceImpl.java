package com.ymkj.cms.biz.service.finance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.finance.GrantInfo;
import com.ymkj.cms.biz.entity.finance.GrantLoanEntity;
import com.ymkj.cms.biz.entity.finance.HttpLoanReturnEntity;
import com.ymkj.cms.biz.entity.finance.ResLoanFailEntity;
import com.ymkj.cms.biz.entity.master.BMSLoanConfirmationQuery;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;

@Service
public class LoanServiceImpl extends BaseServiceImpl<BMSLoanBase> implements ILoanService{
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private IBMSLoanBaseDao loanBaseDao;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;	
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private LoanExtDao loanExtDao;
	@Autowired
	private APPPersonInfoDao aPPPersonInfoDao;
	@Autowired
	private IBMSTMReasonService ibmstmReasonService;
	
	@Override
	protected BaseDao<BMSLoanBase> getDao() {
		
		return this.loanBaseDao;
	}

	@Override
	public PageBean<BMSLoanBase> doneListBy(ReqLoanVo reqLoanVo) {
		// 构造请求参数
		PageParam pageParam = new PageParam(reqLoanVo.getPageNum(), reqLoanVo.getPageSize());
		Map<String, Object> paramMap= null;
		try {
			paramMap = BeanKit.bean2map(reqLoanVo);
			if("".equals(paramMap.get("loanNos"))){
				paramMap.put("loanNos", null);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		// 调用业务逻辑
		PageBean<BMSLoanBase> pageBean = loanBaseDao.doneListPage(pageParam, paramMap);
	
		return pageBean;
	}
	
	@Override
	public boolean passAuditLoan(ReqLoanVo reqLoanVo) {
		boolean passFlag=false;
		//更新借款系统借款状态
		//构造 loanBaseId 和 借款状态RtfNodeState
		BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.FKQR.getValue(),
				EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
		loanBaseEntity.setFinanceAuditTime(new Date());
		boolean updatFlag=updateBMSLoanState(loanBaseEntity);
		if(updatFlag){
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_AUDIT_PASS);
			passFlag=updateCoreLoanState(reqLoanVo);
		}
		if(passFlag){
			//借款日志
			BMSSysLoanLog loanLog= new BMSSysLoanLog();
			loanLog.setLoanBaseId(reqLoanVo.getId());
			loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_AUDIT.getValue());
			loanLog.setLoanNo(reqLoanVo.getLoanNo());
			loanLog.setOperationTime(new Date());
			loanLog.setOperator(reqLoanVo.getServiceName());
			loanLog.setOperatorCode(reqLoanVo.getServiceCode());
			loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog.setRtfState(EnumConstants.RtfState.FKSH.getValue());
			loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
			loanLog.setOperationType(EnumConstants.OptionType.PASS.getValue());
			ibmsSysLoanLogService.saveOrUpdate(loanLog);
			//系统日志
			ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|财务审核|通过|update","ILoanExecuter\backAudit","放款审核通过");
		}
		return passFlag;
	}
	
	/**
	 * 批量财务审核通过     借款状态更新
	 * 
	 */
	@Override
	public Map<String,Object> bacthPassAudit(ReqLoanVo reqLoanVo) {
		
		Map<String,Object> retMap = new HashMap<String,Object>();;
				List<ReqLoanVo> loanList = reqLoanVo.getLoanList();
				List<String> loanNoList= new ArrayList<String>();
				List<String> stateList= new ArrayList<String>();
				List<BMSLoanBase>  loanBaseEntitys = new ArrayList<BMSLoanBase>();
				List<ResLoanVo> successLoanList = new ArrayList<ResLoanVo>();
				List<ResLoanVo> failLoanList = new ArrayList<ResLoanVo>();
				//将请求的reLoanVo 转换成loanBaseEntity
				for (ReqLoanVo reLoanVo2 : loanList) {
					BMSLoanBase loanBaseEntity = new BMSLoanBase(reLoanVo2);
						loanBaseEntity.setLoanNo(reLoanVo2.getLoanNo());
						loanBaseEntitys.add(loanBaseEntity);
				}
				
		//封装请求参数
		Map<String,Object> httpParamMap  = new HashMap<String,Object>();
		httpParamMap.put("loanNos", loanNoList);
		httpParamMap.put("stateCodes",stateList);
		
		//调用批量更新款接口
		List<ResLoanFailEntity>	failList = coreHttpService.bacthUpdateLoanState(loanBaseEntitys,EnumConstants.FINANCE_AUDIT_PASS,reqLoanVo.getServiceCode());
				if(failList !=null && failList.size()>0){
					for (ResLoanFailEntity resLoanFailEntity : failList) {
						ResLoanVo failLoanVo = new ResLoanVo();
						BeanUtils.copyProperties(resLoanFailEntity, failLoanVo);
						failLoanList.add(failLoanVo);
					}
				}
				
			for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
				boolean sucessFlag =true;
					for (ResLoanFailEntity resLoanFailEntity : failList) {
						if(resLoanFailEntity.getLoanNo().equals(loanBaseEntity.getLoanNo())){//失败
							sucessFlag=false;
							break;
						}
					}
					if(sucessFlag){//成功
						//新增：将成功的记录放入list集合
						ResLoanVo successLoanVo = new ResLoanVo();
						BeanUtils.copyProperties(loanBaseEntity, successLoanVo);
						successLoanList.add(successLoanVo);
						//更新借款状态
						loanBaseEntity.setRtfState(EnumConstants.RtfState.FKQR.getValue());
						loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
						loanBaseEntity.setFinanceAuditTime(new Date());
						boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
						if(updatFlag){
							BMSSysLoanLog loanLog2= new BMSSysLoanLog();
							//借款日志
							loanLog2.setLoanBaseId(loanBaseEntity.getId());
							loanLog2.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
							loanLog2.setLoanNo(loanBaseEntity.getLoanNo());
							loanLog2.setOperationTime(new Date());
							loanLog2.setOperator(reqLoanVo.getServiceName());
							loanLog2.setOperatorCode(reqLoanVo.getServiceCode());
							loanLog2.setStatus(EnumConstants.LoanStatus.PASS.getValue());
							loanLog2.setRtfState(EnumConstants.RtfState.FKSH.getValue());
							loanLog2.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
							loanLog2.setOperationType(EnumConstants.OptionType.PASS.getValue());
							ibmsSysLoanLogService.saveOrUpdate(loanLog2);
						}
					
			}
		}
			retMap.put("failLoanList", failLoanList);//新增：返回失败记录列表
			retMap.put("successLoanList", successLoanList);//新增：返回成功记录列表
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款审核|通过|bacthPass","ILoanExecuter","bacthPass");
		return retMap;
	}

	@Override
	public boolean backAudit(ReqLoanVo reqLoanVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		//构造 loanBaseId 和 借款状态RtfNodeState
		BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
				EnumConstants.RtfNodeState.FKSHRETURN.getValue());
		//loanBaseEntity.setStatus(EnumConstants.LoanStatus.getValue());
		loanBaseEntity.setFinanceAuditTime(new Date());
		boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
		if(updatFlag){
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_AUDIT_BACK);
			backFlag=updateCoreLoanState(reqLoanVo);
		}

		//记一二级原因
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanBaseId(reqLoanVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
		loanExtEntity.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
		loanExtEntity.setModifier(reqLoanVo.getServiceName());
		loanExtEntity.setModifier(reqLoanVo.getServiceCode());
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setLoanBaseId(reqLoanVo.getId());
		loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_AUDIT.getValue());
		loanLog.setLoanNo(reqLoanVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanVo.getServiceName());
		loanLog.setOperatorCode(reqLoanVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKSHRETURN.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		loanLog.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
		loanLog.setRemark(reqLoanVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款审核|退回|back","ILoanExecuter","放款审核退回");
		return backFlag;
	}
	
	/**
	 * 放款确认
	 */
	@Override
	public Map<String, Object> grantLoan(ReqLoanVo reqLoanVo) {
		GrantLoanEntity grantLoanEntity= new GrantLoanEntity();	
		Map<String,Object> retMap  = new HashMap<String,Object>();
		List<GrantInfo> infos  = new ArrayList<GrantInfo>();
		List<ReqLoanVo> loanList = reqLoanVo.getLoanList();
		List<BMSLoanBase>  loanBaseEntitys = new ArrayList<BMSLoanBase>();
		//新增：需要返回的成功失败的list,其中只有失败loanNo以及errorMessage有值，errorMessage留着备用
		List<ResLoanVo> successLoanList = new ArrayList<ResLoanVo>();
		List<ResLoanVo> failLoanList = new ArrayList<ResLoanVo>();
		
		//将请求的reLoanVo 转换成loanBaseEntity
		for (ReqLoanVo reLoanVo2 : loanList) {
			BMSLoanBase loanBaseEntity = new BMSLoanBase(reLoanVo2);
			loanBaseEntity.setContractBranch(reLoanVo2.getContractBranch());
			loanBaseEntity.setContractBranchId(reLoanVo2.getContractBranchId());
				loanBaseEntitys.add(loanBaseEntity);
				GrantInfo info = new GrantInfo();
				info.setLoanNo(reLoanVo2.getLoanNo());
				info.setBatchNum(reLoanVo2.getBatchNum()==null?"":reLoanVo2.getBatchNum());
				infos.add(info);
		}
			grantLoanEntity.setInfos(infos);
			grantLoanEntity.setUserCode(reqLoanVo.getServiceCode());

				//调用推送放款款接口
			HttpResponse 	httpResponse = coreHttpService.grantLoanURL(grantLoanEntity);
				HttpLoanReturnEntity httpLoanReturnEntity= JsonUtils.decode(httpResponse.getContent(),HttpLoanReturnEntity.class);
				//判断调用推送放款款接口是否成功
				if (!"000000".equals(httpLoanReturnEntity.getCode())){
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, httpLoanReturnEntity.getMessage());
				}
				List<ResLoanFailEntity> failList = httpLoanReturnEntity.getCreateFaiVOs();
				//新增：需要返回的失败的list,其中只有loanNo以及errorMessage有值
				if(failList !=null && failList.size()>0){
					for (ResLoanFailEntity resLoanFailEntity : failList) {
						//新增：将失败的记录放入list集合
						ResLoanVo failLoanVo = new ResLoanVo();
						BeanUtils.copyProperties(resLoanFailEntity, failLoanVo);
						failLoanList.add(failLoanVo);
					}
				}
				
				for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
					boolean sucessFlag =true;
						for (ResLoanFailEntity resLoanFailEntity : failList) {
							if(resLoanFailEntity.getLoanNo().equals(loanBaseEntity.getLoanNo())){//失败
								sucessFlag=false;
								break;
							}
						}
						if(sucessFlag){//成功
							//新增：将成功的记录放入list集合
							ResLoanVo successLoanVo = new ResLoanVo();
							BeanUtils.copyProperties(loanBaseEntity, successLoanVo);
							successLoanList.add(successLoanVo);
							//更新借款状态
							loanBaseEntity.setStatus(EnumConstants.LoanStatus.NORMAL.getValue());
							loanBaseEntity.setRtfState(EnumConstants.RtfState.DHHK.getValue());
							loanBaseEntity.setLoanDate(new Date());
							loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
							boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
							if(updatFlag){
								BMSSysLoanLog loanLog2= new BMSSysLoanLog();
								//借款日志
								loanLog2.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
								loanLog2.setLoanBaseId(reqLoanVo.getId());
								loanLog2.setLoanNo(loanBaseEntity.getLoanNo());
								loanLog2.setOperationTime(new Date());
								loanLog2.setOperator(reqLoanVo.getServiceName());
								loanLog2.setOperatorCode(reqLoanVo.getServiceCode());
								loanLog2.setStatus(EnumConstants.LoanStatus.PASS.getValue());
								loanLog2.setRtfState(EnumConstants.RtfState.FKQR.getValue());
								loanLog2.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
								loanLog2.setOperationType(EnumConstants.OptionType.LOAN.getValue());
								ibmsSysLoanLogService.saveOrUpdate(loanLog2);
							}
						}
			}	
				retMap.put("failLoanList", failLoanList);//新增：返回失败记录列表
				retMap.put("successLoanList", successLoanList);//新增：返回成功记录列表
				String grantTypeStr=loanList.size()>1?"批量通过":"通过";
				String grantType=loanList.size()>1?"batchPass":"pass";
				ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|"+grantTypeStr+"|"+grantType,"ILoanExecuter|grantLoan",grantTypeStr+"|"+grantType);
		return retMap;
	}
	
	
	

	@Override
	public boolean backLoan(ReqLoanVo reqLoanVo) {
		boolean backFlag=false;
		//更新借款系统借款状态
		BMSLoanBase  loanBaseEntity = new BMSLoanBase(reqLoanVo.getId(),EnumConstants.RtfState.HTQY.getValue(),
				EnumConstants.RtfNodeState.FKQRRETURN.getValue());
		loanBaseEntity.setLoanDate(new Date());
		boolean updatFlag=	updateBMSLoanState(loanBaseEntity);
		if(updatFlag){
			//调用接口更新合同
			reqLoanVo.setState(EnumConstants.FINANCE_LOAN_BACK);
			backFlag=updateCoreLoanState(reqLoanVo);
		}else{
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}	
		//记一二级原因 后面修改为前台传
		LoanExtEntity loanExtEntity = new LoanExtEntity();
		loanExtEntity.setLoanBaseId(reqLoanVo.getId());
		loanExtEntity.setPrimaryReason(reqLoanVo.getFirstLevleReasons());
		loanExtEntity.setSecodeReason(reqLoanVo.getTwoLevleReasons());
		loanExtEntity.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
		loanExtEntity.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
    	loanExtEntity.setModifier(reqLoanVo.getServiceName());
		loanExtEntity.setModifier(reqLoanVo.getServiceCode());
		loanExtEntity.setModifiedTime(new Date());
		loanExtDao.update(loanExtEntity);
		//借款日志
		BMSSysLoanLog loanLog= new BMSSysLoanLog();
		loanLog.setLoanBaseId(reqLoanVo.getId());
		loanLog.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
		loanLog.setLoanNo(reqLoanVo.getLoanNo());
		loanLog.setOperationTime(new Date());
		loanLog.setOperator(reqLoanVo.getServiceName());
		loanLog.setOperatorCode(reqLoanVo.getServiceCode());
		loanLog.setStatus(EnumConstants.LoanStatus.PASS.getValue());
		loanLog.setRtfState(EnumConstants.RtfState.FKQR.getValue());
		loanLog.setRtfNodeState(EnumConstants.RtfNodeState.FKQRRETURN.getValue());
		loanLog.setOperationType(EnumConstants.OptionType.RETRUN.getValue());
		loanLog.setFirstLevleReasons(reqLoanVo.getFirstLevleReasons());
		loanLog.setTwoLevleReasons(reqLoanVo.getTwoLevleReasons());
		loanLog.setFirstLevleReasonsCode(reqLoanVo.getFirstLevleReasonsCode());
		loanLog.setTwoLevleReasonsCode(reqLoanVo.getTwoLevleReasonsCode());
		loanLog.setRemark(reqLoanVo.getRemark());
		ibmsSysLoanLogService.saveOrUpdate(loanLog);
		
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|退回|back","ILoanExecuter|backLoan","放款确认退回");

		return backFlag;
	}
	
	//更新借款系统借款状态
	private boolean  updateBMSLoanState(BMSLoanBase  loanBaseEntity){
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			BmsLogger.error("借款更新失败！借款编号："+loanBaseEntity.getLoanNo());
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
		}
		return true;
	}
	
	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanVo reqLoanVo){
		Map<String,Object> loanDataMap  = new HashMap<String,Object>();
		//构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBase  loanBaseEntity  = new BMSLoanBase(reqLoanVo.getLoanNo(),
				reqLoanVo.getServiceCode(),reqLoanVo.getState());
		
		loanDataMap.put("loanNo", loanBaseEntity.getLoanNo());
		loanDataMap.put("userCode",loanBaseEntity.getUserCode());
		loanDataMap.put("stateCode",loanBaseEntity.getState());
		//调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);
		
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			BmsLogger.error("更新核心系统借款状态失败！借款编号："+loanBaseEntity.getLoanNo());
			throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, contractReturnEntity.getMessage());	
		}else{
			return true;
		}
	}

	@Override
	public Response<BMSLoanConfirmationQuery> auditCommit(ReqBMSWmxtVO vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("accountNo", vo.getAccountNo());
		BMSLoanConfirmationQuery isSuccess=loanBaseDao.auditCommit(map);
		Response<BMSLoanConfirmationQuery> response=new Response<BMSLoanConfirmationQuery>();
		response.setData(isSuccess);
		return response;
	}

	@Override
	public Response<Integer> findLoanIdbyFeeInfo(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		Integer id=reqBMSLoanBaseVO.getId();
		Integer returnId=loanBaseDao.findLoanIdbyFeeInfo(id);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnId);
		return response;
	}
	
	@Override
	public Response<ResBMSLoanImportVO> queryUserLoanInfo(ReqImportExcelVO reqImportExcelVO) {
		// TODO Auto-generated method stub
		Response<ResBMSLoanImportVO> response=new Response<ResBMSLoanImportVO>();
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqImportExcelVO);
			response.setData(aPPPersonInfoDao.findImportLoanInfo(paramMap));
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,"参数转换异常");
		}
		return response;
	}

	@Override
	public Map<String, Object> grantLoanCore(ReqLoanVo reqLoanVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNos", reqLoanVo.getLoanNos());
		List<BMSLoanBase> loanBaseEntitys = loanBaseDao.listBy(paramMap);
		
		List<Map<String,Object>> msgSb = new ArrayList<Map<String,Object>>();// 前台展示
		for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
			// 更新借款状态
			loanBaseEntity.setStatus(EnumConstants.LoanStatus.NORMAL.getValue());
			loanBaseEntity.setRtfState(EnumConstants.RtfState.DHHK.getValue());
			loanBaseEntity.setLoanDate(new Date());
			loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
			long updatFlag = loanBaseDao.update(loanBaseEntity);
			BMSSysLoanLog loanLog2 = new BMSSysLoanLog();
			// 借款日志
			loanLog2.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
			loanLog2.setLoanBaseId(loanBaseEntity.getId());
			loanLog2.setLoanNo(loanBaseEntity.getLoanNo());
			loanLog2.setOperationTime(new Date());
			loanLog2.setOperator(reqLoanVo.getServiceName());
			loanLog2.setOperatorCode(reqLoanVo.getServiceCode());
			loanLog2.setStatus(EnumConstants.LoanStatus.PASS.getValue());
			loanLog2.setRtfState(EnumConstants.RtfState.FKQR.getValue());
			loanLog2.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
			loanLog2.setOperationType(EnumConstants.OptionType.LOAN.getValue());
			ibmsSysLoanLogService.saveOrUpdate(loanLog2);
			if (updatFlag > 0) {
				TaskOpinion option = new TaskOpinion("通过");
				taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(), reqLoanVo.getServiceCode(),
						EnumConstants.WFPH_JS, option);
			}
			Map<String,Object> msgMap = new HashMap<String,Object>();
			msgMap.put("isSuccess","true");
			msgMap.put("loanNo", loanBaseEntity.getLoanNo());
			msgMap.put("message", "放款确认成功;");
			msgSb.add(msgMap);

		}
		retMap.put("msg", msgSb);
		String grantTypeStr = loanBaseEntitys.size() > 1 ? "批量通过" : "通过";
		String grantType = loanBaseEntitys.size() > 1 ? "batchPass" : "pass";
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|" + grantTypeStr + "|" + grantType,
				"ILoanExecuter|grantLoan", "财务管理|放款确认|" + grantTypeStr + "|" + grantType);
		return retMap;
	}

	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public  Map<String, Object> grantLoanByLuJS(ReqLoanVo reqLoanVo) {
	    Map returnMap = new HashMap();
			String loanNo=reqLoanVo.getLoanNo();
			//调用核心接口
			GrantLoanEntity grantLoanEntity=new GrantLoanEntity();
			grantLoanEntity.setUserCode(reqLoanVo.getUserCode());
			List<GrantInfo> infos =new ArrayList<GrantInfo>();
			GrantInfo grantInfo= new GrantInfo();
			grantInfo.setLoanNo(loanNo);
			grantInfo.setBatchNum("");
			infos.add(grantInfo);
			grantLoanEntity.setInfos(infos);
			HttpResponse httpResponse = coreHttpService.grantLoanURL(grantLoanEntity);
			if(httpResponse==null||"200".equals(httpResponse.getCode())){
				returnMap.put("return_code", "9999");
				returnMap.put("return_msg", "调用证大核心系统放款确认接口失败");
			}
			HttpLoanReturnEntity httpLoanReturnEntity= JsonUtils.decode(httpResponse.getContent(),HttpLoanReturnEntity.class);
			//判断调用推送放款款接口是否成功
			if ("000000".equals(httpLoanReturnEntity.getCode())){
				// 更新借款状态
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanNo", loanNo);
				BMSLoanBase loanBaseEntity = loanBaseDao.getBy(paramMap);
				loanBaseEntity.setStatus(EnumConstants.LoanStatus.NORMAL.getValue());
				loanBaseEntity.setRtfState(EnumConstants.RtfState.DHHK.getValue());
				loanBaseEntity.setLoanDate(new Date());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
				long updatFlag = loanBaseDao.update(loanBaseEntity);
				BMSSysLoanLog loanLog2 = new BMSSysLoanLog();
				// 借款日志
				loanLog2.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
				loanLog2.setLoanBaseId(loanBaseEntity.getId());
				loanLog2.setLoanNo(loanBaseEntity.getLoanNo());
				loanLog2.setOperationTime(new Date());
				loanLog2.setOperator(reqLoanVo.getServiceName());
				loanLog2.setOperatorCode(reqLoanVo.getServiceCode());
				loanLog2.setStatus(EnumConstants.LoanStatus.PASS.getValue());
				loanLog2.setRtfState(EnumConstants.RtfState.FKQR.getValue());
				loanLog2.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
				loanLog2.setOperationType(EnumConstants.OptionType.LOAN.getValue());
				ibmsSysLoanLogService.saveOrUpdate(loanLog2);
				
				String grantTypeStr ="通过";
				String grantType = "pass";
				ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|" + grantTypeStr + "|" + grantType,
						"ILoanExecuter|grantLoan", "财务管理|放款确认|" + grantTypeStr + "|" + grantType);
				if (updatFlag > 0) {
					TaskOpinion option = new TaskOpinion("通过");
					taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(), reqLoanVo.getServiceCode(),
							EnumConstants.WFPH_JS, option);
				}
			}else{
				returnMap.put("return_code", "9999");
				returnMap.put("return_msg", "调用证大核心系统放款确认接口返回失败信息");
			}

		return returnMap;
	}*/
}
