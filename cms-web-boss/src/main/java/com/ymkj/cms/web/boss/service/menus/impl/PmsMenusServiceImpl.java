package com.ymkj.cms.web.boss.service.menus.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.cms.web.boss.common.MenuTreeJson;
import com.ymkj.cms.web.boss.common.TreeUtil;
import com.ymkj.cms.web.boss.facade.menus.PmsMenusFacade;
import com.ymkj.cms.web.boss.service.menus.IPmsMenusService;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResResourceVO;

@Service
public class PmsMenusServiceImpl implements IPmsMenusService {

	@Autowired
	private PmsMenusFacade pmsMenusFacade;
	
	@Override
	public List<ResResourceVO> getMenusByAccount(ReqEmployeeVO reqEmployeeVO) {
		return pmsMenusFacade.findMenusByAccount(reqEmployeeVO);
	}

	@Override
	public MenuTreeJson findMenuTree(ReqEmployeeVO reqEmployeeVO) {
		List<MenuTreeJson> treeDataList = new ArrayList<MenuTreeJson>();
		//List<ResResourceVO>封装成树List<TreeJson> 
		//设置根目录
		MenuTreeJson rootData = new MenuTreeJson();
		rootData.setCode("");
        rootData.setName("菜单树根");
        rootData.setDeep("0");
        treeDataList.add(rootData);
		//渠道
        List<ResResourceVO> resultList = this.getMenusByAccount(reqEmployeeVO);
        for (ResResourceVO menu : resultList) {
        	MenuTreeJson treeData = new MenuTreeJson();
        	treeData.setCode(menu.getCode());//菜单code
        	treeData.setName(menu.getName());//菜单名称
            treeData.setParentCode(menu.getParentCode()==null?"":menu.getParentCode());//父菜单code,关联根节点
            treeData.setUrl(menu.getUrl());//访问地址
            treeData.setIcon(menu.getIcon());//显示图标
            treeData.setSystemCode(menu.getSystemCode());//系统编码
            treeDataList.add(treeData);
        }
        MenuTreeJson newTreeDataList = TreeUtil.formatMenuTree(treeDataList);
		return newTreeDataList;
	}

}
