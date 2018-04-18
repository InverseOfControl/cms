package com.ymkj.cms.biz.api.service.master;

import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSTMReasonExecuter {
	
	/**
	 * 一级原因
	 * @param reqBMSTMReasonVO
	 * @return
	 */
	public ResListVO<ReqBMSTMReasonVO> oneLevel(ReqBMSTMReasonVO reqBMSTMReasonVO);
	
	
	/**
	 * 二级原因
	 * @param reqBMSTMReasonVO
	 * @return
	 */
	public ResListVO<ReqBMSTMReasonVO> twoLevel(ReqBMSTMReasonVO reqBMSTMReasonVO);
	
	
	/**
	 * 二级原因
	 * @param reqBMSTMReasonVO
	 * @return
	 */
	public ResListVO<ReqBMSTMReasonVO> twoLevelparents(ReqBMSTMReasonVO reqBMSTMReasonVO);
	
	/**
	 * 根据环节和类型查询原因集合数据
	 */
	public ResListVO<ReqBMSTMReasonVO>findReasonByOperType(ReqBMSTMReasonVO reqBMSTMReasonVO);

}
