package com.ymkj.cms.biz.service.sign.lujs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.HistoryProcessInstance;
import com.bstek.uflo.model.HistoryTask;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.base.CancelLoanImpl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

/**
 * 签约环节     取消
 * 
 * @author YM10138
 *
 */
@Service
public class LuJSCancelLoanImpl extends CancelLoanImpl {

	@Autowired
	private LUJSInterfaceService lujsInterfaceService;

	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//判断是否成功调用陆金所【借款申请信息提交接口】
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		//有发生陆金所人工审核
		if(loanInfo != null && loanInfo.getLujsApplyNo() != null){
			//调用外部进件撤销接口
			//是否需要调用撤销接口，判断
			Map<String, Object> notifyLujsMap = new HashMap<String, Object>();
			notifyLujsMap.put("loanNo",reqLoanContractSignVo.getLoanNo());
			notifyLujsMap.put("aps_apply_no",loanInfo.getLujsApplyNo());
			notifyLujsMap.put("notify_code","2");
			//需要调用，取消接口
			if (lujsInterfaceService.requestNotifyLujs(notifyLujsMap, res)){
				// 处理成功 // 同意终止，通知核心
			} else {
				return false;
			}
		}
		
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}
	

}
