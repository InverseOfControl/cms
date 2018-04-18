package com.ymkj.cms.biz.service.client.impl;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.facade.audit.ContractFirstAdultExecuter;
import com.ymkj.cms.biz.service.client.PMSClientService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResGroupVO;


/**
 * 权限系统调用
 * @author YM10152
 *
 */
@Service
public class PMSClientServiceImpl implements PMSClientService{
	public static Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);
	// JSON 工具类
	private static Gson gson = new Gson();
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Override
	public ResGroupVO findOrgGroupInfo(ReqParamVO csParamVO) {
		//根据登录人CODE获取所在小组，大组
		csParamVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		ResGroupVO groupVo=new ResGroupVO();
		logger.info("调权限API,查询初审人员所在小组入参:" + gson.toJson(csParamVO));
		Response<ResGroupVO> orgGroupVo = OrganizationExecuter.findGroupInfoByAccount(csParamVO);
		logger.info("调权限API,查询初审人员所在小组返回值:" + orgGroupVo.isSuccess());
		if (orgGroupVo.isSuccess()) {
			logger.info("调权限API,查询初审人员所在小组返回数据:" +gson.toJson(orgGroupVo.getData()));
			groupVo.setGroupId(orgGroupVo.getData().getGroupId());
			groupVo.setBigGroupId(orgGroupVo.getData().getBigGroupId());
		}else{
			logger.info("调权限API,查询初审人员所在小组返回异常当前操作人员CODE:"+gson.toJson(csParamVO)+"返回信息"+orgGroupVo.getRepMsg());
		}
		//根据机构CODE，获取机构ID
		 Response<Long> OrgTypeCode=OrganizationExecuter.findOrgIdByOrgTypeCode(csParamVO);
		 if(OrgTypeCode!=null){
			 groupVo.setGroupCode(OrgTypeCode.getData().toString()); 
		 }
		return groupVo;
	}

	@Override
	public ResEmployeeVO findEmployeeByCode(ReqEmployeeVO reqEmployeeVO) {
		
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		logger.info("调权限API,根据员工工号查询员工信息:" + gson.toJson(reqEmployeeVO));
		Response<ResEmployeeVO> response=iEmployeeExecuter.findByAccount(reqEmployeeVO);
		logger.info("调权限API,根据员工工号查询员工信息:" + response.isSuccess());
		if(response.getData()!=null){
			logger.info("调权限API,根据员工工号查询员工信息:" +gson.toJson(response.getData()));	
			ResEmployeeVO res=(ResEmployeeVO) response.getData();
			return res;
		}else{
			logger.info("调权限API,根据员工工号查询员工信息为空,员工工号为:"+reqEmployeeVO.getUsercode());	
			return null;
		}
		
	}



}
