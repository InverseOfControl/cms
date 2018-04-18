package com.ymkj.cms.web.boss.facade.apply;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.service.master.IBMSPatchBoltExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class PatchBoltFacade extends BaseFacade{
	@Autowired
	private IBMSPatchBoltExecuter iBMSPatchBoltExecuter;
	
	public PageResponse<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO){
		PageResponse<ResQueryLoanVo> response=iBMSPatchBoltExecuter.listPage(reqBMSPatchBoltVO);
		if(response.isSuccess()){
			return response;
		}else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
}
