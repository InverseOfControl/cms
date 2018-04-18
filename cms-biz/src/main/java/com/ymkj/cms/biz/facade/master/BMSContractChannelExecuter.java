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
import com.ymkj.cms.biz.api.service.master.IBMSContractChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChannelVO;
import com.ymkj.cms.biz.entity.master.BMSContractChannel;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSContractChannelService;

@Service
public class BMSContractChannelExecuter implements IBMSContractChannelExecuter {

	@Autowired
	private IBMSContractChannelService bmsContractChannelService;

	@Override
	public PageResponse<ResBMSContractChannelVO> listPage( ReqBMSContractChannelVO reqContractChannelVo) {
		PageResponse<ResBMSContractChannelVO> response = new PageResponse<ResBMSContractChannelVO>();
		// 参数校验
		if (reqContractChannelVo.getPageNum() == 0 || reqContractChannelVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数

			PageParam pageParam = new PageParam(reqContractChannelVo.getPageNum(), reqContractChannelVo.getPageSize());

			Map<String, Object> paramMap = BeanKit.bean2map(reqContractChannelVo);

			// 调用业务逻辑,得到list集合
			PageBean<BMSContractChannel> pageBean = bmsContractChannelService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSContractChannelVO> records = new ArrayList<ResBMSContractChannelVO>();
			List<BMSContractChannel> demoEntitys = pageBean.getRecords();
			for (BMSContractChannel demoEntity : demoEntitys) {
				ResBMSContractChannelVO resDemoVO = new ResBMSContractChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			 System.out.println(e); 
			//throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
}



