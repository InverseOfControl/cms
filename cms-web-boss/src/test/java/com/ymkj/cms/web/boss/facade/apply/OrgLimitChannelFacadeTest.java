package com.ymkj.cms.web.boss.facade.apply;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.test.AbstractTestCase;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.common.TreeJson;
import com.ymkj.cms.web.boss.facade.apply.OrgLimitChannelFacade;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationTreeVO;

public class OrgLimitChannelFacadeTest extends AbstractTestCase{
	
	// json 工具类
	private Gson gson = new Gson();
		
    // 请求实体
	private ReqOrganizationVO reqOrganizationVo = new ReqOrganizationVO();
		
	@Autowired
	private OrgLimitChannelFacade orgLimitChannelFacade;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrgTree() {
		ReqParamVO vo = new ReqParamVO();
		ResOrganizationTreeVO result = orgLimitChannelFacade.findOrgTree(vo);
		
		
		System.out.println("-------------结果: "+gson.toJson(result));
		// 返回结果处理
		//树结构展开成平铺数据，treeJson
		List<TreeJson> s = tileTreeDate(result);
		for (TreeJson treeJson : s) {
			System.out.println(treeJson.getText());
		}
		System.out.println("ok");
	}

	private List<TreeJson> tileTreeDate(ResOrganizationTreeVO result) {
		// TODO Auto-generated method stub
		List<TreeJson> s = new ArrayList<TreeJson>();
		//根节点加入
		TreeJson root = new TreeJson();
		root.setId(result.getId().toString());
		root.setText(result.getText());
		root.setPid(result.getParentId());
		root.setDeep("0");
		root.setIconCls(result.getIconCls());
		s.add(root);
		tileTree(result.getChildren(),s);
		return s;
	}

	private void tileTree(List<ResOrganizationTreeVO> result, List<TreeJson> s) {
		for (ResOrganizationTreeVO vo : result) {
			TreeJson node = new TreeJson();
			node.setId(vo.getId().toString());
			node.setText(vo.getText());
			node.setPid(vo.getParentId());
//			node.setDeep("0");
			node.setIconCls(vo.getIconCls());
			s.add(node);
			//子节点判断
			if(!vo.getChildren().isEmpty()){
				//判断
				tileTree(vo.getChildren(),s);
			}
		}
	}
	

}
