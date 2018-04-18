<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/urgentLimit/urgentLimitList.js"></script>
<div id="querylimitDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<form id="manualDispatch_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">网点名称:</span><input id="orgId" type="text" /></td>
				<th>时间:</th>
				<!-- data-options="formatter:yearFormatter,parser:yearParser" -->
				<td><input type="text" id="limitDate"  class="easyui-datebox"
						style="width: 180px" /></td>
			</tr>
			<tr>
				<td colspan="3">
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSOrg();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="limitPage" title="加急限制查询" style="height: 92px; padding-top: 8px;">
	<table id="new_datagrid_Org" toolbar="#querylimitDiv"></table>
</div>


<div id="updateBMSUrgentConfigInfo" title="加急件配置" class="easyui-window"
	 closed="true"
	style="width: 400px; height: 250px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="updateBMSUrgentConfigInfoForm" name="updateBMSUrgentConfigInfoForm"
			method="post" enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W80 center_m">
				<tr>
					<th>配置数量:</th>
					<td><input type="text" class="easyui-textbox input"
						name="countSize" id="countSize"></td>
				</tr>
				<tr>
					    <td style="display: none;"><input type="text" class="easyui-textbox input"
						 id="updateorgId"></td>
						<td style="display: none;"><input type="text" class="easyui-textbox input"
						 id="updateorgName"></td>
						<td style="display: none;"><input type="text" class="easyui-textbox input"
						 id="updateorglimitTime"></td>
				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="updateBMSUrgentConfigInfo();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeForm();">取消</a>
		</div>
	</div>
</div>
