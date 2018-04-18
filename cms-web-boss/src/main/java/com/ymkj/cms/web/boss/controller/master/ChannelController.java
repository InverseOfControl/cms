package com.ymkj.cms.web.boss.controller.master;

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

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.FileProperties;
import com.ymkj.cms.web.boss.common.FileUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("channel")
public class ChannelController extends BaseController {

	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private IChannelService channelService;

	@RequestMapping("view")
	public String view() {
		return "master/channel/channelList";
	}
	@RequestMapping("views")
	public String views() {
		return "master/channel/channelAllList";
	}
	/**
	 * 返回json类型的处理方法 1. 第一步 参数校验 (第一种方式) 2. 第二步 业务调用 3. 第三步 结果处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSChannelVO> listPage(ReqBMSChannelVO reqBMSChannelVO) {
		if (reqBMSChannelVO.getPageNum() == 0 || reqBMSChannelVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		PageResult<ResBMSChannelVO> pageResult = channelService.listPage(reqBMSChannelVO);
		ResponsePage<ResBMSChannelVO> pageList = new ResponsePage<ResBMSChannelVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	@RequestMapping(value = "addChannel")
	@ResponseBody
	public Map<String, Object> addChannelMethod(HttpServletRequest request, ReqBMSChannelVO reqBMSChannelVO)
			throws IOException {
		byte source [] = reqBMSChannelVO.getName().getBytes("iso8859-1");
		String chennelName = new String (source,"UTF-8");
		reqBMSChannelVO.setName(chennelName);
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean addSuccess = false;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			reqBMSChannelVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
			if (isMultipart) {
				/*// 文件处理器
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 文件
				MultipartFile file = multipartRequest.getFile("fileImport");
				// 文件路径不存在就创建
				File uploadPathFile = new File(fileProperties.getUploadPath());
				if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
					uploadPathFile.mkdir();
				}
				// 文件保存的路径
				String fullSavePath = fileProperties.getUploadPath() + "/" + file.getOriginalFilename();
				File saveFile = new File(fullSavePath);*/
				/*try {// 保存
					FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
					// String calculateUrl =
					// file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
					reqBMSChannelVO.setCalculateUrl(fullSavePath);*/
				
					if(reqBMSChannelVO.getIsCanPreferential() == null){
						reqBMSChannelVO.setIsCanPreferential(new Long("1"));//0开启   1关闭
					}
				
					//校验是否存在
					Response<ResBMSCheckIsExitsVO> reponse=channelService.checkIsChennelExits(reqBMSChannelVO);
					String ifFlag=reponse.getData().getIsFlag();
					if("1".equals(ifFlag)){
						hashMap.put("isFlag", true);
						return hashMap;
					}
						
					addSuccess = this.channelService.addChannel(reqBMSChannelVO);
					hashMap.put("isSuccess", addSuccess? "yes" : "no");
				/*} catch (exception e) {
					e.printStackTrace();
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	@RequestMapping(value = "deleteChannelCheck")
	@ResponseBody
	public Map<String, Object> deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
//		String[] ids = reqBMSChannelVO.getIds().split(",");
//		for (String id : ids) {
//			reqBMSChannelVO.setChannelId(id);
//			
//			boolean addSuccess = channelService.deleteChannelCheck(reqBMSChannelVO);
//			if(!addSuccess){
//				hashMap.put("isSuccess", false);
//				hashMap.put("message", id);
//				return hashMap;
//			}
//		}
		hashMap.put("isSuccess", true);
		return hashMap;
	}

	@RequestMapping(value = "deleteChannel")
	@ResponseBody
	public Map<String, Object> deleteChannelMethod(ReqBMSChannelVO reqBMSChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();

		String[] ids = reqBMSChannelVO.getIds().split(",");
		for (String id : ids) {
			reqBMSChannelVO.setId(Integer.parseInt(id));
			reqBMSChannelVO.setIsDeleted((long) 1);
			boolean addSuccess = channelService.updateChannel(reqBMSChannelVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
		}
		return hashMap;
	}

	@RequestMapping(value = "updateChannelInit")
	@ResponseBody
	public Map<String, Object> updateChannelInit(ReqBMSChannelVO reqBMSChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用查询接口接口
		ResBMSChannelVO vo = this.channelService.findById((long) reqBMSChannelVO.getId());
		hashMap.put("info", vo);
		return hashMap;
	}

	@RequestMapping(value = "updateChannel")
	@ResponseBody
	public Map<String, Object> updateChannel(HttpServletRequest request, ReqBMSChannelVO reqBMSChannelVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean addSuccess = false;
		try {
			/*boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				// 文件处理器
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 文件
				MultipartFile file = multipartRequest.getFile("upFileImport");
				// 文件路径不存在就创建
				File uploadPathFile = new File(fileProperties.getUploadPath());
				if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
					uploadPathFile.mkdir();
				}
				// 文件保存的路径
				String fullSavePath = fileProperties.getUploadPath() + "/" + file.getOriginalFilename();
				File saveFile = new File(fullSavePath);
				try {// 保存
					FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
					// String calculateUrl =
					// file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
					reqBMSChannelVO.setCalculateUrl(fullSavePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
			
			//校验是否存在
			reqBMSChannelVO.setSysCode(EnumConstants.CMS_SYSTEM_CODE);
			Response<ResBMSCheckIsExitsVO> reponse=channelService.checkIsChennelExits(reqBMSChannelVO);
			String ifFlag=reponse.getData().getIsFlag();
			if("1".equals(ifFlag)){
				hashMap.put("isFlag", true);
				return hashMap;
			}
			
			
			addSuccess = this.channelService.updateChannel(reqBMSChannelVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "getChannel")
	@ResponseBody
	public Object getChannel(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode("1111111111");
		List<ResBMSChannelVO> list = channelService.getChannel(reqBMSChannelVO);
		return list;
	}

	@RequestMapping(value = "exportFile")
	@ResponseBody
	public String checkExportDatas(HttpServletRequest request, HttpServletResponse response) {
		String calculateUrl = request.getParameter("calculateUrl");
		try {
			// 下载Excel文件到客户端
			if (!FileUtil.downLoadFile(calculateUrl, fileProperties.getUploadPath(), response)) {
				return "excel error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "excel error";
		}
		return "success";
	}
	
	@RequestMapping("importView")
	public String channelDataImport() {
		return "master/channel/channelDataImport";
	}
	
	@RequestMapping("findChannelByOrgIds")
	@ResponseBody
	public Object findChannelByOrgIds(ReqBMSChannelVO reqBMSChannelVO) {
		return channelService.findChannelByOrgIds(reqBMSChannelVO);
	}
	
	/**
	 * 导入excel,下载sql文件
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/channelDataImport", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(@RequestParam("channelDataLoadfile")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException{//@RequestParam("file") MultipartFile file,
		Map<String,Object> hashMap = new HashMap<String,Object>();
		boolean result=false;
		//判断文件是否为空
        if(multipartFile==null) return null;
        //获取文件名
        String name=multipartFile.getOriginalFilename();
        System.out.println("name:"+name);
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=multipartFile.getSize();
        if(name==null || ("").equals(name) && size==0) System.out.println("传入的文件为空文件!");//return null;
        
        Map<String, Object> map= channelService.Analytical(name, multipartFile);
        List<ReqBMSChannelVO> channelList=(List<ReqBMSChannelVO>) map.get("channelList");
        
        Date nowDate = new Date();
		String fileName = "渠道数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
        response.reset();// 
        response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("gb2312"), "iso8859-1"));// 设定输出文件头   
        response.setContentType("text/x-plain");
        
        try {
            ServletOutputStream out = response.getOutputStream();
            String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
            File files = new File(path);
            FileOutputStream fos = new FileOutputStream(files);   
            Writer writer = new OutputStreamWriter(fos, "GBK");   
            String text=null;
        	/*UserSession user=	ApplicationContext.getUser();*/
            //生成插入渠道表sql语句 
            writer.write("/*delete bms_channel sql*/ \n");   
        	writer.write("truncate table bms_channel ;\n");  
          	writer.write("/*insert bms_channel sql*/ \n");
          	if(channelList!=null){
            for(ReqBMSChannelVO channelVo  :channelList){
            if(channelVo.getDerivedResult().equals("成功")){
             text = "INSERT INTO bms_channel (CODE, NAME, START_SALES_TIME, STOP_SALES_TIME,CREATOR,CREATOR_ID,CREATOR_TIME, IS_DELETED, is_disabled, VERSION)"+
            			"values('" +channelVo.getCode()+"','"+channelVo.getName()+"','"+channelVo.getStartSalesTime()+"','"+channelVo.getStopSalesTime()+"','admin',1,sysdate(),0,0,1)";
             writer.write(text+";\n");    
            }
            };
          	}
            if(text!=null){
            	result=true;
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
            if(files.exists()){
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
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/channelDataImportDownLoadExcel", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addDownLoadExcel(@RequestParam("channelDataLoadfile")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException{//@RequestParam("file") MultipartFile file,
		Map<String,Object> hashMap = new HashMap<String,Object>();
		boolean result=false;
		//判断文件是否为空
        if(multipartFile==null) return null;
        //获取文件名
        String name=multipartFile.getOriginalFilename();
        System.out.println("name:"+name);
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=multipartFile.getSize();
        if(name==null || ("").equals(name) && size==0) System.out.println("传入的文件为空文件!");//return null;
  
        Map<String, Object> map= channelService.Analytical(name, multipartFile);
        List<ReqBMSChannelVO> channelList=(List<ReqBMSChannelVO>) map.get("channelList");
        
        CreateExcel(channelList,response,multipartFile);
         	
		hashMap.put("isSuccess", result);
		
	
		return hashMap;
	}
	
	

   /**
    * 创建excel文件
    * @param channelList
    * @param response
    * @param Mfile
    * @throws IOException
    */
	public void CreateExcel(List<ReqBMSChannelVO> channelList,HttpServletResponse response,MultipartFile Mfile) throws IOException{
		
		if (channelList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSChannelVO idd : channelList) {
				String[] row = new String[6]; 
				//渠道代码
				row[0] = new String(idd.getCode() == null ? "":idd.getCode().toString());   
				//渠道名称
				row[1] = new String(idd.getName()==null ? "":idd.getName().toString());       
				//起售时间
				row[2] = new String(idd.getStartSalesTime()==null ? "":(idd.getStartSalesTime()=="noDate"?idd.getMistakeStartSalesTime():idd.getStartSalesTime()));       
				//停售时间
				row[3] = new String(idd.getStopSalesTime()==null ? "":(idd.getStopSalesTime()=="noDate"?idd.getMistakeStopSalesTime():idd.getStopSalesTime()));             
				//导出sql结果
				row[4] = new String(idd.getDerivedResult() ==null ? "":idd.getDerivedResult().toString()); 
				//错误反馈信息
				row[5] = new String(idd.getFailureCause() ==null ? "":idd.getFailureCause().toString()); 
				rows.add(row);
			}
			//创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			//创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList=new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);
			
			//设置导出文件的头部标题
	        String[] headers = new String[6];
			headers[0] = new String("渠道代码");
			headers[1] = new String("渠道名称");
			headers[2] = new String("起售时间");
			headers[3] = new String("停售时间");
			headers[4] = new String("导出sql结果");
			headers[5] = new String("错误反馈信息");
			//创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("渠道模板");
			
			
			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont();  //设置字体样式
			cellStyle_CN.setFont(head_font);
			/*cellStyle_CN.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			cellStyle_CN.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
			cellStyle_CN.setWrapText(false);*/// 不换行显示
			
			XSSFCellStyle cellStyle_Red= xssf_w_book.createCellStyle();//创建失败样式
			XSSFFont font =  xssf_w_book.createFont();
			 /*font.setColor(HSSFColor.RED.index);*///设置字体为红色
			font.setColor(IndexedColors.RED.getIndex());
			 cellStyle_Red.setFont(font);
			 cellStyle_Red.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			 cellStyle_Red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
			 cellStyle_Red.setWrapText(false);// 不换行显示
			
			 XSSFCellStyle cellStyle_errorInfo= xssf_w_book.createCellStyle();//创建失败样式
				XSSFFont errorInfoFont =  xssf_w_book.createFont();
				 /*font.setColor(HSSFColor.RED.index);*///设置字体为红色
				errorInfoFont.setColor(IndexedColors.RED.getIndex());
				cellStyle_errorInfo.setFont(errorInfoFont);
			 
			XSSFRow headerRow = sheet.createRow(0);
			//自适应列宽
			/*sheet.autoSizeColumn(1); 
			sheet.autoSizeColumn(1, true);*/
			
			/*sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
			sheet.getNumMergedRegions();*/
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
                            if(exportExcelList.get(i).getRows().get(j)[4].equals("失败")){
							// 数据显示单元格样式设置
                            	/*if(exportExcelList.get(i).getRows().get(j)[a]==exportExcelList.get(i).getRows().get(j)[5]){
                            		xssf_w_cell.setCellStyle(cellStyle_errorInfo);
                            	}else{*/
                            		xssf_w_cell.setCellStyle(cellStyle_errorInfo);
                            	/*}*/
							
							}else{
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
		
				//设置列宽
				sheet.setColumnWidth(1, 256*9+184);
				sheet.setColumnWidth(2, 256*9+184);
				sheet.setColumnWidth(3, 256*12+184);
				sheet.setColumnWidth(4, 256*11+184);
				sheet.setColumnWidth(5, 256*12+184);
			}
			 response.setContentType("application/vnd.ms-excel");
			 ServletOutputStream out = response.getOutputStream();
			   Date nowDate = new Date();
				String fileName = "渠道数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
			  response.setHeader("Content-Disposition", "attachment; filename="+ new String(fileName.getBytes("gb2312"), "iso8859-1"));
				xssf_w_book.write(out);
				out.flush();
				out.close();
			
		}
		
		 
		
	}
	
}
