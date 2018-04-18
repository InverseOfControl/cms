package com.ymkj.cms.biz.test.facade.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.IBaoShangLoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BaoShangLoanContractSignTest {

	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");

	@Autowired
	private IBaoShangLoanContractSignExecuter loanContractSignExecuter;
	@Autowired
	private ILoanContractSignExecuter iLoanContractSignExecuter;
	/**
	 * 保存合同签约信息
	 */
	 @Test
	public void saveLoanContractSign() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.0.1");
		reqLoanContractSignVo.setPversion(1L);
		// 产品信息
		 reqLoanContractSignVo.setId(1452543L);
		 reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		reqLoanContractSignVo.setContractTrem(24);
		reqLoanContractSignVo.setContractLmt(new BigDecimal("20000"));
		Response<ResLoanContractSignVo> response = iLoanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**
	 * 保存合同银行账号信息
	 */
	@Test
	public void saveLoanContractBankAcc() {

		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(2L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		reqLoanContractSignVo.setApplyBankCardNo("6217211107001880725");
		reqLoanContractSignVo.setApplyBankBranch("联洋广场支行");
		reqLoanContractSignVo.setApplyBankNameId(3);
		reqLoanContractSignVo.setApplyBankName("工商银行");

		reqLoanContractSignVo.setContractTrem(24);
		reqLoanContractSignVo.setContractLmt(new BigDecimal("40000"));
		reqLoanContractSignVo.setBankPhone("13162508558");
		reqLoanContractSignVo.setPversion(2L);
		Response<ResLoanContractSignVo> response = iLoanContractSignExecuter
				.saveLoanContractBankAcc(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**
	 * 黑名单检验-包银渠道
	 */
	@Test
	public void silverListCheck() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.silverListCheck(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	/**
	 * 包商银行渠道审核通过与否查询-包银渠道
	 */
	@Test
	public void BaoShangChannelAuditResult() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(2L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.ByChannelAuditResult(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	/**
	 * 获取短信验证码-包银渠道
	 */
	@Test
	public void getVerificationCode() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(2L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		reqLoanContractSignVo.setBankPhone("13162508558");
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.getVerificationCode(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}


	/**
	 * 银行卡鉴权-包银渠道
	 */
	@Test
	public void authenticationCheck() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试2");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		reqLoanContractSignVo.setVerifyCode("12345");

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.authenticationCheck(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**
	 * 风控审核-包银渠道
	 */
	@Test
	public void airControlCheck() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试12");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.airControlCheck(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	/**
	 * 风控审核通知接口-包银渠道
	 */
	@Test
	public void airControlCheckResult() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试21");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		// 产品信息
		reqLoanContractSignVo.setId(4776L);
		reqLoanContractSignVo.setLoanNo("2017070614BC80");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.airControlCheckResult(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

}
