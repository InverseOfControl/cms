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
import com.ymkj.cms.biz.api.service.master.IBMSTmAppCarInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCarInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCarInfoVO;
import com.ymkj.cms.biz.entity.master.BMSTmAppCarInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTmAppCarInfoService;
@Service
public class BMSTmAppCarInfoExecuter implements IBMSTmAppCarInfoExecuter {
	@Autowired
	private IBMSTmAppCarInfoService BMSTmAppCarInfoService;

	@Override
	public PageResponse<ResBMSTmAppCarInfoVO> listPage(ReqBMSTmAppCarInfoVO reqBMSTmAppCarInfoVO) {
		PageResponse<ResBMSTmAppCarInfoVO> response = new PageResponse<ResBMSTmAppCarInfoVO>();
		// 参数校验
		if (reqBMSTmAppCarInfoVO.getPageNum() == 0 || reqBMSTmAppCarInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSTmAppCarInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSTmAppCarInfoVO.getPageNum(), reqBMSTmAppCarInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSTmAppCarInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSTmAppCarInfo> pageBean = BMSTmAppCarInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSTmAppCarInfoVO> records = new ArrayList<ResBMSTmAppCarInfoVO>();
			List<BMSTmAppCarInfo> Entitys = pageBean.getRecords();
			for (BMSTmAppCarInfo Entity : Entitys) {
				ResBMSTmAppCarInfoVO resDemoVO = new ResBMSTmAppCarInfoVO();
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
