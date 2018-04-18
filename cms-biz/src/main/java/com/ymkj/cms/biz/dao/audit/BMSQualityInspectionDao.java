package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.BMSApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.BMSQualityInspectionEntity;
/**
 * 申请件接口DAO层
 * @author YM10161
 *
 */
public interface BMSQualityInspectionDao extends BaseDao<BMSApplicationPartEntity> {
	/**
	 * 查询当日终审通过的申请件信息
	 * @param map
	 * @return
	 */
	List<BMSQualityInspectionEntity> queryQualityInsInfo(Map<String,Object> map);
	/**
	 * 根据借款单号检测是否是
	 * @param loanNo
	 * @return
	 */
	Integer checkLoanIsNowReject(Map<String,Object> map);
	
	
	public PageBean<BMSApplicationPartEntity> listPageEntrySearch(PageParam pageParam,Map<String, Object> paramMap);
}
