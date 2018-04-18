package com.ymkj.cms.biz.api.service.channel;

import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:报盘接口
 */
public interface ILinePaymentExecuter {

	/**
	 * 生成保存报盘相关表数据
	 * 
	 * @param requestVo
	 * @return
	 */
	Response<Object> createHaTwoOffer(ReqUnderLinePaymentVo requestVo);

	/**
	 * 导出报盘文件
	 * 
	 * @param requestVo
	 * @return
	 */
	Response<Map<String,Object>> expHaTwoOffer(ReqUnderLinePaymentVo requestVo);

	/**
	 * 导入报盘文件
	 * 
	 * @param requestVo
	 * @return
	 */
	Response<Object> impHaTwoOffer(ReqUnderLinePaymentVo requestVo);

}
