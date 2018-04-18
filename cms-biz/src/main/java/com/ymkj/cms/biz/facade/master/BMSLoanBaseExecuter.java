package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSLoanBaseExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseEntityService;

@Service
public class BMSLoanBaseExecuter implements IBMSLoanBaseExecuter{
	
	@Autowired
	private IBMSLoanBaseEntityService bmsLoanBaseEntityService;

	@Override
	public PageResponse<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		PageResponse<ResBMSLoanBaseVO> response = new PageResponse<ResBMSLoanBaseVO>();
		// 参数校验(分页)
		if (reqBMSLoanBaseVO.getPageNum() == 0 || reqBMSLoanBaseVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanBaseVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanBaseVO.getPageNum(), reqBMSLoanBaseVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			String statusStr=	reqBMSLoanBaseVO.getStatus();
			if(StringUtils.isBlank(statusStr)){
				paramMap.put("status", null);
			}else{
				String[] status =statusStr.split(",");
				paramMap.put("status", status);
			}

			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanBase> pageBean = bmsLoanBaseEntityService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanBaseVO> records = new ArrayList<ResBMSLoanBaseVO>();
			List<BMSLoanBase> loanBaseEntitys = pageBean.getRecords();
			for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
				ResBMSLoanBaseVO resLoanBaseVO=new ResBMSLoanBaseVO();
				BeanUtils.copyProperties(loanBaseEntity, resLoanBaseVO);
				records.add(resLoanBaseVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			System.out.println("错误:"+e);
			//throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSLoanBaseVO> findLoanBase(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		ResListVO<ResBMSLoanBaseVO> response = new ResListVO<ResBMSLoanBaseVO>();
		if(StringUtils.isNotEmpty(reqBMSLoanBaseVO.getLoanNo())){
			//编号解析
			reqBMSLoanBaseVO.setLoanNo(ValidationUtils.analysisAitePushLoanNo(reqBMSLoanBaseVO.getLoanNo()));

		}
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			
			List<ResBMSLoanBaseVO> records = new ArrayList<ResBMSLoanBaseVO>();

			List<BMSLoanBase> loanBaseEntitys = bmsLoanBaseEntityService.listBy(paramMap);
			for (BMSLoanBase loanBaseEntity : loanBaseEntitys) {
				ResBMSLoanBaseVO resLoanBaseVO=new ResBMSLoanBaseVO();
				BeanUtils.copyProperties(loanBaseEntity, resLoanBaseVO);
				records.add(resLoanBaseVO);
			}
			response.setParamList(records);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public Response<Integer> findLoanBaseCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		Response<Integer> response = new Response<Integer>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			Integer count = bmsLoanBaseEntityService.findLoanBaseCount(paramMap);
			response.setData(count);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSLoanBaseVO> findGrantLoanUpdateByCoreJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		ResListVO<ResBMSLoanBaseVO> response = new ResListVO<ResBMSLoanBaseVO>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			

			List<ResBMSLoanBaseVO> records = bmsLoanBaseEntityService.findGrantLoanUpdateByCoreJob(paramMap);
			response.setParamList(records);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSLoanBaseVO> findLoanBaseJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		ResListVO<ResBMSLoanBaseVO> response = new ResListVO<ResBMSLoanBaseVO>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			List<ResBMSLoanBaseVO> records = bmsLoanBaseEntityService.findLoanBaseJob(paramMap);
			response.setParamList(records);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSLoanBaseVO> findBindCreditReportJob(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		ResListVO<ResBMSLoanBaseVO> response = new ResListVO<ResBMSLoanBaseVO>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			List<ResBMSLoanBaseVO> records = bmsLoanBaseEntityService.findBindCreditReportJob(paramMap);
			response.setParamList(records);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public Response<Integer> findLoanBaseJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		Response<Integer> response = new Response<Integer>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			Integer count = bmsLoanBaseEntityService.findLoanBaseJobCount(paramMap);
			response.setData(count);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public Response<Integer> findGrantLoanUpdateByCoreJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		Response<Integer> response = new Response<Integer>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			Integer count = bmsLoanBaseEntityService.findGrantLoanUpdateByCoreJobCount(paramMap);
			response.setData(count);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public Response<Integer> findBindCreditReportJobCount(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
		Response<Integer> response = new Response<Integer>();
		// 参数校验(分页)
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanBaseVO);
			for (String key : paramMap.keySet()) {  
				  if("".equals(paramMap.get(key))){
					  paramMap.put(key,null);
				  }
			  
			}  
			Integer count = bmsLoanBaseEntityService.findBindCreditReportJobCount(paramMap);
			response.setData(count);
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			response.setRepMsg(e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		return response;
	}

}
