package com.ymkj.cms.web.boss.controller.master;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.web.boss.common.FileProperties;
import com.ymkj.cms.web.boss.common.Html2PDFUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.IContractTemplateService;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;

@Controller
@RequestMapping("contractTemplate")
public class ContractTemplateController extends BaseController{
	
	@Autowired
	private IContractTemplateService contractService;
	@Autowired
	private IEnumCodeService enumCodeService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private FileProperties fileProperties;
	
	@RequestMapping("view")
	public String view() {
		return "master/contractTemplate/list";
	}
	//报表查询-->合同模板查询
	@RequestMapping("views")
	public String views() {
		return "master/contractTemplate/contractTemplateList";
	}
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSContractTemplateVO> listPage(ReqBMSContractTemplateVO reqBMSContractTemplateVO){
		if(reqBMSContractTemplateVO.getPage() == 0 || reqBMSContractTemplateVO.getRows() == 0){
			// 数组参数 必须 与  错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		//必须 设置请求编码
		reqBMSContractTemplateVO.setSysCode(EnumConstants.BMS_SYSCODE);
		PageResult<ResBMSContractTemplateVO> pageResult = contractService.listPage(reqBMSContractTemplateVO);
		ResponsePage<ResBMSContractTemplateVO> pageList = new ResponsePage<ResBMSContractTemplateVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	/**
	 * 文件下载
	 */
	@RequestMapping("download")    
    public void download(ReqBMSContractTemplateVO reqBMSContractTemplateVO,HttpServletResponse response) throws IOException { 
    	//文件的存储路径
    	reqBMSContractTemplateVO.setSysCode(EnumConstants.BMS_SYSCODE);
    	ResBMSContractTemplateVO res = contractService.findByVO(reqBMSContractTemplateVO);
    	String pdfName = res.getName()+".pdf";
		response.setContentType("application/pdf;charset=utf-8");
		response.addHeader("Content-Disposition", "inline;filename=" + new String(pdfName.getBytes("gb2312"), "ISO8859-1"));
        ServletOutputStream  out= response.getOutputStream();
    	String strHtml=	StringUtils.str2HtmlStr(pdfName, res.getTemplateContent());
    	Html2PDFUtil.generatePdfDownload(strHtml,null,pdfName,out);
    } 
    
    /**
	 * 文件上传
	 */
	@RequestMapping("upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		String flag = "fail";
		Map<String, Object> hashMap = new HashMap<String, Object>();
       //获取上传地址
		String path = fileProperties.getUploadContractTemPath();
		//得到上传时的文件名
        String fileName = file.getOriginalFilename();
        //保存  
        try {  
        	fileName=new String(fileName.getBytes("ISO-8859-1"), "UTF-8");  

        	File targetFile = new File(path, fileName);  
        	if(!targetFile.exists()){  
        		targetFile.mkdirs();  
        	}  
            file.transferTo(targetFile);  
            flag = "success";
            //只存名称
            String url = File.separator+fileName;
            hashMap.put("templateUrl", url);
        } catch (Exception e) { 
            e.printStackTrace();  
        }  
        hashMap.put("isSuccess", flag);
       return hashMap;   
	}
	
	/**
	 * 获取打印类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "findPrintType")
	@ResponseBody
	public Object findPrintType(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		reqBMSEnumCodeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		List<ResBMSEnumCodeVO> list = enumCodeService.listEnumCodeBy(reqBMSEnumCodeVO).getCollections();
		return list;
	} 
	
	/**
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "valiContractTempCodeExist")
	@ResponseBody
	public Object valiContractTempCodeExist(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		reqBMSContractTemplateVO.setSysCode(EnumConstants.BMS_SYSCODE);
		Map retrunMap =new HashMap();
		boolean existflag =false;
		//校验模板code是否存在
		ReqBMSContractTemplateVO findContractTempByCode=new ReqBMSContractTemplateVO();
		findContractTempByCode.setCode(reqBMSContractTemplateVO.getCode());
		findContractTempByCode.setChannelId(reqBMSContractTemplateVO.getChannelId());
		ResBMSContractTemplateVO res = contractService.findByVO(findContractTempByCode);
		if(res !=null){
			existflag =true;
		}
		retrunMap.put("existflag", existflag);
		return retrunMap;
	} 
	/**
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	@RequestMapping(value = "saveTemplate")
	@ResponseBody
	public Object saveTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		reqBMSContractTemplateVO.setSysCode(EnumConstants.BMS_SYSCODE);
		Map retrunMap =new HashMap();
		String flag = "fail";
			Long id = null;
			try {
				id = contractService.saveTemplate(reqBMSContractTemplateVO);
			} catch (Exception e) {
				retrunMap.put("msg", e.getLocalizedMessage());
			}
			if(id !=null && id !=0L){
				 flag = "success";
			}
		retrunMap.put("flag", flag);
		return retrunMap;
	} 
	
	
	
	/**
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	@RequestMapping(value = "updateTemplate")
	@ResponseBody
	public Object updateTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		reqBMSContractTemplateVO.setSysCode(EnumConstants.BMS_SYSCODE);
		Map retrunMap =new HashMap();
		String flag = "fail";
		//校验模板code是否存在
		ReqBMSContractTemplateVO findContractTempByCode=new ReqBMSContractTemplateVO();
		findContractTempByCode.setCode(reqBMSContractTemplateVO.getCode());
			Long id = null;
			try {
				id = contractService.updateTemplate(reqBMSContractTemplateVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(id !=null && id !=0L){
				 flag = "success";
			}
		retrunMap.put("flag", flag);
		return retrunMap;
	} 
	
	/**
	 * 获取模板数据根据id
	 * 
	 * @return
	 */
	@RequestMapping(value = "findTemplateById")
	@ResponseBody
	public Object findTemplateById(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		reqBMSContractTemplateVO.setSysCode("1111111111");
		List<ResBMSContractTemplateVO> templateList = contractService.getAllTemplate(reqBMSContractTemplateVO);
		return templateList.get(0);
	} 

	/**
	 * 获取所有未删除的渠道信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findChannel")
	@ResponseBody
	public Object findChannel(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(SystemCode.SysCode);
		List<ResBMSChannelVO> list = new ArrayList<>();
		list = channelService.getAllChannel(reqBMSChannelVO);
		for (ResBMSChannelVO resBMSChannelVO : list) {
			resBMSChannelVO.setName(resBMSChannelVO.getCode()+"-"+resBMSChannelVO.getName());
		}
		return list;
	}
	/**
	 * 查询可配置合同类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "findContractTypeList")
	@ResponseBody
	public Object findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo) {
		List<ResBMSEnumCodeVO> list = new ArrayList<ResBMSEnumCodeVO>();
		list = channelService.findContractTypeList(reqLoanContractSignVo);
		return list;
	}
}
