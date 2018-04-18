package com.ymkj.cms.biz.service.app;

import java.util.Map;

public interface IInitProductListService{
	
	/**
	 * <p>Description:获取产品列表信息</p>
	 * @uthor YM10159
	 * @date 2017年4月24日 上午11:23:08
	 * @param @param userCode
	 */
	public Map<String,Object> getProductListData(String userCode);
}
