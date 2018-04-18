package com.ymkj.cms.web.boss.controller.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.web.boss.common.Html2PDFUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ILoanUrgentConfigService;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.sso.client.ShiroUtils;

@Controller
@RequestMapping("urgentLimit")
public class LoanUrgentConfigController extends BaseController{

	@Autowired
	private ILoanUrgentConfigService iLoanUrgentConfigService;
	
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	
	@RequestMapping("view")
	public String view() {
		return "master/urgentLimit/urgentLimitList";
	}
	//加载查询的营业部下拉框
	@RequestMapping(value = "findOrgs")
	@SuppressWarnings("deprecation")
	@ResponseBody
	public List<ResOrganizationVO> findOrgs() {
		ReqParamVO reqParamVO=new ReqParamVO();
		reqParamVO.setSysCode("bms");
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		String code=resEmployeeVO.getUsercode();
		reqParamVO.setLoginUser(code);
		Response<List<ResOrganizationVO>> listVos=iOrganizationExecuter.findDataDeptsByAccount(reqParamVO);
		List<ResOrganizationVO> list=listVos.getData();
		return list;
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO) {
		if (reqOrgVO.getPageNum() == 0 || reqOrgVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqOrgVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqOrgVO.setIsDeleted((long) 0);
		PageResult<ResBMSLoanUrgentConfigVO> pageResult = iLoanUrgentConfigService.listPage(reqOrgVO);
		ResponsePage<ResBMSLoanUrgentConfigVO> pageList = new ResponsePage<ResBMSLoanUrgentConfigVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	
	@RequestMapping(value = "updateOrg")
	@ResponseBody
	public Map<String, Object> updateOrg(ReqBMSUrgentLimitListVO reqOrgVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqOrgVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Integer isSuccess = iLoanUrgentConfigService.updateOrg(reqOrgVO);
		hashMap.put("isSuccess", isSuccess==1 ? true : false);
		return hashMap;
	}
}
