package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaTreeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

/**
 * 地区 接口定义
 * @author cj
 *
 */
public interface IBMSBaseAreaExecuter {
	/**
	 * 根据 id 查询 地区数据
	 * @param reqBMSBaseAreaVO
	 * @return
	 */
	public Response<ResBMSBaseAreaVO> findById(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	/**
	 * 根据名字查询  地区数据
	 * @param ReqBMSBaseAreaVO
	 * @return
	 */
	public ResListVO<ResBMSBaseAreaVO> findByName(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	
	/**
	 * 查询出所有的地区
	 * @param reqBMSBaseAreaVO
	 * @return
	 */
	public ResListVO<ResBMSBaseAreaVO> listBy(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	
	/**
	 * 查询出所有的地区(树形)
	 * @param reqBMSBaseAreaVO
	 * @return
	 */
	public ResListVO<ResBMSBaseAreaTreeVO> listByTree(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	
	
	/**
	 * 删除表中的所有数据
	 */
	public Response<ResBMSBaseAreaVO> deletelAllBaseArea(ReqBMSBaseAreaVO vo);
	
	/**
	 * 删除表的编号,按ID
	 * @return
	 */
	public Response<ResBMSBaseAreaVO> deleteById(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	/**
	 * 向表中插入数据
	 * @param reqBMSBaseAreaVO
	 */
	public Response<ResBMSBaseAreaVO> addBaseArea(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	
	
	
	/**
	 * 根据code查询信息
	 * @param reqBMSBaseAreaVO
	 * @return
	 */
	public Response<ResBMSBaseAreaVO> getAllBaseAreaByCode(ReqBMSBaseAreaVO reqBMSBaseAreaVO);
	
	
	
	
	
	
}
