package com.ymkj.cms.web.boss.controller.master;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IDebtRadioService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUser;
import com.ymkj.sso.client.ShiroUtils;

@Controller
@RequestMapping("debtRadio")
public class DebtRadioController extends BaseController {
	
	@Autowired
	private IDebtRadioService idebtRadioService;
	
	@RequestMapping("view")
	public String view() {
		return "master/debtRadio/debtRadioList";
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO reqDebtRadioVo) {
		if (reqDebtRadioVo.getPageNum() == 0 || reqDebtRadioVo.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqDebtRadioVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResBMSDebtRadioVO> pageResult = idebtRadioService.listPage(reqDebtRadioVo);
		ResponsePage<ResBMSDebtRadioVO> pageList = new ResponsePage<ResBMSDebtRadioVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	@RequestMapping(value = "queryDebtRadioInit")
	@ResponseBody
	public Map<String, Object> queryDebtRadioInit(ReqBMSdebtRadioVO reqDebtRadioVo) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		reqDebtRadioVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResBMSDebtRadioVO vo = idebtRadioService.findByDebtRadioId(reqDebtRadioVo);
		hashMap.put("info", vo);
		return hashMap;
	}
	@RequestMapping(value = "editDebtRadio")
	@ResponseBody
	public Map<String, Object> editDebtRadio(ReqBMSdebtRadioVO reqDebtRadioVo) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ShiroUser user=ShiroUtils.getShiroUser();
		ResEmployeeVO resEmployeeVO= user.getEmployeeVO();
		String usrCode=resEmployeeVO.getUsercode();
	/*	reqLoanVo.setServiceCode(usrCode);
		reqLoanVo.setServiceName(resEmployeeVO.getName());*/
		ReqBMSdebtRadioVO reqDebtRadio=new ReqBMSdebtRadioVO();
		//reqDebtRadio.setModifierId(usrCode);
		reqDebtRadio.setModifier(usrCode);
		reqDebtRadio.setModifierDate(new Date());
		reqDebtRadio.setId(reqDebtRadioVo.getId());
		reqDebtRadio.setTotalDebtRadio(reqDebtRadioVo.getTotalDebtRadio().divide(new BigDecimal(100)));
		reqDebtRadio.setInternalDebtRadio(reqDebtRadioVo.getInternalDebtRadio().divide(new BigDecimal(100)));
		reqDebtRadio.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		boolean Flag=idebtRadioService.updateById(reqDebtRadio);
		hashMap.put("isSuccess", Flag ? true : false);
		return hashMap;
	}

}
