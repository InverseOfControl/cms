package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSOrgLimitChannelDao extends BaseDao<BMSOrgLimitChannel>{
	/**
	 * 根据条件删除数据
	 * @param limitChannel
	 */
	public void deleteByCondition(BMSOrgLimitChannel limitChannel);
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public void updateByCondition(BMSOrgLimitChannel limitChannel);
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public void updateByParams(BMSOrgLimitChannel limitChannel);
	
	/**
	 * 逻辑删除
	 * @param orgLimitChannel
	 * @return
	 */
	long logicalDelete(BMSOrgLimitChannel orgLimitChannel);
	
	
	/**
	 * 判断产品是否可用
	 * @param limitChannel
	 */
	public boolean isDisabled(BMSOrgLimitChannel orgLimitChannel);
	
	/**
	 * 判断产品是否可用
	 * @param limitChannel
	 */
	public boolean isDisabledInSign(Map<String, Object> params);
	
	/**
	 * 依据参数查找
	 * @param productCodeList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日下午1:40:29
	 */
	public List<BMSOrgLimitChannel> findByParams(Map<String, Object> params);
	/**
	 * 依据参数，查询配置的对应可用签约网点id  ,多产品取交集
	 * productCodeList必须传入
	 * @param params
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日下午4:05:30
	 */
	public List<BMSOrgLimitChannel> findOrgIntersectByParams(Map<String, Object> params);
	/**
	 * 校验产品-网点是否失效
	 * @param params
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:54:09
	 */
	public boolean branchProductRelevanceCheck(Map<String, Object> params);
	/**
	 * 渠道产品禁用，启用，其下网点产品配置全部级联更新
	 * @param params
	 * @author lix YM10160
	 * @return 
	 * @date 2017年3月31日下午3:12:22
	 */
	public Boolean updateOrgLimitChannelAllByCondition(Map<String, Object> params);
	/**
	 * 查询出所有的网点配置的产品期限(根据网点id，产品id，申请额度)
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日下午1:49:37
	 */
	public List<BMSOrgLimitChannel> listOrgProductLimitByOrgProApp(Map<String, Object> paramMap);
	
	
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public long updateByOrgLimitId(BMSOrgLimitChannel orgLimitChannel);
	/**
	 * 查询配置历史数据，包含已删除数据
	 * 默认对应的，网点id，渠道id，产品期限，只能限制到一条数据
	 * @param historyParamMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午1:46:30
	 */
	public List<BMSOrgLimitChannel> findHistory(Map<String, Object> historyParamMap);
	/**
	 * 查询出可用的网点配置的产品期限对应的上下限（多个取并集），(根据网点id，产品id，申请期限)
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月27日上午11:16:00
	 */
	public BMSOrgLimitChannel findOrgLimitChannelLimitUnion(Map<String, Object> paramMap);
	/**
	 * 查询出所有的优惠门店期限渠道信息
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月1日上午11:05:12
	 */
	public List<BMSOrgLimitChannel> listOrgLimitChannelRateBy(Map<String, Object> paramMap);
	
	
}
