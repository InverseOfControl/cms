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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppMerchantLoanInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMerchantLoanInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppMerchantLoanInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppMerchantLoanInfoService;
@Service
public class BMSTmAppMerchantLoanInfoExecuter implements IBMSTmAppMerchantLoanInfoExecuter {
	@Autowired
	private IBMSTmAppMerchantLoanInfoService BMSTmAppMerchantLoanInfoService;

	@Override
	public PageResponse<ResBMSTmAppMerchantLoanInfoVO> listPage(ReqBMSTmAppMerchantLoanInfoVO reqBMSTmAppMerchantLoanInfoVO) {
		PageResponse<ResBMSTmAppMerchantLoanInfoVO> response = new PageResponse<ResBMSTmAppMerchantLoanInfoVO>();
		// 参数校验
		if (reqBMSTmAppMerchantLoanInfoVO.getPageNum() == 0 || reqBMSTmAppMerchantLoanInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppMerchantLoanInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppMerchantLoanInfoVO.getPageNum(), reqBMSTmAppMerchantLoanInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppMerchantLoanInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppMerchantLoanInfo> pageBean = BMSTmAppMerchantLoanInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppMerchantLoanInfoVO> records = new ArrayList<ResBMSTmAppMerchantLoanInfoVO>();
			List<BMSTmAppMerchantLoanInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppMerchantLoanInfo Entity : Entitys) {
				ResBMSTmAppMerchantLoanInfoVO resDemoVO = new ResBMSTmAppMerchantLoanInfoVO();
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
