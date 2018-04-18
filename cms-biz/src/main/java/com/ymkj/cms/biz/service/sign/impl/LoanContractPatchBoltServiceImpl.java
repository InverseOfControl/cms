package com.ymkj.cms.biz.service.sign.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.ILoanContractPatchBoltService;

@Service
public class LoanContractPatchBoltServiceImpl implements ILoanContractPatchBoltService{

	@Autowired
	private ILoanBaseEntityDao loanBaseEntityDao;
	
	@Override
	public PageBean<BMSLoanBaseEntity> undoneListPatchBoltPage(ReqLoanContractSignVo reqLoanContractSignVo) {
		// 构造请求参数
			 PageBean<BMSLoanBaseEntity> patchBoltPage =null;
				PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
				Map<String, Object> paramMap= null;
				try {
					paramMap = BeanKit.bean2map(reqLoanContractSignVo);
					 patchBoltPage = loanBaseEntityDao.undoneListPatchBoltPage(pageParam, paramMap);
				} catch (Exception e) {
					throw new BizException(CoreErrorCode.PARAM_ERROR,"查询异常");
				}
				// 调用业务逻辑
				
		return patchBoltPage;
	}





	@Override
	public PageBean<BMSLoanBaseEntity>  doneListPatchBoltPage(ReqLoanContractSignVo reqLoanContractSignVo) {
		// 构造请求参数
		 	PageBean<BMSLoanBaseEntity> patchBoltPage =null;
			PageParam pageParam = new PageParam(reqLoanContractSignVo.getPageNum(), reqLoanContractSignVo.getPageSize());
			Map<String, Object> paramMap= null;
			try {
				paramMap = BeanKit.bean2map(reqLoanContractSignVo);
				patchBoltPage = loanBaseEntityDao.undoneListPatchBoltPage(pageParam, paramMap);
			} catch (Exception e) {
				throw new BizException(CoreErrorCode.PARAM_ERROR,"查询异常");
			}
			// 调用业务逻辑	
			return patchBoltPage;
	}
	

	@Override
	public boolean  updatePatchBoltSign(ReqLoanContractSignVo reqLoanContractSignVo) {
			boolean updFlag =false;
			BMSLoanBaseEntity bmsLoanBaseEntity=new BMSLoanBaseEntity();
			bmsLoanBaseEntity.setIfNeedPatchBolt(reqLoanContractSignVo.getIfNeedPatchBolt());
			bmsLoanBaseEntity.setLoanNo(reqLoanContractSignVo.getLoanNo());
			bmsLoanBaseEntity.setId(reqLoanContractSignVo.getId());
			bmsLoanBaseEntity.setPatchBoltTime(new Date());
			long updateCount=	loanBaseEntityDao.update(bmsLoanBaseEntity);
			if(updateCount == 1){
				updFlag =true;
			}
			// 调用业务逻辑	
			return updFlag;
	}





	@Override
	public long queryContractPatchBoltToDoCount(Map<String, Object> paramsMap) {
		return loanBaseEntityDao.queryContractPatchBoltToDoCount(paramsMap);
	}
	
	
	
	

}
