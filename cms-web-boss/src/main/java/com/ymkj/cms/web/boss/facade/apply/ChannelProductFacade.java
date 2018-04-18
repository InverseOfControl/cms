package com.ymkj.cms.web.boss.facade.apply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ymkj.cms.biz.api.service.master.IBMSLimitChannelExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
@Component
public class ChannelProductFacade extends BaseFacade {
	
	@Autowired
	private IBMSProductExecuter iBMSProductExecuter;
	
	@Autowired
	private IBMSLimitChannelExecuter iBMSLimitChannelExecuter;
	
	@Autowired
	private IBMSChannelExecuter iBMSChannelExecuter;
	
	@Autowired
	private IBMSProductAuditLimitExecuter iBMSProductAuditLimitExecuter;
	
	/**
	 * 1. 获取所有渠道产品期限集合
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResBMSOrgLimitChannelVO> listLimitChannelBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		// 请求参数构造
		Response<ResProductBaseListVO> response = iBMSLimitChannelExecuter.listLimitChannelBy(reqBMSOrgLimitChannelVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData().getListOrgLimitChannel();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	/**
	 * 1. 获取所有渠道产品期限集合(优惠费率)
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResBMSOrgLimitChannelVO> listLimitChannelRateBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		// 请求参数构造
		Response<ResProductBaseListVO> response = iBMSLimitChannelExecuter.listLimitChannelRateBy(reqBMSOrgLimitChannelVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData().getListOrgLimitChannel();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	
	
	/**
	 * 1. 获取所有产品通过条件
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResBMSProductVO> findAllProduct(ReqBMSProductVO reqBMSProductVO) {
		// 请求参数构造
		reqBMSProductVO.setSysCode(EnumConstants.BMS_SYSCODE);
		ResListVO<ResBMSProductVO> response = iBMSProductExecuter.listBy(reqBMSProductVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	/**
	 * 获取渠道产品分页对象
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResult<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		PageResponse<ResBMSOrgLimitChannelVO> resonse = iBMSLimitChannelExecuter.listPage(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (resonse.isSuccess()) {
			PageResult<ResBMSOrgLimitChannelVO> pageResult = new PageResult<ResBMSOrgLimitChannelVO>();
			BeanUtils.copyProperties(resonse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(resonse));
		}				
	}
	
	/**
	 * 渠道产品更新是否可用状态
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean updateChannelLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = iBMSLimitChannelExecuter.updateChannelLimit(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(response));
				}
	}
	
	/**
	 * 根据产品Id获取审批期限
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public List<ResBMSProductAuditLimitVO> findLimitByProductId(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO){
		ResListVO<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.listProductLimitBy(reqBMSProductAuditLimitVO);
		// 响应结果处理
		if (response.isSuccess()) {
			return response.getCollections();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}		
	}
	
	/**
	 * 渠道产品期限保存
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Boolean saveOrgProductLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Response<ResBMSOrgLimitChannelVO> response = iBMSLimitChannelExecuter.saveProductLimit(reqBMSOrgLimitChannelVO);
		// 响应结果处理
		if (response.isSuccess()) {
				return new Boolean(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,
					this.getResMsg(response));
				}
	}
	
	
	/**
	 * 1. 获取所有渠道产品信息根据条件
	 * 2. 接口调用
	 * 3. 响应结果处理
	 * @param id
	 * @return
	 */
	public List<ResBMSOrgLimitChannelVO> listOrgLimitChannelBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		// 请求参数构造
		Response<ResProductBaseListVO> response = iBMSLimitChannelExecuter.listLimitChannelBy(reqBMSOrgLimitChannelVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData().getListOrgLimitChannel();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	/**
	 * 获取审批期限根据id
	 * @param reqBMSProductAuditLimitVO
	 * @return
	 */
	public ResBMSProductAuditLimitVO findAuditLimitById(ReqBMSProductAuditLimitVO reqBMSProductAuditLimitVO) {
		// 请求参数构造
		Response<ResBMSProductAuditLimitVO> response = iBMSProductAuditLimitExecuter.findById(reqBMSProductAuditLimitVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
	/**
	 * 渠道产品期限，启用判断，上层数据被禁用不可启用
	 * @param reqBMSLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日下午8:16:23
	 */
	public Map<String, Object> channelLimitDisableCheck(ReqBMSLimitChannelVO reqBMSLimitChannelVO) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		//渠道
		ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
		reqChannelVO.setId(Integer.valueOf(reqBMSLimitChannelVO.getChannelId().toString()));
		reqChannelVO.setSysCode(SystemCode.SysCode);
		Response<ResBMSChannelVO> responseChannel = iBMSChannelExecuter.getById(reqChannelVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseChannel.isSuccess()) {
			ResBMSChannelVO vo = responseChannel.getData();
			if(vo == null || vo.getIsDisabled() == 1L){
				resMap.put("isSuccess", false);
				resMap.put("message", "渠道被禁用");
				return resMap;
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(responseChannel));
		}
		//产品期限
		ReqBMSProductAuditLimitVO reqProductAuditLimitVO = new ReqBMSProductAuditLimitVO();
		reqProductAuditLimitVO.setAuditLimitId(reqBMSLimitChannelVO.getAuditLimitId());
		reqProductAuditLimitVO.setSysCode(SystemCode.SysCode);
		Response<ResBMSProductAuditLimitVO> responseProductAuditLimit = iBMSProductAuditLimitExecuter.findById(reqProductAuditLimitVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseProductAuditLimit.isSuccess()) {
			ResBMSProductAuditLimitVO vo = responseProductAuditLimit.getData();
			if(vo == null || vo.getIsDisabled() == 1L){
				resMap.put("isSuccess", false);
				resMap.put("message", "产品期限被禁用");
				return resMap;
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(responseProductAuditLimit));
		}
		resMap.put("isSuccess", true);
		return resMap;
	}
	/**
	 * 网点产品期限，启用判断，上层数据被禁用不可启用
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月24日下午5:07:59
	 */
	public Map<String, Object> orgProductLimitDisableCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		//渠道
		ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
		reqChannelVO.setId(Integer.valueOf(reqBMSOrgLimitChannelVO.getChannelId().toString()));
		reqChannelVO.setSysCode(SystemCode.SysCode);
		Response<ResBMSChannelVO> responseChannel = iBMSChannelExecuter.getById(reqChannelVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseChannel.isSuccess()) {
			ResBMSChannelVO vo = responseChannel.getData();
			if(vo == null || vo.getIsDisabled() == 1L){
				resMap.put("isSuccess", false);
				resMap.put("message", "渠道被禁用");
				return resMap;
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(responseChannel));
		}
		
		//产品期限
		ReqBMSProductAuditLimitVO reqProductAuditLimitVO = new ReqBMSProductAuditLimitVO();
		reqProductAuditLimitVO.setAuditLimitId(reqBMSOrgLimitChannelVO.getAuditLimitId());
		reqProductAuditLimitVO.setSysCode(SystemCode.SysCode);
		Response<ResBMSProductAuditLimitVO> responseProductAuditLimit = iBMSProductAuditLimitExecuter.findById(reqProductAuditLimitVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseProductAuditLimit.isSuccess()) {
			ResBMSProductAuditLimitVO vo = responseProductAuditLimit.getData();
			if(vo == null || vo.getIsDisabled() == 1L){
				resMap.put("isSuccess", false);
				resMap.put("message", "产品期限被禁用");
				return resMap;
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(responseProductAuditLimit));
		}
		
		//渠道产品期限启用判断
		Response<ResProductBaseListVO> responseLimitChannel = iBMSLimitChannelExecuter.listLimitChannelBy(reqBMSOrgLimitChannelVO);
		
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseLimitChannel.isSuccess()) {
			List<ResBMSOrgLimitChannelVO> voList = responseLimitChannel.getData().getListOrgLimitChannel();
			if(voList == null || voList.isEmpty()){
				resMap.put("isSuccess", false);
				resMap.put("message", "渠道产品期限配置不存在");
				return resMap;
			} else {
				ResBMSOrgLimitChannelVO vo= voList.get(0);
				if(vo == null || vo.getIsDisabled() == 1L){
					resMap.put("isSuccess", false);
					resMap.put("message", "渠道产品期限配置被禁用");
					return resMap;
				}
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(responseProductAuditLimit));
		}
		
		resMap.put("isSuccess", true);
		return resMap;
	}
}
