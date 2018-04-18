package com.ymkj.cms.biz.api.service.audit;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:SignReassignmentExecuter</p>
 * <p>Description:用于对已经签约分派的借款进行签约改派</p>
 * @uthor YM10159
 * @date 2017年3月22日 下午4:33:26
 */
public interface ISignReassignmentExecuter {
	 
	/**
	 * <p>Description:签约改派</p>
	 * @uthor YM10159
	 * @date 2017年3月22日 下午5:11:53
	 */
	public Response<Object> signReassign(ReqSignReassignVO reqSignReassignVO);
}
