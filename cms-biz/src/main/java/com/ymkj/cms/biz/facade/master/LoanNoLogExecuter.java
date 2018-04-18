package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.ILoanNoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanNoLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLoanNoLogVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
/**
 * 借款日志信息服务类
 * @author YM10161
 *
 */
@Service
public class LoanNoLogExecuter implements ILoanNoExecuter{
	public Logger logger = LoggerFactory.getLogger(LoanNoLogExecuter.class);
	@Autowired
	private IBMSSysLoanLogService sSysLoanLogService;
	@Override
	public PageResponse<ResLoanNoLogVo> queryLoanNoLogInfo(ReqBMSLoanNoLogVO reqBMSLoanNoLogVO) {
		logger.info("--------调用借款日志接口开始----------");
		 PageResponse<ResLoanNoLogVo> response = new  PageResponse<ResLoanNoLogVo>();
		PageParam pageParam=null;
		if(reqBMSLoanNoLogVO!=null){
			if(StringUtils.isEmpty(reqBMSLoanNoLogVO.getLoanNo())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "loanNo" });
			}
			if(StringUtils.isEmpty(reqBMSLoanNoLogVO.getOperatorEndTime())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "operatorEndTime" });
			}
			if(StringUtils.isEmpty(reqBMSLoanNoLogVO.getOperatorStartTime())){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "operatorStartTime" });
			}
			if (reqBMSLoanNoLogVO.getPageNum() == 0 || reqBMSLoanNoLogVO.getPageSize() == 0) {
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "pageNum | pageSize" });
			}
			
			try{
				pageParam = new PageParam(reqBMSLoanNoLogVO.getPageNum(),reqBMSLoanNoLogVO.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanNoLogVO);
				PageBean<BMSSysLoanLog> pageBean=sSysLoanLogService.listPage(pageParam, paramMap);
				List<BMSSysLoanLog> list=pageBean.getRecords();
				List<ResLoanNoLogVo> records = new ArrayList<ResLoanNoLogVo>();
				for(BMSSysLoanLog sSysLoanLog:list){
					ResLoanNoLogVo resLoanNoLogVo=new ResLoanNoLogVo();
					BeanUtils.copyProperties(sSysLoanLog,resLoanNoLogVo);
					records.add(resLoanNoLogVo);
				}
				// 忽略 复制的字段
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);
			}catch(Exception e){
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);		
			}	
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSLoanNoLogVO" });
		}
		return null;
	}

}
