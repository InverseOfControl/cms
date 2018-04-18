package com.ymkj.cms.biz.service.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.bds.biz.api.service.IInternalMatchingExecuter;
import com.ymkj.bds.biz.api.vo.request.IdentifyingAntiFraudReqVO;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.service.client.BDSClientService;

/**
 * 行为库系统调用
 * @author YM10152
 *
 */
@Service
public class BDSClientServiceImpl implements BDSClientService{
	// JSON 工具类
		private static Gson gson = new Gson();
	
	@Autowired
	private IInternalMatchingExecuter iInternalMatchingExecuter;

	@Override
	public Integer ifSuspectCheat(BMSAppPersonInfo personInfo) {
		Response<String> identifyingAntiFraud = new Response<String>();

		try {
			String cellPhone = personInfo.getCellphone();
			if (personInfo.getCellphoneSec() != null && !personInfo.getCellphoneSec().equals("")) {
				cellPhone += "," + personInfo.getCellphoneSec();
			}

			String corpPhone = personInfo.getCorpPhone();
			if (personInfo.getCorpPhoneSec() != null && !personInfo.getCorpPhoneSec().equals("")) {
				corpPhone += "," + personInfo.getCorpPhoneSec();
			}
			IdentifyingAntiFraudReqVO identifyingAntiFraudReq = new IdentifyingAntiFraudReqVO();
			identifyingAntiFraudReq.setName(personInfo.getName());// 姓名
			identifyingAntiFraudReq.setIdNo(personInfo.getIdNo());// 证件号
			identifyingAntiFraudReq.setCellPhone(cellPhone);// 手机号码
			identifyingAntiFraudReq.setCorpName(personInfo.getCorpName());// 公司名称
			identifyingAntiFraudReq.setCorpPhone(corpPhone);// 公司电话
			identifyingAntiFraudReq.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			BmsLogger.info("调用行为库入参：" + gson.toJson(identifyingAntiFraudReq));
			identifyingAntiFraud = iInternalMatchingExecuter.identifyingAntiFraud(identifyingAntiFraudReq);
			BmsLogger.info("调用行为库出参：" + gson.toJson(identifyingAntiFraud));
			if (identifyingAntiFraud.getData().equals("1")) {
				BmsLogger.info("返回结果，用户[" +personInfo.getName() + "][" + personInfo.getIdNo() + "]疑似欺诈");
				return ParametersType.ifSuspectCheat._1;
			}
		} catch (Exception e) {
			BmsLogger.info("[" + personInfo.getName() + "][" + personInfo.getIdNo() + "]查询联欺诈信息失败，失败原因：" + e.getMessage() + ",接口返回原因:" + identifyingAntiFraud.getRepMsg());
			return ParametersType.ifSuspectCheat._0;
		}
		return ParametersType.ifSuspectCheat._0;
	}

}
