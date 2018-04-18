package com.ymkj.cms.biz.service.finance.baoyin;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackLoan2Impl;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: BaoShangBackLoanImpl 
 * @Description: 放款确认  退回-包银渠道
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午11:13:46
 */
@Service
public class ByBackLoan2Impl extends BackLoan2Impl {
	
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
		BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityService.findLoanExtByLoanNo(param);
		BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(param);
		String busNumber = loanExtByLoanNo.getBusNumber();
		if(busNumber!=null){
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
					BmsLogger.info("**********************包银撤销成功后置空包银状态值数据库操作异常***********************loanNo:"+reqLoanVo.getLoanNo());
					throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"包银撤销成功后置空包银状态值数据库操作异常！");
				}
			}
		}
		return super.execute(reqLoanVo, res);
	}

}
