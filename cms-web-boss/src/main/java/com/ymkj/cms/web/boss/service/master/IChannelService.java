package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;

public interface IChannelService {

	/**
	 * 根据ID 查询 vo
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSChannelVO findById(Long id);

	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSChannelVO> listPage(ReqBMSChannelVO reqDemoVO);

	public boolean addChannel(ReqBMSChannelVO reqDemoVO);

	public boolean deleteChannel(String id);

	public boolean updateChannel(ReqBMSChannelVO reqDemoVO);

	/**
	 * 获取所有的渠道信息
	 * 
	 * @param reqDemoVO
	 * @return
	 */
	public List<ResBMSChannelVO> getAllChannel(ReqBMSChannelVO reqDemoVO);

	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO);

	public ResBMSChannelVO findOne(ReqBMSChannelVO reqDemoVO);
	
	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	
	
	public Response<ResBMSCheckIsExitsVO> checkIsChennelExits(ReqBMSChannelVO reqDemoVO);
	
	/**
	 * 渠道删除检查
	 * @param reqBMSChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月19日下午9:42:37
	 */
	public boolean deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO);
	
	/**
	 * 获取渠道起止日期和结束日期在当前时间内的
	 */
	
	public List<ResBMSChannelVO> findChannelEqDate(ReqBMSChannelVO reqDemoVO);
	/**
	 * 查询可配置合同类型
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月10日下午1:40:14
	 */
	public List<ResBMSEnumCodeVO> findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo);
	
	public List<ResBMSChannelVO> findChannelByOrgIds(ReqBMSChannelVO reqBMSChannelVO);
}
