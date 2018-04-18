package com.ymkj.cms.biz.test.facade.audit;

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
import com.ymkj.cms.biz.api.service.audit.last.IFinalAdultExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ZsAduitExecuterTest {

	private Gson gson = new Gson();
	
	@Autowired
	private IFinalAdultExecuter finalAdultExecuter;
	
	//终审自动派单测试
	@Test
	public void zSAutomaticDispatchList(){
		ReqAutomaticDispatchVO reqVo=new ReqAutomaticDispatchVO();
		reqVo.setSysCode("ams");
		Response<ResBMSAutomaticDispatchVO> resBMSAutomaticDispatchVO=finalAdultExecuter.zSAutomaticDispatchList(reqVo);
		System.out.println("-------------查询结果: "+gson.toJson(resBMSAutomaticDispatchVO));
	}
	//终审拒绝更新接口
	@Test
	public void zSRejectUpd(){
		String json ="{'accDate':'2017-04-24 14:45:37','accLmt':'200000.00','accTime':'22','firstLevelReasonCode':'RJ00025','loanNo':'20170527115711764562','operatorCode':'final','operatorIP':'12.8.30.19','remark':'测试','rtfNodeStatus':'XSZS-RETURN','sysCode':'ams','twoLevelReasonCode':'RJ0002500025','version':'5','loanNoTopClass':'L1','zSPersonCode':'0001'}";
		ReqZsUpdVO reqzsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);
		Response<ResBMSAudiUpdVo> a=finalAdultExecuter.zSRejectUpd(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());
	}
	//终审挂起更新接口
	@Test
	public void zSHangupUpd(){
		String json ="{'accDate':'2017-04-24 14:45:37','accLmt':'200000.00','accTime':'22','firstLevelReasonCode':'HA00075','loanNo':'2017071085420F','operatorCode':'rongzl','operatorIP':'10.8.30.69','remark':'测试','rtfNodeStatus':'XSZS-HANHUP','sysCode':'ams','version':7,'loanNoTopClass':'L2'}";
		ReqZsUpdVO reqzsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);
		Response<ResBMSAudiUpdVo> a=finalAdultExecuter.zSHangupUpd(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());	
	}
	//终审提交更新接口
	@Test
	public void zSSubmitUpd(){
		//String json="{'accDate':"2017-05-19 13:43:29","accLmt":"20000.00","accTime":"12","allotDate":"2017-05-19 13:43:32","loanNo":"20170519103925960960","loanNoTopClass":"L1","operatorCode":"zs01","operatorIP":"172.16.230.146","remark":"0","rtfNodeStatus":"XSZS-SUBMIT-HIGH","sysCode":"ams","version":6,"zSIfNewLoanNo":"1","zSPersonCode":"zs01"} result:{"repCode":"300000","repMsg":"数据库操作错误","success":false}
		  String json ="{'accDate':'2017-04-24 14:45:37','accLmt':'200000.00','accTime':'22','allotDate':'2017-04-24 15:12:32','loanNo':'20170531143012145137','loanNoTopClass':'L1','operatorCode':'final','operatorIP':'12.8.30.19','remark':'测试','rtfNodeStatus':'XSZS-SUBMIT-HIGH','sysCode':'ams','version':'9','zSIfNewLoanNo':'1','apppovalPersonCode':'0001'}";
		ReqZsUpdVO reqzsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);
		Response<ResBMSAudiUpdVo> a=finalAdultExecuter.zSSubmitUpd(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());
	}
	//终审退回更新接口
	@Test
	public void zSReturnUpd(){
		String json ="{'accDate':'2017-08-09 14:45:37','accLmt':'200000.00','accTime':'22','allotDate':'2017-08-09 15:12:32','firstLevelReasonCode':'RJ0001','loanNo':'201710112413DF','operatorCode':'final','operatorIP':'12.8.30.19','remark':'测试','rtfNodeStatus':'XSZS-RTNCS','sysCode':'ams','twoLevelReasonCode':'RJ00010001','version':'8','loanNoTopClass':'L1','zSPersonCode':'0001'}";
		ReqZsUpdVO reqzsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);
		Response<ResBMSAudiUpdVo> a=finalAdultExecuter.zSReturnUpd(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());
	}
	//借款产品更新接口
	@Test
	public void zSProductUpd (){
		
		String json="{'accLmt':'2452.00','ifCreditRecode':'0','loanNo':'20170622134416480660','operatorCode':'00230435','operatorIP':'10.8.30.76','productCd':'00001','sysCode':'ams','version':5,'accTerm':3,'sysCheckLmt':1800.00,'accDate':'2017-04-24 14:45:37'}";
		ReqBMProductUpdVo reqBMProductUpdVo = gson.fromJson(json, ReqBMProductUpdVo.class);
		Response<Object> q=finalAdultExecuter.zSProductUpd(reqBMProductUpdVo);
		System.out.println(gson.toJson(q));
	}
	//批量终审改派接口
	@Test
	public void zSreassignmentUpd(){
		////String json={"auditPersonCode":"amstest","list":[{"loanNo":"20170622134416480660","oldAuditPersonCode":"rongzl","rtfNodeStatus":"XSZS-ASSIGN","version":"15"}],
		// params:{"auditPersonCode":"dengchao","list":[{"loanNo":"20170527115635887031",''"oldAuditPersonCode":"dlrb","rtfNodeStatus":"XSZS-ASSIGN","version":"6"}],"operatorCode":"final","operatorIP":"127.0.0.1","reqFlag":"2","sysCode":"ams"}
		String json="{'auditPersonCode':'amstest','list':[{'loanNo':'20170622134416480660','oldAuditPersonCode':'rongzl','rtfNodeStatus':'XSZS-ASSIGN','version':21}],'operatorCode':'zhaojianyun','operatorIP':'127.0.0.1','reqFlag':'2','sysCode':'ams'}";
		ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo=gson.fromJson(json, ReqBMSReassignmentUpdVo.class);
		Response<ResBMSPlReassignMentUpdVo> a=finalAdultExecuter.zSreassignmentUpd(reqBMSReassignmentUpdVo);
		System.out.println(gson.toJson(a));
		/*for(int i=0;i<a.getData().getResList().size();i++){
			System.out.println(a.getData().getResList().get(i).getLoanNo()+"-"+a.getData().getResList().get(i).getRepCode()+"-"+a.getData().getResList().get(i).getRepMsg());
		}*/
		
	}
	@Test
	public void zSlistPage(){
	   //"appInputFlag":"1","caseType":"2","flag":"2","loginPersonCode":"final","pageNum":1,"pageSize":10,"sysCode":"ams"
		String json="{'loginPersonCode':'zhaojianyun','pageNum':1,'pageSize':10,'sysCode':'ams','loanNo':'201710139E7434'}";
		//{"flag":"2","loginPersonCode":"Anne","pageNum":1,"pageSize":10,"sysCode":"ams","xsEndDate":"2017-05-26 23:59:59","xsStartDate":"2017-05-18 00:00:00"}
		ReqBMSReassignmentVo reqBMSReassignmentVo = gson.fromJson(json, ReqBMSReassignmentVo.class);
		List<String> caseIdentifyList = new ArrayList<String>();
	/*	caseIdentifyList.add("1");
		caseIdentifyList.add("2");
		caseIdentifyList.add("3");
		caseIdentifyList.add("5");*/
		reqBMSReassignmentVo.setCaseIdentifyList(caseIdentifyList);
		
		PageResponse<ResBMSReassignmentVo> q=finalAdultExecuter.zSlistPage(reqBMSReassignmentVo);
		System.out.println(gson.toJson(q));
	}
	@Test
	public void zSWaitForTheStocks(){
		String json ="{'caseType':'3','flag':'1','pageNum':1,'pageSize':10,'serviceCode':'shipengfei','sysCode':'ams','fieldSort':'applyType'}";
		ReqAuditVo reqFirstAuditVo=gson.fromJson(json, ReqAuditVo.class);
		// 调用 soa 接口
		PageResponse<ResBMSAuditVo> a = finalAdultExecuter.zSWaitForTheStocks(reqFirstAuditVo);
		System.out.println("---------------------------------------------------------------");
		System.out.println("-------------结果: "+gson.toJson(a));

	}
	
	@Test
	public void zSadultOffTheStocks(){
		String json="{'auditPersonCode':'Anne','offEndDate':'2017-08-22 23:59:59','offStartDate':'2017-07-22 00:00:00','operatorCode':'dlrb','operatorIP':'127.0.0.1','pageNum':1,'pageSize':20,'reqFlag':'2','sysCode':'ams'} ";
		// params:{"auditPersonCode":"","shipengfei":"2017-05-31 23:59:59","offStartDate":"2017-05-31 00:00:00","operatorCode":"shipengfei","operatorIP":"127.0.0.1","pageNum":1,"pageSize":10,"reqFlag":"2","sysCode":"ams"}
		ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo=gson.fromJson(json, ReqBMSAdultOffTheStocksVo.class);
		PageResponse<ResOffTheStocksAuditVO> a=finalAdultExecuter.zSAdultOffTheStocks(reqBMSAdultOffTheStocksVo);
		System.out.println("---------------------------------------------------------------");
		System.out.println(gson.toJson(a));
	}
	@Test
	public void xsSystemReject(){
		String json ="{'loanNo':'2017072137E08A','rtfStatus':'CSFP','rtfNodeStatus':'CSFP-REJECT','firstLevelReasonCode':'RJ00039','firstLevelReasons':'不愿透露资料/信息','twoLevelReasonCode':'RJ0003900006','twoLevelReasons':'联系人/单位工作信息','operatorCode':'系统','version':6,'sysCode':'ams','operatorIP':'127.0.0.1'}";
		ReqZsUpdVO reqzsUpdVO=gson.fromJson(json, ReqZsUpdVO.class);
		Response<Object> a=finalAdultExecuter.xsSystemReject(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());
		
	}
	
	@Test
	public void xSInfoChangeCheck(){
		ReqAuditVo reqAuditVo=new ReqAuditVo();
		reqAuditVo.setLoanNo("201709144B9786");
		reqAuditVo.setName("九月十四12");
		reqAuditVo.setIdNo("140423199003069715");
		reqAuditVo.setSysCode("BMS");
		Response<Object> response=finalAdultExecuter.xSInfoChangeCheck(reqAuditVo);
		System.out.println(response);
		
	}
	
}
