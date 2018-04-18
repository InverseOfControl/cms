package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSBankExecuter {

	public PageResponse<ResBMSBankVO> listPage(ReqBMSBankVO reqBankVO);

	public Response<ResBMSBankVO> addBank(ReqBMSBankVO reqBankVO);

	public Response<ResBMSBankVO> deleteBank(ReqBMSBankVO reqBankVO);

	public Response<ResBMSBankVO> updateBank(ReqBMSBankVO reqBankVO);

	public Response<ResBMSBankVO> getBankById(ReqBMSBankVO reqBankVO);

	public ResListVO<ResBMSBankVO> getAllBank(ReqBMSBankVO reqBankVO);

	public Response<ResBMSBankVO> findOne(ReqBMSBankVO reqBankVO);
	/**
	 * 获取银行集合根据渠道id
	 * @param reqBankVO
	 * @return
	 */
	public ResListVO<ResBMSBankVO> getBankByChannelId(ReqBMSBankVO reqBankVO);
	
	public Response<ResBMSCheckIsExitsVO> checkBankIsExits(ReqBMSBankVO reqBankVO);
	
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO);
}
