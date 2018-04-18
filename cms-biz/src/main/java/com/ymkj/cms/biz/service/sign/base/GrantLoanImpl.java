package com.ymkj.cms.biz.service.sign.base;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.entity.finance.*;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 放款确认  通过
 * 
 * @author YM10138
 *
 */
@Service
public class GrantLoanImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private IBMSLoanBaseDao loanBaseDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验	
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if(reqLoanContractSignVo.getLoanList() == null && reqLoanContractSignVo.getLoanList().size()==0){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"}), res);
			return false;
		}
		
		//流程校验
		List<ReqLoanVo> loanList=	reqLoanContractSignVo.getLoanList();
		for (ReqLoanVo reqLoanVo2 : loanList) {
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo2.getId()));
			if(task == null || !EnumConstants.WF_FKQR.equals(task.getTaskName())){
				setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		GrantLoanEntity grantLoanEntity= new GrantLoanEntity();	
		List<GrantInfo> infos  = new ArrayList<GrantInfo>();
		List<ReqLoanVo> loanList = reqLoanContractSignVo.getLoanList();
		
		List<BMSLoanBase>  loanBaseEntitys = new ArrayList<BMSLoanBase>();
		
		//将请求的reLoanVo 转换成loanBaseEntity
		for (ReqLoanVo reLoanVo2 : loanList) {
			BMSLoanBase loanBaseEntity = new BMSLoanBase(reLoanVo2);
			loanBaseEntity.setContractBranch(reLoanVo2.getContractBranch());
			loanBaseEntity.setContractBranchId(reLoanVo2.getContractBranchId());
			loanBaseEntitys.add(loanBaseEntity);
			GrantInfo info = new GrantInfo();
			info.setLoanNo(reLoanVo2.getLoanNo());
			if(reLoanVo2.getBatchNum()==null){
				info.setBatchNum("");
			}else{
				info.setBatchNum(reLoanVo2.getBatchNum());
			}
			infos.add(info);
		}
		grantLoanEntity.setInfos(infos);
		grantLoanEntity.setUserCode(reqLoanContractSignVo.getServiceCode());

		//调用推送放款款接口
		HttpResponse 	httpResponse = coreHttpService.grantLoanURL(grantLoanEntity);
		HttpLoanReturnEntity httpLoanReturnEntity= JsonUtils.decode(httpResponse.getContent(),HttpLoanReturnEntity.class);
		//判断调用推送放款款接口是否成功
		if (httpLoanReturnEntity !=null &&!"000000".equals(httpLoanReturnEntity.getCode())){
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, httpLoanReturnEntity.getMessage());
		}
		StringBuffer logSb =new StringBuffer();//日志
		StringBuffer msgSb =new StringBuffer();//前台展示
		List<ResLoanFailEntity> failList = httpLoanReturnEntity.getCreateFaiVOs();
		//新增：需要返回的失败的list,其中只有loanNo以及errorMessage有值
		List<ResLoanVo> failLoanList = new ArrayList<ResLoanVo>();
		if(failList !=null && failList.size()>0){
			for (ResLoanFailEntity resLoanFailEntity : failList) {
				logSb.append(resLoanFailEntity.getLoanNo());
				logSb.append(":");
				logSb.append(resLoanFailEntity.getErrorMessage());
				msgSb.append(resLoanFailEntity.getLoanNo());
				msgSb.append(":");
				msgSb.append("放款失败");
				//新增：将失败的记录放入list集合
				ResLoanVo failLoanVo = new ResLoanVo();
				BeanUtils.copyProperties(resLoanFailEntity, failLoanVo);
				failLoanList.add(failLoanVo);
			}
		}
		//新增：需要返回的成功的list,其中只有loanNo以及errorMessage有值，errorMessage留着备用
		List<ResLoanVo> successLoanList = new ArrayList<ResLoanVo>();
		for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
			boolean sucessFlag =true;
			for (ResLoanFailEntity resLoanFailEntity : failList) {
				if(resLoanFailEntity.getLoanNo().equals(loanBaseEntity.getLoanNo())){//失败
					sucessFlag=false;
					break;
				}
			}
			if(sucessFlag){//成功
				msgSb.append(loanBaseEntity.getLoanNo());
				msgSb.append(":");
				msgSb.append("放款确认成功;");
				logSb.append(loanBaseEntity.getLoanNo());
				logSb.append(":");
				logSb.append("放款确认成功;");
				//新增：将成功的记录放入list集合
				ResLoanVo successLoanVo = new ResLoanVo();
				BeanUtils.copyProperties(loanBaseEntity, successLoanVo);
				successLoanList.add(successLoanVo);
				//更新借款状态
				loanBaseEntity.setStatus(EnumConstants.LoanStatus.NORMAL.getValue());
				loanBaseEntity.setRtfState(EnumConstants.RtfState.DHHK.getValue());
				loanBaseEntity.setLoanDate(new Date());
				loanBaseEntity.setLoanBranch(loanBaseEntity.getContractBranch());
				loanBaseEntity.setLoanBranchId(loanBaseEntity.getContractBranchId());
				loanBaseEntity.setManageBranch(loanBaseEntity.getContractBranch());
				loanBaseEntity.setManageBranchId(loanBaseEntity.getContractBranchId());
				loanBaseEntity.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
				long updatFlag=	loanBaseDao.update(loanBaseEntity);
				BMSSysLoanLog loanLog2= new BMSSysLoanLog();
				//借款日志
				loanLog2.setOperationModule(EnumConstants.OptionModule.FINANCE_CONFIRM.getValue());
				loanLog2.setLoanBaseId(reqLoanContractSignVo.getId());
				loanLog2.setLoanNo(loanBaseEntity.getLoanNo());
				loanLog2.setOperationTime(new Date());
				loanLog2.setOperator(reqLoanContractSignVo.getServiceName());
				loanLog2.setOperatorCode(reqLoanContractSignVo.getServiceCode());
				loanLog2.setStatus(EnumConstants.LoanStatus.PASS.getValue());
				loanLog2.setRtfState(EnumConstants.RtfState.FKQR.getValue());
				loanLog2.setRtfNodeState(EnumConstants.RtfNodeState.FKQRSUBMIT.getValue());
				loanLog2.setOperationType(EnumConstants.OptionType.LOAN.getValue());
				ibmsSysLoanLogService.saveOrUpdate(loanLog2);
				if(updatFlag>0){
					TaskOpinion option =new TaskOpinion("通过");
					taskService.compUsersTaskByLoanBaseId(loanBaseEntity.getId(),reqLoanContractSignVo.getServiceCode(),EnumConstants.WFPH_JS,option);
				}
			}
		}	
		String grantTypeStr=loanList.size()>1?"批量通过":"通过";
		String grantType=loanList.size()>1?"batchPass":"pass";
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "财务管理|放款确认|"+grantTypeStr+"|"+grantType,"ILoanExecuter|grantLoan",logSb.toString());
		
		res.setRepMsg(msgSb.toString());
		//以下为新增内容，因为需要返回失败的以及成功的记录
		ResLoanContractSignVo resLoanVo = new ResLoanContractSignVo();
		resLoanVo.setFailList(failLoanList);//失败的借款集合
		resLoanVo.setSuccessList(successLoanList);//成功的借款集合
		res.setData(resLoanVo);
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//完成竞争任务
		TaskOpinion option =new TaskOpinion("通过");
		taskService.compUsersTaskByLoanBaseId(reqLoanContractSignVo.getId(), reqLoanContractSignVo.getServiceCode(), EnumConstants.WFPH_TG,option);
		return true;
	}

	//更新核心系统借款状态
	private boolean  updateCoreLoanState(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res){
		Map<String,Object> loanDataMap  = new HashMap<String,Object>();
		
		//构造 LoanNo 和 借款状态useCode 和 state
		BMSLoanBaseEntity bmsLoanBaseEntity = new BMSLoanBaseEntity(reqLoanContractSignVo.getLoanNo(),
				reqLoanContractSignVo.getServiceCode(),reqLoanContractSignVo.getState());
		
		loanDataMap.put("loanNo", bmsLoanBaseEntity.getLoanNo());
		loanDataMap.put("userCode",bmsLoanBaseEntity.getUserCode());
		loanDataMap.put("stateCode",bmsLoanBaseEntity.getState());
		//调用核心更新借款状态
		HttpResponse httpResponse = coreHttpService.updateCoreLoanState(loanDataMap);
		
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(httpResponse.getContent(), HttpContractReturnEntity.class);
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息"), res);
			return false;
		}else{
			return true;
		}
		
	}
	//更新借款系统借款状态
	private boolean  updateBMSLoanState(BMSLoanBase loanBaseEntity, Response<ResLoanContractSignVo> res){
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			setError(new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败，请刷新页面"), res);
			return false;
		}
		return true;
	}
}
