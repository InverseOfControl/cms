package com.ymkj.cms.web.boss.controller.api.sign;

import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.JsonResult;
import com.ymkj.cms.web.boss.service.sign.IBaoshangBankLimitedService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）
 * 
 * @author YM10138
 *
 */
@Controller
@RequestMapping("api/sign/baoshangbanklimited")
public class BaoshangBankLimitedController extends BaseController {

	private static Log log = LogFactory.getLog(BaoshangBankLimitedController.class);
	@Autowired
	private IBaoshangBankLimitedService baoshangBankLimitedService;

	/**
	 * 风控审核通知
	 * 
	 * @return
	 */
	@RequestMapping("riskManagementNotice")
	@ResponseBody
	public JsonResult<String> riskManagementNotice(HttpServletRequest request) throws IOException {
		log.debug("授信结果通知");
		String receivePost = receivePost(request);
		JsonResult<String> result = baoshangBankLimitedService.riskManagementNotice(receivePost);
		return result;
	}

	/**
	 * 单据撤销通知
	 * 
	 * @return
	 */
	@RequestMapping("revokeNotice")
	@ResponseBody
	public JsonResult<String> revokeNotice(HttpServletRequest request) throws IOException {
		log.debug("单据撤销通知");
		String receivePost = receivePost(request);
		JsonResult<String> result =baoshangBankLimitedService.revokeNotice(receivePost);
		return result;
	}

	/**
	 * 授信成功后超时拒绝结果通知
	 * 
	 * @return
	 */
	@RequestMapping("timeOutRefuse")
	@ResponseBody
	public JsonResult<String> timeOutRefuse(HttpServletRequest request) throws IOException {
		log.debug("超时拒绝结果通知");
		String receivePost = receivePost(request);
		JsonResult<String> result =baoshangBankLimitedService.timeOutRefuse(receivePost);
		return result;
	}

	/**
	 * 资产挑选结果通知
	 * 
	 * @return
	 */
	@RequestMapping("asyResult")
	@ResponseBody
	public JsonResult<String> asyResult(HttpServletRequest request) throws IOException {
		log.debug("资产挑选结果通知");
		String receivePost = receivePost(request);

		return success("成功");
	}

	/**
	 * 获取request里面的json数据
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String receivePost(HttpServletRequest request) throws IOException{
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine())!=null){
			sb.append(line);
		}
		// 将资料解码
		String reqBody = sb.toString();
		log.debug("包银渠道返回参数："+URLDecoder.decode(reqBody, "UTF-8"));
		return URLDecoder.decode(reqBody, "UTF-8");
	}
}
