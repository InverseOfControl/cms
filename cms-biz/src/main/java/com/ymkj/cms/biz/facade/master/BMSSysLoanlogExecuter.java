package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.master.IBMSSysLoanLogExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogVO;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
@Service
public class BMSSysLoanlogExecuter implements IBMSSysLoanLogExecuter{
	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;

	@Override
	public Response<ResBMSSysLoanLogListVO>  listByLoan(
			ReqBMSSysLoanLogVO reqSysLoanLogVO) {
	
		Response<ResBMSSysLoanLogListVO> response = new Response<ResBMSSysLoanLogListVO>();
		ResBMSSysLoanLogListVO listVO = new ResBMSSysLoanLogListVO();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqSysLoanLogVO.getLoanBaseId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanBaseId"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqSysLoanLogVO);
			List<BMSSysLoanLog> list = sysLoanLogService.listBy(paramMap);
			if (list != null && list.size() > 0) {
				List<ResBMSSysLoanLogVO> records = new ArrayList<ResBMSSysLoanLogVO>();
				for (BMSSysLoanLog log : list) {
					ResBMSSysLoanLogVO vo = new ResBMSSysLoanLogVO();
					BeanUtils.copyProperties(log, vo);
					records.add(vo);
				}
				listVO.setResBMSSysLoanLogList(records);
			}
			response.setData(listVO);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	
	}

	 

	 

}
