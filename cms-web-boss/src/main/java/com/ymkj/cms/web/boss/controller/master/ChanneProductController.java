package com.ymkj.cms.web.boss.controller.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.common.TreeJson;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IChannelProductService;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Controller
@RequestMapping("channeProduct")
public class ChanneProductController extends BaseController {

	@Autowired
	private IChannelService channelService;
	@Autowired
	private IChannelProductService chaProService;

	@RequestMapping("view")
	public String view() {
		return "master/channelProduct/showList";
	}

	@RequestMapping("channelShow")
	public String channelShow(Integer channelId, HttpServletRequest request) {
		request.setAttribute("channelId", channelId);
		return "master/channelProduct/showList";
	}

	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSOrgLimitChannelVO> listPage(HttpServletRequest request,ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		if (reqBMSOrgLimitChannelVO.getPage() == 0 || reqBMSOrgLimitChannelVO.getRows() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		/*String channelId=request.getParameter("channelId");
		if(channelId!=null && !channelId.equals("")){
			reqBMSOrgLimitChannelVO.setChannelId(Long.parseLong(channelId));
		}*/
		
		// 必须 设置请求编码
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		PageResult<ResBMSOrgLimitChannelVO> pageResult = chaProService.listPage(reqBMSOrgLimitChannelVO);
		ResponsePage<ResBMSOrgLimitChannelVO> pageList = new ResponsePage<ResBMSOrgLimitChannelVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	@RequestMapping(value = "updateChannelLimit")
	@ResponseBody
	public Map<String, Object> updateChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		boolean updateSuccess = chaProService.updateChannelLimit(reqBMSOrgLimitChannelVO);
		ShiroUtils.getAccount();
		
		
		hashMap.put("isSuccess", updateSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "saveChannelLimit")
	@ResponseBody
	public Map<String, Object> saveChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		// 调用新增接口
		boolean addSuccess = chaProService.saveChannelProduct(reqBMSOrgLimitChannelVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	/**
	 * 获取所有未删除的渠道信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findChannel")
	@ResponseBody
	public Object findChannel(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(SystemCode.SysCode);
		List<ResBMSChannelVO> list = new ArrayList<>();
		list = channelService.getAllChannel(reqBMSChannelVO);
		return list;
	}

	/**
	 * 获取所有未删除的渠道信息但要符合起止时间和结束时间是当天的
	 * 
	 * @return
	 */
	@RequestMapping(value = "findChannelEqDate")
	@ResponseBody
	public Object findChannelEqDate(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(SystemCode.SysCode);
		List<ResBMSChannelVO> list = new ArrayList<>();
		list = channelService.findChannelEqDate(reqBMSChannelVO);
		return list;
	}
	/**
	 * 获取所有未删除的产品
	 * 
	 * @return
	 */
	@RequestMapping(value = "findProduct")
	@ResponseBody
	public Object findProduct(ReqBMSProductVO reqBMSProductVO) {
		reqBMSProductVO.setSysCode(SystemCode.SysCode);
		List<ResBMSProductVO> list = new ArrayList<>();
		try {
			list = chaProService.findAllProduct(reqBMSProductVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取产品树
	 * 
	 * @param reqBMSProductVO
	 * @return
	 */
	@RequestMapping(value = "findProductTree")
	@ResponseBody
	public Object findProductTree(ReqBMSProductVO reqBMSProductVO) {
		reqBMSProductVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = chaProService.findChannelProTree(reqBMSProductVO);
		return list;
	}

	/**
	 * 获取渠道树
	 * 
	 * @param reqBMSChannelVO
	 * @return
	 */
	@RequestMapping(value = "findChannelTree")
	@ResponseBody
	public Object findChannelTree(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = chaProService.findChannelTree(reqBMSChannelVO);
		return list;
	}
	/**
	 * 渠道产品启用检测
	 * @param reqBMSLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午5:04:42
	 */
	@RequestMapping(value = "channelLimitDisableCheck")
	@ResponseBody
	public Map<String, Object> channelLimitDisableCheck(ReqBMSLimitChannelVO reqBMSLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		reqBMSLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSLimitChannelVO.setServiceName(currentUser.getName());
		return chaProService.channelLimitDisableCheck(reqBMSLimitChannelVO);
	}
}
