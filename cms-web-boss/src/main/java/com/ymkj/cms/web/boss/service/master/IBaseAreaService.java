package com.ymkj.cms.web.boss.service.master;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IBaseAreaService {
	/**
	 * 删除表中的所有数据
	 */
	public boolean deletelAllBaseArea(ReqBMSBaseAreaVO reqDemoVO);
	
	
	/**
	 * 向表中插入数据
	 * @param reqDemoVO
	 */
	public boolean addBaseArea(ReqBMSBaseAreaVO reqDemoVO,MultipartFile multipartFile) throws IOException;
	
	
	/**
	 * 按ID删除地区数据
	 * @param reqDemoVO
	 * @return
	 */
	public boolean deleteById(ReqBMSBaseAreaVO reqDemoVO);
	
	
	/**
	 * 查询全部地区信息
	 * @param reqDemoVO
	 * @return
	 */
	public List<ResBMSBaseAreaVO> listBy(ReqBMSBaseAreaVO reqDemoVO);
	
	
	public List<ResBMSBaseAreaVO> findByName(ReqBMSBaseAreaVO reqDemoVO);
	
	
	/**
	 * 按code获取信息
	 * @param reqDemoVO
	 * @return
	 */
	public List<ResBMSBaseAreaVO> getAllBaseAreaByCode(ReqBMSBaseAreaVO reqDemoVO);
	

}
