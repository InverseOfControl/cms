package com.ymkj.cms.web.boss.facade.apply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.apply.IBMSChannelBankExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class ChannelBankFacade extends BaseFacade {

	@Autowired
	private IBMSChannelBankExecuter channelBankExecuter;

	public PageResult<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO) {

		// 业务调用
		PageResponse<ResBMSChannelBankVO> pageResponse = channelBankExecuter.listPage(reqDemoVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSChannelBankVO> pageResult = new PageResult<ResBMSChannelBankVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addChannelBank(ReqBMSChannelBankVO reqDemoVO) throws BusinessException {
		// 接口调用
		Response<ResBMSChannelBankVO> response = channelBankExecuter.addChannelBank(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean updateChannelBank(ReqBMSChannelBankVO reqDemoVO) throws BusinessException {
		// 接口调用
		Response<ResBMSChannelBankVO> response = channelBankExecuter.updateChannelBank(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	/**
	 * 1. 请求参数构造 2. 接口调用 3. 响应结果处理
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSChannelBankVO findById(Long id) {
		// 请求参数构造
		ReqBMSChannelBankVO reqDemoVO = new ReqBMSChannelBankVO("1111111");
		reqDemoVO.setId(id);
		// 接口调用
		Response<ResBMSChannelBankVO> response = channelBankExecuter.findOne(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO) {

		// 业务调用
		List<ResBMSChannelVO> arrayList = new ArrayList<ResBMSChannelVO>();
		ResListVO<ResBMSChannelVO> response = channelBankExecuter.getChannel(reqDemoVO);
		
		// 响应结果处理
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}

	public List<ResBMSBankVO> getBank(ReqBMSBankVO reqDemoVO) {

		// 业务调用
		List<ResBMSBankVO> arrayList = new ArrayList<ResBMSBankVO>();
		ResListVO<ResBMSBankVO> response = channelBankExecuter.getBank(reqDemoVO);

		// 响应结果处理
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}
	
	public Response<ResBMSCheckIsExitsVO> checkIsExits(ReqBMSChannelBankVO reqBMSChannelBankVO){
		Response<ResBMSCheckIsExitsVO> response=channelBankExecuter.checkIsExits(reqBMSChannelBankVO);
		if(response.isSuccess()){
			return response;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public Boolean checkParentIsStart(ReqBMSChannelBankVO reqDemoVO){
		Response<Integer> response=channelBankExecuter.checkParentIsStart(reqDemoVO);
		if(response.getData()>=1){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean checkParentIsChanel(ReqBMSChannelBankVO reqDemoVO){
		Response<Integer> response=channelBankExecuter.checkParentIsChanel(reqDemoVO);
		if(response.getData()>=1){
			return true;
		}else{
			return false;
		}
	}
}
