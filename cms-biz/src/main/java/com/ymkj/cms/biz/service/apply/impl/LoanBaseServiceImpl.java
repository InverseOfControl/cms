package com.ymkj.cms.biz.service.apply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.common.util.ApplyEnterEncapsulate;
import com.ymkj.cms.biz.dao.apply.LoanBaseDao;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSContractSignSearchEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPWhitePersonnelService;
import com.ymkj.cms.biz.service.apply.LoanBaseService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

@Service
public class LoanBaseServiceImpl extends BaseServiceImpl<LoanBaseEntity> implements LoanBaseService{
	
	@Autowired
	private LoanBaseDao loanBaseDao;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private APPWhitePersonnelService appWhitePerSonnelService;

	@Override
	protected BaseDao<LoanBaseEntity> getDao() {
		return loanBaseDao;
	}

	@Override
	public Long saveOrUpdate(LoanBaseEntity loanBaseEntity) {
		
		
		long id = 0;
		if(loanBaseEntity.getId() == null){
			long insert = loanBaseDao.insert(loanBaseEntity);
			if(insert > 0){
				id = loanBaseEntity.getId();
			}
		}else{
			 long update = loanBaseDao.update(loanBaseEntity);
			 if(update > 0){
				 id = loanBaseEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<LoanBaseEntity> loanBaseEntitys) {
		
		long i = loanBaseDao.batchInsert(loanBaseEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public PageBean<EntrySearchEntity> listPageEntrySearch(PageParam pageParam,
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.listPageEntrySearch(pageParam, paramMap, "listPageEntrySearch");
	}

	@Override
	public PageBean<BMSContractSignSearchEntity> listPageContractSignSearch(
			PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.listPageContractSignSearch(pageParam, paramMap, "listPageContractSignSearch");
	}

	@Override
	public List<LoanBaseEntity> getByMap(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.getByMap(paramMap);
	}

	@Override
	public PageBean<LoanBaseEntity> getReassignment(PageParam pageParam, Map<String, Object> paramMap) {
		return loanBaseDao.getReassignment(pageParam, paramMap, "getReassignment");
	}

	@Override
	public InformationEntity queryInformationEntity(Map<String, Object> paramMap) {
		return loanBaseDao.queryInformationEntity(paramMap);
	}

	@Override
	public boolean updateOperatingStaff(Map<String, Object> paramMap) {
		return loanBaseDao.updateOperatingStaff(paramMap);
	}
	
	public ResEmployeeVO getEffectiveEmployeeVo(Long orgId,List<String> roleCodes){
		
		ReqParamVO paramv = new ReqParamVO();
		paramv.setOrgId(orgId);
		paramv.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		paramv.setRoleCodes(roleCodes);
		Response<List<ResEmployeeVO>> findEmpsByOrgId = iEmployeeExecuter.findByDeptAndRole(paramv);
		if(!findEmpsByOrgId.isSuccess()){
			throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
		}
		if(findEmpsByOrgId.getData() == null || findEmpsByOrgId.getData().size() == 0){
			throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);
		}
		//查出目前所有禁止接单的列表
		List<APPWhitePersonnelEntity> APPWhitePersonnellist = appWhitePerSonnelService.listBy(new HashMap<String,Object>());
		//禁止接单列表是否为空
		if(APPWhitePersonnellist == null || APPWhitePersonnellist.size() == 0){
			int i = ApplyEnterEncapsulate.getRandomNumber(0, findEmpsByOrgId.getData().size());
			System.out.println("直通车进件签约客服  选择 签约营业部下面随机员工，根据营业部ID=["+orgId+"]，查询员工数["+findEmpsByOrgId.getData().size()+"]，随机选择员工为["+findEmpsByOrgId.getData().get(i).getName()+"]");
			return findEmpsByOrgId.getData().get(i);
		}else{
			for (int i = 0;i<= findEmpsByOrgId.getData().size();i++) {
				int size = ApplyEnterEncapsulate.getRandomNumber(0, findEmpsByOrgId.getData().size());
				boolean tr = true;
				//判断是否是禁止接单员工
				for (APPWhitePersonnelEntity aPPWhitePersonnelEntity : APPWhitePersonnellist) {
					if(findEmpsByOrgId.getData().get(size).getUsercode().equals(aPPWhitePersonnelEntity.getUserCode())){
						tr = false;
					}
				}
				if(tr){
					return findEmpsByOrgId.getData().get(size);
				}
			}
		}	
		return null;
	}

	@Override
	public long updateVersion(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.updateVersion(paramMap);
	}

	@Override
	public Long queryWaitToDealCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.queryWaitToDealCount(paramMap);
	}

	@Override
	public long updateLoanBaseVersionById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loanBaseDao.updateLoanBaseVersionById(paramMap);
	}

	
	@Override
	public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO) {
		return loanBaseDao.findLoanNoByNotBelongTo(personHistoryLoanVO);
	}

	@Override
	public Map<String, Object> findRadioByApplyType(String applyType) {
		return loanBaseDao.findRadioByApplyType(applyType);
	}

	@Override
	public long updateIfNewLoanNo(Map<String, Object> paramMap) {
		return loanBaseDao.updateIfNewLoanNo(paramMap);
	}

	@Override
	public String getOrgPrevReviewCode(Map paramMap) {
		
		return loanBaseDao.getOrgPrevReviewCode(paramMap);
	}

	@Override
	public List<Map<String,Object>> findDataByIdNoRefuse(String idNo) {
		
		return loanBaseDao.findDataByIdNoRefuse(idNo);
	}

	@Override
	public List<Map<String, Object>> findDataByIdNoCancel(String idNo) {
		return loanBaseDao.findDataByIdNoCancel(idNo);
	}

	@Override
	public Map<String, Object> queryContractInfo(String loanNo) {
		return loanBaseDao.queryContractInfo(loanNo);
	}
}
