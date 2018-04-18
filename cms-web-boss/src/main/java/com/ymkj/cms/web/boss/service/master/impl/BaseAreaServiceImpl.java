package com.ymkj.cms.web.boss.service.master.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.facade.apply.BaseAreaFacade;
import com.ymkj.cms.web.boss.service.master.IBaseAreaService;

@Service
public class BaseAreaServiceImpl implements IBaseAreaService {

	@Autowired
	private BaseAreaFacade baseAreaFacade;
	@Override
	public boolean deletelAllBaseArea(ReqBMSBaseAreaVO reqDemoVO){
		return this.baseAreaFacade.deletelAllBaseArea(reqDemoVO);
	}

	@Override
	public boolean addBaseArea(ReqBMSBaseAreaVO reqDemoVO,MultipartFile multipartFile) throws IOException {
		//删除全部数据
		if(deletelAllBaseArea(reqDemoVO)){
		//添加信息
		int resMsg=0;     
		String OneCode="";    //第一级编号
		String TwoCode="";    //第二级编号
		long areaId=0;        //第一级Id
		long areaIdTwo=0;     //第二级Id
		 BufferedReader br = null;
		 String str = "";
		 String str1="";
		 List<String> l = new ArrayList<String>();
		  try {
			  InputStream inputStream = null;
				 inputStream = multipartFile.getInputStream();
				  br= new BufferedReader(new InputStreamReader(inputStream));
		   while ((str = br.readLine()) != null) {
			   if(str.indexOf("=")!=-1){
			   l.add(str);
			   }
			   str1 += str + "\n";
		   }
		  } catch (FileNotFoundException e) {
		   System.out.println("找不到指定文件");
		  } catch (IOException e) {
		   System.out.println("读取文件失败");
		  } finally {
		     br.close();
		  }
		  
		  for(String ls:l){
			  
			    //将第一级插入数据库
			   if(ls.indexOf("0000=")!=-1){
				   System.out.println("第一级");
				   String arr[] = ls.split("=");
				   reqDemoVO.setAreaId(new Long((long)0));
				   reqDemoVO.setName(arr[1]);
				   reqDemoVO.setCode(arr[0]);
				   OneCode=reqDemoVO.getCode().substring(0,2);
				   reqDemoVO.setParentId(new Long((long)0));
				   reqDemoVO.setDeep(new Long((long)1));
				   ReqBMSBaseAreaVO req=this.baseAreaFacade.addBaseArea(reqDemoVO);
				   if(req!=null){
					   areaId=req.getAreaId();
					   System.out.println("areaId:"+areaId);
					   }
					   resMsg++;
			   }else
			   //将第二级插入数据库中
			   if(ls.indexOf(OneCode)!=-1&&ls.indexOf("00=")!=-1){
				   System.out.println("第二级");
				   String arr[] = ls.split("=");
				   reqDemoVO.setName(arr[1]);
				   reqDemoVO.setCode(arr[0]);
				   TwoCode=reqDemoVO.getCode().substring(0,4);
				   reqDemoVO.setParentId(areaId);
				   reqDemoVO.setDeep(new Long((long)2));
				   ReqBMSBaseAreaVO req=this.baseAreaFacade.addBaseArea(reqDemoVO);
				   if(req!=null){
					areaIdTwo=req.getAreaId();
					System.out.println("areaIdTwo:"+areaIdTwo);
				   }
				   resMsg++;
				   
			   }else
			   
			  
			   //将第三级插入数据中
			   if((ls.indexOf(TwoCode)!=-1&&ls.indexOf("区")!=-1&&ls.indexOf("00=")==-1)||(ls.indexOf(TwoCode)!=-1&&ls.indexOf("县")!=-1&&ls.indexOf("00=")==-1)||(ls.indexOf(TwoCode)!=-1&&ls.indexOf("市")!=-1&&ls.indexOf("00=")==-1)){
				   System.out.println("第三级");
				   String arr[] = ls.split("=");
				   reqDemoVO.setName(arr[1]);
				   reqDemoVO.setCode(arr[0]);
				   reqDemoVO.setParentId(areaIdTwo);
				   reqDemoVO.setDeep(new Long((long)3));
				   if(reqDemoVO!=null){
				   if(this.baseAreaFacade.addBaseArea(reqDemoVO)!=null)
					   resMsg++;
				   }
				   
			   }
		  }
		 return resMsg >= 1 ? true : false;
	
		}else{
         return  false;
        }
	}

	@Override
	public boolean deleteById(ReqBMSBaseAreaVO reqDemoVO) {
		return this.baseAreaFacade.deleteById(reqDemoVO.getAreaId().toString());
	}

	@Override
	public List<ResBMSBaseAreaVO> listBy(ReqBMSBaseAreaVO reqDemoVO) {
		ResListVO<ResBMSBaseAreaVO> resp=this.baseAreaFacade.listBy(reqDemoVO);
		return resp.getCollections();
	}

	@Override
	public List<ResBMSBaseAreaVO> findByName(ReqBMSBaseAreaVO reqDemoVO) {
		ResListVO<ResBMSBaseAreaVO> response=baseAreaFacade.findByName(reqDemoVO);
		return response.getCollections();
	}

	@Override
	public List<ResBMSBaseAreaVO> getAllBaseAreaByCode(ReqBMSBaseAreaVO reqDemoVO) {
		Response<ResBMSBaseAreaVO> response=baseAreaFacade.getAllBaseAreaByCode(reqDemoVO);
		return null;
	}

}
