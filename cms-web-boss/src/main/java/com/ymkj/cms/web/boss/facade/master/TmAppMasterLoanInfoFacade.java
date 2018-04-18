package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSTmAppMasterLoanInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class TmAppMasterLoanInfoFacade extends BaseFacade{
	@Autowired
	private IBMSTmAppMasterLoanInfoExecuter BMSTmAppMasterLoanInfoExecuter;

	public PageResult<ResBMSTmAppMasterLoanInfoVO> listPage(ReqBMSTmAppMasterLoanInfoVO reqSysLoanLogVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSTmAppMasterLoanInfoVO> pageResponse = BMSTmAppMasterLoanInfoExecuter.listPage(reqSysLoanLogVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSTmAppMasterLoanInfoVO> pageResult = new PageResult<ResBMSTmAppMasterLoanInfoVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
}
