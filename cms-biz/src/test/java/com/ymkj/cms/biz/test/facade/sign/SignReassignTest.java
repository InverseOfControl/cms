package com.ymkj.cms.biz.test.facade.sign;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.ISignReassignmentExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;



/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:SignReassignTest</p>
 * <p>Description:签约分派</p>
 * @uthor YM10159
 * @date 2017年4月5日 上午9:59:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class SignReassignTest {
	
		@Autowired
		private ISignReassignmentExecuter signReassignmentExecuter;
		
		@Test
		public void signReassign() {
			ReqSignReassignVO reqSignReassignVO = new ReqSignReassignVO();
			
 			Response<Object> response = signReassignmentExecuter.signReassign(reqSignReassignVO);
 			
		}
}

