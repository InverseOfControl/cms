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

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.cms.biz.dao.master.IBMSProductAuditLimitDao;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class BMSProductLimitTest {
	
	@Autowired
	private IBMSProductAuditLimitDao demoDao;
	//@Test
		public void insert(){
			
			BMSProductAuditLimit demo = new BMSProductAuditLimit();
			demo.setProductId(1L);
			demo.setProductCode("CODE");
			demo.setFloorLimit(new BigDecimal(1000.00));
			demo.setUpperLimit(new BigDecimal(20000.00));
			demo.setAuditLimit(12L);
			demo.setIsDisabled(0L);
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
			BMSProductAuditLimit demo = new BMSProductAuditLimit();
			demo.setAuditLimitId(2L);
			demo.setProductId(12L);
			demo.setProductCode("CODE1");
			demo.setFloorLimit(new BigDecimal(10001.00));
			demo.setUpperLimit(new BigDecimal(200001.00));
			demo.setAuditLimit(122L);
			demo.setIsDisabled(0L);
			demo.setIsDeleted(0L);
			demo.setCreator("creator2");
			demo.setCreatorId(1L);
			demo.setCreatorDate(new Date());
			demo.setModified("modified2");
			demo.setModifiedId(2L);
			demo.setModifiedDate(new Date());
			demo.setVersion(1L);
			demoDao.update(demo);
		
			
		}
		//@Test
		public void getById(){
			BMSProductAuditLimit demo = demoDao.getById(2L);
			
			System.out.println(demo);
		}
		
		//@Test
//		public void deleteById(){
//			
//			demoDao.deleteById(1L);
//		}
		//@Test
		public void listPage(){
			
			PageParam pageParam = new PageParam(1, 10);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("productId", 12L);
			paramMap.put("productCode", "CODE1");
			
			PageBean<BMSProductAuditLimit> page = demoDao.listPage(pageParam, paramMap);
			System.out.println(page);
			
		}
		
		@Test
		public void listBy(){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("channelId", 27L);
			param.put("userCode", "CODE1");
			param.put("orgId", 7L);
			
			List<BMSProductAuditLimit> list = demoDao.findLimitByChaIdUserCode(param);
			
			System.out.println(list.size());
		}
}
