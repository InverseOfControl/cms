package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSEnumCodeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class EnumCodeFacade extends BaseFacade {
	@Autowired
	private IBMSEnumCodeExecuter enumCodeExecuter;

	public PageResult<ResBMSEnumCodeVO> listPage(ReqBMSEnumCodeVO reqDemoVO) {

		// 业务调用
		PageResponse<ResBMSEnumCodeVO> pageResponse = enumCodeExecuter.listPage(reqDemoVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSEnumCodeVO> pageResult = new PageResult<ResBMSEnumCodeVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addEnumCode(ReqBMSEnumCodeVO reqDemoVO) throws BusinessException {
		// 请求参数构造
		reqDemoVO.setSysCode("1111111");
		// 接口调用
		Response<ResBMSEnumCodeVO> response = enumCodeExecuter.addEnumCode(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean updateEnumCode(ReqBMSEnumCodeVO reqDemoVO) throws BusinessException {

		reqDemoVO.setSysCode("1111111");
		// 请求参数构造
		// 接口调用
		Response<ResBMSEnumCodeVO> response = enumCodeExecuter.updateEnumCode(reqDemoVO);
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
	public ResBMSEnumCodeVO findById(Long id) {
		// 请求参数构造
		ReqBMSEnumCodeVO reqDemoVO = new ReqBMSEnumCodeVO("1111111");
		reqDemoVO.setCodeId(id);
		// 接口调用
		Response<ResBMSEnumCodeVO> response = enumCodeExecuter.getAllEnumCode(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	/**
	 * 1. 请求参数构造 2. 接口调用 3. 响应结果处理
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public ResListVO<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO){
		reqBMSEnumCodeVO.setSysCode("1111111");
		ResListVO<ResBMSEnumCodeVO>  response = enumCodeExecuter.findEnumCodeByCondition(reqBMSEnumCodeVO);
		return response;
		
	}
	/**
	 * 根据条件查询CODE
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public ResListVO<ResBMSEnumCodeVO> listEnumCodeBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO){
		reqBMSEnumCodeVO.setSysCode("1111111");
		ResListVO<ResBMSEnumCodeVO>  response = enumCodeExecuter.listEnumCodeBy(reqBMSEnumCodeVO);
		return response;
	}
	
	/**
	 * 根据vo传入的信息查询实体
	 * @param VO
	 * @return
	 */
	 public ResBMSEnumCodeVO findByVO(ReqBMSEnumCodeVO VO) {
			// 请求参数构造
		 VO.setSysCode("1111111");
			// 接口调用
		/*	reqDemoVO.setProductId(Long.valueOf(ProductId.toString()));*/
			Response<ResBMSEnumCodeVO> response = enumCodeExecuter.findByVo(VO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return response.getData();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
		}
		public ResListVO<ResBMSEnumCodeVO> listby(ReqBMSEnumCodeVO reqBMSEnumCodeVO){
			reqBMSEnumCodeVO.setSysCode("1111111");
			ResListVO<ResBMSEnumCodeVO>  response = enumCodeExecuter.listBy(reqBMSEnumCodeVO);
			return response;
		}
}
