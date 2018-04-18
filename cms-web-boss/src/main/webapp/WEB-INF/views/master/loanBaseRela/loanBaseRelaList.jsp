<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/loanBaseRela/loanBaseRelaList.js"></script>

<div id="queryLoanBaseRelaDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td>客户主表ID：<input type="text" class="easyui-numberbox input"
					name="personId"></td>
				<td>LOAN_BASE_ID：<input type="text" class="easyui-numberbox input"
					name="loanBaseId"></td>
			</tr>
			<tr>
				<td colspan="2"><a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSLoanBaseRela();">
					<span style="font-size: 12px">搜&nbsp;索</span>
				</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="loanBaseRelaPage" title="借款申请资产信息表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_loanBaseRelaDatagrid" toolbar="#toolbar"></table>
</div>



