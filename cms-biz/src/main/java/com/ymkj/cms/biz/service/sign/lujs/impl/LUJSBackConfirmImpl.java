package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.base.BackConfirmImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:LUJSBackConfirmImpl</p>
 * <p>Description:陆金所合同确认退回</p>
 * @uthor YM10159
 * @date 2017年7月20日 上午11:30:55
 */
@Service
public class LUJSBackConfirmImpl extends BackConfirmImpl{
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	@Autowired
	private ILoanBaseEntityDao iLoanBaseEntityDao;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}
	
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		/*Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
		BMSLoanBaseEntity loanBaseEntity = iLoanBaseEntityDao.findSignInfo(paramsMap);
		
		paramsMap.put("aps_apply_no", loanBaseEntity.getLujsApplyNo());
		paramsMap.put("apply_no", "2");
		
		boolean bool = lujsInterfaceService.requestNotifyLujs(paramsMap, res);
		if(!bool){
			setError(new BizException(BizErrorCode.EOERR, "合同确认退回【外部机构通知进件接口失败】"),res);
			return false;
		}*/
		return super.execute(reqLoanContractSignVo, res);
	}
	
	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	
}
