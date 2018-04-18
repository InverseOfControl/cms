package com.ymkj.cms.biz.common.loan;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.SpringContextHolder;
import com.ymkj.cms.biz.common.sign.Sign;
import com.ymkj.cms.biz.exception.BizErrorCode;

/**
 * 签约工厂
 * 
 * @author YM10138
 *
 */
public final class LoanFactory {

	private final static Log log = LogFactory.getLog(LoanFactory.class);
	@SuppressWarnings("rawtypes")
	private final static Map<String, Class<? extends Loan>> map = new HashMap<>();
	// 已注册渠道
	private final static Map<String, Integer> channelCd = new HashMap<>();

	/**
	 * 注册渠道
	 * 
	 * @param code
	 */
	public static void addChannelCd(String code) {
		channelCd.put(code, 0);
	}

	/**
	 * 验证渠道是否已经注册
	 * 
	 * @param code
	 * @return
	 */
	public static boolean containsChannelCd(String code) {
		return channelCd.containsKey(code);
	}

	/**
	 * 渠道签约业务节点注册
	 * 
	 * @param code
	 * @param node
	 * @param controllerClass
	 */
	public static <T,M> void addRoutes(String code, String node, Class<? extends Loan<T,M>> controllerClass) {
		map.put(code.concat(node), controllerClass);
	}

	/**
	 * 渠道签约业务节点获取
	 * 
	 * @param code
	 *            渠道CODE
	 * @param node
	 *            业务节点
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Loan get(String code, String node) {
		Class<? extends Loan> clazz = map.get(code.concat(node));
		log.debug(String.format("获取签约渠道服务节点 key:%s %s value:%s", code, node, clazz.getName()));
		return SpringContextHolder.getBean(clazz);
	}

	/**
	 * 渠道签约业务处理
	 * @param <T>
	 * 
	 * @param reqLoanContractSignVo
	 * @param code
	 * @param node
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T,M>  Response<M> execute(T reqVo, String code,
			String node) {
		
		if (log.isDebugEnabled()) {
			log.debug("-----------已注册签约渠道服务节点-----------");
			for (Entry<String, Class<? extends Loan>> m : map.entrySet())
				log.debug(String.format("key:%s value:%s", m.getKey(), m.getValue().getName()));
			log.debug("-----------已注册签约渠道服务节点-----------/");
		}
		Response<M> res = new Response<M>();
		// 业务节点是否已经注册
		if (!map.containsKey(code.concat(node))) {
			res.setRepCode(BizErrorCode.SERVICE_NOT_REGISERED.getErrorCode());
			res.setRepMsg(BizErrorCode.SERVICE_NOT_REGISERED.getDefaultMessage());
			return res;
		}
		Loan<T,M> s = get(code, node);
		if (!s.before(reqVo, res))
			return res;
		if (!s.execute(reqVo, res))
			return res;
		if (!s.after(reqVo, res))
			return res;
		return res;
	}
	
}
