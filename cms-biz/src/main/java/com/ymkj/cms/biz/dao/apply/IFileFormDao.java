package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.FileFormEntity;

public interface IFileFormDao extends BaseDao<FileFormEntity> {
	
	public FileFormEntity getByLoanNo(Map<String, Object> paramMap);

}
