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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppProvidentInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppProvidentInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppProvidentInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppProvidentInfoService;
@Service
public class BMSTmAppProvidentInfoExecuter implements IBMSTmAppProvidentInfoExecuter {
	@Autowired
	private IBMSTmAppProvidentInfoService BMSTmAppProvidentInfoService;

	@Override
	public PageResponse<ResBMSTmAppProvidentInfoVO> listPage(ReqBMSTmAppProvidentInfoVO reqBMSTmAppProvidentInfoVO) {
		PageResponse<ResBMSTmAppProvidentInfoVO> response = new PageResponse<ResBMSTmAppProvidentInfoVO>();
		// 参数校验
		if (reqBMSTmAppProvidentInfoVO.getPageNum() == 0 || reqBMSTmAppProvidentInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppProvidentInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppProvidentInfoVO.getPageNum(), reqBMSTmAppProvidentInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppProvidentInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppProvidentInfo> pageBean = BMSTmAppProvidentInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppProvidentInfoVO> records = new ArrayList<ResBMSTmAppProvidentInfoVO>();
			List<BMSTmAppProvidentInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppProvidentInfo Entity : Entitys) {
				ResBMSTmAppProvidentInfoVO resDemoVO = new ResBMSTmAppProvidentInfoVO();
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
