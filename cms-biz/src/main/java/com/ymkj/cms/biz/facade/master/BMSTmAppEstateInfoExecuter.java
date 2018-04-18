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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppEstateInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppEstateInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppEstateInfoService;
@Service
public class BMSTmAppEstateInfoExecuter implements IBMSTmAppEstateInfoExecuter {
	@Autowired
	private IBMSTmAppEstateInfoService BMSTmAppEstateInfoService;

	@Override
	public PageResponse<ResBMSTmAppEstateInfoVO> listPage(ReqBMSTmAppEstateInfoVO reqBMSTmAppEstateInfoVO) {
		PageResponse<ResBMSTmAppEstateInfoVO> response = new PageResponse<ResBMSTmAppEstateInfoVO>();
		// 参数校验
		if (reqBMSTmAppEstateInfoVO.getPageNum() == 0 || reqBMSTmAppEstateInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppEstateInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppEstateInfoVO.getPageNum(), reqBMSTmAppEstateInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppEstateInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppEstateInfo> pageBean = BMSTmAppEstateInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppEstateInfoVO> records = new ArrayList<ResBMSTmAppEstateInfoVO>();
			List<BMSTmAppEstateInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppEstateInfo Entity : Entitys) {
				ResBMSTmAppEstateInfoVO resDemoVO = new ResBMSTmAppEstateInfoVO();
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
