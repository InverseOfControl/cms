<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/patchBolt/patchBoltList.js"></script>
<div id="queryBankDiv" class="easyui-panel W80"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<th>客户名称:</th>
				<td><input type="text" class="easyui-textbox input" name="customerName" id="customerName"></td>
				<th>身份证号:</th>
				<td><input type="text" class="easyui-textbox input" name="customerIDNO" id="customerIDNO"></td>
				<td colspan="10">
					<div style="text-align: center; padding: 5px">
						 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSPatchBoltInfo();" >
		 					<span style="font-size: 12px">搜&nbsp;索</span>
						 </a>
            &nbsp; &nbsp;
					</div>
				</td>
			</tr>
		</table>
		<input type="hidden" id='loginCode' value="${currentAccount}">
	</form>
</div>
<div id="bankPage" title="补件查询"  data-options="region:'north'" style="height:85%;">
	<table id="new_datagrid_patchBolt" toolbar="#toolbar"></table>
</div>
<div id="hovertreewindow"  title="补件信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 1100px; height: 600px;">
	
</div>
</div>
