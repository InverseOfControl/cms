package com.ymkj.cms.biz.facade.audit;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.service.audit.IAduitPersonExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.audit.IAduitPersonService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class AduitPersonExecuter implements IAduitPersonExecuter {
	
	@Autowired
	private IAduitPersonService aduitPersonService;
	
	
	@Override
	public Response<ResBMSAduitPersonVo> findAduitPersonInfo(ReqBMSAduitPersonVo reqBMSReassignmentVo) {
		Response<ResBMSAduitPersonVo> res = new Response<ResBMSAduitPersonVo>();
		try {
			// 参数校验
			if(reqBMSReassignmentVo.getLoanNo()==null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
			}
			ResBMSAduitPersonVo vo = aduitPersonService.findAduitPersonInfo(reqBMSReassignmentVo);
			res.setData(vo);
			res.setRepMsg("success");
			res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
		} catch (CoreException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return res;
	}

}
