package com.ymkj.cms.web.boss.service.finance;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface ILoanAuditService {

	/**
	 * 放款审核待办页面
	 * @param reqLoanVo
	 * @return
	 */
	public PageResult<ResLoanVo> listPage(ReqLoanVo reqLoanVo);

	
	
	/**
	 * 放款审核已完成页面
	 * @param reqLoanVo
	 * @return
	 */
	public PageResult<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo);
	
	

	/**
	 * 通过审核
	 * @param reqLoanVo
	 * @return
	 */
	public Response<ResLoanVo> passAuditLoan(ReqLoanVo reqLoanVo);
	
	/**
	 * 批量
	 * @param reqLoanVo
	 * @return
	 */
	public Response<ResLoanVo> bacthPassAudit(ReqLoanVo reqLoanVo);
	
	
	/**
	 * 退回
	 * @param reqLoanVo
	 * @return
	 */
	public Response<ResLoanVo> backAudit(ReqLoanVo reqLoanVo);
	
	
	/**
	 *校验产品是否可用
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<String> valiProductIsDisabled(ReqLoanVo reqLoanVo);


	
	/**
	 *批量退回
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> bacthBackLoanAudit(ReqLoanVo reqLoanVo);
	
	/**
	 *查询导出数据
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo);

}
