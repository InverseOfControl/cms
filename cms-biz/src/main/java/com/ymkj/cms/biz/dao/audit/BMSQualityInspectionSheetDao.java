package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;


public interface BMSQualityInspectionSheetDao extends BaseDao<BMSLoanBase>{

	List<ResQualityInspectionSheetResultVO> getPass(Map<String,Object> map);
	List<ResQualityInspectionSheetResultVO> getReject(Map<String,Object> map);
	ResQualityInspectionSheetResultVO findByLoanNo(ReqLoanNumberVO vo);
	List<ResQualityInspectionSheetResultVO> getHandPass(Map<String,Object> map);
	List<ResQualityInspectionSheetResultVO> getHandReject(Map<String,Object> map);
	List<ResQualityInspectionSheetResultVO> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos);
	List<String> findCodeByDates(Map<String,Object> map);
	
	Integer getCountZj(Map<String,Object> map);
}
