package com.ymkj.cms.biz.test.dao.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.cms.biz.dao.master.IBMSOrgLimitChannelDao;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSOrgLimitChannelTest {
	
	@Autowired
	private IBMSOrgLimitChannelDao demoDao;
	//@Test
		public void insert(){
			
			BMSOrgLimitChannel demo = new BMSOrgLimitChannel();
			demo.setAuditLimitId(1L);
			demo.setOrgId(1L);
			demo.setChannelId(1L);
			demo.setProductDeployCode("productDeployCode");
			demo.setIsDeleted(0L);
			demoDao.insert(demo);
		}
		
		//@Test
		public void update(){
			BMSOrgLimitChannel demo = new BMSOrgLimitChannel();
			demo.setId(1L);
			demo.setAuditLimitId(11L);
			demo.setOrgId(11L);
			demo.setChannelId(11L);
			demo.setProductDeployCode("productDeployCode2");
			demo.setIsDeleted(10L);
			demoDao.update(demo);
		
			
		}
		//@Test
		public void getById(){
			BMSOrgLimitChannel demo = demoDao.getById(1L);
			
			System.out.println(demo);
		}
		
		//@Test
		public void deleteById(){
			
			demoDao.deleteById(1L);
		}
		@Test
		public void listPage(){
			
			PageParam pageParam = new PageParam(1, 10);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("channel", 1L);
			
			PageBean<BMSOrgLimitChannel> page = demoDao.listPage(pageParam, paramMap);
			System.out.println(page);
			
		}
		
		//@Test
		public void listBy(){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("channel", 11L);
			
			List<BMSOrgLimitChannel> list =  demoDao.listBy(param);
			
			System.out.println(list.size());
		}
}
