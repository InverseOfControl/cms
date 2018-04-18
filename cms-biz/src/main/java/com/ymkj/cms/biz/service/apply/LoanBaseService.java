package com.ymkj.cms.biz.service.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSContractSignSearchEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface LoanBaseService extends BaseService<LoanBaseEntity>{
	
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public Long saveOrUpdate(LoanBaseEntity loanBaseEntity);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public boolean saveList(List<LoanBaseEntity> loanBaseEntity);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public List<LoanBaseEntity> getByMap(Map<String, Object> paramMap);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public PageBean<BMSContractSignSearchEntity> listPageContractSignSearch(PageParam pageParam, Map<String, Object> paramMap);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public PageBean<EntrySearchEntity> listPageEntrySearch(PageParam pageParam, Map<String, Object> paramMap);
	
	/**
	 * 查看待办个数
	 * @param paramMap
	 * @return
	 */
	public Long queryWaitToDealCount(Map<String, Object> paramMap);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public PageBean<LoanBaseEntity> getReassignment(PageParam pageParam, Map<String, Object> paramMap);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public InformationEntity queryInformationEntity(Map<String, Object> paramMap);
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public boolean updateOperatingStaff(Map<String, Object> paramMap);
	
	/**
	 * 获取有效的员工信息
	 * @param orgId
	 * @return
	 */
	public ResEmployeeVO getEffectiveEmployeeVo(Long orgId,List<String> roleCodes);
	
	/**
	 * 更新版本号
	 * @param paramMap
	 * @return
	 */
	public long updateVersion(Map<String, Object> paramMap);
	
	/**
	 * 更新版本号ById
	 * @param paramMap
	 * @return
	 */
	public long updateLoanBaseVersionById(Map<String, Object> paramMap);
	/**
	 * 查询距离最近一次的申请信息状态
	 */
	public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO);
	/**
	 * 查询负债表
	 */
	public Map<String,Object> findRadioByApplyType(String applyType);
	
	/**
	 * 修改
	 * @param paramMap
	 * @return
	 */
	public long updateIfNewLoanNo(Map<String, Object> paramMap);
	
	/**
	 * 查询机构前一个分配的角色
	 * @param paramMap
	 * @return
	 */
	public String getOrgPrevReviewCode(Map paramMap);
	
	
	public List<Map<String,Object>> findDataByIdNoRefuse(String idNo);
	
	public List<Map<String,Object>> findDataByIdNoCancel(String idNo);
	
	public Map<String,Object> queryContractInfo(String loanNo);
}
	