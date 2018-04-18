package  com.ymkj.cms.biz.process.handler.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.AssignmentHandler;
import com.bstek.uflo.process.node.TaskNode;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;


@Component
public class BMSLoanAuditHandler implements AssignmentHandler{

	@Override
	public Collection<String> handle(TaskNode taskNode, ProcessInstance processInstance, Context context) {		
		//调用权限系统查询可以进行财务人员
		ReqParamVO paramReqParamVO =new ReqParamVO();
		paramReqParamVO.setSysCode("bms");
		//paramReqParamVO.setOrgId(100000189L);
		//Response<List<ResEmployeeVO>> response =iEmployeeExecuter.findEmpsByOrgId(paramReqParamVO);
	//	List<ResEmployeeVO> resEmployeeVOs =response.getData();
			List<String> EmployeCodes =new ArrayList<String>();
		//for (ResEmployeeVO resEmployeeVO : resEmployeeVOs) {
		//	EmployeCode.add(resEmployeeVO.getUsercode());
	//	}*/
		//if(resEmployeeVOs.size() <= 1){
			EmployeCodes.add("financialLoan");
			EmployeCodes.add("financialAudit");
		//}
		return EmployeCodes;
	}
}
