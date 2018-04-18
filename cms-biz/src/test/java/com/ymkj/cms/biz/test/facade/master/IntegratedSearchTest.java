package com.ymkj.cms.biz.test.facade.master;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IIntegratedSearchExecuter;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResIntegratedSearchVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentDetailVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryRepaymentSummaryVo;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryflowVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IntegratedSearchTest</p>
 * <p>Description:综合查询测试类</p>
 * @uthor YM10159
 * @date 2017年3月10日 下午1:43:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class IntegratedSearchTest {
	
	private Gson gson = new Gson();

	@Autowired
	private IIntegratedSearchExecuter iIntegratedSearchExecuter;
	/**
	 * <p>Description:综合查询接口</p>
	 * @uthor YM10159
	 * @date 2017年3月7日 下午3:53:13
	 * @result 已测试成功
	 */
	@Test
	public void search() {
		ReqIntegratedSearchVO reqIntegratedSearchVO = new ReqIntegratedSearchVO("cfs");
		
		//reqIntegratedSearchVO.setName("周五9");
		//reqIntegratedSearchVO.setTel("010-1234567");
		//reqIntegratedSearchVO.setPhone("15095400000");
		//reqIntegratedSearchVO.setCorpName("11");
		
		reqIntegratedSearchVO.setLoanNo("20170918D6AD8F");
		//reqIntegratedSearchVO.setIdNo("42022219900000000");
		//reqIntegratedSearchVO.setStartTime("2017-08-02");
		//reqIntegratedSearchVO.setEndTime("2017-08-10");
		
		//reqIntegratedSearchVO.setStartCreatedTime("2017-08-01");
		//reqIntegratedSearchVO.setEndCreatedTime("2017-08-14");
		//reqIntegratedSearchVO.setCaseIdentify("2");
		//reqIntegratedSearchVO.setApplyType("NEW");
		//reqIntegratedSearchVO.setContractNo("ZDB20170462086008");
		reqIntegratedSearchVO.setServiceCode("admin");
		reqIntegratedSearchVO.setServiceName("管理员");
		reqIntegratedSearchVO.setIp("127.0.0.1");
		reqIntegratedSearchVO.setPageNum(1);
		reqIntegratedSearchVO.setPageSize(10);
		//reqIntegratedSearchVO.setFieldSort("corpName");
		//reqIntegratedSearchVO.setApplyType("NEW|RELOAN");
		//reqIntegratedSearchVO.setCaseIdentify("2|0");
		/*reqIntegratedSearchVO.*/
		//reqIntegratedSearchVO.setCorpName("上海");
		//reqIntegratedSearchVO.setPhone("15836459974");
		//reqIntegratedSearchVO.setTel("214521");
		//reqIntegratedSearchVO.setTeLoanNo("2017070768175F,20170525161434051311");
		//String teLoanNo[]={"10000014"};
		//reqIntegratedSearchVO.setTeLoanNo(teLoanNo);
		/// 调用 soa 接口
		PageResponse<ResIntegratedSearchVO> response = iIntegratedSearchExecuter.search(reqIntegratedSearchVO);
		
		System.out.println("------------------------------------------------结果: "+gson.toJson(response));
	}
	
	@Test 
	public void queryLoan() {
		ReqQueryLoanVo reqQueryLoanVo = new ReqQueryLoanVo();
		
		reqQueryLoanVo.setUserCode("zhaocm");
		reqQueryLoanVo.setLoanNo("20170707E882FC");
		
		Response<ResQueryLoanVo> response = iIntegratedSearchExecuter.queryLoan(reqQueryLoanVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryRepaymentSummary() {
		ReqQueryRepaymentSummaryVo reqQueryRepaymentSummaryVo = new ReqQueryRepaymentSummaryVo();
		
		reqQueryRepaymentSummaryVo.setUserCode("zhaocm");
		reqQueryRepaymentSummaryVo.setLoanNo("222");
		
		Response<ResQueryRepaymentSummaryVo> response = iIntegratedSearchExecuter.queryRepaymentSummary(reqQueryRepaymentSummaryVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryRepaymentDetailAll() {
		ReqQueryRepaymentDetailVo reqQueryRepaymentDetailVo = new ReqQueryRepaymentDetailVo();
		
		reqQueryRepaymentDetailVo.setUserCode("zhaocm");
		reqQueryRepaymentDetailVo.setLoanNo("222");
		
		Response<List<ResQueryRepaymentDetailVo>> response = iIntegratedSearchExecuter.queryRepaymentDetailAll(reqQueryRepaymentDetailVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryflow() {
		ReqQueryflowVo reqQueryflowVo = new ReqQueryflowVo();
		
		reqQueryflowVo.setUserCode("zhaocm");
		reqQueryflowVo.setLoanNo("222");
		
		Response<List<ResQueryflowVo>> response = iIntegratedSearchExecuter.queryflow(reqQueryflowVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryLoanLog() {
		ReqQueryLoanLogVO ReqQueryLoanLogVO = new ReqQueryLoanLogVO();
		ReqQueryLoanLogVO.setSysCode("bms");
		ReqQueryLoanLogVO.setLoanNo("201710173B3DE7");
		ReqQueryLoanLogVO.setServiceCode("00219303");
		ReqQueryLoanLogVO.setServiceName("花锦春");
		ReqQueryLoanLogVO.setIp("127.0.0.1");
		
		Response<List<ResBMSQueryLoanLogVO>> response = iIntegratedSearchExecuter.queryLoanLog(ReqQueryLoanLogVO);
		System.out.println("---------------------------------------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryLoanDetail() {
		ReqQueryLoanDetailVo reqQueryLoanDetailVo = new ReqQueryLoanDetailVo();
		
		reqQueryLoanDetailVo.setSysCode("bms");
		reqQueryLoanDetailVo.setLoanNo("20170425193404480251");
		reqQueryLoanDetailVo.setUserCode("1234");
		
		Response<ResQueryLoanDetailVo> response = iIntegratedSearchExecuter.queryLoanDetail(reqQueryLoanDetailVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	

}
