<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/common/head.jsp" %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<link rel="stylesheet"
	href="${ctx}/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/color.css">
<link rel="stylesheet"
	href="${ctx}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/resources/css/style.css">
<script type="text/javascript"
	src="${ctx}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/extendsEasyui.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/moment.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/master/productAuditLimit/productAuditLimit.js"></script>
<%-- <script type="text/javascript"
 src="${ctx}/resources/js/master/productAuditLimit/productCodeModule.js"></script> --%>
<style type="text/css">
.switchbutton-on { background: #EAF2FF; color: #000000; }  /*开时的样式*/  
.switchbutton-off { background: #E1E1E1; color: #000000; } /*关时的样式*/  
.clear { clear: both !important; }  
</style>
<script type="text/javascript">
   /*  $(function(){
        $('#isDisabled').switchbutton({
            checked: true,
            onChange: function(checked){
                alert(checked);
            }
        });
    }) */
</script>
<title>具体产品期限配置</title>
</head>
<body>
	<div id="queryProductAuditLimitDiv" class="easyui-panel W100%"
		data-options="collapsible:true">
		<input type="hidden" name="productId" id="productId"
			value="${productId}" />
		<input type="hidden" name="productCode" id="productCode"
			value="${code}" />
		<input type="hidden" name="productEnumCodeList" id="productEnumCodeList"
			value="${productEnumCodeList}" />
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<th>
				<td style="font-size: 25px">产品代码:${code}</td>
				</th>
				<th>
				<td style="font-size: 25px">产品名称:${name}</td>
				</th>
			</tr>
			<tr>
				<td colspan="3">
				<a href="#" class="easyui-linkbutton " id="addOprate" iconCls="icon-add" onclick="addBMSProductAuditLimitInfo()">
					<span style="font-size: 12px">新&nbsp;增</span>
				</a>
				<!-- onclick="addBMSProductAuditLimitInfo() -->
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsIndex" class="easyui-tabs"
        data-options="fit:true,border:0" style="width: 200px;">
		<div title="期限" style="padding: 10px; width: 200px;">
			<div id="productAuditLimitPage" title="产品期限查询"
				style="height: 92px; padding-top: 8px;">
				<table id="new_datagrid" toolbar="#toolbar"></table>
			</div>
		</div>
    	<!-- -------------------------模块配置START----------------------------------- -->
		<div title="模块"  id="productCodeModulePage" style="padding: 10px;width: 200px;" >
		<div id="base" class="">
	
	 <div style="width:1200px;height:500px;border:1px solid #95b8e7" id="productCodeModuleBody" >
	 
	 <div style="overflow:scroll;width:1158px;height:400px;border:1px solid #95b8e7;padding:20px; " id="productCodeModuleList" >
	 
	 <form id="productCodeModuleForm" name="productCodeModuleForm" >
	 <div id="paramDiv"  >
	 <input type="hidden" name="productId" id="productId" value="${productId}" />
	  <input type="hidden" name="idVal" id="idVal" value="1" />
	 <c:forEach items="${productModuleCodeList}" var="productModuleCode">
		 <input type="hidden" name="initProducts" id="initProducts" value="${productModuleCode.codeId}|${productModuleCode.id}_${productModuleCode.version}" />
	 </c:forEach>
	</div>
	 <c:forEach items="${productModuleCodeList}" var="proEnumCode">
	 <div  class="selectDiv">
	 <select name="productCodeIds" style="width:250px;height:30px;border:1px solid #95b8e7 "  >
	 	<option style="height:30px;border:1px solid #95b8e7" value="${proEnumCode.codeId}" selected="selected">${proEnumCode.nameCN}</option>
	 </select>
	 <input type="hidden" name="productModuleIds" id="productModuleIds" value="${proEnumCode.id}" />
	 <a href="#" class="easyui-linkbutton"  iconCls="icon-remove" onclick="deleteEnumCode(this)">
	 	<span style="font-size: 12px"></span>
	 </a><br>
	</div>
	 </c:forEach>
	 </form>
	 </div>
	<div id="operateDiv" style="padding-left:500px;padding-top: 10px ">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="saveProductModuleCode()">保&nbsp;存</a>
	</div>
	 <div id ="moduleEnumCodeDiv" class="selectDiv" style="display: none">
	 <select id ="addModuleCodes" name="productCodeIds"   style="width:250px;height:30px;border:1px solid #95b8e7 " >
	 <option value=""></option>
	  <c:forEach items="${productEnumCodeList}" var="modEnumCode">
	 	<option style="height:30px;border:1px solid #95b8e7" value="${modEnumCode.codeId}">${modEnumCode.nameCN}</option>
	 </c:forEach>
	 </select>
	 <a href="#" class="easyui-linkbutton c6"  onclick="deleteEnumCode(this)"><i class="fa fa-minus"></i></a><br>
	</div>
	</div>
    </div> 
	<!-- -------------------------模块配置END----------------------------------- -->
		</div>
	</div>

	<div id="addBMSProductAuditLimitInfo" title="新增产品期限"
		class="easyui-window" class="easyui-window" closed="true"
		style="width: 1100px; height: 600px;">
		<div style="padding: 10px 10px 10px 10px">
			<form id="addBMSProductAuditLimitInfoForm"
				name="addBMSProductAuditLimitInfoForm" method="post"
				enctype="multipart/form-data"
				style="background: #FFFFFF; padding-left: 10px; text-align: left;">
				<input type="hidden" name="productId" />
				<input type="hidden" name="productCode" />
				<input type="hidden" name="iState" id="iState" value="${iState}" />
				<table class="table_ui W80 center_m">
					<tr>
						<th>产品名称:</th>
						<td>${code}-${name}</td>
					</tr>
					<tr>
						<th>产品期限:</th>
						<td><input type="text" style="height: 30px" class="input easyui-textbox"
							name="auditLimit" ></td>

						<th>审批额度上限(元):</th>
						<td><input type="text" style="height: 30px" class="input easyui-numberbox"
							name="upperLimit" data-options="min:0,precision:2"></td>
					</tr>
					<tr>
						<th>审批额度下限(元):</th>
						<td><input type="text" style="height: 30px" class="input easyui-numberbox"
							name="floorLimit" data-options="min:0,precision:2"></td>
						<th>是否启用:</th>
						<td><input class="easyui-switchbutton" id="isDisabled"
							name="isDisabled" data-options="onText:'开',offText:'关'"></td>
					</tr>
				</table>
			</form>
			<br />
			<div style="text-align: center; padding: 5px">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					onclick="saveBMSProductAuditLimitInfo();">确认</a> <a class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="closeWindow();">返回</a>
			</div>
		</div>
	</div>
	<div id="updateBMSProductAuditLimitInfo" title="修改产品期限信息"
		class="easyui-window" class="easyui-window" closed="true"
		style="width: 1100px; height: 600px;">
		<div style="padding: 10px 10px 10px 10px">
			<form id="updateBMSProductAuditLimitInfoForm"
				name="updateBMSProductAuditLimitInfoForm" method="post"
				enctype="multipart/form-data"
				style="background: #FFFFFF; padding-left: 10px; text-align: left;">
				<input type="hidden" name="auditLimitId" />
				<table class="table_ui W80 center_m">
					<tr>
						<th>产品名称:</th>
						<td>${code}-${name}</td>
					</tr>
					<tr>
						<th>产品期限:</th>
						<td><input type="text" style="height: 30px" class="input easyui-textbox"
							name="auditLimit" id="auditLimit"></td>

						<th>审批额度上限(元):</th>
						<td><input type="text" style="height: 30px" class="input easyui-numberbox"
							name="upperLimit" id="upperLimit" data-options="min:0,precision:2"></td>
					</tr>
					<tr>
						<th>审批额度下限(元):</th>
						<td><input type="text" style="height: 30px" class="input easyui-numberbox"
							name="floorLimit" id="floorLimit" data-options="min:0,precision:2"></td>
						<th>是否启用:</th>
						<td><input class="easyui-switchbutton" id="isDisabled"
							name="isDisabled" onText="开" offText="关"></td>
					</tr>
				</table>
			</form>
			<br />
			<div style="text-align: center; padding: 5px">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					onclick="saveUpdateBMSProductAuditLimitInfo();">确认</a>
					 <a class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="closeUpdateWindow();">返回</a>
			</div>
		</div>
	</div>
</body>
</html>