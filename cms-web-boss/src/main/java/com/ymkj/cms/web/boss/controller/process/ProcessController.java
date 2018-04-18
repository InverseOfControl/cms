package com.ymkj.cms.web.boss.controller.process;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("process")
public class ProcessController {
	/*
	 * 初始页面
	 */
	@RequestMapping("view")
	public String view() {
		return "processManager/processMannager";
	}

}
