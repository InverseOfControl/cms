package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IIntegratedSearchExecuter</p>
 * <p>Description:综合查询接口服务方</p>
 * @uthor YM10159
 * @date 2017年3月9日 上午11:47:10
 */
public interface IIntegratedSearchExecuter {

	/**
	 * <p>Description:综合查询-查询接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 上午10:30:41
	 * @param @param reqIntegratedSearchVO
	 */
	public PageResponse<ResIntegratedSearchVO> search(ReqIntegratedSearchVO reqIntegratedSearchVO) ;
	
	/**
	 * <p>Description:综合查询-查看借款</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 上午10:32:27
	 * @param @param reqQueryLoanVo
	 */
	public Response<ResQueryLoanVo> queryLoan(ReqQueryLoanVo reqQueryLoanVo); 
	
	/**
	 * <p>Description:综合查询-还款汇总信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 上午10:32:27
	 * @param @param reqQueryLoanVo
	 */
	public Response<ResQueryRepaymentSummaryVo> queryRepaymentSummary(ReqQueryRepaymentSummaryVo reqQueryRepaymentSummaryVo); 
	
	/**
	 * <p>Description:综合查询-还款详细信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 下午3:14:49
	 * @param @param reqQueryRepaymentDetailVo
	 */
	public Response<List<ResQueryRepaymentDetailVo>> queryRepaymentDetailAll(ReqQueryRepaymentDetailVo reqQueryRepaymentDetailVo); 
	
	/**
	 * <p>Description:综合查询-帐卡信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月15日 上午10:19:00
	 * @param @param reqQueryRepaymentDetailVo
	 */
	public Response<List<ResQueryflowVo>> queryflow(ReqQueryflowVo reqQueryflowVo); 
	
	/**
	 * <p>Description:综合查询-查询日志</p>
	 * @uthor YM10159
	 * @date 2017年3月22日 下午2:57:03
	 * @param @param loanNo
	 * @param @return
	 */
	public Response<List<ResBMSQueryLoanLogVO>> queryLoanLog(ReqQueryLoanLogVO reqQueryLoanLogVO);
	
	/**
	 * <p>Description:查看详情</p>
	 * @uthor YM10159
	 * @date 2017年5月5日 上午10:01:52
	 * @param reqQueryLoanLogVO
	 */
	public Response<ResQueryLoanDetailVo> queryLoanDetail(ReqQueryLoanDetailVo reqQueryLoanDetailVo);
	
	
}
