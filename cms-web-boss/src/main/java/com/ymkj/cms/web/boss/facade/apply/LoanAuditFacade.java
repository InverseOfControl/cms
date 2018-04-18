package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLoanAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanAuditVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanAuditVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class LoanAuditFacade extends BaseFacade{

	@Autowired
	private IBMSLoanAuditExecuter bmsLoanAuditExecuter;

	

	public PageResult<ResBMSLoanAuditVO> listPage(ReqBMSLoanAuditVO reqLoanAuditVO) {
		reqLoanAuditVO.setSysCode("1111111"); 
		// 业务调用
		PageResponse<ResBMSLoanAuditVO> pageResponse = bmsLoanAuditExecuter.listPage(reqLoanAuditVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanAuditVO> pageResult = new PageResult<ResBMSLoanAuditVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
}
