package com.ymkj.cms.biz.service.channel.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.constant.LinePaymentConst;
import com.ymkj.cms.biz.api.enums.EnumLinePayment;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOffeDao;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOfferBatchDao;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOfferTransactionDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferBatch;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferTransaction;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.IBMSLinePaymentService;
import com.ymkj.cms.biz.service.channel.IBMSSequenceService;
import com.ymkj.cms.biz.service.finance.ILoanService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作接口实现
 */
@Repository
public class BMSLinePaymentServiceImpl implements IBMSLinePaymentService {

	@Autowired
	private ILinePaymentOfferBatchDao linePaymentOfferBatchDao;

	@Autowired
	private ILinePaymentOffeDao linePaymentOfferDao;

	@Autowired
	private ILinePaymentOfferTransactionDao linePaymentOfferTranscationDao;

	@Autowired
	private IBMSSequenceService bmsSequenceService;

	@Autowired
	private ILoanService loanService;
	
	@Autowired
	private APPPersonInfoDao aPPPersonInfoDao;

	@Autowired
	private ICoreHttpService icoreHttpService;
	
	
	@Override
	public List<LinePaymentOfferBatch> findHaTwoOfferBatch(Map<String,Object> map) {
		return linePaymentOfferBatchDao.findHaTwoOfferBatchNotExport(map);
	}
	@Transactional
	@Override
	public void createOffer(Map<String, Object> map) {
		List<LinePaymentOfferBatch> haTwoOfferBatchs = findHaTwoOfferBatch(map);
		if (haTwoOfferBatchs != null && haTwoOfferBatchs.size() > 0) {
			throw new BizException(BizErrorCode.OFFER_OPERATE, "不能生成报盘文件，因存在未导出的报盘文件!");
		}
		List<LoanBaseEntity> loanBaseList = linePaymentOfferBatchDao.getAllLoanBaseInfo(map);
		for (LoanBaseEntity loanBase : loanBaseList) {
			// 如果存在回盘失败的报盘，则只需更新报盘状态和报盘时间，不需再生成报盘信息
			String loanNo = loanBase.getLoanNo();
			// 根据债权编号和财务类型查询报盘信息
			LinePaymentOffer resultOffer = this.queryOfferByLoanId(loanNo, EnumLinePayment.放款本金);
			// 如果已存在报盘信息，则不需再生成报盘信息；如果报盘状态是扣款失败、则需要更新报盘状态
			if (null != resultOffer) {
				if (EnumLinePayment.扣款失败.getValue().equals(resultOffer.getState())) {
					resultOffer.setState(EnumLinePayment.未报盘.getValue());
					// 编辑报盘信息
					this.editOfferInfo(loanBase, resultOffer);
					// 更新报盘信息
					linePaymentOfferDao.update(resultOffer);
				}
				continue;
			}
			// 编辑放款本金的报盘信息
			LinePaymentOffer offerInstance = new LinePaymentOffer();
			// 编辑报盘信息
			this.editOfferInfo(loanBase, offerInstance);
			// 货币类型
			offerInstance.setCurrencyType(EnumLinePayment.CNY.getValue());
			// 账户类型
			offerInstance.setAccountType(LinePaymentConst.ACCOUNT_TYPE);
			// 债权id
			offerInstance.setLoanNo(loanNo);
			// 财务类型
			offerInstance.setFinancialType(EnumLinePayment.放款本金.getValue());
			// 报盘时间
			offerInstance.setOfferTime(new Date());
			// 备注（手续费）
			offerInstance.setRemark(EnumLinePayment.放款本金.name());
			// 报盘状态
			offerInstance.setState(EnumLinePayment.未报盘.getValue());
			linePaymentOfferDao.insert(offerInstance);
		}
		createOfferBatch(map);

	}

	@Override
	public void createOfferBatch(Map<String, Object> map) {
		String fundsSource = map.get("fundsSource") == null ? null : map.get("fundsSource").toString();
		// 根据合同来源查询报盘状态为未报盘和扣款失败的放款本金报盘信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("financialType", EnumLinePayment.放款本金.getValue());
		params.put("state", new String[] { EnumLinePayment.未报盘.getValue(), EnumLinePayment.扣款失败.getValue() });
		params.put("fundsSource", fundsSource);
		List<LinePaymentOffer> fkbjOfferList = linePaymentOfferDao.findNoOfferOrFail(params);
		if (fkbjOfferList == null || fkbjOfferList.size() <= 0) {
			throw new BizException(BizErrorCode.OFFER_OPERATE, "报盘文件不存在，不能生成批次信息!");
		}
		// 生成报盘批次
		LinePaymentOfferBatch haTwoOfferBatch = new LinePaymentOfferBatch();
		// 交易标志（代付）
		haTwoOfferBatch.setTradeMark(LinePaymentConst.TRADE_MARK_F);
		// 查询配置的商户ID
		String merchantId = null;
		if (EnumLinePayment.张三测试.getCode().equals(fundsSource)) {
			// 渤海2的商户号
			merchantId = "200290000016506";
		}else if(EnumLinePayment.国民信托.getCode().equals(fundsSource)){
			merchantId = LinePaymentConst.GMXT_MERCHANTID;
		}
		if (StringUtils.isEmpty(merchantId)) {
			throw new BizException(BizErrorCode.OFFER_OPERATE, "商户ID不能为空！");
		}
		// 商户ID
		haTwoOfferBatch.setMerchantId(merchantId);
		// 提交日期
		haTwoOfferBatch.setCommitTime(new Date());
		// 报盘文件记录数
		haTwoOfferBatch.setRecordsTotal(fkbjOfferList.size());
		// 业务类型
		haTwoOfferBatch.setBusinessType(LinePaymentConst.BUSINESS_TYPE);
		// 创建时间
		haTwoOfferBatch.setCreateTime(new Date());
		// 版本号
		haTwoOfferBatch.setVersion(LinePaymentConst.VERSION);
		// 是否已导出报盘文件（t：是，f：否）
		haTwoOfferBatch.setExportFile(LinePaymentConst.EXPORT_FILE_F);
		String date = DateUtil.getDate(new Date(), DateUtil.pattern_day);
		// 创建当日批次号(修改)
		int sequenceId = bmsSequenceService.queryBatchSequence("10002");
		String dayBatchNumber = StringUtils.leftPad(String.valueOf(sequenceId), 5, "0");
		haTwoOfferBatch.setDayBatchNumber(dayBatchNumber);
		// 文件名
		StringBuilder fileName = new StringBuilder();
		fileName.append(merchantId);
		fileName.append("_");
		fileName.append(LinePaymentConst.TRADE_MARK_F);
		fileName.append(LinePaymentConst.VERSION);
		fileName.append(date);
		fileName.append("_");
		fileName.append(dayBatchNumber);
		haTwoOfferBatch.setFileName(fileName.toString());
		// 报盘总金额
		BigDecimal totalAmount = new BigDecimal(0.00);
		// 设置报盘总金额
		for (LinePaymentOffer offerInfo : fkbjOfferList) {
			// 报盘金额累加
			totalAmount = totalAmount.add(offerInfo.getAmount());
		}
		// 批次信息记录报盘总金额
		haTwoOfferBatch.setAmountTotal(totalAmount);
		// 记录批次信息
		linePaymentOfferBatchDao.insert(haTwoOfferBatch);
		createOfferTransaction(fundsSource, fkbjOfferList, haTwoOfferBatch.getId(), dayBatchNumber);
	}

	@Override
	public void createOfferTransaction(String fundsSource, List<LinePaymentOffer> offerList, long offerBatchId, String dayBatchNumber) {
		for (int i = 0; i < offerList.size(); i++) {
			LinePaymentOffer haTwoOffer = offerList.get(i);
			LinePaymentOfferTransaction transactionInstance = new LinePaymentOfferTransaction();
			// 报盘id
			long offerId = haTwoOffer.getId();
			transactionInstance.setOfferId(offerId);
			// 记录序号
			String recordNumber = this.updateRecordNumber(i + 1);
			// 流水号：报盘类型（放款本金：'FKBJ'，手续费：'SXF'）+ 提交日期YYYYMMDD + 当日批次号 + 记录序号
			String flowNumber = haTwoOffer.getFinancialType() + DateUtil.getDate(new Date(), DateUtil.pattern_day) + dayBatchNumber + recordNumber;
			transactionInstance.setFlowNumber(flowNumber);
			// 批次ID
			transactionInstance.setBatchId(offerBatchId);
			// 记录序号
			transactionInstance.setRecordNumber(recordNumber);
			// 报盘状态
			transactionInstance.setState(EnumLinePayment.未报盘.getValue());
			// 记录报盘流水信息
			linePaymentOfferTranscationDao.insert(transactionInstance);
		}
	}

	/**
	 * 根据债权编号和财务类型查询报盘信息
	 * 
	 * @param loanId
	 * @param financialType
	 * @return
	 */
	private LinePaymentOffer queryOfferByLoanId(String loanNo, EnumLinePayment financialType) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("loanId", loanNo);
		paraMap.put("financialType", financialType.getValue());
		List<LinePaymentOffer> offerList = linePaymentOfferBatchDao.findListByVo(paraMap);
		if (offerList != null && offerList.size() > 0) {
			return offerList.get(0);
		}
		return null;
	}

	private void editOfferInfo(LoanBaseEntity loanBase, LinePaymentOffer offerInstance) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("loanNo", loanBase.getLoanNo());
		Map<String, String> dataMap = linePaymentOfferBatchDao.findLoanRelInfo(paraMap);
		// 银行代码
		offerInstance.setBankCode(dataMap.get("bankCode"));
		// 账号类型
		offerInstance.setAccountNumberType(LinePaymentConst.ACCOUNT_NUMBER_TYPE);
		// 银行账号
		offerInstance.setAccountNumber(dataMap.get("bankAccount"));
		// 开户行名称
		offerInstance.setBankName(dataMap.get("bankName"));
		// 银行卡号上的姓名
		offerInstance.setAccountName(dataMap.get("userName"));
		// 金额 = 审批金额
		offerInstance.setAmount(new BigDecimal(String.valueOf(dataMap.get("accLmt"))));
	}

	@Override
	public Response<Map<String, Object>> queryExpOffer(ReqUnderLinePaymentVo requestVo) {
		Response<Map<String, Object>> response = new Response<Map<String, Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 合同来源
		String fundsSource = requestVo.getFundsSource();
		if (StringUtils.isEmpty(fundsSource)) {
			throw new BizException(BizErrorCode.OFFER_OPERATE, "导出报盘文件时必须选择一个渠道!");
		}
		// 查询待导出的批次信息
		paraMap.put("fundsSource", requestVo.getFundsSource());
		List<LinePaymentOfferBatch> offerBatchList = linePaymentOfferBatchDao.findHaTwoOfferBatchNotExport(paraMap);
		if (offerBatchList == null || offerBatchList.size() <= 0) {
			throw new BizException(BizErrorCode.OFFER_OPERATE, "【" + fundsSource + "】渠道下没有待导出的报盘信息！");
		}
		LinePaymentOfferBatch batchInfo = offerBatchList.get(0);
		long batchId = batchInfo.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", batchId);
		map.put("state", EnumLinePayment.未报盘.getValue());
		// 批次号
		String batchNum = requestVo.getBatchNum();
		if (StringUtils.isNotEmpty(batchNum)) {
			map.put("batchNum", batchNum);
		}
		map.put("fundsSource", fundsSource);
		// 根据批次号相关条件查询报盘流水表信息
		List<LinePaymentOfferTransaction> haTwoOfferTransactions = linePaymentOfferTranscationDao.findOfferTransactionListByMap(map);
		if (haTwoOfferTransactions == null || haTwoOfferTransactions.size() <= 0) {
			String message = "【" + fundsSource + "】渠道下没有待导出的报盘流水信息！";
			if (StringUtils.isNotEmpty(batchNum)) {
				message = message + "债权批次号：" + batchNum;
			}
			throw new BizException(BizErrorCode.OFFER_OPERATE, message);
		}
		// 导出总金额
		BigDecimal totalAmount = new BigDecimal(0);
		// 导出总件数
		int totalCount = haTwoOfferTransactions.size();
		// 报盘信息
		List<LinePaymentOffer> thirdLineOffers = new ArrayList<LinePaymentOffer>();
		for (int i = 0; i < totalCount; i++) {
			LinePaymentOfferTransaction transaction = haTwoOfferTransactions.get(i);
			long offerId = transaction.getOfferId();
			// 更新报盘表的状态为 已报盘
			LinePaymentOffer offer = new LinePaymentOffer();
			offer.setId(offerId);
			offer.setState(EnumLinePayment.已报盘.getValue());
			linePaymentOfferDao.update(offer);

			// 更新报盘流水表状态为已报盘
			LinePaymentOfferTransaction thirdLineOfferTransaction = new LinePaymentOfferTransaction();
			thirdLineOfferTransaction.setId(transaction.getId());
			thirdLineOfferTransaction.setSendTime(new Date());
			thirdLineOfferTransaction.setState(EnumLinePayment.已报盘.getValue());
			linePaymentOfferTranscationDao.update(thirdLineOfferTransaction);
			// 查询报盘信息
			LinePaymentOffer thirdLineOffer = linePaymentOfferDao.findById(offerId);
			// 金额以分为最小单位
			thirdLineOffer.setAmount(thirdLineOffer.getAmount().multiply(new BigDecimal(100)));
			// 金额累加、最小单位是分
			totalAmount = totalAmount.add(thirdLineOffer.getAmount());
			// 设置记录号
			thirdLineOffer.setRecordNumber(this.updateRecordNumber((i + 1)));
			// 备注设置流水号
			thirdLineOffer.setRemark(transaction.getFlowNumber());
			thirdLineOffers.add(thirdLineOffer);
		}
		// 获取当日最大的日批次号
		int sequenceId = bmsSequenceService.queryBatchSequence("10002");
		String batchNumber = StringUtils.leftPad(String.valueOf(sequenceId), 5, "0");
		batchInfo.setDayBatchNumber(batchNumber);
		// 如果批次下所有的报盘数据都已经导出，则更新导出状态
		if (!this.isExistNoExportOffer(batchId)) {
			batchInfo.setExportFile("t");
		}
		// 更新批次为已导出报盘
		linePaymentOfferBatchDao.update(batchInfo);
		List<LinePaymentOfferBatch> batchList = this.editBatchList(totalCount, totalAmount, batchInfo.getMerchantId());
		dataMap.put("summaryData", batchList);
		dataMap.put("data", thirdLineOffers);
		dataMap.put("merchantId", batchInfo.getMerchantId());
		dataMap.put("batchNum", batchInfo.getDayBatchNumber());
		response.setData(dataMap);
		return response;
	}

	/**
	 * 记录序号 同一债权，从000001开始累加
	 * 
	 * @param paramMap
	 */
	private String updateRecordNumber(int index) {
		return StringUtils.leftPad(String.valueOf(index), 6, "0");
	}

	/**
	 * 判断指定批次下是否存在未导出的报盘数据
	 * 
	 * @param batchId
	 * @return
	 */
	private boolean isExistNoExportOffer(long batchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", batchId);
		map.put("state", EnumLinePayment.未报盘.getValue());
		// 根据批次号相关条件查询报盘流水表信息
		List<LinePaymentOfferTransaction> haTwoOfferTransactions = linePaymentOfferTranscationDao.findOfferTransactionListByMap(map);
		if (haTwoOfferTransactions != null && haTwoOfferTransactions.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 编辑报盘文件头部汇总信息
	 * 
	 * @param totalCount
	 * @param totalAmount
	 * @param merchantId
	 * @return
	 */
	private List<LinePaymentOfferBatch> editBatchList(int totalCount, BigDecimal totalAmount, String merchantId) {
		List<LinePaymentOfferBatch> offerBatchList = new ArrayList<LinePaymentOfferBatch>();
		LinePaymentOfferBatch offerBatch = new LinePaymentOfferBatch();
		offerBatch.setTradeMark(LinePaymentConst.TRADE_MARK_F);
		offerBatch.setMerchantId(merchantId);
		offerBatch.setSubmitDate(DateUtil.getDate(new Date(), DateUtil.pattern_day));
		offerBatch.setRecordsTotal(totalCount);
		offerBatch.setAmountTotal(totalAmount);
		offerBatch.setBusinessType(LinePaymentConst.BUSINESS_TYPE);
		offerBatchList.add(offerBatch);
		return offerBatchList;
	}

	/**
	 * 判断某一笔债权的放款本金和手续费是否都回盘成功
	 * 
	 * @param loanId
	 * @return
	 */
	private boolean isSuccessDebit(String loanNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanId", loanNo);
		paramMap.put("state", EnumLinePayment.扣款成功.getValue());
		paramMap.put("financialType", EnumLinePayment.放款本金.getValue());
		List<LinePaymentOffer> offerList = linePaymentOfferDao.listBy(paramMap);
		if (offerList == null || offerList.size() <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void updateHaTwoOffer(ReqUnderLinePaymentVo requestVo) {
		List<Map<String, String>> dataList = requestVo.getDatas();
		ReqLoanVo reqLoanVo = new ReqLoanVo();
		reqLoanVo.setServiceCode(requestVo.getServiceCode());
		reqLoanVo.setServiceName(requestVo.getServiceName());
		for (Map<String, String> map : dataList) {
			// 备注
			String remark = map.get("remark");
			// 反馈码
			String feedbackCode = map.get("feedbackCode");
			// 原因
			String reason = map.get("reason");

			LinePaymentOffer haTwoOffer = new LinePaymentOffer();
			LinePaymentOfferTransaction haTwoOfferTransaction = new LinePaymentOfferTransaction();
			if (StringUtils.isEmpty(remark) || StringUtils.isEmpty(feedbackCode)) {
				throw new BizException(BizErrorCode.OFFER_OPERATE, "备注或反馈码为空!");
			}
			// 根据流水号和报盘状态（必须是：已报盘）查询报盘流水信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("flowNumber", remark);
			paramMap.put("state", EnumLinePayment.已报盘.getValue());
			haTwoOfferTransaction = linePaymentOfferTranscationDao.findHaTwoOfferTransactionByMap(paramMap);
			if (null == haTwoOfferTransaction) {
				throw new BizException(BizErrorCode.OFFER_OPERATE, "查询不到待更新的报盘流水信息，请检查回盘文件是否有误！");
			}

			String state = EnumLinePayment.扣款失败.getValue();
			if (LinePaymentConst.SUCCESS_BACK_CODE.equals(feedbackCode)) {
				state = EnumLinePayment.扣款成功.getValue();
			}
			// 更变报盘状态表信息
			haTwoOfferTransaction.setState(state);
			// 设置反馈码
			haTwoOfferTransaction.setFeedbackCode(feedbackCode);
			// 设置反馈原因
			haTwoOfferTransaction.setReason(reason);
			// 设置回盘时间
			haTwoOfferTransaction.setReturnTime(new Date());
			// 更新报盘流水信息
			linePaymentOfferTranscationDao.update(haTwoOfferTransaction);

			// 查询报盘信息
			haTwoOffer = linePaymentOfferDao.findById(haTwoOfferTransaction.getOfferId());
			if (null == haTwoOffer) {
				throw new BizException(BizErrorCode.OFFER_OPERATE, "查询不到待更新的报盘信息，请检查回盘文件是否有误！");
			}
			if (!EnumLinePayment.已报盘.getValue().equals(haTwoOffer.getState())) {
				throw new BizException(BizErrorCode.OFFER_OPERATE, "报盘状态不是已报盘，不能更新报盘信息！");
			}
			// 更新报盘信息
			haTwoOffer.setState(state);
			linePaymentOfferDao.update(haTwoOffer);
			// 债权编号
			String loanNo = haTwoOffer.getLoanNo();
			reqLoanVo.setLoanNo(loanNo);
			// 判断某一笔债权的放款本金和手续费是否均回盘成功，成功则调用放款接口
			if (this.isSuccessDebit(loanNo)) {
				loanService.grantLoan(reqLoanVo);
			}
		}
	}


}
