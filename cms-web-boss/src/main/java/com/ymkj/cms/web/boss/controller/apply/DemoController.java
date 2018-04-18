package com.ymkj.cms.web.boss.controller.apply;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.JsonResult;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.apply.IDemoService;

@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {

	@Autowired
	private IDemoService demoService;

	/**
	 * 返回视图类型的处理方法 \n 
	 * 1. 视图根目录的配置 在 spring-web中 
	 * 2. 返回值中的 demo 表示业务模块包名
	 * 3. 返回值中的 index 表示返回的视图文件名, 无需加后缀
	 * @return
	 */
	@RequestMapping("view")
	public String view() {
		return "demo/index";
	}

	/**
	 * 返回json类型的处理方法
	 * 1. 第一步 参数校验 (第一种方式)
	 * 2. 第二步 业务调用
	 * 3. 第三步 结果处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<ResDemoVO> findById(Long id) {
		System.out.println(1122);
		// 请求参数校验 (第一种方式), 第二种方式参见 findByName;
		if (id == null) {
			return this.fail(CoreErrorCode.REQUEST_PARAM_ISNULL.getErrorCode(),
					CoreErrorCode.REQUEST_PARAM_ISNULL.getDefaultMessage(), new Object[]{"id"});
		}
		// 业务层调用
		ResDemoVO resDemoVO = demoService.findById(id);

		// 返回处理结果
		return this.success(resDemoVO);
	}
	/**
	 * 返回json类型的处理方法
	 * 1. 第一步 参数校验 (第二种方式)
	 * 2. 第二步 业务调用
	 * 3. 第三步 结果处理
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "findByName")
	@ResponseBody
	public JsonResult<ResDemoVO> findByName(String name) {

		// 请求参数校验(第二种方式), 推荐使用
		if (StringUtils.isBlank(name)) {
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		ResDemoVO resDemoVO = demoService.findByName(name);
		
		return this.success(resDemoVO);
	}
	/**
	 * 分页查询处理方法 :
	 * 1. 用  @RequestBody 注解时, 请求头 Content-Type 必须为  application/json 格式
	 * @param reqDemoVO
	 * @return
	 */
	@RequestMapping(value = "listPage", method = {RequestMethod.POST })
	@ResponseBody
	public PageResult<ResDemoVO> listPage(ReqDemoVO reqDemoVO){
		
		if(reqDemoVO.getPageNum() == 0 || reqDemoVO.getPageSize() == 0){
			// 数组参数 必须 与  错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		//必须 设置请求编码
		reqDemoVO.setSysCode("1111111111");
		
		PageResult<ResDemoVO> pageResult = demoService.listPage(reqDemoVO);
		return pageResult;
	}
	@RequestMapping(value = "saveOrUpdate", method = {RequestMethod.POST })
	@ResponseBody
	public JsonResult<Long> saveOrUpdate(ReqDemoVO reqDemoVO){
		
		if(StringUtils.isBlank(reqDemoVO.getName()) || StringUtils.isBlank(reqDemoVO.getAddress())){
			// 数组参数 必须 与  错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name | address"});
		}
		//必须 设置请求编码
		reqDemoVO.setSysCode("1111111111");
		
		Long id = demoService.saveOrUpdate(reqDemoVO);
		return this.success(id);
	}

}
