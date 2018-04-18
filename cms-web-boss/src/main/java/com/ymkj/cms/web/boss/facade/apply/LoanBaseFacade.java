package com.ymkj.cms.web.boss.facade.apply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSLoanBaseExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class LoanBaseFacade extends BaseFacade {
	@Autowired
	private IBMSBankExecuter bmsBankExecuter;
	@Autowired
	private IBMSLoanBaseExecuter bmsLoanBaseExecuter;

	

	public PageResult<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqLoanBaseVO) {
		reqLoanBaseVO.setSysCode("1111111"); 
		// 业务调用
		PageResponse<ResBMSLoanBaseVO> pageResponse = bmsLoanBaseExecuter.listPage(reqLoanBaseVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanBaseVO> pageResult = new PageResult<ResBMSLoanBaseVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}


	

	
}
