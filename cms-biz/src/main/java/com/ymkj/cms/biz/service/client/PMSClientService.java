package com.ymkj.cms.biz.service.client;






import com.ymkj.pms.biz.api.vo.request.ReqEmployeeVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.pms.biz.api.vo.response.ResGroupVO;


/**
 * 权限系统调用
 * @author YM10152
 *
 */
public interface PMSClientService {

	/*查询当前处理人所在组*/
	public ResGroupVO findOrgGroupInfo(ReqParamVO csParamVO);
	
	/*根据员工工号获取员工信息*/
	public ResEmployeeVO findEmployeeByCode(ReqEmployeeVO reqEmployeeVO);
	
}
