package com.ymkj.cms.biz.test.facade.sign;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractConfirmExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractNoticeExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.test.BankNumberUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class AiTeLoanContractExecuterTest {
	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo(EnumConstants.BMS_SYSCODE);

	// 请求实体
	private RequestVo requestVo = new RequestVo();
	
	@Autowired
	private IAiTeLoanContractExecuter aiTeLoanContractExecuter;

	@Autowired
	private ILoanContractSignExecuter loanContractSignExecuter;
	
	@Autowired
	private IAiTeLoanContractNoticeExecuter loanContractNoticeExecuter;
	
	@Autowired
	private ILoanExecuter loanExecuter;
	
	@Autowired
	private ILoanContractConfirmExecuter loanContractConfirmExecuter;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCancelLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefuseLoan() {
		fail("Not yet implemented");
	}

	

	/**
	 * 取消
	 */
	@Test
	public void cancelLoan() {
		reqLoanContractSignVo.setId(1292715L);
		reqLoanContractSignVo.setLoanNo("201707136748B8");
		reqLoanContractSignVo.setChannelCd("00021");
		reqLoanContractSignVo.setFirstLevleReasons("客户要求取消申请");
		reqLoanContractSignVo.setFirstLevleReasonsCode("3");
		reqLoanContractSignVo.setTwoLevleReasons("0");
		reqLoanContractSignVo.setTwoLevleReasonsCode("0");
		reqLoanContractSignVo.setRemark("");
		reqLoanContractSignVo.setVersion(1L);
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setIp("10.8.30.89");
		reqLoanContractSignVo.setSysCode("cfs");
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.cancelLoan(reqLoanContractSignVo);

		
//		Response<ResLoanContractSignVo> response = aiTeLoanContractExecuter.cancelLoan(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response));

	}

	/**
	 * 拒绝
	 */
	@Test
	public void refuseLoan() {
		reqLoanContractSignVo.setId(1375L);
		reqLoanContractSignVo.setLoanNo("20170503163435048092");
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setPage(0);
		reqLoanContractSignVo.setPageNum(0);
		reqLoanContractSignVo.setPageSize(0);
		reqLoanContractSignVo.setRows(0);
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setIp("172.16.230.134");
		
		reqLoanContractSignVo.setFirstLevleReasons("提供虚假资料/疑欺诈申请");
		reqLoanContractSignVo.setTwoLevleReasons("虚假身份证");
		reqLoanContractSignVo.setFirstLevleReasonsCode("33");
		reqLoanContractSignVo.setTwoLevleReasonsCode("RJ0002700003");
		reqLoanContractSignVo.setRemark("哈哈");
		reqLoanContractSignVo.setSysCode("cfs");
		
		
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.cancelLoan(reqLoanContractSignVo);
		
//		Response<ResLoanContractSignVo> response = loanContractSignExecuter.refuseLoan(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response));

	}

	/**
	 * 保存合同签约信息
	 */
	@Test
	public void saveLoanContractSign() {
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setSysCode("cfs");
		reqLoanContractSignVo.setIp("172.16.230.134");
		reqLoanContractSignVo.setVersion(1L);
		reqLoanContractSignVo.setAccLmt(new BigDecimal(30000));
		reqLoanContractSignVo.setAccTerm(36);
		reqLoanContractSignVo.setChannelCd("00021");
		BigDecimal lmt = new BigDecimal("30000");
		reqLoanContractSignVo.setContractLmt(lmt);
		reqLoanContractSignVo.setContractTrem(36);
		reqLoanContractSignVo.setName("陆金所专用0");
		reqLoanContractSignVo.setOwningBranchId("12827295");
		reqLoanContractSignVo.setProductCd("00015");
		
		reqLoanContractSignVo.setId(1453119L);
		reqLoanContractSignVo.setLoanNo("20170814C48BB5");
		reqLoanContractSignVo.setPversion(14L);
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}

	/**
	 * 保存合同银行账号信息
	 */
	@Test
	public void saveLoanContractBankAcc() {
		// reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setAccLmt(new BigDecimal(30000));
		reqLoanContractSignVo.setAccTerm(36);
		reqLoanContractSignVo.setChannelCd("00021");
		BigDecimal lmt = new BigDecimal("30000");
		reqLoanContractSignVo.setContractLmt(lmt);
		reqLoanContractSignVo.setContractTrem(36);
		reqLoanContractSignVo.setName("陆金所专用0");
		reqLoanContractSignVo.setRtfNodeState("HTQY-ASSIGN");
		
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setIp("10.8.30.89");
		reqLoanContractSignVo.setApplyBankBranch("浦发银行支行");
		reqLoanContractSignVo.setApplyBankBranchId(3);
//		reqLoanContractSignVo.setApplyBankCardNo(BankNumberUtil.getBrankNumber("0102"));
		reqLoanContractSignVo.setApplyBankCardNo("6222021603008207524");
		reqLoanContractSignVo.setApplyBankName("浦发银行");
		reqLoanContractSignVo.setApplyBankNameId(3);
		reqLoanContractSignVo.setId(1293701L);
		reqLoanContractSignVo.setLoanNo("20170724352035");
		reqLoanContractSignVo.setVersion(1L);
		reqLoanContractSignVo.setBankPhone("13113490864");
		
		//
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		 try {
			Date date = sdf.parse("2018-07-22");
			reqLoanContractSignVo.setIdLastDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reqLoanContractSignVo.setLujsName("laf448883536642186");

		
		Response<ResLoanContractSignVo> response = loanContractSignExecuter
				.saveLoanContractBankAcc(reqLoanContractSignVo);
		System.err.println(gson.toJson(response));
	}

	/**
	 * 生成合同
	 */
	@Test
	public void createLoanContract() {
		// System.err.println(String.class.isAssignableFrom(null)+"*******************************");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setServiceCode("zhuyn001");
		reqLoanContractSignVo.setPostCode("0001");
		reqLoanContractSignVo.setIp("10.8.30.31");
//		reqLoanContractSignVo.setAppNo("20170302100111111111");
//		reqLoanContractSignVo.setId(283L);
//		reqLoanContractSignVo.setLoanNo("20170321104425802284");

		reqLoanContractSignVo.setId(1453119L);
		reqLoanContractSignVo.setLoanNo("20170814C48BB5");
		
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setVersion(101L);

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.createLoanContract(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
	
	/**
	 * 合同签定，含标的推送,调用合同确认通知捞财宝
	 */
	@Test
	public void signLoanContractHasTargetPushed() {
//		reqLoanContractSignVo.setId(283L);
//		reqLoanContractSignVo.setLoanNo("20170321104425802284");
		reqLoanContractSignVo.setAccLmt(new BigDecimal(10000));
		reqLoanContractSignVo.setAccTerm(24);
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setContractLmt(new BigDecimal(2000));
		reqLoanContractSignVo.setContractTrem(12);
		reqLoanContractSignVo.setId(1675L);
		reqLoanContractSignVo.setIp("172.16.230.134");
		reqLoanContractSignVo.setLoanNo("20170511105439881111");
		reqLoanContractSignVo.setName("傅清逸");
		reqLoanContractSignVo.setOwningBranchId("12827295");
		reqLoanContractSignVo.setPage(0);
		reqLoanContractSignVo.setPageNum(0);
		reqLoanContractSignVo.setPageSize(0);
		reqLoanContractSignVo.setPostCode("00219303");
		reqLoanContractSignVo.setProductCd("00013");
		reqLoanContractSignVo.setPversion(9L);
		reqLoanContractSignVo.setRows(0);
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setSysCode("cfs");
		reqLoanContractSignVo.setVersion(8L);

		/*Response<ResLoanContractSignVo> response = aiTeLoanContractExecuter
				.signLoanContractHasTargetPushed(reqLoanContractSignVo);*/
		Response<ResLoanContractSignVo> response = loanContractSignExecuter
				.signLoanContract(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response));

	}
	/**
	 * 查询还款计划
	 * 
	 * @author lix YM10160
	 * @date 2017年4月18日下午4:47:54
	 */
	@Test
	public void queryRepaymentDetail() {
		requestVo.setBorrowNo("20170414170234786837");
//		requestVo.setBorrowNo("20170414170236095380");
//		requestVo.setBorrowNo("20170414170237481657");
		requestVo.setSign("90F10AAE64366B6E763B3B4387C65ED6");
//		requestVo.setSign("F767560AC41B6BCD0A582246B956424B");
//		requestVo.setSign("372B8CFA86A70ABCE11FE5B6AB8DE152");
		
		requestVo.setSignDate("2017-04-19");

		Response<Object> response = aiTeLoanContractExecuter.queryRepaymentDetail(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
		
//		signLoanContractHasTargetPushed();
	}
	@Test
	public void fff() {
		saveLoanContractSign();
		saveLoanContractBankAcc();
		createLoanContract();
//		signLoanContractHasTargetPushed();
	}
	/**
	 * 流标通知
	 */
	@Test
	public void bidFailureNoticeAtBidSuccessNotice() {
		requestVo.setBorrowNo("20170804C4C879");
		Response<RequestVo> response = loanContractNoticeExecuter.bidFailureNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	/**
	 * 满标通知
	 */
	@Test
	public void bidSuccessNotice() {
		requestVo.setBorrowNo("20170321104425802284");
		requestVo.setBorrowNo("20170414170234786837");
		requestVo.setBorrowNo("20170414170236095380");
		requestVo.setBorrowNo("20170414170237481657");
		requestVo.setBorrowNo("20170414170238104119");
		requestVo.setBorrowNo("20170414170238178793");
		requestVo.setBorrowNo("20170414170239174748");
		
		requestVo.setSign("8EF0756F4C789C79415A39E62DA7D9AB");
		requestVo.setSign("F767560AC41B6BCD0A582246B956424B");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("F40298D887585501FCFFE57FA8398F3F");
		

		Response<RequestVo> response = loanContractNoticeExecuter.bidSuccessNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	/**
	 * 流标通知
	 */
	@Test
	public void bidFailureNoticeAtTargetLoan() {
//		requestVo.setBorrowNo("20170321104425802284");
		requestVo.setBorrowNo("20170302100000005409");
		requestVo.setBorrowNo("20170414170238104119");
		Response<RequestVo> response = loanContractNoticeExecuter.bidFailureNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	/**
	 * 标的放款
	 */
	@Test
	public void targetLoan() {
//		requestVo.setBorrowNo("20170321104425802284");
		requestVo.setBorrowNo("20170321104425802284");
		requestVo.setBorrowNo("20170414170234786837");
		requestVo.setBorrowNo("20170414170236095380");
		requestVo.setBorrowNo("20170414170237481657");
		requestVo.setBorrowNo("20170414170238104119");
		requestVo.setBorrowNo("20170414170238178793");
		requestVo.setBorrowNo("20170414170239174748");
		
		requestVo.setSign("8EF0756F4C789C79415A39E62DA7D9AB");
		requestVo.setSign("F767560AC41B6BCD0A582246B956424B");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("D270A5DCC3B895D551DEB95902E78790");
		requestVo.setSign("F40298D887585501FCFFE57FA8398F3F");
		Response<RequestVo> response = loanContractNoticeExecuter.targetLoan(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	@Test
	public void lastStep() {
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setContractTrem(24);
		BigDecimal lmt = new BigDecimal("12222222");
		reqLoanContractSignVo.setContractLmt(lmt);
		reqLoanContractSignVo.setServiceCode("zhuyn001");
		reqLoanContractSignVo.setPostCode("0001");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setIp("10.8.30.31");
		reqLoanContractSignVo.setApplyBankBranch("中国银行");
		reqLoanContractSignVo.setApplyBankBranchId(1);
		reqLoanContractSignVo.setApplyBankCardNo("78945146574651234");
		reqLoanContractSignVo.setApplyBankName("中国银行");
		reqLoanContractSignVo.setApplyBankNameId(1);
		reqLoanContractSignVo.setTaskId(7107L);
		reqLoanContractSignVo.setBankPhone("18701705671");
		reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setRtfNodeState("1111");
		reqLoanContractSignVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		reqLoanContractSignVo.setId(1031L);
		reqLoanContractSignVo.setLoanNo("20170421172751061823");
		requestVo.setBorrowNo("20170421172751061823");
		requestVo.setSign("16F599CBAD838A45E8535FFE84064014");
		
		reqLoanContractSignVo.setId(1032L);
		reqLoanContractSignVo.setLoanNo("20170421172801329020");
		requestVo.setBorrowNo("20170421172801329020");
		requestVo.setSign("61BCBC4B72DFAAFD13A01FCA5A92B893");
		
		reqLoanContractSignVo.setId(1033L);
		reqLoanContractSignVo.setLoanNo("20170421172803966418");
		requestVo.setBorrowNo("20170421172803966418");
		requestVo.setSign("FB29173F6D940C03D926639BF295C64D");
		long i = 1;
		reqLoanContractSignVo.setVersion(i);
		//保存
		Response<ResLoanContractSignVo> response1 = loanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
		System.err.println(response1.getRepCode() + "|" + response1.getRepMsg());
		//银行
		Response<ResLoanContractSignVo> response2 = loanContractSignExecuter
				.saveLoanContractBankAcc(reqLoanContractSignVo);
		System.err.println(response2.getRepCode() + "|" + response2.getRepMsg());
		//合同
		Response<ResLoanContractSignVo> response3 = loanContractSignExecuter.createLoanContract(reqLoanContractSignVo);
		System.err.println(response3.getRepCode() + "|" + response3.getRepMsg());
		//推送
		reqLoanContractSignVo.setVersion(++i);
		Response<ResLoanContractSignVo> response4 = aiTeLoanContractExecuter
				.signLoanContractHasTargetPushed(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response4));
		//满标
		reqLoanContractSignVo.setVersion(++i);
		Response<RequestVo> respons5 = loanContractNoticeExecuter.bidSuccessNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(respons5));
		//放款
		Response<RequestVo> response6 = loanContractNoticeExecuter.targetLoan(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response6));
	}
	@Test
	public void lastStep4liu() {
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setContractTrem(24);
		BigDecimal lmt = new BigDecimal("12222222");
		reqLoanContractSignVo.setContractLmt(lmt);
		reqLoanContractSignVo.setServiceCode("zhuyn001");
		reqLoanContractSignVo.setPostCode("0001");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setIp("10.8.30.31");
		reqLoanContractSignVo.setApplyBankBranch("中国银行");
		reqLoanContractSignVo.setApplyBankBranchId(1);
		reqLoanContractSignVo.setApplyBankCardNo("78945146574651234");
		reqLoanContractSignVo.setApplyBankName("中国银行");
		reqLoanContractSignVo.setApplyBankNameId(1);
		reqLoanContractSignVo.setTaskId(7107L);
		reqLoanContractSignVo.setBankPhone("18701705671");
		reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setRtfNodeState("1111");
		reqLoanContractSignVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		reqLoanContractSignVo.setId(1031L);
		reqLoanContractSignVo.setLoanNo("20170421172751061823");
		requestVo.setBorrowNo("20170421172751061823");
		requestVo.setSign("16F599CBAD838A45E8535FFE84064014");
		
		reqLoanContractSignVo.setId(1032L);
		reqLoanContractSignVo.setLoanNo("20170421172801329020");
		requestVo.setBorrowNo("20170421172801329020");
		requestVo.setSign("61BCBC4B72DFAAFD13A01FCA5A92B893");
		
		reqLoanContractSignVo.setId(1033L);
		reqLoanContractSignVo.setLoanNo("20170421172803966418");
		requestVo.setBorrowNo("20170421172803966418");
		requestVo.setSign("FB29173F6D940C03D926639BF295C64D");
		
		reqLoanContractSignVo.setId(1034L);
		reqLoanContractSignVo.setLoanNo("20170421172807460884");
		requestVo.setBorrowNo("20170421172807460884");
		requestVo.setSign("65DF81FBC2BBC8607E151950EBE2D2EE");
		long i = 5;
		reqLoanContractSignVo.setVersion(i);
		//保存
		Response<ResLoanContractSignVo> response1 = loanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
		System.err.println(response1.getRepCode() + "|" + response1.getRepMsg());
		//银行
		Response<ResLoanContractSignVo> response2 = loanContractSignExecuter
				.saveLoanContractBankAcc(reqLoanContractSignVo);
		System.err.println(response2.getRepCode() + "|" + response2.getRepMsg());
		//合同
		Response<ResLoanContractSignVo> response3 = loanContractSignExecuter.createLoanContract(reqLoanContractSignVo);
		System.err.println(response3.getRepCode() + "|" + response3.getRepMsg());
		//推送
		reqLoanContractSignVo.setVersion(++i);
		Response<ResLoanContractSignVo> response4 = aiTeLoanContractExecuter
				.signLoanContractHasTargetPushed(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response4));
		//流标
		Response<RequestVo> response5 = loanContractNoticeExecuter.bidFailureNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response5));
	}
	@Test
	public void lastStep5liu() {
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setContractTrem(24);
		BigDecimal lmt = new BigDecimal("12222222");
		reqLoanContractSignVo.setContractLmt(lmt);
		reqLoanContractSignVo.setServiceCode("zhuyn001");
		reqLoanContractSignVo.setPostCode("0001");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setIp("10.8.30.31");
		reqLoanContractSignVo.setApplyBankBranch("中国银行");
		reqLoanContractSignVo.setApplyBankBranchId(1);
		reqLoanContractSignVo.setApplyBankCardNo("78945146574651234");
		reqLoanContractSignVo.setApplyBankName("中国银行");
		reqLoanContractSignVo.setApplyBankNameId(1);
		reqLoanContractSignVo.setTaskId(7107L);
		reqLoanContractSignVo.setBankPhone("18701705671");
		reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setRtfNodeState("1111");
		reqLoanContractSignVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		reqLoanContractSignVo.setId(1031L);
		reqLoanContractSignVo.setLoanNo("20170421172751061823");
		requestVo.setBorrowNo("20170421172751061823");
		requestVo.setSign("16F599CBAD838A45E8535FFE84064014");
		
		reqLoanContractSignVo.setId(1032L);
		reqLoanContractSignVo.setLoanNo("20170421172801329020");
		requestVo.setBorrowNo("20170421172801329020");
		requestVo.setSign("61BCBC4B72DFAAFD13A01FCA5A92B893");
		
		reqLoanContractSignVo.setId(1033L);
		reqLoanContractSignVo.setLoanNo("20170421172803966418");
		requestVo.setBorrowNo("20170421172803966418");
		requestVo.setSign("FB29173F6D940C03D926639BF295C64D");
		long i = 1;
		reqLoanContractSignVo.setVersion(i);
		//保存
		Response<ResLoanContractSignVo> response1 = loanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
		System.err.println(response1.getRepCode() + "|" + response1.getRepMsg());
		//银行
		Response<ResLoanContractSignVo> response2 = loanContractSignExecuter
				.saveLoanContractBankAcc(reqLoanContractSignVo);
		System.err.println(response2.getRepCode() + "|" + response2.getRepMsg());
		//合同
		Response<ResLoanContractSignVo> response3 = loanContractSignExecuter.createLoanContract(reqLoanContractSignVo);
		System.err.println(response3.getRepCode() + "|" + response3.getRepMsg());
		//推送
		reqLoanContractSignVo.setVersion(++i);
		Response<ResLoanContractSignVo> response4 = aiTeLoanContractExecuter
				.signLoanContractHasTargetPushed(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response4));
		//满标
		reqLoanContractSignVo.setVersion(++i);
		Response<RequestVo> respons5 = loanContractNoticeExecuter.bidSuccessNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(respons5));
		//流标
		Response<RequestVo> response5 = loanContractNoticeExecuter.bidFailureNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response5));
	}
	
	
	@Test
	public void liantiaorudan() {
		
		reqLoanContractSignVo.setAccLmt(new BigDecimal(10000));
		reqLoanContractSignVo.setAccTerm(24);
		reqLoanContractSignVo.setApplyBankBranch("云南支行");
		reqLoanContractSignVo.setApplyBankCardNo("456321687651321");
		reqLoanContractSignVo.setApplyBankName("云南银行");
		reqLoanContractSignVo.setApplyBankNameId(40);
		reqLoanContractSignVo.setBankPhone("13521346987");
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setContractLmt(new BigDecimal(10000));
		reqLoanContractSignVo.setContractTrem(18);
		reqLoanContractSignVo.setId(1045L);
		reqLoanContractSignVo.setIp("172.16.230.134");
		reqLoanContractSignVo.setLoanNo("20170424160616003671");
		reqLoanContractSignVo.setName("周周0");
		reqLoanContractSignVo.setOwningBranchId("12827295");
		reqLoanContractSignVo.setPage(0);
		reqLoanContractSignVo.setPageNum(0);
		reqLoanContractSignVo.setPageSize(0);
		reqLoanContractSignVo.setPostCode("00219303");
		reqLoanContractSignVo.setProductCd("00013");
		reqLoanContractSignVo.setRows(0);
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setSysCode("cfs");
		
		
		//推送
		Response<ResLoanContractSignVo> response4 = aiTeLoanContractExecuter
				.signLoanContractHasTargetPushed(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response4));
		/*//满标
		reqLoanContractSignVo.setVersion(++i);
		Response<RequestVo> respons5 = loanContractNoticeExecuter.bidSuccessNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(respons5));
		//流标
		Response<RequestVo> response5 = loanContractNoticeExecuter.bidFailureNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response5));*/
	}
	@Test
	public void findContractTypeList() {
		reqLoanContractSignVo.setChannelCd("56456456");
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setSysCode("cfs");
		reqLoanContractSignVo.setIp("172.16.230.134");
		ResListVO<ResBMSEnumCodeVO> response = loanContractSignExecuter.findContractTypeList(reqLoanContractSignVo);
		
		System.out.println("-------------结果: " + gson.toJson(response));
	}
	@Test
	public void valiContractTypeIsDisabled() {
		reqLoanContractSignVo.setContractTypeCode("0");
		reqLoanContractSignVo.setChannelCd("56456456");
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("花锦春");
		reqLoanContractSignVo.setSysCode("cfs");
		reqLoanContractSignVo.setIp("172.16.230.134");
		
		Response<String> response = loanContractSignExecuter.valiContractTypeIsDisabled(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response));
	}
	@Test
	public void findSignInfo(){
		reqLoanContractSignVo.setPageNum(1);
		reqLoanContractSignVo.setPageSize(10);
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setId(1293700L);
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.findSignInfo(reqLoanContractSignVo);
		System.err.println(response.getRepCode()+"|"+response.getRepMsg());
	}
	@Test
	public void findLoanChannelLockTarget(){
		reqLoanContractSignVo.setLoanNo("20170713B7ED46");
		reqLoanContractSignVo.setChannelCd("00016");
		reqLoanContractSignVo.setSysCode("cfs");
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.findLoanChannelLockTarget(reqLoanContractSignVo);
		System.out.println("-------------结果: " + gson.toJson(response));
	}
	@Test
	public void backAudit(){
		ReqLoanVo vo= new ReqLoanVo();
		vo.setLoanNo("201710197CDBE7");
		vo.setChannelCd("00016");
		vo.setId(1454491L);
		vo.setFirstLevleReasons("其他");
		vo.setFirstLevleReasonsCode(EnumConstants.ReturnFirstLevleReasonCode.其他_FKSH.getValue());
		vo.setSysCode("bms");
		vo.setServiceCode("00214054");
		vo.setServiceName("郎玖申");
		vo.setIp("172.16.230.134");
		Response<ResLoanVo> response =  loanExecuter.backAudit(vo);
		System.err.println(response.getRepCode()+"|"+response.getRepMsg());
	}
	@Test
	public void confirmLoanContract() {
		// System.err.println(String.class.isAssignableFrom(null)+"*******************************");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
		reqLoanContractSignVo.setServiceCode("00219304");
		reqLoanContractSignVo.setPostCode("0001");
		reqLoanContractSignVo.setIp("10.8.30.31");
//		reqLoanContractSignVo.setAppNo("20170302100111111111");
//		reqLoanContractSignVo.setId(283L);
//		reqLoanContractSignVo.setLoanNo("20170321104425802284");

		reqLoanContractSignVo.setId(1293754L);
		reqLoanContractSignVo.setLoanNo("2017072625A571");
		
		reqLoanContractSignVo.setChannelCd("00016");

		Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.confirmLoanContract(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
	}
}
