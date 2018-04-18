package com.ymkj.cms.biz.test.facade.sign;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanTest {
	// json 工具类
		private Gson gson = new Gson();
			
	    // 请求实体
		private ReqLoanVo reqLoanVo = new ReqLoanVo("bms");
			
		@Autowired
		private ILoanExecuter loanExecuter;
		
		/*"id":81,"loanNo":"20170303173237383000","serviceCode":"niexl"*/
		
		/**
		 * 放款审核
		 */
		//@Test
		public void passAuditLoan() {
 			reqLoanVo.setLoanNo("20170410154408453543");
 			reqLoanVo.setId(626l);
 			reqLoanVo.setServiceCode("niexl");
 			reqLoanVo.setServiceName("聂学林");
 			reqLoanVo.setIp("213.21.33.12");
 			Response<ResLoanVo> res=loanExecuter.passAuditLoan(reqLoanVo);
 			System.err.println(res.getRepCode()+"|"+res.getRepMsg());
		}
		
		
		/**
		 * 批量更新借款状态
		 */
 		//@Test
		public void batchloanAudit() {	
 			//Response<ResLoanVo> res=loanExecuter.bacthPassAudit(reqLoanVo);
 			//System.err.println(res.getRepCode()+"|"+res.getRepMsg());
			List<ReqLoanVo> lisy =new ArrayList<ReqLoanVo>();
 			ReqLoanVo reqLoanVo2 =new ReqLoanVo("bms");
 			reqLoanVo2.setId(527l);
 			reqLoanVo2.setLoanNo("20170401110714629510");//150000039,150000197
 			reqLoanVo2.setState("0020");
 			//reqLoanVo2.setVersion();
 			ReqLoanVo reqLoanVo5 =new ReqLoanVo("bms");
 			reqLoanVo2.setId(487l);
 			reqLoanVo5.setLoanNo("20170331092404227982");
 			reqLoanVo2.setState("0020");
 			
 			lisy.add(reqLoanVo2);
 			lisy.add(reqLoanVo5);
 			
 			reqLoanVo.setLoanList(lisy);
 			reqLoanVo.setId(48L);
 			reqLoanVo.setState("0020");
 			reqLoanVo.setServiceCode("niexl");
 			Response<ResLoanVo> res=loanExecuter.bacthPassAudit(reqLoanVo);
		}
		
	/*	
 		*//**
		 * 放款确认
		 */
/*		@Test
		public void grantLoan() {
 			//reqLoanVo.s
			List<ReqLoanVo> lisy =new ArrayList<ReqLoanVo>();
 			ReqLoanVo reqLoanVo2 =new ReqLoanVo("11111111");
 			reqLoanVo2.setLoanNo("20170302100000005402");
 			reqLoanVo2.setServiceCode("zhaocm");
 			//reqLoanVo2.setVersion();
 			ReqLoanVo reqLoanVo5 =new ReqLoanVo("11111111");
 			reqLoanVo5.setLoanNo("20170302100000005402");
 			ReqLoanVo reqLoanVo3 =new ReqLoanVo("11111111");
 			reqLoanVo3.setLoanNo("333");
 			lisy.add(reqLoanVo2);
 			lisy.add(reqLoanVo5);
 			lisy.add(reqLoanVo3);	
 			
 			reqLoanVo.setServiceCode("zhaocm");
 			reqLoanVo.setRtfState(RtfState.CSFP.getValue());
 			reqLoanVo.setStatus(LoanStatus.PASS.getValue());
 		//	reqLoanVo.setLoanNo("20151221190000062103");
 			reqLoanVo.setLoanList(lisy);
 			reqLoanVo.setServiceCode("zhaocm");
 			Response<ResLoanVo> es=loanExecuter.grantLoan(reqLoanVo);
		
	}*/
		
	/*	@Test
		public void grantLoan() {
			//reqLoanVo.setId(48L);
			reqLoanVo.setPageNum(1);
			reqLoanVo.setPageSize(10);
			reqLoanVo.setNodeName("保存信息");
 			PageResponse<ResLoanVo> res=loanExecuter.listPage(reqLoanVo);
 			System.err.println(res.getRepCode()+"|"+res.getRepMsg());
 			 List<ResLoanVo> l =res.getRecords();
 			 for (ResLoanVo resLoanVo : l) {
 			System.err.println(resLoanVo.getProductName() +"|"+resLoanVo.getContractLmt()+"|"+resLoanVo.getTaskId()+resLoanVo.getContractBranch());	
			}
		}*/
		
		@Test
		public void loanAudit() {
 			//reqLoanVo.s
 			reqLoanVo.setRtfState(EnumConstants.RtfState.FKQR.getValue());
 			reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHSUBMIT.getValue());
 			//reqLoanVo.setLoanNo("20151221190000062103");
 			reqLoanVo.setId(48L);
 			reqLoanVo.setState("0021");
 			reqLoanVo.setVersion("1");
 			reqLoanVo.setPageNum(1);
 			reqLoanVo.setPageSize(10);
 			PageResponse<ResLoanVo> res=loanExecuter.doneListPage(reqLoanVo);
 			System.err.println(res.getRepCode()+"|"+res.getRepMsg());
		}
	
		//@Test
/* 		public void loanAuditList() {
 			reqLoanVo.setTaskName("放款审核");
 			reqLoanVo.setPageNum(1);
 			reqLoanVo.setPageSize(10);
 			PageResponse<ResLoanVo> res=loanExecuter.listPage(reqLoanVo);
 			System.err.println(res.getRepCode()+"|"+res.getRepMsg());
		
		}*/
		
		

		
}

