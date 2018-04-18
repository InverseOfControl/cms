package com.ymkj.cms.biz.service.audit.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.dao.audit.ILoanAuditDao;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.service.audit.ILoanAuditService;

@Service
public class LoanAuditServiceImpl extends BaseServiceImpl<LoanAuditEntity> implements ILoanAuditService{
	
	@Autowired
	private ILoanAuditDao loanAuditDao;

	@Override
	protected BaseDao<LoanAuditEntity> getDao() {
		return loanAuditDao;
	}

	@Override
	public long insert(LoanAuditEntity ioanAuditEntity) {
		return loanAuditDao.insert(ioanAuditEntity);
	}

	@Override
	public long update(LoanAuditEntity ioanAuditEntity) {
		return loanAuditDao.update(ioanAuditEntity);
	}
	
	@Override
	public LoanAuditEntity findByAudit(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo",personHistoryLoanVO.getLoanNo());
		LoanAuditEntity entity=loanAuditDao.findByAudit(map);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		Date d=null;
		if(null!=entity){
			try {
				d=sdf.parse(sdf.format(entity.getCreated_time()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		entity.setCreated_time(d);
		return entity;
	}

	@Override
	public Integer updaeAudittRulesData(LoanAuditEntity loanAuditEntity) {
		
		return loanAuditDao.updaeAudittRulesData(loanAuditEntity);
	}
}
