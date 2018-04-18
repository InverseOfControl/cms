package com.ymkj.cms.biz.test;

import com.alibaba.dubbo.container.Main;

public class DubboProvider {
	public static void main(String[] args) {
		System.setProperty("dubbo.spring.config", "classpath*:conf_develop/spring/spring-context.xml");
		Main.main(null);
	}
}