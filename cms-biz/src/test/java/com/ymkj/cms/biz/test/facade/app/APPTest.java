package com.ymkj.cms.biz.test.facade.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.app.IAPPExecuter;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600001;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600002;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600003;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600005;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600006;
import com.ymkj.cms.biz.api.vo.response.app.Res_VO_600006;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class APPTest {

	private Gson gson = new Gson();

	@Autowired
	private IAPPExecuter iAPPExecuter;

	@Test
	public void initField() {
		Req_VO_600001 req_V0_600001 = new Req_VO_600001();
		req_V0_600001.setSysCode("app");
		req_V0_600001.setAppCurrentTime("2016-08-29 10:58:45");

		// 调用 soa 接口
		Response<Object> response = iAPPExecuter.initField(req_V0_600001);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void getProductListData() {
		Req_VO_600002 req_VO_600002 = new Req_VO_600002();

		req_VO_600002.setSysCode("app");
		req_VO_600002.setUserCode("00210552");

		// 调用 soa 接口
		Response<Object> response = iAPPExecuter.getProductListData(req_VO_600002);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void saveApplyInfo() throws IOException {
		Req_VO_600003 req_VO_600003 = new Req_VO_600003();
		HashMap<String,Object> applyInputMap = new HashMap<String,Object>();

		StringBuffer sb = new StringBuffer();
		File file = new File("d:\\applyinfo.txt");
		BufferedReader reader = null;  
		try {  
			InputStream fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis, "GBK"));  
			String tempString = null;  
			int line = 1;  
			while ((tempString = reader.readLine()) != null) {  
				sb.append(tempString);
				line++;  
			}  
			reader.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (reader != null) {  
				try {  
					reader.close();  
				} catch (IOException e1) {  
				}  
			}  
		}  
		System.out.println(sb.toString());

		Gson gson = new Gson();  
		Map<String,Object> map = gson.fromJson(sb.toString(), Map.class);
		
		Map<String,Object> applyInfoMap = (Map<String, Object>) map.get("applyInfoMap");

		req_VO_600003.setSysCode(ObjectUtils.toString(map.get("sysCode")));
		req_VO_600003.setUserCode(ObjectUtils.toString(map.get("userCode")));
		req_VO_600003.setIsZXBH(ObjectUtils.toString(map.get("isZXBH")));
		req_VO_600003.setApplyDate(ObjectUtils.toString(map.get("applyDate")));
		req_VO_600003.setApplyInfoMap(applyInfoMap);
		req_VO_600003.setAppNo(ObjectUtils.toString(map.get("appNo")));

		Response<Object> response = iAPPExecuter.saveApplyInfo(req_VO_600003);
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void checkIDCard(){
		Req_VO_600005 req_VO_600005 = new Req_VO_600005();
		req_VO_600005.setSysCode("bms");
		req_VO_600005.setUserCode("00210552");
		req_VO_600005.setIdCardNo("622201201505112412");
		req_VO_600005.setName("高宏旭11");
		req_VO_600005.setProductCd("00001");
		
		Response<Object> response = iAPPExecuter.checkIDCard(req_VO_600005);
		System.out.println("-------------结果: "+gson.toJson(response));
		
	}
	
	
	@Test
	public void findListByState(){
		Req_VO_600006 req = new Req_VO_600006();
		req.setSysCode("bms");
		req.setUserCode("00210552");
		req.setStatus("全部");
		Response<Res_VO_600006> response = iAPPExecuter.getHisApplyInputList(req);
		System.out.println("-------------结果: "+gson.toJson(response.getData()));
	}
	@Test
	public void saveA(){
		Req_VO_600005 req_VO_600005 = new Req_VO_600005();
		Map applyInfoMap = new HashMap();
		String appNo ="201752654258514";
		applyInfoMap.put("appNo", appNo);
		applyInfoMap.put("productCd", "00002");
		applyInfoMap.put("applyLmt", "10000");
		applyInfoMap.put("applyTerm", 12);
		applyInfoMap.put("name", "刘帆之");
		applyInfoMap.put("idNo","嘿嘿");
		applyInfoMap.put("creditApplication", "部署");
		applyInfoMap.put("ifPri", 1);
		applyInfoMap.put("userCode", "00210552");
		req_VO_600005.setName("刘帆之");
		req_VO_600005.setIdCardNo("430646546565465");
		req_VO_600005.setSysCode("app");
		req_VO_600005.setUserCode("00210552");
		req_VO_600005.setApplyInfoMap(applyInfoMap);
		req_VO_600005.setUserCode("00210552");
		req_VO_600005.setApplyInfoMap(applyInfoMap);
		Response<Object> response = iAPPExecuter.saveLoanBaseInfo(req_VO_600005);
		System.out.println("-------------结果: "+gson.toJson(response.getData()));
	}
	
	

}
