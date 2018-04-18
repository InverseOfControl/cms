package com.ymkj.cms.web.boss.facade.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IIntegratedSearchExecuter;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;

@Component
public class IntegratedSearchFacade extends BaseFacade{
	
	@Autowired
	private IIntegratedSearchExecuter iIntegratedSearchExecuter;
	
	public List<ResBMSQueryLoanLogVO> queryLoanLog(ReqQueryLoanLogVO reqQueryLoanLogVO){
		reqQueryLoanLogVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<List<ResBMSQueryLoanLogVO>>  res=	iIntegratedSearchExecuter.queryLoanLog(reqQueryLoanLogVO);
		
		if(res.isSuccess()){
			return	res.getData();
		}else{
			return null;
		}
	}
	
}
