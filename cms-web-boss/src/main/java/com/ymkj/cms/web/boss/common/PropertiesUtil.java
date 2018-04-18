package com.ymkj.cms.web.boss.common;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String get(String propertiesPath, String attr) {
		Properties properties = new Properties();
		FileInputStream fis;
		try {
			//获取绝对路径
			String path = PropertiesUtil.class.getClassLoader().getResource(propertiesPath).getPath();
			fis = new FileInputStream(path);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties.get(attr).toString();
	}
}
