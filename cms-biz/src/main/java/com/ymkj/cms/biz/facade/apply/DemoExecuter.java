package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.apply.IDemoExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.biz.entity.apply.DemoEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.IDemoService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
@Service
public class DemoExecuter implements IDemoExecuter {
	
	@Autowired
	private IDemoService demoService;

	@Override
	public Response<ResDemoVO> findById(ReqDemoVO reqDemoVO) {
		
		Response<ResDemoVO> response = new Response<ResDemoVO>();
		// 参数校验 
		if(reqDemoVO.getId() == null){
			//第一种方式(不会替换变量)
//			response = new Response<ResDemoVO>(CoreErrorCode.VALIDATE_ISNULL.getErrorCode(), 
//					CoreErrorCode.VALIDATE_ISNULL.getDefaultMessage());
//			return response;
			
			//第二种方式
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
		}
		// 调用业务逻辑
		DemoEntity demoEntity = demoService.getById(reqDemoVO.getId());
		
		// 构造相应参数
		if(demoEntity != null){
			ResDemoVO resDemoVO = new ResDemoVO();
			BeanUtils.copyProperties(demoEntity, resDemoVO);
			response.setData(resDemoVO);
		}
		
		return response;
	}

	@Override
	public Response<ResDemoVO> findByName(ReqDemoVO reqDemoVO) {
		
		Response<ResDemoVO> response = new Response<ResDemoVO>();
		
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqDemoVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		// 构造请求参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", reqDemoVO.getName());
		
		// 调用业务逻辑
		DemoEntity demoEntity =new DemoEntity();// demoService.getBy(param);
		demoEntity.setName("ess");
		// 构造相应参数
		if(demoEntity != null){
			ResDemoVO resDemoVO = new ResDemoVO();
			BeanUtils.copyProperties(demoEntity, resDemoVO);
			response.setData(resDemoVO);
		}

		return response;
	}

	public PageResponse<ResDemoVO> listPage(ReqDemoVO reqDemoVO) {
		
		PageResponse<ResDemoVO> response = new PageResponse<ResDemoVO>();
		
		// 参数校验
		if(reqDemoVO.getPageNum()==0 || reqDemoVO.getPageSize()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqDemoVO.getPageNum(), reqDemoVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqDemoVO);

			// 调用业务逻辑
			PageBean<DemoEntity> pageBean = demoService.listPage(pageParam, paramMap);
			
			// 构造响应参数
			List<ResDemoVO> records = new ArrayList<ResDemoVO>();
			List<DemoEntity> demoEntitys = pageBean.getRecords();
			for (DemoEntity demoEntity : demoEntitys) {
				ResDemoVO resDemoVO = new ResDemoVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) { 
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	public Response<Object> saveOrUpdate(ReqDemoVO reqDemoVO) {
		
		// 构造实体类
		DemoEntity demoEntity = new DemoEntity();
		BeanUtils.copyProperties(reqDemoVO, demoEntity);
		// 参数 非空验证
		Response<Object> response = Validate.getInstance().validate(demoEntity);
		
		// 如果校验成功, 继续业务处理
		if (response.isSuccess()) {
			// 业务操作
			Long id = demoService.saveOrUpdate(demoEntity);
			// 构造响应参数
			response = new Response<Object>(id);
		}

		return response;
	}

	@Override
	public Response<Object> saveDemos(List<ReqDemoVO> reqDemoVOs) {
		
		Response<Object> response = new Response<Object>();
		
		List<DemoEntity> demoEntitys = new ArrayList<DemoEntity>();
		for (ReqDemoVO reqDemoVO : reqDemoVOs) {
			DemoEntity demoEntity = new DemoEntity();
			BeanUtils.copyProperties(reqDemoVO, demoEntity);
			demoEntitys.add(demoEntity);
		}
		
		boolean result = demoService.saveDemos(demoEntitys);
		
		// 数据保存失败
		if(!result){
			response = new Response<Object>(CoreErrorCode.DB_RESULT_ERROR.getErrorCode(),
					CoreErrorCode.DB_RESULT_ERROR.getDefaultMessage());
		}
		return response;
	}

}
