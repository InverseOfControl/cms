package com.ymkj.cms.biz.dao.channel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.biz.dao.channel.IBMSBaseLoanDao;
import com.ymkj.cms.biz.entity.channel.BMSBhxtCitOrg;
import com.ymkj.cms.biz.entity.channel.BMSLoanBatch;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;
import com.ymkj.cms.biz.entity.finance.WMXTExportCheckEntity;
import com.ymkj.cms.biz.entity.finance.WMXTExportEntity;

/**
 * @author YM10189
 * @date 2017年5月6日
 * @Description:批次管理 dao impl
 */
@Repository
public class BMSBaseLoanDaoImpl extends BaseDaoImpl<BMSLoanBatch> implements IBMSBaseLoanDao {

	@Override
	public long addBatch(List<BMSLoanBatch> bmsLoanBatchs) {
		return super.batchInsert(bmsLoanBatchs);
	}

	@Override
	public List<ResLoanCheckExpVo> listLoanCheckExp(Map<String, Object> paraMap) {
		return getSessionTemplate().selectList("listLoanCheck",paraMap);
	}

	@Override
	public List<ResLoanExpVo> listLoanExp(Map<String, Object> paraMap) {
		return getSessionTemplate().selectList("listLoan",paraMap);
	}

	@Override
	public long delBatch(List<String> loanNos) {
		return getSessionTemplate().delete("batchDelete", loanNos);
	}

	@Override
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgIds",requestVo.getOrgIds());//管理营业部IDS
		map.put("loanNos", requestVo.getLoanNos());//借款编号S
		List<WMXTExportEntity> entitys=getSessionTemplate().selectList("wmxtExpLoanQuery",map);
		List<ResWMXTExportVo> listResWMXTExportVo=new ArrayList<ResWMXTExportVo>();
		for(WMXTExportEntity entity:entitys){
			ResWMXTExportVo v=new ResWMXTExportVo();
			BeanUtils.copyProperties(entity, v);
			listResWMXTExportVo.add(v);
		}
		Response<List<ResWMXTExportVo>> responstList=new Response<List<ResWMXTExportVo>>();
		responstList.setData(listResWMXTExportVo);
		return responstList;
	}

	@Override
	public Response<List<String>> findLoanNoByNum(String num) {
		Response<List<String>> response=new Response<List<String>>();
		List<BMSLoanBatch> list=getSessionTemplate().selectList("findLoanNoByNum", num);
		List<String> listStr=new ArrayList<String>();
		for(BMSLoanBatch bMSLoanBatch:list){
			listStr.add(bMSLoanBatch.getLoanNo());
		}
		response.setData(listStr);
		return response;
	}

	@Override
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNos", requestVo.getLoanNos());//借款编号S
		List<WMXTExportCheckEntity> entitys=getSessionTemplate().selectList("wmxtExpLoanCheck",map);
		List<ResWMXTExportCheckVo> listResWMXTExportVo=new ArrayList<ResWMXTExportCheckVo>();
		for(WMXTExportCheckEntity entity:entitys){
			ResWMXTExportCheckVo v=new ResWMXTExportCheckVo();
			BeanUtils.copyProperties(entity, v);
			listResWMXTExportVo.add(v);
		}
		Response<List<ResWMXTExportCheckVo>> responstList=new Response<List<ResWMXTExportCheckVo>>();
		responstList.setData(listResWMXTExportVo);
		return responstList;
	}

	@Override
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dowmType", "01");//下载
		map.put("fileType",reqLoanBaseVoQuerySeqFile.getFileType());//08
		map.put("batchNum", reqLoanBaseVoQuerySeqFile.getBatchNum());
		RequestFileOperateRecord entity=getSessionTemplate().selectOne("findFileSeqByBatchNum",map);
		ResRequestFileOperateRecordVo v=new ResRequestFileOperateRecordVo();
		if(null!=entity){
			BeanUtils.copyProperties(entity, v);
		}
		Response<ResRequestFileOperateRecordVo> responst=new Response<ResRequestFileOperateRecordVo>();
		responst.setData(v);
		return responst;
	}

	@Override
	public RequestFileOperateRecord checkRequestManagerOperateRecord(Map<String, Object> map) {
		RequestFileOperateRecord entity=getSessionTemplate().selectOne("checkRequestManagerOperateRecord",map);
		return entity;
	}

	@Override
	public int insertRequestManagerOperateRecord(Map<String, Object> map) {
		return getSessionTemplate().insert("insertRequestManagerOperateRecord",map);
	}

	@Override
	public int updateRequestManagerOperateRecord(Map<String, Object> map) {
		return getSessionTemplate().update("updateRequestManagerOperateRecord",map);
	}

	@Override
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVos) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("listReq", listResWMXTDataVos);
		List<BMSBhxtCitOrg> entitys=getSessionTemplate().selectList("findCodebyParentIds",map);
		List<ResBhxtCitOrgVo> listResBhxtCitOrgVo=new ArrayList<ResBhxtCitOrgVo>();
		for(BMSBhxtCitOrg org:entitys){
			ResBhxtCitOrgVo resVo=new ResBhxtCitOrgVo();
			BeanUtils.copyProperties(org, resVo);
			listResBhxtCitOrgVo.add(resVo);
		}
		
		Response<List<ResBhxtCitOrgVo>> response=new Response<List<ResBhxtCitOrgVo>>();
		response.setData(listResBhxtCitOrgVo);
		return response;
	}

}
