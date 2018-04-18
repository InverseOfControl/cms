package com.ymkj.cms.biz.facade.apply;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IFileFormExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqArchivesVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResArchivesVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResFileFormSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResntrySearchVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.entity.apply.FileFormEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.ContractFirstAdultExecuter;
import com.ymkj.cms.biz.service.apply.FileFormService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
/**
 * 档案管理实现类
 * @author YM10172
 *
 */
@Service
public class FileFormExecuter implements IFileFormExecuter{
	public static Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);
	
	@Autowired
	private FileFormService fileFormService;
	@Autowired
	private IBMSSysLogService ibmsSysLogService;
	@Autowired
	private ILoanContractSignService iloanContractSignService;
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;
	
	private static String STATUS_Y="0";
	private static String STATUS_N="1";
	@Override
	public Response<ResArchivesVO> saveArchives(ReqArchivesVO reqArchivesVo) {
		logger.info("----------------新增档案信息开始请求------------");
		if(StringUtils.isBlank(reqArchivesVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		try {
			Response<ResArchivesVO> response = new Response<ResArchivesVO>();
			FileFormEntity fileFormEntity=new FileFormEntity();
			String fileNo=getFileNo(reqArchivesVo.getLoanNo());
			BeanUtils.copyProperties(reqArchivesVo, fileFormEntity);
			reqArchivesVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
			fileFormEntity.setSysCode(reqArchivesVo.getSysCode());
			fileFormEntity.setFileNo(fileNo);
			long count = this.fileFormService.saveFileForm(fileFormEntity);
			int  result= ibmsSysLogService.recordSysLog(fileFormEntity, "借款系统|新增档案信息|insert|insert",
					"IFileFormExecuter|saveArchives", "新增档案信息");
			if(count>0 && result>0){
				logger.info("新增档案信息完成");
			}
			return response;

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
	}
	@Override
	public PageResponse<ResFileFormSearchVO> listPage( ReqFileFormSearchVO reqFileFormVO) {
		PageResponse<ResFileFormSearchVO> response = new PageResponse<ResFileFormSearchVO>();
		List<String> oid=new ArrayList<String>();
		logger.info("----------------申请件档案列表查询开始请求------------");
		// 参数校验
		if(reqFileFormVO.getPageNum()==0 || reqFileFormVO.getPageSize()==0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"pageNum | pageSize"});
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqFileFormVO.getPageNum(), reqFileFormVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqFileFormVO);
			if(null==reqFileFormVO.getLendingTimeStart()){
				paramMap.put("lendingTimeStart", null);
			}else{
				String startTime = DateUtil.defaultFormatDay(reqFileFormVO.getLendingTimeStart()) + " 00:00:00";
				paramMap.put("lendingTimeStart", startTime);
			}
			if(null==reqFileFormVO.getLendingTimeEnd()){
				paramMap.put("lendingTimeEnd", null);
			}else{
				String endTime = DateUtil.defaultFormatDay(reqFileFormVO.getLendingTimeEnd()) + " 23:59:59";
				paramMap.put("lendingTimeEnd", endTime);
			}
			if(reqFileFormVO.getServiceCode()!=null){
				ReqParamVO vo  = new ReqParamVO();
				vo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				vo.setLoginUser(reqFileFormVO.getServiceCode());
				Response<List<ResOrganizationVO>> res = OrganizationExecuter.findDataDeptsByAccount(vo);
				for(ResOrganizationVO vos:res.getData()){
					oid.add(vos.getId().toString());
				}
				paramMap.put("owningBranchId", oid);
			}
			// 调用业务逻辑,得到list集合
			PageBean<FileFormEntity> pageBean = fileFormService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResFileFormSearchVO> records = new ArrayList<ResFileFormSearchVO>();
			List<FileFormEntity> demoEntitys = pageBean.getRecords();
			for (FileFormEntity demoEntity : demoEntitys) {
				ResFileFormSearchVO resDemoVO = new ResFileFormSearchVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				//判断放款日期是否大于15天
				if(resDemoVO.getLoanDate()!=null){
					long date=DateUtil.getDiffDay(resDemoVO.getLoanDate(),new Date());
					if(date>15){
						resDemoVO.setStatus(STATUS_N);
					}else{
						resDemoVO.setStatus(STATUS_Y);
					}
				}
					records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);
			
			int  result= ibmsSysLogService.recordSysLog(reqFileFormVO, "录单系统|申请件档案列表查询|select|select",
					"IFileFormExecuter|listPage", "申请件档案列表查询");
			if(result>0){
				logger.info("插入系统日志信息");
			}
		} catch (Exception e) { 
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);

		}
		return response;
	}

	@Override
	public Response<ResArchivesVO> updateArchives(ReqArchivesVO reqArchivesVo) {
		logger.info("----------------更新档案信息开始请求------------");
		if(StringUtils.isBlank(reqArchivesVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		try {
			Response<ResArchivesVO> response = new Response<ResArchivesVO>();
			Response<ResntrySearchVO>  res=queryArchives(reqArchivesVo);
			if(null==res.getData().getFileNo()){
				response=saveArchives(reqArchivesVo);
			}else{
				FileFormEntity fileFormEntity=new FileFormEntity();
				BeanUtils.copyProperties(reqArchivesVo, fileFormEntity);
				reqArchivesVo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				fileFormEntity.setSysCode(reqArchivesVo.getSysCode());
				long count = this.fileFormService.updateArchives(fileFormEntity);
				int  result= ibmsSysLogService.recordSysLog(fileFormEntity, "录单系统|更新档案信息|insert|insert",
						"IFileFormExecuter|updateArchives", "修改档案信息");
				if(count>0 && result>0){
					logger.info("修改档案信息完成");
				}
			}
			return response;

		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
	}

	@Override
	public Response<ResntrySearchVO> queryArchives(ReqArchivesVO reqArchivesVo) {
		logger.info("----------------档案详细信息开始请求------------");
		Response<ResntrySearchVO> response=new Response<ResntrySearchVO>();
		if(StringUtils.isBlank(reqArchivesVo.getLoanNo())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"});
		}
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqArchivesVo);
			FileFormEntity	fileFormEntity=fileFormService.queryArchives(paramMap);
			
			if(fileFormEntity != null){
				ResntrySearchVO resDemoVO = new ResntrySearchVO();
				BeanUtils.copyProperties(fileFormEntity, resDemoVO);
				response.setData(resDemoVO);
			}				
		} catch (Exception e) {

			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		} 
		return response;
	}
	public String getFileNo(String loanNo) {
		StringBuilder builder = new StringBuilder();
		if(loanNo != null){
			BMSLoanContract loanContract= iloanContractSignService.findByLoanNo(loanNo);
			if(null!=loanContract){
				String contractSource = loanContract.getContractNum();
				if("00003".equals(contractSource)){
					builder.append("A-DS-");
				}else if("00005".equals(contractSource)){
					builder.append("H-DS-");
				}else if("00004".equals(contractSource)){
					builder.append("G-DS-");
				}else if("00007".equals(contractSource)){
					builder.append("J-DS-");
				}else if("00008".equals(contractSource)){
					builder.append("WC-DS-");
				}else{
					builder.append("C-DS-");
				}
				builder.append(loanContract.getProductCd()+"-"+loanNo.substring(loanNo.length()-7, loanNo.length()));
				return builder.toString();
			}
		}
		return builder.toString();
	}
	
/*	public String splictEntity(String code,String codeType){
		Map<String, Object> paramMap =new HashMap<String,Object>();
		StringBuilder sb = new StringBuilder();
		String[]value=code.split(",");
		for(int i=0;i<value.length;i++){
			paramMap.put("code", value[i]);
			paramMap.put("codeType", codeType);
			BMSEnumCode bmsEnumCode=ibmsEnumCodeService.findEnumByCode(paramMap);
			sb.append(bmsEnumCode.getNameCN()+",");
		}
		return sb.toString();
	}*/
}
