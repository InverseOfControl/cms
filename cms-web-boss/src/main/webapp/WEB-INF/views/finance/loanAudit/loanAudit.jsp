 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
	<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/finance/loanAudit/loanAudit.js"></script>
<script type="text/javascript"
src="${ctx}/resources/js/finance/loanAudit/comboboxList.js"></script>

<div id="queryLoanAuditDiv" class="easyui-panel W100 " data-options="collapsible:true" style="background-color:#EBEEF3"> 
	 <input id ="isFinanceUser" name="isFinanceUser" type="hidden" value="${isFinanceUser}"/>
	 <input id ="userCode" name="userCode" type="hidden" value="${userCode}"/>
	 <input id ="userName" name="userName" type="hidden" value="${userName}"/>
	 <input id ="picFileDataUrl" name="picFileDataUrl" type="hidden" value="${picFileDataUrl}"/>
	  <input id ="queryCount" name="queryCount" type="hidden" value="0"/>
	<table style="border-collapse: separate; border-spacing: 15px;">
		<tr>
				<th>借款编号:</th>
				<td style="text-align: left"><input type="text" class="easyui-textbox input"
					name="loanNos" style="width: 180px"></td>
					
					<th >客户姓名:</th>
					<td style="text-align: left"><input type="text" class="easyui-textbox input"
					name="name" id="name"  style="width: 180px"></td>
			
					<th >身份证号码:</th>
					<td style="text-align:center;"><input  type="text" class="easyui-textbox input"
					name="idNo"  id="idNo" style="width: 180px;text-align:center;"></td>
					
					<th>渠道名称:</th>
					<td style="text-align: left"><input id="loanAudit_channelId" name="channelId" style="width: 180px" /></td>
		</tr>
		<tr>	
					<th>产品名称:</th>
					<td style="text-align: left"><input id="loanAudit_productId" name="productId" style="width: 180px"></td>
			
					<th>交件类型:</th>
					<td style="text-align: left"><input id="loanAudit_applyInputFlag" name="applyInputFlag" style="width: 180px"/></td> 
				
					<th  class="loanAudit_undoneDate">合同确认日期:</th>
					<td  class="loanAudit_undoneDate" ><input style="text-align: left;width:180px" id="loanAudit_confirmDate" type="text" class="easyui-datebox"  style="width: 180px"></td>
					<td class="loanAudit_undoneDate" style="text-align:center;font-weight:bolder; ">~</td> 
					<td  class="loanAudit_undoneDate"><input style="text-align: left;width:180px" id="loanAudit_confirmDate2" type="text" class="easyui-datebox"  style="width: 180px"></td>
					
					<th class ="loanAudit_doneDate" style="text-align: left;">放款审核日期:</th>
					<td  class ="loanAudit_doneDate" style="text-align: left;width:180px" ><input id="loanAudit_financeAuditDate" type="text" class="easyui-datebox"  style="width: 180px" ></td>
					<td class="loanAudit_doneDate" style="text-align: center;font-weight:bolder; ">~</td> 
					<td class ="loanAudit_doneDate" style="text-align: left;width:180px"><input id="loanAudit_financeAuditDate2" type="text" class="easyui-datebox"  style="width: 180px" ></td> 
			</tr>
			<tr>	
					<th class="loanAudit_orgAuditState">机构审核状态:</th>
					<td class="loanAudit_orgAuditState" style="text-align: left"><input id="loanAudit_orgAuditState" name="loanAudit_orgAuditState" style="width: 180px"></td>
			</tr>
					
			</table>
		<br />
		<div style="text-align: left; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
				onclick="query_loanAudit_Info();">查询</a>	
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="bacthPassAuditBtn" 
			onclick="bacthPassLoanAudit();">批量通过</a> 
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="bacthBackAuditBtn" 
			onclick="openBacthAuditBackW();">批量退回</a> 
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="exportLoanAuditBtn" 
			onclick="exportLoanAuditResult();">导出</a> 
		</div>
</div>

<div id="loanAuditTT" class="easyui-tabs" style="height:80%;padding-top: 20px">
    <div  title="待办任务" id="unDoneloanAudit_Page"  data-options="region:'north'" style="height:80%;border:1px solid #95b8e7 ;">
		<table id="unDoneLoanAudit_datagrid" style="width:auto;background-color:#EBEEF3"></table>
	</div>
     <div  title="已完成" id="doneloanAudit_Page"  data-options="region:'north'" style="height:80%;border:1px solid #95b8e7 ">
		<table id="doneloanAudit_datagrid"  style="width:auto;;background-color:#EBEEF3"></table>
	</div>
</div>

<div id="backAuditInfo" title="退回原因"  class="easyui-window" closed="true" style="width:700px;height:350px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div class="easyui-layout">
	 <input id ="backAudit_id" name="backAudit_id" type="hidden" />
	 <input id ="backAudit_loanNo" name="backAudit_loanNo" type="hidden"/>
	 <input id ="loanAudit_remarkCount" name="loanAudit_remarkCount" type="hidden"/>
	 <input id ="backAudit_channelCd" name="backAudit_channelCd" type="hidden"/>
	 <input id ="backAudit_isBacth" name="backAudit_isBacth" type="hidden"/>
	 <form id="backAuditForm" >
		<table id ="backAudit">
			<tr height="40px">
			<td ><span style="color: red">*&nbsp;</span><span style="font-weight: bold">退回类型：</span></td>
			<td><input checked="checked" type="radio" value="0" name="backType" id="0" />退回到合同签约</td><td></td>
			</tr>
			<tr height="40px" class='loanAudit_name'>
			<td class='loanAudit_name'><span style="font-weight: bold">&nbsp; 客户姓名：</span></td><td class='loanAudit_name'><span id="loanAudit_name"></span></td><td></td>
			</tr>
			<tr height="40px">
				<td><span style="color: red">*&nbsp;</span><span style="font-weight: bold">一级原因：</span></td><td>
				<input type="text" class="easyui-textbox input" name="backAudit_firstLevleReasonsCode" id="backAudit_oneReason"></td>
				<td><span style="color: red">*&nbsp;</span><span style="font-weight: bold">二级原因：</span></td><td>
				<input type="text" class="easyui-textbox input" name="backAudit_twoLevleReasonsCode" id="backAudit_twoReason"></td>
			</tr>

			<tr height="100px" style="padding-top:10px" >
				<td valign="top">&nbsp; &nbsp; <span style="font-weight: bold">备注： </span></td>
				<td colspan="3">
				<textarea id="loanAudit_remark" name="mytextarea" style="width:500px;height:80px;" ></textarea></td>
			</tr>
		</table>
		</form>	
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		<div style="margin:0 auto; width:180px; height:20px;">
		 <a class="easyui-linkbutton"  onclick="backAudit();"   iconCls="icon-add"><span style="font-size:12px">确定</span></a>
		<a class="easyui-linkbutton"  onclick="common_closeWindow('#backAuditInfo')"   iconCls="icon-cancel"><span style="font-size:12px">返回</span></a>
		</div>	
		</div>  
	</div>	
</div>


<div id="auditLogInfo" title="日志"  class="easyui-window" closed="true" style="width:600px;height:400px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div class="easyui-layout">
	  <input id ="auditLog_loanNo" name="auditLog_loanNo" type="hidden"/>
	  <table id="audit_LoanLog"></table> 
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		</div>  
	</div>	
</div>

 
 

 