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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class BankFacade extends BaseFacade {
	@Autowired
	private IBMSBankExecuter bmsBankExecuter;

	public ResBMSBankVO findById(Long id) {
		// 请求参数构造
		ReqBMSBankVO reqBankVO = new ReqBMSBankVO(EnumConstants.BMS_SYSTEM_CODE);
		// 接口调用
		reqBankVO.setId(Long.valueOf(id.toString()));
		Response<ResBMSBankVO> response = bmsBankExecuter.getBankById(reqBankVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public PageResult<ResBMSBankVO> listPage(ReqBMSBankVO reqBankVO) {
		/* reqDemoVO.setSysCode("1111111"); */
		// 业务调用
		PageResponse<ResBMSBankVO> pageResponse = bmsBankExecuter.listPage(reqBankVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSBankVO> pageResult = new PageResult<ResBMSBankVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addBank(ReqBMSBankVO reqBankVO) throws BusinessException {
		// 接口调用
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		response = bmsBankExecuter.addBank(reqBankVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean deleteBank(String id) throws BusinessException {
		ReqBMSBankVO reqBankVO = new ReqBMSBankVO(EnumConstants.BMS_SYSTEM_CODE);
		/* reqDemoVO.setSysCode("111111"); */
		reqBankVO.setId(Long.parseLong(id));
		// 请求参数构造
		// 接口调用
		Response<ResBMSBankVO> response = bmsBankExecuter.deleteBank(reqBankVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean updateBank(ReqBMSBankVO reqBankVO) throws BusinessException {
		// 请求参数构造
		// 接口调用
		reqBankVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<ResBMSBankVO> response = bmsBankExecuter.updateBank(reqBankVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public List<ResBMSBankVO> getAllBank(ReqBMSBankVO reqBankVO) {
		List<ResBMSBankVO> arrayList = new ArrayList<>();
		ResListVO<ResBMSBankVO> response = bmsBankExecuter.getAllBank(reqBankVO);
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}

	public ResBMSBankVO findOne(ReqBMSBankVO reqDemoVO) {
		// 请求参数构造
		reqDemoVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 接口调用
		Response<ResBMSBankVO> response = bmsBankExecuter.findOne(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public Response<ResBMSCheckIsExitsVO> checkBankIsExits(ReqBMSBankVO reqBankVO){
		Response<ResBMSCheckIsExitsVO> response=bmsBankExecuter.checkBankIsExits(reqBankVO);
		if(response.isSuccess()){
			return response;
		}else{
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO){
		return bmsBankExecuter.findChannelBankById(reqBankVO);
	}
}
