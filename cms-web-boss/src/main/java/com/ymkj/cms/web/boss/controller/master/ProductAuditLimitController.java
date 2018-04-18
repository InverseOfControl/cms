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

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;
import com.ymkj.cms.web.boss.service.master.ILimitChannelService;
import com.ymkj.cms.web.boss.service.master.IOrgLimitChannelService;
import com.ymkj.cms.web.boss.service.master.IOutletsProductService;
import com.ymkj.cms.web.boss.service.master.IProductAuditLimitService;
import com.ymkj.cms.web.boss.service.master.IProductCodeModuleService;
import com.ymkj.cms.web.boss.service.master.IProductService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Controller
@RequestMapping("productAuditLimit")
public class ProductAuditLimitController extends BaseController {

	@Autowired
	private IProductAuditLimitService productAuditLimitService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IOutletsProductService outletsProductService;

	@Autowired
	private ILimitChannelService limitChannelService;

	@Autowired
	private IEnumCodeService enumCodeService;

	@Autowired
	private IProductCodeModuleService productCodeModuleService;
	
	@Autowired
	private IOrgLimitChannelService orgLimitChannelService;

	
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	/* private long productId; */
	
	@RequestMapping("views")
    public String views(){
		 return "master/productAuditLimit/productAuditLimitAllList";
	}
	@RequestMapping("view")
	public String view(ReqBMSProductVO productVO, HttpServletRequest request) {
		ResBMSProductVO vo = productService.findById((long) productVO.getProductId());
		/* productId=productVO.getProductId(); */
		request.setAttribute("productId", productVO.getProductId() != null ? productVO.getProductId() : "");
		request.setAttribute("name", vo.getName() != null ? vo.getName() : "");
		request.setAttribute("code", vo.getCode() != null ? vo.getCode() : "");
		// 模块初始化数据
		ReqBMSEnumCodeVO reqBMSEnumCodeVO = new ReqBMSEnumCodeVO();
		reqBMSEnumCodeVO.setCodeType(EnumConstants.PRODUCT_CONFIGURATION_MODULE);
		ResListVO<ResBMSEnumCodeVO> response = enumCodeService.listEnumCodeBy(reqBMSEnumCodeVO);
		List<ResBMSEnumCodeVO> productEnumCodeList = response.getCollections();
		request.setAttribute("productEnumCodeList", productEnumCodeList);// 所有模块码

		ReqBMSProductCodeModuleVO reqBMSProductCodeModuleVO = new ReqBMSProductCodeModuleVO();
		reqBMSProductCodeModuleVO.setProductId(productVO.getProductId());
		ResProductBaseListVO response2 = productCodeModuleService.findProCodeModulesByProId(reqBMSProductCodeModuleVO);
		List<ResBMSProductCodeModuleVO> productModuleCodeList = response2.getListCodeModule();
		request.setAttribute("productModuleCodeList", productModuleCodeList);// 产品模块信息

		return "master/productAuditLimit/productAuditLimitList";
	}

	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
		/*
		 * ReqBMSProductAuditLimitVO reqDemoVO=new ReqBMSProductAuditLimitVO();
		 */

		if (reqProductAuditLimitVO.getPageNum() == 0 || reqProductAuditLimitVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqProductAuditLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResBMSProductAuditLimitVO> pageResult = productAuditLimitService.listPage(reqProductAuditLimitVO);
		ResponsePage<ResBMSProductAuditLimitVO> pageList = new ResponsePage<ResBMSProductAuditLimitVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	@RequestMapping(value = "findAllProductAuditLimit")
	@ResponseBody
	public Object findAllProductAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
		reqProductAuditLimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		List<ResBMSProductAuditLimitVO> list = new ArrayList<>();
		try {
			list = productAuditLimitService.findAllProductAuditLimit(reqProductAuditLimitVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "addProductAuditLimit")
	@ResponseBody
	public Map<String, Object> addProductAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ReqBMSProductAuditLimitVO vo = new ReqBMSProductAuditLimitVO();
		vo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		vo.setProductId(reqProductAuditLimitVO.getProductId());
		vo.setAuditLimit(reqProductAuditLimitVO.getAuditLimit());
		ResBMSProductVO resvo = productService.findById((long)reqProductAuditLimitVO.getProductId());
		if(resvo.getFloorLimit().compareTo(reqProductAuditLimitVO.getFloorLimit())==1){
			 hashMap.put("isFloorLimit", false);
			 return hashMap;
		}
		if(resvo.getUpperLimit().compareTo(reqProductAuditLimitVO.getUpperLimit())==-1){
			hashMap.put("isUpperLimit", false);
			 return hashMap;
		}
		List<ResBMSProductAuditLimitVO> list = productAuditLimitService.findAllProductAuditLimit(vo);
		if (list == null || list.size() == 0) {
			// 调用新增接口
			boolean addSuccess = productAuditLimitService.addProductAuditLimit(reqProductAuditLimitVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
			hashMap.put("isRepeat", false);
		} else {
			hashMap.put("isSuccess", false);
			hashMap.put("isRepeat", true);
		}

		return hashMap;
	}

	@RequestMapping(value = "updateProductAuditLimitInit")
	@ResponseBody
	public Map<String, Object> updateProductAuditLimitInit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用查询接口接口
		ResBMSProductAuditLimitVO vo = productAuditLimitService
				.findByAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
		hashMap.put("info", vo);
		return hashMap;
	}

	@RequestMapping(value = "updateProductAuditLimit")
	@ResponseBody
	public Map<String, Object> updateProductAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ResBMSProductVO vo = productService.findById((long)reqProductAuditLimitVO.getProductId());
		if(reqProductAuditLimitVO.getIsDisabled()==1){		
				ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO = new ReqBMSOrgLimitChannelVO();
				reqOrgLimitChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqOrgLimitChannelVO.setAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
				reqOrgLimitChannelVO.setIsDisabled(Integer.valueOf(reqProductAuditLimitVO.getIsDisabled().toString()));
				orgLimitChannelService.updateByOrgLimitId(reqOrgLimitChannelVO);
				ReqBMSLimitChannelVO reqLimitChannelVO=new ReqBMSLimitChannelVO();
				reqLimitChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				reqLimitChannelVO.setAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
				reqLimitChannelVO.setIsDisabled(Integer.valueOf(reqProductAuditLimitVO.getIsDisabled().toString()));
				limitChannelService.updateByAuLimitId(reqLimitChannelVO);
			
		}
		if(vo.getFloorLimit().compareTo(reqProductAuditLimitVO.getFloorLimit())==1){
			 hashMap.put("isFloorLimit", false);
			 return hashMap;
		}
		if(vo.getUpperLimit().compareTo(reqProductAuditLimitVO.getUpperLimit())==-1){
			hashMap.put("isUpperLimit", false);
			 return hashMap;
		}
		ReqBMSProductAuditLimitVO reqVO=new ReqBMSProductAuditLimitVO();
		reqVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqVO.setProductId(reqProductAuditLimitVO.getProductId());
		reqVO.setAuditLimit(reqProductAuditLimitVO.getAuditLimit());
		List<ResBMSProductAuditLimitVO> list = productAuditLimitService.findAllProductAuditLimit(reqVO);
		if(list == null || list.size()==0 ){
			boolean addSuccess = productAuditLimitService.updateProductAuditLimit(reqProductAuditLimitVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
			hashMap.put("isRepeat", false); 
		}else if(list.get(0).getAuditLimitId().equals(reqProductAuditLimitVO.getAuditLimitId())){
			boolean addSuccess = productAuditLimitService.updateProductAuditLimit(reqProductAuditLimitVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
			hashMap.put("isRepeat", false); 
			
			
			ReqBMSProductAuditLimitVO req=new ReqBMSProductAuditLimitVO();
			req.setAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
			//根据产品期限ID查询对应的网店产品下面用到的该期限的数据
			List<ResBMSOrgLimitChannelVO> listOrgs=productAuditLimitService.findOutletByAuditLimitId(req);
			if(listOrgs!=null&&listOrgs.size()>0){
				for(ResBMSOrgLimitChannelVO v:listOrgs){//循环网店产品
					
					ReqBMSOrgLimitChannelVO channelVo=new ReqBMSOrgLimitChannelVO();
					if(reqProductAuditLimitVO.getUpperLimit().compareTo(v.getFloorLimit())==-1 ||reqProductAuditLimitVO.getFloorLimit().compareTo(v.getUpperLimit())==1){//没有交集
						
						channelVo.setId(v.getId());
						//channelVo.setIsDisabled(1);
						channelVo.setConfigure("Y");
						
						
						productAuditLimitService.updateOrgLimitById(channelVo);//更具ID更新网店产品信息
						
					}else{//有交集
						channelVo.setId(v.getId());
						if(reqProductAuditLimitVO.getFloorLimit().compareTo(v.getFloorLimit())==-1){
							channelVo.setFloorLimit(v.getFloorLimit());
						}else{
							channelVo.setFloorLimit(reqProductAuditLimitVO.getFloorLimit());
						}
						
						if(reqProductAuditLimitVO.getUpperLimit().compareTo(v.getUpperLimit())==-1){
							channelVo.setUpperLimit(reqProductAuditLimitVO.getUpperLimit());
						}else{
							channelVo.setUpperLimit(v.getUpperLimit());
						}
						channelVo.setConfigure("N");
						productAuditLimitService.updateOrgLimitById(channelVo);
					}
					
					
				}
			}
			
			
			
		}else{
			hashMap.put("isSuccess", false);
			hashMap.put("isRepeat", true);
		}
		return hashMap;
	}

	@RequestMapping("findEnumCodeByCondition")
	@ResponseBody
	public List<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO,
			HttpServletRequest request) {
		// 调用根据产品条件查询枚举码
		ResListVO<ResBMSEnumCodeVO> response = enumCodeService.findEnumCodeByCondition(reqBMSEnumCodeVO);
		List<ResBMSEnumCodeVO> enumCodeList = response.getCollections();
		return enumCodeList;
	}

	@RequestMapping("saveProductCodeModules")
	@ResponseBody
	public Map<String, Object> saveProductCodeModules(ReqBMSProductCodeModuleVO productCodeModuleVO,
			HttpServletRequest request) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		//if(productCodeModuleVO.getProductCodeIds().length>0){
			boolean addSuccess = productCodeModuleService.saveProductCodeModules(productCodeModuleVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
	//	}
		return hashMap;
	}

	@RequestMapping("importView")
	public String productDataImport() {
		return "master/productAuditLimit/productAuditLimitDataImport";
	}
	 /**
	     * 删除产品期限,以及该期限下的所有信息
	     */
		@RequestMapping("deleteProductTerm")
		@ResponseBody
		public Map<String,Object> deleteProductTerm(ReqBMSProductAuditLimitVO reqProductAuditLimitVO){
			Map<String, Object> hashMap = new HashMap<String, Object>();
		  boolean updateSuccess1=productAuditLimitService.deleteProductTerm(reqProductAuditLimitVO);
		  ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO=new ReqBMSOrgLimitChannelVO();
		  reqOrgLimitChannelVO.setAuditLimitId(reqProductAuditLimitVO.getAuditLimitId());
		  reqOrgLimitChannelVO.setIsDeleted((long)1);
		  reqOrgLimitChannelVO.setServiceCode(EnumConstants.BMS_SYSTEM_CODE);
		  boolean updateSuccess2=limitChannelService.logicalDelete(reqOrgLimitChannelVO);
		  boolean updateSuccess3= orgLimitChannelService.deleteProductTerm(reqOrgLimitChannelVO);
		  if(updateSuccess1==true && updateSuccess2==true && updateSuccess3==true){
			  hashMap.put("isSuccess", true);  
		  }else{
			  hashMap.put("isSuccess", false);   
		  }
		  
			return hashMap;
	
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
	@RequestMapping(value = "/productAuditLimitDataImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestParam("productAuditLimitUploadfile") MultipartFile multipartFile,
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
			System.out.println("传入的文件为空文件!");

		// 从service层获取数据
		Map<String, Object> map = productAuditLimitService.Analytical(name, multipartFile);
		List<ReqBMSProductAuditLimitVO> productAuditLimitList = (List<ReqBMSProductAuditLimitVO>) map
				.get("productAuditLimitList");
		/*
		 * List<ReqBMSProductAuditLimitVO> productAuditLimitList=new
		 * ArrayList<ReqBMSProductAuditLimitVO>();
		 */
		Date nowDate = new Date();
		String fileName = "产品期限数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
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
			// 生成插入机构表sql creator_id,creator,created_time,version
			writer.write("/*delete bms_product_audit_limit sql*/  \n");
			writer.write("truncate table bms_product_audit_limit ;\n");
			writer.write("/*insert bms_product_audit_limit sql*/  \n");
			for (ReqBMSProductAuditLimitVO productAuditLimitVO : productAuditLimitList) {

				if (productAuditLimitVO.getDerivedResult().equals("成功")) {
					text = "insert into bms_product_audit_limit(PRODUCT_ID,PRODUCT_CODE,AUDIT_LIMIT,UPPER_LIMIT,FLOOR_LIMIT,IS_DELETED,CREATOR,CREATOR_ID,CREATOR_DATE,VERSION)"
							+ "values('" + productAuditLimitVO.getProductId() + "','"
							+ productAuditLimitVO.getProductCode() + "'," + productAuditLimitVO.getAuditLimit() + ","
							+ productAuditLimitVO.getUpperLimit() + "," + productAuditLimitVO.getFloorLimit()
							+ ",0,'admin',1,sysdate(),1)";
					System.out.println("sql:" + text);
					writer.write(text + ";\n");
				}
			}
			;
			/*
			 * if(!text.isEmpty()){ result=true; }
			 */
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
	 * 导入excel,下载excel文件反馈信息
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/productAuditLimitDataImportDownLoadExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDownLoadExcel(
			@RequestParam("productAuditLimitUploadfile") MultipartFile multipartFile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {// @RequestParam("file")
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

		// 从service层获取数据
		Map<String, Object> map = productAuditLimitService.Analytical(name, multipartFile);
		List<ReqBMSProductAuditLimitVO> productAuditLimitList = (List<ReqBMSProductAuditLimitVO>) map
				.get("productAuditLimitList");

		CreateExcel(productAuditLimitList, response);

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	// 创建excel文件
	public void CreateExcel(List<ReqBMSProductAuditLimitVO> productAuditLimitList, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = response.getOutputStream();
		Date nowDate = new Date();
		String fileName = "产品期限数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		if (productAuditLimitList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSProductAuditLimitVO idd : productAuditLimitList) {
				String[] row = new String[7];
				// 产品代码
				row[0] = new String(idd.getProductCode() == null ? "" : idd.getProductCode().toString());
				// 产品名称
				row[1] = new String(idd.getProductNameExcel() == null ? ""
						: (idd.getFailureCause().indexOf("在产品表中不存在该产品名称") != -1 ? idd.getProductNameExcel()
								: idd.getProductNameExcel().toString()));
				// 期限
				row[2] = new String(idd.getAuditLimit() == null ? ""
						: (idd.getFailureCause().indexOf("调整基数类型转换错误") != -1 ? idd.getMistakeAuditLimit()
								: idd.getAuditLimit().toString()));
				// 产品额度下限
				/*
				 * System.out.println("产品额度下限:"+idd.getMistakeFloorLimit()+";"+
				 * idd.getFloorLimit().toString());
				 */
				row[3] = new String(idd.getFloorLimit() == null ? ""
						: (idd.getFailureCause().indexOf("产品额度下限类型转换错误") != -1 ? idd.getMistakeFloorLimit()
								: idd.getFloorLimit().toString()));
				// 产品额度上限
				row[4] = new String(idd.getUpperLimit() == null ? ""
						: (idd.getFailureCause().indexOf("产品额度上限类型转换错误") != -1 ? idd.getMistakeUpperLimit()
								: idd.getUpperLimit().toString()));

				// 导出结果
				row[5] = new String(idd.getDerivedResult() == null ? "" : idd.getDerivedResult().toString());
				// 失败原因
				row[6] = new String(idd.getFailureCause() == null ? "" : idd.getFailureCause().toString());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[7];
			headers[0] = new String("产品代码");
			headers[1] = new String("产品名称");
			headers[2] = new String("产品期限");
			headers[3] = new String("产品额度下限");
			headers[4] = new String("产品额度上限");
			headers[5] = new String("导出sql结果");
			headers[6] = new String("错误反馈信息");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("产品期限模板");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			/* head_font.setFontHeightInPoints((short) 90); */
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

			// 自适应列宽
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(1, true);
			XSSFRow headerRow = sheet.createRow(0);

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
							if (exportExcelList.get(i).getRows().get(j)[5].equals("失败")) {
								// 数据显示单元格样式设置

								xssf_w_cell.setCellStyle(cellStyle_errorInfo);

							} else {
								xssf_w_cell.setCellStyle(cellStyle_CN);
							}

							// xssf_w_sheet.autoSizeColumn(a, true);
							// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
							if (exportExcelList.get(i).getRows().get(j)[0] != null
									|| exportExcelList.get(i).getRows().get(j)[0] != "") {
								if (exportExcelList.get(i).getRows().get(j)[a] != null
										&& exportExcelList.get(i).getRows().get(j)[a].matches("\\d+(\\.\\d+)?")) {
									xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
								} else {
									xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
								}
							}

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
				sheet.setColumnWidth(2, 256 * 8 + 184);
				sheet.setColumnWidth(3, 256 * 12 + 184);
				sheet.setColumnWidth(4, 256 * 12 + 184);
				sheet.setColumnWidth(5, 256 * 10 + 184);
				sheet.setColumnWidth(6, 256 * 12 + 184);
			}

			xssf_w_book.write(out);
			out.flush();
			out.close();

		}

	}
	
	
	
	
	//在更新产品期限之前，先判断该期限下面对应的网店是否冲突
		@RequestMapping(value = "queryUpdateProducOnOff",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> queryUpdateProducOnOff(ReqBMSProductAuditLimitVO reqDemoVO) {
			Map<String,Object> map=new HashMap<String,Object>();
			
			String returnStr="";
			Boolean ifTwoConflict=false;
			
			Map<String,String> orgMap=new HashMap<String,String>();//组装网店对应的期限
			
					ReqBMSProductAuditLimitVO req=new ReqBMSProductAuditLimitVO();
					
					req.setAuditLimitId(reqDemoVO.getAuditLimitId());
					
					req.setUpperLimit(reqDemoVO.getUpperLimit());
					req.setFloorLimit(reqDemoVO.getFloorLimit());
					
					
					//根据产品期限ID查询对应的网店产品下面用到的该期限的数据
					List<ResBMSOrgLimitChannelVO> listOrgs=productAuditLimitService.findOutletByAuditLimitId(req);
					if(listOrgs!=null&&listOrgs.size()>0){
						
						for(ResBMSOrgLimitChannelVO v:listOrgs){//循环网店产品
							if(req.getUpperLimit().compareTo(v.getFloorLimit())==-1 ||req.getFloorLimit().compareTo(v.getUpperLimit())==1){//没有交集
								ifTwoConflict=true;
								Long orgId=v.getOrgId();
								
								ReqOrganizationVO reqOrganizationVO=new ReqOrganizationVO();
								reqOrganizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
								reqOrganizationVO.setId(orgId);
								Response<ResOrganizationVO> byOrgIdResponse = OrganizationExecuter.findById(reqOrganizationVO);
								
								if(orgMap.containsKey(byOrgIdResponse.getData().getName())){//如果包含这个KEY
									//不做操作
								}else{//不包含
									orgMap.put(byOrgIdResponse.getData().getName(), "");
								}
								
							}
							
						}
					}
				
				if(ifTwoConflict==true){
					String orgMapStr=orgMap.toString();
					orgMapStr=orgMapStr.replace("{","");
					orgMapStr=orgMapStr.replace("}","");
					orgMapStr=orgMapStr.replace("=", "");
					orgMapStr=orgMapStr.replace(",", "<br/>");
					returnStr="以下配置冲突、确定保存吗？  (合计"+orgMap.size()+"家营业部)<br/>"+orgMapStr;
					
				}
			
			
			
			map.put("ifTwoConflict", ifTwoConflict);
			map.put("returnStr", returnStr);
			return map; 
		}
		
		@RequestMapping(value = "listAuditLimit")
		@ResponseBody
		public List<ResBMSProductAuditLimitVO> listAuditLimit(ReqBMSProductAuditLimitVO reqProductAuditLimitVO) {
			List<ResBMSProductAuditLimitVO> auditLimitList = productAuditLimitService.listAuditLimit(reqProductAuditLimitVO);
			for (ResBMSProductAuditLimitVO auditLimit : auditLimitList) {
				auditLimit.setAuditLimitName(auditLimit.getAuditLimit() + "期");
			}
			return auditLimitList;
		}
}
