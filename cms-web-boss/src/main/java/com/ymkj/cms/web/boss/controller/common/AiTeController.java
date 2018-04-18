package com.ymkj.cms.web.boss.controller.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractFileExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractNoticeExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.web.boss.common.JsonUtils;

@Controller
public class AiTeController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IAiTeLoanContractNoticeExecuter service;
	@Autowired
	private IAiTeLoanContractExecuter contractService;
	@Autowired
	private IAiTeLoanContractFileExecuter fileservice;
	/**
	 * 提供http服务:流标通知
	 *
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/common/aite/standardNotice", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,String> standardNotice(RequestVo requestVo) throws Exception {
		logger.info("进入流标通知服务，接收到的参数:"+requestVo.getBorrowNo()+"|"+requestVo.getSign());
		
		Response<RequestVo> res = service.bidFailureNotice(requestVo);
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", res.getRepCode());
		map.put("message", res.getRepMsg());
		
		return map;
	}

	/**
	 * 提供http服务:满标通知
	 *
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/common/aite/confirmationOfContract", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,String> confirmationOfContract(RequestVo requestVo) throws Exception {
		logger.info("进入合同确认服务，接收到的参数:"+requestVo.getBorrowNo()+"|"+requestVo.getSign());
		Response<RequestVo> res = service.bidSuccessNotice(requestVo);
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", res.getRepCode());
		map.put("message", res.getRepMsg());
		
		return map;
	}

	/**
	 * 提供http服务:标的放款通知
	 *
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/common/aite/financialAudit", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,String> financialAudit(RequestVo requestVo) throws Exception
	{
		logger.info("进入财务审核服务，接收到的参数:"+requestVo.getBorrowNo()+"|"+requestVo.getSign());
		
		Response<RequestVo> res = service.targetLoan(requestVo);
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", res.getRepCode());
		map.put("message", res.getRepMsg());
		
		return map;
	}
	
	/**
	 * 提供http服务:上传文件
	 *
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/common/aite/uploadFiles", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,String> uploadFiles(RequestVo requestVo) throws Exception {
		logger.info("进入上传附件服务，接收到的参数:appNo["+requestVo.getBorrowNo()+"],files:"+requestVo.getFiles()+"");	
		
		Response<RequestVo> res = fileservice.uploadFiles(requestVo);
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", res.getRepCode());
		map.put("message", res.getRepMsg());
		return map;
	}

	/**
	 * 提供http服务:还款计划
	 *
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/common/aite/queryRepaymentDetail", method = { RequestMethod.POST })
	@ResponseBody
	public String queryRepaymentDetail(RequestVo requestVo) throws Exception {
		logger.info("进入财务审核服务，接收到的参数:"+requestVo.getBorrowNo()+"|"+requestVo.getSign());
		Response<Object> res = contractService.queryRepaymentDetail(requestVo);
		
		String json = JsonUtils.encode(res);
		
		return json;
	}
	

}
