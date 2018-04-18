package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSLoanLogExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanLogVO;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLoanLogService;
@Service
public class BMSLoanLogExecuter implements IBMSLoanLogExecuter{
	@Autowired
	private IBMSLoanLogService bmsLoanLogService;

	@Override
	public PageResponse<ResBMSLoanLogVO> listPage(ReqBMSLoanLogVO reqBMSLoanLogVO) {
		PageResponse<ResBMSLoanLogVO> response = new PageResponse<ResBMSLoanLogVO>();
		// 参数校验
		if (reqBMSLoanLogVO.getPageNum() == 0 || reqBMSLoanLogVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanLogVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanLogVO.getPageNum(), reqBMSLoanLogVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanLogVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanLog> pageBean = bmsLoanLogService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanLogVO> records = new ArrayList<ResBMSLoanLogVO>();
			List<BMSLoanLog> Entitys = pageBean.getRecords();
			for (BMSLoanLog Entity : Entitys) {
				ResBMSLoanLogVO resDemoVO = new ResBMSLoanLogVO();
				BeanUtils.copyProperties(Entity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/*System.out.println(e);*/
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	
	}

	@Override
	public Response<ResBMSLoanLogVO> findLastLog(ReqBMSLoanLogVO reqBMSLoanLogVO) {
		Response<ResBMSLoanLogVO> response = new Response<ResBMSLoanLogVO>();
		// 参数校验
		try {
			// 构造请求参数
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanLogVO);
			
			
			// 调用业务逻辑,得到list集合
			BMSLoanLog Entity = bmsLoanLogService.findLastLog(paramMap);
			// 构造响应参数
			ResBMSLoanLogVO resDemoVO = new ResBMSLoanLogVO();
			BeanUtils.copyProperties(Entity, resDemoVO);
			response.setData(resDemoVO);

		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

}
