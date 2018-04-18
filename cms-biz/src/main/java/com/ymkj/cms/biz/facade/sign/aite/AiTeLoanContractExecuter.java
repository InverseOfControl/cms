package com.ymkj.cms.biz.facade.sign.aite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.MD5Utils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

@Service
public class AiTeLoanContractExecuter implements IAiTeLoanContractExecuter {

	@Autowired
	private ILoanContractSignService loanContractSignService;

	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	
	@Autowired
	private ITaskService taskService;
	
	// 网关密钥
	@Value("#{env['picSecretKey']?:''}")
	private String picSecretKey;
	
	@Override
	public Response<ResLoanContractSignVo> cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		try {
			// 参数校验
			if(reqLoanContractSignVo.getId()==null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
			}
			if(reqLoanContractSignVo.getLoanNo()==null || reqLoanContractSignVo.getLoanNo().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
			}
			if(reqLoanContractSignVo.getFirstLevleReasons()==null|| reqLoanContractSignVo.getFirstLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"});
			}
			if(reqLoanContractSignVo.getTwoLevleReasons()==null|| reqLoanContractSignVo.getTwoLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"twoLevleReasons"});
			}
			if(reqLoanContractSignVo.getFirstLevleReasonsCode()==null|| reqLoanContractSignVo.getFirstLevleReasonsCode().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"});
			}
			if(reqLoanContractSignVo.getTwoLevleReasons()==null|| reqLoanContractSignVo.getTwoLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"twoLevleReasonsCode"});
			}
			if(reqLoanContractSignVo.getServiceCode() ==null || reqLoanContractSignVo.getServiceCode().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" });
			}
			if(reqLoanContractSignVo.getServiceName() ==null || reqLoanContractSignVo.getServiceName().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceName" });
			}
			if(reqLoanContractSignVo.getIp() ==null || reqLoanContractSignVo.getIp().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "ip" });
			}

			boolean refuseFlag = aiTeLoanContractService.cancelLoanHasUflo(reqLoanContractSignVo);
			if (refuseFlag) {
				res.setRepMsg("success");
				res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
			} else {
				res.setRepMsg("取消失败，爱特不同意取消");
				res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode());
			}
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			res.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return res;
	}

	@Override
	public Response<ResLoanContractSignVo> refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		try {
			// 参数校验
			if(reqLoanContractSignVo.getId()==null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
			}
			if(reqLoanContractSignVo.getLoanNo()==null || reqLoanContractSignVo.getLoanNo().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
			}
			if(reqLoanContractSignVo.getFirstLevleReasons()==null|| reqLoanContractSignVo.getFirstLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasons"});
			}
			if(reqLoanContractSignVo.getTwoLevleReasons()==null|| reqLoanContractSignVo.getTwoLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"twoLevleReasons"});
			}
			if(reqLoanContractSignVo.getFirstLevleReasonsCode()==null|| reqLoanContractSignVo.getFirstLevleReasonsCode().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"firstLevleReasonsCode"});
			}
			if(reqLoanContractSignVo.getTwoLevleReasons()==null|| reqLoanContractSignVo.getTwoLevleReasons().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"twoLevleReasonsCode"});
			}
			if(reqLoanContractSignVo.getServiceCode() ==null || reqLoanContractSignVo.getServiceCode().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" });
			}
			if(reqLoanContractSignVo.getServiceName() ==null || reqLoanContractSignVo.getServiceName().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceName" });
			}
			if(reqLoanContractSignVo.getIp() ==null || reqLoanContractSignVo.getIp().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "ip" });
			}
			boolean refuseFlag = aiTeLoanContractService.refuseLoanHasUflo(reqLoanContractSignVo);
			if (refuseFlag) {
				res.setRepMsg("success");
				res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
			} else {
				res.setRepMsg("取消失败，爱特不同意拒绝");
				res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode());
			}
		} catch (BizException e) {
			res.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			res.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return res;
	}

	@Override
	public Response<ResLoanContractSignVo> signLoanContractHasTargetPushed(
			ReqLoanContractSignVo reqLoanContractSignVo) {

		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		try {
			if(reqLoanContractSignVo.getId() == null ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "id" });
			}
			if(reqLoanContractSignVo.getLoanNo() == null || reqLoanContractSignVo.getLoanNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "loanNo" });
			}
			if(reqLoanContractSignVo.getServiceCode() == null || reqLoanContractSignVo.getServiceCode().length() == 0 ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" });
			}
			if(reqLoanContractSignVo.getServiceName() == null || reqLoanContractSignVo.getServiceName().length() == 0 ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceName" });
			}
			if(reqLoanContractSignVo.getIp() == null || reqLoanContractSignVo.getIp().length() == 0 ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "ip" });
			}
			
			boolean notifyFalg = false;
			// 标的推送部分
			notifyFalg = aiTeLoanContractService.targetPushedToAiTe(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "推送失败");
			}
			
			// 调用合同确认通知捞财宝
			notifyFalg = aiTeLoanContractService.contractConfirmationToAiTe(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "推送失败");
			}
			
			// 央行征信报告上传到爱特ftp服务器
			notifyFalg = aiTeLoanContractService.creditInvestigationUploadFile(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
			}

			// 上海资信报告上传到爱特ftp服务器
			notifyFalg = aiTeLoanContractService.creditReportUploadFile(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用央行征信报告上传返回失败消息");
			}

			// 人身份证信息上传到爱特ftp服务器
			notifyFalg = aiTeLoanContractService.IDCardUploadFile(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "调用人身份证信息上传返回失败消息");
			}
			//标的锁定
			notifyFalg = aiTeLoanContractService.lockTarget(reqLoanContractSignVo);
			if (!notifyFalg) {
				throw new BizException(CoreErrorCode.DB_RESULT_ERROR, "操作失败");
			}
			//签订处理
//			notifyFalg = aiTeLoanContractService.signLoanContract(reqLoanContractSignVo);
			//流程校验
			Task task=	taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
			if(task !=null && !EnumConstants.WF_HTQD.equals(task.getTaskName())){
					throw new BizException(BizErrorCode.UFLOTASK_EOERR);
			}
			try {
				//完成当前任务
				taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),EnumConstants.WFPH_TSBD);
			} catch (Exception e) {
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常");
			}
			
			retMap.put("code", "000000");
			retMap.put("message", "向捞财宝（爱特）推送标的成功");
			
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|签约代办|合同签订（含标的推送）|confirm","AiTeLoanContractExecuter//signLoanContractHasTargetPushed","合同签订（含标的推送）");
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if(e.getArguments() != null && e.getArguments().length > 0){
				for (int i = 0; i < e.getArguments().length; i++) {
					message  += e.getArguments()[i];
					
				}
			}
			retMap.put("message", e.getErrorMsg()+":"+message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		return res;

	}

	@Override
	public Response<Object> queryRepaymentDetail(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<Object> res = new Response<Object>();
		// 参数校验
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			if(requestVo.getSignDate() == null ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "signDate" });
			}
			requestVo.setServiceCode(EnumConstants.AITE_USER_CODE);
			requestVo.setServiceName(EnumConstants.AITE_USER_NAME);
			requestVo.setSysCode(EnumConstants.AITE_SYSTEM_CODE);
			
			retMap = aiTeLoanContractService.queryRepaymentDetail(requestVo);
			retMap.put("message", "OK");
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if(e.getArguments() != null && e.getArguments().length > 0){
				for (int i = 0; i < e.getArguments().length; i++) {
					message  += e.getArguments()[i];
					
				}
			}
			retMap.put("message", e.getErrorMsg()+":"+message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(retMap.get("rDetailVOs"));
		return res;
	}

}
