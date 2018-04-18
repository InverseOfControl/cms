package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseRelaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseRelaVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ILoanBaseRelaService;
@Controller
@RequestMapping("loanBaseRela")
public class LoanBaseRelaController extends BaseController{
	@Autowired
	private ILoanBaseRelaService LoanBaseRelaService;
	
	@RequestMapping("view")
	public String view() {
		return "master/loanBaseRela/loanBaseRelaList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSLoanBaseRelaVO> listPage(ReqBMSLoanBaseRelaVO reqLoanBaseRelaVO) {
		if (reqLoanBaseRelaVO.getPageNum() == 0 || reqLoanBaseRelaVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqLoanBaseRelaVO.setSysCode("1111111111");
		/*reqSysLoanBaseRelaVO.setIsDeleted((long) 0);*/
		PageResult<ResBMSLoanBaseRelaVO> pageResult = LoanBaseRelaService.listPage(reqLoanBaseRelaVO);
		ResponsePage<ResBMSLoanBaseRelaVO> pageList = new ResponsePage<ResBMSLoanBaseRelaVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}

}
