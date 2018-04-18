package com.ymkj.cms.biz.service.sign.lujs.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILuJSInformDao;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSChannelService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.base.CreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生成合同
 * @author 李璇
 * @date 2017年7月14日 下午4:30:59
 */
@Service
public class LuJSCreateLoanContractImpl extends CreateLoanContractImpl {

	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	@Autowired
	private  ILuJSInformDao luJSInformDao;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {

		//查询反欺诈结果
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
		List<String> informResultList = new ArrayList<String>();
		informResultList.add(EnumConstants.LuJSnotifyCode.FQZTG.getCode());
		informResultList.add(EnumConstants.LuJSnotifyCode.FQZJJ.getCode());
		paramMap.put("informResultList", informResultList);
		BMSLuJSInform appLujsInform  = luJSInformDao.getBy(paramMap);
		
		if(appLujsInform != null && appLujsInform.getInformResult() != null){
			if("00301".equals(appLujsInform.getInformResult())){
				setError(new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, "陆金所反欺诈拒绝，原因是"+appLujsInform.getInformDesc()+"。请改签其他合同来源！"), res);
				return false;
			}else if("004".equals(appLujsInform.getInformResult())){
				BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
				//查询锁定标志
				paramMap.put("channelCode", loanInfo.getChannelCd());
				BMSLoanChannelLockTargetEntity loanChannelLockTarget = loanChannelLockTargetDao.getBy(paramMap);
				
				if (loanChannelLockTarget != null && !"Y".equals((loanChannelLockTarget.getLockTarget()))) {
					setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "该标的不能签约陆金所合同来源。"), res);
					return false;
				}
		
				return super.execute(reqLoanContractSignVo, res);
			}else{
				setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "陆金所未返回反欺诈结果，请稍后再试"), res);					
				return false;
			}
		}else{
			setError(new BizException(BizErrorCode.REQUEST_PARAM_ERROR, "陆金所未返回反欺诈结果，请稍后再试"), res);					
			return false;
		}
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
}
