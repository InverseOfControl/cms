package com.ymkj.cms.biz.test.facade.sign;


import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.ILoanContractConfirmExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanContractConfirmTest {
	// json 工具类
		private Gson gson = new Gson();
			
	    // 请求实体
		private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");
			
		@Autowired
		private ILoanContractConfirmExecuter loanContractConfirmExecuter;
		
		@Autowired
		private ILoanContractSignExecuter loanContractSignExecuter;
		
		
		
		/**
		 * 合同确认待办
		 */
		@Test
		public void undoneContractSignListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00214054");
			Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.undoneContractConfirmListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		
		
		/**
		 * 合同确认已完成
		 */
		@Test
		public void doneContractSignListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00214054");
			Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.doneContractConfirmListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		
		
		/**
		 * 合同确认完成
		 */
		//@Test
		public void doneContractConfirmListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00214054");
			Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.doneContractConfirmListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		/**
		 * 合同确认
		 */
		@Test
		public void LoanContractComfirm(){
/*			
			[{"id":2119,"loanNo":"20170420104721993216","name":"测试二十","accLmt":20000,"accTerm":12,"pageNum":0,
				"pageSize":0,"rows":0,"page":0,"serviceCode":"ceshi02","serviceName":"ceshi","ip":"172.16.230.133","sysCode":"cfs"}]*/
/*			reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");*/
			reqLoanContractSignVo.setServiceCode("00214054");
			reqLoanContractSignVo.setPageNum(0);
			reqLoanContractSignVo.setPageSize(0);
			reqLoanContractSignVo.setSysCode("cfs");
			reqLoanContractSignVo.setServiceName("ceshi");
			reqLoanContractSignVo.setIp("172.16.230.133");
			reqLoanContractSignVo.setName("测试二十");
			BigDecimal lmt= new BigDecimal(20000L);
			reqLoanContractSignVo.setAccTerm(12);
			reqLoanContractSignVo.setContractLmt(lmt);
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setLoanNo("20170627114611278471");
			reqLoanContractSignVo.setId(3603L);
			//reqLoanContractSignVo.setVersion(36L);
			Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.confirmLoanContract(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		/**
		 * 合同确认退回
		 */
		@Test
		public void backComfirm(){
			reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
			reqLoanContractSignVo.setServiceCode("00214054");
			reqLoanContractSignVo.setServiceName("1111");
			reqLoanContractSignVo.setPostCode("0001");
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setFirstLevleReasons("房产信息");
			reqLoanContractSignVo.setFirstLevleReasonsCode("447");
			reqLoanContractSignVo.setTwoLevleReasons("补充房产地址下带有申请者姓名的有效单据（最近30日的电费单 / 水费单 / 燃气单 / 固定电话费单 / 有线电视费单 / 供暖费单）或产调证明");
			reqLoanContractSignVo.setTwoLevleReasonsCode("452");
			reqLoanContractSignVo.setRemark("测试退回");
			//reqLoanContractSignVo.setTaskId(4020L);
			reqLoanContractSignVo.setId(1513L);
			reqLoanContractSignVo.setLoanNo("20170508092432847116");
			//reqLoanContractSignVo.setVersion(36L);
			Response<ResLoanContractSignVo> response = loanContractConfirmExecuter.backConfirm(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		/**
		 * 退件箱办理处理
		 */
		@Test
		public void returnBoxSubmit(){
			reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
			reqLoanContractSignVo.setServiceCode("zhuyn001");
			reqLoanContractSignVo.setServiceName("1111");
			reqLoanContractSignVo.setPostCode("0001");
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
	
			reqLoanContractSignVo.setLoanNo("20170519085622912442");
			//reqLoanContractSignVo.setTaskId(4020L);
			reqLoanContractSignVo.setId(3386L);
			reqLoanContractSignVo.setTaskName("合同签订");
			//reqLoanContractSignVo.setVersion(36L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.returnBoxChoiceTask(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		
		
		/**
		 * 退件箱办理处理取消
		 */
		//@Test
		public void returnBoxSubmitCancel(){
			returnBoxSubmit();
			reqLoanContractSignVo.setApplyBankBranch("zhongguoyinhang");
			reqLoanContractSignVo.setServiceCode("zhuyn001");
			reqLoanContractSignVo.setServiceName("1111");
			reqLoanContractSignVo.setPostCode("0001");
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setFirstLevleReasons("身份信息");
			reqLoanContractSignVo.setFirstLevleReasonsCode("0005");
			reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
			reqLoanContractSignVo.setTwoLevleReasonsCode("00015");
			reqLoanContractSignVo.setRemark("测试拒绝");
			reqLoanContractSignVo.setLoanNo("20170416115236880737");
			//reqLoanContractSignVo.setTaskId(4020L);
			reqLoanContractSignVo.setId(793L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.cancelLoan(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		@Test
		public void queryContractConfirmToDoCount(){
			reqLoanContractSignVo.setServiceCode("00214054");
			reqLoanContractSignVo.setServiceName("aaa");
			reqLoanContractSignVo.setIp("127.0.0.1");
			
			Response<Object> response = loanContractConfirmExecuter.queryContractConfirmToDoCount(reqLoanContractSignVo);
			System.out.println("---------->"+gson.toJson(response));
		}
}
