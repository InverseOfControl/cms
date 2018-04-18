<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/sysLogEntity/sysLogEntityList.js"></script>

<div id="querySysLogEntityDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td>系统编码:<input type="text" class="easyui-textbox input"
					name="systemCode"></td>
				<td>二级目录：<input type="text" class="easyui-textbox input"
					name="twoLevelDir"></td> 
				<td>操作模块：<input type="text" class="easyui-textbox input"
					name="optModule"></td>
			</tr>
			<tr>
				<td colspan="3">
				<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSSysLogEntity();">
					<span style="font-size: 12px">搜&nbsp;索</span>
				</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="sysLogEntityPage" title="系统日志表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_sysLogEntityDatagrid" toolbar="#toolbar"></table>
</div>



