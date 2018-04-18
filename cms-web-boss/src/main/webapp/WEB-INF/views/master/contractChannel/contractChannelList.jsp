<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/contractChannel/contractChannelList.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>	
<div id="queryContractChannelDiv" class="easyui-panel W80"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<th>渠道ID:</th>
				<td><input type="text" class="easyui-textbox input" name="channelId" ></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" onclick="queryContractChannelInfo();"><span style="font-size: 12px">搜&nbsp;索</span></a> </td>
				
			</tr>
			
		</table>
	</form>
</div>
<div id="bankPage" title="渠道合同表" style="height: 92px; padding-top: 8px;">
	<table id="new_contractChannel_Datagrid" toolbar="#toolbar"></table>
</div>



