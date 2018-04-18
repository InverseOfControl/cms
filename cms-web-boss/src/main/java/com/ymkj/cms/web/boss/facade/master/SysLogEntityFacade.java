package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSSysLogEntityExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogEntityVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class SysLogEntityFacade extends BaseFacade{
	
	@Autowired
	private IBMSSysLogEntityExecuter bmsSysLogEntityExecuter;
	
	public PageResult<ResBMSSysLogEntityVO> listPage(ReqBMSSysLogEntityVO reqBankVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSSysLogEntityVO> pageResponse = bmsSysLogEntityExecuter.listPage(reqBankVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSSysLogEntityVO> pageResult = new PageResult<ResBMSSysLogEntityVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

}
