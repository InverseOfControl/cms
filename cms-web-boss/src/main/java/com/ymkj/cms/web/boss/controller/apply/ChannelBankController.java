package com.ymkj.cms.web.boss.controller.apply;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.apply.IChannelBankService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("channelBank")
public class ChannelBankController extends BaseController {

	@Autowired
	private IChannelBankService channelBankService;

	@RequestMapping("main")
	public String view() {
		return "apply/channelBank/channelBank";
	}
	@RequestMapping("view")
	public String views() {
		return "apply/channelBank/channelBankList";
	}

	@RequestMapping("channelShow")
	public String channelShow(Integer channelId, HttpServletRequest request) {
		request.setAttribute("channelId", channelId);
		return "apply/channelBank/channelBank";
	}

	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO) {
		if (reqDemoVO.getPageNum() == 0 || reqDemoVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResBMSChannelBankVO> pageResult = channelBankService.listPage(reqDemoVO);
		ResponsePage<ResBMSChannelBankVO> pageList = new ResponsePage<ResBMSChannelBankVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	@RequestMapping(value = "addChannelBank")
	@ResponseBody
	public Map<String, Object> addChannelBankMethod(ReqBMSChannelBankVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Boolean hasChanel = channelBankService.checkParentIsChanel(reqDemoVO);
		if (!hasChanel) {// 如果是禁用(其道)
			hashMap.put("hasChanel", !hasChanel);
			return hashMap;
		}
		Boolean hasStart=channelBankService.checkParentIsStart(reqDemoVO);
		if(hasStart){//如果是禁用(银行)
			hashMap.put("hasStart", hasStart);
			return hashMap;
		}
		// 保存前先验证是否重复添加相同记录
		Response<ResBMSCheckIsExitsVO> response = channelBankService.checkIsExits(reqDemoVO);
		String isFlag = response.getData().getIsFlag();
		if ("1".equals(isFlag)) {
			hashMap.put("isFlag", true);
		} else {
			// 调用新增接口
			boolean addSuccess = channelBankService.addChannelBank(reqDemoVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
			hashMap.put("add", "add");
		}
		return hashMap;
	}

	@RequestMapping(value = "deleteChannelBank")
	@ResponseBody
	public Map<String, Object> deleteChannelBankMethod(ReqBMSChannelBankVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		String[] ids = reqDemoVO.getIds().split(",");
		for (String id : ids) {
			reqDemoVO.setId(Long.valueOf(id));
			reqDemoVO.setIsDeleted((long) 1);
			boolean addSuccess = channelBankService.updateChannelBank(reqDemoVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
		}

		return hashMap;
	}

	@RequestMapping(value = "updateChannelBankInit")
	@ResponseBody
	public Map<String, Object> updateChannelBankInit(ReqBMSChannelBankVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用查询接口接口
		ResBMSChannelBankVO vo = channelBankService.findById(reqDemoVO.getId());
		hashMap.put("info", vo);
		return hashMap;
	}

	@RequestMapping(value = "updateChannelBank")
	@ResponseBody
	public Map<String, Object> updateChannelBank(ReqBMSChannelBankVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Boolean hasStart=channelBankService.checkParentIsStart(reqDemoVO);
		if(hasStart){//如果是禁用(银行)
			hashMap.put("hasStart", hasStart);
			return hashMap;
		}
		String flag=reqDemoVO.getFlag();
		if("new".equals(flag)){
			// 保存前先验证是否重复添加相同记录
			Response<ResBMSCheckIsExitsVO> response = channelBankService.checkIsExits(reqDemoVO);
			String isFlag = response.getData().getIsFlag();
			if ("1".equals(isFlag)) {
				hashMap.put("isFlag", true);
				return hashMap;
			}
		}
		boolean addSuccess = channelBankService.updateChannelBank(reqDemoVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "getChannel")
	@ResponseBody
	public Object getChannel(ReqBMSChannelVO reqDemoVO) {
		reqDemoVO.setSysCode("1111111111");
		List<ResBMSChannelVO> list = channelBankService.getChannel(reqDemoVO);
		return list;
	}

	@RequestMapping(value = "getBank")
	@ResponseBody
	public Object getBank(ReqBMSBankVO reqDemoVO) {
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		List<ResBMSBankVO> list = channelBankService.getBank(reqDemoVO);
		return list;
	}

	@RequestMapping("importView")
	public String channelBankDataImport() {
		return "apply/channelBank/channelBankDataImport";
	}

	/**
	 * 导入excel,下载sql文件
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/channelBankDataImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestParam("channelBankDataLoadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {// @RequestParam("file")
																							// MultipartFile
																							// file,
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = false;
		// 判断文件是否为空
		if (multipartFile == null)
			return null;
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0)
			System.out.println("传入的文件为空文件!");// return null;

		Map<String, Object> map = channelBankService.Analytical(name, multipartFile);
		List<ReqBMSChannelBankVO> channelBankList = (List<ReqBMSChannelBankVO>) map.get("channelBankList");

		Date nowDate = new Date();
		String fileName = "渠道银行数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		response.reset();
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));// 设定输出文件头
		response.setContentType("text/x-plain");

		try {
			ServletOutputStream out = response.getOutputStream();
			String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
			File files = new File(path);
			FileOutputStream fos = new FileOutputStream(files);
			Writer writer = new OutputStreamWriter(fos, "GBK");
			String text = null;
			/* UserSession user= ApplicationContext.getUser(); */

			if (channelBankList != null) {
				// 生成插入渠道银行表sql语句
				writer.write("/*delete bms_channel_bank sql*/ \n");
				writer.write("truncate table bms_channel_bank ;\n");
				writer.write("/*insert bms_channel_bank sql*/ \n");
				for (ReqBMSChannelBankVO channelBankVo : channelBankList) {
					if (channelBankVo.getDerivedResult().equals("成功")) {
						text = "INSERT INTO bms_channel_bank (channel_id, bank_id, is_deleted, is_disabled,CREATOR,CREATOR_ID,CREATOR_DATE,VERSION)"
								+ "values('" + channelBankVo.getChannelId() + "','" + channelBankVo.getBankId()
								+ "',0,0,'admin',1,sysdate(),1)";
						writer.write(text + ";\n");
					}
				}
				;
			}
			if (text != null) {
				result = true;
			}
			writer.close();
			fos.close();
			FileInputStream fis = new java.io.FileInputStream(files);
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
			byte[] cache = new byte[4096];
			for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
				byteOutputStream.write(cache, 0, offset);
			}
			byte[] bt = null;
			bt = byteOutputStream.toByteArray();
			out.write(bt);
			out.flush();
			out.close();
			fis.close();
			if (files.exists()) {
				files.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	/**
	 * 导入excel,下载excel文件
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/channelBankDataImportDownLoadExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDownLoadExcel(@RequestParam("channelBankDataLoadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {// @RequestParam("file")
																							// MultipartFile
																							// file,
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = false;
		// 判断文件是否为空
		if (multipartFile == null)
			return null;
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0)
			System.out.println("传入的文件为空文件!");// return null;

		Map<String, Object> map = channelBankService.Analytical(name, multipartFile);
		List<ReqBMSChannelBankVO> channelBankList = (List<ReqBMSChannelBankVO>) map.get("channelBankList");

		CreateExcel(channelBankList, response, multipartFile);

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	/**
	 * 创建excel文件
	 * 
	 * @param channelList
	 * @param response
	 * @param Mfile
	 * @throws IOException
	 */
	public void CreateExcel(List<ReqBMSChannelBankVO> channeBanklList, HttpServletResponse response,
			MultipartFile Mfile) throws IOException {

		if (channeBanklList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSChannelBankVO idd : channeBanklList) {
				String[] row = new String[5];
				// 渠道代码
				row[0] = new String(idd.getChannelCode() == null ? "" : idd.getChannelCode().toString());
				// 渠道名称
				row[1] = new String(idd.getChannelName() == null ? "" : idd.getChannelName().toString());
				// 银行名称
				row[2] = new String(idd.getBankName() == null ? "" : idd.getBankName());
				// 导出sql结果
				row[3] = new String(idd.getDerivedResult() == null ? "" : idd.getDerivedResult().toString());
				// 错误反馈信息
				row[4] = new String(idd.getFailureCause() == null ? "" : idd.getFailureCause().toString());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[5];
			headers[0] = new String("渠道代码");
			headers[1] = new String("渠道名称");
			headers[2] = new String("银行名称");
			headers[3] = new String("导出sql结果");
			headers[4] = new String("错误反馈信息");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("渠道银行模板");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			cellStyle_CN.setFont(head_font);
			/*
			 * cellStyle_CN.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			 * cellStyle_CN.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			 * // 上下居中 cellStyle_CN.setWrapText(false);
			 */// 不换行显示

			XSSFCellStyle cellStyle_Red = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont font = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			font.setColor(IndexedColors.RED.getIndex());
			cellStyle_Red.setFont(font);
			cellStyle_Red.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			cellStyle_Red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
			cellStyle_Red.setWrapText(false);// 不换行显示

			XSSFCellStyle cellStyle_errorInfo = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont errorInfoFont = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			errorInfoFont.setColor(IndexedColors.RED.getIndex());
			cellStyle_errorInfo.setFont(errorInfoFont);

			XSSFRow headerRow = sheet.createRow(0);
			// 自适应列宽
			/*
			 * sheet.autoSizeColumn(1); sheet.autoSizeColumn(1, true);
			 */

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
							if (exportExcelList.get(i).getRows().get(j)[3].equals("失败")) {
								// 数据显示单元格样式设置
								/*
								 * if(exportExcelList.get(i).getRows().get(j)[a]
								 * ==exportExcelList.get(i).getRows().get(j)[5])
								 * {
								 * xssf_w_cell.setCellStyle(cellStyle_errorInfo)
								 * ; }else{
								 */
								xssf_w_cell.setCellStyle(cellStyle_errorInfo);
								/* } */

							} else {
								xssf_w_cell.setCellStyle(cellStyle_CN);
							}

							// xssf_w_sheet.autoSizeColumn(a, true);
							// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
							if (exportExcelList.get(i).getRows().get(j)[a] != null
									&& exportExcelList.get(i).getRows().get(j)[a].matches("\\d+(\\.\\d+)?")) {
								xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
							} else {
								xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
							}

						}

					}

				}

				// 设置列宽
				sheet.setColumnWidth(1, 256 * 9 + 184);
				sheet.setColumnWidth(2, 256 * 9 + 184);
				sheet.setColumnWidth(3, 256 * 11 + 184);
				sheet.setColumnWidth(4, 256 * 12 + 184);

			}
			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream out = response.getOutputStream();
			Date nowDate = new Date();
			String fileName = "渠道银行数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			xssf_w_book.write(out);
			out.flush();
			out.close();

		}

	}
}
