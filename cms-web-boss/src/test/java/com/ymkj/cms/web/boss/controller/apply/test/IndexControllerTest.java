package com.ymkj.cms.web.boss.controller.apply.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ymkj.cms.web.test.AbstractTestCase;
import com.ymkj.cms.web.boss.controller.apply.IndexController;


public class IndexControllerTest extends AbstractTestCase{

	private MockMvc mockMvc;      
	private MockHttpServletRequest request;  
	private MockHttpServletResponse response;
	//  从Spring容器中加载LoginController
	@Autowired
	private IndexController indexController;
	@Autowired
    protected WebApplicationContext wac;
	//执行测试方法之前初始化模拟request,response    
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(wac).build();  
		request = new MockHttpServletRequest();  
        response = new MockHttpServletResponse();  
        request.setCharacterEncoding("UTF-8");  
	}
	/** 
     *  
     * @Title：testLogin 
     * @Description: 测试用户登录   
     */  
    @Test  
    public void testLogin() throws Exception{   
		String responseString = mockMvc.perform(MockMvcRequestBuilders.post("http://172.16.230.16:8080/cas/login?service=http://127.0.0.1:8080/bms-web-boss/shiro-cas&locale=zh_CN") // 请求的url,请求的方法是post
				.param("username", "bmstest").param("password", "Zd888888")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE) // 账号，密码
		).andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andDo(MockMvcResultHandlers.print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("--------返回的json = " + responseString);
    }  
	@Test
	public void testLeftMenu() throws Exception{
		
		String responseString = mockMvc.perform(
				MockMvcRequestBuilders.post("/bms-web-boss/index/findMenuTree") //请求的url,请求的方法是post
				.param("username", "bmstest").param("password", "Zd888888")   //账号，密码
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)  //数据的格式
        ).andExpect(MockMvcResultMatchers.status().isOk())    //返回的状态是200
                .andDo(MockMvcResultHandlers.print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + responseString);
        
	}

}
