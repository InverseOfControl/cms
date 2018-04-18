package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLoanBaseRelaExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseRelaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseRelaVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class LoanBaseRelaFacade  extends BaseFacade{
	@Autowired
	private IBMSLoanBaseRelaExecuter BMSLoanBaseRelaExecuter;

	public PageResult<ResBMSLoanBaseRelaVO> listPage(ReqBMSLoanBaseRelaVO reqSysLoanLogVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSLoanBaseRelaVO> pageResponse = BMSLoanBaseRelaExecuter.listPage(reqSysLoanLogVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanBaseRelaVO> pageResult = new PageResult<ResBMSLoanBaseRelaVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
}
