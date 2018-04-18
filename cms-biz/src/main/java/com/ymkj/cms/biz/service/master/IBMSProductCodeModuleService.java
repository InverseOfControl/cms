package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;

/**
 * 产品模块 服务层
 * @author lfz
 *
 */
public interface IBMSProductCodeModuleService extends BaseService<BMSProductCodeModule>{
	
	/**
	 * 新增
	 * @param bmsProductCodeModule
	 * @return
	 */
	public boolean addProductCodeModules(List<BMSProductCodeModule> bmsProductCodeModules);
	

	
	/**
	 * 更新
	 * @param bmsProductCodeModule
	 * @return
	 */
/*	public boolean updateProductCodeModules(List<BMSProductCodeModule> bmsProductCodeModules);*/
	
	
	/**
	 * 更新
	 * @param bmsProductCodeModule
	 * @return
	 */
	public boolean updateProductCodeModules(Map paramMap);
	

	/**
	 * 得到产品所属模块
	 * @param reqProductCodeModuleVO
	 * @return
	 */
	public List<BMSProductCodeModule> findProCodeModulesByProId(Map paramMap);
}
