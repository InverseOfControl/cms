package com.ymkj.cms.biz.facade.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ymkj.cms.biz.api.service.master.IBMSAppPersonInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
@Service
public class BMSAppPersonInfoExecuter implements IBMSAppPersonInfoExecuter {

	@Autowired
	private IBMSAppPersonInfoService BMSAppPersonInfoService;
	@Override
	public PageResponse<ResBMSAppPersonInfoVO> listPage(ReqBMSAppPersonInfoVO reqBMSAppPersonInfoVO) {
		PageResponse<ResBMSAppPersonInfoVO> response = new PageResponse<ResBMSAppPersonInfoVO>();
		// 参数校验
		if (reqBMSAppPersonInfoVO.getPageNum() == 0 || reqBMSAppPersonInfoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSAppPersonInfoVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSAppPersonInfoVO.getPageNum(), reqBMSAppPersonInfoVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSAppPersonInfoVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSAppPersonInfo> pageBean = BMSAppPersonInfoService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSAppPersonInfoVO> records = new ArrayList<ResBMSAppPersonInfoVO>();
			List<BMSAppPersonInfo> Entitys = pageBean.getRecords();
			for (BMSAppPersonInfo Entity : Entitys) {
				ResBMSAppPersonInfoVO resDemoVO = new ResBMSAppPersonInfoVO();
				BeanUtils.copyProperties(Entity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/*System.out.println(e);*/
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSAppPersonInfoVO> updateByLoanNo(ReqBMSAppPersonInfoVO reqBMSAppPersonInfoVO) {
		Response<ResBMSAppPersonInfoVO> response=new Response<ResBMSAppPersonInfoVO>();
		try {
			Map<String,Object> map= BeanKit.bean2map(reqBMSAppPersonInfoVO);
			BMSAppPersonInfoService.updateByLoanNo(map);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		return response;
	}
	@Override
	public Response<List<Map<String, Object>>> queryAdditionRecords(Map<String, String> paramMap) {
		Response<List<Map<String, Object>>> response=new Response<List<Map<String, Object>>>();
		try {
			List<Map<String, Object>> datas=BMSAppPersonInfoService.queryAdditionRecords(paramMap);
			response.setData(datas);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	
	@Override
	public Response<Integer> updatePhoneCellStatus(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		Response<Integer> response=new Response<Integer>();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		try {
			List<Map<String,Object>> datas=BMSAppPersonInfoService.qyeryPhoneCellStatus(map);
			if(datas==null||datas.size()<=0){
				throw new BizException(CoreErrorCode.DB_LIST_IS_NULL);
			}
			Map<String,Object> data=datas.get(0);
			String phoneNum=String.valueOf(map.get("phoneNum"));
			String cellPhone=String.valueOf(data.get("CELLPHONE"));
			String cellphoneSec=String.valueOf(data.get("CELLPHONE_SEC"));
			if (cellPhone.equals(phoneNum)) {
				paramMap.put("cellPhoneStatus", "1");
				paramMap.put("cellPhoneTime", sdf.format(new Date()));
			} else if (cellphoneSec.equals(phoneNum)) {
				paramMap.put("cellPhoneSecStatus", "1");
				paramMap.put("cellPhoneSecTime", sdf.format(new Date()));
			}
			paramMap.put("loanNo", map.get("appNo"));
			Integer backData= BMSAppPersonInfoService.updatePhoneCellStatus(paramMap);
			response.setData(backData);
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

}
