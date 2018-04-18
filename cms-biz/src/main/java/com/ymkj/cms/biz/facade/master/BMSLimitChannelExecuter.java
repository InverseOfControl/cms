package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

public class BMSLimitChannelExecuter  implements IBMSLimitChannelExecuter{
	@Autowired
	private IBMSLimitChannelService demoService;
	@Autowired
	private IBMSSysLogService sysLogService;
	@Override
	public Response<ResProductBaseListVO> listLimitChannelBy(
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
			List<BMSLimitChannel> listProduct = demoService
					.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
				for (BMSLimitChannel product : listProduct) {
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

			// 调用业务逻辑
			PageBean<BMSLimitChannel> pageBean = demoService.listPage(pageParam, paramMap);
			
			// 构造响应参数
			List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
			List<BMSLimitChannel> demoEntitys = pageBean.getRecords();
			for (BMSLimitChannel demoEntity : demoEntitys) {
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
		Boolean flag = demoService.saveLimitChannel(reqBMSOrgLimitChannelVO);
		if(flag){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|渠道产品配置保存|update","BMSLimitChannelExecuter.saveProductLimit()","渠道产品配置保存");
			
		}
	    response.setRepMsg(String.valueOf(flag));
		return response;
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> updateChannelLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {

		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		//更新渠道产品配置
		Boolean flag = demoService.updateByCondition(reqBMSOrgLimitChannelVO);
		//禁用时
		if(reqBMSOrgLimitChannelVO.getIsDisabled() != null  && reqBMSOrgLimitChannelVO.getIsDisabled()==1){
			//更新网点产品配置
			demoService.updateOrgLimitChannelAllByCondition(reqBMSOrgLimitChannelVO);
		}
		if(flag){
			//系统日志
			sysLogService.recordSysLog(reqBMSOrgLimitChannelVO,"产品管理|渠道产品|启动，禁用|update","BMSLimitChannelExecuter.updateChannelLimit()","启动，禁用");
			
		}
	    response.setRepMsg(String.valueOf(flag));
		return response;
	
	}
	@Override
	public Response<ResBMSOrgLimitChannelVO> logicalDelete(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		BMSLimitChannel orgLimitChannel = new BMSLimitChannel();
		BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
		long count = this.demoService.logicalDelete(orgLimitChannel);
		response.setRepMsg(String.valueOf(count)); //返回受影响的行数
		return response;
	}
	
	
	@Override
	public ResBMSOrgLimitChannelVO getFULimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {

		ResBMSOrgLimitChannelVO resBMSOrgLimitChannelVO = new ResBMSOrgLimitChannelVO();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSOrgLimitChannelVO.getProductCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCode"});
		}
		if(StringUtils.isEmpty(reqBMSOrgLimitChannelVO.getChannelCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"channelCode"});
		}
		if(reqBMSOrgLimitChannelVO.getAuditLimit() ==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"auditLimit"});
		}
		try {
			// 构造请求参数
			BMSLimitChannel orgLimitChannel = new BMSLimitChannel();
			BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
			BMSLimitChannel bmsLimitChannel = demoService.getFULimit(orgLimitChannel);
			if (bmsLimitChannel != null ) {
					BeanUtils.copyProperties(bmsLimitChannel, resBMSOrgLimitChannelVO);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		
		return resBMSOrgLimitChannelVO;
	}
	@Override
	public ResBMSOrgLimitChannelVO getFULimitByOrgId(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		ResBMSOrgLimitChannelVO resBMSOrgLimitChannelVO = new ResBMSOrgLimitChannelVO();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSOrgLimitChannelVO.getProductCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCode"});
		}
		if(reqBMSOrgLimitChannelVO.getOrgId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"orgId"});
		}
		if(reqBMSOrgLimitChannelVO.getAuditLimit() ==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"auditLimit"});
		}
		try {
			// 构造请求参数
			BMSLimitChannel orgLimitChannel = new BMSLimitChannel();
			BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
			BMSLimitChannel bmsLimitChannel = demoService.getFULimitByOrgId(orgLimitChannel);
			if (bmsLimitChannel != null ) {
					BeanUtils.copyProperties(bmsLimitChannel, resBMSOrgLimitChannelVO);
			}
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		
		return resBMSOrgLimitChannelVO;
	}
	@Override
	public Response<ResBMSLimitChannelVO> updateByAuLimitId(
		ReqBMSLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSLimitChannelVO> response = new Response<ResBMSLimitChannelVO>();
		BMSLimitChannel orgLimitChannel = new BMSLimitChannel();
		BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, orgLimitChannel);
		long count = this.demoService.updateByAuLimitId(orgLimitChannel);
		response.setRepMsg(String.valueOf(count)); //返回受影响的行数
		return response;
		
	}
	@Override
	public PageResponse<ResBMSLimitChannelVO> listPage( ReqBMSLimitChannelVO reqBMSLimitChannelVo) {
		   PageResponse<ResBMSLimitChannelVO> response = new PageResponse<ResBMSLimitChannelVO>();
			
			// 参数校验
			if(reqBMSLimitChannelVo.getPageNum()==0 || reqBMSLimitChannelVo.getPageSize()==0){
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
			}
			try {
				// 构造请求参数
				PageParam pageParam = new PageParam(reqBMSLimitChannelVo.getPageNum(), reqBMSLimitChannelVo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLimitChannelVo);
				// 调用业务逻辑,得到list集合
				PageBean<BMSLimitChannel> pageBean = demoService.listPage(pageParam, paramMap);
			/*	System.out.println("-------------------pageBean.getRecords().get(0):"+pageBean.getRecords().get(0));*/
				// 构造响应参数
				List<ResBMSLimitChannelVO> records = new ArrayList<ResBMSLimitChannelVO>();
				List<BMSLimitChannel> demoEntitys = pageBean.getRecords();
				for (BMSLimitChannel demoEntity : demoEntitys) {
					ResBMSLimitChannelVO resDemoVO = new ResBMSLimitChannelVO();
					BeanUtils.copyProperties(demoEntity, resDemoVO);
					records.add(resDemoVO);
					
					
				}
				// 忽略 复制的字段
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);
				/*response.setData(listVO);*/

			} catch (Exception e) { 
				// 抛出自定义异常
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			
			}
			return response;
	}
	@Override
	public Response<ResProductBaseListVO> listLimitChannelRateBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResProductBaseListVO> response = new Response<ResProductBaseListVO>();
		ResProductBaseListVO listVO = new ResProductBaseListVO();
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSOrgLimitChannelVO);
			List<BMSLimitChannel> listProduct = demoService.listByRate(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSOrgLimitChannelVO> records = new ArrayList<ResBMSOrgLimitChannelVO>();
				for (BMSLimitChannel product : listProduct) {
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
