package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSIntegraedSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IBMSIntegratedSearchDao</p>
 * <p>Description:综合查询数据访问层</p>
 * @uthor YM10159
 * @date 2017年3月9日 下午1:43:14
 */
public interface IBMSIntegratedSearchDao extends BaseDao<BMSIntegraedSearchEntity>{
	
	public List<BMSSysLoanLog> queryLoanLog(Map<String,Object> map);
	
	public Map<String,Object> queryLoanDetail(String loanNo);
	
	public PageBean<BMSIntegraedSearchEntity> mainSelet(Map<String,Object> map);
}
