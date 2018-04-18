package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSEnumCodeDao extends BaseDao<BMSEnumCode>{
	
	
	/**
	 * 通过产品ID获取枚举信息
	 * @param paramMap
	 * @return
	 */
	List<BMSEnumCode> findEnumCodeByCondition(Map<String, Object> paramMap);
	
	/**
	 * 按vo传入的nameCN查询信息
	 * @param paramMap
	 * @return
	 */
	BMSEnumCode getByVO(Map<String, Object> paramMap);
	
	/**
	 * 获取商品对应的列表信息
	 * @param paramMap
	 * @return
	 */
	public List<BMSEnumCode> getProducAssetsInfo(Map<String, Object> paramMap);
	
	/**
	 * 根据产品Code获取产品对应的模块
	 * @param paramMap
	 * @return
	 */
	public List<BMSEnumCode> getProducAssetsInfoByCode(Map<String, Object> paramMap);
	
	public List<BMSEnumCode> findCodeByUnit(Map<String,String> map);
	public List<BMSEnumCode> findCodeByProfession(Map<String,Object> map);
	public List<BMSEnumCode> findByservenProfession();
	
}
