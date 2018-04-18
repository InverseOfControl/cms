package com.ymkj.cms.biz.test.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.cms.biz.common.util.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-redis.xml")
public class RedisTest {

	@Autowired
	RedisUtil redisUtil;

	@Test
	public void get() {
		Object value = redisUtil.get("BMS_BASE_AREA_0_1");
		System.out.println(value);
	}

	//@Test
	public void set() {
		boolean string = redisUtil.set("sss", "测试");
		System.out.println(string);
	}
	
	

	
}
