package com.ymkj.cms.web.boss.facade.menus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResResourceVO;

@Component
public class PmsMenusFacade extends BaseFacade{
	
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	
	/**
	 * 1. 获取所有菜单集合
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResResourceVO> findMenusByAccount(ReqEmployeeVO ReqEmployeeVO) {
		// 请求参数构造
		Response response = iEmployeeExecuter.findMenusByAccount(ReqEmployeeVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return (List<ResResourceVO>) response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
}
