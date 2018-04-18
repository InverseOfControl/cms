<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/loanExt/loanExtList.js"></script>

<div id="queryLoanExtDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td>借款编号:<input type="text" class="easyui-textbox input"
					name="loanNo"></td>
				<td>申请件编号：<input type="text" class="easyui-textbox input"
					name="appNo"></td>
					<td>LOAN_BASE_ID：<input type="text" class="easyui-numberbox input"
					name="loanBaseId"></td>
				<!-- <td>姓名：<input type="text" class="easyui-textbox input"
					name="name"></td>
				<td>身份证号：<input type="text" class="easyui-textbox input"
					name="idNo"></td> -->
			</tr>
			<tr>
				<td colspan="3">
				<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSLoanExtInfo();">
					<span style="font-size: 12px">搜&nbsp;索</span>
				</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="loanExtPage" title="申请信息表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_loanExtDatagrid" toolbar="#toolbar"></table>
</div>



