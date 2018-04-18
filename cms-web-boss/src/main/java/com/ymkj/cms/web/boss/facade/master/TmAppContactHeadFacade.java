package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSTmAppContactHeadExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactHeadVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class TmAppContactHeadFacade extends BaseFacade{
	@Autowired
	private IBMSTmAppContactHeadExecuter bmsTmAppContactHeadExecuter;

	public PageResult<ResBMSTmAppContactHeadVO> listPage(ReqBMSTmAppContactHeadVO reqSysLoanLogVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSTmAppContactHeadVO> pageResponse = bmsTmAppContactHeadExecuter.listPage(reqSysLoanLogVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSTmAppContactHeadVO> pageResult = new PageResult<ResBMSTmAppContactHeadVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

}
