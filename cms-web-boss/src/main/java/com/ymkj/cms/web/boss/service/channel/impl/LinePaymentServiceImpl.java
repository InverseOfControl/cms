package com.ymkj.cms.web.boss.service.channel.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.web.boss.facade.channel.LinePaymentFacade;
import com.ymkj.cms.web.boss.service.channel.ILinePaymentService;

/**
 * @author YM10189
 * @date 2017年5月17日
 * @Description:报盘管理
 */
@Service
public class LinePaymentServiceImpl implements ILinePaymentService {

	@Autowired
	private LinePaymentFacade linePaymentFacade;

	@Override
	public Response<Object> createOffer(ReqUnderLinePaymentVo requestVo) {
		return linePaymentFacade.createOffer(requestVo);
	}

	@Override
	public Response<Map<String,Object>> exportOffer(ReqUnderLinePaymentVo requestVo) {
		return linePaymentFacade.exportOffer(requestVo);
	}

	@Override
	public Response<Object> importCounteroffer(ReqUnderLinePaymentVo requestVo) {
		return linePaymentFacade.importCounteroffer(requestVo);
	}

}
