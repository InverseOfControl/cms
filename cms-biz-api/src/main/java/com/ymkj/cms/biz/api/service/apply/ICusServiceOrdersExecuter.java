package com.ymkj.cms.biz.api.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersOperationVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ResCusServiceOrdersSearchVO;

/**
 * 客服接单人员管理接口
 * @author YM10152
 *
 */
public interface ICusServiceOrdersExecuter {
	
	/**
	 * 查询接单人员列表信息
	 * @param serviceOrderSearchVo
	 * @return
	 */
	public Response<List<ResCusServiceOrdersSearchVO>> search(ReqCusServiceOrdersSearchVO serviceOrderSearchVo);
	
	
	/**
	 * 开启、禁止    接单人员接单开关
	 * @param reqCusServiceOrdersOperationVo
	 * @return
	 */
	public Response<Object> enableOrClose(ReqCusServiceOrdersOperationVo reqCusServiceOrdersOperationVo);
}
