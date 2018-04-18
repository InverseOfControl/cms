<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/debtRadio/debtRadioManage.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<div id="debtRadioPage" title="客户类型负债率"
	style="height: 92px; padding-top: 8px;">
	<table id="new_datagrid_DateRadio" toolbar="#toolbar"></table>
</div>
<div id="panelBMSDebtRadionManageInfo" title="负债率修改" class="easyui-window" class="easyui-window" closed="true" style="width:200px;height:200px;">
	<div style="padding: 10px 10px 10px 10px;">
		<form id="updateDebtRadionManageInfoForm" method="post"
			enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W100 center_m">
				<tr>
					<th>客户类型:<span style="color: red;">*</span>:</th>
				<td>
		    	<input class="easyui-textbox input" id="edit_customerTypeName" style="width: 200px"  disabled="disabled"/>
			</td>
			</tr>
			<tr>
					<th>总负债率(%):<span style="color: red;">*</span>:</th>
					<td>
					<input class="easyui-textbox input" id="edit_totalDebtRadio" style="width: 200px"  name="totalDebtRadio"/>
					</td>
				  </tr>
				<tr>
					<th>内部负债率(%):<span style="color: red;">*</span>:</th>
					<td>
					<input class="easyui-textbox input" id="edit_internalDebtRadio" style="width: 200px"  name="internalDebtRadio"/>
					</td>
				</tr>
				<tr style="display: none;">
					<td>
					<input type="text" class="easyui-textbox input" id="editid">
						
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSDebtRadioInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="debt_closeWindow();">取消</a>
		</div>
	</div>
</div>


