package com.ymkj.cms.web.boss.service.finance.impl;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.constant.BmsConstant;
import com.ymkj.cms.biz.api.enums.EnumChannelCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants.LoanStatus;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportDocumentVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSWmxtVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.BMSLoanConfirmationQueryVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.common.ValidationUtils;
import com.ymkj.cms.web.boss.facade.finance.LoanFacade;
import com.ymkj.cms.web.boss.facade.finance.LoanFeeInfoFacade;
import com.ymkj.cms.web.boss.service.finance.ILoanConfirmService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Service
public class LoanConfirmServiceImpl implements ILoanConfirmService{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanConfirmServiceImpl.class);
	@Autowired
	private LoanFacade loanFacade;
	@Autowired
	private ILoanExecuter loanExecuter;
	@Autowired
	private LoanFeeInfoFacade loanFeeInfoFacade;
	
	@Override
	public PageResult<ResLoanVo> listPage(ReqLoanVo reqLoanVo) {
		reqLoanVo.setSysCode("11111111");
		PageResult<ResLoanVo> pageResult = loanFacade.listPage(reqLoanVo);
		return pageResult;
	}

	@Override
	public PageResult<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo) {
		reqLoanVo.setSysCode("11111111");
		PageResult<ResLoanVo> pageResult = loanFacade.doneListPage(reqLoanVo);
		return pageResult;
	}

	@Override
	public Response<ResLoanVo> grantLoan(ReqLoanVo reqLoanVo) {
		
		return loanFacade.grantLoan(reqLoanVo);
	}

	@Override
	public  Response<ResLoanVo> backLoan(ReqLoanVo reqLoanVo) {
	
		return loanFacade.backLoan(reqLoanVo);
	}
	
	@Override
	public Response<String> valiProductIsDisabled(ReqLoanVo reqLoanVo) {
	
		return loanFacade.valiProductIsDisabled(reqLoanVo);
	}

	@Override
	public void auditCommit(List<ReqBMSWmxtVO> reqBMSWmxtVOs,HttpServletResponse response) {
		LOGGER.info("准备调用核心借口-----------------------------------------");
		ReqLoanVo reqLoanVo=new ReqLoanVo();
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		reqLoanVo.setSysCode("bms");
		reqLoanVo.setChannelCd("00014");
		reqLoanVo.setServiceCode(resEmployeeVO.getUsercode());
		reqLoanVo.setServiceName(resEmployeeVO.getName());
		reqLoanVo.setIp(StringUtils.getLocalIP());
		List<ReqLoanVo> listReqLoanVo=new ArrayList<ReqLoanVo>();
		
		Map<String,String> mapString=new HashMap<String,String>();
		
		for(ReqBMSWmxtVO reqBMSWmxtVO:reqBMSWmxtVOs){
			if(reqBMSWmxtVO.getIsSuccessStatus().equals("true")){
				reqBMSWmxtVO.setSysCode(EnumConstants.BMS_SYSCODE);
				Response<BMSLoanConfirmationQueryVO> isSuccess=loanFacade.auditCommit(reqBMSWmxtVO);
				if(null==isSuccess.getData()){
					reqBMSWmxtVO.setIsSuccessStatus("失败");
					reqBMSWmxtVO.setFailReason("借款信息不存在,");
					continue;
				}else{//存在放款审核的申请信息
					SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf1 =new SimpleDateFormat("yyyyMMdd");
					
					try {
						if((sdf1.parse(reqBMSWmxtVO.getBankReturnTime().substring(0, 8))).getTime()<(sdf.parse(sdf.format(isSuccess.getData().getApplyDate()))).getTime()){
							reqBMSWmxtVO.setIsSuccessStatus("失败");
							reqBMSWmxtVO.setFailReason("申请日期不能大于放款日期,");
							continue;
						}
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.info("转换日期出错了-----------------------------------------");
						throw new RuntimeException("转换日期出错");
					}
					
					//掉核心接口放款
					ReqLoanVo addList=new ReqLoanVo();
					addList.setId(isSuccess.getData().getId());
					addList.setLoanNo(isSuccess.getData().getLoanNo());
					addList.setContractBranchId(isSuccess.getData().getContractBranchId());
					addList.setContractBranch(isSuccess.getData().getContractBranch());
					listReqLoanVo.add(addList);
					//创建MAP，组装核心借口返回的信息是成功还是失败 ，用于组装下面的返回EXCEL 的值
					mapString.put(isSuccess.getData().getLoanNo(), reqBMSWmxtVO.getAccountNo());
				}
			}else{
				reqBMSWmxtVO.setIsSuccessStatus("失败");
			}
			
			
		}
		
		reqLoanVo.setLoanList(listReqLoanVo);
		
		
		if(listReqLoanVo.size()>0){
			Response<ResLoanVo> resLoanVo = loanExecuter.grantLoan(reqLoanVo);
			List<ResLoanVo> successList=resLoanVo.getData().getSuccessList();
			List<ResLoanVo> failList=resLoanVo.getData().getFailList();
			if(null!=successList&&successList.size()>0){
				for(ResLoanVo ResLoanVosuccess:successList){
					String accountNo=mapString.get(ResLoanVosuccess.getLoanNo());
					for(ReqBMSWmxtVO reqBMSWmxtVO:reqBMSWmxtVOs){
						if(reqBMSWmxtVO.getIsSuccessStatus().equals("true")){
							if(reqBMSWmxtVO.getAccountNo().equals(accountNo)){
								reqBMSWmxtVO.setIsSuccessStatus("成功");
								reqBMSWmxtVO.setFailReason("放款成功");
							}
						}
					}
				}
			}
			if(null!=failList&&failList.size()>0){
				for(ResLoanVo ResLoanVofail:failList){
					String accountNo=mapString.get(ResLoanVofail.getLoanNo());
					for(ReqBMSWmxtVO reqBMSWmxtVO:reqBMSWmxtVOs){
						if(reqBMSWmxtVO.getIsSuccessStatus().equals("true")){
							if(reqBMSWmxtVO.getAccountNo().equals(accountNo)){
								reqBMSWmxtVO.setIsSuccessStatus("失败");
								reqBMSWmxtVO.setFailReason("调用核心放款失败,");
							}
						}
					}
				}
			}
		}
		
		//导出EXCEL
		try {
			CreateExcel(reqBMSWmxtVOs,response);
		} catch (IOException e) {
			LOGGER.info("准备调用核心借口-----------------------------------------");
			e.printStackTrace();
		}
		
	}
	
	
	
	// 创建excel文件
			public void CreateExcel(List<ReqBMSWmxtVO> reqBMSWmxtVOsList, HttpServletResponse response) throws IOException {
				response.setContentType("application/vnd.ms-excel");
				ServletOutputStream out = response.getOutputStream();
				Date nowDate = new Date();
				String fileName = "外贸信托导入数据结果" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
				if (reqBMSWmxtVOsList.size() > 0) {
					List<String[]> rows = new ArrayList<String[]>();
					for (ReqBMSWmxtVO vo : reqBMSWmxtVOsList) {
						String[] row = new String[9];
						// 机构代码
						row[0] = new String(vo.getOrganizationCode()==null?"":vo.getOrganizationCode());
						// 合同号
						row[1] = new String(vo.getAccountNo()==null?"":vo.getAccountNo());
						//放款结果
						row[2] = new String(vo.getLoanResult()==null?"":vo.getLoanResult());
						// 银行回盘时间
						row[3] = new String(vo.getBankReturnTime()==null?"":vo.getBankReturnTime());
						//失败原因
						row[4] = new String(vo.getErrorReason()==null?"":vo.getErrorReason());
						//审批结果
						row[5] = new String(vo.getApprovalResult()==null?"":vo.getApprovalResult());
						//审批拒绝原因
						row[6] = new String(vo.getApprovalReason()==null?"":vo.getApprovalReason());
						//导入成功或者失败状态
						row[7] = new String(vo.getIsSuccessStatus()==null?"":vo.getIsSuccessStatus());
						//成功或者失败信息
						row[8] = new String(vo.getFailReason()==null?"":vo.getFailReason());
						rows.add(row);
					}
					// 创建excel导出帮助类的对象
					ExportExcel exportExcel = new ExportExcel();
					exportExcel.setRows(rows);
					// 创建ExportExcel的List集合对象
					List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
					exportExcelList.add(exportExcel);

					// 设置导出文件的头部标题
					String[] headers = new String[9];
					headers[0] = new String("机构代码");
					headers[1] = new String("合同号");
					headers[2] = new String("放款结果");
					headers[3] = new String("银行回盘时间");
					headers[4] = new String("失败原因");
					headers[5] = new String("审批结果");
					headers[6] = new String("审批拒绝原因");
					headers[7] = new String("状态");
					headers[8] = new String("导入失败原因");
					
					// 创建workbook
					XSSFWorkbook xssf_w_book = new XSSFWorkbook();
					XSSFRow xssf_w_row = null;// 创建一行
					XSSFCell xssf_w_cell = null;// 创建每个单元格
					XSSFSheet sheet = xssf_w_book.createSheet("外贸信托导入结果");

					XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
					XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
					/* head_font.setFontHeightInPoints((short) 90); */
					/*
					 * cellStyle_CN.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
					 * cellStyle_CN.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
					 * // 上下居中 cellStyle_CN.setWrapText(false);// 不换行显示
					 */
					XSSFCellStyle cellStyle_Red = xssf_w_book.createCellStyle();// 创建失败样式
					XSSFFont font = xssf_w_book.createFont();
					/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
					font.setColor(IndexedColors.RED.getIndex());
					cellStyle_Red.setFont(font);
					/*
					 * cellStyle_Red.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
					 * cellStyle_Red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER)
					 * ;// 上下居中 cellStyle_Red.setWrapText(false);// 不换行显示
					 */
					XSSFCellStyle cellStyle_errorInfo = xssf_w_book.createCellStyle();// 创建失败样式
					XSSFFont errorInfoFont = xssf_w_book.createFont();
					/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
					errorInfoFont.setColor(IndexedColors.RED.getIndex());
					cellStyle_errorInfo.setFont(errorInfoFont);

					XSSFRow headerRow = sheet.createRow(0);
					// 自适应列宽
					sheet.autoSizeColumn(1);
					sheet.autoSizeColumn(1, true);
					/*
					 * sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
					 * sheet.getNumMergedRegions();
					 */
					for (int i = 0; i < headers.length; i++) {
						XSSFCell cell = headerRow.createCell(i);
						cell.setCellStyle(cellStyle_CN);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(headers[i]);
					}
					for (int i = 0; i < 1; i++) {
						if (exportExcelList.size() > 0) {
							int rowLength = exportExcelList.get(0).getRows().size();
							// 遍历行
							for (int j = 0; j < rowLength; j++) {
								// 创建行
								xssf_w_row = sheet.createRow(j + 1);

								for (int a = 0; a < exportExcelList.get(i).getRows().get(j).length; a++) {

									// 创建单元格
									xssf_w_cell = xssf_w_row.createCell(a);
									if (exportExcelList.get(i).getRows().get(j)[7].equals("失败")) {
										// 数据显示单元格样式设置

										xssf_w_cell.setCellStyle(cellStyle_errorInfo);

									} else {
										xssf_w_cell.setCellStyle(cellStyle_CN);
									}

									// xssf_w_sheet.autoSizeColumn(a, true);
									// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
									 
									xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
									

								}

							}

						}
						/*
						 * for (int k = 0; k < headers.length; k++) { // 创建单元格
						 * sheet.autoSizeColumn(k);
						 * 
						 * }
						 */
						sheet.setColumnWidth(1, 256 * 8 + 184);
						sheet.setColumnWidth(2, 256 * 12 + 184);
						sheet.setColumnWidth(3, 256 * 12 + 184);
						sheet.setColumnWidth(4, 256 * 8 + 184);
						sheet.setColumnWidth(5, 256 * 12 + 184);
						sheet.setColumnWidth(6, 256 * 12 + 184);
						sheet.setColumnWidth(7, 256 * 12 + 184);
						sheet.setColumnWidth(8, 256 * 12 + 184);
						sheet.setColumnWidth(9, 256 * 12 + 184);
					}

					xssf_w_book.write(out);
					out.flush();
					out.close();

				}

			}
	
	
	@Override
	public void importLoanDocument(ReqImportDocumentVo requestVo) {
		// TODO Auto-generated method stub
		List<ReqImportExcelVO> importDatas = requestVo.getDatas();
		if (!CollectionUtils.isEmpty(importDatas)) {
			List<ReqLoanVo> reqLoanList = new ArrayList<ReqLoanVo>();
			for (ReqImportExcelVO data : importDatas) {
				ResBMSLoanImportVO result = validatorBeforeLoan(data);
				if (null != result) {
					ReqLoanVo loanVo = new ReqLoanVo();
					loanVo.setId(result.getLoanId());
					loanVo.setLoanNo(result.getLoanNo());
					loanVo.setContractBranchId(result.getContractBranchId());
					loanVo.setContractBranch(result.getContractBranch());
					reqLoanList.add(loanVo);
				}

			}
			if (!CollectionUtils.isEmpty(reqLoanList)) {
				ReqLoanVo reqLoanVo = new ReqLoanVo();
				reqLoanVo.setSysCode(SystemCode.SysCode);
				reqLoanVo.setChannelCd(EnumChannelCode.龙信小贷.getChannelCode());
				reqLoanVo.setServiceCode(requestVo.getServiceCode());
				reqLoanVo.setServiceName(requestVo.getServiceName());
				reqLoanVo.setIp(StringUtils.getLocalIP());
				reqLoanVo.setLoanList(reqLoanList);
				//此处有环绕通知切面,因此不捕捉异常
				LOGGER.info("调用接口放款请求参数："+JSON.toJSONString(reqLoanVo));
				ResLoanVo resLoanVo = loanExecuter.grantLoan(reqLoanVo).getData();
				LOGGER.info("调用接口放款返回参数："+JSON.toJSONString(resLoanVo));
				//处理返回结果，有可能部分成功，部分失败
				afterLoan(importDatas,resLoanVo);
			}
		}
	}
	
	/**
	 * 校验参数合法性,合法则返回ResBMSLoanImportVO,反之返回null
	 * @param data
	 * @return
	 */
	private ResBMSLoanImportVO validatorBeforeLoan(ReqImportExcelVO data){
		String result = ValidationUtils.validateEntity(data);
		//错误信息记入反馈结果中
		if(!StringUtils.isEmpty(result)){
			data.setFeedBack(result);
			return null;
		}
		//没有出错，则继续校验
		Response<ResBMSLoanImportVO> resp = loanExecuter.queryUserLoanInfo(data);
		ResBMSLoanImportVO respBean = resp.getData();
		if(null == respBean){//查询不到，说明借款信息不存在
			data.setFeedBack(BmsConstant.ERRORM_MSG[0]);
			return null;
		}
		if (respBean.getContractNum() == null || data.getPactMoney().compareTo(respBean.getContractNum()) != 0) {// 合同金额不一致
			data.setFeedBack(BmsConstant.ERRORM_MSG[1]);
			return null;
		}
		if(respBean.getApplyDate() == null || data.getLoanDate().compareTo(DateUtil.defaultFormatDay(respBean.getApplyDate()))<0){
			data.setFeedBack(BmsConstant.ERRORM_MSG[2]);
			return null;
		}
		if(BmsConstant.FAILED.equals(data.getResult().trim())){
			data.setFeedBack(BmsConstant.ERRORM_MSG[3]);
			return null;
		}
		if(validatorLoanYet(respBean.getLoanStatus(),respBean.getRtfState(),respBean.getRtfNodeState())){
			data.setFeedBack(BmsConstant.ERRORM_MSG[4]);//防止重复放款
			return null;
		}
		data.setLoanNo(respBean.getLoanNo());//loanNo用于匹配放款结果
		return respBean;
	}
	
	/**
	 * 防止重复放款，校验是否已经放款过了
	 * @param status
	 * @param rtfState
	 * @param rtfNodestate
	 * @return
	 */
	private boolean validatorLoanYet(String status, 
			                         String rtfState,
			                         String rtfNodestate) {
		boolean resultOne   = LoanStatus.NORMAL.getValue().equals(status);
		boolean resultTwo   = EnumConstants.RtfState.DHHK.getValue().equals(rtfState);
		boolean resultThree = EnumConstants.RtfNodeState.FKQRSUBMIT.getValue().equals(rtfNodestate);
		return resultOne && resultTwo && resultThree;
	}
	
	/**
	 * 放款之后根据loanNo,将放款结果写入reqVo反馈栏中
	 * @param reqVo
	 * @param resLoanVo
	 * @return
	 */
	private void afterLoan(List<ReqImportExcelVO> reqVo, ResLoanVo resLoanVo) {
		if (!CollectionUtils.isEmpty(reqVo)) {
			for (ReqImportExcelVO excelVo : reqVo) {
				if (StringUtils.isNotBlank(excelVo.getFeedBack())) {// 已经有返回结果的不用处理
					continue;
				}
				if (resLoanVo != null && !CollectionUtils.isEmpty(resLoanVo.getSuccessList())) {
					for (ResLoanVo successLoan : resLoanVo.getSuccessList()) {
						if (excelVo.getLoanNo().equals(successLoan.getLoanNo()))
							excelVo.setFeedBack(BmsConstant.ERRORM_MSG[5]);
					}
				} else {
					excelVo.setFeedBack(BmsConstant.ERRORM_MSG[6]);// 接口报错，切面返回null！或者返回结果没有成功的情况
				}
			}
		}
	}

	@Override
	public Response<ResLoanVo> queryLoanCoreState(ReqLoanVo reqLoanVo) {
		return loanFacade.queryLoanCoreState(reqLoanVo);
	}

	@Override
	public Response<ResLoanVo> bacthBackLoanConfirm(ReqLoanVo reqLoanVo) {
		return loanFacade.bacthBackLoanConfirm(reqLoanVo);
	}
	
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo){
		// 请求参数构造
		reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
		// 接口调用
	    ResListVO<ResLoanExportInfoVo> response = loanFacade.findLoanExportInfo(reqLoanVo);
		return response;
	}

}
