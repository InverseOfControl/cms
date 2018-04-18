package com.ymkj.cms.biz.common.util;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class GMPEnvironmentFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Properties getObject() throws Exception {
		Properties props = new Properties();
		Map env = System.getenv();
		if (StringUtils.isNotEmpty((String) env.get("instanceName"))) {
			this.logger.info("发现 instanceName环境变量，开始从环境变量取实例配置。");
			props.putAll(System.getenv());
		} else {
			this.loadProperties(props);
		}

		props.putAll(System.getProperties());
		return props;
	}

	public Class<?> getObjectType() {
		return Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}
}