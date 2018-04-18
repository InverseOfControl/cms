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
import com.ymkj.cms.biz.api.service.master.IBMSAppPersonExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonVO;
import com.ymkj.cms.biz.entity.master.BMSAppPerson;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSAppPersonService;

@Service
public class BMSAppPersonExecuter implements IBMSAppPersonExecuter{
	@Autowired
	private IBMSAppPersonService bmsAppPersonService;

	@Override
	public PageResponse<ResBMSAppPersonVO> listPage(ReqBMSAppPersonVO reqBMSAppPersonVO) {
		PageResponse<ResBMSAppPersonVO> response = new PageResponse<ResBMSAppPersonVO>();
		// 参数校验
		if (reqBMSAppPersonVO.getPageNum() == 0 || reqBMSAppPersonVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSAppPersonVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSAppPersonVO.getPageNum(), reqBMSAppPersonVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSAppPersonVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSAppPerson> pageBean = bmsAppPersonService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSAppPersonVO> records = new ArrayList<ResBMSAppPersonVO>();
			List<BMSAppPerson> Entitys = pageBean.getRecords();
			for (BMSAppPerson Entity : Entitys) {
				ResBMSAppPersonVO resDemoVO = new ResBMSAppPersonVO();
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
