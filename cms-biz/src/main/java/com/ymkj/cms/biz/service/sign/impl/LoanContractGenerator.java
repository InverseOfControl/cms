package com.ymkj.cms.biz.service.sign.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumBH2Constants;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.FileUtil;
import com.ymkj.cms.biz.common.util.Html2PDFUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSContractTemplateService;
import com.ymkj.cms.biz.service.sign.HttpClientService;

@Service
public class LoanContractGenerator {
	
	public static Logger logger=Logger.getLogger(LoanContractGenerator.class);

	@Autowired
	private IBMSContractTemplateService contractService; 
	
	@Autowired
	private HttpClientService httpClientService;
	
	@Value(value="#{env['service_port']?:''}")
	private String  service_port;
	
	@Value(value="#{env['windows_contractPath']?:''}")
	private String windows_contractPath;
	
	@Value(value="#{env['linux_contractPath']?:''}")
	private String linux_contractPath;

	@Value(value="#{env[push_sign_url]?:''}")
	private String pushSignUrl;
	
	@Value(value="#{env['contractPath_Ip']?:''}")
	private String contractPath_Ip;


	
	

	
	/**
	 * 根据合同来源读取合同模板，将Map中的值填充到合同模板对应占位符，占位符必须为Map中的key
	 * @param contractSource
	 * @param values 模板中占位符对应值，包含这个合同来源下所有模板的值
	 * @return valueMap 文件名--路径
	 */
	public List<BMSContractTemplate> getContractUrlList(String channelCd,String loanNo,Long contractType){
		String localip=contractPath_Ip;//获得IP
		String port=service_port;//获得端口
		//得到合同对象List
		List<BMSContractTemplate> templateList = contractService.listByChannelCd(channelCd);

		//若果生成合同为电子合同类型：剔除同文件类型的纸质版本
		if(contractType != null && EnumConstants.ContractType.DZB.getCode().equals(contractType.toString())){
			
			List<BMSContractTemplate> templateListRemove = new ArrayList<BMSContractTemplate>();
			for (BMSContractTemplate cTBase : templateList) {
				if(cTBase.getContractType() == null || !EnumConstants.ContractType.DZB.getCode().equals(cTBase.getContractType().toString())){
					continue;
				}
				String baseCode = cTBase.getCode();
				for (BMSContractTemplate cT : templateList) {
					if(!baseCode.equals(cT.getCode()) && baseCode.indexOf(cT.getCode()) == 0) {
						templateListRemove.add(cT);
					}
				}
			}
			if(!templateListRemove.isEmpty()){
				templateList.removeAll(templateListRemove);
			}
		}

		List<BMSContractTemplate> resultList =new ArrayList<BMSContractTemplate>();
		//借款TEMPLATE URL
		if(templateList != null && !templateList.isEmpty()){
			for (BMSContractTemplate bmsContractTemplate : templateList) {
				Map<String,String> contractMap=filterContract(channelCd, String.valueOf(contractType), bmsContractTemplate);
				String url =localip+":"+port+"/bms-contractHome/" +channelCd+"/"+loanNo+"/"+channelCd+"-"+bmsContractTemplate.getCode()+".pdf";
				bmsContractTemplate.setTemplateUrl(url);
				if(contractMap ==null){
					resultList.add(bmsContractTemplate);
				}
			}
		}
		return resultList;
	}
	
	
	private String getContractPath(){
		String contractPath="";
		try {
			String osType = StringUtils.osType();
			if(osType.equals("windows")){
				contractPath = windows_contractPath;
			}else{
				contractPath = linux_contractPath;
			}
			
		} catch (Exception e) {

		}
		return contractPath;
		
	}
	
	/**
	 * 根据合同来源读取合同模板，将Map中的值填充到合同模板对应占位符，占位符必须为Map中的key
	 * @param contractSource
	 * @param values 模板中占位符对应值，包含这个合同来源下所有模板的值
	 * @return valueMap 文件名--路径
	 */
	public Map<String, String> getContractTemplate(String org,String channelCd,Map<String,Object> values,String loanNo){
		Map<String,String> valueMap = new HashMap<String, String>();
		//得到合同对象
		List<BMSContractTemplate> templateList = contractService.listByChannelCd(channelCd);

		//若果生成合同为电子合同类型：剔除同文件类型的纸质版本
		if(values.get("contractTypeCode") != null && EnumConstants.ContractType.DZB.getCode().equals(values.get("contractTypeCode"))){
			
			List<BMSContractTemplate> templateListRemove = new ArrayList<BMSContractTemplate>();
			for (BMSContractTemplate cTBase : templateList) {
				if(cTBase.getContractType() == null || !EnumConstants.ContractType.DZB.getCode().equals(cTBase.getContractType().toString())){
					continue;
				}
				String baseCode = cTBase.getCode();
				for (BMSContractTemplate cT : templateList) {
					if(!baseCode.equals(cT.getCode()) && baseCode.indexOf(cT.getCode()) == 0) {
						templateListRemove.add(cT);
					}
				}
			}
			if(!templateListRemove.isEmpty()){
				templateList.removeAll(templateListRemove);
			}
		}

		String	contractPath =getContractPath();
		//先删除原来的合同模板路径
		
		String oldRealPath = contractPath +channelCd+"/"+loanNo;
		File file = new File(oldRealPath);
		if (file.exists()) {
			FileUtil.deleteFile(file);
		}
		if(templateList != null && !templateList.isEmpty()){
			for (BMSContractTemplate bmsContractTemplate : templateList) {
				valueMap.putAll(generatePerPDF(org,bmsContractTemplate,values,loanNo,templateList));
			}
		}
		return valueMap;
	}

	/**
	 * 根据合同模板产生pdf文件--并返还文件名--路径
	 */
	private  Map<String, String> generatePerPDF(String org, BMSContractTemplate template,Map<String,Object> values,String loanNo,List<BMSContractTemplate> templateList){
		String type=String.valueOf(values.get("contractTypeCode"));
		String channelCd=String.valueOf(values.get("channelCd"));
		Map<String,String>map = new HashMap<String, String>();
		Map<String,String>contractMap=filterContract(channelCd, type, template);
		if(contractMap!=null){
			return map;
		}
		String relativePath = "";
		
		String contractPath=getContractPath();
		
		String realPath = contractPath + template.getChannelCd();
		//先创建一级目录
		File file = new File(realPath);
		if(!file.exists()){
				file.mkdirs();
		}
		//在创建二级目录
		String realPath2 = contractPath + template.getChannelCd()+"/"+loanNo;
		File realFile = new File(realPath2);
		if(!realFile.exists()){
			realFile.mkdir();
		}
	
		relativePath =template.getChannelCd()+"-"+template.getCode()+".pdf";
		
		String fileName = realPath2+"/"+relativePath;

		
		for(Entry<String, Object> entry : values.entrySet()){
			if(entry.getValue()!=null&&String.class.isAssignableFrom(entry.getValue().getClass())){
					try {
						String value = (String)entry.getValue();
		/*				value=value.replaceAll("<", HtmlUtils.htmlEscape("<"));
						value=value.replaceAll(">", HtmlUtils.htmlEscape(">"));*/
						entry.setValue(value);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println(e.getCause()+"****************************"+e.getLocalizedMessage());
					}
			}
		}

	    //加入标准格式
		String module = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd \">   ";
		String headModule = "<html xmlns=\"http://www.w3.org/1999/xhtml \">";
		String startModule = headModule+"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>"+template.getName()+"</title></head><body>";
		String endModule="</body></html>";
		Html2PDFUtil.generatePdf(module+startModule+template.getTemplateContent()+endModule, values, fileName);
		//map.put(template.getName(), contractHttpPath+relativePath);
		long begTime=System.currentTimeMillis();
		logger.info("合同:"+fileName+"推送开始时间:"+begTime);
	    pushSignContract(values, relativePath, realPath2,template);
	    long endTime=System.currentTimeMillis();
		logger.info("合同:"+fileName+"推送结束时间:"+endTime);
		return map;
	}
	
	
	//推送待签约合同信息
	public void pushSignContract(Map<String,Object> values,String fileName,String filePath,BMSContractTemplate template){
		String contractType=String.valueOf(values.get("contractTypeCode"));
		String channelCd=String.valueOf(values.get("channelCd"));
		if(template.geteContractFlag()==0&&"1".equals(contractType)){
			contractType=inverseType(contractType);
			Map<String,String> map= filterContract(channelCd, contractType, template);
			if(map!=null&&map.containsKey("protocol")){
				String protocolType=map.get("protocol");
				Map<String,String> picParaMap=new HashMap<String,String>();
				picParaMap.put("sysName", EnumConstants.BMS_SYSTEM_CODE);
				picParaMap.put("dataSources", EnumConstants.dataSources.PC.getValue());
				picParaMap.put("nodeKey", "contractAward");
				picParaMap.put("code",map.get("foldName"));
				picParaMap.put("appNo",String.valueOf(values.get("loanNo")));
				picParaMap.put("operator",String.valueOf(values.get("serviceName")));
				picParaMap.put("jobNumber",String.valueOf(values.get("serviceCode")));
				picParaMap.put("filePath",new StringBuffer(filePath).append("/").append(fileName).toString());
				Map<String,Object> backResult=httpClientService.delContract(picParaMap,String.valueOf(map.get("foldName")));
				if(!"0000".equals(backResult.get("resultCode"))){
					throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"电子签章合同删除失败!");
				}
				Map<String,Object> result=httpClientService.uploadContract(picParaMap);
				if("0000".equals(result.get("resultCode"))&&StringUtils.isNotEmpty(String.valueOf(result.get("filePath")))){
					Map<String,Object> paraMap=new HashMap<String,Object>();
					String picFilePath=String.valueOf(result.get("filePath"));
					String picFileName=picFilePath.substring(picFilePath.lastIndexOf("/")+1, picFilePath.length());
					picFilePath=picFilePath.substring(0,picFilePath.lastIndexOf("/")+1);
					paraMap.put("loanId", values.get("loanId"));
					paraMap.put("appNo", values.get("loanNo"));
					paraMap.put("contractNum",values.get("contractNum"));
					paraMap.put("contractType",protocolType);
					paraMap.put("saveDirectory",picFilePath);
					paraMap.put("fileName", picFileName);
					JSONObject jsonObj=new JSONObject(paraMap);
					result=httpClientService.signContract(pushSignUrl,jsonObj.toString());
					if(result==null||!"000000".equals(String.valueOf(result.get("resCode")))){
						throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"电子合同签章失败!");
					}
					
				}else{
					   throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,"电子签章合同上传pic失败!");
				}
			}
		}
	}
	
	//过滤合同生成
	public Map<String,String> filterContract(String channelCd,String contractType,BMSContractTemplate template){
		contractType=inverseType(contractType);
		Map<String,String> dataMap=EnumBH2Constants.getTmpprotocol(channelCd, contractType,template.getCode());
		if(dataMap!=null&&dataMap.size()>0){
			return dataMap;
		}else{
			return null;
		}
	}
	
	public String inverseType(String contractType){
		if("1".equals(contractType)){
			contractType="0";
		}else if("0".equals(contractType)){
			contractType="1";
		}else{
			contractType= null;
		}
		return contractType;
	}
	
}
