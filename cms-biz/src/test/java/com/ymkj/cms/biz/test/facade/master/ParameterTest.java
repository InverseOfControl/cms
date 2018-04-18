package com.ymkj.cms.biz.test.facade.master;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSTmParameterExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ParameterTest {
	// json 工具类
   private Gson gson = new Gson();
   
   @Autowired
   private IBMSTmParameterExecuter iBMSTmParameterExecuter;
   
   @Test
   public void findTmParameterByCode(){
	/*  ReqBMSTmParameterVO reqDemoVO=new ReqBMSTmParameterVO();
	   reqDemoVO.setServiceCode("zhouwen");
	   reqDemoVO.setLoanNo("20170527115609735592");
	   reqDemoVO.setCode("RELOAN");
	   reqDemoVO.setSysCode("ams");*/
	   String json="{'code':'NEW','page':0,'pageNum':0,'pageSize':0,'rows':0,'serviceCode':'zhouwen','sysCode':'ams','loanNo':'20170527115609735592'}";
	   //{"code":"RELOAN","loanNo":"20170527115609735592","page":0,"pageNum":0,"pageSize":0,"rows":0,"serviceCode":"zhouwen","sysCode":"ams"
	      ReqBMSTmParameterVO reqDemoVO=gson.fromJson(json, ReqBMSTmParameterVO.class);
	   
	   ResListVO<ResBMSTmParameterVO> res= iBMSTmParameterExecuter.findTmParameterByCode(reqDemoVO); 
	   System.out.println(gson.toJson(res));
   }
   @Test
   public void ceshi(){
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	        String str1 = "2017-01";
	        String str2 = "2016-09";
	        Calendar bef = Calendar.getInstance();
	        Calendar aft = Calendar.getInstance();
	        try {
				  bef.setTime(sdf.parse(str1));
				  aft.setTime(sdf.parse(str2));
			        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
			        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
			        System.out.println(Math.abs(month + result));   
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    
   }
   
}
