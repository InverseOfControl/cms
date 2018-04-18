package com.ymkj.cms.biz.facade.apply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IBMSLoanUrgentExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.qualitytest.QualityInspectionSheetExecuter;
import com.ymkj.cms.biz.service.audit.BMSQualityInspectionSheetService;
import com.ymkj.cms.biz.service.master.IBMSLoanUrgentConfigServic;

@Service
public class BMSLoanUrgentExecuter implements IBMSLoanUrgentExecuter{

	
	public Logger logger = LoggerFactory.getLogger(QualityInspectionSheetExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	
	@Autowired
	private IBMSLoanUrgentConfigServic iBMSLoanUrgentConfigServic;
	
	@Override
	public Response<ResLoanUrgentVO> getLoanUrgentSize(ReqLoanUrgentVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		Response<ResLoanUrgentVO> resLoanUrgentRes=new Response<ResLoanUrgentVO>();
		if (vo != null) {
			if (StringUtils.isEmpty(vo.getSysCode())) {
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanUrgentRes);
			} else {
				if (!EnumConstants.CFS_SYSTEM_CODE.equals(vo.getSysCode())) {
					return resAudiInfo2(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), resLoanUrgentRes);
				}
			}
			if(null==vo.getOwningBranchId()||vo.getOwningBranchId()==0){
				return resAudiInfo2(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"owningBranchId" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), resLoanUrgentRes);
			}
			try {
				ResLoanUrgentVO resLoanUrgentVO=iBMSLoanUrgentConfigServic.getLoanUrgentSize(vo);
				resLoanUrgentRes.setData(resLoanUrgentVO);
			} catch (Exception e) {
				logger.info("质检根据借款编号查询申请件数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "ReqLoanUrgentVO" });
		}
		return resLoanUrgentRes;
	}

	
	
	
	public static <T> Response<T> resAudiInfo2(String errCode, String errMsg, Response<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}

}
