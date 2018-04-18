package com.ymkj.cms.web.boss.service.menus;

import java.util.List;

import com.ymkj.cms.web.boss.common.MenuTreeJson;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResResourceVO;

public interface IPmsMenusService {
	/**
	 * 获取对应人员菜单
	 * @param reqEmployeeVO
	 * @return
	 */
	public List<ResResourceVO> getMenusByAccount(ReqEmployeeVO reqEmployeeVO);
	/**
	 * 获取人员菜单树
	 * @param vo
	 * @return
	 */
	public MenuTreeJson findMenuTree(ReqEmployeeVO reqEmployeeVO);
}
