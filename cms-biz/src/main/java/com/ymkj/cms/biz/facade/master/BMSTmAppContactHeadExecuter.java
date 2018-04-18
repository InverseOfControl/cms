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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppContactHeadExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactHead;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactHeadService;
@Service
public class BMSTmAppContactHeadExecuter implements IBMSTmAppContactHeadExecuter {
	@Autowired
	private IBMSTmAppContactHeadService bmsTmAppContactHeadService;
	
	@Override
	public PageResponse<ResBMSTmAppContactHeadVO> listPage(ReqBMSTmAppContactHeadVO reqBMSTmAppContactHeadVO) {
		PageResponse<ResBMSTmAppContactHeadVO> response = new PageResponse<ResBMSTmAppContactHeadVO>();
		// 参数校验
		if (reqBMSTmAppContactHeadVO.getPageNum() == 0 || reqBMSTmAppContactHeadVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppContactHeadVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppContactHeadVO.getPageNum(), reqBMSTmAppContactHeadVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppContactHeadVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppContactHead> pageBean = bmsTmAppContactHeadService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppContactHeadVO> records = new ArrayList<ResBMSTmAppContactHeadVO>();
			List<BMSTmAppContactHead> Entitys = pageBean.getRecords();
			for (BMSTmAppContactHead Entity : Entitys) {
				ResBMSTmAppContactHeadVO resDemoVO = new ResBMSTmAppContactHeadVO();
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
