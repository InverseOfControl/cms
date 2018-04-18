package com.ymkj.cms.biz.facade.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.sign.ILoanContractPatchBoltExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractPatchBoltService;

@Service
public class LoanContractPatchBoltExecuter implements ILoanContractPatchBoltExecuter{

	@Autowired
	private ILoanContractPatchBoltService loanContractPatchBoltService;
	
	/*@Autowired
	private ILoanBaseEntityService loanBaseEntityService;*/
	
	@Override
	public PageResponse<ResLoanContractSignVo> contractPatchBoltListPage(ReqLoanContractSignVo reqLoanContractSignVo) {
		PageResponse<ResLoanContractSignVo> response = new PageResponse<ResLoanContractSignVo>();

		// 参数校验
		if (reqLoanContractSignVo.getPageNum() == 0
				|| reqLoanContractSignVo.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,new Object[] { "pageNum | pageSize" });
		}
		if (reqLoanContractSignVo.getServiceCode() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,new Object[] { "serviceCode" });
		}

		if (reqLoanContractSignVo.getIfNeedPatchBolt() == null ||(reqLoanContractSignVo.getIfNeedPatchBolt() != 1
				&&reqLoanContractSignVo.getIfNeedPatchBolt() != 2)) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,new Object[] { "ifNeedPatchBolt" });
		}
		try {
			// 调用业务逻辑
			PageBean<BMSLoanBaseEntity> pageBean = loanContractPatchBoltService.undoneListPatchBoltPage(reqLoanContractSignVo);
			// 构造响应参数
			List<ResLoanContractSignVo> records = new ArrayList<ResLoanContractSignVo>();
			List<BMSLoanBaseEntity> demoEntitys = pageBean.getRecords();

			for (BMSLoanBaseEntity demoEntity : demoEntitys) {
				ResLoanContractSignVo resDemoVO = new ResLoanContractSignVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRepMsg("suceess");
			response.setRecords(records);

		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage()
					+ e.getLocalizedMessage());
		}
		return response;
	}

	
	
	@Override
	public Response<Object> queryContractPatchBoltToDoCount(ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<Object> response = new Response<Object>();
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("serviceCode", reqLoanContractSignVo.getServiceCode());
		paramsMap.put("ifNeedPatchBolt", "1");
		long count = loanContractPatchBoltService.queryContractPatchBoltToDoCount(paramsMap);
		response.setData(count);
		return response;
	}
	
	@Override
	public Response<ResLoanContractSignVo> updatePatchBoltInSign(ReqLoanContractSignVo reqLoanContractSignVo) {
		if (reqLoanContractSignVo.getIfNeedPatchBolt() == null||reqLoanContractSignVo.getIfNeedPatchBolt() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR,new Object[] { "ifNeedPatchBolt" });
		}
		if (StringUtils.isBlank(reqLoanContractSignVo.getLoanNo())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK,new Object[] { "loanNo" });
		}
		if (reqLoanContractSignVo.getId()==0||reqLoanContractSignVo.getId()==null) {
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL,new Object[] { "id" });
		}
		
		Response<ResLoanContractSignVo> res = new Response<ResLoanContractSignVo>();
		boolean updateFlag =false;
		try {
	 		updateFlag = loanContractPatchBoltService.updatePatchBoltSign(reqLoanContractSignVo);
		} catch (BizException e) {
			throw new BizException(CoreErrorCode.UNKNOWN_ERROR,e.getMessage()+e.getArguments()[0]);
		} 
		if(!updateFlag){
			throw new CoreException(CoreErrorCode.DB_RESULT_ERROR,"操作失败");
		}
		return res;
	}

}
