package com.ymkj.cms.biz.service.master.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.dao.master.IBMSBankDao;
import com.ymkj.cms.biz.dao.master.IBMSLoanUrgentConfigDao;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSLoanUrgentConfig;
import com.ymkj.cms.biz.service.master.IBMSLoanUrgentConfigServic;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
@Service
public class BMSLoanUrgentConfigServiceImpl extends BaseServiceImpl<BMSLoanUrgentConfig> implements IBMSLoanUrgentConfigServic{
	@Autowired
	private IBMSLoanUrgentConfigDao iBMSLoanUrgentConfigDao;
	
	@Autowired
	private IBMSSysLogService sysLogService;
	@Override
	protected BaseDao<BMSLoanUrgentConfig> getDao() {
		return iBMSLoanUrgentConfigDao;
	}
	@Override
	public List<BMSLoanUrgentConfig> selectAllBmsLoanUrgentConfigs(Map<String, Object> param) {
		return iBMSLoanUrgentConfigDao.selectAllBmsLoanUrgentConfigs(param);
	}
	@Override
	public Integer getLongBaseCountById(Map<String, Object> map) {
		return iBMSLoanUrgentConfigDao.getLongBaseCountById(map);
	}
	@Override
	public Response<ResBMSLoanUrgentConfigVO> updateOrg(ReqBMSUrgentLimitListVO reqOrgVO) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("orgId", reqOrgVO.getOrgId());
//		map.put("orgName", reqOrgVO.getOrgName());
//		map.put("limitTime", reqOrgVO.getLimitDate());
//		map.put("urgentCount", reqOrgVO.getUrgentCount());
		BMSLoanUrgentConfig con=new BMSLoanUrgentConfig();
		con.setOrgId(reqOrgVO.getOrgId());
		con.setOrgName(reqOrgVO.getOrgName());
		con.setLimitTime(reqOrgVO.getLimitDate());
		con.setUrgentCount(reqOrgVO.getUrgentCount());
		
		
		List<Long> listOrgids=new ArrayList<Long>();
		listOrgids.add(reqOrgVO.getOrgId());
		Map<String,Object> queryMap=new HashMap<String,Object>();
		queryMap.put("listOrgids", listOrgids);
		queryMap.put("limitDate", reqOrgVO.getLimitDate());
		List<BMSLoanUrgentConfig> list=iBMSLoanUrgentConfigDao.selectAllBmsLoanUrgentConfigs(queryMap);
		Long isSuccess;
		if(list!=null&&list.size()>0){
			con.setModifiedTime(new Date());
			con.setModifier(reqOrgVO.getCreator());
			con.setModifierId(Long.parseLong(reqOrgVO.getCreatorId()));
			isSuccess=iBMSLoanUrgentConfigDao.update(con);
			if(isSuccess==1){
				sysLogService.recordSysLog(reqOrgVO, "申请管理|加急限制管理|update|null", "iBMSLoanUrgentConfigDao.update",
						"更新加急件限制营业部<" + reqOrgVO.getOrgName() + ">");
			}
		}else{
			con.setCreatedTime(new Date());
			con.setCreator(reqOrgVO.getCreator());
			con.setCteatorId(Long.parseLong(reqOrgVO.getCreatorId()));
			isSuccess=iBMSLoanUrgentConfigDao.insert(con);
			if(isSuccess==1){
				sysLogService.recordSysLog(reqOrgVO, "申请管理|加急限制管理|insert|null", "iBMSLoanUrgentConfigDao.insert",
						"新增加急件限制营业部<" + reqOrgVO.getOrgName() + ">");
			}
		}
		Response<ResBMSLoanUrgentConfigVO> response=new Response<ResBMSLoanUrgentConfigVO>();
		response.setRepMsg(String.valueOf(isSuccess));
		return response;
	}
	/**
	 * 开接口，查询营业部是否可以加急
	 */
	@Override
	public ResLoanUrgentVO getLoanUrgentSize(ReqLoanUrgentVO vo) {
		ResLoanUrgentVO resLoanUrgentVO=new ResLoanUrgentVO();
		
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
		String ym=sdf.format(date);
		vo.setCurttenDate(ym);
		
		List<Long> listOrgids=new ArrayList<Long>();
		listOrgids.add(vo.getOwningBranchId());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("listOrgids", listOrgids);
		map.put("limitDate", vo.getCurttenDate());
		List<BMSLoanUrgentConfig> list=selectAllBmsLoanUrgentConfigs(map);
		Integer urgentCount;
		if(null!=list&&list.size()>0){
			urgentCount=list.get(0).getUrgentCount();
			Map<String,Object> ByIdMap=new HashMap<String,Object>();
			ByIdMap.put("id", vo.getOwningBranchId());
			ByIdMap.put("limitDate", vo.getCurttenDate());
			Integer alreadyUrgent=getLongBaseCountById(ByIdMap);
			if(urgentCount-alreadyUrgent>0){
				resLoanUrgentVO.setIsUrgent(true);
				resLoanUrgentVO.setOverUrgentSize(urgentCount-alreadyUrgent);
			}else{
				resLoanUrgentVO.setIsUrgent(false);
				resLoanUrgentVO.setOverUrgentSize(urgentCount-alreadyUrgent);
			}
		}else{
			urgentCount=EnumConstants.LOAN_URGENT_CONFIG_VALUE;
			Map<String,Object> ByIdMap=new HashMap<String,Object>();
			ByIdMap.put("id", vo.getOwningBranchId());
			ByIdMap.put("limitDate", vo.getCurttenDate());
			Integer alreadyUrgent=getLongBaseCountById(ByIdMap);
			if(urgentCount-alreadyUrgent>0){
				resLoanUrgentVO.setIsUrgent(true);
				resLoanUrgentVO.setOverUrgentSize(urgentCount-alreadyUrgent);
			}else{
				resLoanUrgentVO.setIsUrgent(false);
				resLoanUrgentVO.setOverUrgentSize(urgentCount-alreadyUrgent);
			}
		}
		return resLoanUrgentVO;
	}

}
