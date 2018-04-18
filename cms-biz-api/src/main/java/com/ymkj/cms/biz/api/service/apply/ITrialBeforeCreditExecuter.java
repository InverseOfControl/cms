package com.ymkj.cms.biz.api.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ILoanTrialExecuter</p>
 * <p>Description:贷前试算服务方接口</p>
 * @uthor YM10159
 * @date 2017年3月7日 下午5:29:55
 */
public interface ITrialBeforeCreditExecuter {
	
	/**
	 * <p>Description:获取贷前试算结果</p>
	 * @uthor YM10159
	 * @date 2017年3月7日 下午5:32:33
	 */
	public Response<List<ResTrialBeforeCreditVO>> trial(ReqTrialBeforeCreditVO reqLoanTrialVO);
}
