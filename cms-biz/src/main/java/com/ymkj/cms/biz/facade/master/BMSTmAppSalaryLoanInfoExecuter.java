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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppSalaryLoanInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppSalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppSalaryLoanInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppSalaryLoanInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppSalaryLoanInfoService;
@Service
public class BMSTmAppSalaryLoanInfoExecuter implements IBMSTmAppSalaryLoanInfoExecuter {

	@Autowired
	private IBMSTmAppSalaryLoanInfoService BMSTmAppSalaryLoanInfoService;

	@Override
	public PageResponse<ResBMSTmAppSalaryLoanInfoVO> listPage(ReqBMSTmAppSalaryLoanInfoVO reqBMSTmAppSalaryLoanInfoVO) {
		PageResponse<ResBMSTmAppSalaryLoanInfoVO> response = new PageResponse<ResBMSTmAppSalaryLoanInfoVO>();
		// 参数校验
		if (reqBMSTmAppSalaryLoanInfoVO.getPageNum() == 0 || reqBMSTmAppSalaryLoanInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppSalaryLoanInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppSalaryLoanInfoVO.getPageNum(), reqBMSTmAppSalaryLoanInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppSalaryLoanInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppSalaryLoanInfo> pageBean = BMSTmAppSalaryLoanInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppSalaryLoanInfoVO> records = new ArrayList<ResBMSTmAppSalaryLoanInfoVO>();
			List<BMSTmAppSalaryLoanInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppSalaryLoanInfo Entity : Entitys) {
				ResBMSTmAppSalaryLoanInfoVO resDemoVO = new ResBMSTmAppSalaryLoanInfoVO();
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
