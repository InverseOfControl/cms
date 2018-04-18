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
import com.ymkj.cms.biz.dao.master.IBMSProductCodeModuleDao;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSProductCodeModuleTest {
	
	@Autowired
	private IBMSProductCodeModuleDao demoDao;
	
	//@Test
	public void insert(){
		
		BMSProductCodeModule demo = new BMSProductCodeModule();
		demo.setProductId(2L);
		demo.setCodeId(4L);
		demo.setIsDeleted(0L);;
		demoDao.insert(demo);
	}
	
	//@Test
	public void update(){
		BMSProductCodeModule demo = new BMSProductCodeModule();
		demo.setId(1L);
		demo.setProductId(2L);
		demo.setCodeId(4L);
		demo.setIsDeleted(0L);;
		demoDao.update(demo);
	}
	//@Test
	public void getById(){
		BMSProductCodeModule demo = demoDao.getById(1L);
		
		System.out.println(demo);
	}
	
	@Test
	public void deleteById(){
		
		demoDao.deleteById(1L);
	}
	//@Test
	public void listPage(){
		
		PageParam pageParam = new PageParam(1, 10);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productId", 2L);
		PageBean<BMSProductCodeModule> page = demoDao.listPage(pageParam, paramMap);
		System.out.println(page);
		
	}

	//@Test
	public void listBy(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", 2L);
		
		List<BMSProductCodeModule> list = demoDao.listBy(param);
		
		System.out.println(list.size());
	}
	
}
