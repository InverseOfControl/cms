package com.ymkj.cms.biz.service.sign.base;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.StartProcessInfo;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.IProcessService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保存合同签约信息
 * 
 * @author YM10138
 *
 */
@Service
public class SaveLoanContractSignImpl extends BaseSign<ResLoanContractSignVo> {

	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	@Autowired
	private IProcessService processService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ICoreHttpService coreHttpService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private IBMSLoanExtEntityDao loanExtEntityDao;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		@SuppressWarnings("unchecked")
		Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
			return false;
		}
		if (reqLoanContractSignVo.getContractLmt() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "contractLmt" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getChannelCd() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "channelCd" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getContractTrem() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "contractTrem" }), res);
			return false;
		}
		if (reqLoanContractSignVo.getId() == null) {
			setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "id" }), res);
			return false;
		}
		// 流程校验
		Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if (task != null && !EnumConstants.WF_KS.equals(task.getTaskName())) {
		setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
			return false;
		}
		BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		if(!StringUtils.isEmpty(bmsLoanBaseEntity1.getSignCode())){
			if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
				setError(new BizException(BizErrorCode.OPRATEUSER_EOERR,
						"当前签约客服异常，请刷新"), res) ;
				return false;
			}
			
		}
		return true;
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		BMSLoanProduct bmsLoanProduct = new BMSLoanProduct(reqLoanContractSignVo);
		bmsLoanProduct.setVersion(reqLoanContractSignVo.getPversion());
		// 签约信息查询 历史数据
		BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
		//在标的推送成功后，在第一个保存页面，修改渠道名称，点击“保存”
		//调用捞财宝【终止借款】接口，且不可再签约捞财宝
		
		boolean notifyFalg = false;
		// 推送判断,推送后标的锁定   渠道变更
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", reqLoanContractSignVo.getId());
		paramMap.put("channelCode", EnumConstants.channelCode.AITE.getValue());
		BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
		if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget()) && !reqLoanContractSignVo.getChannelCd().equals(loanInfo.getChannelCd())) {
			// 调用终止借款接口，待联调
			Map<String, String> requestMap = new HashMap<String, String>();
			requestMap.put("borrowNo", reqLoanContractSignVo.getLoanNo());
			requestMap.put("reason", "渠道变更");
			HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
			if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
				String content = httpResponse.getContent();
				Map contentMap = JsonUtils.decode(content, Map.class);
				if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
					// 处理成功 // 同意终止，通知核心
					//标的解锁锁定
					reqLoanContractSignVo.setChannelCd(EnumConstants.channelCode.AITE.getValue());
					reqLoanContractSignVo.setLoanNo(loanInfo.getLoanNo());
					notifyFalg = aiTeLoanContractService.discardLockTarget(reqLoanContractSignVo);
					//推送值加一
					Map<String,Object> extUpdateParam = new HashMap<String,Object>();
					extUpdateParam.put("loanNo", loanInfo.getLoanNo());
					extUpdateParam.put("channelPushFrequency", loanInfo.getChannelPushFrequency()+1);
					loanExtEntityDao.updateBySatus(extUpdateParam);
					
					if (!notifyFalg) {
						throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
					}
				} else {
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝借款无法终止，请联系爱特");
				}
			} else {
				throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
			}
		}
		// 保存借款签约信息
		long count = loanContractSignService.update(bmsLoanProduct);
		if (count != 1) {
			res.setRepCode(CoreErrorCode.DB_UPDATE_RESULT_0.getErrorCode());
			res.setRepMsg(CoreErrorCode.DB_UPDATE_RESULT_0.getDefaultMessage());
			throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0,"更新失败，请刷新页面");
		}
		// 推送过的借款为保证与核心数据同步需推送核心同步数据
		if (!StringUtils.isBlank(loanInfo.getApplyBankNameId())) {
			reqLoanContractSignVo.setAccLmt(loanInfo.getContractLmt());
			reqLoanContractSignVo.setAccTerm(loanInfo.getContractTrem());
			reqLoanContractSignVo.setApplyBankNameId(Integer.valueOf(loanInfo.getApplyBankNameId()));
			reqLoanContractSignVo.setApplyBankBranch(loanInfo.getApplyBankBranch());
			reqLoanContractSignVo.setBankPhone(loanInfo.getBankPhone());
			reqLoanContractSignVo.setApplyBankCardNo(loanInfo.getApplyBankCardNo());
			reqLoanContractSignVo.setApplyBankName(loanInfo.getApplyBankName());
			
			if(EnumConstants.channelCode.LUJS.getValue().equals(reqLoanContractSignVo.getChannelCd())
					&& !StringUtils.isBlank(loanInfo.getLujsLoanReqId())
					&& !StringUtils.isBlank(loanInfo.getLujsName())){
				
				reqLoanContractSignVo.setLujsLoanReqId(loanInfo.getLujsLoanReqId());
				reqLoanContractSignVo.setLujsName(loanInfo.getLujsName());
				// 调用核心接口
				if (!coreHttpService.pushCore(reqLoanContractSignVo, res))
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"调用核心接口失败");
			} else if (EnumConstants.channelCode.LUJS.getValue().equals(reqLoanContractSignVo.getChannelCd())){
				//该渠道未发生id校验
			} else {
				// 调用核心接口
				if (!coreHttpService.pushCore(reqLoanContractSignVo, res))
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"调用核心接口失败");
			}
		}
		Long loanBaseId = reqLoanContractSignVo.getId();
		StartProcessInfo startProcessInfo = new StartProcessInfo();
		startProcessInfo.setBusinessId(String.valueOf(loanBaseId));
		startProcessInfo.setCompleteStartTask(true);
		startProcessInfo.setPromoter(reqLoanContractSignVo.getServiceCode());// 流程发起人
		// 流程的渠道
		String channelCode = processService.getChannelCdById(String.valueOf(loanBaseId));
		// 当重新选择渠道时
		if (!StringUtils.isEmpty(channelCode) && !channelCode.equals(reqLoanContractSignVo.getChannelCd())) {
			// 删除流程实例和任务
			processService.deleteProcessInstanceByLoanBaseId(String.valueOf(loanBaseId));
			// 开启新的流程实例
			processService.startProcessByName(reqLoanContractSignVo.getChannelCd(), startProcessInfo);
		} else if (StringUtils.isEmpty(channelCode)) {
			// 开启工作流程
			processService.startProcessByName(reqLoanContractSignVo.getChannelCd(), startProcessInfo);
		} else {
			taskService.completeTaskByLoanBaseId(loanBaseId, "保存");
		}
		// 系统日志
		ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|保存1|save", "ILoanContractSignExecuter",
				"saveLoanContractSign");
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 查询版本号 并传给前端
		BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService
				.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
		ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
		resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
		resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
		res.setData(resLoanSignVo);
		return true;
	}
}
