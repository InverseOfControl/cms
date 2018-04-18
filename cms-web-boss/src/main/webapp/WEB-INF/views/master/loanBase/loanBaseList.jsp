<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/master/loanBase/loanBaseList.js"></script>

<div id="queryLoanBaseDiv" class="easyui-panel W100"
	data-options="collapsible:true">
	<form id="manualDispatch_queryForm" class="margin_20">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td>借款编号:<input type="text" class="easyui-textbox input"
					name="loanNo"></td>
				<td>申请件编号：<input type="text" class="easyui-textbox input"
					name="appNo"></td>
				<td>姓名：<input type="text" class="easyui-textbox input"
					name="name"></td>
				<td>身份证号：<input type="text" class="easyui-textbox input"
					name="idNo"></td>
			</tr>
			<tr>
			   <td>录单门店 : <input type="text" class="easyui-textbox input" name="owningBranch"></td>
			   <td>签约营业部 : <input type="text" class="easyui-textbox input" name="contractBranch"></td>
			   <td>客服CODE : <input type="text" class="easyui-textbox input" name="serviceCode"></td>
			   <td>客服名称 : <input type="text" class="easyui-textbox input" name="serviceName"></td>
			</tr>
			<tr>
			<td colspan="4">创建日期：<input id="startDate" name="startDate" type="text" class="easyui-datebox"  style="width: 180px" />&nbsp;-&nbsp;
			<input id="endDate" name="endDate" type="text" class="easyui-datebox"  style="width: 180px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			VERSION区间:<input id="startVersion" name="startVersion" type="text" class="easyui-textbox input"  style="width: 180px" >&nbsp;-&nbsp;
			<input id="endVersion" name="endVersion" type="text" class="easyui-textbox input"  style="width: 180px" >
			</td>
			
			</tr>
		<tr>	
		 <td colspan="1">申请件状态: <input id="status" name="status" ></td>  
		 <td colspan="1">是否APP进件: 
	 		 <select class="easyui-combobox"
				id="appInputFlag" name="appInputFlag" style="width: 180px;">
				    <option value="0">全部</option>
					<option value="1">APP进件</option>
					<option value="2">普通进件</option>
				</select> </td> 
				<td colspan="3">
				<a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSLoanBaseInfo();">
					<span style="font-size: 12px">搜&nbsp;索</span>
				</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="loanBasePage" title="申请信息表查询"
	style="height: 92px;">
	<table id="new_loanBaseDatagrid" toolbar="#toolbar"></table>
</div>



