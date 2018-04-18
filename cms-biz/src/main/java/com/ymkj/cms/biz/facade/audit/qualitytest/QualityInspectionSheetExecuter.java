package com.ymkj.cms.biz.facade.audit.qualitytest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.qualitytest.IQualityInspectionSheetExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionSheetVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityPassParameterVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionSheetService;
/**
 * 质检抽单
 * @author YM10180
 *
 */
@Service
public class QualityInspectionSheetExecuter implements IQualityInspectionSheetExecuter{

	public Logger logger = LoggerFactory.getLogger(QualityInspectionSheetExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	
	@Autowired
	private BMSQualityInspectionSheetService bMSQualityInspectionSheetService;
	
	@Override
	public ResQualityInspectionSheetVO getPassOrRefuse(ReqQualityInspectionSheetVO reqQualityInspectionSheetVO) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(reqQualityInspectionSheetVO));
		ResQualityInspectionSheetVO resQualityInspectionSheetVO = new ResQualityInspectionSheetVO();
		
		if (reqQualityInspectionSheetVO != null) {
			if (StringUtils.isEmpty(reqQualityInspectionSheetVO.getSysCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(reqQualityInspectionSheetVO.getSysCode())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
				}
			}
			if(null==reqQualityInspectionSheetVO.getUserCode()||reqQualityInspectionSheetVO.getUserCode().size()==0){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"userCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			if(null==reqQualityInspectionSheetVO.getCurttenDate()){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"curttenDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
//			if(null==reqQualityInspectionSheetVO.getHistoryPassCount()){
//				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
//						"historyPassCount" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
//			}
//			if(null==reqQualityInspectionSheetVO.getHistoryRejectCount()){
//				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
//						"historyRejectCount" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
//			}
//			if(null==reqQualityInspectionSheetVO.getHistoryPassScaleCount()){
//				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
//						"historyPassScaleCount" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
//			}
//			if(null==reqQualityInspectionSheetVO.getHistoryRejectScaleCount()){
//				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
//						"historyRejectScaleCount" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
//			}
			try {
				//通过件
				ResQualityInspectionSheetVO vosPass=bMSQualityInspectionSheetService.getPass(reqQualityInspectionSheetVO);
				//拒绝件
				ResQualityInspectionSheetVO vosReject=bMSQualityInspectionSheetService.getReject(reqQualityInspectionSheetVO);
				Integer countZj=bMSQualityInspectionSheetService.getCountZj(reqQualityInspectionSheetVO);
				logger.info("质检查询初审拒绝/终审通过总数+++++++++++++++++++"+countZj);
//				vosPass.getList().addAll(vosReject.getList());
//				resQualityInspectionSheetVO.setList(vosPass.getList());
				resQualityInspectionSheetVO.setPassList(vosPass.getPassList());
				resQualityInspectionSheetVO.setRejectList(vosReject.getRejectList());
				resQualityInspectionSheetVO.setCountZsJj(countZj);
			} catch (Exception e) {
				logger.info("质检查询初审拒绝/终审通过数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqQualityInspectionSheetVO" });
		}
		return resQualityInspectionSheetVO;
	}
	
	

	@Override
	public Response<ResQualityInspectionSheetResultVO> findByLoanNo(ReqLoanNumberVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		Response<ResQualityInspectionSheetResultVO> resLoanNumberVO=new Response<ResQualityInspectionSheetResultVO>();
		if (vo != null) {
			if (StringUtils.isEmpty(vo.getSysCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(vo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resLoanNumberVO);
				}
			}
			if(StringUtils.isEmpty(vo.getLoanNo())){
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
			}
			try {
				ResQualityInspectionSheetResultVO NumberVO=bMSQualityInspectionSheetService.findByLoanNo(vo);
				resLoanNumberVO.setData(NumberVO);
			} catch (Exception e) {
				logger.info("质检根据借款编号查询申请件数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "ReqLoanNumberVO" });
		}
		return resLoanNumberVO;
	}
	
	
	@Override
	public ResQualityInspectionSheetVO getHandPass(ReqQualityPassParameterVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		ResQualityInspectionSheetVO resQualityInspectionSheetVO = new ResQualityInspectionSheetVO();
		if (vo != null) {
			if (StringUtils.isEmpty(vo.getSysCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(vo.getSysCode())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
				}
			}
			if(null==vo.getUserCode()||vo.getUserCode().size()==0){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"userCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			if(null==vo.getStartDate()){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"startDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			if(null==vo.getEndDate()){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"endDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			
			try {
				//通过件
				ResQualityInspectionSheetVO vosPass=bMSQualityInspectionSheetService.getHandPass(vo);
				resQualityInspectionSheetVO.setPassList(vosPass.getPassList());
			} catch (Exception e) {
				logger.info("质检(手动抽单)查询终审通过数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqQualityPassParameterVO" });
		}
		return resQualityInspectionSheetVO;
	}
	
	
	@Override
	public ResQualityInspectionSheetVO getHandReject(ReqQualityPassParameterVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		ResQualityInspectionSheetVO resQualityInspectionSheetVO = new ResQualityInspectionSheetVO();
		if (vo != null) {
			if (StringUtils.isEmpty(vo.getSysCode())) {
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			} else {
				if (!EnumConstants.AMS_SYSTEM_CODE.equals(vo.getSysCode())) {
					return resAudiInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
				}
			}
			if(null==vo.getUserCode()||vo.getUserCode().size()==0){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"userCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			if(null==vo.getStartDate()){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"startDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			if(null==vo.getEndDate()){
				return resAudiInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"endDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resQualityInspectionSheetVO);
			}
			
			try {
				//拒绝件
				ResQualityInspectionSheetVO vosPass=bMSQualityInspectionSheetService.getHandReject(vo);
				resQualityInspectionSheetVO.setRejectList(vosPass.getRejectList());
				
			} catch (Exception e) {
				logger.info("质检(手动抽单)查询初审拒绝数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqQualityPassParameterVO" });
		}
		return resQualityInspectionSheetVO;
		
	}
	
	
	
	@Override
	public Response<List<ResQualityInspectionSheetResultVO>> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vos));
		Response<List<ResQualityInspectionSheetResultVO>> resLoanNumberVO=new Response<List<ResQualityInspectionSheetResultVO>>();
		if (vos != null) {
			for(ReqLoanNumberVO vo:vos){
				if (StringUtils.isEmpty(vo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
				} else {
					if (!EnumConstants.AMS_SYSTEM_CODE.equals(vo.getSysCode())) {
						return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resLoanNumberVO);
					}
				}
				if(StringUtils.isEmpty(vo.getLoanNo())){
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"loanNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
				}
				if(StringUtils.isEmpty(vo.getName())){
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"name" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
				}
				if(StringUtils.isEmpty(vo.getIdNo())){
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"idNo" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanNumberVO);
				}
			}
			try {
				List<ResQualityInspectionSheetResultVO> NumberVOs=bMSQualityInspectionSheetService.findByLoanNoAndNameAndIdNos(vos);
				resLoanNumberVO.setData(NumberVOs);
			} catch (Exception e) {
				logger.info("质检根据借款编号查询申请件数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "List<ReqLoanNumberVO>" });
		}
		return resLoanNumberVO;
	}


	@Override
	public Response<List<String>> findCodeByDates(ReqQualityPassParameterVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		Response<List<String>> responseString=new Response<List<String>>();
		if (vo != null) {
				if (StringUtils.isEmpty(vo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), responseString);
				} else {
					if (!EnumConstants.AMS_SYSTEM_CODE.equals(vo.getSysCode())) {
						return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
							"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), responseString);
					}
				}
				if(StringUtils.isEmpty(vo.getStartDate())){
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"startDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), responseString);
				}
				if(StringUtils.isEmpty(vo.getEndDate())){
					return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
							"endDate" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), responseString);
				}
			
			try {
				List<String> listString=bMSQualityInspectionSheetService.findCodeByDates(vo);
				responseString.setData(listString);
			} catch (Exception e) {
				logger.info("质检根据借款编号查询申请件数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqQualityPassParameterVO" });
		}
		return responseString;
	}



	
	
	public static ResQualityInspectionSheetVO resAudiInfo(String errCode, String errMsg, ResQualityInspectionSheetVO resQualityInspectionSheetVO) {
		resQualityInspectionSheetVO.setRepCode(errCode);
		resQualityInspectionSheetVO.setRepMsg(errMsg);
		return resQualityInspectionSheetVO;
	}
	
	public static <T> Response<T> resAudiInfo2(String errCode, String errMsg, Response<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}


	
	
}
