package com.ymkj.cms.biz.facade.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumLuJSConstants;
import com.ymkj.cms.biz.api.service.sign.ILufaxExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLujsInformVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLufax800001Vo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLujsInformVo;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;


/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:LufaxExecuter</p>
 * <p>Description:陆金所相关接口</p>
 * @uthor YM10159
 * @date 2017年7月14日 下午5:31:49
 */
@Service
public class LufaxExecuter implements ILufaxExecuter {

	private final static Log log = LogFactory.getLog(LufaxExecuter.class); 

	@Autowired
	private ILuJSInformService luJSInformService;

	@Autowired
	private LUJSInterfaceService interfaceService;

	@Autowired
	private ILufaxExecuter service;

	@Autowired
	private ILoanService loanService;

	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;

	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	
	@Autowired
	private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ILoanBaseEntityService loanBaseEntityService;

	@SuppressWarnings("unchecked")
	@Override
	public Response<ResLufax800001Vo> inNoticeExternal(ReqLufax800001Vo lufax800001Vo) {
		log.info("陆金所通知结果：notifyCode="+lufax800001Vo.getNotify_code()+" notifyDesc="+lufax800001Vo.getNotify_desc());
		Map returnMap =new HashMap();
		Map<String, Object> loanParamMap =new HashMap<String, Object>();
		lufax800001Vo.setApply_no(lufax800001Vo.getApply_no().split("_")[1]);

		loanParamMap.put("loanNo", lufax800001Vo.getApply_no());
		BMSLoanBaseEntity loanInfo = loanBaseEntityDao.findSignInfo(loanParamMap);	
		String notify_code = ObjectUtils.toString(lufax800001Vo.getNotify_code());
		loanParamMap.put("notifyCode", notify_code);
		loanParamMap.put("notifyDesc", lufax800001Vo.getNotify_desc());
		//根据通知类型 更新机构审核状态
		updateOrgAuditState(loanParamMap);
		if((EnumConstants.LuJSnotifyCode.XSTG.getCode().equals(notify_code) || EnumConstants.LuJSnotifyCode.XSBTG.getCode().equals(notify_code)) || 
			EnumConstants.LuJSnotifyCode.XSQCL.getCode().equals(notify_code) || EnumConstants.LuJSnotifyCode.XSSQEDBG.getCode().equals(notify_code)){
			Response<ResLufax800001Vo> reponse = new Response<>();

			//封装合同确认所需要的数据
			ReqLoanContractSignVo reqLoanContractSignVo = interfaceService.packageContractConfirmInfo(lufax800001Vo.getApply_no());
			reqLoanContractSignVo.setReqLufax800001Vo(lufax800001Vo);
			Response<ResLoanContractSignVo> contractSignResponse = SignFactory.execute(reqLoanContractSignVo, EnumConstants.channelCode.LUJS.getValue(), EnumConstants.Sign.MANUAL_AUDIT.getValue());

			log.info("陆金所人工审核处理结果：repCode="+contractSignResponse.getRepCode()+" repMsg="+contractSignResponse.getRepMsg());
			reponse.setRepCode(contractSignResponse.getRepCode());
			reponse.setRepMsg(contractSignResponse.getRepMsg());
			return reponse;
		} else if(EnumConstants.LuJSnotifyCode.FQZTG.getCode().equals(lufax800001Vo.getNotify_code())){
			
			Response<ResLufax800001Vo> res = service.inNoticeExternalSave(lufax800001Vo);
			if(res.isSuccess()){
				BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService.getByLoanNo(lufax800001Vo.getApply_no());
				// 流程任务校验（非当前节点抛出异常）
				Task task = taskService.findTaskByLoanBaseId(String
						.valueOf(bmsLoanBaseEntity.getId()));
				if (task == null || !EnumConstants.WF_BCXX.equals(task.getTaskName())) {
					throw new BizException(BizErrorCode.UFLOTASK_EOERR, "借款不在当前任务节点，请刷新");
				}
				// 完成任务
				try {
					taskService.completeTaskByLoanBaseId(bmsLoanBaseEntity.getId(),
							EnumConstants.WFPH_BC);
				} catch (BizException e) {
					throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e);
				}
			}
			return res;

		} else if (EnumConstants.LuJSnotifyCode.FQZJJ.getCode().equals(lufax800001Vo.getNotify_code())) {

			//②　若返回反欺诈结果为拒绝，不可生成合同，页面提示：“反欺诈结果为XXX，原因是XXX。请改签其他渠道！”
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", loanInfo.getLoanNo());
			paramMap.put("channelCode", loanInfo.getChannelCd());
			BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
			String lockTarget = "N";
			if(lockTargetEntity != null){
				lockTargetEntity.setLockTarget(lockTarget);
				lockTargetEntity.setModified(lufax800001Vo.getSysCode());
				long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
				if(udpateNum !=1){
					throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
				}
			} else {
				lockTargetEntity = new BMSLoanChannelLockTargetEntity();
				lockTargetEntity.setLoanBaseId(loanInfo.getId());
				lockTargetEntity.setLoanNo(loanInfo.getLoanNo());
				lockTargetEntity.setChannelCode(loanInfo.getChannelCd());
				lockTargetEntity.setLockTarget(lockTarget);
				lockTargetEntity.setCreator(lufax800001Vo.getSysCode());
				lockTargetEntity.setModified(lufax800001Vo.getSysCode());
				long udpateNum = loanChannelLockTargetDao.insert(lockTargetEntity);
				if(udpateNum !=1){
					throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "插入错误");
				}
			}
			return service.inNoticeExternalSave(lufax800001Vo);

		} else{ //其他通知都需要保存
			return service.inNoticeExternalSave(lufax800001Vo);
		}
		//更新外部机构审批状态
		
	}

	@Override
	public Response<ResLujsInformVo> findlujsInform(ReqLujsInformVo lujsInformVo) {
		Response<ResLujsInformVo> response = new Response<ResLujsInformVo>();
		// 参数校验
		if (StringUtils.isEmpty(lujsInformVo.getLoanNo())) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "loanNo" });
		}
		if (StringUtils.isEmpty(lujsInformVo.getNotifyType())) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "notifyType" });
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanNo", lujsInformVo.getLoanNo());

			//数据类型
			if(EnumConstants.LuJSnotifyType.FQZ.getCode().equals(lujsInformVo.getNotifyType())){
				List<String> informResultList = new ArrayList<String>();
				informResultList.add(EnumConstants.LuJSnotifyCode.FQZTG.getCode());
				informResultList.add(EnumConstants.LuJSnotifyCode.FQZJJ.getCode());
				paramMap.put("informResultList", informResultList);
			}
			// 调用业务逻辑
			BMSLuJSInform luJSInform = luJSInformService.getBy(paramMap);

			if(luJSInform == null){
				response.setData(null);
				//				response.setRepCode(CoreErrorCode.DB_RESULT_ISNULL.getErrorCode());
				//				response.setRepMsg("陆金所未返回反欺诈结果，请稍后再试。");
				return response;
			}
			ResLujsInformVo resLujsInformVo = new ResLujsInformVo();

			BeanUtils.copyProperties(luJSInform, resLujsInformVo);

			response.setData(resLujsInformVo);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}


		return response;
	}

	@Override
	public Response<ResLufax800001Vo> inNoticeExternalSave(ReqLufax800001Vo lufax800001Vo) {
		Response<ResLufax800001Vo> res = new Response<ResLufax800001Vo>();
		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(lufax800001Vo);
		if (!validateResponse.isSuccess()) {
			setError(new BizException(BizErrorCode.EOERR, validateResponse.getRepMsg()), res);
			return res;
		}
		try{
			interfaceService.inNoticeExternal(lufax800001Vo);

			res.setRepMsg("通知成功");
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return res;
	}

	
	private  boolean  updateOrgAuditState(Map paramMap) {
		boolean updFlag =false;
		String orgAuditState =null;
		String loanNo=String.valueOf(paramMap.get("loanNo"));
		String  notifyCode =String.valueOf(paramMap.get("notifyCode")) ;
		String  notifyDesc =String.valueOf(paramMap.get("notifyDesc")) ;
		if(notifyCode.equals(EnumConstants.LuJSnotifyCode.XSTG.getCode())){//信审通过
			orgAuditState=EnumConstants.OrgAuditState.TG.getCode();
		}else if(notifyCode.equals(EnumConstants.LuJSnotifyCode.XSQCL.getCode())){//补充材料
			orgAuditState=EnumConstants.OrgAuditState.BCCL.getCode();
		}else if(notifyCode.equals(EnumConstants.LuJSnotifyCode.XSBTG.getCode())){//信审不通过
			orgAuditState=EnumConstants.OrgAuditState.BTG.getCode();
		}else if(notifyCode.equals(EnumConstants.LuJSnotifyCode.QREDQX.getCode())
				||notifyCode.equals(EnumConstants.LuJSnotifyCode.XSSQEDBG.getCode())){//00201 00601 人工取消
			orgAuditState=EnumConstants.OrgAuditState.RGQX.getCode();
		}else{
			orgAuditState=EnumConstants.OrgAuditState.SHZ.getCode();
		}
        //更新借款表陆金所状态值
		Map paramExt =new HashMap();
		paramExt.put("loanNo", loanNo);
		paramExt.put("orgAuditState",orgAuditState);
		paramExt.put("windControlResult",notifyDesc);
        long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
        if (update != 1) {
            log.debug("陆金所状态，更新借款扩展表机构审批状态失败!");
            //抛异常保证事务回滚
            throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"保存陆金所渠道返回的状态值失败");
        }
		return updFlag;
	}
	/**
	 * 异常返回值处理
	 * 
	 * @param biz
	 * @param res
	 * @return
	 */
	private Response<ResLufax800001Vo> setError(BizException biz, Response<ResLufax800001Vo> res) {
		res.setRepCode(biz.getRealCode());
		res.setRepMsg(biz.getMessage());
		return res;
	}

	@Override
	public Response<ResLujsInformVo> dealWithAuditResult(ReqLujsInformVo lujsInformVo) {
		return interfaceService.getAuditReturnResult(lujsInformVo);
	}
}
