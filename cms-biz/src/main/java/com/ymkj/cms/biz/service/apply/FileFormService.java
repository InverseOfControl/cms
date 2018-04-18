package com.ymkj.cms.biz.service.apply;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.FileFormEntity;

public interface FileFormService extends BaseService<FileFormEntity> {
	
	/**
	 * 新增档案信息
	 * @param fileFormEntity
	 * @return
	 */
	public Long saveFileForm(FileFormEntity fileFormEntity);
	/**
	 * 更新档案信息
	 * @param fileFormEntity
	 * @return
	 */
	public Long updateArchives(FileFormEntity fileFormEntity);
	
	public FileFormEntity queryArchives(Map<String, Object> paramMap);

}
