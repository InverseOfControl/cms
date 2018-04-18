package com.ymkj.cms.web.boss.service.master.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.SystemCode;
import com.ymkj.cms.web.boss.common.TreeJson;
import com.ymkj.cms.web.boss.common.TreeUtil;
import com.ymkj.cms.web.boss.facade.apply.ChannelFacade;
import com.ymkj.cms.web.boss.facade.apply.ChannelProductFacade;
import com.ymkj.cms.web.boss.facade.apply.OrgLimitChannelFacade;
import com.ymkj.cms.web.boss.facade.apply.OutletsProductFacade;
import com.ymkj.cms.web.boss.service.master.IOutletsProductService;
import com.ymkj.pms.biz.api.enums.OrganizationEnum;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationTreeVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;
@Service
public class OutletsProductServiceImpl implements IOutletsProductService{
	
	@Autowired
	private OutletsProductFacade outProFacade;
	
	@Autowired
	private ChannelFacade channelFacade;
	
	@Autowired
	private ChannelProductFacade chaProFacade;
	
	@Autowired
	private OrgLimitChannelFacade orgLimitChannelFacade;
	
	@Override
	public List<ResBMSProductVO> findProByChannelId(ReqBMSProductVO reqBMSProductVO) {
		return outProFacade.findProByChannelId(reqBMSProductVO);
	}
	@Override
	public List<ResBMSProductVO> listProByCondition(ReqBMSProductVO reqBMSProductVO) {
		return outProFacade.listProByCondition(reqBMSProductVO);
	}

	@Override
	public PageResult<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		PageResult<ResBMSOrgLimitChannelVO> pageResult =  outProFacade.listPage(reqBMSOrgLimitChannelVO);
		List<ResBMSOrgLimitChannelVO> recordList = pageResult.getRecords();
		if(recordList != null){
			//填充机构名称
			ReqOrganizationVO vo = new ReqOrganizationVO();
			for (ResBMSOrgLimitChannelVO record : recordList) {
				Long orgId = record.getOrgId();
				vo.setId(orgId);
				ResOrganizationVO resvo = orgLimitChannelFacade.findOrgById(vo);
				if(resvo != null){
					record.setOrgName(resvo.getName());
				}else {
					System.out.println(vo.getId());
				}
			}
			
		}
		
		return pageResult;
	}

	@Override
	public Boolean updateOrgProductLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return outProFacade.updateProductLimit(reqBMSOrgLimitChannelVO);
	}

	@Override
	public boolean saveOrgChannelProduct(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return outProFacade.saveOrgProductLimit(reqBMSOrgLimitChannelVO);
	}
	@Override
	public boolean saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return outProFacade.saveRateOrgChannelLimit(reqBMSOrgLimitChannelVO);
	}

	@Override
	public List<TreeJson> findAllDeptsTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		//机构树数据获取
		ReqParamVO reqParamVO = new ReqParamVO();
		//设置需要剔除的机构类型
		List<String> orgTypes = new ArrayList<String>();
		//机构类型orgType枚举如下：
//		1. funcDepartment:职能部门
//		2. headquarters：总部
//		3. area：区域
//		4. division：分部
//		5.city：城市
//		6. department：营业部
//		7. group：组
		orgTypes.add(OrganizationEnum.OrgType.FUNC_DEPARTMENT.getCode());
		orgTypes.add(OrganizationEnum.OrgType.GROUP.getCode());
		
		reqParamVO.setOrgTypes(orgTypes);
		ResOrganizationTreeVO result = orgLimitChannelFacade.findOrgTree(reqParamVO);
		//树结构展开成平铺数据，treeJson
		Integer deep = 1;
		List<TreeJson> treeOrgList = TreeUtil.tileTreeDate(result, deep);
		
		// 返回结果处理
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		
		//判断产品期限是否为空,不为空，根据渠道和产品期限获取数据,到门店渠道产品表中获取数据
        if(reqBMSOrgLimitChannelVO.getChannelLimitStr() != null && !"".equals(reqBMSOrgLimitChannelVO.getChannelLimitStr())){
        	//到门店渠道产品期限表中获取数据
        	String []limitChaId = reqBMSOrgLimitChannelVO.getChannelLimitStr().split("_");
        	ReqBMSOrgLimitChannelVO reqLimitChannel = new ReqBMSOrgLimitChannelVO();
            reqLimitChannel.setSysCode(SystemCode.SysCode);
        	reqLimitChannel.setAuditLimitId(Long.parseLong(limitChaId[0]));
        	reqLimitChannel.setChannelId(Long.parseLong(limitChaId[1]));
        	//剔除产品是否删除，产品期限是否删除，渠道是否删除
        	List<ResBMSOrgLimitChannelVO> orgLimitList = outProFacade.listOrgLimitChannelBy(reqLimitChannel);
        	if(orgLimitList !=null && orgLimitList.size()>0){
        		for (ResBMSOrgLimitChannelVO orglimit : orgLimitList) {
        			for(TreeJson limitTemp : treeOrgList){
        				if(limitTemp.getId().equals(String.valueOf(orglimit.getOrgId()))){
        				limitTemp.setChecked("true");
        				}
            		}
        		}
        	}
        }
        treeDataList.addAll(treeOrgList);
        List<TreeJson> newTreeDataList = TreeUtil.formatBusinessOrgTree(treeDataList);
		return newTreeDataList;
	}
	@Override
	public List<TreeJson> findAllDeptsRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		//机构树数据获取
		ReqParamVO reqParamVO = new ReqParamVO();
		//设置需要剔除的机构类型
		List<String> orgTypes = new ArrayList<String>();
		//机构类型orgType枚举如下：
//		1. funcDepartment:职能部门
//		2. headquarters：总部
//		3. area：区域
//		4. division：分部
//		5.city：城市
//		6. department：营业部
//		7. group：组
		orgTypes.add(OrganizationEnum.OrgType.FUNC_DEPARTMENT.getCode());
		orgTypes.add(OrganizationEnum.OrgType.GROUP.getCode());
		
		reqParamVO.setOrgTypes(orgTypes);
		ResOrganizationTreeVO result = orgLimitChannelFacade.findOrgTree(reqParamVO);
		//树结构展开成平铺数据，treeJson
		Integer deep = 1;
		List<TreeJson> treeOrgList = TreeUtil.tileTreeDate(result, deep);
		
		// 返回结果处理
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		
		//判断产品期限是否为空,不为空，根据渠道和产品期限获取数据,到门店渠道产品表中获取数据
		if(reqBMSOrgLimitChannelVO.getChannelLimitStr() != null && !"".equals(reqBMSOrgLimitChannelVO.getChannelLimitStr())){
			//到门店渠道产品期限表中获取数据
			String []limitChaId = reqBMSOrgLimitChannelVO.getChannelLimitStr().split("_");
			ReqBMSOrgLimitChannelVO reqLimitChannel = new ReqBMSOrgLimitChannelVO();
			reqLimitChannel.setSysCode(SystemCode.SysCode);
			reqLimitChannel.setAuditLimitId(Long.parseLong(limitChaId[0]));
			reqLimitChannel.setChannelId(Long.parseLong(limitChaId[1]));
			reqLimitChannel.setIsDeleted(0L);
			//剔除产品是否删除，产品期限是否删除，渠道是否删除
			List<ResBMSOrgLimitChannelVO> orgLimitList = outProFacade.listOrgLimitChannelBy(reqLimitChannel);
			
			List<TreeJson> treeOrgShowList = new ArrayList<TreeJson>();

			//依据已有配置逆向查找，可显示机构
			for (ResBMSOrgLimitChannelVO orglimit : orgLimitList) {
				TreeUtil.tileShowTree(orglimit.getOrgId().toString(), treeOrgList, treeOrgShowList);
			}
			
			//勾选配置
			for (ResBMSOrgLimitChannelVO orglimit : orgLimitList) {
				for(TreeJson limitTemp : treeOrgShowList){
					if(limitTemp.getId().equals(orglimit.getOrgId().toString())){
						if(orglimit.getIsCanPreferential()!=null && orglimit.getIsCanPreferential()==0){
							limitTemp.setChecked("true");
						}
						break;
					}
				}
			}
			treeDataList.addAll(treeOrgShowList);
		}
		if(treeDataList.isEmpty()){
			return null;
		}
		List<TreeJson> newTreeDataList = TreeUtil.formatBusinessOrgTree(treeDataList);
		return newTreeDataList;
	}

	@Override
	public List<TreeJson> findChannelProTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		//设置根目录
        TreeJson rootData = new TreeJson();
        rootData.setId("");
        rootData.setText("渠道名称");
        rootData.setDeep("1");
        treeDataList.add(rootData);
        /**
         * 1.从渠道产品期限表中获取渠道产品期限集合
         * 2.通过渠道ID集合获取渠道集合
         * 3.通过渠道id集合获取产品集合
         * 4.通过期限ID集合获取期限集合
         * 5.期限跟产品关联关系
         */
        //渠道集合list
        List<ResBMSChannelVO>  channelList = new ArrayList<ResBMSChannelVO>();
        
        //渠道id集合
        List<Long> channelIds = new ArrayList<Long>();
        
        //产品集合
        List<ResBMSProductVO>  productList = new ArrayList<ResBMSProductVO>(); 
        //审批期限集合
        List<ResBMSProductAuditLimitVO> listLimit = new ArrayList<ResBMSProductAuditLimitVO>();
        
        ReqBMSOrgLimitChannelVO reqLimitChannel = new ReqBMSOrgLimitChannelVO();
        reqLimitChannel.setSysCode(SystemCode.SysCode);
        //渠道产品配置查找
        List<ResBMSOrgLimitChannelVO> channelLimit = chaProFacade.listLimitChannelBy(reqLimitChannel);
        if(channelLimit != null && channelLimit.size()>0){
        	//只可配置，启用的渠道产品配置
	        for(ResBMSOrgLimitChannelVO vo : channelLimit){
	        	if(vo.getIsDisabled() == null || vo.getIsDisabled() == 1){
	        		continue;
	        	}
	        	if(!channelIds.contains(vo.getChannelId())){
	        		channelIds.add(vo.getChannelId());
	        	}
	       		//获取审批期限
	        	ReqBMSProductAuditLimitVO reqAuditLimitVO = new ReqBMSProductAuditLimitVO();
	        	reqAuditLimitVO.setAuditLimitId(vo.getAuditLimitId());
	        	reqAuditLimitVO.setSysCode(SystemCode.SysCode);
	        	//启用的审批期限0
	        	reqAuditLimitVO.setIsDisabled(0L);
	        	ResBMSProductAuditLimitVO vo1 = chaProFacade.findAuditLimitById(reqAuditLimitVO);
	        	if(vo1 !=null){
	        		//设置渠道的值
	        		vo1.setChannelId(vo.getChannelId());
	        		listLimit.add(vo1);            	
	        	}
	        }
	        //获取去重的渠道
	        if(channelIds !=null && channelIds.size()>0){
	        	for(Long channelId :channelIds){
	        		ResBMSChannelVO channelVO = channelFacade.findById(channelId);
	        		if(channelVO != null){
	        			channelList.add(channelVO);
	        		}
	        	}
	        }
	        //构造产品对象
	        ReqBMSProductVO reqProVO = new ReqBMSProductVO();
	    	reqProVO.setSysCode(SystemCode.SysCode);
	    	reqProVO.setChannelIds(channelIds);
	        //渠道产品期限配置了的产品
	        productList = this.findProByChannelId(reqProVO);
	        
	        //===============================树结构数据构建
	        if(channelList !=null && channelList.size()>0){
	        	for(ResBMSChannelVO channel :channelList){
	        		//将渠道放入树中
	        		TreeJson treeChannel = new TreeJson();
	        		treeChannel.setId(String.valueOf(channel.getId()));
	        		treeChannel.setDeep("2");
	        		treeChannel.setPid("");
	        		treeChannel.setText(channel.getName());
	        		treeDataList.add(treeChannel);
	        	}
	        }
	       
	        if(productList !=null && productList.size()>0){
	        	for(ResBMSProductVO product :productList){
	        		//只可配置，启用的渠道产品配置
	        		if(product.getBlcIsDisabled() == null || product.getBlcIsDisabled() == 1){
		        		continue;
		        	}
	        		
	        		TreeJson treeProduct = new TreeJson();
	        		treeProduct.setId(String.valueOf(product.getProductId()+"_"+product.getChannelId()));
	        		treeProduct.setDeep("3");
	        		treeProduct.setPid(String.valueOf(product.getChannelId()));
	        		treeProduct.setText(product.getName());
	        		treeDataList.add(treeProduct);    			
	        	}
	        }
	        //审批期限
	    	if(listLimit !=null && listLimit.size()>0){
	    		List<TreeJson> treeAuditList = new ArrayList<TreeJson>();
	        	for (ResBMSProductAuditLimitVO limit : listLimit) {
	        		TreeJson treeLimit = new TreeJson();
	        		Long times=System.currentTimeMillis();
	        		treeLimit.setId(String.valueOf(limit.getAuditLimitId()+"_"+limit.getChannelId()+"_4"));
	        		treeLimit.setDeep("4");
	        		treeLimit.setPid(String.valueOf(limit.getProductId()+"_"+limit.getChannelId()));
	        		treeLimit.setText(String.valueOf(limit.getAuditLimit())+"期");
	        		treeAuditList.add(treeLimit);
	        	}
	    		 //判断orgId是否为空,不为空，根据门店ID获取数据,到门店渠道产品表中获取数据
	            if(reqBMSOrgLimitChannelVO.getOrgId() !=null){
	            	//到门店渠道产品期限表中获取数据
	            	reqLimitChannel.setOrgId(reqBMSOrgLimitChannelVO.getOrgId());
	            	List<ResBMSOrgLimitChannelVO> orgLimitList = outProFacade.listOrgLimitChannelBy(reqLimitChannel);
	            	if(orgLimitList !=null && orgLimitList.size()>0){
	            		for (ResBMSOrgLimitChannelVO orglimit : orgLimitList) {
	            			for(TreeJson limitTemp : treeAuditList){
	            				String idStr[] = limitTemp.getId().split("_");
	            				if(idStr[0].equals(String.valueOf(orglimit.getAuditLimitId())) 
	            						&&idStr[1].equals(String.valueOf(orglimit.getChannelId()))){
	            				limitTemp.setChecked("true");
	            				}
	                		}
	            		}
	            	}
	            }
	            treeDataList.addAll(treeAuditList);
	    	}
    	}
        List<TreeJson> newTreeDataList =TreeUtil.formatBusinessOrgTree(treeDataList);
        return newTreeDataList;
    }

	@Override
	public boolean logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO) {
		if(reqOrgLimitChannelVO!=null){
		int resMsg = outProFacade.logicalDelete(reqOrgLimitChannelVO);
		return resMsg > 0 ? true : false;
		}else{
			return false;
		}
	}

	@Override
	public List<ResOrganizationVO> findAllDepts(ReqOrganizationVO reqOrganizationVo) {
		
		return orgLimitChannelFacade.findAllDepts(reqOrganizationVo);
	}

	@Override
	public Map<String, Object> orgProductLimitDisableCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return chaProFacade.orgProductLimitDisableCheck(reqBMSOrgLimitChannelVO);
	}
	
	
	@Override
	public List<TreeJson> findChannelProRateTree(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		//设置根目录
        TreeJson rootData = new TreeJson();
        rootData.setId("");
        rootData.setText("渠道名称");
        rootData.setDeep("1");
        treeDataList.add(rootData);
        /**
         * 1.从渠道产品期限表中获取渠道产品期限集合
         * 2.通过渠道ID集合获取渠道集合
         * 3.通过渠道id集合获取产品集合
         * 4.通过期限ID集合获取期限集合
         * 5.期限跟产品关联关系
         */
        //渠道集合list
        List<ResBMSChannelVO>  channelList = new ArrayList<ResBMSChannelVO>();
        
        //渠道id集合
        List<Long> channelIds = new ArrayList<Long>();
        //产品期限集合
        List<Long> auditLimitIds = new ArrayList<Long>();
        //产品期限集合
        List<String> channelAuditLimitList = new ArrayList<String>();
        //产品集合
        List<ResBMSProductVO>  productList = new ArrayList<ResBMSProductVO>(); 
        //审批期限集合
        List<ResBMSProductAuditLimitVO> listLimit = new ArrayList<ResBMSProductAuditLimitVO>();
        
        ReqBMSOrgLimitChannelVO reqLimitChannel = new ReqBMSOrgLimitChannelVO();
        reqLimitChannel.setSysCode(SystemCode.SysCode);
        //到门店渠道产品期限表中获取数据
        reqLimitChannel.setOrgId(reqBMSOrgLimitChannelVO.getOrgId());
    	reqLimitChannel.setBlcIsDisabled("0");
    	//已配置数据集合
    	List<ResBMSOrgLimitChannelVO> channelLimit = outProFacade.listOrgLimitChannelRateBy(reqLimitChannel);

        if(channelLimit != null && channelLimit.size()>0){
        	//只可配置，启用的渠道产品配置
	        for(ResBMSOrgLimitChannelVO vo : channelLimit){
	        	if(!channelIds.contains(vo.getChannelId())){
	        		channelIds.add(vo.getChannelId());
	        	}
	        	String channelAuditLimit = vo.getChannelId() + "_" + vo.getAuditLimitId();
	        	if(!channelAuditLimitList.contains(channelAuditLimit)){
	        		channelAuditLimitList.add(channelAuditLimit);
	        	}
	       		//获取审批期限
	        	ReqBMSProductAuditLimitVO reqAuditLimitVO = new ReqBMSProductAuditLimitVO();
	        	reqAuditLimitVO.setAuditLimitId(vo.getAuditLimitId());
	        	reqAuditLimitVO.setSysCode(SystemCode.SysCode);
	        	//启用的审批期限0
	        	reqAuditLimitVO.setIsDisabled(0L);
	        	ResBMSProductAuditLimitVO vo1 = chaProFacade.findAuditLimitById(reqAuditLimitVO);
	        	if(vo1 !=null){
	        		//设置渠道的值
	        		vo1.setChannelId(vo.getChannelId());
	        		listLimit.add(vo1);            	
	        	}
	        }
	        //获取去重的渠道
	        if(channelIds !=null && channelIds.size()>0){
	        	for(Long channelId :channelIds){
	        		ResBMSChannelVO channelVO = channelFacade.findById(channelId);
	        		//为优惠渠道
	        		if(channelVO != null && new Long(0).equals(channelVO.getIsCanPreferential())){
	        			channelList.add(channelVO);
	        		}
	        	}
	        }
	        if(channelAuditLimitList.isEmpty()){
	        	return null;
	        }
	        //构造产品对象
	        ReqBMSProductVO reqProVO = new ReqBMSProductVO();
	    	reqProVO.setSysCode(SystemCode.SysCode);
	    	reqProVO.setChannelAuditLimitList(channelAuditLimitList);
	        //渠道产品期限配置了的产品
	        productList = this.findProRateByChannelId(reqProVO);
	        
	        //===============================树结构数据构建
	        if(channelList !=null && channelList.size()>0){
	        	for(ResBMSChannelVO channel :channelList){
	        		//将渠道放入树中
	        		TreeJson treeChannel = new TreeJson();
	        		treeChannel.setId(String.valueOf(channel.getId()));
	        		treeChannel.setDeep("2");
	        		treeChannel.setPid("");
	        		treeChannel.setText(channel.getName());
	        		treeDataList.add(treeChannel);
	        	}
	        }
	       
	        if(productList !=null && productList.size()>0){
	        	for(ResBMSProductVO product :productList){
	        		//只可配置，启用的渠道产品配置
	        		if(product.getBlcIsDisabled() == null || product.getBlcIsDisabled() == 1){
		        		continue;
		        	}
	        		
	        		TreeJson treeProduct = new TreeJson();
	        		treeProduct.setId(String.valueOf(product.getProductId()+"_"+product.getChannelId()));
	        		treeProduct.setDeep("3");
	        		treeProduct.setPid(String.valueOf(product.getChannelId()));
	        		treeProduct.setText(product.getName());
	        		treeDataList.add(treeProduct);    			
	        	}
	        }
	        //审批期限
	    	if(listLimit !=null && listLimit.size()>0){
	    		List<TreeJson> treeAuditList = new ArrayList<TreeJson>();
	        	for (ResBMSProductAuditLimitVO limit : listLimit) {
	        		TreeJson treeLimit = new TreeJson();
	        		treeLimit.setId(String.valueOf(limit.getAuditLimitId()+"_"+limit.getChannelId()));
	        		treeLimit.setDeep("4");
	        		treeLimit.setPid(String.valueOf(limit.getProductId()+"_"+limit.getChannelId()));
	        		treeLimit.setText(String.valueOf(limit.getAuditLimit())+"期");
	        		treeAuditList.add(treeLimit);
	        	}
	    		 //判断orgId是否为空,不为空，根据门店ID获取数据,到门店渠道产品表中获取数据
	            if(reqBMSOrgLimitChannelVO.getOrgId() !=null){
	            	//到门店渠道产品期限表中获取数据
	            	reqLimitChannel.setOrgId(reqBMSOrgLimitChannelVO.getOrgId());
	            	List<ResBMSOrgLimitChannelVO> orgLimitList = outProFacade.listOrgLimitChannelBy(reqLimitChannel);
	            	if(orgLimitList !=null && orgLimitList.size()>0){
	            		for (ResBMSOrgLimitChannelVO orglimit : orgLimitList) {
	            			for(TreeJson limitTemp : treeAuditList){
	            				String idStr[] = limitTemp.getId().split("_");
	            				if(idStr[0].equals(String.valueOf(orglimit.getAuditLimitId())) 
	            						&&idStr[1].equals(String.valueOf(orglimit.getChannelId()))
	            						&& orglimit.getIsCanPreferential()!=null
	            						&& orglimit.getIsCanPreferential()==0){
	            				limitTemp.setChecked("true");
	            				}
	                		}
	            		}
	            	}
	            }
	            treeDataList.addAll(treeAuditList);
	    	}
    	} else {
    		return null;
    	}
        List<TreeJson> newTreeDataList =TreeUtil.formatBusinessOrgTree(treeDataList);
        return newTreeDataList;
    }
	@Override
	public List<ResBMSProductVO> findProRateByChannelId(ReqBMSProductVO reqBMSProductVO) {
		return outProFacade.findProRateByChannelId(reqBMSProductVO);
	}
}
