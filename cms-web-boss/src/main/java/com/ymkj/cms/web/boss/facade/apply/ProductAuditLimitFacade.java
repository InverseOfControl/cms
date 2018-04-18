package com.ymkj.cms.web.boss.facade.apply;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class ProductAuditLimitFacade extends BaseFacade{

	@Autowired
	private IBMSProductAuditLimitExecuter iBMSProductAuditLimitExecuter;
	
	/**
	 * 添加产品相关期限信息
	 * @param reqProductAuditLimitVO
	 * @return
	 * @throws BusinessException
	 */
	public int addProductAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) throws BusinessException {
    	// 请求参数构造
		reqProductAuditLimitVO.setSysCode("1111111");
    	// 接口调用
 		Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
 		response=iBMSProductAuditLimitExecuter.addProductLimit(reqProductAuditLimitVO);
 		
 		// 响应结果处理, 如果失败 则抛出 处理失败异常
 		if (response.isSuccess()) {
 			return Integer.valueOf(response.getRepMsg());
 		} else {
 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
 		}
    }
	
	/**
	 * 修改产品相关期限信息
	 * @param reqProductAuditLimitVO
	 * @return
	 * @throws BusinessException
	 */
	 public boolean updateProductAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) throws BusinessException {
	    	
		 reqProductAuditLimitVO.setSysCode("1111111");
	    	// 请求参数构造
	   	   // 接口调用
			Response<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.updateProductLimit(reqProductAuditLimitVO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
	    }
	 
	 /**
	  * 根据auditLimitId查询信息,用于修改
	  * @param id
	  * @return
	  */
	 public ResBMSProductAuditLimitVO findByAuditLimitId(Long id) {
			// 请求参数构造
			ReqBMSProductAuditLimitVO reqProductAuditLimitVO = new ReqBMSProductAuditLimitVO("1111111");
			// 接口调用
			reqProductAuditLimitVO.setAuditLimitId(id);
			Response<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.findByAuditLimitId(reqProductAuditLimitVO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return response.getData();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
		}
	 
	    /**
		 * 查询,根据产品id获取审批期限
		 * @param reqProductAuditLimitVO
		 * @return
		 */
		public List<ResBMSProductAuditLimitVO> findLimitByProductId(ReqBMSProductAuditLimitVO reqProductAuditLimitVO){
			ResListVO<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.listProductLimitBy(reqProductAuditLimitVO);
			// 响应结果处理
			if (response.isSuccess()) {
				return response.getCollections();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}		
		}
		
		/**
		 * 分页查询期限
		 * @param reqProductAuditLimitVO
		 * @return
		 */
		 public PageResult<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqProductAuditLimitVO){
					// 业务调用
					PageResponse<ResBMSProductAuditLimitVO> pageResponse = iBMSProductAuditLimitExecuter.listPage(reqProductAuditLimitVO);
					
					// 响应结果处理
					if(pageResponse.isSuccess()){
						PageResult<ResBMSProductAuditLimitVO> pageResult = new PageResult<ResBMSProductAuditLimitVO>();
						BeanUtils.copyProperties(pageResponse, pageResult);
						return pageResult;
					}else{
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
					}
				}
		 
		 
		 
		 /**
		  * 按vo传入的信息查询实体
		  * @param reqProductAuditLimitVO
		  * @return
		  */

			public ResBMSProductAuditLimitVO findByVO(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
				// 请求参数构造
				reqProductAuditLimitVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
				// 接口调用
				Response<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.findByVO(reqProductAuditLimitVO);
				// 响应结果处理, 如果失败 则抛出 处理失败异常
				if (response.isSuccess()) {
					return response.getData();
				} else {
					throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
				}
			}

			/**
			 * 修改产品相关期限信息
			 * @param reqProductAuditLimitVO
			 * @return
			 * @throws BusinessException
			 */
			 public boolean updateProductAuditLimitByPID(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) throws BusinessException {
			    	
				 reqProductAuditLimitVO.setSysCode("1111111");
			    	// 请求参数构造
			   	   // 接口调用
					Response<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.updateByProductId(reqProductAuditLimitVO);
					// 响应结果处理, 如果失败 则抛出 处理失败异常
					if (response.isSuccess()) {
						return new Boolean(response.getRepMsg());
					} else {
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
					}
			    }
			 
			 public boolean deleteProductTerm(ReqBMSProductAuditLimitVO reqProductAuditLimitVO){
				 reqProductAuditLimitVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
				 Response<ResBMSProductAuditLimitVO> response=iBMSProductAuditLimitExecuter.updateProductTerm(reqProductAuditLimitVO);
				 if(response.getRepMsg().equals("1")){
					 return true;
				 }else{
					 throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
				 }

			 }
			 
			 
			 
			 
			 /**
			  * 根据产品期限ID查询
			  * @param req
			  * @return
			  */
			 public List<ResBMSOrgLimitChannelVO> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req){
				 req.setSysCode("bms");
				 Response<List<ResBMSOrgLimitChannelVO>> response = iBMSProductAuditLimitExecuter.findOutletByAuditLimitId(req);
					// 响应结果处理, 如果失败 则抛出 处理失败异常
					if (response.isSuccess()) {
						return response.getData();
					} else {
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
					}
			 }
			 
			 /**
			  *  更新网店产品对应的产品期限ID对应的信息
			  */
			 public boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req){
				 req.setSysCode("bms");
				 Response<Boolean> response = iBMSProductAuditLimitExecuter.updateOrgLimitByAuditId(req);
				// 响应结果处理, 如果失败 则抛出 处理失败异常
					if (response.isSuccess()) {
						return response.getData();
					} else {
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
					}
			 }
			 
			 /**
			  * 根据ID更新网店产品表对应的信息
			  */
			 public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo){
				 channelVo.setSysCode("bms");
				 Response<Boolean> response = iBMSProductAuditLimitExecuter.updateOrgLimitById(channelVo);
				// 响应结果处理, 如果失败 则抛出 处理失败异常
					if (response.isSuccess()) {
						return response.getData();
					} else {
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
					}

			 }
			 
			 public List<ResBMSProductAuditLimitVO> listAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
					// 请求参数构造
					reqProductAuditLimitVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
					// 接口调用
					Response<List<ResBMSProductAuditLimitVO>> response = iBMSProductAuditLimitExecuter.listAuditLimit(reqProductAuditLimitVO);
					// 响应结果处理, 如果失败 则抛出 处理失败异常
					if (response.isSuccess()) {
						return response.getData();
					} else {
						throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
					}
				}
}
