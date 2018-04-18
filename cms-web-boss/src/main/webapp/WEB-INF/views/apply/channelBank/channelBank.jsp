<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/common/easyuiUtils/comboboxUtils.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/apply/channelBank/comboboxList.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/apply/channelBank/channelBank.js"></script>
<div id="queryChannelBankDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<input type="hidden" id="channelIdRequest" name="channelId" value="${channelId}">
	<table style="border-collapse: separate; border-spacing: 15px;">
		<tr align="left">
			<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input id="channelId" type="text" /></td>
			<td><span style="margin-left: 10px;margin-right: 10px">银行名称:</span><input id="bankId" type="text" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<a id="searchBt" class="easyui-linkbutton" iconCls="icon-search" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSChannelBankInfo()" href="#" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">新&nbsp;建</span>
			</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteBMSChannelBank()" href="#" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">删&nbsp;除</span>
			</a>
			</td>
		</tr>
	</table>
</div>
<div id="channelBankPage" title="签约银行查询" style="padding-top: 10px;">
	<table id="new_channelBankDatagrid" toolbar="#queryChannelBankDiv"></table>
</div>
<!-- 新增、修改枚举面板 -->
<div id="panelBMSChannelBankInfo" class="easyui-window" title="签约银行管理"
	align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<form id="addBMSChannelBankInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					<input type="hidden" id="panelId">
					<th>渠道名称:</th>
					<td><input id="panelChannelId" type="text" /></td>
				</tr>
				<tr>
					<th>银行名称:</th>
					<td><input id="panelBankId" type="text" /></td>
				</tr>
				<tr>
					<th>是否启用:</th>
					<td style="text-align: left"><input type="radio" value="0"
						name="panelIsDisabled" id="0" />是 <input type="radio" value="1"
						name="panelIsDisabled" id="1" />否</td>
						
				</tr>
				
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSChannelBankInfo();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeChBankWindow();">取消</a>
				<input type="hidden" name="oldBankId" id="oldBankId">
		</div>
	</div>
</div>
<!-- 新增、修改参数面板 -->