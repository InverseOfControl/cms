<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/common/easyuiUtils/comboboxUtils.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/master/channel/channelList.js"></script>
<style type="text/css">
.switchbutton-on { background: #EAF2FF; color: #000000; }  /*开时的样式*/  
.switchbutton-off { background: #E1E1E1; color: #000000; } /*关时的样式*/  
.clear { clear: both !important; }  
</style>
<div id="queryChannleDiv" class="easyui-panel W100" data-options="collapsible:true" style="height: 100px;">
	<table style="border-collapse: separate; border-spacing: 15px;">
		<tr align="left">
			<td><span style="margin-left: 10px;margin-right: 10px">渠道名称:</span><input id="cxId" type="text" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<a id="searchChannelBt" class="easyui-linkbutton" iconCls="icon-search" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">查&nbsp;询</span>
			</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addBMSChannelInfo()" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">新&nbsp;建</span>
			</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteBMSChannel()" href="#" style="margin-left: 10px;margin-right: 10px">
				<span style="font-size: 12px">删&nbsp;除</span>
			</a>
			</td>
		</tr>
	</table>
</div>
<div id="channelPage" data-options="region:'north'" style="height: 100%;">
	<table id="new_datagrid_channel" toolbar="#queryChannleDiv" style="width: auto;"></table>
</div>

<div id="addBMSChannelInfo" title="新增渠道" class="easyui-window"
	closed="true" style="width: 500px; height: 460px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="addBMSChannelInfoForm" name="addBMSChannelInfoForm"
			method="post" enctype="multipart/form-data" accept-charset="utf-8"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<table class="table_ui W80 center_m">
				<tr>
					<th>渠道CODE:</th>
					<td><input type="text" class="easyui-validatebox easyui-textbox input"
						data-options="validType:'integerCheck'"
						name="code" style="width: 180px"></td>
				</tr>
				<tr>
					<th>渠道名称:</th>
					<td><input type="text" class="easyui-textbox input"
						name="name" style="width: 180px"></td>
				</tr>
				<!-- <tr>
					<th>计算器:</th>
					<td><input type="file" id="fileImport" name="fileImport"
						style="width: 180px;" onchange="addFileUpload();"></td>
				</tr> -->
				<tr>
					<th>起售时间:</th>
					<td><input type="text" id="startSalesTimeAdd"
						name="startSalesTime" class="easyui-datebox input"
						style="width: 180px"></td>
				</tr>
				<tr>
					<th>停售时间:</th>
					<td><input type="text" id="stopSalesTimeAdd" name="stopSalesTime"
						class="easyui-datebox input" style="width: 180px"
						data-options="validType:'compareDate[\'#startSalesTimeAdd\']'"></td>
				</tr>
				<tr>
					<th>电子合同:</th>
					<td>
					<input type="hidden" name="econtractFlag" id="econtractFlag" value="1" />
					<input class="easyui-switchbutton" id="eContractIState" name="eContractIState" onText="开" offText="关">
					</td>
				</tr>
				<tr>
					<th>纸质合同:</th>
					<td>
					<input type="hidden" name="pcontractFlag" id="pcontractFlag" value="1" />
					<input class="easyui-switchbutton" id="pContractIState" name="pContractIState" onText="开" offText="关">
					</td>
				</tr>
				<tr>
					<th>是否启用:</th>
					<td>
					<input type="hidden" name="isDisabled" id="isDisabled" value="1" />
					<input class="easyui-switchbutton" id="iState" name="iState" onText="开" offText="关">
					</td>
					<!-- <td style="text-align: left">
					<input type="radio" value="1" name="isDisabled" id="1" />否
					<input type="radio" value="0" name="isDisabled" id="0" />是
					</td> -->
				</tr>
				
				<tr>
					<th>是否可优惠:</th>
					<td>
					<input type="hidden" name="isCanPreferential" id="isCanPreferential" value="0" />
					<input class="easyui-switchbutton" id="eIsCanPreferential" name="eIsCanPreferential" onText="关" offText="开">
					</td>
				</tr>
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveBMSChannelInfo();">保存</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="closechWindow('add');">取消</a>
		</div>
	</div>
</div>

<div id="updateBMSChannelInfo" title="修改渠道信息" class="easyui-window"
	closed="true" style="width: 500px; height: 460px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="updateBMSChannelInfoForm" name="updateBMSChannelInfoForm"
			method="post" enctype="multipart/form-data"
			style="background: #FFFFFF; padding-left: 10px; text-align: left;">
			<input type="hidden" name="upId" id="upId" />
			<table class="table_ui W80 center_m">
				<tr>
					<th>渠道CODE:</th>
					<td><input type="text" class="easyui-textbox input"
						name="code" id="code" style="width: 120px" disabled="disabled"></td>
				</tr>
				<tr>
					<th>渠道名称:</th>
					<td><input type="text" class="easyui-textbox input"
						name="name" id="name" style="width: 120px"></td>
				</tr>
				<!-- <tr>
					<th>计算器:</th>
					<td><input type="file" id="upFileImport" name="upFileImport"
						style="width: 180px;" onchange="updateFileUpload();"></td>
				</tr> -->
				<tr>
					<th>起售时间:</th>
					<td><input type="text" id="startSalesTimeUpdate"
						name="startSalesTime" class="easyui-datebox input"
						style="width: 120px"></td>
				</tr>
				<tr>
					<th>停售时间:</th>
					<td><input type="text" id="stopSalesTimeUpdate" name="stopSalesTime"
						class="easyui-datebox input" style="width: 120px"
						data-options="validType:'compareDate[\'#startSalesTimeUpdate\']'"></td>
				</tr>
				<tr>
					<th>电子合同:</th>
					<td style="text-align: left">
					<input type="hidden" name="upEcontractFlag" id="upEcontractFlag" value="" />
					<input class="easyui-switchbutton" id="upEContractIState" name="upEContractIState" onText="开" offText="关">
					</td>
				</tr>
				<tr>
					<th>纸质合同:</th>
					<td style="text-align: left">
					<input type="hidden" name="upPcontractFlag" id="upPcontractFlag" value="" />
					<input class="easyui-switchbutton" id="upPContractIState" name="upPContractIState" onText="开" offText="关">
					</td>
				</tr>
				<tr>
					<th>是否启用:</th>
					<td style="text-align: left">
					<input type="hidden" name="upIsDisabled" id="upIsDisabled" value="" />
					<input class="easyui-switchbutton" id="upIState" name="upIState" onText="开" offText="关">
					
					<!-- <input type="radio" value="1" name="upIsDisabled" id="1" />否 
					<input type="radio" value="0" name="upIsDisabled" id="0" />是 -->
					</td>
				</tr>
				
				
				
				<tr>
					<th>是否可优惠:</th>
					<td style="text-align: left">
					<input type="hidden" name="upIsCanPreferential" id="upIsCanPreferential" value="" />
					<input class="easyui-switchbutton" id="upsIsCanPreferential" name="upsIsCanPreferential"  onText="开" offText="关">
					</td>
				</tr>
				
			</table>
		</form>
		<br />
		<div style="text-align: center; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveUpdateBMSChannelInfo();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="closechWindow('upd');">取消</a>
		</div>
	</div>
</div>