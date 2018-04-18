package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

public class BMSOrgLimitChannelExecuter  implements IBMSOrgLimitChannelExecuter{
	@Autowired
	private IBMSOrgLimitChannelService demoService;
	@Autowired
	private IBMSSysLogService sysLogService;
	@Override
	public Response<ResProductBaseListVO> listOrgLimitChannelBy(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResProductBaseListVO> response = new Response<ResProductBaseListVO>();
		ResProductBaseListVO listVO = new ResProductBaseListVO();
		// 参数校验 第二种方式 
//		if(StringUtils.isEmpty(reqBMSOrgLimitChannelVO.getProductCode())){
//			throw new DemoBizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCode"});
//		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSOrgLimitChannelVO);
			List<BMSOrgLimitChannel> listProduct = demoService
					.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
				for (BMSOrgLimitChannel product : listProduct) {
					ResBMSOrgLimitChannelVO vo = new ResBMSOrgLimitChannelVO();
					BeanUtils.copyProperties(product, vo);
					records.add(vo);
				}
				listVO.setListOrgLimitChannel(records);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		response.setData(listVO);
		return response;
	}
	@Override
	public PageResponse<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		
		PageResponse<ResBMSOrgLimitChannelVO> response = new PageResponse<ResBMSOrgLimitChannelVO>();
		
		// 参数校验
		if(reqBMSOrgLimitChannelVO.getPage()==0 || reqBMSOrgLimitChannelVO.getRows()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSOrgLimitChannelVO.getPage(), reqBMSOrgLimitChannelVO.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSOrgLimitChannelVO);
			if(StringUtils.isEmpty(paramMap.get("isDisabled").toString())){
				paramMap.put("isDisabled",null);
			}
			// 调用业务逻辑
			PageBean<BMSOrgLimitChannel> pageBean = demoService.listPage(pageParam, paramMap);
			
			// 构造响应参数
			List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
			List<BMSOrgLimitChannel> demoEntitys = pageBean.getRecords();
			for (BMSOrgLimitChannel demoEntity : demoEntitys) {
				ResBMSOrgLimitChannelVO resDemoVO = new ResBMSOrgLimitChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) { 
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> saveProductLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		Boolean flag = demoService.saveOrgLimitChannel(reqBMSOrgLimitChannelVO);
		if(flag){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|网点产品配置保存|save","BMSOrgLimitChannelExecuter//saveProductLimit","网点产品配置保存");
			
		}
		response.setRepMsg(String.valueOf(flag));
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> saveRateOrgChannelLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		Boolean flag = demoService.saveRateOrgChannelLimit(reqBMSOrgLimitChannelVO);
		if(flag){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|网点产品配置保存|save","BMSOrgLimitChannelExecuter//saveProductLimit","网点产品配置保存");
			
		}
	    response.setRepMsg(String.valueOf(flag));
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> updateProductLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		
		
		Boolean flag = demoService.updateOrgLimitChannel(reqBMSOrgLimitChannelVO);
		if(flag){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|网点产品配置更新|update","BMSOrgLimitChannelExecuter//updateProductLimit","网点产品配置更新");
			
		}
	    response.setRepMsg(String.valueOf(flag));
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> logicalDelete(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		reqBMSOrgLimitChannelVO.setSysCode("BMS");
		BMSOrgLimitChannel orgLimitChannel = new BMSOrgLimitChannel();
		BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
		long count = this.demoService.logicalDelete(orgLimitChannel);
		if(count>0){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|网点产品配置删除|delete","BMSOrgLimitChannelExecuter//logicalDelete","网点产品配置删除数"+count);
		}
		response.setRepMsg(String.valueOf(count)); //返回受影响的行数
		return response;
	}

	@Override
	public Response<ResBMSOrgLimitChannelVO> isDisabled(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		if(reqBMSOrgLimitChannelVO.getOrgId() ==null||reqBMSOrgLimitChannelVO.getProductCode()==null){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "orgId|productCode" });
		}
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		BMSOrgLimitChannel orgLimitChannel = new BMSOrgLimitChannel();
		BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
		Boolean flag = demoService.isDisabled(orgLimitChannel);
	    response.setRepMsg(String.valueOf(flag));
		return response;
	}
	
	@Override
	public ResListVO<ResBMSOrgLimitChannelVO> findByProductCodeList(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		if(reqBMSOrgLimitChannelVO.getProductCodeList() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "productCodeList" });
		}
		ResListVO<ResBMSOrgLimitChannelVO> res = new ResListVO<ResBMSOrgLimitChannelVO>();
		try {
			
			List<BMSOrgLimitChannel> orgLimitChannelList = demoService.findByProductCodeList(reqBMSOrgLimitChannelVO.getProductCodeList());
			
			// 构造响应参数
			List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
			for (BMSOrgLimitChannel demoEntity : orgLimitChannelList) {
				ResBMSOrgLimitChannelVO resDemoVO = new ResBMSOrgLimitChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			res.setParamList(records);
			
			res.setRepMsg("success");
			res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
		} catch (CoreException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return res;
	}
	
	@Override
	public ResListVO<ResBMSOrgLimitChannelVO> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		if(reqBMSOrgLimitChannelVO.getProductCodeList() == null
				|| reqBMSOrgLimitChannelVO.getProductCodeList().isEmpty()){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "productCodeList" });
		}
		ResListVO<ResBMSOrgLimitChannelVO> res = new ResListVO<ResBMSOrgLimitChannelVO>();
		try {
			
			List<BMSOrgLimitChannel> orgLimitChannelList = demoService.findOrgByProductCodeListIntersect(reqBMSOrgLimitChannelVO);
			
			// 构造响应参数
			List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
			for (BMSOrgLimitChannel demoEntity : orgLimitChannelList) {
				ResBMSOrgLimitChannelVO resDemoVO = new ResBMSOrgLimitChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			res.setParamList(records);
			
			res.setRepMsg("success");
			res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
		} catch (CoreException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return res;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> branchProductRelevanceCheck(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		ResListVO<ResBMSOrgLimitChannelVO> res = new ResListVO<ResBMSOrgLimitChannelVO>();
		try {
			boolean check = false;
			ResBMSOrgLimitChannelVO errorlVo = new ResBMSOrgLimitChannelVO();
			check = demoService.branchProductRelevanceCheck(reqBMSOrgLimitChannelVO);
			if(!check){
				BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, errorlVo);
			}
			res.setRepMsg("success");
			res.setRepCode(Response.SUCCESS_RESPONSE_CODE);
			res.setData(errorlVo);
		} catch (CoreException e) {
			res.setRepCode(e.getRealCode());
			res.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			res.setRepCode(CoreErrorCode.UNKNOWN_ERROR.getErrorCode());
			res.setRepMsg(CoreErrorCode.UNKNOWN_ERROR.getDefaultMessage() + e.getLocalizedMessage());
		}
		
		return res;
	}
	@Override
	public ResListVO<ResBMSOrgLimitChannelVO> listOrgProductLimitByOrgProApp(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		ResListVO<ResBMSOrgLimitChannelVO> response = new ResListVO<ResBMSOrgLimitChannelVO>();
		if(reqBMSOrgLimitChannelVO.getServiceCode() == null || reqBMSOrgLimitChannelVO.getServiceCode().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		if(reqBMSOrgLimitChannelVO.getServiceName() == null || reqBMSOrgLimitChannelVO.getServiceName().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceName"});
		}
		if(reqBMSOrgLimitChannelVO.getIp() == null || reqBMSOrgLimitChannelVO.getIp().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"ip"});
		}
		if(reqBMSOrgLimitChannelVO.getSysCode() == null || reqBMSOrgLimitChannelVO.getSysCode().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"sysCode"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSOrgLimitChannelVO);
			List<BMSOrgLimitChannel> orgLimitChannelList = demoService.listOrgProductLimitByOrgProApp(paramMap);
			if (orgLimitChannelList != null && orgLimitChannelList.size() > 0) {
				List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
				for (BMSOrgLimitChannel orgLimitChannel : orgLimitChannelList) {
					ResBMSOrgLimitChannelVO vo = new ResBMSOrgLimitChannelVO();
					BeanUtils.copyProperties(orgLimitChannel, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> updateByOrgLimitId(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		BMSOrgLimitChannel orgLimitChannel = new BMSOrgLimitChannel();
		BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
		long count = this.demoService.updateByOrgLimitId(orgLimitChannel);
		if(count>0){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|网点产品配置删除|delete","BMSOrgLimitChannelExecuter//logicalDelete","网点产品配置删除数"+count);
		}
		response.setRepMsg(String.valueOf(count)); //返回受影响的行数
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> findOrgLimitChannelLimitUnion(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		if(reqBMSOrgLimitChannelVO.getOrgId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"orgId"});
		}
		/*if(reqBMSOrgLimitChannelVO.getAuditLimit() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"auditLimit"});
		}*/
		if(reqBMSOrgLimitChannelVO.getServiceCode() == null || reqBMSOrgLimitChannelVO.getServiceCode().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceCode"});
		}
		if(reqBMSOrgLimitChannelVO.getServiceName() == null || reqBMSOrgLimitChannelVO.getServiceName().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceName"});
		}
		if(reqBMSOrgLimitChannelVO.getIp() == null || reqBMSOrgLimitChannelVO.getIp().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"ip"});
		}
		if(reqBMSOrgLimitChannelVO.getSysCode() == null || reqBMSOrgLimitChannelVO.getSysCode().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"sysCode"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSOrgLimitChannelVO);
			BMSOrgLimitChannel orgLimitChannel = demoService.findOrgLimitChannelLimitUnion(paramMap);
			if (orgLimitChannel != null) {
				ResBMSOrgLimitChannelVO vo = new ResBMSOrgLimitChannelVO();
				BeanUtils.copyProperties(orgLimitChannel, vo);
				response.setData(vo);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	
	@Override
	public Response<ResProductBaseListVO> listOrgLimitChannelRateBy(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResProductBaseListVO> response = new Response<ResProductBaseListVO>();
		ResProductBaseListVO listVO = new ResProductBaseListVO();
		// 参数校验 第二种方式 
//		if(StringUtils.isEmpty(reqBMSOrgLimitChannelVO.getProductCode())){
//			throw new DemoBizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCode"});
//		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSOrgLimitChannelVO);
			List<BMSOrgLimitChannel> listProduct = demoService
					.listOrgLimitChannelRateBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
				for (BMSOrgLimitChannel product : listProduct) {
					ResBMSOrgLimitChannelVO vo = new ResBMSOrgLimitChannelVO();
					BeanUtils.copyProperties(product, vo);
					records.add(vo);
				}
				listVO.setListOrgLimitChannel(records);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		response.setData(listVO);
		return response;
	}

}
