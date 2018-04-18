package com.ymkj.cms.biz.api.vo.request.task;


import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 任务数接口请求实体
 * @author YM10166
 *
 */
public class ReqBMSTaskNumberVo extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4844348126119259387L;
	
	/**
	 * 工号、审核节点（角色）列表
	 */
	private List<PersonCodeAndRoleVo> personCoAndRos;

	public List<PersonCodeAndRoleVo> getPersonCoAndRos() {
		return personCoAndRos;
	}

	public void setPersonCoAndRos(List<PersonCodeAndRoleVo> personCoAndRos) {
		this.personCoAndRos = personCoAndRos;
	}

}
