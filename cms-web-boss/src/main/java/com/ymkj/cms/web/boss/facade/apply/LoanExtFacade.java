package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLoanExtExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class LoanExtFacade extends BaseFacade{

	@Autowired
	private IBMSLoanExtExecuter bmsLoanExtExecuter;

	

	public PageResult<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqLoanExtVO) {
		reqLoanExtVO.setSysCode("1111111"); 
		// 业务调用
		PageResponse<ResBMSLoanExtVO> pageResponse = bmsLoanExtExecuter.listPage(reqLoanExtVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanExtVO> pageResult = new PageResult<ResBMSLoanExtVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
}
