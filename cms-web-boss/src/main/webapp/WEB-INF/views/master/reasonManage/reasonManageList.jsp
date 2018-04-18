<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/reasonManage/reasonManage.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
 <div id="query_ReasonDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr align="left">
			<td width="300px"><span>原因：</span> <input id="reason_model"
				class="easyui-textbox input" type="text" /></td>
			<td width="300px"><span>原因类型：</span> 
				<input class="easyui-combobox" id="reason_type_1" ></td>
		</tr>
		<tr>
			<td colspan="2">
			<a id="searchBts" class="easyui-linkbutton" iconCls="icon-search">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSReasonManagementInfo()" href="#">
				<span style="font-size: 12px">新&nbsp;建</span>
			</a>
			
			</td>
		</tr>
	</table>
</div> 
<div id="enumCodePage" title="原因查询" style="height:88%;"  data-options="region:'north'">
	<table id="new_reasonManageDatagrid" toolbar="#toolbar"></table>
</div>
<!-- 新增枚举面板 -->
	<div id="panelBMSReasonManagementInfo" title="原因管理新增" class="easyui-window" class="easyui-window" closed="true" style="width:800px;height:400px;">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="addBMSReasonManageInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" class="easyui-textbox input" id="editOldReason">
			<table class="table_ui W100 center_m">
				<tr>
				  <th>原因编码:<span style="color: red;">*</span>:</th>
				  <td><input type="text" class="easyui-textbox input" id="reasonCode" name="reasonCode" style="width: 200px"></td>
				    <th>原因级别<span style="color: red;">*</span>:</th>
				  <td><input class="easyui-combobox" style="width:200px;" id="r_type" data-options="valueField:'value',textField:'label',
					data: [{label: '一级原因',value: '1'},{label: '二级原因',value: '2'}]" editable="false" /></td>
				  
				</tr>
				<tr>
				  <th>原因类型<span style="color: red;">*</span>:</th>
				  <td><input class="easyui-combobox" id="r_operationType" style="width:200px;" editable="false"/></td>
				  <th>一级原因<span style="color: red;">*</span> :</th>
				  <td><input type="text"class="easyui-textbox input" id="r_firstReason" style="width: 200px" valueField='code' textField='reason' editable="false"></td>
				</tr>
				<tr>
					<th>原因<span style="color: red;">*</span> :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="reason"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>备注 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="remark"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>限制再申请天数 :</th>
					<td><input type="text" class="easyui-textbox input" id="canRequestDays" style="width: 200px" name="canRequestDays"></td>
					<th>规则：</th>
					<td><input class="easyui-combobox" style="width:200px;" id="condition_Type" editable="false"></td>
				</tr>
				<tr>
				 <th>操作环节<span style="color: red;">*</span>:</th>
				 <td><input class="easyui-combobox" id="r_operationRelation" style="width:200px;" editable="false"/></td>
				 <th>显示环节:</th>
				 <td><input class="easyui-combobox" id="r_redioQiyong"style="width:200px;" editable="false"></td> 
				</tr>
				<tr>
					<th>是否启用:</th>
					<td> <input type="radio" name="redio" value="0" id="0"/>是
						 <input type="radio" name="redio" value="1" id="1"/>否
					</td>
					
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
<div id="editPanelBMSReasonManagementInfo" class="easyui-window" title="原因管理编辑"
	align="center" closed="true"
	style="top: 350px; height: 250px; width: 750px;">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="editBMSReasonManageInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" class="easyui-textbox input" id="edit_moduleName">
			 <input type="hidden" class="easyui-textbox input" id="edit_parent_id">
			 <input type="hidden" class="easyui-textbox input" id="edit_relationCode">
			<table class="table_ui W100 center_m">
				<tr>
				    <th>原因编码:<span style="color: red;">*</span>:</th>
				    <td><input type="text" class="easyui-textbox input" id="editcode" name="editcode" style="width: 200px" disabled="disabled"></td>
				    <th>原因级别:</th>
					<td><input class="easyui-textbox" style="width:200px;" id="editType" disabled="disabled"/></td>		
				  </tr>
				<tr>
				    <th>原因类型<span style="color: red;">*</span>:</th>
				    <td><input class="easyui-textbox" id="editoperationType" style="width:200px;" disabled="disabled"/></td>
					<th>一级原因 :</th>
					<td><input type="text" class="easyui-textbox input" id="editfirstreason" style="width: 200px" valueField='code' textField='reason' disabled="disabled"></td>
				</tr>
				<tr>
					<th>原因 <span style="color: red;">*</span>:</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="editreason"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>备注 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="editremark"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>限制再申请天数 :</th>
					<td><input type="text" class="easyui-textbox input" id="editcanRequestDays" name="editcanRequestDays" style="width: 200px"></td>
					<th>规则：</th>
					<td><input class="easyui-combobox" style="width:200px;" id="edit_condition_Type" editable="false"></td>
				</tr>
				<tr>
				 <th>操作环节<span style="color: red;">*</span>:</th>
				 <td><input class="easyui-combobox" id="editOperationRelation" style="width:200px;" editable="false"/></td>
				 <th>显示环节<span style="color: red;">*</span>:</th>
				 <td><input class="easyui-combobox" id="editRedioQiyong"style="width:200px;" editable="false"></td> 
				</tr>
				<tr>
					<th>是否启用:</th>
					<td> <input type="radio" name="editredio" value="0" id="00"/>是
						 <input type="radio" name="editredio" value="1" id="11"/>否
					</td>
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
				iconCls="icon-cancel" onclick="editReasoncloseWindow();">取消</a>
		</div>
	</div>
</div>
