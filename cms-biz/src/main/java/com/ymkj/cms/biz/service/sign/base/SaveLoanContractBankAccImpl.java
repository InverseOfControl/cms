package com.ymkj.cms.biz.service.sign.base;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 签约 保存合同银行信息
 * 
 * @author YM10138
 *
 */
@Service
public class SaveLoanContractBankAccImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;

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
		/*if (!StringUtils.pattern(StringUtils.REGEX_MOBILE,
				reqLoanContractSignVo.getBankPhone())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "手机号" }), res);
			return false;
		}*/

		if (reqLoanContractSignVo.getServiceCode() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "serviceCode" }), res);
			return false;
		}
		// 业务校验
		// 流程任务校验（非当前节点抛出异常）
		Task task = taskService.findTaskByLoanBaseId(String
				.valueOf(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_BCXX.equals(task.getTaskName())) {
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		reqLoanContractSignVo.setTaskName(task.getTaskName());
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 保存借款银行账号信息组装参数
		BMSLoanProduct bmsLoanProduct = new BMSLoanProduct(reqLoanContractSignVo);
		bmsLoanProduct.setVersion(reqLoanContractSignVo.getPversion());
		// 保存借款银行信息
		long count = loanContractSignService.update(bmsLoanProduct);
		if (count != 1) {
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0,"保存借款银行错误，请刷新页面");
		} else {
			// 调用推送核心接口
			if (!coreHttpService.pushCore(reqLoanContractSignVo, res) )
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"推送核心失败");
		}
		//删除原有的上传PIC文件
		loanContractSignService.checkChangeAndDelFile(reqLoanContractSignVo,res);
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存2|save", "ILoanContractSignExecuter",
				"保存银行账号信息推送债权");
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
		// 完成任务
		try {
			taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
					EnumConstants.WFPH_BC);
		} catch (BizException e) {
			throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e);
		}
		return true;
	}


}
