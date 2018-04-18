package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSLimitChannelDao extends BaseDao<BMSLimitChannel>{
	/**
	 * 根据条件删除数据
	 * @param limitChannel
	 */
	public void deleteByCondition(BMSLimitChannel limitChannel);
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public void updateByCondition(BMSLimitChannel limitChannel);
	
	/**
	 * 逻辑删除数据(更新is_delete=1)
	 * @param limitChannel
	 * @return
	 */
	public long logicalDelete(BMSLimitChannel limitChannel);
	
	
	/**
	 * 得到额度上下限
	 * @param limitChannel
	 * @return
	 */
	public BMSLimitChannel  getFULimit(BMSLimitChannel limitChannel);
	
	/**
	 * 得到额度上下限根据组织id
	 * @param limitChannel
	 * @return
	 */
	public BMSLimitChannel  getFULimitByOrgId(BMSLimitChannel limitChannel);
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public long updateByAuLimitId(BMSLimitChannel limitChannel);
	/**
	 * 查询配置历史数据，包含已删除数据
	 * 默认对应的，渠道id，产品期限，只能限制到一条数据
	 * @param historyParamMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月25日上午10:37:17
	 */
	public List<BMSLimitChannel> findHistory(Map<String, Object> historyParamMap);
	
	/**
	 * 查询网店产品期限
	 * @param paramMap
	 * @return
	 */
	public List<BMSLimitChannel> listByRate(Map<String, Object> paramMap);
	
}
