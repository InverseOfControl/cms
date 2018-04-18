package com.ymkj.cms.biz.test.dao;

import java.util.ArrayList;
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
import com.ymkj.cms.biz.dao.apply.IDemoDao;
import com.ymkj.cms.biz.entity.apply.DemoEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class DemoTest {
	
	@Autowired
	private IDemoDao demoDao;
	
	@Test
	public void insert(){
		
		DemoEntity demo = new DemoEntity();
		demo.setName("test1");
		demo.setAddress("address1");
		demoDao.insert(demo);
	}
	@Test
	public void batchInsert(){
		
		List<DemoEntity> list = new ArrayList<DemoEntity>();
		DemoEntity demo3 = new DemoEntity();
		demo3.setName("test3");
		demo3.setAddress("address3");
		list.add(demo3);
		
		DemoEntity demo4 = new DemoEntity();
		demo4.setName("test4");
		demo4.setAddress("address4");
		list.add(demo4);
		
		demoDao.batchInsert(list);
	}
	
	@Test
	public void update(){
		DemoEntity demo = new DemoEntity();
		demo.setId(2l);
		demo.setName("test2");
		demoDao.update(demo);
	}
	@Test
	public void getById(){
		DemoEntity demoEntity = demoDao.getById(1);
		
		System.out.println(demoEntity.getAddress());
	}
	
	@Test
	public void deleteById(){
		
		demoDao.deleteById(10);
	}
	@Test
	public void listPage(){
		
		PageParam pageParam = new PageParam(1, 10);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "test");
		PageBean<DemoEntity> page = demoDao.listPage(pageParam, paramMap);
		
	}
	
	@Test
	public void listBy(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "test");
		
		List<DemoEntity> list = demoDao.listBy(param);
		
		System.out.println(list.size());
	}
	
}
