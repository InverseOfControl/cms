package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;


public interface IBankService {

	/**
	 * 根据ID 查询 vo
	 * @param id
	 * @return
	 */
	public ResBMSBankVO findById(Long id);
	
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSBankVO> listPage(ReqBMSBankVO reqDemoVO);
	
	public boolean addBank(ReqBMSBankVO reqDemoVO);
	
	public boolean deleteBank(String id);
	
	public boolean updateBank(ReqBMSBankVO reqDemoVO);
	
	/**
	 * 获取所有的渠道信息
	 * @param reqDemoVO
	 * @return
	 */
	public List<ResBMSBankVO> getAllBank(ReqBMSBankVO reqDemoVO);

	public ResBMSBankVO findOne(ReqBMSBankVO reqDemoVO);
	
	
	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile);
	
	public Response<ResBMSCheckIsExitsVO> checkBankIsExits(ReqBMSBankVO reqBankVO);
	/**
	 * 根据银行ID查询银行渠道表有没有对应的信息
	 * @return
	 */
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO);
}
