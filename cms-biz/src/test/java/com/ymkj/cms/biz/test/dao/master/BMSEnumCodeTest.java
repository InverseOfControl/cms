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
import com.ymkj.cms.biz.dao.master.IBMSEnumCodeDao;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSEnumCodeTest {
	
	@Autowired
	private IBMSEnumCodeDao demoDao;
	
	//@Test
	public void insert(){
		
		BMSEnumCode demo = new BMSEnumCode();
		demo.setCode("code");
		demo.setName("EN");
		demo.setNameCN("中国");
		demo.setCodeType("codeType");
		demo.setOrg("org");
		demo.setCodeIndex(1L);
		demo.setIsDeleted(0L);
		demo.setVersion(1L);
		demoDao.insert(demo);
	}
	
	//@Test
	public void update(){
		BMSEnumCode demo = new BMSEnumCode();
		demo.setCodeId(1L);
		demo.setCode("code2");
		demo.setName("EN2");
		demo.setNameCN("中国2");
		demo.setCodeType("codeType2");
		demo.setOrg("org2");
		demo.setCodeIndex(2L);
		demo.setIsDeleted(11L);
		demo.setVersion(2L);
		demoDao.update(demo);
	}
	//@Test
	public void getById(){
		BMSEnumCode demoEntity = demoDao.getById(1);
		
		System.out.println(demoEntity);
	}
	
	//@Test
	public void deleteById(){
		
		demoDao.deleteById(2L);
	}
	//@Test
	public void listPage(){
		
		PageParam pageParam = new PageParam(1, 10);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", "code");
		PageBean<BMSEnumCode> page = demoDao.listPage(pageParam, paramMap);
		System.out.println(page);
		
	}
	
	//@Test
	public void listBy(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", "code");
		
		List<BMSEnumCode> list = demoDao.listBy(param);
		
		System.out.println(list.size());
	}
	@Test
	public void findEnumCodeByCondition(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", 2L);
		
		List<BMSEnumCode> list = demoDao.findEnumCodeByCondition(param);
		
		System.out.println(list.size());
	}
	
}
