<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/resources/js/common/easyuiUtils/comboboxUtils.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/channelProduct/comboboxList.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/channelProduct/showList.js"></script>
<style type="text/css">
.datagrid-toolbar {
	height: 56px;
}

#ff table td {
	border-bottom: 1px dashed #BFC5C5;
	border-left: #dfe4e5;
	border-right: #dfe4e5;
	height: 36px;
	line-height: 18px;
	width: 110px
}

#ff table tr td:nth-child(odd) {
	background: #f1f5f9;
	padding-right: 10px;
	text-align: left;
	text-indent: 1em;
	width: 120px
}

#ff table tr td:nth-child(even) {
	background: #FFFFFF;
}
</style>
<div class="easyui-layout" style="width: 100%; height: 100%;">
	<!-- toobar -->
	<div id="cha_pro_toolbar" style="height: 100px;">
		<input type="hidden" id="channelIdRequest" name="channelId" value="${channelId}"></input>
		<form id="cha_pro_queryForm" name="queryForm">
    		<table style="border-collapse:separate; border-spacing:15px;">
				<tr>
					<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input id="cha_pro_channelId" name="channelId" /></td>
					<td><span style="margin-left: 10px;margin-right: 10px">产品名称:</span><input id="cha_pro_productId" name="productId" /></td>
					<td><span style="margin-left: 10px;margin-right: 10px">产品期限:</span><input id="cha_pro_auditLimit" name="auditLimit"></td>
				</tr>
				<tr>
			        <td colspan="3">
			        <a class="easyui-linkbutton" iconCls="icon-search" onclick="query_cha_pro_Info();" style="margin-left: 10px;margin-right: 10px">
						<span style="font-size: 12px">查&nbsp;询</span>
					</a> 
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="deploy_cha_pro_Info()" style="margin-left: 10px;margin-right: 10px">
						<span style="font-size: 12px">配&nbsp;置</span>
					</a>
			        </td>
		        </tr>
			</table>
		</form>
	</div>
	<div id="cha_pro_Page" data-options="region:'north'"
		style="height: 100%;">
		<table id="cha_pro_datagrid" toolbar="#cha_pro_toolbar"
			style="width: auto;"></table>
	</div>
</div>
<div id="deployChannelProductInfo" title="配置渠道产品" class="easyui-window"
	closed="true" style="width: 1100px; height: 600px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',split:true" style="width: 50%;">
			<ul id="channelProCenterTree"></ul>
		</div>
		<div data-options="region:'east',iconCls:'icon-reload'"
			style="width: 50%;">
			<ul id="channelProEastTree"></ul>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'"
			style="height: 20%;">
			<div style="margin: 0 auto; width: 180px; height: 100px;">
				<a class="easyui-linkbutton" onclick="add_cha_pro_Info();"
					iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
					class="easyui-linkbutton" onclick="cancel_cha_pro_Info()"
					iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
			</div>
		</div>

	</div>
</div>
<!-- 配置保存提示  -->
<div id="panelChannelProductSave" class="easyui-window" title="配置保存提示"
	align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="panelChannelProductSaveMessage"></span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				id="panelChannelProductSaveConfirm">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeSaveWindow();">返回</a>
		</div>
	</div>
</div>
<!-- 启用禁用提示  -->
<div id="panelChannelProductIsDisable" class="easyui-window"
	title="启用禁用提示" align="center" closed="true"
	style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px"
				id="panelChannelProductIsDisableMessage"></span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				id="panelChannelProductIsDisableConfirm">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeIsDisableWindow();">返回</a>
		</div>
	</div>
</div>
