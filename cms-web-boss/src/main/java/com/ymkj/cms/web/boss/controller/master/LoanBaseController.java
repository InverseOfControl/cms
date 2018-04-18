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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ILoanBaseService;

@Controller
@RequestMapping("loanBase")
public class LoanBaseController extends BaseController {

	@Autowired
	private ILoanBaseService loanBaseService;

	@RequestMapping("view")
	public String view() {
		return "master/loanBase/loanBaseList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqLoanBaseVO) {
		if (reqLoanBaseVO.getPageNum() == 0 || reqLoanBaseVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		PageResult<ResBMSLoanBaseVO> pageResult = loanBaseService.listPage(reqLoanBaseVO);
		ResponsePage<ResBMSLoanBaseVO> pageList = new ResponsePage<ResBMSLoanBaseVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	
	
	@RequestMapping(value = "findStatus")
	@ResponseBody
	public List<Map<String,String>> findStatus(ReqBMSLoanBaseVO reqLoanBaseVO) {
		List<Map<String ,String>> stateList =new ArrayList<Map<String ,String>>();
		Map<String,String> StateMap =null;
		for(EnumConstants.Status status:EnumConstants.Status.values()){
			StateMap=new HashMap<String,String>();
			StateMap.put("code", status.getCode());
			StateMap.put("name", status.getDesc());
			stateList.add(StateMap);
		}
		return stateList;
	}

}
