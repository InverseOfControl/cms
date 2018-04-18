<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/redisManager/redisManagerList.js"></script>
<div id="redisManagerDiv" class="easyui-panel W100"
	data-options="collapsible:true" style="height: 100px;">
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr align="left">
			<td width="300px"><span>Key名称：</span> <input id="name"
				class="easyui-textbox input" type="text" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<a id="searchBt" class="easyui-linkbutton" iconCls="icon-search">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="removeRedisKeys()" href="#">
				<span style="font-size: 12px">删&nbsp;除</span>
			</a>
			</td>
		</tr>
	</table>
</div>
<div id="redisManagerPage" title="redis缓存查询" style="padding-top: 10px;">
	<table id="redisManagerDatagrid" toolbar="#redisManagerDiv"></table>
</div>
