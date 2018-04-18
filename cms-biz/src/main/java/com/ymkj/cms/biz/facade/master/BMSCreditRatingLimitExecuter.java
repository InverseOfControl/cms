package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSCreditRatingLimitExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.entity.master.BMSCreditRatingLimit;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSCreditRatingLimitService;

@Service
public class BMSCreditRatingLimitExecuter implements IBMSCreditRatingLimitExecuter{
	@Autowired
	private IBMSCreditRatingLimitService iBMSCreditRatingLimitService;

	@Override
	public PageResponse<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		PageResponse<ResBMSCreditRatingLimitVO> response = new PageResponse<ResBMSCreditRatingLimitVO>();
		// 参数校验
		if (reqBMSCreditRatingLimitVO.getPageNum() == 0 || reqBMSCreditRatingLimitVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			reqBMSCreditRatingLimitVO.setIsDeleted(0);
			PageParam pageParam = new PageParam(reqBMSCreditRatingLimitVO.getPageNum(), reqBMSCreditRatingLimitVO.getPageSize());

			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSCreditRatingLimitVO);

			// 调用业务逻辑,得到list集合
			PageBean<BMSCreditRatingLimit> pageBean = iBMSCreditRatingLimitService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSCreditRatingLimitVO> records = new ArrayList<ResBMSCreditRatingLimitVO>();
			List<BMSCreditRatingLimit> demoEntitys = pageBean.getRecords();
			for (BMSCreditRatingLimit demoEntity : demoEntitys) {
				ResBMSCreditRatingLimitVO resDemoVO = new ResBMSCreditRatingLimitVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/* System.out.println(e); */
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<List<Map<String, Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v) {
		return iBMSCreditRatingLimitService.findByProductAll(v);
	}

	@Override
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.addCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.deleteCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(
			ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.loadCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.updateCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.findAddIsRepeat(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitService.findUpdateIsRepeat(reqBMSCreditRatingLimitVO);
	}

}
