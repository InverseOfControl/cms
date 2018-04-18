package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ITmAppMasterLoanInfoService;
@Controller
@RequestMapping("tmAppMasterLoanInfo")
public class TmAppMasterLoanInfoController  extends BaseController{
	@Autowired
	private ITmAppMasterLoanInfoService TmAppMasterLoanInfoService;
	
	@RequestMapping("view")
	public String view() {
		return "master/tmAppMasterLoanInfo/tmAppMasterLoanInfoList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSTmAppMasterLoanInfoVO> listPage(ReqBMSTmAppMasterLoanInfoVO reqTmAppMasterLoanInfoVO) {
		if (reqTmAppMasterLoanInfoVO.getPageNum() == 0 || reqTmAppMasterLoanInfoVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqTmAppMasterLoanInfoVO.setSysCode("1111111111");
		/*reqSysTmAppMasterLoanInfoVO.setIsDeleted((long) 0);*/
		PageResult<ResBMSTmAppMasterLoanInfoVO> pageResult = TmAppMasterLoanInfoService.listPage(reqTmAppMasterLoanInfoVO);
		ResponsePage<ResBMSTmAppMasterLoanInfoVO> pageList = new ResponsePage<ResBMSTmAppMasterLoanInfoVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}
}
