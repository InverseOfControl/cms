package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogVO;

public interface ILoanLogService {
	
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSLoanLogVO> listPage(ReqBMSLoanLogVO reqLoanLogVO);

}
