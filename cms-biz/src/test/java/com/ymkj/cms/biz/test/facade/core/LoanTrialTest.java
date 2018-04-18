package com.ymkj.cms.biz.test.facade.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.ITrialBeforeCreditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanTrialVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditVO;
import com.ymkj.cms.biz.common.util.DateUtil;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IReconsiderationLoanTest</p>
 * <p>Description:贷前试算测试类</p>
 * @uthor YM10159
 * @date 2017年3月3日 下午1:43:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class LoanTrialTest {
	
	private Gson gson = new Gson();

	@Autowired
	private ITrialBeforeCreditExecuter iTrialBeforeCreditExecuter;
	
	@Test
	public void trial() throws ParseException {
		
		ReqTrialBeforeCreditVO reqLoanTrialVO = new ReqTrialBeforeCreditVO();
		DecimalFormat format=new java.text.DecimalFormat("0.000");
		
		reqLoanTrialVO.setChannelCode("00001"); 
		reqLoanTrialVO.setProductCode("00020");
		reqLoanTrialVO.setApplyLmt(new BigDecimal(format.format(111)));
		reqLoanTrialVO.setApplyTerm(24);
		reqLoanTrialVO.setFirstRepaymentDate("2018-03-01");
		
		reqLoanTrialVO.setServiceCode("11");
		reqLoanTrialVO.setServiceName("11");
		reqLoanTrialVO.setIp("127.0.0.1");
		
		Response<List<ResTrialBeforeCreditVO>> response = iTrialBeforeCreditExecuter.trial(reqLoanTrialVO);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
}
