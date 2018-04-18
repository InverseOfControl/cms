package com.ymkj.cms.biz.test.facade.audit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.qualitytest.IQualityInspectionSheetExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class QualityInspectionSheetTest {
	private Gson gson = new Gson();
	
	@Autowired
	private IQualityInspectionSheetExecuter iQualityInspectionSheetExecuter;
	
	//批量导入查询
	@Test
	public void zSAutomaticDispatchList(){
		List<ReqLoanNumberVO> vos=new ArrayList<ReqLoanNumberVO>();
		ReqLoanNumberVO a=new ReqLoanNumberVO();
		ReqLoanNumberVO b=new ReqLoanNumberVO();
		ReqLoanNumberVO c=new ReqLoanNumberVO();
		a.setLoanNo("20170525161420279386");
		a.setName("清空0");
		a.setIdNo("140700197205124180");
		a.setSysCode("ams");
		b.setLoanNo("20170525161423675217");
		b.setName("清空1");
		b.setIdNo("130281197403023208");
		b.setSysCode("ams");
		c.setLoanNo("20170525161425283753");
		c.setName("清空2");
		c.setIdNo("522229197709059544");
		c.setSysCode("ams");
		vos.add(a);
		vos.add(b);
		vos.add(c);
		Response<List<ResQualityInspectionSheetResultVO>> v=iQualityInspectionSheetExecuter.findByLoanNoAndNameAndIdNos(vos);
		
		System.out.println("-------------查询结果: "+gson.toJson(v));
	}
}
