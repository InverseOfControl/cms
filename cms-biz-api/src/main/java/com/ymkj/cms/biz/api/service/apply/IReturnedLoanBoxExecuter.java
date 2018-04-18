package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxMessageVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReturnedLoanBoxSearchVO;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:IReturnedLoanBoxExecuter</p>
 * <p>Description:退件箱服务方</p>
 * @uthor YM10159
 * @date 2017年3月7日 上午11:08:01
 */
public interface IReturnedLoanBoxExecuter {
 
	public PageResponse<ResReturnedLoanBoxSearchVO> search(ReqReturnedLoanBoxSearchVO reqReturnedLoanBoxSearchVO);  
	
	/**
	 * <p>Description:获取退件箱页签消息提示数</p>
	 * @uthor YM10159
	 * @date 2017年3月31日 上午9:04:55
	 */
	public Response<Object> queryMessageCount(ReqReturnedLoanBoxMessageVO reqReturnedLoanBoxMessageVO);
}
