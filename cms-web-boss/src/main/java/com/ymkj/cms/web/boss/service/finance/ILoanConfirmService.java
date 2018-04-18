package com.ymkj.cms.web.boss.service.finance;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportDocumentVo;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface ILoanConfirmService {
	/**
	 * 放款确认待办页面
	 * @param reqLoanVo
	 * @return
	 */
	public PageResult<ResLoanVo> listPage(ReqLoanVo reqLoanVo);

	
	
	/**
	 * 放款确认已完成页面
	 * @param reqLoanVo
	 * @return
	 */
	public PageResult<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo);
	
	
	
	/**
	 * 放款确认通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> grantLoan(ReqLoanVo reqLoanVo);
	

	
	/**
	 *放款确认回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanVo> backLoan(ReqLoanVo reqLoanVo);
	
	/**
	 *校验产品是否可用
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<String> valiProductIsDisabled(ReqLoanVo reqLoanVo);
	
	/**
	 * 放款确认导入放款信息txt文件
	 */
	public void auditCommit(List<ReqBMSWmxtVO> reqBMSWmxtVOs,HttpServletResponse response);
	
	/**
	 * 龙信小贷放款文件导入
	 * */
	public void importLoanDocument(ReqImportDocumentVo reqImportDocumentVo);


	/**
	 * 核心状态查询
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月27日下午5:04:29
	 */
	public Response<ResLoanVo> queryLoanCoreState(ReqLoanVo reqLoanVo);

	/**
	 * 放款确认批量退回
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月27日下午5:04:29
	 */
	public Response<ResLoanVo> bacthBackLoanConfirm(ReqLoanVo reqLoanVo);
	
	/**
	 *查询导出数据
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo);
}
