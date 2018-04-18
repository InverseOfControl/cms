<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/limitChannel/limitChannelList.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<div id="queryChannelListDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				
				<td>渠道ID：<input type="text" class="easyui-textbox input"
					name="channelId"></td>
					<td><a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSLimitChannel();"><span style="font-size: 12px">搜&nbsp;索</span></a> 
					
				  </td>
			</tr>
			<!-- <tr>
				<td colspan="3"><a class="easyui-linkbutton c6"
					onclick="queryBMSLimitChannel();"><i class="fa fa-search"
					aria-hidden="true"></i>搜&nbsp;索</a></td>
			</tr> -->
		</table>
	</form>
</div>
<div id="appPersonPage" title="产品期限渠道表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_limitChannelDatagrid" toolbar="#toolbar"></table>
</div>



