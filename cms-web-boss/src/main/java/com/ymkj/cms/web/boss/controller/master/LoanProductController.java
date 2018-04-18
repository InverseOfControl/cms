package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ILoanProductService;

@Controller
@RequestMapping("loanProduct")
public class LoanProductController extends BaseController {

	@Autowired
	private ILoanProductService LoanProductService;

	@RequestMapping("view")
	public String view() {
		return "master/loanProduct/loanProductList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqLoanProductVO) {
		if (reqLoanProductVO.getPageNum() == 0 || reqLoanProductVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		PageResult<ResBMSLoanProductVO> pageResult = LoanProductService.listPage(reqLoanProductVO);
		ResponsePage<ResBMSLoanProductVO> pageList = new ResponsePage<ResBMSLoanProductVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
}
