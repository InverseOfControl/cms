package com.ymkj.cms.biz.api.service.audit;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqContractSignSearchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanReassignmentVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSContractSignSearchVO;

/**
 *   接口定义
 * @author haowp
 *
 */
public interface IContractSignExecuter {
	 
	
	/**
	 * 改派
	 * @param reqDemoVOs
	 * @return
	 */
	public Response reassignment(ReqLoanReassignmentVO  reqLoanReassignmentVO);
	/**
	 * 查询
	 * @param contractSignSearchVO
	 * @return
	 */
	public PageResponse<ResBMSContractSignSearchVO> listPage(ReqContractSignSearchVO contractSignSearchVO) ;
}
