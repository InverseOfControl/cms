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
import com.ymkj.cms.biz.api.service.master.IBMSSysLogEntityExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogEntityVO;
import com.ymkj.cms.biz.entity.master.BMSSysLogEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLogEntityService;
@Service
public class BMSSysLogEntityExecuter  implements IBMSSysLogEntityExecuter{
	@Autowired
	private IBMSSysLogEntityService bmsSysLogEntityService;

	@Override
	public PageResponse<ResBMSSysLogEntityVO> listPage(ReqBMSSysLogEntityVO reqBMSSysLogEntityVO) {
		PageResponse<ResBMSSysLogEntityVO> response = new PageResponse<ResBMSSysLogEntityVO>();
		// 参数校验
		if (reqBMSSysLogEntityVO.getPageNum() == 0 || reqBMSSysLogEntityVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSSysLogEntityVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSSysLogEntityVO.getPageNum(), reqBMSSysLogEntityVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSSysLogEntityVO);
			if(null==reqBMSSysLogEntityVO.getOptTimeStart()){
				paramMap.put("optTimeStart", null);
			}
			if(null==reqBMSSysLogEntityVO.getOptTimeEnd()){
				paramMap.put("optTimeEnd", null);
			}
			// 调用业务逻辑,得到list集合
			PageBean<BMSSysLogEntity> pageBean = bmsSysLogEntityService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSSysLogEntityVO> records = new ArrayList<ResBMSSysLogEntityVO>();
			List<BMSSysLogEntity> Entitys = pageBean.getRecords();
			for (BMSSysLogEntity Entity : Entitys) {
				ResBMSSysLogEntityVO resDemoVO = new ResBMSSysLogEntityVO();
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
