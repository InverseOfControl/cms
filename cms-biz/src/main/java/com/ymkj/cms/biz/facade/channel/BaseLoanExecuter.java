package com.ymkj.cms.biz.facade.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.channel.IBaseLoanExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAccessoryVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUpdBatchVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAccessoryVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBaseVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.biz.entity.channel.BMSLoanBaseBatch;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.IBMSBaseLoanService;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理 facecode impl
 */
@Service
@SuppressWarnings("rawtypes")
public class BaseLoanExecuter implements IBaseLoanExecuter {

	@Autowired
	private IBMSBaseLoanService bmsBaseLoanService;

	@Override
	public PageResponse<ResLoanBaseVo> searchLoanListby(ReqLoanBaseVo reqLoanBaseVo) {
		PageResponse<ResLoanBaseVo> response = new PageResponse<ResLoanBaseVo>();
		try {
			// 分页参数校验
			if (reqLoanBaseVo.getPage() == 0 || reqLoanBaseVo.getRows() == 0) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
			}
			// 构造请求参数
			PageParam pageParam = new PageParam(reqLoanBaseVo.getPage(), reqLoanBaseVo.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqLoanBaseVo);
			// 调用业务逻辑

			PageBean pageBean = bmsBaseLoanService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResLoanBaseVo> records = new ArrayList<ResLoanBaseVo>();
			@SuppressWarnings("unchecked")
			List<BMSLoanBaseBatch> demoEntitys = pageBean.getRecords();
			for (BMSLoanBaseBatch demoEntity : demoEntitys) {
				ResLoanBaseVo resDemoVO = new ResLoanBaseVo();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			response.setRecords(records);
			response.setTotalCount(pageBean.getTotalCount());
		} catch (BizException e) {
			response.setRepCode(e.getErrorMsg());
			response.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.FACADE_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		return response;
	}

	@Override
	public Response<List<ResLoanCheckExpVo>> exportLoanCheck(ReqLoanBaseVo reqLoanBaseVo) {
		Response<List<ResLoanCheckExpVo>> response = new Response<List<ResLoanCheckExpVo>>();
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("batchNum", reqLoanBaseVo.getBatchNum());
			List<ResLoanCheckExpVo> datas = bmsBaseLoanService.listLoanCheckExp(paraMap);
			response.setData(datas);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		return response;
	}

	@Override
	public Response<List<ResLoanExpVo>> exportLoan(ReqLoanBaseVo reqLoanBaseVo) {
		Response<List<ResLoanExpVo>> response = new Response<List<ResLoanExpVo>>();
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("batchNum", reqLoanBaseVo.getBatchNum());
			List<ResLoanExpVo> datas = bmsBaseLoanService.listLoanExp(paraMap);
			response.setData(datas);
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.FACADE_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		return response;
	}

	@Override
	public Response<String> updateBacth(ReqUpdBatchVo reqUpdBatchVo) {
		Response<String> response = new Response<String>();
		try {
			long nums = bmsBaseLoanService.delBatch(reqUpdBatchVo.getNewLoanNos());
			response.setData(String.valueOf(nums));
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.FACADE_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		return response;
	}
	
	@Override
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo) {
		Response<List<ResWMXTExportVo>> response=bmsBaseLoanService.wmxtExpLoanQuery(requestVo);
		return response;
	}
	@Override
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo) {
		return bmsBaseLoanService.findLoanNoByNum(newReqLoanBaseVo);
	}

	@Override
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo) {
		return bmsBaseLoanService.wmxtExpLoanCheck(requestVo);
	}

	@Override
	public Response<ResAccessoryVo> downAccessory(ReqAccessoryVo requestVo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile) {
		return bmsBaseLoanService.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
	}

	@Override
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck) {
		bmsBaseLoanService.checkRequestManagerOperateRecord(reqCheck);
	}

	@Override
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVo) {
		return bmsBaseLoanService.findCodebyParentIds(listResWMXTDataVo);
	}
}
