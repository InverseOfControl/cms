package com.ymkj.cms.web.boss.service.master;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IProductCodeModuleService {
	   /**
	    * 添加
	    * @param reqDemoVO
	    * @return
	    */
		public boolean saveProductCodeModules(ReqBMSProductCodeModuleVO reqDemoVO);
		
		
		/**
		    * 添加
		    * @param reqDemoVO
		    * @return
		    */
		public ResProductBaseListVO findProCodeModulesByProId(ReqBMSProductCodeModuleVO reqDemoVO);
		
	
		/**
		 * 用于导入excel导出sql
		 * @param fileName
		 * @param Mfile
		 * @return
		 */
		public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
		
		
}
