package com.ymkj.cms.biz.service.apply.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.dao.apply.IBMSChannelBankDao;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.IBMSChannelBankService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class BMSChannelBankService extends BaseServiceImpl<BMSChannelBank> implements IBMSChannelBankService {

	@Autowired
	private IBMSChannelBankDao channelBankDao;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	private static final String CHECK_IS_EXITS_SQL="checkIsExits";
	@Override
	public boolean insert(BMSChannelBank channelBank) {
		boolean flag=true;
		long result=channelBankDao.insert(channelBank);
		//系统日志
		int result2=ibmsSysLogService.recordSysLog(channelBank, "借款系统|签约银行|insert|null","接口名称:addChannelBank","插入签约银行信息");
		if(result>0 && result2>0){
			return flag;
		}else{
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
	}

	@Override
	public boolean update(BMSChannelBank channelBank) {
		boolean flag=true;
		long result=channelBankDao.update(channelBank);
		//系统日志
		int result2=ibmsSysLogService.recordSysLog(channelBank, "借款系统|签约银行|delete|null","接口名称:updateChannelBank","删除签约银行信息");
		if(result>0 && result2>0){
			return flag;
		}else{
			throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0);
		}
	}

	@Override
	protected BaseDao<BMSChannelBank> getDao() {
		return channelBankDao;
	}

	@Override
	public boolean checkIsExits(Map<String,Object> map) {
		boolean flag=true;
		Integer result=(Integer) channelBankDao.getBy(map, CHECK_IS_EXITS_SQL);
		if(result>0){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}

	@Override
	public Integer checkParentIsStart(Map<String, Object> map) {
		return channelBankDao.checkParentIsStart(map);
	}

	@Override
	public Integer checkParentIsChanel(Map<String, Object> map) {
		return channelBankDao.checkParentIsChanel(map);
	}
}
