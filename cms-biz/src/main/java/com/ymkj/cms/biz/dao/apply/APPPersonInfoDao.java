package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPPersonInfoDao extends BaseDao<APPPersonInfoEntity>{
	APPPersonInfoEntity	findPersonByLoanNo(Map paramMap);
	
	ResBMSLoanImportVO	findImportLoanInfo(Map<String,Object> paramMap);
	
	long updateAll(APPPersonInfoEntity appPersonInfoEntity);
	
	public long updateUserPhone(APPPersonInfoEntity appPersonInfoEntity);
	
	public long updateById(APPPersonInfoEntity appPersonInfoEntity);
}
