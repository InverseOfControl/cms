<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/resources/js/common/easyuiUtils/comboboxUtils.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/product/productList.js"></script>

<div id="queryProductDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
  <form id="manualDispatch_queryForm">
    <table style="border-collapse:separate; border-spacing:15px;">
      <tr align="left">
		 <td><span style="margin-left: 10px;margin-right: 10px">产品名称:</span><input id="pro_productId" name="productId"  class="easyui-textbox input"/></td>
      </tr>
      <tr>
        <td colspan="3">
          <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryBMSProductInfo();" style="margin-left: 10px;margin-right: 10px">
         	 <span style="font-size: 12px">搜&nbsp;索</span>
          </a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSProductInfo()" style="margin-left: 10px;margin-right: 10px">
         	 <span style="font-size: 12px">新&nbsp;建</span>
          </a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteBMSProductInfo()" style="margin-left: 10px;margin-right: 10px">
         	 <span style="font-size: 12px">删&nbsp;除</span>
          </a>
          <!-- <a class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSChannelInfo()"   iconCls="icon-ok">新&nbsp;建</a> -->
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="productPage" title="产品查询" style="height:100% " data-options="region:'north'" >
	<table id="new_productDatagrid" toolbar="#queryProductDiv"></table>
</div>

<div id="addBMSProductInfo" title="新增产品" class="easyui-window" class="easyui-window" closed="true" style="width:800px;height:400px;">
   <div style="padding:10px 10px 10px 10px">
        <form id="addBMSProductInfoForm" name="addBMSProductInfoForm"  method="post" enctype="multipart/form-data" style="background: #FFFFFF; padding-left:10px;text-align:left;">
            <table class="table_ui W80 center_m">
            <tr>
				<th>产品代码:</th>
				<td><input type="text" class="easyui-textbox input" name="code"></td>
				<th>产品名称:</th>
				<td><input type="text" class="easyui-textbox input" name="name"></td>
			</tr>
			<tr>
			    <th>审批额度下限(元):</th>
				<td><input type="text" style="height: 30px"  max="999999999"  class="input easyui-numberbox" name="floorLimit" data-options="min:0,precision:2"></td>
				 <th>审批额度上限(元):</th>
				<td><input type="text" style="height: 30px" max="999999999" class="input easyui-numberbox" name="upperLimit" data-options="min:0,precision:2"></td>
			</tr>
			<tr>
			   <!--  <th>费率:</th>
				<td><input type="text" class="easyui-textbox input" name="rate"></td> -->
				<th>调整基数:</th>
				<td><input type="text" style="height: 30px" max="999999999" class="input easyui-numberbox" name="adjustBase" data-options="min:0,precision:2"></td>
				<th>产品费率(%):</th>
				<td><input type="text" style="height: 30px" max="999999999" class="input easyui-textbox" name="rate"></td>
			</tr>
			<tr>
			   <th>总负债率(%):</th>
			   <td><input type="text" style="height: 30px" max="999999999" class="input easyui-textbox" name="debtRadio"></td>
			   
			   <th>优惠费率(%):</th>
			   <td><input type="text" style="height: 30px" max="999999999" class="input easyui-textbox" name="preferentialRate"></td>
			</tr>
			
             </table>          
       		 </form>
        	<br/>
	        <div style="text-align:center;padding:5px">
	       		<a  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBMSProductInfo();">保存</a>
				<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWindow();">取消</a>
	        </div>
       	  </div>
</div>

<div id="updateBMSProductInfo" title="修改产品信息" class="easyui-window" class="easyui-window" closed="true" style="width:800px;height:400px;">
   <div style="padding:10px 10px 10px 10px">
        <form id="F_updateBMSProductInfoForm" name="updateBMSProductInfoForm"  method="post" enctype="multipart/form-data" style="background: #FFFFFF; padding-left:10px;text-align:left;">
            <input type="hidden" name="productId"/>
            <table class="table_ui W80 center_m">
            <tr>
				<th>产品代码:</th>
				<td><input type="text" class="easyui-textbox input" name="code" id="code" disabled="disabled"></td>
				<th>产品名称:</th>
				<td><input type="text" class="easyui-textbox input" name="name" id="name"></td>
			</tr>
			<tr>
			    <th>审批额度下限(元):</th>
				<td><input type="text"  style="width: 100px;height: 30px" data-options="min:0,precision:2" class="easyui-numberbox" max="999999999" size="9" maxlength="9"  name="floorLimit" id="floorLimit" ></td>
				 <th>审批额度上限(元):</th>
				<td><input type="text" style="width: 100px;height: 30px"  data-options="min:0,precision:2" class="easyui-numberbox" max="999999999" size="9" maxlength="9"  name="upperLimit" id="upperLimit" ></td>
			</tr>
			<tr>
				<th>调整基数:</th>
				<td><input type="text" style="width: 100px;height: 30px" data-options="min:0,precision:2" max="999999999"  class="easyui-numberbox" name="adjustBase" id="adjustBase" ></td>
			    <th>产品费率(%):</th>
				<td><input type="text" style="width: 100px;height: 30px" max="999999999" class="input easyui-textbox" name="rate" id="rate" ></td>
			</tr>
			<tr>
			   <th>总负债率(%):</th>
			   <td><input type="text" style="height: 30px" max="999999999" class="input easyui-textbox" name="debtRadio" id="debtRadio" data-options="min:0,precision:2"></td>
			   <th>优惠费率(%):</th>
			   <td><input type="text" style="height: 30px" max="999999999" class="input easyui-textbox" name="preferentialRate" id="preferentialRate" data-options="min:0,precision:2"></td>
			</tr>
			
             </table>          
      </form>
        	<br/>
	        <div style="text-align:center;padding:5px">
	       		<a  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUpdateBMSProductInfo()">保存</a>
				<a  class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeUpdateWindow();">取消</a>
	        </div>
       	  </div>
</div>
