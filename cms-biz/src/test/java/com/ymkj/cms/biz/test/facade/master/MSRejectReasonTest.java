package com.ymkj.cms.biz.test.facade.master;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.service.master.IBMSTMReasonExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class MSRejectReasonTest {
	
	@Autowired
	private IBMSTMReasonExecuter iBMSTMReasonExecuter;
	// json 工具类
		private Gson gson = new Gson();
	
	@Test
	public void oneLevel(){
		ReqBMSTMReasonVO reqBMSTMReasonVO = new ReqBMSTMReasonVO();
		reqBMSTMReasonVO.setOperationModule("XSZS");
		reqBMSTMReasonVO.setOperationType("reject");
		//reqBMSTMReasonVO.setReasonType("2");
		reqBMSTMReasonVO.setSysCode("111");
		ResListVO<ReqBMSTMReasonVO>  resList=iBMSTMReasonExecuter.oneLevel(reqBMSTMReasonVO);
		System.out.println(gson.toJson(resList));
	}
	
	@Test
	public void twoLevel(){
		ReqBMSTMReasonVO reqBMSTMReasonVO = new ReqBMSTMReasonVO();
		reqBMSTMReasonVO.setId(new Long("1201"));
		reqBMSTMReasonVO.setSysCode("ams");
		
		ResListVO<ReqBMSTMReasonVO>  resList=iBMSTMReasonExecuter.twoLevel(reqBMSTMReasonVO);
		System.out.println(gson.toJson(resList));
	}
	@Test
	public void twoLevelparents(){
		ReqBMSTMReasonVO reqBMSTMReasonVO = new ReqBMSTMReasonVO();
		List<Long> parentIds=new ArrayList<Long>();
		parentIds.add((long) 21);
		parentIds.add((long) 22);
		reqBMSTMReasonVO.setParentIds(parentIds);
		reqBMSTMReasonVO.setSysCode("111");
		ResListVO<ReqBMSTMReasonVO>  resList=iBMSTMReasonExecuter.twoLevelparents(reqBMSTMReasonVO);
		System.out.println(gson.toJson(resList));
	}
	@Test
	public void findReasonByOperType(){
		ReqBMSTMReasonVO reqBMSTMReasonVO = new ReqBMSTMReasonVO();
		reqBMSTMReasonVO.setOperationType("reject");
		reqBMSTMReasonVO.setOperationModule("HTQY");
		reqBMSTMReasonVO.setSysCode("BMS");
		ResListVO<ReqBMSTMReasonVO>  resList=iBMSTMReasonExecuter.findReasonByOperType(reqBMSTMReasonVO);
		System.out.println(gson.toJson(resList));
	}

}
