package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.Date;
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
import com.ymkj.cms.biz.api.service.master.IBMSReasonManagementExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSReasonManageService;
import com.ymkj.cms.biz.service.master.IBMSReasonManagementService;

/**
 * 服务提供者: 1. 请求信息验证 2. 调用业务层实现业务操作 3. 封装 响应response 4. 对于必须捕获的异常采用 参考
 * listPage 的用法
 * 
 * @author user
 * 
 */
@Service
public class BMSReasonManagementExecuter implements IBMSReasonManagementExecuter{

	@Autowired
	private IBMSReasonManagementService iBMSReasonManagementService;
	@Autowired
	private IBMSReasonManageService iBMSReasonManageService;
	
	@Override
	public PageResponse<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		PageResponse<ResBMSReasonVO> response = new PageResponse<ResBMSReasonVO>();

		// 参数校验
		if (reqReasonVO.getPageNum() == 0 || reqReasonVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}

		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqReasonVO.getPageNum(), reqReasonVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqReasonVO);

			// 调用业务逻辑
			PageBean<BMSReason> pageBean = iBMSReasonManagementService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResBMSReasonVO> records = new ArrayList<ResBMSReasonVO>();
			List<BMSReason> demoEntitys = pageBean.getRecords();
			for (BMSReason bmsReasonEntity : demoEntitys) {
				ResBMSReasonVO resReasonVO = new ResBMSReasonVO();
				BeanUtils.copyProperties(bmsReasonEntity, resReasonVO);
				records.add(resReasonVO);
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
	public Response<ResBMSReasonVO> addReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		BMSReason bmsReason = new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO, bmsReason);
		bmsReason.setIsDeleted((long) 0);
		if(reqBMSReasonVO.getCanRequestDays()!=null&&reqBMSReasonVO.getCanRequestDays().length()!=0){
			bmsReason.setCanRequestDays(Integer.parseInt(reqBMSReasonVO.getCanRequestDays()));
		}
		bmsReason.setCreatorDate(new Date());
		bmsReason.setOperationType(reqBMSReasonVO.getOperationType().toString());
		bmsReason.setCreator(reqBMSReasonVO.getCreator());
		bmsReason.setCreatorId(Long.parseLong(reqBMSReasonVO.getCreatorId()));
		long count = iBMSReasonManagementService.insert(bmsReason);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSReasonVO> queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		BMSReason bmsReason = iBMSReasonManagementService.getById(reqBMSReasonVO.getId());
		// 构造相应参数
		if (bmsReason != null) {
			ResBMSReasonVO resVO = new ResBMSReasonVO();
			BeanUtils.copyProperties(bmsReason, resVO);
			if(bmsReason.getParentId()!=0){
				BMSReason eV=iBMSReasonManageService.findReasonByPId(bmsReason.getParentId());	
				resVO.setFirstReason(eV.getReason());
			}
			if(null==bmsReason.getConditionType() || bmsReason.getConditionType().equals("")){
				resVO.setConditionType(null);
			}
           	response.setData(resVO);
		}
		return response;
	}

	@Override
	public Response<ResBMSReasonVO> editReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		BMSReason bmsReason = new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO, bmsReason);
		//bmsReason.setIsDeleted((long) 0);
		if(reqBMSReasonVO.getCanRequestDays()!=null&&reqBMSReasonVO.getCanRequestDays().length()!=0){
			bmsReason.setCanRequestDays(Integer.parseInt(reqBMSReasonVO.getCanRequestDays()));
		}
		bmsReason.setModifiedDate(new Date());
		bmsReason.setModified(reqBMSReasonVO.getModifier());
		//bmsReason.setOperationType(reqBMSReasonVO.getOperationType().toString());
		bmsReason.setModifiedId(reqBMSReasonVO.getModifierId());
		long count = iBMSReasonManagementService.update(bmsReason);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSReasonVO> deleteReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		BMSReason bmsReason = new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO, bmsReason);
		bmsReason.setIsDeleted(1L);
		bmsReason.setModifiedDate(new Date());
		bmsReason.setModified(reqBMSReasonVO.getModifier());
		bmsReason.setModifiedId(reqBMSReasonVO.getModifierId());
		long count = iBMSReasonManagementService.update(bmsReason);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSReasonVO> findBMSReasonByCode(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response=new Response<ResBMSReasonVO>();
		if (reqBMSReasonVO.getCode() == null ||reqBMSReasonVO.getCode() == "") {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "code" });
		}
		try {
			BMSReason Bmsreason=new BMSReason();
			BeanUtils.copyProperties(reqBMSReasonVO, Bmsreason);
			BMSReason reason=iBMSReasonManagementService.findBMSReasonByCode(Bmsreason);
			if(reason != null){
				ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
				BeanUtils.copyProperties(reason, resDemoVO);
				response.setData(resDemoVO);
			}	
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}

		return response;
	}
	
	

	
}
