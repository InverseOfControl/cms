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
import com.ymkj.cms.biz.api.service.master.IBMSChannelExecuter;
import com.ymkj.cms.biz.api.service.sign.ILoanContractSignExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Component
public class ChannelFacade extends BaseFacade {

	@Autowired
	private IBMSChannelExecuter channelExecuter;
	@Autowired
	private ILoanContractSignExecuter loanContractSignExecuter;

	/**
	 * 1. 请求参数构造 2. 接口调用 3. 响应结果处理
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSChannelVO findById(Long id) {
		// 请求参数构造
		ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
		reqChannelVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 接口调用
		reqChannelVO.setId(Integer.valueOf(id.toString()));
		Response<ResBMSChannelVO> response = channelExecuter.getChannelById(reqChannelVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public PageResult<ResBMSChannelVO> listPage(ReqBMSChannelVO reqChannelVO) {
		reqChannelVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 业务调用
		PageResponse<ResBMSChannelVO> pageResponse = channelExecuter.listPage(reqChannelVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSChannelVO> pageResult = new PageResult<ResBMSChannelVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	public int addChannel(ReqBMSChannelVO reqChannelVO) throws BusinessException {		
		// 接口调用
		Response<ResBMSChannelVO> response = channelExecuter.addChannel(reqChannelVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean deleteChannel(String id) throws BusinessException {
		ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
		reqChannelVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		reqChannelVO.setId(Integer.valueOf(id));
		// 请求参数构造
		// 接口调用
		Response<ResBMSChannelVO> response = channelExecuter.deleteChannel(reqChannelVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public boolean updateChannel(ReqBMSChannelVO reqChannelVO) throws BusinessException {
		reqChannelVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 请求参数构造
		// 接口调用
		Response<ResBMSChannelVO> response = channelExecuter.updateChannel(reqChannelVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public List<ResBMSChannelVO> getAllChannel(ReqBMSChannelVO reqChannelVO) {
		List<ResBMSChannelVO> arrayList = new ArrayList<>();
		ResListVO<ResBMSChannelVO> response = channelExecuter.getAllChannel(reqChannelVO);
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}
	
	public List<ResBMSChannelVO> findChannelEqDate(ReqBMSChannelVO reqChannelVO) {
		List<ResBMSChannelVO> arrayList = new ArrayList<>();
		ResListVO<ResBMSChannelVO> response = channelExecuter.findChannelEqDate(reqChannelVO);
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}
	
	
	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO) {

		// 业务调用
		List<ResBMSChannelVO> arrayList = new ArrayList<ResBMSChannelVO>();
		ResListVO<ResBMSChannelVO> response = channelExecuter.getChannel(reqDemoVO);

		// 响应结果处理
		if (response.isSuccess()) {
			arrayList = response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
		return arrayList;
	}

	public ResBMSChannelVO findOne(ReqBMSChannelVO reqDemoVO) {
		// 请求参数构造
		reqDemoVO.setSysCode(EnumConstants.BMS_RESPONSE_SYSCODE);
		// 接口调用
		Response<ResBMSChannelVO> response = channelExecuter.findOne(reqDemoVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public Response<ResBMSCheckIsExitsVO> checkIsChennelExits(ReqBMSChannelVO reqDemoVO){
		return channelExecuter.checkIsChennelExits(reqDemoVO);
	}

	public boolean deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO) {
		reqBMSChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		// 请求参数构造
		// 接口调用
		Response<ResBMSChannelVO> response = channelExecuter.deleteChannelCheck(reqBMSChannelVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	public List<ResBMSEnumCodeVO> findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo) {
		reqLoanContractSignVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResEmployeeVO currentUser = ShiroUtils.getCurrentUser();
		reqLoanContractSignVo.setServiceCode(currentUser.getUsercode());
		reqLoanContractSignVo.setServiceName(currentUser.getName());
		// 请求参数构造
		// 接口调用
		ResListVO<ResBMSEnumCodeVO> response = loanContractSignExecuter.findContractTypeList(reqLoanContractSignVo);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	
	public List<ResBMSChannelVO> findChannelByOrgIds(ReqBMSChannelVO reqDemoVO) {
		// 接口调用
		reqDemoVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResListVO<ResBMSChannelVO> response = channelExecuter.findChannelByOrgIds(reqDemoVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
}
