package com.ymkj.cms.biz.test.facade.master;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSChannelTest {
	
	// json 工具类
	private Gson gson = new Gson();
		
    // 请求实体
	private ReqBMSChannelVO reqDemo = new ReqBMSChannelVO("11111111");
		
	@Autowired
	private IBMSChannelExecuter channelDemoTest;
	
	//@Test
	public void getAllList() {
		// 调用 soa 接口
		reqDemo.setOrgId(7L);
//		Response<ResBMSChannelVO> aa = channelDemoTest.getChannelById(reqDemo);
//		System.out.println(aa);
//		reqDemo.setName("华澳");
		ResListVO<ResBMSChannelVO> response = channelDemoTest.getChannelByUserCode(reqDemo);
		
		//System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
//		if(response.isSuccess()){
//			List<ResBMSChannelVO> arrayList = response.getCollections();
//			for(ResBMSChannelVO c : arrayList) {
//				System.out.println(c.getName());
//			}
//			System.out.println("-------------vo: "+gson.toJson(response));
//		}
	}
	
	/**
	 * <p>Description:根据借款产品、申请期限、申请额度、营业部获取渠道列表测试方法</p>
	 * @uthor YM10159
	 * @date 2017年3月16日 上午11:06:18
	 */
	//@Test
	public void getChannelByProTermLmt() {
		ReqTrialBeforeCreditChannelVO reqTrialBeforeCreditChannelVO = new ReqTrialBeforeCreditChannelVO("111");
		DecimalFormat format=new java.text.DecimalFormat("0.000");

		//可选参数
		reqTrialBeforeCreditChannelVO.setProductCode("00001");
		reqTrialBeforeCreditChannelVO.setApplyLmt(new BigDecimal(format.format(10000.00)));
		reqTrialBeforeCreditChannelVO.setApplyTerm(12);
		
		reqTrialBeforeCreditChannelVO.setServiceCode("1");
		reqTrialBeforeCreditChannelVO.setServiceName("测试");
		reqTrialBeforeCreditChannelVO.setIp("127.0.0.1");
		
		
		Response<List<ResTrialBeforeCreditChannelVO>> response = channelDemoTest.getChannelByProTermLmt(reqTrialBeforeCreditChannelVO);
		
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	
	/**
	 * <p>Description:根据进件营业部,借款产品、审批额度获取渠道列表测试方法</p>
	 * @uthor YM10139
	 * @date 2017年4月9日 
	 */
	@Test
	public void getChannelByOrgProAlt() {
		ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("111");
		DecimalFormat format=new java.text.DecimalFormat("0.000");

		//可选参数
		reqLoanContractSignVo.setOwningBranchId("12827295");
		reqLoanContractSignVo.setProductCd("00001");
		reqLoanContractSignVo.setAccLmt(new BigDecimal(format.format(10000.00)));
		
		reqLoanContractSignVo.setServiceCode("1");
		reqLoanContractSignVo.setServiceName("测试");
		reqLoanContractSignVo.setIp("127.0.0.1");
		
		
		Response<List<ResBMSChannelVO>> response = channelDemoTest.getChannelByOrgProAlt(reqLoanContractSignVo);
		
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	
//	@Test
	public void getChannelList() {
		// 调用 soa 接口
	/*	reqDemo.setOrgId(7L);*/
//		Response<ResBMSChannelVO> aa = channelDemoTest.getChannelById(reqDemo);
//		System.out.println(aa);
//		reqDemo.setName("华澳");
		reqDemo.setOrgId(12817295L);
		ResListVO<ResBMSChannelVO> response = channelDemoTest.getChannelByOrgId(reqDemo);

		
		//System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
//		if(response.isSuccess()){
//			List<ResBMSChannelVO> arrayList = response.getCollections();
//			for(ResBMSChannelVO c : arrayList) {
//				System.out.println(c.getName());
//			}
//			System.out.println("-------------vo: "+gson.toJson(response));
//		}
	}
	
	@Test
	public void isExistInBMS(){
		ReqBMSLoanBaseVO loanBaseVO = new ReqBMSLoanBaseVO();
		
		loanBaseVO.setSysCode("bms");
		loanBaseVO.setLoanNo("123");
		
		Response<Object> response = channelDemoTest.isExistInBMS(loanBaseVO);
		System.out.println("-------------: "+gson.toJson(response));
	}
}
