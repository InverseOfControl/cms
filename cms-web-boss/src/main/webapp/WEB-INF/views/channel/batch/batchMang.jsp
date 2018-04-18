<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/common/channel.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/channel/batch/batchMang.js"></script>

<div id="queryLoanDiv" class="easyui-panel W100"
	data-options="collapsible:true" style="background-color: #EBEEF3">
	<form id="conditionForm" name="conditionForm">
		<table style="border-collapse: separate; border-spacing: 15px;">
			<tr>
				<th>渠道名称:</th>
				<td><input id="channelId" class="easyui-combobox"
					name="channelId"
					data-options="valueField:'code',textField:'name',url:'loanConfirm/findChannel',onSelect:channelSelect"></td>
				</td>
			</tr>
			<th>批次号:</th>
			<td><input type="text" class="easyui-textbox input"
				id="batchNum" name="batchNum" style="width: 180px" /></td>
			<th class="statusSearchClass">状态:</th>
			<td style="text-align: left" class="statusSearchClass"><select class="easyui-combobox"
				id="status" name="status" style="width: 120px;"
				data-options="editable:false,panelHeight:'auto'">
					<option value="">全部</option>
					<option value="02">已上传</option>
					<option value="01">未上传</option>
			</select></td>
			</tr>
		</table>
	</form>

	<br />
	<div style="text-align: left; padding: 5px">
		<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
			onclick="channel.queryTbl('conditionForm','tblBatchMang','');">查询</a>
	</div>
</div>
<div title="待办任务" id="unDoneloanAudit_Page"
	data-options="region:'north'"
	style="height: 80%; border: 1px solid #95b8e7;">
	<table id="tblBatchMang" class="easyui-datagrid"
		style="width: 100%; height: 100%;"
		data-options="rownumbers:true,singleSelect:true,pagination:true,pageList:$.fn.datagrid.defaults.pageList.concat(200),onLoadSuccess:gridLoadSuccess,loader:channel.datagrid.loader,fitColumns:true,url:'appForm/listPage',loadMsg:'数据加载中.....',method:'post'">
		<thead>
			<tr>
<!-- 				<th field="" align="center" checkbox="true">全选</th> -->
				<th field="batchNum"  width="10%" align="center" data-options="formatter:operateLoan">批次号</th>
				<th field="createTime" width="15%" align="center" data-options="formatter:channel.dataTimeFmt">生成时间</th>
				<th field="status" width="10%" align="center">状态</th>
				<th field="operate" width="62%" align="center" data-options="formatter:operateFmt">操作</th>
			</tr>
		</thead>
	</table>
</div>

    <div id="importExcelWin" class="easyui-window editContentPanel" title="划拨申请书导入"
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,modal:true,resizable:false"
        style="width: 610px; height: 250px; padding: 0px;">
        <div style="text-align: center; padding: 0px; margin: 20px 0px;">
            <form id="baseFileForm" enctype="multipart/form-data" method="post">
                <input type="hidden"  id="formbatchNum" name="batchNum" value=""/>
                <label>划拨申请书原文件(pdf)：</label>
                <input class="easyui-filebox" id="applyPdfEsignatureFile" name="applyEsignaturePdf"
                       data-options="prompt:'请选择文件...',buttonText:'选择文件'" style="width: 300px" />
                <a href="javascript:submitEsignatureBtn();" style="margin-right:-20px" class="easyui-linkbutton" id="submitEsignatureBtn" data-options="iconCls:'icon-ok'">签章</a>
                <br></br>
                <label style="margin-left:-50px">划拨申请书签章文件(pdf)：</label>
                <input class="easyui-filebox" id="applyPdfFile" name="applyFile"
                    data-options="prompt:'请选择文件...',buttonText:'选择文件'" style="width: 300px" /> 
                <br></br>
                <label>划拨申请书(xls)：</label>
                <input class="easyui-filebox" id="applyXlsFile" name="applyFile"
                    data-options="prompt:'请选择文件...',buttonText:'选择文件'" style="width: 300px" /> 
            </form>
            <br></br>
            <div style="text-align:center;padding-top:5px;">
                <a href="javascript:submitBtn();" class="easyui-linkbutton" id="submitBtn" data-options="iconCls:'icon-ok'">提交</a>
                <a href="javascript:closeBtn();" class="easyui-linkbutton" id="closeBtn" data-options="iconCls:'icon-cancel'">关闭</a>
            </div>
        </div>
    </div>
</body>

</html>
