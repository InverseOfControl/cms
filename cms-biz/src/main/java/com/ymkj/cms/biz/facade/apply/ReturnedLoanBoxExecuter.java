package com.ymkj.cms.biz.facade.apply;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.apply.IReturnedLoanBoxExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxMessageVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReturnedLoanBoxSearchVO;
import com.ymkj.cms.biz.entity.apply.ReturnedLoanBoxSearchEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.IReturnedLoanBoxService;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IReturnedLoanBoxExecuter</p>
 * <p>Description:退件箱接口服务端</p>
 * @uthor YM10159
 * @date 2017年3月7日 上午11:06:38
 */
@Service
public class ReturnedLoanBoxExecuter implements IReturnedLoanBoxExecuter {

	@Autowired
	private IReturnedLoanBoxService iReturnedLoanBoxService;


	@Override
	public PageResponse<ResReturnedLoanBoxSearchVO> search(ReqReturnedLoanBoxSearchVO reqReturnedLoanBoxSearchVO) {

		PageResponse<ResReturnedLoanBoxSearchVO> response = new PageResponse<ResReturnedLoanBoxSearchVO>();
		List<ResReturnedLoanBoxSearchVO> records = new ArrayList<ResReturnedLoanBoxSearchVO>();

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqReturnedLoanBoxSearchVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}

		PageParam pageParam = new PageParam(reqReturnedLoanBoxSearchVO.getPageNum(), reqReturnedLoanBoxSearchVO.getPageSize());
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanKit.bean2map(reqReturnedLoanBoxSearchVO);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		// 调用业务逻辑
		PageBean<ReturnedLoanBoxSearchEntity> pageBean = iReturnedLoanBoxService.listPage(pageParam, paramMap);

		List<ReturnedLoanBoxSearchEntity> returnedLoanBoxSearchList = pageBean.getRecords();

		if(CollectionUtils.isNotEmpty(returnedLoanBoxSearchList)){
			for (ReturnedLoanBoxSearchEntity returnedLoanBoxSearchEntity : returnedLoanBoxSearchList) {
				ResReturnedLoanBoxSearchVO resReturnedLoanBoxSearchVO = new ResReturnedLoanBoxSearchVO();
				BeanUtils.copyProperties(returnedLoanBoxSearchEntity, resReturnedLoanBoxSearchVO);
				records.add(resReturnedLoanBoxSearchVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
		}

		return response;
	}

	@Override
	public Response<Object> queryMessageCount(ReqReturnedLoanBoxMessageVO reqReturnedLoanBoxMessageVO) {
		
		Response<Object> response = new Response<Object>(); 
		Map<String, Object> paramMap = null;
		
		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqReturnedLoanBoxMessageVO);
		if (!validateResponse.isSuccess()) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		try {
			paramMap = BeanKit.bean2map(reqReturnedLoanBoxMessageVO);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		int i = iReturnedLoanBoxService.queryMessageCount(paramMap);

		response = new Response<Object>(i);

		return response;
	}

}
