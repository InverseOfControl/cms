package com.ymkj.cms.biz.service.ifc;

import java.util.List;

import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResRoleVO;

/**
 * 平台接口封装
 * @author YM10159
 */
public interface IPMSInterface {
	
	/**
	 * 根据工号查询角色列表
	 * @author YM10159
	 * @param userCode 工号
	 * @return 返回角色列表
	 */
	public List<ResRoleVO> findRolesByAccount(String userCode);
	
	/**
	 * 根据工号查询所在营业部
	 * @author YM10159
	 * @param userCode 工号 
	 * @return 返回营业部信息
	 */
	public List<ResOrganizationVO> findDataDeptsByAccount(String userCode);
	
	/**
	 * 根据机构ID获取机构列表信息
	 * @author YM10159
	 * @param owningBranchIdList 机构id
	 * @return 返回结构列表信息
	 */
	public List<ResOrganizationVO> findByIds(List<Long> owningBranchIdList);
	
	/**
	 * 根据工号查询所在机构及其子机构的成员
	 * @author YM10159
	 * @param userCode 工号
	 * @param roleCode 要查询的角色
	 * @param status 查询状态 0-可用；1-禁用
	 * @return 返回员工列表
	 */
	public List<ResEmployeeVO> findEmpByUsercode(String userCode, String roleCode, Integer status);
}
