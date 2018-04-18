package com.ymkj.cms.web.boss.facade.channel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.service.channel.ILinePaymentExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.web.boss.common.SystemCode;

/**
 * @author YM10189
 * @date 2017年5月17日
 * @Description:报盘生成
 */
@Component
public class LinePaymentFacade extends BaseFacade {

	@Autowired
	private ILinePaymentExecuter iLinePaymentExecuter;

	public Response<Object> createOffer(ReqUnderLinePaymentVo requestVo) {
		Response<Object> response = new Response<Object>();
		requestVo.setSysCode(SystemCode.SysCode);
		response = iLinePaymentExecuter.createHaTwoOffer(requestVo);
		return response;
	}

	public Response<Map<String,Object>> exportOffer(ReqUnderLinePaymentVo requestVo) {
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		requestVo.setSysCode(SystemCode.SysCode);
		response = iLinePaymentExecuter.expHaTwoOffer(requestVo);
		return response;
	}

	public Response<Object> importCounteroffer(ReqUnderLinePaymentVo requestVo) {
		Response<Object> response = new Response<Object>();
		requestVo.setSysCode("1111111111");
		response = iLinePaymentExecuter.impHaTwoOffer(requestVo);
		return response;
	}
	

}
