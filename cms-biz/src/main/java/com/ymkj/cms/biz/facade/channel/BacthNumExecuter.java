package com.ymkj.cms.biz.facade.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.constant.AppFormConst;
import com.ymkj.cms.biz.api.enums.EnumChannelCode;
import com.ymkj.cms.biz.api.service.channel.IBacthNumExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBacthNumVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBacthNumVo;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.entity.channel.BMSLoanBatch;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.channel.IBMSBaseLoanService;
import com.ymkj.cms.biz.service.channel.IBMSSequenceService;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理 facecode impl
 */
@Service
@SuppressWarnings("finally")
public class BacthNumExecuter implements IBacthNumExecuter {

	@Autowired
	private IBMSBaseLoanService bmsBaseLoanService;

	@Autowired
	private IBMSSequenceService bmsSequenceService;

	@Autowired
	private LoanProductService loanProductService;

	@Override
	public Response<ResBacthNumVO> createBacthNum(ReqBacthNumVO reqBatchNumVo) {
		Response<ResBacthNumVO> response = new Response<ResBacthNumVO>();
		List<BMSLoanBatch> bmsLoanBatchs = new ArrayList<BMSLoanBatch>();
		List<BMSLoanBase> bmsLoanBases = new ArrayList<BMSLoanBase>();
		ResBacthNumVO resBatchNumVo = new ResBacthNumVO();
		try {
			int sequenceId = bmsSequenceService.queryBatchSequence("10001");
			String randCode = StringUtils.leftPad(String.valueOf(sequenceId), 5, "0");
			BMSLoanBatch bmsLoanBatch = null;
			if (reqBatchNumVo != null && reqBatchNumVo.getLoanNos().size() > 0) {
				List<String> loanNos = reqBatchNumVo.getLoanNos();
				// 根据借款编号获取渠道code,之后通过渠道code获取简称
				List<String> channelCodesList = loanProductService.getChannelCodeByLoans(loanNos);
				// 生成批次号只能用于同一渠道号
				if (CollectionUtils.isEmpty(channelCodesList) || channelCodesList.size() > 1) {
					throw new BizException(CoreErrorCode.SYSTEM_ERROR, "渠道信息不存在或借款来源于不同渠道!");
				}
				String channelCd = EnumChannelCode.getChannelShort(channelCodesList.get(0));
				if (channelCd == null || "".equals(channelCd)) {
					throw new BizException(CoreErrorCode.SYSTEM_ERROR, "渠道信息不存!");
				}
				String channelShort = channelCd == null ? "" : channelCd;
				String batchNum = new StringBuffer(channelShort).append(AppFormConst.BATCH_SPLIT)
						.append(DateUtil.defaultFormatMinutesDate(new Date()))
						.append(randCode.substring(0, randCode.indexOf(".") > 0 ? randCode.indexOf(".") : randCode.length())).toString();
				for (String loanNo : loanNos) {
					bmsLoanBatch = new BMSLoanBatch();
					bmsLoanBatch.setBatchNum(batchNum);
					bmsLoanBatch.setLoanNo(loanNo);
					bmsLoanBatch.setCreatedTime(new Date());
					bmsLoanBatchs.add(bmsLoanBatch);
				}

				for (long loanBaseId : reqBatchNumVo.getLoanBaseIds()) {
					BMSLoanBase bmsBase = new BMSLoanBase();
					bmsBase.setId((long) loanBaseId);
					bmsLoanBases.add(bmsBase);
				}
				long backNum = bmsBaseLoanService.createBatch(bmsLoanBatchs, bmsLoanBases, channelCd, reqBatchNumVo);
				resBatchNumVo.setData(String.valueOf(backNum));
			}
		} catch (BizException e) {
			response.setRepCode(e.getRealCode());
			response.setRepMsg(String.valueOf(e.getArguments()[0]));
		} catch (Exception e) {
			response.setRepCode(CoreErrorCode.FACADE_ERROR.getErrorCode());
			response.setRepMsg(CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		} finally {
			response.setData(resBatchNumVo);
			return response;
		}
	}

	@Override
	public PageResponse<ResLoanBacthNumVo> searchBacthNum(ReqLoanBacthNumVo reqLoanBacthNumVo) {
		PageResponse<ResLoanBacthNumVo> pageResponse = new PageResponse<ResLoanBacthNumVo>();
		try {
			if (reqLoanBacthNumVo.getPage() == 0 || reqLoanBacthNumVo.getRows() == 0) {
				throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
			}
			PageParam pageParam = new PageParam(reqLoanBacthNumVo.getPage(), reqLoanBacthNumVo.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqLoanBacthNumVo);
			PageBean<BMSLoanBatch> pageBean = bmsBaseLoanService.listPage(pageParam, paramMap, "listBatchInfoPage", "countBatchInfoByPageParam");
			// 构造响应参数
			List<ResLoanBacthNumVo> records = new ArrayList<ResLoanBacthNumVo>();
			List<BMSLoanBatch> demoEntitys = pageBean.getRecords();
			for (BMSLoanBatch demoEntity : demoEntitys) {
				ResLoanBacthNumVo resLoanBatchNumVo = new ResLoanBacthNumVo();
				resLoanBatchNumVo.setBatchNum(demoEntity.getBatchNum());
				resLoanBatchNumVo.setBacthNumCreteTime(demoEntity.getCreatedTime());
				records.add(resLoanBatchNumVo);
			}
			pageResponse.setRecords(records);
			pageResponse.setTotalCount(pageBean.getTotalCount());

		} catch (BizException e) {
			pageResponse.setRepCode(e.getErrorMsg());
			pageResponse.setRepMsg(e.getErrorMsg());
		} catch (Exception e) {
			pageResponse.setRepCode(CoreErrorCode.FACADE_ERROR.getErrorCode());
			pageResponse.setRepMsg(CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		} finally {
			return pageResponse;
		}
	}

}
