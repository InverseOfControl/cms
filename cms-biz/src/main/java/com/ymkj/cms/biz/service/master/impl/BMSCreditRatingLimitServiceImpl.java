package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.dao.master.IBMSBankDao;
import com.ymkj.cms.biz.dao.master.IBMSCreditRatingLimitDao;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSCreditRatingLimit;
import com.ymkj.cms.biz.service.master.IBMSCreditRatingLimitService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class BMSCreditRatingLimitServiceImpl extends BaseServiceImpl<BMSCreditRatingLimit> implements IBMSCreditRatingLimitService{
	@Autowired
	private IBMSCreditRatingLimitDao iBMSCreditRatingLimitDao;
	@Autowired
	private IBMSSysLogService sysLogService;
	@Override
	protected BaseDao<BMSCreditRatingLimit> getDao() {
		return iBMSCreditRatingLimitDao;
	}

	@Override
	public Response<List<Map<String, Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v) {
		return iBMSCreditRatingLimitDao.findByProductAll();
	}

	@Override
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Response<Integer> responseInteger=iBMSCreditRatingLimitDao.addCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(responseInteger.getData()==1){
			sysLogService.recordSysLog(reqBMSCreditRatingLimitVO, "申请管理|征信等级规则设置|insert|新增", "iBMSCreditRatingLimitDao.addCreditRatingLimit",
					"新增征信等级规则设置<" + reqBMSCreditRatingLimitVO.getId() + ">");
		}
		return responseInteger;
	}

	@Override
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Response<Integer> responseInteger=iBMSCreditRatingLimitDao.deleteCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(responseInteger.getData()==1){
			sysLogService.recordSysLog(reqBMSCreditRatingLimitVO, "申请管理|征信等级规则设置|delete|删除", "iBMSCreditRatingLimitDao.deleteCreditRatingLimit",
					"删除征信等级规则设置<" + reqBMSCreditRatingLimitVO.getId() + ">");
		}
		return responseInteger;
	}

	@Override
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(
			ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitDao.loadCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Response<Integer> responseInteger=iBMSCreditRatingLimitDao.updateCreditRatingLimit(reqBMSCreditRatingLimitVO);
		if(responseInteger.getData()==1){
			sysLogService.recordSysLog(reqBMSCreditRatingLimitVO, "申请管理|征信等级规则设置|update|更新", "iBMSCreditRatingLimitDao.updateCreditRatingLimit",
					"修改征信等级规则设置<" + reqBMSCreditRatingLimitVO.getId() + ">");
		}
		return responseInteger;
	}

	@Override
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitDao.findAddIsRepeat(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return iBMSCreditRatingLimitDao.findUpdateIsRepeat(reqBMSCreditRatingLimitVO);
	}

}
