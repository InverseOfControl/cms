package com.ymkj.cms.biz.test.facade.sign;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractFileExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractNoticeExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class AiTeLoanContractFileExecuterTest {

	private Gson gson = new Gson();
	// 请求实体
	private RequestVo requestVo = new RequestVo();
		
	@Autowired
	private IAiTeLoanContractFileExecuter aiTeLoanContractFileExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @author lix YM10160
	 * @date 2017年4月1日下午4:36:13
	 */
	@Test
	public void testUploadFiles() {
		
//		{"borrowNo":"20170809F45A35","files":"[]","papersType":"T","sign":"51FD6BF7934D7D75BC475D79FA0501FE"}

		
		
		requestVo.setBorrowNo("20170804C4C879");
		requestVo.setFiles("[{\"fileName\":\"借款协议.pdf\",\"url\":\"https://testapi.fadada.com:8443/api//getdocs.action?app_id\\u003d400140\\u0026send_app_id\\u003dnull\\u0026v\\u003d2.0\\u0026timestamp\\u003d20170815131942\\u0026transaction_id\\u003d13181429577020170815131817538795\\u0026msg_digest\\u003dNzlFQ0YzMkE4OTYxODRERDE5QUIwMUEwOTA5NkQ3Qjc5NDRBQTVERQ\\u003d\\u003d\"}]");
		requestVo.setPapersType("T");
		Response<RequestVo> response = aiTeLoanContractFileExecuter.uploadFiles(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
		
	}
	@Test
	public void testdeleteFile() {
		requestVo.setBorrowNo("20170803C88406");
		Response<RequestVo> response = aiTeLoanContractFileExecuter.deleteFile(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
		
	}

}
