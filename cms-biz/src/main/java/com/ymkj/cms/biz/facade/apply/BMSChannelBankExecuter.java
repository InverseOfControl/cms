package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.apply.IBMSChannelBankExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.IBMSChannelBankService;
import com.ymkj.cms.biz.service.master.IBMSBankService;
import com.ymkj.cms.biz.service.master.IBMSChannelService;

/**
 * 服务提供者demo: 1. 请求信息验证 2. 调用业务层实现业务操作 3. 封装 响应response 4. 对于必须捕获的异常采用 参考
 * listPage 的用法
 * 
 * @author user
 * 
 */
@Service
public class BMSChannelBankExecuter implements IBMSChannelBankExecuter {

	@Autowired
	private IBMSChannelBankService channelBankService;
	@Autowired
	private IBMSChannelService channelService;
	@Autowired
	private IBMSBankService bankService;

	@Override
	public PageResponse<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO) {
		PageResponse<ResBMSChannelBankVO> response = new PageResponse<ResBMSChannelBankVO>();

		// 参数校验
		if (reqDemoVO.getPageNum() == 0 || reqDemoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}

		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqDemoVO.getPageNum(), reqDemoVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqDemoVO);
			// 调用业务逻辑
			PageBean<BMSChannelBank> pageBean = channelBankService.listPage(pageParam, paramMap,"listPageByParam","countPageByParam");
			// 构造响应参数
			List<ResBMSChannelBankVO> records = new ArrayList<ResBMSChannelBankVO>();
			List<BMSChannelBank> demoEntitys = pageBean.getRecords();
			for (BMSChannelBank demoEntity : demoEntitys) {
				ResBMSChannelBankVO resDemoVO = new ResBMSChannelBankVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelBankVO> addChannelBank(ReqBMSChannelBankVO reqDemoVO) {
		Response<ResBMSChannelBankVO> response = new Response<ResBMSChannelBankVO>();
		BMSChannelBank channelBank = new BMSChannelBank();
		channelBank.setSysCode(reqDemoVO.getSysCode());
		BeanUtils.copyProperties(reqDemoVO, channelBank);
		channelBank.setIsDeleted((long) 0);
		boolean flag = channelBankService.insert(channelBank);
		Integer count=null;
		if(flag){
			count=1;
		}
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSChannelBankVO> updateChannelBank(ReqBMSChannelBankVO reqDemoVO) {
		Response<ResBMSChannelBankVO> response = new Response<ResBMSChannelBankVO>();
		BMSChannelBank channelBank = new BMSChannelBank();
		channelBank.setSysCode(reqDemoVO.getSysCode());
		BeanUtils.copyProperties(reqDemoVO, channelBank);
		boolean flag=channelBankService.update(channelBank);
		if(flag){
			response.setRepMsg("true");
		}
		return response;
	}

	@Override
	public Response<ResBMSChannelBankVO> findOne(ReqBMSChannelBankVO reqDemoVO) {
		Response<ResBMSChannelBankVO> response = new Response<ResBMSChannelBankVO>();
		BMSChannelBank channelBank = channelBankService.getById(reqDemoVO.getId());
		// 构造相应参数
		if (channelBank != null) {
			ResBMSChannelBankVO resDemoVO = new ResBMSChannelBankVO();
			BeanUtils.copyProperties(channelBank, resDemoVO);
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
	public ResListVO<ResBMSBankVO> getBank(ReqBMSBankVO reqDemoVO) {

		List<ResBMSBankVO> records = new ArrayList<ResBMSBankVO>();
		try {

			// 构造响应参数
			List<BMSBank> demoEntitys = bankService.listBy(new HashMap<String, Object>());

			for (BMSBank demoEntity : demoEntitys) {
				ResBMSBankVO resDemoVO = new ResBMSBankVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}

		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return new ResListVO<ResBMSBankVO>(records);
	}

	@Override
	public Response<ResBMSCheckIsExitsVO> checkIsExits(ReqBMSChannelBankVO reqBMSChannelBankVO) {
		ResBMSCheckIsExitsVO resBMSCheckIsExitsVO = new ResBMSCheckIsExitsVO();
		Response<ResBMSCheckIsExitsVO> response = new Response<ResBMSCheckIsExitsVO>();
		try{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("channelId", reqBMSChannelBankVO.getChannelId());
			map.put("bankId", reqBMSChannelBankVO.getBankId());
			boolean flag=channelBankService.checkIsExits(map);
			if(flag){//存在
				resBMSCheckIsExitsVO.setIsFlag("1");
			}else{//不存在
				resBMSCheckIsExitsVO.setIsFlag("0");
			}
			response.setData(resBMSCheckIsExitsVO);
		}catch(Exception e){
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<Integer> checkParentIsStart(ReqBMSChannelBankVO reqDemoVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", reqDemoVO.getBankId());
		//map.put("isDisabled", reqDemoVO.getIsDisabled());
		Integer a=channelBankService.checkParentIsStart(map);
		Response<Integer> response = new Response<Integer>();
		response.setData(a);
		return response;
	}

	
	@Override
	public Response<Integer> checkParentIsChanel(ReqBMSChannelBankVO reqDemoVO) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", reqDemoVO.getChannelId());
			//map.put("isDisabled", reqDemoVO.getIsDisabled());
			Integer a=channelBankService.checkParentIsChanel(map);
			Response<Integer> response = new Response<Integer>();
			response.setData(a);
			return response;
	 }
}
