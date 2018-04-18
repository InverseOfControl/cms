package com.ymkj.cms.web.boss.controller.master;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IBaseAreaService;
import com.ymkj.cms.web.boss.service.master.IChannelService;

@Controller
@RequestMapping("baseArea")
public class BaseAreaController extends BaseController{

	@Autowired
	private IBaseAreaService baseAreaService;
	
	
	
	@RequestMapping("view")
	public String view() {
		return "master/baseArea/baseAreaList";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)//,consumes = "multipart/form-data"
	public Map<String,Object> add(@RequestParam("uploadfile")MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response) throws IOException{//@RequestParam("file") MultipartFile file,
		Map<String,Object> hashMap = new HashMap<String,Object>();
	
		ReqBMSBaseAreaVO reqDemoVO=new ReqBMSBaseAreaVO();
		boolean result=baseAreaService.addBaseArea(reqDemoVO,multipartFile);
		hashMap.put("isSuccess", result);
		
	/*}*/
		return hashMap;
	}
	@RequestMapping("/addBaseArea")
	public Map<String,Object> addBaseArea(@RequestParam MultipartFile uploadfile,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> hashMap = new HashMap<String,Object>();
		/*if(areaService.deletelAll()>0){
			
			areaService.insert(reqDemoVO);
		}*/
		hashMap.put("isSuccess",true);
		return hashMap;
	}

	
}

