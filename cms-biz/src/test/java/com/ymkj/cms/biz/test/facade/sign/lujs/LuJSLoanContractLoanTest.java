package com.ymkj.cms.biz.test.facade.sign.lujs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.ILufaxExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.common.util.JsonUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class LuJSLoanContractLoanTest {

	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");

	

	private ReqLoanVo reqLoanVo= new ReqLoanVo("cfs"); ;
	
	
	@Autowired
	private ILoanExecuter iLoanExecuter;
	
	
	@Autowired
	private ILufaxExecuter iLufaxExecuter;
	
	/**
	 * 陆金所-合同签约
	 */
	@Test
	public void LUJSLoanAuditTest() {
		// 基本信息
		reqLoanVo.setServiceCode("00219303");
		reqLoanVo.setServiceName("陆金所测试");
		reqLoanVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanVo.setLoanNo("2017072199FF3A");
		reqLoanVo.setChannelCd("00021");//
		reqLoanVo.setChannelCd("00021");//

		Response<ResLoanContractSignVo> response =new  Response<ResLoanContractSignVo>();
		Response<ResLoanVo> res = iLoanExecuter.passAuditLoan(reqLoanVo);
		
		System.err.println( "|" +JsonUtils.encode(res) );
	}

	@Test
	public void LUJSLoanAuditBackTest() {
		// 基本信息
		reqLoanVo.setServiceCode("00219303");
		reqLoanVo.setServiceName("陆金所测试");
		reqLoanVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanVo.setId(1293700L);
		reqLoanVo.setLoanNo("2017072199FF3A");
		reqLoanVo.setChannelCd("00021");//
		reqLoanVo.setFirstLevleReasons("身份信息");
		reqLoanVo.setFirstLevleReasonsCode("0005");
		reqLoanVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanVo.setTwoLevleReasonsCode("00015");
		reqLoanVo.setRemark("测试拒绝");

		Response<ResLoanVo> res = iLoanExecuter.backAudit(reqLoanVo);
		
		System.err.println( "|" +JsonUtils.encode(res) );
	}
	@Test
	public void LUJSLoanAuditLoanTest() {
		// 基本信息
		reqLoanVo.setServiceCode("00219303");
		reqLoanVo.setServiceName("陆金所测试");
		reqLoanVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanVo.setId(1292711L);
		reqLoanVo.setLoanNo("2017071317408B");
		reqLoanVo.setChannelCd("00021");//

		Response<ResLoanContractSignVo> response =new  Response<ResLoanContractSignVo>();
		Response<ResLoanVo> res = iLoanExecuter.grantLoan(reqLoanVo);
		
		System.err.println( "|" +JsonUtils.encode(res) );
	}

	
	//放款成功
	@Test
	public void LUJSLoanGrantLoanTest() {
		ReqLufax800001Vo lufax800001Vo = new ReqLufax800001Vo();
		lufax800001Vo.setSysCode("bms");
		lufax800001Vo.setDept_code("111");
		lufax800001Vo.setProject_code("111");
		lufax800001Vo.setProduct_code("111");
		lufax800001Vo.setAps_apply_no("WD001CCCCCZDJR_20170713FD6EE6AAAAAAAAAAA");
		lufax800001Vo.setApply_no("20170713FD6EE6");
		lufax800001Vo.setNotify_type("放款通过");
		lufax800001Vo.setNotify_code("010");
		lufax800001Vo.setNotify_desc("放款通过");
		
		Response<ResLufax800001Vo> response = iLufaxExecuter.inNoticeExternal(lufax800001Vo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	//放款退回
		@Test
		public void LUJSLoanGrantBackTest() {
			// 基本信息
			reqLoanVo.setServiceCode("00219303");
			reqLoanVo.setServiceName("陆金所测试");
			reqLoanVo.setIp("127.0.0.1");

			// 产品信息
			reqLoanVo.setId(1292712L);
			reqLoanVo.setLoanNo("20170713FD6EE6");
			reqLoanVo.setChannelCd("00021");//
			reqLoanVo.setFirstLevleReasons("身份信息");
			reqLoanVo.setFirstLevleReasonsCode("0005");
			reqLoanVo.setTwoLevleReasons("需补充二代身份证");
			reqLoanVo.setTwoLevleReasonsCode("00015");
			reqLoanVo.setRemark("测试退回");

			Response<ResLoanVo> res = iLoanExecuter.backLoan(reqLoanVo);
			System.err.println(res.getRepCode() + "|" + res.getRepMsg());
		}

}
