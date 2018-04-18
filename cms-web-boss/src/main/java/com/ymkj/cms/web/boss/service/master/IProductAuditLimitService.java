package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;

public interface IProductAuditLimitService {
	/**
	 * 根据auditLimitId 查询 vo
	 * @param id
	 * @return
	 */
	public ResBMSProductAuditLimitVO findByAuditLimitId(Long id);
	
   /**
    * 添加
    * @param reqDemoVO
    * @return
    */
	public boolean addProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO);
	
	/**
	 * 修改
	 * @param reqDemoVO
	 * @return
	 */
	public boolean updateProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO);
	
	/**
	 *  查询 所有的list
	 * @param reqDemoVO
	 * @return
	 */
	public List<ResBMSProductAuditLimitVO> findAllProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO);
	
	
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqDemoVO);
	
	/**
	 * 用于导入excel,导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	/**
	 * 修改
	 * @param reqDemoVO
	 * @return
	 */
	public boolean updateProductAuditLimitByPID(ReqBMSProductAuditLimitVO reqDemoVO);

	public boolean deleteProductTerm(ReqBMSProductAuditLimitVO reqDemoVO);
	
	/**
	 * 根据产品期限ID查询对应的网店产品有这个期限的所有数据
	 */
	public List<ResBMSOrgLimitChannelVO> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req);
	/**
	 * 更新网店产品对应的产品期限ID的信息
	 */
	public boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 根据ID更新对应的网店产品信息
	 */
	public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo);
	
	public List<ResBMSProductAuditLimitVO> listAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO);
}
