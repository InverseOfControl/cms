package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * @Description:人工审核（陆金所）</p>
 * @uthor YM10159
 * @date 2017年7月7日 上午11:15:33
 */
@Service
public class LUJSManualAuditImpl extends BaseSign<ResLoanContractSignVo>{

	@Autowired
	private ITaskService taskService;

	@Autowired
	private LUJSInterfaceService interfaceService;

	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;

	@Autowired
	private IBMSLoanChannelLockTargetDao  loanChannelLockTargetDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res){
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(BizErrorCode.EOERR, validateResponse.getRepMsg()), res);
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
		if (StringUtils.isBlank(reqLoanContractSignVo.getChannelCd())) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "channelCd" }), res);
			return false;
		}

		Task task = taskService.findTaskByLoanBaseId(ObjectUtils.toString(reqLoanContractSignVo.getId()));
		if (task == null || !EnumConstants.WF_FKSH.equals(task.getTaskName())) {
			setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//删除陆金人工审核结果
		interfaceService.delLujsManualAnditInfo(reqLoanContractSignVo.getLoanNo());

		//保存陆金所通知信息
		interfaceService.inNoticeExternal(reqLoanContractSignVo.getReqLufax800001Vo());

		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		/*ReqLufax800001Vo reqLufax800001Vo = reqLoanContractSignVo.getReqLufax800001Vo();
		String informResult = reqLufax800001Vo.getNotify_code();
		String loanBaseId = ObjectUtils.toString(reqLoanContractSignVo.getId());

		if(informResult.equals("013")){
			taskService.completeTaskByLoanBaseId(Long.valueOf(loanBaseId), EnumConstants.WFPH_RGSH_LUJS_RETURN1);
		}
		if(informResult.equals("00401") || informResult.equals("00201")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanBaseId", loanBaseId);
			paramMap.put("channelCode", EnumConstants.channelCode.LUJS.getValue());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);

			if(lockTargetEntity != null){
				lockTargetEntity.setLockTarget("N");
				lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
				long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
			}
			taskService.completeTaskByLoanBaseId(Long.valueOf(loanBaseId), EnumConstants.WFPH_RGSH_LUJS_RETURN2);
		}*/
		return true;
	}
}
