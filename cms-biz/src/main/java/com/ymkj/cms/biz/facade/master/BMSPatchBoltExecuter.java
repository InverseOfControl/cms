package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSPatchBoltExecuter;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSPatchBolt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSPatchBoltService;
/**
 * 补件服务实现facade
 * @author YM10161
 *
 */
@Service
public class BMSPatchBoltExecuter implements IBMSPatchBoltExecuter{
	public Logger logger = LoggerFactory.getLogger(BMSPatchBoltExecuter.class);
	// JSON 工具类
	private Gson gson = new Gson();
	@Autowired
	private IBMSPatchBoltService iBMSPatchBoltService;
	@Autowired
	private IntegratedSearchExecuter integratedSearchExecuter;
	@Value("#{env['patchBoltUrl']?:''}")
	private String patchBoltUrl;
	@Override
	public PageResponse<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO) {
		logger.info("-----------请求补件信息接口开始-------------");
		PageResponse<ResQueryLoanVo> pageResponse= new PageResponse<ResQueryLoanVo>();
		//验参
		if(reqBMSPatchBoltVO!=null){
			if(StringUtils.isEmpty(reqBMSPatchBoltVO.getSysCode())){
				pageResponse.setRepCode(BizErrorCode.BIZ_EOERR.getErrorCode());
				pageResponse.setRepMsg(BizErrorCode.BIZ_EOERR.getDefaultMessage());
				return pageResponse;
			}
		
			if(reqBMSPatchBoltVO.getPageNum()==0 || reqBMSPatchBoltVO.getPageSize()==0){
				pageResponse.setRepCode(BizErrorCode.BIZ_PAGE_EOERR.getErrorCode());
				pageResponse.setRepMsg(BizErrorCode.BIZ_PAGE_EOERR.getDefaultMessage());
				return pageResponse;
			}
			/**
			 * 进入业务请求阶段
			 */
			try{
				PageParam pageParam = new PageParam(reqBMSPatchBoltVO.getPageNum(), reqBMSPatchBoltVO.getPageSize());
				Map<String, Object> paramMap = BeanKit.bean2map(reqBMSPatchBoltVO);
				PageBean<BMSPatchBolt> pageBean=iBMSPatchBoltService.listPage(pageParam,paramMap);
				List<ResQueryLoanVo> patchBoltList = new ArrayList<ResQueryLoanVo>();
				List<BMSPatchBolt> Entitys = pageBean.getRecords();
				for(BMSPatchBolt patchBolt : Entitys){
					ResQueryLoanVo reqQueryLoanVo = new ResQueryLoanVo();
						BeanUtils.copyProperties(patchBolt, reqQueryLoanVo);
						reqQueryLoanVo.setPatchBoltUrl(patchBoltUrl);
						patchBoltList.add(reqQueryLoanVo);
					}
												
				// 忽略 复制的字段
				BeanUtils.copyProperties(pageBean, pageResponse, "records");
				pageResponse.setRecords(patchBoltList);
			}catch(Exception e){
				logger.info("请求异常，请求参数:" + gson.toJson(reqBMSPatchBoltVO));
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
			}
		}else{
			logger.info("-----------reqBMSPatchBoltVO对象为空-------------"+gson.toJson(reqBMSPatchBoltVO));
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "reqBMSPatchBoltVO" });
		}
		return pageResponse;
	}

}
