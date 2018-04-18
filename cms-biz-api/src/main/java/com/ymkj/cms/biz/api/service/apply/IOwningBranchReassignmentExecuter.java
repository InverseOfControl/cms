package com.ymkj.cms.biz.api.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyPhoneVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentListVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ResOwningBranchReassignmentSearchVO;

/**
 * 门店改派
 * @author YM10152
 *
 */
public interface IOwningBranchReassignmentExecuter {
	
	/**
	 * 门店改派查询接口
	 * @return
	 */
	public PageResponse<ResOwningBranchReassignmentSearchVO> search(ReqOwningBranchReassignmentSearchVO reqOwningBranchReassignmentSearchVO);
	
	
	/**
	 * 门店改派接口
	 * @param reqOwningBranchReassignmentVO
	 * @return
	 */
	public Response<Object> reassignment(ReqOwningBranchReassignmentListVO reqOwningBranchReassignmentListVO);
	
	/**
	 * 查询联系人信息
	 * @param contactInfoVO
	 * @return
	 */
	public Response<List<ContactInfoVO>> queryContactInfo(ReqContactInfoVO contactInfoVO);
	
	/**
	 * 修改联系人信息
	 * @param contactInfoVO
	 * @return
	 */
	public Response<Object> updateContactInfo(ReqContactInfoVO contactInfoVO);
	
	/**
	 * 新增联系人
	 * @param contactInfoVO
	 * @return
	 */
	public Response<Object> insertContactInfo(ReqContactInfoVO contactInfoVO);
	
	
	/**
	 * 修改个人信息号码（固话+移动号码）
	 * @return
	 */
	public Response<Object> updateUserPhone(ReqApplyPhoneVO reqApplyPhoneVO);
	
}
