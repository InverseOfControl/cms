package com.ymkj.cms.web.boss.service.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;

public interface IIntegratedSearchService {
	
	public List<ResBMSQueryLoanLogVO> queryLoanLog(ReqQueryLoanLogVO reqQueryLoanLogVO);

}
