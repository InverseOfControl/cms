<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/common/channel.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/batch/batchLoan.js"></script>

<div id="queryLoanDiv" class="easyui-panel W100"
	data-options="collapsible:true" style="background-color: #EBEEF3">
	<form id="conditionForm" name="conditionForm" class="">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr>
				<th>渠道名称:</th>
				<td><input id="channelId" class="easyui-combobox"
					name="channelId"
					data-options="valueField:'code',textField:'name',url:'loanConfirm/findChannel'">
					<input type="hidden" name="batchNum" id="batchNum" value="${batchNum}" />
					<input type="hidden" name="createTime" id="createTime" value="${createTime}" /> </</td>
				</td>
				<th>批次号:</th>
				<td><input type="text" class="easyui-textbox input"
					id="batchNum" name="batchNum" style="width: 180px" /></td>
			</tr>
		</table>
	</form>

	<br />
	<div style="text-align: left; padding: 5px">
		<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
			onclick="channel.queryTbl('conditionForm','tblBatchLoan','');">查询</a>
		<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
			onclick="">附件批量下载</a> <a class="easyui-linkbutton" iconCls=""
			id="sercBtn" onclick="updBatch();">批次更新</a>
	</div>
	<span id="moneyTip"></span>
</div>
<span id="moneyTip"></span><!-- onCheck:channel.moneyTip,onUncheck:channel.moneyTip,onCheckAll:channel.moneyTip,onUncheckAll:channel.moneyTip -->
<div title="待办任务" id="unDoneloanAudit_Page" data-options="region:''"
	style="height: 70%; border: 1px solid #95b8e7;">
	<table id="tblBatchLoan" class="easyui-datagrid"
		style="width: 100%; height: 100%;"
		data-options="rownumbers:true,singleSelect:false,pagination:true,selectOnCheck:false,checkOnSelect:false,fitColumns:true,onLoadSuccess:LoadSuccess,loader:channel.datagrid.loader,url:'batchMang/listLoanInfoPage?queryType=02&&batchNum=${batchNum}',loadMsg:'数据加载中.....',method:'post'">
		<thead>
			<tr>
				<th field="tit" id="htitle" data-options="formatter:operateCheck" align="center"><input name="dwn-head" id="head" type="checkbox"></input></th>
				<th field="name" width="8%" align="center">姓名</th>
				<th field="idNo" width="10%" align="center">证件号码</th>
				<th field="loanNo" width="10%" align="center">借款编号</th>
				<th field="productName" width="8%" align="center">产品类型</th>
				<th field="rateey" width="6%" align="center">利率</th>
				<th field="time" width="6%" align="center">期限</th>
				<th field="pactMoney" width="9%" align="center">合同金额</th>
				<th field="grantMoney" width="9%" align="center">放款金额</th>
				<th field="startRDateForT1" width="10%" align="center">首期还款日</th>
				<th field="creditAduit" width="6%" align="center"
					data-options="formatter:operateAduit"><input
					type="checkbox" name="dwn-aduit" id="aduit" style="azimuth: center; vertical-align: middle;"></input>&nbsp;附件下载</th>
				<th field="contract" width="6%" align="center"
					data-options="formatter:operateContract"><input
					type="checkbox" name="dwn-contract" id="contract" style="azimuth: center; vertical-align: middle;">&nbsp;附件下载</th>
				<th field="credit"  width="6%" align="center"
					data-options="formatter:operateDwnCredit"><input
					type="checkbox" name="dwn-credit" id="credit" style="azimuth: center; vertical-align: middle;">&nbsp;附件下载</th>
					
			</tr>
		</thead>
	</table>
</div>

</body>

</html>
