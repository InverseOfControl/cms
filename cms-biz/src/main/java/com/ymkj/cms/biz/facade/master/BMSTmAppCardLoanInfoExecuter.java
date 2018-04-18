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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppCardLoanInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppCardLoanInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppCardLoanInfoService;
@Service
public class BMSTmAppCardLoanInfoExecuter implements IBMSTmAppCardLoanInfoExecuter{
	@Autowired
	private IBMSTmAppCardLoanInfoService bmsTmAppCardLoanInfoService;

	@Override
	public PageResponse<ResBMSTmAppCardLoanInfoVO> listPage(ReqBMSTmAppCardLoanInfoVO reqBMSTmAppCardLoanInfoVO) {
		PageResponse<ResBMSTmAppCardLoanInfoVO> response = new PageResponse<ResBMSTmAppCardLoanInfoVO>();
		// 参数校验
		if (reqBMSTmAppCardLoanInfoVO.getPageNum() == 0 || reqBMSTmAppCardLoanInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppCardLoanInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppCardLoanInfoVO.getPageNum(), reqBMSTmAppCardLoanInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppCardLoanInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppCardLoanInfo> pageBean = bmsTmAppCardLoanInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppCardLoanInfoVO> records = new ArrayList<ResBMSTmAppCardLoanInfoVO>();
			List<BMSTmAppCardLoanInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppCardLoanInfo Entity : Entitys) {
				ResBMSTmAppCardLoanInfoVO resDemoVO = new ResBMSTmAppCardLoanInfoVO();
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

}
