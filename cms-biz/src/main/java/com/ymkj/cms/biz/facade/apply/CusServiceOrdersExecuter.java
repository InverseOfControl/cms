package com.ymkj.cms.biz.facade.apply;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.apply.ICusServiceOrdersExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersOperationVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ResCusServiceOrdersSearchVO;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.APPWhitePersonnelService;
import com.ymkj.pms.biz.api.service.IEmployeeExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

/**
 * 客服接单管理
 * @author YM10152-CYB
 *
 */
@Service
public class CusServiceOrdersExecuter implements ICusServiceOrdersExecuter{
	
	@Autowired
	private APPWhitePersonnelService appWhitePerSonnelService;
	 
	@Autowired
	private IEmployeeExecuter iEmployeeExecuter;
	
	
	/**
	 * 客服接单员工信息查询
	 * @param serviceOrderSearchVo
	 * @return
	 */
	public Response<List<ResCusServiceOrdersSearchVO>> search(ReqCusServiceOrdersSearchVO serviceOrderSearchVo){
		if(serviceOrderSearchVo == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"serviceOrderSearchVo"});
		}
		if(StringUtils.isBlank(serviceOrderSearchVo.getManageBranchCode())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"manageBranchCode"});
		}
		
		if(serviceOrderSearchVo.getRoleCodes() == null || serviceOrderSearchVo.getRoleCodes().size() == 0){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"roleCodes"});
		}
		
		//查出目前所有禁止接单的列表
		List<APPWhitePersonnelEntity> APPWhitePersonnellist = appWhitePerSonnelService.listBy(new HashMap<String,Object>());
		
		ReqParamVO reqVo = new ReqParamVO();
		reqVo.setSysCode("bms");
		reqVo.setOrgId(Long.parseLong(serviceOrderSearchVo.getManageBranchCode()));
		reqVo.setUsercode(serviceOrderSearchVo.getStaffCode());
		reqVo.setUserName(serviceOrderSearchVo.getUserName());
		reqVo.setRoleCodes(serviceOrderSearchVo.getRoleCodes());
		reqVo.setStatus(0);// 可用
		reqVo.setInActive("t");//在职
		//数据查询失败返回告知前台异常
		Response<List<ResEmployeeVO>> deptAndRole = iEmployeeExecuter.findByDeptAndRole(reqVo);
		
		if(!deptAndRole.getRepCode().equals(Response.SUCCESS_RESPONSE_CODE)){
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL);
		}
		//没有数据返回空
		if(deptAndRole.getData() == null || deptAndRole.getData().size() == 0){
			return new Response<List<ResCusServiceOrdersSearchVO>>();
		}
		
		List<ResCusServiceOrdersSearchVO> resCusServiceOrdersSearchVO = new ArrayList<ResCusServiceOrdersSearchVO>();
		
		for (int i = 0; i < deptAndRole.getData().size(); i++) {
			ResCusServiceOrdersSearchVO resVo = new ResCusServiceOrdersSearchVO();
			resVo.setUserCode(deptAndRole.getData().get(i).getUsercode());
			resVo.setUserName(deptAndRole.getData().get(i).getName());
			resVo.setOrgName(deptAndRole.getData().get(i).getEmployeeType());
			resVo.setIfAccept("Y");
			resCusServiceOrdersSearchVO.add(resVo);
		}
		//封装数据
		if(resCusServiceOrdersSearchVO != null && resCusServiceOrdersSearchVO.size() > 0){
			if(APPWhitePersonnellist != null){
				for (int i = 0; i < resCusServiceOrdersSearchVO.size(); i++) {
					for (int j = 0; j < APPWhitePersonnellist.size(); j++) {
						//判断    查询出的数据和配置的数据匹配  如果相同说明该员工不允许接单
						if(resCusServiceOrdersSearchVO.get(i).getUserCode().equals(APPWhitePersonnellist.get(j).getUserCode())){
							resCusServiceOrdersSearchVO.get(i).setIfAccept("N");
							break;
						}
					}
				}
			}
		}
		
		
		List<ResCusServiceOrdersSearchVO> resl = new ArrayList<ResCusServiceOrdersSearchVO>();
		if(serviceOrderSearchVo != null && serviceOrderSearchVo.getIfAccept() == null){
			resl = resCusServiceOrdersSearchVO;
		}else{
			for (int i = 0; i < resCusServiceOrdersSearchVO.size(); i++) {
				if(serviceOrderSearchVo.getIfAccept().equals("Y")){
					//只要允许接单的
					if(resCusServiceOrdersSearchVO.get(i).getIfAccept().equals("Y")){
						resl.add(resCusServiceOrdersSearchVO.get(i));
					}
				}else{
					//只要不允许接单的
					if(resCusServiceOrdersSearchVO.get(i).getIfAccept().equals("N")){
						resl.add(resCusServiceOrdersSearchVO.get(i));
					}
				}
			}
		}
		
		Response<List<ResCusServiceOrdersSearchVO>> resPonse = new Response<List<ResCusServiceOrdersSearchVO>>();
		resPonse.setData(resl);
		return resPonse;
	}
	
	/**
	 * 开启，关闭，接单管理
	 * 
	 */
	public Response<Object> enableOrClose(ReqCusServiceOrdersOperationVo reqCusServiceOrdersOperationVo) {
		if(reqCusServiceOrdersOperationVo.getUserIdList() == null || reqCusServiceOrdersOperationVo.getUserIdList().size() == 0){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"userIdList"});
		}
		
		if(StringUtils.isBlank(reqCusServiceOrdersOperationVo.getIfAccept())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"ifAccept"});
		}
		
		if(!reqCusServiceOrdersOperationVo.getIfAccept().equals("Y") && !reqCusServiceOrdersOperationVo.getIfAccept().equals("N")){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[]{"ifAccept | Y:是 N:否"});
		}
		
		List<APPWhitePersonnelEntity> appWhitePersonnelEntityList = new ArrayList<APPWhitePersonnelEntity>();
		for (int i = 0; i < reqCusServiceOrdersOperationVo.getUserIdList().size(); i++) {
			APPWhitePersonnelEntity appWhitePersonnelEntity = new APPWhitePersonnelEntity();
			appWhitePersonnelEntity.setUserCode(reqCusServiceOrdersOperationVo.getUserIdList().get(i));
			appWhitePersonnelEntity.setValidity(0);
			appWhitePersonnelEntity.setWhiteType(1);
			appWhitePerSonnelService.deleteVo(appWhitePersonnelEntity);
			appWhitePersonnelEntity.setCreator(reqCusServiceOrdersOperationVo.getServiceCode());
			appWhitePersonnelEntity.setCreatedTime(new Date());
			appWhitePersonnelEntityList.add(appWhitePersonnelEntity);
		}
		if(reqCusServiceOrdersOperationVo.getIfAccept().equals("N")){
			appWhitePerSonnelService.saveList(appWhitePersonnelEntityList);
		}
		return new Response<Object>();
	}
	
		
}
