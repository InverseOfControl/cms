package com.ymkj.cms.biz.facade.master;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSReasonManageExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSReasonManageService;

@Service
public class BMSReasonManageExecuter implements IBMSReasonManageExecuter {
	@Autowired
	private IBMSReasonManageService iBMSReasonManageService;
	
	
	
	

	@Override
	public PageResponse<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		PageResponse<ResBMSReasonVO> response=new PageResponse<ResBMSReasonVO>();
		// 参数校验
		if (reqReasonVO.getPageNum() == 0 || reqReasonVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqReasonVO.getPageNum(), reqReasonVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqReasonVO);
			// 调用业务逻辑,得到list集合
			PageBean<BMSReason> pageBean = iBMSReasonManageService.listPage(pageParam, paramMap);
			// 构造响应参数
			List<ResBMSReasonVO> records = new ArrayList<ResBMSReasonVO>();
			List<BMSReason> Entitys = pageBean.getRecords();
			for (BMSReason Entity : Entitys) {
				BMSReason bReason=new BMSReason();
				ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
				BeanUtils.copyProperties(Entity, resDemoVO);
				if(Entity.getParentId()!=0){
					bReason=iBMSReasonManageService.findReasonByPId(Entity.getParentId());	
					resDemoVO.setFirstReason(bReason.getReason());
				}
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
	public ResListVO<ResBMSReasonVO> findBMSReasonByValue( ReqBMSTMReasonVO reqReasonVO) {
		ResListVO<ResBMSReasonVO> response=new ResListVO<ResBMSReasonVO>();
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqReasonVO);
			if(reqReasonVO.getReasonTexplain()==null){
				paramMap.put("reasonTexplain", null);
			}
			List<BMSReason>  list=iBMSReasonManageService.findBMSReasonByValue(paramMap);
			if(list.size()>0){
				List<ResBMSReasonVO> resList=new ArrayList<ResBMSReasonVO>();
				for(BMSReason vo:list){
					ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
					BeanUtils.copyProperties(vo, resDemoVO);
					resList.add(resDemoVO);
				}
				response.setParamList(resList);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	
	@Override
	public Response<ResBMSReasonVO> addReasonManage(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		String []operationRelation=reqBMSReasonVO.getOperationRelation().split(",");
		String []disPlayRelation=reqBMSReasonVO.getDisPlayRelation().split(",");
		long count=0;
		for(String opera:operationRelation){
			BMSReason bmsReason = new BMSReason();
			for(String dis:disPlayRelation){
				if(opera.equals(dis)){
					bmsReason.setReasonTexplain("1");	
				}
			}
			if(!reqBMSReasonVO.getFirstReason().equals("")){
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("code", reqBMSReasonVO.getFirstReason());
				map.put("operationModule", opera);
				List<BMSReason>  list=iBMSReasonManageService.findReasonByParame(map);
				if(list.size()>0){  
					bmsReason.setParentId(list.get(0).getId());	
					count=insertTwoReason(reqBMSReasonVO,bmsReason,opera,"2");
				}else{
					Map<String,Object> maps=new HashMap<String,Object>();
					maps.put("code", reqBMSReasonVO.getFirstReason());
					List<BMSReason>  lists=iBMSReasonManageService.findReasonByParame(maps);
					bmsReason.setParentId((long) 0);
					bmsReason.setReason(lists.get(0).getReason());
					bmsReason.setRemark(lists.get(0).getRemark());
					bmsReason.setCode(reqBMSReasonVO.getFirstReason());
					count=insertTwoReason(reqBMSReasonVO,bmsReason,opera,"1");
					if(count>0){
						Map<String,Object>Hashmap=new HashMap<String, Object>();
						Hashmap.put("code", reqBMSReasonVO.getFirstReason());
						Hashmap.put("operationModule", opera);
						List<BMSReason>  Relist=iBMSReasonManageService.findReasonByParame(map);
						if(Relist.size()>0){
							Integer id=iBMSReasonManageService.findMaxId();
							bmsReason.setId((long) (id+1));
							bmsReason.setParentId(Relist.get(0).getId());	
							count=insertTwoReason(reqBMSReasonVO,bmsReason,opera,"2");	
						}
					}
				}
			}else{
				bmsReason.setCode(reqBMSReasonVO.getCode());
				bmsReason.setRemark(reqBMSReasonVO.getRemark());
				bmsReason.setReason(reqBMSReasonVO.getReason());
				count=insertTwoReason(reqBMSReasonVO,bmsReason,opera,"1");
			}
		}
		response.setRepMsg(String.valueOf(count));
		return response;
	}
	
	/**新增二级原因环节时,如果一级原因不包含这些环境,应添加*/
	public long insertTwoReason(ReqBMSReasonVO reqBMSReasonVO,BMSReason bmsReason,String opera,String Type){
		if(Type.equals("2")){
			bmsReason.setCode(reqBMSReasonVO.getCode());
			bmsReason.setRemark(reqBMSReasonVO.getRemark());
			bmsReason.setReason(reqBMSReasonVO.getReason());
		}
		if(Type.equals("1")){

			bmsReason.setParentId((long) 0);
		}
		bmsReason.setType(Type);
		if(reqBMSReasonVO.getCanRequestDays()!=null&&reqBMSReasonVO.getCanRequestDays().length()!=0){
			bmsReason.setCanRequestDays(Integer.parseInt(reqBMSReasonVO.getCanRequestDays()));
		}
		if(("").equals(bmsReason.getReasonTexplain()) ||null== bmsReason.getReasonTexplain()){
			bmsReason.setReasonTexplain("2");	
		}
		bmsReason.setConditionType(reqBMSReasonVO.getConditionType());
		bmsReason.setOperationModule(opera);
		bmsReason.setCreatorDate(new Date());
		bmsReason.setOperationType(reqBMSReasonVO.getOperationType().toString());
		bmsReason.setCreator(reqBMSReasonVO.getCreator());
		bmsReason.setCreatorId(Long.parseLong(reqBMSReasonVO.getCreatorId()));
		bmsReason.setIsDisabled(reqBMSReasonVO.getIsDisabled());
		bmsReason.setIsDeleted((long) 0);
		bmsReason.setIsBlacklist(0);
		long count = iBMSReasonManageService.insert(bmsReason);
		return count;
	}
	
	@Override
	public Response<ResBMSReasonVO> editReasonManage(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		BMSReason bmsReason = new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO, bmsReason);
		if(reqBMSReasonVO.getCanRequestDays()!=null&&reqBMSReasonVO.getCanRequestDays().length()!=0){
			bmsReason.setCanRequestDays(Integer.parseInt(reqBMSReasonVO.getCanRequestDays()));
		}
		bmsReason.setModifiedDate(new Date());
		bmsReason.setModified(reqBMSReasonVO.getModifier());
		bmsReason.setModifiedId(reqBMSReasonVO.getModifierId());
		if(reqBMSReasonVO.getIsDisabled()==1){
			if( reqBMSReasonVO.getType().equals("1")){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("firstReason", reqBMSReasonVO.getFirstReason());
				map.put("type", reqBMSReasonVO.getType());
				map.put("operationType", reqBMSReasonVO.getOperationType());
				List<BMSReason> reasonList=iBMSReasonManageService.listBy(map);
				for(BMSReason reason:reasonList){
					BMSReason reasons=new BMSReason();
					reasons.setIsDisabled(reqBMSReasonVO.getIsDisabled());
					reasons.setOperationType(reason.getOperationType());
					reasons.setId(reason.getId());
					iBMSReasonManageService.updateById(reasons);
				}
			}
		}
		long count = iBMSReasonManageService.update(bmsReason);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public ResListVO<ResBMSReasonVO> findReasonExport(ReqBMSTMReasonVO reqReasonVO) {
		ResListVO<ResBMSReasonVO> response=new ResListVO<ResBMSReasonVO>();
		try {
			Map<String, Object> paramMap = BeanKit.bean2map(reqReasonVO);
			List<BMSReason> reasonList=iBMSReasonManageService.findReasonExport(paramMap);
			if(reasonList.size()>0){
				List<ResBMSReasonVO> resList=new ArrayList<ResBMSReasonVO>();
				for(BMSReason vo:reasonList){
					ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
					BeanUtils.copyProperties(vo, resDemoVO);
					resList.add(resDemoVO);
				}
				response.setParamList(resList);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		return response;
	}

	@Override
	public ResListVO<ResBMSReasonVO>findReasonByParame(ReqBMSReasonVO reqBMSReasonVO) {
		ResListVO<ResBMSReasonVO> responseVO=new ResListVO<>();
		try {
			Map<String,Object> paramMap=BeanKit.bean2map(reqBMSReasonVO);
			List<BMSReason> bmsReason=iBMSReasonManageService.findReasonByParame(paramMap);
			if(bmsReason.size()>0){
				List<ResBMSReasonVO> resList=new ArrayList<ResBMSReasonVO>();
				for(BMSReason vo:bmsReason){
					ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
					BeanUtils.copyProperties(vo, resDemoVO);
					resList.add(resDemoVO);
				}
				responseVO.setParamList(resList);
			}

		}catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

		return responseVO;
	}

	@Override
	public Response<Object> delReasonByCode(ReqBMSReasonVO reqBMSReasonVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", reqBMSReasonVO.getCode());
		//如果是删除一级原因，查询她的二级原因，如果有要全部删除
		List<BMSReason> reasonList=iBMSReasonManageService.findReasonByParame(map);
		if(reasonList.size()>0){
			for(BMSReason son:reasonList){
				BMSReason bmsVo = new BMSReason();	
				bmsVo.setParentId(son.getId());
				bmsVo.setIsDeleted((long) 1);
				iBMSReasonManageService.update(bmsVo);
			}
		}
		Response<Object> response = new Response<Object>();
		BMSReason bmsVo = new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO,bmsVo);
		bmsVo.setIsDeleted((long) 1);
		long count=iBMSReasonManageService.update(bmsVo);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

}
