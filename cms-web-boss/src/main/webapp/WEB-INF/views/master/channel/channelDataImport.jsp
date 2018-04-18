<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/resources/js/master/channel/channelDataImport.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function showWindow(){
	$('#channelDataImport').window('open').window('refresh');
	$('#channelDataImportInfoForm').form('clear'); 
} 

</script>

<div id="channelToolbar" style="height:92px;padding-top:8px;">
		<div  style="padding:8px;border:solid 1px #99CCFF;background:#ededed;border-top-left-radius:5px;">
	 
        </div>
        <div style="margin: 7px 0 0px 0px;padding:5px;padding-left:5px;border:solid 1px #99CCFF;background:#ededed;border-top-left-radius:5px;">
	        <a class="easyui-linkbutton" onclick="showWindow()" iconCls="icon-edit">导入</a>
		</div>
</div>

<div id="channelDataImport" class="easyui-window" title="一键导入" data-options="top:'80px',modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
          <form id="channelDataImportInfoForm" name="channelDataImportInfoForm"  Method="post" enctype="multipart/form-data" style="background: #FFFFFF; padding-left:10px;text-align:left;">
        <div style="margin-bottom:20px;">
           <input name="channelDataLoadfile" id="channelDataLoadfile" class="easyui-filebox" data-options="prompt:'请选择一个excel文件'" style="width:100%;">
        </div>
        <div align="right">
            <a id="addBtn" class="easyui-linkbutton" style="width:100%" onclick="ajaxFileUpload();">上  传</a>
        </div>
        </form>
        
</div>
