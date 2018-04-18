package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSSysLogExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogVO;
import com.ymkj.cms.biz.entity.master.BMSSysLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
@Service
public class BMSSyslogExecuter implements IBMSSysLogExecuter{
	@Autowired
	private IBMSSysLogService sysLogService;

	@Override
	public PageResponse<ResBMSSysLogVO> listPage(ReqBMSSysLogVO reqSysLogVO) {
         PageResponse<ResBMSSysLogVO> response = new PageResponse<ResBMSSysLogVO>();
		
		// 参数校验
		if(reqSysLogVO.getPageNum()==0 || reqSysLogVO.getPageSize()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqSysLogVO.getPageNum(), reqSysLogVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqSysLogVO);

			// 调用业务逻辑,得到list集合
			PageBean<BMSSysLog> pageBean = sysLogService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSSysLogVO> records = new ArrayList<ResBMSSysLogVO>();
			List<BMSSysLog> sysLogEntitys = pageBean.getRecords();
			for (BMSSysLog sysLog : sysLogEntitys) {
				ResBMSSysLogVO resSyslogVO = new ResBMSSysLogVO();
				BeanUtils.copyProperties(sysLog, resSyslogVO);
				records.add(resSyslogVO);
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

 
	 
	 

	 

}
