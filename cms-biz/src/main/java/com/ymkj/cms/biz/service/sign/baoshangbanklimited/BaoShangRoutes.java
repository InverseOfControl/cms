package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractSignImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 签约 包银渠道业务处理路由
 * 
 * @author YM10138
 *
 */
@Component
public class BaoShangRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(BaoShangRoutes.class);

	@Override
	public void config() {
		log.info("------签约 渠道-包银 路由注册------");
		// 设置渠道
		setChannelCd(EnumConstants.channelCode.BAOSHANG.getValue());
		/**
		 * 包商银行渠道审核通过与否查询
		 */
		add("BY_CHANNEL_AUDIT_RESULT", BaoShangChannelAuditResultImpl.class);
		/**
		 * 保存合同签约信息
		 */
		add(EnumConstants.Sign.SAVE.getValue(), SaveLoanContractSignImpl.class);
		/**
		 * 保存合同银行信息
		 */
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), SaveLoanContractBankAccImpl.class);
		/**
		 * 黑名单校验-包银
		 */
		add("SILVER_LIST_CHECK", BaoShangSilverListCheckImpl.class);
		/**
		 * 获取短信验证码-包银
		 */
		add("GET_SMS_CODE", BaoShangGetVerificationCodeImpl.class);
		/**
		 * 银行卡鉴权-包银
		 */
		add("AUTHENTICATION_CHECK", BaoShangAuthenticationCheckImpl.class);
		/**
		 * 风控审核传参数-包银
		 */
		add("AIR_CONTROL_CHECK", BaoShangAirControlCheckImpl.class);
		/**
		 * 风控审核结果查询-包银
		 */
		add("AIR_CONTROL_CHECK_RESULT",BaoShangAirControlCheckResultImpl.class);
		/**
		 * 上一步
		 */
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(),BaoShangRetrunLastStepImpl.class);
		/**
		 *生成合同
		 */
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(),CompareRepaymentContractImpl.class);
		/**
		 *合同签订
		 */
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(),BaoShangSignContractImpl.class);
		/**
		 *合同确认---推送影像资料
		 */
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), PatchPushContract.class);
		/**
		 *合同确认退回 
		 */
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(),BaoShangBackConfirmImpl.class);
		/**
		 * 银行卡鉴权、风控审核、生成合同、合同签订 拒绝
		 */
		add(EnumConstants.Sign.CONTRACT_REFUSE_LOAN.getValue(),BaoShangRefuseLoanImpl.class);
		/**
		 * 银行卡鉴权、风控审核、生成合同、合同签订 取消
		 */
		add(EnumConstants.Sign.CONTRACT_CANCEL_LOAN.getValue(),BaoShangCancelLoanImpl.class);
		log.info("------签约 渠道-包银 路由注册------/");
	}
}
