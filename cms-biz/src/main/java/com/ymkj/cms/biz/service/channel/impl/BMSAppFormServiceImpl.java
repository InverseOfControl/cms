package com.ymkj.cms.biz.service.channel.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.constant.AppFormConst;
import com.ymkj.cms.biz.api.enums.EnumBH2Constants;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.biz.common.util.JschSftpUtils;
import com.ymkj.cms.biz.dao.channel.IBMSAppBookDao;
import com.ymkj.cms.biz.dao.channel.IBMSAppFormDao;
import com.ymkj.cms.biz.entity.channel.BMSAppForm;
import com.ymkj.cms.biz.entity.channel.BMSApplyBookInfo;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.BMSFileUpdAndDwnService;
import com.ymkj.cms.biz.service.channel.IBMSAppFormService;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理service impl
 */
@Service
public class BMSAppFormServiceImpl extends BaseServiceImpl<BMSAppForm> implements IBMSAppFormService {

	@Autowired
	private IBMSAppFormDao bmsAppFormDao;

	@Autowired
	private IBMSAppBookDao bmsAppBookDao;

	@Autowired
	private BMSFileUpdAndDwnService bmsFileUpdAndDwnService;

	@Override
	protected BaseDao<BMSAppForm> getDao() {
		return bmsAppFormDao;
	}

	@Override
	public ResAppBookVo findAppBook(Map<String, Object> paraMap) {
		return bmsAppFormDao.findAppBook(paraMap);
	}

	@Override
	public List<ResRepaymentExpVo> listLoanRepaymentExp(Map<String, Object> paraMap) {
		return bmsAppFormDao.listLoanRepaymentExp(paraMap);
	}

	@Override
	public List<LoanApplyDetailVo> listLoanAppDetailExp(Map<String, Object> paraMap) {
		return bmsAppFormDao.listLoanAppDetailExp(paraMap);
	}

	public String findRequestFileBatchNum(ReqFileBatchNumVo requestVo) {
		return bmsAppFormDao.findRequestFileBatchNum(requestVo);
	}

	@Override
	public long createApplyBookInfos(Map<String, Object> paraMap) {
		long backnum = 0;
		if (!isUploadApplyBook(String.valueOf(paraMap.get("batchNum")))) {
			String projectCode = getProjectCode(String.valueOf(paraMap.get("batchNum")));
			ResAppBookVo resAppBook = findAppBook(paraMap);
			String fundsSources = getFundsSources(projectCode);
			paraMap.put("fundsSources", fundsSources);
			// 申请笔数
			Integer quantity = Integer.parseInt(resAppBook.getQuantity());
			// 应放款金额
			BigDecimal money = BigDecimal.valueOf(Integer.parseInt(resAppBook.getQuantity()));
			// 调减金额
			BigDecimal diffMoney = new BigDecimal("0.00");
			// 是否是当天第一条申请书信息
			if (isCurrentDayFirstBarApplyBookInfo(paraMap)) {
				diffMoney = getDiffMoney(paraMap);
			}
			// 实际申请金额
			BigDecimal applyMoney = money.subtract(diffMoney);
			BMSApplyBookInfo applyBookInfo = new BMSApplyBookInfo();
			applyBookInfo.setFileName(String.valueOf(paraMap.get("fileBatchNum")));
			applyBookInfo.setBatchNum(String.valueOf(paraMap.get("batchNum")));
			applyBookInfo.setGrantMoney(money);
			applyBookInfo.setLoanTimes(quantity);
			applyBookInfo.setDiffMoney(diffMoney);
			applyBookInfo.setApplyMoney(applyMoney);
			backnum = insertApplyBookInfo(applyBookInfo);
		}else{
			backnum=1;
		}
		return backnum;
	}

	@Override
	public boolean isUploadApplyBook(String batchNum) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("batchNum", batchNum);
		BMSApplyBookInfo applyBookInfo = bmsAppBookDao.queryApplyBookInfoBybatchNum(paraMap);
		if (applyBookInfo == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCurrentDayDownloadFirst(Map<String, Object> paraMap) {
		paraMap.put("fileType", EnumBH2Constants.划拨申请书pdf.getCode());
		paraMap.put("operateType", EnumBH2Constants.downLoad.getValue());
		paraMap.put("createTime", new Date());
		List<RequestFileOperateRecord> recordList = bmsAppFormDao.queryCurrentDayRequestFileOperateRecordByAsc(paraMap);
		if (recordList == null || recordList.size() <= 0) {
			return true;
		}
		RequestFileOperateRecord firstRecord = recordList.get(0);
		if (!String.valueOf(paraMap.get("batchNum")).equals(firstRecord.getBatchNum())) {
			return false;
		}
		return true;
	}

	@Override
	public String getFundsSources(String projectCode) {
		if (AppFormConst.TRUST_PROJECT_CODE2.equals(projectCode)) {
			return EnumBH2Constants.渤海2.getCode();
		} else if (AppFormConst.TRUST_PROJECT_CODE.equals(projectCode)) {
			return EnumBH2Constants.渤海信托.getCode();
		} else if (AppFormConst.TRUST_PROJECT_CODE3.equals(projectCode)) {
			return EnumBH2Constants.华瑞渤海.getCode();
		} else {
			return null;
		}
	}

	@Override
	public String getProjectCode(String batchNum) {
		String org = batchNum.substring(0, 4);
		if (org.equals("BHXT")) {
			return AppFormConst.TRUST_PROJECT_CODE;
		} else if (org.equals("BH2-")) {
			return AppFormConst.TRUST_PROJECT_CODE2;
		} else if (org.equals("HRBH")) {
			return AppFormConst.TRUST_PROJECT_CODE3;
		} else {
			return null;
		}
	}

	@Override
	public boolean isCurrentDayFirstBarApplyBookInfo(Map<String, Object> paraMap) {
		int count = bmsAppBookDao.queryCurrentDayCount(paraMap);
		if (count > 0) {
			return false;
		}
		return true;
	}

	@Override
	public BigDecimal getDiffMoney(Map<String, Object> paraMap) {
		String previousBatchNum = this.getPreviousBatchNum(paraMap);
		if (previousBatchNum == "") {
			return new BigDecimal("0.00");
		}
		List<BMSApplyBookInfo> applyBookInfos = queryPreviousDayApplyBookInfo(paraMap);
		if (applyBookInfos == null || applyBookInfos.size() <= 0) {
			return new BigDecimal("0.00");
		}
		// 前一个批次所在当天的应放款总金额
		BigDecimal toTalGrantMoney = this.getToTalGrantMoney(applyBookInfos);
		List<String> previousBatchNums = this.getPreviousBatchNums(applyBookInfos);
		BigDecimal alreadyGrantMoney = bmsAppBookDao.queryAlreadyGrantMoney(previousBatchNums);
		BigDecimal diffMoney = toTalGrantMoney.subtract(alreadyGrantMoney);
		if (diffMoney.compareTo(new BigDecimal("0.00")) == -1) {
			return new BigDecimal("0.00");
		}
		return diffMoney;
	}

	public String getPreviousBatchNum(Map<String, Object> paraMap) {
		List<String> batchNums = bmsAppBookDao.findBatchNumByFundsSources(paraMap);
		int index = batchNums.indexOf(String.valueOf(paraMap.get("batchNum")));
		if (index == -1) {
			return "";
		}
		if (index == 0) {
			return "";
		}
		return batchNums.get(index - 1);
	}

	@Override
	public long insertApplyBookInfo(BMSApplyBookInfo applyBookInfo) {
		long backNum = bmsAppBookDao.insert(applyBookInfo);
		return backNum;
	}

	public List<BMSApplyBookInfo> queryPreviousDayApplyBookInfo(Map<String, Object> map) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("batchNum", map.get("batchNum"));
		BMSApplyBookInfo applyBookInfo = bmsAppBookDao.queryApplyBookInfoBybatchNum(paraMap);
		if (applyBookInfo == null) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		Date previousDay = applyBookInfo.getCreatedTime();
		param.put("queryDate", previousDay);
		param.put("fundsSources", map.get("foundSource"));
		List<BMSApplyBookInfo> applyBookInfoList = bmsAppBookDao.queryDayApplyBookInfos(paraMap);
		return applyBookInfoList;
	}

	private BigDecimal getToTalGrantMoney(List<BMSApplyBookInfo> applyBookInfos) {
		BigDecimal money = new BigDecimal("0.00");
		for (BMSApplyBookInfo applyBookInfo : applyBookInfos) {
			money = money.add(applyBookInfo.getGrantMoney());
		}
		return money;
	}

	private List<String> getPreviousBatchNums(List<BMSApplyBookInfo> applyBookInfos) {
		List<String> batchNums = new ArrayList<>();
		for (BMSApplyBookInfo applyBookInfo : applyBookInfos) {
			batchNums.add(applyBookInfo.getBatchNum());
		}
		return batchNums;
	}

	@Override
	public boolean importAppBook(ReqdealEsignatureVo requestVo) {
		Map<String, byte[]> filesMap = requestVo.getFileByteMap();
		Map<String, InputStream> filesStream = new HashMap<String, InputStream>();
		String batchNum = requestVo.getBatchNum();
		String fileBatchNum = requestVo.getFileBatchNum();
		InputStream byteInput = null;
		JschSftpUtils sftp = bmsFileUpdAndDwnService.getFtpBhxtConnectJsch();
		if (!sftp.login()) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, "上传失败!服务器连接不可连接!");
		}
		for (String key : filesMap.keySet()) {
			byteInput = new ByteArrayInputStream(filesMap.get(key));
			filesStream.put(key, byteInput);
		}
		String projectCode = getProjectCode(batchNum);
		long backNumOne = bmsFileUpdAndDwnService.createRequestManagerOperateRecord(batchNum, "", "02", fileBatchNum);
		if (backNumOne <= 0) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, "上传日志记录失败！");
		}
		// 申请书信息略过
		if (AppFormConst.TRUST_PROJECT_CODE2.equals(projectCode) || AppFormConst.TRUST_PROJECT_CODE3.equals(projectCode)) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("batchNum", batchNum);
			paraMap.put("fileBatchNum", fileBatchNum);
			long backNum = createApplyBookInfos(paraMap);
			if (backNum <= 0) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "申请书信息记录失败！");
			}
		}

		boolean isTrue = bmsFileUpdAndDwnService.uploadFtpBHXT(sftp, filesStream, projectCode);
		if (!isTrue) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, "申请书上传失败！");
		}
		return true;

	}

}
