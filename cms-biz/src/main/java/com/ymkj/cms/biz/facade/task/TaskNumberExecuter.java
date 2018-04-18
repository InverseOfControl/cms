package com.ymkj.cms.biz.facade.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.task.ITaskNumberExecuter;
import com.ymkj.cms.biz.api.vo.request.task.PersonCodeAndRoleVo;
import com.ymkj.cms.biz.api.vo.request.task.ReqBMSTaskNumberVo;
import com.ymkj.cms.biz.api.vo.response.task.ResBMSTaskNumberVo;
import com.ymkj.cms.biz.api.vo.response.task.TaskNumberQueVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.task.IBMSTaskNumberService;

/**
 * 任务数实现类
 * @author YM10166
 *
 */
@Service
public class TaskNumberExecuter implements ITaskNumberExecuter {
	
	public Logger logger = LoggerFactory.getLogger(TaskNumberExecuter.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private IBMSTaskNumberService bmsTaskNumberService;

	@Override
	public ResBMSTaskNumberVo queryTaskNumberQues(ReqBMSTaskNumberVo reqVo) {
		logger.info("--------------请求开始--------------");
		ResBMSTaskNumberVo resVo = new ResBMSTaskNumberVo();
		if(reqVo != null) {
			List<PersonCodeAndRoleVo> personCoAndRos = reqVo.getPersonCoAndRos();
			//工号、审核节点列表请求数据验证
			if (personCoAndRos == null || personCoAndRos.isEmpty()) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "personCoAndRos" });
			}
			List<TaskNumberQueVo> taskNumQues = new ArrayList<TaskNumberQueVo>();
			for (PersonCodeAndRoleVo personCoAndRo : personCoAndRos) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("personCode", personCoAndRo.getPersonCode()); //工号
				map.put("personRole", personCoAndRo.getPersonRole()); //审核节点（角色：1：初审；2：终审）
				map.put("ifNewLoanNo", "1"); //ifNewLoanNo字段为1，新生件对应正常队列
				Integer normalQueue = bmsTaskNumberService.queryNormalQueCount(map); //查询正常队列
				
				Integer priorityQueue = 0;
				Integer hangQueue = 0;
				if(StringUtils.equals(personCoAndRo.getPersonRole(), "1")) { //初审角色
					
					map.put("ifNewLoanNo", "0"); //ifNewLoanNo字段为0，非新生件对应优先队列
					map.put("rtfStatus", EnumConstants.RtfState.XSCS.getValue()); //流程状态为信审初审
					map.put("rtfNodeState", EnumConstants.RtfNodeState.XSCSASSIGN.getValue()); //流程节点状态为信审初审办理
					priorityQueue = bmsTaskNumberService.queryPriorityQueCount(map); //查询优先队列
					
					map.put("rtfNodeState", EnumConstants.RtfNodeState.XSCSHANGUP.getValue()); //流程节点状态为初审分配挂起
					hangQueue = bmsTaskNumberService.queryPendingQueCount(map); //查询挂起队列
					
				} else if (StringUtils.equals(personCoAndRo.getPersonRole(), "2")) {  //终审角色
					
					map.put("ifNewLoanNo", "0"); //ifNewLoanNo字段为0，非新生件对应优先队列
					map.put("rtfStatus", EnumConstants.RtfState.XSZS.getValue()); //流程状态为信审终审
					map.put("rtfNodeState", EnumConstants.RtfNodeState.XSZSASSIGN.getValue()); //流程节点状态为信审终审办理
					priorityQueue = bmsTaskNumberService.queryPriorityQueCount(map); //查询优先队列
					
					map.put("rtfNodeState", EnumConstants.RtfNodeState.XSZSHANGUP.getValue()); //流程节点状态为终审分配挂起
					hangQueue = bmsTaskNumberService.queryPendingQueCount(map); //查询挂起队列
				}
				//返回对象，除正常队列、优先队列、挂起队列以外，其他属性按请求对象原样值返回
				TaskNumberQueVo  taskNumberQueVo = new TaskNumberQueVo();
				taskNumberQueVo.setPersonRole(personCoAndRo.getPersonRole());
				taskNumberQueVo.setPersonCode(personCoAndRo.getPersonCode());
				taskNumberQueVo.setPersonName(personCoAndRo.getPersonName());
				taskNumberQueVo.setPartenOrg(personCoAndRo.getPartenOrg());
				taskNumberQueVo.setOrg(personCoAndRo.getOrg());
				taskNumberQueVo.setNormalQueue(normalQueue);
				taskNumberQueVo.setPriorityQueue(priorityQueue);
				taskNumberQueVo.setHangQueue(hangQueue);
				taskNumberQueVo.setIsaccept(personCoAndRo.getIsaccept());
				taskNumberQueVo.setPartenOrgCode(personCoAndRo.getPartenOrgCode());
				taskNumberQueVo.setOrgCode(personCoAndRo.getOrgCode());
				taskNumQues.add(taskNumberQueVo);
			}
			resVo.setTaskNumQues(taskNumQues);
		} else {
			logger.info("reqBMSTaskNumberVo请求对象为空" + gson.toJson(reqVo));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSTaskNumberVo" });
		}
		logger.info("--------------请求结束--------------");
		return resVo;
	}

}
