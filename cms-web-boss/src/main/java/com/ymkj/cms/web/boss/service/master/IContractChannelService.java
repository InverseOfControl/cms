package com.ymkj.cms.web.boss.service.master;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChannelVO;

/**
 * 渠道合同模板表service接口
 * @author YM10115
 *
 */
public interface IContractChannelService {
	
	/**
	 * 读取excel中的数据存入map集合中
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSContractChannelVO> listPage(ReqBMSContractChannelVO reqDemoVO);

}
