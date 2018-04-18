package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

/**
 * 签约改派Service接口
 * @author YM10166
 *
 */
public interface IContractChangeService {
	
	/**
	 * 分页列表查询
	 * @param reqBMSContractChangeVo
	 * @return
	 */
	public PageResult<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo);
	
	/**
	 * 签约改派
	 * @param reqBMSContractChangeVo
	 * @return
	 */
	public ResBMSContractChangeVo changeContract(ReqBMSContractChangeVo reqBMSContractChangeVo);

	/**
	 * 根据工号获取机构数据(签约网点)
	 * @param vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午10:21:25
	 */
	public List<ResOrganizationVO> findDataOrgIdsByAccount(ReqParamVO vo);

	/**
	 * 根据机构ID查指定角色的员工
	 * @param vo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日上午11:09:04
	 */
	public List<ResEmployeeVO> findEmployeeByDeptAndRole(ReqParamVO vo);
	
}
