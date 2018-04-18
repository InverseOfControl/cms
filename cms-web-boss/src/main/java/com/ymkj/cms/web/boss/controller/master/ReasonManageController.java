package com.ymkj.cms.web.boss.controller.master;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants.OperationType;
import com.ymkj.cms.biz.api.enums.EnumConstants.RtfStatus;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;
import com.ymkj.cms.web.boss.service.master.IReasonManageService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("reasonManage")
public class ReasonManageController extends BaseController {

	@Autowired
	private IReasonManageService iReasonManageService;

	@Autowired
	private IEnumCodeService enumCodeService;

	@RequestMapping("view")
	public String view() {
		return "master/reasonManage/reasonManageList";
	}

	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		if (reqReasonVO.getPageNum() == 0 || reqReasonVO.getPageSize() == 0) {
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResBMSReasonVO> pageResult = iReasonManageService.listPage(reqReasonVO);
		ResponsePage<ResBMSReasonVO> pageList = new ResponsePage<ResBMSReasonVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	/**
	 * 根据参数查询数据
	 */
	@RequestMapping(value="findByValue")
	@ResponseBody
	public List<ResBMSReasonVO> findBMSReasonByValue( ReqBMSTMReasonVO reqReasonVO){
		reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResListVO<ResBMSReasonVO> resVO=iReasonManageService.findBMSReasonByValue(reqReasonVO);
		List<ResBMSReasonVO> list=resVO.getCollections();
		return list;
	}
	
	@RequestMapping(value = "addReasonManagement")
	@ResponseBody
	public Map<String, Object> addTmParameterMethod(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ReqBMSTMReasonVO reqReasonVO=new ReqBMSTMReasonVO();
		reqReasonVO.setCode(reqBMSReasonVO.getFirstReason());
		reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		//如果是新增的二级原因，先判断他的一级原因是否禁用
		if(reqBMSReasonVO.getType().equals("2")){
			ResListVO<ResBMSReasonVO> resVO=iReasonManageService.findBMSReasonByValue(reqReasonVO);
			if(resVO.getCollections().size()>0 && resVO.getCollections().get(0).getIsDisabled()==1){
				if((!reqBMSReasonVO.getIsDisabled().equals(resVO.getCollections().get(0).getIsDisabled()))  ){
					hashMap.put("isDisabled",false);
					return hashMap;
				}
			}	
		}
		//判断该原因CODE在库中是否存在
		reqBMSReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResListVO<ResBMSReasonVO> response=iReasonManageService.findReasonByParame(reqBMSReasonVO);
		if(null!=response.getCollections() && response.getCollections().size()>0){
			hashMap.put("existence",false);
			return hashMap;
		}
		// 调用新增接口
		boolean addSuccess = iReasonManageService.addReasonManagement(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "editReasonManage")
	@ResponseBody
	public Map<String, Object> editReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if(reqBMSReasonVO.getType().equals("2")){
			ReqBMSTMReasonVO reqReasonVO=new ReqBMSTMReasonVO();
			//reqReasonVO.setCode(reqBMSReasonVO.getFirstReason());
			reqReasonVO.setReasonTexplain("editP");
			reqReasonVO.setParentId(reqBMSReasonVO.getParentId());
			reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			ResListVO<ResBMSReasonVO> resVO=iReasonManageService.findBMSReasonByValue(reqReasonVO);
			if(resVO.getCollections().size()>0 &&  resVO.getCollections().get(0).getIsDisabled()==1){
				if((!reqBMSReasonVO.getIsDisabled().equals(resVO.getCollections().get(0).getIsDisabled()))){
					hashMap.put("isDisabled",false);
					return hashMap;
				}
			}	
		}
		// 调用新增接口
		boolean addSuccess = iReasonManageService.editReasonManagement(reqBMSReasonVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	/**
	 * 查询所有操作模块
	 */
	@RequestMapping(value="listByModule")
	@ResponseBody
	public List<ResBMSEnumCodeVO> listByModule(ReqBMSEnumCodeVO reqBMSEnumCodeVO){
		ResListVO<ResBMSEnumCodeVO> reslist=enumCodeService.listBy(reqBMSEnumCodeVO);
		List<ResBMSEnumCodeVO> list=reslist.getCollections();
		return list;
	}
	
	@RequestMapping(value = "findByReason")
	@ResponseBody
	public ResBMSReasonVO findReasonByParam(ReqBMSReasonVO reqBMSReasonVO){
		reqBMSReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<ResBMSReasonVO> response=iReasonManageService.findReasonByParame(reqBMSReasonVO);
		ResBMSReasonVO reasonVo=response.getData();
		return reasonVo;
	}
	
	@RequestMapping(value = "delReasonByCode")
	@ResponseBody
	public Map<String, Object> delReasonByCode(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean flag=iReasonManageService.delReasonByCode(reqBMSReasonVO);
		hashMap.put("isSuccess", flag ? true : false);
		return hashMap;
	}


	@RequestMapping(value = "exportReason")
	@ResponseBody
	public void exportReason(ReqBMSTMReasonVO reqReasonVO,HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 必须 设置请求编码
		reqReasonVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResListVO<ResBMSReasonVO> pageResult = iReasonManageService.findReasonExport(reqReasonVO);
		CreateUploadExcelSZExcel(pageResult.getCollections(), response);

	}

	// 创建深圳地区excel文件
	public void CreateUploadExcelSZExcel(List<ResBMSReasonVO> resBMSReasonVO, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = response.getOutputStream();
		Date nowDate = new Date();
		String fileName ="原因管理数据导出" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		if (resBMSReasonVO.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ResBMSReasonVO vo : resBMSReasonVO) {
				String days=String.valueOf(vo.getCanRequestDays()==null?"0":vo.getCanRequestDays());
				String[] row = new String[10];
				// 原因
				row[0] = new String(vo.getReason()==null?"":vo.getReason());
				//原因类型
				row[1] = new String(vo.getOperationType()==null?"":OperationType.getOperationTypeVlue(vo.getOperationType()));
				//原因编码
				row[2]=new String(vo.getCode());
				//一级原因
				row[3]=new String(vo.getFirstReason()==null?"":vo.getFirstReason());
				//限制再申请天数
				row[4]=days;
				//规则
				row[5]=new String(vo.getConditionType()==null?"":vo.getConditionType());
				//备注
				row[6]=new String(vo.getRemark()==null?"":vo.getRemark());
				//是否启用
				row[7]=new String(vo.getIsDisabled()==0?"启用":"禁用");
				//是否显示
				row[8]=new String(vo.getReasonTexplain().equals("1")?"显示":"不显示");
				//操作模块
				row[9] = new String(vo.getOperationModule()==null?"":RtfStatus.get(vo.getOperationModule()).getDesc());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[10];
			headers[0] = new String("原因");
			headers[1] = new String("原因类型");
			headers[2] = new String("原因编码");
			headers[3] = new String("一级原因");
			headers[4]=new String("限制再申请天数");
			headers[5]=new String("规则");
			headers[6]=new String("备注");
			headers[7]=new String("是否启用");
			headers[8]=new String("是否显示");
			headers[9]=new String("操作环节");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("原因管理导出");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			XSSFCellStyle cellStyle_Red = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont font = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			font.setColor(IndexedColors.RED.getIndex());
			cellStyle_Red.setFont(font);
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
							xssf_w_cell.setCellStyle(cellStyle_CN);
							xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);


						}

					}

				}
				sheet.setColumnWidth(1, 256 * 8 + 184);
				sheet.setColumnWidth(2, 256 * 12 + 184);
				sheet.setColumnWidth(3, 256 * 12 + 184);
				sheet.setColumnWidth(4, 256 * 8 + 184);
				sheet.setColumnWidth(5, 256 * 12 + 184);
				sheet.setColumnWidth(6, 256 * 12 + 184);
				sheet.setColumnWidth(7, 256 * 12 + 184);
				sheet.setColumnWidth(8, 256 * 12 + 184);
				sheet.setColumnWidth(9, 256 * 12 + 184);
				sheet.setColumnWidth(10, 256 * 12 + 184);

			}

			xssf_w_book.write(out);
			out.flush();
			out.close();

		}else{

			// 设置导出文件的头部标题
			String[] headers = new String[1];
			headers[0] = new String("暂无数据");

			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("原因数据导出");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			XSSFCellStyle cellStyle_Red = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont font = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			font.setColor(IndexedColors.RED.getIndex());
			cellStyle_Red.setFont(font);
			XSSFCellStyle cellStyle_errorInfo = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont errorInfoFont = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			errorInfoFont.setColor(IndexedColors.RED.getIndex());
			cellStyle_errorInfo.setFont(errorInfoFont);

			XSSFRow headerRow = sheet.createRow(0);
			// 自适应列宽
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(1, true);
			for (int i = 0; i < headers.length; i++) {
				XSSFCell cell = headerRow.createCell(i);
				cell.setCellStyle(cellStyle_CN);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(headers[i]);
			}

			xssf_w_book.write(out);
			out.flush();
			out.close();
		}

	}

}
