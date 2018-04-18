package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.entity.master.BMSChannel;

public interface IBMSChannelService extends BaseService<BMSChannel> {

	public long insert(BMSChannel channle);

	public void delete(BMSChannel channle);

	public void update(BMSChannel channle);

	public List<BMSChannel> selectAllChannel(Map<String, Object> param);

	public BMSChannel findOne(ReqBMSChannelVO reqChannelVO);
	/**
	 * 根据用户code(门店ID)获取数据
	 * @param paramMap
	 * @return
	 */
	public List<BMSChannel> getChannelByOrgId(Map<String, Object> paramMap);
	
	/**
	 * <p>Description:根据借款产品、申请期限、申请额度、营业部获取渠道列表</p>
	 * @uthor YM10159
	 * @date 2017年3月16日 上午10:23:49
	 * @param @param paramMap
	 */
	public List<BMSChannel> getChannelByProTermLmt(Map<String,Object> paramMap);
	
	/**
	 * <p>Description:根据借款产品、审批额度、进件营业部获取渠道列表</p>
	 * 
	 */
	public List<BMSChannel> getChannelByOrgProAlt(Map<String, Object> paramMap);
	
	public int checkIsChennelExits(Map<String, Object> paramMap);
	
	/**
	 * 查询渠道，包含禁用，过期
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日上午10:02:46
	 */
	public List<BMSChannel> findBy(Map<String, Object> paramMap);
	
	/**
	 * 渠道产品配置禁用或启用
	 * @param paramMap
	 * @author lix YM10160
	 * @date 2017年4月18日下午2:29:10
	 */
	public int disabledLimitChannel(Map<String, Object> paramMap);
	/**
	 * 网点产品配置禁用或启用
	 * @param paramMap
	 * @author lix YM10160
	 * @date 2017年4月18日下午2:32:44
	 */
	public int disabledOrgLimitChannel(Map<String, Object> paramMap);
	
	public List<BMSChannel> findChannelEqDate(Map<String, Object> paramMap);
	/**
	 * 签约银行配置禁用或启用
	 * @param paramMap
	 * @author lix YM10160
	 * @date 2017年4月24日下午4:04:45
	 */
	public int disabledChannelBank(Map<String, Object> paramMap);
	
	/**
	 * @Description:渠道网关适配接口</p>
	 * @uthor YM10159
	 * @date 2017年7月10日 下午2:15:26
	 * @param reqChannelVO
	 */
	public int isExistInBMS(String loanNo);

}
