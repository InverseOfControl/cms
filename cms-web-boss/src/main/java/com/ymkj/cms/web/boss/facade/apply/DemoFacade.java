package com.ymkj.cms.web.boss.facade.apply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.apply.IDemoExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

/**
 * 服务消费者 即 接口调用端: 
 * 1. 通过注解方式注入接口
 * @author user
 */
@Component
public class DemoFacade extends BaseFacade{
	
	@Autowired
	private IDemoExecuter demoExecuter;
	/**
	 * 1. 请求参数构造
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public ResDemoVO findById(Long id) {
		// 请求参数构造
		ReqDemoVO reqDemoVO = new ReqDemoVO("1111111");
		reqDemoVO.setId(id);
		// 接口调用
		Response<ResDemoVO> response = demoExecuter.findById(reqDemoVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}

	public ResDemoVO findByName(String name) {
		// 请求参数构造
		ReqDemoVO reqDemoVO = new ReqDemoVO("1111111");
		reqDemoVO.setName(name);
		// 接口调用
		Response<ResDemoVO> response = demoExecuter.findByName(reqDemoVO);
		// 响应结果处理
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	public PageResult<ResDemoVO> listPage(ReqDemoVO reqDemoVO){
		
		// 业务调用
		PageResponse<ResDemoVO> pageResponse = demoExecuter.listPage(reqDemoVO);
		
		// 响应结果处理
		if(pageResponse.isSuccess()){
			PageResult<ResDemoVO> pageResult = new PageResult<ResDemoVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	
	public Long saveOrUpdate(ReqDemoVO reqDemoVO){
		Response<Object> response = demoExecuter.saveOrUpdate(reqDemoVO);
		Long id = null;
		if(response.isSuccess()){
			id = (Long) response.getData();
			return id;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

}
