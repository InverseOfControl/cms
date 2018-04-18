package com.ymkj.cms.biz.service.http.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumAppStateConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.finance.GrantLoanEntity;
import com.ymkj.cms.biz.entity.finance.ResLoanFailEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoreHttpServiceImpl implements ICoreHttpService{
	
		//债权推送url
		@Value("#{env['pushBMSLoanUrl']?:''}")
		private String pushBMSLoanUrl;
		
		//生成合同信息接口
		@Value("#{env['createBMSLoanUrl']?:''}")
		private String createBMSLoanUrl;
		
		//更新借款狀態接口
		@Value("#{env['updateLoanStateURL']?:''}")
		private String updateLoanStateURL;
		
		//批量更新借款狀態接口
		@Value("#{env['batchUpdateLoanStateURL']?:''}")
		private String batchUpdateLoanStateURL;
		
		//推送放款接口
		@Value("#{env['grantLoanURL']?:''}")
		private String grantLoanURL;
		
		//查看借款接口
		@Value("#{env['queryLoan']?:''}")
		private String queryLoan;
		
		//查看债权接口
		@Value("#{env['loanStatus']?:''}")
		private String loanStatus;
		
		//根据工号借款状态查询借款列表
		@Value("#{env['findLoanByStateUrl']?:''}")
		private String findLoanByStateUrl;
		//查看还款状态
		@Value("#{env['codePaymentStatus']?:''}")
		private String codePaymentStatus;
		//查看借款款状态
		@Value("#{env['queryLoanState']?:''}")
		private String  queryLoanState;
		
		//借款历史数据查询
		@Value("#{env['findHistoryLoan']?:''}")
		private String findHistoryLoan;

		//新增或更新包商银行业务流水号url
		@Value("#{env['pushByBubNoUrl']?:''}")
		private String pushByBubNoUrl;

		
		//根据姓名身份证查询核心费率
		@Value("#{env['getRateLoanUrl']?:''}")
		private String getRateLoanUrl;
		
		//根据姓名身份证查询核心费率
		@Value("#{env['queryConMoneyURL']?:''}")
		private String queryConMoneyURL;
		
		//lujsu 合同确认接口
		@Value("#{env['lujsContractConfirmURL']?:''}")
		private String  lujsContractConfirmURL;
		
		
		@Autowired
		private ILoanSignDataOprateService loanSignDataOprateService;
		/**
		 * 查看债权接口
		 */
		public HttpResponse loanStatus(Map<String,Object> httpParamMap){
		
				Map<String, String> strMap = new HashMap<String, String>();
				strMap.put("idnum", String.valueOf(httpParamMap.get("idnum")));
				strMap.put("loanNo", String.valueOf(httpParamMap.get("loanNo")));
				strMap.put("userCode", "bms");
				//转换成url参数格式
				String urlParam= CommonUtil.map2UrlParams(strMap);
				try {
				//调用接口
				long currentTime = System.currentTimeMillis();
				BmsLogger.info("接口前的时间：[{}]"+currentTime);
				HttpResponse  httpResponse = HttpUtil.post(loanStatus,urlParam,false);
				BmsLogger.info("接口后的时间：[{}]"+ System.currentTimeMillis());
				BmsLogger.info("接口用时：[{}]"+(System.currentTimeMillis()-currentTime));
				BmsLogger.info("接口，核心返回json信息：[{}]"+httpResponse.getCode()+"|"+httpResponse.getMessage());
				return httpResponse;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"查看债权接口发生异常");
			}
		}
		
		/**
		 * 查看借款接口
		 */
		public HttpResponse queryLoan(Map<String,Object> httpParamMap){
		
				Map<String, String> strMap = new HashMap<String, String>();
				strMap.put("userCode", String.valueOf(httpParamMap.get("userCode")));
				strMap.put("loanNo",String.valueOf( httpParamMap.get("loanNo")));
				strMap.put("idNum",String.valueOf( httpParamMap.get("idNum")));
				//转换成url参数格式
				String urlParam= CommonUtil.map2UrlParams(strMap);
				try {
				//调用接口
				BmsLogger.info("当前申请件操作员[{}]"+ httpParamMap.get("userCode"));
				long currentTime = System.currentTimeMillis();
				BmsLogger.info("接口前的时间：[{}]"+currentTime);
				HttpResponse  httpResponse = HttpUtil.post(queryLoan,urlParam,false);
				BmsLogger.info("接口后的时间：[{}]"+ System.currentTimeMillis());
				BmsLogger.info("接口用时：[{}]"+(System.currentTimeMillis()-currentTime));
				BmsLogger.info("接口，核心返回json信息：[{}]"+httpResponse.getCode()+"|"+httpResponse.getMessage());
				return httpResponse;
			} catch (Exception e) {
				throw new BizException(CoreErrorCode.FACADE_ERROR,"查看借款接口发生异常");
			}
		}
		
		/**
		 * 调用债权推送接口
		 */
		public HttpResponse pushBMSLoan(Map<String,Object> httpParamMap){
		
				Map<String, String> strMap = new HashMap<String, String>();
				strMap.put("jsonStr", String.valueOf(httpParamMap.get("jsonStr")));
				strMap.put("userCode",String.valueOf( httpParamMap.get("userCode")));
				//转换成url参数格式x`
				String urlParam= CommonUtil.map2UrlParams(strMap);
				try {
				//调用接口
				BmsLogger.info("债权推送接口，借款请求核心json：[{}]"+httpParamMap.get("jsonStr"));
				BmsLogger.info("当前申请件操作员[{}]"+ httpParamMap.get("userCode"));
				long currentTime = System.currentTimeMillis();
				BmsLogger.info("调用债权推送接口前的时间：[{}]"+currentTime);
				HttpResponse  httpResponse = HttpUtil.post(pushBMSLoanUrl,urlParam,false);
				BmsLogger.info("调用债权推送接口后的时间：[{}]"+ System.currentTimeMillis());
				BmsLogger.info("调用债权推送接口用时：[{}]"+(System.currentTimeMillis()-currentTime));
				BmsLogger.info("债权推送接口，核心返回json信息：[{}]"+JsonUtils.encode(httpResponse));
				return httpResponse;
			} catch (Exception e) {
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用推送债权接口发生异常");
			}
		}
		
		
		
		
	
		
		@SuppressWarnings("unused")
		public HttpResponse createBMSLoan(Map<String,Object> httpParamMap) {
			
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, String> strMap = new HashMap<String, String>();
				strMap.put("loanNo", String.valueOf(httpParamMap.get("loanNo")));
				if(httpParamMap.get("channelCd") != null 
						&& EnumConstants.channelCode.LUJS.getValue().equals(httpParamMap.get("channelCd"))){
					strMap.put("lujsName",String.valueOf(httpParamMap.get("lujsName")));
					strMap.put("lujsLoanReqId",String.valueOf(httpParamMap.get("lujsLoanReqId")));
				}
				
				//转换成url参数格式
				String urlParam= CommonUtil.map2UrlParams(strMap);
				try {
				//调用接口
					BmsLogger.info("生成合同接口，征审请求核心map：[{}]"+strMap);
					BmsLogger.info("当前申请件操作员[{}]"+httpParamMap.get("currentUser"));
				long currentTime = System.currentTimeMillis();
				BmsLogger.info("调用生成合同接口前的时间：[{}]"+ currentTime);
				HttpResponse  responseObject = HttpUtil.post(createBMSLoanUrl,urlParam,false);
				BmsLogger.info("调用生成合同接口后的时间：[{}]"+ System.currentTimeMillis());
				BmsLogger.info("调用生成合同接口用时：[{}]"+(System.currentTimeMillis()-currentTime));
				BmsLogger.info("生成合同接口，核心返回json信息：[{}]"+JsonUtils.encode(responseObject));
				return responseObject;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用生成接口发生异常");
			}

		}
		
		/**
		 * 调用更新借款状态接口
		 */
	
		public HttpResponse updateCoreLoanState(Map<String,Object> httpParamMap) {		
				Map<String, String> requestMap = new HashMap<String, String>();
				requestMap.put("loanNo", httpParamMap.get("loanNo").toString());
				requestMap.put("stateCode", httpParamMap.get("stateCode").toString());
				requestMap.put("userCode", httpParamMap.get("userCode").toString());
				//转换成url参数格式
				String urlParam=CommonUtil.map2UrlParams(requestMap);			
				try {
				//调用接口
				 BmsLogger.info("更新借款状态接口,征审传送核心map:[{}]"+requestMap);
				 HttpResponse resObject = HttpUtil.post(updateLoanStateURL,urlParam,false);
				 BmsLogger.info("更新借款状态接口,核心返回json:[{}]"+JsonUtils.encode(resObject));
				return resObject;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用更新借款状态接口发生异常");
			}
		}
		
		/**
		 * 调用批量更新借款状态接口
		 */
	

		public List<ResLoanFailEntity>  bacthUpdateLoanState(List<BMSLoanBase> loanBaseEntitys,String stateCode,String userCode) {
				Map<String, String> requestMap = new HashMap<String, String>();
				List<ResLoanFailEntity> failList =new ArrayList<ResLoanFailEntity> ();
				if (loanBaseEntitys != null && loanBaseEntitys.size() > 0) {
					for (BMSLoanBase bmsLoanBase : loanBaseEntitys) {
						requestMap.put("loanNo", bmsLoanBase.getLoanNo());
						requestMap.put("stateCode",stateCode);
						requestMap.put("userCode", userCode);
						
						//转换成url参数格式
						String urlParam=CommonUtil.map2UrlParams(requestMap);			
						
						//调用接口
						BmsLogger.info("更新借款状态接口,征审传送核心map:[{}]"+requestMap);
						HttpResponse resObject = HttpUtil.post(updateLoanStateURL,urlParam,false);
						BmsLogger.info("更新借款状态接口,核心返回json:[{}]"+JsonUtils.encode(resObject));
						HttpContractReturnEntity contractReturnEntity= JsonUtils.decode(resObject.getContent(), HttpContractReturnEntity.class);
						if(!"000000".equals(contractReturnEntity.getCode())){//是否请求成功
							BmsLogger.error("更新核心系统借款状态失败！借款编号："+bmsLoanBase.getLoanNo()+":"+contractReturnEntity.getMessage());
							ResLoanFailEntity res= new ResLoanFailEntity();
							res.setLoanNo(bmsLoanBase.getLoanNo());
							res.setErrorMessage(contractReturnEntity.getMessage());
							failList.add(res);
						}
					}
				}
				return failList;
		}
		
		
		/**
		 * 放款时调用放款推送接口
		 */
		@SuppressWarnings({ "unused" })
		@Transactional
		@Override//List<ApplyNodeData> applyNodeDataList,List<String> taskIdList, List<String> appNos,ApplyNodeData applyNodeData,Map<String, Object> newData
		public HttpResponse grantLoanURL(GrantLoanEntity grantLoanEntity) {
			Map<String, Object> map = new HashMap<String, Object>();
			//根据appNo得到对应的债权Id
			
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("infos", JsonUtils.encode(grantLoanEntity.getInfos()));
			strMap.put("userCode",grantLoanEntity.getUserCode());
			
			//转换成url参数格式
			String urlParam= StringUtils.map2UrlParams(strMap);
			//String urlParam= JsonUtils.encode(grantLoanEntity);
			try {
				//调用放款推送接口，得到返回的信息
				BmsLogger.info("调用放款推送接口,征审传送核心数据：[{}]"+grantLoanURL);
				HttpResponse  httpResponse = HttpUtil.post(grantLoanURL, urlParam, false);
				BmsLogger.info("调用放款推送接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
				return httpResponse;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用放款推送接口时异常");
			}
			
		}
		
		
		/**
		 * 根据状态查询借款
		 */
		@SuppressWarnings({ "unused" })
		@Transactional
		@Override//List<ApplyNodeData> applyNodeDataList,List<String> taskIdList, List<String> appNos,ApplyNodeData applyNodeData,Map<String, Object> newData
		public HttpResponse findLoanByState(Map  paramMap) {
			Map<String, Object> map = new HashMap<String, Object>();
			//根据appNo得到对应的债权Id
			String status =paramMap.get("status")==null?"":paramMap.get("status").toString();
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("userCode",paramMap.get("userCode"));
			strMap.put("userType",paramMap.get("userType"));//1， 客户经理 2，客服
			strMap.put("pageNum", paramMap.get("pageNum"));
			strMap.put("pageSize",paramMap.get("pageSize"));
			status=	EnumAppStateConstants.getAppStateCodeByValue(status);
			strMap.put("status", status);//1：逾期 2：结清 3:正常
			//转换成url参数格式
			String urlParam= StringUtils.map2UrlParams(strMap);
			try {
				//调用放款推送接口，得到返回的信息
				BmsLogger.info("调用状态查询借款,征审传送核心数据：[{}]"+findLoanByStateUrl);
				HttpResponse  httpResponse = HttpUtil.post(findLoanByStateUrl, urlParam, false);
				BmsLogger.info("调用状态查询借款,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
				return httpResponse;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用放款推送接口时异常");
			}
			
		}

		@Override
		public HttpResponse paymentStatus(Map<String, Object> httpParamMap) {
		
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("userCode",httpParamMap.get("userCode"));
			strMap.put("idnum",httpParamMap.get("idnum"));//1， 客户经理 2，客服
			String urlParam= StringUtils.map2UrlParams(strMap);
			try {
				//调用放款推送接口，得到返回的信息
				BmsLogger.info("调用查询还款状态接口,征审传送核心数据：[{}]"+codePaymentStatus);
				HttpResponse  httpResponse = HttpUtil.post(codePaymentStatus, urlParam, false);
				BmsLogger.info("调用查询还款状态接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
				return httpResponse;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用放款推送接口时异常");
			}
			
		}
		
		
		@Override
		public HttpResponse queryLoanState(Map<String, Object> httpParamMap) {
		
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("userCode",httpParamMap.get("userCode"));
			strMap.put("loanNos",httpParamMap.get("loanNos"));//1， 客户经理 2，客服
			String urlParam= StringUtils.map2UrlParams(strMap);
			try {
				//调用放款推送接口，得到返回的信息
				BmsLogger.info("调用查询借款状态接口,征审传送核心数据：[{}]"+queryLoanState);
				HttpResponse  httpResponse = HttpUtil.post(queryLoanState, urlParam, false);
				BmsLogger.info("调用查询借款状态接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
				return httpResponse;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用放款推送接口时异常");
			}
			
		}

		@Override
		public HttpResponse findHistoryLoan(Map<String, Object> httpParamMap) {
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("name",httpParamMap.get("name"));
			strMap.put("idnum",httpParamMap.get("idnum"));
			String urlParam= StringUtils.map2UrlParams(strMap);
			try {
				//调用放款推送接口，得到返回的信息
				BmsLogger.info("调用查询借款历史数据查询接口,传送核心url：[{}]"+findHistoryLoan);
				BmsLogger.info("调用查询借款历史数据查询接口,传送核心数据：[{}]"+urlParam);
				HttpResponse  httpResponse = HttpUtil.post(findHistoryLoan, urlParam, false);
				BmsLogger.info("调用查询借款历史数据查询接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
				return httpResponse;
			} catch (Exception e) {
				BmsLogger.info(e.getMessage());
				throw new BizException(CoreErrorCode.FACADE_ERROR,"调用借款历史数据查询接口时异常");
			}
		}

	@Override
	public HttpResponse saveOrUpdateBsyhBusNo(Map<String, Object> httpParamMap) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("loanNo",httpParamMap.get("loanNo"));
		strMap.put("busNumber",httpParamMap.get("busNumber"));
		String urlParam= StringUtils.map2UrlParams(strMap);
		try {
			//调用放新增或更新包商银行业务流水号接口，得到返回的信息
			BmsLogger.info("调用新增或更新包商银行业务流水号接口,传送核心url：[{}]"+pushByBubNoUrl);
			BmsLogger.info("调用新增或更新包商银行业务流水号接口,传送核心数据：[{}]"+urlParam);
			HttpResponse  httpResponse = HttpUtil.post(pushByBubNoUrl, urlParam, false);
			BmsLogger.info("调用新增或更新包商银行业务流水号接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用新增或更新包商银行业务流水号接口时异常");
		}
	}

	/**
	 * 债权数据推送至核心系统
	 *
	 * @param reqLoanContractSignVo
	 * @param res
	 * @return
	 */
	@Override
	public boolean pushCore(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 债权推送信息组装参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanBaseId", reqLoanContractSignVo.getId());
		String jsonStr = loanSignDataOprateService.fetchAllApplyData(reqLoanContractSignVo);
		Map<String, Object> httpParam = new HashMap<String, Object>();
		httpParam.put("userCode", reqLoanContractSignVo.getServiceCode());
		httpParam.put("jsonStr", jsonStr);
		// 调用
		HttpResponse httpResponse = this.pushBMSLoan(httpParam);
		HttpContractReturnEntity contractReturnEntity = JsonUtils.decode(httpResponse.getContent(),
				HttpContractReturnEntity.class);
		if (!"000000".equals(contractReturnEntity.getCode())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public HttpResponse getRateLoanUrl(Map<String, Object> httpParamMap) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("name",httpParamMap.get("name"));
		strMap.put("idNum",httpParamMap.get("idnum"));
		String urlParam= StringUtils.map2UrlParams(strMap);
		try {
			//调用放款推送接口，得到返回的信息
			BmsLogger.info("调用核心接口根据NAME和IDNUM查询费率,传送核心url：[{}]"+getRateLoanUrl);
			BmsLogger.info("调用核心接口根据NAME和IDNUM查询费率,传送核心数据：[{}]"+urlParam);
			HttpResponse  httpResponse = HttpUtil.post(getRateLoanUrl, urlParam, false);
			BmsLogger.info("调用核心接口根据NAME和IDNUM查询费率,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用借款历史数据查询接口时异常");
		}
	}

	@Override
	public HttpResponse queryContractMoney(Map<String, Object> httpParamMap) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("requestMoney",httpParamMap.get("requestMoney"));
		strMap.put("prodName",httpParamMap.get("prodName"));
		strMap.put("term",httpParamMap.get("term"));
		strMap.put("contractSource",httpParamMap.get("contractSource"));
		String urlParam= JsonUtils.encode(strMap);
		
//		String urlParam= StringUtils.map2UrlParams(strMap);
		try {
			//调用放款推送接口，得到返回的信息
			BmsLogger.info("调用核心获取合同金额接口,传送核心url：[{}]"+queryConMoneyURL);
			BmsLogger.info("调用核心获取合同金额接口,传送核心数据：[{}]"+urlParam);
			HttpResponse  httpResponse = HttpUtil.post(queryConMoneyURL, urlParam, true);
			BmsLogger.info("调用核心获取合同金额接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用借款历史数据查询接口时异常");
		}
	}
	
	public HttpResponse lujsContractConfirm(Map<String, Object> httpParamMap){
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("appNo",httpParamMap.get("loanNo"));
		strMap.put("apsApplyNo",httpParamMap.get("apsApplyNo"));
		strMap.put("applyNo",httpParamMap.get("applyNo"));
		strMap.put("lufaxLoanReqId",httpParamMap.get("lufaxLoanReqId"));
		String urlParam= JsonUtils.encode(strMap);
		//String urlParam= StringUtils.map2UrlParams(strMap);
		try {
			//调用放款推送接口，得到返回的信息
			BmsLogger.info("调用核心陆金所合同确认接口,传送核心url：[{}]"+lujsContractConfirmURL);
			BmsLogger.info("调用核心陆金所合同确认接口,传送核心数据：[{}]"+strMap);
			HttpResponse  httpResponse = HttpUtil.post(lujsContractConfirmURL, urlParam, true);
			BmsLogger.info("调用核心陆金所合同确认接口,核心返回json：[{}]"+JsonUtils.encode(httpResponse));		
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用核心陆金所合同确认接口");
		}
		
	}
}
