package com.ymkj.cms.biz.test.cache;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.cache.redis.ListTranscoder;
import com.ymkj.cms.biz.cache.redis.ObjectsTranscoder;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.common.util.RedisUtil;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.service.master.IBMSEnumCodeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-redis.xml")
public class TestSerializerTranscoder  implements Serializable {
	@Autowired
	RedisUtil redisUtil;

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6859753582797842643L;
	

	//@Test
	public void testObject() {
		
		ObjectsTranscoder<TestPerson> objTranscoder =  new ObjectsTranscoder<>();
		
		
		TestPerson userA_userA = (TestPerson) redisUtil.get("creditApplication");
		
		System.out.println(userA_userA.getName() + "\t" + userA_userA.getAge());
	}
	
	@Test
	public void testList() {
		List<TestPerson> lists = buildTestData();
		

		ListTranscoder<TestPerson> listTranscoder = new ListTranscoder<>();
		
		
		
		List<TestPerson> results = (List<TestPerson>) redisUtil.get("userListA");
	
		for (TestPerson user : results) {
			System.out.println(user.getName() + "\t" + user.getAge());
		}
		
		redisUtil.remove("userListA");
		
	}
	
	private static List<TestPerson> buildTestData() {
		TestSerializerTranscoder tst = new TestSerializerTranscoder();
		TestPerson userA =  new TestPerson();
		userA.setName("lily"); 
        userA.setAge(25);
		
        
        TestPerson userB = new TestPerson();  
        
        
        userB.setName("Josh Wang"); 
		userB.setAge(28);
        
        List<TestPerson> list = new ArrayList<TestPerson>();  
        list.add(userA);  
        list.add(userB);  
        
        return list;  
    }
	//@Test
	public void testList1() throws IllegalAccessException, InvocationTargetException, IntrospectionException {
	
		List<BMSEnumCode> listEnum  = new ArrayList<BMSEnumCode>();
		ReqBMSEnumCodeVO reqDemoVO = new ReqBMSEnumCodeVO();
		reqDemoVO.setCodeType("creditApplication");
		ListTranscoder<BMSEnumCode> listTranscoder = new ListTranscoder<BMSEnumCode>();
		List<BMSEnumCode> result = (List<BMSEnumCode>) redisUtil.get("creditApplication1");
		if(result !=null){
			listEnum = result;
		}else{
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqDemoVO);
			BMSEnumCode enumCode1 = new BMSEnumCode();
			enumCode1.setCode("1111");
			BMSEnumCode enumCode2 = new BMSEnumCode();
			enumCode2.setCode("2222");
			listEnum.add(enumCode1);
			listEnum.add(enumCode2);
			//将枚举放入缓存
			redisUtil.set("creditApplication1", listEnum);
		}
	}
}
