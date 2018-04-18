package com.ymkj.cms.biz.service.finance.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.loan.BaseLoan;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.finance.GrantInfo;
import com.ymkj.cms.biz.entity.finance.GrantLoanEntity;
import com.ymkj.cms.biz.entity.finance.HttpLoanReturnEntity;
import com.ymkj.cms.biz.entity.finance.ResLoanFailEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

/**
 * 放款确认  通过
 * 
 * @author YM10138
 *
 */
@Service
public class GrantLoan2Impl extends BaseLoan<ReqLoanVo,ResLoanVo> {

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
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		// 参数校验	
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if(reqLoanVo.getLoanList() == null && reqLoanVo.getLoanList().size()==0){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"LoanList"}), res);
			return false;
		}
		
		//流程校验
		List<ReqLoanVo> loanList=	reqLoanVo.getLoanList();
		List<ResLoanVo> failLoanList=res.getData().getFailList();
		for (ReqLoanVo reqLoanVo2 : loanList) {
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanVo2.getId()));
			if(task == null || !EnumConstants.WF_FKQR.equals(task.getTaskName())){
				ResLoanVo viTaskFailLoanVO = new ResLoanVo();
				viTaskFailLoanVO.setLoanNo(reqLoanVo2.getLoanNo());
				viTaskFailLoanVO.setErrorMessage(BizErrorCode.UFLOTASK_EOERR.getDefaultMessage());
				failLoanList.add(viTaskFailLoanVO);//getfailLoanList.add(viTaskFailLoanVO);
				loanList.remove(reqLoanVo2);
			}
		}

		return true;
	}

	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		GrantLoanEntity grantLoanEntity= new GrantLoanEntity();	
		List<GrantInfo> infos  = new ArrayList<GrantInfo>();
		List<ReqLoanVo> loanList = reqLoanVo.getLoanList();
		List<BMSLoanBase>  loanBaseEntitys = new ArrayList<BMSLoanBase>();
		//新增：需要返回的成功失败的list,其中只有失败loanNo以及errorMessage有值，errorMessage留着备用
		List<ResLoanVo> successLoanList = new ArrayList<ResLoanVo>();
		List<ResLoanVo> failLoanList = res.getData().getFailList();
		
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
							boolean updatFlag=	updateBMSLoanState(loanBaseEntity,res);
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
		//以下为新增内容，因为需要返回失败的以及成功的记录
		ResLoanVo resLoanVo = new ResLoanVo();
		resLoanVo.setFailList(failLoanList);//失败的借款集合
		resLoanVo.setSuccessList(successLoanList);//成功的借款集合
		res.setData(resLoanVo);
		String grantTypeStr=loanList.size()>1?"批量通过":"通过";
		String grantType=loanList.size()>1?"batchPass":"pass";
		ibmsSysLogService.recordSysLog(reqLoanVo, "财务管理|放款确认|"+grantTypeStr+"|"+grantType,"ILoanExecuter|grantLoan",grantTypeStr+"|"+grantType);
		return true;
	}

	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		//完成竞争任务
		TaskOpinion option =new TaskOpinion("通过");
		BmsLogger.info(reqLoanVo.getServiceCode()+"：放款确认(uflo)loanBaseId"+reqLoanVo.getId());
		taskService.compUsersTaskByLoanBaseId(reqLoanVo.getId(), reqLoanVo.getServiceCode(), EnumConstants.WFPH_TG,option);
		return true;
	}
	//更新借款系统借款状态
	private boolean  updateBMSLoanState(BMSLoanBase loanBaseEntity, Response<ResLoanVo> res){
		long count=  loanBaseDao.update(loanBaseEntity);
		if(count !=1){
			setError(new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败"), res);
			return false;
		}
		return true;
	}
}
