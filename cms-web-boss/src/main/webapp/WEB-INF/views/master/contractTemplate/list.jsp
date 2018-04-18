<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript"
	src="${ctx}/resources/js/common/jqueryform.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/master/contractTemplate/showList.js"></script>
<!--引用html编辑器kindeditor-->
<script charset="utf-8"
	src="${ctx}/resources/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8"
	src="${ctx}/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div  class="easyui-layout" style="width:100%;height:100%;">
	<div id="contract_toolbar" style="height:100px;">
		<form id="contract_search_queryForm" name="queryForm">
			<table style="border-collapse: separate; border-spacing: 15px;">
				<tr>
					<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input id="contract_channelId" name="channelId" /></td>
				</tr>
				<tr>
			        <td colspan="3">
			        <a class="easyui-linkbutton" iconCls="icon-search" onclick="contract_serach();" style="margin-left: 10px;margin-right: 10px">
			      		<span style="font-size:12px">查&nbsp;询</span>
			        </a>
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="contract_add()" style="margin-left: 10px;margin-right: 10px">
						<span style="font-size:12px">新&nbsp;增</span>
					</a>
			        </td>
		        </tr>
		    </table>
		</form>
	</div>
	 <div  data-options="region:'north'" style="height:100%;">
	 <!-- <form action="contractTemplate/upload.do" method="post" enctype="multipart/form-data">  
		<input type="file" name="file" /> <input type="submit" value="上传" />
		</form> -->
		<table id="contract_datagrid" toolbar="#contract_toolbar"style="width:auto;"></table>
	</div>
</div>

<!-- 修改模板 -->
<div id="editContractTemplate" title="修改模板"  class="easyui-window" closed="true" style="width:900px;height:700px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div style="margin:0 auto; width:700px; height:500px;">
	 <input type="hidden" id="editTemplate_nameCount">
	 <form id="templateEditForm">
		<table>
			<tr height="40px">
				<td>
				<span style="font-weight: bold;">渠道名称：</span><input type="text" class="easyui-textbox input" name="channelId" id="editTemplate_channelId" style="width:240px;">
				<span style="font-weight: bold;margin-left:97px">模板名称：</span><input type="text" class="easyui-textbox input" name="name" id="editTemplate_name" style="width:200px;">
				</td>
			</tr>
			<tr height="40px">
				<td>
				<span style="font-weight: bold;">打印类型：</span><input type="text" class="easyui-textbox input" name="printType" id="editTemplate_printType" style="width:240px;">
				<span style="font-weight: bold;margin-left:97px">模板代码：</span><input type="text" class="easyui-textbox input" name="code" id="editTemplate_code" style="width:200px;"  disabled="disabled">
				</td>
			</tr>
			<tr height="40px">	
				<td>
				<span style="font-weight: bold;">合同类型：</span><input type="text" class="easyui-textbox input" name="contractType" id="editTemplate_contractType" style="width:240px;">
						<span style="font-weight: bold;margin-left:97px">是否启用：</span>
				<input type="hidden" name="isDisabled" id="upIsDisabled" value="" />
					<input class="easyui-switchbutton" id="upIState" name="upIState" onText="开" offText="关">
				</td>
			</tr>
			<tr height="40px">
			    <td><span style="font-weight: bold;">合同模板内容：</span><textarea id="templateContent_update"
									name="templateContent"
									style="width: 700px; height: 300px; visibility: hidden;"></textarea>
								</td>
				<!-- <td>模板文件：<input type="file" name="file" id="editfile" style="width:180px;"/><input type="button" value="更新合同模板" onclick="uploadEditTemplate();"/></td> -->
			</tr>
			<tr>	
				<td><input type="hidden" name="templateUrl" id="templateEditUrl"></td>
				<td><input type="hidden" name="id" id="templateId"></td>
				<td><input type="hidden" name="contractChannelId" id="contractChannelId"></td>
			</tr>
		</table>
		</form>	
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		<div style=" margin:0 auto; width:180px; height:20px;">
			 <a class="easyui-linkbutton"  onclick="edit_contractTemplate();"   iconCls="icon-add"><span style="font-size:12px">确定</span></a>
		     <a class="easyui-linkbutton"  onclick="edit_cancel_contractTemplate()"   iconCls="icon-cancel"><span style="font-size:12px">返回</span></a>
		</div>	
		</div>  
	</div>	
</div>
<div id="addContractTemplate" title="新增模板"  class="easyui-window" closed="true" style="width:900px;height:750px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div style="margin:0 auto; width:700px; height:500px;">
	 	 <input type="hidden" id="addTemplate_nameCount">
	 <form id="templateAddForm" >
		<table>
			<tr height="40px">
				<td>
				<span style="font-weight: bold;">渠道名称：</span><input type="text" class="easyui-textbox input" name="channelId" id="addTemplate_channelId" style="width:240px;">
				<span style="font-weight: bold;margin-left:97px">模板名称：</span><input type="text" class="easyui-textbox input" name="name" id="addTemplate_name" style="width:200px;">
				</td>
			</tr>
			<tr height="40px">
				<td>
				<span style="font-weight: bold;">打印类型：</span><input type="text" class="easyui-textbox input" name="printType" id="addTemplate_printType" style="width:240px;">
				<span style="font-weight: bold;margin-left:97px">模板代码：</span><input type="text" class="easyui-textbox input" name="code" id="addTemplate_code" style="width:200px;">
				</td>
			</tr>
			<tr height="40px">	
				<td>
				<span style="font-weight: bold;">合同类型：</span><input type="text" class="easyui-textbox input" name="contractType" id="addTemplate_contractType" style="width:240px;">
				<span style="font-weight: bold;margin-left:97px">是否启用：</span>
				<input type="hidden" name="isDisabled" id="isDisabled" value="" />
				<input class="easyui-switchbutton" id="iState" name="iState" onText="开" offText="关">
				</td>
			</tr>
			<tr height="40px">
			<td><span style="font-weight: bold;">合同模板内容：</span><textarea id="templateContent"
									name="templateContent"
									style="width: 700px; height: 300px; visibility: hidden;"></textarea>
								</td>
				<!-- <td>模板文件：<input type="file" name="file" id="file" style="width:180px;" /> <input type="button" value="上传模板" onclick="uploadTemplate();"/></td> -->
			</tr>
			<tr>	
				<td><input type="hidden" name="templateUrl" id="templateUrl"></td>
			</tr>
		</table>
		</form>	
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		<div style=" margin:0 auto; width:180px; height:20px;">
			 <a class="easyui-linkbutton"  onclick="save_contractTemplate();"   iconCls="icon-add"><span style="font-size:12px">确定</span></a>
		      <a class="easyui-linkbutton"  onclick="cancel_contractTemplate()"   iconCls="icon-cancel"><span style="font-size:12px">返回</span></a>
		</div>	
		</div>  
	</div>	
</div>

<div id="contractTemp" class="easyui-window" title="合同模板"  closed="true" style="width:900px;height:700px;">   
    <div class="easyui-layout" data-options="fit:true">   
        <div data-options="region:'north',split:true" style="height:100px"></div>    
    </div>   
</div> 
