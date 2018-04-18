package com.ymkj.cms.web.boss.service.apply;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;

public interface IChannelBankService {
	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO);

	public boolean addChannelBank(ReqBMSChannelBankVO reqDemoVO);

	public boolean updateChannelBank(ReqBMSChannelBankVO reqDemoVO);

	/**
	 * 根据ID 查询 vo
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSChannelBankVO findById(Long id);

	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO);

	public List<ResBMSBankVO> getBank(ReqBMSBankVO reqDemoVO);
	
	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	
	public Response<ResBMSCheckIsExitsVO> checkIsExits(ReqBMSChannelBankVO reqBMSChannelBankVO);
	/**
	 * 判断新增时，对应的银行是否禁用
	 */
	public Boolean checkParentIsStart(ReqBMSChannelBankVO reqDemoVO);
	
	/**
	* 判断新增时，对应的其道是否禁用
	*/
	public Boolean checkParentIsChanel(ReqBMSChannelBankVO reqDemoVO);
}
