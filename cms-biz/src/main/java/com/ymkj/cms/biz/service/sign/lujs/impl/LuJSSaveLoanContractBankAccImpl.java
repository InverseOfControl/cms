package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * 签约 保存合同银行信息
 * 
 * @author YM10138
 *
 */
@Service
public class LuJSSaveLoanContractBankAccImpl extends SaveLoanContractBankAccImpl {

	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	@Autowired
	private ILuJSInformService luJSInformService;
	
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		//反欺诈调用标志，false需要调用，true不需要
		boolean antiFraudFlag = false;
		//反欺诈接口调用是否第二次判断，依据流程判断，如果是第二次调用，
		//无反欺诈结果，提示等待
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", loanInfo.getId());
		paramMap.put("channelCode", loanInfo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		//第二次调用，
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
				antiFraudFlag = true;
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("2");
			}
		}
		
		//防欺诈接口调用
		Map<String, Object> httpParamMap = new HashMap<String, Object>();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		httpParamMap.put("funcId", "lufax100008");
		params.put("projectNo", "aps");
		requestMap.put("iCode", "antiFraud");
		requestMap.put("name", loanInfo.getName());
		requestMap.put("identityType", "Ind01");
		requestMap.put("idNumber", loanInfo.getIdNo());
		requestMap.put("mobile", reqLoanContractSignVo.getBankPhone());
		requestMap.put("applNoHost", "ZDJR_"+loanInfo.getLoanNo());	//外部申请号
		
		requestMap.put("parentProductNo", "1000500010");
		//依据不同的产品设置大小类
		if ("00015".equals(loanInfo.getProductCd())) {
			requestMap.put("productNo", "1000500010001"); // 产品小类
		} else if ("00014".equals(loanInfo.getProductCd())) {
			requestMap.put("productNo", "1000500010002"); // 产品小类
		} else if ("00018".equals(loanInfo.getProductCd())) {
			requestMap.put("productNo", "1000500010003"); // 产品小类
		}
		requestMap.put("cardNo", reqLoanContractSignVo.getApplyBankCardNo());	//银行卡号

			
		params.put("reqParam", requestMap);
		httpParamMap.put("params", params);
		
		
		
		if(antiFraudFlag || lujsInterfaceService.lufaxGateAntiFraud(httpParamMap, res)){
			String lockTarget = null;
			if(httpParamMap.get("lockTarget") != null){
				lockTarget = httpParamMap.get("lockTarget").toString();
			}
			if(lockTarget != null && "Y".equals(lockTarget)){

				if(lockTargetEntity != null){
					lockTargetEntity.setLockTarget(lockTarget);
					lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
					long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
					if(udpateNum !=1){
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新失败");
					}
				} else {
					//锁定数据
					BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
					saveEntity.setLoanBaseId(loanInfo.getId());
					saveEntity.setLoanNo(loanInfo.getLoanNo());
					saveEntity.setChannelCode(loanInfo.getChannelCd());
					saveEntity.setLockTarget(lockTarget);
					saveEntity.setCreator(reqLoanContractSignVo.getServiceCode());
					saveEntity.setModified(reqLoanContractSignVo.getServiceCode());
					long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
					if(udpateNum !=1){
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "插入错误");
					}
					
				}
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("1");
				res.setRepMsg("反欺诈校验中，请稍后...");
			}
			if(antiFraudFlag){
				if(res.getData()==null){
					res.setData(new ResLoanContractSignVo());
				}
				res.getData().setLujsAntiFraud("2");
			}
		} else {
			return false;
		}
		// 保存借款银行账号信息组装参数
		BMSLoanProduct bmsLoanProduct = new BMSLoanProduct(reqLoanContractSignVo);
		bmsLoanProduct.setVersion(reqLoanContractSignVo.getPversion());
		bmsLoanProduct.setModifiedDate(new Date());
		// 保存借款银行信息
		long count = loanContractSignService.update(bmsLoanProduct);
		if (count != 1) {
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
		
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存2|save", "ILoanContractSignExecuter",
				"保存银行账号信息");
		return true;
				
		
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = null;
		if(res.getData()==null){
			resLoanSignVo = new ResLoanContractSignVo();
		} else {
			resLoanSignVo = res.getData();
		}
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		if(res.getData() != null && "2".equals(res.getData().getLujsAntiFraud())){
			// 完成任务
			try {
				taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
						EnumConstants.WFPH_BC);
			} catch (BizException e) {
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e);
			}
		}
		return true;
	}
	
	private String bankCodeByName(String bankName){
		String bankCode ="";
		if(bankName.indexOf("工商银行") >= 0||"102".equals(bankName)){
			bankCode = "102";
		}else if(bankName.indexOf("农业银行") >= 0||"103".equals(bankName)){
			bankCode = "103";
		}else if(bankName.indexOf("中国银行") >= 0||"104".equals(bankName)){
			bankCode = "104";
		}else if(bankName.indexOf("建设银行") >= 0||"105".equals(bankName)){
			bankCode = "105";
		}else if(bankName.indexOf("平安银行") >= 0||"04105840".equals(bankName)){
			bankCode = "307";
		}else if(bankName.indexOf("中信银行") >= 0||"302".equals(bankName)){
			bankCode = "302";
		}else if(bankName.indexOf("上海浦东发展银行") >= 0||"310".equals(bankName)){
			bankCode = "310";
		}else if(bankName.indexOf("民生银行") >= 0||"305".equals(bankName)){
			bankCode = "305";
		}else if(bankName.indexOf("光大银行") >= 0||"303".equals(bankName)){
			bankCode = "303";
		}
		return bankCode;
	}

}
