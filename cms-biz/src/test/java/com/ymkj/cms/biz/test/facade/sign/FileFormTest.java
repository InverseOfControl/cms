package com.ymkj.cms.biz.test.facade.sign;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IFileFormExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqArchivesVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResArchivesVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResntrySearchVO;
import com.ymkj.cms.biz.common.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class FileFormTest {
	private Gson gson = new Gson();

	@Autowired
	private IFileFormExecuter ifileFormExecuter;
	
	@Test
	public void pagelistTest(){
		String json="{'pageSize':30,'pageNum':1,'sysCode':'cfs','serviceCode':'00219303','serviceName':'ysrils','ip':'127.0.0.1'}";
		ReqFileFormSearchVO reqFileFormVO=gson.fromJson(json, ReqFileFormSearchVO.class);
		/*try {
			reqFileFormVO.setLendingTimeStart(DateUtil.strToDate("2017-01-01",null));
			reqFileFormVO.setLendingTimeEnd(DateUtil.strToDate("2017-08-01",null));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		 PageResponse<ResFileFormSearchVO> page=ifileFormExecuter.listPage(reqFileFormVO);
		 System.out.println("-----------------------------------------"+gson.toJson(page));
	}
	@Test
	public void saveArchives(){
      String json="{'sysCode':'ams','loanNo':'20170421141729092847','approvalNum':2,'loanFormNum':2,'bankCardCopyNum':1,'loanSignRecordTableNum':2,"
      		+ "'personalCreditReportNum':1,'incomeProve':'B01,B02,B03','incomeProveNum':3,'addressProve':'D01,D02,D03','addressProveNum':3,'bussinessProve':'E04,E05,E06',"
      		+ "'bussinessProveNum':8,'propertyProve':'F02,F03','propertyProveNum':6,'othersProve':'G01','othersProveNum':5,'remark':'测试录入','remarkNum':2,"
      		+ "'jobProve':'C01,C02,C03','jobProveNum':'3','identityProve':'A01,A02,A03','identityProveNum':4}";
      ReqArchivesVO reqArchivesVo=gson.fromJson(json, ReqArchivesVO.class);
      Response<ResArchivesVO> res=ifileFormExecuter.saveArchives(reqArchivesVo);
      System.out.println("----------------------------------------------"+res.getRepCode()+res.getRepMsg());
	}
	@Test
	public void updateArchives(){
		//{"addressProve":"D02","addressProveNum":10,"approvalNum":1,"bankCardCopyNum":3,"bussinessProve":"E03,E04","bussinessProveNum":11,"identityProve":"A01,A02,A03","identityProveNum":7,"incomeProve":"B02","incomeProveNum":8,"jobProve":"C03","jobProveNum":9,"loanFormNum":2,"loanNo":"20170519140225948923","loanSignRecordTableNum":4,"othersProve":"G02","othersProveNum":13,"personalCreditReportNum":5,"propertyProve":"F03","propertyProveNum":"12","remark":"灌灌灌灌","sysCode":"cfs"}
		 String json="{'sysCode':'ams','loanNo':'20170526112802582904','approvalNum':66,'loanFormNum':2,'bankCardCopyNum':1,'loanSignRecordTableNum':2,"
		      		+ "'personalCreditReportNum':1,'incomeProve':'B01,B02,B03','incomeProveNum':3,'addressProve':'D01,D02,D03','addressProveNum':3,'bussinessProve':'E04,E05,E06',"
		      		+ "'bussinessProveNum':8,'propertyProve':'F02,F03','propertyProveNum':6666,'othersProve':'G01','othersProveNum':5,'remark':'测试录入','remarkNum':2,"
		      		+ "'jobProve':'C01,C02,C03','jobProveNum':'3','identityProve':'A01,A02,A03','identityProveNum':4}";
		 
		 
		 
		 //
		 ReqArchivesVO reqArchivesVo=gson.fromJson(json, ReqArchivesVO.class);
	      Response<ResArchivesVO> res=ifileFormExecuter.updateArchives(reqArchivesVo);
	      System.out.println("----------------------------------------------"+res.getRepCode()+res.getRepMsg());
	}
	@Test
	public void queryArchives(){
		String json="{'sysCode':'bms','loanNo':'20170417203423864421'}";
		  ReqArchivesVO reqArchivesVo=gson.fromJson(json, ReqArchivesVO.class);
		  Response<ResntrySearchVO> vo=ifileFormExecuter.queryArchives(reqArchivesVo);
		  System.out.println("-----------------------------------------------"+gson.toJson(vo));
	}

}
