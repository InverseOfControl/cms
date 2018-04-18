package com.ymkj.cms.biz.service.sign.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.sign.IUploadFileInfoDao;
import com.ymkj.cms.biz.entity.sign.BMSUploadFileInfo;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractFileService;

@Service
public class AiTeLoanContractFileServiceImpl implements IAiTeLoanContractFileService {
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Autowired
	private IUploadFileInfoDao uploadFileDaoInfo;

	@Override
	public Map<String, Object> uploadFile(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean noticeFlag = false;
		String appNo = requestVo.getBorrowNo();
		String files = requestVo.getFiles();
		// 上传文件，pic接口待定
		Map<String, String> map = new HashMap<String, String>();
		map.put("sysName", EnumConstants.BMS_SYSTEM_CODE);//系统编号
		map.put("nodeKey", EnumConstants.BusinessSegment.CONTRACT_AWARD.getValue());//业务代号   contractAward
		map.put("appNo", appNo);
		map.put("operator", EnumConstants.AITE_USER_NAME);
		map.put("jobNumber", EnumConstants.AITE_USER_CODE);
		map.put("dataSources", EnumConstants.dataSources.PC.getValue());

		//解析files，已文件流的形式上传
		
		map.put("files", files);
		

		HttpResponse httpResponse = aiTeHttpService.uploadFile(map);

		if (0 == httpResponse.getCode()) {
			noticeFlag = true;
		} else {
			noticeFlag = false;
		}

		if (noticeFlag) {
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "success");
		} else {
			throw new CoreException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");
		}
		return retMap;
	}

	@Override
	public Map<String, Object> deleteFile(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean noticeFlag = false;
		String loanNo = requestVo.getBorrowNo();
		// 根据所有人，借款编号，系统编号,业务环节，查找文件在PIC系统保存的id
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("owerCode", EnumConstants.AITE_USER_CODE);
		paramMap.put("loanNo", loanNo);
		paramMap.put("sysCode", EnumConstants.BMS_SYSTEM_CODE);
		paramMap.put("nodeKey", EnumConstants.BusinessSegment.CONTRACT_AWARD.getValue());
		
		List<BMSUploadFileInfo> uploadFileInfoList = uploadFileDaoInfo.listBy(paramMap);
		if(uploadFileInfoList == null || uploadFileInfoList.isEmpty()){
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "满标时未上传文件");
			return retMap;
		}
		String ids = "";
		
		for (int i = 0; i < uploadFileInfoList.size(); i++) {
			BMSUploadFileInfo info = uploadFileInfoList.get(i);
			ids += info.getFileId() + ",";
		}
		ids = ids.substring(0, ids.length()-1);
		
		Map<String, String> map = new HashMap<String, String>();
//		map.put("ids", "1398");//文件Id集合,12,13,14
		map.put("ids", ids);//文件Id集合,12,13,14
		map.put("ifWaste", "Y");//是否作废,Y或者N
		map.put("operator", EnumConstants.AITE_USER_NAME);//操作人姓名
		map.put("jobNumber", EnumConstants.AITE_USER_CODE);//工号
		
		HttpResponse httpResponse = aiTeHttpService.deleteFile(map);
		if(EnumConstants.HTTP_CODE_SUCCESS == httpResponse.getCode()){
			String content = httpResponse.getContent();
			Map contentMap = JsonUtils.decode(content, Map.class);
			if(Response.SUCCESS_RESPONSE_CODE.equals(contentMap.get("errorcode"))){
				//作废本地数据
				for (BMSUploadFileInfo info : uploadFileInfoList) {
					info.setIsDeleted(1L);
				}
				uploadFileDaoInfo.update(uploadFileInfoList);
				
				noticeFlag = true;
			} else {
				noticeFlag = false;
			}
			
		} else{
			noticeFlag = false;
		}

		if (noticeFlag) {
			retMap.put("code", Response.SUCCESS_RESPONSE_CODE);
			retMap.put("message", "success");
		} else {
			throw new CoreException(CoreErrorCode.FACADE_RESPONSE_FAIL, "接口调用返回失败消息");
		}
		return retMap;
	}

	@Override
	public Long saveAiteUploadFile(Map<String, Object> resultMap) {
		
		//{id=1424, url=//localhost//file/bms/20170303165731168805/E_BMS/de4a828d1e114117929a467ec768a751.jpg}
		BMSUploadFileInfo info = new BMSUploadFileInfo();
		
		info.setFileId(Long.valueOf(resultMap.get("id").toString()));
		info.setFileName(resultMap.get("fileName").toString());
		info.setUrl(resultMap.get("url").toString());
		info.setLoanNo(resultMap.get("appNo").toString());
		info.setOwerCode(resultMap.get("jobNumber").toString());
		info.setSysCode(resultMap.get("sysName").toString());
		info.setNodeKey(resultMap.get("nodeKey").toString());
		
		
		return uploadFileDaoInfo.insert(info);
		
	}

}
