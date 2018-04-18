package com.ymkj.cms.biz.test.facade.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.sign.IBaoShangLoanContractSignExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractConfirmExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BaoShangLoanContractTest {
	
	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("bms");
	
	@Autowired
	private ILoanContractConfirmExecuter loanContractSignExecuter;
	@Autowired
	private ILoanContractSignExecuter loanContractSignsExecuter;
	@Autowired
	private IBaoShangLoanContractSignExecuter baoShangLoanContractSignExecuter;
	@Autowired
	private ILoanExecuter loanExecuter;

	/**
	 * 
	 * @Title: testContract 
	 * @Description: 生成合同测试
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月15日 下午4:03:32
	 */
	@Test
	public void testContract(){
		System.out.println("测试开始");
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试12");
		reqLoanContractSignVo.setPversion(3L);
		reqLoanContractSignVo.setIp("127.0.0.1");
		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		Response<ResLoanContractSignVo> response = loanContractSignsExecuter.createLoanContract(reqLoanContractSignVo);
		System.err.println(response.getRepCode() + "|" + response.getRepMsg());
		System.out.println("测试结束");
		
	}
	
	/**
	 * 
	 * @Title: loanTestContract 
	 * @Description: 测试合同签订
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月19日 下午2:06:50
	 */
	@Test
	public void loanTestContract(){
		System.out.println("测试开始");
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.0.1");
		// 产品信息
		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		Response<ResLoanContractSignVo> response = loanContractSignsExecuter.signLoanContract(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg());
	}
	
	/**
	 * 
	 * @Title: loanTestCx 
	 * @Description: 撤销
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月15日 下午4:03:54
	 */
	@Test
	public void loanTestCx(){
		System.out.println("测试开始");
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		reqLoanContractSignVo.setIdType("01");
		reqLoanContractSignVo.setIdNo("411302199102142817");
		reqLoanContractSignVo.setCustName("王建峰");
		reqLoanContractSignVo.setBusNumber("C521707060002620065");
		reqLoanContractSignVo.setSysCode("cfs");
		Response<Object> response = baoShangLoanContractSignExecuter.loanRepeal(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg()+"|"+response.getData());
	}

	/**
	 * 包银通知测试
	 */
	@Test
	public void noticeTest(){
		reqLoanContractSignVo.setRespcd("3800");//0100 交易失败  ; 0000 交易成功 3800
		reqLoanContractSignVo.setResptx("[3802]动态补件信息");//[0101]交易失败  [0001]交易成功 [0102]交易拒绝 [3801]影音资料待上传
		reqLoanContractSignVo.setBusNumber("C521708020002700010");
		reqLoanContractSignVo.setPatchType("01|02");
		Response<ResLoanContractSignVo> response = baoShangLoanContractSignExecuter.riskManagementNotice(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg()+"|"+response.getData());
	}

	/**
	 * 合同确认测试
	 */
	@Test
	public void ComfirmLoanTestContract(){
		System.out.println("测试开始");
		reqLoanContractSignVo.setServiceCode("00219304");
		reqLoanContractSignVo.setServiceName("柳清");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		reqLoanContractSignVo.setId(4791L);
		reqLoanContractSignVo.setLoanNo("201707074B9954");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.confirmLoanContract(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg()+"|"+response.getData());

	}
	/**
	 * 合同确认退回测试
	 */
	@Test
	public void backComfirm(){
		System.out.println("测试开始");
		reqLoanContractSignVo.setServiceCode("00219304");
		reqLoanContractSignVo.setServiceName("柳清");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		reqLoanContractSignVo.setFirstLevleReasons("身份信息");
		reqLoanContractSignVo.setFirstLevleReasonsCode("0005");
		reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanContractSignVo.setTwoLevleReasonsCode("00015");
		reqLoanContractSignVo.setRemark("测试拒绝");
		Response<ResLoanContractSignVo> response = loanContractSignExecuter.backConfirm(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg()+"|"+response.getData());

	}
	/**
	 * 上一步测试
	 */
	@Test
	public void returnLastStep(){
		System.out.println("测试开始");
		reqLoanContractSignVo.setServiceCode("00219304");
		reqLoanContractSignVo.setServiceName("柳清");
		reqLoanContractSignVo.setIp("127.0.6.1");
		reqLoanContractSignVo.setPversion(3L);

		reqLoanContractSignVo.setId(1452543L);
		reqLoanContractSignVo.setLoanNo("20170802396D82");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		Response<ResLoanContractSignVo> response = loanContractSignsExecuter.returnLastStep(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg()+"|"+response.getData());

	}
	/**
	 *
	 * @Title: loanTestContract
	 * @Description: 测试拒绝
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月19日 下午2:06:50
	 */
	@Test
	public void loanTestReulse(){
		System.out.println("测试开始");
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.0.1");
		// 产品信息
		reqLoanContractSignVo.setId(1292831L);
		reqLoanContractSignVo.setLoanNo("20170718849356");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		reqLoanContractSignVo.setFirstLevleReasons("身份信息");
		reqLoanContractSignVo.setFirstLevleReasonsCode("0005");
		reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanContractSignVo.setTwoLevleReasonsCode("00015");
		reqLoanContractSignVo.setRemark("测试拒绝");
		Response<ResLoanContractSignVo> response = loanContractSignsExecuter.refuseLoan(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg());
	}

	/**
	 *
	 * @Title: loanTestContract
	 * @Description: 测试取消
	 * @author lisc@yuminsoft.com
	 * @date: 2017年6月19日 下午2:06:50
	 */
	@Test
	public void loanTestCancel(){
		System.out.println("测试开始");
		// 基本信息
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("包银测试1");
		reqLoanContractSignVo.setIp("127.0.0.1");
		// 产品信息
		reqLoanContractSignVo.setId(1292833L);
		reqLoanContractSignVo.setLoanNo("20170718744520");
		reqLoanContractSignVo.setChannelCd("00018");// 包银渠道
		reqLoanContractSignVo.setFirstLevleReasons("身份信息");
		reqLoanContractSignVo.setFirstLevleReasonsCode("0005");
		reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanContractSignVo.setTwoLevleReasonsCode("00015");
		reqLoanContractSignVo.setRemark("测试拒绝");
		Response<ResLoanContractSignVo> response = loanContractSignsExecuter.cancelLoan(reqLoanContractSignVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg());
	}

	/**
	 *放款审核退回测试
	 */
	@Test
	public void testBackAudit2(){
		ReqLoanVo reqLoanVo=new ReqLoanVo();
		reqLoanVo.setIp("127.0.0.1");
		reqLoanVo.setId(1293710L);
		reqLoanVo.setServiceCode("00219303");
		reqLoanVo.setServiceName("包银测试1");
		reqLoanVo.setLoanNo("2017072272B837");
		reqLoanVo.setSysCode("bms");
		reqLoanVo.setChannelCd("00018");
		reqLoanVo.setFirstLevleReasons("身份信息");
		reqLoanVo.setFirstLevleReasonsCode("0005");
		reqLoanVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanVo.setTwoLevleReasonsCode("00015");
		reqLoanVo.setRemark("测试拒绝");
		Response<ResLoanVo> response = loanExecuter.backAudit(reqLoanVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg());
	}

	/**
	 *放款审核退回测试
	 */
	@Test
	public void testBackLoan2(){
		ReqLoanVo reqLoanVo=new ReqLoanVo();
		reqLoanVo.setIp("127.0.0.1");
		reqLoanVo.setId(1293710L);
		reqLoanVo.setServiceCode("00219303");
		reqLoanVo.setServiceName("包银测试1");
		reqLoanVo.setLoanNo("2017072272B837");
		reqLoanVo.setSysCode("bms");
		reqLoanVo.setChannelCd("00018");
		reqLoanVo.setFirstLevleReasons("身份信息");
		reqLoanVo.setFirstLevleReasonsCode("0005");
		reqLoanVo.setTwoLevleReasons("需补充二代身份证");
		reqLoanVo.setTwoLevleReasonsCode("00015");
		reqLoanVo.setRemark("测试拒绝");
		Response<ResLoanVo> response = loanExecuter.backLoan(reqLoanVo);
		System.out.println(response.getRepCode()+"|"+response.getRepMsg());
	}

}
