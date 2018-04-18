package com.ymkj.cms.biz.api.service.finance;


import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.BMSLoanConfirmationQueryVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

public interface ILoanExecuter {

	/**
	 *审核通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> passAuditLoan(ReqLoanVo reqLoanVo);
	
	
	/**
	 * 审核批量通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> bacthPassAudit(ReqLoanVo reqLoanVo) ;
	
	
	
	/**
	 * 审核退回
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> backAudit(ReqLoanVo reqLoanVo) ;
	

	
	
	/**
	 * 放款确认通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> grantLoan(ReqLoanVo reqLoanVo) ;
	

	
	/**
	 *放款确认回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> backLoan(ReqLoanVo reqLoanVo);
	
	
	
	/**
	 *查询待办列表
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanVo> listPage(ReqLoanVo reqLoanVo);
	
	
	/**
	 *查询已完成列表
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo);
	
	/**
	 *校验产品是否可用
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<String> valiProductIsDisabled( ReqLoanVo reqLoanVo);
	
	/**
	 * 放款确认，外贸信托导入已经放款的信息查询该合同是否审核过
	 */
	public Response<BMSLoanConfirmationQueryVO> auditCommit(ReqBMSWmxtVO vo);	
	
	/**
	 * 查询借款信息在借款收费主表是否存在记录
	 */
	public Response<Integer> findLoanIdbyFeeInfo(ReqBMSLoanBaseVO reqBMSLoanBaseVO);
	
	/**
	 * 龙信小贷借款信息查询
	 * */
	public Response<ResBMSLoanImportVO> queryUserLoanInfo(ReqImportExcelVO importExcelVO);

	/**
	 * 核心状态查询
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月27日下午5:06:18
	 */
	public Response<ResLoanVo> queryLoanCoreState(ReqLoanVo reqLoanVo);

	/**
	 * 放款审核批量退回
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10139
	 * @date 2017年6月30日下午5:06:18
	 */
	public Response<ResLoanVo> bacthBackLoanAudit(ReqLoanVo reqLoanVo);

	/**
	 * 放款确认批量退回
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10139
	 * @date 2017年6月30日下午5:06:18
	 */
	public Response<ResLoanVo> bacthBackLoanConfirm(ReqLoanVo reqLoanVo);
	
	
	/**
	 * 放款审核导出数据查询
	 * @param reqLoanVo
	 * @return
	 * @author lifz YM10160
	 * @date 2017年7月4日下午5:06:18
	 */
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo);
	


}
