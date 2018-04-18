package com.ymkj.cms.web.boss.facade.apply;

import java.util.ArrayList;
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
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationTreeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Component
public class OrgLimitChannelFacade extends BaseFacade{

	@Autowired
	private IBMSOrgLimitChannelExecuter bmsOrgChannelExecuter;
	
	//调用平台系统接口根据营业部名称获取营业部信息
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	public ResOrganizationVO findByName(ReqOrganizationVO reqOrganizationVo) {
		// 请求参数构造
		/*reqOrganizationVo.setBizType("bms");*/
		reqOrganizationVo.setSysCode(SystemCode.SysCode);
		/*Response<ResOrganizationVO> response = new Response<ResOrganizationVO>();;*/
		// 接口调用
		Response<ResOrganizationVO> response = iOrganizationExecuter.findDeptByName(reqOrganizationVo);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	public ResOrganizationTreeVO findOrgTree(ReqParamVO reqParamVO) {
		// 请求参数构造
		/*reqOrganizationVo.setBizType("bms");
		reqOrganizationVo.setSysCode(SystemCode.SysCode);
		Response<ResOrganizationVO> response = new Response<ResOrganizationVO>();;*/
		reqParamVO.setSysCode(SystemCode.SysCode);
		
		// 接口调用
		Response<ResOrganizationTreeVO> response = iOrganizationExecuter.findAllOrgTreeByOrgTypes(reqParamVO);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	/**
	 * 依据产品，查询配置的对应可用签约网点  ,多产品取交集
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月24日下午3:57:55
	 */
	public List<ResOrganizationVO> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		reqBMSOrgLimitChannelVO.setSysCode(SystemCode.SysCode);
		// 接口调用,获取机构id
		ResListVO<ResBMSOrgLimitChannelVO> responseOrgLimitChanne = bmsOrgChannelExecuter
				.findOrgByProductCodeListIntersect(reqBMSOrgLimitChannelVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (responseOrgLimitChanne.isSuccess()) {
			// 参数组装
			List<Long> orgIds = new ArrayList<Long>();
			for (ResBMSOrgLimitChannelVO vo : responseOrgLimitChanne.getCollections()) {
				orgIds.add(vo.getOrgId());
			}
			ReqParamVO reqParamVO = new ReqParamVO();
			reqParamVO.setSysCode(SystemCode.SysCode);
			reqParamVO.setOrgIds(orgIds);
			// 机构名称填充
			Response<List<ResOrganizationVO>> responseOrganization = iOrganizationExecuter.findByIds(reqParamVO);
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (responseOrganization.isSuccess()) {
				return responseOrganization.getData();
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(responseOrganization));
			}
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(responseOrgLimitChanne));
		}
	}
	
	/**
	 * 校验产品-网点是否失效
	 * @param reqBMSOrgLimitChannelVOList
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日下午2:21:36
	 */
	public Map<String, Object> branchProductRelevanceCheck(List<ReqBMSOrgLimitChannelVO> reqBMSOrgLimitChannelVOList) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> errorVoList = new ArrayList<Map<String,Object>>();
		for (ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO : reqBMSOrgLimitChannelVOList) {
			// 接口调用
			Response<ResBMSOrgLimitChannelVO> response = bmsOrgChannelExecuter.branchProductRelevanceCheck(reqBMSOrgLimitChannelVO);
			
			// 响应结果处理, 如果失败 则抛出 处理失败异常
			if (response.isSuccess()) {
				ResBMSOrgLimitChannelVO  resvo= response.getData();
				
				if(resvo.getOrgId() != null){
					Map<String, Object> mapVo = new HashMap<String, Object>();
					mapVo.put("isFailed", true);
					mapVo.put("orgId", resvo.getOrgId());
					mapVo.put("productCode", resvo.getProductCode());
					mapVo.put("loanNo", resvo.getLoanNo());
					errorVoList.add(mapVo);
				}
			} else {
				throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
			}
		}
		if(errorVoList.isEmpty()){
			map.put("isSuccess", true);
		} else {
			map.put("isFailed", true);
			map.put("errorVoList", errorVoList);
		}
		return map;
		
	}
	/**
	 * 依据id查找机构信息
	 * @param vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月30日下午7:50:10
	 */
	public ResOrganizationVO findOrgById(ReqOrganizationVO reqOrganizationVo) {
		// 请求参数构造
		reqOrganizationVo.setSysCode(SystemCode.SysCode);
		// 接口调用
		Response<ResOrganizationVO> response = iOrganizationExecuter.findById(reqOrganizationVo);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
	/**
	 * 查询所有机构
	 * @param reqOrganizationVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月30日下午8:11:15
	 */
	public List<ResOrganizationVO> findAllDepts(ReqOrganizationVO reqOrganizationVo) {
		// 请求参数构造
		reqOrganizationVo.setSysCode(SystemCode.SysCode);
		// 接口调用
		Response<List<ResOrganizationVO>> response = iOrganizationExecuter.findAllDepts(reqOrganizationVo);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}
		public PageResult<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqDemoVO) {
		 reqDemoVO.setSysCode(EnumConstants.BMS_SYSCODE); 
		// 业务调用
		PageResponse<ResBMSOrgLimitChannelVO> pageResponse = bmsOrgChannelExecuter.listPage(reqDemoVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSOrgLimitChannelVO> pageResult = new PageResult<ResBMSOrgLimitChannelVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	public int updateLimitChannelById(ReqBMSOrgLimitChannelVO reqLimitChannelVo){
		 Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		 response=bmsOrgChannelExecuter.updateByOrgLimitId(reqLimitChannelVo);

		 // 响应结果处理, 如果失败 则抛出 处理失败异常
		 if (response.isSuccess()) {
			 return Integer.valueOf(response.getRepMsg());
		 } else {
			 throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		 }
	 }
	public int updateProductTerm(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO){
		Response<ResBMSOrgLimitChannelVO> response = new Response<ResBMSOrgLimitChannelVO>();
		response= bmsOrgChannelExecuter.logicalDelete(reqBMSOrgLimitChannelVO);
		if (response.isSuccess()) {
			return Integer.valueOf(response.getRepMsg());
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL,this.getResMsg(response));
		}
	}
}
