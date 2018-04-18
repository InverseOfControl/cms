package com.ymkj.cms.web.boss.service.process.impl;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.ymkj.cms.web.boss.service.process.IProcessService;



@Service
public class ProcessServiceImpl implements IProcessService{
	
	@Resource(name=ProcessService.BEAN_ID)
	private ProcessService processService;

	/**
	 * 开始流程
	 * @param proType
	 * @param variables
	 */
	public void startProcessByName(String proname,StartProcessInfo startProcessInfo){
		processService.startProcessByName(proname, startProcessInfo);
    }
	
	
		
			
}
