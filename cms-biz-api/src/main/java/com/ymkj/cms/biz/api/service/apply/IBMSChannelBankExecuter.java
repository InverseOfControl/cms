package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSChannelBankExecuter {

	public PageResponse<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO);

	public Response<ResBMSChannelBankVO> addChannelBank(ReqBMSChannelBankVO reqDemoVO);

	public Response<ResBMSChannelBankVO> updateChannelBank(ReqBMSChannelBankVO reqDemoVO);

	public Response<ResBMSChannelBankVO> findOne(ReqBMSChannelBankVO reqDemoVO);
	
	public ResListVO<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO);
	
	public ResListVO<ResBMSBankVO> getBank(ReqBMSBankVO reqDemoVO);
	
	public Response<ResBMSCheckIsExitsVO> checkIsExits(ReqBMSChannelBankVO reqBMSChannelBankVO);
	/**
	 * 根据银行ID查询对应银行列表的信息是否禁用
	 */
	public Response<Integer> checkParentIsStart(ReqBMSChannelBankVO reqDemoVO);
		/**
		 * 根据银行ID查询对应其道列表的信息是否禁用
		 */
	 public Response<Integer> checkParentIsChanel(ReqBMSChannelBankVO reqDemoVO);
}
