package com.ymkj.cms.web.boss.service.channel;

import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;

/**
 * @author YM10189
 * @date 2017年5月17日
 * @Description:报盘管理
 */
public interface ILinePaymentService {

	public Response<Object> createOffer(ReqUnderLinePaymentVo requestVo);

	public Response<Map<String,Object>> exportOffer(ReqUnderLinePaymentVo requestVo);

	public Response<Object> importCounteroffer(ReqUnderLinePaymentVo requestVo);
	
}
