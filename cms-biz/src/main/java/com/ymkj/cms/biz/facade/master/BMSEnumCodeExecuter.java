package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSEnumCodeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.cache.redis.ListTranscoder;
import com.ymkj.cms.biz.common.util.ProductUtils;
import com.ymkj.cms.biz.common.util.RedisUtil;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSEnumCodeService;

/**
 * 服务提供者demo: 1. 请求信息验证 2. 调用业务层实现业务操作 3. 封装 响应response 4. 对于必须捕获的异常采用 参考
 * listPage 的用法
 * 
 * @author user
 * 
 */
@Service
public class BMSEnumCodeExecuter implements IBMSEnumCodeExecuter {

	@Autowired
	private IBMSEnumCodeService demoService;

	@Autowired
	private RedisUtil redisUtil;
	
	private Map<String,Object> map=new HashMap<String,Object>();

	@SuppressWarnings("unchecked")
	@Override
	public ResListVO<ResBMSEnumCodeVO> listEnumCodeBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		ResListVO<ResBMSEnumCodeVO> response = new ResListVO<ResBMSEnumCodeVO>();
		// 参数校验 枚举类型
		if (StringUtils.isEmpty(reqBMSEnumCodeVO.getCodeType())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "codeType" });
		}

		try {
			// 从缓存中获取值
			List<BMSEnumCode> listEnum = new ArrayList<BMSEnumCode>();
			ListTranscoder<BMSEnumCode> listTranscoder = new ListTranscoder<BMSEnumCode>();
			if (redisUtil.exists(reqBMSEnumCodeVO.getCodeType()) && map.get(reqBMSEnumCodeVO.getCodeType())!=null) {
				listEnum = (List<BMSEnumCode>) redisUtil.get(reqBMSEnumCodeVO.getCodeType());
			} else {
				// 构造请求参数
				Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSEnumCodeVO);
				listEnum = demoService.listBy(paramMap);
				// 将枚举放入缓存
				//redisUtil.set(reqBMSEnumCodeVO.getCodeType(), listEnum);
				//加入redis定时清除
				redisUtil.set(reqBMSEnumCodeVO.getCodeType(), listEnum, 3600L);
				
				map.put(reqBMSEnumCodeVO.getCodeType(), reqBMSEnumCodeVO.getCodeType());
			}
			if (listEnum != null && listEnum.size() > 0) {
				List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
				for (BMSEnumCode enumCode : listEnum) {
					ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
					BeanUtils.copyProperties(enumCode, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSEnumCodeVO> findById(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<ResBMSEnumCodeVO> response = new Response<ResBMSEnumCodeVO>();
		if (reqBMSEnumCodeVO.getCodeId() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "codeId" });
		}
		BMSEnumCode enumCode = demoService.getById(reqBMSEnumCodeVO.getCodeId());

		// 构造相应参数
		if (enumCode != null) {
			ResBMSEnumCodeVO resDemoVO = new ResBMSEnumCodeVO();
			BeanUtils.copyProperties(enumCode, resDemoVO);
			response.setData(resDemoVO);
		}

		return response;
	}

	@Override
	public ResListVO<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		ResListVO<ResBMSEnumCodeVO> response = new ResListVO<ResBMSEnumCodeVO>();
		// 参数校验 枚举类型
		if (StringUtils.isEmpty(reqBMSEnumCodeVO.getProductId())) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[] { "productId" });
		}
		// 构造请求参数
		try {
			Map<String, Object> paramMap = ProductUtils.bean2map(reqBMSEnumCodeVO);
			List<BMSEnumCode> listEnum = demoService.findEnumCodeByCondition(paramMap);
			if (listEnum != null && listEnum.size() > 0) {
				List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
				for (BMSEnumCode enumCode : listEnum) {
					ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
					BeanUtils.copyProperties(enumCode, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public PageResponse<ResBMSEnumCodeVO> listPage(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		PageResponse<ResBMSEnumCodeVO> response = new PageResponse<ResBMSEnumCodeVO>();

		// 参数校验
		if (reqBMSEnumCodeVO.getPageNum() == 0 || reqBMSEnumCodeVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqBMSEnumCodeVO.getPageNum(), reqBMSEnumCodeVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqBMSEnumCodeVO);

			// 调用业务逻辑
			PageBean<BMSEnumCode> pageBean = demoService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
			List<BMSEnumCode> demoEntitys = pageBean.getRecords();
			for (BMSEnumCode demoEntity : demoEntitys) {
				ResBMSEnumCodeVO resDemoVO = new ResBMSEnumCodeVO();
				BeanUtils.copyProperties(demoEntity, resDemoVO);
				records.add(resDemoVO);
			}
			// 忽略 复制的字段
			BeanUtils.copyProperties(pageBean, response, "records");
			response.setRecords(records);

		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
		return response;
	}

	@Override
	public Response<ResBMSEnumCodeVO> addEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<ResBMSEnumCodeVO> response = new Response<ResBMSEnumCodeVO>();
		BMSEnumCode enumCode = new BMSEnumCode();
		BeanUtils.copyProperties(reqBMSEnumCodeVO, enumCode);
		enumCode.setIsDeleted((long) 0);
		long count = this.demoService.insert(enumCode);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSEnumCodeVO> updateEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<ResBMSEnumCodeVO> response = new Response<ResBMSEnumCodeVO>();
		BMSEnumCode enumCode = new BMSEnumCode();
		BeanUtils.copyProperties(reqBMSEnumCodeVO, enumCode);
		this.demoService.update(enumCode);
		response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSEnumCodeVO> getAllEnumCode(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<ResBMSEnumCodeVO> response = new Response<ResBMSEnumCodeVO>();
		BMSEnumCode enumCode = this.demoService.getById(reqBMSEnumCodeVO.getCodeId());
		// 构造相应参数
		if (enumCode != null) {
			ResBMSEnumCodeVO resDemoVO = new ResBMSEnumCodeVO();
			BeanUtils.copyProperties(enumCode, resDemoVO);
			response.setData(resDemoVO);
		}
		return response;
	}

	@Override
	public Response<ResBMSEnumCodeVO> findByVo(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<ResBMSEnumCodeVO> response = new Response<ResBMSEnumCodeVO>();
		if (reqBMSEnumCodeVO.getNameCN() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "nameCN" });
		}
		Map<String, Object> paramMap;
		try {
			paramMap = ProductUtils.bean2map(reqBMSEnumCodeVO);
			BMSEnumCode enumCode = demoService.getByVo(paramMap);
			// 构造相应参数
			if (enumCode != null) {
				ResBMSEnumCodeVO resDemoVO = new ResBMSEnumCodeVO();
				BeanUtils.copyProperties(enumCode, resDemoVO);
				response.setData(resDemoVO);
			}
		
		} catch (Exception e) {
			/*System.out.println(e);*/
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}
		

		return response;
	}

	@Override
	public Response<List<ResBMSEnumCodeVO>> getProducAssetsInfo(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<List<ResBMSEnumCodeVO>> response = new Response<List<ResBMSEnumCodeVO>>();
		if (reqBMSEnumCodeVO.getProductId() == null && reqBMSEnumCodeVO.getProductCode() == null) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[] { "productId | productCode" });
		}
		Map<String, Object> paramMap;
		try {
			paramMap = ProductUtils.bean2map(reqBMSEnumCodeVO);
			List<BMSEnumCode> enumCode = demoService.getProducAssetsInfo(paramMap);
			System.out.println("-------------"+enumCode.size());
			// 构造相应参数
			if (enumCode != null) {
				List<ResBMSEnumCodeVO> resDemoList = new ArrayList<ResBMSEnumCodeVO>();
				for (int i = 0; i < enumCode.size(); i++) {
					ResBMSEnumCodeVO resDemoVO = new ResBMSEnumCodeVO();
					BeanUtils.copyProperties(enumCode.get(i), resDemoVO);
					resDemoList.add(resDemoVO);
				}
				response.setData(resDemoList);
			}
		} catch (Exception e) {
			/*System.out.println(e);*/
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSEnumCodeVO> listBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		ResListVO<ResBMSEnumCodeVO> response = new ResListVO<ResBMSEnumCodeVO>();
		List<BMSEnumCode> listEnum = new ArrayList<BMSEnumCode>();
		try {
			Map<String, Object>	paramMap = ProductUtils.bean2map(reqBMSEnumCodeVO);
			listEnum = demoService.listBy(paramMap);
			if (listEnum != null && listEnum.size() > 0) {
				List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
				for (BMSEnumCode enumCode : listEnum) {
					ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
					BeanUtils.copyProperties(enumCode, vo);
					records.add(vo);
				}
				response.setParamList(records);
			}
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);	
		}
		
		return response;
	}

	@Override
	public Response<List<ResBMSEnumCodeVO>> findCodeByUnit(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<List<ResBMSEnumCodeVO>> response = new Response<List<ResBMSEnumCodeVO>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("code",reqBMSEnumCodeVO.getCode());
		List<BMSEnumCode> list=demoService.findCodeByUnit(map);
		if (list != null && list.size() > 0) {
			List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
			for (BMSEnumCode enumCode : list) {
				ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
				BeanUtils.copyProperties(enumCode, vo);
				records.add(vo);
			}
			response.setData(records);
		}
		
		return response;
	}

	@Override
	public Response<List<ResBMSEnumCodeVO>> findCodeByProfession(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<List<ResBMSEnumCodeVO>> response = new Response<List<ResBMSEnumCodeVO>>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code",reqBMSEnumCodeVO.getCode());
		if(reqBMSEnumCodeVO.getParentCode().equals("00002")){//受薪人士
			map.put("parentCode", 1);
		}else{//自雇，私营
			map.put("parentCode", 2);
		}
		
		List<BMSEnumCode> list=demoService.findCodeByProfession(map);
		if (list != null && list.size() > 0) {
			List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
			for (BMSEnumCode enumCode : list) {
				ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
				BeanUtils.copyProperties(enumCode, vo);
				records.add(vo);
			}
			response.setData(records);
		}
		
		return response;
	}

	@Override
	public Response<List<ResBMSEnumCodeVO>> findByservenProfession(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		Response<List<ResBMSEnumCodeVO>> response = new Response<List<ResBMSEnumCodeVO>>();
		
		List<BMSEnumCode> list=demoService.findByservenProfession();
		if (list != null && list.size() > 0) {
			List<ResBMSEnumCodeVO> records = new ArrayList<ResBMSEnumCodeVO>();
			for (BMSEnumCode enumCode : list) {
				ResBMSEnumCodeVO vo = new ResBMSEnumCodeVO();
				BeanUtils.copyProperties(enumCode, vo);
				records.add(vo);
			}
			response.setData(records);
		}
		
		return response;
	}

}
