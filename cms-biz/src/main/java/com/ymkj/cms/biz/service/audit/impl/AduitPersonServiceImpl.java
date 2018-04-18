package com.ymkj.cms.biz.service.audit.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;
import com.ymkj.cms.biz.dao.audit.BMSAPPHistoryDao;
import com.ymkj.cms.biz.dao.audit.IAduitPersonDao;
import com.ymkj.cms.biz.entity.audit.BMSAduitPersonEntity;
import com.ymkj.cms.biz.service.audit.IAduitPersonService;

@Service
public class AduitPersonServiceImpl implements IAduitPersonService {

	@Autowired
	private IAduitPersonDao aduitPersonDao;
	
	@Override
	public ResBMSAduitPersonVo findAduitPersonInfo(ReqBMSAduitPersonVo reqBMSReassignmentVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 更新申请主表状态
		map.put("loanNo", reqBMSReassignmentVo.getLoanNo());
		BMSAduitPersonEntity e = aduitPersonDao.findAduitPersonInfo(map);
		if(e == null){
			throw new CoreException(CoreErrorCode.DB_RESULT_ISNULL, "数据查询结果为空");
		}
		ResBMSAduitPersonVo vo = new ResBMSAduitPersonVo();
		BeanUtils.copyProperties(e, vo);
		
		return vo;
	}

}
