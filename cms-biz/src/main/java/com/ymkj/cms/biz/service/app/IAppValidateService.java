package com.ymkj.cms.biz.service.app;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.app.Req_VO_600005;

public interface IAppValidateService{
	
	public Response<Object> validate(Req_VO_600005 vo_600005) throws Exception;
}
