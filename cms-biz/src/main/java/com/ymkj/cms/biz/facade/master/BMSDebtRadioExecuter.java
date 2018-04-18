package com.ymkj.cms.biz.facade.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSDebtRadioExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSDebtRadioService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BMSDebtRadioExecuter implements IBMSDebtRadioExecuter{
	
	@Autowired
	private IBMSDebtRadioService iBMSDebtRadioService;

	@Override
	public PageResponse<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO reqBMSDebtRadio) {
		PageResponse<ResBMSDebtRadioVO> response=new PageResponse<ResBMSDebtRadioVO>();	
		if(reqBMSDebtRadio.getPageNum()==0 || reqBMSDebtRadio.getPageSize()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSDebtRadio.getPage(), reqBMSDebtRadio.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSDebtRadio);

			// 调用业务逻辑
			PageBean<BMSDebtRadio> pageBean = iBMSDebtRadioService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResBMSDebtRadioVO> records = new ArrayList<ResBMSDebtRadioVO>();
			List<BMSDebtRadio> demoEntitys = pageBean.getRecords();
			for (BMSDebtRadio demoEntity : demoEntitys) {
				ResBMSDebtRadioVO resDemoVO = new ResBMSDebtRadioVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSDebtRadioVO> update(ReqBMSdebtRadioVO reqBMSDebtRadio) {
		Response<ResBMSDebtRadioVO> response=new Response<ResBMSDebtRadioVO>();
		BMSDebtRadio debtRadio=new BMSDebtRadio();
		BeanUtils.copyProperties(reqBMSDebtRadio, debtRadio);
		boolean flag=iBMSDebtRadioService.updateById(debtRadio);
		if(flag){
			response.setRepMsg("true");	
		}else{
			response.setRepMsg("false");
		}
		return response;
	}

	@Override
	public Response<ResBMSDebtRadioVO> findDebtRadioById(ReqBMSdebtRadioVO reqBMSDebtRadio) {
		Response<ResBMSDebtRadioVO> response=new Response<ResBMSDebtRadioVO>();
		BMSDebtRadio debtRadio=new BMSDebtRadio();
		BeanUtils.copyProperties(reqBMSDebtRadio, debtRadio);
		BMSDebtRadio  bdebtRadio=iBMSDebtRadioService.findDebtRadioById(debtRadio);
		if(bdebtRadio != null){
			ResBMSDebtRadioVO resDemoVO = new ResBMSDebtRadioVO();
			BeanUtils.copyProperties(bdebtRadio, resDemoVO);
			response.setData(resDemoVO);
		}	
		return response;
	}
	@Override
	public Response<Integer> testConnection(Request req) {
		Response<Integer>response=new Response<Integer>();
		Integer result=iBMSDebtRadioService.testConnection(req);
		response.setData(result);
		return response;
	}
	

}
