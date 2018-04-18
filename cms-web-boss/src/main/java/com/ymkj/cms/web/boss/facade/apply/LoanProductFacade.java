package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLoanProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class LoanProductFacade extends BaseFacade{
	
	@Autowired
	private IBMSLoanProductExecuter bmsLoanProductExecuter;

	

	public PageResult<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqLoanProductVO) {
		reqLoanProductVO.setSysCode("1111111"); 
		// 业务调用
		PageResponse<ResBMSLoanProductVO> pageResponse = bmsLoanProductExecuter.listPage(reqLoanProductVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanProductVO> pageResult = new PageResult<ResBMSLoanProductVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}


}
