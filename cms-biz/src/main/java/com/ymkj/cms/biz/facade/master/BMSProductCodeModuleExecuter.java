package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.master.IBMSProductCodeModuleExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSProductCodeModuleService;

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
public class BMSProductCodeModuleExecuter implements IBMSProductCodeModuleExecuter{

	
	@Autowired
	private IBMSProductCodeModuleService productCodeModuleService;
	
	//保存
	@Override
	public Response<ResBMSProductCodeModuleVO> saveProductCodeModules(ReqBMSProductCodeModuleVO productCodeModuleVO) {
		// 参数校验  
		if(productCodeModuleVO.getProductId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productId"});
		}
		Response<ResBMSProductCodeModuleVO> response = new Response<ResBMSProductCodeModuleVO>();
		boolean insertFlag =true;
		String[] initProducts = productCodeModuleVO.getInitProducts();//产品原先的module信息
		String[] initProductModuleIds = null;// 产品原先的moduleIds
		String[] initProductCodeIds = null; // 产品原先的moduleCodeIds
		if(initProducts != null && initProducts.length > 0){
			int initProductsNum = initProducts.length;
			initProductModuleIds = new String[initProductsNum];
			initProductCodeIds  = new String[initProductsNum];
			for (int i = 0 ;i < initProductsNum; i++) {
				String[] intProStr=	 initProducts[i].split("\\|");
				initProductCodeIds[i] = intProStr[0];
				initProductModuleIds[i] = intProStr[1];	
			}
		}
		String[] productCodeIds = productCodeModuleVO.getProductCodeIds(); // 当前产品moduleCodeId
		Long proId = productCodeModuleVO.getProductId();
		List<BMSProductCodeModule> addProCodeModules;
		if(productCodeIds != null && productCodeIds.length > 0){
			addProCodeModules = new ArrayList<BMSProductCodeModule>();
			Set<String> addmoduleCodeIds = compare(initProductCodeIds, productCodeIds,"add");
			if(addmoduleCodeIds!=null&&addmoduleCodeIds.size()>0){
			for (String codeId : addmoduleCodeIds) {
				BMSProductCodeModule bmsProductCodeModule = new BMSProductCodeModule();
				bmsProductCodeModule.setCodeId(Long.parseLong(codeId));
				bmsProductCodeModule.setProductId(proId);
				bmsProductCodeModule.setIsDeleted((long) 0);
				addProCodeModules.add(bmsProductCodeModule);
			}
			 insertFlag = this.productCodeModuleService.addProductCodeModules(addProCodeModules);
			}
		}
		boolean updateFlag = true;
		String[] productModuleIds = productCodeModuleVO.getProductModuleIds(); // 当前产品moduleCodeId
		List<BMSProductCodeModule> delPromoduleCodes ;		
		if(initProducts!= null && initProducts.length>0){
			delPromoduleCodes = new ArrayList<BMSProductCodeModule>();
			Set<String> delmoduleIds = compare( productModuleIds,initProductModuleIds,"del");
			if(delmoduleIds!=null&&delmoduleIds.size()>0){
				for (String moduleIdAndV : delmoduleIds) {
					BMSProductCodeModule bmsProductCodeModule = new BMSProductCodeModule();
					String moduleId =moduleIdAndV.split("_")[0];
					String moduleV =moduleIdAndV.split("_")[1];
					bmsProductCodeModule.setId(Long.parseLong(moduleId));
					bmsProductCodeModule.setIsDeleted((long)1);
					bmsProductCodeModule.setVersion(Long.parseLong(moduleV));
					delPromoduleCodes.add(bmsProductCodeModule);
				}
				Map<String, Object> paramMap =new HashMap<String, Object>();
				paramMap.put("delPromoduleCodes", delPromoduleCodes);
				updateFlag = this.productCodeModuleService.updateProductCodeModules(paramMap);
			}
		}		
		if (insertFlag && updateFlag) {
			response.setRepMsg("true");
		} else {
			response.setRepMsg("false");
		}
		return response;
	}


	public <T> Set<T> compare(T[] t1, T[] t2,String opFlag) {   
		Set<T> set = new HashSet<T>();      
		if(t1 !=null){
			List<T> list1 = Arrays.asList(t1); 
			for (T t : t2) { 
					if("del".equals(opFlag)){
						if (!list1.contains((t.toString().split("_"))[0])) {    
				        	  set.add(t);    
				          }    	
					}else{
						if (!list1.contains(t)) {    
				        	  set.add(t);    
				          }    	
					}
				}  
		}else{
			for (T t : t2) {    
		        set.add(t);    
			}
		}
	      return set;    
	  }


	@Override
	public Response<ResProductBaseListVO> findProCodeModulesByProId(ReqBMSProductCodeModuleVO reqProductCodeModuleVO) {
		// 参数校验  
		if(reqProductCodeModuleVO.getProductId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productId"});
		}
		Response<ResProductBaseListVO> response =new Response<ResProductBaseListVO>();
		ResProductBaseListVO listVO = new ResProductBaseListVO();
		Map<String,Long> paramMap = new HashMap<String,Long>();
		 paramMap.put("productId", reqProductCodeModuleVO.getProductId());
		 List<BMSProductCodeModule> listProductCodeModule=productCodeModuleService.findProCodeModulesByProId(paramMap);
		 if (listProductCodeModule != null && listProductCodeModule.size() > 0) {
				List<ResBMSProductCodeModuleVO> records = new ArrayList<ResBMSProductCodeModuleVO>();
				for (BMSProductCodeModule productCodeModule : listProductCodeModule) {
					ResBMSProductCodeModuleVO vo = new ResBMSProductCodeModuleVO();
					BeanUtils.copyProperties(productCodeModule, vo);
					records.add(vo);
				}
				listVO.setListCodeModule(records);
			}
			response.setData(listVO);
		return response;
	} 
	
	
}
