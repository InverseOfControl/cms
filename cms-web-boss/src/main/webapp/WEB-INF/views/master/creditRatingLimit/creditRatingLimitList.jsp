<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/creditRatingLimit/creditRatingLimitList.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script> --%>
<div id="queryCreditRatingLimitDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<form id="creditRatingLimit_queryForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">征信等级:</span><input class="easyui-combobox" style="width:180px;" id="creditRating"  data-options="valueField:'value',textField:'label',
					data: [{label: '白户/无综合信用',value: '1'},{label: '当前逾期造成信用不良',value: '2'},{label: '非当前逾期造成信用不良',value: '3'}]" /></td>
			</tr>
			<tr>
				<td colspan="3">
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryCreditRatingLimitInfo();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
          		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addCreditRatingLimitInfoWindow()" style="margin-left: 10px;margin-right: 10px">
          			<span style="font-size: 12px">新&nbsp;建</span>
          		 </a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="creditRatingLimitPage" title="征信查询" style="height: 92px; padding-top: 8px;">
	<table id="new_datagrid_creditRatingLimit" toolbar="#queryCreditRatingLimitDiv"></table>
</div>

<div id="addCreditRatingLimitInfo" title="新增征信信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 800px; height: 400px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="addCreditRatingLimitInfoForm" name="addBMSBankInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W80 center_m">
				<tr>
					<th>征信等级:</th>
					<td><input class="easyui-combobox" style="width:180px;" id="addCreditRating"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '白户/无综合信用',value: '1'},{label: '当前逾期造成信用不良',value: '2'},{label: '非当前逾期造成信用不良',value: '3'}]" /></td>
				</tr>
				<tr>
					<th>申请类型:</th>
					<td><input class="easyui-combobox" style="width:180px;" id="addCustomerType"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: 'RELOAN',value: 'RELOAN'},{label: 'NEW',value: 'NEW'},{label: 'TOPUP',value: 'TOPUP'}]" /></td>
				</tr>
				<tr>
					<th>可选产品:</th>
					<td><input class="easyui-combotree" style="width:180px;height: 33px;" id="addProduct"   data-options="editable:false,multiple:true,url:'creditRatingLimit/findByProductAll'" /></td>
				</tr>
				<tr>
					<th>贷记类型:</th>
					<td><input class="easyui-combobox" style="width:180px;height: 33px;" id="addCreaditType"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '贷款',value: '1'},{label: '贷记卡',value: '2'}]" /></td>
				</tr>
				<tr>
					<th>提示语设置:</th>
					<td>
						<textarea id="addRemark" cols="50" rows="5" style="resize:none;"></textarea>
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
<div id="updateCreditRatingLimitInfo" title="修改征信信息" class="easyui-window"
	class="easyui-window" closed="true"
	style="width: 800px; height: 400px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="updateCreditRatingLimitInfoForm" name="updateBMSBankInfoForm"
			method="post" enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" name="id" id="id"/>
			<table class="table_ui W80 center_m">
				<tr>
					<th>征信等级:</th>
					<td><input class="easyui-combobox" style="width:180px;" id="updateCreditRating"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '白户/无综合信用',value: '1'},{label: '当前逾期造成信用不良',value: '2'},{label: '非当前逾期造成信用不良',value: '3'}]" /></td>
				</tr>
				<tr>
					<th>申请类型:</th>
					<td><input class="easyui-combobox" style="width:180px;" id="updateCustomerType"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: 'RELOAN',value: 'RELOAN'},{label: 'NEW',value: 'NEW'},{label: 'TOPUP',value: 'TOPUP'}]" /></td>
				</tr>
				<tr>
					<th>可选产品:</th>
					<td><input class="easyui-combotree" style="width:180px;height: 33px;" id="updateProduct"   data-options="editable:false,multiple:true,url:'creditRatingLimit/findByProductAll'" /></td>
				</tr>
				<tr>
					<th>贷记类型:</th>
					<td><input class="easyui-combobox" style="width:180px;height: 33px;" id="updateCreaditType"  data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '贷款',value: '1'},{label: '贷记卡',value: '2'}]" /></td>
				</tr>
				<tr>
					<th>提示语设置:</th>
					<td>
						<textarea id="updateRemark" cols="50" rows="5" style="resize:none;"></textarea>
					</td>
				</tr>
				<tr style="display: none;">
					<td>
						<input id="updateId" />
					</td>
				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveUpdateBMSCreditInfo();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closeForm('upd');">取消</a>
				<input type="hidden" name="oldBankCode" id="oldBankCode">
		</div>
	</div>
</div>
