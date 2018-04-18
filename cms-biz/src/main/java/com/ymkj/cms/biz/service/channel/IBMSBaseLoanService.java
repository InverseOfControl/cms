package com.ymkj.cms.biz.service.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.biz.entity.channel.BMSLoanBatch;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理
 */
public interface IBMSBaseLoanService extends BaseService<BMSLoanBatch> {

	long createBatch(List<BMSLoanBatch> bmsLoanBatchs,List<BMSLoanBase> bmsLoanBases,String channelCd,ReqBacthNumVO reqBatchNumVo);

	public List<ResLoanCheckExpVo> listLoanCheckExp(Map<String, Object> paraMap);

	public List<ResLoanExpVo> listLoanExp(Map<String, Object> paraMap);
	
	long delBatch(List<String> loanNos);

	public Response<List<ResWMXTExportVo>>  wmxtExpLoanQuery(ReqLoanBaseVo requestVo);
	
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo);
	
	public Response<List<ResWMXTExportCheckVo>>  wmxtExpLoanCheck(ReqLoanBaseVo requestVo);
	
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile);
	
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck);
	
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVos);
}
