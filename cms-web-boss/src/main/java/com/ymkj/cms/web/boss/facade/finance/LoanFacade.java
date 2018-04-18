package com.ymkj.cms.web.boss.facade.finance;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.BMSLoanConfirmationQueryVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;


@Component
public class LoanFacade extends BaseFacade {

	@Autowired
	private ILoanExecuter loanExecuter;
	
	

	
	
	public Response<ResLoanVo> passAuditLoan(ReqLoanVo reqLoanVo){
		// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.passAuditLoan(reqLoanVo);

				// 响应结果处理, 如果失败 则抛出 处理失败异常
				return response;
			
	}
	
	public PageResult<ResLoanVo> listPage(ReqLoanVo reqDemoVO) {
		
		// 业务调用
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSCODE);
		PageResponse<ResLoanVo> pageResponse = loanExecuter.listPage(reqDemoVO);

	
			PageResult<ResLoanVo> pageResult = new PageResult<ResLoanVo>();
			
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
	
	}

	public PageResult<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo) {
		// 业务调用
		reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				PageResponse<ResLoanVo> pageResponse = loanExecuter.doneListPage(reqLoanVo);

			
					PageResult<ResLoanVo> pageResult = new PageResult<ResLoanVo>();
					
					BeanUtils.copyProperties(pageResponse, pageResult);
					return pageResult;
				
	}
	
	
		/**
		 * 审核批量通过
		 * @param reqLoanContractSignVo
		 * @return
		 */
		public Response<ResLoanVo> bacthPassAudit(ReqLoanVo reqLoanVo) {
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			Response<ResLoanVo> response = loanExecuter.bacthPassAudit(reqLoanVo);

		
			return response;
			
		}
			
	
	
		/**
		 * 审核退回
		 * @param reqLoanContractSignVo
		 * @return
		 */
		public Response<ResLoanVo> backAudit(ReqLoanVo reqLoanVo){
			// 请求参数构造
			reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
			// 接口调用
			Response<ResLoanVo> response = loanExecuter.backAudit(reqLoanVo);

			// 响应结果处理, 如果失败 则抛出 处理失败异常
			
			return response;

		}


	
	
			/**
			 * 放款确认通过
			 * @param reqLoanContractSignVo
			 * @return
			 */
			public Response<ResLoanVo> grantLoan(ReqLoanVo reqLoanVo){
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.grantLoan(reqLoanVo);

				// 响应结果处理, 如果失败 则抛出 处理失败异常
		
					return response;
				
			}
			
		
			
			/**
			 *放款确认回退
			 * @param reqLoanContractSignVo
			 * @return
			 */
			public Response<ResLoanVo> backLoan(ReqLoanVo reqLoanVo){
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.backLoan(reqLoanVo);

				// 响应结果处理, 如果失败 则抛出 处理失败异常
				return response;
				
			}
			
			
			
			/**
			 *校验产品是否可用
			 * @param reqLoanContractSignVo
			 * @return
			 */
			public Response<String> valiProductIsDisabled(ReqLoanVo reqLoanVo){
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<String> response = loanExecuter.valiProductIsDisabled(reqLoanVo);

				// 响应结果处理, 如果失败 则抛出 处理失败异常
				return response;
				
				
			}
			
			
			/**
			 * 外贸信托放款确认导入放款确认信息查询该合同是否审核过
			 */
			public Response<BMSLoanConfirmationQueryVO> auditCommit(ReqBMSWmxtVO vo){
				return loanExecuter.auditCommit(vo);
				
			}
			/**
			 *查询借款ID再借款收费主表是否存在
			 */
			public Response<Integer> findLoanIdbyFeeInfo(ReqBMSLoanBaseVO reqBMSLoanBaseVO) {
				return loanExecuter.findLoanIdbyFeeInfo(reqBMSLoanBaseVO);
			}
			/**
			 * 核心状态查询
			 * @param reqLoanVo
			 * @return
			 * @author lix YM10160
			 * @date 2017年6月27日下午5:05:34
			 */
			public Response<ResLoanVo> queryLoanCoreState(ReqLoanVo reqLoanVo) {
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.queryLoanCoreState(reqLoanVo);

				// 响应结果处理, 如果失败 则抛出 处理失败异常
				return response;
			}

			public Response<ResLoanVo> bacthBackLoanAudit(ReqLoanVo reqLoanVo) {
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.bacthBackLoanAudit(reqLoanVo);

			
				return response;
			}

			public Response<ResLoanVo> bacthBackLoanConfirm(ReqLoanVo reqLoanVo) {
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
				Response<ResLoanVo> response = loanExecuter.bacthBackLoanConfirm(reqLoanVo);

			
				return response;
			}

			/**
			 * 放款审核导出数据查询
			 * @param reqLoanVo
			 * @return
			 * @author lifz YM10160
			 * @date 2017年7月4日下午5:06:18
			 */
			public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo){
				// 请求参数构造
				reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
				// 接口调用
			    ResListVO<ResLoanExportInfoVo> response = loanExecuter.findLoanExportInfo(reqLoanVo);
				return response;
			}
}
