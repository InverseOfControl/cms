package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSContractChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChannelVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class ContractChannelFacade extends BaseFacade{
	
	@Autowired
	private IBMSContractChannelExecuter contractChannelExcuter;
	
	
   /***
    * 查询渠道合同表集合
    * @param reqBMSContractChannelVO
    * @return
    */
	public PageResult<ResBMSContractChannelVO> listPage(ReqBMSContractChannelVO reqBMSContractChannelVO) {
		reqBMSContractChannelVO.setSysCode(EnumConstants.BMS_SYSCODE); 
		// 业务调用
		PageResponse<ResBMSContractChannelVO> pageResponse = contractChannelExcuter.listPage(reqBMSContractChannelVO);
		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSContractChannelVO> pageResult = new PageResult<ResBMSContractChannelVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

}
