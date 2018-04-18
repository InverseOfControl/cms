package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;

/**
 * 枚举 服务层
 * 
 * @author cj
 *
 */
public interface IBMSEnumCodeService extends BaseService<BMSEnumCode> {
	/**
	 * 通过产品ID获取枚举对象集合
	 * 
	 * @param paramMap
	 * @return
	 */
	List<BMSEnumCode> findEnumCodeByCondition(Map<String, Object> paramMap);

	public long insert(BMSEnumCode enumCode);

	public void update(BMSEnumCode enumCode);
	
	
	/**
	 * 根据vo的讯息查询实体信息
	 * @param paramMap
	 * @return
	 */
	BMSEnumCode getByVo(Map<String, Object> paramMap);
	
	/**
	 * 获取产品对应的模块
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
	
	/**
	 * 加载缓存数据
	 * @param key
	 * @param value
	 * @return
	 */
	public String getVaule(String key,String value);
	
	public List<BMSEnumCode> findCodeByUnit(Map<String,String> map);
	public List<BMSEnumCode> findCodeByProfession(Map<String,Object> map);
	
	public List<BMSEnumCode> findByservenProfession();
}
