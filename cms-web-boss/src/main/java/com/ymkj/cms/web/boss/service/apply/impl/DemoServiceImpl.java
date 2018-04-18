package com.ymkj.cms.web.boss.service.apply.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.web.boss.facade.apply.DemoFacade;
import com.ymkj.cms.web.boss.service.apply.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private DemoFacade demoFacade;

	@Override
	public ResDemoVO findById(Long id) {
		return demoFacade.findById(id);
	}

	@Override
	public ResDemoVO findByName(String name) {

		return demoFacade.findByName(name);
	}

	@Override
	public PageResult<ResDemoVO> listPage(ReqDemoVO reqDemoVO) {
		PageResult<ResDemoVO> pageResult = demoFacade.listPage(reqDemoVO);
		return pageResult;

	}

	@Override
	public Long saveOrUpdate(ReqDemoVO reqDemoVO) {
		return demoFacade.saveOrUpdate(reqDemoVO);
	}

}
