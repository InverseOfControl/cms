package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSLoanUrgentConfig;

public interface IBMSLoanUrgentConfigServic extends BaseService<BMSLoanUrgentConfig>{
	/**
	 * 根据营业部ID（集合）查询限定表里对应的信息
	 * @param param
	 * @return
	 */
	public List<BMSLoanUrgentConfig> selectAllBmsLoanUrgentConfigs(Map<String, Object> param);
	
	/**
	 * 根据营业部ID查询该营业部在给定的期限日期里面的加急申请件
	 */
	public Integer getLongBaseCountById(Map<String,Object> map);
	
	/**
	 * 编辑加急件保存
	 */
	public Response<ResBMSLoanUrgentConfigVO> updateOrg(ReqBMSUrgentLimitListVO reqOrgVO);
	
	/**
	 * 开接口，查询营业部是否可以加急，返回是否可加急，剩余个数
	 */
	public ResLoanUrgentVO getLoanUrgentSize(ReqLoanUrgentVO vo);
	
}
