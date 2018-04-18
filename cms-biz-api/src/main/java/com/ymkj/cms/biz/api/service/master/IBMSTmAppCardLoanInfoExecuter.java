package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCardLoanInfoVO;

public interface IBMSTmAppCardLoanInfoExecuter {
	public PageResponse<ResBMSTmAppCardLoanInfoVO> listPage(ReqBMSTmAppCardLoanInfoVO reqBMSAppPersonVO);

}
