package com.ymkj.cms.biz.service.http.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.FTPUtils;
import com.ymkj.cms.biz.common.util.FileUtil;
import com.ymkj.cms.biz.common.util.HttpKit;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.MD5Utils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractFileService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;

@Service
public class AiTeHttpServiceImpl implements IAiTeHttpService {
	// 网关密钥
	@Value("#{env['picSecretKey']?:''}")
	private String picSecretKey;

	// 推送标的接口
	@Value("#{env['aiTeTargetPushedUrl']?:''}")
	private String aiTeTargetPushedUrl;

	// 终止借款接口
	@Value("#{env['aiTeTerminationLoanUrl']?:''}")
	private String aiTeTerminationLoanUrl;

	// 上海资信查询地址
	@Value("#{env['queryCreditURLById']?:''}")
	private String queryCreditURLById;

	// 身份证获取地址
	@Value("#{env['getIDCardPicUrl']?:''}")
	private String getIDCardPicUrl;

	// 文件上传地址
	@Value("#{env['uploadPicUrl']?:''}")
	private String uploadPicUrl;

	// 文件删除地址
	@Value("#{env['deletePicUrl']?:''}")
	private String deletePicUrl;
	
	// 还款计划查询
	@Value("#{env['core.repaymentDetialCore.queryRepaymentDetail']?:''}")
	private String queryRepaymentDetail;
	
	// 合同确认通知爱特
	@Value("#{env['aite.laocaibao.contractConfirmationToAiTe.url']?:''}")
	private String contractConfirmationToAiTe;
	
	// 合同确认通知爱特
	@Value("#{env['linux_contractPath']?:''}")
	private String linux_contractPath;
	
	// 合同确认通知爱特
	@Value("#{env['windows_contractPath']?:''}")
	private String windows_contractPath;
	
	// 捞财宝标的降额接口
	@Value("#{env['aiTeTargetDeratingUrl']?:''}")
	private String aiTeTargetDeratingUrl;

	@Autowired
	private FTPUtils ftpUtils;
	@Autowired
	private HttpKit httpKit;
	@Autowired
	private IAiTeLoanContractFileService aiTeLoanContractFileService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	
	// json 工具类
	private Gson gson = new Gson();
	
	private final static Logger logger = LoggerFactory.getLogger(AiTeHttpServiceImpl.class);
	
	
	@Override
	public HttpResponse targetPushed(Map<String, String> httpParamMap) {

		try {
			if(StringUtils.isNotEmpty(httpParamMap.get("borrowNo"))){
				Map<String, String> paramMap =new HashMap<String, String>();	
				paramMap.put("loanNo", httpParamMap.get("borrowNo"));
				BMSLoanBaseEntity loanInfo = loanBaseEntityDao.findSignInfo(paramMap);	
				httpParamMap.put("borrowNo", ValidationUtils.composeAitePushLoanNo(httpParamMap.get("borrowNo"), loanInfo.getChannelPushFrequency()));
			}
			//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
			Map<String, String> mapOrder = new TreeMap<String,String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                        // 升序排序
	                        return obj1.compareTo(obj2);
	                    }
	                });
			
			mapOrder.putAll(httpParamMap);
			String sign= MD5Utils.md5(mapOrder, picSecretKey);
			// 加入签名
			mapOrder.put("sign", sign);
			
			// 转换成url参数格式
			String urlParam = CommonUtil.map2UrlParams(mapOrder);
			// 调用接口
			logger.info("调用标的推送接口，借款请求爱特：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用标的推送接口前的时间：[{}]" + currentTime);
			HttpResponse httpResponse = HttpUtil.post(aiTeTargetPushedUrl, urlParam, false);
			logger.info("调用标的推送接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用标的推送接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("调用标的推送接口，爱特返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());
			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用标的推送接口发生异常");
		}
	}

	@Override
	public HttpResponse terminationLoan(Map<String, String> httpParamMap) {
		try {

			String sign= MD5Utils.md5(httpParamMap, picSecretKey);
			// 加入签名
			httpParamMap.put("sign", sign);
			//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
			Map<String, String> mapOrder = new TreeMap<String,String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                        // 升序排序
	                        return obj1.compareTo(obj2);
	                    }
	                });
			
			mapOrder.putAll(httpParamMap);
			
			// 转换成url参数格式
			String urlParam = CommonUtil.map2UrlParams(mapOrder);
			// 调用接口
			logger.info("终止借款接口，借款请求爱特：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用终止借款接口前的时间：[{}]" + currentTime);
			HttpResponse httpResponse = HttpUtil.post(aiTeTerminationLoanUrl, urlParam, false);
			//临时补救方法
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){

				} else {
					logger.info("终止借款接口，爱特返回json信息：[{}]" + gson.toJson(httpResponse));
					httpParamMap.remove("sign");
					if(StringUtils.isNotEmpty(httpParamMap.get("borrowNo"))){
						Map<String, String> paramMap =new HashMap<String, String>();	
						paramMap.put("loanNo", httpParamMap.get("borrowNo"));
						BMSLoanBaseEntity loanInfo = loanBaseEntityDao.findSignInfo(paramMap);	
						httpParamMap.put("borrowNo", ValidationUtils.composeAitePushLoanNo(httpParamMap.get("borrowNo"), loanInfo.getChannelPushFrequency()));
					}
					sign= MD5Utils.md5(httpParamMap, picSecretKey);
					// 加入签名
					httpParamMap.put("sign", sign);
					//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
					
					mapOrder.clear();
					mapOrder.putAll(httpParamMap);
					
					// 转换成url参数格式
					urlParam = CommonUtil.map2UrlParams(mapOrder);
					logger.info("终止借款接口，借款请求爱特：[{}]" + urlParam);

					HttpResponse httpResponseNew = HttpUtil.post(aiTeTerminationLoanUrl, urlParam, false);
					logger.info("调用终止借款接口后的时间：[{}]" + System.currentTimeMillis());
					logger.info("调用终止借款接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
					logger.info("终止借款接口，爱特返回json信息：[{}]" + gson.toJson(httpResponseNew));

					return httpResponseNew;
				}
			}
			logger.info("调用终止借款接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用终止借款接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			
			logger.info("终止借款接口，爱特返回json信息：[{}]" + gson.toJson(httpResponse));
			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用终止借款接口发生异常");
		}
	}

	@Override
	public HttpResponse getCreditStandingAndRespectabilitySH(Map<String, String> httpParamMap) {
		try {
			String urlParam = CommonUtil.map2UrlParams(httpParamMap);

			// 调用接口
			logger.info("上海资信接口，借款请求PMS：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用上海资信接口前的时间：[{}]" + currentTime);
			HttpResponse httpResponse = HttpUtil.post(queryCreditURLById, urlParam, false);
			// HttpUtil.post("http://172.16.230.37:8080/creditzx-web/shCredit/queryCreditById",urlParam,false);
			logger.info("调用上海资信接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用上海资信接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("上海资信接口，PMS返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());

			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用上海资信接口发生异常");
		}

	}

	@Override
	public HttpResponse getIDCard(Map<String, String> httpParamMap) {
		try {
			String urlParam = CommonUtil.map2UrlParams(httpParamMap);

			// 调用接口
			logger.info("人身份证信息接口，借款请求PMS：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用人身份证信息接口前的时间：[{}]" + currentTime);

			HttpResponse httpResponse = HttpUtil.post(getIDCardPicUrl, urlParam, false);
			// 20170302100000005401
			// HttpResponse httpResponse =
			// HttpUtil.post("http://172.16.230.50:8080/pic-app/api/picture/list",urlParam,false);
			logger.info("调用人身份证信息接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用人身份证信息接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("人身份证信息接口，PMS返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());

			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用人身份证信息接口发生异常");
		}
	}

	@Override
	public HttpResponse uploadFile(Map<String, String> httpParamMap) {
		logger.debug("文件上传==================");
		if (httpParamMap.get("files") == null) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "files参数空");
		}
		// 解析files，已文件流的形式上传
		String files = httpParamMap.get("files").toString();
		List<Map<String, Object>> fileListMap = JsonUtils.decode(files, List.class);
		httpParamMap.remove("files");

		// String urlParam = CommonUtil.map2UrlParams(httpParamMap);
		List<String> fileList = new ArrayList();
		// 调用接口
		logger.info("文件上传接口，借款请求PMS：[{}]" + httpParamMap);
		long currentTime = System.currentTimeMillis();
		logger.info("调用文件上传接口前的时间：[{}]" + currentTime);

		List<String> httpResponseList = new ArrayList<String>();
		for (Map<String, Object> map : fileListMap) {
			if (map.get("url") == null) {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "文件url空");
			}
			File targetFile = null;
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			
			try {
				String fileUrl = map.get("url").toString();
				String fileName = map.get("fileName").toString();
				// http://172.16.230.50:8080/pic-app/file/aps/20170302100000005409/B/3b63cd6fbb5945729bb793516563a5f7.jpg
				String osType = StringUtils.osType();
				if (osType.equals("windows")) {
					targetFile = new File(windows_contractPath + fileName);
					logger.debug("文件路径"+ windows_contractPath + fileName);
				} else {
					targetFile = new File(linux_contractPath + fileName);
					logger.debug("文件路径"+ windows_contractPath + fileName);
				}

				fos = new FileOutputStream(targetFile);
				// 文件下载到本地
				URL url = new URL(fileUrl);
				HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
				httpUrl.setConnectTimeout(30000);//请求超时设置
				httpUrl.connect();
				bis = new BufferedInputStream(httpUrl.getInputStream());
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = bis.read(bufferOut)) != -1) {
					fos.write(bufferOut, 0, bytes);
				}
				//链接关闭
				bis.close();
				fos.flush();
				fos.close();
				httpUrl.disconnect();
				
				String result = httpKit.post(uploadPicUrl, httpParamMap, targetFile);
				// {result={id=1424, url=//localhost//file/bms/20170303165731168805/E_BMS/de4a828d1e114117929a467ec768a751.jpg}, isFail=false, isOk=true, errorcode=000000}
				Map<String, Object> resultMap = JsonUtils.decode(result, Map.class);
				if ("000000".equals(resultMap.get("errorcode"))) {
					Map<String, Object> uploadFileInfo = (Map<String, Object>) resultMap.get("result");
					uploadFileInfo.putAll(httpParamMap);
					uploadFileInfo.put("fileName", fileName);
					// 保存上传信息到本地表
					aiTeLoanContractFileService.saveAiteUploadFile(uploadFileInfo);
					
					System.out.println("上传成功");
				} else {
					System.out.println("上传失败");
				}

			} catch (Exception ex) {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用文件上传接口发生异常");
			} finally {
				//流关闭
				if(bis != null){
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(targetFile != null && targetFile.exists()){
					FileUtil.deleteFile(targetFile);
				}
			}
		}
		logger.info("调用文件上传接口后的时间：[{}]" + System.currentTimeMillis());
		logger.info("调用文件上传接口用时：[{}]" + (System.currentTimeMillis() - currentTime));

		return new HttpResponse(0, "调用结束", httpResponseList.toString());

	}

	@Override
	public HttpResponse deleteFile(Map<String, String> httpParamMap) {
		try {
			String urlParam = CommonUtil.map2UrlParams(httpParamMap);

			// 调用接口
			logger.info("文件删除接口，借款请求PMS：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用文件删除接口前的时间：[{}]" + currentTime);

			HttpResponse httpResponse = HttpUtil.post(deletePicUrl, urlParam, false);

			// 20170302100000005401
			// HttpResponse httpResponse =
			// HttpUtil.post("http://172.16.230.50:8080/pic-app/api/picture/list",urlParam,false);
			logger.info("调用文件删除接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用文件删除接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("文件删除接口，PMS返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());

			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用文件删除接口发生异常");
		}

	}

	@Override
	public HttpResponse queryRepaymentDetail(Map<String, String> httpParamMap) {
		String urlParam = CommonUtil.map2UrlParams(httpParamMap);

		// 调用接口
		logger.info("调用还款计划接口，征审传送核心map:[{}]" + urlParam);
		long currentTime = System.currentTimeMillis();
		logger.info("调用还款计划接口，征审传送核心前的时间：[{}]" + currentTime);

		HttpResponse httpResponse = HttpUtil.post(queryRepaymentDetail, urlParam, false);

		// HttpUtil.post("http://172.16.230.50:8080/pic-app/api/picture/list",urlParam,false);
		logger.info("调用还款计划接口，征审传送核心后的时间：[{}]" + System.currentTimeMillis());
		logger.info("调用还款计划接口，征审传送核心用时：[{}]" + (System.currentTimeMillis() - currentTime));
		logger.info("调用还款计划接口，征审传送核心返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());

		return httpResponse;
	}

	@Override
	public HttpResponse contractConfirmationToAiTe(Map<String, String> httpParamMap) {
		try {
			
			String sign= MD5Utils.md5(httpParamMap, picSecretKey);
			// 加入签名
			httpParamMap.put("sign", sign);
			//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
			Map<String, String> mapOrder = new TreeMap<String,String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                        // 升序排序
	                        return obj1.compareTo(obj2);
	                    }
	                });
			
			mapOrder.putAll(httpParamMap);
			
			// 转换成url参数格式
			String urlParam = CommonUtil.map2UrlParams(mapOrder);
			// 调用接口
			logger.info("终止借款接口，借款请求爱特：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用终止借款接口前的时间：[{}]" + currentTime);
			HttpResponse httpResponse = HttpUtil.post(contractConfirmationToAiTe, urlParam, false);
			//临时补救方法
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){

				} else {
					httpParamMap.remove("sign");
					if(StringUtils.isNotEmpty(httpParamMap.get("borrowNo"))){
						Map<String, String> paramMap =new HashMap<String, String>();	
						paramMap.put("loanNo", httpParamMap.get("borrowNo"));
						BMSLoanBaseEntity loanInfo = loanBaseEntityDao.findSignInfo(paramMap);	
						httpParamMap.put("borrowNo", ValidationUtils.composeAitePushLoanNo(httpParamMap.get("borrowNo"), loanInfo.getChannelPushFrequency()));
					}
					sign= MD5Utils.md5(httpParamMap, picSecretKey);
					// 加入签名
					httpParamMap.put("sign", sign);
					//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
					
					mapOrder.clear();
					mapOrder.putAll(httpParamMap);
					
					// 转换成url参数格式
					urlParam = CommonUtil.map2UrlParams(mapOrder);
					HttpResponse httpResponseNew = HttpUtil.post(contractConfirmationToAiTe, urlParam, false);
					logger.info("调用终止借款接口后的时间：[{}]" + System.currentTimeMillis());
					logger.info("调用终止借款接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
					logger.info("终止借款接口，爱特返回json信息：[{}]" + httpResponseNew.getCode() + "|" + httpResponseNew.getMessage());
					return httpResponseNew;
				}
			}
			
			
			logger.info("调用终止借款接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用终止借款接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("终止借款接口，爱特返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());
			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用终止借款接口发生异常");
		}
	}

	@Override
	public HttpResponse targetDerating(Map<String, String> httpParamMap) {
		try {
			
			String sign= MD5Utils.md5(httpParamMap, picSecretKey);
			// 加入签名
			httpParamMap.put("sign", sign);
			//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
			Map<String, String> mapOrder = new TreeMap<String,String>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                        // 升序排序
	                        return obj1.compareTo(obj2);
	                    }
	                });
			
			mapOrder.putAll(httpParamMap);
			
			// 转换成url参数格式
			String urlParam = CommonUtil.map2UrlParams(mapOrder);
			// 调用接口
			logger.info("爱特标的降额接口，借款请求爱特：[{}]" + urlParam);
			long currentTime = System.currentTimeMillis();
			logger.info("调用标的降额接口前的时间：[{}]" + currentTime);
			HttpResponse httpResponse = HttpUtil.post(aiTeTargetDeratingUrl, urlParam, false);
			//临时补救方法，处理老数据
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){

				} else {
					//去除AITE再次推送
					httpParamMap.remove("sign");
					if(StringUtils.isNotEmpty(httpParamMap.get("borrowNo"))){
						Map<String, String> paramMap =new HashMap<String, String>();	
						paramMap.put("loanNo", httpParamMap.get("borrowNo"));
						BMSLoanBaseEntity loanInfo = loanBaseEntityDao.findSignInfo(paramMap);	
						httpParamMap.put("borrowNo", ValidationUtils.composeAitePushLoanNo(httpParamMap.get("borrowNo"), loanInfo.getChannelPushFrequency()));
					}

					sign= MD5Utils.md5(httpParamMap, picSecretKey);
					// 加入签名
					httpParamMap.put("sign", sign);
					//将数据按照参数名首字母升序排序,编译会产生内部类文件需要注意，AiTeHttpServiceImpl$1.class
					
					mapOrder.clear();
					mapOrder.putAll(httpParamMap);
					
					// 转换成url参数格式
					urlParam = CommonUtil.map2UrlParams(mapOrder);
					HttpResponse httpResponseNew = HttpUtil.post(aiTeTargetDeratingUrl, urlParam, false);
					logger.info("调用标的降额接口后的时间：[{}]" + System.currentTimeMillis());
					logger.info("调用标的降额接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
					logger.info("标的降额接口，爱特返回json信息：[{}]" + httpResponseNew.getCode() + "|" + httpResponseNew.getMessage());
					return httpResponseNew;
				}
			}
			logger.info("调用标的降额接口后的时间：[{}]" + System.currentTimeMillis());
			logger.info("调用标的降额接口用时：[{}]" + (System.currentTimeMillis() - currentTime));
			logger.info("标的降额接口，爱特返回json信息：[{}]" + httpResponse.getCode() + "|" + httpResponse.getMessage());
			return httpResponse;
		} catch (Exception e) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "调用标的降额接口发生异常");
		}
	}

}
