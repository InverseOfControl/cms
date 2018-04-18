package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSReasonReLinksExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class ReasonReLinksFacade extends BaseFacade{
	
	@Autowired
	private IBMSReasonReLinksExecuter bmsLinksExecuter;
	
	//查询页面数据
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		// 业务调用
		PageResponse<ResBMSReasonVO> pageResponse = bmsLinksExecuter.listPage(reqReasonVO);
		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSReasonVO> pageResult = new PageResult<ResBMSReasonVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	public ResBMSReasonVO queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO){
		// 请求参数构造
				reqBMSReasonVO.setSysCode("1111111");
				// 接口调用
				Response<ResBMSReasonVO> response = bmsLinksExecuter.findBMSReLinksById(reqBMSReasonVO);
				// 响应结果处理, 如果失败 则抛出 处理失败异常
				if (response.isSuccess()) {
					return response.getData();
				} else {
					throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
				}
		
	}
	
	public int editReasonReLinks (ReqBMSReasonVO reqBMSReasonVO) throws BusinessException {
		// 请求参数构造
		reqBMSReasonVO.setSysCode("1111111");
		// 接口调用
		Response<ResBMSReasonVO> response = bmsLinksExecuter.editReasonReLinks(reqBMSReasonVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
			
	}

}
