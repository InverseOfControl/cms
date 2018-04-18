package com.ymkj.cms.biz.service.apply.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.apply.ReqUpdReviewVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.apply.ILoanReviewDao;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.service.apply.ILoanReviewService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

@Service
public class LoanReviewServiceImpl extends BaseServiceImpl<LoanReviewEntity> implements ILoanReviewService{
	
	@Autowired
	private ILoanReviewDao loanReviewDao;
	
	@Autowired
	private LoanBaseService loanBaseService;
	
	@Autowired
	private PMSClientService pmsClientService;

	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;
	
	@Override
	protected BaseDao<LoanReviewEntity> getDao() {
		return loanReviewDao;
	}
	

	@Override
	public List<LoanReviewEntity> getReviewList(Map<String, Object> paramMap) {
		
		return loanReviewDao.getReviewList(paramMap);
	}
	
	@Override
	public boolean insert(List<LoanReviewEntity> loanReviewEntityList) {
		long i = loanReviewDao.batchInsert(loanReviewEntityList);
		if(i > 0){
			return true;
		}
		return false;
	}

	@Override
	public int updateStatus(LoanReviewEntity loanReviewEntity) {
		return loanReviewDao.updateStatus(loanReviewEntity);
	}

	@Override
	public int insertReviewReason(LoanReviewEntity loanReviewEntity) {
		
		return loanReviewDao.insertReviewReason(loanReviewEntity);
	}

	@Override
	public int queryMessageCount(Map<String,Object> paramMap) {
		
		return loanReviewDao.queryMessageCount(paramMap);
	}

	@Override
	public int updateIsReadStatus(String loanNo) {
		
		return loanReviewDao.updateIsReadStatus(loanNo);
	}

	@Override
	public int updateOrSaveReviewStatus(Map<String, Object> map) {
		
		return loanReviewDao.updateOrSaveReviewStatus(map);
	}

	@Override
	public int updateBlackListIdByLoanNo(Map<String, Object> map) {
		
		return loanReviewDao.updateBlackListIdByLoanNo(map);
	}

	@Override
	public LoanReviewEntity selectByLoanNo(String loanNo) {
	
		return loanReviewDao.selectByLoanNo(loanNo);
	}

	@Override
	public int saveLoanLog(ReqUpdReviewVO reqUpdateReviewReadStatusVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", 	reqUpdateReviewReadStatusVO.getLoanNo());
		InformationEntity informationEntity = loanBaseService.queryInformationEntity(paramMap);
		if(informationEntity!=null){}
		BMSSysLoanLog bmsSysLoanLog = new BMSSysLoanLog();
		bmsSysLoanLog.setLoanNo(informationEntity.getLoanNo());
		bmsSysLoanLog.setStatus(EnumConstants.LoanStatus.REFUSE.getValue());
		bmsSysLoanLog.setRtfState(informationEntity.getRtfState());
		bmsSysLoanLog.setRtfNodeState(informationEntity.getRtfNodeState());
		bmsSysLoanLog.setFirstLevleReasons(reqUpdateReviewReadStatusVO.getNewRejectFirstReason());
		bmsSysLoanLog.setFirstLevleReasonsCode(reqUpdateReviewReadStatusVO.getNewRejectFirstReasonCode());
		bmsSysLoanLog.setTwoLevleReasonsCode(reqUpdateReviewReadStatusVO.getNewRejectTwoReasonCode());
		bmsSysLoanLog.setTwoLevleReasons(reqUpdateReviewReadStatusVO.getNewRejectTwoReason());
		bmsSysLoanLog.setCheckNodeState(informationEntity.getCheckNodeState());
		bmsSysLoanLog.setOperatorCode(reqUpdateReviewReadStatusVO.getModifierId());
		ReqEmployeeVO reqEmployeeVO=new ReqEmployeeVO();
		reqEmployeeVO.setUsercode(reqUpdateReviewReadStatusVO.getModifierId());
		ResEmployeeVO resVO=pmsClientService.findEmployeeByCode(reqEmployeeVO);
		if(resVO!=null){
			bmsSysLoanLog.setOperator(resVO.getName());	
		}
		if(reqUpdateReviewReadStatusVO.getReviewResult()==0){
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_RECONS_PASS.getValue());
		}else if(reqUpdateReviewReadStatusVO.getReviewResult()==1){
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_RECONS_REJECT.getValue());
		}else{
			bmsSysLoanLog.setOperationType(EnumConstants.OptionType.XS_RECONS_TRTURN.getValue());
		}
		
		bmsSysLoanLog.setOperationTime(DateUtil.getTodayHHmmss());
		bmsSysLoanLog.setRemark(reqUpdateReviewReadStatusVO.getRemark());
		int result = (int) bmsSysLoanLogDao.insert(bmsSysLoanLog);
		
		return result;
	}


	@Override
	public int updateReasonByNo(Map<String, Object> map) {
	
		return loanReviewDao.updateReasonByNo(map);
	}
	
}
