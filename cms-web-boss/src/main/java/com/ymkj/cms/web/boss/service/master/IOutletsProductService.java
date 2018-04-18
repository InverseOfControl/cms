package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.TreeJson;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationTreeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;


public interface IOutletsProductService {
	
	/**
	 *  查询 所有的list
	 * @param reqBMSProductVO
	 * @return
	 */
	public List<ResBMSProductVO> findProByChannelId(ReqBMSProductVO reqBMSProductVO);
	/**
	 *  查询 所有的list
	 * @param reqBMSProductVO
	 * @return
	 */
	public List<ResBMSProductVO> listProByCondition(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 查询门店渠道产品分页信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResult<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 更新数据
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean updateOrgProductLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 保存门店渠道产品信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public boolean saveOrgChannelProduct(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 保存门店渠道产品优惠汇率信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public boolean saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 获取营业网点树
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public List<TreeJson> findAllDeptsTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 获取营业网点树
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public List<TreeJson> findAllDeptsRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 获取渠道产品树
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public List<TreeJson> findChannelProTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 获取渠道产品树
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public List<TreeJson> findChannelProRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 逻辑删除数据
	 * @param reqDemoVO
	 * @return
	 */
	public boolean logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO);
	/**
	 * 
	 * @param reqOrganizationVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月30日下午8:10:01
	 */
	public List<ResOrganizationVO> findAllDepts(ReqOrganizationVO reqOrganizationVo);
	/**
	 * 网点产品启用检测
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午5:06:26
	 */
	public Map<String, Object> orgProductLimitDisableCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 查询 优惠汇率 所有的list
	 * @param reqBMSProductVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年8月31日上午10:50:43
	 */
	List<ResBMSProductVO> findProRateByChannelId(ReqBMSProductVO reqBMSProductVO);
	

	
	
}
