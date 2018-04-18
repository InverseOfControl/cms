package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSProductService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;


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
public class BMSProductExecuter implements IBMSProductExecuter {
	
	@Autowired
	private IBMSProductService demoService;

	@Autowired
	private IBMSSysLogService sysLogService;
	@Override
	public Response<ResBMSProductVO> findById(ReqBMSProductVO reqBMSProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		if(reqBMSProductVO.getProductId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"productId"});
		}
		BMSProduct product = demoService.getById(reqBMSProductVO.getProductId());
		
		// 构造相应参数
		if(product != null){
			ResBMSProductVO resDemoVO = new ResBMSProductVO();
			BeanUtils.copyProperties(product, resDemoVO);
			response.setData(resDemoVO);
		}				
		
		return response;
	}

	@Override
	public ResListVO<ResBMSProductVO> findByName(ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO>  response = new ResListVO<ResBMSProductVO>();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSProductVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public ResListVO<ResBMSProductVO> listBy(ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public ResListVO<ResBMSProductVO> listProByCondition(ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService
					.listProByCondition(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public ResListVO<ResBMSProductVO> findByOrgId(
			ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		try {
			//调用权限接口，通过用户code获取该用户的营业部信息
			//根据获取的营业部信息查询产品信息
			//reqBMSProductVO.setOrgId(....);
			//后经讨论，直接传orgId进行查询
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService.listProByCondition(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public PageResponse<ResBMSProductVO> listPage(ReqBMSProductVO reqProductVO) {
PageResponse<ResBMSProductVO> response = new PageResponse<ResBMSProductVO>();
		
		// 参数校验
		if(reqProductVO.getPageNum()==0 || reqProductVO.getPageSize()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqProductVO.getPageNum(), reqProductVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqProductVO);
			// 调用业务逻辑,得到list集合
			//PageBean<BMSProduct> pageBean = demoService.listPage(pageParam, paramMap);
			PageBean<BMSProduct> pageBean = demoService.listPage(pageParam, paramMap, "queryProductList", "queryProductCount");
		/*	System.out.println("-------------------pageBean.getRecords().get(0):"+pageBean.getRecords().get(0));*/
			// 构造响应参数
			List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
			
			List<BMSProduct> demoEntitys = pageBean.getRecords();
			for (BMSProduct demoEntity : demoEntitys) {
				ResBMSProductVO resDemoVO = new ResBMSProductVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
			/*response.setData(listVO);*/

		} catch (Exception e) { 
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		
		}
		return response;
	}

	@Override
	public Response<ResBMSProductVO> addProduct(ReqBMSProductVO reqProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		BMSProduct product = new BMSProduct();
		BeanUtils.copyProperties(reqProductVO, product);
		reqProductVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		product.setSysCode(reqProductVO.getSysCode());
		long count = this.demoService.saveProduct(product);
		int result=sysLogService.recordSysLog(reqProductVO, "产品管理|产品配置|insert|null", "addProduct",
				"添加产品配置<" + reqProductVO.getName() + ">");
		if(count>0 && result>0){
			  response.setRepMsg(String.valueOf(count));
		}
	    return response;
	}

	@Override
	public Response<ResBMSProductVO> deleteProduct(ReqBMSProductVO reqProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		BMSProduct product = new BMSProduct();
		BeanUtils.copyProperties(reqProductVO, product);
		reqProductVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		product.setSysCode(reqProductVO.getSysCode());
		this.demoService.deleteProduct(product);
		int result=sysLogService.recordSysLog(reqProductVO, "产品管理|产品配置|delete|null", "deleteProduct",
				"删除产品配置<" + reqProductVO.getName() + ">");
		if(result>0){
			 response.setRepMsg("true");
		}
	    return response;
	}

	@Override
	public Response<ResBMSProductVO> updateProduct(ReqBMSProductVO reqProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		BMSProduct product = new BMSProduct();
		BeanUtils.copyProperties(reqProductVO, product);
		reqProductVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		product.setSysCode(reqProductVO.getSysCode());
		this.demoService.updateProduct(product);
		int result=sysLogService.recordSysLog(reqProductVO, "产品管理|产品配置|update|null", "updateProduct",
				"更新产品配置<" + reqProductVO.getName() + ">");
		if(result>0){
			 response.setRepMsg("true");
		}
	    return response;
	}

	@Override
	public ResListVO<ResBMSProductVO> findProByChannelId(
			ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSProductVO.getChannelIds())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelIds"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService
					.findProByChannelId(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public ResListVO<ResBMSProductVO> findProByChannelIdNotChannel(
			ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSProductVO.getChannelIds())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelId"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			List<BMSProduct> listProduct = demoService
					.findProByChannelIdNotChannel(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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
	public Response<ResBMSProductVO> findByVo(ReqBMSProductVO reqBMSProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		if(reqBMSProductVO.getName() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"name"});
		}
		
		try {
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			BMSProduct product = demoService.getByVo(paramMap);
			
			// 构造相应参数
			if(product != null){
				ResBMSProductVO resDemoVO = new ResBMSProductVO();
				BeanUtils.copyProperties(product, resDemoVO);
				response.setData(resDemoVO);
			}			
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}
			
		
		return response;
	}

	@Override
	public Response<ResBMSProductVO> findByCode(ReqBMSProductVO reqBMSProductVO) {
		Response<ResBMSProductVO> response = new Response<ResBMSProductVO>();
		try {
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			BMSProduct product = demoService.getByVo(paramMap);

			// 构造相应参数
			if(product != null){
				ResBMSProductVO resDemoVO = new ResBMSProductVO();
				BeanUtils.copyProperties(product, resDemoVO);
				response.setData(resDemoVO);
			}			
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}
		return response;	
	}

	@Override
	public Response<Integer> findProductById(Long productId) {
		Response<Integer> response=new Response<Integer>();
		if(StringUtils.isEmpty(productId)){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productId"});
		}
		try {
			int count=demoService.findProductById(productId);
			response.setData(count);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSProductVO> findProRateByChannelId(
			ReqBMSProductVO reqBMSProductVO) {
		ResListVO<ResBMSProductVO> response = new ResListVO<ResBMSProductVO>();
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSProductVO);
			if("".equals(paramMap.get("paramMap"))){
				paramMap.put("paramMap", null);
			}
			List<BMSProduct> listProduct = demoService.findProRateByChannelId(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSProductVO> records = new ArrayList<ResBMSProductVO>();
				for (BMSProduct product : listProduct) {
					ResBMSProductVO vo = new ResBMSProductVO();
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

}
