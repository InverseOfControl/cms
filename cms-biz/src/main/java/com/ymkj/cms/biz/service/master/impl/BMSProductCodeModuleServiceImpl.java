package com.ymkj.cms.biz.service.master.impl;



import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.dao.master.IBMSProductCodeModuleDao;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;
import com.ymkj.cms.biz.service.master.IBMSProductCodeModuleService;

@Service
public class BMSProductCodeModuleServiceImpl extends BaseServiceImpl<BMSProductCodeModule> implements IBMSProductCodeModuleService{
	
	@Autowired
	private IBMSProductCodeModuleDao productCodeModuleDao;

	@Override
	public boolean addProductCodeModules(List<BMSProductCodeModule> bmsProductCodeModules) {
		long i = productCodeModuleDao.batchInsert(bmsProductCodeModules);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public List<BMSProductCodeModule> findProCodeModulesByProId(Map paramMap) {
		
		return (List<BMSProductCodeModule>)productCodeModuleDao.findModuleCodeByProId(paramMap);
	}

	@Override
	public boolean updateProductCodeModules(Map paramMap) {
		//long i=	productCodeModuleDao.update(bmsProductCodeModules);
				long i=	productCodeModuleDao.batchUpdate(paramMap);
				System.err.println("oprateCount:"+i);
					if(i>0){
						return true;
					}
					return false;
	}
	
/*	@Override
	public boolean updateProductCodeModules(List<BMSProductCodeModule> bmsProductCodeModules) {
		long i=	productCodeModuleDao.update(bmsProductCodeModules);
		//long i=	productCodeModuleDao.batchUpdate(bmsProductCodeModules);
		System.err.println("oprateCount:"+i);
			if(i>0){
				return true;
			}
			return false;
	}*/

	@Override
	protected BaseDao<BMSProductCodeModule> getDao() {
		return productCodeModuleDao;
	}





	

	
	

}
