package com.ymkj.cms.web.boss.controller.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IBankService;
import com.ymkj.cms.web.boss.service.master.ICreditRatingLimitService;

@Controller
@RequestMapping("creditRatingLimit")
public class CreditRatingLimitController extends BaseController{

	
	@Autowired
	private ICreditRatingLimitService iCreditRatingLimitService;

	@RequestMapping("view")
	public String view() {
		return "master/creditRatingLimit/creditRatingLimitList";
	}
	
	
	
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		if (reqBMSCreditRatingLimitVO.getPageNum() == 0 || reqBMSCreditRatingLimitVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		/* System.out.println("-------code:"+reqBankVO.getCode()); */
		// 必须 设置请求编码
		reqBMSCreditRatingLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqBMSCreditRatingLimitVO.setIsDeleted(0);
		PageResult<ResBMSCreditRatingLimitVO> pageResult = iCreditRatingLimitService.listPage(reqBMSCreditRatingLimitVO);
		ResponsePage<ResBMSCreditRatingLimitVO> pageList = new ResponsePage<ResBMSCreditRatingLimitVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}
	
	
	
	@RequestMapping(value = "findByProductAll")
	@ResponseBody
	public List<Map<String, Object>> findByProductAll() {
		ReqBMSCreditRatingLimitVO v=new ReqBMSCreditRatingLimitVO();
		v.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<List<Map<String,Object>>> response=iCreditRatingLimitService.findByProductAll(v);
		return response.getData();
	}
	
	
	
	@RequestMapping(value = "addCreditRatingLimit")
	@ResponseBody
	public Map<String, Object> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSCreditRatingLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<Integer> isRepeat=iCreditRatingLimitService.findAddIsRepeat(reqBMSCreditRatingLimitVO);
		if(isRepeat.getData()>0){
			hashMap.put("isSuccess", false);
			hashMap.put("message", "不能重复新增同一条信息");
			return hashMap;
		}
		Response<Integer> response = iCreditRatingLimitService.addCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(response.getData()==1){
			hashMap.put("isSuccess", true);
		}else{
			hashMap.put("isSuccess", false);
			hashMap.put("message", "新增出问题");
		}
		return hashMap;
	}
	
	
	
	@RequestMapping(value = "deleteCreditRatingLimit")
	@ResponseBody
	public Map<String, Object> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSCreditRatingLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<Integer> response = iCreditRatingLimitService.deleteCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(response.getData()==1){
			hashMap.put("isSuccess", true);
		}else{
			hashMap.put("isSuccess", false);
			hashMap.put("message", "删除出问题");
		}
		return hashMap;
	}
	
	@RequestMapping(value = "loadCreditRatingLimit")
	@ResponseBody
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		reqBMSCreditRatingLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<ResBMSCreditRatingLimitVO> response = iCreditRatingLimitService.loadCreditRatingLimit(reqBMSCreditRatingLimitVO);
		return response;
	}
	
	
	@RequestMapping(value = "updateCreditRatingLimit")
	@ResponseBody
	public Map<String, Object> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSCreditRatingLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		Response<Integer> isRepeat=iCreditRatingLimitService.findUpdateIsRepeat(reqBMSCreditRatingLimitVO);
		if(isRepeat.getData()>0){
			hashMap.put("isSuccess", false);
			hashMap.put("message", "编辑的记录已经存在");
			return hashMap;
		}
		
		Response<Integer> response = iCreditRatingLimitService.updateCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(response.getData()==1){
			hashMap.put("isSuccess", true);
		}else{
			hashMap.put("isSuccess", false);
			hashMap.put("message", "编辑出问题");
		}
		return hashMap;
	}
}
