package com.ymkj.cms.biz.service.apply.impl;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.bds.biz.api.service.IBlackListExecuter;
import com.ymkj.bds.biz.api.vo.request.BlackListQueryReqVO;
import com.ymkj.bds.biz.api.vo.response.BlackListResVO;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.enums.EnumConstants.LoanStatus;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResCheckNewDataVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.checkNewDataEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.service.apply.LoanExtService;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanExtServiceImpl extends BaseServiceImpl<LoanExtEntity> implements LoanExtService{
	
	@Autowired
	private LoanExtDao loanExtDao;

	
	@Autowired
	private IBlackListExecuter blackListExecuter;
	
	@Autowired
	private IBMSTmParameterService tmParameterService;
	
	@Autowired
	private IBMSSysLoanLogService sysLoanLogService;
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;
	
	@Override
	protected BaseDao<LoanExtEntity> getDao() {
		return loanExtDao;
	}

	@Override
	public Long saveOrUpdate(LoanExtEntity loanExtEntity) {
		
		
		long id = 0;
		if(loanExtEntity.getId() == null){
			long insert = loanExtDao.insert(loanExtEntity);
			if(insert > 0){
				id = loanExtEntity.getId();
			}
		}else{
			 long update = loanExtDao.update(loanExtEntity);
			 if(update > 0){
				 id = loanExtEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<LoanExtEntity> loanExtEntitys) {
		
		long i = loanExtDao.batchInsert(loanExtEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public ResCheckNewDataVO queryCheckNewData(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		//map.put("name", personHistoryLoanVO.getName());
		map.put("idNo", personHistoryLoanVO.getIdNo());
		List<checkNewDataEntity> entitys=loanExtDao.queryCheckNewData(map);
		ResCheckNewDataVO vo=new ResCheckNewDataVO();
	   
	    String black=getBlackList(personHistoryLoanVO.getName(),personHistoryLoanVO.getIdNo());
	    
	    if(null==entitys||entitys.size()==0){
	    	vo.setIfHaveValidate("N");
	    	vo.setIfGreyOrBlack(black);
	    }else{
	    	 BeanUtils.copyProperties(entitys.get(0), vo);
	    	 vo.setIfHaveValidate("Y");
	    	 vo.setIfGreyOrBlack(black);
	    	 if(vo.getPreviousStatus().equals(LoanStatus.CANCEL.getValue())||vo.getPreviousStatus().equals(LoanStatus.REFUSE.getValue())){
	    		 vo.setProtectDays(queryProtectDays());
	    		 LoanBaseEntity loanBase=new LoanBaseEntity();
	    		 loanBase.setLoanNo(vo.getLoanNo());
	    		 Map<String, String> returnMap =queryLimitRefuseDays(loanBase);
	    		 if(null!=returnMap){
	    			 if(null!=returnMap.get("evel")){
	    				 vo.setLimitDays(Integer.parseInt(returnMap.get("evel")));
	    			 }
	    		 }
	    		 
	    	 }else{
	    		 vo.setLimitDays(0);
	    		 vo.setProtectDays(0);
	    	 }
	    }
		return vo;
	}
	
	public String getBlackList(String name, String idNo) {
		// 灰黑名单
		BlackListQueryReqVO queryReqVo = new BlackListQueryReqVO();
		queryReqVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		queryReqVo.setIdNo(idNo);
		queryReqVo.setName(name);
		PageResponse<BlackListResVO> blackList = blackListExecuter.getBlackList(queryReqVo);
		if (blackList.isSuccess()) {
			// 当前申请 是否匹配黑名单 Y匹配 N不匹配
			if (blackList.getRecords() != null && blackList.getRecords().size() > 0) {
				return "Y";
			} else {
				return "N";
			}
		}
		return "N";
	}
	
	
	/**
	 * 查询保护天数
	 * 
	 * @return
	 */
	public Integer queryProtectDays() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", EnumConstants.LOAN_PROTECT_DAY);
		BMSTmParameter queryByCode = (BMSTmParameter) tmParameterService.getBy(paramMap, "queryByCode");
		if (queryByCode == null) {
			return Integer.parseInt(EnumConstants.LOAN_PROTECT_DAY_VALUE);
		} else {
			return Integer.parseInt(queryByCode.getParameterValue());
		}
	}

	/**
	 * 查询拒绝限制天数
	 * 
	 * @param loanBase
	 * @return
	 */
	public Map<String, String> queryLimitRefuseDays(LoanBaseEntity loanBase) {
		Map<String, String> res = new HashMap<String, String>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanBase.getLoanNo());
		paramMap.put("status", EnumConstants.LoanStatus.REFUSE.getValue());
		BMSSysLoanLog by = sysLoanLogService.getBy(paramMap);
		if (by != null) {
			res.put("previousRefuseDate", DateUtil.defaultFormatDate(by.getOperationTime()));

			if (!StringUtils.isEmpty(by.getTwoLevleReasonsCode())) {
				paramMap.put("code", by.getTwoLevleReasonsCode());
				BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
				if (evel != null) {
					res.put("evel", evel.getCanRequestDays() + "");
				}
			} else {
				if (!StringUtils.isEmpty(by.getFirstLevleReasonsCode())) {
					paramMap.put("code", by.getFirstLevleReasonsCode());
					BMSTMReasonEntity evel = iBMSTMReasonService.getBy(paramMap);
					if (evel != null) {
						res.put("evel", evel.getCanRequestDays() + "");
					}
				}
			}
			return res;
		}
		return null;
	}

}
