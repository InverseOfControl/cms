package com.ymkj.cms.biz.facade.sign.aite;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractFileExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.common.util.ValidationUtils;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractFileService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

@Service
public class AiTeLoanContractFileExecuter implements IAiTeLoanContractFileExecuter {

	@Autowired
	private IAiTeLoanContractFileService aiTeLoanContractFileService;
	
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	
	// 网关密钥
	@Value("#{env['picSecretKey']?:''}")
	private String picSecretKey;
	
	@Override
	public Response<RequestVo> uploadFiles(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<RequestVo> res = new Response<RequestVo>();
		// 参数校验
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			if(requestVo.getFiles() == null || requestVo.getFiles().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "files" });
			}
			//编号解析
			requestVo.setBorrowNo(ValidationUtils.analysisAitePushLoanNo(requestVo.getBorrowNo()));
			//常规校验
			BMSLoanBaseEntity loanBaseEntity = loanSignDataOprateService.aiTeConventionCheck(requestVo);
			
			//满标之后的文件上传
			retMap = aiTeLoanContractFileService.uploadFile(requestVo);;
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			retMap.put("message", e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(requestVo);
		return res;
	}

	@Override
	public Response<RequestVo> deleteFile(RequestVo requestVo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Response<RequestVo> res = new Response<RequestVo>();
		// 参数校验
		try {
			if(requestVo.getBorrowNo() == null || requestVo.getBorrowNo().length() == 0  ){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "borrowNo" });
			}
			//编号解析
			requestVo.setBorrowNo(ValidationUtils.analysisAitePushLoanNo(requestVo.getBorrowNo()));
			//常规校验
			BMSLoanBaseEntity loanBaseEntity = loanSignDataOprateService.aiTeConventionCheck(requestVo);
			//满标之后的文件上传
			retMap = aiTeLoanContractFileService.deleteFile(requestVo);
		} catch (BizException e) {
			retMap.put("code", e.getRealCode());
			String message = "";
			if (e.getArguments() != null && e.getArguments().length > 0) {
				for (int i = 0; i < e.getArguments().length; i++) {
					message += e.getArguments()[i];
				}
			}
			retMap.put("message", e.getErrorMsg() +":"+ message);
		} catch (Exception e) {
			// e.printStackTrace();
			retMap.put("code", "300002");
			retMap.put("message", e.getLocalizedMessage());
		}
		res.setRepMsg((String) retMap.get("message"));
		res.setRepCode((String) retMap.get("code"));
		res.setData(requestVo);
		return res;
	}

}
