package com.ymkj.cms.web.boss.controller.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.web.boss.common.RedisUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.common.TreeJson;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.IOutletsProductService;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.sso.client.ShiroUtils;

@Controller
@RequestMapping("outletsProduct")
public class OutletsProductController extends BaseController {

	@Autowired
	private IChannelService channelService;
	@Autowired
	private IOutletsProductService outProService;
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("view")
	public String view() {
		return "master/outletsProduct/showList";
	}

	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		if (reqBMSOrgLimitChannelVO.getPage() == 0 || reqBMSOrgLimitChannelVO.getRows() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		PageResult<ResBMSOrgLimitChannelVO> pageResult = outProService.listPage(reqBMSOrgLimitChannelVO);
		ResponsePage<ResBMSOrgLimitChannelVO> pageList = new ResponsePage<ResBMSOrgLimitChannelVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	/**
	 * 修改审批期限上下限以及是否可用
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@RequestMapping(value = "updateOrgProductLimit")
	@ResponseBody
	public Map<String, Object> updateOrgProductLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		boolean updateSuccess = outProService.updateOrgProductLimit(reqBMSOrgLimitChannelVO);
		hashMap.put("isSuccess", updateSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "saveOrgChannelLimit")
	@ResponseBody
	public Map<String, Object> saveOrgChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO,@RequestParam(value="orgid[]",required=false) List<Long> orgIds,@RequestParam(value="channelid[]",required=false) List<String> channelIds) {
		reqBMSOrgLimitChannelVO.setOrgIds(orgIds);
		reqBMSOrgLimitChannelVO.setChannelIds(channelIds);
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		// 调用新增接口
		boolean addSuccess = outProService.saveOrgChannelProduct(reqBMSOrgLimitChannelVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}
	@RequestMapping(value = "saveRateOrgChannelLimit")
	@ResponseBody
	public Map<String, Object> saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		// 调用新增接口
		boolean addSuccess = outProService.saveRateOrgChannelLimit(reqBMSOrgLimitChannelVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	/**
	 * 获取机构树
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "findAllDepts")
	@ResponseBody
	public List<ResOrganizationVO> findAllDepts(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		
		
		List<ResOrganizationVO> depts = new ArrayList<ResOrganizationVO>();
		if (redisUtil.exists("bms_all_depts")) {
			return (List<ResOrganizationVO>) redisUtil.get("bms_all_depts");
		}
		
		ReqOrganizationVO reqOrganizationVo = new ReqOrganizationVO();
		reqOrganizationVo.setSysCode(SystemCode.SysCode);
		
		//查询所有营业网店
		depts = outProService.findAllDepts(reqOrganizationVo);
		redisUtil.set("bms_all_depts", depts, 3600L);
		
		
		
		return depts;
		 
	}

	/**
	 * 获取所有未删除的营业网点
	 * 
	 * @return
	 */
	@RequestMapping(value = "findBusinessProduct")
	@ResponseBody
	public Object findBusinessProduct(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		List<ResBMSOrgLimitChannelVO> list = new ArrayList<>();
		try {
			// ResBMSOrgLimitChannelVO vo1 = new ResBMSOrgLimitChannelVO();
			// vo1.setOrgId(1L);
			// vo1.setOrgName("证大财富");
			// ResBMSOrgLimitChannelVO vo2 = new ResBMSOrgLimitChannelVO();
			// vo2.setOrgId(2L);
			// vo2.setOrgName("投资咨询");
			// ResBMSOrgLimitChannelVO vo3 = new ResBMSOrgLimitChannelVO();
			// vo3.setOrgId(3L);
			// vo3.setOrgName("东区");
			// ResBMSOrgLimitChannelVO vo4 = new ResBMSOrgLimitChannelVO();
			// vo4.setOrgId(4L);
			// vo4.setOrgName("上海分部");
			// ResBMSOrgLimitChannelVO vo5 = new ResBMSOrgLimitChannelVO();
			// vo5.setOrgId(5L);
			// vo5.setOrgName("上海");
			ResBMSOrgLimitChannelVO vo6 = new ResBMSOrgLimitChannelVO();
			vo6.setOrgId(6L);
			vo6.setOrgName("上海浦东大道营业部");
			ResBMSOrgLimitChannelVO vo7 = new ResBMSOrgLimitChannelVO();
			vo7.setOrgId(7L);
			vo7.setOrgName("上海张杨路营业部");
			list.add(vo6);
			list.add(vo7);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取网点树
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@RequestMapping(value = "findAllDeptsTree")
	@ResponseBody
	public Object findAllDeptsTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = outProService.findAllDeptsTree(reqBMSOrgLimitChannelVO);
		return list;
	}
	/**
	 * 获取网点树(优惠费率)
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@RequestMapping(value = "findAllDeptsRateTree")
	@ResponseBody
	public Object findAllDeptsRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = outProService.findAllDeptsRateTree(reqBMSOrgLimitChannelVO);
		return list;
	}

	/**
	 * 获取渠道树
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@RequestMapping(value = "findChannelProTree")
	@ResponseBody
	public Object findChannelProTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = outProService.findChannelProTree(reqBMSOrgLimitChannelVO);
		return list;
	}
	
	/**
	 * 获取渠道树(优惠费率)
	 * 
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	@RequestMapping(value = "findChannelProRateTree")
	@ResponseBody
	public Object findChannelProRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		List<TreeJson> list = outProService.findChannelProRateTree(reqBMSOrgLimitChannelVO);
		return list;
	}
	
	/**
	 * 网点产品启用检测
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午5:06:03
	 */
	@RequestMapping(value = "orgProductLimitDisableCheck")
	@ResponseBody
	public Map<String, Object> orgProductLimitDisableCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用新增接口
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqBMSOrgLimitChannelVO.setServiceCode(currentUser.getUsercode());
		reqBMSOrgLimitChannelVO.setServiceName(currentUser.getName());
		return outProService.orgProductLimitDisableCheck(reqBMSOrgLimitChannelVO);
	}
}
