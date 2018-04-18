package com.ymkj.cms.biz.service.http;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.finance.GrantLoanEntity;
import com.ymkj.cms.biz.entity.finance.ResLoanFailEntity;

import java.util.List;
import java.util.Map;

public interface ICoreHttpService {

	/**
	 * 推送债权
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse pushBMSLoan(Map<String,Object> appDataMap);
	
	
	/**
	 * 生成合同
	 * @param loanDataMap
	 * @return
	 */
	public HttpResponse createBMSLoan(Map<String,Object> loanDataMap);
	
	
	/**
	 * 更新借款状态
	 * @param loanDataMap
	 * @return
	 */
	public HttpResponse updateCoreLoanState(Map<String,Object> httpData);


	/**
	 * 批量更新借款状态
	 * @param loanDataMap
	 * @return
	 */
	public List<ResLoanFailEntity>  bacthUpdateLoanState(List<BMSLoanBase> loanBaseEntitys,String stateCode,String userCode);

	
	/**
	 * 推送放款
	 * @param loanDataMap
	 * @return
	 */
	public HttpResponse grantLoanURL(GrantLoanEntity grantLoanEntity);
	
	/**
	 * 查看借款接口
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse queryLoan(Map<String,Object> httpParamMap);
	
	/**
	 * 查看债权接口
	 */
	public HttpResponse loanStatus(Map<String,Object> httpParamMap);
	
	/**
	 * 根据工号查询借款列表
	 */
	public HttpResponse findLoanByState(Map<String,Object> httpParamMap);
	/**
	 * 还款状态查询接口
	 */
	public HttpResponse paymentStatus(Map<String,Object> httpParamMap);
	
	/**
	 * 借款款状态查询接口
	 */
	public HttpResponse queryLoanState(Map<String, Object> httpParamMap);
	
	
	/**
	 * 借款历史数据查询
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse findHistoryLoan(Map<String, Object> httpParamMap);

	/**
	 * 新增或更新包商银行业务流水号
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse saveOrUpdateBsyhBusNo(Map<String, Object> httpParamMap);

	/**
	 * 债权数据推送至核心系统
	 * @param reqLoanContractSignVo
	 * @param res
	 * @return
	 */
	public boolean pushCore(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res);
	
	/**
	 * 调用核心获取合同金额接口
	 */
	public HttpResponse queryContractMoney(Map<String, Object> httpParamMap);
	
	/**
	 * 根据姓名身份证查询费率
	 */
	public HttpResponse getRateLoanUrl(Map<String, Object> httpParamMap);
	
	/**
	 * 陆金所合同确认接口
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse lujsContractConfirm(Map<String, Object> httpParamMap);
}
