package com.ymkj.cms.web.boss.controller.channel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumBH2Constants;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAppFormVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppFormVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.NumberUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.channel.IBMSAppFormService;
import com.ymkj.cms.web.boss.service.channel.IBMSExceptionConsole;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;
import com.ymkj.cms.web.boss.service.channel.impl.ConfigServiceImpl;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书控制器
 */
@Controller
@RequestMapping("appForm")
@SuppressWarnings("finally")
public class AppFormController extends BaseController {

	public static Logger logger = Logger.getLogger(AppFormController.class);

	@Autowired
	private IBMSAppFormService iBmsAppFormService;

	@Autowired
	private IBMSExceptionConsole excConsole;

	@Autowired
	private IExcelExport iExcelExport;
	
	@Autowired
	private ConfigServiceImpl config;

	/*
	 * 初始页面
	 */
	@RequestMapping("view")
	public String view() {
		return "channel/batch/appForm";
	}

	/**
	 * 申请管理查询
	 * 
	 * @param reqAppFormVo
	 * @return
	 */
	@RequestMapping("listPage")
	@ResponseBody
	public Response<ResponsePage<ResAppFormVO>> listPage(ReqAppFormVO reqAppFormVo) {
		Response<ResponsePage<ResAppFormVO>> result = new Response<ResponsePage<ResAppFormVO>>();
		try {
			if (reqAppFormVo.getPage() == 0 || reqAppFormVo.getRows() == 0) {
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR);
			}
			PageResult<ResAppFormVO> pageResult = iBmsAppFormService.listPage(reqAppFormVo);
			ResponsePage<ResAppFormVO> pageList = new ResponsePage<ResAppFormVO>();
			pageList.setTotal(pageResult.getTotalCount());
			pageList.setRows(pageResult.getRecords());
			result.setData(pageList);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "申请管理查询异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "申请管理查询异常");
		} finally {
			return result;
		}
	}

	/**
	 * 划拔申请书导出(PDF)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("transferAppExpPdf")
	@ResponseBody
	public Response<String> transferAppExpPdf(ReqBatchNumVo requestVo, HttpServletResponse response) {
		Response<String> result = new Response<String>();
		try {
			String fileBatchNum = generFileBatchNum(requestVo.getBatchNum(), EnumBH2Constants.划拨申请书pdf.getCode());
			String expFileName = generFileName(EnumBH2Constants.划拨申请书pdf, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			Map<String, Object> map = new HashMap<String, Object>();
			ResAppBookVo resVo = iBmsAppFormService.findAppBookPdfXls(requestVo);
			PdfReader reader = new PdfReader(this.getClass().getResource(config.getAppTmpPdfPath()));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();

			map.put("quantity", resVo.getQuantity());
			map.put("money", resVo.getSumPactMoney());
			map.put("diffMoney", resVo.getDiffMoney());

			Calendar ca = Calendar.getInstance();
			int year = ca.get(Calendar.YEAR);
			int month = ca.get(Calendar.MONTH) + 1;
			int day = ca.get(Calendar.DAY_OF_MONTH);

			Integer quantity = Integer.parseInt(String.valueOf(map.get("quantity")));
			BigDecimal money = new BigDecimal(String.valueOf((map.get("money") == null ? 0.0 : map.get("money"))));
			String applyMoney = NumberUtil.formatAmount(money.subtract(resVo.getDiffMoney()));
			String requestDate = year + "-" + month + "-" + day;
			String stampDate = year + "年" + month + "月" + day + "日";

			form.setField("requestDate", requestDate);
			form.setField("loanMoney", NumberUtil.formatAmount(money));
			form.setField("quantity", quantity.toString());
			form.setField("reduceAmount", NumberUtil.formatAmount(resVo.getDiffMoney()));
			form.setField("factReqAmount", applyMoney);
			form.setField("stampDate", stampDate);
			form.setField("reviewDate", stampDate);

			stamper.setFormFlattening(true);
			if (stamper != null) {
				stamper.close();
			}
			if (reader != null) {
				reader.close();
			}

			response.setHeader("Content-Disposition", "attachment;filename=" + expFileName);
			response.setContentType("application/octet-stream");
			OutputStream os = response.getOutputStream();
			os.write(bos.toByteArray());
			os.flush();
			if (os != null) {
				os.close();
			}
			if (bos != null) {
				bos.close();
			}
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "划拔申请书导出(PDF)异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "划拔申请书导出(PDF)异常");
		} finally {
			return result;
		}

	}

	/**
	 * 划拔申请书导出(Xls)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("transferAppExpXls")
	@ResponseBody
	public Response<String> transferAppExpXls(HttpServletResponse response, ReqBatchNumVo requestVo) {
		Response<String> result = new Response<String>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			ExcelObjVo excelVo = new ExcelObjVo();
			String fileBatchNum = generFileBatchNum(requestVo.getBatchNum(), EnumBH2Constants.划拨申请书xls.getCode());
			String expFileName = generFileName(EnumBH2Constants.划拨申请书xls, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			excelVo.setExcFileName(expFileName);
			ResAppBookVo resVo = iBmsAppFormService.findAppBookPdfXls(requestVo);
			map.put("quantity", resVo.getQuantity());
			map.put("money", resVo.getSumPactMoney());
			map.put("diffMoney", resVo.getDiffMoney());
			iExcelExport.createtransferAppExcel(response,config.getAppTmpExcelPath(), map, excelVo);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "划拔申请书导出(Xls)异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "划拔申请书导出(Xls)异常");
		} finally {
			return result;
		}

	}

	/**
	 * 放款申请明细导出(Xls)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("loanAppExpXls")
	@ResponseBody
	public Response<String> loanAppExpXls(ReqBatchNumVo reqVo, HttpServletResponse response) throws Exception {
		Response<String> result = new Response<String>();
		try {
			String fileBatchNum = generFileBatchNum(reqVo.getBatchNum(), EnumBH2Constants.放款申请书xls.getCode());
			String expFileName = generFileName(EnumBH2Constants.放款申请书xls, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			ExcelObjVo excelVo = new ExcelObjVo(expFileName, "", 1, 0);
			Map<String, String[]> fields = new HashMap<String, String[]>();
			fields.put("fieldNames", EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcName());
			fields.put("fieldCodes", EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcCode());
			List<LoanApplyDetailVo> datas = iBmsAppFormService.findLoanAppBookXls(reqVo);
			iExcelExport.createLoanCheckExcel(response, datas, fields, EnumBH2Constants.LOAN_APP_DETAIL_EXP.getClassObj(), excelVo);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "放款申请明细导出(Xls)异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "放款申请明细导出(Xls)异常");
		} finally {
			return result;
		}

	}

	/**
	 * 放款申请明细导出(Txt)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("loanAppExpTxt")
	@ResponseBody
	public Response<String> loanAppExpTxt(ReqBatchNumVo reqVo, HttpServletResponse response) {
		Response<String> result = new Response<String>();
		try {
			InputStream instream = null;
			OutputStream outstream = null;
			String fileBatchNum = generFileBatchNum(reqVo.getBatchNum(), EnumBH2Constants.放款申请书txt.getCode());
			String expFileName = generFileName(EnumBH2Constants.放款申请书txt, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			response.setHeader("Content-Disposition", "attachment;filename=" + expFileName);
			response.setContentType("application/octet-stream");
			StringBuffer dataBuf = new StringBuffer("");
			List<LoanApplyDetailVo> datas = iBmsAppFormService.findLoanAppBookXls(reqVo);
			for (LoanApplyDetailVo resLoanAppBookVo : datas) {
				for (int i = 0; i < EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcCode().length; i++) {
					Field field = LoanApplyDetailVo.class.getDeclaredField(EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcCode()[i]);
					field.setAccessible(true);
					String data = String.valueOf(field.get(resLoanAppBookVo));
					dataBuf.append(data).append("|+|");
				}
			}
			instream = new ByteArrayInputStream(dataBuf.toString().getBytes());
			outstream = response.getOutputStream();
			int b = 0;
			while ((b = instream.read()) != -1) {
				outstream.write(b);
			}
			outstream.flush();
			outstream.close();
			instream.close();
		} catch (BusinessException e) {
			logger.info(e);
			excConsole.printBusinessException(e, result, logger, "放款申请明细导出(Xls)异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "放款申请明细导出(Xls)异常");
		} finally {
			return result;
		}

	}

	/**
	 * 还款计划导出(Xls)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("repaymentExpXls")
	@ResponseBody
	public Response<String> repaymentExpXls(ReqBatchNumVo reqVo, HttpServletResponse response) {
		Response<String> result = new Response<String>();
		try {
			String fileBatchNum = generFileBatchNum(reqVo.getBatchNum(), EnumBH2Constants.还款计划xls.getCode());
			String expFileName = generFileName(EnumBH2Constants.还款计划xls, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			ExcelObjVo excelVo = new ExcelObjVo(expFileName, "", 1, 0);
			Map<String, String[]> fields = new HashMap<String, String[]>();
			fields.put("fieldNames", EnumBH2Constants.REPAYMENT_PLAN_EXP.getExcName());
			fields.put("fieldCodes", EnumBH2Constants.REPAYMENT_PLAN_EXP.getExcCode());
			List<ResRepaymentExpVo> datas = iBmsAppFormService.exportLoanRepayment(reqVo);
			iExcelExport.createLoanCheckExcel(response, datas, fields, EnumBH2Constants.REPAYMENT_PLAN_EXP.getClassObj(), excelVo);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "还款计划导出(Xls)异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "还款计划导出(Xls)异常");
		} finally {
			return result;
		}

	}

	/**
	 * 划拔申请书导入(Xls)
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("transferAppImpXls")
	@ResponseBody
	public Response<String> transferAppImpXls(ReqLoanBaseVo requestVo, HttpServletResponse response,
			@RequestParam(value = "applyFile") MultipartFile[] files) throws Exception {
		Response<String> result = new Response<String>();
		try {
			Map<String, byte[]> inputStreamMap = new HashMap<String, byte[]>();
			if (files == null || files.length == 0) {
				throw new BusinessException(CoreErrorCode.SYSTEM_ERROR, "查询数据不存在！");
			}
			String batchNum = requestVo.getBatchNum();
			if (batchNum == null || "".equals(batchNum)) {
				throw new BusinessException(CoreErrorCode.SYSTEM_ERROR, "导入批次号不能为空!");
			}
			String fileBatchNum = "";
			for (MultipartFile multipartFile : files) {
				if (multipartFile.isEmpty()) {
					throw new BusinessException(CoreErrorCode.SYSTEM_ERROR, "划拨申请书不存在！");
				}
				String applyFileName = multipartFile.getOriginalFilename();
				if (applyFileName.indexOf(".pdf") != -1) {
					fileBatchNum = applyFileName.substring(applyFileName.lastIndexOf(".") - 12, applyFileName.lastIndexOf(".pdf"));
				}
				inputStreamMap.put(applyFileName, multipartFile.getBytes());
			}
			ReqBatchNumVo reqBatchVo = new ReqBatchNumVo();
			reqBatchVo.setBatchNum(requestVo.getBatchNum());
			reqBatchVo.setServiceCode(requestVo.getServiceCode());
			reqBatchVo.setServiceName(requestVo.getServiceName());

			ReqdealEsignatureVo reqVo = new ReqdealEsignatureVo();
			reqVo.setBatchNum(requestVo.getBatchNum());
			reqVo.setServiceCode(requestVo.getServiceCode());
			reqVo.setServiceName(requestVo.getServiceName());
			reqVo.setFileBatchNum(fileBatchNum);
			String expFileName = generFileName(EnumBH2Constants.放款申请书xls, EnumBH2Constants.渤海项目简码.getCode(), fileBatchNum);
			ExcelObjVo excelVo = new ExcelObjVo(expFileName, "", 1, 0);
			Map<String, String[]> fields = new HashMap<String, String[]>();
			fields.put("fieldNames", EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcName());
			fields.put("fieldCodes", EnumBH2Constants.LOAN_APP_DETAIL_EXP.getExcCode());
			List<LoanApplyDetailVo> datas = iBmsAppFormService.findLoanAppBookXls(reqBatchVo);
			Workbook workBook = iExcelExport.buildWork(datas, fields, EnumBH2Constants.LOAN_APP_DETAIL_EXP.getClassObj(), excelVo);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			workBook.write(byteArrayOutputStream);
			inputStreamMap.put(expFileName, byteArrayOutputStream.toByteArray());
			reqVo.setFileByteMap(inputStreamMap);
			iBmsAppFormService.importAppBook(reqVo);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "划拔申请书导入异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "划拔申请书导入异常");
		} finally {
			return result;
		}

	}

	@RequestMapping("/applyPdfEsignature")
	@ResponseBody
	public Response<String> applyPdfEsignature(@RequestParam(value = "applyEsignaturePdf") MultipartFile multipartfile, HttpServletRequest request,
			HttpServletResponse response) {
		Response<String> result = new Response<String>();
		try {
			Map<String, byte[]> inputStreamMap = new HashMap<String, byte[]>();
			String fileName = multipartfile.getOriginalFilename();
			ReqdealEsignatureVo reqVo = new ReqdealEsignatureVo();
			inputStreamMap.put(fileName, multipartfile.getBytes());
			reqVo.setFileByteMap(inputStreamMap);
			reqVo.setFileName(fileName);
			Response<byte[]> resObj = iBmsAppFormService.dealEsignature(reqVo);
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setContentType("application/pdf");
			OutputStream out = response.getOutputStream();
			out.write(resObj.getData());
			out.flush();
			if (out != null) {
				out.close();
			}
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "划拔申请书签章异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "划拔申请书签章异常");
		} finally {
			return result;
		}

	}

	/**
	 * 导出文件名生成
	 * 
	 * @param enumConstant
	 *            {@link EnumBH2Constants}
	 * @param projectCode
	 *            渠道项目简码
	 * @param fileBatchNum
	 *            文件批次号
	 * @return
	 */
	public static String generFileName(EnumBH2Constants enumConstant, String projectCode, String fileBatchNum) {
		StringBuilder buildFileName = new StringBuilder(projectCode);
		buildFileName.append(enumConstant.getName());
		if (fileBatchNum.length() <= 3) {
			buildFileName.append(DateUtil.getDate(new Date(), DateUtil.pattern_day));
			buildFileName.append("_");
		}
		buildFileName.append(fileBatchNum);
		buildFileName.append(enumConstant.getType());
		String fileName = buildFileName.toString();
		return fileName;
	}

	/**
	 * 文件批次号生成
	 * 
	 * @param batchNum
	 *            债权批次号
	 * @param fileType
	 *            文件类型
	 * @return
	 */
	public String generFileBatchNum(String batchNum, String fileType) {
		ReqFileBatchNumVo reqVo = new ReqFileBatchNumVo();
		reqVo.setBatchNum(batchNum);
		reqVo.setFileType(fileType);
		reqVo.setOperateDate(new Date());
		Response<String> response = iBmsAppFormService.findRequestFileBatchNum(reqVo);
		return response.getData();
	}
}
