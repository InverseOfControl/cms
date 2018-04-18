package com.ymkj.cms.web.boss.service.master.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.cms.web.boss.facade.apply.ContractChangeFacade;
import com.ymkj.cms.web.boss.service.master.IContractChangeService;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

/**
 * 签约改派Service实现
 * @author YM10166
 *
 */
@Service
public class ContractChangeServiceImpl implements IContractChangeService {
	
	@Autowired
	private ContractChangeFacade contractChangeFacade;

	@Override
	public PageResult<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo) {
		return contractChangeFacade.listPage(reqBMSContractChangeVo);
	}

	@Override
	public ResBMSContractChangeVo changeContract(ReqBMSContractChangeVo reqVo) {
		return contractChangeFacade.changeContract(reqVo);
	}

	@Override
	public List<ResOrganizationVO> findDataOrgIdsByAccount(ReqParamVO vo) {
		return contractChangeFacade.findDataOrgIdsByAccount(vo);
	}

	@Override
	public List<ResEmployeeVO> findEmployeeByDeptAndRole(ReqParamVO vo) {
		return contractChangeFacade.findEmployeeByDeptAndRole(vo);
	}
	
}
