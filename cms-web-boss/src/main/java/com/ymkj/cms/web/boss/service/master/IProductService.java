package com.ymkj.cms.web.boss.service.master;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;

public interface IProductService {

	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSProductVO> listPage(ReqBMSProductVO reqDemoVO);

	public boolean addProduct(ReqBMSProductVO reqDemoVO);

	public boolean deleteProduct(String productId);
	
	/**
	 * 根据ID 查询 vo
	 * @param id
	 * @return
	 */
	public ResBMSProductVO findById(Long productId);

	public boolean updateProduct(ReqBMSProductVO reqDemoVO);
	
	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	/**
	 * 根据产品Code查询
	 */
	public ResBMSProductVO findByVo(ReqBMSProductVO VO);
	
	public Integer findProductById(Long productId);

}
