package com.ymkj.cms.biz.test.facade;

import java.text.ParseException;
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
import com.ymkj.cms.biz.api.service.audit.IQualityInspectionExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionTwoVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSApplicationPartVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSQualityInspectionVO;
import com.ymkj.cms.biz.common.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class QualityInspectionTest {
	//JSON 工具类
		private Gson gson = new Gson();	
		@Autowired
		private IQualityInspectionExecuter iQualityInspectionExecuter;
		
		//@Test
		public void queryQualityInsInfo(){
			String json="{'status':'1','startDate':'2017-03-21 10:00:00','endDate':'2017-03-21 23:59:59','pageNum':1,'pageSize':10,'list':['XSZS-PASS'],'sysCode':'ams'}";
			ReqQualityInspectionVO reqQualityInspectionVO=gson.fromJson(json, ReqQualityInspectionVO.class);
			/*List<String> list= new ArrayList<String>();
			list.add("1");
			list.add("2");
			reqQualityInspectionVO.setStatus("4");
		

			reqQualityInspectionVO.setStartDate("2017-03-01 00:00:00");		

			reqQualityInspectionVO.setEndDate("2017-03-02 00:00:00");	
			reqQualityInspectionVO.setSysCode("bms");
			reqQualityInspectionVO.setList(list);*/
			/*reqQualityInspectionVO.setStatus("1");		

			reqQualityInspectionVO.setStatus("1");	*/	
			ResBMSQualityInspectionVO resBMSQualityInspectionVO=iQualityInspectionExecuter.queryQualityInsInfo(reqQualityInspectionVO);
			if(resBMSQualityInspectionVO.isSuccess()){
				System.out.println(gson.toJson(resBMSQualityInspectionVO.getList()));
			}
		}
		//@Test
		public void queryQualityInsInfoTwo(){
			ReqQualityInspectionTwoVO ReqQualityInspectionTwoVO = new ReqQualityInspectionTwoVO();
			ReqQualityInspectionTwoVO.setSysCode("bms");
			ResBMSQualityInspectionVO resBMSQualityInspectionVO=iQualityInspectionExecuter.queryQualityInsInfoTwo(ReqQualityInspectionTwoVO);
			
			if(resBMSQualityInspectionVO.isSuccess()){
				System.out.println(gson.toJson(resBMSQualityInspectionVO.getList()));
			}
		}
		@Test
		public void getApplicationInfo(){
			ReqApplicationPartVO reqApplicationPartVO = new ReqApplicationPartVO();
			reqApplicationPartVO.setSysCode("ams");
			
			reqApplicationPartVO.setPageNum(1);
			reqApplicationPartVO.setPageSize(10);
			reqApplicationPartVO.setFlag("2");
			try {
				reqApplicationPartVO.setStartDate(DateUtil.strToDateTime("2017-09-12 00:00:00"));
				reqApplicationPartVO.setEndDate(new Date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PageResponse<ResBMSApplicationPartVO>  vo=iQualityInspectionExecuter.getApplicationInfo(reqApplicationPartVO);
			
			System.out.println(gson.toJson(vo));
		}
		@Test
		public void updateApplicationInfo(){
			String json="{'firstLevelReasons':'高风险客户','firstLevelReasonsCode':'00020','flag':'2','loanNo':'20170302100000005401','operatorCode':'amstest','operatorIP':'127.0.0.1','rtfNodeState':'XSZS-REJECT','rtfState':'XSZS','status':'APPLY','sysCode':'ams','twoLevelReasons':'信用类高风险','twoLevelReasonsCode':'0002000003','version':1}";
			ReqApplicationPartUpdVO reqApplicationPartUpdVO=gson.fromJson(json, ReqApplicationPartUpdVO.class);
			ResBMSAudiUpdVo resBMSAudiUpdVo=iQualityInspectionExecuter.updateApplicationInfo(reqApplicationPartUpdVO);
		}
}
