package com.ymkj.cms.biz.test.facade;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.IFirstAdultExecuter;
import com.ymkj.cms.biz.api.service.audit.first.ISplitContractFirstAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqChcekVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsPlupdateStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSCheckVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class FirstAuditTest {
	//JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private IFirstAdultExecuter firstAdultExecuter;

	@Autowired
	private ISplitContractFirstAuditExecuter iSplitContractFirstAuditExecuter;
	@Test
	public void findById() {
		//{"caseType":"2","flag":"2","pageNum":1,"pageSize":10,"serviceCode":"Anne","sysCode":"ams"} 
		String json ="{'caseType':'1','flag':'1','pageNum':1,'pageSize':10,'serviceCode':'dlrb','sysCode':'ams','fieldSort':'submitAuditDate','rulesSort':1}";
		//"caseType":"3","flag":"1","pageNum":1,"pageSize":3,"serviceCode":"dongmingzhi","sysCode":"ams"
		//"auditPersonCode":"rongzl","offEndDate":"2017-06-01 23:59:59","offStartDate":"2017-06-01 00:00:00","operatorCode":"rongzl","operatorIP":"127.0.0.1","pageNum":1,"pageSize":10,"reqFlag":"2","sysCode":"ams"
		ReqAuditVo reqFirstAuditVo=gson.fromJson(json, ReqAuditVo.class);
		// 调用 soa 接口
		PageResponse<ResBMSAuditVo> a = firstAdultExecuter.listPage(reqFirstAuditVo);
		System.out.println("-------------结果: "+gson.toJson(a));

		// 返回结果处理

	}

	@Test
	public void automaticDispatchList(){
		ReqAutomaticDispatchVO reqAutomaticDispatchVO = new ReqAutomaticDispatchVO();
		reqAutomaticDispatchVO.setFlag("1");
		reqAutomaticDispatchVO.setSysCode("ams");
		Response<ResBMSAutomaticDispatchVO> resBMSAutomaticDispatchVO=firstAdultExecuter.automaticDispatchList(reqAutomaticDispatchVO);

	}
	@Test
	public void updateCsLoanNoState(){
		String json ="{'accDate':'2017-05-06 13:07:05','cSPersonCode':'zhouwen','checkNodeStatus':'NO_CHECK','firstLevelReasonCode':'RJ00001','loanNo':'20170503191821639807','operatorCode':'zhouwen','operatorIP':'127.0.0.1','remark':'456','rtfNodeStatus':'XSCS-REJECT','sysCode':'ams','version':2}";
		ReqCsUpdVO reqCsUpdVO=gson.fromJson(json, ReqCsUpdVO.class);
		Response<ResBMSAudiUpdVo> a=firstAdultExecuter.updateCsLoanNoState(reqCsUpdVO);
		System.out.println(a.getRepCode());
	}
	@Test
	public void queryCheckInfoByCode(){
		String json="{'loginUserCode':'huwf','pageNum':1,'pageSize':10,'sysCode':'ams'}";
		ReqChcekVO reqChcekVO=gson.fromJson(json, ReqChcekVO.class);
		PageResponse<ResBMSCheckVO> pageResponse=firstAdultExecuter.queryCheckInfoByCode(reqChcekVO);
		if(pageResponse.isSuccess()){
			System.out.println(pageResponse.getRecords().size());
		}
	}


	@Test
	public void xsProductUpd(){
		String json="{'accDate':'2017-05-05 16:07:31','accLmt':'222222222.00','accTerm':'12','loanNo':'20170428150229172847','operatorCode':'final','operatorIP':'10.8.30.69','productCd':'公积金贷','reqFlag':'2','sysCode':'ams','version':6}";
		ReqBMProductUpdVo reqBMProductUpdVo = gson.fromJson(json, ReqBMProductUpdVo.class);
		Response<Object> q=firstAdultExecuter.xsProductUpd(reqBMProductUpdVo);
		System.out.println(q);
	}
	@Test
	public void reassignmentUpd(){
		/*ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo = new ReqBMSReassignmentUpdVo();
		ReqBMSLoansAndVersionsVO reqBMSLoansAndVersionsVO = new ReqBMSLoansAndVersionsVO();
		List<ReqBMSLoansAndVersionsVO> list= new ArrayList<ReqBMSLoansAndVersionsVO>();
		reqBMSReassignmentUpdVo.setAuditPersonCode("test");
		reqBMSReassignmentUpdVo.setOperatorCode("test");
		reqBMSReassignmentUpdVo.setOperatorIP("127.0.0.1");
		reqBMSReassignmentUpdVo.setReqFlag("1");
		reqBMSReassignmentUpdVo.setSysCode("ams");

		reqBMSLoansAndVersionsVO.setLoanNo("123456");
		reqBMSLoansAndVersionsVO.setVersion("1");
		ReqBMSLoansAndVersionsVO reqBMSLoansAndVersionsVO2 = new ReqBMSLoansAndVersionsVO();
		reqBMSLoansAndVersionsVO2.setLoanNo("67890");
		reqBMSLoansAndVersionsVO2.setVersion("2");
		list.add(reqBMSLoansAndVersionsVO);
		list.add(reqBMSLoansAndVersionsVO2);
		reqBMSReassignmentUpdVo.setList(list);
		String a=gson.toJson(reqBMSReassignmentUpdVo);*/
		String json="{'auditPersonCode':'huwf','list':[{'loanNo':'20170506134602230819','oldAuditPersonCode':'','rtfNodeStatus':'CSFP-SUBMIT','version':'1'}],'operatorCode':'amstest','operatorIP':'127.0.0.1','reqFlag':'1','sysCode':'ams'}";
		ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo=gson.fromJson(json, ReqBMSReassignmentUpdVo.class);
		Response<ResBMSPlReassignMentUpdVo> a=firstAdultExecuter.reassignmentUpd(reqBMSReassignmentUpdVo);
		System.out.println(a.getData().getCheckList());
	}
	@Test
	public void adultOffTheStocks(){
		//:{"auditPersonCode":"shipengfei","offEndDate":"2017-06-03 23:59:59","offStartDate":"2017-05-29 00:00:00","operatorCode":"shipengfei","operatorIP":"127.0.0.1","pageNum":1,"pageSize":10,"reqFlag":"2","sysCode":"ams"} 
		//"auditPersonCode":"rongzl","offEndDate":"2017-06-01 23:59:59","offStartDate":"2017-06-01 00:00:00","operatorCode":"rongzl","operatorIP":"127.0.0.1","pageNum":1,"pageSize":10,"reqFlag":"2","sysCode":"ams"
		String json="{'auditPersonCode':'dlrb','offEndDate':'2017-09-04 23:59:59','offStartDate':'2017-07-04 00:00:00','operatorCode':'dlrb','operatorIP':'127.0.0.1','pageNum':1,'pageSize':20,'reqFlag':'1','sysCode':'ams','fieldSort':'applyType'} ";
		// params:{"auditPersonCode":"","shipengfei":"2017-05-31 23:59:59","offStartDate":"2017-05-31 00:00:00","operatorCode":"shipengfei","operatorIP":"127.0.0.1","pageNum":1,"pageSize":10,"reqFlag":"2","sysCode":"ams"}
		ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo=gson.fromJson(json, ReqBMSAdultOffTheStocksVo.class);
		PageResponse<ResOffTheStocksAuditVO> a=firstAdultExecuter.adultOffTheStocks(reqBMSAdultOffTheStocksVo);
		System.out.println(gson.toJson(a));
	}
	@Test
	public void reassignmentPlUpdStatus(){
		String json="{'firstLevelReasonCode':'RJ00021','list':[{'loanNo':'20170506100633307378','rtfNodeStatus':'CSFP-SUBMIT','version':'2'},{'loanNo':'20170506100631770989','rtfNodeStatus':'CSFP-SUBMIT','version':'2'}],'operatorCode':'dongmingzhi','operatorFlag':'CSFP-REJECT','operatorIP':'127.0.0.1','remark':'456456','sysCode':'ams','twoLevelReasonCode':'RJ0002100001'} ";
		ReqCsPlupdateStatusVo reqCsPlupdateStatusVo=gson.fromJson(json, ReqCsPlupdateStatusVo.class);	
		firstAdultExecuter.reassignmentPlUpdStatus(reqCsPlupdateStatusVo);
	}
	@Test
	public void firstTrialReassignmentQuery(){
		String json="{'loginPersonCode':'admin','pageNum':1,'pageSize':10,'sysCode':'ams','rulesSort':0,'fpStatus':'2'}";
		ReqBMSReassignmentVo reqBMSReassignmentVo = gson.fromJson(json, ReqBMSReassignmentVo.class);
		PageResponse<ResBMSReassignmentVo> q=iSplitContractFirstAuditExecuter.firstTrialReassignmentQuery(reqBMSReassignmentVo);
		System.out.println(q.isSuccess());
	}
	@Test
   public void updateZsLoanNoState(){
	   String json="{'allotDate':'2017-10-17 15:30:46','loanNo':'20170904B1676D','operatorCode':'系统','operatorIP':'10.8.30.79','rtfNodeStatus':'XSZS-ASSIGN','sysCode':'ams','version':3,'zSIfNewLoanNo':'0','zSPersonCode':'Anne','remark':'分派至：终审四号 '}";
	   ReqZsUpdVO reqZsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);	
	   ResBMSAudiUpdVo res= firstAdultExecuter.updateZsLoanNoState(reqZsUpdVO);
	   System.out.println(gson.toJson(res));
   }
	@Test
   public void queryCsAduditOffTheStocks(){
	   //String json="{'auditPersonCode':'shipengfei','offEndDate':'2017-06-12 23:59:59','offStartDate':'2017-05-12 00:00:00','operatorCode':'shipengfei','operatorIP':'127.0.0.1','pageNum':1,'pageSize':20,'reqFlag':'2','sysCode':'ams'} ";
		String json="{'auditPersonCode':'zhouwen','offEndDate':'2017-06-14 23:59:59','offStartDate':'2017-06-05 00:00:00','operatorCode':'zhouwen','operatorIP':'127.0.0.1','pageNum':1,'pageSize':20,'reqFlag':'2','sysCode':'ams'} ";
		ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo=gson.fromJson(json, ReqBMSAdultOffTheStocksVo.class);
		PageResponse<ResOffTheStocksAuditVO> response=iSplitContractFirstAuditExecuter.cSAduditOffTheStocks(reqBMSAdultOffTheStocksVo);
		System.out.println("-----------------------------------------------------");
		System.out.println(gson.toJson(response));
   }
	@Test
	public  void queryCsWaitForTheStocks(){
		String json="{'caseType':'2','flag':'1','pageNum':1,'pageSize':10,'serviceCode':'zhouwen','sysCode':'ams'}";
		ReqAuditVo reqFirstAuditVo=gson.fromJson(json, ReqAuditVo.class);
		PageResponse<ResBMSAuditVo> response=iSplitContractFirstAuditExecuter.cSWaitForTheStocks(reqFirstAuditVo);
		System.out.println("-------------------------------------------------------");
		System.out.println(gson.toJson(response));
	}
}
