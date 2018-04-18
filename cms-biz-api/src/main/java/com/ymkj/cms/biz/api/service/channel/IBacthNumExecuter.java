package com.ymkj.cms.biz.api.service.channel;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBacthNumVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBacthNumVo;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理
 */
public interface IBacthNumExecuter {

	/**
	 * 根据借款信息生成批次号
	 * @param reqLoanNumberVo
	 * @return
	 */
	Response<ResBacthNumVO> createBacthNum(ReqBacthNumVO reqBatchNumVo);
	
	/**
	 * 根据渠道和批次号查询批次信息
	 * @param reqLoanBacthNumVo
	 * @return
	 */
	PageResponse<ResLoanBacthNumVo> searchBacthNum(ReqLoanBacthNumVo reqLoanBacthNumVo);
	
}
