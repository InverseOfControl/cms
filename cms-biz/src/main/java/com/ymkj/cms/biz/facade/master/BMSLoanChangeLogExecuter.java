package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSLoanChangeLogExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogNewVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogVO;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.apply.BmsLoanChangeLogEntity;
import com.ymkj.cms.biz.entity.master.BMSLoanChangeLog;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.qualitytest.QualityInspectionSheetExecuter;
import com.ymkj.cms.biz.service.master.IBMSLoanChangeLogEntityService;
import com.ymkj.cms.biz.service.master.IBMSLoanChangeLogService;
@Service
public class BMSLoanChangeLogExecuter implements IBMSLoanChangeLogExecuter{
	
	public Logger logger = LoggerFactory.getLogger(QualityInspectionSheetExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private IBMSLoanChangeLogService bmsLoanChangeLogService;
	@Autowired
	private IBMSLoanChangeLogEntityService iBMSLoanChangeLogEntityService;

	@Override
	public PageResponse<ResBMSLoanChangeLogVO> listPage(ReqBMSLoanChangeLogVO reqBMSLoanChangeLogVO) {
		PageResponse<ResBMSLoanChangeLogVO> response = new PageResponse<ResBMSLoanChangeLogVO>();
		// 参数校验
		if (reqBMSLoanChangeLogVO.getPageNum() == 0 || reqBMSLoanChangeLogVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			/*reqBMSLoanChangeLogVO.setIsDeleted((long) 0);*/
			PageParam pageParam = new PageParam(reqBMSLoanChangeLogVO.getPageNum(), reqBMSLoanChangeLogVO.getPageSize());
		
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSLoanChangeLogVO);
			
			
			// 调用业务逻辑,得到list集合
			PageBean<BMSLoanChangeLog> pageBean = bmsLoanChangeLogService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSLoanChangeLogVO> records = new ArrayList<ResBMSLoanChangeLogVO>();
			List<BMSLoanChangeLog> Entitys = pageBean.getRecords();
			for (BMSLoanChangeLog Entity : Entitys) {
				ResBMSLoanChangeLogVO resDemoVO = new ResBMSLoanChangeLogVO();
				BeanUtils.copyProperties(Entity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			/*System.out.println(e);*/
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	
	}

	@Override
	public PageResponse<ResBMSLoanChangeLogEntityVO> getListPage(ReqBMSLoanChangeLogNewVO vo) {
		logger.info("----------------开始请求------------");
		logger.info("请求参数:" + gson.toJson(vo));
		PageResponse<ResBMSLoanChangeLogEntityVO> response = new PageResponse<ResBMSLoanChangeLogEntityVO>();
		if (vo != null) {
			if (StringUtils.isEmpty(vo.getSysCode())) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			} else {
				if (!EnumConstants.PMS_SYSTEM_CODE.equals(vo.getSysCode())) {
					return resAuditQueryInfo(BizErrorCode.BIZ_VALUE_EOERR.getErrorCode(),
						"sysCode" + BizErrorCode.BIZ_VALUE_EOERR.getDefaultMessage(), response);
				}
			}
			// 参数校验
			if (vo.getPageNum() == 0 || vo.getPageSize() == 0) {
				return resAuditQueryInfo(BizErrorCode.BIZ_EOERR.getErrorCode(),
						"pageNum|pageSize" + BizErrorCode.BIZ_EOERR.getDefaultMessage(), response);
			}
			try {
				String start=vo.getStartDate();
				String end=vo.getEndDate();
				if(null!=start&&start.length()!=0){
					vo.setStartDate(start+" 00:00:00");
				}
				if(null!=end&&end.length()!=0){
					vo.setEndDate(end+" 23:59:59");
				}
				
				
				PageParam pageParam = new PageParam(vo.getPageNum(), vo.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(vo);
				
				
				// 调用业务逻辑,得到list集合
				PageBean<BmsLoanChangeLogEntity> pageBean = iBMSLoanChangeLogEntityService.listPage(pageParam, paramMap);
				// 构造响应参数
				List<ResBMSLoanChangeLogEntityVO> records = new ArrayList<ResBMSLoanChangeLogEntityVO>();
				List<BmsLoanChangeLogEntity> Entitys = pageBean.getRecords();
				for (BmsLoanChangeLogEntity Entity : Entitys) {
					ResBMSLoanChangeLogEntityVO resDemoVO = new ResBMSLoanChangeLogEntityVO();
					BeanUtils.copyProperties(Entity, resDemoVO);
					records.add(resDemoVO);
				}
				// 忽略 复制的字段
				BeanUtils.copyProperties(pageBean, response, "records");
				response.setRecords(records);

			} catch (Exception e) {
				logger.info("借款信息变更日志接口查询数据库异常!!!!");
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSLoanChangeLogNewVO" });
		}
		return response;
	}

	
	/**
	 * 审核台查询响应对象
	 * 
	 * @param <T>
	 */
	public static <T> PageResponse<T> resAuditQueryInfo(String errCode, String errMsg, PageResponse<T> response) {
		response.setRepCode(errCode);
		response.setRepMsg(errMsg);
		return response;
	}
}
