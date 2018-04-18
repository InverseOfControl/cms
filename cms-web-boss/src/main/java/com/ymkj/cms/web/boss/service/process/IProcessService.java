package com.ymkj.cms.web.boss.service.process;

import com.bstek.uflo.service.StartProcessInfo;

public interface IProcessService {
	

		/**
		 * 开始流程
		 * @param proType
		 * @param variables
		 */
		public void startProcessByName(String proType,StartProcessInfo startProcessInfo);
		
	
		
}
