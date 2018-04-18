package com.ymkj.cms.biz.service.master.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.biz.dao.master.IBMSIntegratedSearchDao;
import com.ymkj.cms.biz.dao.master.IBMSLineNumberSetDao;
import com.ymkj.cms.biz.entity.master.BMSLoanBank;
import com.ymkj.cms.biz.entity.master.BMSLoanBankExt;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDic;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDicEntity;
import com.ymkj.cms.biz.service.master.IBMSLineNumberSetService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class BMSLineNumberSetServiceImpl extends BaseServiceImpl<BMSOffLineOfferBankDic> implements IBMSLineNumberSetService{

	@Autowired
	private IBMSSysLogService sysLogService;
	@Autowired
	private IBMSLineNumberSetDao iBMSLineNumberSetDao;
	@Override
	protected BaseDao<BMSOffLineOfferBankDic> getDao() {
		return iBMSLineNumberSetDao;
	}
	@Override
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO) {
		Response<Integer> res=new Response<Integer>();
		String ids=reqLineNumberSetVO.getBankIds();
		ids=ids.substring(0, ids.length()-1);
		String arrays[]=ids.split(",");
		for(int i=0;i<arrays.length;i++){
			Map<String,Object> mapLoanBank=new HashMap<String,Object>();
			mapLoanBank.put("bankId", arrays[i]);
			BMSLoanBank bank=iBMSLineNumberSetDao.findLoanBank(mapLoanBank);
			Map<String,Object> lineNumberMap=new HashMap<String,Object>();
			lineNumberMap.put("code", bank.getBankCode());
			lineNumberMap.put("areaType", reqLineNumberSetVO.getAreaType());
			Integer numberSize=iBMSLineNumberSetDao.findByRegionAndCode(lineNumberMap);
			if(numberSize==0){
				res.setData(0);
				res.setRepMsg(bank.getBankName()+"没有配置银行卡所属地区信息");
				return res;
			}
		}
		for(int a=0;a<arrays.length;a++){
			Map<String,Object> mapLoanBank=new HashMap<String,Object>();
			mapLoanBank.put("bankId", arrays[a]);
			BMSLoanBank bank=iBMSLineNumberSetDao.findLoanBank(mapLoanBank);
			
			
			Map<String,Object> mapBankId=new HashMap<String,Object>();
			mapBankId.put("loanBnakId", arrays[a]);
			BMSLoanBankExt ext=iBMSLineNumberSetDao.findLoanBankExtByBankId(mapBankId);
			if(ext==null){
				Map<String,Object> LineNumberData=new HashMap<String,Object>(); 
				LineNumberData.put("areaType", reqLineNumberSetVO.getAreaType());
				LineNumberData.put("code", bank.getBankCode());
				BMSOffLineOfferBankDicEntity dicEntity=iBMSLineNumberSetDao.findByRegionAndCodeData(LineNumberData);
				BMSLoanBankExt bmsLoanBankExt=new BMSLoanBankExt();
				bmsLoanBankExt.setLoanBankId(bank.getId());
				if(null!=dicEntity){
					bmsLoanBankExt.setOfferBankId(dicEntity.getId());
				}
				bmsLoanBankExt.setCreator(reqLineNumberSetVO.getCreateName());
				bmsLoanBankExt.setCreatorDate(new Date());
				bmsLoanBankExt.setCreatorId(Long.parseLong(reqLineNumberSetVO.getCreateId()));
				Integer isSuccess=iBMSLineNumberSetDao.insertLoanBankExt(bmsLoanBankExt);
				if(isSuccess==1){
					sysLogService.recordSysLog(reqLineNumberSetVO, "放款管理|行别行号配置|insert|null", "iBMSLineNumberSetDao.insertLoanBankExt",
							"新增行别行号对应所属地<" + reqLineNumberSetVO.getAreaType() + ">");
				}
			}else{
				Map<String,Object> LineNumberData=new HashMap<String,Object>(); 
				LineNumberData.put("areaType", reqLineNumberSetVO.getAreaType());
				LineNumberData.put("code", bank.getBankCode());
				BMSOffLineOfferBankDicEntity dicEntity=iBMSLineNumberSetDao.findByRegionAndCodeData(LineNumberData);
				if(null!=dicEntity){
					ext.setOfferBankId(dicEntity.getId());
				}
				ext.setModifiedId(Long.parseLong(reqLineNumberSetVO.getCreateId()));
				ext.setModified(reqLineNumberSetVO.getCreateName());
				ext.setModifiedDate(new Date());
				Integer isSuccess=iBMSLineNumberSetDao.updateLoanBankExt(ext);
				if(isSuccess==1){
					sysLogService.recordSysLog(reqLineNumberSetVO, "放款管理|行别行号配置|update|null", "iBMSLineNumberSetDao.updateLoanBankExt",
							"更新行别行号对应所属地<" + reqLineNumberSetVO.getAreaType() + ">");
				}
			}
		}
		res.setData(1);
		res.setRepMsg("保存成功");
		return res;
	}
	@Override
	public void insertOrUpdateDic(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs,
			String importExcelAreaType,String name,String id) {
		if(importExcelAreaType.equals("01")){//深圳地区
			for(int i=0;i<LineNumberUploadVOs.size();i++){
				if(LineNumberUploadVOs.get(i).getReturnStatus().equals("成功")){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("areaType", importExcelAreaType);
					map.put("code", LineNumberUploadVOs.get(i).getBankCode());
					BMSOffLineOfferBankDicEntity dicEntity=iBMSLineNumberSetDao.findByRegionAndCodeData(map);
					if(dicEntity==null){// 新增
						BMSOffLineOfferBankDicEntity entity=new BMSOffLineOfferBankDicEntity();
						entity.setCode(LineNumberUploadVOs.get(i).getBankCode());
						entity.setName(LineNumberUploadVOs.get(i).getBankName());
						entity.setBankType(LineNumberUploadVOs.get(i).getBankType());
						entity.setBankNo(LineNumberUploadVOs.get(i).getBankNo());
						entity.setRegionType(importExcelAreaType);
						entity.setCreator(name);
						entity.setCreatorId(Long.parseLong(id));
						entity.setCreatorDate(new Date());
						iBMSLineNumberSetDao.insertLineOfferBankDic(entity);
						sysLogService.recordSysLog(LineNumberUploadVOs.get(i), "放款管理|行别行号配置|insert|null", "iBMSLineNumberSetDao.insertOrUpdateDic",
								"新增行别行号对应所属地<" + LineNumberUploadVOs.get(i).getBankName() + ">");
					}else{//更新
						dicEntity.setName(LineNumberUploadVOs.get(i).getBankName());
						dicEntity.setBankType(LineNumberUploadVOs.get(i).getBankType());
						dicEntity.setBankNo(LineNumberUploadVOs.get(i).getBankNo());
						dicEntity.setModified(name);
						dicEntity.setModifiedDate(new Date());
						dicEntity.setModifiedId(Long.parseLong(id));
						iBMSLineNumberSetDao.updateLineOfferBankDic(dicEntity);
						sysLogService.recordSysLog(LineNumberUploadVOs.get(i), "放款管理|行别行号配置|update|null", "iBMSLineNumberSetDao.insertOrUpdateDic",
								"更新行别行号对应所属地<" + LineNumberUploadVOs.get(i).getBankName() + ">");
					}
				}
			}
		}else{//异地
			for(int i=0;i<LineNumberUploadVOs.size();i++){
				if(LineNumberUploadVOs.get(i).getReturnStatus().equals("成功")){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("areaType", importExcelAreaType);
					map.put("code", LineNumberUploadVOs.get(i).getBankCode());
					BMSOffLineOfferBankDicEntity dicEntity=iBMSLineNumberSetDao.findByRegionAndCodeData(map);
					if(dicEntity==null){// 新增
						BMSOffLineOfferBankDicEntity entity=new BMSOffLineOfferBankDicEntity();
						entity.setCode(LineNumberUploadVOs.get(i).getBankCode());
						entity.setName(LineNumberUploadVOs.get(i).getBankName());
						entity.setBankType(LineNumberUploadVOs.get(i).getBankType());
						entity.setBankNo(LineNumberUploadVOs.get(i).getBankNo());
						entity.setRegionType(importExcelAreaType);
						entity.setCreator(name);
						entity.setCreatorId(Long.parseLong(id));
						entity.setCreatorDate(new Date());
						iBMSLineNumberSetDao.insertLineOfferBankDic(entity);
						sysLogService.recordSysLog(LineNumberUploadVOs.get(i), "放款管理|行别行号配置|insert|null", "iBMSLineNumberSetDao.insertOrUpdateDic",
								"新增行别行号对应所属地<" + LineNumberUploadVOs.get(i).getBankName() + ">");
					}else{//更新
						dicEntity.setName(LineNumberUploadVOs.get(i).getBankName());
						dicEntity.setBankType(LineNumberUploadVOs.get(i).getBankType());
						dicEntity.setBankNo(LineNumberUploadVOs.get(i).getBankNo());
						dicEntity.setModified(name);
						dicEntity.setModifiedDate(new Date());
						dicEntity.setModifiedId(Long.parseLong(id));
						iBMSLineNumberSetDao.updateLineOfferBankDic(dicEntity);
						sysLogService.recordSysLog(LineNumberUploadVOs.get(i), "放款管理|行别行号配置|update|null", "iBMSLineNumberSetDao.insertOrUpdateDic",
								"更新行别行号对应所属地<" + LineNumberUploadVOs.get(i).getBankName() + ">");
					}
				}
			}
		}
		
		
	}

}
