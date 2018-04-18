package com.ymkj.cms.biz.service.ifc.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.service.ifc.IPMSInterface;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;

@Service
public class PMSInterfaceImpl implements IPMSInterface{
	private Log log = LogFactory.getLog(PMSInterfaceImpl.class);
	private static String logTag = "PMSInterfaceImpl.";
	
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;

	@Override
	public List<ResRoleVO> findRolesByAccount(String userCode) {
		log.info(logTag+"findRolesByAccount接口入参：userCode="+userCode);
		Response<List<ResRoleVO>> response = null;
		List<ResRoleVO> roleList = null;
		
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqEmployeeVO.setUsercode(userCode);
		try{
			response = iEmployeeExecuter.findRolesByAccount(reqEmployeeVO);
			log.info(logTag+"findRolesByAccount接口响应：response="+response);
		}catch(Exception e){
			log.info(logTag+"findRolesByAccount接口异常：",e);
			return roleList;
		}
		if(null == response){
			log.info(logTag+"findRolesByAccount接口响应：null;");
			return roleList;
		}
		if(!response.isSuccess()){
			log.info(logTag+"findRolesByAccount接口响应：非000000;");
			log.info(logTag+"findRolesByAccount接口响应：repCode="+response.getRepCode()+" repMsg="+response.getRepMsg());
			return roleList;
		}
		
		roleList = response.getData();
		if(null == roleList){
			log.info(logTag+"findRolesByAccount接口响应：List<ResRoleVO> is null;");
		}else if(CollectionUtils.isEmpty(roleList)){
			log.info(logTag+"findRolesByAccount接口响应：List<ResRoleVO> is empty;");
		}
		return roleList;
	}

	@Override
	public List<ResOrganizationVO> findDataDeptsByAccount(String userCode) {
		log.info(logTag+"findDataDeptsByAccount接口入参：userCode="+userCode);
		Response<List<ResOrganizationVO>> response = null;
		List<ResOrganizationVO> organizationList = null;
		
		ReqParamVO reqParamVO = new ReqParamVO();
		reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO.setLoginUser(userCode);
		
		try{
			response = iOrganizationExecuter.findDataDeptsByAccount(reqParamVO);
			log.info(logTag+"findDataDeptsByAccount接口响应：response="+response);
		}catch(Exception e){
			log.info(logTag+"findDataDeptsByAccount接口异常：",e);
			return organizationList;
		}
		if(null == response){
			log.info(logTag+"findDataDeptsByAccount接口响应：null");
			return organizationList;
		}
		if(!response.isSuccess()){
			log.info(logTag+"findDataDeptsByAccount接口响应：非000000;");
			log.info(logTag+"findDataDeptsByAccount接口响应：repCode="+response.getRepCode()+" repMsg="+response.getRepMsg());
			return organizationList;
		}
		
		organizationList = response.getData();
		if(null == organizationList){
			log.info(logTag+"findDataDeptsByAccount接口响应：List<ResOrganizationVO> is null;");
		}else if(CollectionUtils.isEmpty(organizationList)){
			log.info(logTag+"findDataDeptsByAccount接口响应：List<ResOrganizationVO> is empty;");
		}
		return organizationList;
	}

	@Override
	public List<ResOrganizationVO> findByIds(List<Long> owningBranchIdList) {
		log.info(logTag+"findByIds接口入参：owningBranchIdList="+owningBranchIdList);
		Response<List<ResOrganizationVO>> response = null;
		List<ResOrganizationVO> organizationList = null;
		
		ReqParamVO reqParamVO=new ReqParamVO();
		reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO.setOrgIds(owningBranchIdList);
		
		try{
			response = iOrganizationExecuter.findByIds(reqParamVO);
			log.info(logTag+"findByIds接口响应：response="+response);
		}catch(Exception e){
			log.info(logTag+"findByIds接口异常：",e);
			return organizationList;
		}
		if(null == response){
			log.info(logTag+"findByIds接口响应：null");
			return organizationList;
		}
		if(!response.isSuccess()){
			log.info(logTag+"findByIds接口响应：非000000;");
			log.info(logTag+"findByIds接口响应：repCode="+response.getRepCode()+" repMsg="+response.getRepMsg());
			return organizationList;
		}
		
		organizationList = response.getData();
		if(null == organizationList){
			log.info(logTag+"findByIds接口响应：List<ResOrganizationVO> is null;");
		}else if(CollectionUtils.isEmpty(organizationList)){
			log.info(logTag+"findByIds接口响应：List<ResOrganizationVO> is empty;");
		}
		return organizationList;
	}

	@Override
	public List<ResEmployeeVO> findEmpByUsercode(String userCode, String roleCode, Integer status) {
		log.info(logTag+"findEmpByUsercode接口入参：userCode="+userCode+",roleCode="+roleCode+",status="+status);
		Response<List<ResEmployeeVO>> response = null;
		List<ResEmployeeVO> employeeList = null;
		
		ReqParamVO reqParamVO = new ReqParamVO();
		reqParamVO.setSysCode(EnumConstants.BMS_SYSCODE);
		reqParamVO.setUsercode(userCode);
		reqParamVO.setRoleCode(roleCode);
		reqParamVO.setStatus(status);
		try{
			response = iEmployeeExecuter.findEmpByUsercode(reqParamVO);
			log.info(logTag+"findEmpByUsercode接口响应：response="+response);
		}catch(Exception e){
			log.info(logTag+"findEmpByUsercode接口异常：",e);
			return employeeList;
		}
		if(null == response){
			log.info(logTag+"findEmpByUsercode接口响应：null");
			return employeeList;
		}
		if(!response.isSuccess()){
			log.info(logTag+"findEmpByUsercode接口响应：非000000;");
			log.info(logTag+"findEmpByUsercode接口响应：repCode="+response.getRepCode()+" repMsg="+response.getRepMsg());
			return employeeList;
		}
		
		employeeList = response.getData();
		if(null == employeeList){
			log.info(logTag+"findEmpByUsercode接口响应：List<ResEmployeeVO> is null;");
		}else if(CollectionUtils.isEmpty(employeeList)){
			log.info(logTag+"findEmpByUsercode接口响应：List<ResEmployeeVO> is empty;");
		}
		return employeeList;
	}
}
