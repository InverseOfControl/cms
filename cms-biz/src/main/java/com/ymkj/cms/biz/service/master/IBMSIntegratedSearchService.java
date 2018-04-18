package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryflowVo;
import com.ymkj.cms.biz.entity.master.BMSIntegraedSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IBMSIntegratedSearchService</p>
 * <p>Description:综合查询业务接口层</p>
 * @uthor YM10159
 * @date 2017年3月14日 下午2:32:47
 */
public interface IBMSIntegratedSearchService extends BaseService<BMSIntegraedSearchEntity> {

	/**
	 * <p>Description:查询借款接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 下午2:33:38
	 * @param @param reqQueryLoanVo
	 */
	public HttpResponse queryLoan(ReqQueryLoanVo reqQueryLoanVo); 
	
	/**
	 * <p>Description:还款汇总信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 下午2:34:06
	 * @param @param reqQueryRepaymentSummaryVo
	 */
	public HttpResponse queryRepaymentSummary(ReqQueryRepaymentSummaryVo reqQueryRepaymentSummaryVo);
	
	/**
	 * <p>Description:还款详细信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月14日 下午3:23:29
	 * @param @param reqQueryRepaymentDetailVo
	 */
	public HttpResponse queryRepaymentDetailAll(ReqQueryRepaymentDetailVo reqQueryRepaymentDetailVo); 
	
	/**
	 * <p>Description:查询帐卡信息接口</p>
	 * @uthor YM10159
	 * @date 2017年3月15日 上午9:42:07
	 * @param @param reqQueryflowVo
	 */
	public HttpResponse queryflow(ReqQueryflowVo reqQueryflowVo); 
	
	/**
	 * <p>Description:综合查询-查看借款日志</p>
	 * @uthor YM10159
	 * @date 2017年3月22日 下午2:47:42
	 * @param @param loanNo 借款编号
	 */
	public List<BMSSysLoanLog> queryLoanLog(Map<String,Object> map);
	
	/**
	 * <p>Description:查看借款详情</p>
	 * @uthor YM10159
	 * @date 2017年5月5日 上午11:08:10
	 * @param loanNo
	 */
	public Map<String,Object> queryLoanDetail(String loanNo);
}
