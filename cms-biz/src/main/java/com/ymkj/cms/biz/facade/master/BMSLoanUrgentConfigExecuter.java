package com.ymkj.cms.biz.facade.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSLoanUrgentConfigExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSLoanUrgentConfig;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.qualitytest.QualityInspectionSheetExecuter;
import com.ymkj.cms.biz.service.master.IBMSBankService;
import com.ymkj.cms.biz.service.master.IBMSLoanUrgentConfigServic;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class BMSLoanUrgentConfigExecuter implements IBMSLoanUrgentConfigExecuter{
	
	@Autowired
	private IBMSLoanUrgentConfigServic iBMSLoanUrgentConfigServic;
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	@Override
	public PageResponse<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO) {
		PageResponse<ResBMSLoanUrgentConfigVO> response = new PageResponse<ResBMSLoanUrgentConfigVO>();
		// 参数校验
		if (reqOrgVO.getPageNum() == 0 || reqOrgVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			ReqOrganizationVO v=new ReqOrganizationVO();
			v.setSysCode("bms");
			v.setPageNum(reqOrgVO.getPageNum());
			v.setPageSize(reqOrgVO.getPageSize());
			v.setId(reqOrgVO.getOrgId());
			v.setLoginUser(reqOrgVO.getUserCode());
			//分页查询营业部  平台提供接口
			PageResponse<ResOrganizationVO> pages=iOrganizationExecuter.findAllDeptsByParam(v);
			
			
			//定义集合存储分页查询营业部的ID
			List<Long> listOrgids=new ArrayList<Long>();
			List<ResOrganizationVO> listResOrganizationVO=pages.getRecords();
			for(ResOrganizationVO resOrganizationVO:listResOrganizationVO){
				listOrgids.add(resOrganizationVO.getId());
			}
			//如果限定时间没有值默认当前月
			if(null==reqOrgVO.getLimitDate()||reqOrgVO.getLimitDate().length()==0){
				Date date =new Date();
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
				reqOrgVO.setLimitDate(sdf.format(date));
			}
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("listOrgids", listOrgids);
			map.put("limitDate", reqOrgVO.getLimitDate());
			//根据平台系统传来的分页的信息，拿分页所有的ID查询加急限制表
			List<BMSLoanUrgentConfig> listBmsLoanUrgentConfig=iBMSLoanUrgentConfigServic.selectAllBmsLoanUrgentConfigs(map);
			if(listBmsLoanUrgentConfig!=null&&listBmsLoanUrgentConfig.size()>0){
				response.setPageCount(pages.getPageCount());
				response.setPageNum(pages.getPageNum());
				response.setPageSize(pages.getPageSize());
				response.setTotalCount(pages.getTotalCount());
				List<ResBMSLoanUrgentConfigVO> resBMSLoanUrgentConfigVO=new ArrayList<ResBMSLoanUrgentConfigVO>();
				for(ResOrganizationVO resOrganizationVO:listResOrganizationVO){
					ResBMSLoanUrgentConfigVO vo=new ResBMSLoanUrgentConfigVO();
					vo.setOrgId(resOrganizationVO.getId());
					vo.setOrgName(resOrganizationVO.getName());
					vo.setLimitTime(reqOrgVO.getLimitDate());
					vo.setUrgentCount(EnumConstants.LOAN_URGENT_CONFIG_VALUE);
					Map<String,Object> mapId=new HashMap<String,Object>();
					mapId.put("id", resOrganizationVO.getId());
					mapId.put("limitDate", reqOrgVO.getLimitDate());
					Integer loanBaseCount=iBMSLoanUrgentConfigServic.getLongBaseCountById(mapId);
					vo.setOverCount(EnumConstants.LOAN_URGENT_CONFIG_VALUE-loanBaseCount);
					for(BMSLoanUrgentConfig bMSLoanUrgentConfig:listBmsLoanUrgentConfig){
						if(resOrganizationVO.getId().equals(bMSLoanUrgentConfig.getOrgId())){
							vo.setUrgentCount(bMSLoanUrgentConfig.getUrgentCount());
							vo.setOverCount(bMSLoanUrgentConfig.getUrgentCount()-loanBaseCount);
						}
					}
					resBMSLoanUrgentConfigVO.add(vo);
				}
				response.setRecords(resBMSLoanUrgentConfigVO);
			}else{
				response.setPageCount(pages.getPageCount());
				response.setPageNum(pages.getPageNum());
				response.setPageSize(pages.getPageSize());
				response.setTotalCount(pages.getTotalCount());
				List<ResBMSLoanUrgentConfigVO> resBMSLoanUrgentConfigVO=new ArrayList<ResBMSLoanUrgentConfigVO>();
				for(ResOrganizationVO resOrganizationVO:listResOrganizationVO){
					ResBMSLoanUrgentConfigVO vo=new ResBMSLoanUrgentConfigVO();
					vo.setOrgId(resOrganizationVO.getId());
					vo.setOrgName(resOrganizationVO.getName());
					vo.setLimitTime(reqOrgVO.getLimitDate());
					vo.setUrgentCount(EnumConstants.LOAN_URGENT_CONFIG_VALUE);
					Map<String,Object> mapId=new HashMap<String,Object>();
					mapId.put("id", resOrganizationVO.getId());
					mapId.put("limitDate", reqOrgVO.getLimitDate());
					Integer loanBaseCount=iBMSLoanUrgentConfigServic.getLongBaseCountById(mapId);
					vo.setOverCount(EnumConstants.LOAN_URGENT_CONFIG_VALUE-loanBaseCount);
					resBMSLoanUrgentConfigVO.add(vo);
				}
				response.setRecords(resBMSLoanUrgentConfigVO);
			}
			

		} catch (Exception e) {
			// 抛出自定义异常
			/* System.out.println(e); */
			e.printStackTrace();
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSLoanUrgentConfigVO> updateOrg(ReqBMSUrgentLimitListVO reqOrgVO) {
		Response<ResBMSLoanUrgentConfigVO> response=iBMSLoanUrgentConfigServic.updateOrg(reqOrgVO);
		return response;
	}

}
