<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/orgLimitChannel/orgLimitChannelList.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<div id="queryOrgLimitChannelDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td>门店ID:<input type="text" class="easyui-textbox input"
					name="orgId"></td>
				<td>渠道ID：<input type="text" class="easyui-textbox input"
					name="channelId"></td>
				<!-- <td>产品期限ID：<input type="text" class="easyui-textbox input"
					name="auditLimitId"></td> -->
			</tr>
			<tr>
				<td colspan="3">
				<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSOrgLimitChannelInfo();"><span style="font-size: 12px">搜&nbsp;索</span></a> 
				
				
			</td>
			</tr>
		</table>
	</form>
</div>
<div id="orgLimitChannelPage" title="申请信息表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_OrgLimitChannelDatagrid" toolbar="#toolbar"></table>
</div>



