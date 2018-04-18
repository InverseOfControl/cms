package com.ymkj.cms.biz.test.facade.master;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSLoanBaseExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanBaseExecuterTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSLoanBaseVO reqBMSLoanBaseVO = new ReqBMSLoanBaseVO();
	
	@Autowired
	private IBMSLoanBaseExecuter demoExecuter;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLoanBase() {
		reqBMSLoanBaseVO.setSysCode(EnumConstants.BMS_SYSCODE);
		//渠道限制
		List<String> channelList = new ArrayList<String>();
		channelList.add(EnumConstants.channelCode.BH2.getValue());
		channelList.add(EnumConstants.channelCode.WMXT.getValue());
		channelList.add(EnumConstants.channelCode.LXXD.getValue());
		channelList.add(EnumConstants.channelCode.HRBH.getValue());
		channelList.add(EnumConstants.channelCode.BSYH.getValue());
		reqBMSLoanBaseVO.setChannelList(channelList);
		//状态限制（放款审核代办）
		reqBMSLoanBaseVO.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
		//流程限制
		reqBMSLoanBaseVO.setTaskName(EnumConstants.WF_FKQR_HX);
		
		
		ResListVO<ResBMSLoanBaseVO> response = demoExecuter.findLoanBase(reqBMSLoanBaseVO);
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}

}
