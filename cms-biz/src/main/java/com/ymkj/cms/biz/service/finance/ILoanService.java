package com.ymkj.cms.biz.service.finance;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanConfirmationQuery;

public interface ILoanService extends BaseService<BMSLoanBase> {
	
	/**
	 *财务审核通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean passAuditLoan(ReqLoanVo reqLoanVo);
	
	
	/**
	 * 批量通过审核
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String, Object> bacthPassAudit(ReqLoanVo reqLoanVo) ;
	
	
	/**
	 * 放款确认通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String, Object> grantLoan(ReqLoanVo reqLoanVo) ;
	
	/**
	 * 放款确认通过(在核心操作了，需要更新借款数据)
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String, Object> grantLoanCore(ReqLoanVo reqLoanVo) ;
	
	
	/**
	 *放款确认回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean backLoan(ReqLoanVo reqLoanVo);
	

	/**
	 *放款审核回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean backAudit(ReqLoanVo reqLoanVo);
	

	/**
	 *放款审核已完成
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageBean<BMSLoanBase> doneListBy(ReqLoanVo reqLoanVo);
	

	/**
	 * 放款确认，外贸信托导入放款确认信息查询该合同是否审核过
	 */
	public Response<BMSLoanConfirmationQuery> auditCommit(ReqBMSWmxtVO vo);
	
	/**
	 * 查询该借款信息是否存在借款收费主表
	 */
	public Response<Integer> findLoanIdbyFeeInfo(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 查询用户借款信息以及用户信息
	 * */
	public Response<ResBMSLoanImportVO> queryUserLoanInfo(ReqImportExcelVO reqLxxdExcelVO);

/*	*//**
	 * 查询导出
	 * *//*
	public List<BMSLoanExportInfoEntity> queryLoanlistBy(Map<String, Object> paramMap);*/


	

}
