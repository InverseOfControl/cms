package com.ymkj.cms.biz.service.audit.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants.sysType;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionSheetVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityPassParameterVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetVO;
import com.ymkj.cms.biz.dao.audit.BMSQualityInspectionSheetDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionSheetService;
@Service
public class BMSQualityInspectionSheetServiceImpl extends BaseServiceImpl<BMSLoanBase> implements BMSQualityInspectionSheetService{

	
	@Autowired
	private BMSQualityInspectionSheetDao bMSQualityInspectionSheetDao;
	
	@Override
	protected BaseDao<BMSLoanBase> getDao() {
		return bMSQualityInspectionSheetDao;
	}

	@Override
	public ResQualityInspectionSheetVO getPass(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO) {
		ResQualityInspectionSheetVO vo=new ResQualityInspectionSheetVO();
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("userCode", reqQualityInspectionSheetVO.getUserCode());
		map.put("curttenDate", sdf.format(reqQualityInspectionSheetVO.getCurttenDate()));
		List<ResQualityInspectionSheetResultVO> listPass=bMSQualityInspectionSheetDao.getPass(map);
		vo.setPassList(listPass);;
//		Double countSize=((reqQualityInspectionSheetVO.getHistoryPassCount()+listPass.size())*reqQualityInspectionSheetVO.getScale())-reqQualityInspectionSheetVO.getHistoryPassScaleCount();
//		if(countSize.toString().substring(countSize.toString().indexOf(".")+1, countSize.toString().length()).equals("0")){
//			//如果是整数
//			int intCountSize=countSize.intValue();
//			
//		}else{
//			//如果有小数
//			int intCountSize=countSize.intValue()+1;
//			
//		}
		return vo;
	}

	@Override
	public ResQualityInspectionSheetVO getReject(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO) {
		ResQualityInspectionSheetVO vo=new ResQualityInspectionSheetVO();
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("userCode", reqQualityInspectionSheetVO.getUserCode());
		map.put("curttenDate", sdf.format(reqQualityInspectionSheetVO.getCurttenDate()));
		List<ResQualityInspectionSheetResultVO> listPass=bMSQualityInspectionSheetDao.getReject(map);
		vo.setRejectList(listPass);
		return vo;
	}
	
	@Override
	public ResQualityInspectionSheetResultVO findByLoanNo(ReqLoanNumberVO vo) {
		ResQualityInspectionSheetResultVO bms=bMSQualityInspectionSheetDao.findByLoanNo(vo);
		return bms;
	}
	
	@Override
	public ResQualityInspectionSheetVO getHandPass(ReqQualityPassParameterVO vo) {
		ResQualityInspectionSheetVO sheetVo=new ResQualityInspectionSheetVO();
		Map<String,Object> map=new HashMap<String,Object>();
		//SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("userCode", vo.getUserCode());
		map.put("startDate", vo.getStartDate()+" 00:00:00");
		map.put("endDate", vo.getEndDate()+" 23:59:59");
		map.put("owningBranchIds", vo.getOwningBranchIds());
		map.put("productCd", vo.getProductCd());
		map.put("firstLevleReasonsCode", vo.getFirstLevleReasonsCode());
		map.put("twoLevleReasonsCode", vo.getTwoLevleReasonsCode());
		map.put("applyType", vo.getApplyType());
		List<ResQualityInspectionSheetResultVO> listPass=bMSQualityInspectionSheetDao.getHandPass(map);
		sheetVo.setPassList(listPass);
		return sheetVo;
	}

	@Override
	public ResQualityInspectionSheetVO getHandReject(ReqQualityPassParameterVO vo) {
		ResQualityInspectionSheetVO sheetVo=new ResQualityInspectionSheetVO();
		Map<String,Object> map=new HashMap<String,Object>();
		//SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("userCode", vo.getUserCode());
		map.put("startDate", vo.getStartDate()+" 00:00:00");
		map.put("endDate", vo.getEndDate()+" 23:59:59");
		map.put("owningBranchIds", vo.getOwningBranchIds());
		map.put("productCd", vo.getProductCd());
		map.put("firstLevleReasonsCode", vo.getFirstLevleReasonsCode());
		map.put("twoLevleReasonsCode", vo.getTwoLevleReasonsCode());
		map.put("applyType", vo.getApplyType());
		List<ResQualityInspectionSheetResultVO> listPass=bMSQualityInspectionSheetDao.getHandReject(map);
		sheetVo.setRejectList(listPass);
		return sheetVo;
	}

	@Override
	public List<ResQualityInspectionSheetResultVO> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos) {
		List<ResQualityInspectionSheetResultVO> resQualityInspectionSheetResultVOs=bMSQualityInspectionSheetDao.findByLoanNoAndNameAndIdNos(vos);
		return resQualityInspectionSheetResultVOs;
	}

	@Override
	public List<String> findCodeByDates(ReqQualityPassParameterVO vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startDate", vo.getStartDate()+" 00:00:00");
		map.put("endDate", vo.getEndDate()+" 23:59:59");
		List<String> listString=bMSQualityInspectionSheetDao.findCodeByDates(map);
		return listString;
	}

	@Override
	public Integer getCountZj(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("userCode", reqQualityInspectionSheetVO.getUserCode());
		map.put("curttenDate", sdf.format(reqQualityInspectionSheetVO.getCurttenDate()));
		Integer countZj=bMSQualityInspectionSheetDao.getCountZj(map);
		return countZj;
		
	}

}
