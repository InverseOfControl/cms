package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSAppPersonInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class AppPersonInfoFacade extends BaseFacade{
	
	@Autowired
	private IBMSAppPersonInfoExecuter iBMSAppPersonInfoExecuter;

	public PageResult<ResBMSAppPersonInfoVO> listPage(ReqBMSAppPersonInfoVO reqSysLoanLogVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSAppPersonInfoVO> pageResponse = iBMSAppPersonInfoExecuter.listPage(reqSysLoanLogVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSAppPersonInfoVO> pageResult = new PageResult<ResBMSAppPersonInfoVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

}
