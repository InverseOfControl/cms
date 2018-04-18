package com.ymkj.cms.biz.test.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IReconsiderationLoanExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanReviewVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqQueryReviewMessageCountVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReconsiderationLoanSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdReviewVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdateReviewReadStatusVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReconsiderationLoanSearchVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IReconsiderationLoanTest</p>
 * <p>Description:复议接口测试类</p>
 * @uthor YM10159
 * @date 2017年3月3日 下午1:43:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class IReconsiderationLoanTest {
	
	private Gson gson = new Gson();

	// 请求实体
	private ReqReconsiderationLoanSearchVO reqReconLoanSerVo = new ReqReconsiderationLoanSearchVO();
	
	@Autowired
	private IReconsiderationLoanExecuter iReconsiderationLoanExecuter;
	
	@Test
	public void insert() {
		ReqLoanReviewVo ReqLoanReviewVo = new ReqLoanReviewVo("bms");
		
		// 调用 soa 接口
		Response<Object> response = iReconsiderationLoanExecuter.insert(ReqLoanReviewVo);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void listPage() {
		//可选参数
		/*reqReconLoanSerVo.setLoanNo("");
		reqReconLoanSerVo.setName("王二小");
		reqReconLoanSerVo.setIdNo("");*/
		//必填参数
		reqReconLoanSerVo.setAgencyOrComplete("0");//代办任务和已完成任务标识
		reqReconLoanSerVo.setSubmittedOrUnsubmitted("0");//已提交任务和未提交任务标识
		reqReconLoanSerVo.setServiceCode("00219303");
		reqReconLoanSerVo.setServiceName("1000");
		reqReconLoanSerVo.setIp("127.0.0.1");
		reqReconLoanSerVo.setPageNum(1);
		reqReconLoanSerVo.setPageSize(10);
		
		// 调用 soa 接口
		PageResponse<ResReconsiderationLoanSearchVO> response = iReconsiderationLoanExecuter.search(reqReconLoanSerVo);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryMessageCount() {
		ReqQueryReviewMessageCountVO reqQueryReviewMessageCountVO = new ReqQueryReviewMessageCountVO("bms");
		
		reqQueryReviewMessageCountVO.setMessageFlag("1");
		reqQueryReviewMessageCountVO.setUserCode("ldadmin");
		
		Response<Object> response = iReconsiderationLoanExecuter.queryMessageCount(reqQueryReviewMessageCountVO);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void updateStatus() {
		ReqLoanReviewVo ReqLoanReviewVo = new ReqLoanReviewVo();
		
		ReqLoanReviewVo.setSysCode("bms");
		ReqLoanReviewVo.setLoan_no("20170908F36760");
		ReqLoanReviewVo.setStatus(2);
		
		Response<Object> response = iReconsiderationLoanExecuter.updateStatus(ReqLoanReviewVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void insertReviewReason() {
		ReqLoanReviewVo ReqLoanReviewVo = new ReqLoanReviewVo();
		
		ReqLoanReviewVo.setSysCode("bms");
		ReqLoanReviewVo.setLoan_no("20170315134718664199");
		ReqLoanReviewVo.setReview_remark("");
		ReqLoanReviewVo.setReview_reason("原因");
		
		Response<Object> response = iReconsiderationLoanExecuter.insertReviewReason(ReqLoanReviewVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void updateIsReadStatus() {
		ReqUpdateReviewReadStatusVO ReqLoanReviewVo = new ReqUpdateReviewReadStatusVO();
		List<String> list = new ArrayList<>();
		
		list.add("20170315134921359096");
		
		ReqLoanReviewVo.setSysCode("cfs");
		ReqLoanReviewVo.setLoanNoList(list);
		ReqLoanReviewVo.setServiceCode("1000");
		ReqLoanReviewVo.setServiceName("1000");
		ReqLoanReviewVo.setIp("127.0.0.1");
		
		Response<Object> response = iReconsiderationLoanExecuter.updateIsReadStatus(ReqLoanReviewVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	@Test
	public void updateOrSaveReviewStatus(){
		ReqUpdReviewVO reqUpdateReviewReadStatusVO=new ReqUpdReviewVO();
		reqUpdateReviewReadStatusVO.setFlag(2);
		reqUpdateReviewReadStatusVO.setVersion("1");
		reqUpdateReviewReadStatusVO.setSysCode("ams");
		reqUpdateReviewReadStatusVO.setLoanNo("20170710F1DBD1");
		reqUpdateReviewReadStatusVO.setModifierId("zhouwen");;
		reqUpdateReviewReadStatusVO.setModifierDate(new Date());
		//reqUpdateReviewReadStatusVO.setNewRejectFirstReasonCode("RJ00041");
		//reqUpdateReviewReadStatusVO.setNewRejectFirstReason("贷款用途不符");
		reqUpdateReviewReadStatusVO.setReviewResult(1);
		Response<Object> response=iReconsiderationLoanExecuter.updateOrSaveReviewStatus(reqUpdateReviewReadStatusVO);
		System.out.println("------------------------------------------------------------结果: "+gson.toJson(response));
	}
	@Test
	public void search(){
		String json="{'agencyOrComplete':'0','ip':'10.8.30.39','pageNum':1,'pageSize':10,'serviceCode':'zhouwen','serviceName':'周文','sysCode':'ams'}";
		ReqReconsiderationLoanSearchVO reqReconsiderationLoanSearchVO=gson.fromJson(json,ReqReconsiderationLoanSearchVO.class);
		PageResponse<ResReconsiderationLoanSearchVO> page=iReconsiderationLoanExecuter.xsSearch(reqReconsiderationLoanSearchVO);
		System.out.println(gson.toJson(page));
	}
}
