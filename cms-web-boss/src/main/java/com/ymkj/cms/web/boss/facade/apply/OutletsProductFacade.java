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
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class OutletsProductFacade extends BaseFacade {
	
	@Autowired
	private IBMSProductExecuter iBMSProductExecuter;
	
	@Autowired
	private IBMSOrgLimitChannelExecuter iBMSOrgLimitChannelExecuter;
	
	@Autowired
	private IBMSProductAuditLimitExecuter iBMSProductAuditLimitExecuter;
	
	
	/**
	 * 根据渠道ID获取产品数据
	 * @param id
	 * @return
	 */
	public List<ResBMSProductVO> findProByChannelId(ReqBMSProductVO reqBMSProductVO) {
		// 请求参数构造
		ResListVO<ResBMSProductVO> response = iBMSProductExecuter.findProByChannelId(reqBMSProductVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	/**
	 * 获取产品数据
	 * @param id
	 * @return
	 */
	public List<ResBMSProductVO> listProByCondition(ReqBMSProductVO reqBMSProductVO) {
		// 请求参数构造
		ResListVO<ResBMSProductVO> response = iBMSProductExecuter.listProByCondition(reqBMSProductVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	/**
	 * 根据渠道ID获取产品数据 无渠道
	 * @param id
	 * @return
	 */
	public List<ResBMSProductVO> findProByChannelIdNotChannel(ReqBMSProductVO reqBMSProductVO) {
		// 请求参数构造
		ResListVO<ResBMSProductVO> response = iBMSProductExecuter.findProByChannelIdNotChannel(reqBMSProductVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	/**
	 * 获取渠道产品分页对象
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResult<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		PageResponse<ResBMSOrgLimitChannelVO> resonse = iBMSOrgLimitChannelExecuter.listPage(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (resonse.isSuccess()) {
			PageResult<ResBMSOrgLimitChannelVO> pageResult = new PageResult<ResBMSOrgLimitChannelVO>();
			BeanUtils.copyProperties(resonse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(resonse));
		}				
	}
	
	/**
	 * 渠道产品更新是否可用状态
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean updateProductLimit(
			ReqBMSOrgLimitChannelVO  reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = iBMSOrgLimitChannelExecuter.updateProductLimit(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(response));
				}
	}
	
	/**
	 * 根据产品Id获取数据审批期限
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public List<ResBMSProductAuditLimitVO> findLimitByProductId(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO){
		ResListVO<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.listProductLimitBy(reqBMSProductAuditLimitVO);
		// 响应结果处理
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}		
	}
	
	/**
	 * 渠道产品期限保存
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean saveOrgProductLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = iBMSOrgLimitChannelExecuter.saveProductLimit(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(response));
		}
	}
	/**
	 * 优惠汇率渠道产品期限保存
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean saveRateOrgChannelLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = iBMSOrgLimitChannelExecuter.saveRateOrgChannelLimit(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(response));
				}
	}
	
	
	/**
	 * 1. 获取所有门店渠道产品信息根据条件
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResBMSOrgLimitChannelVO> listOrgLimitChannelBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		// 请求参数构造
		Response<ResProductBaseListVO> response = iBMSOrgLimitChannelExecuter.listOrgLimitChannelBy(reqBMSOrgLimitChannelVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData().getListOrgLimitChannel();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	
	 public int logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO) throws BusinessException {
	    	// 请求参数构造
		    reqOrgLimitChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
	    	// 接口调用
	 		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
	 		response=iBMSOrgLimitChannelExecuter.logicalDelete(reqOrgLimitChannelVO);
	 		
	 		// 响应结果处理, 如果失败 则抛出 处理失败异常
	 		if (response.isSuccess()) {
	 			return Integer.valueOf(response.getRepMsg());
	 		} else {
	 			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
	 		}
	    }
	 	/**
		 * 根据渠道ID获取优惠产品数据
		 * @param id
		 * @return
		 */
		public List<ResBMSProductVO> findProRateByChannelId(ReqBMSProductVO reqBMSProductVO) {
			// 请求参数构造
			ResListVO<ResBMSProductVO> response = iBMSProductExecuter.findProRateByChannelId(reqBMSProductVO);
			
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return response.getCollections();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
		}
		/**
		 * 1. 获取所有优惠门店渠道产品信息根据条件
		 * 2. 接口调用
		 * 3. 响应结果处理
		 * @param id
		 * @return
		 */
		public List<ResBMSOrgLimitChannelVO> listOrgLimitChannelRateBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
			// 请求参数构造
			Response<ResProductBaseListVO> response = iBMSOrgLimitChannelExecuter.listOrgLimitChannelRateBy(reqBMSOrgLimitChannelVO);
			
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				return response.getData().getListOrgLimitChannel();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
			}
		}
    

}
