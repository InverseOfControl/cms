package com.ymkj.cms.biz.service.client;

import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;

/**
 * 行为库系统调用
 * @author YM10152
 *
 */
public interface BDSClientService {
	//查询当前用户是否疑似欺诈
	public Integer ifSuspectCheat(BMSAppPersonInfo personInfo);

}
