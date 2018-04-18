package com.ymkj.cms.biz.api.service.audit.qualitytest;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionSheetVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityPassParameterVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetVO;

/**
 * 质检抽单
 * @author YM10180
 *
 */
public interface IQualityInspectionSheetExecuter {
	
	/**
	 * 抽（终审通过)还有(初审拒绝)的单子
	 */
	ResQualityInspectionSheetVO getPassOrRefuse(ReqQualityInspectionSheetVO vo);
	
	/**
	 * 根据借款编号查询申请件EXCEL导入
	 */
	Response<ResQualityInspectionSheetResultVO> findByLoanNo(ReqLoanNumberVO vo);
	
	/**
	 * 根据一些列条件查询对应的通过件(手动抽单)
	 */
	ResQualityInspectionSheetVO getHandPass(ReqQualityPassParameterVO vo);
	
	/**
	 * 根据一些条件查询对应的拒绝件(手动抽单)
	 */
	ResQualityInspectionSheetVO getHandReject(ReqQualityPassParameterVO vo);
	/**
	 * 根据借款编号，姓名，省份证集合查询申请件(批量)EXCEL导入
	 */
	Response<List<ResQualityInspectionSheetResultVO>> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos);
	
	/**
	 *  根据给定的期限查询在这段期限之内的走玩初审的初审人员CODE
	 */
	Response<List<String>> findCodeByDates(ReqQualityPassParameterVO vo);
}
