<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/bank/bankList.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script> --%>
<div id="queryBankDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<form id="manualDispatch_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">银行名称:</span><input type="text" class="easyui-textbox input" name="name" ></td>
				<th style="display: none;">银行编码:</th>
				<td style="display: none;"><input type="text" class="easyui-textbox input" name="code"></td>
			</tr>
			<tr>
				<td colspan="3">
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSBankInfo();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
          		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSBankInfo()" style="margin-left: 10px;margin-right: 10px">
          			<span style="font-size: 12px">新&nbsp;建</span>
          		 </a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="bankPage" title="银行查询" style="height: 92px; padding-top: 8px;">
	<table id="new_datagrid_bank" toolbar="#queryBankDiv"></table>
</div>

<div id="addBMSBankInfo" title="新增银行信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 800px; height: 400px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="addBMSBankInfoForm" name="addBMSBankInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W80 center_m">
				<tr>
					<th>银行编码:</th>
					<td><input type="text" class="easyui-textbox input"  id="addCode"
						name="code" width="100px"></td>
				</tr>
				<tr>
					<th>银行名称:</th>
					<td><input type="text" class="easyui-textbox input" id="addName"
						name="name" width="100px"></td>
				</tr>
				<tr>
				<th>是否启用:</th>
					<td> <input type="radio" name="redio" value="0" id="0"/>是
						 <input type="radio" name="redio" value="1" id="1"/>否
					</td>
 				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSBankInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closeForm('add');">取消</a>
		</div>
	</div>
</div>
<div id="updateBMSBankInfo" title="修改银行信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 800px; height: 400px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="updateBMSBankInfoForm" name="updateBMSBankInfoForm"
			method="post" enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" name="id" id="id"/>
			<table class="table_ui W80 center_m">
				<tr>
					<th>银行编码:</th>
					<td><input type="text" class="easyui-textbox input"
						name="code" id="code"></td>
				</tr>
				<tr>
					<th>银行名称:</th>
					<td><input type="text" class="easyui-textbox input"
						name="name" id="name"></td>
					<td><input type="hidden" 
						name="flag" id="flag"></td>
				</tr>
				<tr>
				<th>是否启用:</th>
					<td> <input type="radio" name="editredio" value="0" id="00"/>是
						 <input type="radio" name="editredio" value="1" id="11"/>否
					</td>
 				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveUpdateBMSBankInfo();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeForm('upd');">取消</a>
				<input type="hidden" name="oldBankCode" id="oldBankCode">
		</div>
	</div>
</div>
