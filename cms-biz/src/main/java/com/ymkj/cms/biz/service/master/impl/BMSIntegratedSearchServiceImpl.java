package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryflowVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSIntegratedSearchDao;
import com.ymkj.cms.biz.entity.master.BMSIntegraedSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSIntegratedSearchService;

@Service
public class BMSIntegratedSearchServiceImpl extends BaseServiceImpl<BMSIntegraedSearchEntity> implements IBMSIntegratedSearchService {

	@Autowired
	private IBMSIntegratedSearchDao bmsIntegratedSearchDao;

	//查询借款
	@Value("#{env['queryLoan']?:''}")
	private String queryLoan;

	//还款汇总信息接口
	@Value("#{env['queryRepaymentSummary']?:''}")
	private String queryRepaymentSummary;

	//还款详细信息接口
	@Value("#{env['queryRepaymentDetailAll']?:''}")
	private String queryRepaymentDetailAll;

	//查询帐卡信息接口
	@Value("#{env['queryflow']?:''}")
	private String queryflow;

	@Override
	protected BaseDao<BMSIntegraedSearchEntity> getDao() {
		return bmsIntegratedSearchDao;
	}

	@Override
	public HttpResponse queryLoan(ReqQueryLoanVo reqQueryLoanVo) {

		HttpResponse  httpResponse = null;

		try {
			Map<String, Object> paramsMap = BeanKit.bean2map(reqQueryLoanVo);

			String urlParam= StringUtils.map2UrlParams(paramsMap);
			long startTime = System.currentTimeMillis();

			httpResponse = HttpUtil.post(queryLoan,urlParam,false);
			System.err.println("调用贷查询借款接口用时："+(System.currentTimeMillis()-startTime));

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用查询借款接口发生异常");
		}
		return httpResponse;
	}

	@Override
	public HttpResponse queryRepaymentSummary(ReqQueryRepaymentSummaryVo reqQueryRepaymentSummaryVo) {
		HttpResponse  httpResponse = null;
		Map<String, Object> paramsMap = null;

		try {
			paramsMap = BeanKit.bean2map(reqQueryRepaymentSummaryVo);

			String urlParam= StringUtils.map2UrlParams(paramsMap);
			long startTime = System.currentTimeMillis();

			httpResponse = HttpUtil.post(queryRepaymentSummary,urlParam,false);
			System.err.println("调用还款汇总信息接口用时："+(System.currentTimeMillis()-startTime));

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用还款汇总信息接口发生异常");
		}
		return httpResponse;
	}

	@Override
	public HttpResponse queryRepaymentDetailAll(ReqQueryRepaymentDetailVo reqQueryRepaymentDetailVo) {

		HttpResponse  httpResponse = null;

		try {
			Map<String, Object> paramsMap = BeanKit.bean2map(reqQueryRepaymentDetailVo);
			
			String urlParam= StringUtils.map2UrlParams(paramsMap);
			long startTime = System.currentTimeMillis();

			httpResponse = HttpUtil.post(queryRepaymentDetailAll,urlParam,false);
			System.err.println("调用还款详细信息接口用时："+(System.currentTimeMillis()-startTime));

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用还款详细信息接口发生异常");
		}

		return httpResponse;
	}

	@Override
	public HttpResponse queryflow(ReqQueryflowVo reqQueryflowVo) {

		HttpResponse  httpResponse = null;

		try {
			Map<String, Object> paramsMap = BeanKit.bean2map(reqQueryflowVo);
			String urlParam= StringUtils.map2UrlParams(paramsMap);
			long startTime = System.currentTimeMillis();

			httpResponse = HttpUtil.post(queryflow,urlParam,false);
			System.err.println("调用帐卡信息接口用时："+(System.currentTimeMillis()-startTime));

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用还帐卡信息接口发生异常");
		}

		return httpResponse;
	}

	@Override
	public List<BMSSysLoanLog> queryLoanLog(Map<String,Object> map) {
		return bmsIntegratedSearchDao.queryLoanLog(map);
	}

	@Override
	public Map<String, Object> queryLoanDetail(String loanNo) {
		return bmsIntegratedSearchDao.queryLoanDetail(loanNo);
	}

}
