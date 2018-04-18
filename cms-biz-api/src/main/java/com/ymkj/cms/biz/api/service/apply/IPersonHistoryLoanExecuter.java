package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResPersonHistoryLoanVO;

/**
 * 客户进件信息查询 接口定义
 * @author wyj
 *
 */
public interface IPersonHistoryLoanExecuter {
 
	/**
	 * 
	 * @param PersonHistoryLoanVO
	 * @return
	 */
	public Response<ResPersonHistoryLoanVO> loanInfo(PersonHistoryLoanVO personHistoryLoanVO);
	
		
}
