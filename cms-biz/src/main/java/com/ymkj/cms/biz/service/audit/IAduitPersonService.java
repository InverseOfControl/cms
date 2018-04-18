package com.ymkj.cms.biz.service.audit;

import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;

public interface IAduitPersonService {
	
	/**
	 * 审核环节查找人员信息
	 * @param reqBMSReassignmentVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月12日下午4:03:27
	 */
	ResBMSAduitPersonVo findAduitPersonInfo(ReqBMSAduitPersonVo reqBMSReassignmentVo);
	

}
