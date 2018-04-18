package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppProvidentInfoVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ITmAppProvidentInfoService;
@Controller
@RequestMapping("tmAppProvidentInfo")
public class TmAppProvidentInfoController extends BaseController{
	@Autowired
	private ITmAppProvidentInfoService TmAppProvidentInfoService;
	
	@RequestMapping("view")
	public String view() {
		return "master/tmAppProvidentInfo/tmAppProvidentInfoList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSTmAppProvidentInfoVO> listPage(ReqBMSTmAppProvidentInfoVO reqTmAppProvidentInfoVO) {
		if (reqTmAppProvidentInfoVO.getPageNum() == 0 || reqTmAppProvidentInfoVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqTmAppProvidentInfoVO.setSysCode("1111111111");
		/*reqSysTmAppProvidentInfoVO.setIsDeleted((long) 0);*/
		PageResult<ResBMSTmAppProvidentInfoVO> pageResult = TmAppProvidentInfoService.listPage(reqTmAppProvidentInfoVO);
		ResponsePage<ResBMSTmAppProvidentInfoVO> pageList = new ResponsePage<ResBMSTmAppProvidentInfoVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}

}
