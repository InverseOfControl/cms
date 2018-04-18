package com.ymkj.cms.web.boss.common;

import java.util.ArrayList;
import java.util.List;

import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationTreeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

public class TreeUtil {
	/**
	 * 渠道产品树逻辑实现
	 * @param list
	 * @return
	 */
	public static List<TreeJson> formatChannelProductTree(List<TreeJson> list) {

        TreeJson root = new TreeJson();
        TreeJson node = new TreeJson();
        List<TreeJson> treelist = new ArrayList<TreeJson>();// 拼凑好的json格式的数据
        List<TreeJson> treeListTwo = new ArrayList<TreeJson>();
        List<TreeJson> treeListThree = new ArrayList<TreeJson>();
        if (list != null && list.size() > 0) {
       
          //循环遍历oracle树查询的所有节点
          //获取一级二级三级树，根为一级
          for (int i = 0; i < list.size(); i++) {
        	  node = list.get(i);
        	  if("1".equals(node.getDeep())){
                  root = node ; 
        	  }else if("2".equals(node.getDeep())){
                  //为tree root 增加子节点
            	  treeListTwo.add(node) ;
              }else if("3".equals(node.getDeep())){
            	  treeListThree.add(node);
              }        	  
          }
          //构建逻辑关系
          if(treeListTwo != null && treeListTwo.size()> 0){
        	  for (int i = 0; i < treeListTwo.size(); i++) {
            	  if(treeListThree != null && treeListThree.size()> 0){
            		  for(int j = 0; j < treeListThree.size(); j++){
            			  if(treeListThree.get(j).getPid().equals(treeListTwo.get(i).getId())){
            				  treeListTwo.get(i).getChildren().add(treeListThree.get(j));
            			  }
            		  }
            		  
            	  }
                  root.getChildren().add(treeListTwo.get(i)) ; 
              }        	  
          }
         
      }
        treelist.add(root) ;
        return treelist ;

    }
	
	/**
	 * 门店树
	 * @param list
	 * @return
	 */
	public static List<TreeJson> formatBusinessOrgTree(List<TreeJson> list) {

        TreeJson root = new TreeJson();
        TreeJson node = new TreeJson();
        List<TreeJson> treelist = new ArrayList<TreeJson>();// 拼凑好的json格式的数据
        List<TreeJson> treeListTwo = new ArrayList<TreeJson>();
        List<TreeJson> treeListThree = new ArrayList<TreeJson>();
        List<TreeJson> treeListfour = new ArrayList<TreeJson>();
        List<TreeJson> treeListFive = new ArrayList<TreeJson>();
        List<TreeJson> treeListSix = new ArrayList<TreeJson>();
        if (list != null && list.size() > 0) {
          //循环遍历oracle树查询的所有节点
          //获取一级二级三级树，根为一级
          for (int i = 0; i < list.size(); i++) {
        	  node = list.get(i);
        	  if("1".equals(node.getDeep())){
                  root = node ; 
        	  }else if("2".equals(node.getDeep())){
                  //为tree root 增加子节点
            	  treeListTwo.add(node) ;
              }else if("3".equals(node.getDeep())){
            	  treeListThree.add(node);
              }else if("4".equals(node.getDeep())){
            	  treeListfour.add(node);
              }else if("5".equals(node.getDeep())){
            	  treeListFive.add(node);
              } else if("6".equals(node.getDeep())){
            	  treeListSix.add(node); 
              }     	  
          }
          //从小到大
          //构建深度为5子数据
          if(treeListFive != null && treeListFive.size()> 0){
        	  for (int i = 0; i < treeListFive.size(); i++) {
            	  if(treeListSix != null && treeListSix.size()> 0){
            		  for(int j = 0; j < treeListSix.size(); j++){
            			  if(treeListSix.get(j).getPid().equals(treeListFive.get(i).getId())){
            				  treeListFive.get(i).getChildren().add(treeListSix.get(j));
            			  }  
            		  }
            		  
            	  }
            	  
              }        	  
          }
          //构建深度为4的子数据
          if(treeListfour != null && treeListfour.size()> 0){
        	  for (int i = 0; i < treeListfour.size(); i++) {
            	  if(treeListFive != null && treeListFive.size()> 0){
            		  for(int j = 0; j < treeListFive.size(); j++){
            			  if(treeListFive.get(j).getPid().equals(treeListfour.get(i).getId())){
            				  treeListfour.get(i).getChildren().add(treeListFive.get(j));
            			  }  
            		  }
            		  
            	  }
            	  
              }        	  
          }
          //构建深度为3的子数据
          if(treeListThree != null && treeListThree.size()> 0){
        	  for (int i = 0; i < treeListThree.size(); i++) {
            	  if(treeListfour != null && treeListfour.size()> 0){
            		  for(int j = 0; j < treeListfour.size(); j++){
            			  if(treeListfour.get(j).getPid().equals(treeListThree.get(i).getId())){
            				  treeListThree.get(i).getChildren().add(treeListfour.get(j));
            			  }  
            		  }
            	  }
            	  
              }        	  
          }
          //构建深度为2的子数据
          if(treeListTwo != null && treeListTwo.size()> 0){
        	  for (int i = 0; i < treeListTwo.size(); i++) {
            	  if(treeListThree != null && treeListThree.size()> 0){
            		  for(int j = 0; j < treeListThree.size(); j++){
            			  if(treeListThree.get(j).getPid().equals(treeListTwo.get(i).getId())){
            				  treeListTwo.get(i).getChildren().add(treeListThree.get(j));
            			  }
            		  }
            		  
            	  }
                  root.getChildren().add(treeListTwo.get(i)) ; 
              }        	  
          }
         
      }
        treelist.add(root) ;
        return treelist ;

    }
	/**
	 * 菜单树生成
	 * @param treeDataList
	 * @return
	 */
	public static MenuTreeJson formatMenuTree(List<MenuTreeJson> treeDataList) {
		//根节点是否找到
		MenuTreeJson root = null;
		if(treeDataList != null) {//空判断
			MenuTreeJson node = new MenuTreeJson();
			for(MenuTreeJson menu : treeDataList) {//根节点查找
				if("0".equals(menu.getDeep()))  {
					root = menu;
					break;
				}
			}
			//下级节点查找
			if(root != null){
				getChildrenNodes(root, treeDataList);
			}
            
        }
        return root;
	}
	/**
	 * 通过回调生成菜单树
	 * @param root
	 * @param treeDataList
	 */
	private static void getChildrenNodes(MenuTreeJson root, List<MenuTreeJson> treeDataList) {
		List<MenuTreeJson> menuList = new ArrayList<MenuTreeJson>();
		for (MenuTreeJson menu : treeDataList) {
			if(root.getCode().equals(menu.getParentCode())){//子节点判断
				//递归查找
				getChildrenNodes(menu,treeDataList);
				menuList.add(menu);
			}
		}
		if(!menuList.isEmpty()){
			root.setChildren(menuList);//子节点加入
			root.setHasChildren(true);//修改父级节点，含有子节点标识
		}
	}
	/**
	 * 树结构展开成平铺数据，treeJson
	 * @param deep 
	 * @param result
	 * @return
	 */
	public static List<TreeJson> tileTreeDate(ResOrganizationTreeVO treeVo, Integer deep) {
		List<TreeJson> treeList = new ArrayList<TreeJson>();
		//根节点加入
		TreeJson root = new TreeJson();
		root.setId(treeVo.getId().toString());
		root.setText(treeVo.getText());
		root.setPid(treeVo.getParentId());
		root.setDeep(deep+"");
		root.setIconCls(treeVo.getIconCls());
		treeList.add(root);
		tileTree(treeVo.getChildren(),treeList, deep);
		return treeList;
	}
	/**
	 * 利用deep值复制的方式，保证在递归结束，每个层次值不表
	 * @param treeVo
	 * @param treeList
	 * @param deep
	 */
	public static void tileTree(List<ResOrganizationTreeVO> treeVo, List<TreeJson> treeList, int deep) {
		//深度增加
		deep++;
		for (ResOrganizationTreeVO vo : treeVo) {
			TreeJson node = new TreeJson();
			node.setId(vo.getId().toString());
			node.setText(vo.getText());
			node.setPid(vo.getParentId());
			node.setDeep(deep+"");
			node.setIconCls(vo.getIconCls());
			treeList.add(node);
			//子节点判断
			if(!vo.getChildren().isEmpty()){
				//判断
				tileTree(vo.getChildren(),treeList, deep);
			}
		}
	}

	public static List<TreeJson> formatOrgToTreeJson(List<ResOrganizationVO> result) {
		List<TreeJson> treeList = new ArrayList<TreeJson>();
		for (ResOrganizationVO vo : result) {
			TreeJson node = new TreeJson();
			node.setId(vo.getId().toString());
			node.setText(vo.getName());
			node.setPid(vo.getParentId());
			node.setDeep(vo.getDepLevel());
			treeList.add(node);
		}
		return treeList;
	}

	public static void tileShowTree(String baseId, List<TreeJson> treeOrgList,
			List<TreeJson> treeOrgShowList) {
		for(TreeJson limitTemp : treeOrgList){
			if(limitTemp.getId().equals(baseId)){
				if(!treeOrgShowList.contains(limitTemp)){
					treeOrgShowList.add(limitTemp);
				}
				if(StringUtils.isNotEmpty(limitTemp.getPid())){
					baseId = limitTemp.getPid();
					TreeUtil.tileShowTree(baseId, treeOrgList, treeOrgShowList);
				}
			}
		}
		
	}
	
//  if (list != null && list.size() > 0) {
//  root = list.get(0) ;
//  //循环遍历oracle树查询的所有节点
//  for (int i = 1; i < list.size(); i++) {
//      node = list.get(i);
//      if(node.getPid().equals(root.getId())){
//          //为tree root 增加子节点
//          parentnodes.add(node) ;
//          root.getChildren().add(node) ;
//      }else{//获取root子节点的孩子节点
//          getChildrenNodes(parentnodes, node);
//          parentnodes.add(node) ;
//      }
//  }    
//}
//    private static void getChildrenNodes(List<TreeJson> parentnodes, TreeJson node) {
//        //循环遍历所有父节点和node进行匹配，确定父子关系
//        for (int i = parentnodes.size() - 1; i >= 0; i--) {
//            
//            TreeJson pnode = parentnodes.get(i);
//            //如果是父子关系，为父节点增加子节点，退出for循环
//            if (pnode.getId().equals(node.getPid())&&"2".equals(pnode.getDeep())&&"3".equals(node.getDeep())) {
//                pnode.setState("closed") ;//关闭二级树
//                pnode.getChildren().add(node) ;
//               // return ;
//            } else {
//                //如果不是父子关系，删除父节点栈里当前的节点，
//                //继续此次循环，直到确定父子关系或不存在退出for循环
//                parentnodes.remove(i) ;
//            }
//        }
//    }
}
