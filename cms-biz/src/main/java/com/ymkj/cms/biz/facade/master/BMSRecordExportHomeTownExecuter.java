package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
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
import com.ymkj.cms.biz.api.service.master.IBMSRecordExportHomeTownExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;
import com.ymkj.cms.biz.entity.master.BMSRecordExportSZ;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSRecordExportHomeTownService;
import com.ymkj.cms.biz.service.master.IBMSRecordExportService;

@Service
public class BMSRecordExportHomeTownExecuter implements IBMSRecordExportHomeTownExecuter{

	@Autowired
	private IBMSRecordExportHomeTownService iBMSRecordExportHomeTownService;
	
	@Override
	public PageResponse<ResBMSRecordExportYDVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		PageResponse<ResBMSRecordExportYDVO> response = new PageResponse<ResBMSRecordExportYDVO>();
		// 参数校验
		if (reqBMSRecordExportVO.getPageNum() == 0 || reqBMSRecordExportVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSRecordExportVO.getPageNum(), reqBMSRecordExportVO.getPageSize());
			if(reqBMSRecordExportVO.getStartDate()!=null&&reqBMSRecordExportVO.getStartDate().length()!=0){
				reqBMSRecordExportVO.setStartDate(reqBMSRecordExportVO.getStartDate()+" 00:00:00");
			}
			
			if(reqBMSRecordExportVO.getEndDate()!=null&&reqBMSRecordExportVO.getEndDate().length()!=0){
				reqBMSRecordExportVO.setEndDate(reqBMSRecordExportVO.getEndDate()+" 23:59:59");
			}
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSRecordExportVO);

			// 调用业务逻辑,得到list集合
			PageBean<BMSRecordExportYD> pageBean = iBMSRecordExportHomeTownService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSRecordExportYDVO> records = new ArrayList<ResBMSRecordExportYDVO>();
			List<BMSRecordExportYD> demoEntitys = pageBean.getRecords();
			for (BMSRecordExportYD demoEntity : demoEntitys) {
				ResBMSRecordExportYDVO resDemoVO = new ResBMSRecordExportYDVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				resDemoVO.setMerchantCode("C00000000M31");
				resDemoVO.setExpenditure("04903");
				resDemoVO.setBankType("00");
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/* System.out.println(e); */
			e.printStackTrace();
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	
	@Override
	public Response<List<ResBMSRecordExportYDVO>> uploadExcelYD(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		Response<List<ResBMSRecordExportYDVO>> responses=new Response<List<ResBMSRecordExportYDVO>>();
		if(reqBMSRecordExportVO.getStartDate()!=null&&reqBMSRecordExportVO.getStartDate().length()!=0){
			reqBMSRecordExportVO.setStartDate(reqBMSRecordExportVO.getStartDate()+" 00:00:00");
		}
		
		if(reqBMSRecordExportVO.getEndDate()!=null&&reqBMSRecordExportVO.getEndDate().length()!=0){
			reqBMSRecordExportVO.setEndDate(reqBMSRecordExportVO.getEndDate()+" 23:59:59");
		}
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSRecordExportVO);
			List<BMSRecordExportYD> list=iBMSRecordExportHomeTownService.uploadExcelYD(paramMap);
			List<ResBMSRecordExportYDVO> records = new ArrayList<ResBMSRecordExportYDVO>();
			for(BMSRecordExportYD bMSRecordExportYD:list){
				ResBMSRecordExportYDVO vo=new ResBMSRecordExportYDVO();
				BeanUtils.copyProperties(bMSRecordExportYD, vo);
				vo.setMerchantCode("C00000000M31");
				vo.setExpenditure("04903");
				vo.setBankType("00");
				records.add(vo);
			}
			
			responses.setData(records);
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return responses;
	}
}
