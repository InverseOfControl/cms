package com.ymkj.cms.biz.dao.channel.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.biz.dao.channel.IBMSAppFormDao;
import com.ymkj.cms.biz.entity.channel.BMSAppForm;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理dao impl
 */
@Repository
public class BMSAppFormDaoImpl extends BaseDaoImpl<BMSAppForm> implements IBMSAppFormDao {

	@Override
	public ResAppBookVo findAppBook(Map<String, Object> paraMap) {
		return getSessionTemplate().selectOne("transferApply", paraMap);
	}

	@Override
	public List<ResRepaymentExpVo> listLoanRepaymentExp(Map<String, Object> paraMap) {
		return getSessionTemplate().selectList("repaymentExpXls", paraMap);
	}

	@Override
	public List<LoanApplyDetailVo> listLoanAppDetailExp(Map<String, Object> paraMap) {
		return getSessionTemplate().selectList("queryLoanApplyDetailList", paraMap);
	}

	public List<RequestFileOperateRecord> findRequestFileOperateRecord(RequestFileOperateRecord requestFileOperateRecord) {
		return getSessionTemplate().selectList("findRequestFileOperateRecord", requestFileOperateRecord);
	}
	
	public long insertRequestFileOpr(RequestFileOperateRecord requestFileOperateRecord){
		return getSessionTemplate().insert("insertRequestFileOpr", requestFileOperateRecord);
	}
	
	public long updateRequestFileOpr(RequestFileOperateRecord requestFileOperateRecord){
		return getSessionTemplate().update("updateFileRecord", requestFileOperateRecord);
	}

	@Override
	public String findRequestFileBatchNum(ReqFileBatchNumVo requestVo) {
		return getSessionTemplate().selectOne("findRequestFileBatchNum", requestVo);
	}

	@Override
	public List<RequestFileOperateRecord> queryCurrentDayRequestFileOperateRecordByAsc(Map<String, Object> paramMap) {
		 return getSessionTemplate().selectList("queryCurrentDayRequestFileOperateRecordByAsc",paramMap);
	}

}
