package com.ymkj.cms.biz.facade.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.channel.ILinePaymentExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBmsLineOfferVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLinePaymentOfferBatch;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferBatch;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.IBMSLinePaymentService;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:报盘接口 impl
 */
@Service
public class LinePaymentExecuter implements ILinePaymentExecuter {

	public static Logger logger = Logger.getLogger(LinePaymentExecuter.class);

	@Autowired
	private IBMSLinePaymentService linePaymentService;

	@Override
	public Response<Object> createHaTwoOffer(ReqUnderLinePaymentVo requestVo) {
		Response<Object> responseInfo = new Response<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String batchNum = requestVo.getBatchNum();
			map.put("batchNum", batchNum);
			String fundsSource = requestVo.getFundsSource();
			if (fundsSource == null || "".equals(fundsSource)) {
				throw new BizException(BizErrorCode.OFFER_OPERATE, "生成报盘记录时必须选择一个渠道!");
			}
			map.put("fundsSource", fundsSource);
			linePaymentService.createOffer(map);
		} catch (BizException e) {
			logger.error(e.getMessage(), e);
			responseInfo.setRepCode(e.getErrorCode().getErrorCode());
			responseInfo.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseInfo.setRepCode(BizErrorCode.OFFER_OPERATE.getErrorCode());
			responseInfo.setRepMsg(BizErrorCode.OFFER_OPERATE.getDefaultMessage());
		}

		return responseInfo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Response<Map<String, Object>> expHaTwoOffer(ReqUnderLinePaymentVo requestVo) {
		List<ResBmsLineOfferVo> records = new ArrayList<ResBmsLineOfferVo>();
		List<ResLinePaymentOfferBatch> sumRecords = new ArrayList<ResLinePaymentOfferBatch>();
		Response<Map<String, Object>> demoEntitys = linePaymentService.queryExpOffer(requestVo);
		List<LinePaymentOffer> datas = (List<LinePaymentOffer>) demoEntitys.getData().get("data");
		List<LinePaymentOfferBatch> summaryData = (List<LinePaymentOfferBatch>) demoEntitys.getData().get("summaryData");

		for (LinePaymentOfferBatch demoEntity : summaryData) {
			ResLinePaymentOfferBatch resDemoVO = new ResLinePaymentOfferBatch();

			BeanUtils.copyProperties(demoEntity, resDemoVO);
			sumRecords.add(resDemoVO);
		}

		for (LinePaymentOffer demoEntity : datas) {
			ResBmsLineOfferVo resDemoVO = new ResBmsLineOfferVo();
			BeanUtils.copyProperties(demoEntity, resDemoVO);
			records.add(resDemoVO);
		}
		demoEntitys.getData().put("data", records);
		demoEntitys.getData().put("summaryData", sumRecords);
		return demoEntitys;
	}

	@Override
	public Response<Object> impHaTwoOffer(ReqUnderLinePaymentVo requestVo) {
		Response<Object> responseInfo = new Response<Object>();
		linePaymentService.updateHaTwoOffer(requestVo);
		return responseInfo;
	}

}
