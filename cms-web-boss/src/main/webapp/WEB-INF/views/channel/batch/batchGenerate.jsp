<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/common/channel.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/batch/batchCrt.js"></script>

<div id="queryLoanDiv" class="easyui-panel W100"
	data-options="collapsible:true" style="background-color: #EBEEF3">
	<form id="conditionForm" name="conditionForm" class="">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr>
				<th>渠道名称:</th>
				<td><input id="channelId" class="easyui-combobox"
					name="channelId"
					data-options="valueField:'code',textField:'name',url:'loanConfirm/findChannel'"></</td>
				<th>放款审核通过时间:</th>
				<td><input type="text" class="easyui-datebox input"
					id="begDate" name="accDateStart" style="width: 180px" />~<input
					type="text" class="easyui-datebox input" id="endDate"
					name="accDateEnd" style="width: 180px" /></td>
			</tr>
		</table>
	</form>

	<br />
	<div style="text-align: left; padding: 5px">
		<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
			onclick="channel.queryTbl('conditionForm','tblBatchGenert','validateDate');">查询</a>
		<a class="easyui-linkbutton" iconCls="" id="sercBtn"
			onclick="gener_batchNum();">生成批次号</a>
	</div>
	<span id="moneyTip"></span>
</div>
<div title="待办任务" id="unDoneloanAudit_Page" data-options="region:''"
	style="height: 70%; border: 1px solid #95b8e7;">
	<table id="tblBatchGenert" class="easyui-datagrid"
		style="width: 100%; height: 100%;"
		data-options="rownumbers:true,singleSelect:false,pagination:true,selectOnCheck:true,fitColumns:true,onLoadSuccess:channel.gridLoadSuccess,loader:channel.datagrid.loader,onSelect:channel.moneyTip,onUnselect:channel.moneyTip,onSelectAll:channel.moneyTip,onUnselectAll:channel.moneyTip,url:'batchMang/listLoanInfoPage?queryType=01',loadMsg:'数据加载中.....',method:'post'">
		<thead>
			<tr>
				<th field="" align="center" checkbox="true">全选</th>
				<th field="loanBaseId" hidden="true" align="center"></th>
				<th field="name" width="10%" align="center">姓名</th>
				<th field="idNo" width="13%" align="center">证件号码</th>
				<th field="loanNo" width="13%" align="center">借款编号</th>
				<th field="productName" width="10%" align="center">产品类型</th>
				<th field="rateey" width="10%" align="center">利率</th>
				<th field="time" width="10%" align="center">期限</th>
				<th field="pactMoney" width="10%" align="center">合同金额</th>
				<th field="grantMoney" width="10%" align="center">放款金额</th>
				<th field="startRDateForT1" width="10%" align="center">首期还款日</th>
			</tr>
		</thead>
	</table>
</div>

</body>

</html>
