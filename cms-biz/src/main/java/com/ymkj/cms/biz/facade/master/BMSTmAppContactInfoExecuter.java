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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppContactInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactInfoService;
@Service
public class BMSTmAppContactInfoExecuter implements IBMSTmAppContactInfoExecuter {
	@Autowired
	private IBMSTmAppContactInfoService BMSTmAppContactInfoService;

	@Override
	public PageResponse<ResBMSTmAppContactInfoVO> listPage(ReqBMSTmAppContactInfoVO reqBMSTmAppContactInfoVO) {
		PageResponse<ResBMSTmAppContactInfoVO> response = new PageResponse<ResBMSTmAppContactInfoVO>();
		// 参数校验
		if (reqBMSTmAppContactInfoVO.getPageNum() == 0 || reqBMSTmAppContactInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppContactInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppContactInfoVO.getPageNum(), reqBMSTmAppContactInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppContactInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppContactInfo> pageBean = BMSTmAppContactInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppContactInfoVO> records = new ArrayList<ResBMSTmAppContactInfoVO>();
			List<BMSTmAppContactInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppContactInfo Entity : Entitys) {
				ResBMSTmAppContactInfoVO resDemoVO = new ResBMSTmAppContactInfoVO();
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
