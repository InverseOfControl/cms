package com.ymkj.cms.biz.facade.master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSReasonReLinksExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSReasonManageService;
import com.ymkj.cms.biz.service.master.IBMSReasonReLinksService;

@Service
public class BMSReasonReLinksExecuter implements IBMSReasonReLinksExecuter {
	@Autowired
	private IBMSReasonReLinksService iBMSReasonReLinksService;
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
			PageBean<BMSReason> pageBean = iBMSReasonReLinksService.listPage(pageParam, paramMap);
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
	public Response<ResBMSReasonVO> findBMSReLinksById( ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response=new Response<ResBMSReasonVO>();
		if(reqBMSReasonVO.getCode()==null){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] {"code"});
		}
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("code", reqBMSReasonVO.getCode());
			BMSReason bmsReason=iBMSReasonReLinksService.findBMSReLinksById(map);
			if (bmsReason != null) {
				ResBMSReasonVO resVO = new ResBMSReasonVO();
				BeanUtils.copyProperties(bmsReason, resVO);
				if(bmsReason.getParentId()!=0){
					BMSReason eV=iBMSReasonManageService.findReasonByPId(bmsReason.getParentId());	
					bmsReason.setFirstReason(eV.getReason());
				}
				map.put("reasonTexplain", "1");
				ResBMSReasonVO resDemoVO = new ResBMSReasonVO();
				StringBuffer sb=new StringBuffer();
				List<BMSReason>  reasonList=iBMSReasonReLinksService.findByRelationCode(map);
				if(reasonList.size()>0){
					for(BMSReason reason:reasonList){
						sb.append(reason.getOperationModule()+",");
					}
					bmsReason.setReasonTexplain(sb.deleteCharAt(sb.length() - 1).toString());	
				}else{
					bmsReason.setReasonTexplain(null);	
				}
				BeanUtils.copyProperties(bmsReason, resDemoVO);
				response.setData(resDemoVO);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	@Override
	public Response<ResBMSReasonVO> editReasonReLinks(ReqBMSReasonVO reqBMSReasonVO) {
		Response<ResBMSReasonVO> response = new Response<ResBMSReasonVO>();
		String[]operationModule=reqBMSReasonVO.getOperationModules();
		String[] moudleName=reqBMSReasonVO.getModuleName().split(",");

		Integer count=1;

		Set<String> delmoduleIds = compare( operationModule,moudleName,"del");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code", reqBMSReasonVO.getCode());
		//BMSReason bmsReason=iBMSReasonReLinksService.findBMSReLinksById(map);
		BMSReason bmsReason=new BMSReason();
		BeanUtils.copyProperties(reqBMSReasonVO,bmsReason);
		String days=reqBMSReasonVO.getCanRequestDays().equals("")|| reqBMSReasonVO.getCanRequestDays().equals(null)?"0":reqBMSReasonVO.getCanRequestDays();
		bmsReason.setCanRequestDays(Integer.valueOf(days));
		//判断，如何要删除的话，状态改为1
		//如果要删除一级原因所有的一级关联原因都将被删除
		//如果是一级原因直接删除
		if(delmoduleIds!=null && delmoduleIds.size()>0){
			for(String oModule:delmoduleIds){
				if(reqBMSReasonVO.getType().equals("1")){
					Map<String, Object> paramMap =new HashMap<String, Object>();
					paramMap.put("code", reqBMSReasonVO.getCode());
					paramMap.put("operationModule", oModule);
					List<BMSReason> reason=iBMSReasonReLinksService.findByRelationCode(paramMap);	
					Map<String, Object> paramMaps =new HashMap<String, Object>();
					paramMaps.put("operationModule", reason.get(0).getOperationModule());
					paramMaps.put("parentId",reason.get(0).getId());
					List<BMSReason> reasonss=iBMSReasonReLinksService.findByRelationCode(paramMaps);	
					if(reasonss.size()>0){
						for(BMSReason bms:reasonss){
							count=delBMSReasonByVal(bms.getOperationModule(),bms, 1);
						}

					}
					count=delBMSReasonByVal(oModule,bmsReason,1);
				}else{
					count=delBMSReasonByVal(oModule,bmsReason,1);
				}
			}
		}
		Set<String> addmoduleCodeIds = compare(moudleName, operationModule,"add");
		//判断要新增的，直接新增数据
		if(addmoduleCodeIds!=null && addmoduleCodeIds.size()>0){
			for(String oModule: addmoduleCodeIds){
				//根据规则，生成相对应的CODE
				if(bmsReason.getType().equals("1")){
					Map<String, Object> paramMap =new HashMap<String, Object>();
					paramMap.put("code", bmsReason.getCode());
					paramMap.put("operationModule", oModule);
					List<BMSReason> reasonList=iBMSReasonReLinksService.findByRelationCode(paramMap);
					if(reasonList.size()>0){
						for(BMSReason bms:reasonList){
							count=delBMSReasonByVal(oModule,bms,0);	
						}	
					}else{
						insert("1",oModule,bmsReason,(long) 0,null);
					}
				}else{
					//子类菜单首先根据父类ID查询Relation_code
					//判断要新增的，根据Relation_code,OPERATION_MODULE来查询原值是否存在
					//原值存在，父类ID就是原值;父类ID不存在直接新增
					BMSReason  bmsReasons=iBMSReasonManageService.findReasonByPId(bmsReason.getParentId());
					Map<String, Object> paramMap =new HashMap<String, Object>();
					paramMap.put("code", bmsReasons.getCode());
					paramMap.put("operationModule", oModule);
					List<BMSReason> reasonList=iBMSReasonReLinksService.findByRelationCode(paramMap);
					if(reasonList.size()==0){
						insert("1",oModule,bmsReasons,(long) 0,null);
						paramMap.put("code", bmsReasons.getCode());
						paramMap.put("operationModule", oModule);
						List<BMSReason> reasonLists=iBMSReasonReLinksService.findByRelationCode(paramMap);	
						insert("2",oModule,bmsReason,(long) reasonLists.get(0).getId(),reasonLists.get(0).getCode());
					}else{
						paramMap.put("code", bmsReasons.getCode());
						paramMap.put("operationModule", oModule);
						List<BMSReason> rseasonList=iBMSReasonReLinksService.findByRelationCode(paramMap);	
						insert("2",oModule,bmsReason,(long) rseasonList.get(0).getId(),rseasonList.get(0).getCode());					

					}	
				}
			}	
		}
		if(delmoduleIds.isEmpty() && addmoduleCodeIds.isEmpty()){
			BMSReason bmsR=new BMSReason();
			BeanUtils.copyProperties(reqBMSReasonVO,bmsR);
			bmsR.setCanRequestDays(Integer.valueOf(days));
			count=updateBMSReasonByVal(null,bmsR,0);		
		}
		updateByIfShow(reqBMSReasonVO);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	/**
	 * 更新是否可见的字段数据
	 * 先从库中查询可以显示的数据,和前端传过来可见的数据比较
	 * 1:不可见,2:可见
	 * @param reqBMSReasonVO
	 * @return
	 */
	public int updateByIfShow(ReqBMSReasonVO reqBMSReasonVO){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code", reqBMSReasonVO.getCode());
		map.put("reasonTexplain", "1");
		String[] oldReasonShow=new String[0];
		int count=0;
		StringBuffer sb=new StringBuffer();
		List<BMSReason>  reasonList=iBMSReasonReLinksService.findByRelationCode(map);
		if(reasonList.size()>0){
			for(BMSReason reason:reasonList){
				sb.append(reason.getOperationModule()+",");
			}
			oldReasonShow=sb.deleteCharAt(sb.length() - 1).toString().split(",");
		}
		String[]newReasonShow=reqBMSReasonVO.getReasonTexplain().split(",");
		Set<String> addmoduleCodeIds = compare(oldReasonShow, newReasonShow,"add");
		//判断要新增的，直接新增数据
		if(addmoduleCodeIds!=null && addmoduleCodeIds.size()>0){
			for(String oModule: addmoduleCodeIds){
				if(!oModule.equals("")){
					BMSReason bReason=new BMSReason();
					bReason.setOperationType(reqBMSReasonVO.getOperationType());
					bReason.setType(reqBMSReasonVO.getType());
					bReason.setOperationModule(oModule);
					bReason.setCode(reqBMSReasonVO.getCode());
					bReason.setReasonTexplain("1");
					bReason.setRemark(reqBMSReasonVO.getRemark());
					//bReason.setCanRequestDays(canRequestDays);
					count=iBMSReasonReLinksService.updateBMSReasonIfShow(bReason);
				}	
			}
		}
		Set<String> delmoduleIds = compare( newReasonShow,oldReasonShow,"del");
		if(delmoduleIds!=null && delmoduleIds.size()>0){	
			for(String oModule: delmoduleIds){
				if(!oModule.equals("")){
					BMSReason bReason=new BMSReason();
					bReason.setOperationType(reqBMSReasonVO.getOperationType());
					bReason.setType(reqBMSReasonVO.getType());
					bReason.setOperationModule(oModule);
					bReason.setCode(reqBMSReasonVO.getCode());
					bReason.setReasonTexplain("2");
					count=iBMSReasonReLinksService.updateBMSReasonIfShow(bReason);	
				}
			}
		}

		return count;
	}

	//删除原因表信息
	public Integer delBMSReasonByVal(String oModule,BMSReason bmsReason,Integer isDeleted){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code", bmsReason.getCode());
		map.put("operationModule", oModule);
		int count=iBMSReasonReLinksService.delReasonCode(map);
		return count;
	}

	//更新原因表信息
	public Integer updateBMSReasonByVal(String oModule,BMSReason bmsReason,Integer isDeleted){
		BMSReason bReason=new BMSReason();
		bReason.setIsDeleted((long) 0);  //修改状态为删除
		bReason.setOperationType(bmsReason.getOperationType()); //类型
		bReason.setType(bmsReason.getType());  //TYPE
		bReason.setOperationModule(oModule);  //环节
		bReason.setCode(bmsReason.getCode());  //CODE
		bReason.setReason(bmsReason.getReason());  //原因
		bReason.setIsDisabled(bmsReason.getIsDisabled()); //是否禁用
		bReason.setConditionType(bmsReason.getConditionType());  //规则
		bReason.setRemark(bmsReason.getRemark()); //备注
		bReason.setReasonTexplain("1");
		bReason.setCanRequestDays(bmsReason.getCanRequestDays()); //限制天数
		int count=iBMSReasonReLinksService.updateBMSReasonByVal(bReason);
		return count;
	}

	//插入原因表信息
	public long insert(String Type,String oModule, BMSReason bmsReason,Long parentId,String newEmpNo){
		BMSReason bReason=new BMSReason();
		if(Type.equals("1")){
			bReason.setCode(bmsReason.getCode());
			bReason.setParentId(parentId);
			bReason.setType("1");  //原因级别
		}
		if(Type.equals("2")){ 	
			bReason.setType("2");  //原因级别
			bReason.setParentId(parentId);
		}
		bReason.setCode(bmsReason.getCode());
		bReason.setOperationModule(oModule);  //操作环节
		bReason.setReason(bmsReason.getReason());    //原因
		bReason.setCanRequestDays(bmsReason.getCanRequestDays());  //禁用天数
		bReason.setIsDisabled(bmsReason.getIsDisabled());  //是否禁用
		bReason.setConditionType(bmsReason.getConditionType()); //操作类型
		bReason.setOperationType(bmsReason.getOperationType()); //规则
		bReason.setRemark(bmsReason.getRemark());
		bReason.setReasonTexplain("2");
		long count=iBMSReasonReLinksService.insert(bReason);	
		return count;
	}

	public Integer updateReason(ReqBMSReasonVO reqBMSReasonVO ){
		String []disPlayRelation=reqBMSReasonVO.getDisPlayRelation().split(",");
		String []operationRelation=reqBMSReasonVO.getOperationRelation().split(",");
		for(String opera:operationRelation){
			BMSReason bmsReason = new BMSReason();
			for(String dis:disPlayRelation){
				if(opera.equals(dis)){
					bmsReason.setReasonTexplain("1");	
				}
			}
			if(reqBMSReasonVO.getCanRequestDays()!=null&&reqBMSReasonVO.getCanRequestDays().length()!=0){
				bmsReason.setCanRequestDays(Integer.parseInt(reqBMSReasonVO.getCanRequestDays()));
			}
			if(!reqBMSReasonVO.getFirstReason().equals("")){
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("code", reqBMSReasonVO.getFirstReason());
				List<BMSReason>  list=iBMSReasonManageService.findBMSReasonByValue(map);
				bmsReason.setCode(reqBMSReasonVO.getCode());
				bmsReason.setParentId(list.get(0).getId());
				bmsReason.setIsBlacklist(0);
			}else{
				bmsReason.setParentId((long) 0);
			}
			bmsReason.setConditionType(reqBMSReasonVO.getConditionType());
			bmsReason.setRemark(reqBMSReasonVO.getRemark());
			bmsReason.setType(reqBMSReasonVO.getType());
			bmsReason.setCode(reqBMSReasonVO.getCode());
			bmsReason.setReason(reqBMSReasonVO.getReason());
			bmsReason.setOperationModule(opera);
			bmsReason.setCreatorDate(new Date());
			bmsReason.setOperationType(reqBMSReasonVO.getOperationType().toString());
			bmsReason.setCreator(reqBMSReasonVO.getCreator());
			bmsReason.setCreatorId(Long.parseLong(reqBMSReasonVO.getCreatorId()));
			bmsReason.setIsDisabled(reqBMSReasonVO.getIsDisabled());
			bmsReason.setIsDeleted((long) 0);
			bmsReason.setIsBlacklist(0);
			iBMSReasonManageService.updateById(bmsReason);
			//long count = iBMSReasonManageService.insert(bmsReason);
		}
		return 1;
	}

	public <T> Set<T> compare(T[] t1, T[] t2,String opFlag) {   
		Set<T> set = new HashSet<T>();      
		if(t1 !=null){
			List<T> list1 = Arrays.asList(t1); 
			for (T t : t2) { 
				if("del".equals(opFlag)){
					if (!list1.contains((t.toString().split("_"))[0])) {    
						set.add(t);    
					}    	
				}else{
					if (!list1.contains(t)) {    
						set.add(t);    
					}    	
				}
			}  
		}else{
			for (T t : t2) {    
				set.add(t);    
			}
		}
		return set;    
	}

}
