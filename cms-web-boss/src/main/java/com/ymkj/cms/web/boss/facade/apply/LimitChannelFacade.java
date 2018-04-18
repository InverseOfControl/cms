package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class LimitChannelFacade extends BaseFacade{
	@Autowired
	private IBMSLimitChannelExecuter bmsLimitChannelExecuter;
	
	 public int logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO) throws BusinessException {
	    	// 请求参数构造
		    reqOrgLimitChannelVO.setSysCode("1111111");
	    	// 接口调用
	 		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
	 		response=bmsLimitChannelExecuter.logicalDelete(reqOrgLimitChannelVO);
	 		
	 		// 响应结果处理, 如果失败 则抛出 处理失败异常
	 		if (response.isSuccess()) {
	 			return Integer.valueOf(response.getRepMsg());
	 		} else {
	 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
	 		}
	    }
		  public PageResult<ResBMSLimitChannelVO> listPage(ReqBMSLimitChannelVO reqBMSLimitChannelVO) {
	    	 reqBMSLimitChannelVO.setSysCode("1111111"); 
			// 业务调用
			PageResponse<ResBMSLimitChannelVO> pageResponse = bmsLimitChannelExecuter.listPage(reqBMSLimitChannelVO);

			// 响应结果处理
			if (pageResponse.isSuccess()) {
				PageResult<ResBMSLimitChannelVO> pageResult = new PageResult<ResBMSLimitChannelVO>();
				BeanUtils.copyProperties(pageResponse, pageResult);
				return pageResult;
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
			}
		}
	 public int updateLimitChannelById(ReqBMSLimitChannelVO reqLimitChannelVo){
		 Response<ResBMSLimitChannelVO> response = new Response<ResBMSLimitChannelVO>();
		 response=bmsLimitChannelExecuter.updateByAuLimitId(reqLimitChannelVo);

		 // 响应结果处理, 如果失败 则抛出 处理失败异常
		 if (response.isSuccess()) {
			 return Integer.valueOf(response.getRepMsg());
		 } else {
			 throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		 }
	 }
	 
	 
	

}
