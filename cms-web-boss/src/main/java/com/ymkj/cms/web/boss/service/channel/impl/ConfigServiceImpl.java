package com.ymkj.cms.web.boss.service.channel.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author YM10189
 * @date 2017年6月2日
 * @Description:配置信息
 */
@Service
public class ConfigServiceImpl {

	@Value("${apply.template.pdf}")
	private String appTmpPdfPath;

	@Value("${apply.template.excel}")
	private String appTmpExcelPath;
	
	@Value("${apply.file}")
	private String filePath;

	public String getAppTmpPdfPath() {
		return appTmpPdfPath;
	}

	public void setAppTmpPdfPath(String appTmpPdfPath) {
		this.appTmpPdfPath = appTmpPdfPath;
	}

	public String getAppTmpExcelPath() {
		return appTmpExcelPath;
	}

	public void setAppTmpExcelPath(String appTmpExcelPath) {
		this.appTmpExcelPath = appTmpExcelPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
