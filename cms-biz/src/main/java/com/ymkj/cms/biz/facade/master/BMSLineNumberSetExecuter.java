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
import com.ymkj.cms.biz.api.service.master.IBMSLineNumberSetExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDic;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLineNumberSetService;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseEntityService;

@Service
public class BMSLineNumberSetExecuter implements IBMSLineNumberSetExecuter{

	
	
	@Autowired
	private IBMSLineNumberSetService iBMSLineNumberSetService;
	@Override
	public PageResponse<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO) {
		PageResponse<ResLineNumberSetVO> response = new PageResponse<ResLineNumberSetVO>();
		// 参数校验
		if (reqLineNumberSetVO.getPageNum() == 0 || reqLineNumberSetVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqLineNumberSetVO.getPageNum(), reqLineNumberSetVO.getPageSize());

			Map<String, Object> paramMap = BeanKit.bean2map(reqLineNumberSetVO);

			// 调用业务逻辑,得到list集合
			PageBean<BMSOffLineOfferBankDic> pageBean = iBMSLineNumberSetService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResLineNumberSetVO> records = new ArrayList<ResLineNumberSetVO>();
			List<BMSOffLineOfferBankDic> demoEntitys = pageBean.getRecords();
			for (BMSOffLineOfferBankDic demoEntity : demoEntitys) {
				ResLineNumberSetVO resDemoVO = new ResLineNumberSetVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
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
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO) {
		Response<Integer> res=iBMSLineNumberSetService.updateLineNumber(reqLineNumberSetVO);
		return res;
	}
	@Override
	public void insertOrUpdateDic(ReqLineNumberSetVO reqLineNumberSetVO) {
		List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs=reqLineNumberSetVO.getLineNumberUploadVOs();
		String id=reqLineNumberSetVO.getId();
		String name=reqLineNumberSetVO.getName();
		String importExcelAreaType=reqLineNumberSetVO.getImportExcelAreaType();
		iBMSLineNumberSetService.insertOrUpdateDic(LineNumberUploadVOs,importExcelAreaType,name,id);
		
	}

}
