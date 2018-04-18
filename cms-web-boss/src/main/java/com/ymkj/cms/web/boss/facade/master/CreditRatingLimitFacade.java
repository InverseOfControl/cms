package com.ymkj.cms.web.boss.facade.master;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSCreditRatingLimitExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class CreditRatingLimitFacade extends BaseFacade{

	@Autowired
	private IBMSCreditRatingLimitExecuter iBMSCreditRatingLimitExecuter;
	
	
	public PageResult<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSCreditRatingLimitVO> pageResponse = iBMSCreditRatingLimitExecuter.listPage(reqBMSCreditRatingLimitVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSCreditRatingLimitVO> pageResult = new PageResult<ResBMSCreditRatingLimitVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	
	public Response<List<Map<String,Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v){
		return  iBMSCreditRatingLimitExecuter.findByProductAll(v);
	}
	
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.addCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}
	
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.deleteCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}
	
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.loadCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}
	
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.updateCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}
	
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.findAddIsRepeat(reqBMSCreditRatingLimitVO);
	}
	
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO){
		return  iBMSCreditRatingLimitExecuter.findUpdateIsRepeat(reqBMSCreditRatingLimitVO);
	}
}
