package com.ymkj.cms.biz.service.sign.waimao.three.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;
import com.ymkj.cms.biz.service.sign.base.CreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.ifc.WaiMaoTInterfaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成合同
 * 
 * @author YM10138
 *
 */
@Service
public class WaiMaoTCreateLoanContractImpl extends CreateLoanContractImpl {

	@Autowired
	private WaiMaoTInterfaceService waiMaoTInterfaceService;
	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	@Autowired
	private ILoanContractSignService loanContractSignService;
	
	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		//调用预审批接口
		Map<String,Object> httpParamMap = loanSignDataOprateService.getPreliminaryAuditData(reqLoanContractSignVo.getLoanNo());
		if(waiMaoTInterfaceService.preliminaryAudit(httpParamMap, res)){
			return super.execute(reqLoanContractSignVo, res);
		} else {
			String lockTarget = "N";
			if(lockTarget.equals(httpParamMap.get("lockTarget"))){
				BMSLoanBaseEntity loanInfo = loanContractSignService.findSignInfo(reqLoanContractSignVo);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanBaseId", loanInfo.getId());
				paramMap.put("channelCode", loanInfo.getChannelCd());
				BMSLoanChannelLockTargetEntity lockTargetEntity = loanChannelLockTargetDao.getBy(paramMap);
				
				if(lockTargetEntity != null){
					lockTargetEntity.setLockTarget(lockTarget);
					lockTargetEntity.setModified(reqLoanContractSignVo.getServiceCode());
					long udpateNum = loanChannelLockTargetDao.update(lockTargetEntity);
					if(udpateNum !=1){
						throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "更新失败");
					}
				} else {
					//锁定数据
					BMSLoanChannelLockTargetEntity saveEntity = new BMSLoanChannelLockTargetEntity();
					saveEntity.setLoanBaseId(loanInfo.getId());
					saveEntity.setLoanNo(loanInfo.getLoanNo());
					saveEntity.setChannelCode(loanInfo.getChannelCd());
					saveEntity.setLockTarget(lockTarget);
					saveEntity.setCreator(reqLoanContractSignVo.getServiceCode());
					saveEntity.setModified(reqLoanContractSignVo.getServiceCode());
					long udpateNum = loanChannelLockTargetDao.insert(saveEntity);
					if(udpateNum !=1){
						throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "插入错误");
					}
					
				}
			}
		}
		
		
		return false;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}

}
