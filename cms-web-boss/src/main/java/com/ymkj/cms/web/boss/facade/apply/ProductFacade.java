package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class ProductFacade extends BaseFacade {

	@Autowired
	private IBMSProductExecuter productExecuter;

	public PageResult<ResBMSProductVO> listPage(ReqBMSProductVO reqDemoVO) {
		 reqDemoVO.setSysCode(EnumConstants.BMS_SYSCODE); 
		// 业务调用
		PageResponse<ResBMSProductVO> pageResponse = productExecuter.listPage(reqDemoVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSProductVO> pageResult = new PageResult<ResBMSProductVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addProduct(ReqBMSProductVO reqDemoVO) throws BusinessException {
		// 请求参数构造
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSCODE);
		// 接口调用
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		response = productExecuter.addProduct(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	
	public boolean deleteProduct(String productId) throws BusinessException {
    	ReqBMSProductVO reqDemoVO = new ReqBMSProductVO("1111111");
    	reqDemoVO.setSysCode("111111");
    	reqDemoVO.setProductId((new Long((long)Integer.valueOf(productId))));
    	// 请求参数构造
   	   // 接口调用
		Response<ResBMSProductVO> response = productExecuter.deleteProduct(reqDemoVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
    }
	
	public ResBMSProductVO findById(Long ProductId) {
		// 请求参数构造
		ReqBMSProductVO reqDemoVO = new ReqBMSProductVO("1111111");
		// 接口调用
		reqDemoVO.setProductId(Long.valueOf(ProductId.toString()));
		Response<ResBMSProductVO> response = productExecuter.findById(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	 public boolean updateProduct(ReqBMSProductVO reqDemoVO) throws BusinessException {
	    	
	    	reqDemoVO.setSysCode("1111111");
	    	// 请求参数构造
	   	   // 接口调用
			Response<ResBMSProductVO> response = productExecuter.updateProduct(reqDemoVO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
	    }
	 
	 
	 public ResBMSProductVO findByVO(ReqBMSProductVO VO) {
			// 请求参数构造
		 VO.setSysCode("1111111");
			// 接口调用
		/*	reqDemoVO.setProductId(Long.valueOf(ProductId.toString()));*/
			Response<ResBMSProductVO> response = productExecuter.findByVo(VO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return response.getData();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
		}
	 
	 public ResBMSProductVO findByCode(ReqBMSProductVO VO){
		 VO.setSysCode("1111111");
		 Response<ResBMSProductVO> response = productExecuter.findByCode(VO);
		 if (response.isSuccess()) {
			 return response.getData();
		 } else {
			 throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		 }
	 }
	 
	 public Integer findProductById(Long productId){
		Response<Integer> response=productExecuter.findProductById(productId);

		return response.getData();
	 }

}
