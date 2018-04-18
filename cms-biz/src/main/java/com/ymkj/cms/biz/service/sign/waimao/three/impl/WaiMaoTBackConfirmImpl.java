package com.ymkj.cms.biz.service.sign.waimao.three.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractConfirmService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.base.BackConfirmImpl;

/**
 * 合同确认 退回
 * 
 * @author YM10138
 *
 */
@Service
public class WaiMaoTBackConfirmImpl extends BackConfirmImpl {

	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private LoanExtDao loanExtDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}

}
