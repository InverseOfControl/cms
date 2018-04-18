package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSLoanUrgentConfigExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Component
public class LoanUrgentConfigFacade extends BaseFacade{
	@Autowired
	private IBMSLoanUrgentConfigExecuter iBMSLoanUrgentConfigExecuter;

	public PageResult<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO) {
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		String code =resEmployeeVO.getUsercode();
		reqOrgVO.setUserCode(code);
		// 业务调用
		PageResponse<ResBMSLoanUrgentConfigVO> pageResponse = iBMSLoanUrgentConfigExecuter.listPage(reqOrgVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSLoanUrgentConfigVO> pageResult = new PageResult<ResBMSLoanUrgentConfigVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	
	public Integer updateOrg(ReqBMSUrgentLimitListVO reqOrgVO){
		Response<ResBMSLoanUrgentConfigVO> response = new Response<ResBMSLoanUrgentConfigVO>();
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqOrgVO.setCreator(vo.getUsercode());
		reqOrgVO.setCreatorId(vo.getId().toString());
		response = iBMSLoanUrgentConfigExecuter.updateOrg(reqOrgVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
}
