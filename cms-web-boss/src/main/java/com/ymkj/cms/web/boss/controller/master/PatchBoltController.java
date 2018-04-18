package com.ymkj.cms.web.boss.controller.master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IPatchBoltService;

@Controller
@RequestMapping("patchBolt")
public class PatchBoltController {
	@Autowired
	private IPatchBoltService patchBoltService;
	@RequestMapping("view")
	public String view() {
		return "master/patchBolt/patchBoltList";
	}
	@RequestMapping(value="listPage")
	@ResponseBody
	public ResponsePage<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO){
		if (reqBMSPatchBoltVO.getPageNum() == 0 || reqBMSPatchBoltVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		reqBMSPatchBoltVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResponse<ResQueryLoanVo> pageResult=patchBoltService.listPage(reqBMSPatchBoltVO);
		ResponsePage<ResQueryLoanVo> responsePage = new ResponsePage<ResQueryLoanVo>();
		responsePage.setTotal(pageResult.getTotalCount());
		responsePage.setRows(pageResult.getRecords());
		return responsePage;
	}
}
