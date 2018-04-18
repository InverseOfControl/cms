package com.ymkj.cms.web.boss.service.master;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IReasonService {

	/**
	 * 用于导入excel导出sql
	 * @param fileName
	 * @param Mfile
	 * @return
	 */
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile,String ss);
}
