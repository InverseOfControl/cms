package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSLoanAuditExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSLoanBaseExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanAuditVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanAuditVO;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLoanAuditEntityService;

@Service
public class BMSLoanAuditExecuter implements IBMSLoanAuditExecuter{

	@Autowired
	private IBMSLoanAuditEntityService bmsLoanAuditEntityService;
	@Override
	public PageResponse<ResBMSLoanAuditVO> listPage(ReqBMSLoanAuditVO reqBMSLoanAuditVO) {
		PageResponse<ResBMSLoanAuditVO> response = new PageResponse<ResBMSLoanAuditVO>();
		// 参数校验(分页)
		if (reqBMSLoanAuditVO.getPageNum() == 0 || reqBMSLoanAuditVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanBaseVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanAuditVO.getPageNum(), reqBMSLoanAuditVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanAuditVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanAudit> pageBean = bmsLoanAuditEntityService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanAuditVO> records = new ArrayList<ResBMSLoanAuditVO>();
			List<BMSLoanAudit> LoanAuditEntitys = pageBean.getRecords();
			for (BMSLoanAudit LoanAuditEntity : LoanAuditEntitys) {
				ResBMSLoanAuditVO resLoanAuditVO=new ResBMSLoanAuditVO();
				BeanUtils.copyProperties(LoanAuditEntity, resLoanAuditVO);
				records.add(resLoanAuditVO);
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
}
