package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryAuditOperationVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryCancelVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntrySearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqInformationVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqPersonalInformation;
import com.ymkj.cms.biz.api.vo.response.apply.ResEntrySearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResWaitToDealCount;

/**
 *  
 * @author  
 *
 */
public interface IEntryAuditExecuter {
 
	
	/**
	 * 分页查询  
	 * @param  ReqEntrySearchVO
	 * @return
	 */
	public PageResponse<ResEntrySearchVO> listPage( ReqEntrySearchVO reqEntrySearchVO) ;
	
	/**
	 * <p>Description:获取录入修改待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午2:07:59
	 * @param reqEntrySearchVO
	 * @return
	 */
	public Response<Object> queryInputModifyCount(ReqEntrySearchVO reqEntrySearchVO) ;
	
	/**
	 * 录入复核，查询等待办理个数
	 * @param reqEntrySearchVO
	 * @return
	 */
	public Response<ResWaitToDealCount> queryWaitToDealCount(ReqEntrySearchVO reqEntrySearchVO) ;
	
	/**
	 * 录入修改，查询等待办理个数
	 * @param reqEntrySearchVO
	 * @return
	 */
	public Response<ResWaitToDealCount> queryWaitToUpdateCount(ReqEntrySearchVO reqEntrySearchVO) ;
	
	
	/**
	 * 取消接口
	 * @param reqEntryCancelVO
	 * @return
	 */
	public Response cancel(ReqEntryCancelVO reqEntryCancelVO) ;
	
	/**
	 * 基础信息查询
	 * @param reqInformationVO
	 * @return
	 */
	public Response<ReqInformationVO> queryInformationVO(ReqInformationVO reqInformationVO);
	
	/**
	 * 复核退回接口
	 * @param reqEntryAuditOperationVO
	 * @return
	 */
	public Response<String> reviewReturn(ReqEntryAuditOperationVO reqEntryAuditOperationVO);
	
	/**
	 * 电核修改信息
	 * @param reqPersonalInformation
	 * @return
	 */
	public Response<String> updatePersonalInformation(ReqPersonalInformation reqPersonalInformation);
	/**
	 * 超时拒绝接口
	 * @param reqEntryCancelVO
	 * @return
	 */
	public Response timeOutRefuse(ReqEntryCancelVO reqEntryCancelVO) ;
	
	/**
	 * 拒绝接口
	 *   @param   reqEntrySearchVO
	 */
	public Response invalid(ReqEntryCancelVO reqEntryCancelVO);
}
