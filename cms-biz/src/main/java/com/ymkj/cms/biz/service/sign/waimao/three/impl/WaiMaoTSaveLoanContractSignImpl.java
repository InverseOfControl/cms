package com.ymkj.cms.biz.service.sign.waimao.three.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractSignImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保存合同签约信息
 * 
 * @author YM10138
 *
 */
@Service
public class WaiMaoTSaveLoanContractSignImpl extends SaveLoanContractSignImpl {
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//推送数据判断  标识已被拒绝过渠道
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", reqLoanContractSignVo.getChannelCd());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		
		if(lockTargetEntity != null && "N".equals(lockTargetEntity.getLockTarget())){
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, "渠道审核未通过，请改签其他渠道！"), res);
			return false;
		}
		
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
}
