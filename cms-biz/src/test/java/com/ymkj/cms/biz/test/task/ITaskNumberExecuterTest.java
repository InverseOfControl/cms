package com.ymkj.cms.biz.test.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.service.task.ITaskNumberExecuter;
import com.ymkj.cms.biz.api.vo.request.task.PersonCodeAndRoleVo;
import com.ymkj.cms.biz.api.vo.request.task.ReqBMSTaskNumberVo;
import com.ymkj.cms.biz.api.vo.response.task.ResBMSTaskNumberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ITaskNumberExecuterTest {
	
	private Gson gson = new Gson();
	
	private ReqBMSTaskNumberVo reqVo;
	
	@Autowired
	private ITaskNumberExecuter iTaskNumberExecuter;
	
	@Before
	public void init() {
		reqVo = new ReqBMSTaskNumberVo();
		List<PersonCodeAndRoleVo> personCoAndRoVos = new ArrayList<PersonCodeAndRoleVo>();
		/*PersonCodeAndRoleVo personCoAndRoVo1 = new PersonCodeAndRoleVo();
		personCoAndRoVo1.setPersonRole("1");
		personCoAndRoVo1.setPersonCode("zhouwen");
		
		personCoAndRoVos.add(personCoAndRoVo1);*/
		
		PersonCodeAndRoleVo personCoAndRoVo2 = new PersonCodeAndRoleVo();
		personCoAndRoVo2.setPersonRole("2");
		personCoAndRoVo2.setPersonCode("shipengfei");
		
		personCoAndRoVos.add(personCoAndRoVo2);
		
		reqVo.setPersonCoAndRos(personCoAndRoVos);
	}
	
	@Test
	public void queryTaskNumberQues() {
		reqVo.setSysCode("bms");
		ResBMSTaskNumberVo resVo = iTaskNumberExecuter.queryTaskNumberQues(reqVo);
		System.err.println("-------------结果: "+gson.toJson(resVo));
	}

}
