package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSContractTemplateExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSContractTemplateService;

public class BMSContractTemplateExecuter implements IBMSContractTemplateExecuter{
	
	@Autowired
	private IBMSContractTemplateService contractService;

	@Override
	public PageResponse<ResBMSContractTemplateVO> listPage(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		
		PageResponse<ResBMSContractTemplateVO> response = new PageResponse<ResBMSContractTemplateVO>();
		
		// 参数校验
		if(reqBMSContractTemplateVO.getPage()==0 || reqBMSContractTemplateVO.getRows()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSContractTemplateVO.getPage(), reqBMSContractTemplateVO.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSContractTemplateVO);

			// 调用业务逻辑
			PageBean<BMSContractTemplate> pageBean = contractService.listPage(pageParam, paramMap);
			
			// 构造响应参数
			List<ResBMSContractTemplateVO> records = new ArrayList<ResBMSContractTemplateVO>();
			List<BMSContractTemplate> demoEntitys = pageBean.getRecords();
			for (BMSContractTemplate demoEntity : demoEntitys) {
				ResBMSContractTemplateVO resDemoVO = new ResBMSContractTemplateVO();
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

	@Override
	public Response<ResBMSContractTemplateVO> saveTemplate(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {

		Response<ResBMSContractTemplateVO> response = new Response<ResBMSContractTemplateVO>();
		BMSContractTemplate template = new BMSContractTemplate();
		BeanUtils.copyProperties(reqBMSContractTemplateVO, template);
		Long count = contractService.saveTemplate(template);
		response.setRepMsg(String.valueOf(count));
		return response;
	
	}
	@Override
	public ResListVO<ResBMSContractTemplateVO> getAllTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		Map<String, Object> paramMap;
		List<ResBMSContractTemplateVO> paramList = new ArrayList<ResBMSContractTemplateVO>();
		try {
			paramMap = BeanKit.bean2map(reqBMSContractTemplateVO);
			List<BMSContractTemplate> allList = contractService.listBy(paramMap);
			for (BMSContractTemplate e : allList) {
				ResBMSContractTemplateVO vo = new ResBMSContractTemplateVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}
		return new ResListVO<ResBMSContractTemplateVO>(paramList);

	}

	@Override
	public Response<ResBMSContractTemplateVO> updateTemplate(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {

		Response<ResBMSContractTemplateVO> response = new Response<ResBMSContractTemplateVO>();
		BMSContractTemplate template = new BMSContractTemplate();
		BeanUtils.copyProperties(reqBMSContractTemplateVO, template);
		Long count = contractService.updateTemplate(template);
		response.setRepMsg(String.valueOf(count));
		return response;
	
	}

	@Override
	public Response<ResBMSContractTemplateVO> findByVO(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		Response<ResBMSContractTemplateVO> response = new Response<ResBMSContractTemplateVO>();
		BMSContractTemplate template = this.contractService.findByVO(reqBMSContractTemplateVO);
		// 构造相应参数
		if (template != null) {
			ResBMSContractTemplateVO resContractTemplateVO = new ResBMSContractTemplateVO();
			BeanUtils.copyProperties(template, resContractTemplateVO);
			response.setData(resContractTemplateVO);
		}
		
		return response;
	}

}
