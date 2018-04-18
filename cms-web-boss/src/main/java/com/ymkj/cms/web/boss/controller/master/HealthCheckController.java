package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.web.boss.service.master.IDebtRadioService;

@Controller
@Scope("singleton")
@RequestMapping("/healthCheck")
public class HealthCheckController  extends BaseController {

	@Autowired
	private IDebtRadioService idebtRadioService;

	@RequestMapping(value = "/verify")
	@ResponseBody
	public String testConnection() {
		try {
			Request req=new Request ();
			req.setSysCode("bms");
			idebtRadioService.testConnection(req);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
}
