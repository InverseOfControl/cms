package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
/**
 * 地区 服务层
 * @author haowp
 *
 */
public interface IBMSBaseAreaService extends BaseService<BMSBaseArea>{

	/**
	 * 删除表中所有的数据
	 * @return
	 */
	public void deletelAll(BMSBaseArea bmsBaseArea);
	
	/**
	 * 插入数据
	 * @param bmsBaseArea
	 * @return
	 */
	public long insert(BMSBaseArea bmsBaseArea);
	
	
	/**
	 * 按id删除数据
	 * @param bmsBaseArea
	 * @return
	 */
	public long deleteById(long id);
}
