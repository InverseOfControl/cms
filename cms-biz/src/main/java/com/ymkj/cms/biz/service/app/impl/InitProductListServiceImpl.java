
package com.ymkj.cms.biz.service.app.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.dao.app.IInitProductInfoDao;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.app.IInitProductListService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class InitProductListServiceImpl implements IInitProductListService{
	
	public static String LOG_TAG = "InitProductListServiceImpl.";
	
	private Log log = LogFactory.getLog(InitProductListServiceImpl.class);

	@Autowired
	private IBMSProductExecuter iBMSProductExecuter;
	
	@Autowired
	private IBMSOrgLimitChannelExecuter iBMSOrgLimitChannelExecuter;

	@Autowired
	private IEmployeeExecuter iEmployeeExecuter; 
	
	@Autowired
	private IInitProductInfoDao iInitProductInfoDao;

	@Override
	public Map<String, Object> getProductListData(String userCode) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//产品申请期限
		ResListVO<ResBMSOrgLimitChannelVO> productAuditLimitResponse = null; 
		List<ResBMSOrgLimitChannelVO> productAuditLimit = null;
		ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO = new ReqBMSOrgLimitChannelVO();
		reqBMSOrgLimitChannelVO.setSysCode("bms");
		reqBMSOrgLimitChannelVO.setServiceCode(userCode);
		reqBMSOrgLimitChannelVO.setServiceName(userCode);
		reqBMSOrgLimitChannelVO.setIp("127.0.0.1");
		
		//根据员工编号获取结构编号
		ReqEmployeeVO reqEmployeeVO = new ReqEmployeeVO();
		reqEmployeeVO.setSysCode("bms");
		reqEmployeeVO.setUsercode(userCode);
		Response<List<ResOrganizationVO>>  organizationResponse = iEmployeeExecuter.findDeptsByAccount(reqEmployeeVO);

		if(null == organizationResponse){
			log.info(LOG_TAG+"getProductListData：获取【组织机构信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(),CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		
		List<ResOrganizationVO>  organizationList = organizationResponse.getData();
		if(CollectionUtils.isEmpty(organizationList)){
			log.info(LOG_TAG+"getProductListData：获取【组织机构信息】为空！");
			throw new CoreException(CoreErrorCode.DEFAULT.getErrorCode(),CoreErrorCode.DEFAULT.getDefaultMessage());
		}
		Long orgId = organizationList.get(0).getId();
		
		//获取机构编码获取产品列表信息
		ReqBMSProductVO reqBMSProductVO = new ReqBMSProductVO(); 
		reqBMSProductVO.setSysCode("bms");
		reqBMSProductVO.setOrgId(orgId);

		ResListVO<ResBMSProductVO> productResponse = iBMSProductExecuter.findByOrgId(reqBMSProductVO);
		
		if(null == productResponse){
			log.info(LOG_TAG+"getProductListData：获取【产品信息】失败！【接口返回：null】");
			throw new CoreException(CoreErrorCode.FACADE_ERROR.getErrorCode(), CoreErrorCode.FACADE_ERROR.getDefaultMessage());
		}
		
		List<ResBMSProductVO> productList = productResponse.getCollections();
		/*if(CollectionUtils.isEmpty(productList)){
			log.info(LOG_TAG+"getProductListData：获取【产品信息】为空！");
			throw new CoreException(CoreErrorCode.DEFAULT.getErrorCode(),CoreErrorCode.DEFAULT.getDefaultMessage());
		}*/
		
		//封装产品列表信息和产品申请期限列表信息
		List<Map<String,Object>> productListData = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(productList)){
			for (ResBMSProductVO resBMSProductVO : productList) {
				Map<String,Object> tempMap = new LinkedMap();
				tempMap.put("name", ObjectUtils.toString(resBMSProductVO.getCode()));
				tempMap.put("title", ObjectUtils.toString(resBMSProductVO.getName()));
				tempMap.put("isDefault", "0");
				tempMap.put("productDesc", ObjectUtils.toString(resBMSProductVO.getName()));
				tempMap.put("floorLimit", ObjectUtils.toString(resBMSProductVO.getFloorLimit().longValue()));
				tempMap.put("ceilingLimit", ObjectUtils.toString(resBMSProductVO.getUpperLimit().longValue()));
				tempMap.put("applyRate", ObjectUtils.toString(resBMSProductVO.getRate()));
				tempMap.put("moduleAuthList", getModuleAuthList( this.convertModuleAuth(resBMSProductVO.getCode())));
				tempMap.put("moduleKeyList", getModuleKey( this.convertModuleAuth(resBMSProductVO.getCode())));
				
				//申请期限列表信息
				reqBMSOrgLimitChannelVO.setProductId(resBMSProductVO.getProductId());
				reqBMSOrgLimitChannelVO.setOrgId(orgId);
				
				productAuditLimitResponse = iBMSOrgLimitChannelExecuter.listOrgProductLimitByOrgProApp(reqBMSOrgLimitChannelVO);
				
				if(productAuditLimitResponse.isSuccess()){
					productAuditLimit = productAuditLimitResponse.getCollections();
					
					if(CollectionUtils.isNotEmpty(productAuditLimit)){
						List<Map<String,Object>> applyTermTempList = new ArrayList<Map<String,Object>>();
						for (ResBMSOrgLimitChannelVO resBMSOrgLimitChannelVO : productAuditLimit) {
							Map<String,Object> applyTermTempMap = new HashMap<>();
							int auditLimit =  resBMSOrgLimitChannelVO.getAuditLimit();
							/**获取产品上下限*/
							ReqBMSOrgLimitChannelVO upperLowerLimitVO = new ReqBMSOrgLimitChannelVO();
							BeanUtils.copyProperties(reqBMSOrgLimitChannelVO, upperLowerLimitVO);
							upperLowerLimitVO.setAuditLimit(auditLimit);
							Response<ResBMSOrgLimitChannelVO> upperLowerLimitResponse = iBMSOrgLimitChannelExecuter.findOrgLimitChannelLimitUnion(upperLowerLimitVO);
							
							resBMSOrgLimitChannelVO  = upperLowerLimitResponse.getData();
							
							applyTermTempMap.put("name", auditLimit);
							applyTermTempMap.put("title", ObjectUtils.toString(auditLimit));
							applyTermTempMap.put("isDefault", "0");
							applyTermTempMap.put("floorLimitTh", resBMSOrgLimitChannelVO.getFloorLimit().longValue());
							applyTermTempMap.put("ceilingLimitTh", resBMSOrgLimitChannelVO.getUpperLimit().longValue());
							
							applyTermTempList.add(applyTermTempMap);
						}
						tempMap.put("applyTermList", applyTermTempList);
						productListData.add(tempMap);
					}
				}
			}
		}	
		//取消原因列表
		List<Map<String,Object>> refuseReasonList = iInitProductInfoDao.getRefuseReasonList();
		
		//接口状态列表
		List<Map<String,Object>> stautsList = this.getStautsList();
		
		//封装最终响应map
		resultMap.put("userCode", userCode);
		resultMap.put("flag",true);
		resultMap.put("productListData", productListData);//产品信息（包含申请期限和模块列表）
		resultMap.put("refuseReasonList", refuseReasonList);
		resultMap.put("stautsList", stautsList);
		
		return resultMap;
	}

	/**
	 * <p>Description:获取申请单状态列表</p>
	 * @uthor YM10159
	 * @date 2017年4月24日 下午4:12:04
	 * 0-灰，1-红，2-蓝，3-全部
	 */
	public List<Map<String, Object>> getStautsList () {
		List<Map<String, Object>> stautsList = new ArrayList<Map<String, Object>>();
		String[] titleArr = {"全部-3","待签约-2","已结清-0","拒绝-1","逾期-1","取消-0","申请中-2","正常-2","待放款-2","信审退回-1"};

		for (int i = 0; i < titleArr.length; i++) {
			Map<String,Object> tempMap = new HashMap<>();
			String titleStr = titleArr[i];
			tempMap.put("title", titleStr.split("-")[0]);
			tempMap.put("colorType", titleStr.split("-")[1]);

			stautsList.add(tempMap);
		}

		return stautsList;
	}

	/**
	 * <p>Description:获取模块列表</p>
	 * @uthor YM10159
	 * @date 2017年4月24日 下午4:25:38
	 * @param @param moduleAuthList
	 */
	public List<String> getModuleAuthList(List<String> moduleAuthList){
		List<String> retList = new ArrayList<String>();
		
		for(String moduleAuth : moduleAuthList){
			if ("ESTATE".equals(moduleAuth)) {
				retList.add("房产信息表");
			}
			if ("CAR".equals(moduleAuth)) {
				retList.add("车辆信息表");
			}
			if ("POLICY".equals(moduleAuth)) {
				retList.add("保单信息表");
			}
			if ("PROVIDENT".equals(moduleAuth)) {
				retList.add("公积金信息表");
			}
			if ("CARDLOAN".equals(moduleAuth)) {
				retList.add("卡友贷信息");
			}
			if ("MERCHANTLOAN".equals(moduleAuth)) {
				retList.add("淘宝商户贷信息表");
			}
			if ("MASTERLOAN".equals(moduleAuth)) {
				retList.add("网购达人贷信息表");
			}
			if ("MASTERLOAN_B".equals(moduleAuth)) {
				retList.add("网购达人贷B信息表");
			}
		}
		return retList;
	}
	
	/**
	 * 获取产品所属模块key
	 * @param moduleAuthList
	 */
	public List<String> getModuleKey(List<String> moduleAuthList){
		List<String> retList = new ArrayList<String>();
		
		for(String moduleAuth : moduleAuthList){
			if ("ESTATE".equals(moduleAuth)) {
				retList.add("estateInfo");
			}
			if ("CAR".equals(moduleAuth)) {
				retList.add("carInfo");
			}
			if ("POLICY".equals(moduleAuth)) {
				retList.add("policyInfo");
			}
			if ("PROVIDENT".equals(moduleAuth)) {
				retList.add("providentInfo");
			}
			if ("CARDLOAN".equals(moduleAuth)) {
				retList.add("cardLoanInfo");
			}
			if ("MERCHANTLOAN".equals(moduleAuth)) {
				retList.add("merchantLoanInfo");
			}
			if ("MASTERLOAN".equals(moduleAuth)) {
				retList.add("masterLoanInfo");
			}
			if ("MASTERLOAN_B".equals(moduleAuth)) {
				retList.add("masterLoanBInfo");
			}
		}
		return retList;
	}
	
	public List<String> convertModuleAuth(String productCode){
		List<String> retList = new ArrayList<String>();
		
		if(productCode.equals("00011") || productCode.equals("00012") || productCode.equals("00003")) retList.add("ESTATE");
		if(productCode.equals("00010")) retList.add("CAR");
		if(productCode.equals("00014")) retList.add("POLICY");
		if(productCode.equals("00013")) retList.add("PROVIDENT");
		if(productCode.equals("00018")) retList.add("CARDLOAN");
		if(productCode.equals("00001")) retList.add("SALARYLOAN");
		if(productCode.equals("00016")) retList.add("MERCHANTLOAN");
		if(productCode.equals("00015")) retList.add("MASTERLOAN");
		if(productCode.equals("00020")) retList.add("MASTERLOAN_B");
		
		return retList;
	}

}



