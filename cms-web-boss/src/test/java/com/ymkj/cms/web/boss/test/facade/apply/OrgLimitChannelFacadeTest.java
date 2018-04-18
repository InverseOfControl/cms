package com.ymkj.cms.web.boss.test.facade.apply;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.web.test.AbstractTestCase;
import com.ymkj.cms.web.boss.facade.apply.OrgLimitChannelFacade;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

public class OrgLimitChannelFacadeTest extends AbstractTestCase{

	
	
	// json 工具类
	private Gson gson = new Gson();
		
    // 请求实体
	private ReqBMSOrgLimitChannelVO deomVo = new ReqBMSOrgLimitChannelVO();
	
	@Autowired
	private OrgLimitChannelFacade orgLimitChannelFacade;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrgTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrgByProductCodeListIntersect() {
		List<String> productCodeList = new ArrayList<String>();
		productCodeList.add("00020");
		productCodeList.add("00001");
		productCodeList.add("00003");
		productCodeList.add("00004");
		deomVo.setProductCodeList(productCodeList);
		List<ResOrganizationVO> res = orgLimitChannelFacade.findOrgByProductCodeListIntersect(deomVo);
		
		System.out.println("---------------"+gson.toJson(res));
	}

}
