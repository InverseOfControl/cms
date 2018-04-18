package com.ymkj.cms.biz.facade.sign;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.sign.IBaoShangLoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.SignFactory;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.IBaoshangBankLimitedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）
 */
@Service
public class BaoShangLoanContractSignExecuter implements IBaoShangLoanContractSignExecuter {
	
	//pic接口地址
	@Value("#{env['patchBoltUrl']?:''}")
	private String patchBoltUrl;

	//系统名称
	@Value("#{env['sysName']?:''}")
	private String sysName;
	//业务环节
	@Value("#{env['nodeKey']?:''}")
	private String nodeKey;

	@Autowired
	private IBaoshangBankLimitedService baoshangRiskNotice;

	/**
	 * 包银渠道审核通过与否查询
	 * @param reqLoanContractSignVo
	 * @return
	 */
    @Override
    public Response<ResLoanContractSignVo> ByChannelAuditResult(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"BY_CHANNEL_AUDIT_RESULT");
    }

    /**
	 * 获取包银渠道短信验证码
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> getVerificationCode(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"GET_SMS_CODE");
	}

	/**
	 * 黑名单校验-包银渠道
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> silverListCheck(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"SILVER_LIST_CHECK");
	}

	/**
	 * 银行卡鉴权-包银渠道
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> authenticationCheck(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"AUTHENTICATION_CHECK");
	}

	/**
	 * 风控审核-包银渠道
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> airControlCheck(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"AIR_CONTROL_CHECK");
	}

	/**
	 * 风控审核结果查询-包银渠道
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> airControlCheckResult(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"AIR_CONTROL_CHECK_RESULT");
	}

	/**
	 * 风控审核通知
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> riskManagementNotice(ReqLoanContractSignVo reqLoanContractSignVo) {
		if(reqLoanContractSignVo.getBusNumber()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"busNumber"});
		}
		if(reqLoanContractSignVo.getRespcd()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"respcd"});
		}
		if(reqLoanContractSignVo.getResptx()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"resptx"});
		}
		Map<String, String> map = baoshangRiskNotice.confirmRiskManagementNotice(reqLoanContractSignVo.getBusNumber(), reqLoanContractSignVo.getRespcd(), reqLoanContractSignVo.getResptx(), reqLoanContractSignVo.getPatchType());
		Response<ResLoanContractSignVo> response = new Response<>();
		response.setRepCode(map.get("code"));
		response.setRepMsg(map.get("msg"));
		return  response;
	}

	/**
	 * 单据撤销通知
	 *
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> revokeNotice(ReqLoanContractSignVo reqLoanContractSignVo) {
		if(reqLoanContractSignVo.getBusNumber()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"busNumber"});
		}
		if(reqLoanContractSignVo.getRespcd()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"respcd"});
		}
		if(reqLoanContractSignVo.getResptx()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"resptx"});
		}
		Map<String, String> map = baoshangRiskNotice.confirmRevokeNotice(reqLoanContractSignVo.getBusNumber(), reqLoanContractSignVo.getRespcd(), reqLoanContractSignVo.getResptx());
		Response<ResLoanContractSignVo> response = new Response<>();
		response.setRepCode(map.get("code"));
		response.setRepMsg(map.get("msg"));
		return  response;
	}

	/**
	 * 授信成功后超时拒绝结果通知
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> timeOutRefuse(ReqLoanContractSignVo reqLoanContractSignVo) {
		if(reqLoanContractSignVo.getBusNumber()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"busNumber"});
		}
		if(reqLoanContractSignVo.getRespcd()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"respcd"});
		}
		if(reqLoanContractSignVo.getResptx()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"resptx"});
		}
		Map<String, String> map = baoshangRiskNotice.confirmTimeOutRefuse(reqLoanContractSignVo.getBusNumber(), reqLoanContractSignVo.getRespcd(), reqLoanContractSignVo.getResptx());
		Response<ResLoanContractSignVo> response = new Response<>();
		response.setRepCode(map.get("code"));
		response.setRepMsg(map.get("msg"));
		return  response;
	}

	@Override
	public Response<Object> loanRepeal(ReqLoanContractSignVo reqLoanContractSignVo){
		if(reqLoanContractSignVo.getBusNumber()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"busNumber"});
		}
		if(reqLoanContractSignVo.getIdNo()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"});
		}
		if(reqLoanContractSignVo.getCustName()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"custName"});
		}
		if(reqLoanContractSignVo.getIdType()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"idType"});
		}
		Map<String, String> map = baoshangRiskNotice.loanRepeal(reqLoanContractSignVo.getIdType(), reqLoanContractSignVo.getIdNo(), reqLoanContractSignVo.getCustName(),reqLoanContractSignVo.getBusNumber());
		Response<Object> response = new Response<>();
		response.setRepCode(map.get("code"));
		response.setRepMsg(map.get("msg"));
		response.setData(map);
		return  response;
	}

	/**
	 * 包银app改造上一步之前需要判断第二次审核状态
	 * @param reqLoanContractSignVo
	 * @return
	 */
	@Override
	public Response<ResLoanContractSignVo> byAppAuditStatus(ReqLoanContractSignVo reqLoanContractSignVo) {
		return SignFactory.execute(reqLoanContractSignVo, reqLoanContractSignVo.getChannelCd(),
				"BY_APP_AUDIT_STATUS");
	}
}
