package com.ymkj.cms.web.boss.facade.apply;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSContractChangeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Component
public class ContractChangeFacade extends BaseFacade {

	@Autowired
	private IBMSContractChangeExecuter contractChangeExecuter;
	@Autowired
	private IOrganizationExecuter organizationExecuter;
	@Autowired
	private IEmployeeExecuter employeeExecuter;

	/**
	 * 分页列表查询
	 * 
	 * @param reqBMSContractChangeVo
	 * @return
	 */
	public PageResult<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo) {
		// 业务调用
		PageResponse<ResBMSContractChangeVo> pageResponse = contractChangeExecuter.listPage(reqBMSContractChangeVo);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSContractChangeVo> pageResult = new PageResult<ResBMSContractChangeVo>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}

	/**
	 * 签约改派
	 * 
	 * @param reqVo
	 * @return
	 * @throws BusinessException
	 */
	public ResBMSContractChangeVo changeContract(ReqBMSContractChangeVo reqVo) throws BusinessException {
		//机构人员查询
		ReqParamVO vo = new ReqParamVO();
		vo.setOrgId(Long.valueOf(reqVo.getContractBranchId()));
		List<String> roleCodeList = new ArrayList<String>();
		roleCodeList.add("customerService");
		vo.setRoleCodes(roleCodeList);
		vo.setSysCode(SystemCode.SysCode);
		//平均分配原则
		List<ResEmployeeVO> employeeList = this.findEmployeeByDeptAndRole(vo);
		List<String> customerServiceList = new ArrayList<String>();
		for (ResEmployeeVO employeeVo : employeeList) {
			customerServiceList.add(employeeVo.getUsercode());
		}
		reqVo.setCustomerServiceList(customerServiceList);
		
		// 接口调用
		Response<ResBMSContractChangeVo> response = contractChangeExecuter.changeContract(reqVo);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

	/**
	 * 根据工号获取机构数据(签约网点)
	 * 
	 * @param vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午10:22:43
	 */
	public List<ResOrganizationVO> findDataOrgIdsByAccount(ReqParamVO vo) {

		// 机构获取
		Response<List<ResOrganizationVO>> orgInfoRes = organizationExecuter.findDataDeptsByAccount(vo);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (orgInfoRes.isSuccess()) {
			return orgInfoRes.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(orgInfoRes));
		}

	}

	/**
	 * 根据机构ID查指定角色的员工
	 * 
	 * @param vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午11:09:40
	 */
	public List<ResEmployeeVO> findEmployeeByDeptAndRole(ReqParamVO vo) {
		// 接口调用
		Response<List<ResEmployeeVO>> response = employeeExecuter.findByDeptAndRole(vo);

		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			return response.getData();
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(response));
		}
	}

}
