package com.ymkj.cms.biz.service.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.dao.master.IBMSEnumCodeDao;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.service.master.IBMSEnumCodeService;

@Service
public class BMSEnumCodeServiceImpl extends BaseServiceImpl<BMSEnumCode> implements IBMSEnumCodeService {
	@Autowired
	private IBMSEnumCodeDao enumCodeDao;
	
	private static Map<String,String> map = new HashMap<String,String>();

	@Override
	protected BaseDao<BMSEnumCode> getDao() {
		return enumCodeDao;
	}

	@Override
	public List<BMSEnumCode> findEnumCodeByCondition(Map<String, Object> paramMap) {
		return enumCodeDao.findEnumCodeByCondition(paramMap);
	}

	@Override
	public long insert(BMSEnumCode enumCode) {
		return enumCodeDao.insert(enumCode);
	}

	@Override
	public void update(BMSEnumCode enumCode) {
		enumCodeDao.update(enumCode);
	}

	@Override
	public BMSEnumCode getByVo(Map<String, Object> paramMap) {
		return enumCodeDao.getByVO(paramMap);
	}

	@Override
	public List<BMSEnumCode> getProducAssetsInfo(Map<String, Object> paramMap) {
		return enumCodeDao.getProducAssetsInfo(paramMap);
	}

	@Override
	public List<BMSEnumCode> getProducAssetsInfoByCode(Map<String, Object> paramMap) {
		return enumCodeDao.getProducAssetsInfoByCode(paramMap);
	}
	
	public String getVaule(String key,String value){
		try {
			if (map.get(key) == null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("codeType", key);
				List<BMSEnumCode> listEnum = enumCodeDao.listBy(paramMap);
				
				//职务 添加国企区分
				if(key != null && key.equals("NoGovInstitution")){
					paramMap.put("codeType", "EmpPositionAttrType");
					List<BMSEnumCode> listEnum2 = enumCodeDao.listBy(paramMap);
					if(listEnum2 != null){
						for (BMSEnumCode emum : listEnum2) {
							emum.setCodeType(key);
						}
						listEnum.addAll(listEnum2);
					}
				}
				

				if (listEnum != null) {
					setEnumMap(listEnum);
				}

				String va = map.get(key + "_" + value);
				return va == null ? value : va;
			} else {
				String va = map.get(key + "_" + value);
				return va == null ? value : va;
			} 
		} catch (Exception e) {
			System.out.println("获取枚举异常:"+e.getMessage());
			return value;
		}
	}
	
	
	
	public void setMap(String key,String value){
		map.put(key, value);
	}
	
	
	public void setEnumMap(List<BMSEnumCode> listEnum){
		if(!listEnum.isEmpty()){
			for (BMSEnumCode enumCode : listEnum) {
				map.put(enumCode.getCodeType()+"_"+enumCode.getCode(), enumCode.getNameCN());
				if(map.get(enumCode.getCodeType()) == null){
					map.put(enumCode.getCodeType(), enumCode.getNameCN());
				}
			}
		}
	}

	@Override
	public List<BMSEnumCode> findCodeByUnit(Map<String, String> map) {
		List<BMSEnumCode> list=enumCodeDao.findCodeByUnit(map);
		return list;
	}

	@Override
	public List<BMSEnumCode> findCodeByProfession(Map<String, Object> map) {
		List<BMSEnumCode> list=enumCodeDao.findCodeByProfession(map);
		return list;
	}

	@Override
	public List<BMSEnumCode> findByservenProfession() {
		List<BMSEnumCode> list=enumCodeDao.findByservenProfession();
		return list;
	}
}
