package com.ymkj.cms.biz.test.facade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IReturnedLoanBoxExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxMessageVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReturnedLoanBoxSearchVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReturnedLoanBoxTest</p>
 * <p>Description:退件箱测试类</p>
 * @uthor YM10159
 * @date 2017年3月7日 下午2:43:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ReturnedLoanBoxTest {

	private Gson gson = new Gson();

	@Autowired
	private IReturnedLoanBoxExecuter iReturnedLoanBoxExecuter;

	/**
	 * <p>Description:退件箱查询接口</p>
	 * @uthor YM10159
	 * @date 2017年3月7日 下午3:53:13
	 */
	@Test
	public void search() {
		ReqReturnedLoanBoxSearchVO reqReturnedLoanBoxSearchVO = new ReqReturnedLoanBoxSearchVO("cfs");
		
		//可选参数
		/*reqReturnedLoanBoxSearchVO.setLoanNo("20170511154801793528");
		reqReturnedLoanBoxSearchVO.setName("");
		reqReturnedLoanBoxSearchVO.setIdNo("");
		reqReturnedLoanBoxSearchVO.setReturnType("XSCS-RETURN");*/
		
		//必填参数
		
		reqReturnedLoanBoxSearchVO.setServiceCode("00219303");
		reqReturnedLoanBoxSearchVO.setServiceName("1000");
		reqReturnedLoanBoxSearchVO.setIp("127.0.0.1");
		reqReturnedLoanBoxSearchVO.setPageNum(1);
		reqReturnedLoanBoxSearchVO.setPageSize(15);
		
		
		// 调用 soa 接口
		PageResponse<ResReturnedLoanBoxSearchVO> response = iReturnedLoanBoxExecuter.search(reqReturnedLoanBoxSearchVO);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
	
	@Test
	public void queryMessageCount(){
		ReqReturnedLoanBoxMessageVO reqReturnedLoanBoxMessageVO = new ReqReturnedLoanBoxMessageVO("cfs");
		reqReturnedLoanBoxMessageVO.setUserCode("ldadmin");
		
		try{
		
		Response<Object> response = iReturnedLoanBoxExecuter.queryMessageCount(reqReturnedLoanBoxMessageVO);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
