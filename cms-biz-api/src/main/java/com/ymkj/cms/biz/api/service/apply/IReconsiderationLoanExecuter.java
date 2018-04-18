package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanReviewVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqQueryReviewMessageCountVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReconsiderationLoanSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdReviewVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdateReviewReadStatusVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReconsiderationLoanSearchVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IReconsiderationLoanExecuter</p>
 * <p>Description:复议接口消费方</p>
 * @uthor YM10159
 * @date 2017年3月3日 上午10:19:32
 */
public interface IReconsiderationLoanExecuter {
	
	/**
	 * <p>Description:复议数据同步</p>
	 * @uthor YM10159
	 * @date 2017年3月3日 下午2:27:20
	 * @param @param reqLoanReviewVo
	 * @param @return
	 */
	public Response<Object> insert(ReqLoanReviewVo reqLoanReviewVo);
	
	/**
	 * <p>Description:录单复议接口查询</p>
	 * @uthor YM10159
	 * @date 2017年3月3日 上午10:52:06
	 * @param @param reqReconsiderationLoanSearchVO 接口请求对象 
	 */
	public PageResponse<ResReconsiderationLoanSearchVO> search(ReqReconsiderationLoanSearchVO reqReconsiderationLoanSearchVO); 
	
	/**
	 * <p>Description:修改复议的贷款申请记录状态</p>
	 * @uthor YM10159
	 * @date 2017年3月6日 上午10:28:45
	 * @param @param reqReconsiderationLoanSearchVO
	 */
	public Response<Object> updateStatus(ReqLoanReviewVo reqLoanReviewVo);
	
	/**
	 * <p>Description:插入复议原因和备注</p>
	 * @uthor YM10159
	 * @date 2017年3月6日 下午2:06:18
	 * @param @param reqLoanReviewVo
	 */
	public Response<Object> insertReviewReason(ReqLoanReviewVo reqLoanReviewVo);
	
	/**
	 * <p>Description:查询复议未处理的信息数</p>
	 * @uthor YM10159
	 * @date 2017年3月28日 下午3:56:15
	 * @param @param reqLoanReviewVo
	 */
	public Response<Object> queryMessageCount(ReqQueryReviewMessageCountVO reqQueryReviewMessageCountVO);
	
	/**
	 * <p>Description:修改未读标识为未已读</p>
	 * @uthor YM10159
	 * @date 2017年3月31日 上午9:59:34
	 * @param @param reqQueryReviewMessageCountVO
	 */
	public Response<Object> updateIsReadStatus(ReqUpdateReviewReadStatusVO reqUpdateReviewReadStatusVO);
	
	/**
	 * 复议办理接口
	 */
	public Response<Object> updateOrSaveReviewStatus(ReqUpdReviewVO reqUpdateReviewReadStatusVO);
	/**
	 * <p>Description:信审复议接口查询</p>
	 * @uthor YM10159
	 * @date 2017年3月3日 上午10:52:06
	 * @param @param reqReconsiderationLoanSearchVO 接口请求对象 
	 */
	public PageResponse<ResReconsiderationLoanSearchVO> xsSearch(ReqReconsiderationLoanSearchVO reqReconsiderationLoanSearchVO); 
}
