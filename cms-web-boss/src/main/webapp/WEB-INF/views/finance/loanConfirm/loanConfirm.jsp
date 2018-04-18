<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
	<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/finance/loanConfirm/loanConfirm.js"></script>
<script type="text/javascript"
src="${ctx}/resources/js/finance/loanConfirm/comboboxList.js"></script>
<script type="text/javascript"
src="${ctx}/resources/easyui/plugins/jquery.form.js"></script>

<div id="queryLoanConfirmDiv" class="easyui-panel W100"
	data-options="collapsible:true" style="background-color:#EBEEF3">
	<input type="hidden" id="channelIdRequest" name="channelId" value="${channelId}">
	<input id ="userCode" name="userCode" type="hidden" value="${userCode}"/>
	 <input id ="userName" name="userName" type="hidden" value="${userName}"/>
	 <input id ="picFileDataUrl" name="picFileDataUrl" type="hidden" value="${picFileDataUrl}"/>
	 <input id ="queryCount" name="queryCount" type="hidden" value="0"/>
	<table style="border-collapse: separate; border-spacing: 15px;">
	
			<tr>
				<th >渠道名称:</th>
					<td><input id="loanConfirm_channelId" name="channelId" /></td>
				</tr>
			<tr>
				<th class="noClassLoanNo">借款编号:</th>
				<td class="noClassLoanNo"><input type="text" class="easyui-textbox input"
					name="loanNos" style="width: 180px"></td>
					<th class="noClassName">客户姓名:</th>
					<td style="text-align: left" class="noClassName"><input type="text" class="easyui-textbox input"
					name="name" style="width: 180px"></td>
			
					<th class="noClassIdNo">身份证号码:</th>
					<td style="text-align: left" class="noClassIdNo"><input type="text" class="easyui-textbox input"
					name="idNo" style="width: 180px"></td>
					
					<!-- <th class="noClassBatchNum">批次号:</th>
					<td style="text-align: left" class="noClassBatchNum"><input type="text" class="easyui-textbox input"
					name="batchNum" style="width: 180px"></td> -->
			
				<!-- 	<th>渠道名称:</th>
					<td><input id="loanConfirm_channelId" name="channelId" /></td> -->
					</tr>
					<tr>	
					<th class="noClassProductId">产品名称:</th>
					<td class="noClassProductId"><input id="loanConfirm_productId"
					name="productId" style="width: 180px"></td>
			
					<th class="noClassOrgId">交件类型:</th>
					<td style="text-align: left" class="noClassOrgId"><input id="loanConfirm_applyInputFlag" name="applyInputFlag" style="width: 180px"/></td> 
				
					<th class ="loanConfirm_undoneDate" >放款审核日期:</th>
					<td class ="loanConfirm_undoneDate" ><input id="loanConfirm_financeAuditDate" type="text" class="easyui-datebox"  style="width: 180px"></td>
					<td class="loanConfirm_undoneDate" style="text-align: center;font-weight:bolder; ">&nbsp;~&nbsp;</td> 
					<td class ="loanConfirm_undoneDate"><input id="loanConfirm_financeAuditDate2" type="text" class="easyui-datebox"  style="width: 180px"></td> 
				
					<th class ="loanConfirm_doneDate" >放款确认日期:</th>
					<td class ="loanConfirm_doneDate" ><input id="loanConfirm_loanDate" type="text" class="easyui-datebox"  style="width: 180px"></td>
					<td class="loanConfirm_doneDate" style="text-align:center;font-weight:bolder;width:40px">&nbsp;~&nbsp;</td> 
					<td class ="loanConfirm_doneDate"><input id="loanConfirm_loanDate2" type="text" class="easyui-datebox"  style="width: 180px"></td> 
				</tr>
			</table>
		<br />
		<div style="text-align: left; padding: 5px">
			<a class="easyui-linkbutton" iconCls="icon-search" id="sercBtn"
				onclick="query_loanConfirm_Info();">查询</a>
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="bacthPassConfirmBtn" 
			onclick="bacthPassLoanConfirm();">批量通过</a>
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="bacthBackConfirmBtn" 
			onclick="openBacthConfirmBackW();">批量退回</a> 
			<a class="easyui-linkbutton"  iconCls="icon-ok" id="exportLoanConfirmBtn" 
			onclick="exportLoanConfirmResult();">导出</a> 
			
			<!-- <a class="easyui-linkbutton" iconCls="icon-search" id="sercBtnOne"
			onclick="createOffer('linePayment/createOffer');">生成报盘文件</a> <a
			class="easyui-linkbutton" iconCls="icon-search" id="sercBtnTwo"
			onclick="exportOffer('linePayment/exportOffer');">导出报盘文件</a> <a
			class="easyui-linkbutton" iconCls="icon-search" id="sercBtnThree"
			onclick="openOfferWindow();">导入回盘文件</a>
			<a class="easyui-linkbutton"  iconCls="icon-search" id="uploadLoanFile" 
			onclick="uploadLoanFile();">导入已放款文件</a>
			<a class="easyui-linkbutton" iconCls="icon-search" id="uploadFileOne" onclick="lxxdOpenOfferWindow();">导入已放款文件</a> -->
		</div>
</div>

<div id="loanConfirmTT" class="easyui-tabs" style="height:80%;padding-top: 20px;">
    <div  title="待办任务" id="unDoneloanConfirm_Page"  data-options="region:'north'" style="height:80%;border:1px solid #95b8e7">
	<table id="unDoneloanConfirm_datagrid" toolbar="#loanConfirm_toolbar"style="width:auto;"></table>
	</div>
     <div  title="已完成" id="doneloanConfirm_Page"  data-options="region:'north'" style="height:80%;border:1px solid #95b8e7">
	<table id="doneloanConfirm_datagrid" toolbar="#loanConfirm_toolbar"style="width:auto;"></table>
	</div>
</div>

<div id="backLoanInfo" title="退回原因"  class="easyui-window" closed="true" style="width:700px;height:350px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div class="easyui-layout">
	 <input id ="backLoan_id" name="backLoan_id" type="hidden" />
	 <input id ="backLoan_loanNo" name="backLoan_loanNo" type="hidden"/>
	 <input id ="loanConfirm_remarkCount" name="loanConfirm_remarkCount" type="hidden"/>
	 <input id ="backLoan_isBacth" name="backLoan_isBacth" type="hidden"/>
	 <input id ="backLoan_channleCode" name="backLoan_channleCode" type="hidden"/>
	 <form id="backLoanForm" >
		<table id ="backLoan" >
			<tr height="40px">
			<td ><span style="color: red">*&nbsp;</span><span style="font-weight: bold">退回类型：</span></td>
			<td><input checked="checked" type="radio" value="0" name="loan_backType" id="loan_backType" />退回到合同签约</td><td></td>
			</tr>
			<tr height="40px" class="backLoan_name">
			<td  class="backLoan_name"><span style="font-weight: bold">&nbsp; 客户姓名：</span></td>
			<td class="backLoan_name"><span id="loan_name"></span></td><td></td>
			</tr>
			<tr height="40px">
				<td><span style="color: red">*&nbsp;</span><span style="font-weight: bold">一级原因：</span></td>
			<td><input type="text" class="easyui-textbox input" name="backLoan_firstLevleReasonsCode" id="backLoan_oneReason"></td>
				<td><span style="color: red">*&nbsp;</span><span style="font-weight: bold">二级原因：</span></td>
			<td><input type="text" class="easyui-textbox input" name="backLoan_twoLevleReasonsCode" id="backLoan_twoReason"></td>
			</tr>
			<tr height="100px" >
			<td valign="top">&nbsp; <span style="font-weight: bold">备注：&nbsp; </span></td>
			<td colspan="3"><textarea id="backLoan_remark" name="mytextarea" style="width:500px;height:80px;" ></textarea></td>
			</tr>
		</table>
		</form>	
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		<div style="margin:0 auto; width:180px; height:20px;">
			 <a class="easyui-linkbutton"  onclick="backLoan();"   iconCls="icon-add"><span style="font-size:12px">确定</span></a>
		     <a class="easyui-linkbutton"  onclick="common_closeWindow('#backLoanInfo')"   iconCls="icon-cancel"><span style="font-size:12px">返回</span></a>
		</div>	
		</div>  
	</div>	
</div>


<div id="LoanLogInfo" title="日志"  class="easyui-window" closed="true" style="width:600px;height:400px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div class="easyui-layout">
	  <input id ="LoanLog_loanNo" name="LoanLog_loanNo" type="hidden"/>
	  <table id="Loan_LoanLog"></table> 
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		</div>  
	</div>	
</div>

    <div id="importExcelWin" class="easyui-window" title="Excel导入"
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,modal:true,resizable:false"
        style="width: 550px; height: 200px; padding: 0px;">
        <div style="text-align:center;padding: 0px; margin: 50px 0px;">
            <form id="baseFileForm" enctype="multipart/form-data" method="post">
                <label>导入回盘文件：</label>
                <input class="easyui-filebox" id="offerFile" name="offerFile"
                    data-options="prompt:'请选择文件...',buttonText:'选择文件'" style="width: 300px" /> 
                <a href="javascript:importOffer('linePayment/importOffer');" class="easyui-linkbutton" id="batchImportHaTwoOffer" iconCls="pic_52" plain="false">导入</a>
            </form>
        </div>
    </div>

<%-- <div id="fileDataWin" title="附件"  class="easyui-window" closed="true" style="width:600px;height:400px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
	 <div data-options="region:'center',split:true" style="width:100%;">
	 <div class="easyui-layout">
	 
		</div>
		</div>
		<div data-options="region:'south',iconCls:'icon-reload'" style="height:20%;">
		</div>  
	</div>	
</div> --%>


<div id="DataImport" class="easyui-window" title="一键导入" data-options="top:'80px',modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
          <form id="DataImportInfoForm" name="DataImportInfoForm"  Method="post" enctype="multipart/form-data" style="background: #FFFFFF; padding-left:10px;text-align:left;">
        <div style="margin-bottom:20px;">
           <input id="Uploadfile" name="Uploadfile" class="easyui-filebox" data-options="prompt:'请选择一个excel文件'" style="width:100%;">
          
        </div>
        <div align="right" style="margin-top: 10px;">
            <a id="addBtn" class="easyui-linkbutton" style="width:100%" onclick="ajaxFileUpload();">上  传</a>
        </div>
        </form>
        
</div>

 <div id="lxxdImportExcel" class="easyui-window" title="Excel导入"
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,modal:true,resizable:false"
        style="width: 550px; height: 200px; padding: 0px;">
        <div style="text-align:center;padding: 0px; margin: 50px 0px;">
            <form id="lxxdBaseFileForm" enctype="multipart/form-data" method="post">
                <label>导入放款文件：</label>
                <input class="easyui-filebox" id="lxxdOfferFile" name="lxxdOfferFile"
                    data-options="prompt:'请选择文件...',buttonText:'选择文件'" style="width: 300px" /> 
                <a href="javascript:importLxxdOffer('loanConfirm/importLxxdOffer');" class="easyui-linkbutton" id="lxxdBatchImportHaTwoOffer" iconCls="pic_52" plain="false">导入</a>
            </form>
        </div>
    </div>
</body> 

</html>
