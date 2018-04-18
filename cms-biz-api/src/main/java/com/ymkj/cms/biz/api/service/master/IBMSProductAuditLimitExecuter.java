package com.ymkj.cms.biz.api.service.master;



import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

/**
 * 产品 接口定义
 * @author cj
 *
 */
public interface IBMSProductAuditLimitExecuter {
	/**
	 * 根据 id 查询 产品期限
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> findById(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO);
	
	/**
	 * 查询出所有的产品期限根据产品id
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public ResListVO<ResBMSProductAuditLimitVO> listProductLimitBy(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO);
	/**
	 * 更新产品期限信息
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> updateProductLimit(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO);
	
	/**
	 * 添加产品信息
	 * @param reqProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> addProductLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	
	/**
	 * 列表显示,分页
	 * @param reqProductAuditLimitVO
	 * @return
	 */
	public PageResponse<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	
	/**
	 * 根据 auditLimitId 查询 产品期限(忽略isDisabled)
	 * @param reqProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> findByAuditLimitId(ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	
	
	/**
	 * 按传入的实体类中的信息查询实体信息
	 * @param reqProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> findByVO(ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	
	/**
	 * 根据条件查询审核期限数据
	 * @param paramMap
	 * @return
	 */
	public ResListVO<ResBMSProductAuditLimitVO>  findLimitByChaIdUserCode(
			ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	/**
	 * 根据条件查询审核期限数据
	 * @param paramMap
	 * @return
	 */
	public ResListVO<ResBMSProductAuditLimitVO> findLimitByChaIdOrgId(
			ReqBMSProductAuditLimitVO reqProductAuditLimitVO);
	/**
	 * 根据产品ID更新产品期限信息
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public Response<ResBMSProductAuditLimitVO> updateByProductId(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO);
	
	/**
	 * 根据产品期限ID，修改期限状态
	 */
	public Response<ResBMSProductAuditLimitVO> updateProductTerm(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO);
	
	/**
	 * 根据产品期限ID查询对应的网店产品对应的该期限信息
	 */
	public Response<List<ResBMSOrgLimitChannelVO>> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 更新网店产品对应的产品期限ID对应的信息
	 */
	public Response<Boolean>  updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 根据ID修改网店产品信息
	 */
	public Response<Boolean> updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo);
	
	/**
	 * 根据产品ids查询期限列表
	 * @param reqDemoVO
	 * @return
	 */
	public Response<List<ResBMSProductAuditLimitVO>> listAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO);
	
}
