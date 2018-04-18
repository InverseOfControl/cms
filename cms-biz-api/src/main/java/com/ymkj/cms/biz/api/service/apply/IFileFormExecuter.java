package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqArchivesVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResArchivesVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResntrySearchVO;

/**
 * 档案管理接口
 * @author YM10172
 *
 */
public interface IFileFormExecuter {
	/**
	 * 新增档案信息
	 * @param reqArchivesVo
	 * @return
	 */
	public Response<ResArchivesVO> saveArchives(ReqArchivesVO reqArchivesVo);
	/**
	 * 申请件档案列表展示
	 * @param reqFileFormVO
	 * @return
	 */
	public PageResponse<ResFileFormSearchVO>listPage(ReqFileFormSearchVO reqFileFormVO);
	/**
	 * 更新档案接口
	 * @param reqArchivesVo
	 * @return
	 */
	public Response<ResArchivesVO>updateArchives(ReqArchivesVO reqArchivesVo);
	/**
	 * 查询档案详细信息
	 * @param resntrySearchVo
	 * @return
	 */
	public Response<ResntrySearchVO> queryArchives(ReqArchivesVO reqArchivesVo);



}
