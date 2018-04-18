package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSBaseAreaDao extends BaseDao<BMSBaseArea>{
	/**
	 * 删除表中所有的数据
	 * @return
	 */
	public void deletelAll(BMSBaseArea bmsBaseArea);
	
	/**
	 * 插入数据
	 * @param reqBMSBaseAreaVO
	 * @return
	 *//*
	public Integer insert(ReqBMSBaseAreaVO reqBMSBaseAreaVO);*/
	
}
