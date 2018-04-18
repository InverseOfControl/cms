package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSTmParameterExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class TmParameterFacade extends BaseFacade {

	@Autowired
	private IBMSTmParameterExecuter tmParameterExecuter;

	public PageResult<ResBMSTmParameterVO> listPage(ReqBMSTmParameterVO reqDemoVO) {

		// 业务调用
		PageResponse<ResBMSTmParameterVO> pageResponse = tmParameterExecuter.listPage(reqDemoVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSTmParameterVO> pageResult = new PageResult<ResBMSTmParameterVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addTmParameter(ReqBMSTmParameterVO reqDemoVO) throws BusinessException {
		// 请求参数构造
		reqDemoVO.setSysCode("1111111");
		// 接口调用
		Response<ResBMSTmParameterVO> response = tmParameterExecuter.addTmParameter(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean updateTmParameter(ReqBMSTmParameterVO reqDemoVO) throws BusinessException {

		reqDemoVO.setSysCode("1111111");
		// 请求参数构造
		// 接口调用
		Response<ResBMSTmParameterVO> response = tmParameterExecuter.updateTmParameter(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	/**
	 * 1. 请求参数构造 2. 接口调用 3. 响应结果处理
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSTmParameterVO findById(Long id) {
		// 请求参数构造
		ReqBMSTmParameterVO reqDemoVO = new ReqBMSTmParameterVO("1111111");
		reqDemoVO.setId(id);
		// 接口调用
		Response<ResBMSTmParameterVO> response = tmParameterExecuter.findOne(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
}
