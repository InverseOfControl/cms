package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
/**
 * 门店期限渠道 服务层
 * @author cj
 *
 */
public interface IBMSLimitChannelService extends BaseService<BMSLimitChannel>{
	/**
	 * 保存渠道产品信息
	 * @param product
	 * @return
	 */
	public Boolean saveLimitChannel(ReqBMSOrgLimitChannelVO reqlimitChannel);
	
	/**
	 * 更新渠道产品信息
	 * @param reqlimitChannel
	 * @return
	 */
	public Boolean updateByCondition(ReqBMSOrgLimitChannelVO reqlimitChannel);
	
	
	/**
	 * 渠道产品逻辑删除
	 * @param reqlimitChannel
	 * @return
	 */
	public long logicalDelete(BMSLimitChannel limitChannel);
	
	
	/**
	 * 得到上下限
	 * @param limitChannel
	 * @return
	 */
	public BMSLimitChannel  getFULimit(BMSLimitChannel limitChannel);
	
	
	/**
	 * 得到上下限根据组织id
	 * @param limitChannel
	 * @return
	 */
	public BMSLimitChannel  getFULimitByOrgId(BMSLimitChannel limitChannel);
	
	/**
	 * 渠道产品禁用，启用，其下网点产品配置全部级联更新
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月31日下午3:06:06
	 */
	public Boolean updateOrgLimitChannelAllByCondition(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 根据条件更新数据
	 * @param limitChannel
	 */
	public long updateByAuLimitId(BMSLimitChannel limitChannel);
	
	/**
	 * 查询网店下面的渠道产品期限
	 * @param paramMap
	 * @return
	 */
	public List<BMSLimitChannel> listByRate(Map<String, Object> paramMap);
	
	
}
