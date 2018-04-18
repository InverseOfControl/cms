<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/enumCode/enumCode.js"></script>
<div id="queryChannleDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr align="left">
			<td width="300px"><span>数据类型：</span> <input id="codeType"
				class="easyui-textbox input" type="text" /></td>
			<td width="300px"><span>数据代码：</span> <input id="code"
				class="easyui-textbox input" type="text" /></td>
			<td width="300px"><span>数 据 值：</span> <input id="nameCN"
				class="easyui-textbox input" type="text" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<a id="searchBt" class="easyui-linkbutton" iconCls="icon-search">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSEnumCodeInfo()" href="#">
				<span style="font-size: 12px">新&nbsp;建</span>
			</a>
			</td>
		</tr>
	</table>
</div>
<div id="enumCodePage" title="枚举查询" style="padding-top: 10px;">
	<table id="new_enumCodeDatagrid" toolbar="#toolbar"></table>
</div>
<!-- 新增、修改枚举面板 -->
<div id="panelBMSEnumCodeInfo" class="easyui-window" title="改枚管理"
	align="center" closed="true"
	style="top: 350px; height: 180px; width: 750px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="addBMSEnumCodeInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					<input type="hidden" id="panelCodeId">
					<th>数据类型:</th>
					<td><input type="text" class="easyui-textbox input"
						id="panelCodeType" style="width: 200px"></td>
					<th>数据代码:</th>
					<td><input type="text" class="easyui-textbox input"
						id="panelCode" style="width: 200px"></td>
				</tr>
				<tr>
					<th>数据值:</th>
					<td><input type="text" class="easyui-textbox input"
						id="panelNameCN" style="width: 200px"></td>
					<th>版 本:</th>
					<td><input type="text" class="easyui-textbox input"
						id="panelVersion" style="width: 200px"></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSEnumCodeInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closeWindow();">取消</a>
		</div>
	</div>
</div>
<!-- 新增、修改枚举面板 -->
