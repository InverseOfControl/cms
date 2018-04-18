package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.IBMSChannelBankService;
import com.ymkj.cms.biz.service.master.IBMSChannelService;
import com.ymkj.cms.biz.service.master.IBMSLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;
import com.ymkj.cms.biz.service.master.IBMSProductAuditLimitService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

public class BMSChannelExecuter implements IBMSChannelExecuter {

	@Autowired
	private IBMSChannelService channelService;
	@Autowired
	private IBMSChannelBankService channelBankService;
	@Autowired
	private IBMSLimitChannelService limitChannelService;
	@Autowired
	private IBMSOrgLimitChannelService orgLimitChannelService;
	@Autowired
	private IBMSSysLogService sysLogService;
	@Autowired
	private IBMSProductAuditLimitService productAuditLimitService;
	
	public ResListVO<ResBMSChannelVO> getAllChannel(ReqBMSChannelVO reqChannelVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSChannelVO> list = new ResListVO<ResBMSChannelVO>();
		try {
			paramMap = BeanKit.bean2map(reqChannelVO);
			List<BMSChannel> allList = channelService.findBy(paramMap);
			List<ResBMSChannelVO> paramList = new ArrayList<ResBMSChannelVO>();
			for (BMSChannel e : allList) {
				ResBMSChannelVO vo = new ResBMSChannelVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}

		return list;

	}

	public PageResponse<ResBMSChannelVO> listPage(ReqBMSChannelVO reqChannelVO) {

		PageResponse<ResBMSChannelVO> response = new PageResponse<ResBMSChannelVO>();

		// 参数校验
		if (reqChannelVO.getPageNum() == 0 || reqChannelVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqChannelVO.getPageNum(), reqChannelVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqChannelVO);

			// 调用业务逻辑
			PageBean<BMSChannel> pageBean = channelService.listPage(pageParam, paramMap,"queryChannelList","queryChannelCount");

			// 构造响应参数
			List<ResBMSChannelVO> records = new ArrayList<ResBMSChannelVO>();
			List<BMSChannel> demoEntitys = pageBean.getRecords();
			for (BMSChannel demoEntity : demoEntitys) {
				ResBMSChannelVO resDemoVO = new ResBMSChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			// System.out.println(e);
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelVO> addChannel(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = new BMSChannel();
		BeanUtils.copyProperties(reqChannelVO, channel);
		reqChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		channel.setSysCode(reqChannelVO.getSysCode());
		long count = this.channelService.insert(channel);
		int result=sysLogService.recordSysLog(reqChannelVO, "产品管理|渠道配置|insert|null", "addChannel",
				"添加渠道配置<" + reqChannelVO.getName() + ">");
		if(count>0 && result>0){
			response.setRepMsg(String.valueOf(count));
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelVO> deleteChannel(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = new BMSChannel();
		BeanUtils.copyProperties(reqChannelVO, channel);
		reqChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		channel.setSysCode(reqChannelVO.getSysCode());
		this.channelService.delete(channel);
		int result=sysLogService.recordSysLog(reqChannelVO, "产品管理|渠道配置|delete|null", "deleteChannel",
				"删除渠道配置<" + reqChannelVO.getName() + ">");
		if(result>0){
			response.setRepMsg("true");
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelVO> updateChannel(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = new BMSChannel();
		BeanUtils.copyProperties(reqChannelVO, channel);
		reqChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		channel.setSysCode(reqChannelVO.getSysCode());
		this.channelService.update(channel);
		//禁用时，级联禁用，启用不用级联
		if(channel.getIsDisabled() != null && channel.getId() != 0 && channel.getIsDisabled() != 0){
			//渠道被禁用，级联下级配置被禁用，目前下级是：渠道产品配置，网点产品配置
			//获取所有关联渠道产品配置
			Integer channelId = channel.getId();
			Long isdisabled = channel.getIsDisabled();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("channelId", channelId.toString());	
			paramMap.put("isdisabled", isdisabled);	
			//下级是：渠道产品配置
			channelService.disabledLimitChannel(paramMap);
			//下级是：网点产品配置
			channelService.disabledOrgLimitChannel(paramMap);
			//下级是：签约银行
			channelService.disabledChannelBank(paramMap);
		}
		//优惠配置，级联禁用，启用不用级联
		if(channel.getIsDisabled() != null && channel.getId() != 0 && channel.getIsCanPreferential() != 0){
			//渠道优惠配置被禁用，级联下级优惠配置被禁用，目前下级是：网点产品配置
			//获取所有关联渠道产品配置
			Integer channelId = channel.getId();
			Long isCanPreferential = channel.getIsCanPreferential();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("channelId", channelId.toString());	
			paramMap.put("isCanPreferential", isCanPreferential);	
			//下级是：网点产品配置
			channelService.disabledOrgLimitChannel(paramMap);
		}
		
		
		
		int result=sysLogService.recordSysLog(reqChannelVO, "产品管理|渠道配置|update|null", "updateChannel",
				"更新渠道配置<" + reqChannelVO.getName() + ">");
		if(result>0){
			response.setRepMsg("true");
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelVO> getChannelById(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = this.channelService.getById(reqChannelVO.getId());
		// 构造相应参数
		if (channel != null) {
			ResBMSChannelVO resDemoVO = new ResBMSChannelVO();
			BeanUtils.copyProperties(channel, resDemoVO);
			response.setData(resDemoVO);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO) {
		List<ResBMSChannelVO> records = new ArrayList<ResBMSChannelVO>();

		try {

			// 构造响应参数
			List<BMSChannel> demoEntitys = channelService.listBy(new HashMap<String, Object>());

			for (BMSChannel demoEntity : demoEntitys) {
				ResBMSChannelVO resDemoVO = new ResBMSChannelVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}

		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return new ResListVO<ResBMSChannelVO>(records);
	}

	@Override
	public Response<ResBMSChannelVO> findOne(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = this.channelService.findOne(reqChannelVO);
		// 构造相应参数
		if (channel != null) {
			ResBMSChannelVO resDemoVO = new ResBMSChannelVO();
			BeanUtils.copyProperties(channel, resDemoVO);
			response.setData(resDemoVO);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSChannelVO> getChannelByUserCode(
			ReqBMSChannelVO reqChannelVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSChannelVO> list = new ResListVO<ResBMSChannelVO>();
		//TODO: 通过客户code到权限系统查询出门店id,根据门店id获取数据
		try {
			paramMap = BeanKit.bean2map(reqChannelVO);
			List<BMSChannel> allList = channelService.getChannelByOrgId(paramMap);
			List<ResBMSChannelVO> paramList = new ArrayList<ResBMSChannelVO>();
			for (BMSChannel e : allList) {
				ResBMSChannelVO vo = new ResBMSChannelVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}

		return list;

	}

	@Override
	public ResListVO<ResBMSChannelVO> getChannelByOrgId(
			ReqBMSChannelVO reqChannelVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSChannelVO> list = new ResListVO<ResBMSChannelVO>();
		//TODO: 通过客户code到权限系统查询出门店id,根据门店id获取数据
		try {
			paramMap = BeanKit.bean2map(reqChannelVO);
			List<BMSChannel> allList = channelService.getChannelByOrgId(paramMap);
			List<ResBMSChannelVO> paramList = new ArrayList<ResBMSChannelVO>();
			for (BMSChannel e : allList) {
				ResBMSChannelVO vo = new ResBMSChannelVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}

		return list;

	}

	@Override
	public Response<List<ResTrialBeforeCreditChannelVO>> getChannelByProTermLmt(
			ReqTrialBeforeCreditChannelVO reqTrialBeforeCreditChannelVO) {

		boolean bool = false;
		Response<List<ResTrialBeforeCreditChannelVO>> response = new Response<List<ResTrialBeforeCreditChannelVO>>();
		List<ResTrialBeforeCreditChannelVO> records = new ArrayList<ResTrialBeforeCreditChannelVO>();
		Map<String,Object> paramMap = null;

		// 参数校验
		Response<Object> validateResponse = Validate.getInstance().validate(reqTrialBeforeCreditChannelVO);
		if (validateResponse.isSuccess()) {
			bool = true;
		}else{
			bool = false;
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg());
		}
		
		reqTrialBeforeCreditChannelVO.setIsCanPreferential(("Y".equals(reqTrialBeforeCreditChannelVO.getIfPreferentialUser()))?"0":"1");

		if(bool){
			try {
				paramMap = BeanKit.bean2map(reqTrialBeforeCreditChannelVO);
				if("".equals(paramMap.get("orgIdList"))){
					paramMap.put("orgIdList",null);
				}
			} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {}

			List<BMSChannel> channelList = channelService.getChannelByProTermLmt(paramMap);
			for (BMSChannel bmsChannel : channelList) {
				ResTrialBeforeCreditChannelVO resTrialBeforeCreditChannelVO = new ResTrialBeforeCreditChannelVO();
				BeanUtils.copyProperties(bmsChannel, resTrialBeforeCreditChannelVO);
				records.add(resTrialBeforeCreditChannelVO);
			}
			response.setData(records);
		}

		return response;
	}
	
	@Override
	public Response<List<ResBMSChannelVO>> getChannelByOrgProAlt(
			ReqLoanContractSignVo reqLoanContractSignVo) {
		Response<List<ResBMSChannelVO>> response = new Response<List<ResBMSChannelVO>>();
		
		if(reqLoanContractSignVo.getOwningBranchId()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "owningBranchId");
		}
		if(StringUtils.isBlank(reqLoanContractSignVo.getProductCd())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "productCd");
		}
		if(reqLoanContractSignVo.getAccLmt()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "accLmt");
		}
		
		List<ResBMSChannelVO> records = new ArrayList<ResBMSChannelVO>();
		Map<String,Object> paramMap = null;
		reqLoanContractSignVo.setIsCanPreferential(("Y".equals(reqLoanContractSignVo.getIfPreferentialUser()))?"0":"1");
		try {
			paramMap = BeanKit.bean2map(reqLoanContractSignVo);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {}
		

		List<BMSChannel> channelList = channelService.getChannelByOrgProAlt(paramMap);
		for (BMSChannel bmsChannel : channelList) {
			ResBMSChannelVO resBMSChannelVO = new ResBMSChannelVO();
			BeanUtils.copyProperties(bmsChannel, resBMSChannelVO);
			records.add(resBMSChannelVO);
		}
		response.setData(records);
		return response;
	}

	@Override
	public Response<ResBMSCheckIsExitsVO> checkIsChennelExits(ReqBMSChannelVO reqDemoVO) {
		Map<String, Object> paramMap=null;
		Response<ResBMSCheckIsExitsVO> reponse= new Response<ResBMSCheckIsExitsVO>();
		ResBMSCheckIsExitsVO resBMSCheckIsExitsVO = new ResBMSCheckIsExitsVO();
		boolean flag=false;
		try {
			paramMap = BeanKit.bean2map(reqDemoVO);
			int result=channelService.checkIsChennelExits(paramMap);
			if(result>0){
				resBMSCheckIsExitsVO.setIsFlag("1");
			}else{
				resBMSCheckIsExitsVO.setIsFlag("0");
			}
			reponse.setData(resBMSCheckIsExitsVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return reponse;
	}

	@Override
	public Response<ResBMSChannelVO> deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		BMSChannel channel = new BMSChannel();
		if(reqBMSChannelVO.getChannelId()==null || reqBMSChannelVO.getChannelId().length() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "channelId");
		}
		BeanUtils.copyProperties(reqBMSChannelVO, channel);
		reqBMSChannelVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		channel.setSysCode(reqBMSChannelVO.getSysCode());
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("channelId", reqBMSChannelVO.getChannelId());
		//签约银行配置查找
		List<BMSChannelBank> channelBankList= channelBankService.listBy(paramMap);
		if(!channelBankList.isEmpty()){
			response.setRepMsg("false");
			return response;
		}
		//渠道产品配置查找
		List<BMSLimitChannel> limitChannelList = limitChannelService.listBy(paramMap);
		if(!limitChannelList.isEmpty()){
			response.setRepMsg("false");
			return response;
		}
		//网点产品配置查找
		List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelService.listBy(paramMap);
		if(!orgLimitChannelList.isEmpty()){
			response.setRepMsg("false");
			return response;
		}
		response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSChannelVO> getById(ReqBMSChannelVO reqChannelVO) {
		Response<ResBMSChannelVO> response = new Response<ResBMSChannelVO>();
		if(reqChannelVO.getId()==0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "id");
		}
		ResBMSChannelVO vo = new ResBMSChannelVO();
		BMSChannel channel = channelService.getById(reqChannelVO.getId());
		BeanUtils.copyProperties(channel, vo);
		response.setData(vo);
		response.setRepCode(EnumConstants.RES_CODE_SUCCESS);
		response.setRepMsg("true");
		return response;
	}

	@Override
	public ResListVO<ResBMSChannelVO> findChannelEqDate(ReqBMSChannelVO reqChannelVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSChannelVO> list = new ResListVO<ResBMSChannelVO>();
		try {
			paramMap = BeanKit.bean2map(reqChannelVO);
			List<BMSChannel> allList = channelService.findChannelEqDate(paramMap);
			List<ResBMSChannelVO> paramList = new ArrayList<ResBMSChannelVO>();
			for (BMSChannel e : allList) {
				ResBMSChannelVO vo = new ResBMSChannelVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}

		return list;
	}
	
	@Override
	public Response<Object> isExistInBMS(ReqBMSLoanBaseVO loanBaseVO) {
		Response<Object> response = new Response<Object>();
		String loanNo = loanBaseVO.getLoanNo();
		
		if(StringUtils.isBlank(loanNo)){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, "loanNo");
		}
		
		int count = channelService.isExistInBMS(loanNo);
		if(count > 0){
			response.setData("true");
		}else{
			response.setData("false");
		}
		
		return response;
	}
	
	@Override
	public ResListVO<ResBMSChannelVO> findChannelByOrgIds(ReqBMSChannelVO reqDemoVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSChannelVO> list = new ResListVO<ResBMSChannelVO>();
		try {
			paramMap = BeanKit.bean2map(reqDemoVO);
			List<Object> allList = channelService.listBy(paramMap, "queryChannelByOrgIds");
			List<ResBMSChannelVO> paramList = new ArrayList<ResBMSChannelVO>();
			for (Object e : allList) {
				ResBMSChannelVO vo = new ResBMSChannelVO();
				BeanUtils.copyProperties(e, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException
				| IntrospectionException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
