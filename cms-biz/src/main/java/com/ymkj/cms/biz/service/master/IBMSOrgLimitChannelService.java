package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
/**
 * 门店期限渠道 服务层
 * @author cj
 *
 */
public interface IBMSOrgLimitChannelService extends BaseService<BMSOrgLimitChannel>{
	/**
	 * 保存门店渠道产品信息
	 * 对已存在配置，delete由1变0
	 * @param product
	 * @return
	 */
	public Boolean saveOrgLimitChannel(ReqBMSOrgLimitChannelVO reqlimitChannel);
	/**
	 * 保存优惠汇率门店渠道产品信息
	 * 对已存在配置，delete由1变0
	 * @param product
	 * @return
	 */
	public Boolean saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqlimitChannel);
	
	/**
	 * 更新门店渠道产品信息
	 * @param product
	 * @return
	 */
	public Boolean updateOrgLimitChannel(ReqBMSOrgLimitChannelVO reqlimitChannel);
	
	
	/**
	 * 门店渠道逻辑删除
	 * @param reqlimitChannel
	 * @return
	 */
	public long logicalDelete(BMSOrgLimitChannel orgLimitChannel);
	

	/**
	 * 根据产品CODE
	 * @param limitChannel
	 */
	public boolean isDisabledInSign(Map<String, Object> params);
	
	
	
	/**
	 * 依据产品，查询配置的对应可用签约网点  
	 * @param productCodeList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日下午1:37:41
	 */
	public List<BMSOrgLimitChannel> findByProductCodeList(List<String> productCodeList);
	/**
	 * 依据产品，查询配置的对应可用签约网点  ,多产品取交集
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日下午1:37:41
	 */
	public List<BMSOrgLimitChannel> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 校验产品-网点是否失效
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:39:55
	 */
	public boolean branchProductRelevanceCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 根据产品CODE
	 * @param limitChannel
	 */
	public boolean isDisabled(BMSOrgLimitChannel orgLimitChannel);
	/**
	 * 查询出所有的网点配置的产品期限(根据网点id，产品id，申请额度)
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日上午11:57:49
	 */
	public List<BMSOrgLimitChannel> listOrgProductLimitByOrgProApp(Map<String, Object> paramMap);
	
	public long updateByOrgLimitId(BMSOrgLimitChannel orgLimitChannel);
	
	/**
	 * 查询出可用的网点配置的产品期限对应的上下限（多个取并集），(根据网点id，产品id，申请期限)
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月27日上午11:04:51
	 */
	public BMSOrgLimitChannel findOrgLimitChannelLimitUnion(Map<String, Object> paramMap);
	/**
	 * 查询出所有的优惠门店期限渠道信息
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月1日上午11:03:58
	 */
	public List<BMSOrgLimitChannel> listOrgLimitChannelRateBy(Map<String, Object> paramMap);
	
	
}
