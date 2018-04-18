package com.ymkj.cms.biz.service.master.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.dao.master.IBMSSysLogDao;
import com.ymkj.cms.biz.entity.master.BMSSysLog;
import com.ymkj.cms.biz.entity.master.BMSSysLogRecordEntity;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class BMSSysLogServiceImpl extends BaseServiceImpl<BMSSysLog> implements IBMSSysLogService {

	@Autowired
	private IBMSSysLogDao bmsSysLogDao;
	@Override
	protected BaseDao<BMSSysLog> getDao() {
		return bmsSysLogDao;
	}
	@Override
	public Long saveOrUpdate(BMSSysLog sysLog) {
		// TODO Auto-generated method stub
		long id = 0;
		if(sysLog.getId() == null){
			long insert = bmsSysLogDao.insert(sysLog);
			if(insert > 0){
				id = insert;
			}
		}else{
			long update = bmsSysLogDao.update(sysLog);
			if(update > 0){
				id = sysLog.getId();
			}
		}

		return id;
	}

	@Override
	public int recordSysLog(Object reqVO, Object...args) {
		
		BMSSysLogRecordEntity sysLogRecordEntity = new BMSSysLogRecordEntity();
		
		String operate = ObjectUtils.toString(args[0]);
		String operateArr[] =  operate.split("\\|");
		
		String requestUrl = ObjectUtils.toString(args[1]);
		String remark = ObjectUtils.toString(args[2]);
		
		String sysCode = ObjectUtils.toString(getValFormObj(reqVO,"sysCode"));
		String sysName =  ("sys_"+sysCode).toUpperCase();
		
		sysLogRecordEntity.setSystemCode(sysCode);
		sysLogRecordEntity.setSystemName(EnumConstants.sysType.valueOf(sysName).getValue());//通过枚举获取
		
		sysLogRecordEntity.setFirstLevelDir(operateArr[0]);
		sysLogRecordEntity.setTwoLevelDir(operateArr[1]);
		sysLogRecordEntity.setOptModule(operateArr[2]);
		sysLogRecordEntity.setOptType(operateArr[3]);
		
		sysLogRecordEntity.setOptCode(ObjectUtils.toString(getValFormObj(reqVO,"serviceCode")));
		sysLogRecordEntity.setOptName(ObjectUtils.toString(getValFormObj(reqVO,"serviceName")));
		sysLogRecordEntity.setRequestUri(requestUrl);
		sysLogRecordEntity.setEmployeeType("");//调接口获取
		sysLogRecordEntity.setRomoteAddr(ObjectUtils.toString(getValFormObj(reqVO,"ip")));
		sysLogRecordEntity.setMemo(remark);
		
		return bmsSysLogDao.recordSysLog(sysLogRecordEntity);
	}
	
	
	public static Object getValFormObj(Object obj, String field){
		
		Class clazz = obj.getClass();
		String val = "";
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field, clazz);
			
			Method method = pd.getReadMethod();
			
			val = ObjectUtils.toString(method.invoke(obj));
			
		} catch (Exception e) {}
		return val;
	}

}
