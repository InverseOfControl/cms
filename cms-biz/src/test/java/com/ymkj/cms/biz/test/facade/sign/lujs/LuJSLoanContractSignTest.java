package com.ymkj.cms.biz.test.facade.sign.lujs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.ILoanContractConfirmExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.ILufaxExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLujsInformVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLujsInformVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class LuJSLoanContractSignTest {

	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");

	@Autowired
	private ILoanContractSignExecuter iLoanContractSignExecuter;

	@Autowired
	private ILoanContractConfirmExecuter iLoanContractConfirmExecuter;

	@Autowired
	private ILufaxExecuter iLufaxExecuter;
	
	Gson gson = new Gson();

	/**
	 * 陆金所-合同签约
	 */
	@Test
	public void LUJSContractSignTest() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00213223");
		reqLoanContractSignVo.setServiceName("陆金所测试");
		reqLoanContractSignVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanContractSignVo.setId(Long.valueOf(1292712));
		reqLoanContractSignVo.setLoanNo("20170713FD6EE6");
		reqLoanContractSignVo.setChannelCd("00021");//
		
		Response<ResLoanContractSignVo> response = iLoanContractSignExecuter.signLoanContract(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**
	 * 陆金所-人工审核通知
	 */
	@Test
	public void inNoticeExternalTest() {
		ReqLufax800001Vo lufax800001Vo = new ReqLufax800001Vo();
		lufax800001Vo.setSysCode("bms");
		lufax800001Vo.setDept_code("WD001");
		lufax800001Vo.setProject_code("1000500010");
		lufax800001Vo.setProduct_code("1000500010001");
		lufax800001Vo.setAps_apply_no("WD001CCCCCZDJR_2017072185FB26AAAAAAAAAAA");
		lufax800001Vo.setApply_no("ZDJR_2017072185FB26");
		lufax800001Vo.setNotify_type("信审通过");
		lufax800001Vo.setNotify_code("005");
		lufax800001Vo.setNotify_desc("信审通过");

		Response<ResLufax800001Vo> response = iLufaxExecuter.inNoticeExternal(lufax800001Vo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**合同确认*/
	@Test
	public void contractConfirmTest() {
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00213223");
		reqLoanContractSignVo.setServiceName("陆金所测试");
		reqLoanContractSignVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanContractSignVo.setId(Long.valueOf(1292712));
		reqLoanContractSignVo.setLoanNo("20170825FB86CE");
		reqLoanContractSignVo.setChannelCd("00021");//

		Response<ResLoanContractSignVo> response = iLoanContractConfirmExecuter.confirmLoanContract(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**合同确认退回*/
	@Test
	public void backConfirmTest(){
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00213223");
		reqLoanContractSignVo.setServiceName("陆金所测试");
		reqLoanContractSignVo.setIp("127.0.0.1");

		// 产品信息
		reqLoanContractSignVo.setLoanNo("20170713FD6EE6");
		reqLoanContractSignVo.setId(Long.valueOf(1292712));
		reqLoanContractSignVo.setChannelCd("00021");//
		reqLoanContractSignVo.setFirstLevleReasonsCode("001");
		reqLoanContractSignVo.setFirstLevleReasons("退回原因");
		
		Response<ResLoanContractSignVo> response = iLoanContractConfirmExecuter.backConfirm(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	
	/**
	 * @Description:点击办理判断陆金所人工审核返回结果</p>
	 * @uthor YM10159
	 * @date 2017年7月21日 上午8:39:55
	 */
	@Test
	public void dealWithAuditResultTest(){
		// 基本信息
		ReqLujsInformVo lujsInformVo = new ReqLujsInformVo();
		lujsInformVo.setSysCode("cfs");
		lujsInformVo.setLoanNo("20170713FD6EE6");
		lujsInformVo.setCurrentTaskNode("LUJS_MANUAL_AUDIT");
		
		Response<ResLujsInformVo> response = iLufaxExecuter.dealWithAuditResult(lujsInformVo);
		System.out.println("结果: "+gson.toJson(response));
	}

}
