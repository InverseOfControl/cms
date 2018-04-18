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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppMasterLoanInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppMasterLoanInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppMasterLoanInfoService;
@Service
public class BMSTmAppMasterLoanInfoExecuter implements IBMSTmAppMasterLoanInfoExecuter {
	@Autowired
	private IBMSTmAppMasterLoanInfoService BMSTmAppMasterLoanInfoService;

	@Override
	public PageResponse<ResBMSTmAppMasterLoanInfoVO> listPage(ReqBMSTmAppMasterLoanInfoVO reqBMSTmAppMasterLoanInfoVO) {
		PageResponse<ResBMSTmAppMasterLoanInfoVO> response = new PageResponse<ResBMSTmAppMasterLoanInfoVO>();
		// 参数校验
		if (reqBMSTmAppMasterLoanInfoVO.getPageNum() == 0 || reqBMSTmAppMasterLoanInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppMasterLoanInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppMasterLoanInfoVO.getPageNum(), reqBMSTmAppMasterLoanInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppMasterLoanInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppMasterLoanInfo> pageBean = BMSTmAppMasterLoanInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppMasterLoanInfoVO> records = new ArrayList<ResBMSTmAppMasterLoanInfoVO>();
			List<BMSTmAppMasterLoanInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppMasterLoanInfo Entity : Entitys) {
				ResBMSTmAppMasterLoanInfoVO resDemoVO = new ResBMSTmAppMasterLoanInfoVO();
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
