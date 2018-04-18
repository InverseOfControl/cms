<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/common/jqueryform.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/master/contractChange/contractChangeList.js"></script>
<style type="text/css">
/** 防止案件标识过高，将序号挤错位
*/
.datagrid-row {
	height: 32px;
}
</style>
<!-- 查询面板 -->
<div id="queryContractChangeDiv" style="height: 152px; padding-top: 8px;">
	<form id="manualDispatch_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">借款编号:</span><input type="text" id="loanNo"
					class="easyui-textbox input" style="width: 173px;"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">客户姓名:</span><input type="text" id="personName"
					class="easyui-textbox input" style="width: 173px;"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input type="text" id="channelName"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">产品名称:</span><input type="text" id="productName"></td>
			</tr>
			<tr>
				<td><span style="margin-left: 10px;margin-right: 10px">签约网点:</span><input type="text" id="contractBranch"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">签约客服:</span><input type="text" id="signName"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">业务环节:</span><input type="text" id="businessSegment"></td>
			</tr>
			<tr>
				<td>
				<a id="searchBt" class="easyui-linkbutton" iconCls="icon-search" onclick="contract_search();" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">查&nbsp;询</span>
				</a> 
				<a class="easyui-linkbutton" iconCls="icon-add" onclick="contract_change();" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">改&nbsp;派</span>
				</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<!-- 数据列表面板 -->
<div id="contractChangePage" title="签约改派查询" style="padding-top: 10px;"
	data-options="region:'north'" style="height:100%;">
	<table id="contractChangeDatagrid" toolbar="#queryContractChangeDiv"></table>
</div>

<!-- 改派面板 -->
<div id="panelContractChange" class="easyui-window" title="签约改派"
	align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<form id="changeContractForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					<th>签约网点:</th>
					<td><input id="contract_Branch" type="text"
						class="easyui-textbox input" style="width: 173px;" /></td>
				</tr>
				<tr>
					<th>签约客服:</th>
					<td><input id="sign_Name" type="text"
						class="easyui-textbox input" style="width: 173px;" /></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="changeContract();">确定</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closeWindow();">返回</a>
		</div>
	</div>
</div>
<!-- 改派确认  -->
<div id="panelContractChangeConfirm" class="easyui-window" title="签约改派"
	align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			
			<span style="font-size: 14px" id="panelContractChangeConfirmMessage">若跨门店改派，合同签约流程将重新开始！是否继续？</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="changeContractConfirm();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeConfirmWindow();">返回</a>
		</div>
	</div>
</div>
<!-- 网点-产品配置失效  -->
<div id="panelContractChangeCheck" class="easyui-window" title="签约改派"
	align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="panelContractChangeCheckMessage"></span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="changeContractConfirm();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeCheckWindow();">返回</a>
		</div>
	</div>
</div>
<!-- 改派失败提示  -->
<div id="panelContractChangeFailed" class="easyui-window" title="签约改派"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="panelContractChangeFailedMessage"></span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="closeFailedWindow();">确定</a>
		</div>
	</div>
</div>
