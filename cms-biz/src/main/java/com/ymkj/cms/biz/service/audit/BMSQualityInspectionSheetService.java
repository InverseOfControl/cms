package com.ymkj.cms.biz.service.audit;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionSheetVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityPassParameterVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetVO;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;

public interface BMSQualityInspectionSheetService extends BaseService<BMSLoanBase>{
	/**
	 * 通过件
	 * @param reqQualityInspectionSheetVO
	 * @return
	 */
	public ResQualityInspectionSheetVO getPass(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO);
	/**
	 * 拒绝件
	 */
	public ResQualityInspectionSheetVO getReject(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO);
	
	/**
	 * 根据借款编号查询对应申请件
	 */
	public ResQualityInspectionSheetResultVO findByLoanNo(ReqLoanNumberVO vo);
	

	/**
	 * 通过件（手动）
	 */
	public ResQualityInspectionSheetVO getHandPass(ReqQualityPassParameterVO vo);
	/**
	 * 拒绝件(手动)
	 */
	public ResQualityInspectionSheetVO getHandReject(ReqQualityPassParameterVO vo);
	
	/**
	 * 根据借款编号，姓名，身份证集合查询对应的申请件
	 */
	public List<ResQualityInspectionSheetResultVO> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos);
	
	/**
	 * 根据给定的日期期限，查询走过初审的初审人员CDE
	 */
	public  List<String> findCodeByDates(ReqQualityPassParameterVO vo);
	
	/**
	 * 查询通过件拒绝件自动抽单求总数
	 */
	public Integer getCountZj(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO);
}
