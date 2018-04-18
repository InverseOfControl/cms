package com.ymkj.cms.web.boss.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppEstateInfoVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.ITmAppEstateInfoService;
@Controller
@RequestMapping("tmAppEstateInfo")
public class TmAppEstateInfoController extends BaseController{
	@Autowired
	private ITmAppEstateInfoService TmAppEstateInfoService;
	
	@RequestMapping("view")
	public String view() {
		return "master/tmAppEstateInfo/tmAppEstateInfoList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSTmAppEstateInfoVO> listPage(ReqBMSTmAppEstateInfoVO reqTmAppEstateInfoVO) {
		if (reqTmAppEstateInfoVO.getPageNum() == 0 || reqTmAppEstateInfoVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqTmAppEstateInfoVO.setSysCode("1111111111");
		/*reqSysTmAppEstateInfoVO.setIsDeleted((long) 0);*/
		PageResult<ResBMSTmAppEstateInfoVO> pageResult = TmAppEstateInfoService.listPage(reqTmAppEstateInfoVO);
		ResponsePage<ResBMSTmAppEstateInfoVO> pageList = new ResponsePage<ResBMSTmAppEstateInfoVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		/*
		 * System.out.println("pageResult.getTotalCount():"+pageResult.
		 * getTotalCount());
		 */
		return pageList;
	}

}
