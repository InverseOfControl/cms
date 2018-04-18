package com.ymkj.cms.biz.service.channel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.enums.EnumChannelCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.biz.dao.channel.IBMSBaseLoanDao;
import com.ymkj.cms.biz.entity.channel.BMSLoanBatch;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.service.channel.IBMSBaseLoanService;
import com.ymkj.cms.biz.service.process.ITaskService;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理 service impl
 */
@Service
public class BMSBaseLoanServiceImpl extends BaseServiceImpl<BMSLoanBatch> implements IBMSBaseLoanService {

	@Autowired
	private IBMSBaseLoanDao bmsBaseLoanDao;

	@Override
	protected BaseDao<BMSLoanBatch> getDao() {
		return bmsBaseLoanDao;
		
		
	}
	
	@Autowired
	private ITaskService taskService;

	@Override
	public long createBatch(List<BMSLoanBatch> bmsLoanBatchs, List<BMSLoanBase> bmsLoanBases,String channelCd,ReqBacthNumVO reqBatchNumVo) {
		long backNum = getDao().batchInsert(bmsLoanBatchs);
		if (backNum == bmsLoanBatchs.size()) {
			if (EnumChannelCode.海门小贷.getChannelShort() != channelCd) {// 海门小贷批次生成非必经流程
				taskService.bacthCompTaskByList(bmsLoanBases, reqBatchNumVo.getServiceCode(), EnumConstants.WFPH_SCPC);
			}
		}
		return backNum;
	}

	@Override
	public List<ResLoanCheckExpVo> listLoanCheckExp(Map<String, Object> paraMap) {
		return bmsBaseLoanDao.listLoanCheckExp(paraMap);
	}

	@Override
	public List<ResLoanExpVo> listLoanExp(Map<String, Object> paraMap) {
		return bmsBaseLoanDao.listLoanExp(paraMap);
	}

	@Override
	public long delBatch(List<String> loanNos) {
		return bmsBaseLoanDao.delBatch(loanNos);
	}

	@Override
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo) {
		Response<List<ResWMXTExportVo>> response = bmsBaseLoanDao.wmxtExpLoanQuery(requestVo);
		return response;
	}

	@Override
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo) {
		String num = newReqLoanBaseVo.getBatchNum();
		return bmsBaseLoanDao.findLoanNoByNum(num);
	}

	@Override
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo) {
		return bmsBaseLoanDao.wmxtExpLoanCheck(requestVo);
	}
	
	@Override
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile) {
		return bmsBaseLoanDao.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
	}
	
	@Override
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dowmType", "01");//下载
		map.put("fileType",reqCheck.getFileType());//08
		map.put("batchNum", reqCheck.getBatchNum());
		RequestFileOperateRecord req=bmsBaseLoanDao.checkRequestManagerOperateRecord(map);
		if(null==req){//新增
			map.put("seqFile", reqCheck.getSeqFile());
			map.put("createId", reqCheck.getCreateId());
			map.put("create", reqCheck.getCreate());
			bmsBaseLoanDao.insertRequestManagerOperateRecord(map);
		}else{//更新
			map.put("id", req.getId());
			map.put("times",req.getTimes()+1);
			map.put("seqFile", reqCheck.getSeqFile());
			map.put("createId", reqCheck.getCreateId());
			map.put("create", reqCheck.getCreate());
			bmsBaseLoanDao.updateRequestManagerOperateRecord(map);
		}
		
	}

	@Override
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVos) {
		return bmsBaseLoanDao.findCodebyParentIds(listResWMXTDataVos);
	}
	
}
