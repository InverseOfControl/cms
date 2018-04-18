package com.ymkj.cms.biz.service.apply;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResCheckNewDataVO;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;

import java.util.List;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface LoanExtService extends BaseService<LoanExtEntity>{
	
	public Long saveOrUpdate(LoanExtEntity loanExtEntity);
	
	public boolean saveList(List<LoanExtEntity> loanExtEntity);
	
	//此接口将返回新征审系统最新一笔贷款记录
	public ResCheckNewDataVO queryCheckNewData(PersonHistoryLoanVO personHistoryLoanVO);
}
	