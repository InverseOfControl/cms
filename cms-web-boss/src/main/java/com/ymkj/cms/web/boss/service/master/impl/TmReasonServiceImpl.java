package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.master.IBMSTMReasonExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ITmReasonService;

@Service
public class TmReasonServiceImpl implements ITmReasonService {
	
	@Autowired
	private IBMSTMReasonExecuter ibmstmReasonExecuter;
	
	
	/**
	 * 一级原因
	 * @param reqBMSTMReasonVO
	 * @return
	 */
	public ResListVO<ReqBMSTMReasonVO> oneLevel(ReqBMSTMReasonVO reqBMSTMReasonVO){
		ResListVO<ReqBMSTMReasonVO> resListVO  =	ibmstmReasonExecuter.oneLevel(reqBMSTMReasonVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
 		if (resListVO.isSuccess()) {
 			return resListVO;
 		} else {
 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL);
 		}
	}
	
	
	/**
	 * 二级原因
	 * @param reqBMSTMReasonVO
	 * @return
	 */
	public ResListVO<ReqBMSTMReasonVO> twoLevel(ReqBMSTMReasonVO reqBMSTMReasonVO){
		
		ResListVO<ReqBMSTMReasonVO> resListVO  =ibmstmReasonExecuter.twoLevel(reqBMSTMReasonVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (resListVO.isSuccess()) {
 			return resListVO;
 		} else {
 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL);
 		}
		
	}

}
