package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogEntityVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ISysLogEntityService;
@Controller
@RequestMapping("sysLogEntity")
public class SysLogEntityController extends BaseController{
	@Autowired
	private ISysLogEntityService sysLogEntityService;
	
	@RequestMapping("view")
	public String view() {
		return "master/sysLogEntity/sysLogEntityList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSSysLogEntityVO> listPage(ReqBMSSysLogEntityVO reqSysLogEntityVO) {
		if (reqSysLogEntityVO.getPageNum() == 0 || reqSysLogEntityVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqSysLogEntityVO.setSysCode("1111111111");
		/*reqSysSysLogEntityVO.setIsDeleted((long) 0);*/
		PageResult<ResBMSSysLogEntityVO> pageResult = sysLogEntityService.listPage(reqSysLogEntityVO);
		ResponsePage<ResBMSSysLogEntityVO> pageList = new ResponsePage<ResBMSSysLogEntityVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}

}
