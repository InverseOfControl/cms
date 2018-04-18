package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.TreeJson;


public interface IChannelProductService {
	
	/**
	 *  查询 所有的list
	 * @param reqBMSProductVO
	 * @return
	 */
	public List<ResBMSProductVO> findAllProduct(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 查询渠道产品分页信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResult<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 更新数据
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public Boolean updateChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 获取产品树
	 * @param reqBMSProductVO
	 * @return
	 */
	public List<TreeJson> findChannelProTree(ReqBMSProductVO reqBMSProductVO);
	
	/**
	 * 获取渠道树
	 * @param reqBMSChannelVO
	 * @return
	 */
	public List<TreeJson> findChannelTree(ReqBMSChannelVO reqBMSChannelVO);
	
	/**
	 * 保存门店渠道产品信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public boolean saveChannelProduct(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	

	/**
	 * 获取产品期限以及最终产品树
	 * @param treeDataList
	 * @param reqBMSProductVO
	 * @return
	 */
	public  List<TreeJson> findLimitChanel(ReqBMSProductVO reqBMSProductVO,List<TreeJson> treeDataList, List<ResBMSProductAuditLimitVO> listLimit);
	/**
	 * 渠道产品期限，启用判断，上层数据被禁用不可启用
	 * @param reqBMSLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日下午8:15:16
	 */
	public Map<String, Object> channelLimitDisableCheck(ReqBMSLimitChannelVO reqBMSLimitChannelVO);
	
	
}
