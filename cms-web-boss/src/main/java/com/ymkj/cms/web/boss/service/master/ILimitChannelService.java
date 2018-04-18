package com.ymkj.cms.web.boss.service.master;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;


public interface ILimitChannelService {
	
	/**
	 * 逻辑删除数据
	 * @param reqDemoVO
	 * @return
	 */
	public boolean logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO);
	
	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
/**
	 * 查询借款审批表
	 * @param reqLoanBaseVO
	 * @return
	 */
	public PageResult<ResBMSLimitChannelVO> listPage(ReqBMSLimitChannelVO reqLimitChannelVO);
	/**
	 * 逻辑删除数据
	 * @param reqDemoVO
	 * @return
	 */
	public boolean updateByAuLimitId(ReqBMSLimitChannelVO reqLimitChannelVO);
	
	

}
