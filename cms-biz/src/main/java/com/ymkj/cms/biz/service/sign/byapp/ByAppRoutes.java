package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;

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
public class ByAppRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(ByAppRoutes.class);

	@Override
	public void config() {
		log.debug("------签约 渠道-包银 路由注册------");
		// 设置渠道
		setChannelCd(EnumConstants.channelCode.BAOSHANG.getValue());
		/**
		 * 第一步保存签约信息-包银App改造
		 */
		add(EnumConstants.Sign.SAVE.getValue(), ByAppSaveLoanContractSignImpl.class,"2.0");
		/**
		 * 第二步保存银行卡信息-包银App改造
		 */
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), ByAppSaveLoanContractBankAccImpl.class,"2.0");
		/**
		 * 风控审核传参数-包银App改造
		 */
		add("AIR_CONTROL_CHECK", ByAppAirControlCheckImpl.class,"2.0");
		/**
		 * 预授信结果查询-包银App改造
		 */
		add("BY_APP_PRE_APPROVAL_RES", ByAppPreApprovalResImpl.class,"2.0");
		/**
		 * 生成合同-包银App改造
		 */
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(),ByAppCreateLoanContractImpl.class,"2.0");
		/**
		 * 合同签订-包银App改造
		 */
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(),ByAppSignLoanContractImpl.class,"2.0");
		/**
		 * 合同确认-包银App改造
		 */
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(),ByAppConfirmLoanContractImpl.class,"2.0");
		/**
		 * 合同确认退回-包银App改造
		 */
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(),ByAppBackConfirmImpl.class,"2.0");
		/**
		 * 拒绝-包银App改造
		 */
		add(EnumConstants.Sign.CONTRACT_REFUSE_LOAN.getValue(),ByAppRefuseLoanImpl.class,"2.0");
		/**
		 * 取消-包银App改造
		 */
		add(EnumConstants.Sign.CONTRACT_CANCEL_LOAN.getValue(),ByAppCancelLoanImpl.class,"2.0");
		/**
		 * 包银app改造上一步之前需要判断第二次审核状态-包银App改造
		 */
		add("BY_APP_AUDIT_STATUS",ByAppAuditStatusImpl.class,"2.0");
		/**
		 * 上一步-包银App改造
		 */
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(),ByAppReturnLastStepImpl.class,"2.0");

		log.debug("------签约 渠道-包银 路由注册------/");
	}
}
