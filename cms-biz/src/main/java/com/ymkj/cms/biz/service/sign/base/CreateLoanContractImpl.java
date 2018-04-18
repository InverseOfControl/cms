package com.ymkj.cms.biz.service.sign.base;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.dao.sign.IContractLoanDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.dao.sign.ILoanContractDao;
import com.ymkj.cms.biz.dao.sign.ILoanContractSignDao;
import com.ymkj.cms.biz.dao.sign.IRepaymentDetailDao;
import com.ymkj.cms.biz.entity.sign.*;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.impl.LoanContractGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成合同
 * 
 * @author YM10138
 *
 */
@Service
public class CreateLoanContractImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ILoanContractDao loanContractDao;
	@Autowired
	private IContractLoanDao contractLoanDao;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private IRepaymentDetailDao repaymentDetailDao;
	@Autowired
	private LoanContractGenerator loanContractGenerator;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private LoanProductService loanProductService;
	@Autowired
	private ILoanContractSignDao loanContractSignDao;
	@Autowired
	private ILoanContractSignService loanContractSignService;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate( reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "loanNo" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getId() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "id" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" }), res);
			return false;
		}
		// 业务校验
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String
				.valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_SCHT.equals(task.getTaskName())) {
			throw new BizException(BizErrorCode.UFLOTASK_EOERR);
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		String contractTypeCode=loanProductService.getContractType(reqLoanContractSignVo.getLoanNo());
		if (contractTypeCode == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "contractTypeCode" }), res);
			return false;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object>  retMap=new HashMap<String, Object>();
		String loanNo =reqLoanContractSignVo.getLoanNo();
		Long id =reqLoanContractSignVo.getId();
		paramMap.put("loanNo",loanNo);
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);

		paramMap.put("channelPushNo", ValidationUtils.composeAitePushLoanNo(reqLoanContractSignVo.getLoanNo(), loanInfo.getChannelPushFrequency()));
		if(EnumConstants.channelCode.LUJS.getValue().equals(reqLoanContractSignVo.getChannelCd())){
			
			paramMap.put("channelCd",reqLoanContractSignVo.getChannelCd());
			paramMap.put("lujsName",String.valueOf(loanInfo.getLujsName()));
			paramMap.put("lujsLoanReqId",String.valueOf(loanInfo.getLujsLoanReqId()));
		}
		String org = "0000000"; //OrganizationContextHolder.getCurrentOrg(); // 机构号
		String contractNum=""; 
		HttpResponse httpResponse =  coreHttpService.createBMSLoan(paramMap);
		String retStr = httpResponse.getContent();
		HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(retStr, HttpContractReturnEntity.class);
		
		if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
			setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息"), res);
			return false;

		}
		BMSLoanContract bmsLoanContract  = contractReturnEntity.getContract();//合同信息
		BMSContractLoan  bmsContractLoan = contractReturnEntity.getLoan();//合同借款信息
		List<BMSRepaymentDetail> repaylist = contractReturnEntity.getRepaymentDetail();//还款计划信息
		Long loanId= null;
		//保存合同信息	
		if(bmsLoanContract != null){
			if(loanContractDao.selectConutByLoanBaseId(id)>0){
				loanContractDao.deleteByLoanBaseId(id);
			}
			bmsLoanContract.setLoanBaseId(id);
			loanContractDao.insert(bmsLoanContract);
			loanId=bmsLoanContract.getLoanId();
			contractNum = bmsLoanContract.getContractNum();
		}
		
		//保存借款银行账号信息组装参数
		BMSLoanProduct bmsLoanProduct =new BMSLoanProduct();
		bmsLoanProduct.setContractNum(contractNum);
		bmsLoanProduct.setLoanBaseId(reqLoanContractSignVo.getId());
		long upProCount=loanContractSignDao.update(bmsLoanProduct);
		if(upProCount !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新合同编号失败，请检查参数");
		}
		//保存债权ID
		BMSLoanBaseEntity loanBaseEntity = new BMSLoanBaseEntity();
		loanBaseEntity.setLoanId(loanId);
		loanBaseEntity.setId(id);
		loanBaseEntity.setVersion(reqLoanContractSignVo.getVersion());
		long upCount= loanBaseEntityDao.update(loanBaseEntity);
		if(upCount !=1){
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0, "更新债权ID失败，请检查参数");
		}
		//保存合同借款信息	
		if(bmsContractLoan != null){
			if(contractLoanDao.selectConutByLoanBaseId(id)>0){
				contractLoanDao.deleteByLoanBaseId(id);
			}
			bmsContractLoan.setLoanId(loanId);
			bmsContractLoan.setLoanNo(loanNo);
			bmsContractLoan.setLoanBaseId(id);
			contractLoanDao.insert(contractReturnEntity.getLoan());
		}
		//保存还款计划	
		if(repaylist != null && repaylist.size()>0){
			if(repaymentDetailDao.selectConutByLoanBaseId(id)>0){
				repaymentDetailDao.deleteByLoanBaseId(id);
			}
			for (BMSRepaymentDetail bmsRepaymentDetail : repaylist) {
				bmsRepaymentDetail.setLoanBaseId(id);
			}
			repaymentDetailDao.batchInsert(repaylist);
		}
		retMap.put("code", contractReturnEntity.getCode());
		retMap.put("message", contractReturnEntity.getMessage());
		//生成合同开始
		org=null;
		//封装生成合同数据
		Map<String, Object> generatorMap = null;
		try {
			contractReturnEntity.getLoan().setLoanNo(loanNo);
			generatorMap = loanSignDataOprateService.setValueToContract(contractReturnEntity);
		} catch (Exception e1) {
			throw new BizException(BizErrorCode.CREATEFILE_EOERR, "生成合同异常");
		}
		loanSignDataOprateService.setRepayArrayToMap(generatorMap,repaylist);
		//生成合同
		generatorMap.put("contractTypeCode", contractTypeCode);
		generatorMap.put("channelCd", reqLoanContractSignVo.getChannelCd());
		generatorMap.put("serviceName", reqLoanContractSignVo.getServiceName());
		generatorMap.put("createDate", DateUtil.defaultFormatDay(new Date()));
		generatorMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
		loanContractGenerator.getContractTemplate(org,reqLoanContractSignVo.getChannelCd(), generatorMap, loanNo);
		//系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|生成合同|create","ILoanContractSignExecuter","createLoanContract");
		retMap.put("isSuceess",true);
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		// 完成任务
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_SCHT);
		} catch (Exception e) {
			setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage()), res);
			return false;
		}
		return true;
	}

}
