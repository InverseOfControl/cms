package com.ymkj.cms.biz.facade.channel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.constant.AppFormConst;
import com.ymkj.cms.biz.api.service.channel.IAppBookManageExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAppFormVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppFormVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.biz.entity.channel.BMSAppForm;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.BMSFileUpdAndDwnService;
import com.ymkj.cms.biz.service.channel.IBMSAppFormService;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:划拨申请书 impl
 */
@Service
public class AppBookManageExecuter implements IAppBookManageExecuter {

	public static Logger logger = Logger.getLogger(AppBookManageExecuter.class);

	@Autowired
	private IBMSAppFormService bmsAppFormService;

	@Autowired
	private BMSFileUpdAndDwnService bmsFileUpdAndDwnService;

	@Override
	public PageResponse<ResAppFormVO> listPages(ReqAppFormVO reqAppFormVo) {
		PageResponse<ResAppFormVO> response = new PageResponse<ResAppFormVO>();
		// 分页参数校验
		if (reqAppFormVo.getPage() == 0 || reqAppFormVo.getRows() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqAppFormVo.getPage(), reqAppFormVo.getRows());
			Map<String, Object> paramMap = BeanKit.bean2map(reqAppFormVo);
			// 调用业务逻辑
			PageBean<BMSAppForm> pageBean = bmsAppFormService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResAppFormVO> records = new ArrayList<ResAppFormVO>();
			List<BMSAppForm> demoEntitys = pageBean.getRecords();
			for (BMSAppForm demoEntity : demoEntitys) {
				ResAppFormVO resDemoVO = new ResAppFormVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			response.setRecords(records);
			response.setTotalCount(pageBean.getTotalCount());

		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<byte[]> dealEsignature(ReqdealEsignatureVo requestVo) {
		Response<byte[]> response = new Response<byte[]>();
		ByteArrayOutputStream out = null;
		try {
			byte[] bytes = requestVo.getFileByteMap().get(requestVo.getFileName());
			if (bytes == null || bytes.length <= 0) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "划拨申请书原件不存在！");
			}

			String fileName = requestVo.getFileName();
			String projectCode = fileName.substring(0, fileName.indexOf("_"));
			bmsFileUpdAndDwnService.initFtpClient("172.16.250.69", "21", "qianzhang", "!qaz@wsx", "/qianzhang/upload");
			String esignatureFtpFilePathName = bmsFileUpdAndDwnService.isExistEsignatureFtpFile("/qianzhang/download/" + projectCode, fileName);
			if (!StringUtils.isEmpty(esignatureFtpFilePathName)) {
				out = bmsFileUpdAndDwnService.downloadEsignatureFileByFilePath(esignatureFtpFilePathName);
				response.setData(out.toByteArray());
				return response;
			}

			String httpFilePath = bmsFileUpdAndDwnService.uploadEsignatureFile(new ByteArrayInputStream(bytes), "/qianzhang/upload", fileName,
					projectCode);
			if (StringUtils.isEmpty(httpFilePath)) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "上传签章文件到服务器失败！");
			}

			String httpUrl = bmsFileUpdAndDwnService.dealEsignature(httpFilePath, fileName);
			if (StringUtils.isEmpty(httpUrl)) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "签章失败！");
			}
			out = bmsFileUpdAndDwnService.downloadEsignatureFileByHttpUrl(httpUrl);
			response.setData(out.toByteArray());
		} catch (BizException e) {
			response.setRepMsg(e.getRealCode());
			response.setRepCode(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(e);
			response.setRepCode("00001");
			response.setRepMsg(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> importAppBook(ReqdealEsignatureVo requestVo) {
		Response<String> response = new Response<String>();
		try {
			if (!bmsAppFormService.importAppBook(requestVo)) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "申请书上传失败!");
			}
		} catch (BizException e) {
			response.setRepMsg(e.getRealCode());
			response.setRepCode(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(e);
			response.setRepCode("00001");
			response.setRepMsg(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<ResAppBookVo> findAppBookPdfXls(ReqBatchNumVo requestVo) {
		Response<ResAppBookVo> response = new Response<ResAppBookVo>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		try {
			String batchNum = requestVo.getBatchNum();
			paraMap.put("batchNum", batchNum);
			ResAppBookVo resObj = bmsAppFormService.findAppBook(paraMap);
			String projectCode = bmsAppFormService.getProjectCode(batchNum);
			if (AppFormConst.TRUST_PROJECT_CODE2.equals(projectCode) || AppFormConst.TRUST_PROJECT_CODE3.equals(projectCode)) {
				BigDecimal diffMoney = new BigDecimal("0.00");
				String fundsSources = bmsAppFormService.getFundsSources(projectCode);
				ReqAppFormVO reqVo = new ReqAppFormVO();
				paraMap.put("fundsSources", fundsSources);
				reqVo.setChannelId(fundsSources);
				if (!bmsAppFormService.isUploadApplyBook(batchNum) && bmsAppFormService.isCurrentDayDownloadFirst(paraMap)) {
					diffMoney = bmsAppFormService.getDiffMoney(paraMap);
				}
				resObj.setDiffMoney(diffMoney);
			}
			response.setData(resObj);
		} catch (BizException e) {
			response.setRepMsg(e.getRealCode());
			response.setRepCode(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(e);
			response.setRepCode("00001");
			response.setRepMsg(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<LoanApplyDetailVo>> findLoanAppBookXls(ReqBatchNumVo requestVo) {
		Response<List<LoanApplyDetailVo>> response = new Response<List<LoanApplyDetailVo>>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<LoanApplyDetailVo> resObjs = bmsAppFormService.listLoanAppDetailExp(paraMap);
		response.setData(resObjs);
		return response;
	}

	@Override
	public Response<List<ResRepaymentExpVo>> exportLoanRepayment(ReqBatchNumVo requestVo) {
		Response<List<ResRepaymentExpVo>> response = new Response<List<ResRepaymentExpVo>>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<ResRepaymentExpVo> resObjs = bmsAppFormService.listLoanRepaymentExp(paraMap);
		response.setData(resObjs);
		return response;
	}

	@Override
	public Response<String> findRequestFileBatchNum(ReqFileBatchNumVo requestVo) {
		Response<String> response = new Response<String>();
		String fileBatchNum = bmsAppFormService.findRequestFileBatchNum(requestVo);
		response.setData(fileBatchNum);
		return response;
	}

	@Override
	public Response<Boolean> isUploadApplyBook(ReqAppFormVO req) {
		Response<Boolean> response = new Response<Boolean>();
		boolean isTrue = bmsAppFormService.isUploadApplyBook(req.getBatchNum());
		response.setData(isTrue);
		return response;
	}

	@Override
	public Response<Boolean> isCurrentDayDownloadFirst(ReqAppFormVO req) {
		Response<Boolean> response = new Response<Boolean>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("batchNum", req.getBatchNum());
		paraMap.put("fundsSources", req.getChannelId());
		boolean isTrue = bmsAppFormService.isCurrentDayDownloadFirst(paraMap);
		response.setData(isTrue);
		return response;
	}

}
