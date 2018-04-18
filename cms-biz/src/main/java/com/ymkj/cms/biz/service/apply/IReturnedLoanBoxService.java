package com.ymkj.cms.biz.service.apply;

import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.ReturnedLoanBoxSearchEntity;

public interface IReturnedLoanBoxService extends BaseService<ReturnedLoanBoxSearchEntity> {
	
	/**
	 * <p>Description:退件箱查询接口</p>
	 * @uthor YM10159
	 * @date 2017年3月7日 上午11:38:10
	 * @param @param loanBaseEntity
	 */
	public PageBean<ReturnedLoanBoxSearchEntity> search(PageParam pageParam, Map<String,Object> paramMap);
	
	/**
	 * <p>Description:获取推荐箱页签消息提示数</p>
	 * @uthor YM10159
	 * @date 2017年3月31日 上午9:20:38
	 * @param @return
	 */
	public int queryMessageCount(Map<String,Object> paramMap);
	
}
