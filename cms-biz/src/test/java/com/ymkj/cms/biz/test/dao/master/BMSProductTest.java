package com.ymkj.cms.biz.test.dao.master;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.cms.biz.dao.master.IBMSProductDao;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.service.master.IBMSProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSProductTest {
	
	@Autowired
	private IBMSProductDao demoDao;
	
	@Autowired
	private IBMSProductService demoService;
	// json 工具类
		private Gson gson = new Gson();
	
	//@Test
	public void insert(){
		
		BMSProduct demo = new BMSProduct();
		demo.setName("name");
		demo.setCode("code");
		demo.setDepict("depict");
		demo.setFloorLimit(new BigDecimal(1000.00));
		demo.setUpperLimit(new BigDecimal(20000.00));
		demo.setRate(new BigDecimal(1.50));
		demo.setIsDeleted(0L);
		demo.setCreator("creator");
		demo.setCreatorId(1L);
		demo.setCreatorDate(new Date());
		demo.setModified("modified");
		demo.setModifiedId(2L);
		demo.setModifiedDate(new Date());
		demo.setVersion(1L);
		demoDao.insert(demo);
	}
	
	//@Test
	public void update(){
		
		BMSProduct demo = new BMSProduct();
		demo.setProductId(4L);
		demo.setName("name2");
		demo.setCode("code2");
		demo.setDepict("depict2");
		demo.setFloorLimit(new BigDecimal(10001.00));
		demo.setUpperLimit(new BigDecimal(200002.00));
		demo.setRate(new BigDecimal(1.52));
		demo.setIsDeleted(0L);
		demo.setCreator("creator2");
		demo.setCreatorId(2L);
		demo.setCreatorDate(new Date());
		demo.setModified("modified2");
		demo.setModifiedId(3L);
		demo.setModifiedDate(new Date());
		demo.setVersion(2L);
		demoDao.update(demo);
	}
	//@Test
	public void getById(){
		BMSProduct demo = demoDao.getById(4L);
		
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
		/*paramMap.put("productId", 1L);
		paramMap.put("name", "name2");
		paramMap.put("code", "code2");*/
		
		PageBean<BMSProduct> page = demoService.listPage(pageParam, paramMap);
		System.out.println("-------------结果: "+gson.toJson(page));
		System.out.println(page);
		
		
	}
	
	@Test
	public void listBy(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "");
		List<BMSProduct> list = demoDao.listBy(param);
		
		System.out.println(list.size());
	}
	
	@Test
	public void listProByCondition(){
		Map<String, Object> param = new HashMap<String, Object>();
		/*param.put("orgId", 44L);*/
		param.put("productId", 44L);
		
		List<BMSProduct> list = demoDao.listProByCondition(param);
		
		System.out.println(list.size());
	}		
	
}
