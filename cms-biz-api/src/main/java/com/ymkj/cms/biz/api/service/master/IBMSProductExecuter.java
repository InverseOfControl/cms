package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

/**
 * 产品 接口定义
 * @author cj
 *
 */
public interface IBMSProductExecuter {
	/**
	 * 查询出产品根据门店（营业部Id）
	 * @param paramMap
	 * @return
	 */
	public ResListVO<ResBMSProductVO> listProByCondition(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 根据 id 查询 产品信息
	 * @param reqBMSProductVO
	 * @return
	 */
	public Response<ResBMSProductVO> findById(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 根据名字查询 demoVO
	 * @param ReqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> findByName(ReqBMSProductVO reqBMSProductVO);
	
	/**
	 * 查询出所有的产品
	 * 
	 * @param reqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> listBy(ReqBMSProductVO reqBMSProductVO);
	
	/**
	 * 根据客服OrgId查询出所有产品
	 * @param reqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> findByOrgId(ReqBMSProductVO reqBMSProductVO);
	
	/**
	 * 分页查询产品信息
	 * @param reqProduct
	 * @return
	 */
	public PageResponse<ResBMSProductVO> listPage(ReqBMSProductVO reqProduct) ;
	
	/**
	 * 添加产品信息
	 * @param reqProductVO
	 * @return
	 */
	public Response<ResBMSProductVO> addProduct(ReqBMSProductVO reqProductVO);
	
	/**
	 * 删除产品信息
	 * @param reqProductVO
	 * @return
	 */
	public Response<ResBMSProductVO> deleteProduct(ReqBMSProductVO reqProductVO);
	
	/**
	 * 更新修改产品信息
	 * @param reqProductVO
	 * @return
	 */
	public Response<ResBMSProductVO> updateProduct(ReqBMSProductVO reqProductVO);
	
	/**
	 * 根据渠道ID获取产品集合 有渠道ID
	 * @param reqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> findProByChannelId(ReqBMSProductVO reqBMSProductVO);
	
	/**
	 * 根据渠道ID获取产品集合 无渠道id
	 * @param reqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> findProByChannelIdNotChannel(ReqBMSProductVO reqBMSProductVO);
	
	
	/**
	 * 根据vo类中的信息查询实体
	 * @param reqBMSProductVO
	 * @return
	 */
	public Response<ResBMSProductVO> findByVo(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 根据产品Code查询信息
	 */
	public Response<ResBMSProductVO> findByCode(ReqBMSProductVO reqBMSProductVO);
	/**
	 * 根据产品ID查询
	 * @param productId
	 * @return
	 */
	public Response<Integer>findProductById(Long productId);
	
	/**
	 * 根据渠道ID获取优惠产品集合 有渠道ID
	 * @param reqBMSProductVO
	 * @return
	 */
	public ResListVO<ResBMSProductVO> findProRateByChannelId(ReqBMSProductVO reqBMSProductVO);
	
	
	
	
}
