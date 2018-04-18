package com.ymkj.cms.biz.facade.master;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSContractChangeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.master.BMSContractChange;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.http.ILuJSHttpService;
import com.ymkj.cms.biz.service.master.IBMSContractChangeService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.IProcessService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.IBMSLoanChannelLockTargetService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class BMSContractChangeExecuter implements IBMSContractChangeExecuter {

	@Autowired
	private IBMSContractChangeService contractChangeService;
	@Autowired
	ITaskService iTaskService;
	@Autowired
	IEmployeeExecuter employeeExecuter;
	@Autowired
	IBMSSysLogService sysLogService;
	@Autowired
	private IProcessService processService;
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Autowired
	private IBMSSysLoanLogService ibmsSysLoanLogService;
	@Autowired
	private LUJSInterfaceService luJSInterfaceService;
	@Autowired
	private ILuJSInformService luJSInformService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private IBMSLoanExtEntityDao loanExtEntityDao;
	
	@Override
	public PageResponse<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo) {

		PageResponse<ResBMSContractChangeVo> response = new PageResponse<ResBMSContractChangeVo>();

		try {
			// 参数校验
			if (reqBMSContractChangeVo.getPageNum() == 0 || reqBMSContractChangeVo.getPageSize() == 0) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
			}
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSContractChangeVo.getPageNum(),
					reqBMSContractChangeVo.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSContractChangeVo);
			//空值null
			for (String key : paramMap.keySet()) {
				if("".equals(paramMap.get(key))){
					paramMap.put(key, null);
				}
			}
			// 调用业务逻辑
			PageBean<BMSContractChange> pageBean = contractChangeService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResBMSContractChangeVo> records = new ArrayList<ResBMSContractChangeVo>();
			List<BMSContractChange> demoEntitys = pageBean.getRecords();
			for (BMSContractChange demoEntity : demoEntitys) {
				ResBMSContractChangeVo resDemoVO = new ResBMSContractChangeVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSContractChangeVo> changeContract(ReqBMSContractChangeVo reqVo) {
		Response<ResBMSContractChangeVo> response = new Response<ResBMSContractChangeVo>();
		
		ResBMSContractChangeVo resVo = new ResBMSContractChangeVo();
		List<String> isFailedLoanNoList = new ArrayList<String>();
		try {
			// 验证选中数据是否有效
			if (reqVo.getLoanNos() == null || reqVo.getLoanNos().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "loanNos" });
			}
			if (reqVo.getCustomerServiceList() == null || reqVo.getCustomerServiceList().isEmpty()){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "customerServiceList" });
			}
			if (reqVo.getContractBranchId() == null || reqVo.getContractBranchId().length() == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "contractBranchId" });
			}
			String[] loanNos = StringUtils.split(reqVo.getLoanNos(), ",");
			if (loanNos == null || loanNos.length == 0) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "loanNos" });
			}
			if(reqVo.getSignCodeRejects() != null && reqVo.getSignCodeRejects().length() > 0){
				String[] singCodeRejects = StringUtils.split(reqVo.getSignCodeRejects(), ",");
				if (singCodeRejects == null || singCodeRejects.length == 0) {
					throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "singCodeRejects" });
				}
				List<String> singCodeRejectList = new ArrayList<String>();
				for (String singCodeReject : singCodeRejects) {
					singCodeRejectList.add(singCodeReject);
				}
				reqVo.setSignCodeRejectList(singCodeRejectList);
			}
			// 人员更新
			int deleteNum = contractChangeService.deleteContractChangeFrequency(reqVo.getCustomerServiceList(),
					Long.valueOf(reqVo.getContractBranchId()));
			if (deleteNum > 0) {

				sysLogService.recordSysLog(reqVo, "签约管理|签约改派|平均分配|delete",
						"IBMSContractChangeService/deleteContractChangeFrequency",
						"人员更新数：" + deleteNum);
			}
			// 存在新人执行插入
			int insertNum = contractChangeService.insertContractChangeFrequency(reqVo.getCustomerServiceList(),
					Long.valueOf(reqVo.getContractBranchId()));
			if (insertNum > 0) {

				sysLogService.recordSysLog(reqVo, "签约管理|签约改派|平均分配|insert",
						"IBMSContractChangeService/deleteContractChangeFrequency",
						"存在新人执行插入数：" + insertNum);
			}

			for (String loanNo : loanNos) {
				//当单子，产品，期限，改派网点必有
				// 验证改派网点产品期限是存在可用渠道下的网点产品期限配置，，不考虑渠道
				/*String orgId= reqVo.getContractBranchId();
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("orgId", orgId);
				params.put("loanNo", loanNo);
				BMSContractChange oldContractChange = contractChangeService.getProductIsDisabled(params);
				if (oldContractChange == null) {
					isFailedLoanNoList.add(loanNo);
					continue;
				}*/
				// 存在验证
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanNo", loanNo);
				BMSLoanBaseEntity loanBaseEntity = loanBaseEntityDao.getBy(paramMap);
				if (loanBaseEntity == null) {
					isFailedLoanNoList.add(loanNo);
					continue;
				}
				String signCode = reqVo.getSignCode();
				String signName = reqVo.getSignName();
				Integer loanBaseId = Integer.valueOf(loanBaseEntity.getId().toString());
				Task task = taskService.findTaskByLoanBaseId(String.valueOf(loanBaseId));
				// 对签约门店进行变更了，关闭借款流程，签约改派后重签
				if(!reqVo.getContractBranchId().equals(loanBaseEntity.getContractBranchId())){
					if (EnumConstants.channelCode.BSYH.getValue().equals(loanBaseEntity.getChannelCd())) {
						//bms_loan_ext 表的字段 BY_STATE  0黑名单拒绝 1黑名单通过  通过需要发送取消请求
						if(1 <= Integer.valueOf(loanBaseEntity.getByState()==null?"-1":loanBaseEntity.getByState())){
							ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
							reqLoanContractSignVo.setLoanNo(loanBaseEntity.getLoanNo());
							
							Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
							//包银需要调用，取消接口
							if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)){
								// 处理成功 // 同意终止，通知核心
							} else {
								isFailedLoanNoList.add(loanBaseEntity.getLoanNo());
								continue;
							}
						}
						
						
					} else if(EnumConstants.channelCode.LUJS.getValue().equals(loanBaseEntity.getChannelCd())){
						//不允许跨门店改派:陆金所未返回人工审核结果
						// 流程任务校验
						if (task != null && EnumConstants.WF_RGSH_LUJS.equals(task.getTaskName())) {
							isFailedLoanNoList.add(loanBaseEntity.getLoanNo());
							continue;
						}
						//不允许跨门店改派:陆金所返回人工审核结果为补件
						Map<String, Object> lockTargetParamMap = new HashMap<String, Object>();
						lockTargetParamMap.put("loanNo", loanBaseEntity.getLoanNo());
						// 调用业务逻辑
						BMSLuJSInform luJSInform = luJSInformService.getBy(paramMap);
						if(luJSInform != null && EnumConstants.LuJSnotifyCode.XSQCL.getCode().equals(luJSInform.getInformResult())){
							//数据类型
							isFailedLoanNoList.add(loanBaseEntity.getLoanNo());
							continue;
						}
						//整个签约环节，陆金所，改派就不能用调撤销接口
						//有成功调用陆金所【借款申请信息提交接口】
						//当返回信审不通过，反欺诈拒绝时无需调用
						/*if(loanBaseEntity != null && loanBaseEntity.getLujsApplyNo() != null){
							//是否需要调用撤销接口，判断
							Map<String, Object> notifyLujsMap = new HashMap<String, Object>();
							notifyLujsMap.put("loanNo",loanBaseEntity.getLoanNo());
							notifyLujsMap.put("aps_apply_no",loanBaseEntity.getLujsApplyNo());
							notifyLujsMap.put("notify_code","2");
							Response res = new Response();
							//需要调用，取消接口
							if (luJSInterfaceService.requestNotifyLujs(notifyLujsMap, res)){
								// 处理成功 // 同意终止，通知核心
								
							} else {
								isFailedLoanNoList.add(loanBaseEntity.getLoanNo());
								continue;
							}
						}*/
					} else if(EnumConstants.channelCode.AITE.getValue().equals(loanBaseEntity.getChannelCd())){
						paramMap.clear();
						paramMap.put("loanBaseId", loanBaseEntity.getId());
						paramMap.put("channelCode", loanBaseEntity.getChannelCd());
						BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
						// 推送判断,推送后标的锁定
						if (lockTargetEntity !=null && "Y".equals(lockTargetEntity.getLockTarget())) {
							// 调用终止借款接口，待联调
							Map<String, String> requestMap = new HashMap<String, String>();
							requestMap.put("borrowNo", loanBaseEntity.getLoanNo());
							requestMap.put("reason", "签约改派");
							HttpResponse httpResponse = aiTeHttpService.terminationLoan(requestMap);
							if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
								String content = httpResponse.getContent();
								Map contentMap = JsonUtils.decode(content, Map.class);
								if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("repCode"))){
									// 处理成功 // 同意终止，通知核心
									ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo();
									reqLoanContractSignVo.setId(loanBaseEntity.getId());
									reqLoanContractSignVo.setLoanNo(loanBaseEntity.getLoanNo());
									reqLoanContractSignVo.setChannelCd(loanBaseEntity.getChannelCd());
									reqLoanContractSignVo.setServiceCode(reqVo.getServiceCode());
									reqLoanContractSignVo.setServiceName(reqVo.getServiceName());
									reqLoanContractSignVo.setSysCode(reqVo.getSysCode());
									
									//标的解锁锁定
									boolean notifyFalg = aiTeLoanContractService.discardLockTarget(reqLoanContractSignVo);
									if (!notifyFalg) {
										throw new BizException(BizErrorCode.DB_UPDATE_RESULT_0);
									}
									
									//推送值加一
									Map<String,Object> extUpdateParam = new HashMap<String,Object>();
									extUpdateParam.put("loanNo", loanBaseEntity.getLoanNo());
									extUpdateParam.put("channelPushFrequency", loanBaseEntity.getChannelPushFrequency()+1);
									loanExtEntityDao.updateBySatus(extUpdateParam);
								} else {
									throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"借款无法取消，请联系爱特确认是否已放款");
								}
							} else {
								throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"捞财宝地址访问失败");
							}
						}
					}
					if(task != null){
						// 删除流程实例和任务
						processService.deleteProcessInstanceByLoanBaseId(String.valueOf(loanBaseId));
					}
				} else {
					//非跨门店改派，修改流程人
					if(task != null){
						taskService.changeTaskAssigneeByLoanBaseId(String.valueOf(loanBaseId), signCode);
					}
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orgId", Long.valueOf(reqVo.getContractBranchId()));
				if(reqVo.getSignCodeRejectList() != null && !reqVo.getSignCodeRejectList().isEmpty()){
					map.put("signCodeRejectList", reqVo.getSignCodeRejectList());
				}

				if (StringUtils.isEmpty(signCode)) {// 未指派签约客服
					// 平均分配原则
					signCode = contractChangeService.getUserCodeFrequencyLow(map);
					// 依据code查name
					ReqEmployeeVO vo = new ReqEmployeeVO();
					vo.setSysCode(EnumConstants.BMS_SYSCODE);
					vo.setUsercode(signCode);
					Response<ResEmployeeVO> responseEmployee = employeeExecuter.findByAccount(vo);
					if (responseEmployee.isSuccess()) {
						ResEmployeeVO employee = responseEmployee.getData();
						signName = employee.getName();
					} else {
						throw new BizException(CoreErrorCode.REQUEST_PARAM_ERROR, "员工信息查询接口返回失败信息");
					}
				}
				// 改派次数加一
				map.put("userCode", signCode);
				Integer increaseNum = contractChangeService.increaseContractChangeFrequency(map);
				if (increaseNum > 0) {

					sysLogService.recordSysLog(reqVo, "签约管理|签约改派|平均分配|update",
							"IBMSContractChangeService/increaseContractChangeFrequency",
							"改派次数加一数：" + increaseNum);
				}

				// 获取loanbase表旧的版本号
				Integer oldVersion = contractChangeService.getLoanBaseVersion(loanBaseId);
				// 更新loanbase表的签约网点和签约客服字段
				map.clear();
				map.put("id", loanBaseId);
				map.put("contractBranchId", reqVo.getContractBranchId());
				map.put("contractBranch", reqVo.getContractBranch());
				map.put("signCode", signCode);
				map.put("signName", signName);
				map.put("version", oldVersion);
				Integer updateLoanBaseNum = contractChangeService.updateLoanBase(map);
				// 借款日志
				if (updateLoanBaseNum > 0) {
					//借款日志
					BMSSysLoanLog loanLog= new BMSSysLoanLog();
					loanLog.setLoanBaseId(loanBaseEntity.getId());
					loanLog.setOperationModule(EnumConstants.OptionModule.SIGN_CHANGE.getValue());
					loanLog.setLoanNo(loanBaseEntity.getLoanNo());
					loanLog.setOperationTime(new Date());
					loanLog.setOperator(reqVo.getServiceName());
					loanLog.setOperatorCode(reqVo.getServiceCode());
					loanLog.setStatus(loanBaseEntity.getStatus());
					loanLog.setRtfState(loanBaseEntity.getRtfState());
					loanLog.setRtfNodeState(loanBaseEntity.getRtfNodeState());
					loanLog.setOperationType(EnumConstants.OptionType.SIGN_ASSIGNMENT_CHANGE.getValue());
					
					String contractBranchOld = loanBaseEntity.getContractBranch()==null?"":loanBaseEntity.getContractBranch().toString();
					String contractBranchNew = reqVo.getContractBranch()==null?"":loanBaseEntity.getContractBranch().toString();
					
					String signNameOld = loanBaseEntity.getSignName()==null?"":loanBaseEntity.getSignName().toString();
					String signNameNew = signName==null?"":signName.toString();
					
					loanLog.setRemark("门店由【"+contractBranchOld+"】改派到【"+contractBranchNew+"】;客服由【"+signNameOld+"】改派到【"+signNameNew+"】");
					ibmsSysLoanLogService.saveOrUpdate(loanLog);
				}
				
				sysLogService.recordSysLog(reqVo, "签约管理|签约改派|平均分配|update",
						"IBMSContractChangeService/updateLoanBase",
						"借款主表更新数：" + updateLoanBaseNum);
			}
			resVo.setIsFailedLoanNoList(isFailedLoanNoList);
			response.setRepMsg("success");
			response.setRepCode(Response.SUCCESS_RESPONSE_CODE);
			response.setData(resVo);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

}
