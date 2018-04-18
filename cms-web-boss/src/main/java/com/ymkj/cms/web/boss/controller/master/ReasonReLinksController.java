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
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;
import com.ymkj.cms.web.boss.service.master.IReasonReLinkService;


@Controller
@RequestMapping("reasonReLinks")
public class ReasonReLinksController {
	@Autowired
	private IEnumCodeService enumCodeService;
	@Autowired
	private IReasonReLinkService iReasonReLinkService;



	@RequestMapping("view")
	public String view() {
		return "master/reasonManage/reasonReLinks";
	}

	/**
	 * 查询所有操作模块
	 */
	@RequestMapping(value="listByModule")
	@ResponseBody
	public List<ResBMSEnumCodeVO> listByModule(ReqBMSEnumCodeVO reqBMSEnumCodeVO){

		ResListVO<ResBMSEnumCodeVO> reslist=enumCodeService.listBy(reqBMSEnumCodeVO);
		List<ResBMSEnumCodeVO> list=reslist.getCollections();
		return list;
	}

	@RequestMapping(value = "listPage")
	@ResponseBody
	public Map<String,Object> listPage(ReqBMSTMReasonVO reqReasonVO) {
		if (reqReasonVO.getPageNum() == 0 || reqReasonVO.getPageSize() == 0) {
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		String operationType=null;
		// 必须 设置请求编码
		reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResBMSReasonVO> pageResult = iReasonReLinkService.listPage(reqReasonVO);
		Map<String,Object> hashMap=new HashMap<String, Object>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for(int i=0;i<pageResult.getRecords().size();i++){
			String []reasonShow=pageResult.getRecords().get(i).getTexPlain().split(",");
			Map<String, Object>map=new HashMap<String, Object>();
			if(pageResult.getRecords().get(i).getModuleName()!=null){
				String[]moduleName=pageResult.getRecords().get(i).getModuleName().split(",");	
				for(int j=0;j<moduleName.length;j++){

					if(reasonShow[j].equals("1")){
						map.put(moduleName[j].replaceAll(" ", ""), "Y");	
					}else{
						map.put(moduleName[j].replaceAll(" ", ""), "N");	
					}	
				}

			}
			map.put("firstReason", pageResult.getRecords().get(i).getFirstReason());
			map.put("id",  pageResult.getRecords().get(i).getId());
			map.put("code", pageResult.getRecords().get(i).getCode());
			map.put("reason",pageResult.getRecords().get(i).getReason());
			map.put("relationCode", pageResult.getRecords().get(i).getRelationCode());
			if(pageResult.getRecords().get(i).getOperationType().equals("return")){
				operationType="退回";
			}else if(pageResult.getRecords().get(i).getOperationType().equals("hang")){
				operationType="挂起";
			}else if(pageResult.getRecords().get(i).getOperationType().equals("reject")){
				operationType="拒绝";
			}else{
				operationType="取消";
			}
			map.put("operationType",operationType);

			list.add(map);
		}
		hashMap.put("total", pageResult.getTotalCount());
		hashMap.put("rows", list);
		return hashMap;
	}

	@RequestMapping(value = "queryReasonManagementInit")
	@ResponseBody
	public Map<String, Object> queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		ResBMSReasonVO vo = iReasonReLinkService.queryReasonManagementInit(reqBMSReasonVO);
		if(vo!=null){
			if(null!=vo.getModuleName()){
				String [] reasonText=vo.getModuleName().split(",");
				List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
				for(String show:reasonText){
					Map<String,Object>map=new HashMap<String,Object>();
					ReqBMSEnumCodeVO enumCodeVO=new ReqBMSEnumCodeVO();
					enumCodeVO.setCode(show);
					ResListVO<ResBMSEnumCodeVO> resList=enumCodeService.listBy(enumCodeVO);
					map.put("code", show);
					map.put("nameCN", resList.getCollections().get(0).getNameCN());
					list.add(map);
				}
				hashMap.put("ReasonShow", list);
			}
			hashMap.put("info", vo);	
		}else{
			hashMap.put("isNotExit", false);	
		}

		return hashMap;
	}
	
	@RequestMapping(value = "editReasonReLinks")
	@ResponseBody
	public Map<String, Object> editReasonReLinks(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		boolean addSuccess = iReasonReLinkService.editReasonReLinks(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

}
