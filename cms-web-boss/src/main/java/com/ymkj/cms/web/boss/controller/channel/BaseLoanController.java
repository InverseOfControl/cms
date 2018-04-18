package com.ymkj.cms.web.boss.controller.channel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.constant.BmsConstant;
import com.ymkj.cms.biz.api.enums.EnumBH2Constants;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUpdBatchVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBacthNumVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBaseVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.channel.IBMSExceptionConsole;
import com.ymkj.cms.web.boss.service.channel.IBaseLoanService;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
import com.ymkj.sso.client.ShiroUtils;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理,批次生成 controller
 */
@Controller
@RequestMapping("batchMang")
@SuppressWarnings("finally")
public class BaseLoanController extends BaseController {

	public static Logger logger = Logger.getLogger(BaseLoanController.class);

	@Autowired
	private IBaseLoanService ibaseLoanService;

	@Autowired
	private IExcelExport iExcelExport;

	@Autowired
	private IBMSExceptionConsole excConsole;

	
	@Autowired
	private IOrganizationExecuter iOrganizationExecuter;
	
	/*
	 * 初始批次生成页面
	 */
	@RequestMapping("batchCrtView")
	public String viewCrt() {
		return "channel/batch/batchGenerate";
	}

	/**
	 * 初始批次管理页面
	 * 
	 * @return
	 */
	@RequestMapping("batchMangView")
	public String viewMang() {
		return "channel/batch/batchMang";
	}

	/**
	 * 初始债权批次信息页面
	 * 
	 * @return
	 */
	@RequestMapping("batchLoanView")
	public ModelAndView viewLoan(String batchNum, String createTime) {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("batchNum", batchNum);
		dataMap.put("createTime", createTime);
		return new ModelAndView("channel/batch/batchLoanInfo", dataMap);
	}

	/**
	 * 借款相关信息查询
	 * 
	 * @param reqLoanBaseVo
	 * @return
	 */
	@RequestMapping("listLoanInfoPage")
	@ResponseBody
	public Response<ResponsePage<ResLoanBaseVo>> listLoanInfoPage(ReqLoanBaseVo reqLoanBaseVo) {
		Response<ResponsePage<ResLoanBaseVo>> result = new Response<ResponsePage<ResLoanBaseVo>>();
		try {
			if (reqLoanBaseVo.getPage() == 0 || reqLoanBaseVo.getRows() == 0) {
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
			}
			PageResult<ResLoanBaseVo> pageResult = ibaseLoanService.listPage(reqLoanBaseVo);
			ResponsePage<ResLoanBaseVo> pageList = new ResponsePage<ResLoanBaseVo>();
			pageList.setTotal(pageResult.getTotalCount());
			pageList.setRows(pageResult.getRecords());
			result.setData(pageList);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "借款相关信息查询异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "借款相关信息查询异常");
		} finally {
			return result;
		}
	}

	/**
	 * 批次生成
	 * 
	 * @param reqBatchNumVo
	 * @return
	 */
	@RequestMapping("generBatch")
	@ResponseBody
	public Response<ResBacthNumVO> generateBatch(@RequestBody ReqBacthNumVO reqBatchNumVo) {
		Response<ResBacthNumVO> result = new Response<ResBacthNumVO>();
		try {
			ResEmployeeVO user = ShiroUtils.getCurrentUser();
			reqBatchNumVo.setServiceCode(user.getUsercode());
			result = ibaseLoanService.createBacthNum(reqBatchNumVo);
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "批次生成异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "批次生成异常");
		} finally {
			return result;
		}
	}

	/**
	 * 批次更新
	 * 
	 * @param reqBatchNumVo
	 * @return
	 */
	@RequestMapping("updBatch")
	@ResponseBody
	public Response<String> updateBatch(@RequestBody ReqUpdBatchVo reqBatchNumVo) {
		Response<String> result = new Response<String>();
		try {
			Response<String> response = ibaseLoanService.updateBacthNum(reqBatchNumVo);
			result.setData(response.getData());
			result.setRepCode(response.getRepCode());
			result.setRepMsg(response.getRepMsg());
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "批次更新异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "批次更新异常");
		} finally {
			return result;
		}
	}

	/**
	 * 批次查询
	 * 
	 * @param reqLoanBacthNumVo
	 * @return
	 */
	@RequestMapping("listBatchInfoPage")
	@ResponseBody
	public Response<ResponsePage<ResLoanBacthNumVo>> listBatchInfoPage(ReqLoanBacthNumVo reqLoanBacthNumVo) {
		Response<ResponsePage<ResLoanBacthNumVo>> result = new Response<ResponsePage<ResLoanBacthNumVo>>();
		try {
			if (reqLoanBacthNumVo.getPage() == 0 || reqLoanBacthNumVo.getRows() == 0) {
				throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
			}
			PageResult<ResLoanBacthNumVo> pageResult = ibaseLoanService.listBatchInfoPage(reqLoanBacthNumVo);
			ResponsePage<ResLoanBacthNumVo> responsePage = new ResponsePage<ResLoanBacthNumVo>();
			responsePage.setRows(pageResult.getRecords());
			responsePage.setTotal(pageResult.getTotalCount());
			result.setData(responsePage);
			result.setRepCode(pageResult.getCode());
			result.setRepMsg(pageResult.getResMsg());
		} catch (BusinessException e) {
			excConsole.printBusinessException(e, result, logger, "批次查询异常");
		} catch (Exception e) {
			excConsole.printException(e, result, logger, "批次查询异常");
		} finally {
			return result;
		}
	}

	/**
	 * 债权审核导出
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportRightsAudit")
	public void expLoanCheck(ReqLoanBaseVo requestVo, HttpServletResponse response) throws Exception {
		String excelName = StringUtils.stringAdd(BmsConstant.PCH, requestVo.getBatchNum(), requestVo.getChannelName(),BmsConstant.ZQSH, BmsConstant.EXCEL_SUFFIX);
		ExcelObjVo excelVo = new ExcelObjVo(excelName, "", 1, 0);
		Map<String, String[]> fields = new HashMap<String, String[]>();
		fields.put("fieldNames", EnumBH2Constants.LOAN_CHECK_EXP.getExcName());
		fields.put("fieldCodes", EnumBH2Constants.LOAN_CHECK_EXP.getExcCode());
		List<ResLoanCheckExpVo> datas = ibaseLoanService.listLoanCheckExp(requestVo);
		iExcelExport.createLoanCheckExcel(response, datas, fields, EnumBH2Constants.LOAN_CHECK_EXP.getClassObj(),
				excelVo);
	}

	/**
	 * 债权导出
	 * 
	 * @param requestVo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportRights")
	public void expCheck(ReqLoanBaseVo requestVo, HttpServletResponse response) throws Exception {
		String excelName = StringUtils.stringAdd(BmsConstant.PCH, requestVo.getBatchNum(), requestVo.getChannelName(),BmsConstant.ZQ, BmsConstant.EXCEL_SUFFIX);
		ExcelObjVo excelVo = new ExcelObjVo(excelName, "", 4, 0);
		Map<String, String[]> fields = new HashMap<String, String[]>();
		fields.put("fieldNames", EnumBH2Constants.LOAN_EXP.getExcName());
		fields.put("fieldCodes", EnumBH2Constants.LOAN_EXP.getExcCode());
		List<ResLoanExpVo> datas = ibaseLoanService.listLoanExp(requestVo);
		iExcelExport.createLoanExcel(response, datas, fields, EnumBH2Constants.LOAN_EXP.getClassObj(), excelVo);
	}
	
	/**
	 * 外贸信托债券导出
	 * @param requestVo
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("wmxtExpLoan")
	public void wmxtExpLoan(ReqLoanBaseVo requestVo, HttpServletResponse response) throws IOException{
		requestVo.setSysCode("bms");
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
//		ReqOrganizationVO reqOrganizationVO=new ReqOrganizationVO();
//		reqOrganizationVO.setSysCode("bms");
//		reqOrganizationVO.setUsercode(resEmployeeVO.getUsercode());
		ReqParamVO reqParamVO=new ReqParamVO();
		reqParamVO.setSysCode("bms");
		reqParamVO.setLoginUser(resEmployeeVO.getUsercode());
		Response<List<ResOrganizationVO>> responseResOrganizationVO=iOrganizationExecuter.findDataDeptsByAccount(reqParamVO);
		List<Long> orgIds=new ArrayList<Long>();
		for(ResOrganizationVO resOrganizationVO:responseResOrganizationVO.getData()){
			orgIds.add(resOrganizationVO.getId());
		}
		requestVo.setOrgIds(orgIds);
		ReqLoanBaseVo newReqLoanBaseVo=new ReqLoanBaseVo();
		newReqLoanBaseVo.setSysCode("bms");
		newReqLoanBaseVo.setBatchNum(requestVo.getBatchNum());
		Response<List<String>> responsLoanNo=ibaseLoanService.findLoanNoByNum(newReqLoanBaseVo);
		
		requestVo.setLoanNos(responsLoanNo.getData());
		Response<List<ResWMXTExportVo>> responseResWMXTExportVo=ibaseLoanService.wmxtExpLoanQuery(requestVo);
		List<Long> listLong=new ArrayList<Long>();
		//循环获取查询出来的管理营业部ID传给平台获取营业部信息
		for(ResWMXTExportVo resWMXTExportVoto:responseResWMXTExportVo.getData()){
			if(resWMXTExportVoto.getManageBranchId()!=null&&resWMXTExportVoto.getManageBranchId().length()!=0){
				listLong.add(Long.parseLong(resWMXTExportVoto.getManageBranchId()));
			}
		}
		ReqParamVO reqParamVOto=new ReqParamVO();
		reqParamVOto.setSysCode("bms");
		reqParamVOto.setOrgIds(listLong);
		//获取到对应的营业部集合
		Response<List<ResOrganizationVO>> returnResOrg=null;
		if(listLong.size()>0){
			returnResOrg=iOrganizationExecuter.findByIds(reqParamVOto);	
		}
		
		
		List<ReqWMXTDataVo> listResWMXTDataVo=new ArrayList<ReqWMXTDataVo>();
		//循环获取父节点ID
		if(null!=returnResOrg){
			for(ResOrganizationVO resOrganizationVO:returnResOrg.getData()){
				ReqWMXTDataVo v=new ReqWMXTDataVo();
				v.setId(resOrganizationVO.getId()+"");
				v.setParent_Id(resOrganizationVO.getParentId());
				v.setSysCode("bms");
				listResWMXTDataVo.add(v);
			}
		}
		//查询渤海信托机构城市表
		Response<List<ResBhxtCitOrgVo>> listResBhxtCitOrgVo=null;
		if(listResWMXTDataVo.size()>0){
			listResBhxtCitOrgVo=ibaseLoanService.findCodebyParentIds(listResWMXTDataVo);
		}
		//根据org_id等于parent_Id把拨号信托CODE赋值 给listResWMXTDataVo对应的实体
		if(null!=listResBhxtCitOrgVo){
			for(int i=0;i<listResBhxtCitOrgVo.getData().size();i++){
				for(int j=0;j<listResWMXTDataVo.size();j++){
					if(listResBhxtCitOrgVo.getData().get(i).getOrgId().toString().equals(listResWMXTDataVo.get(j).getParent_Id())){
						listResWMXTDataVo.get(j).setCode(listResBhxtCitOrgVo.getData().get(i).getCode());
					}
				}
			}
		}
		
		InputStream is = null;
        OutputStream out = null;
		StringBuffer contentBuf = new StringBuffer();
		for(ResWMXTExportVo vo:responseResWMXTExportVo.getData()){
			contentBuf.append(vo.getOrganizationCode());
			contentBuf.append("|+|");
			contentBuf.append(vo.getContractNum());
			contentBuf.append("|+|");
			if(vo.getManageBranchId()!=null&&vo.getManageBranchId().length()!=0){
				Boolean isEquals=false;
				for(ReqWMXTDataVo reqWMXTDataVoSetData:listResWMXTDataVo){
					if(vo.getManageBranchId().equals(reqWMXTDataVoSetData.getId())){
						if(null==reqWMXTDataVoSetData.getCode()||reqWMXTDataVoSetData.getCode().length()==0){
							contentBuf.append(vo.getLoggedArea());
							isEquals=true;
						}else{
							contentBuf.append(reqWMXTDataVoSetData.getCode());
							isEquals=true;
						}
					}
				}
				if(isEquals==false){
					contentBuf.append(vo.getLoggedArea());
				}
			}else{
				contentBuf.append(vo.getLoggedArea());
			}
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanNo());
			contentBuf.append("|+|");
			contentBuf.append(vo.getChannelSource());
			contentBuf.append("|+|");
			contentBuf.append(vo.getIdType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getIdNo());
			contentBuf.append("|+|");
			contentBuf.append(vo.getName());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCellPhone());
			contentBuf.append("|+|");
			contentBuf.append(vo.getHomePhone());
			contentBuf.append("|+|");
			contentBuf.append(vo.getIssuerPostcode());
			contentBuf.append("|+|");
			contentBuf.append(vo.getHomeAddress());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCreditApplication());
			contentBuf.append("|+|");
			contentBuf.append(vo.getPactMoney());
			contentBuf.append("|+|");
			contentBuf.append(vo.getContractLmt());
			contentBuf.append("|+|");
			contentBuf.append(vo.getApplyMoney());
			contentBuf.append("|+|");
			contentBuf.append(vo.getApplyTerm());
			contentBuf.append("|+|");
			contentBuf.append(vo.getRepaymentAccountType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getGbAccount());
			contentBuf.append("|+|");
			contentBuf.append(vo.getGbMode());
			contentBuf.append("|+|");
			contentBuf.append(vo.getDateOfDebitType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getDateOfDebitType2());
			contentBuf.append("|+|");
			contentBuf.append(vo.getStartrdate()==null?"":vo.getStartrdate());
			contentBuf.append("|+|");
			contentBuf.append(vo.getMaritalStatus());
			contentBuf.append("|+|");
			contentBuf.append(vo.getQualification());
			contentBuf.append("|+|");
			if(vo.getManageBranchId()!=null&&vo.getManageBranchId().length()!=0){
				Boolean isEquals=false;
				for(ReqWMXTDataVo reqWMXTDataVoSetData:listResWMXTDataVo){
					if(vo.getManageBranchId().equals(reqWMXTDataVoSetData.getId())){
						if(null==reqWMXTDataVoSetData.getCode()||reqWMXTDataVoSetData.getCode().length()==0){
							contentBuf.append(vo.getIdIssuerAddress());
							isEquals=true;
						}else{
							contentBuf.append(reqWMXTDataVoSetData.getCode());
							isEquals=true;
						}
					}
				}
				if(isEquals==false){
					contentBuf.append(vo.getIdIssuerAddress());
				}
			}else{
				contentBuf.append(vo.getIdIssuerAddress());
			}
			contentBuf.append("|+|");
			contentBuf.append(vo.getMonthSalary());
			contentBuf.append("|+|");
			contentBuf.append(vo.getHomeAddress2());
			contentBuf.append("|+|");
			contentBuf.append(vo.getHomePostcode());
			contentBuf.append("|+|");
			contentBuf.append(vo.getHomePhone1());
			contentBuf.append("|+|");
			contentBuf.append(vo.getOrganization());
			contentBuf.append("|+|");
			contentBuf.append(vo.getRepayMentway());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getBorrowerType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getProductCd());
			contentBuf.append("|+|");
			contentBuf.append(vo.getProductName());
			contentBuf.append("|+|");
			contentBuf.append(vo.getPoundage());
			contentBuf.append("|+|");
			contentBuf.append(vo.getRateem());
			contentBuf.append("|+|");
			contentBuf.append(vo.getPenaltyRateem());
			contentBuf.append("|+|");
			contentBuf.append(vo.getPenaltyInterestRate());
			contentBuf.append("|+|");
			contentBuf.append(vo.getGuaranteeDays());
			contentBuf.append("|+|");
			contentBuf.append(vo.getManageRate());
			contentBuf.append("|+|");
			contentBuf.append(vo.getManageRateRateem());
			contentBuf.append("|+|");
			contentBuf.append(vo.getGuaranteeFee());
			contentBuf.append("|+|");
			contentBuf.append(vo.getGuaranteeFeeRateem());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanBankIdStill());
			contentBuf.append("|+|");
			if(vo.getManageBranchId()!=null&&vo.getManageBranchId().length()!=0){
				Boolean isEquals=false;
				for(ReqWMXTDataVo reqWMXTDataVoSetData:listResWMXTDataVo){
					if(vo.getManageBranchId().equals(reqWMXTDataVoSetData.getId())){
						if(null==reqWMXTDataVoSetData.getCode()||reqWMXTDataVoSetData.getCode().length()==0){
							contentBuf.append(vo.getProvincesAndCities());
							isEquals=true;
						}else{
							contentBuf.append(reqWMXTDataVoSetData.getCode());
							isEquals=true;
						}
					}
				}
				if(isEquals==false){
					contentBuf.append(vo.getProvincesAndCities());
				}
			}else{
				contentBuf.append(vo.getProvincesAndCities());
			}
			contentBuf.append("|+|");
			contentBuf.append(vo.getCostOne());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCostTwo());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCostThree());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCostFour());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCostFix());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanDate()==null?"":vo.getLoanDate());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanAccountType()==null?"":vo.getLoanAccountType());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanBankCode()==null?"":vo.getLoanBankCode());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanAccountName()==null?"":vo.getLoanAccountName());
			contentBuf.append("|+|");
			contentBuf.append(vo.getLoanAccountCode()==null?"":vo.getLoanAccountCode());
			contentBuf.append("|+|");
			contentBuf.append("\n");
		}
		String subContentBuf=contentBuf.substring(0, contentBuf.length()-4);
		subContentBuf=subContentBuf+"\n";
		is = new ByteArrayInputStream(subContentBuf.toString().getBytes("gbk"));
		 
		
		//查询申请书上传下载表查看序号
		ReqLoanBaseVo reqLoanBaseVoQuerySeqFile=new ReqLoanBaseVo();
		reqLoanBaseVoQuerySeqFile.setSysCode("bms");
		reqLoanBaseVoQuerySeqFile.setFileType(EnumBH2Constants.债权导出供理财txt.getCode());
		Response<ResRequestFileOperateRecordVo> resRequestVo=ibaseLoanService.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
		String seqFile="";
		if(null==resRequestVo.getData()||resRequestVo.getData().getFileSeq()==0){
			seqFile="01";
		}else{
			Integer sqeFile=resRequestVo.getData().getFileSeq();
			reqLoanBaseVoQuerySeqFile.setBatchNum(requestVo.getBatchNum());
			Response<ResRequestFileOperateRecordVo> thisResRequestVo=ibaseLoanService.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
			if(null==thisResRequestVo||thisResRequestVo.getData().getFileSeq()==0){
				sqeFile++;
				seqFile=sqeFile+"";
			}else{
				seqFile=thisResRequestVo.getData().getFileSeq()+"";
			}
		}
		//如果小于10自动加0
		if(seqFile.length()<=1){
			seqFile="0"+seqFile;
		}
		
        Date nowDate = new Date();
		String fileName = DateUtil.defaultFormatDay(nowDate)+"_016_"+seqFile+"_LoanApply" + ".txt";
       
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.setContentType("application/octet-stream; charset=gbk");
        out = response.getOutputStream();
        byte [] temp = new byte[1024];
        int len = 0;
        while((len = is.read(temp))>0){
        	out.write(temp,0,len);
        }
        //logger.info(out.toString());
        out.flush();
        
        //判断在bms_request_file_operate_record表里面存在数据吗？不存在新增存在更新
        ReqLoanBaseVo reqCheck=new ReqLoanBaseVo();
        reqCheck.setSysCode("bms");
        reqCheck.setFileType(EnumBH2Constants.债权导出供理财txt.getCode());
        reqCheck.setBatchNum(requestVo.getBatchNum());
        Integer seqFileInteger=null;
        if(seqFile.indexOf("0")==0){
        	seqFileInteger=Integer.parseInt(seqFile.substring(1,seqFile.length()));
        }else{
        	seqFileInteger=Integer.parseInt(seqFile);
        }
        reqCheck.setSeqFile(seqFileInteger);
        ibaseLoanService.checkRequestManagerOperateRecord(reqCheck);
	}

	/**
	 * 外贸信托还款计划导出 
	 */
	@RequestMapping("wmxtExpLoanCheck")
	public void wmxtExpLoanCheck(ReqLoanBaseVo requestVo, HttpServletResponse response) throws IOException{
		requestVo.setSysCode("bms");
		
		ReqLoanBaseVo newReqLoanBaseVo=new ReqLoanBaseVo();
		newReqLoanBaseVo.setSysCode("bms");
		newReqLoanBaseVo.setBatchNum(requestVo.getBatchNum());
		Response<List<String>> responsLoanNo=ibaseLoanService.findLoanNoByNum(newReqLoanBaseVo);
		
		requestVo.setLoanNos(responsLoanNo.getData());
		Response<List<ResWMXTExportCheckVo>> responseResWMXTExportVo=ibaseLoanService.wmxtExpLoanCheck(requestVo);
		
		InputStream is = null;
        OutputStream out = null;
		StringBuffer contentBuf = new StringBuffer();
		contentBuf.append("01603");
		for(ResWMXTExportCheckVo vo:responseResWMXTExportVo.getData()){
			contentBuf.append("|+|");
			contentBuf.append(vo.getContractNum());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCurrentTerm());
			contentBuf.append("|+|");
			contentBuf.append(vo.getReturnDate());
			contentBuf.append("|+|");
			contentBuf.append(vo.getReturneterm());
			contentBuf.append("|+|");
			contentBuf.append(vo.getCurrentAccrual());
			contentBuf.append("|+|");
			contentBuf.append(vo.getPrincipalBalance());
			contentBuf.append("\n");
		}
		
		is = new ByteArrayInputStream(contentBuf.toString().getBytes("gbk"));
		 
		
		//查询申请书上传下载表查看序号
				ReqLoanBaseVo reqLoanBaseVoQuerySeqFile=new ReqLoanBaseVo();
				reqLoanBaseVoQuerySeqFile.setSysCode("bms");
				reqLoanBaseVoQuerySeqFile.setFileType(EnumBH2Constants.还款计划导出txt.getCode());
				Response<ResRequestFileOperateRecordVo> resRequestVo=ibaseLoanService.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
				String seqFile="";
				if(null==resRequestVo.getData()||resRequestVo.getData().getFileSeq()==0){
					seqFile="01";
				}else{
					Integer sqeFile=resRequestVo.getData().getFileSeq();
					reqLoanBaseVoQuerySeqFile.setBatchNum(requestVo.getBatchNum());
					Response<ResRequestFileOperateRecordVo> thisResRequestVo=ibaseLoanService.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
					if(null==thisResRequestVo||thisResRequestVo.getData().getFileSeq()==0){
						sqeFile++;
						seqFile=sqeFile+"";
					}else{
						seqFile=thisResRequestVo.getData().getFileSeq()+"";
					}
				}
				//如果小于10自动加0
				if(seqFile.length()<=1){
					seqFile="0"+seqFile;
				}
		
		
		
        Date nowDate = new Date();
		String fileName = DateUtil.defaultFormatDay(nowDate)+"_016_"+seqFile+"_RetuPlan" + ".txt";
       
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.setContentType("application/octet-stream; charset=gbk");
        out = response.getOutputStream();
        byte [] temp = new byte[1024];
        int len = 0;
        while((len = is.read(temp))>0){
        	out.write(temp,0,len);
        }
       // logger.info(out.toString());
        out.flush();
        
        
      //判断在bms_request_file_operate_record表里面存在数据吗？不存在新增存在更新
        ReqLoanBaseVo reqCheck=new ReqLoanBaseVo();
        reqCheck.setSysCode("bms");
        reqCheck.setFileType(EnumBH2Constants.还款计划导出txt.getCode());
        reqCheck.setBatchNum(requestVo.getBatchNum());
        Integer seqFileInteger=null;
        if(seqFile.indexOf("0")==0){
        	seqFileInteger=Integer.parseInt(seqFile.substring(1,seqFile.length()));
        }else{
        	seqFileInteger=Integer.parseInt(seqFile);
        }
        reqCheck.setSeqFile(seqFileInteger);
        ibaseLoanService.checkRequestManagerOperateRecord(reqCheck);
	}

}
