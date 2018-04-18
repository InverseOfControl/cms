package com.ymkj.cms.biz.test.facade.sign;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractInfoVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanContractSignTest {
		// json 工具类
		private Gson gson = new Gson();
			
	    // 请求实体
		private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");
			
		@Autowired
		private ILoanContractSignExecuter loanContractSignExecuter;
		
		
		//@Test
		public void process() {
			saveLoanContractSign();
			saveLoanContractBankAcc();
			createLoanContract();
			signLoanContract();
		}
	
		
		
		/**
		 * 保存合同签约信息
		 */
		@Test
		public void saveLoanContractSign() {
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setIp("218.55.55.5");
			reqLoanContractSignVo.setServiceName("流");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setContractTrem(24);	
			BigDecimal lmt= new BigDecimal("30000");
			reqLoanContractSignVo.setContractLmt(lmt);
			reqLoanContractSignVo.setId(1453812L);
			reqLoanContractSignVo.setLoanNo("20170911D0E7A1");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.saveLoanContractSign(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
	
 		/**
		 * 保存合同银行账号信息
		 */
		@Test
		public void saveLoanContractBankAcc(){
			
			
	/*		String json="\"accLmt\":20000,\"accTerm\":12,\"applyBankBranch\":\"dfas\",\"applyBankCardNo\":\"6228480402564890018\",\"applyBankName\":\"平安银行\",\"applyBankNameId\":137,\"bankPhone\":\"18844445555\",\"channelCd\":\"00001\",\"contractLmt\":20000,\"contractTrem\":12,\"id\":3387,\"ip\":\"10.8.30.71\",\"loanNo\":\"20170522153403799718\",\"name\":\"遍历测一\",\"owningBranchId\":\"6913\",\"page\":0,\"pageNum\":0,\"pageSize\":0,\"productCd\":\"00001\",\"pversion\":2,\"rows\":0,\"rtfNodeState\":\"HTQY-ASSIGN\",\"serviceCode\":\"kefu001\",\"serviceName\":\"客服一\",\"sysCode\":\"cfs\",\"version\":7";
			
			ReqLoanContractSignVo req=JsonUtils.decode(json, ReqLoanContractSignVo.class);*/
			
			
			
			//System.err.println(bn.length());
			BigDecimal lmt= new BigDecimal("10000");
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setPostCode("0001");
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setContractLmt(lmt);
			reqLoanContractSignVo.setContractTrem(24);
			reqLoanContractSignVo.setApplyBankBranch("6666666666661");
			reqLoanContractSignVo.setApplyBankName("恒丰银行");
			reqLoanContractSignVo.setApplyBankCardNo("6228480402564890018");
			reqLoanContractSignVo.setApplyBankNameId(13);
			reqLoanContractSignVo.setContractTypeCode("0");
			reqLoanContractSignVo.setLoanNo("20170911565CCB");
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setId(1453813L);//544
			reqLoanContractSignVo.setBankPhone("18701705671");
			reqLoanContractSignVo.setVersion(1L);
			reqLoanContractSignVo.setPversion(11L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.saveLoanContractBankAcc(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
	
		
 		/**
		 * 生成合同
		 */
		@Test
		public void createLoanContract(){
			reqLoanContractSignVo.setServiceCode("zhaocm");
			
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setLoanNo("201707119184B6");
			reqLoanContractSignVo.setId(1292122L);;
			//reqLoanContractSignVo.setId(897L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.createLoanContract(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
	
		/**
	 * 上一步
	 */
	//@Test
	public void returnLastStep(){
		reqLoanContractSignVo.setServiceCode("00219303");
		reqLoanContractSignVo.setServiceName("朱运");
		reqLoanContractSignVo.setIp("10.8.30.31");
		//System.err.println(String.class.isAssignableFrom(null)+"*******************************");
		reqLoanContractSignVo.setId(831L);
		//reqLoanContractSignVo.setLoanNo("20170302100000005402");

		Response<ResLoanContractSignVo> response = loanContractSignExecuter.returnLastStep(reqLoanContractSignVo);
		System.err.println(response.getRepCode()+"|"+response.getRepMsg());
	}

 
	
	
 		/**
		 * 合同签订
		 * 
		 */
		@Test
		public void signLoanContract(){
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setServiceName("朱运");
			reqLoanContractSignVo.setIp("10.8.30.31");
			reqLoanContractSignVo.setLoanNo("20170519090452387445");
			reqLoanContractSignVo.setId(3392L);;
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.signLoanContract(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		/**
		 * 合同拒绝
		 */
		@Test
		public void refuseLoan(){
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setServiceName("WFEAWEF");
			reqLoanContractSignVo.setIp("231.3.12.9");
			reqLoanContractSignVo.setOprateLevel("3");
			reqLoanContractSignVo.setLoanNo("20170713C45A28");
			reqLoanContractSignVo.setId(1292713L);
			reqLoanContractSignVo.setFirstLevleReasons("提供虚假资料/疑欺诈申请");
			reqLoanContractSignVo.setFirstLevleReasonsCode("RJ00047");
			reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
			reqLoanContractSignVo.setTwoLevleReasonsCode("RJ0004700015");
			reqLoanContractSignVo.setRemark("测试虚假银行流水");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.refuseLoan(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}

		
		
 		/**
		 * 合同取消
		 */
		//@Test
		public void cancelLoan(){
			reqLoanContractSignVo.setServiceCode("zhuyn001");
			reqLoanContractSignVo.setServiceName("刘凡");
			reqLoanContractSignVo.setId(624L);
			reqLoanContractSignVo.setOprateLevel("2");
			reqLoanContractSignVo.setIp("21.564.45.4");
			reqLoanContractSignVo.setLoanNo("20170410151435051863");
			reqLoanContractSignVo.setFirstLevleReasons("身份信息");
			reqLoanContractSignVo.setFirstLevleReasonsCode("0005");
			reqLoanContractSignVo.setTwoLevleReasons("需补充二代身份证");
			reqLoanContractSignVo.setTwoLevleReasonsCode("00015");
			reqLoanContractSignVo.setRemark("测试取消");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.cancelLoan(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		 
		
		
		/**
		 * 合同签约待办
		 */
		@Test
		public void undoneContractSign(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			PageResponse<ResLoanContractSignVo> response = loanContractSignExecuter.undoneContractSignListBy(reqLoanContractSignVo);
			System.err.println(	gson.toJson(response.getRecords()));
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		
		
		/**
		 * 合同签约完成
		 */
		@Test
		public void doneContractSignListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setSignEndDate("2017-05-08");
			reqLoanContractSignVo.setSignEndDate2("2017-05-08");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.doneContractSignListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());	
		}
	
		
	/*	
		*//**
		 * 合同签约待办
		 */
		//@Test
		public void undoneContractSignListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.undoneContractSignListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		
		
	/*	*//**
		 * 合同签约办理页面
		 */
		@Test
		public void findSignInfo(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setId(1292712L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.findSignInfo(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		@Test
		public void returnBoxChoiceTask(){
			reqLoanContractSignVo.setServiceCode("zhuyn001");
			reqLoanContractSignVo.setServiceName("刘凡");
			reqLoanContractSignVo.setIp("11");
			reqLoanContractSignVo.setTaskName("合同签订");
			reqLoanContractSignVo.setId(3376L);
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.returnBoxChoiceTask(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		/**
		 * 合同签约页面信息查询
		 */
		//@Test
		public void findConfirmInfo(){
			reqLoanContractSignVo.setId(95L);
			reqLoanContractSignVo.setServiceCode("005555");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.findSignInfo(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
	}
		
		/**
		 * 查询合同URL 列表
		 */
		@Test
		public void getContrctListByTempId(){
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setLoanNo("20170302100000005409");
			ResListVO<ResBMSContractTemplateVO> response = loanContractSignExecuter.getContrctListByTempId(reqLoanContractSignVo);
			response.getCollections();
			for (ResBMSContractTemplateVO turl : response.getCollections()) {
				System.err.println("******************************"+turl.getTemplateUrl());
				
			}
			
		}
		/**
		 * 查询产品 列表
		 */
		@Test
		public void getContrctInfo(){
			reqLoanContractSignVo.setId(3400L);
			Response<ResLoanContractInfoVo> response = loanContractSignExecuter.findLoanProdcut(reqLoanContractSignVo);
			
				System.err.println("******************************"+gson.toJson(response));
				
			
			
		}
		/**
		 * 查询还款计划列表
		 */
		@Test
		public void getRepaymentInfo(){
			reqLoanContractSignVo.setId(3400L);
			ResListVO<ResLoanContractInfoVo> response = loanContractSignExecuter.getPayPlanList(reqLoanContractSignVo);
			
				System.err.println("******************************"+gson.toJson(response));
				
			
			
		}
		/**
		 * 校验产品是否可用
		 */
		//@Test
		public void valiProductIsDisabled(){
			reqLoanContractSignVo.setOwningBranchId("12827295");
			reqLoanContractSignVo.setChannelCd("00001");
			reqLoanContractSignVo.setProductCd("00013");
			BigDecimal lmt= new BigDecimal("20000");
			reqLoanContractSignVo.setContractLmt(lmt);
			reqLoanContractSignVo.setContractTrem(36);
			Response<String> response = loanContractSignExecuter.valiProductIsDisabled(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		
		
		/**
		 * 改变处理人
		 */
		//@Test
		public void changeOprateUser(){
			
			/*taskService.changeTaskAssigneeByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId())
			, reqLoanContractSignVo.getServiceCode());*/
			reqLoanContractSignVo.setId(554L);
			reqLoanContractSignVo.setServiceCode("changeUser");
			Response<String> response = loanContractSignExecuter.valiProductIsDisabled(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
		public static void main(String[] args) {
			String str="111111111111111111111111111111111111111111";
			if(str.length()>800){
				str=str.substring(800);	;
			}
			System.err.println();
		}
		
		@Test
		public void testQueryContractSignToDoCount(){
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setServiceName("aaa");
			reqLoanContractSignVo.setIp("127.0.0.1");
			
			Response<Object> response = loanContractSignExecuter.queryContractSignToDoCount(reqLoanContractSignVo);
			
			System.out.println(gson.toJson(response));
		}
		
		
		/**
		 * 
		 */
		@Test
		public void unclaimedContractSignListBy(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.unclaimedContractSignListBy(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());	
		}
		
		
		@Test
		public void claimedContractSign(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219304");
			reqLoanContractSignVo.setServiceName("花景春");
			reqLoanContractSignVo.setLoanNo("20170711960661");
			reqLoanContractSignVo.setId(1292124L);
			reqLoanContractSignVo.setIp("10.8.30.31");
			Response<ResLoanContractSignVo> response = loanContractSignExecuter.claimedContractSign(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
}
