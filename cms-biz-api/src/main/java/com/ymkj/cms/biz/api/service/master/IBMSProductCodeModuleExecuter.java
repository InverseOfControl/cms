package com.ymkj.cms.biz.api.service.master;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IBMSProductCodeModuleExecuter {

	/**
	 * 保存产品模块信息
	 * @param reqBMSProductCodeModuleVO
	 * @return
	 */
	public Response<ResBMSProductCodeModuleVO> saveProductCodeModules(ReqBMSProductCodeModuleVO reqBMSProductCodeModuleVO);
	
	
	/**
	 * 得到产品的所属模块
	 * @param reqBMSProductCodeModuleVO
	 * @return
	 */
	public Response<ResProductBaseListVO> findProCodeModulesByProId(ReqBMSProductCodeModuleVO reqProductCodeModuleVO);
	
	
}
