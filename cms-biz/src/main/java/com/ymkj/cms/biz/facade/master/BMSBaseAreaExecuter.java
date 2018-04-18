package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSBaseAreaExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaTreeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.cache.redis.ListTranscoder;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.common.util.RedisUtil;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSBaseAreaService;


/**
 * 服务提供者demo:
 * 1. 请求信息验证
 * 2. 调用业务层实现业务操作
 * 3. 封装 响应response
 * 4. 对于必须捕获的异常采用 参考 listPage 的用法
 * @author user
 *		
 */
public class BMSBaseAreaExecuter implements IBMSBaseAreaExecuter {
	
	@Autowired
	private IBMSBaseAreaService bmsBaseAreaService;
	
	@Autowired
	private RedisUtil redisUtil;

	
	private Map<String,String> baseArea = new HashMap<String,String>();

	@Override
	public Response<ResBMSBaseAreaVO> findById(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		Response<ResBMSBaseAreaVO> response = new Response<ResBMSBaseAreaVO>();
		if(reqBMSBaseAreaVO.getAreaId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"areaId"});
		}
		BMSBaseArea product = bmsBaseAreaService.getById(reqBMSBaseAreaVO.getAreaId());
		
		// 构造相应参数
		if(product != null){
			ResBMSBaseAreaVO resDemoVO = new ResBMSBaseAreaVO();
			BeanUtils.copyProperties(product, resDemoVO);
			response.setData(resDemoVO);
		}				
		
		return response;
	}

	@Override
	public ResListVO<ResBMSBaseAreaVO> findByName(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		ResListVO<ResBMSBaseAreaVO> response = new ResListVO<ResBMSBaseAreaVO>();
		// 参数校验 第二种方式 
		if(StringUtils.isEmpty(reqBMSBaseAreaVO.getName())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"name"});
		}
		if(StringUtils.isEmpty(reqBMSBaseAreaVO.getDeep())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"deep"});
		}
		try {
			// 构造请求参数
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSBaseAreaVO);
			List<BMSBaseArea> listProduct = bmsBaseAreaService.listBy(paramMap);
			if (listProduct != null && listProduct.size() > 0) {
				List<ResBMSBaseAreaVO> records = new ArrayList<ResBMSBaseAreaVO>();
				for (BMSBaseArea product : listProduct) {
					ResBMSBaseAreaVO vo = new ResBMSBaseAreaVO();
					BeanUtils.copyProperties(product, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResListVO<ResBMSBaseAreaVO> listBy(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		ResListVO<ResBMSBaseAreaVO> response = new ResListVO<ResBMSBaseAreaVO>();
		//根据父ID和深度查询数据
		if(StringUtils.isEmpty(reqBMSBaseAreaVO.getParentId())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"parentId"});
		}
		if(StringUtils.isEmpty(reqBMSBaseAreaVO.getDeep())){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"deep"});
		}
		try {
			// 从缓存中获取值
			List<BMSBaseArea> listArea = new ArrayList<BMSBaseArea>();
			ListTranscoder<BMSBaseArea> listTranscoder = new ListTranscoder<BMSBaseArea>();
			//KEY唯一  父ID_+深度_+BMS_BASE_AREA
			String areaKey ="BMS_BASE_AREA_"+reqBMSBaseAreaVO.getParentId()+"_"+reqBMSBaseAreaVO.getDeep();
			if(redisUtil.exists(areaKey) && baseArea.get(areaKey) != null){
				listArea = (List<BMSBaseArea>) redisUtil.get(areaKey);
			} else {
				// 构造请求参数
				Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSBaseAreaVO);
				listArea = bmsBaseAreaService.listBy(paramMap);
				// 将区域信息放入缓存
				redisUtil.set(areaKey, listArea ,3600L);
				baseArea.put(areaKey, areaKey);
			}
			List<ResBMSBaseAreaVO> records = new ArrayList<ResBMSBaseAreaVO>();
			if (listArea != null && listArea.size() > 0) {
				for (BMSBaseArea baseArea : listArea) {
					ResBMSBaseAreaVO vo = new ResBMSBaseAreaVO();
					BeanUtils.copyProperties(baseArea, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}else{
				//所在区】，如果只有市辖区，导致无枚举值时，自动填充选择的【所在市】，且不可修改 ，code为7位的不可修改
				if(reqBMSBaseAreaVO.getDeep() == 3L ){
					Map isNullMap =new HashMap();
					isNullMap.put("areaId", reqBMSBaseAreaVO.getParentId());
					BMSBaseArea	area = bmsBaseAreaService.getBy(isNullMap);
					ResBMSBaseAreaVO vo = new ResBMSBaseAreaVO();
					BeanUtils.copyProperties(area, vo);
					vo.setParentId(vo.getAreaId());
					vo.setCode(vo.getCode()+"1");//将code+1 变为7位
					vo.setDeep(reqBMSBaseAreaVO.getDeep());
					records.add(vo);
					response.setParamList(records);
				} 
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}
	
	@Override
	public ResListVO<ResBMSBaseAreaTreeVO> listByTree(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		ResListVO<ResBMSBaseAreaTreeVO> response = new ResListVO<ResBMSBaseAreaTreeVO>();
		
		try {
			// 构造请求参数
			List<BMSBaseArea> listArea = bmsBaseAreaService.listBy(new HashMap<String, Object>());
			if(listArea != null && listArea.size() > 0){
				List<ResBMSBaseAreaTreeVO> listrsTree = new ArrayList<ResBMSBaseAreaTreeVO>();
				for (int i = 0; i < listArea.size(); i++) {
					ResBMSBaseAreaTreeVO vo = new ResBMSBaseAreaTreeVO();
					BeanUtils.copyProperties(listArea.get(i), vo);
					listrsTree.add(vo);
				}
				// 创建根节点
				ResBMSBaseAreaTreeVO root = new ResBMSBaseAreaTreeVO();
			    root.setName("菜单根目录");
			    // 组装Map数据
			    Map<Long, ResBMSBaseAreaTreeVO> dataMap = new HashMap<Long, ResBMSBaseAreaTreeVO>();
			    for (ResBMSBaseAreaTreeVO menu : listrsTree) {
			    	dataMap.put(menu.getAreaId(), menu);
			    }
			 
			    // 组装树形结构
			    Set<Entry<Long, ResBMSBaseAreaTreeVO>> entrySet = dataMap.entrySet();
			    for (Entry<Long, ResBMSBaseAreaTreeVO> entry : entrySet) {
			    	ResBMSBaseAreaTreeVO menu = entry.getValue();
			    	if (null == menu.getParentId() || 0 == menu.getParentId()) {
			    		root.getChildren().add(menu);
			    	} else {
			    		dataMap.get(menu.getParentId()).getChildren().add(menu);
			    	}
			    }
			    // 组装树形结构
			    for (Entry<Long, ResBMSBaseAreaTreeVO> entry : entrySet) {
			    	ResBMSBaseAreaTreeVO menu = entry.getValue();
			    	if ( 2L == menu.getDeep()&&dataMap.get(menu.getAreaId()).getChildren().size()==0) {
			    		BmsLogger.info("not exist childrenArea-"+menu.getName() +"|"+menu.getAreaId());
			    		ResBMSBaseAreaTreeVO gerArea =new ResBMSBaseAreaTreeVO();
			    		gerArea.setAreaId(menu.getAreaId());
			    		gerArea.setCreatorId(menu.getCreatorId());
			    		gerArea.setCreator(menu.getCreator());
			    		gerArea.setModifiedId(menu.getModifiedId());
			    		gerArea.setCreatorDate(menu.getCreatorDate());
			    		gerArea.setModified(menu.getModified());;
			    		gerArea.setName(menu.getName());
			    		gerArea.setCode(menu.getCode()+"1");//将code+1 变为7位
			    		gerArea.setDeep(3L);
			    		gerArea.setIsDeleted(menu.getIsDeleted());
			    		gerArea.setVersion(menu.getVersion());
			    		gerArea.setParentId(menu.getAreaId());
			    		dataMap.get(menu.getAreaId()).getChildren().add(gerArea);
			    	} 
			    }
			    root.sortChildren();
			    response.setParamList(root.getChildren());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message:"+e.getMessage());
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSBaseAreaVO> deletelAllBaseArea(ReqBMSBaseAreaVO vo) {
		Response<ResBMSBaseAreaVO> response = new Response<ResBMSBaseAreaVO>();
		BMSBaseArea baseArea = new BMSBaseArea();
		BeanUtils.copyProperties(vo,baseArea);
		bmsBaseAreaService.deletelAll(baseArea);
		   response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSBaseAreaVO> addBaseArea(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		Response<ResBMSBaseAreaVO> response = new Response<ResBMSBaseAreaVO>();
		ResBMSBaseAreaVO res=new ResBMSBaseAreaVO();
		BMSBaseArea baseArea = new BMSBaseArea();
		BeanUtils.copyProperties(reqBMSBaseAreaVO,baseArea);
		long count=this.bmsBaseAreaService.insert(baseArea);
		System.out.println("baseAreaId:"+baseArea.getAreaId());
		res.setAreaId(baseArea.getAreaId());
		response.setData(res);
		System.out.println("response.getData().getAreaId():"+response.getData().getAreaId());
	     response.setRepMsg(String.valueOf(count));
		 return response;
	}

	@Override
	public Response<ResBMSBaseAreaVO> deleteById(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		
		Response<ResBMSBaseAreaVO> response = new Response<ResBMSBaseAreaVO>();
		if(reqBMSBaseAreaVO.getAreaId() == null){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"areaId"});
		}
		long count= bmsBaseAreaService.deleteById(reqBMSBaseAreaVO.getAreaId());
		
		// 构造相应参数
		if(count != 0){
			ResBMSBaseAreaVO resDemoVO = new ResBMSBaseAreaVO();
			BeanUtils.copyProperties(count, resDemoVO.getAreaId());
			response.setData(resDemoVO);
		}				
		return response;
	}

	@Override
	public Response<ResBMSBaseAreaVO> getAllBaseAreaByCode(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		Response<ResBMSBaseAreaVO> response = new Response<ResBMSBaseAreaVO>();
		Map<String, Object> paramMap;
		try {
			paramMap = BeanKit.bean2map(reqBMSBaseAreaVO);
			List<BMSBaseArea> area = this.bmsBaseAreaService.listBy(paramMap);
			 if(area != null){
				   ResBMSBaseAreaVO resDemoVO = new ResBMSBaseAreaVO();
					BeanUtils.copyProperties(area, resDemoVO);
			        response.setData(resDemoVO);
				}	
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		// 构造相应参数
	   return response;
	}

}
