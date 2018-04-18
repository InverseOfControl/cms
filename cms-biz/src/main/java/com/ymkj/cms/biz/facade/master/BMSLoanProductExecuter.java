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
import com.ymkj.cms.biz.api.service.master.IBMSLoanProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSLoanProductEntityService;

@Service
public class BMSLoanProductExecuter implements IBMSLoanProductExecuter{

	@Autowired
	private IBMSLoanProductEntityService iBMSLoanProductEntityService;
	@Override
	public PageResponse<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqBMSLoanProductVO) {
		PageResponse<ResBMSLoanProductVO> response = new PageResponse<ResBMSLoanProductVO>();
		// 参数校验(分页)
		if (reqBMSLoanProductVO.getPageNum() == 0 || reqBMSLoanProductVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanBaseVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanProductVO.getPageNum(), reqBMSLoanProductVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanProductVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanProduct> pageBean = iBMSLoanProductEntityService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanProductVO> records = new ArrayList<ResBMSLoanProductVO>();
			List<BMSLoanProduct> loanProductEntitys = pageBean.getRecords();
			for (BMSLoanProduct loanProductEntity : loanProductEntitys) {
				ResBMSLoanProductVO resLoanProductVO=new ResBMSLoanProductVO();
				BeanUtils.copyProperties(loanProductEntity, resLoanProductVO);
				records.add(resLoanProductVO);
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
