<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/master/lineNumberSet/lineNumberSetList.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script> --%>
<div id="queryLineNumberSetListDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 200px;background-color: #F4F4F4;">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">客户姓名:</span><input type="text" class="easyui-textbox input" id="borrowerName" ></td>
				<td><span style="margin-left: 10px;margin-right: 10px">身份证号码:</span><input type="text" class="easyui-textbox input" id="idNum"></td>
				<td><span style="margin-left: 10px;margin-right: 10px">合同编号:</span><input type="text" class="easyui-textbox input" id="cobtractNum"></td>
			</tr>
			<tr align="left">
				<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span>
					<input class="easyui-combobox" style="width:180px;" id="channelName" value="wmxt" readonly="readonly" data-options="valueField:'value',textField:'label',
					data: [{label: '外贸信托',value: 'wmxt'}]" />
				</td>
				<td><span style="margin-left: 10px;margin-right: 10px">银行卡所属地区:</span><input class="easyui-combobox" style="width:180px;" id="queryAreaType" data-options="valueField:'value',textField:'label',
					data: [{label: '深圳地区',value: '01'},{label: '异地',value: '99'}]" /></td>
			</tr>
			<tr align="left">
				<td>
				 <a class="easyui-linkbutton" iconCls="icon-search" onclick="queryContractInfo();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">查&nbsp;询</span>
				 </a>
				</td>
				<td>
					<span style="margin-left: 10px;margin-right: 10px">银行卡所属地区:</span><input class="easyui-combobox" style="width:180px;" id="areaType" data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '深圳地区',value: '01'},{label: '异地',value: '99'}]" />
				</td>
				<td>	
					<a class="easyui-linkbutton"  onclick="setClear();" style="margin-right: 10px">
				 	<span style="font-size: 12px">&nbsp;重置&nbsp;</span>
				 	</a>
					<a class="easyui-linkbutton"  onclick="setNumberLine();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">&nbsp;配置行别行号&nbsp;</span>
				 	</a>
					<a class="easyui-linkbutton"  onclick="showWindow();" style="margin-left: 10px;margin-right: 10px">
				 	<span style="font-size: 12px">&nbsp;更新模板&nbsp;</span>
				 	</a>
				</td>
			</tr>
		</table>
</div>
<div id="contractPage" title="查询生成合同列表" style="height: 92px; padding-top: 8px;">
	<table id="new_datagrid_contract" toolbar="#queryLineNumberSetListDiv"></table>
</div>


<div id="productDataImport" class="easyui-window" title="一键导入" data-options="top:'80px',modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
          <form id="productDataImportInfoForm" name="productDataImportInfoForm"  Method="post" enctype="multipart/form-data" style="background: #FFFFFF; padding-left:10px;text-align:left;">
        <div style="margin-bottom:20px;">
           <input id="productUploadfile" name="productUploadfile" class="easyui-filebox" data-options="prompt:'请选择一个excel文件'" style="width:100%;">
          
        </div>
        <div>
        	<span>文件类型:</span><input class="easyui-combobox" style="width:180px;" id="importExcelAreaType" data-options="editable:false,valueField:'value',textField:'label',
					data: [{label: '深圳地区',value: '01'},{label: '异地',value: '99'}]" />
        </div>
        <div align="right" style="margin-top: 10px;">
            <a id="addBtn" class="easyui-linkbutton" style="width:100%" onclick="ajaxFileUpload();">上  传</a>
        </div>
        </form>
        
</div>

