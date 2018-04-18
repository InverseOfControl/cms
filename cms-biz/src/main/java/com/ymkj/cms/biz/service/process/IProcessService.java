package com.ymkj.cms.biz.service.process;

import java.util.List;
import java.util.Map;

import com.bstek.uflo.model.variable.Variable;
import com.bstek.uflo.query.ProcessInstanceQuery;
import com.bstek.uflo.query.ProcessQuery;
import com.bstek.uflo.query.ProcessVariableQuery;
import com.bstek.uflo.service.StartProcessInfo;

public interface IProcessService {
	

		/**
		 * 根据流程名称开启流程
		 * @param proType
		 * @param variables
		 */
		public void startProcessByName(String proType,StartProcessInfo startProcessInfo);
		
		
		/**
		  * 向指定流程实例ID对应的流程实例中添加流程变量
		  * @param processInstanceId 流程实例ID
		  * @param key 流程变量的key
		  * @param value 对应的流程变量的值
		  */
		 void saveProcessVariable(long processInstanceId,String key,Object value);
		 
		 /**
		  * 向指定流程实例ID对应的流程实例中批量添加流程变量
		  * @param processInstanceId 流程实例ID
		  * @param variables 要添加的流程变量的Map
		  */
		 void saveProcessVariables(long processInstanceId,Map<String,Object> variables);
		  
		 /**
		  * @return 返回创建成功的流程实例查询对象
		  */
		 public boolean deleteProcessInstanceByLoanBaseId(String bussnesId);
		 /**
		  * @return 返回创建成功的流程变量查询对象
		  */
		 ProcessVariableQuery createProcessVariableQuery();
		  
		 /**
		  * @return 创建创建成功的流程模版查询对象
		  */
		 ProcessQuery createProcessQuery();
		 
		 
		 /**
		  * @return 查询流程当前流程的渠道
		  */
		 public String getChannelCdById(String bussnesId);
	
		 
		 
		 public  List<Variable> getProcessVariables(long processId);


		void saveVariableByLoanBseId(long loanBaseId, Map<String, Object> variables);

		/**
		 * 获取流程的key值
		 * @param processId
		 * @return
		 */
		public String getProcessKey(long processId);
		
}
