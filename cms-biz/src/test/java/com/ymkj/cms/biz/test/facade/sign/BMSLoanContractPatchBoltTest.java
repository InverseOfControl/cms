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
import com.ymkj.cms.biz.api.service.sign.ILoanContractPatchBoltExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractInfoVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanContractPatchBoltTest {
		// json 工具类
		private Gson gson = new Gson();
			
	    // 请求实体
		private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("cfs");
			
		@Autowired
		private ILoanContractPatchBoltExecuter loanContractPatchBoltExecuter;
		
		
		/**
		 * 合同签约补件列表
		 */
		@Test
		public void undoneContractSign(){
			reqLoanContractSignVo.setPageNum(1);
			reqLoanContractSignVo.setPageSize(10);
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setIfNeedPatchBolt(1);
			PageResponse<ResLoanContractSignVo> response = loanContractPatchBoltExecuter.contractPatchBoltListPage(reqLoanContractSignVo);
			System.err.println(	gson.toJson(response.getRecords()));
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
			
		}
		
		/**
		 * 更新补件状态
		 */
		@Test
		public void updatePatchBoltInSign(){
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setIfNeedPatchBolt(1);
			reqLoanContractSignVo.setLoanNo("20170803A91851");
			reqLoanContractSignVo.setId(1452554L);
			Response<ResLoanContractSignVo> response = loanContractPatchBoltExecuter.updatePatchBoltInSign(reqLoanContractSignVo);
			System.err.println(response.getRepCode()+"|"+response.getRepMsg());
		}
	
		
	
		@Test
		public void tupdatePatchBoltInSignToDoCount(){
			reqLoanContractSignVo.setServiceCode("00219303");
			reqLoanContractSignVo.setServiceName("aaa");
			reqLoanContractSignVo.setIp("127.0.0.1");
			
			Response<Object> response = loanContractPatchBoltExecuter.queryContractPatchBoltToDoCount(reqLoanContractSignVo);
			
			System.out.println(gson.toJson(response));
		}
}
