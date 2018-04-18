package com.ymkj.cms.biz.facade.master;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.service.master.IBMSTmParameterExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.entity.apply.LoanMapperToEntity;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.facade.audit.ContractFirstAdultExecuter;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSDebtRadioService;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseEntityService;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;

/**
 * 服务提供者demo: 1. 请求信息验证 2. 调用业务层实现业务操作 3. 封装 响应response 4. 对于必须捕获的异常采用 参考
 * listPage 的用法
 * 
 * @author user
 * 
 */
@Service
public class BMSTmParameterExecuter implements IBMSTmParameterExecuter {
	public static Logger logger = LoggerFactory.getLogger(ContractFirstAdultExecuter.class);

	@Autowired
	private IBMSTmParameterService tmParameterService;
	
	@Autowired
	private IBMSLoanBaseEntityService loanBaseService;
	
	@Autowired
	private ICoreHttpService coreHttpService;
    @Autowired
    private IBMSDebtRadioService iDebtRadioService;
	@Override
	public PageResponse<ResBMSTmParameterVO> listPage(ReqBMSTmParameterVO reqDemoVO) {
		PageResponse<ResBMSTmParameterVO> response = new PageResponse<ResBMSTmParameterVO>();

		// 参数校验
		if (reqDemoVO.getPageNum() == 0 || reqDemoVO.getPageSize() == 0) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}

		try {
			// 构造请求参数
			PageParam pageParam = new PageParam(reqDemoVO.getPageNum(), reqDemoVO.getPageSize());
			Map<String, Object> paramMap = BeanKit.bean2map(reqDemoVO);

			// 调用业务逻辑
			PageBean<BMSTmParameter> pageBean = tmParameterService.listPage(pageParam, paramMap);

			// 构造响应参数
			List<ResBMSTmParameterVO> records = new ArrayList<ResBMSTmParameterVO>();
			List<BMSTmParameter> demoEntitys = pageBean.getRecords();
			for (BMSTmParameter demoEntity : demoEntitys) {
				ResBMSTmParameterVO resDemoVO = new ResBMSTmParameterVO();
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
	public Response<ResBMSTmParameterVO> addTmParameter(ReqBMSTmParameterVO reqDemoVO) {
		Response<ResBMSTmParameterVO> response = new Response<ResBMSTmParameterVO>();
		BMSTmParameter tmParameter = new BMSTmParameter();
		BeanUtils.copyProperties(reqDemoVO, tmParameter);
		tmParameter.setIsDelete((long) 0);
		tmParameter.setInputType((long) 1);
		long count = tmParameterService.insert(tmParameter);
		response.setRepMsg(String.valueOf(count));
		return response;
	}

	@Override
	public Response<ResBMSTmParameterVO> updateTmParameter(ReqBMSTmParameterVO reqDemoVO) {
		Response<ResBMSTmParameterVO> response = new Response<ResBMSTmParameterVO>();
		BMSTmParameter tmParameter = new BMSTmParameter();
		BeanUtils.copyProperties(reqDemoVO, tmParameter);
		tmParameterService.update(tmParameter);
		response.setRepMsg("true");
		return response;
	}

	@Override
	public Response<ResBMSTmParameterVO> findOne(ReqBMSTmParameterVO reqDemoVO) {
		Response<ResBMSTmParameterVO> response = new Response<ResBMSTmParameterVO>();
		BMSTmParameter tmParameter = tmParameterService.getById(reqDemoVO.getId());
		// 构造相应参数
		if (tmParameter != null) {
			ResBMSTmParameterVO resDemoVO = new ResBMSTmParameterVO();
			BeanUtils.copyProperties(tmParameter, resDemoVO);
			response.setData(resDemoVO);
		}
		return response;
	}

	@Override
	public ResListVO<ResBMSTmParameterVO> findTmParameterByCode(ReqBMSTmParameterVO reqDemoVO) {
		// 参数校验
		if (reqDemoVO.getLoanNo()==null) {
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "loanNo" });
		}
		if(reqDemoVO.getCode()==null){
			throw new BizException(CoreErrorCode.PARAM_ERROR, new Object[] { "code" });
		}
		try {
			List<ResBMSTmParameterVO> records = new ArrayList<ResBMSTmParameterVO>();
			BMSLoanBase loanBase=loanBaseService.findBYLoanNo(reqDemoVO.getLoanNo());
			if(loanBase!=null){
				//如果是结清状态掉核心，查询结清日期
				if(reqDemoVO.getCode().equals("RELOAN")){
					Map<String,Object> map=new HashMap<String, Object>();

					map.put("idnum",loanBase.getIdNo());//1， 客户经理 2，客服
					logger.info("调用核心API，查询当前人的单子的单子是否结清入参："+loanBase.getIdNo());
					HttpResponse httpResponse=coreHttpService.loanStatus(map);
					Map<String,Object> contentMap= JsonUtils.decode(httpResponse.getContent(),Map.class);
					logger.info("调用核心API,查询当前人的单子是否结清返回CODE:"+contentMap.get("code")+",MSG:"+contentMap.get("msg"));
					if(contentMap.get("code").equals("000000") && !contentMap.get("msg").equals("该用户不在系统中")){
						ObjectMapper mapperLoan = new ObjectMapper(); 
						List<LoanMapperToEntity> listReadValues=null;
						try {
							JsonNode node = mapperLoan.readTree(httpResponse.getContent());  
							JsonNode nodeString=node.get("loan");
							JavaType javaType=mapperLoan.getTypeFactory().constructParametricType(ArrayList.class, LoanMapperToEntity.class);   
							listReadValues = mapperLoan.readValue(nodeString.toString(), javaType);
							System.out.println(listReadValues.get(0).getLoanNo());
						} catch (Exception e) {
							logger.info("-------------RELOAN状态的申请单装换集合实体失败----------------"+contentMap.get("loan").toString());
							e.printStackTrace();
						} 
						if(null!=listReadValues&&listReadValues.size()>0){
							for(LoanMapperToEntity loanMapperToEntity:listReadValues){
								if(loanMapperToEntity.getLoanState().equals("结清")){
									int mouth=mouthDiff(new Date(),loanMapperToEntity.getCreateTime());
									if(mouth<=3){
										reqDemoVO.setCode("RELOAN_LT");
									}else{
										reqDemoVO.setCode("RELOAN_GT");
									}
									break;
								}
							}
						}	
					}else{
						logger.info("当前还款状态为" +contentMap.get("status"));
						throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL);	
					}	
				}
				BMSDebtRadio debtRadio=new BMSDebtRadio();
				ResBMSTmParameterVO resDemoVO = new ResBMSTmParameterVO();
				debtRadio.setCustomerTypeCode(reqDemoVO.getCode());
				BMSDebtRadio bmsDebtRadio=iDebtRadioService.findDebtRadioById(debtRadio);
				if(bmsDebtRadio.getTotalDebtRadio().compareTo(loanBase.getDebtDio())==-1){
					resDemoVO.setTotalDebtRadio(loanBase.getDebtDio().toString()); 
				}else{
					resDemoVO.setTotalDebtRadio(bmsDebtRadio.getTotalDebtRadio().toString());
				}

				resDemoVO.setCode(bmsDebtRadio.getCustomerTypeCode());
				resDemoVO.setName(bmsDebtRadio.getCustomerTypeName());
				resDemoVO.setInternalDebtRadio(bmsDebtRadio.getInternalDebtRadio().toString());
				records.add(resDemoVO);
			}

			return new ResListVO<ResBMSTmParameterVO>(records);	
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}

	}
	public Integer mouthDiff(Date str1,String str2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(str1);
		aft.setTime(sdf.parse(str2));
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		Integer mouth=(Math.abs(month + result));
		return mouth;
	}

	@Override
	public Response<ResBMSTmParameterVO> findOneByParam(ReqBMSTmParameterVO reqDemoVO) {
		Response<ResBMSTmParameterVO> response = new Response<ResBMSTmParameterVO>();
		Map<String, Object> paramMap;
		try {
			paramMap = BeanKit.bean2map(reqDemoVO);
			BMSTmParameter tmParameter = tmParameterService.getBy(paramMap);
			// 构造相应参数
			if (tmParameter != null) {
				ResBMSTmParameterVO resDemoVO = new ResBMSTmParameterVO();
				BeanUtils.copyProperties(tmParameter, resDemoVO);
				response.setData(resDemoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
