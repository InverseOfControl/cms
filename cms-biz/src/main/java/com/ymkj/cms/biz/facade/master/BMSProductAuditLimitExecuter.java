package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSProductAuditLimitService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
@Service
public class BMSProductAuditLimitExecuter implements IBMSProductAuditLimitExecuter {
	
	@Autowired
	private IBMSProductAuditLimitService productAuditLimitService;

	@Override
	public Response<ResBMSProductAuditLimitVO> findById(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
		Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
		if(reqBMSProductAuditLimitVO.getAuditLimitId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"auditLimitId"});
		}
		BMSProductAuditLimit product = productAuditLimitService.getById(reqBMSProductAuditLimitVO.getAuditLimitId());
		
		// 构造相应参数
		if(product != null){
			ResBMSProductAuditLimitVO resProductAuditLimitVO = new ResBMSProductAuditLimitVO();
			BeanUtils.copyProperties(product, resProductAuditLimitVO);
			response.setData(resProductAuditLimitVO);
		}				
		
		return response;
	}
	/**
	 * 此方法为外系统提供产品对应期限有误（未关联网点产品期限配置）
	 */
	@Override
	@Deprecated
	public ResListVO<ResBMSProductAuditLimitVO> listProductLimitBy(
			ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
		ResListVO<ResBMSProductAuditLimitVO> response = new ResListVO<ResBMSProductAuditLimitVO>();
		// 参数校验  
		if(reqBMSProductAuditLimitVO.getProductId() != null || reqBMSProductAuditLimitVO.getProductCode() != null){
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productId|productCode"});
		}
		
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductAuditLimitVO);
			//防止isDisabled为Long类型时    <if test="isDisabled != null and isDisabled !='' ">永远为false
			//Long转String
			if(paramMap.get("isDisabled") != null){
				paramMap.put("isDisabled",paramMap.get("isDisabled").toString());
			}
			
			
			List<BMSProductAuditLimit> listProduct = productAuditLimitService
					.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductAuditLimitVO> records = new ArrayList<ResBMSProductAuditLimitVO>();
				for (BMSProductAuditLimit product : listProduct) {
					ResBMSProductAuditLimitVO vo = new ResBMSProductAuditLimitVO();
					BeanUtils.copyProperties(product, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSProductAuditLimitVO> updateProductLimit(
			ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
		Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
		try {
			productAuditLimitService.updateProductLimit(reqBMSProductAuditLimitVO);
			 response.setRepMsg("true");
			 
			// 构造相应参数
		} catch (Exception e) {
			// 抛出自定义异常
			System.out.println(e);
			/*throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);*/
		}
		return response;
	}
	
	//新增
		@Override
		public Response<ResBMSProductAuditLimitVO> addProductLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
			BMSProductAuditLimit productAuditLimit = new BMSProductAuditLimit();
			BeanUtils.copyProperties(reqProductAuditLimitVO, productAuditLimit);
			long count = this.productAuditLimitService.saveProductLimit(productAuditLimit);
		    response.setRepMsg(String.valueOf(count));
		    return response;
		}
		//分页查询
		@Override
		public PageResponse<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
	        PageResponse<ResBMSProductAuditLimitVO> response = new PageResponse<ResBMSProductAuditLimitVO>();
			
			// 参数校验
			try {
				// 构造请求参数
				PageParam pageParam = new PageParam(reqProductAuditLimitVO.getPageNum(), reqProductAuditLimitVO.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqProductAuditLimitVO);
				// 调用业务逻辑,得到list集合
				PageBean<BMSProductAuditLimit> pageBean = this.productAuditLimitService.listPage(pageParam, paramMap);
				// 构造响应参数
				List<ResBMSProductAuditLimitVO> records = new ArrayList<ResBMSProductAuditLimitVO>();
				List<BMSProductAuditLimit> productAuditLimitEntitys = pageBean.getRecords();
				for (BMSProductAuditLimit productAuditLimitEntity : productAuditLimitEntitys) {
					ResBMSProductAuditLimitVO resProductAuditLimitVO = new ResBMSProductAuditLimitVO();
					BeanUtils.copyProperties(productAuditLimitEntity, resProductAuditLimitVO);
					records.add(resProductAuditLimitVO);
				}
				// 忽略 复制的字段
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);

			} catch (Exception e) { 
				// 抛出自定义异常
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
			return response;
		}
		
		//根据 auditLimitId 查询 产品期限(忽略isDisabled)
		@Override
		public Response<ResBMSProductAuditLimitVO> findByAuditLimitId(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
			if(reqProductAuditLimitVO.getAuditLimitId() == null){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"auditLimitId"});
			}
			BMSProductAuditLimit product = productAuditLimitService.findByAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
			
			// 构造相应参数
			if(product != null){
				ResBMSProductAuditLimitVO resProductAuditLimitVO = new ResBMSProductAuditLimitVO();
				BeanUtils.copyProperties(product, resProductAuditLimitVO);
				response.setData(resProductAuditLimitVO);
			}				
			return response;
		}
		
		@Override
		public Response<ResBMSProductAuditLimitVO> findByVO(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
			BMSProductAuditLimit product = this.productAuditLimitService.findByVO(reqProductAuditLimitVO);
			// 构造相应参数
			if (product != null) {
				ResBMSProductAuditLimitVO resProductAuditLimitVO = new ResBMSProductAuditLimitVO();
				BeanUtils.copyProperties(product, resProductAuditLimitVO);
				response.setData(resProductAuditLimitVO);
			}
			System.out.println("reponse:"+response);
			return response;
		}
		@Override
		public ResListVO<ResBMSProductAuditLimitVO> findLimitByChaIdUserCode(
				ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			

			ResListVO<ResBMSProductAuditLimitVO> response = new ResListVO<ResBMSProductAuditLimitVO>();
			// 参数校验  
			if(reqProductAuditLimitVO.getChannelId()== null || reqProductAuditLimitVO.getUserCode() == null
					||"".equals(reqProductAuditLimitVO.getUserCode())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelId | userCode"});
			}
			//TODO :这里先调用用户系统根据用户userCode获取orgId,然后查询,这里先默认一个orgId
			reqProductAuditLimitVO.setOrgId(7L);
			try {
				// 构造请求参数
				Map<String, Object> paramMap = ProductUtils.bean2map(reqProductAuditLimitVO);
				List<BMSProductAuditLimit> listProduct = productAuditLimitService.findLimitByChaIdUserCode(paramMap);
				if (listProduct != null && listProduct.size() > 0) {
					List<ResBMSProductAuditLimitVO> records = new ArrayList<ResBMSProductAuditLimitVO>();
					for (BMSProductAuditLimit product : listProduct) {
						ResBMSProductAuditLimitVO vo = new ResBMSProductAuditLimitVO();
						BeanUtils.copyProperties(product, vo);
						records.add(vo);
					}
					response.setParamList(records);
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 抛出自定义异常
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
			return response;
		
		}
		
		public ResListVO<ResBMSProductAuditLimitVO> findLimitByChaIdOrgId(
				ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			ResListVO<ResBMSProductAuditLimitVO> response = new ResListVO<ResBMSProductAuditLimitVO>();
			// 参数校验  
			if(reqProductAuditLimitVO.getChannelId()== null || reqProductAuditLimitVO.getOrgId() == null
					||"".equals(reqProductAuditLimitVO.getUserCode())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelId | orgId"});
			}
			try {
				// 构造请求参数
				Map<String, Object> paramMap = ProductUtils.bean2map(reqProductAuditLimitVO);
				List<BMSProductAuditLimit> listProduct = productAuditLimitService.findLimitByChaIdOrgId(paramMap);
				if (listProduct != null && listProduct.size() > 0) {
					List<ResBMSProductAuditLimitVO> records = new ArrayList<ResBMSProductAuditLimitVO>();
					for (BMSProductAuditLimit product : listProduct) {
						ResBMSProductAuditLimitVO vo = new ResBMSProductAuditLimitVO();
						BeanUtils.copyProperties(product, vo);
						records.add(vo);
					}
					response.setParamList(records);
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 抛出自定义异常
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
			return response;
		
		}
		@Override
		public Response<ResBMSProductAuditLimitVO> updateByProductId(
				ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
			Response<ResBMSProductAuditLimitVO> response = new Response<ResBMSProductAuditLimitVO>();
			try {
				productAuditLimitService.updateByProductId(reqBMSProductAuditLimitVO);
				 response.setRepMsg("true");
				 
				// 构造相应参数
			} catch (Exception e) {
				// 抛出自定义异常
				System.out.println(e);
				/*throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);*/
			}
			return response;
		}
		@Override
		public Response<ResBMSProductAuditLimitVO> updateProductTerm(
				ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
			Response<ResBMSProductAuditLimitVO> response=new Response<ResBMSProductAuditLimitVO>();
			long result=productAuditLimitService.deleteProductTerm(reqBMSProductAuditLimitVO);
			response.setRepMsg(String.valueOf(result));
			return response;
		}
		@Override
		public Response<List<ResBMSOrgLimitChannelVO>> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req) {
			Response<List<ResBMSOrgLimitChannelVO>> response=new Response<List<ResBMSOrgLimitChannelVO>>();
			List<BMSOrgLimitChannel> list=productAuditLimitService.findOutletByAuditLimitId(req);
			List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
			if (list != null && list.size() > 0) {
				
				for (BMSOrgLimitChannel bmsOrg : list) {
					ResBMSOrgLimitChannelVO vo = new ResBMSOrgLimitChannelVO();
					BeanUtils.copyProperties(bmsOrg, vo);
					records.add(vo);
				}
			}
			response.setData(records);
			return response;
		}
		@Override
		public Response<Boolean> updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req) {
			Response<Boolean> response=new Response<Boolean>();
			Boolean b=productAuditLimitService.updateOrgLimitByAuditId(req);
			response.setData(b);
			return response;
		}
		@Override
		public Response<Boolean> updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo) {
			Response<Boolean> response=new Response<Boolean>();
			Boolean b=productAuditLimitService.updateOrgLimitById(channelVo);
			response.setData(b);
			return response;
		}
		@Override
		public Response<List<ResBMSProductAuditLimitVO>> listAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO) {
			Response<List<ResBMSProductAuditLimitVO>> response=new Response<List<ResBMSProductAuditLimitVO>>();
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("productIds", reqDemoVO.getProductIds());
			List<ResBMSProductAuditLimitVO> records = new ArrayList<ResBMSProductAuditLimitVO>();
			List<Object> list=productAuditLimitService.listBy(param, "queryLimitByProIds");
			if (list != null && list.size() > 0) {
				for (Object bmsOrg : list) {
					ResBMSProductAuditLimitVO vo = new ResBMSProductAuditLimitVO();
					BeanUtils.copyProperties(bmsOrg, vo);
					records.add(vo);
				}
			}
			response.setData(records);
			return response;
		}
}

