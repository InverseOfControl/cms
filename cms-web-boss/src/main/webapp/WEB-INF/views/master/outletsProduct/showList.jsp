<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/resources/js/common/easyuiUtils/comboboxUtils.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/outletsProduct/comboboxList.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/outletsProduct/showList.js"></script>
<style type="text/css">
.datagrid-toolbar {
	height: 56px;
}

#ff table td {
	border-bottom: 1px dashed #BFC5C5;
	border-left: #dfe4e5;
	border-right: #dfe4e5;
	height: 36px;
	line-height: 18px;
	width: 110px
}

#ff table tr td:nth-child(odd) {
	background: #f1f5f9;
	padding-right: 10px;
	text-align: left;
	text-indent: 1em;
	width: 120px
}

#ff table tr td:nth-child(even) {
	background: #FFFFFF;
}
.switchbutton-on { background: #EAF2FF; color: #000000; }  /*开时的样式*/  
.switchbutton-off { background: #E1E1E1; color: #000000; } /*关时的样式*/  
.clear { clear: both !important; }  
</style>
<div class="easyui-layout" style="width: 100%; height: 100%;">
	<!-- toobar -->
	<div id="org_cha_pro_toolbar" style="height: 150px;">
		<form id="org_cha_pro_queryForm" name="queryForm">
			<table style="border-collapse: separate; border-spacing: 15px;">
				<tr>
					<td><span style="margin-left: 10px;margin-right: 10px">网点名称:</span><input id="org_cha_pro" name="orgId" /></td>
					<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input id="org_cha_pro_channelId" name="channelId" /></td>
					<td><span style="margin-left: 10px;margin-right: 10px">产品名称:</span><input id="org_cha_pro_productId" name="productId" /></td>
					<td><span style="margin-left: 10px;margin-right: 10px">产品期限:</span><input id="org_cha_pro_auditLimit" name="auditLimit"></td>
					
				</tr>
				<tr>
					<td><span style="margin-left: 10px;margin-right: 10px">是否启用:</span> <input id="org_cha_pro_isDisabled" name="isDisabled" />
					<!-- <input id="abledOnQuery" type="radio" value="0" name="isDisabled" selectValue="0" onclick="setSelectDisabledOnQuery(this);"></input>是 &nbsp;&nbsp; &nbsp;&nbsp;
					<input id="disabledOnQuery" type="radio" value="1" name="isDisabled" selectValue="0" onclick="setSelectDisabledOnQuery(this);"></input>否 -->
					</td>
					<td colspan="3"><span style="margin-left: 10px;margin-right: 10px">配置冲突:</span><input id="configure" name="configure" /></td>
				</tr>
				<tr>
			        <td colspan="4">
			        <a class="easyui-linkbutton" iconCls="icon-search" onclick="org_query_cha_pro_Info();" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">查&nbsp;询</span>
					</a> 
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="busi_org_cha_pro_Info()" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">网点配置</span>
					</a> 
					
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="busi_org_cha_pro_rate_Info()" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">网点优惠配置</span>
					</a> 
					
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="busi_pro_org_cha_Info()" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">产品配置</span>
					</a>
					
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="busi_pro_org_cha_rate_Info()" style="margin-left: 10px;margin-right: 10px">
					<span style="font-size: 12px">产品优惠配置</span>
					</a> 
			        </td>
		        </tr>
			</table>
		</form>
	</div>
	<div data-options="region:'north'" style="height: 100%;">
		<table id="org_cha_pro_datagrid" toolbar="#org_cha_pro_toolbar"
			style="width: auto;"></table>
	</div>
</div>
<div id="orgproductAuditInfo" title="修改网点录单产品配置信息" class="easyui-window"
	closed="true" style="width: 400px; height: 280px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<form id="org_limit_form">
			<div data-options="region:'center'"
				style="height: 200px; text-align: center">
				<table
					style="width: 100%; height: 60%; margin-left: auto; padding-top: 20px;">
					<tr>
						<td><span style="color: red">*</span>审批额度下限（元）：<input
							class="easyui-validatebox" validType="integerCheck"
							name="floorLimit" /></td>
						<td><input type="hidden" name="oldFloorLimit" /></td>
						<td><input type="hidden" name="oldUpperLimit" /></td>
						<td><input type="hidden" name="id" /></td>
						<td><input type="hidden" name="productId" /></td>
						<td><input type="hidden" name="channelId" /></td>
						<td><input type="hidden" name="auditLimitId" /></td>
						<td><input type="hidden" name="proFloorLimit" /></td>
						<td><input type="hidden" name="proUpperLimit" /></td>
						<td><input type="hidden" name="proAdjustBase" /></td>
					</tr>
					<tr>
						<td><span style="color: red">*</span>审批额度上限（元）：<input
							class="easyui-validatebox" validType="integerCheck"
							name="upperLimit" /></td>
					</tr>
					<tr>
						<td>
							<span style="color: red">*</span>是否启用：&nbsp;&nbsp; &nbsp;&nbsp; 
							<input type="hidden" name="isDisabled" id="isDisabled" value="1" />
							<input class="easyui-switchbutton" id="iState" name="iState" onText="开" offText="关">
							<!-- <input type="radio" value="0" name="isDisabled" id="org_limit_form_abled"></input>是&nbsp;&nbsp; &nbsp;&nbsp;
							<input type="radio" value="1" name="isDisabled" id="org_limit_form_disabled"></input>否 -->
						</td>
					</tr>
				</table>
			</div>
			<div data-options="region:'south',iconCls:'icon-reload'"
				style="height: 40px; text-align: center">
				<div style="width: 100%; height: 100%; margin-left: auto;">
					<a class="easyui-linkbutton" onclick="add_out_pro_Info();"
						iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
						class="easyui-linkbutton" onclick="cancel_out_pro_Info()"
						iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
				</div>
			</div>
		</form>
	</div>
</div>
<!--网点录单产品配置  -->
<div id="busi_orgChannelProductInfo" title="网点录单产品配置"
	class="easyui-window" closed="true"
	style="width: 1100px; height: 600px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',split:true" style="width: 50%;">
			<ul id="busi_orgTree"></ul>
		</div>
		<div data-options="region:'east',iconCls:'icon-reload'"
			style="width: 50%;">
			<ul id="busi_channelProTree"></ul>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'"
			style="height: 20%;">
			<div style="margin: 0 auto; width: 180px; height: 100px;">
				<a class="easyui-linkbutton" onclick="busi_add_cha_pro_Info();"
					iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
					class="easyui-linkbutton" onclick="busi_cancel_cha_pro_Info()"
					iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
			</div>
		</div>

	</div>
</div>


<!--网点录单产品优惠费率配置  -->
<div id="busi_rate_orgChannelProductInfo" title="网点录单产品优惠费率配置"
	class="easyui-window" closed="true"
	style="width: 1100px; height: 600px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',split:true" style="width: 50%;">
			<ul id="busi_rate_orgTree"></ul>
		</div>
		<div data-options="region:'east',iconCls:'icon-reload'"
			style="width: 50%;">
			<ul id="busi_rate_channelProTree"></ul>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'"
			style="height: 20%;">
			<div style="margin: 0 auto; width: 180px; height: 100px;">
				<a class="easyui-linkbutton" onclick="busi_add_cha_pro_rate_Info();"
					iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
					class="easyui-linkbutton" onclick="busi_cancel_cha_pro_rate_Info()"
					iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
			</div>
		</div>

	</div>
</div>

<!-- 产品网点录单配置 -->
<div id="busi_orgProductInfo" title="产品网点录单配置" class="easyui-window"
	closed="true" style="width: 1100px; height: 600px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',split:true" style="width: 50%;">
			<ul id="busi_org_productTree"></ul>
		</div>
		<div data-options="region:'east',iconCls:'icon-reload'"
			style="width: 50%;">
			<ul id="busi_orgProTree"></ul>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'"
			style="height: 20%;">
			<div style="margin: 0 auto; width: 180px; height: 100px;">
				<a class="easyui-linkbutton" onclick="busi_org_add_cha_pro_Info();"
					iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
					class="easyui-linkbutton" onclick="busi_org_cancel_cha_pro_Info()"
					iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
			</div>
		</div>

	</div>
</div>
<!-- 录单产品网点优惠费率配置 -->
<div id="busi_rate_orgProductInfo" title="录单产品网点优惠费率配置" class="easyui-window"
	closed="true" style="width: 1100px; height: 600px;">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',split:true" style="width: 50%;">
			<ul id="busi_rate_org_productTree"></ul>
		</div>
		<div data-options="region:'east',iconCls:'icon-reload'"
			style="width: 50%;">
			<ul id="busi_rate_orgProTree"></ul>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'"
			style="height: 20%;">
			<div style="margin: 0 auto; width: 180px; height: 100px;">
				<a class="easyui-linkbutton" onclick="busi_org_add_cha_pro_rate_Info();"
					iconCls="icon-add"><span style="font-size: 12px">确定</span></a> <a
					class="easyui-linkbutton" onclick="busi_org_cancel_cha_pro_rate_Info()"
					iconCls="icon-cancel"><span style="font-size: 12px">返回</span></a>
			</div>
		</div>

	</div>
</div>
<!-- 修改网点录单产品配置信息确认框 -->
<div id="orgproductAuditInfoConfirmWindow" class="easyui-window" title="修改网点录单产品配置信息"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="orgproductAuditInfoConfirmMessage">确定修改网点录单产品配置信息?</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="orgproductAuditInfoSave();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="orgproductAuditInfoCancle();">返回</a>
		</div>
	</div>
</div>
<!-- 网点-录单产品配置确认框 -->
<div id="busi_orgChannelProductInfoConfirmWindow" class="easyui-window" title="网点录单产品配置"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="busi_orgChannelProductInfoMessage">确定保存网点录单产品配置?</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="busi_orgChannelProductInfoSave();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="busi_orgChannelProductInfoCancle();">返回</a>
		</div>
	</div>
</div>


<!-- 网点-录单产品优惠费率配置确认框 -->
<div id="busi_rate_orgChannelProductInfoConfirmWindow" class="easyui-window" title="网点录单产品优惠费率配置"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="busi_rate_orgChannelProductInfoMessage">确定保存网点录单产品配置?</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="busi_rate_orgChannelProductInfoSave();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="busi_rate_orgChannelProductInfoCancle();">返回</a>
		</div>
	</div>
</div>


<!-- 产品-网点配置确认框 -->
<div id="busi_orgProductInfoConfirmWindow" class="easyui-window" title="产品网点录单配置"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="busi_orgProductInfoMessage">确定保存产品网点录单配置?</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="busi_orgProductInfoSave();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="busi_orgProductInfoCancle();">返回</a>
		</div>
	</div>
</div>

<!-- 产品-网点配置确认框(优惠费率) -->
<div id="busi_rate_orgProductInfoConfirmWindow" class="easyui-window" title="产品网点录单配置"
	align="center" closed="true" style="top: 350px; height: 230px; width: 450px; overflow: hidden;"
	data-options="collapsible:false,minimizable:false,maximizable:false, resizable:false,draggable:false,modal:true">
	<div style="padding: 10px 10px 10px 10px; width: 450px;">
		<div class="easyui-layout"
			style="padding-right: 40px; padding-left: 10px; height: 120px">
			<span style="font-size: 14px" id="busi_rate_orgProductInfoMessage">确定保存产品网点录单配置?</span>
		</div>
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="busi_rate_orgProductInfoSave();">确定</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="busi_rate_orgProductInfoCancle();">返回</a>
		</div>
	</div>
</div>
