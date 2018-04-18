package com.ymkj.cms.web.boss.controller.master;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IReasonManagementService;

@Controller
@RequestMapping("reasonManagement")
public class ReasonManagementController extends BaseController{

	@Autowired
	private IReasonManagementService iReasonManagementService;

	@RequestMapping("main")
	public String view() {
		return "master/reasonManagement/reasonManagementList";
	}
	
	/**
	 * 返回json类型的数据: 操作类型
	 * 暂时没有数据，模拟直接在controller返回JSON数据
	 * 
	 * @param 
	 * @return json
	 */
	@RequestMapping(value = "getreasonManagement")
	@ResponseBody
	public List<Map<String,String>> getreasonManage() {
		List<Map<String,String>> maps=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", "cancel");
		map.put("text", "cancel");
		Map<String,String> map1=new HashMap<String,String>();
		map1.put("id", "return");
		map1.put("text", "return");
		Map<String,String> map2=new HashMap<String,String>();
		map2.put("id", "reject");
		map2.put("text", "reject");
		maps.add(map);
		maps.add(map1);
		maps.add(map2);
		return maps;
	}
	
	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 根据参数查询原因
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		if (reqReasonVO.getPageNum() == 0 || reqReasonVO.getPageSize() == 0) {
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqReasonVO.setSysCode("1111111111");
		PageResult<ResBMSReasonVO> pageResult = iReasonManagementService.listPage(reqReasonVO);
		ResponsePage<ResBMSReasonVO> pageList = new ResponsePage<ResBMSReasonVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	
	@RequestMapping(value = "addReasonManagement")
	@ResponseBody
	public Map<String, Object> addTmParameterMethod(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		boolean addSuccess = iReasonManagementService.addReasonManagement(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}
	
	
	@RequestMapping(value = "queryReasonManagementInit")
	@ResponseBody
	public Map<String, Object> queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		ResBMSReasonVO vo = iReasonManagementService.queryReasonManagementInit(reqBMSReasonVO);
		hashMap.put("info", vo);
		return hashMap;
	}
	
	@RequestMapping(value = "editReasonManagement")
	@ResponseBody
	public Map<String, Object> editReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		boolean addSuccess = iReasonManagementService.editReasonManagement(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}
	
	
	@RequestMapping(value = "deleteReasonManage")
	@ResponseBody
	public Map<String, Object> deleteReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		boolean addSuccess = iReasonManagementService.deleteReasonManagement(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}
}
