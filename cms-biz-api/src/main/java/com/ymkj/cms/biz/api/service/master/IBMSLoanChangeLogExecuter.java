package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogNewVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogVO;

public interface IBMSLoanChangeLogExecuter {

	public PageResponse<ResBMSLoanChangeLogVO> listPage(ReqBMSLoanChangeLogVO reqBMSLoanChangeLogVO);
	
	/**
	 * 借款信息日志变更接口
	 */
	public  PageResponse<ResBMSLoanChangeLogEntityVO> getListPage(ReqBMSLoanChangeLogNewVO vo); 
}
