package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSBankService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;

@Service
public class BMSBankExecuter implements IBMSBankExecuter {
	@Autowired
	private IBMSBankService bmsBankService;
	@Autowired
	private IBMSSysLogService sysLogService;

	@Override
	public PageResponse<ResBMSBankVO> listPage(ReqBMSBankVO reqBankVO) {
		PageResponse<ResBMSBankVO> response = new PageResponse<ResBMSBankVO>();
		// 参数校验
		if (reqBankVO.getPageNum() == 0 || reqBankVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			reqBankVO.setIsDeleted((long) 0);
			PageParam pageParam = new PageParam(reqBankVO.getPageNum(), reqBankVO.getPageSize());

			Map<String, Object> paramMap = BeanKit.bean2map(reqBankVO);

			// 调用业务逻辑,得到list集合
			PageBean<BMSBank> pageBean = bmsBankService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSBankVO> records = new ArrayList<ResBMSBankVO>();
			List<BMSBank> demoEntitys = pageBean.getRecords();
			for (BMSBank demoEntity : demoEntitys) {
				ResBMSBankVO resDemoVO = new ResBMSBankVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/* System.out.println(e); */
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSBankVO> addBank(ReqBMSBankVO reqBankVO) {
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		BMSBank bank = new BMSBank();
		BeanUtils.copyProperties(reqBankVO, bank);
		long count = this.bmsBankService.insert(bank);
		if (count > 0) {
		sysLogService.recordSysLog(reqBankVO, "公共管理|银行列表|insert|null", "IBMSBankService.insert",
					"添加银行<" + reqBankVO.getName() + ">");

		}
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSBankVO> deleteBank(ReqBMSBankVO reqBankVO) {
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		BMSBank bank = new BMSBank();
		BeanUtils.copyProperties(reqBankVO, bank);
		this.bmsBankService.delete(bank);
		sysLogService.recordSysLog(reqBankVO, "公共管理|银行列表|delete|null", "IBMSBankService.delete",
				"删除银行<" + reqBankVO.getName() + ">");
		response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSBankVO> updateBank(ReqBMSBankVO reqBankVO) {
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		BMSBank bank = new BMSBank();
		BeanUtils.copyProperties(reqBankVO, bank);
		if(reqBankVO.getIsDisabled()==1){
			Boolean hasB=bmsBankService.findChannelBankById(reqBankVO);
			if(hasB){
				bmsBankService.accordingBankIdUpdateChannelBank(reqBankVO);
			}
		}
		this.bmsBankService.update(bank);
		sysLogService.recordSysLog(reqBankVO, "公共管理|银行列表|update|null", "IBMSBankService.update",
				"修改银行<" + reqBankVO.getName() + ">");
		response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSBankVO> getBankById(ReqBMSBankVO reqBankVO) {
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		BMSBank bank = this.bmsBankService.getById(reqBankVO.getId());
		// 构造相应参数
		if (bank != null) {
			ResBMSBankVO resDemoVO = new ResBMSBankVO();
			BeanUtils.copyProperties(bank, resDemoVO);
			response.setData(resDemoVO);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSBankVO> getAllBank(ReqBMSBankVO reqBankVO) {
		Map<String, Object> paramMap;
		ResListVO<ResBMSBankVO> listResult = new ResListVO<ResBMSBankVO>();
		try {
			paramMap = BeanKit.bean2map(reqBankVO);
			List<BMSBank> allList = bmsBankService.listBy(paramMap);
			List<ResBMSBankVO> paramList = new ArrayList<ResBMSBankVO>();
			for (BMSBank bank : allList) {
				ResBMSBankVO vo = new ResBMSBankVO();
				BeanUtils.copyProperties(bank, vo);
				paramList.add(vo);
			}
			listResult.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			e.printStackTrace();
		}

		return listResult;
	}

	@Override
	public Response<ResBMSBankVO> findOne(ReqBMSBankVO reqBankVO) {
		Response<ResBMSBankVO> response = new Response<ResBMSBankVO>();
		BMSBank bank = this.bmsBankService.findOne(reqBankVO);
		// 构造相应参数
		if (bank != null) {
			ResBMSBankVO resDemoVO = new ResBMSBankVO();
			BeanUtils.copyProperties(bank, resDemoVO);
			response.setData(resDemoVO);
		}

		return response;
	}

	@Override
	public ResListVO<ResBMSBankVO> getBankByChannelId(ReqBMSBankVO reqBankVO) {
		if (reqBankVO.getChannelId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "channelId" });
		}
		Map<String, Object> paramMap;
		ResListVO<ResBMSBankVO> list = new ResListVO<ResBMSBankVO>();
		try {
			paramMap = BeanKit.bean2map(reqBankVO);
			List<BMSBank> allList = bmsBankService.getBankByChannelId(paramMap);
			List<ResBMSBankVO> paramList = new ArrayList<ResBMSBankVO>();
			for (BMSBank bank : allList) {
				ResBMSBankVO vo = new ResBMSBankVO();
				BeanUtils.copyProperties(bank, vo);
				paramList.add(vo);
			}
			list.setParamList(paramList);
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Response<ResBMSCheckIsExitsVO> checkBankIsExits(ReqBMSBankVO reqBankVO) {
		Response<ResBMSCheckIsExitsVO> response=null;
		ResBMSCheckIsExitsVO checkIsExitsVO=null;
		try {
			response=new Response<ResBMSCheckIsExitsVO>();
			checkIsExitsVO = new ResBMSCheckIsExitsVO();
			Map<String, Object> paramMap = BeanKit.bean2map(reqBankVO);
			boolean flag=bmsBankService.checkBankIsExits(paramMap);
			if(flag){
				checkIsExitsVO.setIsFlag("1");
			}else{
				checkIsExitsVO.setIsFlag("0");
			}
			response.setData(checkIsExitsVO);
		} catch (Exception e) {

		}
		return response;
	}

	@Override
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO) {
		boolean flag=bmsBankService.findChannelBankById(reqBankVO);
		return flag;
	}

}
