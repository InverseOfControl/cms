package com.ymkj.cms.biz.api.service.audit;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;

/**
 * 审核环节,人员信息-接口类
 * @author YM10143
 *
 */
public interface IAduitPersonExecuter {
	/**
	 * 审核环节查找人员信息
	 */
	Response<ResBMSAduitPersonVo> findAduitPersonInfo(ReqBMSAduitPersonVo reqBMSReassignmentVo);
	
}
