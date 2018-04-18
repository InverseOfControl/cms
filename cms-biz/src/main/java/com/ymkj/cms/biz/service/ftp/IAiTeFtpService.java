package com.ymkj.cms.biz.service.ftp;

import java.io.BufferedInputStream;

import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;

public interface IAiTeFtpService {
	/**
	 * 央行征信报告获取
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午5:06:50
	 */
	public String getPeopleBankCredit(APPPersonInfoEntity tmAppPersonInfo);
	
	/**
	 * 发送央行征信报告
	 * @param creditInvestigationStream
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午5:15:36
	 */
	public boolean creditInvestigationUploadFile(String creditInvestigationStream, APPPersonInfoEntity tmAppPersonInfo);
	
	/**
	 * 发送上海资信报告
	 * @param sinputStreamString
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月20日上午11:45:38
	 */
	public boolean creditReportUploadFile(String sinputStreamString, APPPersonInfoEntity tmAppPersonInfo);

	/**
	 * 发送身份证
	 * @param jsonPic
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月20日下午1:58:55
	 */
	public boolean IDCardUploadFile(String jsonPic, APPPersonInfoEntity tmAppPersonInfo);

}
