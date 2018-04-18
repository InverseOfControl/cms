package com.ymkj.cms.biz.api.service.master;



import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IBMSOrgLimitChannelExecuter {
	/**
	 * 查询出所有的门店期限渠道信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResProductBaseListVO> listOrgLimitChannelBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 获取分页数据
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResponse<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	
	/**
	 * 保存渠道产品期限信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> saveProductLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 优惠汇率保存渠道产品期限信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 更新渠道产品期限信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> updateProductLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 逻辑删除该条数据
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> logicalDelete(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 判断门店产品是否被禁
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	Response<ResBMSOrgLimitChannelVO> isDisabled(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 依据产品，查询配置的对应可用签约网点  
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日上午11:47:28
	 */
	public ResListVO<ResBMSOrgLimitChannelVO> findByProductCodeList(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 依据产品，查询配置的对应可用签约网点id  ,多产品取交集
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日上午11:47:28
	 */
	public ResListVO<ResBMSOrgLimitChannelVO> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 校验产品-网点是否失效
	 * @param reqBMSOrgLimitChannelVOList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:25:03
	 */
	public Response<ResBMSOrgLimitChannelVO> branchProductRelevanceCheck(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	/**
	 * 查询出可用的网点配置的产品期限(根据网点id，产品id，申请额度)，期限去重
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public ResListVO<ResBMSOrgLimitChannelVO> listOrgProductLimitByOrgProApp(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	public Response<ResBMSOrgLimitChannelVO> updateByOrgLimitId(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 查询出可用的网点配置的产品期限对应的上下限（多个取并集），(根据网点id，产品id，申请期限)
	 * @param reqBMSOrgLimitChannelVOList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:25:03
	 */
	public Response<ResBMSOrgLimitChannelVO> findOrgLimitChannelLimitUnion(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 查询出所有的优惠门店期限渠道信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResProductBaseListVO> listOrgLimitChannelRateBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
}
