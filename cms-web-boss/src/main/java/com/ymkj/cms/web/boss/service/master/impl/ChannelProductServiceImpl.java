package com.ymkj.cms.web.boss.service.master.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
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
import com.ymkj.cms.web.boss.service.master.IChannelProductService;
@Service
public class ChannelProductServiceImpl implements IChannelProductService {
	@Autowired
	private ChannelProductFacade chaProFacade;
	
	@Autowired
	private ChannelFacade channelFacade;
	@Override
	public List<ResBMSProductVO> findAllProduct(ReqBMSProductVO reqBMSProductVO) {
		return chaProFacade.findAllProduct(reqBMSProductVO);
	}
	@Override
	public PageResult<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return chaProFacade.listPage(reqBMSOrgLimitChannelVO);
	}
	@Override
	public Boolean updateChannelLimit(
			ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		return chaProFacade.updateChannelLimit(reqBMSOrgLimitChannelVO);
	}
	@Override
	public List<TreeJson> findChannelProTree(ReqBMSProductVO reqBMSProductVO) {
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		//设置根目录
        TreeJson rootData = new TreeJson();
        rootData.setId("");
        rootData.setText("产品类型");
        rootData.setDeep("1");
        treeDataList.add(rootData);
		//产品
        List<ResBMSProductVO>  resultList = this.findAllProduct(reqBMSProductVO);
        List<ResBMSProductAuditLimitVO> listLimit = new ArrayList<ResBMSProductAuditLimitVO>();
        for (ResBMSProductVO product : resultList) {
        	TreeJson treeData = new TreeJson();
            ReqBMSProductAuditLimitVO reqLimit = new  ReqBMSProductAuditLimitVO();
            reqLimit.setSysCode(SystemCode.SysCode);
            reqLimit.setProductId(product.getProductId());
            //可用的审批期限0.可用，1，禁用
            reqLimit.setIsDisabled(0L);
            List<ResBMSProductAuditLimitVO> listTemp = chaProFacade.findLimitByProductId(reqLimit);
            //将没有产品审批期限的产品移除
            if(listTemp != null && listTemp.size()>0){
                treeData.setId(String.valueOf(product.getProductId()));
                treeData.setDeep("2");
                treeData.setPid("");
                treeData.setText(product.getName());
                treeDataList.add(treeData);
            	listLimit.addAll(listTemp);
            }
           
        }
        List<TreeJson> newTreeDataList = this.findLimitChanel(reqBMSProductVO,treeDataList,listLimit);
        return newTreeDataList;
    }
	
	/**
	 * 获取产品期限以及最终产品树
	 * @param treeDataList
	 * @param reqBMSProductVO
	 * @return
	 */
	@Override
	public  List<TreeJson> findLimitChanel(ReqBMSProductVO reqBMSProductVO,List<TreeJson> treeDataList, List<ResBMSProductAuditLimitVO> listLimit){
		List<TreeJson> treeAuditList = new ArrayList<TreeJson>();
		//审批期限
		for (ResBMSProductAuditLimitVO limit : listLimit) {
			TreeJson treeData = new TreeJson();
			treeData.setId(String.valueOf(limit.getAuditLimitId()));
			treeData.setDeep("3");
			treeData.setPid(String.valueOf(limit.getProductId()));
			treeData.setText(String.valueOf(limit.getAuditLimit())+"期");
			treeAuditList.add(treeData);
		}
		if(reqBMSProductVO.getChannelId() != null){
			//根据渠道ID获取审批期限
			ReqBMSOrgLimitChannelVO limitChanel = new  ReqBMSOrgLimitChannelVO();
			limitChanel.setSysCode(SystemCode.SysCode);
			limitChanel.setChannelId(reqBMSProductVO.getChannelId());
			List<ResBMSOrgLimitChannelVO> listLimitChanel = chaProFacade.listOrgLimitChannelBy(limitChanel);
			if(listLimitChanel !=null && listLimitChanel.size()>0){
				//审批期限
				for(ResBMSOrgLimitChannelVO limitChanelTemp :listLimitChanel){
					for (TreeJson limit : treeAuditList) {
						if(limit.getId().equals(String.valueOf(limitChanelTemp.getAuditLimitId()))){
							limit.setChecked("true");
						}
					}
				}
			}
		}
		//将审批期限树数据加入
		treeDataList.addAll(treeAuditList);
        //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
        List<TreeJson> newTreeDataList = TreeUtil.formatChannelProductTree(treeDataList);
		return newTreeDataList;
	}
	
	
	
	@Override
	public List<TreeJson> findChannelTree(ReqBMSChannelVO reqBMSChannelVO) {
		List<TreeJson> treeDataList = new ArrayList<TreeJson>();
		//设置根目录
        TreeJson rootData = new TreeJson();
        rootData.setId("");
        rootData.setText("业务渠道");
        rootData.setDeep("1");
        treeDataList.add(rootData);
		//渠道
        List<ResBMSChannelVO>  resultList = channelFacade.getAllChannel(reqBMSChannelVO);
        for (ResBMSChannelVO channel : resultList) {
        	//禁用渠道，显示
        	if(channel.getIsDisabled()==null || channel.getIsDisabled()==1){
        		continue;
        	}
            TreeJson treeData = new TreeJson();
            treeData.setId(String.valueOf(channel.getId()));
            treeData.setDeep("2");
            treeData.setPid(String.valueOf(""));
            treeData.setText(channel.getName());
            treeDataList.add(treeData);
        }
        List<TreeJson> newTreeDataList = TreeUtil.formatChannelProductTree(treeDataList);
		return newTreeDataList;
	}
	@Override
	public boolean saveChannelProduct(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		//将传入的产品期限id字符串进行处理,将最后一个逗号去掉
		String limitIds = reqBMSOrgLimitChannelVO.getChannelLimitIds();
		//空判断
		if(limitIds == null || limitIds.length() == 0){
			limitIds = "";
		} else {
			limitIds = limitIds.substring(0, limitIds.length()-1);
		}
		
		
		reqBMSOrgLimitChannelVO.setChannelLimitIds(limitIds);
		return chaProFacade.saveOrgProductLimit(reqBMSOrgLimitChannelVO);
	}
	@Override
	public Map<String, Object> channelLimitDisableCheck(ReqBMSLimitChannelVO reqBMSLimitChannelVO) {
		return chaProFacade.channelLimitDisableCheck(reqBMSLimitChannelVO);
	}

}
