package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ILoanExtService;

@Controller
@RequestMapping("loanExt")
public class LoanExtController extends BaseController {

	@Autowired
	private ILoanExtService LoanExtService;

	@RequestMapping("view")
	public String view() {
		return "master/loanExt/loanExtList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqLoanExtVO) {
		if (reqLoanExtVO.getPageNum() == 0 || reqLoanExtVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		PageResult<ResBMSLoanExtVO> pageResult = LoanExtService.listPage(reqLoanExtVO);
		ResponsePage<ResBMSLoanExtVO> pageList = new ResponsePage<ResBMSLoanExtVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

}
