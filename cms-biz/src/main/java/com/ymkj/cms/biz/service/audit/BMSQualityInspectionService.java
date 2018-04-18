package com.ymkj.cms.biz.service.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.entity.audit.BMSApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.BMSQualityInspectionEntity;
import com.ymkj.cms.biz.entity.audit.first.ApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.first.BlackGreyVO;
/**
 * 申请件接口Service层
 * @author YM10161
 *
 */
public interface BMSQualityInspectionService extends BaseService<BMSApplicationPartEntity> {
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
	boolean checkLoanIsNowReject(Map<String,Object> map);
	/**
	 * 更新申请件维护信息
	 * @param map
	 * @return
	 */
	boolean  updateApplicationPartInfo(ReqApplicationPartUpdVO reqApplicationPartUpdVO);
	
	
	/**
	 * 更新申请件维护信息(改造)
	 * @param map
	 * @return
	 */
	void  updateApplicationPartInfoNew(ApplicationPartEntity applicationPartEntity);
	
	/**
	 * 添加黑名单是否成功
	 * @param blackGreyVO
	 * @return
	 */
	ResReassignmentUpdVO ifSaveBlackGrey(BlackGreyVO blackGreyVO);
	
	/**
	 * 删除黑名单
	 * @param loanNo
	 */
	void removeBlackListId(String loanNo);
	
	/**
	 * 审核提交产品校验
	 * @param loanNo
	 * @return
	 */
	boolean auditCheckProductSales(String loanNo);
	
	public PageBean<BMSApplicationPartEntity> listPageEntrySearch(PageParam pageParam,Map<String, Object> paramMap);
}
