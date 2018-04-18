package com.ymkj.cms.web.boss.facade.master;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSDebtRadioExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class DebtRadioFacade  extends BaseFacade {
	
	@Autowired
	private IBMSDebtRadioExecuter  iBMSDebtRadioExecuter;
	
	public PageResult<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO reqDebtRadio){
		// 业务调用
		PageResponse<ResBMSDebtRadioVO> pageResponse = iBMSDebtRadioExecuter.listPage(reqDebtRadio);
		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSDebtRadioVO> pageResult = new PageResult<ResBMSDebtRadioVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	public boolean updatebyId(ReqBMSdebtRadioVO reqDebtRadio){
		Response<ResBMSDebtRadioVO> response=iBMSDebtRadioExecuter.update(reqDebtRadio);
		if (response.isSuccess()) {
			return true;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public ResBMSDebtRadioVO findByDebtRadioId(ReqBMSdebtRadioVO reqDebtRadio){
		Response<ResBMSDebtRadioVO> response=iBMSDebtRadioExecuter.findDebtRadioById(reqDebtRadio);
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public Integer testConnection(Request req){
		Response<Integer> result=iBMSDebtRadioExecuter.testConnection(req);

		return result.getData();
	}

}
