package com.ymkj.cms.web.boss.service.menus.impl.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ymkj.cms.web.test.AbstractTestCase;
import com.ymkj.cms.web.boss.common.MenuTreeJson;
import com.ymkj.cms.web.boss.controller.apply.IndexController;
import com.ymkj.cms.web.boss.service.menus.IPmsMenusService;
import com.ymkj.cms.web.boss.service.menus.impl.PmsMenusServiceImpl;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;

public class PmsMenusServiceImplTest extends AbstractTestCase{
	
	@Autowired
	private IPmsMenusService pmsMenusService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMenusByAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMenuTree() {
		ReqEmployeeVO vo  = new ReqEmployeeVO();
		//业务系统编码
		vo.setSysCode("bms");
		//业务类别
		vo.setBizType("");
		//员工工号
		vo.setUsercode("bmstest");
		MenuTreeJson resutl = pmsMenusService.findMenuTree(vo);
		assertTrue(resutl.isHasChildren());
		System.out.println(resutl);
		
		
	}

}
