package com.ymkj.cms.web.boss.controller.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.web.boss.common.MenuTreeJson;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.service.menus.IPmsMenusService;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Controller
public class IndexController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private IPmsMenusService pmsMenusService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

  /**
   * 返回左边菜单
   * 
   * @author dongmingzhi
   * @date 2016年12月14日
   * @return
   */
  @RequestMapping(value = "index/leftMenu", method = RequestMethod.GET)
  public String leftMenu() {
    return "common/leftMenu";
  }

  @RequestMapping("dataGrid")
  public String dataGrid() {
    return "datagrid";
  }

  /**
   * 获取渠道树
   * 
   * @param reqBMSChannelVO
   * @return
   */
  @RequestMapping(value = "index/findMenuTree")
  @ResponseBody
  public Object findMenuTree() {
    // 获取当前登录用户
    ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
    // 获取登录用户工号
    String usercode = currentUser.getUsercode();
    // 返回动态菜单，替换静态菜单
    ReqEmployeeVO vo = new ReqEmployeeVO();

    // 业务系统编码
    vo.setSysCode(SystemCode.SysCode);
    // 业务类别
    vo.setBizType("");
    // 员工工号
    vo.setUsercode(usercode);
    // 菜单树形结构生成
    MenuTreeJson menus = pmsMenusService.findMenuTree(vo);
    updateMenu(menus);
    return menus;
  }

  public void updateMenu(MenuTreeJson menu) {
    java.util.List<MenuTreeJson> menus = menu.getChildren();
    for (int i = 0; i < menus.size(); i++) {
      if (!menus.get(i).isHasChildren() && menus.get(i).getName().equals("申请书管理")) {
        menus.get(i).setUrl("/appForm/view");
      }
      if (!menus.get(i).isHasChildren() && menus.get(i).getName().equals("批次管理")) {
        menus.get(i).setUrl("/batchMang/batchMangView");
      }
      if (!menus.get(i).isHasChildren() && menus.get(i).getName().equals("生成批次")) {
        menus.get(i).setUrl("/batchMang/batchCrtView");
      } else {
        updateMenu(menus.get(i));
      }
    }
  }
}
