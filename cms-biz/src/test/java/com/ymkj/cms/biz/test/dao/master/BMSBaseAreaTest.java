package com.ymkj.cms.biz.test.dao.master;

import java.util.Date;
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
import com.ymkj.cms.biz.dao.master.IBMSBaseAreaDao;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSBaseAreaTest {
	
	@Autowired
	private IBMSBaseAreaDao demoDao;
	
	//@Test
	public void insert(){
		
		BMSBaseArea demo = new BMSBaseArea();
		demo.setCode("0100203");
		demo.setName("苏州");
		demo.setParentId(0L);
		demo.setDeep(2L);
		demo.setIsDeleted(0L);
		demo.setVersion(1L);
		demo.setCreator("老王2");
		demo.setCreatorId(2L);
		demo.setCreatorDate(new Date());
		demoDao.insert(demo);
	}
	
	//@Test
	public void update(){
		
		BMSBaseArea demo = new BMSBaseArea();
		demo.setAreaId(3L);
		demo.setCode("010020304");
		demo.setName("昆山");
		demo.setParentId(2L);
		demo.setDeep(5L);
		demo.setIsDeleted(3L);
		demo.setVersion(2L);
		demo.setCreator("老王3");
		demo.setCreatorId(6L);
		demo.setCreatorDate(new Date());
		demoDao.update(demo);
	}
	//@Test
	public void getById(){
		BMSBaseArea demoEntity = demoDao.getById(1L);
		
		System.out.println(demoEntity);
	}
	
	//@Test
	public void deleteById(){
		
		demoDao.deleteById(5L);
	}
	//@Test
	public void listPage(){
		
		PageParam pageParam = new PageParam(1, 10);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", 2L);
		PageBean<BMSBaseArea> page = demoDao.listPage(pageParam, paramMap);
		System.out.println(page);
		
	}
	
	@Test
	public void listBy(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "苏");
		//param.put("deep", 2L);
		
		List<BMSBaseArea> list = demoDao.listBy(param);
		
		System.out.println(list.size());
	}
	
}
