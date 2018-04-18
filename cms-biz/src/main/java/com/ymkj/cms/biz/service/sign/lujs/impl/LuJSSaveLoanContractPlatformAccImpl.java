package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSBankService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * 保存陆金所用户信息（陆金所）
 * 
 * @author YM10138
 *
 */
@Service
public class LuJSSaveLoanContractPlatformAccImpl extends SaveLoanContractBankAccImpl {

	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private LoanExtDao loanExtDao;
	
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	@Autowired
	private IBMSBankService bankService;
	
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	
	@Autowired
	private ICoreHttpService coreHttpService;
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private ILuJSInformService luJSInformService;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(
				reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}

		if (reqLoanContractSignVo.getId() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "id" }), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getBankPhone())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "bankPhone" }), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankCardNo())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "applyBankCardNo" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getApplyBankCardNo().length() > 39) {
			setError(new BizException(CoreErrorCode.VALIDATE_OVERLENGTH, new Object[] { "applyBankCardNo" }), res);
			return false;
		}
		if (!StringUtils.checkBankCard(reqLoanContractSignVo
				.getApplyBankCardNo())) {
			setError(new BizException(BizErrorCode.BANK_CARD_NO_VAULE_EOERR, "银行卡格式不正确"), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankBranch())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "applyBankBranch" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getApplyBankBranch().length() > 69) {
			setError(new BizException(CoreErrorCode.VALIDATE_OVERLENGTH, new Object[] { "applyBankBranch" }), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getApplyBankName())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "applyBankName" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getApplyBankNameId() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "applyBankNameId" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" }), res);
			return false;
		}
		
		if (reqLoanContractSignVo.getIdLastDate() == null) {
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "idLastDate" }), res);
			return false;
		} else {
			//Date1.after(Date2),当Date1大于Date2时，返回TRUE
			Date now = new Date();
			if(!reqLoanContractSignVo.getIdLastDate().after(now)){
				setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "证件到期日：必填且大于系统当前日期"), res);
				return false;
			}
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLujsName())) {
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "lujsName" }), res);
			return false;
		}
		
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);

		//无反欺诈结果，提示等待
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", loanInfo.getId());
		paramMap.put("channelCode", loanInfo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		if(lockTargetEntity != null && "Y".equals(lockTargetEntity.getLockTarget())){
			Map<String, Object> luJSInformParamMap = new HashMap<String, Object>();
			luJSInformParamMap.put("loanNo", loanInfo.getLoanNo());
			//数据类型
			List<String> informResultList = new ArrayList<String>();
			informResultList.add(EnumConstants.LuJSnotifyCode.FQZTG.getCode());
			informResultList.add(EnumConstants.LuJSnotifyCode.FQZJJ.getCode());
			luJSInformParamMap.put("informResultList", informResultList);
			// 调用业务逻辑
			BMSLuJSInform luJSInform = luJSInformService.getBy(luJSInformParamMap);
			if(luJSInform == null){
				//无反欺诈结果，提示等待
				res.setRepMsg("陆金所未返回反欺诈结果，请稍后再试。");
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("1");
				
				return false;
			} else if(EnumConstants.LuJSnotifyCode.FQZTG.getCode().equals(luJSInform.getInformResult())){
				//反欺诈结果为通过,不需调用反欺诈，
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("2");
				
			} else if(EnumConstants.LuJSnotifyCode.FQZJJ.getCode().equals(luJSInform.getInformResult())){
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("0");
			}
		}
		// 业务校验
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String
				.valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_PLATFROM_BCXX.equals(task.getTaskName())) {
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		reqLoanContractSignVo.setTaskName(task.getTaskName());
		
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		
		Map<String, Object> httpParamMap = new HashMap<String, Object>();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
			
		//陆金所ID校验接口
		//依据id查找code
		ReqBMSBankVO reqBankVO = new ReqBMSBankVO();
		reqBankVO.setId(Long.valueOf(reqLoanContractSignVo.getApplyBankNameId()));
		BMSBank bank = bankService.findOne(reqBankVO);
		if(bank == null || StringUtils.isEmpty(bank.getCode())){
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "该银行不可银行"), res);
			return false;
		}
		String bankCode = bank.getCode();
		//中国平安银行  转，深圳发展银行，传个陆金所
		if("04105840".equals(bankCode)){
			bankCode = "307";
		}
		
		httpParamMap.put("funcId", "lufax100004");
		
		params.put("projectNo", "aps");
		requestMap.put("product_type", "1000500010");	//业务类型
		requestMap.put("lufax_borrower_username", reqLoanContractSignVo.getLujsName());  			//陆金所网站用户名
		requestMap.put("identitycard_type", "Ind01");
		requestMap.put("identitycard_no", loanInfo.getIdNo());
		requestMap.put("mobile_no", reqLoanContractSignVo.getBankPhone());
		requestMap.put("anshuo_loan_accept_id", "ZDJR_"+loanInfo.getLoanNo());	//贷款申请码  规则：外部机构简称+“_”+外部机构生成的申请号
//		requestMap.put("loanee_bank_code", bankCodeByName(reqLoanContractSignVo.getApplyBankName()));//银行编码 信保：放款帐户银行编码,网站映射表cgi_lufax_bank_mapping
		requestMap.put("loanee_bank_code", bankCode);//银行编码 信保：放款帐户银行编码,网站映射表cgi_lufax_bank_mapping
		requestMap.put("loanee_bank_account", reqLoanContractSignVo.getApplyBankCardNo());//银行卡号
		
		params.put("reqParam", requestMap);
		httpParamMap.put("params", params);
		
		if(lujsInterfaceService.lufaxGateIdCheck(httpParamMap, res)){
			
			if(httpParamMap.get("lufax_loan_req_id") != null){
				reqLoanContractSignVo.setLujsLoanReqId(httpParamMap.get("lufax_loan_req_id").toString());
				
				LoanExtEntity entity = new LoanExtEntity();
				entity.setLoanBaseId(loanInfo.getId());
				entity.setLujsLoanReqId(httpParamMap.get("lufax_loan_req_id").toString());
				entity.setLujsName(reqLoanContractSignVo.getLujsName());
				entity.setModifier(reqLoanContractSignVo.getServiceCode());
				long udpateNum = loanExtDao.update(entity);
				if(udpateNum !=1){
					throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
				}
			}
			//对证件到期日期进行保存
			APPPersonInfoEntity appPersonInfoEntity = new APPPersonInfoEntity();
			appPersonInfoEntity.setLoanBaseId(loanInfo.getId());
			appPersonInfoEntity.setIdLastDate(reqLoanContractSignVo.getIdLastDate());
			appPersonInfoEntity.setModifier(reqLoanContractSignVo.getServiceCode());
			long udpateNum = appPersonInfoDao.update(appPersonInfoEntity);
			if(udpateNum !=1){
				throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
			} else {
				// 调用推送核心接口
				if (!coreHttpService.pushCore(reqLoanContractSignVo, res) )
					throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
			}
			// 系统日志
			ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存3|save", "ILoanContractSignExecuter",
					"保存陆金所用户信息推送债权");
			
			
			return true;
			
		}
		
		return false;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
}
