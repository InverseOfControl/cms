package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSLoanBaseExecuter {
	/**
	 * 查询申请信息表的信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 查找借款信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月16日下午4:08:02
	 */
	public ResListVO<ResBMSLoanBaseVO> findLoanBase(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	/**
	 * 查找借款信息数量
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月16日下午4:08:02
	 */
	public Response<Integer> findLoanBaseCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 为job提供的查询
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年8月11日下午8:38:43
	 */
	public ResListVO<ResBMSLoanBaseVO> findGrantLoanUpdateByCoreJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	/**
	 * 为job提供的查询
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年8月11日下午8:38:43
	 */
	public ResListVO<ResBMSLoanBaseVO> findLoanBaseJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 自动绑定征信报告定时任务
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年8月21日上午10:53:50
	 */
	public ResListVO<ResBMSLoanBaseVO> findBindCreditReportJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 数据条数
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月11日上午10:40:55
	 */
	public Response<Integer> findLoanBaseJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 数据条数
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月11日上午10:40:55
	 */
	public Response<Integer> findGrantLoanUpdateByCoreJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO);

	/**
	 * 数据条数
	 * @param reqBMSLoanBaseVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月11日上午10:40:55
	 */
	public Response<Integer> findBindCreditReportJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO);

}
