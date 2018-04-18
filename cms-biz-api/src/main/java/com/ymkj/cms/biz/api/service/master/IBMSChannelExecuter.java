package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.apply.ResTrialBeforeCreditChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSChannelExecuter {

	public ResListVO<ResBMSChannelVO> getAllChannel(ReqBMSChannelVO reqChannelVO);
	
	public ResListVO<ResBMSChannelVO> findChannelEqDate(ReqBMSChannelVO reqChannelVO);
	

	public Response<ResBMSChannelVO> getChannelById(ReqBMSChannelVO reqChannelVO);

	/**
	 * 分页查询 demoVO
	 * 
	 * @param ReqDemoVO
	 * @return
	 */
	public PageResponse<ResBMSChannelVO> listPage(ReqBMSChannelVO reqChannelVO);

	public Response<ResBMSChannelVO> addChannel(ReqBMSChannelVO reqChannelVO);

	public Response<ResBMSChannelVO> deleteChannel(ReqBMSChannelVO reqChannelVO);

	public Response<ResBMSChannelVO> updateChannel(ReqBMSChannelVO reqChannelVO);

	public ResListVO<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO);

	public Response<ResBMSChannelVO> findOne(ReqBMSChannelVO reqChannelVO);
	
	/**
	 * 根据用户code(门店id)获取数据
	 * @param reqDemoVO
	 * @return
	 */
	public ResListVO<ResBMSChannelVO> getChannelByUserCode(ReqBMSChannelVO reqChannelVO);
	
	/**
	 * 根据门店id获取数据
	 * @param reqDemoVO
	 * @return
	 */
	public ResListVO<ResBMSChannelVO> getChannelByOrgId(ReqBMSChannelVO reqChannelVO);
	
	/**
	 * <p>Description:根据借款产品、申请期限、申请额度、营业部获取渠道列表</p>
	 * @uthor YM10159
	 * @date 2017年3月16日 上午10:20:14
	 * @param @param reqTrialBeforeCreditChannelVO
	 * @param @return 渠道列表
	 */
	public Response<List<ResTrialBeforeCreditChannelVO>> getChannelByProTermLmt(ReqTrialBeforeCreditChannelVO reqTrialBeforeCreditChannelVO);

	/**
	 * <p>Description:根据借款产品、审批额度、进件营业部获取渠道列表</p>
	 * 
	 */
	public Response<List<ResBMSChannelVO>> getChannelByOrgProAlt(
			ReqLoanContractSignVo reqLoanContractSignVo);
	

	public Response<ResBMSCheckIsExitsVO> checkIsChennelExits(ReqBMSChannelVO reqDemoVO);
	
	/**
	 * 渠道删除检查
	 * @param reqBMSChannelVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月19日下午9:44:56
	 */
	public Response<ResBMSChannelVO> deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO);
	/**
	 * 依据id查找渠道
	 * @return
	 * @author lix YM10160
	 * @param reqChannelVO 
	 * @date 2017年4月20日下午8:31:15
	 */
	public Response<ResBMSChannelVO> getById(ReqBMSChannelVO reqChannelVO);
	
	/**
	 * @Description:渠道网关适配接口（如果在BMS存在借款走借款，否则走APS）</p>
	 * @uthor YM10159
	 * @date 2017年7月10日 下午2:11:16
	 * @param reqChannelVO
	 */
	public Response<Object> isExistInBMS(ReqBMSLoanBaseVO loanBaseVO);
	
	/**
	 * 查询门店下渠道
	 * @param reqDemoVO
	 * @return
	 */
	public ResListVO<ResBMSChannelVO>  findChannelByOrgIds(ReqBMSChannelVO reqDemoVO);
}
