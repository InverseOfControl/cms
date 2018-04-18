<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/reasonManagement/reasonManagement.js"></script>
<div id="queryReasonDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr align="left">
			<td width="300px"><span>操作模块：</span> <input id="model"
				class="easyui-textbox input" type="text" /></td>
			<td width="300px"><span>操作类型：</span> <input id="type"
				class="easyui-textbox input" type="text" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<a id="searchBt" class="easyui-linkbutton" iconCls="icon-search">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSReasonManagementInfo()" href="#">
				<span style="font-size: 12px">新&nbsp;建</span>
			</a>
			</td>
		</tr>
	</table>
</div>
<div id="enumCodePage" title="原因查询" style="padding-top: 10px;">
	<table id="new_reasonManageDatagrid" toolbar="#toolbar"></table>
</div>
<!-- 新增枚举面板 -->
<div id="panelBMSReasonManagementInfo" class="easyui-window" title="原因管理新增"
	align="center" closed="true"
	style="top: 350px; height: 250px; width: 750px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="addBMSReasonManageInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					
					<th>原因码<span style="color: red;">*</span>:</th>
					<td><input type="text" class="easyui-textbox input"
						id="code" style="width: 200px"></td>
					<th>分类<span style="color: red;">*</span>:</th>
					<td><input class="easyui-combobox" style="width:200px;" id="addType" data-options="valueField:'value',textField:'label',
					data: [{label: '一级',value: '1'},{label: '二级',value: '2'}]" /></td>
				</tr>
				<tr>
					<th>父节点 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="parentId" style="width: 200px"></td>
					<th>排序:</th>
					<td><input type="text" class="easyui-textbox input"
						id="levelOrder" style="width: 200px"></td>
				</tr>
				<tr>
					<th>操作模块 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="operationModule" style="width: 200px"></td>
					<th>是否擦入黑名单:</th>
					<td><input class="easyui-combobox" style="width:200px;" id="isBlacklist" data-options="valueField:'value',textField:'label',
					data: [{label: '是',value: '0'},{label: '否',value: '1'}]" /></td>
				</tr>
				<tr>
					<th>操作类型 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="operationType" style="width: 200px"></td>
					<th>限制申请天数:</th>
					<td><input type="text" class="easyui-textbox input"
						id="canRequestDays" style="width: 200px"></td>
				</tr>
				<tr>
					<th>原因 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="reason"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>原因解释 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="reasonTexplain"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>备注 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="remark"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSReasonManageInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closeWindow();">取消</a>
		</div>
	</div>
</div>
<!-- 新增参数面板 -->
<!-- 修改参数面板 -->
<div id="editPanelBMSReasonManagementInfo" class="easyui-window" title="原因管理编辑"
	align="center" closed="true"
	style="top: 350px; height: 250px; width: 750px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="editBMSReasonManageInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					
					<th>原因码<span style="color: red;">*</span>:</th>
					<td><input type="text" class="easyui-textbox input"
						id="editcode" style="width: 200px"></td>
					<th>分类<span style="color: red;">*</span>:</th>
					<td><input class="easyui-combobox" style="width:200px;" id="editType" data-options="valueField:'value',textField:'label',
					data: [{label: '一级',value: '1'},{label: '二级',value: '2'}]" /></td>
				</tr>
				<tr>
					<th>父节点 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="editparentId" style="width: 200px"></td>
					<th>排序:</th>
					<td><input type="text" class="easyui-textbox input"
						id="editlevelOrder" style="width: 200px"></td>
				</tr>
				<tr>
					<th>操作模块 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="editoperationModule" style="width: 200px"></td>
					<th>是否擦入黑名单:</th>
					<td><input class="easyui-combobox" style="width:200px;" id="editisBlacklist" data-options="valueField:'value',textField:'label',
					data: [{label: '是',value: '0'},{label: '否',value: '1'}]" /></td>
				</tr>
				<tr>
					<th>操作类型 :</th>
					<td><input type="text" class="easyui-textbox input"
						id="editoperationType" style="width: 200px"></td>
					<th>限制申请天数:</th>
					<td><input type="text" class="easyui-textbox input"
						id="editcanRequestDays" style="width: 200px"></td>
				</tr>
				<tr>
					<th>原因 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="editreason"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>原因解释 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="editreasonTexplain"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>备注 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="editremark"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr style="display: none;">
					<td><input type="text"
						class="easyui-textbox input" id="editid">
						<input type="text"
						class="easyui-textbox input" id="editversion">
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="editBMSReasonManageInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="editcloseWindow();">取消</a>
		</div>
	</div>
</div>
<!-- 修改参数面板 -->