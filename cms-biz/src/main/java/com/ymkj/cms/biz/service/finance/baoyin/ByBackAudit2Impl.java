package com.ymkj.cms.biz.service.finance.baoyin;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackAudit2Impl;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName: BaoShangBackAuditImpl
 * @Description: 放款审核退回-包银渠道
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午10:38:18
 */
@Service
public class ByBackAudit2Impl extends BackAudit2Impl {
	
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Autowired
	private IBMSLoanExtEntityService ibmsLoanExtEntityService;
	@Autowired
	private IBMSAppPersonInfoService ibmsAppPersonInfoService;
	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", reqLoanVo.getLoanNo());
		BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(param);
		BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityService.findLoanExtByLoanNo(param);
		String busNumber = loanExtByLoanNo.getBusNumber();
		/*	审核结果	是否可确认	是否可退回	退回调撤销接口	是否可再签包商银行
			拒绝	     	否			是			否				否
			审核中			否			是			是				是
			通过			是			是			是				否
			补件			否			是			否				-*/
		//审核中和通过的时候放款审核退回是需要调撤销接口的
		if(busNumber!=null){
			if(EnumConstants.BaoyinAuditingState._000.getCode().equals(loanExtByLoanNo.getAuditingState()) ||
					EnumConstants.BaoyinAuditingState._004.getCode().equals(loanExtByLoanNo.getAuditingState())){
				//调包银撤销接口
				Map<String, String> map = baoShangHttpService.loanRepeal("01", appPersonInfo.getIdNo(), appPersonInfo.getName(), busNumber);
				if(!"000000".equals(map.get("code"))){
					res.setRepMsg(map.get("msg"));
					res.setRepCode(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode());
					return false;
				}else{
					//包银撤销成功后置空包银状态值
					BmsLogger.info("***********************包银撤销成功后置空借款扩展表包银状态值***********************loanNo:"+reqLoanVo.getLoanNo());
					long byStatusNull = ibmsLoanExtEntityService.updateByStatusNull( reqLoanVo.getLoanNo());
					if(byStatusNull!=1){
						BmsLogger.info("***********************包银撤销成功后置空包银状态值数据库操作异常***********************loanNo:"+reqLoanVo.getLoanNo());
						throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"包银撤销成功后置空包银状态值数据库操作异常！");
					}
				}
			}
		}
		return super.execute(reqLoanVo, res);
	}
}
