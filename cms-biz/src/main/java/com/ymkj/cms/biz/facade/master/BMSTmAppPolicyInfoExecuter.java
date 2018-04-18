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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppPolicyInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppPolicyInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppPolicyInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppPolicyInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppPolicyInfoService;
@Service
public class BMSTmAppPolicyInfoExecuter implements IBMSTmAppPolicyInfoExecuter {
	@Autowired
	private IBMSTmAppPolicyInfoService BMSTmAppPolicyInfoService;

	@Override
	public PageResponse<ResBMSTmAppPolicyInfoVO> listPage(ReqBMSTmAppPolicyInfoVO reqBMSTmAppPolicyInfoVO) {
		PageResponse<ResBMSTmAppPolicyInfoVO> response = new PageResponse<ResBMSTmAppPolicyInfoVO>();
		// 参数校验
		if (reqBMSTmAppPolicyInfoVO.getPageNum() == 0 || reqBMSTmAppPolicyInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppPolicyInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppPolicyInfoVO.getPageNum(), reqBMSTmAppPolicyInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppPolicyInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppPolicyInfo> pageBean = BMSTmAppPolicyInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppPolicyInfoVO> records = new ArrayList<ResBMSTmAppPolicyInfoVO>();
			List<BMSTmAppPolicyInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppPolicyInfo Entity : Entitys) {
				ResBMSTmAppPolicyInfoVO resDemoVO = new ResBMSTmAppPolicyInfoVO();
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
