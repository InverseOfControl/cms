package com.ymkj.cms.biz.service.http.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ObjectUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.CommonUtil;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.CreditHttpService;


@Service
public class CreditHttpServiceImpl implements CreditHttpService {
	
	private Gson gson = new Gson();

	// 央行征信报告url
	@Value("#{env['queryBMSReportUrl']?:''}")
	private String queryBMSReportUrl;
	
	// 上海资信报告url
	@Value("#{env['queryShangHaiCreditReport']?:''}")
	private String queryShangHaiCreditReport;

	
	// 算话查询欺诈
	@Value("#{env['queryAntifraudInfoUrl']?:''}")
	private String queryAntifraudInfoUrl;
	
	// 第三方接口查询
	@Value("#{env['queryThirdPartyInfo']?:''}")
	private String queryThirdPartyInfo;
	
	// 老征审，借款数据查询
	@Value("#{env['queryMainByIdNo']?:''}")
	private String queryMainByIdNo;
	
	// 解读央行报告
	@Value("#{env['getBadCreditInfo']?:''}")
	private String getBadCreditInfo;
	
	// 查询用户是否在已分派名单
	@Value("#{env['getSalesman']?:''}")
	private String getSalesman;
	
	// 查询用户是否在已分派名单
	@Value("#{env['getSalesman2']?:''}")
	private String getSalesman2;
		
	// 查询用户是否是优质客户名单
	@Value("#{env['personValidate']?:''}")
	private String personValidate;
	
	@Value("#{env['creditReportAnalyze']?:''}")
	private String creditReportAnalyze;
	
	// 央行报告限制
	@Value("#{env['applyCreditReportRule']?:''}")
	private String applyCreditReportRule;
	
	// 获取老政审对应的最近一次拒绝和取消时间还有限制天数URL
	@Value("#{env['queryOldRefuseAndCancelInfo']?:''}")
	private String queryOldRefuseAndCancelInfo;
	
	
	/**
	 * 解读央行报告
	 */
	@Override
	public JSONObject getBadCreditInfo(Map<String, Object> appDataMap) {
		JSONObject resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("customerIdCard", appDataMap.get("customerIdCard").toString());//
		json.put("customerName", appDataMap.get("customerName").toString());//
		json.put("reportId",appDataMap.get("reportId").toString());//央行报告id
		json.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
		json.put("sources", EnumConstants.BMS_SYSCODE);//来源
		json.put("queryDate",DateUtil.defaultFormatDay(new Date()));//首次录入复核时间
		
		
//		json.put("customerIdCard", "62100019801016482X");//
//		json.put("customerName", "张三");//
//		json.put("reportId","101082");//央行报告id
//		json.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
//		json.put("sources", EnumConstants.BMS_SYSCODE);//来源
//		json.put("queryDate","2017-06-23");//首次录入复核时间
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("param", json.toString());
		try {
			// 调用接口
			BmsLogger.info("解读央行征信报告查询，查询参数url:[{}]" + getBadCreditInfo);
			BmsLogger.info("解读央行征信报告查询，查询参数map:[{}]" + requestMap.toString());
			resp = postBaseMethod(getBadCreditInfo, requestMap);
			BmsLogger.info("解读央行征信报告查询，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			return resp;
		}
	}
	
	
	/**
	 * reportId 返回的报告id
	 */
	public JSONObject queryBMSReport(Map<String, Object> appDataMap) {
		JSONObject resp = null; 
		JSONObject json = new JSONObject();
		json.put("customerIdCard", appDataMap.get("customerIdCard").toString());//
		json.put("customerName", appDataMap.get("customerName").toString());//
		json.put("queryDate",appDataMap.get("queryDate").toString());//首次录入复核时间
		json.put("appNo",appDataMap.get("appNo").toString());//借款编号 
		json.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
		json.put("sources", EnumConstants.BMS_SYSCODE);//来源
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("param", json.toString());
		try {
			// 调用接口
			BmsLogger.info("央行征信报告查询，查询参数url:[{}]" + queryBMSReportUrl);
			BmsLogger.info("央行征信报告查询，查询参数map:[{}]" + requestMap.toString());
			resp = postBaseMethod(queryBMSReportUrl, requestMap);
			BmsLogger.info("央行征信报告查询，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "央行征信报告查询");
		}
	}
	
	/**
	 * reportId 返回的报告id  无需借款编号
	 */
	public JSONObject queryBMSReportNotAppNo(Map<String, Object> appDataMap) {
		JSONObject resp = null; 
		JSONObject json = new JSONObject();
		json.put("customerIdCard", appDataMap.get("customerIdCard").toString());//
		json.put("customerName", appDataMap.get("customerName").toString());//
		json.put("queryDate",appDataMap.get("queryDate").toString());//首次录入复核时间
		json.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
		json.put("sources", EnumConstants.BMS_SYSCODE);//来源
		json.put("sysCode", EnumConstants.BMS_SYSCODE);//来源
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("param", json.toString());
		try {
			// 调用接口
			BmsLogger.info("央行征信报告查询，查询参数url:[{}]" + queryBMSReportUrl);
			BmsLogger.info("央行征信报告查询，查询参数map:[{}]" + requestMap.toString());
			resp = postBaseMethod(queryBMSReportUrl, requestMap);
			BmsLogger.info("央行征信报告查询，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "央行征信报告查询");
		}
	}
	
	@Override
	public HttpResponse queryShangHaiCreditReport(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("idnum", appDataMap.get("idnum").toString());//身份证
		requestMap.put("name", appDataMap.get("name").toString());//姓名
		requestMap.put("appNo",appDataMap.get("appNo").toString());//借款编号
		requestMap.put("sources", EnumConstants.BMS_SYSCODE);//系统编号
		String urlParamt = CommonUtil.map2UrlParams(requestMap);
		try {
			// 调用接口
			BmsLogger.info("上海资信报告查询，查询参数url:[{}]" + queryShangHaiCreditReport);
			BmsLogger.info("上海资信报告查询，查询参数map:[{}]" + urlParamt);
			resp = HttpUtil.post(queryShangHaiCreditReport, urlParamt, false);
			BmsLogger.info("上海资信报告查询，查询参数res:[{}]" + resp);
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "上海资信报告查询");
		}
	}
	
	/**
	 * post请求公共方法
	 * @param url
	 * @param map
	 * @return
	 */
	public JSONObject postBaseMethod(String url, Map<String,String> map) {
		JSONObject resp = null; 
		try {
			if(org.apache.commons.lang.StringUtils.isBlank(url)){
				resp = new JSONObject("{'code':'000002';'message':'调用接口url地址不能为空.';}");
			}
			// 构造HttpClient的实例
			HttpClient client = new HttpClient();
			// 创建PostMethod的实例
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
			for (String item : map.keySet()) {
				postMethod.addParameter(item, map.get(item));
			}
			// 执行getMethod,调用http接口
			client.executeMethod(postMethod);

			if(404 == postMethod.getStatusCode()){ // 服务调用失败：404
				resp = new JSONObject("{'code':'000001';'message':'调用接口失败 404,请联系系统管理员.';}");
			}else{
				resp = new JSONObject(postMethod.getResponseBodyAsString());
			}
		} catch (Exception e) {
			BmsLogger.error(e.getMessage(), e);
			return resp;
		}
		return resp;
	}

	/**
	 * 算话查询
	 */
	@Override
	public JSONObject querySuanHuaAntifraud(Map<String, Object> appDataMap) {
		JSONObject resp = null; 
		JSONObject json = new JSONObject();
		
		json.put("creatorId", appDataMap.get("creatorId"));//操作员
		json.put("sources", appDataMap.get("sources"));
	    json.put("appNo", appDataMap.get("appNo"));//借款编号
	    json.put("idCard", appDataMap.get("idCard"));//身份证
	    json.put("name", appDataMap.get("name"));//姓名
	    json.put("cellphone", appDataMap.get("cellphone"));//手机号
	    json.put("address", appDataMap.get("address"));//家庭地址
	    if(appDataMap.get("homePhone") == null || appDataMap.get("homePhone").equals("")){
	    	json.put("homePhone", " ");//家庭电话
	    }else{
	    	json.put("homePhone", appDataMap.get("homePhone"));//家庭电话
	    }
	    json.put("companyName", appDataMap.get("companyName"));//工作单位名称
	    json.put("companyAddress", appDataMap.get("companyAddress"));//工作单位地址
	    json.put("companyPhone", appDataMap.get("companyPhone"));//工作单位电话
	    json.put("contactPhone1", appDataMap.get("contactPhone1"));//
	    json.put("timestamp",appDataMap.get("timestamp"));
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("param", json.toString());
		try {
			// 调用接口
			BmsLogger.info("算话征信查询，查询参数url:[{}]" + queryAntifraudInfoUrl);
			BmsLogger.info("算话征信查询，查询参数map:[{}]" + requestMap.toString());
			resp = postBaseMethod(queryAntifraudInfoUrl, requestMap);
			BmsLogger.info("算话征信查询，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "算话征信查询");
		}
	}

	@Override
	public JSONObject getThirdPartyInfo(Map<String, Object> appDataMap) {
		JSONObject resp = null; 
		JSONObject json = new JSONObject();
		json.put("idCard", appDataMap.get("idCard").toString());//身份证
		json.put("name", appDataMap.get("name").toString());//用户名
		json.put("creatorId",appDataMap.get("creatorId").toString());//员工
		json.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
		json.put("sources", EnumConstants.BMS_SYSCODE);//来源
		
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("param", json.toString());
		
		try {
			// 调用接口
			BmsLogger.info("第三方接口查询记录获取，查询参数url:[{}]" + queryThirdPartyInfo);
			BmsLogger.info("第三方接口查询记录获取，查询参数map:[{}]" + requestMap.toString());
			resp = postBaseMethod(queryThirdPartyInfo, requestMap);
			BmsLogger.info("第三方接口查询记录获取，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "第三方接口查询记录获取");
		}
	}

	@Override
	public HttpResponse queryMainByIdNo(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("idNo", appDataMap.get("idNo").toString());//身份证
		json.put("name", appDataMap.get("name").toString());//用户名
		try {
			BmsLogger.info("老政审数据获取，查询参数url:[{}]" + queryMainByIdNo);
			BmsLogger.info("老政审数据获取，查询参数map:[{}]" + json.toString());
			resp = HttpUtil.post(queryMainByIdNo, json.toString(), true);
			BmsLogger.info("老政审数据获取，查询参数res:[{}]" + gson.toJson(resp));
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "老政审数据获取");
		}
	}


	@Override
	public HttpResponse getSalesman(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("idNum", appDataMap.get("idNum").toString());//身份证
		json.put("name", appDataMap.get("name").toString());//用户名
		
		String url = "?idNum="+appDataMap.get("idNum").toString()+"&name="+appDataMap.get("name").toString()+"";
		try {
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数url:[{}]" + getSalesman);
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数map:[{}]" + json.toString());
			resp = HttpUtil.post(getSalesman+url, null, false);
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数res:[{}]" + gson.toJson(resp));
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "(电销)获取用户是否在已分派名单");
		}
	}
	@Override
	public HttpResponse getSalesman2(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("idNum", appDataMap.get("idNum").toString());//身份证
		//json.put("name", appDataMap.get("name").toString());//用户名
		
		String url = "?idNum="+appDataMap.get("idNum").toString();
		try {
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数url:[{}]" + getSalesman2);
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数map:[{}]" + json.toString());
			resp = HttpUtil.post(getSalesman2+url, null, false);
			BmsLogger.info("(电销)获取用户是否在已分派名单，查询参数res:[{}]" + gson.toJson(resp));
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "(电销)获取用户是否在已分派名单");
		}
	}

	@Override
	public HttpResponse personValidate(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("idNo", appDataMap.get("idNum").toString());//身份证
		
		String url = "?idNo="+appDataMap.get("idNum").toString()+"";
		try {
			BmsLogger.info("(电销)查询用户是否是优质客户名单，查询参数url:[{}]" + personValidate);
			BmsLogger.info("(电销)查询用户是否是优质客户名单，查询参数map:[{}]" + json.toString());
			resp = HttpUtil.post(personValidate+url, null, false);
			BmsLogger.info("(电销)查询用户是否是优质客户名单，查询参数res:[{}]" + gson.toJson(resp));
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "(电销)查询用户是否是优质客户名单");
		}
	}

	@Override
	public JSONObject creditReportAnalyze(Map<String, Object> appDataMap) {
		String param = ObjectUtils.toString(appDataMap.get("param"));
		String content = sendPost(creditReportAnalyze, param);
		
		return new JSONObject(content);
	}
	
	public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            BmsLogger.info("陆金所征信报告解析成功：" + result);
        } catch (Exception e) {
        	BmsLogger.info("陆金所征信报告解析异常：" + e);
        	e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	@Override
	public HttpResponse applyCreditReportRule(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("reportId",appDataMap.get("reportId").toString());//央行报告id
		
		Map<String, String> requestMap = new HashMap<String, String>();
		String url = "?reportId="+appDataMap.get("reportId").toString()+"";
		try {
			// 调用接口
			BmsLogger.info("央行报告限制条件接口，查询参数url:[{}]" + applyCreditReportRule);
			BmsLogger.info("央行报告限制条件接口，查询参数map:[{}]" + requestMap.toString());
			resp = HttpUtil.post(applyCreditReportRule+url, null, false);
			BmsLogger.info("央行报告限制条件接口，查询参数res:[{}]" + resp.toString());
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			return resp;
		}
	}

	
	@Override
	public HttpResponse queryOldRefuseAndCancelAndLimitDaysData(Map<String, Object> appDataMap) {
		HttpResponse resp = null; 
		
		JSONObject json = new JSONObject();
		json.put("idNo", appDataMap.get("idNo").toString());//身份证
		try {
			BmsLogger.info("老政审数据获取最近一次拒绝时间，取消时间，限制天数，查询参数url:[{}]" +queryOldRefuseAndCancelInfo );
			BmsLogger.info("老政审数据获取最近一次拒绝时间，取消时间，限制天数，查询参数map:[{}]" + json.toString());
			resp = HttpUtil.post(queryOldRefuseAndCancelInfo, json.toString(), true);
			BmsLogger.info("老政审数据获取最近一次拒绝时间，取消时间，限制天数，查询参数res:[{}]" + gson.toJson(resp));
			return resp;
		} catch (Exception e) {
			BmsLogger.error(e.getMessage());;
			throw new BizException(CoreErrorCode.FACADE_ERROR, "老政审数据获取最近一次拒绝时间，取消时间，限制天数");
		}
	}
	
}
