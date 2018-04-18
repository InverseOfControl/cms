<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/reasonManage/reasonReLinks.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
 <div id="query_ReasonDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr align="left">
			<td width="300px"><span>原因：</span> <input id="reason_models"
				class="easyui-textbox input" type="text" /></td>
			<td width="300px"><span>原因类型：</span> 
				<input class="easyui-combobox" id="reason_type" />
			</td>
			<td width="300px"><span>操作环节：</span> 
				<input class="easyui-combobox" id="operation_module"></td>
		</tr>
		<tr>
			<td colspan="2">
			<a id="searchLinksBt" class="easyui-linkbutton" iconCls="icon-search">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="exportReason()" href="#">
				<span style="font-size: 12px">导&nbsp;出</span>
			</a>
			</td>
		</tr>
	</table>
</div> 
<div id="enumCodePage" title="原因查询" style="height:88%;"  data-options="region:'north'" >
	<table id="new_reasonReLinksDatagrid" toolbar="#toolbar" pagination="true" style="width: auto;"></table>
</div>

<!-- <div id="editPanelBMSReasonLinksInfo" class="easyui-window" title="原因环节操作"
	align="center" closed="true"
	style="top: 350px; height: 250px; width: 750px;">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="editBMSReasonLinksInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" class="easyui-textbox input" id="editOldReasons">
			<input type="hidden" class="easyui-textbox input" id="moduleName">
			<input type="hidden" class="easyui-textbox input" id="editId">
			<table class="table_ui W100 center_m">
				<tr>
				<th>原因关联环节</th>  
              
                <td>
                <input class="easyui-combobox" id="operation_module_1">
               
                </td>
                </td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="editBMSReasonLinksInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="editcloseWindow();">取消</a>
		</div>
	</div>
</div> -->

<div id="editPanelBMSReasonLinksInfo" class="easyui-window" title="原因管理编辑"
	align="center" closed="true"
	style="top: 350px; height: 250px; width: 750px;">
	<div style="padding: 10px 10px 10px 10px; width: 700px;">
		<form id="editBMSReasonLinksInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			 <input type="hidden" class="easyui-textbox input" id="r_edit_parent_id">
			 <input type="hidden" class="easyui-textbox input" id="r_edit_relationCode">
			 <input type="hidden" class="easyui-textbox input" id="r_editOldReasons">
			 <input type="hidden" class="easyui-textbox input" id="r_moduleName">
			 <input type="hidden" class="easyui-textbox input" id="r_editId">
			<table class="table_ui W100 center_m">
				<tr>
				    <th>原因编码:<span style="color: red;">*</span>:</th>
				    <td><input type="text" class="easyui-textbox input" id="r_editcode" name="r_editcode" style="width: 200px" disabled="disabled"></td>
				    <th>原因级别:</th>
					<td><input class="easyui-textbox" style="width:200px;" id="r_editType" disabled="disabled"/></td>		
				  </tr>
				<tr>
				    <th>原因类型<span style="color: red;">*</span>:</th>
				    <td><input class="easyui-textbox" id="r_editoperationType" style="width:200px;" disabled="disabled"/></td>
					<th>一级原因 :</th>
					<td><input type="text" class="easyui-textbox input" id="r_editfirstreason" style="width: 200px" valueField='code' textField='reason' disabled="disabled"></td>
				</tr>
				<tr>
					<th>原因 <span style="color: red;">*</span>:</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="r_editreason"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>备注 :</th>
					<td colspan="3"><input type="text"
						class="easyui-textbox input" id="r_editremark"
						data-options="multiline:true" style="height: 60px; width: 565px"></td>
				</tr>
				<tr>
					<th>限制再申请天数 :</th>
					<td><input type="text" class="easyui-textbox input" id="r_editcanRequestDays" name="r_editcanRequestDays" style="width: 200px"></td>
					<th>规则：</th>
					<td><input class="easyui-combobox" style="width:200px;" id="r_edit_condition_Type"></td>
				</tr>
				<tr>
				 <th>操作环节<span style="color: red;">*</span>:</th>
				 <td><input class="easyui-combobox" id="r_operation_module_1" style="width:200px;" editable="false"/></td>
				 <th>显示环节:</th>
				 <td><input class="easyui-combobox" id="r_editRedioQiyong"style="width:200px;" editable="false" ></td> 
				</tr>
				<tr>
					<th>是否启用:</th>
					<td> <input type="radio" name="r_editredio" value="0" id="00"/>是
						 <input type="radio" name="r_editredio" value="1" id="11"/>否
					</td>
				</tr>
				<tr style="display: none;">
					<td><input type="text" class="easyui-textbox input" id="r_editid">
						<input type="text" class="easyui-textbox input" id="r_editversion">
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="editBMSReasonLinksInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="editcloseWindow();">取消</a>
		</div>
	</div>
</div>
