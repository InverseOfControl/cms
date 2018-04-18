package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.master.IBMSTMReasonExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;


@Service
public class BMSTMReasonExecuter implements IBMSTMReasonExecuter{
	
	@Autowired
	private IBMSTMReasonService iBMSTMReasonService;

	@Override
	public ResListVO<ReqBMSTMReasonVO> oneLevel(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		ResListVO<ReqBMSTMReasonVO> response = new ResListVO<ReqBMSTMReasonVO>();
		
		if(StringUtils.isEmpty(reqBMSTMReasonVO.getOperationModule())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operationModule"});
		}
		if(StringUtils.isEmpty(reqBMSTMReasonVO.getOperationType())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operationType"});
		}
//		if(StringUtils.isEmpty(reqBMSTMReasonVO.getType())){
//			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"type"});
//		}
		
		if(StringUtils.isEmpty(reqBMSTMReasonVO.getType())){
			reqBMSTMReasonVO.setType("1");
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("operationModule", reqBMSTMReasonVO.getOperationModule());
		paramMap.put("operationType", reqBMSTMReasonVO.getOperationType());
		paramMap.put("type", reqBMSTMReasonVO.getType());
		paramMap.put("reasonType", "1");
		List<BMSTMReasonEntity> listpara = iBMSTMReasonService.listBy(paramMap);
		List<ReqBMSTMReasonVO> resList = null;
		if(listpara != null && listpara.size() > 0){
			resList = new ArrayList<ReqBMSTMReasonVO>();
			for (BMSTMReasonEntity TMReasonEntity : listpara) {
				ReqBMSTMReasonVO vo = new ReqBMSTMReasonVO();
				BeanUtils.copyProperties(TMReasonEntity, vo);
				if(TMReasonEntity.getConditionType()!=null && TMReasonEntity.getConditionType().indexOf("isBlackList") != -1){
					vo.setIsBlackList(1);
				}
				resList.add(vo);
			}
			response.setParamList(resList);
		}
		return response;
	}

	@Override
	public ResListVO<ReqBMSTMReasonVO> twoLevel(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		ResListVO<ReqBMSTMReasonVO> response = new ResListVO<ReqBMSTMReasonVO>();
		// 参数校验  
		if(reqBMSTMReasonVO.getId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"id"});
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId",reqBMSTMReasonVO.getId());
		paramMap.put("reasonType", "1");
		List<BMSTMReasonEntity> listpara = iBMSTMReasonService.listBy(paramMap);
		List<ReqBMSTMReasonVO> resList = null;
		if(listpara != null && listpara.size() > 0){
			resList = new ArrayList<ReqBMSTMReasonVO>();
			for (BMSTMReasonEntity TMReasonEntity : listpara) {
				ReqBMSTMReasonVO vo = new ReqBMSTMReasonVO();
				BeanUtils.copyProperties(TMReasonEntity, vo);
				if(TMReasonEntity.getConditionType()!=null && TMReasonEntity.getConditionType().indexOf("isBlackList") != -1){
					vo.setIsBlackList(1);
				}
				resList.add(vo);
			}
			response.setParamList(resList);
		}
		return response;
	}

	@Override
	public ResListVO<ReqBMSTMReasonVO> twoLevelparents(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		ResListVO<ReqBMSTMReasonVO> response = new ResListVO<ReqBMSTMReasonVO>();
		// 参数校验  
		if(reqBMSTMReasonVO.getParentIds() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"parentids"});
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentIds",reqBMSTMReasonVO.getParentIds());
		paramMap.put("reasonType", "1");
		List<BMSTMReasonEntity> listpara = iBMSTMReasonService.twoLevelparents(paramMap);
		List<ReqBMSTMReasonVO> resList = null;
		if(listpara != null && listpara.size() > 0){
			resList = new ArrayList<ReqBMSTMReasonVO>();
			for (BMSTMReasonEntity TMReasonEntity : listpara) {
				ReqBMSTMReasonVO vo = new ReqBMSTMReasonVO();
				BeanUtils.copyProperties(TMReasonEntity, vo);
				if(TMReasonEntity.getConditionType()!=null && TMReasonEntity.getConditionType().indexOf("isBlackList") != -1){
					vo.setIsBlackList(1);
				}
				resList.add(vo);
			}
			response.setParamList(resList);
		}
		return response;
	}

	@Override
	public ResListVO<ReqBMSTMReasonVO> findReasonByOperType(ReqBMSTMReasonVO reqBMSTMReasonVO) {
		ResListVO<ReqBMSTMReasonVO> response = new ResListVO<ReqBMSTMReasonVO>();
		Map<String, Object> map=new HashMap<String,Object>();
		if(reqBMSTMReasonVO.getOperationModule() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operationModule"});
		}
		map.put("operationModule", reqBMSTMReasonVO.getOperationModule());
		if(reqBMSTMReasonVO.getOperationType()==null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operationType"});
		}
		map.put("operationType", reqBMSTMReasonVO.getOperationType());
		List<BMSTMReasonEntity> reasonList=iBMSTMReasonService.findFirstReasonByOperType(map);
		List<ReqBMSTMReasonVO> resList=new ArrayList<ReqBMSTMReasonVO>();
		if(reasonList.size()>0){
			for (BMSTMReasonEntity TMReasonEntity : reasonList) {
				ReqBMSTMReasonVO reqVo = new ReqBMSTMReasonVO();
				BeanUtils.copyProperties(TMReasonEntity, reqVo);
				map.put("id", TMReasonEntity.getId());
				List<BMSTMReasonEntity>secondList=iBMSTMReasonService.findSecondReasonByOperType(map);
				if(secondList.size()>0){
					List<ReqBMSTMReasonVO> reList=new ArrayList<ReqBMSTMReasonVO>();
					for(BMSTMReasonEntity tmReason:secondList){
						ReqBMSTMReasonVO vo = new ReqBMSTMReasonVO();
						BeanUtils.copyProperties(tmReason, vo);
						reList.add(vo); 
					}
					reqVo.setChildren(reList);
				}
				resList.add(reqVo);
			}
			response.setParamList(resList);
		}
		return response;
	}

}
