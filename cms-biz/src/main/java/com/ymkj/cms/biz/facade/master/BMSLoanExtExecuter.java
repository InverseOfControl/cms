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
import com.ymkj.cms.biz.api.service.master.IBMSLoanExtExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

@Service
public class BMSLoanExtExecuter implements IBMSLoanExtExecuter{

	@Autowired
	private IBMSLoanExtEntityService bmsLoanExtEntityService;
	@Override
	public PageResponse<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqBMSLoanExtVO) {
		PageResponse<ResBMSLoanExtVO> response = new PageResponse<ResBMSLoanExtVO>();
		// 参数校验(分页)
		if (reqBMSLoanExtVO.getPageNum() == 0 || reqBMSLoanExtVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanBaseVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanExtVO.getPageNum(), reqBMSLoanExtVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanExtVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanExt> pageBean = bmsLoanExtEntityService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanExtVO> records = new ArrayList<ResBMSLoanExtVO>();
			List<BMSLoanExt> LoanExtEntitys = pageBean.getRecords();
			for (BMSLoanExt LoanExtEntity : LoanExtEntitys) {
				ResBMSLoanExtVO resLoanExtVO=new ResBMSLoanExtVO();
				BeanUtils.copyProperties(LoanExtEntity, resLoanExtVO);
				records.add(resLoanExtVO);
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
