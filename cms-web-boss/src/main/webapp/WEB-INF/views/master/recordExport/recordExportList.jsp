<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/recordExport/recordExportList.js"></script>
<div id="queryRecordExportDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 150px;background-color: rgb(244, 244, 244);">
	<form id="manualDispatch_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">协议签署日期:</span><input type="text"  class="easyui-datebox" id="startDate" ></td>
				<td><span style="margin-left: 10px;margin-right: 10px">~ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text"  class="easyui-datebox" id="endDate" ></td>
				<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span>
					<input class="easyui-combobox" style="width:180px;" id="queryChannelName" value="wmxt" readonly="readonly" data-options="valueField:'value',textField:'label',
					data: [{label: '外贸信托',value: 'wmxt'}]" />
				</td>
				<td><span style="margin-left: 10px;margin-right: 10px">银行卡所属地区:</span><input class="easyui-combobox"  style="width:180px;" id="queryAreaType" value="01" data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '深圳地区',value: '01'},{label: '异地',value: '99'}]" /></td>
			</tr>
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">合同编号:</span><input type="text" class="easyui-textbox input" id="contractNo" ></td>
			</tr>
			<tr align="left">
				<td colspan="3">
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSRecordExportInfo();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
          		 <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="uploadRecordInfo()" style="margin-left: 10px;margin-right: 10px">
          			<span style="font-size: 12px">导&nbsp;出</span>
          		 </a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="szGridPage" title="银行查询深圳地区" style="height: 92px;">
	<table id="new_datagrid_recordExportSZ" ></table>
</div>
<div  id="ydGridPage" title="银行查询异地" style="height: 92px;">
	<table id="new_datagrid_recordExportYD" ></table>
</div>
