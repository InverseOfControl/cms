package com.ymkj.cms.biz.service.sign;

import java.util.Map;

import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

/**
 * 合同签约相关通知处理
 * @author 李璇
 * @date 2017年3月15日 上午11:39:13
 */
public interface IAiTeLoanContractNoticeService {
	/**
	 * 满标通知
	 * @param loanApp
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日上午11:33:51
	 */
	Map<String, Object> bidSuccessNotice(BMSLoanBaseEntity loanBaseEntity);

	/**
	 * 标的放款通知
	 * @param loanBaseEntity
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日上午11:33:41
	 */
	Map<String, Object> targetLoan(BMSLoanBaseEntity loanBaseEntity);

	/**
	 * 流标通知
	 * @param loanBaseEntity
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午2:02:07
	 */
	Map<String, Object> bidFailureNotice(BMSLoanBaseEntity loanBaseEntity);

}
