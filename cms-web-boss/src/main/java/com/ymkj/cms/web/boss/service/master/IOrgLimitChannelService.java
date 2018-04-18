package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

public interface IOrgLimitChannelService {
	
	/**
	 * 解析excel文件并存入map集合中
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	
	/**
	 * 依据产品，查询配置的对应可用签约网点  ,多产品取交集
	 * @param productCodeList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月30日下午4:17:14
	 */
	public List<ResOrganizationVO> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO productCodeList);
	
	/**
	 * 校验产品-网点是否失效
	 * @param reqBMSOrgLimitChannelVOList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:19:19
	 */
	public Map<String, Object> branchProductRelevanceCheck(List<ReqBMSOrgLimitChannelVO> reqBMSOrgLimitChannelVOList);
		/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	/**
	 * 逻辑删除数据
	 * @param reqDemoVO
	 * @return
	 */
	public boolean updateByOrgLimitId(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO);
	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqDemoVO);

	public boolean deleteProductTerm(ReqBMSOrgLimitChannelVO reqDemoVO);
}
